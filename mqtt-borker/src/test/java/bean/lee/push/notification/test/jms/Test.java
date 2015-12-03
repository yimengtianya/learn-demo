package bean.lee.push.notification.test.jms;

/**
 * 发布订阅模式
 * <p>
 * 发送端发送广播消息，多个接收端接收,即广播消息，不需要使用queue，发送端不需要关心谁接收。
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:44:06
 */
public class Test {

	public static void main(String[] args) {
		
		
		new Thread(new Producer()).start();
		
		
		
	}

}
