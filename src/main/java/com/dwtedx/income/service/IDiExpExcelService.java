package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.BaseModel;
import com.dwtedx.income.model.ExpexcelModel;

public interface IDiExpExcelService {
	
	public List<ExpexcelModel> findExpexcels(BaseModel model, int userid);

	public ExpexcelModel findExpExcel(int id, int userid) throws DiException;
	
	public ExpexcelModel findLastExpExcel(int userId);
	
	public void deleteExpExcel(int id, int userid) throws DiException;
	
	public int poolCount(ExpexcelModel model) throws DiException;
	
	public void saveExpExcel(ExpexcelModel model) throws DiException;

}
