package bean.lee.demo.jedis.learn.keys;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import bean.lee.demo.jedis.learn.RedisClient;
import redis.clients.jedis.Jedis;

public class KeyOperationDemo {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new RedisClient().getJedis();
	}
	
	@Test
	public void test(){
		
		//匹配
		jedis.set("message_001", "hehe1");
		jedis.set("message_002", "hehe2");
		jedis.set("message_003", "hehe3");
		jedis.set("messag_001", "hehe3");
		System.out.println(jedis.keys("message_*").toString());
		System.out.println(jedis.keys("messag*001").toString());
		
		

		//设置超时时间 3秒
		jedis.expire("messag_001", 3);
		System.out.println(jedis.exists("messag_001"));
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jedis.exists("messag_001"));

		
	}

}
