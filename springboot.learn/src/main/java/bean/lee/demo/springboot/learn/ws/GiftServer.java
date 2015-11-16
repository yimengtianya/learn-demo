package bean.lee.demo.springboot.learn.ws;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.lee.demo.springboot.learn.service.GiftService;

@Component
@Path("/gift")
public class GiftServer {

	@Autowired
	private GiftService giftService;

	/**
	 * 获取所有gift
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 *
	 * @author Dube
	 * @date 2015年11月14日 下午3:36:58
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return Response.ok(om.writeValueAsString(giftService.findGifts())).build();
	}

	/**
	 * 通过gift名称获取gitf
	 * 
	 * @param request
	 * @param name
	 *            gift名称
	 * @return
	 * @throws JsonProcessingException
	 *
	 * @author Dube
	 * @date 2015年11月14日 下午3:37:16
	 */
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request, @PathParam("name") String name)
			throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return Response.ok(om.writeValueAsString(giftService.findGiftByName(name))).build();
	}

}
