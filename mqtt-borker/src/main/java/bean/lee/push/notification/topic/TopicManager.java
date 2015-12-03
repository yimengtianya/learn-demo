package bean.lee.push.notification.topic;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 维护topic和channel关系
 * 
 * @author Dube
 * @date 2015年11月19日 下午5:54:06
 */
public class TopicManager {

	private Map<String, Set<String>> topicChannelMap = new ConcurrentHashMap<>();

	private static TopicManager topicManager = null;

	public static TopicManager instance() {
		if (topicManager == null) {
			topicManager = new TopicManager();
		}
		return topicManager;
	}

	public void register(String topic, String channelId) {
		if (exist(topic)) {
			topicChannelMap.get(topic).add(channelId);
		} else {
			Set<String> channelIds = new HashSet<>();
			channelIds.add(channelId);
			topicChannelMap.put(topic, channelIds);
		}
	}

	public boolean exist(String topic) {
		return topicChannelMap.containsKey(topic);
	}

	public Set<String> channelSubscirbedTopic(String topic) {
		return topicChannelMap.get(topic);
	}

	public Set<String> topics() {
		return topicChannelMap.keySet();
	}

	public void removeChannel(String channelId) {
		for (Map.Entry<String, Set<String>> entry : topicChannelMap.entrySet()) {
			entry.getValue().remove(channelId);
		}
	}

}
