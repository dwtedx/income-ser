package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopictalkimg;

public interface IDiTopictalkimgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopictalkimg record);

    int insertSelective(DiTopictalkimg record);

    DiTopictalkimg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopictalkimg record);

    int updateByPrimaryKey(DiTopictalkimg record);
	
	@Select("select * from di_topictalkimg where topictalkid = #{topictalkid,jdbcType=INTEGER};")
    List<DiTopictalkimg> selectDiTopictalkimgByTalk(@Param("topictalkid")int topictalkid);
}