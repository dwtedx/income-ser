package com.dwtedx.income.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	
	/**
	     *    导出查询
	* @param name
	* @param start
	* @param length
	* @return
	*/
	@Select({"<script>",
	"select * from di_income i where 1 = 1",
	"and (i.deletefalag = 0 or i.deletefalag is null)",
	"<when test='userid != null and userid != \"\"'>",
	"and i.userid = #{userid,jdbcType=INTEGER}",
	"</when>",
	"<when test='role != null and role != \"\"'>",
	"and i.role = #{role,jdbcType=INTEGER}",
	"</when>",
	"<when test='moneysumstart != null and moneysumstart != \"\"'>",
	"and i.moneysum <![CDATA[ >= ]]> #{moneysumstart,jdbcType=DECIMAL}",
	"</when>",
	"<when test='moneysumend != null and moneysumend != \"\"'>",
	"and i.moneysum <![CDATA[ <= ]]> #{moneysumend,jdbcType=DECIMAL}",
	"</when>",
	"<when test='typeid != null'>",
	"and typeid in",
	"<foreach collection=\"typeid\" item=\"id\" index=\"index\" open=\"(\" close=\")\" separator=\",\">",
	  "#{id}",
	"</foreach>",
	"</when>",
	"<when test='accountid != null'>",
	"and accountid in",
	"<foreach collection=\"accountid\" item=\"id\" index=\"index\" open=\"(\" close=\")\" separator=\",\">",
	  "#{id}",
	"</foreach>",
	"</when>",
	"<when test='recordtimestart != null and recordtimestart != \"\"'>",
	"and i.recordtime <![CDATA[ >= ]]> #{recordtimestart,jdbcType=VARCHAR}",
	"</when>",
	"<when test='recordtimeend != null and recordtimeend != \"\"'>",
	"and i.recordtime <![CDATA[ <= ]]> #{recordtimeend,jdbcType=VARCHAR}",
	"</when>",
	"order by id desc;",
	"</script>"})
	List<DiIncome> selectByExpExcels(@Param("userid")Integer userid, @Param("role")Integer role, @Param("moneysumstart")BigDecimal moneysumstart, @Param("moneysumend")BigDecimal moneysumend, 
			@Param("typeid")String[] typeid, @Param("accountid")String[] accountid, @Param("recordtimestart")String recordtimestart, @Param("recordtimeend")String recordtimeend);

}