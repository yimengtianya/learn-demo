package bean.lee.push.notification.test.jms;

import java.io.IOException;

/**
 * 向mq中产生数据
 * 
 * @author Dube
 * @date 2015年10月15日 下午4:16:51
 */
public class Producer extends EndPoint implements Runnable {
	/**
	 * 队列名称
	 */
	private static final String TOPIC = "message";

	public void run() {

		// 连接
		connection();

		try {
			channel.exchangeDeclare(TOPIC, "fanout");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String message = "";
		for (int i = 0; i < 10; i++) {
			message = "message_" + i;
			try {
				try {
					//广播模式，如果不等待，发布消息过快，看不到测试效果，（接收线程启动，消息已经发完了）
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				channel.basicPublish(TOPIC, "", null, message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();

	}

}
