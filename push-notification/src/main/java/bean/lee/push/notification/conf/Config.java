package bean.lee.push.notification.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public static String zkAddress = "127.0.0.1:2181";

	public static String redisIp = "127.0.0.1";
	public static int redisPort = 6379;
	public static int redisPoolMaxTotal = 20;
	public static int redisPoolMaxIdle = 5;
	public static long redisPoolMaxWaitMillis = 1000L;
	public static boolean redisPoolTestOnBorrow = false;

	static {
		Properties prop = new Properties();
		InputStream stream = null;
		try {
			stream = new FileInputStream("conf/pn.properties");
			prop.load(stream);

			zkAddress = prop.getProperty("zookeeper").trim();
			redisIp = prop.getProperty("redis.ip").trim();
			redisPort = Integer.valueOf(prop.getProperty("redis.port").trim());
			redisPoolMaxTotal = Integer.valueOf(prop.getProperty("redis.pool.maxTotal").trim());
			redisPoolMaxIdle = Integer.valueOf(prop.getProperty("redis.pool.maxIdle").trim());
			redisPoolMaxWaitMillis = Long.valueOf(prop.getProperty("redis.pool.maxWaitMillis").trim());
			redisPoolTestOnBorrow = Boolean.valueOf(prop.getProperty("redis.pool.testOnBorrow").trim());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
