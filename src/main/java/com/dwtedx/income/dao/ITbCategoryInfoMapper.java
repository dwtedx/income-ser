package com.dwtedx.income.dao;

import java.util.List;

import com.dwtedx.income.pojo.TbCategoryInfo;

public interface ITbCategoryInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCategoryInfo record);

    int insertSelective(TbCategoryInfo record);

    TbCategoryInfo selectByPrimaryKey(Integer id);
    
    TbCategoryInfo selectByCategoryName(String categoryName);
    
    List<TbCategoryInfo> selectByCategoryTop();

    int updateByPrimaryKeySelective(TbCategoryInfo record);

    int updateByPrimaryKey(TbCategoryInfo record);
}