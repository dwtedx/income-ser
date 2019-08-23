package com.dwtedx.income.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopictalk;

public interface IDiTopictalkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopictalk record);

    int insertSelective(DiTopictalk record);

    DiTopictalk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopictalk record);

    int updateByPrimaryKey(DiTopictalk record);
    
    @Select({"<script>",
    	"select count(*) as total from di_topictalk where deleteflag = 0",
    	"<when test='topicid != null and topicid != 0 and topicid != \"\"'>",
        "and topicid = #{topicid,jdbcType=INTEGER}",
        "</when>",
    	"</script>"})
    int selectTopictalkCount(@Param("topicid")int topicid);
}