package platform.education.rest.jw.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.JobControl;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherAssist;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.JobControlService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryFieldService;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeacherAssistService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.JobControlCondition;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryCondition;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import platform.education.generalTeachingAffair.vo.TeacherArchiveVo;
import platform.education.generalTeachingAffair.vo.TeacherArchiveVolist;
import platform.education.generalTeachingAffair.vo.TeacherAssistCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.jw.service.TeacherRestService;
import platform.education.rest.jw.service.constant.TeacherArchiveConstants;
import platform.education.rest.jw.service.util.SalaryUtils;
import platform.education.rest.jw.service.vo.FieldNameValue;
import platform.education.rest.jw.service.vo.GradeInfo;
import platform.education.rest.jw.service.vo.SalaryFieldValue;
import platform.education.rest.jw.service.vo.TeacherArchiveInformation;
import platform.education.rest.jw.service.vo.TeacherSalary;
import platform.education.rest.jw.service.vo.TeacherSalaryField;
import platform.education.rest.jw.service.vo.TeamInfo;
import platform.education.rest.util.BeanUtilsSub;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.UserService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TeacherRestServiceImpl implements TeacherRestService{

	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("teacherAssistService")
	private TeacherAssistService teacherAssistService;
	
	@Autowired
	@Qualifier("pjTeacherSalaryFieldService")
	private PjTeacherSalaryFieldService salaryFieldService;
	
	@Autowired
	@Qualifier("pjTeacherSalaryService")
	private PjTeacherSalaryService salaryService;
	
	@Autowired
	@Qualifier("departmentService")
	private DepartmentService departmentService;
	
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	
	@Autowired
	@Qualifier("jobControlService")
	private JobControlService jobControlService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Override
	public Object getAllteam(Integer schoolId, String schoolYear, Integer userId, String role) {
		
		List<GradeInfo> gradeInfoList = null;
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(role == null || "".equals(role)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("role参数必填");
				info.setMsg("role参数不能为空");
				info.setParam("role");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(schoolYear==null || "".equals(schoolYear)) {
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
				if(schoolTermCurrent != null){
					schoolYear = schoolTermCurrent.getSchoolYear();
				} else {
					return null;
				}
			}
			
			//role: 
			//SCHOOL_MASTER  校长
			//SCHOOL_LEADER  管理员，德育主任
			//CLASS_MASTER   	班主任
			//SUBJECT_TEACHER	科任教师
			gradeInfoList = new ArrayList<GradeInfo>(); 
			if("SCHOOL_LEADER".equals(role) || "SCHOOL_MASTER".equals(role)){
				//管理员，德育主任  查找全部
				gradeInfoList = teamsForLeader(schoolId, schoolYear);
				
			}else{
				
				Teacher teacher = teacherService.findOfUser(schoolId, userId);
				if(teacher != null){
					if("CLASS_MASTER".equals(role) || "SUBJECT_TEACHER".equals(role)){
						//普通教师，查找所教的班级
						gradeInfoList = teamForTeacher(schoolId, schoolYear, teacher.getId(), role);
						
					}else {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("role参数异常");
						info.setMsg("role参数异常");
						info.setParam("role");
						return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
					}
					
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("schoolId,userId参数异常");
					info.setMsg("schoolId,userId参数异常");
					info.setParam("schoolId,userId");
					return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("userId,schoolYear,role");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		return new ResponseVo<List<GradeInfo>>("0", gradeInfoList);
	}

	private List<GradeInfo> teamsForLeader(Integer schoolId, String schoolYear){
		List<GradeInfo> gradeInfoList = new ArrayList<GradeInfo>();
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
		sortGrade(gradeList);
		if(gradeList != null && gradeList.size() > 0){
			for(Grade grade : gradeList){
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeId(grade.getId());
				gradeInfo.setGradeName(grade.getName());
				gradeInfo.setGradeCode(grade.getUniGradeCode());
				
				List<TeamInfo> teamInfoList = new ArrayList<TeamInfo>();
				List<Team> teamList = teamService.findTeamOfGradeByAsc(grade.getId());
				sortTeam(teamList);
				if(teamList != null && teamList.size() > 0){
					for(Team team : teamList){
						TeamInfo teamInfo = new TeamInfo();
						teamInfo.setTeamId(team.getId());
						teamInfo.setTeamName(team.getName());
						teamInfo.setName(team.getTeamNumber()+"班");
						teamInfo.setTeamNumber(team.getTeamNumber());
						teamInfoList.add(teamInfo);
					}
				}
				
				gradeInfo.setTeamList(teamInfoList);
				gradeInfoList.add(gradeInfo);
			}
		}
		return gradeInfoList;
	}
	
	private List<GradeInfo> teamForTeacher(Integer schoolId, String schoolYear, Integer teacherId, String role){
		List<GradeInfo> gradeInfoList = new ArrayList<GradeInfo>();
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setSchoolId(schoolId);
		condition.setSchoolYear(schoolYear);
		condition.setTeacherId(teacherId);
		if("CLASS_MASTER".equals(role)){
			condition.setType(1);
		}
		if("SUBJECT_TEACHER".equals(role)){
			condition.setType(2);
		}
		List<TeamTeacherVo> gradeList = teamTeacherService.findTeamTeacherGradeByCondition(condition);
		if(gradeList !=  null && gradeList.size() > 0){
			for(TeamTeacherVo grade : gradeList){
				GradeInfo gradeInfo = new GradeInfo();
				gradeInfo.setGradeId(grade.getGradeId());
				gradeInfo.setGradeName(grade.getGradeName());
				gradeInfo.setGradeCode(gradeService.findGradeById(grade.getGradeId()).getUniGradeCode());
				
				List<TeamInfo> teamInfoList = new ArrayList<TeamInfo>();
				condition.setGradeId(grade.getGradeId());
				
				List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherVoByGroupBy(condition);
				if(teamList != null && teamList.size() > 0){
					for(TeamTeacherVo team : teamList){
						TeamInfo teamInfo = new TeamInfo();
						String name = team.getTeamName();
						teamInfo.setTeamId(team.getTeamId());
						teamInfo.setTeamName(team.getTeamName());
						teamInfo.setName(name.substring(name.indexOf("(")+1, name.indexOf(")")) + "班");
						teamInfo.setTeamNumber(teamService.findTeamById(team.getTeamId()).getTeamNumber());
						teamInfoList.add(teamInfo);
					}
				}
				sortTeamInfo(teamInfoList);
				gradeInfo.setTeamList(teamInfoList);
				gradeInfoList.add(gradeInfo);
			}
			sortGradeInfo(gradeInfoList);
		}
		return gradeInfoList;
	}
	
	
	@Override
	public Object getAllteam(Integer schoolId, String schoolYear,
			Integer userId, String role, String jsonpCallback) {
		List<GradeInfo> gradeInfoList = null;
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(role == null || "".equals(role)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("role参数必填");
				info.setMsg("role参数不能为空");
				info.setParam("role");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			
			if(schoolYear==null || "".equals(schoolYear)) {
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
				if(schoolTermCurrent != null){
					schoolYear = schoolTermCurrent.getSchoolYear();
				} else {
					return null;
				}
			}
			
			gradeInfoList = new ArrayList<GradeInfo>(); 
			if("SCHOOL_LEADER".equals(role) || "SCHOOL_MASTER".equals(role)){
				//管理员，德育主任  查找全部
				gradeInfoList = teamsForLeader(schoolId, schoolYear);
				
			}else{
				
				Teacher teacher = teacherService.findOfUser(schoolId, userId);
				if(teacher != null){
					if("CLASS_MASTER".equals(role) || "SUBJECT_TEACHER".equals(role)){
						//普通教师，查找所教的班级
						gradeInfoList = teamForTeacher(schoolId, schoolYear, teacher.getId(), role);
						
					}else {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("role参数异常");
						info.setMsg("role参数异常");
						info.setParam("role");
						return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";
					}
					
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("schoolId,userId参数异常");
					info.setMsg("schoolId,userId参数异常");
					info.setParam("schoolId,userId");
					return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";
				}
				
			}
			String json = JSON.toJSONString(gradeInfoList);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("userId,schoolYear,role");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info) + ")";
		}
	}

	public void sortGrade(List<Grade> gradeList){
		Collections.sort(gradeList, new Comparator<Grade>(){  
			public int compare(Grade d1, Grade d2) {  
				Integer n1 = Integer.parseInt(d1.getUniGradeCode());
				Integer n2 = Integer.parseInt(d2.getUniGradeCode());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}
	
	public void sortTeam(List<Team> teamList){
		Collections.sort(teamList, new Comparator<Team>(){  
			public int compare(Team d1, Team d2) {  
				if(d1.getTeamNumber()>d2.getTeamNumber()){  
					return 1;  
				}  
				if(d1.getTeamNumber()==d2.getTeamNumber()){  
					return 0;  
				}  
				return -1;  
			}
		});
	}
	
	
	public void sortGradeInfo(List<GradeInfo> gradeList){
		Collections.sort(gradeList, new Comparator<GradeInfo>(){  
			public int compare(GradeInfo d1, GradeInfo d2) {  
				Integer n1 = Integer.parseInt(d1.getGradeCode());
				Integer n2 = Integer.parseInt(d2.getGradeCode());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}
	
	public void sortTeamInfo(List<TeamInfo> teamList){
		Collections.sort(teamList, new Comparator<TeamInfo>(){  
			public int compare(TeamInfo d1, TeamInfo d2) {  
				if(d1.getTeamNumber()>d2.getTeamNumber()){  
					return 1;  
				}  
				if(d1.getTeamNumber()==d2.getTeamNumber()){  
					return 0;  
				}  
				return -1;  
			}
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getTeacherMess(String userIds,Integer schoolId) {
		if(schoolId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数异常");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if(userIds == null || "".equals(userIds)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userIds参数异常");
			info.setMsg("userIds参数异常");
			info.setParam("userIds");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		TeacherArchiveInformation teacherArchiveInformation = null;
		TeacherArchiveInformation.TeacherAssistVo ta = null;
		List<TeacherArchiveInformation> teacherArchiveInformationList = new ArrayList<TeacherArchiveInformation>();
		if(userIds != null && !"".equals(userIds)){
			String[] userIdArr = userIds.split(",");
			if(userIdArr != null && userIdArr.length > 0){
				Boolean isInteger = false;
				for(String userId : userIdArr){
					Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
					isInteger = pattern.matcher(userId).matches();
					if(isInteger){
							teacherArchiveInformation = new TeacherArchiveInformation();
							//获取基本信息
							TeacherDetailInfo teacherDetailInfo = teacherService.findTeacherDetailInfoByUserId(Integer.valueOf(userId),schoolId);
							//获取辅助信息
							if(teacherDetailInfo != null && teacherDetailInfo.getUserId() != null){
								teacherArchiveInformation.setBasic(teacherDetailInfo);
								teacherArchiveInformation.setName(teacherDetailInfo.getName());
								teacherArchiveInformation.setUserId(teacherDetailInfo.getUserId());
								TeacherAssistCondition teacherAssistCondition = new TeacherAssistCondition();
								teacherAssistCondition.setUserId(teacherDetailInfo.getUserId());
								teacherAssistCondition.setSchoolId(teacherDetailInfo.getSchoolId());
								
								teacherDetailInfo.setPhotoUrl(ImgUtil.getImgUrl(teacherDetailInfo.getPhotoUuid()));
								
								List<TeacherAssist> teacherAssistList = teacherAssistService.findTeacherAssistByCondition(teacherAssistCondition);
								if(teacherAssistList != null && teacherAssistList.size() > 0){
									ta = teacherArchiveInformation.new TeacherAssistVo();
									try {
										BeanUtilsSub.copyProperties(ta, teacherAssistList.get(0));
									} catch (Exception e) {
										e.printStackTrace();
									} 
									teacherArchiveInformation.setAssist(ta);
									Date birthDate = teacherDetailInfo.getBirthDate();
									if ( birthDate != null ) {
										int age = Calendar.getInstance().get(Calendar.YEAR) - birthDate.getYear()-1900;
										teacherArchiveInformation.getAssist().setAge(age);
									}
								}
								teacherArchiveInformation.setValueList(getSalaryFieldAttrValueList(Integer.valueOf(userId), schoolId));
							}
							teacherArchiveInformation.setBasic(teacherDetailInfo);
							if(teacherArchiveInformation != null){
								teacherArchiveInformationList.add(teacherArchiveInformation);
							}
					}
				}
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userIds参数异常");
				info.setMsg("参数不能为空");
				info.setParam("userIds");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
		}
		return new ResponseVo<List<TeacherArchiveInformation>>("0", teacherArchiveInformationList);
	}
	
	private List<SalaryFieldValue> getSalaryFieldAttrValueList(Integer userId, Integer schoolId) {
		List<SalaryFieldValue> result = null;
		PjTeacherSalaryCondition pjTeacherSalaryCondition = new PjTeacherSalaryCondition();
		pjTeacherSalaryCondition.setUserId(userId);
		List<PjTeacherSalary> pjTeacherSalaryList = salaryService.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, SalaryUtils.getPage(0, 1), null);
		if ( pjTeacherSalaryList != null && pjTeacherSalaryList.size() > 0 ) {
			PjTeacherSalary pjTeacherSalary = pjTeacherSalaryList.get(0);
			result = new ArrayList<SalaryFieldValue>();
			List<PjTeacherSalaryField> salaryFieldList = getSalaryFieldList(schoolId);
			for (PjTeacherSalaryField pjTeacherSalaryField : salaryFieldList) {
				SalaryFieldValue value = new SalaryFieldValue();
				result.add(value);
				value.setFieldName(pjTeacherSalaryField.getFieldName());
				value.setAttrName(pjTeacherSalaryField.getAttrName());
				try {
					Field field = pjTeacherSalary.getClass().getDeclaredField(pjTeacherSalaryField.getFieldName());
					field.setAccessible(true);
					Float val = (Float) field.get(pjTeacherSalary);
					value.setValue(val);
				} catch (NoSuchFieldException | SecurityException
						| IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 待数校里的service迁移出来之后，可以直接取代该方法，待优化
	 * @param id
	 * @return
	 */
	public TeacherDetailInfo findTeacherDetailInfoById(Integer id) {
		TeacherDetailInfo teacherDetailInfo = null;
		try {
			teacherDetailInfo = new TeacherDetailInfo();
			Teacher teacher = teacherService.findTeacherById(id);
			Person person = this.personService.findPersonById(teacher
					.getPersionId());
					if(teacher != null) {
						teacherDetailInfo.setUserId(teacher.getUserId());
						teacherDetailInfo.setUserName(teacher.getUserName());
						teacherDetailInfo.setTeacherId(teacher.getId());
						teacherDetailInfo.setSchoolId(teacher.getSchoolId());
						teacherDetailInfo.setName(teacher.getName());
						teacherDetailInfo.setSex(teacher.getSex());
						teacherDetailInfo.setJobNumber(teacher.getJobNumber());
						teacherDetailInfo.setEnrollDate(teacher.getEnrollDate());
						teacherDetailInfo.setLeaveDate(teacher.getLeaveDate());
						teacherDetailInfo.setWorkBeginDate(teacher.getWorkBeginDate());
						teacherDetailInfo.setQualification(teacher.getQualification());
						teacherDetailInfo.setOccupationCode(teacher.getOccupationCode());
						teacherDetailInfo.setPosition(teacher.getPosition());
						teacherDetailInfo.setMobile(teacher.getMobile());
						teacherDetailInfo.setTelephone(teacher.getTelephone());
						teacherDetailInfo.setPostStaffing(teacher.getPostStaffing());
						teacherDetailInfo.setJobState(teacher.getJobState());
						teacherDetailInfo.setWorkBeginDate(teacher.getWorkBeginDate());
						teacherDetailInfo.setIsExternal(teacher.getIsExternal());  //是否外聘   1--是外聘  0--不是外聘
						teacherDetailInfo.setAlias(teacher.getAlias()); // 别名
					}
					
					if(person != null) {
						teacherDetailInfo.setEntityId(person.getEntityId());//图片ID
						teacherDetailInfo.setPhotoUuid(person.getPhotoUuid());	//头像id 2016-08-08新增
						teacherDetailInfo.setPersionId(person.getId());
						teacherDetailInfo.setEnglishName(person.getEnglishName());
						teacherDetailInfo.setCertificateType(person.getIdCardType());
						teacherDetailInfo.setCertificateNum(person.getIdCardNumber());
						teacherDetailInfo.setNationality(person.getNationality());
						teacherDetailInfo.setNation(person.getRace());
						teacherDetailInfo.setNativePlace(person.getNativePlace());
						teacherDetailInfo.setBirthPlace(person.getBirthPlace());
						teacherDetailInfo.setMarriage(person.getMarriage());
						teacherDetailInfo.setPolitical(person.getPoliticalStatus());
						teacherDetailInfo.setSpecialty(person.getSpecialSkill());
						teacherDetailInfo.setReligiousBelief(person.getReligion());
						teacherDetailInfo.setRegister(person.getResidenceType());
						teacherDetailInfo.setRegisterPlace(person.getResidenceAddress());
						teacherDetailInfo.setNowAddress(person.getAddress());
						teacherDetailInfo.setEmail(person.getEmail());
						teacherDetailInfo.setLiveAddress(person.getResideAddress());
						teacherDetailInfo.setRemark(person.getRemark());
						teacherDetailInfo.setBirthDate(person.getBirthday());
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherDetailInfo;
	}
	
	
	@Override
	public Object setTeacherMess(String data, Integer userId, Integer schoolId, Boolean isCompleted) {
		try {
			if(data == null || "".equals(data)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("data参数不能为空");
				info.setMsg("data参数异常");
				info.setParam("data");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数不能为空");
				info.setMsg("userId参数异常");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数不能为空");
				info.setMsg("schoolId参数异常");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			if(teacher == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到教师数据");
				info.setMsg("找不到教师数据");
				info.setParam("userId，schoolId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
			if(isCompleted == null){
				isCompleted = false;
			}
			
			TeacherArchiveInformation teacherArchiveInformation = JSON.parseObject(data, TeacherArchiveInformation.class);
			if(teacherArchiveInformation != null){
				//修改基本信息
				if(teacherArchiveInformation.getBasic() != null){
					TeacherDetailInfo teacherDetailInfo = teacherArchiveInformation.getBasic();
					teacherDetailInfo.setUserType(SysContants.USER_TYPE_TEACHER);
					teacherDetailInfo = teacherService.modifyInfoForTeacher(teacherDetailInfo);
					teacherDetailInfo.setPhotoUrl(ImgUtil.getImgUrl(teacherDetailInfo.getPhotoUuid()));
					teacherArchiveInformation.setBasic(teacherDetailInfo);
				}
				//修改辅助信息
				if(teacherArchiveInformation.getAssist() != null){
					TeacherAssist teacherAssist = new TeacherAssist();
					BeanUtilsSub.copyProperties(teacherAssist, teacherArchiveInformation.getAssist());
					teacherAssistService.updateTeacherAssist(schoolId, userId, teacherAssist);
				}
				
				//添加是否完成标志
				jobControlService.updateArchiveStatus(TeacherArchiveConstants.TYPE_TEACHER_FINISH, userId, isCompleted, null);
			}
			return new ResponseVo<TeacherArchiveInformation>("0", teacherArchiveInformation);
		
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据解析异常");
			info.setMsg("输入解析异常");
			info.setParam("data");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}
	
	
	@Override
	public Object updateTeacherAssistMess(Integer schoolId, String teacherInformations) {
		if(schoolId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数不能为空");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if(teacherInformations == null || "".equals(teacherInformations)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("teacherInformations参数不能为空");
			info.setMsg("teacherInformations参数异常");
			info.setParam("teacherInformations");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		School school = schoolService.findSchoolById(schoolId);
		if(school == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("找不到学校数据");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		int num = 0;
		try {
			JSONArray jsonArray = JSONArray.fromObject(teacherInformations);
			for(int i=0; i<jsonArray.size(); i++){
				net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
				num = i+1;
				TeacherArchiveInformation data = JSON.parseObject(jsonObject.toString(), TeacherArchiveInformation.class);
				
				if(data != null && data.getUserId() != null && data.getAssist() != null){
					TeacherAssist teacherAssist = new TeacherAssist();
					BeanUtilsSub.copyProperties(teacherAssist, data.getAssist());
					teacherAssistService.updateTeacherAssist(schoolId, data.getUserId(), teacherAssist);
				}
			}
			return new ResponseVo<String>("0", null);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("第"+num+"条记录有误");
			info.setMsg("teacherInformations参数异常");
			info.setParam("teacherInformations");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
	}

	@Override
	public Object getTeacherSalary(Integer userId) {
		Object result = null;
		List<SalaryUtils.PropertyValue> data = new ArrayList<SalaryUtils.PropertyValue>();
		data.add(SalaryUtils.getSingleton().new PropertyValue("userId", userId));
		result = SalaryUtils.checkNotNull(data);
		
		if(result == null ){
			PjTeacherSalaryCondition pjTeacherSalaryCondition = new PjTeacherSalaryCondition();
			pjTeacherSalaryCondition.setUserId(userId);
			List<PjTeacherSalary> list = salaryService.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, SalaryUtils.getPage(0, 1), null);
			if ( list != null && list.size() > 0 ) {
				PjTeacherSalary pjTeacherSalary = list.get(0);
				TeacherSalary teacherSalary = SalaryUtils.getTeacherSalary(pjTeacherSalary);
				result = new ResponseVo<TeacherSalary>("0", teacherSalary);
			} else {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId无数据");
				info.setMsg("userId无数据");
				info.setParam("userId");
				result = new ResponseError(CommonCode.D$DATA_NOT_EXIST, info);
			}
		}
		return result;
	}

	@Override
	public Object getTeacherSalaryList(Integer schoolId, Integer departmentId,
			Integer year, Integer month) {
		Object result = null;
		List<SalaryUtils.PropertyValue> data = new ArrayList<SalaryUtils.PropertyValue>();
		data.add(SalaryUtils.getSingleton().new PropertyValue("departmentId", departmentId));
		data.add(SalaryUtils.getSingleton().new PropertyValue("year", year));
		data.add(SalaryUtils.getSingleton().new PropertyValue("month", month));
		result = SalaryUtils.checkNotNull(data);
		if ( result == null ) {
			PjTeacherSalaryCondition pjTeacherSalaryCondition = new PjTeacherSalaryCondition();
			pjTeacherSalaryCondition.setSchoolId(schoolId);
			if ( departmentId.equals(Integer.valueOf(0)) ) {
				pjTeacherSalaryCondition.setDepartmentId(null);
			} else {
				pjTeacherSalaryCondition.setDepartmentId(departmentId);
			}
			pjTeacherSalaryCondition.setPayYear(year);
			pjTeacherSalaryCondition.setPayMonth(month);
			List<PjTeacherSalary> list = salaryService.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, null, null);
			if ( list != null && list.size() > 0 ) {
				List<TeacherSalary> teacherSalaryList = new ArrayList<TeacherSalary>();
				for (PjTeacherSalary pjTeacherSalary : list) {
					teacherSalaryList.add(SalaryUtils.getTeacherSalary(pjTeacherSalary));
				}
				result = new ResponseVo<List<TeacherSalary>>("0", teacherSalaryList);
			} else {
				result = SalaryUtils.queryNotExsit("schoolId:" + schoolId);
			}
		}
		return result;
	}

	@Override
	public Object updateTeacherSalary(Integer id, Integer year,
			Integer month, Float salaryTotal, String salaryDetail) {
		Object result = null;
		List<SalaryUtils.PropertyValue> data = new ArrayList<SalaryUtils.PropertyValue>();
		if ( salaryDetail == null || salaryDetail.length() == 0 ) {
			data.add(SalaryUtils.getSingleton().new PropertyValue("salaryDetail", null));
		}
		data.add(SalaryUtils.getSingleton().new PropertyValue("id", id));
		data.add(SalaryUtils.getSingleton().new PropertyValue("year", year));
		data.add(SalaryUtils.getSingleton().new PropertyValue("month", month));
		data.add(SalaryUtils.getSingleton().new PropertyValue("salaryTotal", salaryTotal));
		result = SalaryUtils.checkNotNull(data);
		if ( result != null ) {
			FieldNameValue fieldValue = JSONObject.parseObject(salaryDetail, FieldNameValue.class);
			PjTeacherSalary pjTeacherSalary = SalaryUtils.getPjTeacherSalary(id,null, null, null, year, month, salaryTotal, fieldValue);
			try {
				salaryService.modify(pjTeacherSalary);
				result = new ResponseVo<Boolean>("0", Boolean.TRUE);
			} catch (IllegalArgumentException e) {
				result = SalaryUtils.queryNotExsit("id:"+ id);
			}
		}
		return result;
	}

	@Override
	public Object getTeacherSalaryFieldList(Integer schoolId) {
		Object result = null;
		List<SalaryUtils.PropertyValue> data = new ArrayList<SalaryUtils.PropertyValue>();
		data.add(SalaryUtils.getSingleton().new PropertyValue("schoolId", schoolId));
		result = SalaryUtils.checkNotNull(data);
		if (result == null) {
			List<PjTeacherSalaryField> fieldList = getSalaryFieldList(schoolId);
			List<TeacherSalaryField> fieldList2 = new ArrayList<TeacherSalaryField>();
			if ( fieldList != null && fieldList.size() > 0 ) {
				for (PjTeacherSalaryField pjTeacherSalaryField : fieldList) {
					TeacherSalaryField field = new TeacherSalaryField(pjTeacherSalaryField.getFieldName(), pjTeacherSalaryField.getAttrName());
					fieldList2.add(field);
				}
				result = new ResponseVo<List<TeacherSalaryField>>("0", fieldList2);
			} else {
				result = SalaryUtils.queryNotExsit("schoolId:" + schoolId);
			}
		}
		return result;
	}

	@Override
	public Object importTeacherSalary(Integer schoolId, String fieldInfo, String stringData) {
		Object result = null;
		List<TeacherSalary> salaryList = new ArrayList<TeacherSalary>();
		List<ResponseInfo> errorList = new ArrayList<ResponseInfo>();
		try {
			salaryList = JSONObject.parseArray(stringData, TeacherSalary.class);
		} catch (Exception e1) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("data:"+stringData+" 不能反序列");
			info.setMsg("data:"+stringData+" 不能反序列");
			info.setParam("data:"+stringData+" 不能反序列");
			errorList.add(info);
		}
		List<SalaryUtils.PropertyValue> data = new ArrayList<SalaryUtils.PropertyValue>();
		data.add(SalaryUtils.getSingleton().new PropertyValue("schoolId", schoolId));
		if ( fieldInfo == null || fieldInfo.length() == 0 ) {
			data.add(SalaryUtils.getSingleton().new PropertyValue("fieldInfo", null));
		}
		if ( salaryList == null || salaryList.size() < 1 ) {
			data.add(SalaryUtils.getSingleton().new PropertyValue("salaryList", null));
		}
		result = SalaryUtils.checkNotNull(data);
		if ( result == null ) {
					checkSalaryField(schoolId, fieldInfo);
			for (TeacherSalary teacherSalary : salaryList) {
				data.clear();
				data.add(SalaryUtils.getSingleton().new PropertyValue("userId", teacherSalary.getUserId()));
				result = SalaryUtils.checkNotNull(data);
				try {
					if ( schoolId == null || teacherSalary.getUserId() == null ) {
						throw new IllegalArgumentException("参数schoolId:"+schoolId+", userId:"+teacherSalary.getUserId()+"异常");
					}
					PjTeacherSalary pjSalary = SalaryUtils.getPjTeacherSalary(null,schoolId, teacherSalary.getUserId(), teacherSalary.getName(),
							teacherSalary.getYear(), teacherSalary.getMonth(), teacherSalary.getSalaryTotal(), null);
					SalaryUtils.getPjTeacherSalaryByFieldNameValue(teacherSalary.getSalaryDetail(), pjSalary);
					salaryService.addPjTeacherSalary(pjSalary);
				} catch (IllegalArgumentException e) {
					ResponseError notExist = SalaryUtils.queryNotExsit("userId:" + teacherSalary.getUserId());
					notExist.getInfo().setDetail(e.getMessage());
					errorList.add(notExist.getInfo());
				} catch (Exception e) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail(e.getMessage());
					info.setMsg("userId:"+teacherSalary.getUserId()+" 导入时未知错误");
					info.setParam("...");
					errorList.add(info);
				}
			}
			if ( errorList.size() > 0 ) {
				result = new ResponseVo<List<ResponseInfo>>(CommonCode.$ERROR_IN_BULK_IMPORT, errorList);
			} else {
				result = new ResponseVo<Boolean>("0", null);
			}
		}
		return result;
	}
	
	/**
	 * 根据schoolId获取数据库所有表头项信息
	 * @param schoolId
	 * @return
	 */
	private List<PjTeacherSalaryField> getSalaryFieldList(Integer schoolId) {
		PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition = new PjTeacherSalaryFieldCondition();
		pjTeacherSalaryFieldCondition.setSchoolId(schoolId);
		List<PjTeacherSalaryField> fieldList = salaryFieldService
				.findPjTeacherSalaryFieldByCondition(pjTeacherSalaryFieldCondition);
		return fieldList;
	}
	
	/**
	 * 检查属性项名称,不存在则添加
	 * @param schoolId
	 * @param fieldInfo
	 * @return
	 */
	private List<PjTeacherSalaryField> checkSalaryField(Integer schoolId, String fieldInfo) {
		fieldInfo = fieldInfo.replace("，", ",");
		String[] split = fieldInfo.split(",");
		List<String> asList = Arrays.asList(split);
		List<PjTeacherSalaryField> fieldList = getSalaryFieldList(schoolId);
		Map<String, PjTeacherSalaryField> fieldMap = new HashMap<String, PjTeacherSalaryField>();
		List<PjTeacherSalaryField> orderedFieldList = new ArrayList<PjTeacherSalaryField>();
		for (PjTeacherSalaryField pjTeacherSalaryField : fieldList) {
			fieldMap.put(pjTeacherSalaryField.getAttrName(),
					pjTeacherSalaryField);
		}
		for (String string : asList) {
			PjTeacherSalaryField field = null;
			if (fieldMap.containsKey(string)) {
				field = fieldMap.get(string);
			} else {
				// 不存在则添加
				PjTeacherSalaryField pjTeacherSalaryField = new PjTeacherSalaryField();
				pjTeacherSalaryField.setSchoolId(schoolId);
				pjTeacherSalaryField.setAttrName(string);
				field = salaryFieldService.add(pjTeacherSalaryField);
			}
			orderedFieldList.add(field);
		}
		return orderedFieldList;
	}

	@Override
	public Object getTeacherArchivesCount(Integer departmentId) {
		if(departmentId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("departmentId参数异常");
			info.setMsg("departmentId参数异常");
			info.setParam("departmentId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		Department department= departmentService.findDepartmentById(departmentId);
		if(department == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("不存在该部门");
			info.setMsg("departmentId参数异常");
			info.setParam("departmentId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		TeacherArchiveVolist teacherArchiveVo = teacherService.getTeacherArchiveMess(departmentId);
		if(teacherArchiveVo != null){
			List<TeacherArchiveVo> tavList = teacherArchiveVo.getFinish();
			if(tavList != null && tavList.size() > 0){
				for(TeacherArchiveVo tav : tavList){
					tav.setUserIcon(ImgUtil.getImgUrl(tav.getIconUUID()));
				}
			}
			List<TeacherArchiveVo> tavunList = teacherArchiveVo.getUnFinish();
			if(tavunList != null && tavunList.size() > 0){
				for(TeacherArchiveVo tav : tavunList){
					tav.setUserIcon(ImgUtil.getImgUrl(tav.getIconUUID()));
				}
			}
		}
		return new ResponseVo<TeacherArchiveVolist>("0", teacherArchiveVo);
	}
	
}
