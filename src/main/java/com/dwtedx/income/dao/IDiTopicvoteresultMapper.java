package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dwtedx.income.pojo.DiTopicvoteresult;

public interface IDiTopicvoteresultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicvoteresult record);

    int insertSelective(DiTopicvoteresult record);

    DiTopicvoteresult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicvoteresult record);

    int updateByPrimaryKey(DiTopicvoteresult record);

    @Select("select * from di_topicvoteresult where topicid = #{topicid,jdbcType=INTEGER} and userid = #{userid,jdbcType=INTEGER};")
    List<DiTopicvoteresult> selectInsTopicvoteresultByUserId(@Param("topicid")int topicid, @Param("userid")int userid);

    @Select({"<script>",
    	"select count(*) as total from di_topicvoteresult where deleteflag = 0",
    	"<when test='topicid != null and topicid != 0 and topicid != \"\"'>",
        "and topicid = #{topicid,jdbcType=INTEGER}",
        "</when>",
        "<when test='topicvoteid != null and topicvoteid != 0 and topicvoteid != \"\"'>",
        "and topicvoteid = #{topicvoteid,jdbcType=INTEGER}",
        "</when>",
    	"</script>"})
    int selectInsTopicvoteresultCount(@Param("topicid")int topicid, @Param("topicvoteid")int topicvoteid);
}