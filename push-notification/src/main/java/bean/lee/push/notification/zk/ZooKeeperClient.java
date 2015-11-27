package bean.lee.push.notification.zk;

import org.apache.zookeeper.ZooKeeper;

import bean.lee.push.notification.conf.Config;

public class ZooKeeperClient {

	private ZooKeeper zooKeeper = null;

	public void init() throws Exception {
		zooKeeper = new ZooKeeper(Config.zkAddress, 3000, null);
		MessageWatcher watcher = new MessageWatcher(zooKeeper);
		zooKeeper.register(watcher);
		zooKeeper.exists(MessageWatcher.MESSAGE_PATH, true);
	}

}
