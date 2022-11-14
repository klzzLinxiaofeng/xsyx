package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@RequestMapping(value="/teach/student")
public class ExtStudentController extends BaseController {
	
	private final static String viewBasePath = "/teach/student";
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") StudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return studentService.findStudentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/teamStu/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> teamStuJsonList(@RequestParam("teamId") Integer teamId) {
		TeamStudentCondition condition = new TeamStudentCondition();
		condition.setTeamId(teamId);
//		return this.teamStudentService.findTeamStudentByCondition(condition, null, null);
		return this.studentService.findStudentOfTeam(teamId);
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView getList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") StudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage,
			@RequestParam(value = "userOrder", required = false) boolean useOrder,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "teacher", required = false) Integer teacher,
			Model model) {
		conditionFilter(user, condition);
		page.setPageSize(20);
		List<Student> items = null;
		if (teacher != null) {
			// 获取登录用户的老师id，根据老师id获取，老师班级下的所有学生信息
			Integer teacherId = user.getTeacherId();
			if (teacherId == null) {
				return null;
			}
			items = this.studentService.findStudentByTeacherIdCondition(condition, usePage ? page : null, useOrder ? order : null, teacherId);
		} else {
			items = this.studentService.findStudentByCondition(condition, usePage ? page : null, useOrder ? order : null);
		}


		List<StudentVo> voList = new ArrayList<StudentVo>();
		for(Student stu : items){
			StudentVo vo = new StudentVo();
			List<ParentVo> parents = parentProxyService.findParentByStudentUserId(stu.getUserId());
			if(parents.size() > 0){
				vo.setParentMobile(parents.get(0).getMobile());
			}
			BeanUtils.copyProperties(stu, vo);
			voList.add(vo);
		}
		model.addAttribute("seachParam", condition.getName());
		model.addAttribute("items", voList);
		return new ModelAndView(structurePath("/list" + type), model.asMap());
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, StudentCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if(user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
			condition.setSchoolYear(user.getSchoolYear());
		}
	}
}
