package bean.lee.push.web.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.lee.push.web.entity.Message;
import bean.lee.push.web.server.MessageService;

@Component
@Path("/message")
public class MessageServer {

	@Autowired
	private MessageService messageService;

	@GET
	@Path("/test")
	public String test() {
		return "test";
	}

	@POST
	@Path("/push")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context HttpServletRequest request, String content) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		Map<String, String> result = new HashMap<>();
		// json转对象可以起到json校验作用
		Message message = null;
		try {
			message = om.readValue(content, Message.class);
		} catch (IOException e) {
			e.printStackTrace();
			result.put("message", "error");
			return Response.ok(om.writeValueAsString(result)).build();
		}
		if (message == null) {
			result.put("message", "error");
		} else {
			if (messageService.pustMessage(message)) {
				result.put("message", "success");
			} else {
				result.put("message", "error");
			}
		}
		return Response.ok(om.writeValueAsString(result)).build();
	}

}
