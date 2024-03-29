package bean.lee.push.notification.data;

import java.util.ArrayList;
import java.util.List;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.route.ChannelManage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池

	private static RedisClient redisClient = null;

	public static RedisClient instance() {
		if (redisClient == null)
			redisClient = new RedisClient();
		return redisClient;
	}

	private RedisClient() {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();
	}

	public Jedis getJedis() {
		return jedis;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Config.redisPoolMaxTotal);
		config.setMaxIdle(Config.redisPoolMaxIdle);
		config.setMaxWaitMillis(Config.redisPoolMaxWaitMillis);
		config.setTestOnBorrow(Config.redisPoolTestOnBorrow);

		jedisPool = new JedisPool(config, Config.redisIp, Config.redisPort);
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.142.129", 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

}
