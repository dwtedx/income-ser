package com.dwtedx.income.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiParacontentMapper;
import com.dwtedx.income.pojo.DiParacontent;
import com.dwtedx.income.service.IDiParameterService;

@Service("diParameterService")
public class DiParameterServiceImpl implements IDiParameterService {

	@Resource
	private IDiParacontentMapper diParacontentMapper;

	@Override
	public List<DiParacontent> getParacontentByScan() {
		List<DiParacontent> diParacontents = diParacontentMapper.selectParacontentByParaId(1);
		return diParacontents;
	}

}
