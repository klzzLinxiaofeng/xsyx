package platform.szxyzxx.web.sys.controller;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.user.model.User;
import platform.education.user.model.UserRegion;
import platform.education.user.vo.UserCondition;
import platform.education.user.vo.UserRegionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@RequestMapping("/sys/userregion")
public class UserRegionController extends BaseController { 
	
	private final static String viewBasePath = "/sys/userregion";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") UserCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<User> accounts = userService.findUserByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		model.addAttribute("accounts", accounts);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRegion> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") UserRegionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.userRegionService.findUserRegionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@RequestParam("userId") Integer userId, Model model) {
		
		UserRegionCondition condition = new UserRegionCondition();
		condition.setUserId(userId);
		List<UserRegion> urs = this.userRegionService.findUserRegionByCondition(condition);
		if(urs != null && urs.size() > 0) {
			model.addAttribute("userRegion", urs.get(0));
		}
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(UserRegion userRegion, @CurrentUser UserInfo user) {
		Integer appId = userRegion.getAppId();
		if(appId == null) {
			userRegion.setAppId(SysContants.SYSTEM_APP_ID);
		}
		userRegion.setCreateUserId(user.getId());
		userRegion = this.userRegionService.add(userRegion);
		return userRegion != null ? new ResponseInfomation(userRegion.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		UserRegion userRegion = this.userRegionService.findUserRegionById(id);
		model.addAttribute("userRegion", userRegion);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		UserRegion userRegion = this.userRegionService.findUserRegionById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("userRegion", userRegion);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, UserRegion userRegion) {
		if (userRegion != null) {
			userRegion.setId(id);
		}
		try {
			this.userRegionService.remove(userRegion);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, UserRegion userRegion) {
		userRegion.setId(id);
		userRegion = this.userRegionService.modify(userRegion);
		return userRegion != null ? new ResponseInfomation(userRegion.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, UserRegionCondition condition) {
		Integer appId = condition.getAppId();
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
	
	private void conditionFilter(UserInfo user, UserCondition condition) {
		Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null) {
			if(groupId == null) {
				condition.setGroupId(user.getGroupId());
			}
			if(appId == null) {
				condition.setAppId(SysContants.SYSTEM_APP_ID);
			}
		}
		condition.setIsDeleted(false);
	}
}
