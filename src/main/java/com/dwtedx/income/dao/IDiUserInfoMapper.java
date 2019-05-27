package com.dwtedx.income.dao;

import org.apache.ibatis.annotations.Param;

import com.dwtedx.income.pojo.DiUserInfo;

public interface IDiUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiUserInfo record);

    int insertSelective(DiUserInfo record);

    DiUserInfo selectByPrimaryKey(Integer id);
    
    DiUserInfo selectByQQOpenId(String qqopenid);
    
    DiUserInfo selectByWeixinOpenId(String weixinopenid);
    
    DiUserInfo selectBySinaOpenId(String sinaopenid);
    
    DiUserInfo selectByPhone(String phone);

    int updateByPrimaryKeySelective(DiUserInfo record);

    int updateByPrimaryKey(DiUserInfo record);

	DiUserInfo selectByUserName(String useruame);
	
	DiUserInfo selectByUserNameAndPassWord(@Param("useruame")String useruame, @Param("password")String password);
}