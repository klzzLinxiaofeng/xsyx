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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.vo.CanteenCondition;
import platform.education.user.model.App;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.vo.AppCondition;
import platform.education.user.vo.AppEditionCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;




@Controller
@RequestMapping("/sys/appTree")
public class AppTreeController extends BaseController{ 
	
	private final static String viewBasePath = "/sys/appTree";
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<AppEdition> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AppEditionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.appEditionService.findAppEditionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/index")
	public String indexTree() {
		return structurePath("/tree_index");
	}
	
	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			@RequestParam(value = "check", required = false, defaultValue = "false") boolean check,
			@RequestParam(value = "groupId", required = false) Integer groupId,
			@ModelAttribute("condition") AppEditionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, 
			Model model) {
		conditionFilter(user, condition);
		List<AppEdition> appEditions = appEditionService.findAppEditionByCondition(condition);
		List<TreeVo> vo = new ArrayList<TreeVo>();
		if (check & groupId != null) {
			vo = moduleToTreeVo(appEditions, groupId);
		}
		vo = moduleToTreeVo(appEditions);
		return vo;
	}
	
	/**
	 * 创建菜单跳转到编辑页面
	 * 
	 * @param caller
	 * @param parentCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@RequestParam(value = "caller", required = false) String caller,
			@RequestParam(value = "parentId", required = false) String parentId,
			Model model) {
		if("0".equals(parentId)){
			return new ModelAndView(structurePath("/app_input"));
		}else {
			AppEdition appEdition = this.appEditionService.findAppEditionById(Integer.parseInt(parentId));
			model.addAttribute("parent", appEdition);
			return new ModelAndView(structurePath("/appEdition_input"));
		}
	}

	/**
	 * 创建迅云产品
	 * 
	 * @param app
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/app_creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(App app, @CurrentUser UserInfo user) {
		app = this.appService.add(app);
		return app != null ? new ResponseInfomation(app.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 修改迅云app
	 * 
	 * @param id
	 * @param app
	 * @return
	 */
	@RequestMapping(value = "/app/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, App app) {
		app.setId(id);
		app = this.appService.modify(app);
		return app != null ? new ResponseInfomation(app.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	
	/**
	 * 
	 * 
	 * @param app
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/appEdition_creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creatorAppEdition(AppEdition appEdition, @CurrentUser UserInfo user) {
		appEdition = this.appEditionService.add(appEdition);
		return appEdition != null ? new ResponseInfomation(appEdition.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/appEdition/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation editAppEdition(@PathVariable(value = "id") Integer id, AppEdition appEdition) {
		appEdition.setId(id);
		appEdition = this.appEditionService.modify(appEdition);
		return appEdition != null ? new ResponseInfomation(appEdition.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	
	/**
	 * 创建顶级菜单
	 * 
	 * @param appEdition
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AppEdition appEdition, @CurrentUser UserInfo user) {
		return appEdition != null ? new ResponseInfomation(appEdition.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 编辑界面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AppEdition appEdition = this.appEditionService.findAppEditionById(id);
		if(appEdition != null){
			if("1".equals(appEdition.getRecordType())){
				App app = this.appService.findAppById(appEdition.getAppId());
				model.addAttribute("app",app);
				if(app!=null){
					EntityFile entity = this.entityFileService.findFileByUUID(app.getTrademark());
					model.addAttribute("entity", entity);
				}
				return new ModelAndView(structurePath("/app_input"));
			}else {
				AppEdition parent = this.appEditionService.findAppEditionById(appEdition.getParentId());
				model.addAttribute("parent", parent);
				model.addAttribute("appEdition", appEdition);
				return new ModelAndView(structurePath("/appEdition_input"));
			}
		}
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AppEdition appEdition) {
		try {
			this.appService.deleteById(id);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * AppKey不能重复的校验
	 * 
	 * @param appKey
	 * @return
	 */
	@RequestMapping(value = "/appEdition/check", method = RequestMethod.POST)
	@ResponseBody
	public Boolean appEditionCheck(@RequestParam(value = "appKey", required = false) String appKey,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		//检测appKey
		if ("appKey".equals(dxlx)) {
			AppEditionCondition condition = new AppEditionCondition();
			condition.setAppKey(appKey);
			condition.setIsDeleted(false);
			List<AppEdition> list = appEditionService.findAppEditionByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(AppEdition temp : list) {
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
		//检测name
		if ("name".equals(dxlx)) {
			AppEditionCondition condition = new AppEditionCondition();
			condition.setName(name);
			condition.setIsDeleted(false);
			List<AppEdition> list = appEditionService.findAppEditionByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(AppEdition temp : list) {
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
	
	
	@RequestMapping(value = "/app/check", method = RequestMethod.POST)
	@ResponseBody
	public Boolean appCheck(@RequestParam(value = "appKey", required = false) String appKey,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		//检测appKey
		if ("appKey".equals(dxlx)) {
			AppCondition condition = new AppCondition();
			condition.setAppKey(appKey);
			condition.setIsDeleted(false);
			List<App> list = appService.findAppByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(App temp : list) {
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
		//检测name
		if ("name".equals(dxlx)) {
			AppCondition condition = new AppCondition();
			condition.setName(name);
			condition.setIsDeleted(false);
			List<App> list = appService.findAppByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(App temp : list) {
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AppEdition appEdition) {
		appEdition.setId(id);
		appEdition = this.appEditionService.modify(appEdition);
		return appEdition != null ? new ResponseInfomation(appEdition.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AppEditionCondition condition) {
		condition.setIsDeleted(false);
	}
	
	private List<TreeVo> moduleToTreeVo(List<AppEdition> appEditions) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		for (AppEdition ae : appEditions) {
			TreeVo vo = new TreeVo();
			vo.setId(ae.getId()+"");
			vo.setpId(ae.getParentId()+"");
			vo.setName(ae.getName());
			vo.setIsParent(ae.getRecordType().equals("2")?false:true);
			vo.setChecked(false);
			treeVos.add(vo);
		}
		return treeVos;
	}
	
	private List<TreeVo> moduleToTreeVo(List<AppEdition> appEditions, Integer groupId) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		for (AppEdition ae : appEditions) {
			TreeVo vo = new TreeVo();
			vo.setId(ae.getId()+"");
			vo.setpId(ae.getParentId()+"");
			vo.setName(ae.getName());
			vo.setIsParent(ae.getRecordType().equals("2")?false:true);
			vo.setChecked(false);
			treeVos.add(vo);
		}
		return treeVos;
	}
	
}
