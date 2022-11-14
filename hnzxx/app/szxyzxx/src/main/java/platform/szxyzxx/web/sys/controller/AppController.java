package platform.szxyzxx.web.sys.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.user.model.App;
import platform.education.user.model.AppRelease;
import platform.education.user.service.AppReleaseService;
import platform.education.user.vo.AppCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/sys/app")
public class AppController extends BaseController { 
	
	private final static String viewBasePath = "/sys/app";
	@Resource
	private AppReleaseService appReleaseService;
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<App> items = this.appService.findAppByCondition(condition, page, order);
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
	public List<App> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appService.findAppByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(App app, @CurrentUser UserInfo user) {
		String trademark = fileService.relativePath2HttpUrlByUUID(app.getTrademark());
		app.setTrademark(trademark);
		app = this.appService.add(app);
		return app != null ? new ResponseInfomation(app.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		App app = this.appService.findAppById(id);
		model.addAttribute("app", app);
		String trademark = app.getTrademark();
		if (trademark !=null && !trademark.equals("")) {
			StringBuffer sb=new StringBuffer(trademark);
			sb=sb.reverse();
			String substring = sb.substring(sb.indexOf("."), sb.indexOf("/"));
			StringBuffer sb1=new StringBuffer(substring);
			sb1=sb1.reverse();
			trademark = sb1.substring(0, sb1.length()-1);
			EntityFile tentity = entityFileService.findFileByMD5(trademark);
			model.addAttribute("tentity", tentity);
		}
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		App app = this.appService.findAppById(id);
		model.addAttribute("isCK", "true");
		model.addAttribute("app", app);
		String trademark = app.getTrademark();
		if (trademark !=null && !trademark.equals("")) {
			StringBuffer sb=new StringBuffer(trademark);
			sb=sb.reverse();
			String substring = sb.substring(sb.indexOf("."), sb.indexOf("/"));
			StringBuffer sb1=new StringBuffer(substring);
			sb1=sb1.reverse();
			trademark = sb1.substring(0, sb1.length()-1);
			EntityFile tentity = entityFileService.findFileByMD5(trademark);
			model.addAttribute("tentity", tentity);
		}
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, App app) {
		if (app != null) {
			app.setId(id);
		}
		
		List<AppRelease> appReleaseList = appReleaseService.findByAppId(id, null, null);
		if(appReleaseList != null && appReleaseList.size() > 0){
			return "hasData";
		}
		
		try {
			this.appService.remove(app);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, App app) {
		app.setId(id);
		String trademark = fileService.relativePath2HttpUrlByUUID(app.getTrademark());
		if (trademark != null && !trademark.equals("")) {
			app.setTrademark(trademark);
		}
		app = this.appService.modify(app);
		return app != null ? new ResponseInfomation(app.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AppCondition condition) {
		condition.setIsDeleted(false);
	}
}
