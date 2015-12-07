package bean.lee.push.notification.route.client;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.data.RedisClient;

/**
 * 接入客户端管理,记录在线用户的标识
 * 
 * @author Dube
 * @date 2015年12月7日 下午4:08:23
 */
public class ClientManage {

	public final static String CLIENT_SET_KEY = Config.serverName + "_CLIENT";

	private static ClientManage clientManage;

	private ClientManage() {

	}

	public static ClientManage instance() {
		if (clientManage == null)
			clientManage = new ClientManage();
		return clientManage;
	}

	public void add(String clientId) {
		RedisClient.instance().getJedis().sadd(CLIENT_SET_KEY, clientId);
	}

	public void remove(String clientId) {
		RedisClient.instance().getJedis().srem(CLIENT_SET_KEY, clientId);
	}

}
