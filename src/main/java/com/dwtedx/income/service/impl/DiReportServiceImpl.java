package com.dwtedx.income.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiReportMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.ReportModel;
import com.dwtedx.income.pojo.DiReport;
import com.dwtedx.income.service.IDiReportService;

@Service("diReportService")
public class DiReportServiceImpl implements IDiReportService {

	@Resource
	private IDiReportMapper diReportMapper;
	
	private ModelMapper modelMapper;

	public DiReportServiceImpl() {
		modelMapper = new ModelMapper();
	}

	@Override
	public void saveReport(ReportModel model) throws DiException {
		// 保存topic
		if (model.getUserid() == 0) {
			throw new DiException("请先登录");
		}
		if (model.getReportid() == 0) {
			throw new DiException("请选择举报内容");
		}
		
		DiReport pojo = modelMapper.map(model, DiReport.class);
		pojo.setCreatetime(new Date());
		diReportMapper.insertSelective(pojo);
		
	}
	


}
