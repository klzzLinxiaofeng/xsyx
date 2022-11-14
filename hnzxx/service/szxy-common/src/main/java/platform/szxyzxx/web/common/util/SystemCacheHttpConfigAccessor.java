package platform.szxyzxx.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * <p>Title:SystemCacheHttpConfigAccessor.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统缓存配置文件访问器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class SystemCacheHttpConfigAccessor {
	private static Properties cachePops = new Properties();
	private static final String HTTP_CACHE = "config/properties/custom/sys_data_cache.properties";

	static {
		InputStream inStream = null;
		try {
			inStream = SystemCacheHttpConfigAccessor.class.getClassLoader().getResourceAsStream(HTTP_CACHE);
			cachePops.load(inStream);
		} catch (Exception ex) {
			throw new RuntimeException("读取配置文件出错了..." + ex.getMessage(), ex);
		} finally {
			if(inStream != null) {
				try {
					inStream.close();
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
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(cachePops.getProperty(key));
	}
	
}
