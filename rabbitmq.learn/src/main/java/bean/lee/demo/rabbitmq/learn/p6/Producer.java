package bean.lee.demo.rabbitmq.learn.p6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.caucho.hessian.io.HessianOutput;

import bean.lee.demo.rabbitmq.learn.EndPoint;

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
	private static final String EXCHANGE_NAME = "logs";

	public void run() {

		// 连接
		connection();

		try {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Message message;
		Set<String> topic = new HashSet<String>();
		topic.add("t1");
		topic.add("t2");
		for (int i = 0; i < 10; i++) {
			message = new Message(i, topic, "AND", "{\"title\":\"消息\",\"content\",\"消息内容\"}");
			try {
				try {
					// 广播模式，如果不等待，发布消息过快，看不到测试效果，（接收线程启动，消息已经发完了）
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				HessianOutput ho = new HessianOutput(os);
				ho.writeObject(message);
				channel.basicPublish(EXCHANGE_NAME, "", null, os.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();

	}

}
