package bean.lee.demo.rabbitmq.learn.p4;

/**
 * 按路线发送接收
 * <p>
 * 发送端按routing key发送消息，不同的接收端按不同的routing key接收消息。(与模式3发布订阅的区别是多了routing key)
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:44:06
 */
public class Test {

	public static void main(String[] args) {
		
		
		new Thread(new Producer()).start();
		new Thread(new Consumer("info")).start();
		new Thread(new Consumer("debug")).start();
		
		
		
	}

}
