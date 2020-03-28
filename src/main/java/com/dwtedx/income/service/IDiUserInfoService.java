package com.dwtedx.income.service;

import java.util.Date;

import com.dwtedx.income.exception.DiException;
import com.dwtedx.income.model.PassWordModel;
import com.dwtedx.income.model.UserInfoModel;
import com.dwtedx.income.pojo.DiUserInfo;

public interface IDiUserInfoService {
		
	public DiUserInfo getUserById(int userId);
	
	public DiUserInfo getUserByOtherId(UserInfoModel userInfoModel, String clientsid);

	public DiUserInfo uploadPic(int userId, String imgData) throws DiException;
	
	public DiUserInfo updateUserName(int userId, String userName) throws DiException;
	
	public DiUserInfo updateUserInfo(int userId, UserInfoModel serInfoModel) throws DiException;
	
	public DiUserInfo updatePassWord(int userId, PassWordModel passWordModel) throws DiException;
	
	public DiUserInfo login(UserInfoModel serInfoModel) throws DiException;

	public DiUserInfo register(UserInfoModel modelBody, String clientsid) throws DiException;
	
	public DiUserInfo registerByPhone(UserInfoModel modelBody, String clientsid) throws DiException;
	
	public DiUserInfo getUserByOtherIdV2(UserInfoModel userInfoModel, String clientsid);
	
	public DiUserInfo updateUserPhone(int userId, UserInfoModel modelBody) throws DiException;

	public DiUserInfo reSetUserPass(int userId, UserInfoModel modelBody) throws DiException;

	public void updateUserVipInfo(int userid, Date endtime) throws DiException;
	
	public int getOpenVipCount() throws DiException;

}
