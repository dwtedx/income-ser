package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dwtedx.income.pojo.DiScan;

public interface IDiScanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiScan record);

    int insertSelective(DiScan record);

    DiScan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiScan record);

    int updateByPrimaryKey(DiScan record);
    
    List<DiScan> selectIncomesByIncomeId(@Param("incomeId")Integer incomeId);
}