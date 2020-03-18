package com.dwtedx.income.service;

import java.util.List;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.UservipModel;

public interface IDiVipInfoService {

	public UservipModel getUserVipById(int id);

	public UservipModel saveUserVip(UservipModel model) throws DiException;

	public List<UservipModel> getUserVipByUserId(int userId);

	public UservipModel getUserVipByOrderCode(String code);

}
