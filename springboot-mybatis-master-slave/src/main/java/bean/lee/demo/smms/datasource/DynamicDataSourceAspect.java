package bean.lee.demo.smms.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DynamicDataSourceAspect {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Before("@annotation(tds)")
	public void changeDataSource(JoinPoint point, TargetDataSource tds) throws Throwable {
		DynamicDataSourceContextHolder.setDataSourceType(tds.name(),tds.level());
	}

	@After("@annotation(tds)")
	public void restoreDataSource(JoinPoint point, TargetDataSource tds) {
		logger.debug("Revert DataSource : {} > {}", tds.name(), point.getSignature());
		DynamicDataSourceContextHolder.clearDataSourceType();
	}

}
