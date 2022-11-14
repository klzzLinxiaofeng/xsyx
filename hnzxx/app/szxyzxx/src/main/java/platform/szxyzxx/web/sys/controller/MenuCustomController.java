package platform.szxyzxx.web.sys.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;
import platform.education.user.contants.PermissionContants;
import platform.education.user.model.Permission;
import platform.education.user.vo.GroupPermissionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/sys/menucustom")
public class MenuCustomController extends BaseController { 
	private final static String viewBasePath = "/sys/menucustom";
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") GroupPermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Permission> items = this.groupPermissionService.findPermissionByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("modules", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") GroupPermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.groupPermissionService.findPermissionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/tree/index")
	public String indexTree(@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,Model model) {
		PjPermissionCondition pjPermissionCondition = new PjPermissionCondition();
		pjPermissionCondition.setType("0");
		List<PjPermission> pjPermissions = pjPermissionService.findPjPermissionByCondition(pjPermissionCondition,page,order);
		model.addAttribute("pjPermissions", pjPermissions);
		return structurePath("/tree_index");
	}
	
	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			@ModelAttribute("condition") GroupPermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		order.setProperty("list_order");
		order.setAscending(true);
		conditionFilter(user, condition);
		List<Permission> modules = groupPermissionService.findPermissionByCondition(condition, "1".equals(usePage) ? page : null, order);
		return moduleToTreeVo(modules);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(@RequestParam(value="permissionCode",required=true)String permissionCode) {
		Permission permission = permissionService.findPermissionByCode(permissionCode);
		PjPermission pjPermission = new PjPermission();
		BeanUtils.copyProperties(permission, pjPermission);
		pjPermission.setId(null);
		pjPermission.setType("0");
		PjPermission pjPermissionadd = pjPermissionService.add(pjPermission);
		return pjPermissionadd != null ? new ResponseInfomation(pjPermissionadd.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PjPermission pjPermission) {
		if (pjPermission != null) {
			pjPermission.setId(id);
		}
		try {
			this.pjPermissionService.remove(pjPermission);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
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
	
	private void conditionFilter(UserInfo user, GroupPermissionCondition condition) {
		Integer groupId = condition.getGroupId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
	}
}
