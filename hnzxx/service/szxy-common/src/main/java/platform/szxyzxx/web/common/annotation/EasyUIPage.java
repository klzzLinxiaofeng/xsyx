package platform.szxyzxx.web.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p>Title:EasyUIPage.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：easyUi分页组件
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EasyUIPage {
	
	/**
     * 当前用户在session中的key
     *
     * @return
     */
    String value() default "page";
    
    
}
 