package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiUservipinfo;

public interface IDiUservipinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiUservipinfo record);

    int insertSelective(DiUservipinfo record);

    DiUservipinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiUservipinfo record);

    int updateByPrimaryKey(DiUservipinfo record);
    
    @Select("select * from di_uservipinfo WHERE deleteflag = 0 and userid = #{userId,jdbcType=INTEGER} order by id desc;")
    List<DiUservipinfo> selectDiUserVipByUserId(@Param("userId")int userId);
    
    @Select("select * from di_uservipinfo WHERE deleteflag = 0 and ordercode = #{code,jdbcType=VARCHAR};")
    DiUservipinfo selectDiUserVipByOrderCode(@Param("code")String code);
}