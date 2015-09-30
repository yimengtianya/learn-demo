package bean.lee.demo.akka.learn.one.actors;

import java.util.HashMap;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import bean.lee.demo.akka.learn.one.messages.MapData;
import bean.lee.demo.akka.learn.one.messages.ReduceData;
import bean.lee.demo.akka.learn.one.messages.WordCount;

/**
 * reduce计算
 * 
 * @author Dube
 * @date 2015年9月30日 上午11:23:37
 */
public class ReduceActor extends UntypedActor {

	private ActorRef aggregateActor = null;

	public ReduceActor(ActorRef inAggregateActor) {
		aggregateActor = inAggregateActor;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof MapData) {
			MapData mapData = (MapData) message;
			// reduce the incoming data
			ReduceData reduceData = reduce(mapData.getDataList());
			// forward the result to aggregate actor
			aggregateActor.tell(reduceData, getSelf());
		} else
			unhandled(message);
	}

	private ReduceData reduce(List<WordCount> dataList) {
		HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
		for (WordCount wordCount : dataList) {
			if (reducedMap.containsKey(wordCount.getWord())) {
				Integer value = (Integer) reducedMap.get(wordCount.getWord());
				value++;
				reducedMap.put(wordCount.getWord(), value);
			} else {
				reducedMap.put(wordCount.getWord(), Integer.valueOf(1));
			}
		}
		return new ReduceData(reducedMap);
	}
}
