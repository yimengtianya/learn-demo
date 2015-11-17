package bean.lee.push.notification.hander;

import bean.lee.push.notification.channel.ChannelMap;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

/**
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class MainServerHandler extends ChannelHandlerAdapter {

	/**
	 * 连接断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ChannelMap.remove((SocketChannel) ctx.channel());
	}

	/**
	 * 消息处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
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
		ChannelMap.add(ctx.channel().id().toString(), (SocketChannel) ctx.channel());
	}
}
