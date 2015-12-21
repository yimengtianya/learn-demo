package bean.lee.demo.rabbitmq.learn.p7;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bean.lee.demo.rabbitmq.learn.EndPoint;

public class Consumer extends EndPoint implements Runnable {

	public int number;

	public Consumer(int number) {
		this.number = number;
	}

	/**
	 * 队列名称
	 */
	private static final String QUEUE_NAME = "logs7";

	public void run() {

		// 连接
		connection();

		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-message-ttl", 1000);
			channel.queueDeclare(QUEUE_NAME, false, false, false, args);
			//channel.queueDeclare(QUEUE_NAME, false, false, false, null);

			channel.basicConsume(QUEUE_NAME, true, new MessageDeal(channel, number));
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
