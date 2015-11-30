package bean.lee.push.notification.server.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 
 * @author Dube
 * @date 2015年11月23日 上午11:51:45
 */
public class PubAckProcesser extends Processer {

	private final static Logger LOGGER = LoggerFactory.getLogger(PubAckProcesser.class);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		LOGGER.debug(String.format("Variable Header: %s", msg.variableHeader().toString()));
		return null;
	}

}
