package platform.szxyzxx.web.sys.controller;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.service.SchoolAttributePlatformService;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import platform.education.user.model.Applet;
import platform.education.user.model.AppletDesktop;
import platform.education.user.service.AppletDesktopService;
import platform.education.user.service.AppletOwnerService;
import platform.education.user.service.AppletService;
import platform.education.user.vo.AppletCondition;
import platform.education.user.vo.AppletDesktopCondition;
import platform.education.user.vo.AppletOwnerCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/sys/appletDesktop")
public class AppletDesktopController extends BaseController{ 
	
	private final static String viewBasePath = "/sys/appletCenter/appletDesktop";
	
	@Autowired
	@Qualifier("appletDesktopService")
	private AppletDesktopService appletDesktopService;
	
	@Autowired
	@Qualifier("appletService")
	private AppletService appletService;
	
	@Autowired
	@Qualifier("appletOwnerService")
	private AppletOwnerService appletOwnerService;
	
	@Autowired
	@Qualifier("schoolAttributePlatformService")
	private SchoolAttributePlatformService schoolAttributePlatformService;
	
	//超管，校管？进
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		
		AppletDesktop desktop = new AppletDesktop();
		
		//上架的appletList
		List<Applet> appletList = new ArrayList<>();
		AppletCondition appletCondition = new AppletCondition();
		appletCondition.setLineType(1);
		appletList = this.appletService.findAppletByCondition(appletCondition, null, null);
		appletCondition.setLineType(2);
		List<Applet> appletList1 = this.appletService.findAppletByCondition(appletCondition, null, null);
		for (Applet applet : appletList1) {
			appletList.add(applet);
		}
		//下架的downAppletList
		List<Applet> downAppletList = new ArrayList<>();
		AppletCondition appletCondition1 = new AppletCondition();
		appletCondition1.setLineType(0);
		downAppletList = this.appletService.findAppletByCondition(appletCondition1, null, null);
		
		List<AppletDesktop> appletDesktop = new ArrayList<AppletDesktop>();
		
		
		//如果非超管
		if(user.getSchoolId() != null && !"superAdmin".equalsIgnoreCase(user.getUserName())){
			condition.setOwnerType(5);
			condition.setOwnerId(user.getSchoolId());
			condition.setAppKey("xunyun#educloud#web");
			appletDesktop = this.appletDesktopService.findAppletDesktopByCondition(condition, page, order);
			if (appletDesktop!=null && appletDesktop.size()>0) {
				for (AppletDesktop des : appletDesktop) {
					//if(des.getIsDefault()){
						desktop = des;
//						break;
//					}
				}
			}
			//查这学校的applet
			AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
			appletOwnerCondition.setOwnerId(user.getSchoolId());
			appletList = this.appletService.findAppletByOwnerCondition(appletOwnerCondition, null, null);
			//查学校未上架的applet
			AppletOwnerCondition appletOwnerCondition2 = new AppletOwnerCondition();
			appletOwnerCondition2.setOwnerId(user.getSchoolId());
			downAppletList = this.appletService.findDownAppletByOwnerCondition(appletOwnerCondition2, null, null);
		}
		
		if(desktop.getId() == null){
			if("superAdmin".equalsIgnoreCase(user.getUserName())){
				user.setUserName("superAdmin");
			}
			/**超管，默认配置*/
			condition.setOwnerType(1);
			condition.setOwnerId(0);
			condition.setIsDefault(true);
			condition.setAppKey("xunyun#educloud#web");
			appletDesktop = this.appletDesktopService.findAppletDesktopByCondition(condition, page, order);
			if(appletDesktop != null && appletDesktop.size() >0 ){
				desktop = appletDesktop.get(0);
			}else {
				desktop = createDefaultDestop();
			}
		}
		
		//applet里面显示图标urlString
		for (Applet app : appletList) {
			String url = getIconUrl(app);
			app.setIconUrl(url);
		}
		for (Applet app1 : downAppletList) {
			String url = getIconUrl(app1);
			app1.setIconUrl(url);
		}
		
		//appletList、downAppletList转JSON
		String json = JSON.toJSONString(appletList);
		String downjson = JSON.toJSONString(downAppletList);
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else { 
			viewPath = structurePath("/index");
		}
		
		model.addAttribute("appletDesktop", desktop);
		model.addAttribute("appletList", appletList);
		model.addAttribute("appletListJson", json);
		model.addAttribute("downAppletList", downAppletList);
		model.addAttribute("downAppletListJson", downjson);
		model.addAttribute("user", user);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private String getIconUrl(Applet applet) {
		String uuid = applet.getIcon();
		FileResult result = fileService.findFileByUUID(uuid);
		return result.getHttpUrl();
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<AppletDesktop> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appletDesktopService.findAppletDesktopByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AppletDesktop appletDesktop, @CurrentUser UserInfo user) {
		appletDesktop = this.appletDesktopService.add(appletDesktop);
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AppletDesktop appletDesktop = this.appletDesktopService.findAppletDesktopById(id);
		model.addAttribute("appletDesktop", appletDesktop);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AppletDesktop appletDesktop = this.appletDesktopService.findAppletDesktopById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("appletDesktop", appletDesktop);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppletDesktop appletDesktop) {
		if (appletDesktop != null) {
			appletDesktop.setId(id);
		}
		try {
			this.appletDesktopService.remove(appletDesktop);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AppletDesktop appletDesktop) {
		appletDesktop.setId(id);
		appletDesktop = this.appletDesktopService.modify(appletDesktop);
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/*另存为模板*/
	@RequestMapping(value = "/saveas",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation saveas(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		//按学校设置
		appletDesktop.setOwnerType(1);
		appletDesktop.setOwnerId(0);
		//另存为
		appletDesktop.setCreateUserId(user.getId());
		appletDesktop.setAppKey("xunyun#educloud#web");
		appletDesktop = this.appletDesktopService.add(appletDesktop);
		//如果设置为默认桌面,其他默认配置桌面记录移除默认
		if(appletDesktop.getIsDefault()){
			AppletDesktopCondition appletDesktopCondition = new AppletDesktopCondition();
			appletDesktopCondition.setOwnerType(1);
			appletDesktopCondition.setOwnerId(0);
			List<AppletDesktop> list = this.appletDesktopService.findAppletDesktopByCondition(appletDesktopCondition,null,null);
			for (AppletDesktop appdes : list) {
				//if(appdes.getId() != appletDesktop.getId()){
					appdes.setIsDefault(false);
					this.appletDesktopService.modify(appdes);
				//}
			}
			appletDesktop.setIsDefault(true);
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*保存*/
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation save(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getName() == null){
			appletDesktop.setName(null);
		}
		//非超管
		if(user.getSchoolId() != null && !"superAdmin".equalsIgnoreCase(user.getUserName())){
			AppletDesktopCondition appletDesktopCondition = new AppletDesktopCondition();
			appletDesktopCondition.setIsDeleted(false);
			appletDesktopCondition.setOwnerId(user.getSchoolId());
			List<AppletDesktop> list = this.appletDesktopService.findAppletDesktopByCondition(appletDesktopCondition);
			if(list != null && list.size() >0){
				//如果学校有记录
				AppletDesktop appletDesktop2 = list.get(0);
				appletDesktop2.setDesktopBody(appletDesktop.getDesktopBody());
				appletDesktop2.setIsDefault(false);
				this.appletDesktopService.modify(appletDesktop2);
			}else {
				appletDesktop.setIsDefault(false);
				appletDesktop.setId(null);
				appletDesktop.setParentId(0);
				appletDesktop.setOwnerType(5);
				appletDesktop.setOwnerId(user.getSchoolId());
				appletDesktop.setName(user.getSchoolName());
				appletDesktop.setAppKey("xunyun#educloud#web");
				this.appletDesktopService.add(appletDesktop);
			}
		}else {
			//超管则保存
			if("superAdmin".equalsIgnoreCase(user.getUserName())){
				user.setUserName("superAdmin");
			}
			appletDesktop = this.appletDesktopService.modify(appletDesktop);
		}
		
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*在设置里面保存,能修改名称、显示、默认*/
	@RequestMapping(value = "/save2",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation save2(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getName() == null){
			appletDesktop.setName(null);
		}
		appletDesktop = this.appletDesktopService.modify(appletDesktop);
		//如果设置为默认桌面,其他默认配置桌面记录移除默认
		if(appletDesktop.getIsDefault()){
			AppletDesktopCondition appletDesktopCondition = new AppletDesktopCondition();
			appletDesktopCondition.setOwnerType(1);
			appletDesktopCondition.setOwnerId(0);
			List<AppletDesktop> list = this.appletDesktopService.findAppletDesktopByCondition(appletDesktopCondition,null,null);
			for (AppletDesktop appdes : list) {
				//if(appdes.getId() != appletDesktop.getId()){
					appdes.setIsDefault(false);
					this.appletDesktopService.modify(appdes);
				//}
			}
			appletDesktop.setIsDefault(true);
			this.appletDesktopService.modify(appletDesktop);
		}
		
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*删除*/
	@RequestMapping(value = "/delDes",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation delDes(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			AppletDesktop desktop = this.appletDesktopService.findAppletDesktopById(appletDesktop.getId());
			if (desktop.getOwnerType() == 1 && desktop.getIsDefault() == true) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
			this.appletDesktopService.remove(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*跳转栏目管理*/
	@RequestMapping(value = "/column/{desId}")
	public ModelAndView column(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,@PathVariable(value = "desId") Integer desId,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		desktop = this.appletDesktopService.findAppletDesktopById(desId);
		
		model.addAttribute("appletDesktop", desktop);
		
		String viewPath = structurePath("/column");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	/*栏目编辑*/
	@RequestMapping(value = "/editColumn",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation editColumn(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*栏目添加*/
	@RequestMapping(value = "/addColumn",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addColumn(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*栏目删除*/
	@RequestMapping(value = "/delColumn",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation delColumn(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*栏目上移*/
	@RequestMapping(value = "/up",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation up(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*栏目下移*/
	@RequestMapping(value = "/down",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation down(AppletDesktop appletDesktop, 
			@RequestParam(value = "destopBody", required = false) Object destopBody,
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null){
			this.appletDesktopService.modify(appletDesktop);
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		
	}
	
	/*跳转切换桌面*/
	@RequestMapping(value = "/changeDes")
	public ModelAndView changeDes(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		condition.setOwnerType(1);
		
		List<AppletDesktop> defDesList = this.appletDesktopService.findAppletDesktopByCondition(condition,null,null);
		
		condition.setOwnerType(5);
		List<AppletDesktop> schDesList = this.appletDesktopService.findAllSchoolDesktopByCondition(condition,null,null);
		
		model.addAttribute("defDesList", defDesList);
		model.addAttribute("schDesList", schDesList);
		String viewPath = structurePath("/changeDes");
		return new ModelAndView(viewPath, model.asMap());
	}
	/*平台配置列表查询*/
	@RequestMapping(value = "/plaList")
	public ModelAndView plaList(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		condition.setOwnerType(1);
		List<AppletDesktop> defDesList = this.appletDesktopService.findAppletDesktopByCondition(condition,null,null);
		
		model.addAttribute("defDesList", defDesList);
		String viewPath = structurePath("/plaList");
		return new ModelAndView(viewPath, model.asMap());
	}
	/*学校配置列表查询*/
	@RequestMapping(value = "/schList")
	public ModelAndView schList(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		
		condition.setOwnerType(5);
		String city = condition.getCity();
		if("--选择城市--".equals(city)){
			condition.setCity(null);
		}
		List<AppletDesktop> schDesList = this.appletDesktopService.findSchoolDesktopByArea(condition,null,null);
		model.addAttribute("schDesList", schDesList);
		String viewPath = structurePath("/schList");
		return new ModelAndView(viewPath, model.asMap());
	}
	// TODO
	/*切换桌面,进入桌面首页*/
	@RequestMapping(value = "/changeDestop/{id}")
	public ModelAndView changeDestop(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@PathVariable(value = "id") Integer id,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		//把登录的超管账号弄成superAdmin
		if("superAdmin".equalsIgnoreCase(user.getUserName())){
			user.setUserName("superAdmin");
		}
		
		condition = new AppletDesktopCondition();
		condition.setId(id);
		AppletDesktop desktop = new AppletDesktop();
		
		List<AppletDesktop> appletDesktop = new ArrayList<AppletDesktop>();
		appletDesktop = this.appletDesktopService.findAppletDesktopByCondition(condition, page, order);
		
		if (appletDesktop!=null && appletDesktop.size()>0) {
			if(appletDesktop.get(0).getOwnerType()!=1){
				for (AppletDesktop des : appletDesktop) {
					//if(des.getIsDefault()){
						desktop = des;
//						break;
//					}
				}
			}else {
				desktop = appletDesktop.get(0);
			}
		}
		
		//下架的downAppletList
		List<Applet> downAppletList = new ArrayList<>();
		AppletCondition appletCondition1 = new AppletCondition();
		appletCondition1.setLineType(0);
		downAppletList = this.appletService.findAppletByCondition(appletCondition1, null, null);
		
		AppletCondition appletCondition = new AppletCondition();
		List<Applet> appletList = new ArrayList<>();
		//判断切换的是学校桌面还是默认桌面
		if(desktop.getOwnerType() == 1){
			appletCondition.setLineType(1);
			appletList = this.appletService.findAppletByCondition(appletCondition, null, null);
			appletCondition.setLineType(2);
			List<Applet> appletList1 = this.appletService.findAppletByCondition(appletCondition, null, null);
			for (Applet applet : appletList1) {
				appletList.add(applet);
			}
		}
		if(desktop.getOwnerType() == 5){
			//查这学校的applet
			AppletOwnerCondition appletOwnerCondition = new AppletOwnerCondition();
			appletOwnerCondition.setOwnerId(desktop.getOwnerId());
			appletList = this.appletService.findAppletByOwnerCondition(appletOwnerCondition, null, null);
			//查学校未上架的applet
			AppletOwnerCondition appletOwnerCondition2 = new AppletOwnerCondition();
			appletOwnerCondition2.setOwnerId(desktop.getOwnerId());
			downAppletList = this.appletService.findDownAppletByOwnerCondition(appletOwnerCondition2, null, null);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else { 
			viewPath = structurePath("/index");
		}
		
		//applet里面显示图标urlString
		for (Applet app : appletList) {
			String url = getIconUrl(app);
			app.setIconUrl(url);
		}
		for (Applet app1 : downAppletList) {
			String url = getIconUrl(app1);
			app1.setIconUrl(url);
		}
		
		//appletList转JSON
		String json = JSON.toJSONString(appletList);
		String downjson = JSON.toJSONString(downAppletList);
		//查学校信息
		Integer schId = desktop.getOwnerId();
		School school = schoolService.findSchoolById(schId);
		model.addAttribute("school", school);
		model.addAttribute("appletDesktop", desktop);
		model.addAttribute("appletList", appletList);
		model.addAttribute("appletListJson", json);
		model.addAttribute("downAppletList", downAppletList);
		model.addAttribute("downAppletListJson", downjson);
		model.addAttribute("user", user);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/*跳转覆盖桌面*/
	@RequestMapping(value = "/coverDes/{id}")
	public ModelAndView coverDes(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@PathVariable(value = "id") Integer id,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		
		List<AppletDesktop> schDesList = this.appletDesktopService.findAllSchool();
		
		//学校归类List
		SchoolAttributePlatformCondition schoolAttributePlatformCondition = new SchoolAttributePlatformCondition();
		schoolAttributePlatformCondition.setIsDeleted(false);
		List<SchoolAttributePlatform> schoolAttributePlatformList = schoolAttributePlatformService.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition);
		
		model.addAttribute("schDesList", schDesList);
		model.addAttribute("appletDesktopId", id);
		model.addAttribute("schoolAttributePlatformList", schoolAttributePlatformList);
		String viewPath = structurePath("/coverDes");
		return new ModelAndView(viewPath, model.asMap());
	}
	/*覆盖桌面列表查询*/
	@RequestMapping(value = "/coverList/{id}")
	public ModelAndView coverList(AppletDesktop desktop,@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@PathVariable(value = "id") Integer id,
			@ModelAttribute("page") Page page,@ModelAttribute("order") Order order, Model model,
			@ModelAttribute("condition") AppletDesktopCondition condition,
			@CurrentUser UserInfo user) {
		
		condition.setOwnerType(5);
		String city = condition.getCity();
		if("--选择城市--".equals(city)){
			condition.setCity(null);
		}
		if("--选择省份--".equals(condition.getProvince())){
			condition.setProvince(null);
		}
		List<AppletDesktop> schDesList = this.appletDesktopService.findSchoolByArea(condition,null,null);
		model.addAttribute("schDesList", schDesList);
		model.addAttribute("appletDesktopId", id);
		String viewPath = structurePath("/coverList");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/*执行覆盖*/
	@RequestMapping(value = "/cover",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation cover(AppletDesktop appletDesktop, 
			@CurrentUser UserInfo user) {
		
		if(appletDesktop.getId() != null && appletDesktop.getSchoolIdList()!=null){
			AppletDesktop desktop = this.appletDesktopService.findAppletDesktopById(appletDesktop.getId());
			
			String[] schoolIds = appletDesktop.getSchoolIdList().split(",");
			for (String schoolId : schoolIds) {
				AppletDesktopCondition appletDesktopCondition = new AppletDesktopCondition();
				appletDesktopCondition.setOwnerId(Integer.parseInt(schoolId));
				//appletDesktopCondition.setIsDefault(true);
				List<AppletDesktop> desktopList = this.appletDesktopService.findAppletDesktopByCondition(appletDesktopCondition,null,null);
				if(desktopList == null || desktopList.size() == 0){
					AppletDesktop desktopById = new AppletDesktop();
					desktopById = desktop;
					desktopById.setId(null);
					desktopById.setOwnerId(Integer.parseInt(schoolId));
					desktopById.setOwnerType(5);
					desktopById.setIsDefault(false);
					this.appletDesktopService.add(desktopById);
				}else {
					AppletDesktop desktopById = desktopList.get(0);
					desktopById.setIsDefault(false);
					desktopById.setDesktopBody(desktop.getDesktopBody());
					this.appletDesktopService.modify(desktopById);
				}
			}
		}
		return appletDesktop != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/*执行恢复默认桌面*/
	@RequestMapping(value = "/defaultDes",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation defaultDes(AppletDesktop appletDesktop, 
			@CurrentUser UserInfo user) {
		AppletDesktopCondition appletDesktopCondition = new AppletDesktopCondition();
		appletDesktopCondition.setOwnerType(1);
		appletDesktopCondition.setOwnerId(0);
		appletDesktopCondition.setIsDefault(true);
		List<AppletDesktop> list = this.appletDesktopService.findAppletDesktopByCondition(appletDesktopCondition,null,null);
		AppletDesktop defDes = list.get(0);
		
		AppletDesktop modify = null;
		if(appletDesktop.getId() != null){
			AppletDesktop desktop = this.appletDesktopService.findAppletDesktopById(appletDesktop.getId());
			desktop.setIsDefault(true);
			desktop.setDesktopBody(defDes.getDesktopBody());
			modify = this.appletDesktopService.modify(desktop);
		}
		return modify != null ? new ResponseInfomation(appletDesktop.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	
	private AppletDesktop createDefaultDestop() {
		AppletDesktop desktop1 = new AppletDesktop();
		desktop1.setOwnerType(1);
		desktop1.setOwnerId(0);
		desktop1.setIsDefault(true);
		desktop1.setAppKey("xunyun#educloud#web");
		desktop1.setName("智慧校园应用桌面标准配置");
		desktop1.setCreateUserId(1);
		desktop1.setShowAll(false);
		desktop1.setShowMore(true);
		desktop1.setIsDeleted(false);
		desktop1.setDesktopBody("{\"groups\": [{\"name\": \"智核\",\"enabled\": true,\"list_order\": 1,\"applets\": []}, {\"name\": \"智教\",\"enabled\": true,\"list_order\": 2,\"applets\": []}, {\"name\": \"智库\",\"enabled\": true,\"list_order\": 3,\"applets\": []}, {\"name\": \"智校\",\"enabled\": true,\"list_order\": 4,\"applets\": []}, {\"name\": \"智管\",\"enabled\": true,\"list_order\": 5,\"applets\": []}, {\"name\": \"智评\",\"enabled\": true,\"list_order\": 6,\"applets\": []}, {\"name\": \"智班\",\"enabled\": true,\"list_order\": 7,\"applets\": []}]}");
		desktop1 = this.appletDesktopService.add(desktop1);
		return desktop1;
	}
}


