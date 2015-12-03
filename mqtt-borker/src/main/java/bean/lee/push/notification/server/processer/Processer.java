package bean.lee.push.notification.server.processer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

public abstract class Processer {

	/**
	 * Process message and reply it.<br>
	 * retrun <code>null</code> if no need for reply.
	 * 
	 * @param msg
	 * @param ctx
	 * @return
	 */
	public abstract MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx);

}
