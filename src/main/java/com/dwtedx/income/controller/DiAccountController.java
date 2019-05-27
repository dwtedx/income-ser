package com.dwtedx.income.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.AccountModel;
import com.dwtedx.income.model.IdModel;
import com.dwtedx.income.model.TypeModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiAccount;
import com.dwtedx.income.service.IDiAccountService;

@Controller
@RequestMapping("/account")
public class DiAccountController {

	@Resource
	private IDiAccountService diAccountService;
	
	@ResponseBody
	@RequestMapping(value = "/addaccount", method = RequestMethod.POST) 
	public ResultInfo toAddAccount(@RequestBody MessageInfo<AccountModel> model) throws DiException{
		
		AccountModel modelBody = model.getBody();
		int id = this.diAccountService.saveAccount(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(new IdModel(id));
		return resultInfo;
	}

	
	@ResponseBody
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST) 
	public ResultInfo toDeleteAccount(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		this.diAccountService.deleteAccount(modelBody.getId());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getaccount", method = RequestMethod.POST) 
	public ResultInfo toGetAccount(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		List<DiAccount> diAccounts = this.diAccountService.getAccountByuserId(modelBody.getId());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diAccounts);
		return resultInfo;
	}
	
}
