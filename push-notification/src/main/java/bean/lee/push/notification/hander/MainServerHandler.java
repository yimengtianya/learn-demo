package bean.lee.push.notification.hander;

import bean.lee.push.notification.channel.ChannelManage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

/**
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class MainServerHandler extends ChannelHandlerAdapter {

	private ChannelManage channelManage;

	public MainServerHandler(ChannelManage channelManage) {
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
}
