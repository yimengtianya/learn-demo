package bean.lee.demo.akka.learn.one.actors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import bean.lee.demo.akka.learn.one.messages.MapData;
import bean.lee.demo.akka.learn.one.messages.WordCount;

/**
 * map计算
 * 
 * @author Dube
 * @date 2015年9月30日 上午11:23:25
 */
public class MapActor extends UntypedActor {

	private ActorRef reduceActor = null;

	String[] STOP_WORDS = { "a", "am", "an", "and", "are", "as", "at", "be", "do", "go", "if", "in", "is", "it", "of",
			"on", "the", "to" };

	private List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

	public MapActor(ActorRef inReduceActor) {
		reduceActor = inReduceActor;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			String work = (String) message;
			// map the words in the sentence
			MapData data = evaluateExpression(work);
			// send the result to ReduceActor
			reduceActor.tell(data,getSelf());
		} else
			unhandled(message);
	}

	private MapData evaluateExpression(String line) {
		List<WordCount> dataList = new ArrayList<WordCount>();
		StringTokenizer parser = new StringTokenizer(line);
		while (parser.hasMoreTokens()) {
			String word = parser.nextToken().toLowerCase();
			if (!STOP_WORDS_LIST.contains(word)) {
				dataList.add(new WordCount(word, Integer.valueOf(1)));
			}
		}
		return new MapData(dataList);
	}
}
