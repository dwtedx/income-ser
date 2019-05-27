package com.dwtedx.income.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.IncomeModel;
import com.dwtedx.income.model.RegainModel;
import com.dwtedx.income.model.common.MessageInfo;
import com.dwtedx.income.model.common.ResultInfo;
import com.dwtedx.income.pojo.DiIncome;
import com.dwtedx.income.service.IDiIncomeService;

@Controller
@RequestMapping("/income")
public class DiIncomeController {

	@Resource
	private IDiIncomeService diIncomeService;
	
	@ResponseBody
	@RequestMapping(value = "/incomebyid", method = RequestMethod.POST) 
	public ResultInfo toDiIncomeById(@RequestBody MessageInfo<IncomeModel> model){
			
		IncomeModel modelBody = model.getBody();
		int diIncomeId = modelBody.getId();
		DiIncome diIncome = this.diIncomeService.getIncomeById(diIncomeId);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(diIncome);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteincome", method = RequestMethod.POST) 
	public ResultInfo toDeleteIncome(@RequestBody MessageInfo<IncomeModel> model){
		
		this.diIncomeService.deleteIncome(model.getBody());
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronize", method = RequestMethod.POST) 
	public ResultInfo toSynchronize(@RequestBody MessageInfo<ArrayList<IncomeModel>> model) throws DiException{
			
		ArrayList<IncomeModel> modelBody = model.getBody();
		this.diIncomeService.saveIncomes(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronizeid", method = RequestMethod.POST) 
	public ResultInfo toSynchronizeid(@RequestBody MessageInfo<ArrayList<IncomeModel>> model) throws DiException{
			
		ArrayList<IncomeModel> modelBody = model.getBody();
		List<IncomeModel> synchronizeIncome = this.diIncomeService.saveIncomes(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(synchronizeIncome);
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronizesingle", method = RequestMethod.POST) 
	public ResultInfo toSynchronizeSingle(@RequestBody MessageInfo<IncomeModel> model) throws DiException{
		
		IncomeModel modelBody = model.getBody();
		this.diIncomeService.saveIncomeSingle(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/synchronizeegain", method = RequestMethod.POST) 
	public ResultInfo toSynchronizeRegain(@RequestBody MessageInfo<RegainModel> model) throws DiException{
			
		RegainModel modelBody = model.getBody();
		List<IncomeModel> incomes = this.diIncomeService.getIncomesByUserId(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(incomes);
		return resultInfo;
	}
	
	/**
	 * 2017-11-2 新增 通过服务器id删除
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteincomeid", method = RequestMethod.POST) 
	public ResultInfo toDeleteIncomeById(@RequestBody MessageInfo<IncomeModel> model) throws DiException{
		
		this.diIncomeService.deleteIncomeById(model.getBody());
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	/**
	 * 2017-11-2 新增 通过服务器id修改
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/synchronizesingleid", method = RequestMethod.POST) 
	public ResultInfo toSynchronizeSingleById(@RequestBody MessageInfo<IncomeModel> model) throws DiException{
		
		IncomeModel modelBody = model.getBody();
		this.diIncomeService.saveIncomeSingleById(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		return resultInfo;
	}
	
	/**
	 * 2019-02-1 新增 通过服务器id修改 并 返回 数据和扫单数据
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveincomescansingleid", method = RequestMethod.POST) 
	public ResultInfo saveIncomeScanSingleById(@RequestBody MessageInfo<IncomeModel> model) throws DiException{
		
		IncomeModel modelBody = model.getBody();
		IncomeModel resultIncome = this.diIncomeService.saveIncomeScanSingleById(modelBody);
		
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setBody(resultIncome);
		return resultInfo;
	}
}
