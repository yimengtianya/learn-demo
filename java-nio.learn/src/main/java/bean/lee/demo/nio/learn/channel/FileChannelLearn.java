package bean.lee.demo.nio.learn.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelLearn {

	public static void main(String[] args) throws IOException {

		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(48);

		// 读取到Buffer
		int bytesRead = inChannel.read(buf);

		while (bytesRead != -1) {

			// System.out.println("Read " + bytesRead);

			// 将Buffer从写模式切换到读模式
			buf.flip();

			while (buf.hasRemaining()) {
				// 从Buffer中读数据
				System.out.print((char) buf.get());
			}

			buf.clear();

			// 读取到Buffer
			bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}

}
