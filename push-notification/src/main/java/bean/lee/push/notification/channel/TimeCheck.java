package bean.lee.push.notification.channel;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 时间检测，用于清除超时的channel
 * 
 * @author Dube
 * @date 2015年11月17日 下午5:38:34
 */
public class TimeCheck extends Thread {

	/**
	 * 超时时间，默认5分钟
	 */
	private long outTime = 1000 * 60 * 5;

	private ChannelManage channelManage;

	public TimeCheck(ChannelManage channelManage) {
		this.channelManage = channelManage;
	}

	private Map<String, Long> channelTimeMap = new ConcurrentHashMap<String, Long>();

	public void add(String clientId) {
		channelTimeMap.put(clientId, new Date().getTime());
	}

	public void refreshTime(String clientId) {
		channelTimeMap.put(clientId, new Date().getTime());
	}

	private void check() {

	}

	public void run() {

	}

}
