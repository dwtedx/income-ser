package com.dwtedx.income.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.model.VersionModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiVersion;
import com.dwtedx.income.service.IDiVersionService;

@Controller
@RequestMapping("/version")
public class DiVersionController {

	@Resource
	private IDiVersionService diVersionService;
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public ResultInfo toUpdate(@RequestBody MessageInfo<VersionModel> model){
			
		VersionModel modelBody = model.getBody();
		DiVersion diVersion = this.diVersionService.selectByreateTime(modelBody.getVersionCode());
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diVersion);
		return resultInfo;
	}
	
	
}
