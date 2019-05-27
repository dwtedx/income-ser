package com.dwtedx.income.dao;

import java.util.List;

import com.dwtedx.income.pojo.DiAccount;

public interface IDiAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiAccount record);

    int insertSelective(DiAccount record);

    DiAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiAccount record);

    int updateByPrimaryKey(DiAccount record);
    
    List<DiAccount> selectAccountByUserId(Integer userId);
}