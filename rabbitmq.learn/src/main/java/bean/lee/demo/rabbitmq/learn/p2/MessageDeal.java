package bean.lee.demo.rabbitmq.learn.p2;

import java.io.IOException;

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
		String message = new String(body, "UTF-8");

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		//TODO 出错处理使用了消息拒绝，使消息从新回到队列，学习阶段，不知道其他人怎么做的，待修改
		
		// 模拟消息处理，20%的几率处理出错
		if (Math.random() > 0.8) {
			// 消息处理出错，拒绝消息， requeue为true使消息回到队列
			getChannel().basicReject(envelope.getDeliveryTag(), true);
			System.out.println("Consumer-" + number + ": [" + Thread.currentThread().getName() + "] Received-> '"
					+ message + "' deal error ");
			return;
		} else {
			// 处理成功
			getChannel().basicAck(envelope.getDeliveryTag(), false);
			// 消息处理成功，打印信息
			System.out.println(
					"Consumer-" + number + ": [" + Thread.currentThread().getName() + "] Received-> '" + message + "'");
		}

	}
}
