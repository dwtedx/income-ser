package com.dwtedx.income.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.ScanModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.service.IDiScanService;

@Controller
@RequestMapping("/scan")
public class DiScanController {

	@Resource
	private IDiScanService diScanService;
	
	@ResponseBody
	@RequestMapping(value = "/deletescan", method = RequestMethod.POST) 
	public ResultInfo toDeleteType(@RequestBody MessageInfo<ScanModel> model) throws DiException{
		
		ScanModel modelBody = model.getBody();
		this.diScanService.deleteScan(modelBody.getServerid());
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	
}
