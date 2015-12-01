package bean.lee.push.notification.test.data;

import org.junit.Before;
import org.junit.Test;

import bean.lee.push.notification.data.RedisClient;
import redis.clients.jedis.Jedis;

public class RedisClientTest {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = RedisClient.instance().getJedis();
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
