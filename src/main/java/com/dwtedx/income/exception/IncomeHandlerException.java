package com.dwtedx.income.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.utility.ICConsants;

public class IncomeHandlerException implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(IncomeHandlerException.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			ex.printStackTrace();
			
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.getHead().setErrorCode(ICConsants.ERRORCODE_10001);
			if (ex instanceof DiException) {
				resultInfo.getHead().setMessage(((DiException) ex).getErrorMag());
				int errorCode = ((DiException) ex).getErrorCode();
				if(errorCode > 0){
					resultInfo.getHead().setErrorCode(((DiException) ex).getErrorCode());
				}
			} else if (ex instanceof MyBatisSystemException) {
				resultInfo.getHead().setMessage("服务器数据库连接失败，请稍后重试");
			} else if (ex instanceof Exception) {
				logger.error(ex.getMessage(), ex);
				resultInfo.getHead().setMessage("服务器跑偏了，请稍后重试");
			}

			// ObjectMapper mapper = new ObjectMapper();
			// mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
			// Boolean.TRUE);
			// String json = mapper.writeValueAsString(resultInfo);
			String json = JSON.toJSONString(resultInfo);

			OutputStream outputStream = response.getOutputStream();// 获取OutputStream输出流
			response.setHeader("content-type", "text/html;charset=UTF-8");// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
			response.setStatus(500);
			byte[] dataByteArr = json.getBytes("UTF-8");// 将字符转换成字节数组，指定以UTF-8编码进行转换
			outputStream.write(dataByteArr);// 使用OutputStream流向客户端输出字节数组

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
