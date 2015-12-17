package bean.lee.demo.rabbitmq.learn.p6;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageDeal extends DefaultConsumer {

	private int number;

	public MessageDeal(Channel channel, int number) {
		super(channel);
		this.number = number;
	}

	/**
	 * 处理消息
	 */
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {

		ByteArrayInputStream is = new ByteArrayInputStream(body);
		HessianInput hi = new HessianInput(is);

		Message message = (Message) hi.readObject();

		System.out.println(message.getMessageId() + "  " + message.getTopicOp() + "  " + message.getTopic().toString()
				+ "  " + message.getContent());

	}
}
