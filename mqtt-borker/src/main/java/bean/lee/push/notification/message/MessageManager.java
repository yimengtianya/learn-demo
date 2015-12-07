package bean.lee.push.notification.message;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.message.entity.Message;
import bean.lee.push.notification.route.ChannelManage;
import bean.lee.push.notification.topic.TopicManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.util.CharsetUtil;

/**
 * 消息推送
 * 
 * @author Dube
 * @date 2015年11月23日 上午10:30:00
 */
public class MessageManager {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

	private static final ByteBufAllocator ALLOCATOR = new UnpooledByteBufAllocator(false);

	public static void publish(Message message) {
		
		String topic = message.getTopic();
		String content = message.getContent();
		
		LOGGER.debug(String.format("Publish message at topic %s", topic));
		Set<String> channelIds = TopicManager.instance().channelSubscirbedTopic(topic);
		Channel channel = null;
		if (channelIds != null && channelIds.size() > 0) {
			for (String channelId : channelIds) {
				channel = ChannelManage.instance().get(channelId);
				if (channel != null && channel.isOpen()) {
					channel.writeAndFlush(buildMessage(topic, content));
					LOGGER.debug(String.format("topic: %s  channel: %s  content: %s", topic, channel.id().toString(),
							content));
				}
			}
		}
	}

	private static MqttPublishMessage buildMessage(String topic, String message) {
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_LEAST_ONCE,
				true, 0);
		MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topic, 1);
		ByteBuf payload = ALLOCATOR.buffer();
		payload.writeBytes(message.getBytes(CharsetUtil.UTF_8));
		MqttPublishMessage publishMessage = new MqttPublishMessage(mqttFixedHeader, mqttPublishVariableHeader, payload);
		return publishMessage;
	}

}
