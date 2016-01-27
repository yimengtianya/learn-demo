package bean.lee.demo.smms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.smms.Application;
import bean.lee.demo.smms.domain.City;
import bean.lee.demo.smms.mapper.CityMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CityMapperTest {

	@Autowired
	private CityMapper cityMapper;

	@Test
	public void testFind() {
		System.out.println(cityMapper.findByState("CC").getName());
		cityMapper.add(new City("t3", "CV", "AA"));
	}

}
