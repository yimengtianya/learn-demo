package bean.lee.push.web.conf;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQClientInit {
	
	public final static String TOPIC = "message";

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
		factory.setHost("192.168.142.129");
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

	@Bean
	public Channel getChannel() {
		connection();
		try {
			channel.exchangeDeclare(TOPIC, "fanout");
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		return channel;
	}


}
