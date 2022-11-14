package platform.szxyzxx.web.sys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.vo.GroupCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/sys/group")
public class GroupController extends BaseController {
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") GroupCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return groupService.findGroupByCondition(condition, page, order);
	}
	
	
	private void conditionFilter(UserInfo user, GroupCondition condition) {
		if(condition != null) {
			String type = condition.getType();
			Integer appId = condition.getAppId();
			condition.setType(type != null ? type : GroupContants.TYPE_SCHOOL);
			condition.setAppId(appId != null ? appId : SysContants.SYSTEM_APP_ID);
		}
	}
	
}
