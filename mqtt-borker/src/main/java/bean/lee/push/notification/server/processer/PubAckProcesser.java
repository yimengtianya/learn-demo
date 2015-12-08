package bean.lee.push.notification.server.processer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.lee.push.notification.message.MessageManager;
import bean.lee.push.notification.route.client.ClientManage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;

/**
 * 推送反馈
 * 
 * @author Dube
 * @date 2015年11月23日 上午11:51:45
 */
public class PubAckProcesser extends Processer {

	private final static Logger LOGGER = LoggerFactory.getLogger(PubAckProcesser.class);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		LOGGER.debug(String.format("Variable Header: %s", msg.variableHeader().toString()));
		MqttPubAckMessage message = (MqttPubAckMessage) msg;
		int messageId = message.variableHeader().messageId();
		MessageManager.instance().pubAck(messageId, ClientManage.instance().getClientId(ctx.channel().id().toString()));
		return null;
	}

}
