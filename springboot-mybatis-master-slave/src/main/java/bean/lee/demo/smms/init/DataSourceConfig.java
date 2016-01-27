package bean.lee.demo.smms.init;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import bean.lee.demo.smms.datasource.DataSources;
import bean.lee.demo.smms.datasource.DynamicDataSource;

@Configuration
public class DataSourceConfig {

	@Bean(name = "master")
	@ConfigurationProperties(prefix = "master.datasource")
	public DataSource master() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "slave1")
	@ConfigurationProperties(prefix = "slave1.datasource")
	public DataSource slave1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSource")
	@Primary
	@Autowired
	public DataSource dynamicDataSource(@Qualifier("master") DataSource master,
			@Qualifier("slave1") DataSource slave1) {
		DynamicDataSource dataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSources.MASTER, master);
		targetDataSources.put(DataSources.SLAVE, slave1);
		dataSource.setTargetDataSources(targetDataSources);
		return dataSource;
	}

}
