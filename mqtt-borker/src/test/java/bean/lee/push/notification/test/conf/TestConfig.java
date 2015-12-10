package bean.lee.push.notification.test.conf;

import org.junit.Test;

import bean.lee.push.notification.conf.Config;

public class TestConfig {

	@Test
	public void test() {
		System.out.println(Config.zkAddress);
	}

	@Test
	public void testPath() {
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(Config.class.getClassLoader().getResource(""));
		System.out.println(Config.class.getResource("/"));
		System.out.println(Config.class.getResource("")); // 获得当前类所在路径
		System.out.println(System.getProperty("user.dir")); // 获得项目根目录的绝对路径
		System.out.println(System.getProperty("java.class.path"));

		// null
		// null
		// null
		// jar:file:/usr/local/mqtt-server/mqtt-borker/mqtt-broker-0.0.1-SNAPSHOT.jar!/bean/lee/push/notification/conf/
		// /
		// /usr/local/mqtt-server/mqtt-borker/mqtt-broker-0.0.1-SNAPSHOT.jar

	}

}
