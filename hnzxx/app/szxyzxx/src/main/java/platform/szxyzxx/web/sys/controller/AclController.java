package platform.szxyzxx.web.sys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.user.contants.AbandonedDefaultStatus;
import platform.education.user.contants.AclContants;
import platform.education.user.model.RolePermission;
import platform.education.user.vo.RolePermissionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 
 * <p>
 * Title:AclController.java
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
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月31日
 */
@RequestMapping("/sys/acl")
@Controller
public class AclController extends BaseController {

	/**
	 * @Method grant
	 * @Function 功能描述：授权增删改查
	 * @param ztlx
	 * @param ztId
	 * @param permissionCode
	 * @param permissionType
	 * @param isAllow
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/grantor/crud", method = RequestMethod.GET)
	@ResponseBody
	public String grant(
			@RequestParam(value = "lx", required = false, defaultValue = AclContants.TYPE_ROLE) String ztlx,
			@RequestParam(value = "ztbs", required = true) Integer ztId,
			@RequestParam(value = "zybs", required = true) String permissionCode,
			@RequestParam(value = "per", required = true) int permissionType,
			@RequestParam(value = "allow", required = true) boolean isAllow) {
		if(AclContants.TYPE_ROLE.equals(ztlx)) {
			return this.aclService.assignPermissionToRole(ztId, permissionCode, permissionType, isAllow) ?  ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
		}
		return this.aclService.assignPermissionToUser(ztId, permissionCode, permissionType, isAllow) ?  ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
	}
	
	/**
	 * @Method grantAll
	 * @Function 功能描述：进行全部权限授权
	 * @param ztlx
	 * @param ztId
	 * @param permissionCode
	 * @param isAllow
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/grantor/allper", method = RequestMethod.GET)
	@ResponseBody
	public String grantAll(
			@RequestParam(value = "lx", required = false, defaultValue = AclContants.TYPE_ROLE) String ztlx,
			@RequestParam(value = "ztbs", required = true) Integer ztId,
			@RequestParam(value = "zybs", required = true) String permissionCode,
			@RequestParam(value = "allow", required = true) boolean isAllow) {
		if(AclContants.TYPE_ROLE.equals(ztlx)) {
			return this.aclService.assignAllPermissionToRole(ztId, permissionCode, isAllow) ?  ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
		}
		return this.aclService.assignAllPermissionToUser(ztId, permissionCode, isAllow) ?  ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
	}
	
	/**
	 * @Method enable
	 * @Function 功能描述：是否启用某个授权状态
	 * @param ztlx
	 * @param ztId
	 * @param permissionCode
	 * @param isEnable
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/grantor/enable", method = RequestMethod.GET)
	@ResponseBody
	public String enable (
			@RequestParam(value = "lx", required = false, defaultValue = AclContants.TYPE_ROLE) String ztlx,
			@RequestParam(value = "ztbs", required = true) Integer ztId,
			@RequestParam(value = "zybs", required = true) String permissionCode,
			@RequestParam(value = "sfqy", required = true) boolean isEnable) {
		if(AclContants.TYPE_ROLE.equals(ztlx)) {
			return this.aclService.abandonOrRecoverRolePermission(ztId, permissionCode, isEnable);
		}
		return this.aclService.abandonOrRecoverUserPermission(ztId, permissionCode, isEnable);
	}
	
	/**
	 * @Method listByZtbs
	 * @Function 功能描述：获取角色已经授权的
	 * @param user
	 * @param condition
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/role/granted", method = RequestMethod.GET)
	@ResponseBody
	public List<RolePermission> listByZtbs (@CurrentUser UserInfo user, @ModelAttribute("condition") RolePermissionCondition condition) {
		Integer appId = condition.getAppId();
		Integer groupId = condition.getGroupId();
		if (appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
		condition.setGroupId(groupId);
		condition.setState(AbandonedDefaultStatus.STATUS_ENABLED);
		return this.rolePermissionService.findRolePermissionByCondition(condition, null, null);


	}
	
	/**
	 * @Method batchGrantAllPer
	 * @Function 功能描述：批量的授权一批菜单所有权限给予某个角色
	 * @param ztlx
	 * @param ztId
	 * @param permissionCodes
	 * @param isAllow
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/grantor/batch/all", method = RequestMethod.GET)
	@ResponseBody
	public String batchGrantAllPer(
			@RequestParam(value = "lx", required = false, defaultValue = AclContants.TYPE_ROLE) String ztlx,
			@RequestParam(value = "ztbs", required = true) Integer ztId,
			@RequestParam(value = "zys[]", required = true) String[] permissionCodes,
			@RequestParam(value = "allow", required = true) boolean isAllow
			) {
		if(AclContants.TYPE_ROLE.equals(ztlx)) {
			return this.aclService.assignAllPermissionToRole(ztId, permissionCodes, isAllow) ? ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
		}
		return this.aclService.assignAllPermissionToUser(ztId, permissionCodes, isAllow) ? ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
		
	}
	
	/**
	 * @Method batchGrantPer
	 * @Function 功能描述：
	 * @param ztlx
	 * @param ztId
	 * @param permissionCodes
	 * @param permissionType
	 * @param isAllow
	 * @return
	 * @Date 2015年5月11日
	 */
	@RequestMapping(value = "/grantor/batch/crud", method = RequestMethod.GET)
	@ResponseBody
	public String batchGrantPer(
			@RequestParam(value = "lx", required = false, defaultValue = AclContants.TYPE_ROLE) String ztlx,
			@RequestParam(value = "ztbs", required = true) Integer ztId,
			@RequestParam(value = "zys[]", required = true) String[] permissionCodes,
			@RequestParam(value = "per", required = true) int permissionType,
			@RequestParam(value = "allow", required = true) boolean isAllow
			) {
		
		if(AclContants.TYPE_ROLE.equals(ztlx)) {
			return this.aclService.assignPermissionToRole(ztId, permissionCodes, permissionType, isAllow) ? ResponseInfomation.OPERATION_SUC
					: ResponseInfomation.OPERATION_FAIL;
		}
		return this.aclService.assignPermissionToUser(ztId, permissionCodes, permissionType, isAllow) ? ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
		
	}

}
