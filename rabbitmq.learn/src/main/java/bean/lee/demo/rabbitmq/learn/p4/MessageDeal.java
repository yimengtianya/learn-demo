package bean.lee.demo.rabbitmq.learn.p4;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageDeal extends DefaultConsumer {

	public String routingKey;

	public MessageDeal(Channel channel, String routingKey) {
		super(channel);
		this.routingKey = routingKey;
	}

	/**
	 * 处理消息
	 */
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {
		String message = new String(body, "UTF-8");
		System.out.println(
				"Consumer-" + routingKey + ": [" + Thread.currentThread().getName() + "] Received-> '" + message + "'");
	}
}
