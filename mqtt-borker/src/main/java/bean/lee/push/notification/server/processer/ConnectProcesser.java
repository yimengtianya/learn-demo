package bean.lee.push.notification.server.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.route.ChannelManage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttConnectVariableHeader;
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

	private final static Logger LOGGER = LoggerFactory.getLogger(ConnectProcesser.class);

	/**
	 * 接受连接
	 */
	private static MqttConnAckMessage ACCEPTED = createConnAckMessage(MqttConnectReturnCode.CONNECTION_ACCEPTED);

	private static MqttConnAckMessage REFUSED_UNACCEPTABLE_PROTOCOL_VERSION = createConnAckMessage(
			MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttConnectMessage cm = (MqttConnectMessage) msg;
		MqttConnectVariableHeader connectVariableHeader = cm.variableHeader();
		LOGGER.debug(String.format("Variable Header: %s", connectVariableHeader.toString()));
		// 协议版本验证
		if (connectVariableHeader.version() != 3) {
			return REFUSED_UNACCEPTABLE_PROTOCOL_VERSION;
		}
		ChannelManage.instance().add(ctx.channel().id().toString(), ctx.channel());
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
