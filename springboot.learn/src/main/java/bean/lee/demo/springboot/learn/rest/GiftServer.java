package bean.lee.demo.springboot.learn.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class GiftServer {

	@GET
	@Path("/one")
	public Response get(@Context HttpServletRequest request, @QueryParam("name") @DefaultValue("lee") String name) {
		System.out.println(request.getLocalAddr());
		return Response.ok(name).build();
	}

}
