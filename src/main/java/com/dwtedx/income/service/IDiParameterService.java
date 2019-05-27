package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.pojo.DiParacontent;

public interface IDiParameterService {
	
	/**
	 * 扫描小票商品名排除库
	 * @return List<DiParacontent>
	 */
	public List<DiParacontent> getParacontentByScan();
	
}
