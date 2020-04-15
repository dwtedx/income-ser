package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiReport;

public interface IDiReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiReport record);

    int insertSelective(DiReport record);

    DiReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiReport record);

    int updateByPrimaryKey(DiReport record);
}