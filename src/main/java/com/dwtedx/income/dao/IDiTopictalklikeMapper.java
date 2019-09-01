package com.dwtedx.income.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopictalklike;

public interface IDiTopictalklikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopictalklike record);

    int insertSelective(DiTopictalklike record);

    DiTopictalklike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopictalklike record);

    int updateByPrimaryKey(DiTopictalklike record);	
	
	@Select("select * from di_topictalklike where topictalkid = #{topictalkid,jdbcType=INTEGER} and userid = #{userid,jdbcType=INTEGER};")
	DiTopictalklike selectDiTopictalklikeByTalkAndUser(@Param("topictalkid")int topictalkid, @Param("userid")int userid);
}