package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiParameter;

public interface IDiParameterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiParameter record);

    int insertSelective(DiParameter record);

    DiParameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiParameter record);

    int updateByPrimaryKey(DiParameter record);
}