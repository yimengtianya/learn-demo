package bean.lee.push.notification.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.lee.push.notification.message.entity.Message;
import bean.lee.push.notification.message.publish.PublishManager;

/**
 * 监听zk上消息节点变化
 * 
 * @author Dube
 * @date 2015年11月27日 下午7:16:18
 */
public class MessageWatcher implements Watcher {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageWatcher.class);

	private ZooKeeper zooKeeper;

	public static final String MESSAGE_PATH = "/message";

	public MessageWatcher(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == EventType.NodeDataChanged) {
			try {
				String jsonMessage = new String(zooKeeper.getData(MESSAGE_PATH, true, null), "UTF-8");
				LOGGER.debug(jsonMessage);
				Message message = new ObjectMapper().readValue(jsonMessage, Message.class);
				PublishManager.publish(message.getTopic(), message.getContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}