package platform.education.generalTeachingAffair.utils;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * 重写日期
 * @author Ken
 *
 */
public class DateConvert implements Converter {

	   @SuppressWarnings("rawtypes")
	public Object convert(Class arg0, Object arg1) {
	   if ( arg1 == null ) return null;
	   String targetTypeName = arg0.getName();
	   String paramTypeName = arg1.getClass().getName();
	   if ( targetTypeName.equals(paramTypeName) ) return arg1;
	   String utilDateName = java.util.Date.class.getName();
	   String sqlDateName = java.sql.Date.class.getName();
	   String stringName = String.class.getName();
	   if ( !paramTypeName.equals(utilDateName) || !paramTypeName.equals(sqlDateName) || !paramTypeName.equals(stringName) ) 
		   throw new IllegalArgumentException("未知的日期参数类型");
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   if ( targetTypeName.equals(utilDateName) && paramTypeName.equals(sqlDateName) ) {
		   java.sql.Date param =  (Date) arg1;
		   return new java.util.Date(param.getTime());
	   } else if ( targetTypeName.equals(sqlDateName) && paramTypeName.equals(utilDateName)  ) {
		   java.util.Date param = (java.util.Date) arg1;
		   return new java.sql.Date(param.getTime());
	   }
	   
       String p = (String) arg1;
       if (p == null || p.trim().length() == 0) {
           return null;
       }
       try {
           return df.parse(p.trim());
       } catch (Exception e) {
           try {
               SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
               return dff.parse(p.trim());
           } catch (ParseException ex) {
               throw new IllegalArgumentException("字符串日期格式不正确");
           }
       }

	  }

}
