package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.TypeModel;
import com.dwtedx.income.pojo.DiType;

public interface IDiTypeService {
	
	public DiType getTypeById(int userId);
	
	public int saveType(TypeModel modelBody) throws DiException;
	
	public void updateType(TypeModel modelBody) throws DiException;

	public void deleteType(int id);

	public List<DiType> getTypeByuserId(int id);
	
}
