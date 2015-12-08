package bean.lee.push.notification.jms;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import bean.lee.push.notification.message.MessageManager;
import bean.lee.push.notification.message.entity.Message;

/**
 * 处理从mq上获取的消息，可实现异步
 * 
 * @author Dube
 * @date 2015年12月3日 下午2:22:31
 */
public class MessageDeal extends DefaultConsumer {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageDeal.class);

	public MessageDeal(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {
		String jsonMessage = new String(body, "UTF-8");
		LOGGER.debug(jsonMessage);
		Message message = new ObjectMapper().readValue(jsonMessage, Message.class);	
		MessageManager.instance().publish(message);
	}
}
