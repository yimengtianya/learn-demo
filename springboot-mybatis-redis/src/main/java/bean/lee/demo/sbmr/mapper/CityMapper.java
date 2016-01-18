/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package bean.lee.demo.sbmr.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cache.decorators.LruCache;

import bean.lee.demo.sbmr.domain.City;

/**
 * 测试缓存
 * 
 * @author Dube
 * @date 2016年1月15日 下午5:49:24
 */
@CacheNamespace(implementation = org.mybatis.caches.redis.RedisCache.class, eviction = LruCache.class, flushInterval/* 单位ms */ = 1800000)
public interface CityMapper {

	@Options(useCache = true)
	@Select("SELECT * FROM CITY WHERE state = #{state}")
	public City findByState(@Param("state") String state);

	@Options(flushCache = true)
	@Insert("INSERT INTO CITY (name, state, country) VALUES (#{name}, #{state}, #{country});")
	public int add(City city);

	@Options(flushCache = true)
	@Update("UPDATE CITY SET name=#{name}, state=#{state}, country=#{country} WHERE id=#{id}")
	public int update(City city);

}
