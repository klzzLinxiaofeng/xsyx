package platform.szxyzxx.web.personnel.controller;
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

import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryFieldService;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/generalTeachingAffair/pjteachersalaryfield")
public class PjTeacherSalaryFieldController { 
	
	private final static String viewBasePath = "/generalTeachingAffair/pjteachersalaryfield";
	
	@Autowired
	@Qualifier("pjTeacherSalaryFieldService")
	private PjTeacherSalaryFieldService pjTeacherSalaryFieldService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PjTeacherSalaryFieldCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<PjTeacherSalaryField> items = this.pjTeacherSalaryFieldService.findPjTeacherSalaryFieldByCondition(condition, page, order);
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
	public List<PjTeacherSalaryField> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PjTeacherSalaryFieldCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.pjTeacherSalaryFieldService.findPjTeacherSalaryFieldByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(PjTeacherSalaryField pjTeacherSalaryField, @CurrentUser UserInfo user) {
		pjTeacherSalaryField = this.pjTeacherSalaryFieldService.add(pjTeacherSalaryField);
		return pjTeacherSalaryField != null ? new ResponseInfomation(pjTeacherSalaryField.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PjTeacherSalaryField pjTeacherSalaryField = this.pjTeacherSalaryFieldService.findPjTeacherSalaryFieldById(id);
		model.addAttribute("pjTeacherSalaryField", pjTeacherSalaryField);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		PjTeacherSalaryField pjTeacherSalaryField = this.pjTeacherSalaryFieldService.findPjTeacherSalaryFieldById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("pjTeacherSalaryField", pjTeacherSalaryField);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PjTeacherSalaryField pjTeacherSalaryField) {
		if (pjTeacherSalaryField != null) {
			pjTeacherSalaryField.setId(id);
		}
		try {
			this.pjTeacherSalaryFieldService.remove(pjTeacherSalaryField);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PjTeacherSalaryField pjTeacherSalaryField) {
		pjTeacherSalaryField.setId(id);
		pjTeacherSalaryField = this.pjTeacherSalaryFieldService.modify(pjTeacherSalaryField);
		return pjTeacherSalaryField != null ? new ResponseInfomation(pjTeacherSalaryField.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, PjTeacherSalaryFieldCondition condition) {
	}
}
