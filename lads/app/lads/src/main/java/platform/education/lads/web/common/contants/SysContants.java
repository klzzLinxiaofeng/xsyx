package platform.education.lads.web.common.contants;

import platform.education.lads.web.common.util.SysConfigAccessor;



/**
 * <p>Title:SysContants.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统常量
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年6月29日
 */
public class SysContants {
	
	
	public final static String CURRENT_USER = "tbkt_user_info";
        
	
	private final static String SYSTEM_APP_ID_KEY = "system.app.id";
	public final static Integer SYSTEM_APP_ID = SysConfigAccessor.getIntegerProp(SYSTEM_APP_ID_KEY);
	

}
