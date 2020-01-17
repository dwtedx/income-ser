package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;

import com.dwtedx.income.pojo.DiTopicimg;

@CacheNamespace(implementation = EhcacheCache.class)
public interface IDiTopicimgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopicimg record);

    int insertSelective(DiTopicimg record);

    DiTopicimg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopicimg record);

    int updateByPrimaryKey(DiTopicimg record);

	@Select("select * from di_topicimg where topicid = #{topicid,jdbcType=INTEGER} order by id asc;")
    List<DiTopicimg> selectInsTopicimgs(@Param("topicid")int topicid);
}