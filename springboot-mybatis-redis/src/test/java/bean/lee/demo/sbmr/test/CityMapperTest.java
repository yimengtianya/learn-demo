package bean.lee.demo.sbmr.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.sbmr.SampleMybatisApplication;
import bean.lee.demo.sbmr.domain.City;
import bean.lee.demo.sbmr.mapper.CityMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleMybatisApplication.class)
public class CityMapperTest {

	@Autowired
	private CityMapper cityMapper;

	@Test
	public void testFind() throws InterruptedException {
		City city = cityMapper.findByState("CB");

		//cityMapper.findByState("CA");
		city.setState("CC");
		cityMapper.update(city);

		cityMapper.findByState("CA");
		cityMapper.findByState("CB");
		cityMapper.findByState("CA");
		cityMapper.findByState("CB");
		cityMapper.findByState("CC");

		TimeUnit.SECONDS.sleep(3);

	}

}
