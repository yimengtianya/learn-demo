package bean.lee.demo.smms.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

	private ConversionService conversionService = new DefaultConversionService();
	private PropertyValues dataSourcePropertyValues;

	// 如配置文件中未指定数据源类型，使用该默认值
	private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

	// 数据源
	private Map<String, DataSource> dataSources = new HashMap<>();

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 添加更多数据源
		targetDataSources.putAll(dataSources);
		for (String key : dataSources.keySet()) {
			DynamicDataSourceContextHolder.addDataSourceId(key);
		}

		// 创建DynamicDataSource
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(DynamicDataSource.class);
		beanDefinition.setSynthetic(true);
		MutablePropertyValues mpv = beanDefinition.getPropertyValues();
		mpv.addPropertyValue("targetDataSources", targetDataSources);
		registry.registerBeanDefinition("dataSource", beanDefinition);

		logger.info("Dynamic DataSource Registry");
	}

	/**
	 * 创建DataSource
	 * 
	 * @param dsMap
	 * @return
	 * @author Dube
	 * @date 2016年2月16日 下午12:04:35
	 */
	@SuppressWarnings("unchecked")
	public DataSource buildDataSource(Map<String, Object> dsMap) {
		try {
			Object type = dsMap.get("type");
			if (type == null)
				type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
			
			Class<? extends DataSource> dataSourceType;
			dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

			String driverClassName = dsMap.get("driver-class-name").toString();
			String url = dsMap.get("url").toString();
			String username = dsMap.get("username").toString();
			String password = dsMap.get("password").toString();

			DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
					.username(username).password(password).type(dataSourceType);
			return factory.build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载多数据源配置
	 */
	@Override
	public void setEnvironment(Environment env) {
		initCustomDataSources(env);
	}

	/**
	 * 初始化多个 从数据源
	 * 
	 * @param env
	 * @author Dube
	 * @date 2016年2月16日 下午12:05:43
	 */
	private void initCustomDataSources(Environment env) {
		// group
		RelaxedPropertyResolver groupResolver = new RelaxedPropertyResolver(env, "datasource.group.");
		String groups = groupResolver.getProperty("useful");
		if (groups == null || groups.equals(""))
			return;
		for (String group : groups.split(",")) {
			RelaxedPropertyResolver dbResolver = new RelaxedPropertyResolver(env, "datasource." + group + ".");
			String dbs = dbResolver.getProperty("useful");
			if (dbs == null || dbs.equals(""))
				continue;
			for (String db : dbs.split(",")) {
				Map<String, Object> dsMap = dbResolver.getSubProperties(db + ".");
				DataSource ds = buildDataSource(dsMap);
				dataSources.put(group + "`" + db, ds);
				dataBinder(ds, env);
			}

		}

		
	}

	/**
	 * 为DataSource绑定更多配置
	 * 
	 * @param dataSource
	 * @param env
	 * @author Dube
	 * @date 2016年2月16日 下午12:07:32
	 */
	private void dataBinder(DataSource dataSource, Environment env) {
		RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
		// dataBinder.setValidator(new
		// LocalValidatorFactory().run(this.applicationContext));
		dataBinder.setConversionService(conversionService);
		dataBinder.setIgnoreNestedProperties(false);// false
		dataBinder.setIgnoreInvalidFields(false);// false
		dataBinder.setIgnoreUnknownFields(true);// true
		if (dataSourcePropertyValues == null) {
			Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
			Map<String, Object> values = new HashMap<>(rpr);
			// 排除已经设置的属性
			values.remove("type");
			values.remove("driver-class-name");
			values.remove("url");
			values.remove("username");
			values.remove("password");
			dataSourcePropertyValues = new MutablePropertyValues(values);
		}
		dataBinder.bind(dataSourcePropertyValues);
	}

}
