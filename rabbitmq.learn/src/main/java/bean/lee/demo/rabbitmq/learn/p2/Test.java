package bean.lee.demo.rabbitmq.learn.p2;

/**
 * 单发送多接收
 * <p>
 * 使用场景：一个发送端，多个接收端，如分布式的任务派发。为了保证消息发送的可靠性，不丢失消息，使消息持久化了。同时为了防止接收端在处理消息时down掉，
 * 只有在消息处理完成后才发送ack消息。
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:44:06
 */
public class Test {

	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer(1)).start();
		new Thread(new Consumer(2)).start();
	}

}
