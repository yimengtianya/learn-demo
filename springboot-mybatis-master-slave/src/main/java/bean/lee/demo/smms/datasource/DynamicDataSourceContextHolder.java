package bean.lee.demo.smms.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DynamicDataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static Map<String, DataSourceGroup> groups = new HashMap<>();

	public static void setDataSourceType(String group, DataSourceLevel level) {
		if (level == DataSourceLevel.MASTER) {
			contextHolder.set(groups.get(group).getMaster());
		} else {
			contextHolder.set(groups.get(group).getSlave());
		}
	}

	public static void addDataSourceId(String key) {
		String groupName = key.split("`")[0];
		DataSourceGroup group = groups.get(groupName);
		if (group == null) {
			group = new DataSourceGroup();
			groups.put(groupName, group);
		}
		group.put(key);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}

}
