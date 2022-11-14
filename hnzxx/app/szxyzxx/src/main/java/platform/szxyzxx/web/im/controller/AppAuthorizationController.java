package platform.szxyzxx.web.im.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import platform.education.im.model.AppAuthorization;
import platform.education.im.model.ImProvider;
import platform.education.im.service.AppAuthorizationService;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.AppAuthorizationCondition;
import platform.education.im.vo.AppAuthorizationVo;
import platform.education.school.affair.model.Canteen;
import platform.education.user.model.App;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.AppService;
import platform.education.user.vo.AppCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/im/appAuthorization")
public class AppAuthorizationController { 
	
	private final static String viewBasePath = "/im/appAuthorization";
	
	@Autowired
	@Qualifier("appAuthorizationService")
	private AppAuthorizationService appAuthorizationService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("imProviderService")
	private ImProviderService imProviderService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppAuthorizationCondition condition,//condition参数，
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {		
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<AppAuthorization> appAuthorizationList = this.appAuthorizationService.findAppAuthorizationByCondition(condition, page, order);
		List<AppAuthorizationVo> items = new ArrayList<AppAuthorizationVo>();
		AppAuthorizationVo vo = null;
		for(AppAuthorization aaz : appAuthorizationList){
			vo = new AppAuthorizationVo();
			BeanUtils.copyProperties(aaz, vo);
			AppEdition ae = this.appEditionService.findByAppKey(aaz.getAppKey());
			if(ae != null){
				vo.setAppName(ae.getName());
			}
			AppEdition imApp = this.appEditionService.findByAppKey(aaz.getImAccountApp());
			if(imApp != null){
				vo.setImAccountAppName(imApp.getName());
			}
			ImProvider ip = this.imProviderService.findByImType(aaz.getImType());
			if(ip != null){
				vo.setImProviderName(ip.getName());
			}
			items.add(vo);
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
	public List<AppAuthorization> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppAuthorizationCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appAuthorizationService.findAppAuthorizationByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/getAppEditions", method = RequestMethod.GET)
	@ResponseBody
	public List<AppEdition> getAppEditions(){
		return this.appEditionService.findAllAppEditions();
	}
	
	@RequestMapping(value = "/getParentAppGroups", method = RequestMethod.GET)
	@ResponseBody
	public List<AppEdition> getParentAppGroups(@RequestParam(value = "appKey", required = true) String appKey){
		return this.appEditionService.findParentAppGroups(appKey);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AppAuthorization appAuthorization, @CurrentUser UserInfo user,
			@RequestParam(value = "enable", required = true) Boolean enable) {
		if(enable){
			appAuthorization.setImAccountApp(appAuthorization.getAppKey());
		}
		appAuthorization = this.appAuthorizationService.add(appAuthorization);
		return appAuthorization != null ? new ResponseInfomation(appAuthorization.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AppAuthorization appAuthorization = this.appAuthorizationService.findAppAuthorizationById(id);
		model.addAttribute("appAuthorization", appAuthorization);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AppAuthorization appAuthorization = this.appAuthorizationService.findAppAuthorizationById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("appAuthorization", appAuthorization);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppAuthorization appAuthorization) {
		if (appAuthorization != null) {
			appAuthorization.setId(id);
		}
		try {
			this.appAuthorizationService.remove(appAuthorization);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AppAuthorization appAuthorization,
			@RequestParam(value = "enable", required = true) Boolean enable) {
		if(enable){
			appAuthorization.setImAccountApp(appAuthorization.getAppKey());
		}
		appAuthorization.setId(id);
		appAuthorization = this.appAuthorizationService.modify(appAuthorization);
		return appAuthorization != null ? new ResponseInfomation(appAuthorization.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AppAuthorizationCondition condition) {
		condition.setIsDeleted(false);
	}
	/**
	 * 回显检查
	 * @param appKey
	 * @param id
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public boolean editChecker(@RequestParam(value="appKey") String appKey,
			@RequestParam(value="id") Integer id,
			@RequestParam(value = "dxlx", required = false) String dxlx){
		boolean isExist = false;
		AppAuthorizationCondition condition = new AppAuthorizationCondition();
		condition.setAppKey(appKey);
		condition.setIsDeleted(false);
		if ("appKey".equals(dxlx)) {
			List<AppAuthorization> list = appAuthorizationService.findAppAuthorizationByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(AppAuthorization temp : list) {
					currentId = temp.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
}
