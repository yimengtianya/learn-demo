package bean.lee.demo.rabbitmq.learn;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 用于和RabbitMQ服务器建立连接
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:48:17
 */
public abstract class EndPoint {

	private ConnectionFactory factory;

	private Connection connection;

	protected Channel channel;

	/**
	 * 连接
	 * 
	 * @author Dube
	 * @date 2015年10月16日 下午12:00:43
	 */
	protected void connection() {
		factory = new ConnectionFactory();
		factory.setHost("120.25.2.117");
		factory.setPort(5672);
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
	protected void close() {
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

}
