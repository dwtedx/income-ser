package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiExpexcel;

public interface IDiExpexcelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiExpexcel record);

    int insertSelective(DiExpexcel record);

    DiExpexcel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiExpexcel record);

    int updateByPrimaryKey(DiExpexcel record);

	@Select("select * from di_expexcel where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} order by id desc LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
	List<DiExpexcel> selectExpExcels(@Param("start")int start, @Param("length")int length, @Param("userid")int userid);
	
	@Select("select * from di_expexcel where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} order by id desc LIMIT 1;")    
	DiExpexcel selectByLastExp(@Param("userid")int userid);
	
}