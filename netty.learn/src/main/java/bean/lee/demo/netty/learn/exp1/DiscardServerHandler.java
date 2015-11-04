package bean.lee.demo.netty.learn.exp1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * DISCARD服务(丢弃服务，指的是会忽略所有接收的数据的一种协议)
 * <p>
 * 1、DisCardServerHandler 继承自
 * ChannelHandlerAdapter，这个类实现了ChannelHandler接口，ChannelHandler提供了许多事件处理的接口方法，
 * 然后你可以覆盖这些方法。现在仅仅只需要继承ChannelHandlerAdapter类而不是你自己去实现接口方法。<br>
 * <br>
 * 2、这里我们覆盖了chanelRead()事件处理方法。每当从客户端收到新的数据时，这个方法会在收到消息时被调用，这个例子中，
 * 收到的消息的类型是ByteBuf, 通常channelRead的实现为： 
 * public void channelRead(ChannelHandlerContext ctx, Object msg) { 
 * 		try { 
 * 			//Do something with msg 
 * 		} finally { 
 * 			ReferenceCountUtil.release(msg); 
 * 		} 
 * }
 * <br>
 * 3、exceptionCaught()事件处理方法是当出现Throwable对象才会被调用，
 * 即当Netty由于IO错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来并且把关联的channel给关闭掉。
 * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。
 * 
 * @author Dube
 * @date 2015年11月4日 下午2:43:04
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {// (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {// (2)
		// 默默的丢掉接收的数据
		System.out.println("丢掉一条消息");
		((ByteBuf) msg).release();

	}

	/**
	 * 观察接收到的数据 1、这个低效的循环事实上可以简化为:System.out.println(in.toString(io.netty.util.
	 * CharsetUtil.US_ASCII)) 2、或者，你可以在这里调用in.release()。
	 */
	/*@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				// (1) System.out.print((char) in.readByte());
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg); // (2)
		}
	}*/

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (3)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
