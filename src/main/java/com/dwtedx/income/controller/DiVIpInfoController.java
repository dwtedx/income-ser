package com.dwtedx.income.controller;

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
import com.alipay.api.internal.util.AlipaySignature;
import com.dwtedx.income.alipay.AlipayConfig;
import com.dwtedx.income.alipay.AlipayModel;
import com.dwtedx.income.alipay.AlipayNotifyParam;
import com.dwtedx.income.alipay.AlipayTradeStatus;
import com.dwtedx.income.alipay.OrderInfoUtil2_0;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.filter.HttpHelper;
import com.dwtedx.income.filter.RequestVerifySingFilter;
import com.dwtedx.income.model.UservipModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiVipInfoService;

@Controller
@RequestMapping("/vipinfo")
public class DiVIpInfoController {

	@Resource
	private IDiVipInfoService diVipInfoService;

	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	private Logger logger = Logger.getLogger(RequestVerifySingFilter.class);

	@ResponseBody
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResultInfo toOrder(@RequestBody MessageInfo<UservipModel> model) throws DiException {

		// 测试金额0.01
		model.getBody().setPayaccount(new BigDecimal("0.01"));
		UservipModel codeModel = diVipInfoService.saveUserVip(model.getBody());

		// orderInfo 的获取必须来自服务端；
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(String.valueOf(codeModel.getPayaccount()),
				codeModel.getTypename(), codeModel.getOrdercode());
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.rsa_private_key, true);

		AlipayModel alipayModel = new AlipayModel();
		alipayModel.setOrderParam(orderParam);
		alipayModel.setSign(sign);

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(alipayModel);
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/ordernotify", method = RequestMethod.POST)
	public String toOrderNotify(HttpServletRequest request) throws DiException {

		Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
		String paramsJson = JSON.toJSONString(params);
		logger.info("支付宝回调：" + paramsJson);
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.signtype);
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
								UservipModel uservipModel = diVipInfoService
										.getUserVipByOrderCode(param.getOutTradeNo());
								uservipModel.setPaytotalaccount(param.getTotalAmount());
								uservipModel.setPaytime(param.getGmtPayment());
								uservipModel.setRemark(paramsJson);
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
	private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();

		String str = HttpHelper.getBodyString(request);

		// 感谢bojueyou指出的问题
		//判断str是否有值
		if (null == str || "".equals(str)) {
			return retMap;
		}
		//根据&截取
		String[] strings = str.split("&");
		// 设置HashMap长度
		int mapLength = strings.length;
		// 判断hashMap的长度是否是2的幂。
		if ((strings.length % 2) != 0) {
			mapLength = mapLength + 1;
		}

		// 循环加入map集合
		for (int i = 0; i < strings.length; i++) {
			// 截取一组字符串
			String[] strArray = strings[i].split("=");
			// strArray[0]为KEY strArray[1]为值
			retMap.put(strArray[0], strArray[1]);
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
		long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
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
