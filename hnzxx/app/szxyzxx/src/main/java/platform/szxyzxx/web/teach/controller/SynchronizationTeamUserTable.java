package platform.szxyzxx.web.teach.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.clazz.model.TeamAccount;
import platform.education.clazz.service.TeamAccountService;
import platform.education.generalTeachingAffair.contants.StudentAlterationContants;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentAlteration;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.StudentAlterationService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUpgradeService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.StudentAlterationCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping("/updata")
public class SynchronizationTeamUserTable {
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("teamAccountService")
	private TeamAccountService teamAccountService;
	
	@Autowired
	@Qualifier("studentAlterationService")
	private StudentAlterationService studentAlterationService;
	
	@Autowired
	@Qualifier("teamUpgradeService")
	private TeamUpgradeService teamUpgradeService;
	
	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;
	
	

	//????????????
	@RequestMapping(value = "/upgradeIndex")
	public ModelAndView toUpgradePage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm
			){
		ModelAndView mav = new ModelAndView();
		String viewPath = "/teach/grade/upgradeIndex";
		mav.setViewName(viewPath);
		return mav;
	}
	
	//???????????????????????????????????????
	@RequestMapping(value = "/getNewYG")
	@ResponseBody
	public ResponseInfomation getNewYG(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false)String year,
			@RequestParam(value = "gradeId", required = false)Integer gradeId
			){
		SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(user.getSchoolId(), year);
		//??????????????????
		boolean isGraduate = false;
		Map map = new LinkedHashMap();
		Grade oldGrade = gradeService.findGradeById(gradeId);
		if(oldGrade != null){
			//??????????????????????????????
			//List<Team> teamList = teamService.findTeamOfGrade(oldGrade.getId());
			List<Team> teamList = teamService.findAdministrativeTeam(oldGrade.getId(), oldGrade.getSchoolId());
			if(teamList != null && teamList.size() > 0){
				map.put("teamList", teamList);
			}
			
			//??????????????????????????????????????????????????????????????????
			String uniGradeCode = oldGrade.getUniGradeCode();
			if("26".equals(uniGradeCode) || "33".equals(uniGradeCode) || "43".equals(uniGradeCode)){
				isGraduate = true;
			}
			//???????????????????????????????????????????????????????????????????????????
			if(schoolYear != null){
				String newUGCode = String.valueOf(Integer.parseInt(uniGradeCode) + 1);
				String code = oldGrade.getSchoolId() + "-" + year + "-" + newUGCode;
				List<Grade> glist = gradeService.findGradeByCode(code);
				if(glist != null && glist.size() > 0){
					map.put("grade", glist.get(0));
				}
				map.put("schoolYear", schoolYear);
			}
		}
		map.put("isGraduate", isGraduate);
		
		return new ResponseInfomation(map, ResponseInfomation.OPERATION_SUC);
	}
	
	//??????????????????
	@RequestMapping(value = "/upgradeGrade")
	@ResponseBody
	public ResponseInfomation upgradeGrade(
			@RequestParam(value = "oldGradeId", required = false)Integer oldGradeId,
			@RequestParam(value = "newGradeId", required = false)Integer newGradeId,
			@RequestParam(value = "teamIds", required = false)String teamIds
			){
		Grade oldGrade = gradeService.findGradeById(oldGradeId);
		if(oldGrade == null){
			log.info("???????????????????????????");
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		Grade newGrade = null;
		if(newGradeId != null){
			newGrade = gradeService.findGradeById(newGradeId);
		}
		if(newGrade == null){
			newGrade = gradeService.createUpgradeGrade(oldGradeId);
		}
		String[] oldTeamIds = teamIds.split(",");
		Integer[] ids = new Integer[oldTeamIds.length];
		for(int i=0; i < ids.length; i++){
			ids[i] = Integer.parseInt(oldTeamIds[i]);
		}
		
		String skipName = "";	//?????????????????????
		List<Team> oldTeamList = teamService.findByIds(ids);
		if(oldTeamList != null && oldTeamList.size() > 0){
			for(Team oldTeam : oldTeamList){
				Map map = teamUpgradeService.upgradeTeam(newGrade.getId(), oldTeam.getId());
				if(map != null){
					boolean isSuccess = (boolean) map.get("isSuccess");
					Team newTeam = (Team) map.get("team");
					if(newTeam != null){
						//C ????????????????????????
						createTeamAccount(oldTeam.getId(), oldTeam.getSchoolId(), newTeam);
					} 
					if(isSuccess){
						System.out.println("????????????  ???" + newTeam.getName() + " / " + newTeam.getId() + " / " + newTeam.getCode());
					}else{
						System.out.println("????????????????????????????????????" + oldTeam.getName() + "?????????");
						skipName += oldTeam.getName() +"???";
					}
				}
			}
		}
		if(skipName.length() > 0){
			skipName = skipName.substring(0, skipName.length()-1);
		}
		
		//?????????????????????????????? 
		updataTeamCount(newGrade);
		return new ResponseInfomation(skipName, ResponseInfomation.OPERATION_SUC);
			
	}

	private void updataTeamCount(Grade newGrade) {
		TeamCondition teamCondition = new TeamCondition();
		teamCondition.setGradeId(newGrade.getId());
		Long count = teamService.count(teamCondition);
		newGrade.setTeamCount(Integer.parseInt(String.valueOf(count)));
		gradeService.modify(newGrade);
	}

	//??????team_account???
	private void createTeamAccount(Integer oldTeamId, Integer oldSchoolId, Team newTeam) {
		//C1 ??????????????????????????????????????????????????????
		TeamAccount oldTeamAccount = teamAccountService.findBySchoolIdAndTeamId(oldSchoolId, oldTeamId);
		 
		 if(oldTeamAccount != null){
			 TeamAccount newTeamAccount = teamAccountService.findBySchoolIdAndTeamId(newTeam.getSchoolId(), newTeam.getId());
			//C2 ?????????????????????????????????????????????
			 if(newTeamAccount != null){
				 log.info("????????????  ??????????????????????????????????????????" +newTeam.getId() +"?????????");
				 newTeamAccount.setModifyDate(new Date());
				 newTeamAccount.setTeamUuid(newTeam.getUuid());
				 newTeamAccount.setTeamCode(newTeam.getCode());
				 teamAccountService.modify(newTeamAccount);
			 }else{
				 log.info("????????????  ??????????????????" + newTeam.getId() +"?????????");
				 newTeamAccount = new TeamAccount();
				 newTeamAccount.setAccount(oldTeamAccount.getAccount());
				 newTeamAccount.setPassword(oldTeamAccount.getPassword());
				 newTeamAccount.setAccessCode(oldTeamAccount.getAccessCode());
				 newTeamAccount.setAccessType(oldTeamAccount.getAccessType());
				 newTeamAccount.setAccountState(oldTeamAccount.getAccountState());
				 newTeamAccount.setSignature(oldTeamAccount.getSignature());
				 newTeamAccount.setCreateUserId(oldTeamAccount.getCreateUserId());
				 
				 newTeamAccount.setSchoolId(newTeam.getSchoolId());
				 newTeamAccount.setGradeId(newTeam.getGradeId());
				 newTeamAccount.setTeamId(newTeam.getId());
				 newTeamAccount.setTeamCode(newTeam.getCode());
				 newTeamAccount.setTeamUuid(newTeam.getUuid());
				 newTeamAccount.setCreateDate(new Date());
				 newTeamAccount.setModifyDate(new Date());
				 teamAccountService.add(newTeamAccount);
			 }
		 }
	}

	//??????????????????
	@RequestMapping(value = "/graduateGrade")
	@ResponseBody
	public ResponseInfomation graduateGrade(
			@RequestParam(value = "oldGradeId", required = false)Integer oldGradeId,
			@RequestParam(value = "teamIds", required = false)String teamIds
			){
		Grade oldGrade = gradeService.findGradeById(oldGradeId);
		if(oldGrade == null || !("26".equals(oldGrade.getUniGradeCode()) 
				|| "33".equals(oldGrade.getUniGradeCode()) || "43".equals(oldGrade.getUniGradeCode()))){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		String[] oldTeamIds = teamIds.split(",");
		if(oldTeamIds.length > 0){
			for(String oldTeamId : oldTeamIds){
				teamUpgradeService.graduateTeam(Integer.parseInt(oldTeamId));
			}
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	

	/**
	 * ????????????team_user???????????????
	 */
	@RequestMapping(value = "/updata/team_user/go")
	public void synchronTeamUser(){
		//??????????????????????????????
		SynchronizationStudentAndParent();
		//??????????????????
		SynchronizationTeacher();
	}
	
	/**
	 * ??????????????????
	 */
	@RequestMapping(value = "/updata/upGrade/go")
	public void upGrade(@RequestParam(value = "oldTeamId", required = false)String oldTeamId,
						@RequestParam(value = "newTeamId", required = false)String newTeamId,
						@RequestParam(value = "gradId", required = false)String graduatingId){
		log.info("================================");
		log.info("????????????");
		log.info("================================");
		
		if(oldTeamId == null || "".equals(oldTeamId) || newTeamId == null || "".equals(newTeamId)){
			log.info("================================");
			log.info("???????????????????????????????????????");
			log.info("================================");
			return;
		}
		
		String[] oldTeams = oldTeamId.split(",");
		String[] newTeams = newTeamId.split(",");
		
		//??????
		Integer length = newTeams.length;
		
		for(int i = 0; i < length; i++){
			//?????????ID
			Integer oldId = Integer.parseInt(oldTeams[i]);
			//?????????ID
			Integer newId = Integer.parseInt(newTeams[i]);
			
			//???????????????????????????????????????
			Team oldTeam = teamService.findTeamById(oldId);
			
			//???????????????????????????????????????
			Team newTeam = teamService.findTeamById(newId);
			
			if(oldTeam == null || newTeam == null){
				log.info("================================");
				log.info("??????????????????????????????TeamStudent???????????????????????????");
				log.info("================================");
				continue;
			}
			
			//??????????????????
			upStudent(oldId,newId);
			//????????????????????????
			upTeamStudent(oldId,newId);
			//????????????????????????
			upTeamUser(oldId,newId);
			//?????????????????????
			upTeamAccount(oldId,newId);
			//????????????????????????
			upAlteration(oldId,newId,StudentAlterationContants.TYPE_SHENGJI);
		}
		
		log.info("================================");
		log.info("??????????????????");
		log.info("================================");
		
		if(graduatingId != null){
			String[] graduatingIds = graduatingId.split(",");
			//??????
			Integer graduatingIdslength = graduatingIds.length;
			
			for(int i = 0; i < graduatingIdslength; i++){
				//????????????ID
				Integer gId = Integer.parseInt(graduatingIds[i]);
				//????????????????????????
				upAlteration(gId,gId,StudentAlterationContants.TYPE_BIYE);
				//??????????????????
				upBYStudent(gId);
			}
			log.info("================================");
			log.info("????????????????????????");
			log.info("================================");
		}else{
			log.info("================================");
			log.info("?????????????????????");
			log.info("================================");
		}
		
		log.info("================================");
		log.info("????????????");
		log.info("================================");
	}
	
	//?????????????????????
	public void upStudent(Integer oldTeamId,Integer newTeamId){
		
		if(oldTeamId == null || newTeamId == null){
			return;
		}
		
		//???????????????????????????????????????
		Team oldTeam = teamService.findTeamById(oldTeamId);
		
		//???????????????????????????????????????
		Team newTeam = teamService.findTeamById(newTeamId);
		
		if(oldTeam == null || newTeam == null){
			return;
		}
		
		//????????????Id????????????????????????????????????
		List<Student> studentList = studentService.findStudentByTeamId(oldTeamId);
		if(studentList != null && studentList.size() > 0){
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					log.info("================================");
					log.info("????????????==?????? "+student.getName()+" ?????????");
					log.info("================================");
					student.setTeamId(newTeam.getId());
					student.setTeamName(newTeam.getName());
					studentService.modify(student);
				}
			}
		}
	}
	
	//????????????????????????
	public void upTeamStudent(Integer oldTeamId,Integer newTeamId){
		if(oldTeamId == null || newTeamId == null){
			log.info("================================");
			log.info("????????????????????????????????????TeamStudent??????????????????");
			log.info("================================");
			return;
		}
		
		//???????????????????????????????????????
		Team oldTeam = teamService.findTeamById(oldTeamId);
		
		//???????????????????????????????????????
		Team newTeam = teamService.findTeamById(newTeamId);
		
		if(oldTeam == null || newTeam == null){
			log.info("================================");
			log.info("??????????????????????????????TeamStudent??????????????????");
			log.info("================================");
			return;
		}
		
		Grade newGrade = gradeService.findGradeById(newTeam.getGradeId());
		
		if(newGrade == null || newGrade == null){
			return;
		}
		
		//??????????????????????????????????????????
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		teamStudentCondition.setTeamId(oldTeamId);
		teamStudentCondition.setIsDelete(false);
		teamStudentCondition.setInState(true);
		List<TeamStudent> TeamStudentList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
		if(TeamStudentList != null && TeamStudentList.size() > 0){
			//copy??????
			for(TeamStudent teamStudent : TeamStudentList){
				if(teamStudent != null && teamStudent.getId() != null){
					TeamStudent ts = teamStudentService.findUnique(newTeamId, teamStudent.getStudentId());
					if(ts == null){
						log.info("================================");
						log.info("????????????==???????????? "+teamStudent.getName()+" ?????????(????????????????????????)");
						log.info("================================");
						teamStudent.setModifyDate(new Date());
						teamStudent.setCreateDate(new Date());
						teamStudent.setJoinDate(new Date());;
						teamStudent.setTeamId(newTeamId);
						teamStudent.setGradeId(newTeam.getGradeId());
						teamStudent.setGradeName(newGrade.getName());
						teamStudent.setId(null);
						teamStudentService.add(teamStudent);
					}else{
						log.info("================================");
						log.info("????????????==???????????? "+teamStudent.getName()+" ?????????(????????????????????????????????????)");
						log.info("================================");
						ts.setModifyDate(new Date());
						ts.setTeamId(newTeamId);
						ts.setGradeId(newTeam.getGradeId());
						ts.setGradeName(newGrade.getName());
						teamStudentService.modify(ts);
					}
				}
			}
		}
		
	}
	
	//????????????????????????????????????
	public void upBYStudent(Integer teamId){
		if(teamId == null){
			return;
		}
		
		Team team = teamService.findTeamById(teamId);
		if(team == null){
			return;
		}
		
		//????????????Id????????????????????????????????????
		List<Student> studentList = studentService.findStudentByTeamId(teamId);
		if(studentList != null && studentList.size() > 0){
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					log.info("================================");
					log.info("????????????==?????? "+student.getName()+" ???????????????");
					log.info("================================");
					student.setStudyState("07");
					studentService.modify(student);
				}
			}
		}
		
		log.info("================================");
		log.info("??????????????????????????????");
		log.info("================================");
	}
	
	//????????????????????????
	public void upTeamUser(Integer oldTeamId,Integer newTeamId){
		if(oldTeamId == null || newTeamId == null){
			log.info("================================");
			log.info("???????????????????????????????????????");
			log.info("================================");
			return;
		}
		
		//???????????????????????????????????????
		Team oldTeam = teamService.findTeamById(oldTeamId);
		
		//???????????????????????????????????????
		Team newTeam = teamService.findTeamById(newTeamId);
		
		if(oldTeam == null || newTeam == null){
			log.info("================================");
			log.info("????????????????????????????????????");
			log.info("================================");
			return;
		}
		
		//??????????????????ID????????????
		List<TeamUser> teamUserList = teamUserService.findTeamUserOfAll(oldTeamId);
		if(teamUserList != null && teamUserList.size() > 0){
			for(TeamUser teamUser : teamUserList){
				if(teamUser != null){
					TeamUser tu = teamUserService.findUnique(newTeamId, teamUser.getUserId());
					if(tu != null && tu.getId() != null){
						log.info("================================");
						log.info("????????????==???????????? "+ teamUser.getName() + " ???????????????????????????????????????????????????");
						log.info("================================");
						tu.setModifyDate(new Date());
						tu.setTeamId(newTeamId);
						teamUserService.modify(tu);
					}else{
						log.info("================================");
						log.info("????????????==???????????? "+ teamUser.getName() + " ???????????????????????????????????????");
						log.info("================================");
						teamUser.setId(null);
						teamUser.setModifyDate(new Date());
						teamUser.setCreateDate(new Date());
						teamUser.setTeamId(newTeamId);
						teamUserService.add(teamUser);
					}
				}
			}
		}
		
	}
	
	//?????????????????????
	public void upTeamAccount(Integer oldTeamId,Integer newTeamId){
		if(oldTeamId == null || newTeamId == null){
			log.info("================================");
			log.info("????????????==?????????????????????????????????????????????");
			log.info("================================");
			return;
		}
		
		//???????????????????????????????????????
		Team oldTeam = teamService.findTeamById(oldTeamId);
		
		//???????????????????????????????????????
		Team newTeam = teamService.findTeamById(newTeamId);
		
		if(oldTeam == null || newTeam == null){
			log.info("================================");
			log.info("????????????==???????????????????????????????????????");
			log.info("================================");
			return;
		}
		
		//??????????????????????????????
		TeamAccount oldTeamAccount = teamAccountService.findBySchoolIdAndTeamId(oldTeam.getSchoolId(), oldTeamId);
		 
		 if(oldTeamAccount != null){
			 TeamAccount newTeamAccount = teamAccountService.findBySchoolIdAndTeamId(oldTeam.getSchoolId(), oldTeamId);
			 
			 if(newTeamAccount != null){
				 log.info("================================");
				 log.info("????????????==??????????????????????????????????????????"+newTeam.getId()+"?????????");
				 log.info("================================");
				 newTeamAccount.setModifyDate(new Date());
				 newTeamAccount.setTeamUuid(newTeam.getUuid());
				 newTeamAccount.setTeamCode(newTeam.getCode());
				 teamAccountService.modify(newTeamAccount);
			 }else{
				 log.info("================================");
				 log.info("????????????==???????????????????????????"+newTeam.getId()+"?????????");
				 log.info("================================");
				 newTeamAccount = new TeamAccount();
					 try {
						 BeanUtils.copyProperties( newTeamAccount, oldTeamAccount);
					 } catch (IllegalAccessException e) {
						 e.printStackTrace();
					 } catch (InvocationTargetException e) {
						 e.printStackTrace();
					 }
				 newTeamAccount.setCreateDate(new Date());
				 newTeamAccount.setId(null);
				 newTeamAccount.setSchoolId(newTeam.getSchoolId());
				 teamAccountService.add(newTeamAccount);
			 }
		 }else{
			 log.info("================================");
			 log.info("????????????==?????????"+oldTeamId+"?????????????????????????????????????????????????????????");
			 log.info("================================");
		 }
	}
	
	public void upAlteration(Integer oldTeamId,Integer newTeamId,String type){
		if(oldTeamId == null || newTeamId == null){
			log.info("================================");
			log.info("????????????==?????????????????????");
			log.info("================================");
			return;
		}
		
		Team oldTeam = teamService.findTeamById(oldTeamId);
		
		Team newTeam = teamService.findTeamById(newTeamId);
		
		if(oldTeam == null || newTeam == null){
			return;
		}
		
		//????????????Id????????????????????????????????????
		List<Student> studentList = studentService.findStudentByTeamId(oldTeamId);
		if(studentList != null && studentList.size() > 0){
			for(Student student : studentList){
				if(student != null && student.getId() != null){
					StudentAlterationCondition studentAlterationCondition = new StudentAlterationCondition();
					studentAlterationCondition.setAlterType("");
					studentAlterationCondition.setIsDelete(false);
					studentAlterationCondition.setSchoolId(student.getSchoolId());
					studentAlterationCondition.setSchoolYear(newTeam.getSchoolYear());
					studentAlterationCondition.setStudentId(student.getId());
					studentAlterationCondition.setUserId(student.getUserId());
					studentAlterationCondition.setTeamId(newTeam.getId());
					List<StudentAlteration> saList = studentAlterationService.findStudentAlterationByCondition(studentAlterationCondition);
					if(saList != null && saList.size() > 0){
						StudentAlteration sa = saList.get(0);
						if(sa != null && sa.getId() != null){
							sa.setAlterDate(new Date());
							sa.setAlterFrom(oldTeam.getName());
							sa.setAlterTo(newTeam.getName());
							sa.setAlterType(type);
							if(type.equals(StudentAlterationContants.TYPE_BIYE)){
								sa.setComment("????????????");
							}else{
								sa.setComment("????????????");
							}
							sa.setModifyDate(new Date());
							log.info("================================");
							log.info("????????????==?????????????????????");
							log.info("================================");
							studentAlterationService.modify(sa);
						}
					}else{
						StudentAlteration sa = new StudentAlterationCondition();
						sa.setAlterDate(new Date());
						sa.setAlterFrom(oldTeam.getName());
						sa.setAlterTo(newTeam.getName());
						sa.setAlterType(type);
						if(type.equals(StudentAlterationContants.TYPE_BIYE)){
							sa.setComment("????????????");
						}else{
							sa.setComment("????????????");
						}
						sa.setCreateDate(new Date());
						sa.setIsDelete(false);
						sa.setModifyDate(new Date());
						sa.setSchoolId(newTeam.getSchoolId());
						sa.setSchoolYear(newTeam.getSchoolYear());
						sa.setStudentId(student.getId());
						sa.setTeamId(newTeamId);
						sa.setUserId(student.getUserId());
						log.info("================================");
						log.info("????????????==?????????????????????");
						log.info("================================");
						studentAlterationService.add(sa);
					}
				}
			}
		}
		
	}
	
	public void SynchronizationStudentAndParent(){
		StudentCondition studentCondition = new StudentCondition();
		studentCondition.setIsDelete(false);
		log.info("?????????????????????????????????");
		List<Student> allStudentList = studentService.findStudentByCondition(studentCondition, null, null);
		if(allStudentList != null && allStudentList.size() > 0){
			for(Student student : allStudentList){
				if(student.getTeamId() != null && !"".equals(student.getTeamId())){
					//????????????????????????
					log.info("...??????????????????  "+student.getName()+"?????????");
					TeamUser teamUser = addOrModify(student.getTeamId(),student.getUserId(),student.getName(),student.getSex(),"s");
					log.info("????????????  "+teamUser.getId()+"???"+student.getUserId()+","+student.getName());
					writerTxt(teamUser.getId(),student.getUserId(),student.getName(),"s");
					
					//??????????????????
					List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
					if(parentStudentList != null && parentStudentList.size() > 0){
						for(ParentStudent parentStudent : parentStudentList){
							if(parentStudent != null && parentStudent.getId() != null){
								Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
								if(parent != null && parent.getId() != null){
									Person person = personService.findPersonById(parent.getPersonId());
									if(person != null){
										log.info("...??????????????????  "+parent.getName()+"?????????");
										teamUser = addOrModify(student.getTeamId(),parent.getUserId(),parent.getName(),person.getSex(),"p");
										log.info("????????????  "+teamUser.getId()+"???"+parent.getUserId()+","+parent.getName());
//										writerTxt(teamUser.getId(),parent.getUserId(),parent.getName(),"p");
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * ??????????????????
	 */
	public void SynchronizationTeacher(){
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setDelete(false);
		log.info("?????????????????????????????????");
		List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		if(teamTeacherList != null && teamTeacherList.size() > 0){
			Teacher teacher = null;
			TeamUser teamUser = null;
			for(TeamTeacher teamTeacher : teamTeacherList){
				if(teamTeacher != null){
					teacher = teacherService.findTeacherById(teamTeacher.getTeacherId());
					if(teacher != null){
						if(teamTeacher.getType()==1){
							log.info("...?????????????????????  "+teacher.getName()+"?????????");
							teamUser = addOrModify(teamTeacher.getTeamId(),teacher.getUserId(),teacher.getName(),teacher.getSex(),"m");
							log.info("????????????  "+teamUser.getId()+"???"+teacher.getUserId()+","+teacher.getName());
							writerTxt(teamUser.getId(),teacher.getUserId(),teacher.getName(),"m");
						}else if(teamTeacher.getType()==2){
							log.info("...????????????????????????  "+teacher.getName()+"?????????");
							teamUser = addOrModify(teamTeacher.getTeamId(),teacher.getUserId(),teacher.getName(),teacher.getSex(),"t");
							log.info("????????????  "+teamUser.getId()+"???"+teacher.getUserId()+","+teacher.getName());
//							writerTxt(teamUser.getId(),teacher.getUserId(),teacher.getName(),"t");
						}
					}
				}
			}
		}
	}
	
	
	
	
	public TeamUser addOrModify(Integer teamId,Integer userId,String name,String sex,String type){
		Boolean isM = false;
		Boolean isP = false;
		Boolean isS = false;
		Boolean isT = false;
		
		if(type == "m"){
			isM = true;
		}else if(type == "p"){
			isP = true;
		}else if(type == "s"){
			isS = true;
		}else if(type == "t"){
			isT = true;
		}
		
		Date date = new Date();
		
		TeamUser teamUser = teamUserService.findUnique(teamId, userId);
		if(teamUser == null){
			teamUser = new TeamUser();
			teamUser.setCreateDate(date);
			teamUser.setIsMaster(isM);
			teamUser.setIsParent(isP);
			teamUser.setIsStudent(isS);
			teamUser.setIsTeacher(isT);
			teamUser.setModifyDate(date);
			teamUser.setName(name);
			teamUser.setSex(sex);
			teamUser.setTeamId(teamId);
			teamUser.setUserId(userId);
			teamUser = teamUserService.add(teamUser);
		}else{
			if(type == "m"){
				teamUser.setIsMaster(isM);
			}else if(type == "p"){
				teamUser.setIsParent(isP);
			}else if(type == "s"){
				teamUser.setIsStudent(isS);
			}else if(type == "t"){
				teamUser.setIsTeacher(isT);
			}
			teamUser.setModifyDate(date);
			teamUser.setName(name);
			teamUser.setTeamId(teamId);
			teamUser = teamUserService.modify(teamUser);
		}
		return teamUser;
	}
	//?????????????????????????????????
	public void writerTxt(Integer id,Integer userId,String name,String type){
		if(type == "s"){
			type="student";
		}else if(type=="p"){
			type="parent";
		}else if(type=="m"){
			type="master";
		}else if(type=="t"){
			type="teacher";
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("E:\\data\\data.txt"),true));
			String txt = "teamUserId is : "+id+", userId is : "+userId+", and userType is : "+type +"";
			writer.write(new String(txt.getBytes(),"UTF-8"));
			writer.newLine();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
