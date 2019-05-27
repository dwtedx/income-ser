package com.dwtedx.income.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.IdModel;
import com.dwtedx.income.model.TypeModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiType;
import com.dwtedx.income.service.IDiTypeService;

@Controller
@RequestMapping("/type")
public class DiTypeController {

	@Resource
	private IDiTypeService diTypeService;
	
	@ResponseBody
	@RequestMapping(value = "/addtype", method = RequestMethod.POST) 
	public ResultInfo toAddType(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		int id = this.diTypeService.saveType(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(new IdModel(id));
		return resultInfo;
	}

	@ResponseBody
	@RequestMapping(value = "/updatetype", method = RequestMethod.POST) 
	public ResultInfo toUpdateType(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		this.diTypeService.updateType(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletetype", method = RequestMethod.POST) 
	public ResultInfo toDeleteType(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		this.diTypeService.deleteType(modelBody.getId());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/gettype", method = RequestMethod.POST) 
	public ResultInfo toGetType(@RequestBody MessageInfo<TypeModel> model) throws DiException{
		
		TypeModel modelBody = model.getBody();
		List<DiType> diTypes = this.diTypeService.getTypeByuserId(modelBody.getId());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diTypes);
		return resultInfo;
	}
}
