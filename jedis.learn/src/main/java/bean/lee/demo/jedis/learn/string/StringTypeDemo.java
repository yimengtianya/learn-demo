package bean.lee.demo.jedis.learn.string;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.lee.demo.jedis.learn.RedisClient;
import redis.clients.jedis.Jedis;

public class StringTypeDemo {

	private RedisClient redisClient;
	private Jedis jedis;

	@Before
	public void beforeClass() {
		redisClient = new RedisClient();
		jedis = redisClient.getJedisPool().getResource();
	}

	@Test
	public void test() {
		jedis.set("name", "hehe");
		System.out.println(jedis.get("name"));
		
	}

}
