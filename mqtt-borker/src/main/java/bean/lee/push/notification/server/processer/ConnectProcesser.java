package bean.lee.push.notification.server.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.route.ChannelManage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
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

	/**
	 * 协议版本错误
	 */
	private static MqttConnAckMessage REFUSED_UNACCEPTABLE_PROTOCOL_VERSION = createConnAckMessage(
			MqttConnectReturnCode.CONNECTION_REFUSED_UNACCEPTABLE_PROTOCOL_VERSION);
	
	/**
	 * clientId拒绝
	 */
	private static MqttConnAckMessage CONNECTION_REFUSED_IDENTIFIER_REJECTED = createConnAckMessage(
			MqttConnectReturnCode.CONNECTION_REFUSED_IDENTIFIER_REJECTED);
	
	/**
	 * 帐号或密码错误
	 */
	private static MqttConnAckMessage CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD = createConnAckMessage(
			MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		MqttConnectMessage message = (MqttConnectMessage) msg;
		MqttConnectVariableHeader connectVariableHeader = message.variableHeader();
		MqttConnectPayload connectPayload = message.payload();

		LOGGER.debug(String.format("Variable Header: %s", connectVariableHeader.toString()));

		// 协议版本验证
		if (connectVariableHeader.version() != 3) {
			return REFUSED_UNACCEPTABLE_PROTOCOL_VERSION;
		}

		// 获取clientId
		String clientId = connectPayload.clientIdentifier();
		if (clientId == null || clientId.equals("")) {
			return CONNECTION_REFUSED_IDENTIFIER_REJECTED;
		}

		// 帐号密码验证
		/*if (connectVariableHeader.hasPassword() && connectVariableHeader.hasUserName()) {
			if (!checkUser(connectPayload.userName(), connectPayload.password())) {
				return CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD;
			}
		} else {
			return CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD;
		}*/

		ChannelManage.instance().add(clientId, ctx.channel());
		return ACCEPTED;
	}

	private static MqttConnAckMessage createConnAckMessage(MqttConnectReturnCode code) {
		MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE,
				false, 2);
		MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(code);
		return new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
	}

	/**
	 * 帐号密码验证
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @author Dube
	 * @date 2015年12月9日 上午10:34:53
	 */
	private boolean checkUser(String userName, String password) {
		// TODO
		return true;
	}

}
