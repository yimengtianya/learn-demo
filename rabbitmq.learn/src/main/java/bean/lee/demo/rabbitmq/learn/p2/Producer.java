package bean.lee.demo.rabbitmq.learn.p2;

import java.io.IOException;

import com.rabbitmq.client.MessageProperties;

import bean.lee.demo.rabbitmq.learn.EndPoint;

/**
 * 向mq中产生数据
 * 
 * @author Dube
 * @date 2015年10月15日 下午4:16:51
 */
public class Producer extends EndPoint implements Runnable {
	/**
	 * 队列名称
	 */
	private static final String QUEUE_NAME = "p2";

	public void run() {

		// 连接
		connection();

		try {
			// durable的属性为true，即使消息队列持久化
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String message = "";
		for (int i = 0; i < 100; i++) {
			message = "message_" + i;
			try {
				// MessageProperties.PERSISTENT_TEXT_PLAIN 持久化方式
				channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 关闭连接
		close();

	}

}
