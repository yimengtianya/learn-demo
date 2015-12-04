package bean.lee.push.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.jms.RabbitMQClient;
import bean.lee.push.notification.server.MqttServer;

/**
 * 程序入口
 * @author Dube
 * @date 2015年12月3日 下午3:04:56
 */
public class Main {

	private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		LOGGER.info(String.format("Server start at port %d", Config.mqttPort));
		new RabbitMQClient().init();
		new MqttServer(Config.mqttPort).run();
	}

}
