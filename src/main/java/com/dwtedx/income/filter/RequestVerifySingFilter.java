package com.dwtedx.income.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.utility.ICConsants;
import com.dwtedx.income.utility.MD5Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestVerifySingFilter extends OncePerRequestFilter {

	private static Logger logger = Logger.getLogger(RequestVerifySingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 防止流读取一次后就没有了, 所以需要将流继续写出去
		ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
		String json = HttpHelper.getBodyString(requestWrapper);
		logger.info("request:" + json);

		boolean isJson = isJson(json);

		// 不拦截 index.html
		if (isJson) {
			// 准备工作 传入vo请参照第一篇里面的实体。此处不再重新贴上代码 浪费大家时间
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);// 这里的JsonNode和XML里面的Node很像
			json = node.get("body").toString();// 获取voName

			node = node.get("head");// 获取voName
			String sing = node.get("sign").textValue();// 获取voName

			// MD5
			String md5Str = json + ";jFX024sn0gk08m8J630PJq7D787sWNnIQYLdwtedx199117??";
			md5Str = md5Str.replaceAll("/", "");
			md5Str = md5Str.replaceAll("\\\\", "");
			md5Str = MD5Util.stringToMD5(md5Str);
			// String strVal32 = MD5Util.getStringRandom(32);

			// 暂时放过一切请求
			// filterChain.doFilter(request, response);
			if (md5Str.equals(sing)) {
				filterChain.doFilter(requestWrapper, response);
			} else {
				try {
					ResultInfo resultInfo = new ResultInfo();
					resultInfo.getHead().setMessage("非法请求，肉机走开。");
					resultInfo.getHead().setErrorCode(ICConsants.ERRORCODE_10001);

					// ObjectMapper mapper = new ObjectMapper();
					// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
					// Boolean.TRUE);
					// String json = mapper.writeValueAsString(resultInfo);
					String jsonOut = JSON.toJSONString(resultInfo);

					response.setStatus(500);
					OutputStream outputStream = response.getOutputStream();// 获取OutputStream输出流
					response.setHeader("content-type", "text/html;charset=UTF-8");// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
					byte[] dataByteArr = jsonOut.getBytes("UTF-8");// 将字符转换成字节数组，指定以UTF-8编码进行转换
					outputStream.write(dataByteArr);// 使用OutputStream流向客户端输出字节数组

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			filterChain.doFilter(requestWrapper, response);
		}

	}

	private boolean isJson(String str) {
		if("".equals(str)) {
			return false;
		}
		try {
			JSONObject.parseObject(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
