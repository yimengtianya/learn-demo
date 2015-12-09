package bean.lee.push.notification.message;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.data.RedisClient;
import bean.lee.push.notification.message.entity.Message;
import bean.lee.push.notification.message.publish.MessagePublishTask;
import bean.lee.push.notification.topic.TopicManager;
import redis.clients.jedis.Jedis;

/**
 * 消息推送
 * 
 * @author Dube
 * @date 2015年11月23日 上午10:30:00
 */
public class MessageManager {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

	public final static String MESSAGE_ACK_HEADER = "MESSAGE_ACK_";

	private static MessageManager messageManager = null;

	private ExecutorService threadPool = null;

	public static MessageManager instance() {
		if (messageManager == null) {
			messageManager = new MessageManager();
		}
		return messageManager;
	}

	private MessageManager() {
		threadPool = Executors.newFixedThreadPool(4);
	}

	/**
	 * 推送消息
	 * 
	 * @param message
	 * @author Dube
	 * @date 2015年12月8日 下午4:37:50
	 */
	public void publish(Message message) {
		// 获取订阅但未收到此消息的在线客户端ID
		Set<String> clientIds = RedisClient.instance().getJedis().sdiff(TopicManager.TOPIC_HEADER + message.getTopic(),
				MESSAGE_ACK_HEADER + String.valueOf(message.getId()));
		MessagePublishTask messagePublishTask = new MessagePublishTask(message, clientIds);
		threadPool.execute(messagePublishTask);
	}

	/**
	 * 接收反馈
	 * 
	 * @param message
	 * @param clientId
	 * @author Dube
	 * @date 2015年12月8日 下午4:38:06
	 */
	public void pubAck(int messageId, String clientId) {
		RedisClient.instance().getJedis().sadd(MESSAGE_ACK_HEADER + String.valueOf(messageId), clientId);
	}

}
