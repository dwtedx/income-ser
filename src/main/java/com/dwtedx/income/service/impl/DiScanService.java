package com.dwtedx.income.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiScanMapper;
import com.dwtedx.income.pojo.DiScan;
import com.dwtedx.income.service.IDiScanService;
import com.dwtedx.income.utility.ICConsants;

@Service("diScanService")
public class DiScanService implements IDiScanService {
	
	@Resource
	private IDiScanMapper diScanMapper;

	@Override
	public void deleteScan(int id) {
		DiScan scan = diScanMapper.selectByPrimaryKey(id);
		scan.setDeletefalag(ICConsants.DELETEFALAG_DELETEED);
		diScanMapper.updateByPrimaryKey(scan);
	}



}
