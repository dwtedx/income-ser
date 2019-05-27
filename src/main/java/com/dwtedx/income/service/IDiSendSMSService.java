package com.dwtedx.income.service;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.pojo.DiSendSms;

public interface IDiSendSMSService {
	
	public void sendSmsCode(String phone) throws DiException;
	
	public void sendSmsCodeHave(String phone) throws DiException;
	
	public DiSendSms selectByFristRow(String phone);
	
}
