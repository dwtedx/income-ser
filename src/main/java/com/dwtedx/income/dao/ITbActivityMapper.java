package com.dwtedx.income.dao;

import com.dwtedx.income.pojo.TbActivity;

public interface ITbActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbActivity record);

    int insertSelective(TbActivity record);

    TbActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbActivity record);

    int updateByPrimaryKey(TbActivity record);
    
    TbActivity selectByLastActivity();
}