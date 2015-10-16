package bean.lee.demo.rabbitmq.learn.p1;

/**
 * 单发送单接收
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:44:06
 */
public class Test {

	public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

}
