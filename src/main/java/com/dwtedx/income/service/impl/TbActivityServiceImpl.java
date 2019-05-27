package com.dwtedx.income.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.ITbActivityMapper;
import com.dwtedx.income.model.TaobaoActivityInfo;
import com.dwtedx.income.pojo.TbActivity;
import com.dwtedx.income.service.ITbActivityService;

@Service("tbActivityService")
public class TbActivityServiceImpl implements ITbActivityService {

	@Resource
	private ITbActivityMapper tbActivityMapper;

	@Override
	public TaobaoActivityInfo selectByLastActivity() {
		TaobaoActivityInfo taobaoActivityInfo = null;
		TbActivity tbActivity = tbActivityMapper.selectByLastActivity();
		if(null != tbActivity) {
			taobaoActivityInfo = new TaobaoActivityInfo();
			taobaoActivityInfo.setImgUlr(tbActivity.getImageurl());
			taobaoActivityInfo.setPathUrl(tbActivity.getPathurl());
			taobaoActivityInfo.setRemark(tbActivity.getRemark());
		}
		return taobaoActivityInfo;
	}

}
