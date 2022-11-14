package platform.resource.main.sys;

/**
 * <p>Title:SysContants.java</p>
 * <p>Description:基础架构项目</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统常量
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年10月20日
 */
public class SysContants {
	
	private final static String RESOURCE_SRC_PATH_KEY = "resource.src.path";
	
	public final static String RESOURCE_SRC_PATH = SysContantsAccessor.getStringProp(RESOURCE_SRC_PATH_KEY);
	
	private final static String RESOURCE_SRC_PATH_PREFIX_KEY = "resource.src.path.perfixe";
	
	public final static String RESOURCE_SRC_PATH_PREFIX = SysContantsAccessor.getStringProp(RESOURCE_SRC_PATH_PREFIX_KEY);
	
	private final static String RESOURCE_ERROR_PATH_KEY = "resource.error.path";
	
	public final static String RESOURCE_ERROR_PATH = SysContantsAccessor.getStringProp(RESOURCE_ERROR_PATH_KEY);
	
}
