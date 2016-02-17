package bean.lee.demo.smms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.smms.Application;
import bean.lee.demo.smms.domain.City;
import bean.lee.demo.smms.mapper.CityMapper;
import bean.lee.demo.smms.service.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CityServiceTest {

	@Autowired
	private CityService cityService;

	@Test
	public void testFind() {
		System.out.println(cityService.findByState("CB").getName());
		cityService.add(new City("t3", "CV", "AA"));
	}

	@Test
	public void testUpdate() {
		City city = cityService.findByState("CB");
		System.out.println(city.getName());
		cityService.update(city);
	}

}
