package bean.lee.demo.rabbitmq.learn.p5;

/**
 * 按topic发送接收
 * <p>
 * 使用场景：发送端不只按固定的routing key发送消息，而是按字符串“匹配”发送(由逗号隔开的单词)，接收端同样如此（*号和#进行匹配）。
 * 
 * @author Dube
 * @date 2015年10月16日 上午11:44:06
 */
public class Test {

	public static void main(String[] args) {

		// *匹配一个单词，#匹配0个或多个单词,不能匹配字符串的

		new Thread(new Producer()).start();
		new Thread(new Consumer("#")).start();
		new Thread(new Consumer("com.*")).start();
		new Thread(new Consumer("*.bb")).start();
		//

	}

}
