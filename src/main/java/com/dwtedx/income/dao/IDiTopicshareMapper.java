package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiTopicshare;

public interface IDiTopicshareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicshare record);

    int insertSelective(DiTopicshare record);

    DiTopicshare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicshare record);

    int updateByPrimaryKey(DiTopicshare record);
}