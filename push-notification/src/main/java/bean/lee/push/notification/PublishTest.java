package bean.lee.push.notification;

import java.util.concurrent.TimeUnit;

import bean.lee.push.notification.publish.PublishManager;

public class PublishTest extends Thread {

	int i = 0;

	public void run() {
		System.out.println("pust test start");
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			PublishManager.publish("mqtt", "the message " + i);
		}
	}

}
