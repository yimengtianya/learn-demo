package bean.lee.push.notification.test.connect;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

/**
 * 测试连接
 * 
 * @author Dube
 * @date 2015年11月19日 下午3:20:23
 */
public class ConnectTest {

	@Test
	public void testConnect() throws MqttException {
		MqttClient client = new MqttClient("tcp://127.0.0.1:1883", "client_1");
		client.connect();
	}

}
