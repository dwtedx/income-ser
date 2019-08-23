package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiTopicvote;

public interface IDiTopicvoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicvote record);

    int insertSelective(DiTopicvote record);

    DiTopicvote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicvote record);

    int updateByPrimaryKey(DiTopicvote record);
}