package bean.lee.push.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.conf.Config;
import bean.lee.push.notification.jms.RabbitMQClient;
import bean.lee.push.notification.server.MqttServer;

/**
 * 程序入口
 * 
 * @author Dube
 * @date 2015年12月3日 下午3:04:56
 */
public class Main {

	private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		// 加载配置文件
		String config = null;
		if (args.length > 0) {
			config = args[0];
		} else {
			config = "conf/pn.properties";
		}
		Config.init(config);
		LOGGER.info(String.format("Server start at port %d", Config.mqttPort));
		// 初始化RabbitMQ客户端
		new RabbitMQClient().init();
		// 初始化Matt服务
		new MqttServer(Config.mqttPort).run();
	}

}
