package platform.education.rest.basic.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.rest.basic.service.TeamRestService;
import platform.education.rest.basic.service.vo.ArchiveSummary;
import platform.education.rest.basic.service.vo.ArchiveSummaryList;
import platform.education.rest.basic.service.vo.ExtTeamStudentVo;
import platform.education.rest.basic.service.vo.Parents;
import platform.education.rest.basic.service.vo.PersonNumber;
import platform.education.rest.basic.service.vo.TeamParent;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.constant.PersonSex;
import platform.education.rest.util.ImgUtil;
import platform.education.rest.util.NameUtil;
import platform.education.user.service.ProfileService;

public class TeamRestServiceImpl implements TeamRestService{
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;

	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;

	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;

	@Autowired
	@Qualifier("subjectGradeService")
	private SubjectGradeService subjectGradeService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;


	@Override
	public Object getSexNumber(String teamId) {
		try {
			List<Student> studentList = this.studentService.findStudentByTeamId(Integer.parseInt(teamId));
			PersonNumber personNum = new PersonNumber();
			for(Student stu : studentList){
				if(PersonSex.MALE.equals(stu.getSex())){
					personNum.addBoy();
				}
				if(PersonSex.FEMALE.equals(stu.getSex())){
					personNum.addGirl();
				}
				if(PersonSex.UNKNOWN.equals(stu.getSex())){
					personNum.addUnknow();
				}
				if(PersonSex.UNSECIFIED.equals(stu.getSex())){
					personNum.addUnsecified();
				}
			}
			return new ResponseVo<PersonNumber>("0",personNum);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("班级Id转换异常...");
			info.setMsg("参数格式出错");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getArchiveSummary(Integer teamId){
		ArchiveSummaryList asl = null;
		try{
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数不能为空");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			ArchiveSummary archiveSummary = null;
			List<ArchiveSummary> finished = null;
			List<ArchiveSummary> unfinished = null;
			List<StudentVo> finish = studentService.findArchiveSummary(teamId,true);
			List<StudentVo> unfinish = studentService.findArchiveSummary(teamId,false);
			
			if(finish != null && finish.size() > 0){
				finished = new ArrayList<ArchiveSummary>();
				for(StudentVo student : finish){
					archiveSummary = new ArchiveSummary();
					if(student != null && student.getId() != null){
						archiveSummary.setStudentId(student.getId());
						archiveSummary.setName(student.getName());
						archiveSummary.setNumber(student.getNumber() == null ? "" : student.getNumber()+"");
						archiveSummary.setSex(student.getSex());
						archiveSummary.setUserId(student.getUserId());
						archiveSummary.setUserIcon(ImgUtil.getImgSrc(student.getUserId(), profileService));
						finished.add(archiveSummary);
					}
				}
			}
			
			if(unfinish != null && unfinish.size() > 0){
				unfinished = new ArrayList<ArchiveSummary>();
				for(StudentVo student : unfinish){
					archiveSummary = new ArchiveSummary();
					if(student != null && student.getId() != null){
						archiveSummary.setStudentId(student.getId());
						archiveSummary.setName(student.getName());
						archiveSummary.setNumber(student.getNumber() == null ? "" : student.getNumber()+"");
						archiveSummary.setSex(student.getSex());
						archiveSummary.setUserId(student.getUserId());
						archiveSummary.setUserIcon(ImgUtil.getImgSrc(student.getUserId(), profileService));
						unfinished.add(archiveSummary);
					}
				}
			}
			
			if(finished != null || unfinished != null){
				 asl = new ArchiveSummaryList();
				 asl.setFinished(finished);
				 asl.setUnfinished(unfinished);
			}
			if(asl != null){
				return new ResponseVo<ArchiveSummaryList>("0",asl);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("输入参数不合法");
			info.setMsg("输入参数不合法");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
		ResponseInfo info = new ResponseInfo();
		info.setDetail("没有获取到相关数据");
		info.setMsg("没有获取到相关数据");
		info.setParam("teamId");
		return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
	}


	@Override
	public Object getGradeStudentParentMsg(String teamId, String appKey) {
		try {
			List<Student> studentList = studentService.findStudentByTeamId(Integer.parseInt(teamId));//通过班级ID找到该学生
			
			List<TeamParent> teamParentList = new ArrayList<TeamParent>();//学生信息List
			TeamParent teamParent = null;//学生信息实体类
			List<Parents> parentsList = null;//家长信息list
			Parents parents = null;//家长信息实体类
			
			Parent parent = null;
			
			List<ParentStudent> parentstudentList = null;//ParentStudent表的实体类
			
			for(Student student: studentList){//遍历所有学生
				teamParent = new TeamParent();//每次获取到的数据都实例一个学生信息放进去
				parentsList = new ArrayList<Parents>();//每次获取到所有家长信息都实例一个家长信息List放进去
				//得到学生信息
				teamParent.setId(student.getUserId());
				teamParent.setName(student.getName());
				//通过StudentUserId在ParentStudent表找该学生的所有父母
				parentstudentList = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
				for(ParentStudent parentStudent : parentstudentList){//遍历该学生的所有父母
					parents = new Parents();//每次获取到的数据都实例一个家长信息放进去
					//通过家长ID找家长信息
					parent = this.parentService.findUniqueByUserId(parentStudent.getParentUserId());
					if(parent != null){
						parents.setId(parent.getUserId());//家长ID					
						parents.setName(parent.getUserName());//家长姓名
						parents.setMobile(parent.getMobile());//家长手机号码
					}
					if(parentStudent != null){
						parents.setRelation(parentStudent.getParentRelation());//与子女的关系
						parents.setRank(parentStudent.getRank());//0=普通 1=主监护人
					}
					parentsList.add(parents);
				}
				teamParent.setParents(parentsList);
				teamParentList.add(teamParent);
			}
			
			return new ResponseVo<List<TeamParent>>("0",teamParentList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询错误");
			info.setMsg("参数出错");
			info.setParam("userId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	@Override
	public Object getStudentCompleteList(Integer teamId){
		List<StudentArchiveComplete> studentArchiveCompleteList = new ArrayList<StudentArchiveComplete>();
		try{
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			List<Student> studentList = studentService.findStudentByTeamId(teamId);
			
			if(studentList == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到学生数据");
				info.setMsg("找不到学生数据");
				info.setParam("userId，schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			//获取学生完整学籍档案
			StudentArchiveComplete studentArchiveComplete = null;
			for(Student student : studentList){
				studentArchiveComplete = studentService.getStudentArchiveComplete(student.getId());
				studentArchiveCompleteList.add(studentArchiveComplete);
			}
			
			if(studentArchiveCompleteList.size() > 0){
				return new ResponseVo<List<StudentArchiveComplete>>("0", studentArchiveCompleteList);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("查不到学生档案数据");
				info.setMsg("查询结果为空");
				info.setParam("studentId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("studentId参数异常");
			info.setMsg("studentId参数异常");
			info.setParam("studentId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
	}

	@Override
	public Object getTeamStudentList(Integer teamId, String jsonpCallback) {
		List<TeamStudentVo> teamStudentVoList = new ArrayList<TeamStudentVo>();
		List<ExtTeamStudentVo> teamStudents = new ArrayList<ExtTeamStudentVo>();
		try{
			if(jsonpCallback == null || "".equals(jsonpCallback)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			Student student = null;
			teamStudentVoList = this.teamStudentService.getTeamStudentsByTeamId(teamId);
			for (TeamStudentVo vo : teamStudentVoList) {
				String url = ImgUtil.getImgSrc(vo.getUserId(), profileService);
				student = studentService.findStudentById(vo.getStudentId());
				
				ExtTeamStudentVo teamStudentVo = new ExtTeamStudentVo();
				teamStudentVo.setAlias(student == null ? null : student.getAlias());
				teamStudentVo.setId(vo.getUserId());
				teamStudentVo.setStudentId(vo.getStudentId());
				teamStudentVo.setName(vo.getName());
				teamStudentVo.setSex(vo.getSex());
				teamStudentVo.setNumber(vo.getNumber());
				teamStudentVo.setStudentNumber(vo.getStudentNumber());
				teamStudentVo.setUserIcon(url);
				teamStudentVo.setUserName(vo.getUserName());
				teamStudents.add(teamStudentVo);
			}
			
			String json = JSON.toJSONString(teamStudents);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("teamId参数异常");
			info.setMsg("teamId参数异常");
			info.setParam("teamId");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";
		}
	}

	@Override
	public Object getAllTeam(Integer schoolId, String schoolYear) {
		if(schoolId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数不能为空");
			info.setMsg("参数错误");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		if(schoolYear == null || "".equals(schoolYear)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolYear参数不能为空");
			info.setMsg("参数错误");
			info.setParam("schoolYear");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		List<Object> list = null;
		try {
			list = new ArrayList<>();
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
			Map<String, Object> gradeMap = null;
			Map<String, Object> teamMap = null;
			List<Team> teamList = null;
			if (gradeList != null && gradeList.size() > 0) {
                for (Grade grade : gradeList) {
                    gradeMap = new HashMap<>();
                    gradeMap.put("gradeId", grade.getId());
                    gradeMap.put("gradeName", grade.getName());
                    gradeMap.put("gradeCode", grade.getUniGradeCode());

                    List<Object> teams = new ArrayList<>();
                    teamList = teamService.findTeamOfGrade(grade.getId());
                    if (teamList != null && teamList.size() >0) {
                        for (Team team : teamList) {
							teamMap = new HashMap<>();
                            teamMap.put("teamId", team.getId());
                            teamMap.put("teamName", team.getName());
                            teamMap.put("name", NameUtil.getAbbrTeamName(team.getName(), team.getTeamNumber()));
                            teamMap.put("teamNumber", team.getTeamNumber());
                            teams.add(teamMap);
                        }
                    }
                    gradeMap.put("teamList", teams);
                    list.add(gradeMap);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
		}
		return new ResponseVo<Object>("0", list);
	}

	@Override
	public Object getAllTeamOfTeacher(Integer schoolId, String schoolYear, Integer userId) {
		if(schoolId == null){
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					new ResponseInfo("参数错误", "schoolId参数不能为空", "schoolId"));
		}
		if(schoolYear == null || "".equals(schoolYear)){
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					new ResponseInfo("参数错误", "schoolYear参数不能为空", "schoolYear"));
		}
		if(userId == null){
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					new ResponseInfo("参数错误", "userId参数不能为空", "userId"));
		}

		List<Object> list = new ArrayList<>();

		Teacher teacher = teacherService.findOfUser(schoolId, userId);
		if (teacher == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("无匹配的教师");
			info.setMsg("找不到数据");
			info.setParam("userId,schoolId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}

		try {
			Map<String, String> subjects = getNoRepeatSubject(schoolId, schoolYear, teacher.getId());
			for (String subjectCode : subjects.keySet()) {
                String subjectName = subjects.get(subjectCode);
                Map<String, Object> subjectMap = getSubjectMap(schoolId, schoolYear, teacher.getId(), subjectCode, subjectName);
                list.add(subjectMap);
            }
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
		}
		return new ResponseVo<Object>("0", list);
	}


	//获取班主科目（去重）
	private Map<String, String> getNoRepeatSubject(Integer schoolId, String schoolYear, Integer teacherId){
		Map<String, String> map = new HashMap<>();
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setSchoolId(schoolId);
		condition.setSchoolYear(schoolYear);
		condition.setTeacherId(teacherId);
		condition.setType(1);
		List<TeamTeacherVo> grades = teamTeacherService.findTeamTeacherGradeByCondition(condition);
		if (grades != null && grades.size() > 0) {
			//班主任--根据年级获取科目
			for (TeamTeacherVo vo : grades) {
				Grade grade = gradeService.findGradeById(vo.getGradeId());
				List<SubjectGrade> subjectGradeList = subjectGradeService.findSubjectGradeByGradeCode(schoolId, grade.getUniGradeCode());
				for (SubjectGrade sg : subjectGradeList) {
					map.put(sg.getSubjectCode(), sg.getSubjectName());
				}
			}
		} else {
			//科任老师
			condition.setType(2);
			List<TeamTeacherVo> voList = teamTeacherService.findTeamTeacherVoByCondition(condition);
			if (voList != null && voList.size() > 0) {
				for (TeamTeacherVo vo : voList) {
					map.put(vo.getSubjectCode(), vo.getSubjectName());
				}
			}
		}
		return map;
	}

	//获取单科的年级、班级
	private Map<String, Object> getSubjectMap(Integer schoolId, String schoolYear, Integer teacherId, String subjectCode, String subjectName){
		Map<String, Object> map = new HashMap<>();
		//教师所教的班级，未去重
//		TeamTeacherCondition condition = new TeamTeacherCondition();
//		condition.setSchoolId(schoolId);
//		condition.setSchoolYear(schoolYear);
//		condition.setTeacherId(teacherId);
//		List<TeamTeacherVo> voList = teamTeacherService.findTeamTeacherVoByCondition(condition);
		List<TeamTeacherVo> voList = teamTeacherService.findTeamOrGradeOfTeacher(schoolId, schoolYear, null, teacherId, null, null);

		if (voList != null && voList.size() > 0) {
			//获取年级	map存储(key:gradeId, value:gradeName)
			Map<Integer, Object> gMap = new HashMap<>();
			for (TeamTeacherVo vo : voList) {
				if (subjectCode.equals(vo.getSubjectCode()) || vo.getType() == 1) {
					gMap.put(vo.getGradeId(), vo.getGradeName() + "#" + vo.getGradeCode());
				}
			}

			List<Object> gradeList = new ArrayList<>();
			for (Integer gradeId : gMap.keySet()) {
				Map<String, Object> gradeMap = new HashMap<>();

				List<Object> teamList = new ArrayList<>();
				//遍历班级，科目和年级相同放在同一个list中
				String teamIds = "";
				for (TeamTeacherVo vo : voList) {
					if ((subjectCode.equals(vo.getSubjectCode()) || vo.getType() == 1) && gradeId.equals(vo.getGradeId())) {
						//班级id数组中不包含该teamId的才录入，用于去重
						if (teamIds.indexOf(String.valueOf(vo.getTeamId())) == -1) {
							Map<String, Object> teamMap = new HashMap<>();
							teamMap.put("teamId", vo.getTeamId());
							teamMap.put("teamName", vo.getTeamName());
							teamMap.put("teamNumber", vo.getTeamNumber());
							teamMap.put("name", NameUtil.getAbbrTeamName(vo.getTeamName(), vo.getTeamNumber()));
							teamList.add(teamMap);
							teamIds += vo.getTeamId() + ",";
						}
					}
				}
				String[] strings = gMap.get(gradeId).toString().split("#");

				gradeMap.put("gradeId", gradeId);
				gradeMap.put("gradeName", strings[0]);
				gradeMap.put("gradeCode", strings[1]);
				gradeMap.put("teamList", teamList);
				gradeList.add(gradeMap);
			}

			map.put("subjectCode", subjectCode);
			map.put("subjectName", subjectName);
			map.put("gradeList", gradeList);
		}

		//对年级、班级排序
		List<Map<String, Object>> gradeList = (List<Map<String, Object>>) map.get("gradeList");
		sortList(gradeList);
		map.put("gradeList", gradeList);

		return map;
	}


	private void sortList(List<Map<String, Object>> list) {
		sortGrade(list);
		for (Map<String, Object> gradeMap : list) {
			List<Map<String, Object>> teamList = (List<Map<String, Object>>) gradeMap.get("teamList");
			sortTeam(teamList);
		}
	}

	private void sortGrade(List<Map<String, Object>> list){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer code1 = Integer.valueOf((String) o1.get("gradeCode"));
				Integer code2 = Integer.valueOf((String) o2.get("gradeCode"));
				if (code1 > code2) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}

	private void sortTeam(List<Map<String, Object>> list){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer n1 = (Integer) o1.get("teamNumber");
				Integer n2 = (Integer) o2.get("teamNumber");
				if (n1 > n2) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}



	@Override
	public Object getStudentList(String teamIds) {
		if(teamIds == null || "".equals(teamIds)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("teamId参数不能为空");
			info.setMsg("参数错误");
			info.setParam("teamIds");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		List<Object> list = new ArrayList<>();
		try {
			String[] teamIdStr = teamIds.split(",");
			Integer teamId = null;
			for (String str : teamIdStr) {
                teamId = Integer.parseInt(str);
                List<Object> studentList = new ArrayList<>();
                Team team = teamService.findTeamById(teamId);
                List<TeamStudentVo> teamStudentVoList = teamStudentService.getTeamStudentsByTeamId(teamId);
                if (teamStudentVoList != null && teamStudentVoList.size() > 0) {
                    for (TeamStudentVo vo : teamStudentVoList) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("userId", vo.getUserId());
                        map.put("studentId", vo.getStudentId());
                        map.put("studentName", vo.getName());
                        map.put("sex", vo.getSex());
                        map.put("number", vo.getNumber());
                        map.put("studentNumber", vo.getStudentNumber());
                        studentList.add(map);
                    }
                }

                Map<String, Object> teamMap = new HashMap<>();
                teamMap.put("teamId", teamId);
                teamMap.put("teamName", team.getName());
                teamMap.put("studentList", studentList);
                list.add(teamMap);

            }
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
		}

		return new ResponseVo<Object>("0", list);
	}
}
