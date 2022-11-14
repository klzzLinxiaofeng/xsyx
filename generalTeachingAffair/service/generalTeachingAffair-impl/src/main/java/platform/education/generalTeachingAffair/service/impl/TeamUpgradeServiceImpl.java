package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.contants.StudentAlterationContants;
import platform.education.generalTeachingAffair.contants.TeamUserContants;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.StudentAlterationCondition;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeamUpgradeServiceImpl implements TeamUpgradeService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private GradeService gradeService;
	
	private TeamService teamService;
	
	private TeamStudentService teamStudentService;
	
	private TeamTeacherService teamTeacherService;
	
	private TeamUserService teamUserService;
	
	private StudentService studentService;
	
	private StudentAlterationService studentAlterationService;
	
	private SchoolUserService schoolUserService;
	
	private ParentStudentService parentStudentService;
	
	public void setLog(Logger log) {
		this.log = log;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}

	public void setTeamTeacherService(TeamTeacherService teamTeacherService) {
		this.teamTeacherService = teamTeacherService;
	}

	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setStudentAlterationService(
			StudentAlterationService studentAlterationService) {
		this.studentAlterationService = studentAlterationService;
	}

	public void setSchoolUserService(SchoolUserService schoolUserService) {
		this.schoolUserService = schoolUserService;
	}

	public void setParentStudentService(ParentStudentService parentStudentService) {
		this.parentStudentService = parentStudentService;
	}

	@Override
	public Map upgradeTeam(Integer newGradeId, Integer oldTeamId) {
		Map map = new LinkedHashMap();
		boolean isSuccess = true;
		//B1 获取原班级记录 
		Team oldTeam = teamService.findTeamById(oldTeamId);
		if(oldTeam == null){
			log.info("原班级数据不存在，跳过该班级（teamId：" + oldTeamId +"），升级下一个班级");
			return null;
		}
		
		//B2 判断是否存在新班级，存在则直接结束
		Grade newGrade = gradeService.findGradeById(newGradeId);
		if(newGrade == null){
			newGrade = gradeService.createUpgradeGrade(oldTeam.getGradeId());
		}
		String code = newGrade.getCode() + "-" + oldTeam.getTeamNumber(); 
		String name = newGrade.getName() + "(" + oldTeam.getTeamNumber() + ")班";
		Team newTeam = teamService.findTeamByCode(code);
		
		if (newTeam != null) {
			log.info("新班级数据已经存在，跳过该班级（teamId：" + newTeam.getId() 
					+ "  teamName：" + newTeam.getName() + " ），升级下一个班级");
			isSuccess = false;
		} else {
			//B3 不存在创建新班级
			newTeam = createTeam(oldTeam, newGrade, code, name);
			Integer newTeamId = newTeam.getId();
			
			//B4 取出原班级的所有学生
			List<Student> studentList = studentService.findStudentByTeamId(oldTeamId);
			List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(oldTeamId);
			
			//B5 将原班级学生迁入新班级
			//B5-1 更新student表中的team_id和team_name字段
			updateStudent(newTeam, studentList);
			
			//B5-2 创建team_student新记录
			createTeamStudent(newTeam, teamStudentList);
			
			//B5-3 创建team_teacher中班主任的记录
			createClassMaster(newGradeId, oldTeamId, newTeamId, newGrade.getSchoolYear());
			
			//B5-4 创建team_user新纪录，
			createTeamUser(oldTeamId, newTeamId);
			
			//B6 创建学生变更历史记录
			createStudentAlteration(oldTeam, newTeam, newTeamId, studentList,StudentAlterationContants.TYPE_SHENGJI);
			
			//C 生成新的班级账号
			//createTeamAccount(oldTeamId, oldTeam.getSchoolId(), newTeam);
		}
		
		map.put("isSuccess", isSuccess);
		map.put("team", newTeam);
		return map;
	}

	//根据原班级创建新年级
	private Team createTeam(Team oldTeam, Grade newGrade, String code, String name) {
		Team newTeam = new Team();
		newTeam.setSchoolId(newGrade.getSchoolId());
		newTeam.setGradeId(newGrade.getId());
		newTeam.setFullName(name);
		newTeam.setName(name);
		newTeam.setTeamNumber(oldTeam.getTeamNumber());
		newTeam.setCode(code);
		newTeam.setCode2(oldTeam.getCode2());
		newTeam.setTeamType(oldTeam.getTeamType());
		newTeam.setTeamProperty(oldTeam.getTeamProperty());
		newTeam.setSchoolYear(newGrade.getSchoolYear());
		newTeam.setMemberCount(oldTeam.getMemberCount());
		newTeam.setBeginDate(new Date());
		newTeam.setFinishDate(new Date());
		newTeam.setIsDelete(false);
		newTeam.setCreateDate(new Date());
		newTeam.setModifyDate(new Date());
		newTeam = teamService.add(newTeam);
		return newTeam;
	}
	
	//升级student表
	private void updateStudent(Team newTeam, List<Student> studentList) {
		if(studentList != null && studentList.size() > 0){
			log.info("正在更新student表中的数据");
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					log.info("正在升级学生：" + student.getName() + " 的数据");
					student.setTeamId(newTeam.getId());
					student.setTeamName(newTeam.getName());
					studentService.modify(student);
				}
			}
		}
	}	
	
	//升级team_student表
	private void createTeamStudent(Team newTeam, List<TeamStudent> teamStudentList) {
		if(teamStudentList != null && teamStudentList.size() > 0 && newTeam != null){
			log.info("正在升级 team_student表中的数据");
			for(TeamStudent teamStudent : teamStudentList){
				TeamStudent ts = teamStudentService.findUnique(newTeam.getId(), teamStudent.getStudentId());
				if(ts != null){
					log.info("正在升级 班级学生 " + teamStudent.getName() + " 的数据(发现已存在数据，进行修改)");
					ts.setTeamId(newTeam.getId());
					ts.setGradeId(newTeam.getGradeId());
					ts.setModifyDate(new Date());
					teamStudentService.modify(ts);
				}else{
					log.info("正在升级 班级学生 " + teamStudent.getName() + " 的数据(新增班级学生数据)");
					teamStudent.setGradeId(newTeam.getGradeId());
					teamStudent.setTeamId(newTeam.getId());
					teamStudent.setJoinDate(new Date());;
					teamStudent.setCreateDate(new Date());
					teamStudent.setModifyDate(new Date());
					teamStudent.setId(null);
					teamStudentService.add(teamStudent);
				}
			}
		}
	}	
	
	//升级team_teacher的班主任
	private void createClassMaster(Integer newGradeId, Integer oldTeamId,
			Integer newTeamId, String schoolYear) {
		List<TeamTeacher> teamTeacherList = teamTeacherService.findByTeamIdAndType(oldTeamId, TeamUserContants.TYPE_MASTER);
		if(teamTeacherList != null && teamTeacherList.size() > 0){
			TeamTeacher teamTeacher = teamTeacherList.get(0);
			teamTeacher.setId(null);
			teamTeacher.setGradeId(newGradeId);
			teamTeacher.setTeamId(newTeamId);
			teamTeacher.setSchoolYear(schoolYear);
			teamTeacher.setCreateDate(new Date());
			teamTeacher.setModifyDate(new Date());
			teamTeacherService.add(teamTeacher);
		}else{
			log.info("原班级（teamId：" + oldTeamId +"）没有设置班主任，跳过该流程");
		}
	}	
	
	//升级team_user表
	private void createTeamUser(Integer oldTeamId, Integer newTeamId) {
		List<TeamUser> teamUserList = teamUserService.findTeamUserOfAll(oldTeamId);
		if(teamUserList != null && teamUserList.size() > 0){
			log.info("正在升级 team_user表中的数据");
			for(TeamUser teamUser : teamUserList){
				//只升级班主任、学生和家长的记录，科任教师除外(班主任+科任教师、家长+科任教师的角色也需记录)
				if(teamUser != null){
					if(teamUser.getIsMaster() == true || teamUser.getIsParent() == true || teamUser.getIsStudent() == true){
						TeamUser tu = teamUserService.findUnique(newTeamId, teamUser.getUserId());
						if(tu != null && tu.getId() != null){
							log.info("正在升级 班级用户 "+ teamUser.getName() + " 的数据(发现已存在数据，进行修改)");
							tu.setTeamId(newTeamId);
							tu.setModifyDate(new Date());
							teamUserService.modify(tu);
						}else{
							log.info("正在升级 班级用户 "+ teamUser.getName() + " 的数据(新增班级学生数据)");
							teamUser.setId(null);
							teamUser.setTeamId(newTeamId);
							teamUser.setCreateDate(new Date());
							teamUser.setModifyDate(new Date());
							teamUserService.add(teamUser);
						}
					}
				}
			}
		}
	}	
	
	//升级student_alteration表
	private void createStudentAlteration(Team oldTeam, Team newTeam,
			Integer newTeamId, List<Student> studentList, String type) {
		
		if(studentList != null && studentList.size() > 0){
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					StudentAlteration sa = studentAlterationService.findStudentRecord(newTeamId, student.getUserId(), type);
					if(sa != null){
						log.info("正在升级  修改学生：" + student.getName() +"的变更历史");
						sa.setAlterDate(new Date());
						sa.setAlterFrom(oldTeam.getName());
						sa.setAlterTo(newTeam.getName());
						sa.setAlterType(type);
						if(type.equals(StudentAlterationContants.TYPE_BIYE)){
							sa.setComment("学生毕业");
						}else{
							sa.setComment("学生升级");
						}
						sa.setModifyDate(new Date());
						studentAlterationService.modify(sa);
					}else{
						log.info("正在升级  新增学生：" + student.getName() +"的变更历史");
						sa = new StudentAlterationCondition();
						sa.setSchoolId(newTeam.getSchoolId());
						sa.setSchoolYear(newTeam.getSchoolYear());
						sa.setUserId(student.getUserId());
						sa.setStudentId(student.getId());
						sa.setTeamId(newTeamId);
						sa.setAlterDate(new Date());
						sa.setAlterFrom(oldTeam.getName());
						sa.setAlterTo(newTeam.getName());
						sa.setAlterType(type);
						if(type.equals(StudentAlterationContants.TYPE_BIYE)){
							sa.setComment("学生毕业");
						}else{
							sa.setComment("学生升级");
						}
						sa.setIsDelete(false);
						sa.setCreateDate(new Date());
						sa.setModifyDate(new Date());
						studentAlterationService.add(sa);
					}
				}
			}
		}
	}	
	
	//升级team_account表
//	private void createTeamAccount(Integer oldTeamId, Integer oldSchoolId, Team newTeam) {
//		//C1 取原班级的班级账号，生成新的班级账号
//		TeamAccount oldTeamAccount = teamAccountService.findBySchoolIdAndTeamId(oldSchoolId, oldTeamId);
//		 
//		 if(oldTeamAccount != null){
//			 TeamAccount newTeamAccount = teamAccountService.findBySchoolIdAndTeamId(newTeam.getSchoolId(), newTeam.getId());
//			//C2 判断新账号是否存在，没有则生成
//			 if(newTeamAccount != null){
//				 log.info("正在升级  新班级已存在，正在修改班级（" +newTeam.getId() +"）账号");
//				 newTeamAccount.setModifyDate(new Date());
//				 newTeamAccount.setTeamUuid(newTeam.getUuid());
//				 newTeamAccount.setTeamCode(newTeam.getCode());
//				 teamAccountService.modify(newTeamAccount);
//			 }else{
//				 log.info("正在升级  创建新班级（" + newTeam.getId() +"）账号");
//				 newTeamAccount = new TeamAccount();
//				 newTeamAccount.setAccount(oldTeamAccount.getAccount());
//				 newTeamAccount.setPassword(oldTeamAccount.getPassword());
//				 newTeamAccount.setAccessCode(oldTeamAccount.getAccessCode());
//				 newTeamAccount.setAccessType(oldTeamAccount.getAccessType());
//				 newTeamAccount.setAccountState(oldTeamAccount.getAccountState());
//				 newTeamAccount.setSignature(oldTeamAccount.getSignature());
//				 newTeamAccount.setCreateUserId(oldTeamAccount.getCreateUserId());
//				 
//				 newTeamAccount.setSchoolId(newTeam.getSchoolId());
//				 newTeamAccount.setGradeId(newTeam.getGradeId());
//				 newTeamAccount.setTeamId(newTeam.getId());
//				 newTeamAccount.setTeamCode(newTeam.getCode());
//				 newTeamAccount.setTeamUuid(newTeam.getUuid());
//				 newTeamAccount.setCreateDate(new Date());
//				 newTeamAccount.setModifyDate(new Date());
//				 teamAccountService.add(newTeamAccount);
//			 }
//		 }
//	}
		
	@Override
	public void graduateTeam(Integer oldTeamId) {
		Team oldTeam = teamService.findTeamById(oldTeamId);
		Integer schoolId = oldTeam.getSchoolId();
		
		//B1 获取原班级所有学生
		List<Student> studentList = studentService.findStudentByTeamId(oldTeamId);
		List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(oldTeamId);
		
		//B2 将学生的在读状态设为毕业
		if(studentList != null && studentList.size() > 0){
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					student.setStudyState("07");
					studentService.modify(student);
				}
			}
		}
		
		//B3 将学生的在校标志设置为离校，更新school_user表
		if(teamStudentList != null && teamStudentList.size() > 0){
			Integer[] userIds = new Integer[teamStudentList.size()];
			for(int i=0; i< teamStudentList.size(); i++){
				userIds[i] = teamStudentList.get(i).getUserId();
			}	
			int row = schoolUserService.updateNotInSchoolByType("4", userIds);
			log.info("已将原班级 " + oldTeam.getName() +" 的 " + row + "位学生设置为离校");
		}
		
		//B4 创建学生变更历史记录
		createStudentAlteration(oldTeam, oldTeam, oldTeam.getId(), studentList,StudentAlterationContants.TYPE_BIYE);
		
		//C1 获取学生所有家长
		//C2 获取主监护人或父母其中一人
		if(teamStudentList != null && teamStudentList.size() > 0){
			for(TeamStudent ts : teamStudentList){
				List<ParentStudent> pslist = parentStudentService.findParentStudentByStudentUserId(ts.getUserId());
				
				if(pslist != null && pslist.size() > 0){
					Integer[] puserIds = new Integer[pslist.size()];
					for(int i=0; i<pslist.size(); i++){
						puserIds[i] = pslist.get(i).getParentUserId();
					}
					//C3 获取家长的所有子女，判断除了本人以外是否还有其他子女与之同校就读
					List<SchoolUser> schoolUserList = schoolUserService.findSiblingOfStudentUser(puserIds, ts.getUserId(), schoolId);
					
					//C4 若没有，更新school_user,将家长的在校标志设为离校
					if(schoolUserList == null || schoolUserList.size() == 0){
						int row = schoolUserService.updateNotInSchoolByType("3", puserIds);
						System.out.println("已将原 " + oldTeam.getName() +" "+ ts.getName() +" 的 "+ row + "位家长设置为离校");
					}
				}
			}
		}
		
		
		
	}

}
