package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiTopictalkimg;

public interface IDiTopictalkimgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopictalkimg record);

    int insertSelective(DiTopictalkimg record);

    DiTopictalkimg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopictalkimg record);

    int updateByPrimaryKey(DiTopictalkimg record);
}