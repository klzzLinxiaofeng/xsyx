package platform.szxyzxx.web.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * <p>Title:JsonValueProcessorImpl.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：格式化JSON字符串
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月7日
 */
public class JsonValueProcessorImpl implements JsonValueProcessor {
	private String format = "yyyy-MM-dd HH:mm:ss";

	public JsonValueProcessorImpl() {
	}

	public JsonValueProcessorImpl(String format) {
		this.format = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		String[] obj = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date[] dates = (Date[]) value;
			obj = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				obj[i] = sf.format(dates[i]);
			}
		}
		return obj;
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value instanceof Date) {
			String str = new SimpleDateFormat(format).format((Date) value);
			return str;
		}
		return value == null ? value : value.toString();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
