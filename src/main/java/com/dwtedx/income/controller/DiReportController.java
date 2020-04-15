package com.dwtedx.income.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.ReportModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiReportService;

@Controller
@RequestMapping("/report")
public class DiReportController {

	@Resource
	private IDiReportService diReportService;
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	public ResultInfo toUpdate(@RequestBody MessageInfo<ReportModel> model) throws DiException{
			
		this.diReportService.saveReport(model.getBody());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	
}
