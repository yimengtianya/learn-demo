package bean.lee.demo.jedis.learn.hash;

import org.junit.Before;
import org.junit.Test;

import bean.lee.demo.jedis.learn.RedisClient;
import redis.clients.jedis.Jedis;

public class HashTypeDemo {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new RedisClient().getJedis();
	}

	@Test
	public void test() {
		jedis.hset("message1", "clinet1", "a");
		System.out.println(jedis.hget("message1", "clinet1"));
		System.out.println(jedis.hexists("message1", "clinet1"));
		System.out.println(jedis.exists("message1"));
		System.out.println(jedis.hgetAll("message1"));
	}

}
