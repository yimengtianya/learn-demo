package bean.lee.push.notification.message.publish;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.message.MessageManager;
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
 * 消息发送任务
 * 
 * @author Dube
 * @date 2015年12月7日 下午4:57:27
 */
public class MessagePublishTask implements Runnable {

	private final static Logger LOGGER = LoggerFactory.getLogger(MessagePublishTask.class);

	private static final ByteBufAllocator ALLOCATOR = new UnpooledByteBufAllocator(false);

	private Message message;

	private Set<String> clientIds;

	public MessagePublishTask(Message message, Set<String> clientIds) {
		this.message = message;
		this.clientIds = clientIds;
	}

	@Override
	public void run() {
		String topic = message.getTopic();
		String content = message.getContent();
		Channel channel = null;
		if (clientIds != null && clientIds.size() > 0) {
			for (String channelId : clientIds) {
				channel = ChannelManage.instance().get(channelId);
				if (channel != null && channel.isOpen()) {
					channel.writeAndFlush(buildMessage(topic, content));
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
