package platform.education.generalTeachingAffair.utils;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

/**
* 重写BeanUtils.copyProperties
*
* @author Ken
*/
public class BeanUtilsSub extends BeanUtils {
   static {
       ConvertUtils.register(new DateConvert(), java.util.Date.class);
       ConvertUtils.register(new DateConvert(), java.sql.Date.class);
   }

   public static void copyProperties(Object dest, Object orig) {
       try {
           BeanUtils.copyProperties(dest, orig);
       } catch (IllegalAccessException ex) {
           ex.printStackTrace();
       } catch (InvocationTargetException ex) {
           ex.printStackTrace();
       }
   }
}
