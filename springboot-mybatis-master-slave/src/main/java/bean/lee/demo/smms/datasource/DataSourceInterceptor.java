package bean.lee.demo.smms.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DataSourceInterceptor {

	@Pointcut("execution(public * bean.lee.demo.smms.mapper.*.find*(..))")
	public void dataSourceSlave() {
	};

	@Pointcut("execution(public * bean.lee.demo.smms.mapper.*.add*(..))")
	public void dataSourceMaster() {
	};

	@Before("dataSourceSlave()")
	public void before(JoinPoint jp) {
		DataSourceTypeManager.set(DataSources.SLAVE);
	}

	@Before("dataSourceSlave()")
	public void beforeMaster(JoinPoint jp) {
		DataSourceTypeManager.reset();
	}
}
