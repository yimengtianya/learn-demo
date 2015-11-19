package bean.lee.push.notification.processer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;

/**
 * 连接处理
 * 
 * @author Dube
 * @date 2015年11月19日 下午3:35:17
 */
public class ConnectProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(ConnectProcesser.class);

	/**
	 * 接受连接
	 */
	private static MqttConnAckMessage ACCEPTED = createConnAckMessage(MqttConnectReturnCode.CONNECTION_ACCEPTED);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttConnectMessage cm = (MqttConnectMessage) msg;

		LOGGER.debug("Variable Header: %s", cm.variableHeader().toString());

		// TODO 身份验证

		return ACCEPTED;
	}

	private static MqttConnAckMessage createConnAckMessage(MqttConnectReturnCode code) {
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE,
				false, 2);
		MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(code);
		return new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
	}

}
