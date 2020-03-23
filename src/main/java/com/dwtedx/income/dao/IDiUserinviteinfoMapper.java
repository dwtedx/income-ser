package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiUserinviteinfo;

public interface IDiUserinviteinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiUserinviteinfo record);

    int insertSelective(DiUserinviteinfo record);

    DiUserinviteinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiUserinviteinfo record);

    int updateByPrimaryKey(DiUserinviteinfo record);
    
    @Select("select * from di_userinviteinfo where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} order by id desc;")
    List<DiUserinviteinfo> selectUserinviteId(@Param("userid")int userid);
}