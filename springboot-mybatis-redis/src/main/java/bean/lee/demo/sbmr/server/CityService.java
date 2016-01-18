package bean.lee.demo.sbmr.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bean.lee.demo.sbmr.domain.City;
import bean.lee.demo.sbmr.mapper.CityMapper;

/**
 * 测试事务
 * 
 * @author Dube
 * @date 2016年1月18日 下午4:33:29
 */
@Component
public class CityService {

	@Autowired
	private CityMapper cityMapper;

	@Transactional
	public void test() {

		cityMapper.add(new City("Test1", "CV", "AA"));
		City city = cityMapper.findByState("CV");
		city.setCountry("AB");
		cityMapper.update(city);

	}

}
