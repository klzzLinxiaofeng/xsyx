package platform.szxyzxx.web.teach.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.contants.TeamTeacherContants;
import platform.education.generalTeachingAffair.contants.TeamUserContants;
import platform.education.generalTeachingAffair.model.ClassTeacher;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.vo.ClassTeacherCondition;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
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
 * ???????????????
 * @author zhoujin
 *
 */
@Controller
@RequestMapping("/teach/classTeacher")
public class ClassTeacherController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(ClassTeacherController.class);
	
	/**
	 * ???????????????
	 */
	@RequestMapping("/classTeacherList")
	public ModelAndView getClassTeacherList(
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = new ModelAndView();
		String viewPath = "";
		try{
			List<SchoolTermCurrent> schoolTermCurrentList = this.schoolTermCurrentService.findCurrentSchoolYear(user.getSchoolId());
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentList.get(0);
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
			gradeCondition.setSchoolId(user.getSchoolId());
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, Order.asc("uni_grade_code"));

			viewPath="/teach/classTeacher/classTeacherList";
			mav.addObject("gradeList", gradeList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("..??????????????? ????????????...");
			//e.printStackTrace();
		}
		return mav;
	}
	
	/***
	 * ???????????????????????????
	 * @param teamCondition
	 * @param page
	 * @param order
	 * @param user
	 * @return
	 */
//	@RequestMapping(value = "/getTeamList", method = RequestMethod.POST)
//	@ResponseBody
//	public List<ClassTeacher> getTeamList(
//			@RequestParam(value = "gradeId", required = true) String gradeId,
//			//@ModelAttribute("teamCondition") TeamCondition teamCondition,
//			@ModelAttribute("classTeacherCondition") ClassTeacherCondition classTeacherCondition,
//			@ModelAttribute("page") Page page,
//			@ModelAttribute("order") Order order,
//			@CurrentUser UserInfo user){
//		List<ClassTeacher> classTeacherList = null;
//		try{
//			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
//			classTeacherCondition.setSchoolId(user.getSchoolId());
//			classTeacherCondition.setGradeId(Integer.parseInt(gradeId));
//			classTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
//			classTeacherList = teamTeacherService.findClassTeacherByCondition(classTeacherCondition, page, order);
//		}catch(Exception e){
//			log.info("..?????????????????????????????????...");
//			//e.printStackTrace();
//		}
//		return classTeacherList;
//	}
	
	/**
	 * ???????????????????????????
	 * @param gradeId
	 * @param classTeacherCondition
	 * @param page
	 * @param order
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getTeamList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getTeamList(
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@ModelAttribute("classTeacherCondition") ClassTeacherCondition classTeacherCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user,
			Model model){
		String viewPath = null;
		List<ClassTeacher> classTeacherList = null;
		try{
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			classTeacherCondition.setSchoolId(user.getSchoolId());
			classTeacherCondition.setGradeId(gradeId);
			classTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
			classTeacherList = teamTeacherService.findClassTeacherByCondition(classTeacherCondition, page, order);
		}catch(Exception e){
			log.info("..?????????????????????????????????...");
			//e.printStackTrace();
		}
		model.addAttribute("classTeacherList", classTeacherList);
		viewPath = "/teach/classTeacher/list";
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * ???????????????
	 */
	@RequestMapping("/modifyClassTeacher") 
	public ModelAndView modifyClassTeacher(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "teacherId", required = true) String teacherId,
			@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();
		try{
			List<Teacher> teacherList = teacherService.findActiveTeacherOfSchool(user.getSchoolId());
			mav.addObject("teacherList", teacherList);
			mav.addObject("id", id);
			mav.addObject("teacherId", teacherId);
			mav.setViewName("/teach/classTeacher/modifyClassTeacher");
		}catch(Exception e){
			log.info("===?????????????????????====");
			e.printStackTrace();
		}
		
		return mav;
	}
	
	/**
	 * ?????????????????????
	 * @param id
	 * @param teacherId
	 * @return
	 */
	@RequestMapping("/updateClassTeacher")
	@ResponseBody
	public String updateClassTeacher(@RequestParam(value = "id", required = true) String id,
                                          @RequestParam(value = "teacherId", required = true) String teacherId){
		try{
			TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(Integer.parseInt(id));
			teamTeacher.setTeacherId(Integer.parseInt(teacherId));
			teamTeacherService.modify(teamTeacher);
			
		}catch(Exception e){
			log.info("..?????????????????????..");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * ?????????????????????
	 */
	@RequestMapping("/addClassTeacherPage")
	public ModelAndView addClassTeacherPage(
			@RequestParam(value = "teamId", required = true) String teamId,
            @RequestParam(value = "gradeId", required = true) String gradeId,
            @RequestParam(value = "urlType", required = true) String urlType,
            @CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();
		try{
			List<Teacher> teacherList = teacherService.findActiveTeacherOfSchool(user.getSchoolId());
			mav.addObject("urlType", urlType);
			mav.addObject("teamId", teamId);
			mav.addObject("gradeId", gradeId);
			mav.addObject("teacherList", teacherList);
			mav.setViewName("/teach/classTeacher/addClassTeacherPage");
		}catch(Exception e){
			log.info("?????????????????????");
			//e.printStackTrace();
		}
		return mav;
	}
	/***
	 * ???????????????
	 * @param teamId
	 * @param gradeId
	 * @param teacherId
	 * @param type
	 * @param user
	 * @return
	 */
	@RequestMapping("/addClassTeacher")
	@ResponseBody
	public String addClassTeacher(
			@RequestParam(value = "teamId", required = true) String teamId,
            @RequestParam(value = "gradeId", required = true) String gradeId,
            @RequestParam(value = "teacherId", required = true) String teacherId,
            @RequestParam(value = "type", required = true) String type,
            @CurrentUser UserInfo user){
		try{
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeamId(Integer.parseInt(teamId));
			//teamTeacherCondition.setTeacherId(Integer.parseInt(teacherId));
			teamTeacherCondition.setGradeId(Integer.parseInt(gradeId));
			teamTeacherCondition.setType(Integer.parseInt(type));
			teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
			List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
			Teacher teacher = this.teacherService.findTeacherById(Integer.parseInt(teacherId));
			//System.out.println("teacher:"+teacher.getName());
			if(teamTeacherList.isEmpty()){
				TeamTeacher teamTeacher = new TeamTeacher();
				teamTeacher.setTeamId(Integer.parseInt(teamId));
				teamTeacher.setGradeId(Integer.parseInt(gradeId));
				teamTeacher.setTeacherId(Integer.parseInt(teacherId));
				teamTeacher.setName(teacher.getName());
				teamTeacher.setSubjectCode(null);
				teamTeacher.setType(Integer.parseInt(type));
				teamTeacher.setCreateDate(new Date());
				teamTeacher.setModifyDate(new Date());
				teamTeacher.setSchoolYear(schoolTermCurrent.getSchoolYear());
				teamTeacherService.add(teamTeacher);
				
				//int teacherRoleId = user.getId();
				Teacher teacherTemp = this.teacherService.findTeacherById(Integer.parseInt(teacherId));
				addBbxUserRole(user,teacherTemp.getUserId());
				
				
			}else{
				TeamTeacher teamTeacher_ = teamTeacherList.get(0);
				teamTeacher_.setTeacherId(Integer.parseInt(teacherId));
				teamTeacher_.setName(teacher.getName());
				
				teamTeacherService.modify(teamTeacher_);
				if(teamTeacher_.getTeacherId() == Integer.parseInt(teacherId)){
					
				}else{
					TeamTeacherCondition teamTeacherConditionTemp = new TeamTeacherCondition();
					//teamTeacherConditionTemp.setTeamId(teamId);
					teamTeacherConditionTemp.setTeacherId(teamTeacher_.getTeacherId());
					teamTeacherConditionTemp.setType(1);
					//teamTeacherConditionTemp.isDelete();
					List<TeamTeacher> teamTeacherListTemp = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherConditionTemp, null, null);
					if(teamTeacherListTemp != null&&teamTeacherListTemp.size()>0){
						//?????????????????????????????????
					}else{
						/*
						 * ??????????????????????????????
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
				
				}
				
				
				//int teacherRoleId = user.getId();
				Teacher teacherTemp = this.teacherService.findTeacherById(teamTeacher_.getTeacherId());
				addBbxUserRole(user,teacherTemp.getUserId());
				
				
			}
			
		}catch(Exception e){
			log.info("?????????????????????");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	/**
	 * ?????????????????????????????????????????????????????????
	 * ??????????????????????????????????????????????????????????????????????????????
	 * @param user
	 */
	private void addBbxUserRole(UserInfo user,int teacherId) {
		RoleCondition condition = new RoleCondition();
		condition.setCode(RoleCondition.DEFAULT_CLASS_MASTER);
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
	
	/**  ?????????
	 *   ???????????????????????????
	 *   date:2015-12-10
	 */
	@RequestMapping("/addOrModifyClassTeacher")
	@ResponseBody
	public String addOrModifyClassTeacher(
			@RequestParam(value = "teamId", required = false) String teamId,
            @RequestParam(value = "gradeId", required = false) String gradeId,
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @CurrentUser UserInfo user
            ){
		boolean isNotNullOfTeamId = teamId != null && !"".equals(teamId);
		boolean isNotNullOfGradeId = gradeId != null && !"".equals(gradeId);
		boolean isNotNullOfTeacherId = teacherId != null && !"".equals(teacherId);
		try{
			if(isNotNullOfTeamId && isNotNullOfGradeId && isNotNullOfTeacherId){
				this.teamTeacherService.addOrModifyClassTeacher(SysContants.SYSTEM_APP_ID, user.getGroupId(), gradeId, teamId, teacherId);
			}else{
				log.error("?????????????????????????????????????????????????????????");
				return ResponseInfomation.OPERATION_FAIL;
			}
		}catch(Exception e){
			log.error("??????????????????????????????");
			return ResponseInfomation.OPERATION_FAIL;
		}

		//???????????????????????????
		//setClassMaster(Integer.parseInt(teamId),Integer.parseInt(teacherId));


		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * ???????????????
	 * 2016-01-28
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteTeamTeacher(@PathVariable(value="id") Integer id, @CurrentUser UserInfo user) {
		TeamTeacher teamTeacher = teamTeacherService.findTeamTeacherById(id);
		return this.teamTeacherService.removeMasterFromTeam(teamTeacher, SysContants.SYSTEM_APP_ID, user.getGroupId(), user.getSchoolId());
	}
	
	
	/*=============??????????????????????????????????????????????????????????????????pj_team_user?????????    2015-12-15==============*/
	/**
	 * ??????????????????????????????pj_team_teacher?????????????????????pj_team_user???
	 * 2015-12-15
	 */
	@RequestMapping("/updateTeamUserForTeacher")
	public void updateTeamUserForTeacher() {
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setDelete(false);
		//????????????????????????????????????
		List<TeamTeacher> teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		Teacher teacher = null;
		for(TeamTeacher teamTeacher : teamTeacherList) {
			Integer teacherId = teamTeacher.getTeacherId();
			Integer teamId = teamTeacher.getTeamId();
			Integer type = teamTeacher.getType();
			//??????????????????
			teacher = this.teacherService.findTeacherById(teacherId);
			if(teacher != null) {
				Integer userId = teacher.getUserId();
				//??????teamUser????????????
				TeamUser teamUser = this.teamUserService.findUnique(teamId, userId);
				//?????????????????????????????????????????????????????????????????????isTeacher/isMaster = true
				if(teamUser != null) {
					if(type.equals(TeamTeacherContants.TYPE_MASTER)) {
						//?????????????????????
						if(!teamUser.getIsMaster()) {
							teamUser.setIsMaster(true);
							teamUser.setModifyDate(new Date());
							teamUser = this.teamUserService.modify(teamUser);
						}
					}else if(type.equals(TeamTeacherContants.TYPE_SUBJECT_TEACHER)) {
						//????????????????????????
						if(!teamUser.getIsTeacher()) {
							teamUser.setIsTeacher(true);
							teamUser.setModifyDate(new Date());
							teamUser = this.teamUserService.modify(teamUser);
						}
					}
				}else {
					//??????????????????????????????????????????????????????????????????????????????
					addTeamUser(type, teamId, userId, teacher.getName(), teacher.getSex());
				}
			}
		}
	}
	
	/**
	 * ??????????????????????????????pj_team_student????????????????????????pj_parent_student?????????????????????pj_team_user???
	 * 2015-12-18
	 */
	@RequestMapping("/updateTeamUserForStudent")
	public void updateTeamUserForStudent() {
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		teamStudentCondition.setIsDelete(false);
		List<TeamStudent> teamStudentList = this.teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
		Student student = null;
		Parent parent = null;
		for(TeamStudent teamStudent : teamStudentList) {
			Integer studentId = teamStudent.getStudentId();
			Integer teamId = teamStudent.getTeamId();
			
			/*=====================????????????==================*/
			//????????????????????????
			student = this.studentService.findStudentById(studentId);
			if(student != null) {
				Integer studentUserId = student.getUserId();
				//??????TeamUser????????????
				TeamUser teamUser = this.teamUserService.findUnique(teamId, studentUserId);
				if(teamUser != null) {
					//?????????????????????????????????????????????????????????????????????isStudent = true
					if(!teamUser.getIsStudent()) {
						teamUser.setIsStudent(true);
						teamUser.setModifyDate(new Date());
						teamUser = this.teamUserService.modify(teamUser);
					}
				}else {
					//??????????????????????????????????????????????????????????????????????????????
					addTeamUser(TeamUserContants.TYPE_STUDENT, teamId, studentUserId, student.getName(), student.getSex());
				}
				/*=====================????????????==================*/
				
				/*=====================????????????==================*/
				//?????????????????????????????????
				ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
				parentStudentCondition.setStudentUserId(studentUserId);
				parentStudentCondition.setIsDelete(false);
				List<ParentStudent> parentStudentList = this.parentStudentService.findParentStudentByCondition(parentStudentCondition);
				for(ParentStudent parentStudent : parentStudentList) {
					Integer parentUserId = parentStudent.getParentUserId();
					//????????????????????????
					parent = this.parentService.findUniqueByUserId(parentUserId);
					if(parent != null) {
						//??????TeamUser????????????
						TeamUser teamUserParent = this.teamUserService.findUnique(teamId, parentUserId);
						if(teamUserParent != null) {
							//?????????????????????????????????????????????????????????????????????isParent = true
							if(!teamUserParent.getIsParent()) {
								teamUserParent.setIsParent(true);
								teamUserParent.setModifyDate(new Date());
								teamUserParent = this.teamUserService.modify(teamUserParent);
							}
						}else {
							//??????????????????????????????????????????????????????????????????????????????
							addTeamUser(TeamUserContants.TYPE_PARENT, teamId, parentUserId, parent.getName(), "");
						}
					}
				}
				/*=====================????????????==================*/
				
			}
		}
	}
	
	/**
	 * ???????????????????????????pj_team_user???
	 * 2015-12-18
	 * @param type
	 * @param teamId
	 * @param userId
	 * @param name
	 * @param sex
	 * @return
	 */
	public TeamUser addTeamUser(Integer type, Integer teamId, Integer userId, String name, String sex) {
		TeamUser teamUser = new TeamUser();
		Boolean isMaster = false;
		Boolean isTeacher = false;
		Boolean isStudent = false;
		Boolean isParent = false;
		if (type.equals(TeamUserContants.TYPE_MASTER)) {
			isMaster = true;
		}else if(type.equals(TeamUserContants.TYPE_SUBJECT_TEACHER)) {
			isTeacher = true;
		}else if(type.equals(TeamUserContants.TYPE_STUDENT)) {
			isStudent = true;
		}else if(type.equals(TeamUserContants.TYPE_PARENT)) {
			isParent = true;
		}
		teamUser.setTeamId(teamId);
		teamUser.setUserId(userId);
		teamUser.setIsMaster(isMaster);
		teamUser.setIsTeacher(isTeacher);
		teamUser.setIsStudent(isStudent);
		teamUser.setIsParent(isParent);
		teamUser.setName(name);
		teamUser.setSex(sex);
		teamUser.setCreateDate(new Date());
		teamUser.setModifyDate(new Date());
		teamUser = this.teamUserService.add(teamUser);
		return teamUser;
	}
	/*=============??????????????????????????????????????????????????????????????????pj_team_user?????????    2015-12-18==============*/




//	/**
//	 * ?????????????????????????????????????????????
//	 */
//	@RequestMapping(method = RequestMethod.GET,value = "/import/seewo")
//	public boolean importSeewo(){
//
//		List<TeamBo> teams = teamService.findBoNotSendSeewo();
//
//		MySeewoClient client =
//				MySeewoClient.request(ClassApiAddStandardClassesRequest.class);
//
//		ClassApiAddStandardClassesParam param = new ClassApiAddStandardClassesParam();
//
//		ClassApiAddStandardClassesParam.JSONRequestBody body = ClassApiAddStandardClassesParam.JSONRequestBody.builder()
//				.unitId(SCHOOL_CODE)
//				.classAddDtoList(teamConversion(teams))
//				.build();
//
//		param.setRequestBody(body);
//		if(client.param(param).invoke().getStatusCode() == 200){
//
//			List<Integer> ids = new ArrayList<>(teams.size());
//			for (int i = 0; i < teams.size(); i++) {
//				ids.add(teams.get(i).getId());
//			}
//			teamService.updateAsSendSeewoByIds(ids);
//			return true;
//		}
//
//		return false;
//
//
//	}


//	private List<ClassApiAddStandardClassesParam.ClassSaveDto> teamConversion(List<TeamBo> teams){
//
//		List<ClassApiAddStandardClassesParam.ClassSaveDto> classSaveDtos = new ArrayList<>(teams.size());
//
//		for (TeamBo team : teams) {
//
//			ClassApiAddStandardClassesParam.ClassSaveDto saveDto = new ClassApiAddStandardClassesParam.ClassSaveDto();
//			saveDto.setId(team.getId().toString());
//			saveDto.setUnitId(SCHOOL_CODE);
//			saveDto.setStageCode(team.getStageCode());
//			saveDto.setGrade(team.getGradeNumber());
//			saveDto.setGradeYear(team.getShoolYear());
//			saveDto.setClazz(team.getTeamNumber());
//			saveDto.setNickName(team.getName());
//			// ?????????uid??????????????????
//			saveDto.setCreatorId("0");
//			classSaveDtos.add(saveDto);
//
//		}
//
//		return classSaveDtos;
//
//
//	}
//
//
//	/**
//	 * ??????????????????????????????
//	 * @param teamId
//	 * @param teacherId
//	 */
//	@RequestMapping(value = "/setMaster")
//	public boolean setClassMaster(Integer teamId,Integer teacherId){
//
//		//????????????
//		Teacher teacher = teacherService.findTeacherById(teacherId);
//
//
//		ClassApiSetClassMasterParam param = new ClassApiSetClassMasterParam();
//		//????????????MimeType??? application/json
//		ClassApiSetClassMasterParam.JSONRequestBody requestBody = ClassApiSetClassMasterParam.JSONRequestBody.builder()
//				.build();
//		param.setRequestBody(requestBody);
//
//		ClassApiSetClassMasterParam.MisThirdClassQueryDto query = ClassApiSetClassMasterParam.MisThirdClassQueryDto.builder()
//				.classId(teamId.toString())
//				.seewoClassId(teamId.toString())
//				.phone(teacher.getMobile())
//				.schoolUid(SCHOOL_UID)
//				.appId(APPID)
//				.build();
//		requestBody.setQuery(query);
//		param.setRequestBody(requestBody);
//
//		ClassApiSetClassMasterResult result = MySeewoClient.request(ClassApiSetClassMasterRequest.class)
//				.param(param).invoke();
//
//
//		if(result.getStatusCode() == 200){
//			return true;
//		}else {
//			return false;
//		}
//
//
//	}

}
