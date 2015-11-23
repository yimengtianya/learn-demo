package bean.lee.push.notification.processer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.lee.push.notification.topic.TopicManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;
import io.netty.handler.codec.mqtt.MqttSubAckPayload;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttSubscribePayload;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;

/**
 * 订阅处理
 * 
 * @author Dube
 * @date 2015年11月19日 下午4:24:45
 */
public class SubscibeProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(SubscibeProcesser.class);

	private TopicManager topicManager = TopicManager.instance();

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttSubscribeMessage message = (MqttSubscribeMessage) msg;
		LOGGER.debug(String.format("Variable Header: %s", message.variableHeader().toString()));
		MqttSubscribePayload payload = message.payload();

		// 主题订阅
		List<MqttTopicSubscription> topicSubs = payload.topicSubscriptions();
		int topicSubsSize = topicSubs.size();
		int[] topicQos = new int[topicSubsSize];
		String channelId = ctx.channel().id().toString();
		for (int i = 0; i < topicSubsSize; i++) {
			MqttTopicSubscription topicSub = topicSubs.get(i);
			topicManager.register(topicSub.topicName(), channelId);
			topicQos[i] = topicSub.qualityOfService().value();
			LOGGER.debug(String.format("Channel [%s] subscribe at tipic [%s].", channelId, topicSub.topicName()));
		}

		// 返回消息构建
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE,
				false, 0);
		MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(1);
		MqttSubAckPayload mqttSubAckPayload = new MqttSubAckPayload(topicQos);
		MqttSubAckMessage subAckMessage = new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader,
				mqttSubAckPayload);
		ctx.fireChannelRead(msg);
		return subAckMessage;
	}

}
