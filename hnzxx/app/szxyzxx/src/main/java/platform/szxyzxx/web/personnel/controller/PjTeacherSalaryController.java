package platform.szxyzxx.web.personnel.controller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryFieldService;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryService;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryCondition;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryVo;
import platform.education.generalTeachingAffair.vo.SalaryFieldValue;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/personnel/teacherSalary")
public class PjTeacherSalaryController { 
// /generalTeachingAffair/pjteachersalary	
	private final static String viewBasePath = "/personnel/salary/PjTeacherSalary";
	
	@Autowired
	@Qualifier("pjTeacherSalaryService")
	private PjTeacherSalaryService pjTeacherSalaryService;
	@Autowired
	@Qualifier("pjTeacherSalaryFieldService")
	private PjTeacherSalaryFieldService pjTeacherSalaryFieldService;
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PjTeacherSalaryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<PjTeacherSalaryField> salaryFieldList = getTeacherSalaryFieldList(user.getSchoolId());
		
		List<PjTeacherSalary> teacherSalaryItems = this.pjTeacherSalaryService.findPjTeacherSalaryByCondition(condition, page, order);
		List<PjTeacherSalaryVo> items = new ArrayList<PjTeacherSalaryVo>();
		if ( teacherSalaryItems != null && teacherSalaryItems.size() > 0 && salaryFieldList.size() > 0 ) {
			for (PjTeacherSalary pjTeacherSalary : teacherSalaryItems) {
				items.add(getPjTeacherSalaryVoFromPjTeacherSalary(pjTeacherSalary, salaryFieldList, false));
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
			model.addAttribute("salaryFieldList", salaryFieldList);
			List<Department> departmentList = departmentService.findDepartmentBySchoolId(user.getSchoolId(), null, null);
			Map<Integer, String> departmentMap = new HashMap<Integer, String>();
			for (Department department : departmentList) {
				departmentMap.put(department.getId(), department.getName());
			}
			model.addAttribute("departmentMap", departmentMap);
			List<Integer> yearList = pjTeacherSalaryService.findPjTeacherSalaryYearBySchoolId(user.getSchoolId());
			model.addAttribute("yearList", yearList);
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private List<PjTeacherSalaryField> getTeacherSalaryFieldList(Integer schoolId) {
		PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition = new PjTeacherSalaryFieldCondition();
		pjTeacherSalaryFieldCondition.setSchoolId(schoolId);
		return pjTeacherSalaryFieldService.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition);
	}
	
	private PjTeacherSalaryVo getPjTeacherSalaryVoFromPjTeacherSalary(PjTeacherSalary pjTeacherSalary, List<PjTeacherSalaryField> salaryFieldList, boolean isEdit) {
		PjTeacherSalaryVo item = new PjTeacherSalaryVo();
		item.setId(pjTeacherSalary.getId());
		item.setName(pjTeacherSalary.getName());
		item.setPayYear(pjTeacherSalary.getPayYear());
		item.setPayMonth(pjTeacherSalary.getPayMonth());
		item.setSalaryTotal(pjTeacherSalary.getSalaryTotal());
		for (PjTeacherSalaryField pjTeacherSalaryField : salaryFieldList) {
				try {
					Field declaredField = pjTeacherSalary.getClass().getDeclaredField(pjTeacherSalaryField.getFieldName());
					declaredField.setAccessible(true);
					Float value = (Float) declaredField.get(pjTeacherSalary);
//					.getFloat(pjTeacherSalary);
					SalaryFieldValue fieldValue = new SalaryFieldValue();
					fieldValue.setValue(value);
					if ( isEdit ) {
						fieldValue.setFieldName(pjTeacherSalaryField.getFieldName());
						fieldValue.setAttrName(pjTeacherSalaryField.getAttrName());
					}
					item.getValueList().add(fieldValue);
				} catch (NoSuchFieldException | SecurityException
						| IllegalArgumentException
						| IllegalAccessException e) {
					e.printStackTrace();
				}
				
		}
		return item;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<PjTeacherSalary> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PjTeacherSalaryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.pjTeacherSalaryService.findPjTeacherSalaryByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(PjTeacherSalary pjTeacherSalary, @CurrentUser UserInfo user) {
		pjTeacherSalary = this.pjTeacherSalaryService.addPjTeacherSalary(pjTeacherSalary);
		return pjTeacherSalary != null ? new ResponseInfomation(pjTeacherSalary.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PjTeacherSalary pjTeacherSalary = this.pjTeacherSalaryService.findPjTeacherSalaryById(id);
		PjTeacherSalaryVo pjTeacherSalaryVo = null;
		if ( pjTeacherSalary != null ) {
			List<PjTeacherSalaryField> teacherSalaryFieldList = getTeacherSalaryFieldList(pjTeacherSalary.getSchoolId());
			pjTeacherSalaryVo = getPjTeacherSalaryVoFromPjTeacherSalary(pjTeacherSalary, teacherSalaryFieldList, true);
		}
		model.addAttribute("pjTeacherSalaryVo", pjTeacherSalaryVo);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		PjTeacherSalary pjTeacherSalary = this.pjTeacherSalaryService.findPjTeacherSalaryById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("pjTeacherSalary", pjTeacherSalary);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PjTeacherSalary pjTeacherSalary) {
		if (pjTeacherSalary != null) {
			pjTeacherSalary.setId(id);
		}
		try {
			this.pjTeacherSalaryService.remove(pjTeacherSalary);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PjTeacherSalary pjTeacherSalary) {
		pjTeacherSalary.setId(id);
		pjTeacherSalary = this.pjTeacherSalaryService.modify(pjTeacherSalary);
		return pjTeacherSalary != null ? new ResponseInfomation(pjTeacherSalary.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, PjTeacherSalaryCondition condition) {
		if ( condition.getSchoolId() == null ) {
			condition.setSchoolId(user.getSchoolId());
		}
		if ( condition.getIsDeleted() == null ) {
			condition.setIsDeleted(false);
		}
		
	}
}
