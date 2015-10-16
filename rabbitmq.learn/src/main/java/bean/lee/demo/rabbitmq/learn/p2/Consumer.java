package bean.lee.demo.rabbitmq.learn.p2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;

import bean.lee.demo.rabbitmq.learn.EndPoint;

public class Consumer extends EndPoint implements Runnable {

	/**
	 * 队列名称
	 */
	private static final String QUEUE_NAME = "lee";

	public void run() {
		
		//连接
		connection();

		try {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.basicConsume(QUEUE_NAME, true, new MessageDeal(channel));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 使得MessageDeal对象能够存活100秒，在这100秒内来处理收到的消息
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		//关闭连接
		close();

	}

}
