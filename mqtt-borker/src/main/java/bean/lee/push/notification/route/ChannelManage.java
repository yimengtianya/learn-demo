package bean.lee.push.notification.route;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import bean.lee.push.notification.route.client.ClientManage;
import bean.lee.push.notification.topic.TopicManager;
import io.netty.channel.Channel;

/**
 * 管理接入的channel
 * 
 * 单立
 * 
 * @author Dube
 * @date 2015年11月17日 下午2:15:10
 */
public class ChannelManage {

	private final static Logger LOGGER = LoggerFactory.getLogger(ChannelManage.class);

	private Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

	private static ChannelManage channelManage = null;

	public static ChannelManage instance() {
		if (channelManage == null)
			channelManage = new ChannelManage();
		return channelManage;
	}

	private ChannelManage() {
	}

	public void add(String clientId, Channel channel) {
		// 不同步，最终一致
		map.put(clientId, channel);
		TimeCheck.instance().add(clientId);
		ClientManage.instance().add(clientId);
		LOGGER.debug(String.format("Add %s , Channel map size is %d", clientId, map.size()));
	}

	public Channel get(String clientId) {
		return map.get(clientId);
	}

	public void remove(Channel channel) {
		for (Map.Entry<String, Channel> entry : map.entrySet()) {
			if (entry.getValue() == channel) {
				remove(entry.getKey());
			}
		}
	}

	public void remove(String clientId) {
		Channel channel = map.get(clientId);
		if (channel != null && channel.isActive()) {
			channel.close();
		}
		synchronized (this) {
			map.remove(clientId);
			TimeCheck.instance().remove(clientId);
			TopicManager.instance().removeChannel(clientId);
			ClientManage.instance().remove(clientId);
		}
		LOGGER.debug(String.format("Remove %s , Channel map size is %d", clientId, map.size()));
	}

	/**
	 * 刷新
	 * 
	 * @param chanel
	 *
	 * @author Dube
	 * @date 2015年11月17日 下午6:02:42
	 */
	public void refresh(Channel channel) {
		TimeCheck.instance().refreshTime(channel.id().toString());
	}

	/**
	 * 判断channel是否仍存在
	 * 
	 * @param channel
	 * @return
	 *
	 * @author Dube
	 * @date 2015年11月20日 上午11:23:23
	 */
	public boolean exist(Channel channel) {
		if (channel == null)
			return false;
		return map.containsKey(channel.id().toString());
	}

}
