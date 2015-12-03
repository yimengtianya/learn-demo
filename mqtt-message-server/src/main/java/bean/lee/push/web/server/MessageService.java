package bean.lee.push.web.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import bean.lee.push.web.conf.RabbitMQClientInit;
import bean.lee.push.web.entity.Message;

/**
 * 消息推送，将数据写入zk
 * 
 * @author Dube
 * @date 2015年11月28日 下午2:25:05
 */
@Component
public class MessageService {

	@Autowired
	private Channel channel;

	public boolean pustMessage(Message message) {
		try {
			channel.basicPublish(RabbitMQClientInit.TOPIC, "", null,
					new ObjectMapper().writeValueAsString(message).getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
