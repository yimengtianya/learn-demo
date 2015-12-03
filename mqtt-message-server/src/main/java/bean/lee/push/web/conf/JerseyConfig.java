package bean.lee.push.web.conf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import bean.lee.push.web.ws.MessageServer;

@ApplicationPath("/rest")
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(MessageServer.class);
	}

}
