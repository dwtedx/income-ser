package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopicvote;

public interface IDiTopicvoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicvote record);

    int insertSelective(DiTopicvote record);

    DiTopicvote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicvote record);

    int updateByPrimaryKey(DiTopicvote record);

	@Select("select * from di_topicvote where deleteflag = 0 and topicid = #{topicid,jdbcType=INTEGER};")
    List<DiTopicvote> selectInsTopicvoteByTopicId(@Param("topicid")int topicid);
}