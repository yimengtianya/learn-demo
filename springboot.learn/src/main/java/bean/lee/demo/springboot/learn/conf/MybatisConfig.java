package bean.lee.demo.springboot.learn.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "bean.lee.demo.springboot.learn.mapper", sqlSessionFactoryRef = "sessionFactory")
public class MybatisConfig {

	@Bean(name = "sessionFactory")
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		return sessionFactory.getObject();
	}
}
