package bean.lee.push.notification.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

/**
 * 管理接入的channel
 * 
 * 单立
 * 
 * @author Dube
 * @date 2015年11月17日 下午2:15:10
 */
public class ChannelManage {

	private final static Logger LOGGER = LogManager.getLogger(ChannelManage.class);

	private Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

	private TimeCheck timeCheck;

	private static ChannelManage channelManage = null;

	public static ChannelManage instance() {
		if (channelManage == null)
			channelManage = new ChannelManage();
		return channelManage;
	}

	private ChannelManage() {
		timeCheck = new TimeCheck(this);
		timeCheck.start();
	}

	public void add(String clientId, Channel channel) {
		// 不同步，最终一致
		map.put(clientId, channel);
		timeCheck.add(clientId);
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
		map.remove(clientId);
		timeCheck.remove(clientId);
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
		timeCheck.refreshTime(channel.id().toString());
	}

}
