package bean.lee.demo.rabbitmq.learn.p2;

import java.io.IOException;
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
	private static final String QUEUE_NAME = "p2";

	public void run() {

		// 连接
		connection();

		try {
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			
			// channel.basicQos(1)保证在接收端一个消息没有处理完时不会接收另一个消息，
			// 即接收端发送了ack后才会接收下一个消息。在这种情况下发送端会尝试把消息发送给下一个not
			// busy的接收端。
			channel.basicQos(1);
			
			// 接收消息时使autoAck为false，即不自动会发ack
			channel.basicConsume(QUEUE_NAME, false, new MessageDeal(channel,number));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 使得MessageDeal对象能够存活10秒，在这10秒内来处理收到的消息
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// 关闭连接
		close();

	}

}
