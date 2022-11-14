package platform.szxyzxx.web.sys.controller;
import java.util.ArrayList;
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

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.user.model.UserWeb;
import platform.education.user.service.UserWebService;
import platform.education.user.vo.UserWebCondition;
import platform.service.uin.service.UinUserService;
import platform.service.uin.vo.UinCompUser;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.contants.ServiceContants;
import platform.szxyzxx.web.sys.vo.UINServiceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/user/service")
public class UserWebController extends BaseController{ 
	
	private final static String viewBasePath = "sys/userservice";
	
	@Resource(name = "userWebService")
	private UserWebService userWebService;
	
	@Resource
	private UinUserService uinUserService;
	
	@RequestMapping(value = "/index") 
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SchoolCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		order.setAscending(true);
		List<School> schoolList = schoolService.findSchoolByCondition(condition, page, order);
		UserWebCondition userWebCondition = new UserWebCondition();
		List<UINServiceVo> voList = new ArrayList<UINServiceVo>();
		if(schoolList.size() > 0 ){
			for(School sc : schoolList){
				UINServiceVo vo = new UINServiceVo();
				vo.setSchoolEnglishName(sc.getEnglishName());
				vo.setSchoolName(sc.getName());
				Integer schoolId = sc.getId();
				if(schoolId != null){
					vo.setSchoolId(schoolId);
					userWebCondition.setSchoolId(schoolId);
					userWebCondition.setAccountType(ServiceContants.AC_TYPE_XUE_XIAO);
					userWebCondition.setServiceType(ServiceContants.SERVICE_TYPE_HUI_YI);
					List<UserWeb> list = userWebService.findUserWebByCondition(userWebCondition);
					if(list.size() > 0){
						UserWeb uw = list.get(0);
						vo.setOpenResult(uw.getOpenResult());
						vo.setExt(uw.getExt());
						vo.setAccount(uw.getAccount());
						vo.setCreateDate(uw.getCreateDate());
					}
				}
				voList.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", voList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<UserWeb> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") UserWebCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.userWebService.findUserWebByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			@RequestParam(value = "schoolId", required = true) Integer schoolId,
			@RequestParam(value = "schoolEnglishName", required = true) String schoolEnglishName,
			@RequestParam(value = "schoolName", required = true) String schoolName,
			UserWeb userWeb, @CurrentUser UserInfo user) {
		School info = schoolService.findSchoolById(schoolId);
		if(info != null){
			UinCompUser cu = new UinCompUser();
			cu.setCloudaccount(schoolEnglishName);
			cu.setCompSuffix(schoolEnglishName);
			cu.setCompname(schoolName);
			cu.setPassword(ServiceContants.DEFAULT_PWD);
			try {
				
				String result = uinUserService.register(cu);
				if(result != null){
					if(!"isExit".equals(result)){
						userWeb.setAccount(result);
						userWeb.setAccountType(ServiceContants.AC_TYPE_XUE_XIAO);
						userWeb.setExt("@" + schoolEnglishName);
						userWeb.setOpenResult(ServiceContants.OPEN_SUCCESS);
						userWeb.setSchoolId(schoolId);
						userWeb.setServiceType(ServiceContants.SERVICE_TYPE_HUI_YI);
						userWeb = this.userWebService.add(userWeb);
					}else{
						return new ResponseInfomation("", ResponseInfomation.OPERATION_ERROR);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseInfomation("", ResponseInfomation.OPERATION_ERROR);
			}
		}else{
			return new ResponseInfomation("", ResponseInfomation.OPERATION_FAIL);
		}
		return userWeb != null ? new ResponseInfomation(userWeb, ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 跳转自定义开通页面
	 * @param schoolId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editSuffix", method = RequestMethod.GET)
	public ModelAndView editSuffix(
			@RequestParam(value = "schoolId", required = true) Integer schoolId, Model model) {
		School info = schoolService.findSchoolById(schoolId);
		model.addAttribute("schoolInfo", info);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	@RequestMapping(value = "/customReg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation customReg(
			@RequestParam(value = "schoolId", required = true) Integer schoolId,
			@RequestParam(value = "schoolEnglishName", required = true) String schoolEnglishName,
			@RequestParam(value = "schoolName", required = true) String schoolName,
			@RequestParam(value = "suffix", required = true) String suffix,
			UserWeb userWeb, @CurrentUser UserInfo user) {
		School info = schoolService.findSchoolById(schoolId);
		if(info != null){
			UinCompUser cu = new UinCompUser();
			cu.setCloudaccount(suffix);
			cu.setCompSuffix(suffix);
			cu.setCompname(schoolName);
			cu.setPassword(ServiceContants.DEFAULT_PWD);
			try {
				
				String result = uinUserService.register(cu);
				if(result != null){
					if(!"isExit".equals(result)){
						userWeb.setAccount(result);
						userWeb.setAccountType(ServiceContants.AC_TYPE_XUE_XIAO);
						userWeb.setExt("@" + suffix);
						userWeb.setOpenResult(ServiceContants.OPEN_SUCCESS);
						userWeb.setSchoolId(schoolId);
						userWeb.setServiceType(ServiceContants.SERVICE_TYPE_HUI_YI);
						userWeb = this.userWebService.add(userWeb);
					}else{
						return new ResponseInfomation("", ResponseInfomation.OPERATION_ERROR);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseInfomation("", ResponseInfomation.OPERATION_ERROR);
			}
		}else{
			return new ResponseInfomation("", ResponseInfomation.OPERATION_FAIL);
		}
		return userWeb != null ? new ResponseInfomation(userWeb, ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		UserWeb userWeb = this.userWebService.findUserWebById(id);
		model.addAttribute("userWeb", userWeb);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		UserWeb userWeb = this.userWebService.findUserWebById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("userWeb", userWeb);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, UserWeb userWeb) {
		if (userWeb != null) {
			userWeb.setId(id);
		}
		try {
			this.userWebService.remove(userWeb);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, UserWeb userWeb) {
		userWeb.setId(id);
		userWeb = this.userWebService.modify(userWeb);
		return userWeb != null ? new ResponseInfomation(userWeb.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
