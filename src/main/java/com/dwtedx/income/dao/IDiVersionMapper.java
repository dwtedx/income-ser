package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiVersion;

public interface IDiVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiVersion record);

    int insertSelective(DiVersion record);

    DiVersion selectByPrimaryKey(Integer id);
    
    DiVersion selectByreateTime();

    int updateByPrimaryKeySelective(DiVersion record);

    int updateByPrimaryKey(DiVersion record);
}