package platform.education.paper.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 将不规则JSON 字符串数组转为数组的工具
 * @author pantq
 *
 */
public class MqtPaperUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String[] StringToArray(String source,String domain){
		String[] sourceArray = new String[]{};
		
		try{
			//替换域名地址
			if(source != null && !"[]".equals(source)){ 
				if(JsonValidator.validate(source)){ //标准JSON格式解析
					//source = replaceDomain(source,domain);
					sourceArray = mapper.readValue(source, String[].class);
				}else{
					//source = replaceDomain(source,domain);
					if(source.contains("(")){
						sourceArray = source.split("\",");
					}else{
						sourceArray = source.split(",");
					}
				}
				
				int len = sourceArray.length;
				//sourceArray = new String[len];
				for(int i= 0; i < len;i++){
					sourceArray[i] = replaceDomain(sourceArray[i],domain);
				}
				
			}
		}catch(Exception e){
			System.out.println("无法把空对象或者空字符串转为数组");
		}
		
		return sourceArray;
	}
	
	/**
	 * 将带有图片的数组拆分 处理，处理完再转成JSON字符串
	 * @param source
	 * @param targerPath
	 * @return
	 */
	public static String isJson(String source,String targerPath){
		if(source != null && !"".equals(source) && source.contains("<img")){
			
			try {
				List<String> newList = new ArrayList<String>();
				String[] list = null;
				if(JsonValidator.validate(source)){ //标准JSON格式解析
					list = mapper.readValue(source, String[].class);
				
				}else{ //非标准JSON格式 当字符串拆分处理
					list = source.split(",");
					
				}
				
				if(list != null && list.length > 0){
					String newString = "";
					for(String s:list){
						newString = MqtPaperUtil.replaceDomain(s,targerPath);
						//newString = newString.substring(1, newString.length() - 1);
						newList.add(newString);
				}
				
			}
				source = mapper.writeValueAsString(newList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//source = StringEscapeUtils.unescapeJava(source);
		return source;
	}

	
	/**
	* @Title: replaceDomain
	* @author pantq 
	* @Description: 如有图片转换为http图片格式
	* @param content
	* @param domain
	* @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String replaceDomain(String source,String domain){
	//check
				if(source == null || source.trim().length() == 0)
					return "";
				if(source.contains("<img")){
				source = source.replace("${@}$", "");
				source = source.trim();
				source = source.replaceAll("", "");
				source = source.replaceAll("&quot;","");
				source = source.replace("[", "");
				source = source.replace("]", "");
				source = source.replace("&nbsp;", " ");
				source = source.replaceAll("&plusmn;", "±");
				source = source.replace("\n", "");
				
				//init rs
				//int rs = 0;
				
				//new text
				StringBuilder sb = new StringBuilder();
				source = StringEscapeUtils.unescapeJava(source);
				//start text
				String st = "src=\""; 
				String et = "\"";
				
				//handle
				int start = source.indexOf(st);
				//check start
				if(start < 0){
					
					//由于数据库存在 三个/所以加多一层判断，兰数据
					st = "src=\\\"";
					start = source.indexOf(st);
					if(start < 0){
						return source;
					}
				}
				
				//handle_1
				String[] array = source.split(st);
				if(array.length < 2)
					return source;
				
				//handle_2
				sb.append(array[0]);
				for(int index = 1 ; index < array.length ; index++){
					//check array
					if(array[index].trim().length() == 0)
						continue;
					
					//init next
					int next = array[index].indexOf(et);
					
					//check next
					if(next < 0)
						continue;
					
					
					//handle src
					String srcText = array[index].substring(0, next);
					
					//String fileSrc =  fileResult.getEntityFile().getRelativePath();;
					
					//原图片路径
					//System.out.println("srcText : " + srcText);
					if(srcText.trim().length() == 0)
						continue;
					
					//append st
					sb.append(st);
					sb.append(domain +srcText + et + array[index].substring(next + 1, array[index].length()));
				}
				
				return sb.toString();
			}
			return source;
			//return 
}

	public static String[] parseAnswer(String answers, String domain) {
		if(answers==null || "".equals(answers)) {
			return null;
		}
		
		JSONArray result = new JSONArray();
		JSONArray array = JSONArray.fromObject(answers);
		for (int i = 0; i < array.size(); i++) {
			JSONObject answer = array.getJSONObject(i);
			JSONArray answerArray =new JSONArray();
		    answerArray = answer.getJSONArray("answer");
			for (int j = 0; j < answerArray.size(); j++) {
				result.add(answerArray.getString(j));
			}
			if(answerArray.size()==0){
				result.add("");
			}
		}
		return StringToArray(result.toString(), domain);
	} 
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
	 
	 public static String fixOldDate(String dbAnwser,String questionType,String content,String questionUuid){
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
