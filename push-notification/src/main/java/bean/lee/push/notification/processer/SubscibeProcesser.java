package bean.lee.push.notification.processer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/**
 * 订阅处理
 * 
 * @author Dube
 * @date 2015年11月19日 下午4:24:45
 */
public class SubscibeProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(SubscibeProcesser.class);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttSubscribeMessage message = (MqttSubscribeMessage) msg;
		LOGGER.debug(String.format("Variable Header: %s", message.variableHeader().toString()));

		MqttSubscribePayload payload = message.payload();

		LOGGER.debug("[%s] Subscribe at tipic [%s].", ctx.channel().id().toString(), payload.topicSubscriptions());

		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE,
				false, 0);
		
		MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(1);
		MqttSubAckPayload mqttSubAckPayload = new MqttSubAckPayload(1);

		MqttSubAckMessage subAckMessage = new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader,
				mqttSubAckPayload);

		return subAckMessage;
	}

}
