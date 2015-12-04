package bean.lee.push.notification.jms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import bean.lee.push.notification.conf.Config;

/**
 * RabbitMQ客户端，未晚上，待增加连接失败重连
 * 
 * @author Dube
 * @date 2015年12月3日 下午2:18:18
 */
public class RabbitMQClient implements JmsClient {

	private final static String TOPIC = "message";

	private ConnectionFactory factory;

	private Connection connection;

	private Channel channel;

	/**
	 * 连接
	 * 
	 * @author Dube
	 * @date 2015年10月16日 下午12:00:43
	 */
	private void connection() {
		factory = new ConnectionFactory();
		factory.setHost(Config.mqIp);
		factory.setPort(Config.mqPort);
		try {
			connection = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		try {
			channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @author Dube
	 * @date 2015年10月16日 上午11:52:27
	 */
	private void close() {
		try {
			if (channel.isOpen())
				channel.close();
			if (connection.isOpen())
				connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {
		connection();
		try {
			channel.exchangeDeclare(TOPIC, "fanout");
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, TOPIC, "");
			channel.basicConsume(queueName, true, new MessageDeal(channel));
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}

}
