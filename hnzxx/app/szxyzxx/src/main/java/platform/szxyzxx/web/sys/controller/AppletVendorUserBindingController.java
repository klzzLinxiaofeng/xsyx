package platform.szxyzxx.web.sys.controller;
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

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.School;
import platform.education.user.dao.AppletOwnerDao;
import platform.education.user.model.AppletVendor;
import platform.education.user.model.AppletVendorUserBinding;
import platform.education.user.model.Role;
import platform.education.user.model.UserRole;
import platform.education.user.service.AppletVendorService;
import platform.education.user.service.AppletVendorUserBindingService;
import platform.education.user.vo.AppletVendorCondition;
import platform.education.user.vo.AppletVendorUserBindingCondition;
import platform.education.user.vo.GroupCondition;
import platform.education.user.vo.RoleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/sys/applet/binding")
public class AppletVendorUserBindingController extends BaseController{ 
	
	private final static String viewBasePath = "/sys/appletCenter/appletVendorUserBinding";
	
	@Autowired
	@Qualifier("appletVendorUserBindingService")
	private AppletVendorUserBindingService appletVendorUserBindingService;
	
	@Autowired
	@Qualifier("appletVendorService")
	private AppletVendorService appletVendorService;
	
	@Autowired
	@Qualifier("appletOwnerDao")
	private AppletOwnerDao appletOwnerDao;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletVendorUserBindingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		//所有第三方信息
		List<AppletVendor> appletVendorList = this.appletVendorService.findAppletVendorByCondition(new AppletVendorCondition());
		
		AppletVendorUserBindingCondition con = new AppletVendorUserBindingCondition();
		con.setUserId(20953);
		List<AppletVendorUserBinding> items = this.appletVendorUserBindingService.findAppletVendorUserBindingByCondition(con, page, order);
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		if(condition.getRoleId() !=null && !"".equals(condition.getRoleId())){
			model.addAttribute("roleId", condition.getRoleId());
		}
		Integer schoolId = condition.getSchoolId();
		//查学校信息
		School school = null;
		//查学校所有角色
		List<Role> roleList = new ArrayList<>();
		if(schoolId != null){
			School byId = this.schoolService.findSchoolById(schoolId);
			
			RoleCondition roleCondition = new RoleCondition();
            //学校的话先找到groupId
            roleCondition.setGroupId(getGroupIdBySchoolId(schoolId));
            //找到学校所有的角色
            roleList = roleService.findRoleByCondition(roleCondition, null, Order.desc("id"));
			if(byId != null){
				school = changeSchoolAreaCode(byId);
			}
		}
		
		//查该学校角色
		
		
		//获取当前学校用户信息
		List<AppletVendorUserBinding> vendorBindingList = new ArrayList<>();
		if(schoolId!=null && !"".equals(schoolId)){
			vendorBindingList= this.appletVendorUserBindingService.findUserAndVendorBySchoolId(condition,page, order);
		}
		for (AppletVendorUserBinding avub : vendorBindingList) {
			//角色名称String整理
			String role = "";
			if(avub.getRoleString()!=null){
				String[] roleArr = avub.getRoleString().split(";");
				for (String roleAndType : roleArr) {
					String[] roleAndTypeArr = roleAndType.split(",");
					if("".equals(role)){
						role = roleAndTypeArr[1];
					}else {
						role = role + "、" + roleAndTypeArr[1];
					}
				}
			}
			avub.setRoleString(role);
		}
		
		String roleListJson = JSON.toJSONString(roleList);
		
		model.addAttribute("items", items);
		model.addAttribute("school", school);
		model.addAttribute("roleList", roleList);
		model.addAttribute("roleListJson", roleListJson);
		model.addAttribute("appletVendorList", appletVendorList);
		model.addAttribute("vendorBindingList", vendorBindingList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<AppletVendorUserBinding> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppletVendorUserBindingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.appletVendorUserBindingService.findAppletVendorUserBindingByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	//绑定
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public String creator(AppletVendorUserBinding appletVendorUserBinding, @CurrentUser UserInfo user) {
		
		appletVendorUserBinding = this.appletVendorUserBindingService.add(appletVendorUserBinding);
		return appletVendorUserBinding != null ?  ResponseInfomation.OPERATION_SUC : ResponseInfomation.OPERATION_FAIL;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AppletVendorUserBinding appletVendorUserBinding = this.appletVendorUserBindingService.findAppletVendorUserBindingById(id);
		model.addAttribute("appletVendorUserBinding", appletVendorUserBinding);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AppletVendorUserBinding appletVendorUserBinding = this.appletVendorUserBindingService.findAppletVendorUserBindingById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("appletVendorUserBinding", appletVendorUserBinding);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppletVendorUserBinding appletVendorUserBinding) {
		if (appletVendorUserBinding != null) {
			appletVendorUserBinding.setId(id);
		}
		try {
			this.appletVendorUserBindingService.remove(appletVendorUserBinding);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AppletVendorUserBinding appletVendorUserBinding) {
		appletVendorUserBinding.setId(id);
		appletVendorUserBinding = this.appletVendorUserBindingService.modify(appletVendorUserBinding);
		return appletVendorUserBinding != null ? new ResponseInfomation(appletVendorUserBinding.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	//批量绑定
	@RequestMapping(value = "/batchBind", method = RequestMethod.POST)
	@ResponseBody
	public String batchBind(AppletVendorUserBinding appletVendorUserBinding, @CurrentUser UserInfo user) {
		if(appletVendorUserBinding.getUserIdStr() == null || "".equals(appletVendorUserBinding.getUserIdStr())){
			return ResponseInfomation.OPERATION_FAIL;
		}
		String[] split = appletVendorUserBinding.getUserIdStr().split(",");
		for (String userId : split) {
			appletVendorUserBinding.setUserId(Integer.parseInt(userId));
			appletVendorUserBinding.setId(null);
			appletVendorUserBinding = this.appletVendorUserBindingService.add(appletVendorUserBinding);
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//解除绑定
	@RequestMapping(value = "/unBind", method = RequestMethod.POST)
	@ResponseBody
	public String unBind(AppletVendorUserBinding appletVendorUserBinding, @CurrentUser UserInfo user) {
		if(appletVendorUserBinding.getUserId() == null || appletVendorUserBinding.getVendorId() == null){
			return ResponseInfomation.OPERATION_FAIL;
		}
		
		AppletVendorUserBindingCondition condition = new AppletVendorUserBindingCondition();
		condition.setUserId(appletVendorUserBinding.getUserId());
		condition.setVendorId(appletVendorUserBinding.getVendorId());
		List<AppletVendorUserBinding> list = this.appletVendorUserBindingService.findAppletVendorUserBindingByCondition(condition);
		if(list != null && list.size() >0){
			for (AppletVendorUserBinding userbind : list) {
				this.appletVendorUserBindingService.remove(userbind);
				/*userbind.setIsDeleted(true);
				this.appletVendorUserBindingService.modify(userbind);*/
			}
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private School changeSchoolAreaCode(School school) {
		if (school.getProvince() != null && !"".equals(school.getProvince())) {
			String province = appletOwnerDao.findAreaName(school.getProvince(),null,null);
			school.setProvince(province);
		}
		if (school.getCity() != null && !"".equals(school.getCity())) {
			String city = appletOwnerDao.findAreaName(null,school.getCity(),null);
			school.setCity(city);
		}
		if (school.getDistrict() != null && !"".equals(school.getDistrict())) {
			String district = appletOwnerDao.findAreaName(null,null,school.getDistrict());
			school.setDistrict(district);
		}
		return school;
	}
	
	private Integer getGroupIdBySchoolId(Integer schoolId) {
        if (schoolId != 0) {
            GroupCondition groupCondition = new GroupCondition();
            groupCondition.setOwnerId(schoolId);
            return groupService.findGroupByCondition(groupCondition, null, null).get(0).getId();
        } else {
            //0为默认角色组
            return schoolId;
        }
    }
}
