package bean.lee.push.notification.pust;

import java.util.Set;

import org.apache.logging.log4j.message.Message;

import bean.lee.push.notification.channel.ChannelManage;
import bean.lee.push.notification.topic.TopicManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.util.CharsetUtil;

public class PustManager {

	private static final ByteBufAllocator ALLOCATOR = new UnpooledByteBufAllocator(false);

	public static void pust(String topic, String message) {

		System.out.println(2);
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_LEAST_ONCE,
				true, 0);
		MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topic, 1);
		ByteBuf payload = ALLOCATOR.buffer();
		payload.writeBytes(message.getBytes(CharsetUtil.UTF_8));
		MqttPublishMessage publishMessage = new MqttPublishMessage(mqttFixedHeader, mqttPublishVariableHeader, payload);

		Set<String> channelIds = TopicManager.channelSubscirbedTopic(topic);
		System.out.println(channelIds);
		if (channelIds.size() > 0) {
			for (String channelId : channelIds) {
				System.out.println("message " + channelId + " " + topic);
				ChannelManage.instance().get(channelId).writeAndFlush(publishMessage);
			}
		}

	}

}
