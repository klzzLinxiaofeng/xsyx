package platform.szxyzxx.web.teach.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.ClassRoomTeacherCondition;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.education.user.vo.RoleCondition;
import platform.education.user.vo.UserRoleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 任课教师管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/classRoomTeacher")
public class ClassRoomTeacherController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(ClassRoomTeacherController.class);
	@RequestMapping("/classRoomTeacherList")
	public ModelAndView getClassRoomTeacherList(
			@ModelAttribute("classRoomTeacherCondition") ClassRoomTeacherCondition classRoomTeacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();
		try{
			List<SchoolTermCurrent> schoolTermCurrentList = this.schoolTermCurrentService.findCurrentSchoolYear(user.getSchoolId());
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentList.get(0);
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
			gradeCondition.setSchoolId(user.getSchoolId());
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, Order.asc("uni_grade_code"));
			mav.addObject("gradeList", gradeList);
			//mav.addObject("schoolId", user.getSchoolId());
		}catch(Exception e){
			log.info("查询任课教师列表异常");
			e.printStackTrace();
		}
		mav.setViewName("/teach/classRoomTeacher/classRoomTeacherList");
		return mav;
	}
	
	
	@RequestMapping(value = "/getSubjectList", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> getSubjectList(
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@CurrentUser UserInfo user) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		Grade grade = this.gradeService.findGradeById(Integer.parseInt(gradeId));
		//年级学科
		List<SubjectGrade> gradeSubjectList = subjectGradeService.findSubjectGradeByGradeCode(user.getSchoolId(), grade.getUniGradeCode());
		
		//冗余数据的问题
		Subject subject = null;
		if(gradeSubjectList != null && gradeSubjectList.size() > 0){
			for(SubjectGrade gs : gradeSubjectList){
				if(gs != null && gs.getSubjectCode() != null && !"".equals(gs.getSubjectCode())){
					subject = subjectService.findUnique(user.getSchoolId(), gs.getSubjectCode());
					if(subject != null){
						gs.setSubjectName(subject.getName());
					}
				}
			}
		}
		
		//班级
		List<Team> teamList =  this.teamService.findTeamOfGradeAndSchool(Integer.parseInt(gradeId),user.getSchoolId());
		//判断某个班级是否有任课老师
		List<List<TeamTeacher>> courseTeacherList = new ArrayList<List<TeamTeacher>>();
		for(Team team :teamList){
			//科目表
			List<TeamTeacher> courseTeacher =  this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(Integer.parseInt(gradeId),team.getId());
			if(!courseTeacher.isEmpty()){
				courseTeacherList.add(courseTeacher);
			}else{ //不存在给个空数组
				courseTeacherList.add(new ArrayList<TeamTeacher>());
			}
		}
		result.put("teamList", teamList); //班级列表
		result.put("gradeSubjectList", gradeSubjectList); //科目列表
		result.put("courseTeacherList", courseTeacherList); //任课老师列表

		return result;
	}
	
	//添加教师
	@RequestMapping("/addClassRoomTeacherPage")  
	public ModelAndView addClassRoomTeacherPage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "teamId", required = true) String teamId,
			@RequestParam(value = "subjectName", required = true) String subjectName,
			@RequestParam(value = "subjectCode", required = true) String subjectCode,
			@RequestParam(value = "urlType", required = true) String urlType){
		ModelAndView mav = new ModelAndView();
		List<Teacher> teacherList = this.teacherService.findActiveTeacherOfSchool(user.getSchoolId());
		mav.addObject("teacherList", teacherList);
		mav.addObject("gradeId", gradeId);
		mav.addObject("teamId", teamId);
		mav.addObject("subjectName", subjectName);
		mav.addObject("subjectCode", subjectCode);
		mav.addObject("urlType", urlType);
		mav.setViewName("/teach/classRoomTeacher/addClassRoomTeacherPage");
		return mav;
	}
	/**
	 * 添加任课教师
	 * @param teamTeacher
	 * @return
	 */
	@RequestMapping("/addClassRoomTeacher") 
	@ResponseBody
	public String addClassRoomTeacher(TeamTeacher teamTeacher,
			@CurrentUser UserInfo user){
		try{
			Grade grade = this.gradeService.findGradeById(teamTeacher.getGradeId());
			//当前学年
			List<SchoolTermCurrent> schoolTermCurrentList = this.schoolTermCurrentService.findCurrentSchoolYear(user.getSchoolId());
			SchoolTermCurrent schoolYear = schoolTermCurrentList.get(0);
			//教师
			Teacher teacher = this.teacherService.findTeacherById(teamTeacher.getTeacherId());
			SubjectGrade gradeSubject = this.subjectGradeService.findSubjectGradeByGradeCodeAndSubjectCode(grade.getUniGradeCode(), teamTeacher.getSubjectCode(), user.getSchoolId());
			teamTeacher.setName(teacher.getName());
			teamTeacher.setSubjectCode(teamTeacher.getSubjectCode());
			teamTeacher.setSubjectName(gradeSubject.getSubjectName());
			teamTeacher.setSchoolYear(schoolYear.getSchoolYear());
			teamTeacher.setType(2);
			teamTeacher.setCreateDate(new Date());
			teamTeacher.setModifyDate(new Date());
			this.teamTeacherService.add(teamTeacher);
			//添加角色和用户关系
			addBbxUserRole(user,teacher.getUserId());
			
		}catch(Exception e){
			log.info("添加任课教师异常...");
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC; 
	}
	
	/**
	 * 修改任课教师
	 */
	@RequestMapping("/editClassRoomTeacherPage")  
	public ModelAndView editClassRoomTeacherPage(
			@RequestParam(value = "schoolId", required = true) String schoolId,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "teamId", required = true) String teamId,
			@RequestParam(value = "subjectName", required = true) String subjectName,
			@RequestParam(value = "subjectCode", required = true) String subjectCode,
			@RequestParam(value = "urlType", required = true) String urlType,
			@RequestParam(value = "teamTeacherId", required = true) String teamTeacherId,
			@RequestParam(value = "teacherId", required = true) String teacherId){
		ModelAndView mav = new ModelAndView();
		TeamTeacher teamTeacher = this.teamTeacherService.findTeamTeacherById(Integer.parseInt(teamTeacherId));
		List<Teacher> teacherList = this.teacherService.findActiveTeacherOfSchool(Integer.parseInt(schoolId));
		mav.addObject("teacherList", teacherList);
		mav.addObject("teacherId", teamTeacher.getTeacherId());
		mav.addObject("teamTeacherId", teamTeacherId);
		mav.setViewName("/teach/classRoomTeacher/editClassRoomTeacherPage");
		return mav;
	}
	
	/**
	 * 更新任课教师
	 */
	@RequestMapping("/updatClassRoomTeacher")
	@ResponseBody
	public String updatClassRoomTeacher(
			@CurrentUser UserInfo user,
			@RequestParam(value = "teamTeacherId", required = true) String teamTeacherId,
			@RequestParam(value = "teacherId", required = true) String teacherId){
		try{
			TeamTeacher teamTeacher = this.teamTeacherService.findTeamTeacherById(Integer.parseInt(teamTeacherId));
			Teacher teacher = this.teacherService.findTeacherById(Integer.parseInt(teacherId));
			teamTeacher.setTeacherId(teacher.getId());
			teamTeacher.setName(teacher.getName());
			teamTeacher.setModifyDate(new Date());
			this.teamTeacherService.modify(teamTeacher);
			
			if(teamTeacher.getTeacherId() == Integer.parseInt(teacherId)){
				
			}else{
				TeamTeacherCondition teamTeacherConditionTemp = new TeamTeacherCondition();
				//teamTeacherConditionTemp.setTeamId(teamId);
				teamTeacherConditionTemp.setTeacherId(teamTeacher.getTeacherId());
				teamTeacherConditionTemp.setType(2);
				//teamTeacherConditionTemp.isDelete();
				List<TeamTeacher> teamTeacherListTemp = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherConditionTemp, null, null);
				if(teamTeacherListTemp != null&&teamTeacherListTemp.size()>0){
					//保留角色和用户的俄关系
				}else{
					/*
					 * 删除用户和角色的关系
					 * UserRole userRole = new UserRole();
					User userTemp = new User();
					userTemp.setId(user.getId());
					Role role = new Role();
					role.setId(roleId);
					userRole.setUser(userTemp);
					userRole.setRole(role);
					this.userRoleService.add(userRole);
					
					this.userRoleService.addOrUpdate(userRole);*/
				}
				Teacher teacherTemp = this.teacherService.findTeacherById(teamTeacher.getTeacherId());
				addBbxUserRole(user,teacherTemp.getUserId());
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("更新任课教师异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC; 
	}
	
	/**
	 * 给班主任和科任教师添加对应的班班星角色
	 * 关于修改班主任和科任教师的地方逻辑没有处理，需要记着
	 * @param user
	 */
	private void addBbxUserRole(UserInfo user,int teacherId) {
		RoleCondition condition = new RoleCondition();
		condition.setCode(RoleCondition.DEFAULT_SUBJECT_TEACHER);
		condition.setGroupId(user.getGroupId());
		List<Role> roles = this.roleService.findRoleByCondition(condition, null, null);
		if(roles != null&&roles.size()>0){
			int roleId = roles.get(0).getId();
			UserRoleCondition userRoleCondition = new UserRoleCondition();
			userRoleCondition.setRoleId(roleId);
			userRoleCondition.setUserId(teacherId);
			List<UserRole> userRoleList = this.userRoleService.findUserRoleByCondition(userRoleCondition, null, null);
			if(userRoleList != null&&userRoleList.size()>0){
				
			}else{
				UserRole userRole = new UserRole();
				User userTemp = new User();
				userTemp.setId(teacherId);
				Role role = new Role();
				role.setId(roleId);
				userRole.setUser(userTemp);
				userRole.setRole(role);
				this.userRoleService.add(userRole);
			}
		}
	}
	/****************************************************新增修改任课教师开始************************************************/
	/**
	 * 添加任课教师
	 * @param teamTeacher
	 * @return
	 */
	@RequestMapping("/addOrModifyClassRoomTeacher") 
	@ResponseBody
	public String addOrModifyClassRoomTeacher(TeamTeacher teamTeacher,
			@CurrentUser UserInfo user){
		//添加任课教师 具备四个条件   必须有年级  必须有班级  必须有科目  必须有教师
		boolean isNotNUllOfGrade = teamTeacher.getGradeId() !=null && !"".equals(teamTeacher.getGradeId());
		boolean isNotNUllOfTeam = teamTeacher.getTeamId() != null && !"".equals(teamTeacher.getTeamId());
		boolean isNotNUllOfSubject = teamTeacher.getSubjectCode() != null && !"".equals(teamTeacher.getSubjectCode());
		boolean isNotNUllOfTeacher = teamTeacher.getTeacherId() != null && !"".equals(teamTeacher.getTeacherId());
		
		try{
			if(isNotNUllOfGrade && isNotNUllOfTeam && isNotNUllOfSubject && isNotNUllOfTeacher){
				String mess = this.teamTeacherService.addOrModifyClassRoomTeacherOfTeam(SysContants.SYSTEM_APP_ID,user.getGroupId(),teamTeacher);
				if(mess.equals(ResponseInfomation.OPERATION_ERROR)){
					return ResponseInfomation.OPERATION_FAIL;
				}
			}else{
				log.info("添加任课教师参数不全...");
				return ResponseInfomation.OPERATION_FAIL;
			}
		}catch(Exception e){
			log.info("添加任课教师异常...");
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC; 
	}

	/****************************************************新增修改任课教师结束************************************************/
	
}
