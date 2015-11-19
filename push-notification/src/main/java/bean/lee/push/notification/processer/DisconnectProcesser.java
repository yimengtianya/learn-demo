package bean.lee.push.notification.processer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 断开连接请求处理
 * 
 * @author Dube
 * @date 2015年11月19日 下午3:48:28
 */
public class DisconnectProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(DisconnectProcesser.class);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		LOGGER.debug("Variable Header: %s", msg.variableHeader().toString());

		ctx.channel().close();
		// TODO 清楚连接信息，订阅信息

		return null;
	}

}
