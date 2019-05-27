package com.dwtedx.income.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiParacontent;
import com.dwtedx.income.service.IDiParameterService;

@Controller
@RequestMapping("/para")
public class DiParaController {

	@Resource
	private IDiParameterService diParameterService;
	
	@ResponseBody
	@RequestMapping(value = "/getscan", method = RequestMethod.POST) 
	public ResultInfo toGetType(@RequestBody MessageInfo<BaseModel> model) throws DiException{
		
		//int paraId = model.getBody().getId();
		List<DiParacontent> diParacontents = this.diParameterService.getParacontentByScan();
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diParacontents);
		return resultInfo;
	}
}
