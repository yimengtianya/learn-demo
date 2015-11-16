package bean.lee.demo.springboot.learn.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import bean.lee.demo.springboot.learn.ws.GiftServer;
import bean.lee.demo.springboot.learn.ws.UserServer;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(GiftServer.class);
		register(UserServer.class);
	}

}
