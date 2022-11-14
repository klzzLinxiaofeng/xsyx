package platform.szxyzxx.web.hnzxx.controller;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.hnzxx.ui.model.Setting;
import platform.education.hnzxx.ui.service.SettingService;
import platform.education.hnzxx.ui.vo.SettingCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;




@Controller
@RequestMapping("/hnzxx/ui/setting")
public class SettingController { 
	
	private final static String viewBasePath = "/hnzxx/ui/setting";
	
	@Autowired
	@Qualifier("settingService")
	private SettingService settingService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SettingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Setting> items = this.settingService.findSettingByCondition(condition, page, order);
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
	public List<Setting> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SettingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.settingService.findSettingByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "getWallpaper", method = RequestMethod.GET)
	@ResponseBody
	public Object getWallpaper(@CurrentUser UserInfo user) {
		//String wallpaperPath = "";
		Setting setting = new Setting();
		setting = this.settingService.findByUserId(user.getId());
		/*if(setting != null) {
			wallpaperPath = setting.getWallpaperPath();
		}*/
		return setting;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void saveOrUpdate(
			@CurrentUser UserInfo user,
			@RequestParam(value = "wallpaperPath", required = false) String wallpaperPath,@RequestParam(value = "newWallpaperPath", required = false) String newWallpaperPath,@RequestParam(value = "wallpaperColor", required = false) String wallpaperColor) {
		Setting setting = new Setting();
		//根据用户ID查找数据中是否有记录，有则修改记录，无则添加记录
		Setting temp = new Setting();
		temp = this.settingService.findByUserId(user.getId());
		
		if(temp != null) {
			setting.setId(temp.getId());
			if(wallpaperPath != null && !"".equals(wallpaperPath)) {
				setting.setWallpaperPath(wallpaperPath);
			}
			setting.setWallpaperColor(wallpaperColor);
			if(newWallpaperPath != null && !"".equals(newWallpaperPath)) {
				setting.setNewWallpaperPath(newWallpaperPath);
			}
			setting = this.settingService.modify(setting);
		}else {
			setting.setSchoolId(user.getSchoolId());
			setting.setUserId(user.getId());
			if(wallpaperPath != null && !"".equals(wallpaperPath)) {
				setting.setWallpaperPath(wallpaperPath);
			}
			setting.setWallpaperColor(wallpaperColor);
			if(newWallpaperPath != null && !"".equals(newWallpaperPath)) {
				setting.setNewWallpaperPath(newWallpaperPath);
			}
			setting = this.settingService.add(setting);
		}
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Setting setting, @CurrentUser UserInfo user) {
		setting = this.settingService.add(setting);
		return setting != null ? new ResponseInfomation(setting.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Setting setting = this.settingService.findSettingById(id);
		model.addAttribute("setting", setting);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Setting setting = this.settingService.findSettingById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("setting", setting);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Setting setting) {
		if (setting != null) {
			setting.setId(id);
		}
		try {
			this.settingService.remove(setting);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Setting setting) {
		setting.setId(id);
		setting = this.settingService.modify(setting);
		return setting != null ? new ResponseInfomation(setting.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, SettingCondition condition) {
		Integer schoolId = condition.getSchoolId();
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
