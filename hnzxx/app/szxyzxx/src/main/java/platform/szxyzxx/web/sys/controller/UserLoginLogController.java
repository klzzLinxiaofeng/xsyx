package platform.szxyzxx.web.sys.controller;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.user.model.User;
import platform.education.user.model.UserLoginLog;
import platform.education.user.service.UserLoginLogService;
import platform.education.user.service.UserService;
import platform.education.user.vo.UserLoginLogCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/sys/user/userLoginLog")
public class UserLoginLogController { 
	
	private final static String viewBasePath = "/sys/user/userloginlog";
	
	@Autowired
	@Qualifier("userLoginLogService")
	private UserLoginLogService userLoginLogService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") UserLoginLogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		order.setAscending(false);
		List<UserLoginLog> items = this.userLoginLogService.findUserLoginLogByCondition(condition, page, order);
		if(items != null && items.size() > 0) {
			for(UserLoginLog userLoginLog:items) {
				if(userLoginLog.getUserId() != null) {
					User _user = this.userService.findUserById(userLoginLog.getUserId());
					userLoginLog.setName(_user.getUserName());
				}
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<UserLoginLog> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") UserLoginLogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.userLoginLogService.findUserLoginLogByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(UserLoginLog userLoginLog, @CurrentUser UserInfo user) {
		/*Integer groupId = userLoginLog.getGroupId();
		Integer appId = userLoginLog.getAppId();
		if(groupId == null) {
			userLoginLog.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			userLoginLog.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		try {
			userLoginLog = this.userLoginLogService.add(userLoginLog);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLoginLog != null ? new ResponseInfomation(userLoginLog.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		UserLoginLog userLoginLog = this.userLoginLogService.findUserLoginLogById(id);
		model.addAttribute("userLoginLog", userLoginLog);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		UserLoginLog userLoginLog = this.userLoginLogService.findUserLoginLogById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("userLoginLog", userLoginLog);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, UserLoginLog userLoginLog) {
		if (userLoginLog != null) {
			userLoginLog.setId(id);
		}
		try {
			this.userLoginLogService.remove(userLoginLog);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, UserLoginLog userLoginLog) {
		userLoginLog.setId(id);
		userLoginLog = this.userLoginLogService.modify(userLoginLog);
		return userLoginLog != null ? new ResponseInfomation(userLoginLog.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/*private void conditionFilter(UserInfo user, UserLoginLogCondition condition) {
		Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}*/
}
