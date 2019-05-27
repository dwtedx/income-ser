package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiBudgetMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BudgetModel;
import com.dwtedx.income.pojo.DiBudget;
import com.dwtedx.income.service.IDiBudgetService;
import com.dwtedx.income.utility.CommonUtility;

@Service("diBudgetService")
public class DiBudgetService implements IDiBudgetService {
	
	@Resource
	private IDiBudgetMapper diBudgetMapper;

	@Override
	public DiBudget getBudgetById(int id) {
		return diBudgetMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveBudgets(List<BudgetModel> modelBody) throws DiException {
		DiBudget budget = null;
		for (BudgetModel budgetModel: modelBody) {
			if(0 == budgetModel.getUserid()){
				throw new DiException("数据同步需要先登录哦");
			}
		
			budget = new DiBudget();
			budget.setClientid(budgetModel.getId());
			budget.setMoneysum(budgetModel.getMoneysum());
			budget.setMoneylast(budgetModel.getMoneylast());
			budget.setUsername(budgetModel.getUsername());
			budget.setUserid(budgetModel.getUserid());
			budget.setYearnom(budgetModel.getYearnom());
			budget.setMonthnom(budgetModel.getMonthnom());
			budget.setRemark(budgetModel.getRemark());
			budget.setCreatetime(CommonUtility.dateToString(budgetModel.getCreatetime()));
			budget.setUpdatetime(CommonUtility.dateToString(budgetModel.getUpdatetime()));
			
			diBudgetMapper.insertSelective(budget);
		}
	}

	@Override
	public void deleteBudget(int id) {
		this.diBudgetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<DiBudget> getBudgetByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBudgetSingle(BudgetModel budgetModel) throws DiException{
		if(0 == budgetModel.getUserid()){
			throw new DiException("数据同步需要先登录哦");
		}
		//得到原来的记录
		DiBudget budget = diBudgetMapper.selectByClientidAndUserId(new DiBudget(budgetModel.getId(), budgetModel.getUserid()));
		if(null == budget){
			throw new DiException("该数据未同步到服务器哦");
		}
		budget.setClientid(budgetModel.getId());
		budget.setMoneysum(budgetModel.getMoneysum());
		budget.setMoneylast(budgetModel.getMoneylast());
		budget.setUsername(budgetModel.getUsername());
		budget.setUserid(budgetModel.getUserid());
		budget.setYearnom(budgetModel.getYearnom());
		budget.setMonthnom(budgetModel.getMonthnom());
		budget.setRemark(budgetModel.getRemark());
		budget.setCreatetime(CommonUtility.dateToString(budgetModel.getCreatetime()));
		budget.setUpdatetime(CommonUtility.dateToString(budgetModel.getUpdatetime()));
		diBudgetMapper.updateByPrimaryKeySelective(budget);
	}

	
	
	
}
