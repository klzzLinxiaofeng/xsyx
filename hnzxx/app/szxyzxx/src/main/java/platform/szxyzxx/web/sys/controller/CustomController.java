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

import platform.education.hnzxx.ui.model.PjPermission;
import platform.education.hnzxx.ui.vo.PjPermissionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping(value = "/sys/custom")
public class CustomController extends BaseController {

	private final static String viewBasePath = "/sys/custom";

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PjPermissionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<PjPermission> pjPermissions = new ArrayList<PjPermission>();
		List<String> codes = groupPermissionService.findPermissionCodesByGroupId(user.getGroupId(),SysContants.SYSTEM_APP_ID);
		for (String code : codes) {
				PjPermission pjPermission1 = pjPermissionService.findByCodeANDtype(code, "1");
				if (pjPermission1 == null) {
					PjPermission pjPermission = pjPermissionService.findByCodeANDtype(code, "0");
					if (pjPermission !=null) {
						pjPermission.setSchoolId(user.getSchoolId());
						pjPermission.setType("1");
						pjPermission.setId(null);
						pjPermissionService.add(pjPermission);
						pjPermissions.add(pjPermission);
					}
				}else{
					pjPermissions.add(pjPermission1);
				}
		}
		if (pjPermissions.isEmpty()) {
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("pjPermissions", pjPermissions);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,Model model,PjPermission pjPermission) {
		if (id !=null) {
			pjPermission = pjPermissionService.findPjPermissionById(id);
		}
		model.addAttribute("pjPermission", pjPermission);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PjPermission pjPermission) {
		pjPermission.setId(id);
		pjPermission = this.pjPermissionService.modify(pjPermission);
		return pjPermission != null ? new ResponseInfomation(pjPermission.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
