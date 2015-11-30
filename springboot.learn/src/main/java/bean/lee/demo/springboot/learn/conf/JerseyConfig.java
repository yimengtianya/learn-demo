package bean.lee.demo.springboot.learn.conf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import bean.lee.demo.springboot.learn.ws.GiftServer;
import bean.lee.demo.springboot.learn.ws.UserServer;

/**
 * 注册Jersey，@ApplicationPath("/rest")将 所以接口映射在/rest/*下
 * 
 * @author Dube
 * @date 2015年11月30日 下午6:13:17
 */
@ApplicationPath("/rest") // 所以接口映射在/rest/*下
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(GiftServer.class);
		register(UserServer.class);
	}

}
