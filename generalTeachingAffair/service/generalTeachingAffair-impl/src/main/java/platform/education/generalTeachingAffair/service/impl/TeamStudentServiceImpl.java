package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.contants.StudentAlterationContants;
import platform.education.generalTeachingAffair.contants.TeamUserContants;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.dao.TeamStudentDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamStudentServiceImpl implements TeamStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TeamStudentDao teamStudentDao;
	/**
	 * 班级（2015-12-23）
	 */
	private TeamDao teamDao;

	/**
	 * 学生（2015-12-23）
	 */
//	private StudentService studentService;
	
	private StudentDao studentDao;
	
	/**
	 * 学生家长（2015-12-23）
	 */
	private ParentStudentService parentStudentService;
	
	/**
	 * 班级用户（2015-12-24）
	 */
	private TeamUserService teamUserService;
	
	/**
	 * 家长（2015-12-28）
	 */
	private ParentService parentService;
	
	/**
	 * 学生变更历史表（2016-01-29）
	 */
	private StudentAlterationService studentAlterationService;
	
	/**
	 * 当前学期
	 */
	@Resource
	private SchoolTermCurrentService schoolTermCurrentService;
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setSchoolTermCurrentService(
			SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}
	
	public void setTeamStudentDao(TeamStudentDao teamStudentDao) {
		this.teamStudentDao = teamStudentDao;
	}
	
//	public void setTeamService(TeamService teamService) {
//		this.teamService = teamService;
//	}
	
	public void setParentStudentService(ParentStudentService parentStudentService) {
		this.parentStudentService = parentStudentService;
	}
	
	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public void setParentService(ParentService parentService) {
		this.parentService = parentService;
	}
	
	public void setStudentAlterationService(
			StudentAlterationService studentAlterationService) {
		this.studentAlterationService = studentAlterationService;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public TeamStudent findTeamStudentById(Integer id) {
		try {
			return teamStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TeamStudent add(TeamStudent teamStudent) {
		return teamStudentDao.create(teamStudent);
	}

	@Override
	public TeamStudent modify(TeamStudent teamStudent) {
		return teamStudentDao.update(teamStudent);
	}
	
	@Override
	public void remove(TeamStudent teamStudent) {
		try{
			teamStudentDao.delete(teamStudent);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
		 
	}
	@Override
	public List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order)
	{
		return teamStudentDao.findTeamStudentByCondition(teamStudentCondition, page, order);
	}
	
	@Override
	public List<TeamStudent> findTeamStudentByConditionForTransfer(TeamStudentCondition teamStudentCondition, Page page, Order order){
		return teamStudentDao.findTeamStudentByConditionForTransfer(teamStudentCondition, page, order);
	}

	@Override
	public TeamStudent findUnique(Integer teamId, Integer studentId) {
		try {
			return teamStudentDao.findUnique(teamId, studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public TeamStudent findUnique2(Integer teamId, Integer studentId,String year) {
		try {
			return teamStudentDao.findUnique2(teamId, studentId,year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TeamStudentVo> findTeamStudentByConditionMore(
			TeamStudentCondition teamStudentCondition, Page page, Order order) {
		return teamStudentDao.findTeamStudentByConditionMore(teamStudentCondition, page, order);
	}

	/**
	 * 功能描述：通过id关联查询出该学生所属的学年、年级、班级
	 */
	@Override
	public TeamStudentVo findTeamStudentByIdMore(Integer id, Integer schoolId) {
		try {
			return teamStudentDao.findByIdMore(id, schoolId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	/**
	 * 功能描述：关联查询出学生的个人信息
	 */
	@Override
	public List<TeamStudentVo> findTeamStudentByConditionStudent(
			TeamStudentCondition teamStudentCondition, Page page, Order order) {
		return teamStudentDao.findTeamStudentByConditionStudent(teamStudentCondition, page, order);
	}
	/**
	 * 功能描述：校车系统查询分配的学生信息
	 */
	@Override
	public List<TeamStudentVo> findTeamStudentByConditionStudentSchoolBus(
			TeamStudentCondition teamStudentCondition, Page page, Order order) {
		return teamStudentDao.findTeamStudentByConditionStudentSchoolBus(teamStudentCondition, page, order);
	}

	/**
	 * 功能描述：通过条件（学校ID，学校当前学年等条件）查询班级学生
	 */
	@Override
	public List<TeamStudent> findCurrentTeamStudentByCondition(
			TeamStudentCondition teamStudentCondition, Page page, Order order) {
		return teamStudentDao.findCurrentTeamStudentByCondition(teamStudentCondition, page, order);
	}
	
	/**
	 * 统计每个班的人数
	 */
	@Override
	public List<TeamStudentVo> findTeamNum(){
		List<TeamStudentVo> tsList = teamStudentDao.findTeamNum();
		return tsList;
	}

	/**
	 * 获得总数
	 * 2015-12-25
	 */
	@Override
	public Long count() {
		return this.teamStudentDao.count(null);
	}
	
	/**
	 * 功能描述：通过条件获得总数
	 * 2015-12-25
	 */
	@Override
	public Long count(TeamStudentCondition teamStudentCondition) {
		return this.teamStudentDao.count(teamStudentCondition);
	}
	
	/*=========================学生更换班级开始（2015-12-28）===============================*/
	
	@Override
	public String moveStudentToTeam(Integer studentId, Integer oldTeamId, Integer newTeamId) {
		
		try {
			
			Boolean oldIsNull = (oldTeamId == null || "".equals(oldTeamId)) ? true : false;
			Boolean newIsNull = (newTeamId == null || "".equals(newTeamId)) ? true : false;
			if(!oldIsNull && !newIsNull) {
				//将学生从原班级中移除
				removeStudentFromTeam(studentId, oldTeamId);
				
				//将学生加入新班级中
				String message = assignStudentToTeam(studentId, newTeamId);

				/* Modified on 2017-08-04  更改说明：assignStudentToTeam() 方法中，判断学生是否已经存在当前班级，如果已经存在，不再重复添加记录，意味着学生变更类型，没有发生改变，此时学生变更历史表不需要添加新的记录 */
				addStudentAlterationForOperateType(message, studentId, oldTeamId, newTeamId, StudentAlterationContants.TYPE_DIAOBAN);
//				//将学生加入学生变更历史表
//				if(message.equals(TeamStudentService.OPERATE_EXIST)) {
//					//学生已经存在，班级学生没有添加学生的相应记录，学生没有产生变更记录
//					return TeamStudentService.OPERATE_SUCCESS;
//				}else if (message.equals(TeamStudentService.OPERATE_SUCCESS)) {
//					//学生的班级记录添加成功，产生变更记录
//					addStudentAlteration(studentId, oldTeamId, newTeamId, StudentAlterationContants.TYPE_DIAOBAN);
//				} else if (message.equals(TeamStudentService.OPERATE_FAIL)) {
//					return TeamStudentService.OPERATE_FAIL;
//				}

			}else {
				return TeamStudentService.OPERATE_FAIL;
			}
			
		} catch (Exception e) {
			log.info("学生更换班级异常");
			e.printStackTrace();
			return TeamStudentService.OPERATE_ERROR;
		}
		
		return TeamStudentService.OPERATE_SUCCESS;
	}
	
	/*=========================学生更换班级结束（2015-12-28）===============================*/
	
	
	/*=========================学生迁入班级开始（2015-12-23）===============================*/
	
	/**
	 * 功能描述：学生加入班级
	 * 2015-12-23
	 * @param studentId
	 * @param teamId
	 * 
	 * @modify ：2016-3-11添加学生变更信息的添加
	 * 
	 * @return
	 */
	@Override
	public String assignStudentToTeam(Integer studentId, Integer teamId) {
		try {
			Team team = teamDao.findById(teamId);
			Student student = studentDao.findById(studentId);
			if(team != null && student != null) {
				
				//学生迁入班级A流程
				//判断学生是否存在当前班级中
				Boolean isStudentInTeam = isStudentInTeam(studentId, teamId);
				
				if(!isStudentInTeam) {
					//学生当前班级中不存在记录，添加相应记录
					//将学生加入班级中（pj_team_student）
					addTeamStudent(team, student);
					
					//学生迁入班级B流程
					//判断TeamUser是否已经存在此记录，不存在则添加，存在则更新相对应的状态
					addOrUpdateTeamUser(teamId, student.getUserId(), student.getName(), student.getSex(), TeamUserContants.TYPE_STUDENT);
					
					//取学生所有家长，判断TeamUser是否已经存在此记录，不存在则添加，存在则更新相对应的状态
					List<ParentStudent> parentStudentList = findParentsOfStudent(student.getUserId());
					for(ParentStudent parentStudent : parentStudentList) {
						Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
						if(parent != null) {
							//判断TeamUser是否已经存在此记录，不存在则添加，存在则更新相对应的状态
							addOrUpdateTeamUser(teamId, parent.getUserId(), parent.getName(), "", TeamUserContants.TYPE_PARENT);
						}
					}
					
					//学生迁入班级C流程
					//更新学生所在的当前班级
					updateStudentTeam(student, team);
					
					//重新获得班级学生人数
					updateStudentCount(teamId);
					
					//将学生加入学生变更历史表  2016-3-11添加
					//此处不合理
//					addStudentAlteration(studentId, null, teamId, StudentAlterationContants.TYPE_ZHUANRU);
				}else {
					//学生已经存在当前班级中，退出
					return TeamStudentService.OPERATE_EXIST;
				}
				
			}else {
				return TeamStudentService.OPERATE_FAIL;
			}
		} catch (Exception e) {
			log.info("学生加入班级异常");
			e.printStackTrace();
			return TeamStudentService.OPERATE_ERROR;
		} 
		return TeamStudentService.OPERATE_SUCCESS;
	}
	
	/**
	 * 功能描述：将学生迁入班级中（pj_team_student）(2015-12-23)
	 * @param team
	 * @param student
	 * @return
	 */
	public TeamStudent addTeamStudent(Team team, Student student) {
		TeamStudent teamStudent = new TeamStudent();
		teamStudent.setGradeId(team.getGradeId());
		teamStudent.setTeamId(team.getId());
		teamStudent.setStudentId(student.getId());
		teamStudent.setUserId(student.getUserId());
		teamStudent.setName(student.getName());
		teamStudent.setJoinDate(new Date());
		teamStudent.setRecordDate(new Date());
		teamStudent.setCreateDate(new Date());
		teamStudent.setModifyDate(new Date());
		teamStudent.setIsDelete(false);
		teamStudent.setInState(true);
		teamStudent = this.teamStudentDao.create(teamStudent);
		return teamStudent;
	}
	
	/**
	 * 功能描述：判断TeamUser是否已经存在此记录，不存在则添加，存在则更新相对应的状态
	 * 2015-12-28
	 * @param teamId
	 * @param userId
	 * @param name
	 * @param sex
	 * @param type
	 * @return
	 */
	public TeamUser addOrUpdateTeamUser(Integer teamId, Integer userId, String name, String sex, Integer type) {
		TeamUser teamUserTemp = this.teamUserService.findUnique(teamId, userId);
		TeamUser teamUser = new TeamUser();
		if(teamUserTemp != null) {
			teamUser.setId(teamUserTemp.getId());
			if(type.equals(TeamUserContants.TYPE_MASTER)) {
				teamUser.setIsMaster(true);
			}else if(type.equals(TeamUserContants.TYPE_SUBJECT_TEACHER)) {
				teamUser.setIsTeacher(true);
			}else if(type.equals(TeamUserContants.TYPE_STUDENT)) {
				teamUser.setIsStudent(true);
			}else if(type.equals(TeamUserContants.TYPE_PARENT)) {
				teamUser.setIsParent(true);
			}
			teamUser.setModifyDate(new Date());
			teamUser = teamUserService.modify(teamUser);
		}else {
			Boolean isMaster = false;
			Boolean isTeacher = false;
			Boolean isStudent = false;
			Boolean isParent = false;
			
			if(type.equals(TeamUserContants.TYPE_MASTER)) {
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
		}
		return teamUser;
	}

	/**
	 * 功能描述：取得学生所有家长
	 * @param studentUserId
	 * @return
	 */
	public List<ParentStudent> findParentsOfStudent(Integer studentUserId) {
		ParentStudentCondition condition = new ParentStudentCondition();
		condition.setStudentUserId(studentUserId);
		condition.setIsDelete(false);
		List<ParentStudent> parentStudentList = this.parentStudentService.findParentStudentByCondition(condition);
		return parentStudentList;
	}
	
	/**
	 * 功能描述：更新学生所在的当前班级
	 * @param student
	 * @param team
	 * @return
	 */
	public Student updateStudentTeam(Student student, Team team) {
		Student studentTemp = new Student();
		studentTemp.setId(student.getId());
		studentTemp.setTeamId(team.getId());
		studentTemp.setTeamName(team.getName());
		studentTemp.setModifyDate(new Date());
		studentTemp = studentDao.update(studentTemp);
		return studentTemp;
	}
	
	/**
	 * 功能描述：判断学生是否在班级中
	 * 2015-12-23
	 * @param studentId
	 * @param teamId
	 * @return
	 */
	public Boolean isStudentInTeam(Integer studentId, Integer teamId) {
		TeamStudent teamStudent = this.teamStudentDao.findUnique(teamId, studentId);
		Boolean flag = false;
		if(teamStudent != null) {
			flag = true;
		}
		return flag;
	}
	
	/*=========================学生迁入班级结束（2015-12-23）===============================*/
	
	/*=========================学生迁出班级开始（2015-12-23）===============================*/
	
	/**
	 * 功能描述：将学生从班级移除
	 * 2015-12-23
	 * @param studentId
	 * @param teamId
	 * @return
	 */
	public String removeStudentFromTeam(Integer studentId, Integer teamId) {
		TeamStudent teamStudent = new TeamStudent();
		Student student = this.studentDao.findById(studentId);
		try {
			
			if(student != null) {
				//学生迁出班级A流程
				//获得学生当前所在的班级记录
				teamStudent = this.teamStudentDao.findUnique(teamId, studentId);
				if(teamStudent != null) {
					teamStudent.setInState(false);
					teamStudent.setFinishDate(new Date());
					teamStudent.setModifyDate(new Date());
					teamStudent = this.teamStudentDao.update(teamStudent);
				}
				
				//学生迁出班级B流程
				//更新TeamUser中学生状态为false 即 isStudent = false
				updateTeamUser(teamId, student.getUserId(), TeamUserContants.TYPE_STUDENT);
				
				//获得该学生的所有家长，判断家长是否还有其他子女也在这个班中，无则更新isParent = false
				findParentsOfStudent(student.getUserId(), teamId, student.getSchoolId());
				
				//学生迁出班级C流程
				//判断student.teamId是否等于teamId
				if(teamId.equals(student.getTeamId())) {
					//取消学生当前班级（即更新无班级状态）
					updateStudentTeamIsNull(studentId);
					
					//重新获得班级学生人数
					updateStudentCount(teamId);
				}
				
			}else {
				return TeamStudentService.OPERATE_FAIL;
			}
		} catch (Exception e) {
			log.info("学生从班级移除异常");
			e.printStackTrace();
			return TeamStudentService.OPERATE_ERROR;
		}
		return TeamStudentService.OPERATE_SUCCESS;
	}
	
	/**
	 * 功能描述：获得该学生所有家长记录
	 * 2015-12-23
	 * @param studentUserId
	 * @return
	 */
	public List<ParentStudent> findParentsOfStudent(Integer studentUserId, Integer teamId, Integer schoolId) {
		ParentStudentCondition condition = new ParentStudentCondition();
		condition.setStudentUserId(studentUserId);
		condition.setIsDelete(false);
		List<ParentStudent> parentStudentList = this.parentStudentService.findParentStudentByCondition(condition);
		for(ParentStudent parentStudent : parentStudentList) {
			Boolean isParentInTeam = isParentInTeam(parentStudent, studentUserId, schoolId, teamId);
			//如果该家长除了该子女外在本班中没有其他子女了，则更新班级用户中isParent = false
			if(!isParentInTeam) {
				updateTeamUser(teamId, parentStudent.getParentUserId(), TeamUserContants.TYPE_PARENT);
			}
		}
		return parentStudentList;
	}
	
	/**
	 * 功能描述：获得家长的其他子女，判断该家长除了指定的某个子女外在本班中是否还有其他子女
	 * 2015-12-24
	 * @param parentStudent
	 * @param studentUserId
	 * @param schoolId
	 * @param teamId
	 * @return
	 */
	public Boolean isParentInTeam(ParentStudent parentStudent, Integer studentUserId, Integer schoolId, Integer teamId) {
		Boolean isParentInTeam = false;
		Integer parentUserId = parentStudent.getParentUserId();
		if(parentUserId != null) {
			ParentStudentCondition condition = new ParentStudentCondition();
			condition.setParentUserId(parentUserId);
			condition.setIsDelete(false);
			List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(condition);
			for(ParentStudent ps : psList) {
				if(ps.getStudentUserId() != null && !ps.getStudentUserId().equals(studentUserId)) {
					Student student = this.studentDao.findOfUser(schoolId, ps.getStudentUserId());
					if(student != null) {
						TeamStudent teamStudent = this.teamStudentDao.findUnique(teamId, student.getId());
						if(teamStudent != null) {
							isParentInTeam = true;
							break;
						}
					}
				}
			}
		}
		return isParentInTeam;
	}
	
	/**
	 * 功能描述：移除班级用户记录（更新相对应的状态）
	 * 2015-12-24
	 * @param teamId
	 * @param userId
	 * @param type
	 * @return
	 */
	public TeamUser updateTeamUser(Integer teamId, Integer userId, Integer type) {
		TeamUser teamUser = this.teamUserService.findUnique(teamId, userId);
		if(teamUser != null) {
			if(type.equals(TeamUserContants.TYPE_MASTER)) {
				teamUser.setIsMaster(false);
			}else if(type.equals(TeamUserContants.TYPE_SUBJECT_TEACHER)) {
				teamUser.setIsTeacher(false);
			}else if(type.equals(TeamUserContants.TYPE_STUDENT)) {
				teamUser.setIsStudent(false);
			}else if(type.equals(TeamUserContants.TYPE_PARENT)) {
				teamUser.setIsParent(false);
			}
			teamUser.setModifyDate(new Date());
			teamUser = this.teamUserService.modify(teamUser);
		}
		return teamUser;
	}
	
	/**
	 * 功能描述：更新学生所在的班级，移除班级后，学生对应的teamID设为null
	 * 2015-12-24
	 * @param studentId
	 * @return
	 */
	public Student updateStudentTeamIsNull(Integer studentId) {
		Student student = this.studentDao.findById(studentId);
		student = this.studentDao.updateStudentSetTeamIsNull(student);
		return student;
	}
	
	/**
	 * 功能描述：重新获得班级人员总数
	 * 2015-12-25
	 * @param teamId
	 * @return
	 */
	public Team updateStudentCount(Integer teamId) {
		Team team = this.teamDao.findById(teamId);
		try {
			if(team != null) {
				TeamStudentCondition condition = new TeamStudentCondition();
				condition.setTeamId(teamId);
				condition.setInState(true);   //迁入/迁出状态
				condition.setIsDelete(false);
				Long studentCount = this.teamStudentDao.count(condition);
				team.setStudentCount(String.valueOf(studentCount));
				team.setModifyDate(new Date());
				team = this.teamDao.update(team);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获得班级人员总数异常");
		}
		return team;
	}
	
	/**
	 * 功能描述：该方法适用于调班情况，添加学生变更表记录
	 * 2016-01-29
	 * @param studentId
	 * @param oldTeamId
	 * @param newTeamId
	 * @param alterType
	 * @return
	 */
	@Override
	public StudentAlteration addStudentAlteration(Integer studentId, Integer oldTeamId, Integer newTeamId, String alterType) {
		StudentAlteration studentAlteration = new StudentAlteration();
		Student student = new Student();
		Team oldTeam = null;
		Team newTeam = null;
		if(studentId != null) {
			student = this.studentDao.findById(studentId);
		}
		
		if(oldTeamId != null) {
			oldTeam  = this.teamDao.findById(oldTeamId);
		}
		
		if(newTeamId != null) {
			newTeam = this.teamDao.findById(newTeamId);
		}
		
		if(student != null) {
			studentAlteration.setSchoolId(student.getSchoolId());
			studentAlteration.setUserId(student.getUserId());
			studentAlteration.setStudentId(student.getId());
		}
		if(newTeam != null) {
			studentAlteration.setTeamId(newTeamId);
			studentAlteration.setAlterTo(newTeam.getFullName());
		}
		if(oldTeam != null) {
			studentAlteration.setAlterFrom(oldTeam.getFullName());
		}
//		SchoolTermCurrent schoolTermCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(student.getSchoolId());
		
		if(student != null && student.getSchoolId() != null){
			List<SchoolTermCurrent> schoolTermCurrentList = schoolTermCurrentService.findCurrentSchoolYear(student.getSchoolId());
			if(schoolTermCurrentList != null && schoolTermCurrentList.size() > 0) {
				studentAlteration.setSchoolYear(schoolTermCurrentList.get(0).getSchoolYear());
			}
		}
		
		studentAlteration.setAlterType(alterType);
		studentAlteration.setAlterDate(new Date());
//		studentAlteration.setComment(comment);  //备注
		studentAlteration.setModifyDate(new Date());
		studentAlteration.setCreateDate(new Date());
		studentAlteration.setIsDelete(false);
		studentAlteration = studentAlterationService.add(studentAlteration);
		return studentAlteration;
	}


	private String addStudentAlterationForOperateType(String operate_type, Integer studentId, Integer oldTeamId, Integer newTeamId, String alterType) {
		//将学生加入学生变更历史表
		if(operate_type.equals(TeamStudentService.OPERATE_EXIST)) {
			//学生已经存在，班级学生没有添加学生的相应记录，学生没有产生变更记录
			return TeamStudentService.OPERATE_SUCCESS;
		}else if (operate_type.equals(TeamStudentService.OPERATE_SUCCESS)) {
			//学生的班级记录添加成功，产生变更记录
			addStudentAlteration(studentId, oldTeamId, newTeamId, alterType);
		} else if (operate_type.equals(TeamStudentService.OPERATE_FAIL)) {
			return TeamStudentService.OPERATE_FAIL;
		}
		return TeamStudentService.OPERATE_SUCCESS;
	}

	/*=========================学生迁出班级结束（2015-12-23）===============================*/
	
	/**
	 * 功能描述：通过班级ID(teamId)获得一个班的当前在读的学生名单
	 * 2016-01-12
	 */
	@Override
	public List<TeamStudentVo> getTeamStudentsByTeamId(Integer teamId) {
		if(teamId == null) {
			return null;
		}
		return this.teamStudentDao.findTeamStudentsByTeamId(teamId);
	}

	/**
	 * 功能描述：升级并分班（适用于在校生分班业务）
	 * 2017-07-28
	 * @param studentId
	 * @param newTeamId
	 * @param oldTeamId
	 * @return
	 */
	@Override
	public String upgradeAndAssignToTeam(Integer studentId, Integer newTeamId, Integer oldTeamId) {
		try {
			Boolean oldIsNull = (oldTeamId == null || "".equals(oldTeamId)) ? true : false;
			Boolean newIsNull = (newTeamId == null || "".equals(newTeamId)) ? true : false;
			if(!oldIsNull && !newIsNull) {

				//将学生加入新班级中
				String message = assignStudentToTeam(studentId, newTeamId);

				//将学生加入学生变更历史表
				addStudentAlterationForOperateType(message, studentId, oldTeamId, newTeamId, StudentAlterationContants.TYPE_SHENGJI);
//				if(message.equals(TeamStudentService.OPERATE_EXIST)) {
//					//学生已经存在，班级学生没有添加学生的相应记录，学生没有产生变更记录
//					return TeamStudentService.OPERATE_SUCCESS;
//				}else if (message.equals(TeamStudentService.OPERATE_SUCCESS)) {
//					//学生的班级记录添加成功，产生变更记录
//					addStudentAlteration(studentId, oldTeamId, newTeamId, StudentAlterationContants.TYPE_SHENGJI);
//				} else if (message.equals(TeamStudentService.OPERATE_FAIL)) {
//					return TeamStudentService.OPERATE_FAIL;
//				}

			}else {
				return TeamStudentService.OPERATE_FAIL;
			}

		} catch (Exception e) {
			log.info("学生升级并分班异常");
			e.printStackTrace();
			return TeamStudentService.OPERATE_ERROR;
		}

		return TeamStudentService.OPERATE_SUCCESS;
	}

	@Override
	public List<TeamStudent> findByTeamId(Integer teamId) {
		return this.teamStudentDao.findByTeamId(teamId);
	}
	@Override
	public List<TeamStudent> findByTeamId2(Integer teamId,String stats) {
		return this.teamStudentDao.findByTeamId2(teamId,stats);
	}

	@Override
	public List<TeamStudent> findByTeamIds(Integer[] teamIds) {
		// TODO Auto-generated method stub
		if(teamIds.length==0){
			return new ArrayList<TeamStudent>();
		}
		return this.teamStudentDao.findByTeamIds(teamIds);
	}

	@Override
	public List<TeamStudentVo> findTeamStudentVoByTeamIds(Integer[] teamIds) {
		
		return teamStudentDao.findTeamStudentVoByTeamIds(teamIds);
	}


@Override
	public List<TeamStudentVo> findTeamStudentsByTeamId(Integer teamId) {
		
		return teamStudentDao.findTeamStudentsByTeamId(teamId);
	}
	@Override
	public Integer findGradeStudentCountByGradeId(Integer gradeId) {
		return teamStudentDao.findGradeStudentCountByGradeId(gradeId);
	}

	@Override
	public List<TeamStudentVo> findTeamStudentVo(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId) {
		return this.teamStudentDao.findTeamStudentVo(schoolId, schoolYear, gradeId, teamId);
	}

	@Override
	public TeamStudent findByTeamIdAndNumber(Integer teamId, Integer number) {
		if(teamId==null || number==null) {
			return null;
		}
		TeamStudentCondition condition = new TeamStudentCondition();
		condition.setTeamId(teamId);
		condition.setNumber(number);
		List<TeamStudent> result = teamStudentDao.findTeamStudentByCondition(condition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Integer findMaxTeamNumberByTeamId(Integer teamId) {
		if(teamId==null) {
			return 0;
		} else {
			Integer number = teamStudentDao.findMaxTeamNumberByTeamId(teamId);
			if(number==null) {
				return 0;
			}
			return number;
		}
	}

	@Override
	public void deleteByTeamId(Integer teamId) {
		if(teamId==null) {
			return;
		}
		teamStudentDao.deleteByTeamId(teamId);
	}

}
