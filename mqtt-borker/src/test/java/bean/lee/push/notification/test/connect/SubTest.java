package bean.lee.push.notification.test.connect;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;

public class SubTest {

	public static final String BROKER_URL = "tcp://127.0.0.1:1883";
	public static final String TOPIC = "mqtt";
	private static final String CLIENT_ID = "c1";

	public static void main(String[] args) throws MqttException {
		Subscriber subscriber = new Subscriber(BROKER_URL, CLIENT_ID, TOPIC);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.trim().toLowerCase().equals("quit")) {
				break;
			}
		}
		subscriber.disconnect();
	}

}
