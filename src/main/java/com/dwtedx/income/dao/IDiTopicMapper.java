package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopic;

public interface IDiTopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopic record);

    int insertSelective(DiTopic record);

    DiTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopic record);

    int updateByPrimaryKey(DiTopic record);
	
	@Select("select * from di_topic where deleteflag = 0 order by topping desc, id desc LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
    List<DiTopic> selectTopics(@Param("start")int start, @Param("length")int length);
	
	@Select("select * from di_topic where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} order by id desc LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
    List<DiTopic> selectMyTopics(@Param("start")int start, @Param("length")int length, @Param("userid")int userid);
}