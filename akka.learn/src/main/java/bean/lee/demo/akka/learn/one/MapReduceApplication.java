package bean.lee.demo.akka.learn.one;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import bean.lee.demo.akka.learn.one.actors.MasterActor;
import bean.lee.demo.akka.learn.one.messages.Result;

//实现MapReduce的例子
public class MapReduceApplication {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		// 创建一个actorsystem
		ActorSystem _system = ActorSystem.create("MapReduceApp");

		// prop可以配置，通过配置创建actor
		// actorOf方法将创建的actor加入ActorSystem和上下文
		// 用ActorSystem创建的为顶级的actor,用actir.getContext().actorOf()方法创建子actor
		ActorRef master = _system.actorOf(Props.create(MasterActor.class), "master");

		// 发送消息,第一个参数为消息，第二个为消息的发送者
		master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", null);
		master.tell("Dog is man's best friend", null);
		master.tell("Dog and Fox belong to the same family", null);

		Thread.sleep(500);

		master.tell(new Result(), null);

		Thread.sleep(500);

		_system.shutdown();
		System.out.println("Java done!");
	}
}
