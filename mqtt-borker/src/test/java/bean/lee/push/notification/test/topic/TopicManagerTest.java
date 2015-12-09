package bean.lee.push.notification.test.topic;

import org.junit.Test;

import bean.lee.push.notification.topic.TopicManager;

public class TopicManagerTest {
	
	@Test
	public void test(){
		System.out.println(TopicManager.instance().topics().toString());
		TopicManager.instance().removeClient("c2");
		System.out.println(TopicManager.instance().clientIdsSubscirbedTopic("mqtt").toString());
	}

}
