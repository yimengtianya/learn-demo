package bean.lee.push.notification.hander;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.lee.push.notification.channel.ChannelManage;
import bean.lee.push.notification.processer.Processer;
import bean.lee.push.notification.processer.ProcesserFactory;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class MqttServerHandler extends SimpleChannelInboundHandler<Object> {

	private final static Logger LOGGER = LogManager.getLogger(MqttServerHandler.class);

	private ChannelManage channelManage;

	public MqttServerHandler(ChannelManage channelManage) {
		this.channelManage = channelManage;
	}

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

		channelManage.refresh(ctx.channel());

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
		ctx.flush();

	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// cause.printStackTrace();
		ctx.close();
	}

	/**
	 * 连接建立
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		channelManage.add(ctx.channel().id().toString(), ctx.channel());
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

	}
}
