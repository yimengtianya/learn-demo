package bean.lee.demo.netty.learn.exp3.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * TIME服务客户端
 * <p>
 * 在Netty中,编写服务端和客户端最大的并且唯一不同的使用了不同的BootStrap和Channel的实现。<br>
 * <br>
 * 1、BootStrap和ServerBootstrap类似,不过他是对非服务端的channel而言，比如客户端或者无连接传输模式的channel。<br>
 * 2、如果你只指定了一个EventLoopGroup，那他就会即作为一个‘boss’线程，也会作为一个‘workder’线程，尽管客户端不需要使用到‘
 * boss’线程。<br>
 * <br>
 * 3、代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel被创建时使用。<br>
 * <br>
 * 4、不像在使用ServerBootstrap时需要用childOption()方法，因为客户端的SocketChannel没有父channel的概念。
 * <br>
 * <br>
 * 5、我们用connect()方法代替了bind()方法。
 * 
 * @author Dube
 * @date 2015年11月4日 下午4:05:23
 */
public class TimeClient {

	public static void main(String[] args) throws Exception {
		String host = "127.0.0.1";
		int port = 8080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			// Start the client.
			ChannelFuture f = b.connect(host, port).sync(); // (5)
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
