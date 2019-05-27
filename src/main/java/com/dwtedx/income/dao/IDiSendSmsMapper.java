package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.DiSendSms;

public interface IDiSendSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiSendSms record);

    int insertSelective(DiSendSms record);

    DiSendSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiSendSms record);

    int updateByPrimaryKey(DiSendSms record);
    
    DiSendSms selectByFristRow(String phone);
}