package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import platform.education.generalTeachingAffair.contants.RolePriorityContants;
import platform.education.generalTeachingAffair.contants.TeamTeacherContants;
import platform.education.generalTeachingAffair.contants.TeamUserContants;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.dao.TeamTeacherDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.utils.DataImportCheck;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.contants.SysDefRole;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;

import java.util.*;

public class TeamTeacherServiceImpl implements TeamTeacherService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private TeamTeacherDao teamTeacherDao;

	/**
	 * 某学校的当前学年（2015-12-09）
	 */
	private SchoolTermCurrentService schoolTermCurrentService;

	private TeamDao teamDao;

	private TeacherDao teacherDao;

	private SubjectService subjectService;

	private TeamUserService teamUserService;

	private UserRoleService userRoleService;

	private RoleService roleService;

	private UserService userService;

	private GradeService gradeService;

	private TeamService teamService;

	@Autowired
	private BasicSQLService basicSQLService;


	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}


	public SchoolTermCurrentService getSchoolTermCurrentService() {
		return schoolTermCurrentService;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public TeamUserService getTeamUserService() {
		return teamUserService;
	}

	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TeamTeacherDao getTeamTeacherDao() {
		return teamTeacherDao;
	}

	public void setTeamTeacherDao(TeamTeacherDao teamTeacherDao) {
		this.teamTeacherDao = teamTeacherDao;
	}

	public void setSchoolTermCurrentService(
			SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}

	@Override
	public TeamTeacher findTeamTeacherById(Integer id) {
		try {
			return teamTeacherDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public TeamTeacher add(TeamTeacher teamTeacher) {
		return teamTeacherDao.create(teamTeacher);
	}

	@Override
	public TeamTeacher modify(TeamTeacher teamTeacher) {
		return teamTeacherDao.update(teamTeacher);
	}

	@Override
	public void remove(TeamTeacher teamTeacher) {
		try {
			teamTeacherDao.delete(teamTeacher);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}

	}

	@Override
	public List<TeamTeacher> findTeamTeacherByCondition(TeamTeacherCondition teamTeacherCondition, Page page, Order order) {
		return teamTeacherDao.findTeamTeacherByCondition(teamTeacherCondition,page, order);
	}

	@Override
	public List<TeamTeacher> findClassTeacherByGradeIdAndTeamId(
			Integer gradeId, Integer teamId) {
		return teamTeacherDao.findClassTeacherByGradeIdAndTeamId(gradeId,
				teamId);
	}

	@Override
	public List<ClassTeacher> findClassTeacherByCondition(
			ClassTeacherCondition classTeacherCondition, Page page, Order order) {
		return teamTeacherDao.findClassTeacherByCondition(
				classTeacherCondition, page, order);

	}

	@Override
	public List<TeamTeacherVo> findTeacherNumberOfSubjectBySchoolId(
			Integer schoolId) {
		// TODO Auto-generated method stub
		return this.teamTeacherDao.findTeacherNumberOfSubjectDate(schoolId);
	}
	@Override
	public List<TeamTeacherVo> findTeamTeacherGradeByCondition(TeamTeacherCondition teamTeacherCondition) {
		return this.teamTeacherDao.findTeamTeacherGradeByCondition(teamTeacherCondition);
	}


	@Override
	public List<TeamTeacherVo> findTeamTeacherVoByCondition(
			TeamTeacherCondition teamTeacherCondition) {
		return this.teamTeacherDao
				.findTeamTeacherVoByCondition(teamTeacherCondition);
	}


	@Override
	public TeamTeacher findTeamTeacherByTeamIdAndSubjectCode(Integer teamId,String subjectCode) {
		return this.teamTeacherDao.findTeamTeacherByTeamIdAndSubjectCode(teamId, subjectCode);
	}

	//==================================添加或修改任课教师开始======================================================//
	@Override
	public String addOrModifyClassRoomTeacherOfTeam(Integer appId, Integer groupId, TeamTeacher teamTeacher) {
		Team team = teamDao.findById(teamTeacher.getTeamId());
		Teacher teacher = teacherDao.findById(teamTeacher.getTeacherId());

		// 所选的教师 和 科目都存在  （A流程第一步）
		if (team != null && teacher != null) {

			// 教师所在的学校 和 科目所在的学校一致  （A流程第二步）
			if (team.getSchoolId() != null && teacher.getSchoolId() != null && teacher.getSchoolId().equals(team.getSchoolId())) {

				// 将teamTeacher的属性赋给teamTeacherVo,存的是新的教师 和科目的其他信息
				TeamTeacherVo teamTeacherVo = new TeamTeacherVo();
				BeanUtils.copyProperties(teamTeacher, teamTeacherVo);
				teamTeacherVo.setUserId(teacher.getUserId());
				teamTeacherVo.setName(teacher.getName());
				teamTeacherVo.setSex(teacher.getSex());
				teamTeacherVo.setType(TeamTeacherContants.TYPE_SUBJECT_TEACHER);

				SchoolTermCurrent schoolYear = getCurrentSchoolTerm(teacher.getSchoolId());
				if (schoolYear != null && schoolYear.getSchoolYear() != null && !"".equals(schoolYear.getSchoolYear())) {
					teamTeacherVo.setSchoolYear(schoolYear.getSchoolYear());
				}

				// 获取科目
//				Subject subject = subjectService.findUnique(teacher.getSchoolId(), null, teamTeacher.getSubjectCode());
				Subject subject = subjectService.findUnique(teacher.getSchoolId(), teamTeacher.getSubjectCode());
				if (subject != null && subject.getName() != null && !"".equals(subject.getName())) {
					teamTeacherVo.setSubjectName(subject.getName());

					teamTeacherVo.setPosition(teamTeacherVo.getSubjectName()==null?"":teamTeacherVo.getSubjectName() + TeamTeacherContants.TYPE_SUBJECT_TEACHER_STR);  //职务，班主任--【班主任】，任课教师--【Subject教师】
				}

				// 获取指定科目的任课教师,这是是原来的旧的、被替换走的教师 （A流程第三步）
				TeamTeacher teamTea = this.teamTeacherDao.findTeamTeacherByTeamIdAndSubjectCode(teamTeacher.getTeamId(), teamTeacher.getSubjectCode());

				// 给用户添加任课教师角色开始
				// 获取角色 在创建用户角色时用到
				Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.SUBJECT_TEACHER);

				// 获取user 在创建用户角色时用到
				User user = userService.findUserById(teacher.getUserId());

				// 获取任课教师角色是否已经存在 在获取之前先判断是否有该角色存在
				UserRole userRole = null;
				if (role != null) {
					//用户角色
					userRole = userRoleService.findUnique(teacher.getUserId(), role.getId());
				}

				// 指定科目的任课教师存在（A流程第四步）
				if (teamTea != null) {

					// 判断现在添加的 和 之前添加的是否是同一个老师，如果不是，将旧记录删除 如果是 则表示任课教师已存在，添加成功  退出
					if (teacher.getId() != null && teamTea.getTeacherId() != null && !teacher.getId().equals(teamTea.getTeacherId())) {
						//原有的任课教师与输入的不是同一个人，则修改原有任课教师作废标记为删除（is_delete = true）
						teamTea.setDelete(true);
						this.teamTeacherDao.update(teamTea);

						//2017-11-29	任课教师作废，修改teacher主表
						Teacher t = new Teacher();
						t.setId(teamTea.getTeacherId());
						t.setModifyDate(new Date());
						this.teacherDao.update(t);

						// 修改被替换教师的相关信息   （A流程第五步）
						removeTeamTeacherFormUserRole(role, teamTea, teacher.getSchoolId());
					} else {
						//原有的任课教师与输入的是同一个人，任课教师已经添加，退出
						return "success";
					}
				}

				// 任课教师不存在 或者是存在 但是不是同一个人 则添加班级任课教师记录
				addTeamTeacher(teamTeacherVo);

				//2017-11-29	添加任课教师，修改teacher主表
				Teacher t = new Teacher();
				t.setId(teacher.getId());
				t.setModifyDate(new Date());
				this.teacherDao.update(t);

				// B流程
				// 去到teamUser表 判断该教师是否已存在 如果存在 将isTeacher设置为true 不存在 新增记录  并且isTeacher是true
//				TeamUser tu = getTeamUserByUserId(teacher.getUserId());
				TeamUser tu = this.teamUserService.findUnique(teamTeacherVo.getTeamId(), teacher.getUserId());

				// teamUser表中存在添加的教师 将isTeacher设置为true
				if (tu != null) {
					TeamUser teamUser = new TeamUser();
					teamUser.setId(tu.getId());
					teamUser.setIsTeacher(true);
					teamUser.setModifyDate(new Date());
					teamUserService.modify(teamUser);
				} else {

					// teamUser表中不存在添加的教师 新增记录并将isTeacher设置为true
					addTeamUser(teamTeacherVo);
				}

				//给用户添加任课教师角色   如果userRole已经存在 不需创建 直接返回成功标识 ；不存在则创建
				if (userRole == null) {
					if (role != null && user != null) {
						//addUserRole(user, role, RoleContants.DEFAULT_PRIORITY);
						//将任课教师优先级设为1，区分任课教师、教职工的角色优先级，

						addUserRole(user, role, RolePriorityContants.SUBJECT_TEACHER);
					} else {
						log.error("没有对应的角色或用户，导致创建任课教师失败！！！");
						return "error";
					}
				}
				return "success";
			} else {
				log.error("对应的科目所在学校 与 教师所在学校不一致");
				return "error";
			}
		} else {
			log.error("对应的科目 或 教师不存在");
			return "error";
		}
	}

	//==================================添加或修改科任教师结束======================================================//



	//==================================添加或修改班主任开始======================================================//
	@Override
	public String addOrModifyClassTeacher(Integer appId, Integer groupId, String gradeId, String teamId, String teacherId){
		//获得班级信息
		Team team = teamDao.findById(Integer.valueOf(teamId));
		//要指定为班主任的教师
		Teacher teacher = teacherDao.findById(Integer.valueOf(teacherId));

		// 所选的教师 和 科目都存在  （A流程第一步）
		if(team != null && teacher != null){
			//判断班级所在学校与教师所在学校是否一致（A流程第二步）
			if (team.getSchoolId() != null && teacher.getSchoolId() != null && teacher.getSchoolId().equals(team.getSchoolId())) {

				//获取当前学年
//				SchoolTermCurrent schoolYear = getCurrentSchoolTerm(teacher.getSchoolId());

				//TeamTeacherVo创建班主任的时候用到
				TeamTeacherVo teamTeacherVo = new TeamTeacherVo();
				teamTeacherVo.setGradeId(Integer.valueOf(gradeId));
				teamTeacherVo.setTeamId(Integer.valueOf(teamId));
				teamTeacherVo.setTeacherId(Integer.valueOf(teacherId));
				teamTeacherVo.setUserId(teacher.getUserId());
				teamTeacherVo.setName(teacher.getName());
				teamTeacherVo.setSex(teacher.getSex());
//				teamTeacherVo.setSchoolYear(schoolYear.getSchoolYear() == null ? null : schoolYear.getSchoolYear());
				teamTeacherVo.setType(TeamTeacherContants.TYPE_MASTER);
				teamTeacherVo.setPosition(TeamTeacherContants.TYPE_MASTER_STR);    //职务，班主任--【班主任】，任课教师--【Subject教师】

				//获取当前学年
				SchoolTermCurrent schoolYear = getCurrentSchoolTerm(teacher.getSchoolId());
				if (schoolYear != null && schoolYear.getSchoolYear() != null && !"".equals(schoolYear.getSchoolYear())) {
					teamTeacherVo.setSchoolYear(schoolYear.getSchoolYear());
				}

				// 获取角色 在创建用户角色时用到
				Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.CLASS_MASTER);

				// 获取user 在创建用户角色时用到
				User user = userService.findUserById(teacher.getUserId());

				// 获取任课教师角色是否已经存在 在获取之前先判断是否有该角色存在
				UserRole userRole = null;
				if (role != null) {
					userRole = userRoleService.findUnique(teacher.getUserId(), role.getId());
				}


				//判断班级是否已经有班主任记录
				List<Teacher> masterList = this.teacherDao.findMasterOfTeam(Integer.valueOf(teamId));

				//判断班级是否已经有班主任记录（A流程第三步）
				if(masterList.size() > 0){

					//判断原有班主任与输入的班主任是否是同一个人  这个教师是将要被取代的教师（旧的班主任）（A流程第四步）
					Teacher t = masterList.get(0);
					if(t != null && !t.getId().equals(Integer.valueOf(teacherId))){

						//不相同：删除原有的班主任记录（A流程第五步）
						removeMasterFormUserRole(role, t.getId(), t.getUserId(), Integer.valueOf(teamId), t.getSchoolId());

						//2017-11-29	班主任作废，修改teacher主表
						t.setModifyDate(new Date());
						this.teacherDao.update(t);
					}else{

						//相同  表示班主任已添加，退出程序
						return "success";
					}

				}

				//添加班级班主任记录（A流程第六步）
				addTeamTeacher(teamTeacherVo);

				//2017-11-29	添加班主任，修改teacher主表
				Teacher t = new Teacher();
				t.setId(teacher.getId());
				t.setModifyDate(new Date());
				this.teacherDao.update(t);

				//B流程开始
				//判断TeamUser（新指派的教师的TeamUser）是否存在（B流程第一步）
//				TeamUser tu = getTeamUserByUserId(teacher.getUserId());
				TeamUser tu = this.teamUserService.findUnique(teamTeacherVo.getTeamId(), teacher.getUserId());

				//（B流程第二步）
				if(tu != null){

					//如果之前存在这个班主任记录  将isMaster设置为true
					TeamUser teamUser = new TeamUser();
					teamUser.setIsMaster(true);
					teamUser.setId(tu.getId());
					teamUser.setModifyDate(new Date());
					teamUserService.modify(teamUser);
				}else{

					//如果不存在这个班主任记录  新建TeamUser记录 设置isMaster 为true
					addTeamUser(teamTeacherVo);
				}

				//给用户添加班主任角色（yh_user_role）（B流程第三步）
				// 如果userRole已经存在 不需创建 直接返回成功标识 ；不存在则创建
				if (userRole == null) {
					if (role != null && user != null) {
						//addUserRole(user, role, RoleContants.DEFAULT_PRIORITY);
						//将班主任优先级设为2，区分班主任、任课教师、教职工的角色优先级，

						addUserRole(user, role, RolePriorityContants.CLASS_MASTER);
					} else {
						log.error("没有对应的角色或用户，导致创建任课教师失败！！！");
						return "error";
					}
				}
				return "success";
			}else {
				log.error("科目所在学校与教师所在学校不一致，创建失败！");
				return "error";
			}
		}else{
			log.error("班级与教师不存在，创建失败!");
			return "error";
		}
	}
	//==================================添加或修改班主任结束======================================================//

	/**
	 * @function 移除原班主任的一些信息
	 * @param role
	 * @param schoolId
	 */
	public void removeMasterFormUserRole(Role role, Integer teacherId, Integer userId, Integer teamId, Integer schoolId) {

		//判断TeamUser是否已存在
//		TeamUserCondition teamUserCondition = new TeamUserCondition();
//		teamUserCondition.setUserId(userId);
//		teamUserCondition.setTeamId(teamId);
//		List<TeamUser> tamUserList = teamUserService.findTeamUserByCondition(teamUserCondition);
//		
//		//如果存在  修改为isMaster = false
//		if(tamUserList.size() > 0){
//			TeamUser tu = tamUserList.get(0);
//			if(tu != null){
//				tu.setIsMaster(false);
//				tu.setModifyDate(new Date());
//				teamUserService.modify(tu);
//			}
//		}

		//判断TeamUser是否存在，存在则修改班主任状态为false（isMaster = false）
		TeamUser teamUser = this.teamUserService.findUnique(teamId, userId);
		if(teamUser != null) {
			teamUser.setIsMaster(false);
			teamUser.setModifyDate(new Date());
			teamUserService.modify(teamUser);
		}

		//取得该教师在本校的所有TeamTeacher记录，判断其是否在其他班中也担任班主任
		 TeamTeacherCondition ttc = new TeamTeacherCondition();
		 ttc.setDelete(false);
		 ttc.setSchoolId(schoolId);
//		 ttc.setUserId(userId);
		 ttc.setTeacherId(teacherId);
		 ttc.setType(TeamTeacherContants.TYPE_MASTER);
		 List<TeamTeacher> teamTeacherList = teamTeacherDao.findTeamTeacherByCondition(ttc, null, null);

		 //判断在其他班级是否还担任班主任, false不再担任其他班班主任，true担任其他班班主任
		 boolean isTask = false;
		 if(teamTeacherList.size() > 0){
			 for(TeamTeacher t : teamTeacherList){
				 if(t.getTeamId() != null && !t.getTeamId().equals(teamId)){
					 isTask = true;
				 }else{
					 //如果班级是当前班级  就将其删除
					 t.setDelete(true);
					 teamTeacherDao.update(t);
				 }
			 }
		 }

		 //如果不担任其他班班主任  移除（yh_user_role）中该教师的班主任角色
		 if(!isTask){
			 //移除角色
			 reMoveRoleFormUserRole(userId, role);
		 }

	}


	/**
	 * @function 移除原教师的一些信息
	 * @param role
	 * @param teamTeacher
	 * @param schoolId
	 */
	public void removeTeamTeacherFormUserRole(Role role, TeamTeacher teamTeacher, Integer schoolId) {

		TeamTeacherCondition ttc = new TeamTeacherCondition();
		ttc.setDelete(false);
		ttc.setSchoolId(schoolId);
		ttc.setTeacherId(teamTeacher.getTeacherId());
		ttc.setType(TeamTeacherContants.TYPE_SUBJECT_TEACHER);  //任课教师

		// 查找本校被替换的教师是否还担任其他科目教师  此处判断是否要移除用户在本校的任课教师角色（pj_user_role）
		List<TeamTeacherVo> listInSchool = teamTeacherDao.findTeamTeacherVoByCondition(ttc);

		// 查找被替换的教师在本班中是否还担任其他科目教师   此处判断是否要修改pj_team_user表中isTeacher标志
		ttc.setTeamId(teamTeacher.getTeamId());
		List<TeamTeacherVo> listInTeam = teamTeacherDao.findTeamTeacherVoByCondition(ttc);

		// 获取被替换教师的信息
		Teacher teacher = teacherDao.findById(teamTeacher.getTeacherId());

		// 判断如果该教师在本班中除了该科目之外 不再担任其他科目教师  则更新TeamUser表中isTeacher=false
		boolean flagInTeam = isTakeOfTeam(listInTeam, teamTeacher.getSubjectCode());

		// 如果不担任 更新TeamUser表中isTeacher=false
		if (!flagInTeam) {
//			TeamUserCondition tuc = new TeamUserCondition();
//			tuc.setUserId(teacher.getUserId());
//			tuc.setIsTeacher(true);
//			tuc.setTeamId(teamTeacher.getTeamId());
//			List<TeamUser> tuList = teamUserService.findTeamUserByCondition(tuc);
//			
//			// 如果该教师在本班中不担任其他科目的教师，则有且仅有一条数据
//			if (tuList.size() > 0) {
//				TeamUser tus = new TeamUser();
//				tus.setId(tuList.get(0).getId());
//				tus.setIsTeacher(false);
//				tus.setModifyDate(new Date());
//				teamUserService.modify(tus);
//			}

			//该教师在本班中除了该科目外不再担任其他科目教师，更新TeamUser表中isTeacher=false
			TeamUser teamUser = this.teamUserService.findUnique(teamTeacher.getTeamId(), teacher.getUserId());

			if(teamUser != null) {
				teamUser.setIsTeacher(false);
				teamUser.setModifyDate(new Date());
				teamUserService.modify(teamUser);
			}

			// 判断如果该教师除了在本班中 不再担任其他科目教师 则移除用户在本校的任课教师角色（pj_user_role）
			boolean flagInSchool = isTakeOfSchool(listInSchool, teamTeacher.getTeamId());

			// 如果不担任 更新UserRole表中任课教师的角色
			if (!flagInSchool) {

				// 获取任课教师角色是否已经存在 在获取之前先判断是否有该角色存在
				if (role != null) {
					reMoveRoleFormUserRole(teacher.getUserId(), role);
				}

			}

		} else {
			//在本班中还担任其他科目的教师不用更新pj_team_user、yh_user_role,流程结束
		}

	}

	/**
	 * 判断某教师在某班中是否还担任其他科目的教师 true:表示担任 false：表示不担任
	 * @param list
	 * @param subjectCode
	 * @return TRUE 担任 false 不担任
	 */
	public boolean isTakeOfTeam(List<TeamTeacherVo> list, String subjectCode) {

		// 该教师在本班中是否还担任其他科目的教师 TRUE 担任  false 不担任
		boolean flag = false;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSubjectCode() != null && !"".equals(list.get(i).getSubjectCode()) && !list.get(i).getSubjectCode().equals(subjectCode)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断某教师在某校中是否还担任其他科目的教师 true:表示担任 false：表示不担任
	 * @param list
	 * @return TRUE 担任 false 不担任
	 */
	public boolean isTakeOfSchool(List<TeamTeacherVo> list, Integer teamId) {

		// 该教师在本班中是否还担任其他科目的教师 TRUE 担任  false 不担任
		boolean flag = false;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getTeamId() != null && !"".equals(list.get(i).getTeamId()) && !list.get(i).getTeamId().equals(teamId)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 *  从userRole表中移除某教师的用户角色
	 */
	public void reMoveRoleFormUserRole(Integer userId, Role role){
		if(userId != null && !"".equals(userId) && role != null){
			UserRole userRole = userRoleService.findUnique(userId, role.getId());
			if(userRole != null){
				userRoleService.remove(userRole);
			}
			//2017-11-29	移除角色时修改user表属性
			User user = new User();
			user.setId(userId);
			user.setModifyDate(new Date());
			userService.modify(user);
		}
	}

	/**
	 * @function 创建TeamUser
	 * @param teamTeacherVo
	 * @return TeamUser
	 */
	public TeamUser addTeamUser(TeamTeacherVo teamTeacherVo){
		boolean typeNotNull = teamTeacherVo.getType() != null && !"".equals(teamTeacherVo.getType());
		boolean isTeacher = false;
		boolean isMaster = false;
		boolean isParent = false;
		boolean isStudent = false;
		if(typeNotNull){
			Integer type = teamTeacherVo.getType();
			if(type == TeamUserContants.TYPE_SUBJECT_TEACHER || type.equals(TeamUserContants.TYPE_SUBJECT_TEACHER)){
				isTeacher = true;
			}else if(type == TeamUserContants.TYPE_MASTER || type.equals(TeamUserContants.TYPE_MASTER)){
				isMaster = true;
			}else if(type == TeamUserContants.TYPE_PARENT || type.equals(TeamUserContants.TYPE_PARENT)){
				isParent = true;
			}else if(type == TeamUserContants.TYPE_STUDENT || type.equals(TeamUserContants.TYPE_STUDENT)){
				isStudent = true;
			}
		}
		TeamUser teamUser = new TeamUser();
		teamUser.setCreateDate(new Date());
		teamUser.setModifyDate(new Date());
		teamUser.setIsMaster(isMaster);
		teamUser.setIsTeacher(isTeacher);
		teamUser.setIsStudent(isStudent);
		teamUser.setIsParent(isParent);
		teamUser.setName(teamTeacherVo.getName());
		teamUser.setSex(teamTeacherVo.getSex());
		teamUser.setTeamId(teamTeacherVo.getTeamId());
		teamUser.setUserId(teamTeacherVo.getUserId());
		return teamUserService.add(teamUser);
	}

	/**
	 * @function 创建UserRole
	 * @param user
	 * @param role
	 * @param priority 表示是自定义角色还是系统默认角色
	 */
	public UserRole addUserRole(User user, Role role, Integer priority){
		UserRole userRole = null;
		if (role != null && user != null) {
			UserRole ur = new UserRole();
			ur.setCreateDate(new Date());
			ur.setModifyDate(new Date());
			ur.setPriority(priority);   //用户角色优先级     默认0
			ur.setRole(role);
			ur.setUser(user);
			userRole = userRoleService.add(ur);
		}
		//2017-11-29	添加用户角色，修改User主表
		user.setModifyDate(new Date());
		userService.modify(user);
		return userRole;
	}

	/**
	 * 创建TeamTeacher
	 */
	public TeamTeacher addTeamTeacher(TeamTeacherVo teamTeacherVo){
		TeamTeacher tt = new TeamTeacher();
		tt.setDelete(false);
		tt.setCreateDate(new Date());
		tt.setModifyDate(new Date());
//		tt.setJoinDate(new Date());
//		tt.setFinishDate(new Date());
		tt.setUserId(teamTeacherVo.getUserId());
		tt.setGradeId(teamTeacherVo.getGradeId());
		tt.setName(teamTeacherVo.getName());
		tt.setSchoolYear(teamTeacherVo.getSchoolYear());
		tt.setSubjectCode(teamTeacherVo.getSubjectCode());
		tt.setSubjectName(teamTeacherVo.getSubjectName());
		tt.setTeacherId(teamTeacherVo.getTeacherId());
		tt.setTeamId(teamTeacherVo.getTeamId());
		tt.setType(teamTeacherVo.getType());
		tt.setPosition(teamTeacherVo.getPosition());
		return teamTeacherDao.create(tt);
	}

	/**
	 * 功能描述：返回班级的班主任和任课老师名单
	 * 2015-12-08
	 */
	@Override
	public List<TeamTeacher> getTeachersOfTeam(Integer teamId) {
		return this.teamTeacherDao.findTeachersOfTeam(teamId);
	}

	/**
	 * 功能描述：取得一个教师在一所学校的班主任和任课情况（即所有的TeamTeacher记录）
	 * 2015-12-08
	 */
	@Override
	public List<TeamTeacher> getMyTeamTeacher(Integer teacherId) {
		return this.teamTeacherDao.findMyTeamTeacher(teacherId, null);
	}

	/**
	 * 功能描述：取得一个教师某个学年在一所学校的班主任和任课情况
	 * 2015-12-08
	 */
	@Override
	public List<TeamTeacher> getMyTeamTeacher(Integer teacherId, String schoolYear) {
		List<TeamTeacher> teamTeacherList = new ArrayList<TeamTeacher>();
		// 传入的学年为空或者为null时返回一个空集合
		if ("".equals(schoolYear) || schoolYear == null) {
			return teamTeacherList;
		} else {
			teamTeacherList = this.teamTeacherDao.findMyTeamTeacher(teacherId, schoolYear);
		}
		return teamTeacherList;
	}

	/**
	 * 功能描述：取得一个教师当前学年在一所学校的班主任和任课情况
	 * 2015-12-08
	 */
	@Override
	public List<TeamTeacher> getMyCurrentTeamTeacher(Integer teacherId, Integer schoolId) {
		List<TeamTeacher> teamTeacherList = new ArrayList<TeamTeacher>();
		String schoolYear = "";
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
		if(schoolTermCurrent != null) {
			schoolYear = schoolTermCurrent.getSchoolYear();
		}
		//没有查询到该学校的当前学年返回一个空集合
		if("".equals(schoolYear) || schoolYear == null) {
			return teamTeacherList;
		}else {
			teamTeacherList = this.teamTeacherDao.findMyTeamTeacher(teacherId, schoolYear);
		}
		return teamTeacherList;
	}

	/**
	 * 获取当前学年
	 */
	public SchoolTermCurrent getCurrentSchoolTerm(Integer schoolId){
		List<SchoolTermCurrent> schoolTermCurrentList = this.schoolTermCurrentService.findCurrentSchoolYear(schoolId);
		SchoolTermCurrent schoolYear = null;
		if (schoolTermCurrentList.size() > 0) {
			schoolYear = schoolTermCurrentList.get(0);
		}
		return schoolYear;
	}

	//判断某教师是否存在TeamUser表中
	public TeamUser getTeamUserByUserId(Integer userId){
		TeamUser teamUser = null;
		TeamUserCondition tuCon = new TeamUserCondition();
		tuCon.setUserId(userId);
		List<TeamUser> tuList = teamUserService.findTeamUserByCondition(tuCon);
		if(tuList.size() > 0){
			teamUser = tuList.get(0);
		}
		return teamUser;
	}

	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 * 2016-01-28
	 */
	@Override
	public String removeMasterFromTeam(TeamTeacher teamTeacher, Integer appId, Integer groupId, Integer schoolId) {
		if(teamTeacher != null) {
			teamTeacher.setDelete(true);
			teamTeacher.setModifyDate(new Date());
			try {
				teamTeacher = this.teamTeacherDao.update(teamTeacher);
				//2017-11-29	移除班主任，修改teacher主表
				Teacher t = new Teacher();
				t.setId(teamTeacher.getTeacherId());
				t.setModifyDate(new Date());
				this.teacherDao.update(t);

				if(teamTeacher != null) {
					// 获取角色 在创建用户角色时用到
					Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.CLASS_MASTER);
					removeMasterFormUserRole(role, teamTeacher.getTeacherId(), teamTeacher.getUserId(), teamTeacher.getTeamId(), schoolId);
					return TeamTeacherService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				log.info("废弃记录失败");
				return TeamTeacherService.OPERATE_ERROR;
			}
		}
		return TeamTeacherService.OPERATE_FAIL;
	}

	@Override
	public String removeClassTeacherFromTeam(TeamTeacher teamTeacher, Integer appId, Integer groupId, Integer schoolId) {
		if (teamTeacher != null) {
			teamTeacher.setDelete(true);
			teamTeacher.setModifyDate(new Date());

			try {
				teamTeacher = this.teamTeacherDao.update(teamTeacher);

				if (teamTeacher != null) {
                    Teacher t = new Teacher();
                    t.setId(teamTeacher.getTeacherId());
                    t.setModifyDate(new Date());
                    this.teacherDao.update(t);

                    Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.SUBJECT_TEACHER);
                    removeTeamTeacherFormUserRole(role, teamTeacher, schoolId);
                    return TeamTeacherService.OPERATE_SUCCESS;
                }
			} catch (Exception e) {
				log.info("废弃任课教师记录失败");
				return TeamTeacherService.OPERATE_ERROR;
			}

		}
		return TeamTeacherService.OPERATE_FAIL;
	}

	@Override
	public List<TeamTeacherVo> findTeamTeacherVoByGroupBy(
			TeamTeacherCondition teamTeacherCondition) {
		return this.teamTeacherDao.findTeamTeacherVoByGroupBy(teamTeacherCondition);
	}

	@Override
	public Integer findTeamTeacherByTeamId(Integer teamId) {
		return this.teamTeacherDao.findTeamTeacherByTeamId(teamId);
	}

	@Override
	public List<TeamTeacher> findByTeamIdAndType(Integer teamId, Integer type) {
		return this.teamTeacherDao.findByTeamIdAndType(teamId, type);
	}

	@Override
	public List<TeamTeacherVo> findVoWithSubjectInfo(TeamTeacherCondition teamTeacherCondition) {
		return this.teamTeacherDao.findVoWithSubjectInfo(teamTeacherCondition);
	}

	@Override
	public List<TeamTeacher> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted) {
		return this.teamTeacherDao.findIncrementByTeacherId(schoolId, schoolYear, teacherId, modifyDate, isDeleted);
	}
	@Override
	public Map<Integer, Object> getTeacherTeachGrade(Integer userId, String schoolYear, Integer schoolId) {
		List<TeamTeacherVo> voList = teamTeacherDao.findTeacherTeachInfo(userId, schoolYear, null, null);

		/**教师任教年级信息*/
		Map<Integer, Object> gradeMap = new HashMap<Integer, Object>();
		for (TeamTeacherVo teamTeacherVo : voList) {
			 List<Team> list = teamDao.findTeamOfGrade(teamTeacherVo.getGradeId(), schoolId);
			 if(list.size()==0){
				 continue;
			 }
			gradeMap.put(teamTeacherVo.getGradeId(), teamTeacherVo.getGradeName());
		}
		return gradeMap;
	}

	@Override
	public Map<Integer, Object> getTeacherTeachTeam(Integer userId, String schoolYear, Integer gradeId) {
		List<TeamTeacherVo> voList = teamTeacherDao.findTeacherTeachInfo(userId, schoolYear, gradeId, null);

		Map<Integer, Object> teamMap = new HashMap<Integer, Object>();
		for (TeamTeacherVo teamTeacherVo : voList) {
			teamMap.put(teamTeacherVo.getTeamId(), teamTeacherVo.getTeamName());
		}
		return teamMap;
	}

	@Override
	public Map<String, Object> getTeacherTeachSubject(Integer userId, String schoolYear, Integer gradeId, Integer teamId) {
		List<TeamTeacherVo> voList = teamTeacherDao.findTeacherTeachInfo(userId, schoolYear, gradeId, teamId);

		Map<String, Object> subjectMap = new HashMap<String, Object>();
		for (TeamTeacherVo teamTeacherVo : voList) {
			subjectMap.put(teamTeacherVo.getSubjectCode(), teamTeacherVo.getSubjectName());
		}
		return subjectMap;
	}

	@Override
	public List<TeamTeacherVo> findTeamTeacherVoByConditionGroupByTeamId(
			TeamTeacherCondition teamTeacherCondition) {

		return teamTeacherDao.findTeamTeacherVoByConditionGroupByTeamId(teamTeacherCondition);
	}

	@Override
	public List<TeamTeacher> findTeamTeacherBySchoolId(Integer schoolId,Integer type,String schoolYear) {

		return teamTeacherDao.findTeamTeacherBySchoolId(schoolId, type,schoolYear);
	}

	@Override
	public List<TeamTeacherVo> findTeamOrGradeOfTeacher(Integer schoolId, String schoolYear, Integer userId, Integer teacherId, Integer type, String groupType) {
		return this.teamTeacherDao.findTeamOrGradeOfTeacher(schoolId, schoolYear, userId, teacherId, type, groupType);
	}
	@Override
	public void removeByTeacherId(Integer teacherId) {
		if(teacherId==null) {
			return;













		}
		teamTeacherDao.deleteByTeacherId(teacherId);
	}

	@Override
	public Map<String, Object> addTeamTeacherFromExcelImport(String gradeName, String teamNumber, String name, String subjectName, Integer type, Integer schoolId, String schoolYear, Integer groupId, Integer appId) {

		/**数据格式校验*/
		Map<String, Object> entity = DataImportCheck.checkTeamTeacherData(gradeName, teamNumber, name, subjectName);
		Integer status = (Integer) entity.get("status");
		if(status-2==0) {
			return entity;
		}

		Grade grade = gradeService.findGradeBySchoolIdYearAndName(schoolId, schoolYear, gradeName);
		if (grade == null) {
			entity.put("status", 2);
			entity.put("errorFiled", "grade");
			entity.put("errorInfo", "没有该年级");
			return entity;
		}
		Integer gradeId = grade.getId();
		entity.put("gradeId", gradeId);

		Team team = teamService.findBySchoolGradeIdAndName(schoolId, gradeId, gradeName, teamNumber, schoolYear);
		if (team == null) {
			entity.put("status", 2);
			entity.put("errorFiled", "team");
			entity.put("errorInfo", "没有该班级");
			return entity;
		}
		Integer teamId = team.getId();
		entity.put("teamId", teamId);

		String subjectCode = null;
		if (!"班主任".equals(subjectName)) {
            //TODO 先判断学校是否有该科目，没有则新建，有则判断该年级是否有
            List<Subject> subjects = subjectService.findSubjectByName(subjectName, schoolId, null, null);
            if (subjects != null && subjects.size() > 0) {
                List<Subject> subjectList = subjectService.findSubjectByName(subjectName, schoolId, grade.getStageCode(), null);
                if (subjectList == null || subjectList.size() == 0) {
                    entity.put("status", 2);
                    entity.put("errorFiled", "subject");
                    entity.put("errorInfo", "该年级没有该科目");
                    return entity;
                }
                subjectCode = subjectList.get(0).getCode();

            } else {
                //新增jc_subject，pj_subject，pj_subject_grade
                Subject subject = new Subject();
                subject.setSchoolId(schoolId);
                subject.setName(subjectName);
                subject.setSubjectClass("3");
				subject = subjectService.addGeneralSubject(subject);
				if (subject != null) {
					subjectCode = subject.getCode();
				} else {
					entity.put("status", 2);
					entity.put("errorFiled", "subject");
					entity.put("errorInfo", "无法创建新科目");
					return entity;
				}
            }
		}

		String alias = "";  //别名
		if (name.indexOf("(") != -1 && name.indexOf(")") != -1) {
			alias = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
		} else {
			alias = name;
		}

		TeacherCondition condition = new TeacherCondition();
		condition.setSchoolId(schoolId);
		condition.setName(alias);
		condition.setIsDelete(false);
		List<Teacher> teachers = teacherDao.findTeacherByCondition(condition);
		if (teachers == null || teachers.size() == 0) {
			entity.put("status", 2);
			entity.put("errorFiled", "name");
			entity.put("errorInfo", "没有该教师");
			entity.put("teacherName", name);
			return entity;
		}
		Integer teacherId = teachers.get(0).getId();
		Integer userId = teachers.get(0).getUserId();

		TeamTeacher teamTeacher =  new TeamTeacher();
		teamTeacher.setSchoolYear(schoolYear);
		teamTeacher.setGradeId(gradeId);
		teamTeacher.setTeamId(teamId);
		teamTeacher.setSubjectName(subjectName);
		teamTeacher.setSubjectCode(subjectCode);
		teamTeacher.setUserId(userId);
		teamTeacher.setTeacherId(teacherId);
		teamTeacher.setName(alias);
		teamTeacher.setType(type);

		if (Integer.valueOf(type).equals(1)) {
			addOrModifyClassTeacher(appId, groupId, String.valueOf(gradeId), String.valueOf(teamId), String.valueOf(teacherId));
		} else if (Integer.valueOf(type).equals(2)) {
			addOrModifyClassRoomTeacherOfTeam(appId, groupId, teamTeacher);
		}


		entity.put("teacherName", name);
		entity.put("phone", teachers.get(0).getMobile());

		TeamTeacherCondition ttCondition = new TeamTeacherCondition();
		ttCondition.setGradeId(gradeId);
		ttCondition.setTeamId(teamId);
		ttCondition.setTeamId(teamId);
		ttCondition.setType(type);
		ttCondition.setSubjectCode(subjectCode);
		List<TeamTeacher> teamTeacherList = teamTeacherDao.findTeamTeacherByCondition(ttCondition, null, null);
		if (teamTeacherList != null && teamTeacherList.size() > 0) {
			entity.put("subjectTeacherId", teamTeacherList.get(0).getId());
		}

		return entity;
	}

	/**
	 * 获取学生的接送信息
	 *
	 * @param teamId
	 * @param gradeId
	 * @param schoolId
	 * @return
	 */
	@Override
	public List<Map<String, String>> findStuInfo(Integer teamId, Integer gradeId, Integer schoolId) {
		return teamTeacherDao.findStuInfo(teamId, gradeId, schoolId);
	}


	@Override
	public List<TeamTeacher> findTeamTeacherByConditionIncludeDelete(
			TeamTeacherCondition teamTeacherCondition, Page page, Order order) {
		return teamTeacherDao.findTeamTeacherByConditionIncludeDelete(teamTeacherCondition, page, order);
	}

	@Override
	public String removeTeamTeacherFromTeam(TeamTeacher teamTeacher,
			Integer appId, Integer groupId, Integer schoolId) {
		if(teamTeacher != null){
			teamTeacher.setDelete(true);
			teamTeacher.setModifyDate(new Date());
			try {
				teamTeacher = this.teamTeacherDao.update(teamTeacher);
				if(teamTeacher != null){
					if(teamTeacher.getType().equals(TeamTeacherContants.TYPE_SUBJECT_TEACHER)){
						Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.SUBJECT_TEACHER);
						removeTeamTeacherFormUserRole(role, teamTeacher, schoolId);
						return TeamTeacherService.OPERATE_SUCCESS;
					}else if(teamTeacher.getType().equals(TeamTeacherContants.TYPE_MASTER)){
						Role role = roleService.findUniqueInGroup(appId, groupId, SysDefRole.CLASS_MASTER);
						removeMasterFormUserRole(role, teamTeacher.getTeacherId(), teamTeacher.getUserId(), teamTeacher.getTeamId(), schoolId);
						return TeamTeacherService.OPERATE_SUCCESS;
					}
				}
			} catch (Exception e) {
				log.info("废弃记录失败");
				return TeamTeacherService.OPERATE_ERROR;
			}
		}
		return TeamTeacherService.OPERATE_FAIL;
	}

	@Override
	public List<Map<String, Object>> findNotSendSeewo() {
		return teamTeacherDao.findNotSendSeewo();
	}

	@Override
	public boolean updateSeewoStatus(Integer[] ids) {
		return teamTeacherDao.updateSeewoStatus(ids)>0;
	}

	@Override
	public boolean userIsTeamManager(Integer userId) {
		return basicSQLService.findUniqueLong("select exists(select 1 from pj_teacher t left join pj_team_teacher tt on tt.teacher_id=t.id  where t.user_id="+userId+" and tt.type=1 and t.is_delete=0 ) as c ")>0;
	}
}