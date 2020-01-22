package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiUservipinfo;

public interface IDiUservipinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiUservipinfo record);

    int insertSelective(DiUservipinfo record);

    DiUservipinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiUservipinfo record);

    int updateByPrimaryKey(DiUservipinfo record);
}