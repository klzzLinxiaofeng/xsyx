package platform.szxyzxx.web.sys.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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


import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.user.dao.AppletOwnerDao;
import platform.education.user.model.App;
import platform.education.user.model.Applet;
import platform.education.user.model.AppletOwner;
import platform.education.user.service.AppletOwnerService;
import platform.education.user.service.AppletRoleService;
import platform.education.user.service.AppletService;
import platform.education.user.vo.AppCondition;
import platform.education.user.vo.AppletCondition;
import platform.education.user.vo.AppletDesktopCondition;
import platform.education.user.vo.AppletOwnerCondition;
import platform.education.user.vo.AppletOwnerList;
import platform.education.user.vo.AppletOwnerVo;
import platform.education.user.vo.AppletVo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONObject;





@Controller
@RequestMapping("/sys/applet/school")
public class AppletSchoolController extends BaseController { 
	
	private final static String viewBasePath = "/sys/appletCenter/applet";
	
	@Autowired
	@Qualifier("appletService")
	private AppletService appletService;
	
	@Autowired
	@Qualifier("appletOwnerService")
	private AppletOwnerService appletOwnerService;
	
	@Autowired
	@Qualifier("appletOwnerDao")
	private AppletOwnerDao appletOwnerDao;
	
	@Autowired
	@Qualifier("appletRoleService")
	private AppletRoleService appletRoleService;
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletCondition condition,
			@ModelAttribute("appCondition") AppCondition appCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		/**appList*/
		/*List<App> appList = this.appService.findAppByCondition(new AppCondition(), null, order.desc("modify_date"));*/
		//conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		/**appletList*/
		/*if(condition.getAppKey() == null || "" == condition.getAppKey()){
			condition.setAppKey(appList.get(0).getAppKey());
		}*/
		List<Applet> appletList = this.appletService.findAppletByCondition(condition, page, order);
		List<AppletVo> appletVoList = new ArrayList<AppletVo>();
		for (Applet applet : appletList) {
			AppletVo appletVo = new AppletVo();
			BeanUtils.copyProperties(applet,appletVo);
			appletVo.setTypeAndTime();
			appletVoList.add(appletVo);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/schoolIndex");
		} else {
			viewPath = structurePath("/schoolIndex");
		}
		if("superAdmin".equalsIgnoreCase(user.getUserName())){
			user.setUserName("superAdmin");
		}
		model.addAttribute("user", user);
		model.addAttribute("appletVoList", appletVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	//根据省市区搜索学校
	@RequestMapping(value = "/searchSchool", method = RequestMethod.POST)
	@ResponseBody
	public Object addSchoolApplet(@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,@CurrentUser UserInfo user,
			@ModelAttribute("ownerCondition") AppletOwnerCondition ownerCondition,
			@RequestParam(value = "val", required = true) Object val,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub) {
		
		JSONObject obj = JSONObject.fromObject(val);
		String province = obj.getString("province");
		String city = obj.getString("city");
		String district = obj.getString("district");
		ownerCondition.setProvince(province);
		ownerCondition.setCity(city);
		ownerCondition.setDistrict(district);
		
		List<AppletOwner> appletOwnerList= null;
		//优化condition条件，地区信息完整
		ownerCondition = changeAreaCondition(ownerCondition);
		appletOwnerList = this.appletOwnerService.findAllSchoolByCondition(ownerCondition, null, order);
		return appletOwnerList;
	}
	
	private AppletOwnerCondition changeAreaCondition(AppletOwnerCondition ownerCondition) {
		if("--选择省份--".equals(ownerCondition.getProvince())){
			ownerCondition.setProvince(null);
		}
		if("--选择城市--".equals(ownerCondition.getCity())){
			ownerCondition.setCity(null);
		}
		if("--选择地区--".equals(ownerCondition.getDistrict())){
			ownerCondition.setDistrict(null);
		}
		return ownerCondition;
	}
	
	//搜索出学校应用
	@RequestMapping(value = "/searchOwnerApplet")
	public ModelAndView searchOwnerApplet(@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,@CurrentUser UserInfo user,
			@ModelAttribute("ownerCondition") AppletOwnerCondition ownerCondition,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub) {
		String viewPath = null;
		if(ownerCondition.getOwnerId() == 0){
			ownerCondition.setOwnerId(null);
		}
		Integer schoolId = ownerCondition.getOwnerId();
		String schoolName = ownerCondition.getSchoolName();
		
		//查学校信息
		School school = null;
		if(schoolId != null){
			School byId = this.schoolService.findSchoolById(schoolId);
			if(byId != null){
				school = changeSchoolAreaCode(byId);
			}
		}else if (schoolName!=null && !"".equals(schoolName)) {
			List<School> byName = this.schoolService.findSchoolByName(schoolName);
			if(byName != null && byName.size() >0){
				school = changeSchoolAreaCode(byName.get(0));
			}
		}
		//查学校应用信息
		List<AppletVo> appletList = new ArrayList<>();
		if(school != null){
			appletList = this.appletOwnerService.findAppletOwnerBySchoolCondition(ownerCondition, page, order);
			for (AppletVo vo : appletList) {
				String uuid = vo.getIcon();
				FileResult result = fileService.findFileByUUID(uuid);
				vo.setIconUrl(result.getHttpUrl());
			}
		}
		viewPath = structurePath("/schoolIndexList");
		if("superAdmin".equalsIgnoreCase(user.getUserName())){
			user.setUserName("superAdmin");
		}
		model.addAttribute("user", user);
		model.addAttribute("appletList", appletList);
		model.addAttribute("school", school);
		return new ModelAndView(viewPath, model.asMap());
	}
	

	private void setRegisterTime(AppletOwner owner) {
		Date date = owner.getCreateDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		owner.setRegisterTime(format.format(date));
	}
	
	//添加学校
	@RequestMapping(value = "/addSchoolApplet/{appletId}/{ownerId}", method = RequestMethod.PUT)
	@ResponseBody
	public String addSchoolApplet(@PathVariable(value = "appletId") Integer appletId,@PathVariable(value = "ownerId") String ownerId, 
			AppletOwner appletOwner ,@CurrentUser UserInfo user) {
		/*try {*/
			
			appletOwner = new AppletOwner();
			appletOwner.setAppletId(appletId);
			appletOwner.setOwnerId(Integer.parseInt(ownerId));
			appletOwner.setOwnerType(5);
			
			AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
			BeanUtils.copyProperties(appletOwner,appletOwnerCondition);
			List<AppletOwner> list = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition, null,null);
			
			Applet applet = this.appletService.findAppletById(appletId);
			appletOwner.setCreateUserId(applet.getCreateUserId());
			appletOwner.setLineType(1);
			if(list != null && list.size() > 0){
				appletOwner.setId(list.get(0).getId());
				this.appletOwnerService.modify(appletOwner);
			}else {
				appletOwner.setIsDeleted(false);
				this.appletOwnerService.add(appletOwner);
			}
			
			Applet applet1 = new Applet();
			applet.setId(appletId);
			applet.setLineType(2);
			this.appletService.modify(applet);
		/*} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}*/
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//上架appletOwner
	@RequestMapping(value = "/upOwner/{ownerId}", method = RequestMethod.PUT)
	@ResponseBody
	public String upOwner(@PathVariable(value = "ownerId") Integer ownerId, AppletOwner appletOwner) {
		if (appletOwner != null) {
			appletOwner = new AppletOwner();
			appletOwner.setId(ownerId);
		}
		try {
			appletOwner.setLineType(1);
			this.appletOwnerService.modify(appletOwner);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	//删除appletOwner
	@RequestMapping(value = "/deleteOwner/{ownerId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "ownerId") Integer ownerId, AppletOwner appletOwner) {
		if (appletOwner != null) {
			appletOwner.setId(ownerId);
		}
		try {
			this.appletOwnerService.remove(appletOwner);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//批量删除appletOwner
	@RequestMapping(value = "/batchDel", method = RequestMethod.POST)
	@ResponseBody
	public String batchDel(@CurrentUser UserInfo user, AppletOwner appletOwner) {
		String[] arrId = null;
		if (appletOwner.getAppletOwnerIds() != null) {
			arrId = appletOwner.getAppletOwnerIds().split(",");
		}
		try {
			AppletOwner owner = null;
			for (String id : arrId) {
				owner = this.appletOwnerService.findAppletOwnerById(Integer.parseInt(id));
				this.appletOwnerService.remove(owner);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//批量下架appletOwner
	@RequestMapping(value = "/batchDown", method = RequestMethod.POST)
	@ResponseBody
	public String batchDown(@CurrentUser UserInfo user, AppletOwner appletOwner) {
		String[] arrId = null;
		if (appletOwner.getAppletOwnerIds() != null) {
			arrId = appletOwner.getAppletOwnerIds().split(",");
		}
		try {
			AppletOwner owner = null;
			for (String id : arrId) {
				owner = this.appletOwnerService.findAppletOwnerById(Integer.parseInt(id));
				owner.setLineType(0);
				AppletOwner modify = this.appletOwnerService.modify(owner);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**  添加应用页面   */
	@RequestMapping(value = "/addApplet/{schoolId}")
	public ModelAndView addApplet(
			@CurrentUser UserInfo user,
			@PathVariable(value = "schoolId") Integer schoolId,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletCondition condition,
			@ModelAttribute("appCondition") AppCondition appCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		/**appList*/
		/*AppCondition condit = new AppCondition();
		List<App> appList = this.appService.findAppByCondition(condit, null, null);*/
		
		//conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		/**appletList*/
		/*if(condition.getAppKey() == null || "" == condition.getAppKey()){
			condition.setAppKey(appList.get(0).getAppKey());
		}*/
		
		List<Applet> appletList = new ArrayList<>();
		AppletCondition appletCondition = new AppletCondition();
		appletCondition.setLineType(1);
		appletList = this.appletService.findAppletByCondition(appletCondition, null, null);
		appletCondition.setLineType(2);
		List<Applet> appletList1 = this.appletService.findAppletByCondition(appletCondition, null, null);
		for (Applet applet : appletList1) {
			appletList.add(applet);
		}
		
		List<AppletVo> appletVoList = new ArrayList<AppletVo>();
		for (Applet applet : appletList) {
			AppletVo appletVo = new AppletVo();
			BeanUtils.copyProperties(applet,appletVo);
			appletVo.setTypeAndTime();
			String uuid = appletVo.getIcon();
			FileResult file = fileService.findFileByUUID(uuid);
			appletVo.setIconUrl(file.getHttpUrl());
			appletVoList.add(appletVo);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/schoolAddApplet");
		} else {
			viewPath = structurePath("/schoolAddApplet");
		}
		
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("appletVoList", appletVoList);
		//model.addAttribute("appList", appList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**  下架应用页面   */
	@RequestMapping(value = "/downApplet/{schoolId}")
	public ModelAndView downApplet(
			@CurrentUser UserInfo user,
			@PathVariable(value = "schoolId") Integer schoolId,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletCondition condition,
			@ModelAttribute("appCondition") AppCondition appCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		/**appList*/
		/*AppCondition condit = new AppCondition();
		List<App> appList = this.appService.findAppByCondition(condit, null, null);*/
		
		//conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		/**appletList*/
		/*if(condition.getAppKey() == null || "" == condition.getAppKey()){
			condition.setAppKey(appList.get(0).getAppKey());
		}*/
		
		//appletList为全部应用
		List<Applet> appletList = new ArrayList<>();
		AppletCondition appletCondition = new AppletCondition();
		appletCondition.setLineType(1);
		appletList = this.appletService.findAppletByCondition(appletCondition, null, null);
		appletCondition.setLineType(2);
		List<Applet> appletList1 = this.appletService.findAppletByCondition(appletCondition, null, null);
		for (Applet applet : appletList1) {
			appletList.add(applet);
		}
		
		//ownerList为该学校所有上架的应用
		AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
		appletOwnerCondition.setIsDeleted(false);
		appletOwnerCondition.setOwnerId(schoolId);
		appletOwnerCondition.setOwnerType(5);
		appletOwnerCondition.setLineType(1);
		List<AppletOwner> ownerList = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition,null,null);
		
		//根据ownerList提取appletList上架的应用
		List<Applet> appletUpList = selectUpApplet(appletList,ownerList);
		
		List<AppletVo> appletVoList = new ArrayList<AppletVo>();
		for (Applet applet : appletUpList) {
			AppletVo appletVo = new AppletVo();
			BeanUtils.copyProperties(applet,appletVo);
			appletVo.setTypeAndTime();
			String uuid = appletVo.getIcon();
			FileResult file = fileService.findFileByUUID(uuid);
			appletVo.setIconUrl(file.getHttpUrl());
			appletVoList.add(appletVo);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/schoolDownApplet");
		} else {
			viewPath = structurePath("/schoolDownApplet");
		}
		
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("appletVoList", appletVoList);
		//model.addAttribute("appList", appList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	//根据ownerList提取appletList上架的应用
	private List<Applet> selectUpApplet(List<Applet> appletList, List<AppletOwner> ownerList) {
		List<Applet> appletUpList = new ArrayList<>();
		List<Integer> appletIds = new ArrayList<>();
		for (AppletOwner owner : ownerList) {
			appletIds.add(owner.getAppletId());
		}
		for (Applet applet : appletList) {
			if (appletIds.contains(applet.getId())) {
				appletUpList.add(applet);
			}
		}
		return appletUpList;
	}

	//批量添加应用appletOwner
	@RequestMapping(value = "/addAppletOwner", method = RequestMethod.POST)
	@ResponseBody
	public String addAppletOwner(@CurrentUser UserInfo user, AppletOwner appletOwner) {
		String[] arrId = null;
		if (appletOwner.getAppletIds() != null) {
			arrId = appletOwner.getAppletIds().split(",");
		}
		try {
			List<AppletOwner> list = new ArrayList<>();
			for (String appletId : arrId) {
				Applet applet = this.appletService.findAppletById(Integer.parseInt(appletId));
				AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
				appletOwnerCondition.setAppletId(Integer.parseInt(appletId));
				appletOwnerCondition.setOwnerId(appletOwner.getOwnerId());
				appletOwnerCondition.setOwnerType(5);
				list = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition,null,null);
				if(list.size() == 0){
					//创建新的
					AppletOwner owner = new AppletOwner();
					owner.setAppletId(Integer.parseInt(appletId));
					owner.setOwnerId(appletOwner.getOwnerId());
					owner.setOwnerType(5);
					owner.setLineType(1);
					owner.setNeedPaying(false);
					owner.setIsDeleted(false);
					owner.setName(applet.getName());
					AppletOwner add = this.appletOwnerService.add(owner);
					this.appletRoleService.addAppletRoleOnSchool(Integer.parseInt(appletId), add.getOwnerId());
				}else {
					if (list.get(0).getLineType() == 0) {
						//状态改为上线
						for (AppletOwner appletOwner2 : list) {
							appletOwner2.setLineType(1);
							this.appletOwnerService.modify(appletOwner2);
						}
					}
					
				}
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//批量下架应用appletOwner
	@RequestMapping(value = "/downAppletOwner", method = RequestMethod.POST)
	@ResponseBody
	public String downAppletOwner(@CurrentUser UserInfo user, AppletOwner appletOwner) {
		String[] arrId = null;
		if (appletOwner.getAppletIds() != null) {
			arrId = appletOwner.getAppletIds().split(",");
		}
		try {
			List<AppletOwner> list = new ArrayList<>();
			for (String appletId : arrId) {
				//Applet applet = this.appletService.findAppletById(Integer.parseInt(appletId));
				AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
				appletOwnerCondition.setAppletId(Integer.parseInt(appletId));
				appletOwnerCondition.setOwnerId(appletOwner.getOwnerId());
				appletOwnerCondition.setOwnerType(5);
				appletOwnerCondition.setIsDeleted(false);
				list = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition,null,null);
				if(list.size() == 0){
					/*//创建新的
					AppletOwner owner = new AppletOwner();
					owner.setAppletId(Integer.parseInt(appletId));
					owner.setOwnerId(appletOwner.getOwnerId());
					owner.setOwnerType(5);
					owner.setLineType(1);
					owner.setNeedPaying(false);
					owner.setIsDeleted(false);
					owner.setName(applet.getName());
					AppletOwner add = this.appletOwnerService.add(owner);
					this.appletRoleService.addAppletRoleOnSchool(Integer.parseInt(appletId), add.getOwnerId());*/
				}else {
					if (list.get(0).getLineType() == 1) {
						//状态改为下线
						for (AppletOwner appletOwner2 : list) {
							appletOwner2.setLineType(0);
							this.appletOwnerService.modify(appletOwner2);
						}
					}
					
				}
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
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
}
