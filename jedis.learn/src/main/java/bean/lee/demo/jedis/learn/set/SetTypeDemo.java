package bean.lee.demo.jedis.learn.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import bean.lee.demo.jedis.learn.RedisClient;
import redis.clients.jedis.Jedis;

public class SetTypeDemo {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new RedisClient().getJedis();
	}

	@Test
	public void test() {

		jedis.sadd("set01", "001", "002", "004");
		System.out.println("set01: " + jedis.smembers("set01").toString());
		jedis.sadd("set02", "001", "002", "003");
		System.out.println("set02: " + jedis.smembers("set02").toString());

		// 判断是否存在
		System.out.println(jedis.sismember("set01", "001"));
		System.out.println(jedis.sismember("set01", "006"));

		// 获取set02中有的而不在set01中的
		System.out.println(jedis.sdiff("set02", "set01").toString());

		// 移除
		jedis.srem("set01", "002");
		System.out.println("set01: " + jedis.smembers("set01").toString());

	}

	/**
	 * 测试并集
	 * 
	 * @author Dube
	 * @date 2015年12月18日 上午10:56:28
	 */
	@Test
	public void testSunionstore() {
		jedis.sadd("set01", "001", "002", "004");
		System.out.println("set01: " + jedis.smembers("set01").toString());
		jedis.sadd("set02", "001", "002", "003");
		System.out.println("set02: " + jedis.smembers("set02").toString());
		jedis.sadd("set03", "004", "006", "007");
		System.out.println("set03: " + jedis.smembers("set03").toString());

		Set<String> set= new HashSet<>();
		set.add("set01");
		set.add("set02");
		set.add("set03");
		String[] sets = set.toArray(new String[set.size()]);
		System.out.println(sets);
		
		jedis.sunionstore("set04", sets);
		System.out.println("set04: " + jedis.smembers("set04").toString());

		jedis.del("set01");
		jedis.del("set02");
		jedis.del("set03");
		jedis.del("set04");
	}
	
	@Test
	public void testSdiff() {
		jedis.sadd("set01", "001", "002", "004");
		System.out.println("set01: " + jedis.smembers("set01").toString());
		jedis.sadd("set02", "001", "002", "003");
		System.out.println("set02: " + jedis.smembers("set02").toString());

		System.out.println("set02-set01: " + jedis.sdiff("set02","set03").toString());
		System.out.println("set01-set02: " + jedis.sdiff("set01","set03").toString());

		jedis.del("set01");
		jedis.del("set02");
	}

}
