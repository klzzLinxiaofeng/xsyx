package platform.szxyzxx.web.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
/**
 * 用年月日时分秒 + 四年随机数字 做为userId
 * @author admin
 *
 */
public class UserAccountUtil {
	public static String  getUserAccount(){
		String userAccount ="";
		try{
			String d = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
			Random r = new Random();
		    int temp = r.nextInt(9000)+1000;
		    userAccount = d+temp;
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return userAccount;
	}
	
}
