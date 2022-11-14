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

import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
/**
 * <p>Title:AccountRoleController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：账号角色管理器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年8月7日
 */
@Controller
@RequestMapping("/sys/ur")
public class UserRoleController extends BaseController {

	private final static String viewBasePath = "/sys/user";
	
	@RequestMapping(value = "/index")
	public ModelAndView getAssignedRole(
			@RequestParam("userId") Integer userId, 
			Model model) {
		List<UserRole> userRoles = this.userRoleService.findByUserId(userId);
		model.addAttribute("list", userRoles);
		return new ModelAndView(structurePath("/ur_index"), model.asMap());
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@RequestParam(value = "userId", required = true) Integer userId) {
		return new ModelAndView(structurePath("/ur_input"));
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public String creator(
			@ModelAttribute("userRole") UserRole userRole, 
			@RequestParam("roleId") Integer roleId, 
			@RequestParam("userId") Integer userId) {
		userRole.setRole(new Role(roleId));
		userRole.setUser(new User(userId));
		userRole = this.userRoleService.addOrUpdate(userRole);
		return userRole != null ? ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(@RequestParam(value = "id", required = true) Integer id, Model model) {
		UserRole userRole = this.userRoleService.findUserRoleById(id);
		model.addAttribute("ur", userRole);
		return new ModelAndView(structurePath("/ur_input"), model.asMap()); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, UserRole userRole) {
		if(userRole != null) {
			userRole.setId(id);
		}
		return this.userRoleService.remove(userRole); 
	}

	@RequestMapping(value = "/priority/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean priorityChecker(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "priority", required = false) Integer priority){
		boolean isExist = true;
		List<UserRole> userRoleList = userRoleService.findByUserIdAndGroupId(userId, groupId, null);
		if (userRoleList != null && userRoleList.size() > 0) {
			for (UserRole userRole : userRoleList) {
				if (userRole.getPriority().intValue() == priority) {
					isExist = false;
					break;
				}
			}
		}
		return isExist;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
