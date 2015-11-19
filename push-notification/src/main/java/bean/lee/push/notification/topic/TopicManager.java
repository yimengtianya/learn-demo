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

	private static Map<String, Set<String>> topicChannelMap = new ConcurrentHashMap<>();

	synchronized public static void register(String topic, String channelId) {
		System.out.println("注册："+topic+"  "+channelId);
		if (exist(topic)) {
			topicChannelMap.get(topic).add(channelId);
		} else {
			Set<String> channelIds = new HashSet<>();
			channelIds.add(channelId);
			topicChannelMap.put(topic, channelIds);
		}
	}

	public static boolean exist(String topic) {
		return topicChannelMap.containsKey(topic);
	}

	public static Set<String> channelSubscirbedTopic(String topic) {
		return topicChannelMap.get(topic);
	}

	public static Set<String> topics() {
		return topicChannelMap.keySet();
	}

}
