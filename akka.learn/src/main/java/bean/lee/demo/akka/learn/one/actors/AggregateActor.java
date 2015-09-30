package bean.lee.demo.akka.learn.one.actors;

import java.util.HashMap;
import java.util.Map;

import akka.actor.UntypedActor;
import bean.lee.demo.akka.learn.one.messages.ReduceData;
import bean.lee.demo.akka.learn.one.messages.Result;

/**
 * 聚合
 * 
 * @author Dube
 * @date 2015年9月30日 上午11:24:08
 */
public class AggregateActor extends UntypedActor {

	private Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ReduceData) {
			ReduceData reduceData = (ReduceData) message;
			aggregateInMemoryReduce(reduceData.getReduceDataList());
		} else if (message instanceof Result) {
			System.out.println(finalReducedMap.toString());
		} else
			unhandled(message);
	}

	private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
		Integer count = null;
		for (String key : reducedList.keySet()) {
			if (finalReducedMap.containsKey(key)) {
				count = reducedList.get(key) + finalReducedMap.get(key);
				finalReducedMap.put(key, count);
			} else {
				finalReducedMap.put(key, reducedList.get(key));
			}
		}
	}
}
