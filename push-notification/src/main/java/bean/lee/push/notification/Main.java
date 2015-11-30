package bean.lee.push.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.server.MqttServer;
import bean.lee.push.notification.zk.ZooKeeperClient;

public class Main {

	private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	// 测试：打开终端， telnet 127.0.0.1 8080
	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 1883;
		}
		LOGGER.info(String.format("Server start at port %d", port));
		new ZooKeeperClient().connect();
		new MqttServer(port).run();
	}

}
