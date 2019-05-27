package com.dwtedx.income.dao;

import java.util.List;

import com.dwtedx.income.pojo.DiType;

public interface IDiTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiType record);

    int insertSelective(DiType record);

    DiType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiType record);

    int updateByPrimaryKey(DiType record);
    
    List<DiType> selectTypeByUserId(Integer userId);
}