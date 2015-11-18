package bean.lee.push.notification.processer;

import io.netty.handler.codec.mqtt.MqttMessageType;

public final class ProcesserFactory {

	private static ConnectProcesser connectProcesser = new ConnectProcesser();

	private ProcesserFactory() {
	}

	public static Processer newMessage(MqttMessageType type) {

		switch (type) {
		case CONNECT:
			return connectProcesser;
		default:
			return null;
		}

	}

}
