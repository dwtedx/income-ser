package com.dwtedx.income.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.dwtedx.income.alipay.AlipayConfig;
import com.dwtedx.income.alipay.AlipayModel;
import com.dwtedx.income.alipay.AlipayNotifyParam;
import com.dwtedx.income.alipay.AlipayTradeStatus;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.filter.RequestVerifySingFilter;
import com.dwtedx.income.model.UservipModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiUserInfoService;
import com.dwtedx.income.service.IDiVipInfoService;

@Controller
@RequestMapping("/vipinfo")
public class DiVIpInfoController {

	@Resource
	private IDiVipInfoService diVipInfoService;
	@Resource
	private IDiUserInfoService diUserInfoService;

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	private Logger logger = Logger.getLogger(RequestVerifySingFilter.class);

	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResultInfo toOrder(@RequestBody MessageInfo<UservipModel> model) throws DiException {

		// 测试金额0.01
		model.getBody().setPayaccount(new BigDecimal("0.01"));
		UservipModel codeModel = diVipInfoService.saveUserVip(model.getBody());

		// orderInfo 的获取必须来自服务端；
		// Map<String, String> params =
		// OrderInfoUtil2_0.buildOrderParamMap(String.valueOf(codeModel.getPayaccount()),
		// codeModel.getTypename(), codeModel.getOrdercode());
		// String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		// String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.rsa_private_key,
		// true);

		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.appid,
				AlipayConfig.rsa_private_key, AlipayConfig.format, AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.signtype);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel alipayModel = new AlipayTradeAppPayModel();
		alipayModel.setBody(codeModel.getTypename());
		alipayModel.setSubject(codeModel.getTypename());
		alipayModel.setOutTradeNo(codeModel.getOrdercode());
		alipayModel.setTimeoutExpress("30m");
		alipayModel.setTotalAmount(String.valueOf(codeModel.getPayaccount()));
		alipayModel.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(alipayModel);
		request.setNotifyUrl(AlipayConfig.notify_url);
		
		AlipayModel alipayBodyModel = new AlipayModel();
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			System.out.println(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
			
			alipayBodyModel.setOrderParam(response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new DiException("订单创建失败");
		}

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(alipayBodyModel);
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/ordernotify", method = RequestMethod.POST)
	public String toOrderNotify(HttpServletRequest request) throws DiException, UnsupportedEncodingException, AlipayApiException {

		Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
		String paramsJson = JSON.toJSONString(params);
		logger.info("支付宝回调：" + paramsJson);
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.signtype);
			if (signVerified) {
				logger.info("支付宝回调签名认证成功");
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				this.check(params);
				// 另起线程处理业务
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						AlipayNotifyParam param = buildAlipayNotifyParam(params);
						String trade_status = param.getTradeStatus();
						// 支付成功
						if (trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS)
								|| trade_status.equals(AlipayTradeStatus.TRADE_FINISHED)) {
							// 处理支付成功逻辑
							try {
								UservipModel uservipModel = diVipInfoService.getUserVipByOrderCode(param.getOutTradeNo());
								uservipModel.setPaytotalaccount(param.getTotalAmount());
								uservipModel.setPaytime(param.getGmtPayment());
								uservipModel.setRemark(paramsJson);
								diVipInfoService.saveUserVip(uservipModel);
								//用户表状态改为VIP，增加时长								
								diUserInfoService.updateUserVipInfo(uservipModel.getUserid(), uservipModel.getEndtime());
							} catch (Exception e) {
								logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
							}
						} else {
							logger.error("没有处理支付宝回调业务，支付宝交易状态：" + trade_status + ",params:" + paramsJson);
						}
					}
				});
				// 如果签名验证正确，立即返回success，后续业务另起线程单独处理
				// 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
				return "success";
			} else {
				logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:" + paramsJson);
				return "failure";
			}
		} catch (AlipayApiException e) {
			logger.error("支付宝回调签名认证失败,paramsJson:,errorMsg:" + paramsJson, e);
			return "failure";
		}

	}

	// 将request中的参数转换成Map
	private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> retMap = new HashMap<String, String>();

        Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
	}

	private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
		String json = JSON.toJSONString(params);
		return JSON.parseObject(json, AlipayNotifyParam.class);
	}

	/**
	 * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	 * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
	 * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
	 * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
	 * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
	 * 
	 * @param params
	 * @throws AlipayApiException
	 */
	private void check(Map<String, String> params) throws AlipayApiException {
		String outTradeNo = params.get("out_trade_no");

		// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		UservipModel order = diVipInfoService.getUserVipByOrderCode(outTradeNo); // 这个方法自己实现
		if (order == null) {
			throw new AlipayApiException("out_trade_no错误");
		}

		// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		long total_amount = new BigDecimal(params.get("total_amount")).longValue();
		if (total_amount != order.getPayaccount().longValue()) {
			throw new AlipayApiException("error total_amount");
		}

		// 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
		// 第三步可根据实际情况省略

		// 4、验证app_id是否为该商户本身。
		if (!params.get("app_id").equals(AlipayConfig.appid)) {
			throw new AlipayApiException("app_id不一致");
		}
	}
}
