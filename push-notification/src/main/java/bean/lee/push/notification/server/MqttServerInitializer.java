package bean.lee.push.notification.server;

import bean.lee.push.notification.channel.ChannelManage;
import bean.lee.push.notification.hander.MqttServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;

public class MqttServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("encoder", new MqttEncoder());
		pipeline.addLast("decoder", new MqttDecoder());
		pipeline.addLast("handler", new MqttServerHandler());
	}
}
