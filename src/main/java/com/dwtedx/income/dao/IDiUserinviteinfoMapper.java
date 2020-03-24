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
    
    @Select("select * from di_userinviteinfo where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} and status = #{status,jdbcType=INTEGER} order by id desc;")
    List<DiUserinviteinfo> selectUserinviteId(@Param("userid")int userid, @Param("status")int status);
    
    @Select("SELECT * FROM di_userinviteinfo WHERE invitephone = #{phone,jdbcType=VARCHAR} ORDER BY id desc LIMIT 1;")
    DiUserinviteinfo selectUserinviteByPhone(@Param("phone")String phone);
}