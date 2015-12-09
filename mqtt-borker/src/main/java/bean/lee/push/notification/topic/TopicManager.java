package bean.lee.push.notification.topic;

import java.util.HashSet;
import java.util.Set;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.data.RedisClient;

/**
 * 维护topic和channel关系(目前只维护在线的客户端)
 * 
 * @author Dube
 * @date 2015年11月19日 下午5:54:06
 */
public class TopicManager {

	/**
	 * 与消息主题构成持久化时主题集合的key
	 */
	public static final String TOPIC_HEADER = Config.serverName + "_TOPIC_";

	private static TopicManager topicManager = null;

	public static TopicManager instance() {
		if (topicManager == null) {
			topicManager = new TopicManager();
		}
		return topicManager;
	}

	public void register(String topic, String clientId) {
		RedisClient.instance().getJedis().sadd(TOPIC_HEADER + topic, clientId);
	}

	public boolean exist(String topic) {
		return RedisClient.instance().getJedis().exists(TOPIC_HEADER + topic);
	}

	public Set<String> clientIdsSubscirbedTopic(String topic) {
		return RedisClient.instance().getJedis().smembers(TOPIC_HEADER + topic);
	}

	public Set<String> topics() {
		Set<String> keys = RedisClient.instance().getJedis().keys(TOPIC_HEADER + "*");
		Set<String> topics = new HashSet<>();
		for (String key : keys) {
			topics.add(key.substring(TOPIC_HEADER.length()));
		}
		return topics;
	}

	public void removeClient(String clientId) {
		for (String topic : topics()) {
			RedisClient.instance().getJedis().srem(TOPIC_HEADER + topic, clientId);
		}
	}

}
