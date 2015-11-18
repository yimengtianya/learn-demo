package bean.lee.push.notification.hander;

import bean.lee.push.notification.channel.ChannelManage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class MqttServerHandler extends SimpleChannelInboundHandler<Object> {

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
		System.out.println(message.variableHeader());
		
		
		ctx.write(msg);
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
