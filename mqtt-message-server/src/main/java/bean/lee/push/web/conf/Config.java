package bean.lee.push.web.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public static String zkAddress = "";

	static {
		Properties prop = new Properties();
		InputStream stream = null;
		try {
			stream = new FileInputStream("conf/pn.properties");
			prop.load(stream);
			zkAddress = prop.getProperty("zookeeper").trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
