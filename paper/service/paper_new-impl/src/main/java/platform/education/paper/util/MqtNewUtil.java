package platform.education.paper.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

public class MqtNewUtil {
	 public static List<String> getblank(String htmlStr) {
	        List<String> pics=new ArrayList<String>();
	        String img = "";
	        Pattern p_image;
	        Matcher m_image;
	        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
	        String regEx_img = "<input.*id\\s*=\\s*(.*?)[^>]*?>";
	        p_image = Pattern.compile
	                (regEx_img, Pattern.CASE_INSENSITIVE);
	        m_image = p_image.matcher(htmlStr);
	        while (m_image.find()) {
	            // 得到<img />数据
	            img = m_image.group();
	            // 匹配<img>中的src数据
	            Matcher m = Pattern.compile("id\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
	            while (m.find()) {
	                pics.add(m.group(1));
	            }
	        }
	        return pics;
	    }
	 
	 public static String fixOldDates(String dbAnwser,String questionType,String content,String questionUuid){
			try {
				String db=dbAnwser;
				JSONArray first=JSONArray.fromObject(db);
				ObjectMapper om=new ObjectMapper();
				if(first.size()>0&&first.get(0) instanceof JSONObject){
					
					}else{
						String[] s1=om.readValue(db, String[].class);
						if(questionType.equals("填空题")||questionType.equals("blank")){
								List<String> list=getblank(content);
								JSONArray ary=new JSONArray();
								for(int i=0;i<list.size();i++){
									String s=list.get(i);
									JSONObject obj=new JSONObject();
									obj.put("uuid", s);
									String s2="";
									if(i<=s1.length-1){
										s2=s1[i];
									}
									String[] s3={s2};
									obj.put("answer",s3);
									ary.add(obj);
								}
								db=ary.toString();
					}else{
						JSONArray ary=new JSONArray();
						JSONObject obj=new JSONObject();
						obj.put("uuid",questionUuid);
						obj.put("answer", s1);
						ary.add(obj);
						db=ary.toString();
					}
				}
				return db;
			} catch (Exception e) {
				e.printStackTrace();
				JSONArray first=new JSONArray();
				JSONObject obj=new JSONObject();
				obj.put("uuid", questionUuid);
				String[]  answer={""};
				obj.put("answer", answer);
				first.add(obj);
				return first.toString();
			}
		 
	 }
}
