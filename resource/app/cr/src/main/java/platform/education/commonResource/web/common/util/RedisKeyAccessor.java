package platform.education.commonResource.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * <p>Title:SystemCacheHttpConfigAccessor.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统公共配置文件访问器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class RedisKeyAccessor {
	private static Properties casProps = new Properties();
	private static final String CAS_FILE = "redis.key.properties";

	static {
		InputStream inStream = null;
		try {
			inStream = RedisKeyAccessor.class.getClassLoader().getResourceAsStream(CAS_FILE);
			casProps.load(inStream);
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
		return casProps;
	}

	public static String getStringProp(String key) {
		return casProps.getProperty(key);
	}
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(casProps.getProperty(key));
	}
	
}
