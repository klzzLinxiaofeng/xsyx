package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.HealthDiseaseReport;
import platform.education.school.affair.service.HealthDiseaseReportService;
import platform.education.school.affair.vo.HealthDiseaseReportCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/healthDiseaseReport")
public class HealthDiseaseReportController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/health/diseaseReport";
	
	@Autowired
	@Qualifier("healthDiseaseReportService")
	private HealthDiseaseReportService healthDiseaseReportService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") HealthDiseaseReportCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<HealthDiseaseReport> items = this.healthDiseaseReportService.findHealthDiseaseReportByCondition(condition, page, order);
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
	public List<HealthDiseaseReport> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") HealthDiseaseReportCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.healthDiseaseReportService.findHealthDiseaseReportByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(HealthDiseaseReport healthDiseaseReport, @CurrentUser UserInfo user) {
		healthDiseaseReport.setSchoolId(user.getSchoolId());
		healthDiseaseReport = this.healthDiseaseReportService.add(healthDiseaseReport);
		return healthDiseaseReport != null ? new ResponseInfomation(healthDiseaseReport.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		HealthDiseaseReport healthDiseaseReport = this.healthDiseaseReportService.findHealthDiseaseReportById(id);
		model.addAttribute("healthDiseaseReport", healthDiseaseReport);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		HealthDiseaseReport healthDiseaseReport = this.healthDiseaseReportService.findHealthDiseaseReportById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("healthDiseaseReport", healthDiseaseReport);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, HealthDiseaseReport healthDiseaseReport) {
		if (healthDiseaseReport != null) {
			healthDiseaseReport.setId(id);
		}
		return this.healthDiseaseReportService.abandon(healthDiseaseReport);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, HealthDiseaseReport healthDiseaseReport) {
		healthDiseaseReport.setId(id);
		healthDiseaseReport = this.healthDiseaseReportService.modify(healthDiseaseReport);
		return healthDiseaseReport != null ? new ResponseInfomation(healthDiseaseReport.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, HealthDiseaseReportCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	
}
