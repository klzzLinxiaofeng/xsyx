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
import platform.education.user.model.User;
import platform.education.user.vo.GroupAdminVo;
import platform.education.user.vo.UserResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/sys/gadmin")
public class GroupAdminController extends BaseController {

	private final static String viewBasePath = "/sys/gadmin";

	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		if ("list".equals(sub)) {
			List<GroupAdminVo> accounts = groupAdminService.findGroupAdmin(SysContants.USER_TYPE_ADMIN, groupId, SysContants.SYSTEM_APP_ID, page, order);
			model.addAttribute("accounts", accounts);
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(User user, @RequestParam(value = "groupId") Integer groupId) {
		UserResult result = null;
		try {
			result = this.groupAdminService.addDefautGroupAdmin(user, groupId, SysContants.SYSTEM_APP_ID, SysContants.USER_TYPE_ADMIN);
			if (result != null) {
				user = result.getUser();
			}
		} catch (RoleConflictException e) {
			return new ResponseInfomation("roleConflict");
		}
		return result != null && UserResult.STATUS_SUCCESS.equals(result.getStatusCode()) ? new ResponseInfomation(user.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation(result.getStatusCode());
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		User account = this.userService.findUserById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("account", account);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, User user) {
		if (user != null) {
			user.setId(id);
		}
		return this.userService.abandon(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			User user) {
		user.setId(id);
		user = this.userService.modify(user);
		return user != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor/pwd", method = RequestMethod.GET)
	@ResponseBody
	public String resetPwd(@RequestParam("ids[]") Integer[] ids) {
		return this.userService.modifyUsersPassword(ids,
				SysContants.DEFAULT_PASSWORD);
	}

	@RequestMapping(value = "checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "id", required = false) Integer id) {
		boolean isExist = false;
		if ("userName".equals(dxlx)) {
			User user = this.userService.findUserByUsername(username);
			if (user != null) {
				Integer currentId = user.getId();
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

}
