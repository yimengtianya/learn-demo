package bean.lee.demo.springboot.learn.conf;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * 从zk上同步配置
 * 
 * @author Dube
 * @date 2015年11月14日 下午5:22:59
 */
//@Configuration
//@EnableDiscoveryClient
public class SystemConfig {

	//@Value("${spring.application.name:testZookeeperApp}")
	private String appname;

}
