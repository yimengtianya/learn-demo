package bean.lee.demo.jedis.learn.set;

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
	public void test(){
		
		jedis.sadd("set01", "001","002","004");
		System.out.println("set01: "+jedis.smembers("set01").toString());
		jedis.sadd("set02", "001","002","003");
		System.out.println("set02: "+jedis.smembers("set02").toString());
		
		//判断是否存在
		System.out.println(jedis.sismember("set01", "001"));
		System.out.println(jedis.sismember("set01", "006"));
		
		//获取set02中有的而不在set01中的
		System.out.println(jedis.sdiff("set02","set01").toString());
		
		//移除
		jedis.srem("set01", "002");
		System.out.println("set01: "+jedis.smembers("set01").toString());
				
		
		
		
	}
	
	

}
