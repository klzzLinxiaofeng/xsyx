package platform.education.rest.open.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.GradeVo;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.generalcode.service.JcCacheService;
import platform.education.rest.common.ResponseDto;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.open.service.BasicSingleRestService;
import platform.education.rest.open.service.util.VerificationUtil;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.Profile;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.education.user.service.ProfileService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;

public class BasicSingleRestServiceImpl implements BasicSingleRestService{

	@Autowired
    @Qualifier("jcCacheService")
    private JcCacheService jcCacheService;
	
	@Autowired
    @Qualifier("schoolService")
    private SchoolService schoolService;
	
	@Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
	
	@Autowired
    @Qualifier("teamService")
    private TeamService teamService;
	
	@Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
	
	@Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
	
	@Autowired
    @Qualifier("userService")
    private UserService userService;
	
	@Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
	
	@Autowired
    @Qualifier("roleService")
    private RoleService roleService;
	
	@Autowired
    @Qualifier("userRoleService")
    private UserRoleService userRoleService;
	
	@Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;
	
	@Autowired
    @Qualifier("departmentTeacherService")
    private DepartmentTeacherService departmentTeacherService;
	
	@Autowired
    @Qualifier("schoolTermCurrentService")
    private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;
	
	@Autowired
    @Qualifier("personService")
    private PersonService personService;
	
	@Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
	
	@Autowired
    @Qualifier("studentService")
    private StudentService studentService;
	
	@Autowired
    @Qualifier("parentService")
    private ParentService parentService;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	@Override
	public Object getSchool(String sign, String appKey, String timeStamp, Integer schoolId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "学校id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
//        List<Object> list = new ArrayList<>();\
        Object obj = null;
        try{
        	School school = schoolService.findSchoolById(schoolId);
        	if(school != null){
        		//转map存进list
        		obj = addSchoolList(school);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}
	
	/**
     *  获取地区码对应的地区名
     */
    private String getRegionName(String code) {
        String name = "";
        if (code != null && !"".equals(code)) {
            code = String.valueOf(jcCacheService.findById("jc_region", code, "name"));
        }
        if (code != null) {
            name = ", " + code;
        }
        return name;
    }

    private Object addSchoolList(School school) {
    	String stageScope = school.getStageScope();
        stageScope = stageScope.replaceAll("-1,|,-1", "");
        String province = getRegionName(school.getProvince());
        String city = getRegionName(school.getCity());
        String district = getRegionName(school.getDistrict());
        String area = province + city + district;
        area = area.substring(2, area.length());
        
    	Map<String, Object> map = new HashMap<>();
        map.put("schoolId", school.getId());
        map.put("schoolName", school.getName());
        map.put("stageScope", stageScope);
        map.put("area", area);
        map.put("address", school.getAddress());
        map.put("schoolType", school.getSchoolType());
        map.put("schoolBadge", ImgUtil.getImgUrl(school.getEntityId_badge()));
        map.put("modifiedTime", sdf.format(school.getModifyDate()));
    	
        return map;
	}

    
	@Override
	public Object getGrade(String sign, String appKey, String timeStamp, Integer gradeId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_gradeId = VerificationUtil.judgeNull(gradeId, "gradeId", "年级id");
        if (o_gradeId != null) {
            return o_gradeId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	Grade grade = gradeService.findGradeById(gradeId);
        	if(grade != null){
        		obj = addGradeList(grade);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addGradeList(Grade grade) {
		Map<String, Object> map = new HashMap<>();
		map.put("gradeId", grade.getId());
        map.put("gradeName", grade.getName());
        map.put("stageCode", grade.getStageCode());
        map.put("gbGradeCode", grade.getUniGradeCode());
        map.put("gradeCode", getGradeCode(grade.getSchoolYear(), grade.getUniGradeCode()));
        map.put("schoolYear", grade.getSchoolYear());
        map.put("modifiedTime", sdf.format(grade.getModifyDate()));
        return map;
	}

	private String getGradeCode(String schoolYear, String uniGradeCode){
        String gradeCode = "";
        int year = Integer.parseInt(schoolYear);
        int code = Integer.parseInt(uniGradeCode);
        if (code >= 21 && code <=26) {
            gradeCode = "X" + String.valueOf(year - (code - 21)).substring(2);
        } else if (code >= 31 && code <= 33){
            gradeCode = "C" + String.valueOf(year - (code - 31)).substring(2);
        } else if (code >= 41 && code <= 43){
            gradeCode = "G" + String.valueOf(year - (code - 41)).substring(2);
        }
        return gradeCode;
    }

	@Override
	public Object getTeam(String sign, String appKey, String timeStamp, Integer teamId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_teamId = VerificationUtil.judgeNull(teamId, "teamId", "班级id");
        if (o_teamId != null) {
            return o_teamId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	Team team = teamService.findTeamById(teamId);
        	if(team != null){
        		obj = addTeamList(team);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addTeamList(Team team) {
		Grade grade = gradeService.findGradeById(team.getGradeId());
		Map<String, Object> map = new HashMap<>();
		map.put("teamId", team.getId());
        map.put("teamName", team.getName());
        map.put("teamCode", team.getCode2());
        map.put("gradeId", team.getGradeId());
        map.put("gradeCode", getGradeCode(grade.getSchoolYear(), grade.getUniGradeCode()));
        map.put("gbGradeCode", grade.getUniGradeCode());
        map.put("schoolYear", team.getSchoolYear());
        map.put("modifiedTime", sdf.format(team.getModifyDate()));
        return map;
	}

	@Override
	public Object getDepartment(String sign, String appKey, String timeStamp, Integer departmentId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_departmentId = VerificationUtil.judgeNull(departmentId, "departmentId", "部门id");
        if (o_departmentId != null) {
            return o_departmentId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	Department department = departmentService.findDepartmentById(departmentId);
        	if(department != null){
        		obj = addDepartmentList(department);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addDepartmentList(Department department) {
		Map<String, Object> map = new HashMap<>();
        map.put("schoolId", department.getSchoolId());
		map.put("departmentId", department.getId());
        map.put("departmentName", department.getName());
        map.put("listOrder", department.getListOrder());
        map.put("parentId", department.getParentId());
        map.put("modifiedTime", sdf.format(department.getModifyDate()));
        return map;
	}

	@Override
	public Object getSubject(String sign, String appKey, String timeStamp, Integer schoolId, Integer subjectCode) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "学校id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        Object o_subjectCode = VerificationUtil.judgeNull(subjectCode, "subjectCode", "科目code值");
        if (o_subjectCode != null) {
            return o_subjectCode;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	SubjectCondition condition = new SubjectCondition();
        	condition.setSchoolId(schoolId);
        	condition.setCode(String.valueOf(subjectCode));
        	List<Subject> subjectList = subjectService.findSubjectByCondition(condition, null, null);
        	if(subjectList != null && subjectList.size() > 0){
        		obj = addSubjectList(subjectList);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addSubjectList(List<Subject> subjectList) {
		Map<String, Object> map = new HashMap<>();
		for (Subject subject : subjectList) {
            map.put("subjectId", subject.getId());
            map.put("subjectName", subject.getName());
            map.put("subjectCode", subject.getCode());
            map.put("subjectClass", subject.getSubjectClass());
            map.put("listOrder", subject.getListOrder() != null ? subject.getListOrder() : "");
            map.put("stageCode", subject.getStageCode());
            map.put("modifiedTime", sdf.format(subject.getModifyDate()));
            return map;
		}
		return map;
	}

	@Override
	public Object getAccount(String sign, String appKey, String timeStamp, Integer userId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_userId = VerificationUtil.judgeNull(userId, "userId", "用户id");
        if (o_userId != null) {
            return o_userId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	User user = userService.findUserById(userId);
        	if(user != null){
        		obj = addUserList(user);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addUserList(User user) {
		Profile profile = profileService.findByUserId(user.getId());
		String nickName = "";
		String icon = "";
        if (profile != null) {
            nickName = profile.getNickname() != null ? profile.getNickname() : "";
            icon = ImgUtil.getImgUrl(profile.getIcon());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("userName", user.getUserName());
        map.put("nickname", nickName);
        map.put("icon", icon);
        map.put("state", user.getState());
        map.put("modifiedTime", sdf.format(user.getModifyDate()));
        
        //设角色的list
        List<Object> roleList = new ArrayList<>();
        List<UserRole> userRole = userRoleService.findByUserId(user.getId());
        if(userRole != null){
        	for (UserRole ur : userRole) {
        		Role role = ur.getRole();
        		Map<String, Object> roleMap = new HashMap<>();
        		roleMap.put("type", role.getUserType());
        		roleMap.put("roleId", role.getId());
        		roleMap.put("roleCode", role.getCode());
        		roleList.add(roleMap);
        	}
        }
        map.put("roleList", roleList);

        return map;
	}

	@Override
	public Object getTeacher(String sign, String appKey, String timeStamp, Integer userId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_userId = VerificationUtil.judgeNull(userId, "userId", "用户id");
        if (o_userId != null) {
            return o_userId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	User user = userService.findUserById(userId);
        	if(user == null){
        		return new ResponseVo<Object>("0", obj);
        	}
        	Integer schoolId = null;
            List<Teacher> teacherList = teacherService.findTeacherByUserId(userId);
            if (teacherList != null && teacherList.size() > 0) {
                schoolId = teacherList.get(0).getSchoolId();
            } else {
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据","该学校没有这个教师",""));
            }
            //获取当前学年
            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }
            obj = addTeacherList(user,schoolId, schoolYear);
            
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	/**
     * 判断是否有当前学期（学年）
     */
	private Object judgeSchoolTermCurrent(SchoolTermCurrent current){
        if (current == null) {
            ResponseInfo info = new ResponseInfo();
            info.setMsg("找不到数据");
            info.setDetail("该学校未设置当前学年");
            info.setParam("");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        } else {
            return null;
        }
    }
	
	private Object addTeacherList(User user, Integer schoolId, String schoolYear) {
		TeacherCondition condition = new TeacherCondition();
		condition.setUserId(user.getId());
		List<TeacherVo> teacherLi = teacherService.findTeacherVoByCondition(condition, null, null);
		TeacherVo teacher = null;
		if(teacherLi != null && teacherLi.size() > 0){
			teacher = teacherLi.get(0);
			if(teacher == null){
				return null;
			}
		}else {
			//此id没有对应的教师
			return null;
		}
		Person person = personService.findPersonById(teacher.getPersionId());
		String email = "";
		if(person != null){
			email = person.getEmail();
		}
		
		Map<String, Object> map = new HashMap<>();
        //基本信息
        map.put("userId", teacher.getUserId());
        map.put("name", teacher.getName());
        map.put("sex", teacher.getSex() != null && !"".equals(teacher.getSex()) ? teacher.getSex() : "0");
        map.put("mobile", teacher.getMobile() != null ? teacher.getMobile() : "");
        map.put("email", email != null ? email: "");
        map.put("modifiedTime", sdf.format(teacher.getModifyDate()));
        
        //部门信息
        List<Object> departList = new ArrayList<>();
        List<DepartmentTeacher> departmentTeachers = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(), schoolId);
        if(departmentTeachers != null){
        	for (DepartmentTeacher department : departmentTeachers) {
                Map<String, Object> departMap = new HashMap<>();
                if (department != null) {
                	departMap.put("departmentId", department.getDepartmentId());
                	departMap.put("departmentName", department.getDepartmentName());
                	departList.add(departMap);
				}
            }
        }
        map.put("departmentList", departList);
        
        //班级、科目信息
        List<Object> teamList = new ArrayList<>();
        //查询条件
        TeamTeacherCondition ttCondition = new TeamTeacherCondition();
        ttCondition.setSchoolId(schoolId);
        ttCondition.setSchoolYear(schoolYear);
        ttCondition.setTeacherId(teacher.getId());
        ttCondition.setUserId(teacher.getUserId());
        List<TeamTeacherVo> teamTeacherVos = teamTeacherService.findVoWithSubjectInfo(ttCondition);
        
        if(teamTeacherVos != null){
        	for (TeamTeacherVo vo : teamTeacherVos) {
        		if(vo != null){
        			Map<String, Object> teamMap = new HashMap<>();
        			//teamList 的基本信息
        			teamMap.put("teamId", vo.getTeamId());
        			teamMap.put("teamCode", vo.getTeamCode());
        			teamMap.put("teamName", vo.getTeamName());
        			teamMap.put("schoolYear", schoolYear);
        			teamMap.put("type", getTeacherType(vo.getTypes()));
        			
        			//teamList中的subjectList信息集合
        			List<Object> subjectList = new ArrayList<>();
        			if (vo.getSubjectInfo() != null && !"".equals(vo.getSubjectInfo())) {
        				//查询科目信息集结果,返回切割
        				String[] subjectInfo = vo.getSubjectInfo().split(";");
        				for (String s : subjectInfo) {
        					String[] subject = s.split(",");
        					Map<String, Object> subjectMap = new HashMap<>();
        					subjectMap.put("subjectCode", subject[0]);
        					subjectMap.put("subjectName", subject[1]);
        					
        					//查询条件，查询subjectId的内容
        					SubjectCondition subjectCondition = new SubjectCondition();
        					subjectCondition.setCode(subject[0]);
        					subjectCondition.setName(subject[1]);
        					subjectCondition.setSchoolId(schoolId);
        					List<Subject> list2 = subjectService.findSubjectByCondition(subjectCondition, null, null);
        					if(list2 !=null && list2.get(0) != null){
        						subjectMap.put("subjectId", list2.get(0).getId());
        						subjectList.add(subjectMap);
        					}
        				}
        			}
        			
        			teamMap.put("subjectList", subjectList);
        			teamList.add(teamMap);
        		}
        	}
        }
        map.put("teamList", teamList);
        
        return map;
	}
	
	private String getTeacherType(String types) {
        String type = "";
        if (types.contains("1") && types.contains("2")) {
            type = "3";
        } else if (types.contains("1")) {
            type = "1";
        } else if (types.contains("2")) {
            type = "2";
        }
        return type;
    }

	@Override
	public Object getStudent(String sign, String appKey, String timeStamp, Integer userId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_userId = VerificationUtil.judgeNull(userId, "userId", "用户id");
        if (o_userId != null) {
            return o_userId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	User user = userService.findUserById(userId);
        	if(user == null){
        		return new ResponseVo<Object>("0", obj);
        	}
        	Integer schoolId = null;
            Student student = studentService.findStudentByUserId(userId);
            if (student != null) {
                schoolId = student.getSchoolId();
            } else {
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, new ResponseInfo("找不到数据","该学校没有这个学生",""));
            }
            //获取当前学年
            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }
            obj = addStudentList(user,schoolId, schoolYear);
            
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addStudentList(User user, Integer schoolId, String schoolYear) {
		Student student = studentService.findStudentByUserId(user.getId());
		if(student == null){
			return null;
		}
		Person person = personService.findPersonById(student.getPersonId());
		String email = "";
		if(person != null){
			email = person.getEmail();
		}
		Team team = teamService.findTeamById(student.getTeamId());
		String teamCode = "";
		String teamName = "";
		if(team != null){
			teamCode = team.getCode2();
			teamName = team.getName();
		}
		Map<String, Object> map = new HashMap<>();
        map.put("userId", student.getUserId());
        map.put("name", student.getName());
        map.put("sex", student.getSex() != null && !"".equals(student.getSex()) ? student.getSex() : "0");
        map.put("email", email != null ? email : "");
        map.put("schoolYear", schoolYear);
        map.put("teamId", student.getTeamId());
        map.put("teamCode", teamCode);
        map.put("teamName", teamName);
        map.put("modifiedTime", sdf.format(student.getModifyDate()));

        return map;
	}

	@Override
	public Object getParent(String sign, String appKey, String timeStamp, Integer userId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_userId = VerificationUtil.judgeNull(userId, "userId", "用户id");
        if (o_userId != null) {
            return o_userId;
        }
//        List<Object> list = new ArrayList<>();
        Object obj = null;
        try{
        	obj = addParentList(userId);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", obj);
	}

	private Object addParentList(Integer userId) {
		ParentVo parent = parentService.findParentVoByUserId(userId);
		if(parent == null){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
        map.put("userId", parent.getUserId());
        map.put("name", parent.getName());
        map.put("sex", parent.getSex() != null && !"".equals(parent.getSex()) ? parent.getSex() : "0");
        map.put("mobile", parent.getMobile() != null ? parent.getMobile() : "");
        map.put("email", parent.getEmail() != null ? parent.getEmail() : "");
        map.put("modifiedTime", sdf.format(parent.getModifyDate()));

        List<Object> studentList = new ArrayList<>();
        if (parent.getStudentInfo() != null && !"".equals(parent.getStudentInfo())) {
            String[] studentInfo = parent.getStudentInfo().split(";");
            for (String str : studentInfo) {
                String[] info = str.split(",");
                Map<String, Object> stuMap = new HashMap<>();
                stuMap.put("studentUserId", info[0]);
                stuMap.put("studentName", info[1]);
                stuMap.put("parentRelation", info[2]);
                stuMap.put("rank", info[3]);
                studentList.add(stuMap);
            }
        }
        map.put("studentList", studentList);
        return map;
	}
	
	
}
