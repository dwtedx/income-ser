package com.dwtedx.income.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.dwtedx.income.dao.IDiUserInfoMapper;
import com.dwtedx.income.dao.IDiUservipinfoMapper;
import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.UservipModel;
import com.dwtedx.income.pojo.DiUserInfo;
import com.dwtedx.income.pojo.DiUservipinfo;
import com.dwtedx.income.service.IDiVipInfoService;
import com.dwtedx.income.utility.ICConsants;

@Service("diVipInfoService")
public class DiVipInfoServiceImpl implements IDiVipInfoService {

	@Resource
	private IDiUservipinfoMapper diUservipinfoMapper;
	@Resource
	private IDiUserInfoMapper diUserInfoMapper;
	
	private ModelMapper modelMapper;

	public DiVipInfoServiceImpl() {
		modelMapper = new ModelMapper();
	}

	@Override
	public UservipModel getUserVipById(int id) {
		DiUservipinfo pojo = diUservipinfoMapper.selectByPrimaryKey(id);
		return modelMapper.map(pojo, UservipModel.class);
	}

	@Override
	public UservipModel saveUserVip(UservipModel model) throws DiException {
		
		DiUservipinfo uservipinfo = modelMapper.map(model, DiUservipinfo.class);
		if(uservipinfo.getId() > 0) {
			int id = diUservipinfoMapper.updateByPrimaryKeySelective(uservipinfo);
			if(id == 0) {
				throw new DiException("VIP信息异常");
			}
		}else {
			uservipinfo.setTypename("DD记账" + model.getMonths() + "个月VIP");
			model.setTypename("DD记账" + model.getMonths() + "个月VIP");
			//计算时间
			DiUserInfo userInviteUserInfo = diUserInfoMapper.selectByPrimaryKey(model.getUserid());
			//VIP记录 时间计算
			Date starttime = new Date();
			if (userInviteUserInfo.getVipflag() == ICConsants.VIP_TYPE_VIP) {
				starttime = userInviteUserInfo.getVipendtime();
            }
			Calendar rightNow = Calendar.getInstance();  
	        rightNow.setTime(starttime);  
	        rightNow.add(Calendar.MONTH, uservipinfo.getMonths());  
	        uservipinfo.setStarttime(starttime);
	        uservipinfo.setEndtime(rightNow.getTime());
			
			int id = diUservipinfoMapper.insertSelective(uservipinfo);
			if(id == 0) {
				throw new DiException("VIP信息保存异常");
			}
			//保存订单号
			//0:充值VIp 1:活动获得VIP
			if(uservipinfo.getType() == ICConsants.VIP_TYPE_BUY) {
				String ordercode = getOutTradeNo(uservipinfo.getId());
				DiUservipinfo codeVip = new DiUservipinfo();
				codeVip.setId(uservipinfo.getId());
				codeVip.setOrdercode(ordercode);
				uservipinfo.setOrdercode(ordercode);
				model.setOrdercode(ordercode);
				diUservipinfoMapper.updateByPrimaryKeySelective(codeVip);
			}
		}
		model.setId(uservipinfo.getId());
		return model;
		
	}

	@Override
	public List<UservipModel> getUserVipByUserId(int userId) {
		List<UservipModel> models = new ArrayList<UservipModel>();
		List<DiUservipinfo> pojos = diUservipinfoMapper.selectDiUserVipByUserId(userId);
		models = modelMapper.map(pojos, new TypeToken<List<UservipModel>>() {}.getType());
		return models;
	}

	@Override
	public UservipModel getUserVipByOrderCode(String code) {
		DiUservipinfo pojo = diUservipinfoMapper.selectDiUserVipByOrderCode(code);
		return modelMapper.map(pojo, UservipModel.class);
	}
	
	
	/**
	 * 要求外部订单号必须唯一。
	 * @return
	 */
	private String getOutTradeNo(int id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		String idStr = String.valueOf(id);
		while (idStr.length() < 5) {
			idStr = "0" + idStr;
		}
		return "DV" + key + idStr;
	}
	
}
