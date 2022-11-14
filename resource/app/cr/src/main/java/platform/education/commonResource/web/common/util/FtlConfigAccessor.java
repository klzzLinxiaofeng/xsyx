package platform.education.commonResource.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FtlConfigAccessor {
	private static Properties cachePops = new Properties();
	private static final String SYSTEM_FILE = "Ftl.properties";

	static {
		InputStream inStream = null;
		try {
			inStream = SysConfigAccessor.class.getClassLoader().getResourceAsStream(SYSTEM_FILE);
			cachePops.load(inStream);
		} catch (Exception ex) {
			throw new RuntimeException("读取配置文件出错了..." + ex.getMessage(), ex);
		} finally {
			if(inStream != null) {
				try {
					inStream.close();
					inStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getProperty() {
		return cachePops;
	}

	public static String getStringProp(String key) {
		return cachePops.getProperty(key);
	}
	
	public static Integer getIntegerProp(String key) {
		return Integer.parseInt(cachePops.getProperty(key));
	}
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(cachePops.getProperty(key));
	}
	
}
