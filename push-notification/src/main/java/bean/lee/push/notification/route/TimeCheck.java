package bean.lee.push.notification.route;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.lee.push.notification.route.ChannelManage;

/**
 * 时间检测，用于清除超时的channel
 * 
 * @author Dube
 * @date 2015年11月17日 下午5:38:34
 */
public class TimeCheck extends Thread {

	private final static Logger LOGGER = LogManager.getLogger(TimeCheck.class);

	/**
	 * 超时时间，默认10分钟
	 */
	private long outTime = 1000 * 60 * 10;

	private static TimeCheck timeCheck = null;

	public static TimeCheck instance() {
		if (null == timeCheck)
			timeCheck = new TimeCheck();
		return timeCheck;
	}

	private TimeCheck() {
		start();
	}

	private Map<String, Long> channelTimeMap = new ConcurrentHashMap<String, Long>();

	public void add(String clientId) {
		channelTimeMap.put(clientId, new Date().getTime());
	}

	public void refreshTime(String clientId) {
		channelTimeMap.put(clientId, new Date().getTime());
	}

	public void remove(String clientId) {
		channelTimeMap.remove(clientId);
	}

	/**
	 * TODO 可用fork/join框架提升效率
	 * 
	 *
	 * @author Dube
	 * @date 2015年11月18日 上午9:45:37
	 */
	private void check() {
		LOGGER.debug(String.format("Chack over-time begin, channel map size %d", channelTimeMap.size()));
		Long timeNow = new Date().getTime();
		Set<String> clientIds = channelTimeMap.keySet();
		for (String clientId : clientIds) {
			Long time = channelTimeMap.get(clientId);
			if (time != null && ((timeNow - time) > outTime)) {
				// remove(clientId); 由ChannelManage调用
				ChannelManage.instance().remove(clientId);
			}
		}
		LOGGER.debug(String.format("Chack over-time end, channel map size %d", channelTimeMap.size()));

	}

	public void run() {
		while (true) {
			check();
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
