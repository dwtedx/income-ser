package com.dwtedx.income.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BudgetModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiBudget;
import com.dwtedx.income.service.IDiBudgetService;

@Controller
@RequestMapping("/budget")
public class DiBudgetController {

	@Resource
	private IDiBudgetService diBudgetService;
	
	@ResponseBody
	@RequestMapping(value = "/budgetbyid", method = RequestMethod.POST) 
	public ResultInfo toDiIncomeById(@RequestBody MessageInfo<BudgetModel> model){
			
		BudgetModel modelBody = model.getBody();
		int id = modelBody.getId();
		DiBudget budget = this.diBudgetService.getBudgetById(id);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(budget);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletebudget", method = RequestMethod.POST) 
	public ResultInfo toDeleteIncome(@RequestBody MessageInfo<BudgetModel> model){
		BudgetModel modelBody = model.getBody();
		int id = modelBody.getId();
		this.diBudgetService.deleteBudget(id);
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronize", method = RequestMethod.POST) 
	public ResultInfo toSynchronize(@RequestBody MessageInfo<ArrayList<BudgetModel>> model) throws DiException{
			
		ArrayList<BudgetModel> modelBody = model.getBody();
		this.diBudgetService.saveBudgets(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronizesingle", method = RequestMethod.POST) 
	public ResultInfo toSynchronizeSingle(@RequestBody MessageInfo<BudgetModel> model) throws DiException{
		
		BudgetModel modelBody = model.getBody();
		this.diBudgetService.saveBudgetSingle(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
}
