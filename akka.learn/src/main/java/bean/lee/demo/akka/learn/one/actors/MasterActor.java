package bean.lee.demo.akka.learn.one.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.japi.Creator;
import bean.lee.demo.akka.learn.one.messages.Result;

/**
 * 管理Actor
 * <p>
 * 
 * 在java中，一个actor需要继承UntypedActor，并实现onReceive方法；
 * 
 * @author Dube
 * @date 2015年9月30日 上午11:21:29
 */
public class MasterActor extends UntypedActor {

	// getContext().actorOf()，创建子actor
	private ActorRef aggregateActor = getContext().actorOf(Props.create(AggregateActor.class), "aggregate");

	// 带参数的actor,通过静态内部类来创建
	private ActorRef reduceActor = getContext().actorOf(Props.create(new ReduceCreator(aggregateActor)), "reduce");

	private ActorRef mapActor = getContext().actorOf(Props.create(new MapCreator(reduceActor)), "map");

	static class ReduceCreator implements Creator<ReduceActor> {
		private static final long serialVersionUID = -276393139166976525L;
		private ActorRef actor = null;

		public ReduceCreator(ActorRef actor) {
			this.actor = actor;
		}

		public ReduceActor create() throws Exception {
			return new ReduceActor(actor);
		}
	}

	static class MapCreator implements Creator<MapActor> {
		private static final long serialVersionUID = -3628365987867965723L;
		private ActorRef actor = null;

		public MapCreator(ActorRef actor) {
			this.actor = actor;
		}

		public MapActor create() throws Exception {
			return new MapActor(actor);
		}
	}

	/**
	 * 接收消息
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		System.out.println(message);
		if (message instanceof String) {
			// 接收到string的为原始的输入，发送给mapActor
			mapActor.tell(message, getSelf());
		} else if (message instanceof Result) {
			aggregateActor.tell(message, getSelf());
		} else {
			unhandled(message);
		}
	}
}
