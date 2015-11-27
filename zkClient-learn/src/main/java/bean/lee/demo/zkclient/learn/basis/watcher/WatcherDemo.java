package bean.lee.demo.zkclient.learn.basis.watcher;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 监听某节点数据变化
 * 
 * @author Dube
 * @date 2015年11月27日 下午6:27:36
 */
public class WatcherDemo {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1", 3000, null);
		Watcher myWatcher = new MyWatcher(zooKeeper);
		zooKeeper.register(myWatcher);

		zooKeeper.exists("/lee", true);

		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
