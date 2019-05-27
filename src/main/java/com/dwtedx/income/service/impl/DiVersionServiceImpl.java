package com.dwtedx.income.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiVersionMapper;
import com.dwtedx.income.pojo.DiVersion;
import com.dwtedx.income.service.IDiVersionService;

@Service("diVersionService")
public class DiVersionServiceImpl implements IDiVersionService {

	@Resource
	private IDiVersionMapper diVersionMapper;

	@Override
	public DiVersion selectByreateTime(int versioncode) {
		DiVersion diVersion = diVersionMapper.selectByreateTime();
		if(versioncode < diVersion.getVersioncode()){
			diVersion.setUpdate(true);
		}else {
			diVersion.setUpdate(false);
		}
		return diVersion;
	}
	


}
