package bean.lee.push.notification.test.route;

import org.junit.Test;

import bean.lee.push.notification.route.client.ClientManage;

public class ClientManagerTest {
	
	@Test
	public void testRomeveByClientId(){
		System.out.println(ClientManage.instance().exist("c1"));
		System.out.println(ClientManage.instance().getClientId("c1"));
		ClientManage.instance().removeByClinetId("c1");
		System.out.println(ClientManage.instance().exist("c1"));
		
	}

}
