package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiUserinviteinfo;

public interface IDiUserinviteinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiUserinviteinfo record);

    int insertSelective(DiUserinviteinfo record);

    DiUserinviteinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiUserinviteinfo record);

    int updateByPrimaryKey(DiUserinviteinfo record);
}