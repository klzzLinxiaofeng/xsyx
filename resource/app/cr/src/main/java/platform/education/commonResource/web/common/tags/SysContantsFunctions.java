package platform.education.commonResource.web.common.tags;

import platform.education.commonResource.web.common.contants.SysContants;
/**
 * <p>Title:SysContantsAccessor.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：自定义系统常量访问 方便用于EL获取常量值
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class SysContantsFunctions {
	
	public static String currentUserKey() {
		return SysContants.CURRENT_USER;
	}
	
	public static Integer getSystemAppId() {
		return SysContants.SYSTEM_APP_ID;
	}
	
}
