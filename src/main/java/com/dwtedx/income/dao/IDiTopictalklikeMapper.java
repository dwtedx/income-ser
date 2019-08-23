package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiTopictalklike;

public interface IDiTopictalklikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopictalklike record);

    int insertSelective(DiTopictalklike record);

    DiTopictalklike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopictalklike record);

    int updateByPrimaryKey(DiTopictalklike record);
}