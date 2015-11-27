package bean.lee.demo.zkclient.learn.basis.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class MyWatcher implements Watcher {

	private ZooKeeper zooKeeper = null;

	public MyWatcher(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	@Override
	public void process(WatchedEvent event) {
		String path = event.getPath();
		System.out.println(event.getState() + "  " + event.getType() + "  " + event.getPath());
		System.out.println("event");

		try {
			if (null != path) {
				Stat stat = new Stat();
				System.out.println(new String(zooKeeper.getData("/lee", true, stat)));// 获取数据时从新注册事件
				System.out.println(stat.getVersion());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
