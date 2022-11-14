package platform.szxyzxx.web.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * <p>
 * Title:JSONUtil.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：JSONUtil
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月7日
 */
@SuppressWarnings({ "unchecked" })
public class JSONUtil {


	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @Method JSONArray
	 * @Function 功能描述：解析数组
	 * @param array
	 * @return
	 * @Date Aug 31, 2012
	 */
	public static JSONArray JSONArray(Object array) {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessorImpl());
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessorImpl());
		JSONArray a = JSONArray.fromObject(array, cfg);
		return a;
	}

	/**
	 * @Method JSONObject
	 * @Function 功能描述：解析对象
	 * @param obj
	 * @return
	 * @Date Aug 31, 2012
	 */
	public static JSONObject JSONObject(Object obj) {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessorImpl());
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessorImpl());
		JSONObject o = JSONObject.fromObject(obj, cfg);
		return o;
	}

	/**
	 * @Method JSON2Map
	 * @Function 功能描述：JSON字符串转换为列表Map
	 * @param param
	 * @return
	 * @Date Sep 19, 2012
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map> JSON2Map(String param) {
		JSONArray j = JSONArray(param);
		HashMap[] r = (HashMap[]) JSONArray.toArray(j, HashMap.class);
		List list = new MyArrayList();
		for (int i = 0; i < r.length; i++) {
			list.add(r[i]);
		}
		return list;
	}

	/**
	 * @Method JSON2Bean
	 * @Function 功能描述：json数组串转换为List
	 * @param param
	 * @param bean
	 * @return
	 * @Date Sep 19, 2012
	 */
	@SuppressWarnings("rawtypes")
	public static List JSON2Bean(String param, Class bean) {
		JSONArray j = JSONArray(param);
		Object[] r = (Object[]) JSONArray.toArray(j, bean);
		List list = new MyArrayList();
		for (int i = 0; i < r.length; i++) {
			list.add(r[i]);
		}
		return list;
	}

	public static <T> T parseJson(String json,Class<T> cls){
		try {
			return cls.equals(String.class) ? (T)json : objectMapper.readValue(json, cls);
		} catch (IOException e) {
			throw new RuntimeException("JSON解析失败",e);
		}
	}

	public static String toJson(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("序列化为JSON失败",e);
		}
	}

}
