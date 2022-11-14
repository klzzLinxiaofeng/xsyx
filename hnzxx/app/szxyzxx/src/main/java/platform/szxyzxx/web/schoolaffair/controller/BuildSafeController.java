package platform.szxyzxx.web.schoolaffair.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import platform.education.school.affair.model.BuildSafe;
import platform.education.school.affair.service.BuildSafeService;
import platform.education.school.affair.vo.BuildSafeCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/buildsafe")
public class BuildSafeController { 
	
	private final static String viewBasePath = "/schoolaffair/buildsafe";
	
	@Autowired
	@Qualifier("buildSafeService")
	private BuildSafeService buildSafeService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") BuildSafeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		if(user != null) {
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDeleted(false);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<BuildSafe> items = this.buildSafeService.findBuildSafeByCondition(condition, page, order);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("items", items);
		}
		
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<BuildSafe> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") BuildSafeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.buildSafeService.findBuildSafeByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(BuildSafe buildSafe, @CurrentUser UserInfo user,@RequestParam(value = "checkTime", required = false) String checkTime) {
		/*Integer groupId = buildSafe.getGroupId();
		Integer appId = buildSafe.getAppId();
		if(groupId == null) {
			buildSafe.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			buildSafe.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		if(user != null) {
			Date checkDate = null;
			try {
				if(checkTime != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					checkDate = format.parse(checkTime);
					buildSafe.setCheckDate(checkDate);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			buildSafe.setSchoolId(user.getSchoolId());
			buildSafe = this.buildSafeService.add(buildSafe);
		}
		return buildSafe != null ? new ResponseInfomation(buildSafe.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		BuildSafe buildSafe = this.buildSafeService.findBuildSafeById(id);
		model.addAttribute("buildSafe", buildSafe);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		BuildSafe buildSafe = this.buildSafeService.findBuildSafeById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("buildSafe", buildSafe);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, BuildSafe buildSafe) {
		if (buildSafe != null) {
			buildSafe.setId(id);
		}
		try {
			buildSafe = this.buildSafeService.findBuildSafeById(id);
			if(buildSafe != null) {
				buildSafe.setIsDeleted(true);
				this.buildSafeService.modify(buildSafe);
			}
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, BuildSafe buildSafe,@RequestParam(value = "checkTime", required = false) String checkTime) {
		
		Date checkDate = null;
		try {
			if(checkTime != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				checkDate = format.parse(checkTime);
				buildSafe.setCheckDate(checkDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		buildSafe.setId(id);
		buildSafe = this.buildSafeService.modify(buildSafe);
		return buildSafe != null ? new ResponseInfomation(buildSafe.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
