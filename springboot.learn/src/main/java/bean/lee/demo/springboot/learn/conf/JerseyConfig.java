package bean.lee.demo.springboot.learn.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import bean.lee.demo.springboot.learn.rest.GiftServer;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(GiftServer.class);
	}

}
