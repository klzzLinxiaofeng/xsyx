package platform.education.oa.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class UUIDUtils {
	
	
	public static String getUserCodeUUID(){
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance();
		String date=df.format(d);
		String str[]=date.split("-");
		String s="";
		for(String i:str){
			s+=i;
		}
		
		return s+d.getTime();
		
	}
	public static String getUserCode(){
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance();
		String date=df.format(d);
		String str[]=date.split("-");
		String s="";
		for(String i:str){
			s+=i;
		}
		
		return s;
		
	}
	

	public static String getLogTime(){
        Date d=new Date();
		
		DateFormat df=DateFormat.getDateTimeInstance();
		String date=df.format(d);
		return date;
	}
	
	 /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
    	//去掉“-”符号 
        String s = java.util.UUID.randomUUID().toString().replaceAll("-", ""); 
        
        return s; 
    } 
    
    public static String getCode(){
    	// 取随机产生的认证码(4位数字)

		String codeList = "1234567890";
		String sRand="";
		//生成随机类
		Random random = new Random();


		for (int i=0;i<6;i++){
			int a=random.nextInt(codeList.length()-1);
			  String rand=codeList.substring(a,a+1);
			  sRand+=rand;

		}
		 
    	return sRand;
    }

    
    public static String getFamiilyId(){
    	// 取随机产生的认证码(4位数字)

		String codeList = "1234567890";
		String sRand="";
		//生成随机类
		Random random = new Random();

		
		boolean flag=true;
		do{
			int a=random.nextInt(codeList.length()-1);
			  String rand=codeList.substring(a,a+1);
			  if(sRand.length()==5){
				  if(!"4".equals(rand)&&!"7".equals(rand)){
					 
					  sRand+=rand;
				  }
				  
			  }else{
				  sRand+=rand;
			  }
			  
			  if(sRand.length()==6){
				
					flag=false;
				}
			  
			
		}while(flag);
		
    	return sRand;
    }
	public static void main(String[] args) {
		/*Date d =new Date();
		DateFormat df=DateFormat.getDateTimeInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dd=sdf.format(d);
		
		System.out.println(dd);*/
		
		System.out.println(getUUID());
	}
	
	
}
