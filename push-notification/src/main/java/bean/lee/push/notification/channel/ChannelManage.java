package bean.lee.push.notification.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.Channel;

/**
 * 管理接入的channel
 * 
 * @author Dube
 * @date 2015年11月17日 下午2:15:10
 */
public class ChannelManage {

	private final static Logger LOGGER = LogManager.getLogger(ChannelManage.class);

	private Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

	private TimeCheck timeCheck;

	public ChannelManage() {
		timeCheck = new TimeCheck(this);
		timeCheck.run();
	}

	public void add(String clientId, Channel channel) {
		map.put(clientId, channel);
		LOGGER.debug(String.format("Add %s , Channel size is %d", clientId, map.size()));
	}

	public Channel get(String clientId) {
		return map.get(clientId);
	}

	public void remove(Channel channel) {
		for (Map.Entry<String, Channel> entry : map.entrySet()) {
			if (entry.getValue() == channel) {
				map.remove(entry.getKey());
				LOGGER.debug(String.format("Remove %s , Channel size is %d", entry.getKey(), map.size()));
			}
		}
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
