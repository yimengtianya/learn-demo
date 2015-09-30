package bean.lee.demo.akka.learn.one.messages;

import java.util.Collections;
import java.util.Map;

public class ReduceData {

	private Map<String, Integer> reduceDataList;

	public Map<String, Integer> getReduceDataList() {
		return reduceDataList;
	}

	public ReduceData(Map<String, Integer> reduceDataList) {
		this.reduceDataList = Collections.unmodifiableMap(reduceDataList);
	}
}
