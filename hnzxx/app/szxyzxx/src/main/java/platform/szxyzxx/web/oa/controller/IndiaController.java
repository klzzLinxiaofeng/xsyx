package platform.szxyzxx.web.oa.controller;

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

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.oa.model.India;
import platform.education.oa.service.IndiaService;
import platform.education.oa.vo.IndiaCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/office/india")
public class IndiaController extends BaseController{

	private final static String viewBasePath = "/oa/india";

	@Autowired
	@Qualifier("indiaService")
	private IndiaService indiaService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("departmentTeacherService")
	private DepartmentTeacherService departmentTeacherService;
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;

	/**
	 * 我申请的主页面
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") IndiaCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		viewPath = structurePath("/index");
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<India> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") IndiaCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.indiaService.findIndiaByCondition(condition, page, order);
	}

	/**
	 * 我申请的文印创建页面
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		// 根据用户id user_id查找对应的教师Id teacherId
		Integer teacherId = this.teacherService.findUnqiueTeacherId(
				user.getId(), user.getSchoolId());

		// 根据teacherId查找对应的departmentId
		DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTeacherId(teacherId);
		List<DepartmentTeacher> departmentList = this.departmentTeacherService
				.findDepartmentTeacherByConditionMore(condition);
		model.addAttribute("departmentList", departmentList);
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 保存添加 我申请的文印
	 * @param india
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(India india, @CurrentUser UserInfo user) {
		india.setSchoolId(user.getSchoolId());
		india.setIndiaStatus("0");
		india.setUserId(user.getId());
		// 根据用户id user_id查找对应的教师Id teacherId
		Integer teacherId = this.teacherService.findUnqiueTeacherId(
						user.getId(), user.getSchoolId());
        //根据teacherId查找对应的教师姓名
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		india.setUserName(teacher.getName());
		
		india = this.indiaService.add(india);
		return india != null ? new ResponseInfomation(india.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 我申请的文印的修改页面
	 * @param id
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model,
			@CurrentUser UserInfo user) {

		India india = this.indiaService.findIndiaById(id);
		// 根据用户id user_id查找对应的教师Id teacherId
				Integer teacherId = this.teacherService.findUnqiueTeacherId(
						user.getId(), user.getSchoolId());

				// 根据teacherId查找对应的departmentId
				DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
				condition.setSchoolId(user.getSchoolId());
				condition.setTeacherId(teacherId);
				List<DepartmentTeacher> departmentList = this.departmentTeacherService
						.findDepartmentTeacherByConditionMore(condition);
				model.addAttribute("departmentList", departmentList);
		//根据departmentId查找该用户所属部门
		Department d = this.departmentService.findDepartmentById(india.getDepartmentId());
		
		if (india != null) {
			EntityFile entity = this.entityFileService.findFileByUUID(india.getUploadFile());
			model.addAttribute("entity", entity);
		}
		
		
		model.addAttribute("d", d);
		model.addAttribute("india", india);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 保存修改我申请的文印 
	 * @param id
	 * @param india
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			India india) {
		india.setId(id);
		india = this.indiaService.modify(india);
		return india != null ? new ResponseInfomation(india.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		India india = this.indiaService.findIndiaById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("india", india);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 删除我申请的文印 
	 * @param id
	 * @param india
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, India india) {
		if (india != null) {
			india.setId(id);
		}
		try {
			this.indiaService.remove(india);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}



	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, IndiaCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				condition.setSchoolId(user.getSchoolId());
			}
		}
	}
	
	
	
	
}
