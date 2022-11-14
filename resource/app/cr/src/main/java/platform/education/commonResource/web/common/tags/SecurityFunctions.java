package platform.education.commonResource.web.common.tags;

import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.user.service.AclService;

/**
 * <p>
 * Title:SecurityFunctions.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：用于acl 通过判断当期用户的权限 控制页面的按钮显示
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class SecurityFunctions {

	private static AclService aclService;

	public static boolean hasPermission(Integer userId, String permissionCode,
			int permissionType) {
		return aclService.hasPermission(userId, permissionCode, permissionType, SysContants.SYSTEM_SZXY_APP_ID);
	}

	public static void setDmGyFwkzlbService(AclService aclService) {
		SecurityFunctions.aclService = aclService;
	}

	public static void setAclService(AclService aclService) {
		SecurityFunctions.aclService = aclService;
	}

}
