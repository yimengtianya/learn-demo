package bean.lee.push.notification.test.zk;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import bean.lee.push.notification.zk.ZooKeeperClient;

public class ZkTest {

	@Test
	public void test() throws Exception {
		new ZooKeeperClient().connect();
		TimeUnit.SECONDS.sleep(10);
	}

}
