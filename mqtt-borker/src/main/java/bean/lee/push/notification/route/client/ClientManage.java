package bean.lee.push.notification.route.client;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.data.RedisClient;
import redis.clients.jedis.Jedis;

/**
 * 接入客户端管理,记录clientId与channelId对应关系
 * 
 * @author Dube
 * @date 2015年12月7日 下午4:08:23
 */
public class ClientManage {

	/**
	 * channelId-->clientId
	 */
	public final static String CLIENT_SET_KEY_CH2CL = Config.serverName + "_ONLINE_CLIENT_CH2CL";
	/**
	 * clientId-->channelId
	 */
	public final static String CLIENT_SET_KEY_CL2CH = Config.serverName + "_ONLINE_CLIENT_CL2CH";

	private static ClientManage clientManage;

	private ClientManage() {

	}

	public static ClientManage instance() {
		if (clientManage == null)
			clientManage = new ClientManage();
		return clientManage;
	}

	public void add(String channelId, String clientId) {
		synchronized (this) {
			RedisClient.instance().getJedis().hset(CLIENT_SET_KEY_CH2CL, channelId, clientId);
			RedisClient.instance().getJedis().hset(CLIENT_SET_KEY_CL2CH, clientId, channelId);
		}
	}

	public boolean exist(String clientId) {
		return RedisClient.instance().getJedis().hexists(CLIENT_SET_KEY_CL2CH, clientId);
	}

	public String getClientId(String channelId) {
		return RedisClient.instance().getJedis().hget(CLIENT_SET_KEY_CH2CL, channelId);
	}

	public void remove(String channelId) {
		String clientId = getClientId(channelId);
		synchronized (this) {
			RedisClient.instance().getJedis().hdel(CLIENT_SET_KEY_CH2CL, channelId);
			RedisClient.instance().getJedis().hdel(CLIENT_SET_KEY_CL2CH, clientId);
		}
	}

	public void removeByClinetId(String clientId) {
		String channelId = getClientId(clientId);
		synchronized (this) {
			RedisClient.instance().getJedis().hdel(CLIENT_SET_KEY_CH2CL, channelId);
			RedisClient.instance().getJedis().hdel(CLIENT_SET_KEY_CL2CH, clientId);
		}
	}

}
