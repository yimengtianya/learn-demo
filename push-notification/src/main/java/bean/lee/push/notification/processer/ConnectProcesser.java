package bean.lee.push.notification.processer;

import java.util.Stack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;

public class ConnectProcesser extends Processer {
	
	//private static MqttConnAckMessage ACCEPTED = new MqttConnAckMessage(new MqttFixedHeader(MqttMessageType.CONNACK, isDup, qosLevel, isRetain, remainingLength), new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED))

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
