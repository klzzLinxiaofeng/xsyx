package platform.szxyzxx.web.sys.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;
import platform.education.user.vo.GroupPermissionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping(value = "/school/self")
public class CeShiCaiDanController extends BaseController {

	@RequestMapping(value = "/indefined")
	public ModelAndView defined(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") GroupPermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		
		PjPermissionCondition permissionCondition = new PjPermissionCondition();
		
		permissionCondition.setSchoolId(user.getSchoolId());
		permissionCondition.setCode(dm);
		List<PjPermission> permissionListString = pjPermissionService.findPjPermissionByCondition(permissionCondition);
		
		if(permissionListString != null&& permissionListString.size()>0){
			PjPermission pjPermission = permissionListString.get(0);
			viewPath = pjPermission.getAccessUrl();
			String checkIp = viewPath.substring(0, viewPath.indexOf("/"));
			if (checkIp.equals("http:") || checkIp.equals("https:")) {
				viewPath = "redirect:"+viewPath;
			}
		}	
		return new ModelAndView(viewPath);
	}

}
