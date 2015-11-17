package bean.lee.push.notification.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

/**
 * 管理接入的channel
 * 
 * @author Dube
 * @date 2015年11月17日 下午2:15:10
 */
public class ChannelManage {

	private Map<String, Channel> map = new ConcurrentHashMap<String, Channel>();

	private TimeCheck timeCheck;

	public ChannelManage() {
		timeCheck = new TimeCheck(this);
		timeCheck.run();
	}

	public void add(String clientId, Channel channel) {
		map.put(clientId, channel);
		System.out.println("接入：" + clientId + "  当前连接数：" + map.size());
	}

	public Channel get(String clientId) {
		return map.get(clientId);
	}

	public void remove(Channel channel) {
		for (Map.Entry<String, Channel> entry : map.entrySet()) {
			if (entry.getValue() == channel) {
				map.remove(entry.getKey());
			}
		}
		System.out.println("断开：" + channel.id().toString() + "  当前连接数：" + map.size());
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
