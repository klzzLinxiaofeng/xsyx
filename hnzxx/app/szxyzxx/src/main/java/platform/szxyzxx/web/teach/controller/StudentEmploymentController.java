package platform.szxyzxx.web.teach.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import platform.education.generalTeachingAffair.model.StudentEmployment;
import platform.education.generalTeachingAffair.service.StudentEmploymentService;
import platform.education.generalTeachingAffair.vo.StudentEmploymentCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



/**
 * 学生就业
 * @author tangzh
 *
 */
@Controller
@RequestMapping("/teach/employment")
public class StudentEmploymentController extends BaseController{ 
	private static final Logger log = LoggerFactory
			.getLogger(StudentCheckAttendanceController.class);
	private final static String viewBasePath = "/teach/studentEmployment";
	
	@Autowired
	@Qualifier("studentEmploymentService")
	private StudentEmploymentService studentEmploymentService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") StudentEmploymentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<StudentEmployment> items = this.studentEmploymentService.findStudentEmploymentByCondition(condition, page, order);
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
	public List<StudentEmployment> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") StudentEmploymentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
//		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.studentEmploymentService.findStudentEmploymentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(StudentEmployment studentEmployment, @CurrentUser UserInfo user) {
		/*Integer groupId = studentEmployment.getGroupId();
		Integer appId = studentEmployment.getAppId();
		if(groupId == null) {
			studentEmployment.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			studentEmployment.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		studentEmployment = this.studentEmploymentService.add(studentEmployment);
		return studentEmployment != null ? new ResponseInfomation(studentEmployment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		StudentEmployment studentEmployment = this.studentEmploymentService.findStudentEmploymentById(id);
		model.addAttribute("studentEmployment", studentEmployment);
		return new ModelAndView(structurePath("/edit"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		StudentEmployment studentEmployment = this.studentEmploymentService.findStudentEmploymentById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("studentEmployment", studentEmployment);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, StudentEmployment studentEmployment) {
		if (studentEmployment != null) {
			studentEmployment.setId(id);
		}
		try {
			this.studentEmploymentService.remove(studentEmployment);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, StudentEmployment studentEmployment) {
		studentEmployment.setId(id);
		studentEmployment = this.studentEmploymentService.modify(studentEmployment);
		return studentEmployment != null ? new ResponseInfomation(studentEmployment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/*private void conditionFilter(UserInfo user, StudentEmploymentCondition condition) {
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
