package platform.szxyzxx.web.sys.controller;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolServerManage;
import platform.education.generalTeachingAffair.service.SchoolServerManageService;
import platform.education.generalTeachingAffair.vo.SchoolServerManageCondition;
import platform.education.generalTeachingAffair.vo.StudentScoreData;
import platform.education.user.model.Group;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.SchoolServerAddressJsonVo;
import platform.szxyzxx.web.sys.vo.SchoolServerAddressVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/sys/schoolServerManage")
public class SchoolServerManageController extends BaseController{ 
	
	private final static String viewBasePath = "/sys/schoolServerManage";
	
	@Autowired
	@Qualifier("schoolServerManageService")
	private SchoolServerManageService schoolServerManageService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SchoolServerManageCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<SchoolServerManage> items = this.schoolServerManageService.findSchoolServerManageByCondition(condition, page, order);
		List<SchoolServerAddressVo> list = new ArrayList<SchoolServerAddressVo>();
		for(SchoolServerManage schoolServerManage: items){
			JSONArray array = JSONArray.fromObject(schoolServerManage.getServerAddress());
			Object o = array.get(0);
			JSONObject jsonObject =JSONObject.fromObject(o);
			SchoolServerAddressVo vo =(SchoolServerAddressVo)JSONObject.toBean(jsonObject, SchoolServerAddressVo.class);
			vo.setId(schoolServerManage.getId());
			list.add(vo);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", list);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolServerManage> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SchoolServerManageCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.schoolServerManageService.findSchoolServerManageByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(SchoolServerAddressVo schoolServerAddressVo, @CurrentUser UserInfo user) {
		SchoolServerAddressJsonVo vo = new SchoolServerAddressJsonVo();
		SchoolServerManage schoolServerManage = new SchoolServerManage();
		Group group = this.groupService.findGroupById(schoolServerAddressVo.getGorupId());
		SchoolServerManageCondition condition = new SchoolServerManageCondition();
		condition.setSchoolId(group.getOwnerId());
		condition.setIsDeleted(false);
		List<SchoolServerManage> list = this.schoolServerManageService.findSchoolServerManageByCondition(condition);
		if(list.size()>0){
			new ResponseInfomation(ResponseInfomation.DATA_REPEAT);
		}
		School school = this.schoolService.findSchoolById(group.getOwnerId());
		vo.setCode(school.getCode());
		vo.setName(school.getName());
		vo.setEduServer(schoolServerAddressVo.getEduServer());
		vo.setEduPort(schoolServerAddressVo.getEduPort());
		vo.setApiServer(schoolServerAddressVo.getApiServer());
		vo.setApiPort(schoolServerAddressVo.getApiPort());
		JSONArray serverAddress = JSONArray.fromObject(vo); 
		schoolServerManage.setServerAddress(serverAddress.toString());
		schoolServerManage.setSchoolId(school.getId());
		schoolServerManage.setName(school.getName());
		schoolServerManage.setSchoolName(school.getName());
		schoolServerManage = this.schoolServerManageService.add(schoolServerManage);
		return schoolServerManage != null ? new ResponseInfomation(schoolServerManage.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		SchoolServerManage schoolServerManage = this.schoolServerManageService.findSchoolServerManageById(id);
		model.addAttribute("schoolServerManage", schoolServerManage);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		SchoolServerManage schoolServerManage = this.schoolServerManageService.findSchoolServerManageById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("schoolServerManage", schoolServerManage);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SchoolServerManage schoolServerManage) {
		if (schoolServerManage != null) {
			schoolServerManage.setId(id);
		}
		try {
			this.schoolServerManageService.remove(schoolServerManage);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SchoolServerManage schoolServerManage) {
		schoolServerManage.setId(id);
		schoolServerManage = this.schoolServerManageService.modify(schoolServerManage);
		return schoolServerManage != null ? new ResponseInfomation(schoolServerManage.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, SchoolServerManageCondition condition) {
//		Integer groupId = condition.getGroupId();
//		Integer appId = condition.getAppId();
//		if(user != null && groupId == null) {
//			condition.setGroupId(user.getGroupId());
//		}
//		
//		if(appId == null) {
//			condition.setAppId(SysContants.SYSTEM_APP_ID);
//		}
	}
}
