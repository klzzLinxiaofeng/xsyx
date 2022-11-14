package platform.education.commonResource.web.common.contants;

import platform.education.commonResource.web.common.util.CasConfigAccessor;

/**
 * <p>Title:CasConfigContants.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：cas配置常量
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年6月29日
 */
public class CasContants {
	
	
	private final static String CAS_ENABLE_KEY = "cas.enable";
	public final static Boolean CAS_ENABLE = CasConfigAccessor.getBooleanProp(CAS_ENABLE_KEY);

	private final static String CASE_BASE_PATH_KEY = "cas.base.path";
	public final static String CASE_BASE_PATH = CasConfigAccessor.getStringProp(CASE_BASE_PATH_KEY);
	
}
