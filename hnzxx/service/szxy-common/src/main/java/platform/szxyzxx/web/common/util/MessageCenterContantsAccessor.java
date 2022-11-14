package platform.szxyzxx.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * <p>Title:SystemCacheHttpConfigAccessor.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统公共配置文件访问器
 * @Author 陈业强
 * @Version 1.0
 * @Date 2015年8月12日
 */
public class MessageCenterContantsAccessor {
	private static Properties pathPops = new Properties();
	private static final String MESSAGE_CENTER_FILE = "config/properties/custom/messageCenter.properties";

	static {
		InputStream inStream = null;
		try {
			inStream = MessageCenterContantsAccessor.class.getClassLoader().getResourceAsStream(MESSAGE_CENTER_FILE);
			pathPops.load(inStream);
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
		return pathPops;
	}

	public static String getStringProp(String key) {
		return pathPops.getProperty(key);
	}
	
	public static Integer getIntegerProp(String key) {
		return Integer.parseInt(pathPops.getProperty(key));
	}
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(pathPops.getProperty(key));
	}
	
}
