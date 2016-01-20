package bean.lee.demo.sbmr.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.decorators.LruCache;

@CacheNamespace(implementation = org.mybatis.caches.redis.RedisCache.class, eviction = LruCache.class, flushInterval/* 单位ms */ = 1800000)
public interface PeopleMapper {

	@Options(useCache = true)
	@Select("SELECT * FROM CITY WHERE state = #{state}")
	public int add();

}
