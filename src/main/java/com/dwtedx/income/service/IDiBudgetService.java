package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BudgetModel;
import com.dwtedx.income.pojo.DiBudget;

public interface IDiBudgetService {
	
	public DiBudget getBudgetById(int id);
	
	public void saveBudgets(List<BudgetModel> modelBody) throws DiException;

	public void deleteBudget(int id);

	public List<DiBudget> getBudgetByUserId(int id);

	public void saveBudgetSingle(BudgetModel modelBody) throws DiException;
	
}
