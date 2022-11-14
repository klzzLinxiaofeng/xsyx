package platform.szxyzxx.web.sys.controller;

import java.util.ArrayList;
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

import platform.education.user.contants.PermissionContants;
import platform.education.user.model.GroupPermission;
import platform.education.user.model.Permission;
import platform.education.user.vo.PermissionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 
 * <p>
 * Title:ModuleController.java
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
 * @Function 功能描述：模块管理
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月28日
 */
@Controller
@RequestMapping(value = "/sys/module")
public class ModuleController extends BaseController {

	private final static String viewBasePath = "/sys/module";

	/**
	 * 
	 * @Method index
	 * @Function 功能描述：菜单管理首页
	 * @param sub
	 *            首页的子页面 菜单列表
	 * @param condition
	 *            查询列表条件Vo
	 * @param page
	 *            分页Vo
	 * @param order
	 *            排序Vo
	 * @param model
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		order.setProperty("list_order");
		order.setAscending(true);
		String viewPath = null;
		conditionFilter(condition, user);
		List<Permission> modules = permissionService.findPermissionByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("modules", modules);
		Permission parent = this.permissionService.findPermissionByCode(condition.getParentCode());
		model.addAttribute("parent", parent);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> jsonList(@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(condition, user);
		page = usePage ? page : null;
		return permissionService.findPermissionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/tree/index")
	public String indexTree(
			@RequestParam(value = "id", required = false) String id) {
		return structurePath("/tree_index");
	}

	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			@RequestParam(value = "check", required = false, defaultValue = "false") boolean check,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@ModelAttribute("condition") PermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, 
			Model model) {
		order.setProperty("list_order");
		order.setAscending(true);
		conditionFilter(condition, user);
		List<Permission> modules = permissionService.findPermissionByCondition(condition, "1".equals(usePage) ? page : null, order);
		if (check & groupId != null) {
			return moduleToTreeVo(modules, groupId);
		}
		return moduleToTreeVo(modules);
	}

	/**
	 * 
	 * @Method creator
	 * @Function 功能描述：跳转者创建页面
	 * @param parentId
	 *            父资源ID
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@RequestParam(value = "caller", required = false) String caller,
			@RequestParam(value = "parentCode", required = false) String parentCode,
			Model model) {
		Permission parentModule = this.permissionService.findPermissionByCode(parentCode);
		model.addAttribute("parent", parentModule);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 
	 * @Method creator
	 * @Function 功能描述：post请求 即创建菜单
	 * @param permission
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Permission permission, @CurrentUser UserInfo user) {
		Integer appId = permission.getAppId();
		if(appId == null) {
			permission.setAppId(SysContants.SYSTEM_APP_ID);
		}
		permission = this.permissionService.add(permission);
		return permission != null ? new ResponseInfomation(permission.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 
	 * @Method editor
	 * @Function 功能描述：跳转者修改页面
	 * @param id 菜单ID
	 * @param model
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "code", required = true) String code, Model model) {
		Permission module = this.permissionService.findPermissionByCode(code);
		model.addAttribute("module", module);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 
	 * @Method delete
	 * @Function 功能描述：删除某菜单
	 * @param id
	 * @param permission
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "code") String code, Permission permission) {
		permission = this.permissionService.findPermissionByCode(code);
		return this.permissionService.deleteByRecursive(permission);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public Permission getItem(@PathVariable(value = "code") String code) {
		Permission permission = this.permissionService.findPermissionByCode(code);
		return permission;
	}

	/**
	 * 
	 * @Method edit
	 * @Function 功能描述：更新某菜单
	 * @param id
	 * @param permission
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			Permission permission) {
		permission.setId(id);
		permission = this.permissionService.modify(permission);
		return permission != null ? new ResponseInfomation(permission.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		if ("code".equals(dxlx)) {
			Permission permission = this.permissionService
					.findPermissionByCode(code);
			if (permission != null) {
				Integer currentId = permission.getId();
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
	
	private List<TreeVo> moduleToTreeVo(List<Permission> modules) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		for (Permission module : modules) {
			TreeVo vo = new TreeVo();
			vo.setId(module.getCode());
			vo.setpId(module.getParentCode());
			vo.setName(module.getName());
			vo.setIsParent(PermissionContants.LV_4 != module.getLevel() ? true : false);
			vo.setModuleLv(module.getLevel());
			treeVos.add(vo);
		}
		return treeVos;
	}
	
	private List<TreeVo> moduleToTreeVo(List<Permission> modules, Integer groupId) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		for (Permission module : modules) {
			TreeVo vo = new TreeVo();
			vo.setId(module.getCode());
			vo.setpId(module.getParentCode());
			vo.setName(module.getName());
			vo.setIsParent(PermissionContants.LV_4 != module.getLevel() ? true : false);
			vo.setModuleLv(module.getLevel());
			GroupPermission gp = this.groupPermissionService.findUnique(module.getCode(), groupId);
			vo.setChecked(gp != null ? true : false);
//			vo.setChecked(true);
			treeVos.add(vo);
		}
		return treeVos;
	}
	
	private void conditionFilter(PermissionCondition condition, UserInfo user) {
		Integer appId = condition.getAppId();
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}

}
