package bean.lee.push.notification.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.conf.Config;

public class ZooKeeperClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(ZooKeeperClient.class);

	private ZooKeeper zooKeeper = null;

	private void createZooKeeper(final CountDownLatch connectionLatch) throws IOException {
		zooKeeper = new ZooKeeper(Config.zkAddress, 3000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				sessionEvent(connectionLatch, event);
			}
		});
		System.out.println(1);
	}

	public void connect() {
		try {
			CountDownLatch connectionLatch = new CountDownLatch(1);
			createZooKeeper(connectionLatch);
			connectionLatch.await();
			MessageWatcher watcher = new MessageWatcher(zooKeeper);
			zooKeeper.register(watcher);
			zooKeeper.exists(MessageWatcher.MESSAGE_PATH, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sessionEvent(CountDownLatch connectionLatch, WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			connectionLatch.countDown();
			LOGGER.info("ZooKeeper连接成功");
		} else if (event.getState() == KeeperState.Expired)
			try {
				LOGGER.info("ZooKeeper连接失败，正在重连");
				System.out.println("重连接");
				reConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
	} // Disconnected：Zookeeper会自动处理Disconnected状态重连

	private void reConnection() throws Exception {
		if (this.zooKeeper != null) {
			this.zooKeeper.close();
			this.zooKeeper = null;
			this.connect();
		}
	}

}
