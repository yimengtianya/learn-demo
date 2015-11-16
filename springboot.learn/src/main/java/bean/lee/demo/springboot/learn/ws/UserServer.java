package bean.lee.demo.springboot.learn.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.lee.demo.springboot.learn.mapper.UserMapper;

@Path("/user")
public class UserServer {

	@Autowired
	private UserMapper userMapper;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request, @PathParam("id") int id) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return Response.ok(om.writeValueAsString(userMapper.findById(id))).build();
	}

}
