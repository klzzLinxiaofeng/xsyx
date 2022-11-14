//package platform.szxyzxx.web.im.controller;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import platform.education.im.model.UserAuthorization;
//import platform.education.im.service.UserAuthorizationService;
//import platform.education.im.vo.UserAuthorizationCondition;
//import platform.szxyzxx.web.common.annotation.CurrentUser;
//import platform.szxyzxx.web.common.contants.SysContants;
//import platform.szxyzxx.web.common.util.ResponseInfomation;
//import platform.szxyzxx.web.common.vo.UserInfo;
//import framework.generic.dao.Order;
//import framework.generic.dao.Page;
//
//
//
//
//@Controller
//@RequestMapping("/im/userauthorization")
//public class UserAuthorizationController { 
//	
//	private final static String viewBasePath = "/im/userauthorization";
//	
//	@Autowired
//	@Qualifier("userAuthorizationService")
//	private UserAuthorizationService userAuthorizationService;
//	
//	@RequestMapping(value = "/index")
//	public ModelAndView index(
//			@CurrentUser UserInfo user,
//			@RequestParam(value = "dm", required = false) String dm,
//			@RequestParam(value = "sub", required = false) String sub,
//			@ModelAttribute("condition") UserAuthorizationCondition condition,
//			@ModelAttribute("page") Page page,
//			@ModelAttribute("order") Order order, Model model) {
//		String viewPath = null;
//		conditionFilter(user, condition);
//		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
//		List<UserAuthorization> items = this.userAuthorizationService.findUserAuthorizationByCondition(condition, page, order);
//		if ("list".equals(sub)) {
//			viewPath = structurePath("/list");
//		} else {
//			viewPath = structurePath("/index");
//		}
//		model.addAttribute("items", items);
//		return new ModelAndView(viewPath, model.asMap());
//	}
//	
//	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
//	@ResponseBody
//	public List<UserAuthorization> jsonList(
//			@CurrentUser UserInfo user, 
//			@ModelAttribute("condition") UserAuthorizationCondition condition,
//			@ModelAttribute("page") Page page,
//			@ModelAttribute("order") Order order,
//			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		conditionFilter(user, condition);
//		page = usePage ? page : null;
//		return this.userAuthorizationService.findUserAuthorizationByCondition(condition, page, order);
//	}
//	
//	@RequestMapping(value = "/creator", method = RequestMethod.GET)
//	public ModelAndView creator() {
//		return new ModelAndView(structurePath("/input"));
//	}
//
//	@RequestMapping(value = "/creator", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseInfomation creator(UserAuthorization userAuthorization, @CurrentUser UserInfo user) {
//		userAuthorization = this.userAuthorizationService.add(userAuthorization);
//		return userAuthorization != null ? new ResponseInfomation(userAuthorization.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
//	}
//
//	@RequestMapping(value = "/editor", method = RequestMethod.GET)
//	public ModelAndView editor(
//			@RequestParam(value = "id", required = true) Integer id, Model model) {
//		UserAuthorization userAuthorization = this.userAuthorizationService.findUserAuthorizationById(id);
//		model.addAttribute("userAuthorization", userAuthorization);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
//	
//	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
//	public ModelAndView viewer(
//			@RequestParam(value = "id", required = true) Integer id,
//			Model model) {
//		UserAuthorization userAuthorization = this.userAuthorizationService.findUserAuthorizationById(id);
//		model.addAttribute("isCK", "disable");
//		model.addAttribute("userAuthorization", userAuthorization);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public String delete(@PathVariable(value = "id") Integer id, UserAuthorization userAuthorization) {
//		if (userAuthorization != null) {
//			userAuthorization.setId(id);
//		}
//		try {
//			this.userAuthorizationService.remove(userAuthorization);
//		} catch (Exception e) {
//			return ResponseInfomation.OPERATION_FAIL;
//		}
//		return ResponseInfomation.OPERATION_SUC;
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	@ResponseBody
//	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, UserAuthorization userAuthorization) {
//		userAuthorization.setId(id);
//		userAuthorization = this.userAuthorizationService.modify(userAuthorization);
//		return userAuthorization != null ? new ResponseInfomation(userAuthorization.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
//	}
//	
//	private String structurePath(String subPath) {
//		return viewBasePath + subPath;
//	}
//	
//	private void conditionFilter(UserInfo user, UserAuthorizationCondition condition) {
//	}
//}
