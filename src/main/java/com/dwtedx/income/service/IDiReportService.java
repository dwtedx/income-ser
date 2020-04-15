package com.dwtedx.income.service;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.ReportModel;

public interface IDiReportService {
	
	public void saveReport(ReportModel body) throws DiException;

}
