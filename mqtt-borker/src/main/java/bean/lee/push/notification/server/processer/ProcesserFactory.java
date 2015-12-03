package bean.lee.push.notification.server.processer;

import io.netty.handler.codec.mqtt.MqttMessageType;

/**
 * processer工厂类
 * 
 * @author Dube
 * @date 2015年11月19日 下午3:35:57
 */
public final class ProcesserFactory {

	private static ConnectProcesser connectProcesser = new ConnectProcesser();
	private static DisconnectProcesser disconnectProcesser = new DisconnectProcesser();
	private static SubscibeProcesser subscibeProcesser = new SubscibeProcesser();
	private static PingReqProcesser pingReqProcesser = new PingReqProcesser();
	private static PubAckProcesser pubAckProcesser = new PubAckProcesser();

	private ProcesserFactory() {
	}

	public static Processer newMessage(MqttMessageType type) {

		switch (type) {
		case CONNECT:
			return connectProcesser;
		case DISCONNECT:
			return disconnectProcesser;
		case SUBSCRIBE:
			return subscibeProcesser;
		case PINGREQ:
			return pingReqProcesser;
		case PUBACK:
			return pubAckProcesser;
		default:
			return null;
		}

	}

}
