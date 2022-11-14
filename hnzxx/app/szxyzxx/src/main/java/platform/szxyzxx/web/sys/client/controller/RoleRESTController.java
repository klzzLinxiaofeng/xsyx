package platform.szxyzxx.web.sys.client.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.user.model.Role;
import platform.education.user.vo.RoleCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/client/sys/role")
public class RoleRESTController extends BaseController {
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> jsonList(
			@ModelAttribute("condition") RoleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(condition);
		page = usePage ? page : null;
		return roleService.findRoleByCondition(condition, page, order);
	}
	
	private void conditionFilter(RoleCondition condition) {
		Integer appId = condition.getAppId();
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
	
}
