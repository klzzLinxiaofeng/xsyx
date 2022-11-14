package platform.szxyzxx.web.oa.utils;

import java.util.Arrays;


/**
 * @功能描述: 判断客户端请求是否合法的
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2015年6月30日下午6:04:20
 */
public class WechatCallbackApi {

	
	public static boolean isLegitimacyApi(String[] str,String signature){
		boolean flag = false;
		String key = "HnPjDxm@_2014";
        Arrays.sort(str); // 字典序排序
        String bigStr = "";
        for(int i=0;i<str.length;i++){
        	bigStr+=str[i];
        }
        bigStr = bigStr+key;
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        // 确认请求是否合法
        if (digest.equals(signature)) {
        	flag = true;
        }
		
		return flag ;
		
	}
}
