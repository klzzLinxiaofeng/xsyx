package platform.szxyzxx.web.sys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.user.exception.RoleConflictException;
import platform.education.user.model.Permission;
import platform.education.user.model.Role;
import platform.education.user.vo.RoleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 
 * <p>
 * Title:RoleController.java
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
 * @Function 功能描述：角色管理
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月30日
 */
@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends BaseController {

	private final static String viewBasePath = "/sys/role";

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") RoleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Role> roles = this.roleService.findRoleByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("roles", roles);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/def/index")
	public ModelAndView sysRoleIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") RoleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setGroupId(0);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Role> roles = this.roleService.findRoleByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/def_list");
		} else {
			viewPath = structurePath("/def_index");
		}
		model.addAttribute("roles", roles);
		return new ModelAndView(viewPath, model.asMap());
	}
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(
			@CurrentUser UserInfo user,
			@RequestParam("t") String type,
			@ModelAttribute("condition") RoleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		conditionFilter(user, condition);
		List<Role> roles = roleService.findRoleByCondition(condition, page, order);
		model.addAttribute("roles", roles);
		return new ModelAndView(structurePath("/list" + type), model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") RoleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return roleService.findRoleByCondition(condition, page, order);
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	@RequestMapping(value = "/def/creator", method = RequestMethod.GET)
	public ModelAndView defRoleCreator() {
		return new ModelAndView(structurePath("/def_input"));
	}

	@RequestMapping(value = "/def/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation defRoleCreator(Role role, @CurrentUser UserInfo user) {
		Integer appId = role.getAppId();
		if(appId == null) {
			role.setAppId(SysContants.SYSTEM_APP_ID);
		}
		role.setGroupId(0);
		role.setDefaultRole(true);
		try {
			role = this.roleService.add(role);
		} catch (Exception e) {
			return new ResponseInfomation();
		}
		return role != null ? new ResponseInfomation(role.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Role role, @CurrentUser UserInfo user) {
		Integer groupId = role.getGroupId();
		Integer appId = role.getAppId();
		Boolean defaultRole = role.getDefaultRole();
		if(groupId == null) {
			role.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			role.setAppId(SysContants.SYSTEM_APP_ID);
		}
		role.setDefaultRole(defaultRole != null ? defaultRole : false);
		try {
			role = this.roleService.add(role);
		} catch (RoleConflictException e) {
			return new ResponseInfomation("roleConflict");
		}
		return role != null ? new ResponseInfomation(role.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK, Model model) {
		Role role = this.roleService.findRoleById(id);
		if(isCK.equals("disable")){
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("role", role);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/def/editor", method = RequestMethod.GET)
	public ModelAndView defRoleEditor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK, Model model) {
		Role role = this.roleService.findRoleById(id);
		if(isCK.equals("disable")){
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("role", role);
		return new ModelAndView(structurePath("/def_input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Role role) {
		if (role != null) {
			role.setId(id);
		}
		try {
			this.roleService.remove(role);
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Role role) {
		role.setId(id);
		role = this.roleService.modify(role);
		return role != null ? new ResponseInfomation(role.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping("/grantor")
	public ModelAndView grantor(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "groupId", required=false) Integer groupId,
			Model model) {
		List<Permission> modules;
		if(groupId != null) {
			modules = this.groupPermissionService.findChildrensByCascade("0", groupId, SysContants.SYSTEM_APP_ID);
		} else {
			modules = this.permissionService.findChildrensByCascade("0", SysContants.SYSTEM_APP_ID);
		}
		model.addAttribute("modules", modules);
		return new ModelAndView(structurePath("/grantor"), model.asMap());
	}
	
	@RequestMapping("/group/grantor")
	public ModelAndView groupGrantor(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "groupId", required=false) Integer groupId,
			@CurrentUser UserInfo user, 
			Model model) {
		if(user != null) {
			groupId = groupId != null ? groupId : user.getGroupId();
			List<Permission> modules = this.groupPermissionService.findChildrensByCascade("0", groupId, SysContants.SYSTEM_APP_ID);
			model.addAttribute("modules", modules);
		}
		return new ModelAndView(structurePath("/grantor"), model.asMap());
	}
	
	@RequestMapping(value = "checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id,
			@CurrentUser UserInfo user) {
		boolean isExist = false;
		if ("code".equals(dxlx)) {
			Role role = this.roleService.findUniqueInGroup(SysContants.SYSTEM_APP_ID, user.getGroupId(), code);
			if (role != null) {
				Integer currentId = role.getId();
				if (currentId != null && currentId.equals(id)) {
					isExist = true;
				} else {
					isExist = false;
				}
			} else {
				isExist = true;
			}
		}
		return isExist;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	private void conditionFilter(UserInfo user, RoleCondition condition) {
		Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
}
