package bean.lee.push.notification;

import bean.lee.push.notification.server.MainServer;

public class Main {

	// 测试：打开终端， telnet 127.0.0.1 8080
	public static void main(String[] args) throws Exception {
		System.out.println("服务器启动, tcp:127.0.0.1:8080");
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new MainServer(port).run();
	}

}
