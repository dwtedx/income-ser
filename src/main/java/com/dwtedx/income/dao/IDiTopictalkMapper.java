package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;

import com.dwtedx.income.pojo.DiTopictalk;

@CacheNamespace(implementation = EhcacheCache.class)
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

	@Select("select t.id, t.topicid, t.userid, t.name, t.content, t.liked, c.head as remark, t.deleteflag, t.createtime, t.createuser, t.updatetime, t.updateuser from di_topictalk t left join di_userinfo c on t.userid = c.id where t.deleteflag = 0 and t.topicid = #{topicid,jdbcType=INTEGER} order by t.id desc;")
    List<DiTopictalk> selectDiTopicTalks(@Param("topicid")int topicid);
	
	@Select("select * from di_topictalk where deleteflag = 0 and topicid = #{topicid,jdbcType=INTEGER} and userid = #{userid,jdbcType=INTEGER};")
	List<DiTopictalk> selectDiTopictalkByUserId(@Param("topicid")int topicid, @Param("userid")int userid);
}