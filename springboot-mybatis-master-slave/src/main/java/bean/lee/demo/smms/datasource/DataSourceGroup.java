package bean.lee.demo.smms.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSourceGroup {
	private String master;

	private List<String> slaves = new ArrayList<>();

	public DataSourceGroup() {
		super();
	}

	public void put(String dbName) {
		if (dbName.endsWith("master")) {
			this.master = dbName;
		} else {
			if (!slaves.contains(dbName))
				this.slaves.add(dbName);
		}
	}

	public String getMaster() {
		return master;
	}

	public boolean hasSlave() {
		return slaves.size() > 0;
	}

	public String getSlave() {
		if (hasSlave()) {
			return slaves.get(random(0, slaves.size()));
		} else {
			return getMaster();
		}
	}

	private Random random = new Random();

	/**
	 * 随机返回min和max之间的整数， （包括min,不包括max）
	 * 
	 * @param min
	 *            下边界
	 * @param max
	 *            上边界
	 * @return
	 * @author 李彬
	 * @date 2015年12月26日 下午5:17:20
	 */
	private int random(int min, int max) {
		return random.nextInt(max) % (max - min + 1) + min;
	}
}
