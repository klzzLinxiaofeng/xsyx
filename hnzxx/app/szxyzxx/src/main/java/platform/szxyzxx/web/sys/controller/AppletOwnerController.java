package platform.szxyzxx.web.sys.controller;
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

import platform.education.user.model.AppletOwner;
import platform.education.user.service.AppletOwnerService;
import platform.education.user.vo.AppletOwnerCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/sys/appletOwner")
public class AppletOwnerController extends BaseController{ 
	
	private final static String viewBasePath = "/sys/appletCenter/appletOwner";
	
	@Autowired
	@Qualifier("appletOwnerService")
	private AppletOwnerService appletOwnerService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletOwnerCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<AppletOwner> items = this.appletOwnerService.findAppletOwnerByCondition(condition, page, order);
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
	public List<AppletOwner> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppletOwnerCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appletOwnerService.findAppletOwnerByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AppletOwner appletOwner, @CurrentUser UserInfo user) {
		appletOwner = this.appletOwnerService.add(appletOwner);
		return appletOwner != null ? new ResponseInfomation(appletOwner.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AppletOwner appletOwner = this.appletOwnerService.findAppletOwnerById(id);
		model.addAttribute("appletOwner", appletOwner);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AppletOwner appletOwner = this.appletOwnerService.findAppletOwnerById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("appletOwner", appletOwner);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppletOwner appletOwner) {
		if (appletOwner != null) {
			appletOwner.setId(id);
		}
		try {
			this.appletOwnerService.remove(appletOwner);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AppletOwner appletOwner) {
		appletOwner.setId(id);
		appletOwner = this.appletOwnerService.modify(appletOwner);
		return appletOwner != null ? new ResponseInfomation(appletOwner.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/*private void conditionFilter(UserInfo user, AppletOwnerCondition condition) {
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
