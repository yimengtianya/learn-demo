package bean.lee.push.notification.processer;

import java.util.concurrent.TimeUnit;

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

public class ConnectProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(ConnectProcesser.class);

	/**
	 * 接受连接
	 */
	private static MqttConnAckMessage ACCEPTED = createConnAckMessage(MqttConnectReturnCode.CONNECTION_ACCEPTED);
	/**
	 * 拒绝连接
	 */
	private static MqttConnAckMessage UNACCEPTABLE = new MqttConnAckMessage(
			new MqttFixedHeader(MqttMessageType.CONNACK, false, null, false, 2),
			new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION));

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttConnectMessage cm = (MqttConnectMessage) msg;

		LOGGER.debug(String.format("Variable Header: %s", cm.variableHeader().toString()));

		/*
		 * if (!"MQIsdp".equalsIgnoreCase(cm.getProtocolId()) ||
		 * cm.getProtocolVersion() != 3) { return UNACCEPTABLE_PROTOCOL_VERSION;
		 * }
		 */

	/*	int timeout = (int) Math.ceil(cm.variableHeader().keepAliveTimeSeconds() * 1.5);
		System.out.println("timeout is " + timeout);
*/
		//ctx.pipeline().addFirst("readTimeOutHandler", new ReadTimeoutHandler(timeout, TimeUnit.SECONDS));

		//MemPool.registerClienId(cm.getClientId(), ctx.channel());

		return ACCEPTED;
	}

	private static MqttConnAckMessage createConnAckMessage(MqttConnectReturnCode code) {
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE,
				false, 2);
		MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(code);
		return new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
	}

}
