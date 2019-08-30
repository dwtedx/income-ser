package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopiclike;

public interface IDiTopiclikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopiclike record);

    int insertSelective(DiTopiclike record);

    DiTopiclike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopiclike record);

    int updateByPrimaryKey(DiTopiclike record);
    
    @Select("select * from di_topiclike where topicid = #{topicid,jdbcType=INTEGER} and userid = #{userid,jdbcType=INTEGER};")
    List<DiTopiclike> selectDiTopiclikeByUserId(@Param("topicid")int topicid, @Param("userid")int userid);
}