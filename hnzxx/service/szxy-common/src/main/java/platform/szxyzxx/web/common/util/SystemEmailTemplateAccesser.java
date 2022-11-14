package platform.szxyzxx.web.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * <p>Title:SystemEmailTemplateAccesser.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年11月17日
 */
public class SystemEmailTemplateAccesser {
	private static Properties mailTemplatePops = new Properties();
	private static final String EMAIL_TEMPLATE = "config/properties/custom/sys_email_template.properties";

	static {
		InputStream inStream = null;
		try {
			
			inStream = SystemEmailTemplateAccesser.class.getClassLoader().getResourceAsStream(EMAIL_TEMPLATE);
			mailTemplatePops.load(inStream);
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
		return mailTemplatePops;
	}

	public static String getStringProp(String key) {
		return mailTemplatePops.getProperty(key);
	}
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(mailTemplatePops.getProperty(key));
	}
	
}
