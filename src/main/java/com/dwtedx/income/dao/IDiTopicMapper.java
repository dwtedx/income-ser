package com.dwtedx.income.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;

import com.dwtedx.income.pojo.DiTopic;

@CacheNamespace(implementation = EhcacheCache.class)
public interface IDiTopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiTopic record);

    int insertSelective(DiTopic record);

    @Options(useCache = false)
    DiTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiTopic record);

    int updateByPrimaryKey(DiTopic record);
	
	@Select("select * from di_topic where deleteflag = 0 and topping = 0 order by id desc LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
    List<DiTopic> selectTopics(@Param("start")int start, @Param("length")int length);
	
	@Select("select * from di_topic where deleteflag = 0 and topping = 1 order by id desc;")
    List<DiTopic> selectTopping();

	@Select("select * from di_topic where deleteflag = 0 and userid = #{userid,jdbcType=INTEGER} order by id desc LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
    List<DiTopic> selectMyTopics(@Param("start")int start, @Param("length")int length, @Param("userid")int userid);
}