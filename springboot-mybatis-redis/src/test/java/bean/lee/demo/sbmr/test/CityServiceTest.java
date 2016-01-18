package bean.lee.demo.sbmr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.sbmr.SampleMybatisApplication;
import bean.lee.demo.sbmr.server.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleMybatisApplication.class)
public class CityServiceTest {

	@Autowired
	private CityService cityService;

	@Test
	public void test() {
		cityService.test();
	}

}
