package bean.lee.demo.smms.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

	/**
	 * 数据源组
	 * 
	 * @return
	 * @author Dube
	 * @date 2016年2月16日 下午3:12:27
	 */
	String name();

	/**
	 * 级别：主or从
	 * 
	 * @return
	 * @author Dube
	 * @date 2016年2月16日 下午3:12:54
	 */
	DataSourceLevel level() default DataSourceLevel.MASTER;
}
