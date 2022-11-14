package platform.education.rest.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @功能描述: 判断客户端请求是否合法的
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2015年6月30日下午6:04:20
 */
public class ClientCallbackApi {

	
	
	/**
	 * 判断请求是否合法
	 * @param params 参数列表
	 * @param signature 数字签名
	 * @return flag 
	 *          1:验证成功
	 *          -1：已超过有效期
	 *          0：非法diaoyong
	 */
	public static Integer isLegitimacyApi(String[] params,String timeStamp,String signature){
		int flag = 0;
		if(params != null && params.length > 0) {
			//十分钟之内可以重复调用
			if(DateUtil.compareDate(timeStamp)){
				// SHA1加密
				String digest = getDigestApi(params);
				// 确认请求是否合法
				if (digest.equals(signature)) {
					flag = 1;
				}
				
			}else { //时间不合法
				flag = -1;
			}
		}
		
		return flag ;
		
	}
	
	/**
	 * 过滤掉字符串数组中的空值, 避免加密参数时爆空指针异常;
	 * @param params
	 * @return
	 */
	public static String[] filterNullStr(String[] params){
		List<String> filterNullList = new ArrayList<String>(); 
		for(int i=0;i<params.length;i++){
        	String s = params[i];
        	if(s!=null){
        		filterNullList.add(s);
        	}
        }
		return filterNullList.toArray(new String[filterNullList.size()]);
	}
	
	/**
	 * 获取数字签名
	 * @param params 参数列表
	 * @return 数字签名
	 */
	public static String getDigestApi(String[] params){
		//秘钥
		String key = "HnPjDMqTMm%@_2017";
		params = filterNullStr(params);
        Arrays.sort(params); // 字典序排序
        String bigStr = "";
        for(int i=0;i<params.length;i++){
        	bigStr+=params[i];
        }
        bigStr = bigStr+key;
        
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		return digest ;
	}
	public static void main(String[] args) {
		//String sr [] ={"{\"content\":\"地方\",\"posterId\":\"343\",\"posterName\":\"王五\",\"receiverId\":\"168\",\"receiverName\":\"莉莉\",\"receiverType\":\"6\",\"schoolId\":\"1\",\"teamId\":\"7\",\"title\":\"的\"}","1438332590565"};
		//System.out.println(Arrays.toString(sr));
		//System.out.println(isLegitimacyApi(sr,"96e1bc72ae54ee7c2d14d29a08cb8e3d673bb665"));
	//	System.out.println(getDigestApi(sr));
		Date date = new Date();
		long timeS = date.getTime();
		System.out.println(timeS);
		String str[] = {"maiqituo#qyjx#educloud#mobile#seewo","564670","yRgBn5ZC88Y=","1" ,timeS+""};
		//System.out.println(new Date().getTime());
		System.out.println(getDigestApi(str));
		
	}

}
