package platform.szxyzxx.web.sys.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.service.SchoolAttributePlatformService;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.user.model.App;
import platform.education.user.model.Applet;
import platform.education.user.model.AppletOwner;
import platform.education.user.model.AppletVendor;
import platform.education.user.service.AppletOwnerService;
import platform.education.user.service.AppletRoleService;
import platform.education.user.service.AppletService;
import platform.education.user.service.AppletVendorService;
import platform.education.user.vo.AppCondition;
import platform.education.user.vo.AppletCondition;
import platform.education.user.vo.AppletOwnerCondition;
import platform.education.user.vo.AppletVendorCondition;
import platform.education.user.vo.AppletVo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/sys/applet")
public class AppletController extends BaseController { 
	
	private final static String viewBasePath = "/sys/appletCenter/applet";
	
	@Autowired
	@Qualifier("appletService")
	private AppletService appletService;
	
	@Autowired
	@Qualifier("appletOwnerService")
	private AppletOwnerService appletOwnerService;
	
	@Autowired
	@Qualifier("appletRoleService")
	private AppletRoleService appletRoleService;
	
	@Autowired
	@Qualifier("appletVendorService")
	private AppletVendorService appletVendorService;
	
	@Autowired
	@Qualifier("schoolAttributePlatformService")
	private SchoolAttributePlatformService schoolAttributePlatformService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		/**appletList*/
		/*if(condition.getAppKey() == null || "" == condition.getAppKey()){
			condition.setAppKey(appList.get(0).getAppKey());
		}*/
		
		List<Applet> appletList = this.appletService.findAppletAndVendorByCondition(condition, page, order);
		List<AppletVo> appletVoList = new ArrayList<AppletVo>();
		for (Applet applet : appletList) {
			AppletVo appletVo = new AppletVo();
			BeanUtils.copyProperties(applet,appletVo);
			appletVo.setTypeAndTime();
			FileResult file = fileService.findFileByUUID(appletVo.getIcon());
			appletVo.setIconUrl(file.getHttpUrl());
			appletVoList.add(appletVo);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/indexList");
		} else {
			viewPath = structurePath("/platformApplet");
		}
		//第三方来源的查询
		AppletVendorCondition appletVendorCondition = new AppletVendorCondition();
		appletVendorCondition.setIsDeleted(false);
		List<AppletVendor> appletVendorList = this.appletVendorService.findAppletVendorByCondition(appletVendorCondition);
		model.addAttribute("appletVendorList", appletVendorList);
		
		model.addAttribute("appletVoList", appletVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Applet> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppletCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appletService.findAppletByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		/**appList*//*
		List<App> appList = this.appService.findAppByCondition(new AppCondition(), null, order.desc("modify_date"));
		model.addAttribute("appList", appList);*/
		//第三方来源的查询
		AppletVendorCondition appletVendorCondition = new AppletVendorCondition();
		appletVendorCondition.setIsDeleted(false);
		List<AppletVendor> appletVendorList = this.appletVendorService.findAppletVendorByCondition(appletVendorCondition);
		model.addAttribute("appletVendorList", appletVendorList);
		return new ModelAndView(structurePath("/appletCreate"), model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Applet applet, @CurrentUser UserInfo user) {
		applet.setLineType(0);
		applet.setCreateUserId(user.getId());
		
		if(applet.getIcon() == null || "".equals(applet.getIcon())){
			applet.setIcon("6f35d10856794faabced381cc3f3a6ab");
		}
		
		List<Applet> list = appletService.findAppletByCondition(new AppletCondition());
		for (Applet applet2 : list) {
			if(applet2.getAppletKey().equals(applet.getAppletKey())){
				return new ResponseInfomation(applet.getId(), ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		applet = this.appletService.add(applet);
		//调用角色权限
		this.appletRoleService.addAppletRoleOnPlatform(applet.getId());
		return applet != null ? new ResponseInfomation(applet.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model,@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		Applet applet = this.appletService.findAppletById(id);
		String uuid = applet.getIcon();
		FileResult fileResult = fileService.findFileByUUID(uuid);
		applet.setIconUrl(fileResult.getHttpUrl());
		//第三方来源的查询
		AppletVendorCondition appletVendorCondition = new AppletVendorCondition();
		appletVendorCondition.setIsDeleted(false);
		List<AppletVendor> appletVendorList = this.appletVendorService.findAppletVendorByCondition(appletVendorCondition);
		model.addAttribute("appletVendorList", appletVendorList);
		model.addAttribute("applet", applet);
		//test:初始化学校应用
		//this.appletService.initNewSchoolApplet(187);
		return new ModelAndView(structurePath("/appletCreate"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Applet applet = this.appletService.findAppletById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("applet", applet);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Applet applet) {
		if (applet != null) {
			applet.setId(id);
		}
		try {
			AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
			appletOwnerCondition.setAppletId(id);
			List<AppletOwner> appletOwnerList = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition);
			for (AppletOwner ao : appletOwnerList) {
				this.appletOwnerService.remove(ao);
			}
			this.appletService.remove(applet);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Applet applet) {
		applet.setId(id);
		//辨别key的唯一
		List<Applet> list = appletService.findAppletByCondition(new AppletCondition());
		for (Applet applet2 : list) {
			if(applet2.getAppletKey().equals(applet.getAppletKey()) && !applet2.getId().equals(id)){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		//无图用默认图片
		if(applet.getIcon() == null || "".equals(applet.getIcon())){
			applet.setIcon("6f35d10856794faabced381cc3f3a6ab");
		}
		
		applet = this.appletService.modify(applet);
		//修改该平台应用对应所有学校应用的名字
		this.appletOwnerService.updateAppletOwnerName(id,applet.getName());
		return applet != null ? new ResponseInfomation(applet.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/** ---------------------管理applet 主平台、学校上下架(owner)----------------------- */
	//applet 管理页面
	@RequestMapping(value = "/appletManage/editor")
	public ModelAndView appletManage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("appletCondition") AppletCondition appletCondition,
			@ModelAttribute("ownerCondition") AppletOwnerCondition ownerCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		Applet applet = this.appletService.findAppletById(id);
		String iconUrl = getIconUrl(applet);
		applet.setIconUrl(iconUrl);
		
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		ownerCondition.setAppletId(applet.getId());
		//优化condition条件，地区信息完整
		ownerCondition = changeAreaCondition(ownerCondition);
		List<AppletOwner> appletOwnerList = this.appletOwnerService.findAppletOwnerSchoolByCondition(ownerCondition, page, Order.desc("line_type"));
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/appletSchoolList");
		} else {
			viewPath = structurePath("/appletManage");
		}
		
		//学校归类List
		SchoolAttributePlatformCondition schoolAttributePlatformCondition = new SchoolAttributePlatformCondition();
		schoolAttributePlatformCondition.setIsDeleted(false);
		List<SchoolAttributePlatform> schoolAttributePlatformList = schoolAttributePlatformService.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition);
		
		model.addAttribute("applet", applet);
		model.addAttribute("appletOwnerList", appletOwnerList);
		model.addAttribute("schoolAttributePlatformList", schoolAttributePlatformList);
		return new ModelAndView(viewPath, model.asMap());
	}

	private String getIconUrl(Applet applet) {
		String uuid = applet.getIcon();
		FileResult result = fileService.findFileByUUID(uuid);
		return result.getHttpUrl();
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
	
	//applet 应用下架
	@RequestMapping(value = "/appletXiaJia/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String appletXiaJia(@PathVariable(value = "id") Integer id, Applet applet) {
		if (applet != null) {
			applet.setId(id);
			applet.setLineType(0);
		}
		try {
			this.appletService.modify(applet);
			//appletOwner物理删除
			this.appletOwnerService.removeByAppletId(applet.getId());
			//干掉权限,物理删除
			this.appletOwnerService.removeRoleByAppletId(applet.getId());
			
			//把appletOwner改为下架
			/*AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
			appletOwnerCondition.setAppletId(applet.getId());
			List<AppletOwner> ownerList = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition,null,null);
			if(ownerList != null){
				for (AppletOwner owner : ownerList) {
					owner.setLineType(0);
					this.appletOwnerService.modify(owner);
				}
			}*/
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//owner下架，下架某个学校
	@RequestMapping(value = "/ownerXiaJia/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String ownerXiaJia(@PathVariable(value = "id") Integer id, AppletOwner appletOwner) {
		if (appletOwner != null) {
			appletOwner.setId(id);
			appletOwner.setLineType(0);
		}
		try {
			AppletOwner owner = this.appletOwnerService.modify(appletOwner);
			
			Integer appletId = owner.getAppletId();
			Applet applet = new Applet();
			applet.setId(appletId);
			applet.setLineType(2);
			this.appletService.modify(applet);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//applet上架平台应用
	@RequestMapping(value = "/onlyUpAppelt/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String onlyUpAppelt(@PathVariable(value = "id") Integer id, Applet applet, @CurrentUser UserInfo user) {
		if (applet != null) {
			applet.setId(id);
			applet.setLineType(2);
			applet.setIsDeleted(false);
		}
		try {
			Applet applet2 = this.appletService.modify(applet);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//applet上架到全部学校，全平台上线
	@RequestMapping(value = "/appletShangJia/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String appletShangJia(@PathVariable(value = "id") Integer id, Applet applet, @CurrentUser UserInfo user) {
		if (applet != null) {
			applet.setId(id);
			applet.setLineType(1);
		}
		Integer createUserId = applet.getCreateUserId();
		try {
			Applet applet2 = this.appletService.modify(applet);
			/*//调用角色权限
			this.appletRoleService.addAppletRoleOnPlatform(applet2.getId());*/
			//添加所有学校上线
			SchoolCondition schoolCondition = new SchoolCondition();
			schoolCondition.setDelete(false);
			List<School> schoolList = this.schoolService.findSchoolByCondition(schoolCondition, null, null);
			
			//判断学校是否已经上线了的
			AppletOwnerCondition appletOwnerCondition1 = new AppletOwnerCondition();
			appletOwnerCondition1.setAppletId(id);
			appletOwnerCondition1.setIsDeleted(false);
			List<AppletOwner> uplist = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition1);
			List<Integer> numList = new ArrayList<>();
			for (AppletOwner ao : uplist) {
				Integer schoolId1 = ao.getOwnerId();
				numList.add(schoolId1);
			}
			for (int i = 0; i < schoolList.size(); i++) {
				Integer schId = schoolList.get(i).getId();
				if(numList.contains(schId)){
					//判断存在的school是上架还是下架
					AppletOwnerCondition appletOwnerCondition2 = new AppletOwnerCondition();
					appletOwnerCondition2.setOwnerId(schId);
					appletOwnerCondition2.setIsDeleted(false);
					List<AppletOwner> ownerBySchool = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition2);
					for (AppletOwner appletOwner : ownerBySchool) {
						if(appletOwner.getLineType() == 0){
							appletOwner.setLineType(1);
							this.appletOwnerService.modify(appletOwner);
						}
					}
					//schoolList删掉存在的school
					schoolList.remove(i);
					i--;
				}
			}
			
			AppletOwner appletOwner = null;
			for (School school : schoolList) {
				appletOwner = new AppletOwner();
				appletOwner.setName(applet2.getName());
				appletOwner.setOwnerId(school.getId());
				appletOwner.setAppletId(id);
				appletOwner.setOwnerType(5);
				appletOwner.setCreateUserId(createUserId);
				appletOwner.setLineType(1);
				appletOwner.setIsDeleted(false);
				this.appletOwnerService.add(appletOwner);
				//调用角色权限
				this.appletRoleService.addAppletRoleOnSchool(id, school.getId());
			}
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//owner批量下架，批量下架某个学校
	@RequestMapping(value = "/appletBatchXiaJia/{id}/{arr}", method = RequestMethod.DELETE)
	@ResponseBody
	public String ownerXiaJia(@PathVariable(value = "id") Integer id,@PathVariable(value = "arr") String arr, AppletOwner appletOwner) {
		String[] ownerIdArr = null;
		try {
			if(arr != null){
				ownerIdArr = arr.split(",");
			}
			appletOwner.setAppletId(id);
			for (int i = 0; i < ownerIdArr.length; i++) {
				appletOwner.setId(Integer.parseInt(ownerIdArr[i]));
				appletOwner.setLineType(0);
				this.appletOwnerService.modify(appletOwner);
			}
			Applet applet = new Applet();
			applet.setId(id);
			applet.setLineType(2);
			this.appletService.modify(applet);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	//选择上架学校页面
	@RequestMapping(value = "/addSchoolApplet/{id}")
	public ModelAndView addSchoolApplet(@PathVariable(value = "id") Integer appletId,@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,@CurrentUser UserInfo user,
			@ModelAttribute("ownerCondition") AppletOwnerCondition ownerCondition,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub) {
		String viewPath = null;
		List<AppletOwner> appletOwnerList= null;
		if ("list".equals(sub)) {
			ownerCondition.setAppletId(appletId);
			//优化condition条件，地区信息完整
			ownerCondition = changeAreaCondition(ownerCondition);
			appletOwnerList = this.appletOwnerService.findAllSchoolByCondition1(ownerCondition, null, null);
			
			//appletOwnerList = this.appletOwnerService.findAppletOwnerSchoolByCondition(ownerCondition, null, order);
			viewPath = structurePath("/addSchAppletList");
			
		} else {
			viewPath = structurePath("/addSchoolApplet");
		}
		
		//学校归类List
		SchoolAttributePlatformCondition schoolAttributePlatformCondition = new SchoolAttributePlatformCondition();
		schoolAttributePlatformCondition.setIsDeleted(false);
		List<SchoolAttributePlatform> schoolAttributePlatformList = schoolAttributePlatformService.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition);
		
		model.addAttribute("appletOwnerList", appletOwnerList);
		model.addAttribute("appletId", appletId);
		model.addAttribute("schoolAttributePlatformList", schoolAttributePlatformList);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/addSchoolApplet/{id}/{arr}", method = RequestMethod.PUT)
	@ResponseBody
	public String addSchoolApplet(@PathVariable(value = "id") Integer id,@PathVariable(value = "arr") String arr, 
			AppletOwner appletOwner ,@CurrentUser UserInfo user) {
		String[] ownerIdArr = null;
		try {
			if(arr != null){
				ownerIdArr = arr.split(",");
			}
			for (int i = 0; i < ownerIdArr.length; i++) {
				appletOwner = new AppletOwner();
				appletOwner.setAppletId(id);
				appletOwner.setOwnerId(Integer.parseInt(ownerIdArr[i]));
				appletOwner.setOwnerType(5);
				
				AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
				BeanUtils.copyProperties(appletOwner,appletOwnerCondition);
				List<AppletOwner> list = this.appletOwnerService.findAppletOwnerByCondition(appletOwnerCondition, null,null);
				
				Applet applet = this.appletService.findAppletById(id);
				appletOwner.setCreateUserId(applet.getCreateUserId());
				appletOwner.setLineType(1);
				appletOwner.setName(applet.getName());
				if(list != null && list.size() > 0){
					appletOwner.setId(list.get(0).getId());
					this.appletOwnerService.modify(appletOwner);
				}else {
					appletOwner.setIsDeleted(false);
					AppletOwner add = this.appletOwnerService.add(appletOwner);
					this.appletRoleService.addAppletRoleOnSchool(id, add.getOwnerId());
				}
				
			}
			Applet applet = new Applet();
			applet.setId(id);
			applet.setLineType(2);
			this.appletService.modify(applet);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/*private void conditionFilter(UserInfo user, AppletCondition condition) {
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
