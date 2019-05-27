package com.dwtedx.income.service;

import java.util.ArrayList;
import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.IncomeModel;
import com.dwtedx.income.model.RegainModel;
import com.dwtedx.income.pojo.DiIncome;

public interface IDiIncomeService {
	
	public DiIncome getIncomeById(int userId);
	
	public void deleteIncome(IncomeModel modelBody);
	
	public List<IncomeModel> saveIncomes(ArrayList<IncomeModel> modelBody) throws DiException;
	
	public void saveIncomeSingle(IncomeModel modelBody) throws DiException;

	public List<IncomeModel> getIncomesByUserId(RegainModel modelBody) throws DiException;

	public void deleteIncomeById(IncomeModel body) throws DiException;

	public void saveIncomeSingleById(IncomeModel modelBody) throws DiException;
	
	public IncomeModel saveIncomeScanSingleById(IncomeModel modelBody) throws DiException;
}
