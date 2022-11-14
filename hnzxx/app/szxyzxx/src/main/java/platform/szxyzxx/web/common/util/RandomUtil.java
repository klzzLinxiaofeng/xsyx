package platform.szxyzxx.web.common.util;

public class RandomUtil {
	
	/***
	 * 产生四位随机数字
	 * @return
	 */
	public  static String get4RandomUtil(){
		 int a[] = new int[4];
	      String ss = "";
	      for(int i=0;i<a.length;i++ ) {
	          int str = (int)(10*(Math.random()));
	          ss+=String.valueOf(str);
	      }
	     return ss;
	}

	
}
