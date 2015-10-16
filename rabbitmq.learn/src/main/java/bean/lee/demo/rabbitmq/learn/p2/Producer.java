package bean.lee.demo.rabbitmq.learn.p2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
	private static final String QUEUE_NAME = "lee";

	public void run() {

		//连接
		connection();
		
		try {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String message = "";
		for (int i = 0; i < 100; i++) {
			message = "message_" + i;
			try {
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//关闭连接
		close();
		
	}

}
