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

import platform.education.school.affair.model.Facility;
import platform.education.school.affair.service.FacilityService;
import platform.education.school.affair.vo.FacilityCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/facility")
public class FacilityController { 
	
	private final static String viewBasePath = "/schoolaffair/facility";
	
	@Autowired
	@Qualifier("facilityService")
	private FacilityService facilityService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") FacilityCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		if(user != null) {
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDeleted(false);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<Facility> items = this.facilityService.findFacilityByCondition(condition, page, order);
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
	public List<Facility> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") FacilityCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.facilityService.findFacilityByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Facility facility, @CurrentUser UserInfo user,@RequestParam(value = "buildTime", required = false) String buildTime,@RequestParam(value = "repairTime", required = false) String repairTime) {
		/*Integer groupId = facility.getGroupId();
		Integer appId = facility.getAppId();
		if(groupId == null) {
			facility.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			facility.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		
		if(user != null) {
			
			Date buildDate = null;
			Date repairDate = null;
			try {
				if(buildTime != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					buildDate = format.parse(buildTime);
					repairDate = format.parse(repairTime);
					facility.setBuildDate(buildDate);
					facility.setRepairDate(repairDate);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			facility.setSchoolId(user.getSchoolId());
			facility = this.facilityService.add(facility);
		}
		
		return facility != null ? new ResponseInfomation(facility.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Facility facility = this.facilityService.findFacilityById(id);
		model.addAttribute("facility", facility);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Facility facility = this.facilityService.findFacilityById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("facility", facility);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Facility facility) {
		if (facility != null) {
			facility.setId(id);
		}
		try {
			facility = this.facilityService.findFacilityById(id);
			if(facility != null) {
				facility.setIsDeleted(true);
				this.facilityService.modify(facility);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Facility facility,@RequestParam(value = "buildTime", required = true)String buildTime,@RequestParam(value = "repairTime", required = true) String repairTime) {
		
		Date buildDate = null;
		Date repairDate = null;
		try {
			if(buildTime != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				buildDate = format.parse(buildTime);
				repairDate = format.parse(repairTime);
				facility.setBuildDate(buildDate);
				facility.setRepairDate(repairDate);
			}
			
			facility.setId(id);
			facility = this.facilityService.modify(facility);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return facility != null ? new ResponseInfomation(facility.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
