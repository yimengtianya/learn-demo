package bean.lee.push.notification.message;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.message.entity.Message;
import bean.lee.push.notification.message.publish.MessagePublishTask;
import bean.lee.push.notification.topic.TopicManager;

/**
 * 消息推送
 * 
 * @author Dube
 * @date 2015年11月23日 上午10:30:00
 */
public class MessageManager {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

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

	public void publish(Message message) {
		Set<String> clientIds = TopicManager.instance().channelSubscirbedTopic(message.getTopic());
		MessagePublishTask messagePublishTask = new MessagePublishTask(message, clientIds);
		threadPool.execute(messagePublishTask);
	}

}
