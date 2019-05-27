package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dwtedx.income.pojo.DiIncome;

public interface IDiIncomeMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteByClientidAndUserId(DiIncome record);

    int insert(DiIncome record);

    int insertSelective(DiIncome record);

    DiIncome selectByPrimaryKey(Integer id);
    
    DiIncome selectByClientidAndUserId(DiIncome record);

    int updateByPrimaryKeySelective(DiIncome record);

    int updateByPrimaryKey(DiIncome record);
    
    List<DiIncome> selectIncomesByUserId(@Param("userId")Integer userId, @Param("startNo")Integer startNo, @Param("lengthNo")Integer lengthNo);

	List<DiIncome> selectByRoleAndUserId(@Param("role")Integer role,@Param("userId") Integer userid);
}