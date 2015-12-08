package bean.lee.push.notification.test.topic;

import org.junit.Test;

import bean.lee.push.notification.topic.TopicManager;

public class TopicManagerTest {
	
	@Test
	public void test(){
		System.out.println(TopicManager.instance().topics().toString());
		TopicManager.instance().removeChannel("da8c4d4f");
		System.out.println(TopicManager.instance().channelSubscirbedTopic("/mqtt").toString());
	}

}
