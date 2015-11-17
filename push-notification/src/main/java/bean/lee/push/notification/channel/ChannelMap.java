package bean.lee.push.notification.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

/**
 * 缓存所有channel
 * 
 * @author Dube
 * @date 2015年11月17日 下午2:15:10
 */
public class ChannelMap {

	private static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

	public static void add(String clientId, SocketChannel socketChannel) {
		map.put(clientId, socketChannel);
		System.out.println("接入：" + clientId + "  当前连接数：" + map.size());
	}

	public static Channel get(String clientId) {

		return map.get(clientId);
	}

	public static void remove(SocketChannel socketChannel) {
		for (Map.Entry<String, SocketChannel> entry : map.entrySet()) {
			if (entry.getValue() == socketChannel) {
				map.remove(entry.getKey());
			}
		}
		System.out.println("断开：" + socketChannel.id().toString() + "  当前连接数：" + map.size());
	}

}
