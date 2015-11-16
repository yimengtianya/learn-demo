package bean.lee.demo.springboot.learn.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import bean.lee.demo.springboot.learn.entity.User;

public interface UserMapper {

	@Select("select * from user where id=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "age", property = "age") })
	User findById(int id);

}
