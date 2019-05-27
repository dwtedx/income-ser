package com.dwtedx.income.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.UserInfoModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.impl.DiSendSMSService;

@Controller
@RequestMapping("/sms")
public class DiSendSmsController {

	@Resource
	private DiSendSMSService diSendSMSService;
	
	@ResponseBody
	@RequestMapping(value = "/sendsms", method = RequestMethod.POST) 
	public ResultInfo toSendSms(@RequestBody MessageInfo<UserInfoModel> model) throws DiException{
			
		UserInfoModel modelBody = model.getBody();
		this.diSendSMSService.sendSmsCode(modelBody.getPhone());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendsmshave", method = RequestMethod.POST) 
	public ResultInfo toSendSmsHave(@RequestBody MessageInfo<UserInfoModel> model) throws DiException{
			
		UserInfoModel modelBody = model.getBody();
		this.diSendSMSService.sendSmsCodeHave(modelBody.getPhone());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
}
