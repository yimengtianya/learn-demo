package bean.lee.demo.sbmr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.sbmr.SampleMybatisApplication;
import bean.lee.demo.sbmr.mapper.CityMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleMybatisApplication.class)
public class CityMapperTest {

	@Autowired
	private CityMapper cityMapper;

	@Test
	public void testFind() {
		System.out.println(this.cityMapper.findByState("CA"));
	}

}
