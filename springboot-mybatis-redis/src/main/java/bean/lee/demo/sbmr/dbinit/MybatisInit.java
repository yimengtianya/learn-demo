package bean.lee.demo.sbmr.dbinit;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "bean.lee.demo.sbmr.mapper")
public class MybatisInit {

	@Bean
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {

		// 属性
		Properties properties = new Properties();
		properties.setProperty("defaultStatementTimeout", "3000");
		properties.setProperty("cacheEnabled", "true");

		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setConfigurationProperties(properties);
		return sessionFactory.getObject();
	}
}
