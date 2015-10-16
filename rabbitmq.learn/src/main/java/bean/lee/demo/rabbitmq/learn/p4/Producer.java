package bean.lee.demo.rabbitmq.learn.p4;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import bean.lee.demo.rabbitmq.learn.EndPoint;

/**
 * 向mq中产生数据
 * 
 * @author Dube
 * @date 2015年10月15日 下午4:16:51
 */
public class Producer extends EndPoint implements Runnable {
	
	private static final String EXCHANGE_NAME = "direct_logs";

	public void run() {

		// 连接
		connection();

		try {
			 channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String message = "";
		for (int i = 0; i < 20; i++) {
			
			try {
				try {
					//广播模式，如果不等待，发布消息过快，看不到测试效果，（接收线程启动，消息已经发完了）
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String routingKey="info";
				if(Math.random()>0.5){
					routingKey="debug";
				}
				message = "message_" + i+"_"+routingKey;
				channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();

	}

}
