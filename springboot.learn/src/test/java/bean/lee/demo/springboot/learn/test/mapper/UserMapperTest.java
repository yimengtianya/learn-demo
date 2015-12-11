package bean.lee.demo.springboot.learn.test.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bean.lee.demo.springboot.learn.Application;
import bean.lee.demo.springboot.learn.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindById() {
		System.out.println(userMapper.findById(1).getName());
	}

}
