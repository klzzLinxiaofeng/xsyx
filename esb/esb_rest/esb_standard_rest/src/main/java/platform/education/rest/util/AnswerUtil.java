package platform.education.rest.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnswerUtil {
	private static final Logger log = LoggerFactory.getLogger(AnswerUtil.class);
	
	public static Object parseAnswer(String answers) {
		if(answers==null || "".equals(answers)) {
			return "";
		}
		
		JSONArray result = new JSONArray();
		JSONArray array = JSONArray.fromObject(answers);
		for (int i = 0; i < array.size(); i++) {
			JSONObject answer = array.getJSONObject(i);
			JSONArray answerArray = answer.getJSONArray("answer");
			for (int j = 0; j < answerArray.size(); j++) {
				result.add(answerArray.getString(j));
			}
		}
		return result;
	}

	public static Object[] stringToArray(String corretAnswerStr) {
		if(corretAnswerStr==null || "".equals(corretAnswerStr)) {
			return null;
		}
		try{
			Object[] array = JSONArray.fromObject(corretAnswerStr).toArray();
			return array;
		} catch(Exception e) {
			log.error("不合法的字符串对象");
		}
		return null;
	}
}
