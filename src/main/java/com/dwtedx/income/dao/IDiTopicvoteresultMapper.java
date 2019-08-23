package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiTopicvoteresult;

public interface IDiTopicvoteresultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicvoteresult record);

    int insertSelective(DiTopicvoteresult record);

    DiTopicvoteresult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicvoteresult record);

    int updateByPrimaryKey(DiTopicvoteresult record);
}