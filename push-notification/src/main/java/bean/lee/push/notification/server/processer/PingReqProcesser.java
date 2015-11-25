package bean.lee.push.notification.server.processer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.lee.push.notification.route.ChannelManage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;

/**
 * 心跳消息处理
 * 
 * @author Dube
 * @date 2015年11月20日 上午11:05:51
 */
public class PingReqProcesser extends Processer {

	private final static Logger LOGGER = LogManager.getLogger(DisconnectProcesser.class);

	private ChannelManage channelManage = ChannelManage.instance();

	/**
	 * 断开连接
	 */
	private static MqttMessage DISCONNECT = MqttMessageFactory.newMessage(
			new MqttFixedHeader(MqttMessageType.DISCONNECT, false, MqttQoS.AT_MOST_ONCE, false, 0), null, null);

	/**
	 * ping消息正常回复
	 */
	private static MqttMessage PINGRESP = MqttMessageFactory.newMessage(
			new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0), null, null);

	@Override
	public MqttMessage proc(MqttMessage msg, ChannelHandlerContext ctx) {
		Channel channel = ctx.channel();
		if (ChannelManage.instance().exist(channel)) {
			channelManage.refresh(channel);
			return PINGRESP;
		}
		return DISCONNECT;
	}

}
