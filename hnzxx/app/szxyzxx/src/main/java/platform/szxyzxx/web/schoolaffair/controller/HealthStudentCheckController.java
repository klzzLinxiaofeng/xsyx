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

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.school.affair.model.HealthStudentCheck;
import platform.education.school.affair.service.HealthStudentCheckService;
import platform.education.school.affair.vo.HealthStudentCheckCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/healthStudentCheck")
public class HealthStudentCheckController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/health/studentCheck";
	
	@Autowired
	@Qualifier("healthStudentCheckService")
	private HealthStudentCheckService healthStudentCheckService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeamStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");	
		
		condition.setSchoolId(user.getSchoolId());
		//获得当前学校的当前学年值
		SchoolTermCurrent termCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		if(termCurrent != null) {
			String year = termCurrent.getSchoolYear();
			condition.setSchoolYear(year);
		}
		List<TeamStudent> teamStudentList = this.teamStudentService.findCurrentTeamStudentByCondition(condition, page, order);
		List<TeamStudentVo> voList = teamStudentToVos(teamStudentList, user);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", voList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<HealthStudentCheck> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") HealthStudentCheckCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.healthStudentCheckService.findHealthStudentCheckByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(HealthStudentCheck healthStudentCheck, @CurrentUser UserInfo user) {
		healthStudentCheck.setSchoolId(user.getSchoolId());
		healthStudentCheck = this.healthStudentCheckService.add(healthStudentCheck);
		return healthStudentCheck != null ? new ResponseInfomation(healthStudentCheck.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * 修改记录页面
	 * @param id
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, @CurrentUser UserInfo user, Model model) {
		HealthStudentCheck healthStudentCheck = this.healthStudentCheckService.findHealthStudentCheckById(id);
		model.addAttribute("healthStudentCheck", healthStudentCheck);
		return new ModelAndView(structurePath("/modify"), model.asMap());
	}
	
	/**
	 * 添加记录页面
	 * @param studentId
	 * @param teamId
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(
			@RequestParam(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@CurrentUser UserInfo user, Model model) {
		model.addAttribute("studentId", studentId);
		model.addAttribute("teamId", teamId);
		return new ModelAndView(structurePath("/add"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@CurrentUser UserInfo user,
			Model model) {
		HealthStudentCheckCondition condition = new HealthStudentCheckCondition();
		conditionFilter(user, condition);
		condition.setStudentId(studentId);
		condition.setTeamId(teamId);
		List<HealthStudentCheck> checkList = this.healthStudentCheckService.findHealthStudentCheckByCondition(condition);
		model.addAttribute("checkList", checkList);
		model.addAttribute("isCK", "disable");
		return new ModelAndView(structurePath("/check"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, HealthStudentCheck healthStudentCheck) {
		if (healthStudentCheck != null) {
			healthStudentCheck.setId(id);
		}
		try {
			this.healthStudentCheckService.remove(healthStudentCheck);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, HealthStudentCheck healthStudentCheck) {
		healthStudentCheck.setId(id);
		healthStudentCheck = this.healthStudentCheckService.modify(healthStudentCheck);
		return healthStudentCheck != null ? new ResponseInfomation(healthStudentCheck.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	@ResponseBody
	private ModelAndView check(
			@RequestParam(value = "studentId", required = true) Integer studentId,
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@CurrentUser UserInfo user,
			Model model) {
		HealthStudentCheckCondition condition = new HealthStudentCheckCondition();
		conditionFilter(user, condition);
		condition.setStudentId(studentId);
		condition.setTeamId(teamId);
		List<HealthStudentCheck> checkList = this.healthStudentCheckService.findHealthStudentCheckByCondition(condition);
		model.addAttribute("checkList", checkList);
		model.addAttribute("studentId", studentId);
		model.addAttribute("teamId", teamId);
		return new ModelAndView(structurePath("/check"), model.asMap());
	}
	
	private List<TeamStudentVo> teamStudentToVos(List<TeamStudent> teamStudentList, UserInfo user) {
		List<TeamStudentVo> voList = new ArrayList<TeamStudentVo>();
		if(teamStudentList.size() > 0) {
			for(TeamStudent teamStudent : teamStudentList) {
				voList.add(teamStudentToVo(teamStudent, user));
			}
		}
		return voList;
	}
	
	private TeamStudentVo teamStudentToVo(TeamStudent teamStudent, UserInfo user) {
		Student student = new Student();
		Grade grade = new Grade();
		Team team = new Team();
		
		TeamStudentVo vo = new TeamStudentVo();
		BeanUtils.copyProperties(teamStudent, vo);
		//获得学生性别/手机号码
		student = this.studentService.findStudentById(teamStudent.getStudentId());
		if(student != null) {
			vo.setSex(student.getSex());
			vo.setMobile(student.getMobile());
		}
		//获得班级名称、年级名称、学年名称
		team = this.teamService.findTeamById(teamStudent.getTeamId());
		if(team != null) {
			vo.setSchoolYear(team.getSchoolYear()); //学年的开始年份
			vo.setTeamName(team.getName()); //班级名称
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setSchoolId(user.getSchoolId());
			schoolYearCondition.setYear(team.getSchoolYear());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
			if(schoolYear != null) {
				vo.setSchoolYearName(schoolYear.getName());
			}
		}
		grade = this.gradeService.findGradeById(teamStudent.getGradeId());
		if(grade != null) {
			vo.setGradeName(grade.getName());
		}
		return vo;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, HealthStudentCheckCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
