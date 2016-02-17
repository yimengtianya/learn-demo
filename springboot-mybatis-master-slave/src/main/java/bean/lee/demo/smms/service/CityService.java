package bean.lee.demo.smms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.lee.demo.smms.datasource.DataSourceLevel;
import bean.lee.demo.smms.datasource.TargetDataSource;
import bean.lee.demo.smms.domain.City;
import bean.lee.demo.smms.mapper.CityMapper;

@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;

	@TargetDataSource(name = "app", level = DataSourceLevel.SLAVE)
	public City findByState(String state) {
		return cityMapper.findByState(state);
	}

	@TargetDataSource(name = "app", level = DataSourceLevel.MASTER)
	public int add(City city) {
		return cityMapper.add(city);
	}

	@TargetDataSource(name = "log", level = DataSourceLevel.MASTER)
	public int update(City city) {
		return cityMapper.update(city);
	}

}
