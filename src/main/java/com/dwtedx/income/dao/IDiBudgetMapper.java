package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiBudget;

public interface IDiBudgetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiBudget record);

    int insertSelective(DiBudget record);

    DiBudget selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiBudget record);

    int updateByPrimaryKey(DiBudget record);
    
    DiBudget selectByClientidAndUserId(DiBudget record);
}