package bean.lee.demo.rabbitmq.learn.p5;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import bean.lee.demo.rabbitmq.learn.EndPoint;

public class Consumer extends EndPoint implements Runnable {

	public String routingKey;

	public Consumer(String routingKey) {
		this.routingKey = routingKey;
	}

	
	private static final String EXCHANGE_NAME = "topic_logs";

	public void run() {

		// 连接
		connection();

		try {

			//设置exchange的name和type
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			// 得到一个随机名称的Queue，该queue的类型为non-durable、exclusive、auto-delete的
			String queueName = channel.queueDeclare().getQueue();
			// 将该queue绑定到上面的exchange上接收消息。第三个参数RoutingKey为空，即所有的消息都接收。如果这个值不为空，在exchange
			// type为“fanout”方式下该值被忽略！
			channel.queueBind(queueName, EXCHANGE_NAME, routingKey);

			channel.basicConsume(queueName, true, new MessageDeal(channel, routingKey));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 使得MessageDeal对象能够存活10秒，在这10秒内来处理收到的消息
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// 关闭连接
		close();

	}

}
