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
import com.dwtedx.income.model.ExpexcelModel;
import com.dwtedx.income.model.IdModel;
import com.dwtedx.income.model.TopicModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiExpExcelService;

@Controller
@RequestMapping("/expexcel")
public class DiExpExcelController {

	@Resource
	private IDiExpExcelService diExpExcelService;
	
	@ResponseBody
	@RequestMapping(value = "/findlist", method = RequestMethod.POST) 
	public ResultInfo toFindList(@RequestBody MessageInfo<BaseModel> model) throws DiException{
			
		List<ExpexcelModel> list = diExpExcelService.findExpexcels(model.getBody(), model.getHead().getUserId());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(list);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/find", method = RequestMethod.POST) 
	public ResultInfo toFind(@RequestBody MessageInfo<BaseModel> model) throws DiException{
			
		ExpexcelModel expexcelModel  = diExpExcelService.findExpExcel(model.getBody().getId(), model.getHead().getUserId());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(expexcelModel);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletetopic", method = RequestMethod.POST) 
	public ResultInfo toDeleteTopic(@RequestBody MessageInfo<TopicModel> model) throws DiException{
		
		diExpExcelService.deleteExpExcel(model.getBody().getId(), model.getBody().getUserid());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/pool", method = RequestMethod.POST) 
	public ResultInfo toPool(@RequestBody MessageInfo<ExpexcelModel> model) throws DiException{
			
		int count = diExpExcelService.poolCount(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(new IdModel(count));
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	public ResultInfo toSave(@RequestBody MessageInfo<ExpexcelModel> model) throws DiException{
			
		diExpExcelService.saveExpExcel(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
}
