package bean.lee.push.notification.server.hander;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.lee.push.notification.route.ChannelManage;
import bean.lee.push.notification.server.processer.Processer;
import bean.lee.push.notification.server.processer.ProcesserFactory;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class MqttServerHandler extends SimpleChannelInboundHandler<Object> {

	private final static Logger LOGGER = LogManager.getLogger(MqttServerHandler.class);

	private ChannelManage channelManage = ChannelManage.instance();

	/**
	 * 连接断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		channelManage.remove(ctx.channel());
	}

	/**
	 * 消息处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		MqttMessage message = (MqttMessage) msg;

		LOGGER.debug(String.format("Fixed Header：%s ", message.fixedHeader().toString()));

		Processer p = ProcesserFactory.newMessage(message.fixedHeader().messageType());

		if (p == null) {
			return;
		}

		MqttMessage rmsg = p.proc(message, ctx);

		if (rmsg == null) {
			return;
		}

		if (rmsg instanceof MqttConnAckMessage && ((MqttConnAckMessage) rmsg).variableHeader()
				.connectReturnCode() != MqttConnectReturnCode.CONNECTION_ACCEPTED) {
			ctx.write(rmsg).addListener(ChannelFutureListener.CLOSE);
		} else {
			ctx.write(rmsg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// TODO 异常处理
		//cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

	}
}
