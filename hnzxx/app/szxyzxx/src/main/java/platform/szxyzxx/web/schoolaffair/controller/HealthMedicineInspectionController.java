package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.HealthClinic;
import platform.education.school.affair.model.HealthMedicineInspection;
import platform.education.school.affair.service.HealthClinicService;
import platform.education.school.affair.service.HealthMedicineInspectionService;
import platform.education.school.affair.vo.HealthClinicCondition;
import platform.education.school.affair.vo.HealthMedicineInspectionCondition;
import platform.education.school.affair.vo.HealthMedicineInspectionVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/healthMedicineInspection")
public class HealthMedicineInspectionController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/health/medicineInspection";
	
	@Autowired
	@Qualifier("healthMedicineInspectionService")
	private HealthMedicineInspectionService healthMedicineInspectionService;
	
	@Autowired
	@Qualifier("healthClinicService")
	private HealthClinicService healthClinicService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") HealthMedicineInspectionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<HealthMedicineInspection> items = this.healthMedicineInspectionService.findHealthMedicineInspectionByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", toVos(items));
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<HealthMedicineInspection> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") HealthMedicineInspectionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.healthMedicineInspectionService.findHealthMedicineInspectionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, 
			Model model) {
		HealthClinicCondition condition = new HealthClinicCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<HealthClinic> clinicList = this.healthClinicService.findHealthClinicByCondition(condition);
		model.addAttribute("clinicList", clinicList);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(HealthMedicineInspection healthMedicineInspection, @CurrentUser UserInfo user) {
		healthMedicineInspection.setSchoolId(user.getSchoolId());
		healthMedicineInspection = this.healthMedicineInspectionService.add(healthMedicineInspection);
		return healthMedicineInspection != null ? new ResponseInfomation(healthMedicineInspection.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, @CurrentUser UserInfo user, Model model) {
		HealthMedicineInspection healthMedicineInspection = this.healthMedicineInspectionService.findHealthMedicineInspectionById(id);
		HealthClinicCondition condition = new HealthClinicCondition();
		condition.setSchoolId(user.getSchoolId());
		List<HealthClinic> clinicList = this.healthClinicService.findHealthClinicByCondition(condition);
		model.addAttribute("clinicList", clinicList);
		model.addAttribute("healthMedicineInspection", healthMedicineInspection);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			Model model) {
		HealthMedicineInspection healthMedicineInspection = this.healthMedicineInspectionService.findHealthMedicineInspectionById(id);
		HealthClinicCondition condition = new HealthClinicCondition();
		condition.setSchoolId(user.getSchoolId());
		List<HealthClinic> clinicList = this.healthClinicService.findHealthClinicByCondition(condition);
		model.addAttribute("clinicList", clinicList);
		model.addAttribute("isCK", "disable");
		model.addAttribute("healthMedicineInspection", healthMedicineInspection);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, HealthMedicineInspection healthMedicineInspection) {
		if (healthMedicineInspection != null) {
			healthMedicineInspection.setId(id);
		}
		return this.healthMedicineInspectionService.abandon(healthMedicineInspection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, HealthMedicineInspection healthMedicineInspection) {
		healthMedicineInspection.setId(id);
		healthMedicineInspection = this.healthMedicineInspectionService.modify(healthMedicineInspection);
		return healthMedicineInspection != null ? new ResponseInfomation(healthMedicineInspection.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private List<HealthMedicineInspectionVo> toVos(List<HealthMedicineInspection> inspectionList) {
		List<HealthMedicineInspectionVo> voList = new ArrayList<HealthMedicineInspectionVo>();
		for(HealthMedicineInspection inspection : inspectionList) {
			voList.add(toVo(inspection));
		}
		return voList;
	}
	
	private HealthMedicineInspectionVo toVo(HealthMedicineInspection inspection) {
		HealthMedicineInspectionVo vo = new HealthMedicineInspectionVo();
		BeanUtils.copyProperties(inspection, vo);
		HealthClinic clinic = this.healthClinicService.findHealthClinicById(inspection.getClinicId());
		String clinicName = "0";
		if(clinic != null) {
			if(clinic.getIsDelete() == true) {
				
			}else {
				clinicName = clinic.getName();
			}
		}
		vo.setClinicName(clinicName);
		return vo;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, HealthMedicineInspectionCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
