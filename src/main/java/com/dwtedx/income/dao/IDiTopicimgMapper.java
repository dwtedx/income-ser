package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopicimg;

public interface IDiTopicimgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicimg record);

    int insertSelective(DiTopicimg record);

    DiTopicimg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicimg record);

    int updateByPrimaryKey(DiTopicimg record);

	@Select("select * from di_topicimg where topicid = #{topicid,jdbcType=INTEGER} order by id desc;")
    List<DiTopicimg> selectInsTopicimgs(@Param("topicid")int topicid);
}