package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dwtedx.income.pojo.DiParacontent;

public interface IDiParacontentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiParacontent record);

    int insertSelective(DiParacontent record);

    DiParacontent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiParacontent record);

    int updateByPrimaryKey(DiParacontent record);
    
    List<DiParacontent> selectParacontentByParaId(@Param("paraId")Integer paraId);
}