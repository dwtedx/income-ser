package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.TbItemInfo;

public interface ITbItemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbItemInfo record);

    int insertSelective(TbItemInfo record);

    TbItemInfo selectByPrimaryKey(Integer id);
    
    TbItemInfo selectByTaobaoUid(String id);

    int updateByPrimaryKeySelective(TbItemInfo record);

    int updateByPrimaryKey(TbItemInfo record);
    
    List<TbItemInfo> selectByTopTaobaoItem(@Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    List<TbItemInfo> selectByNineNineItem(@Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    List<TbItemInfo> selectByRandItem();
    
    List<TbItemInfo> selectByCategorysItem(@Param("ids")Integer[] ids, @Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    List<TbItemInfo> selectByNotCategorysItem(@Param("ids")Integer[] ids, @Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    List<TbItemInfo> selectByCouponItem(@Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    List<TbItemInfo> selectByCategoryIdItem(@Param("cateId")Integer cateId, @Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);
    
    /**
     * 清空所有记录，并重新定义index
     */
    @Select("TRUNCATE TABLE tb_iteminfo")
    void deleteAll();
    
}