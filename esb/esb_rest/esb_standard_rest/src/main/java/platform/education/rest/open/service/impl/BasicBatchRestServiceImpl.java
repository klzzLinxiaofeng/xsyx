package platform.education.rest.open.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.generalcode.service.JcCacheService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.open.service.BasicBatchRestService;
import platform.education.rest.open.service.util.VerificationUtil;
import platform.education.user.model.User;
import platform.education.user.service.UserService;

public class BasicBatchRestServiceImpl implements BasicBatchRestService{

	@Autowired
    @Qualifier("jcCacheService")
    private JcCacheService jcCacheService;
	
	@Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
	
	@Autowired
    @Qualifier("teamService")
    private TeamService teamService;
	
	@Autowired
    @Qualifier("studentService")
    private StudentService studentService;
	
	@Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;
	
	@Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;
	
	@Autowired
    @Qualifier("parentService")
    private ParentService parentService;
	
	@Autowired
    @Qualifier("schoolTermCurrentService")
    private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;
	
	@Autowired
    @Qualifier("userService")
    private UserService userService;
	
	@Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
	
	@Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
	
	@Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
	
	@Autowired
    @Qualifier("departmentTeacherService")
    private DepartmentTeacherService departmentTeacherService;
	
	@Autowired
    @Qualifier("schoolTermService")
    private SchoolTermService schoolTermService;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	public Object findGrades(String sign, String appKey, String timeStamp, Integer schoolId, String schoolYear) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "学校id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        List<Object> list = new ArrayList<>();
        if(schoolId == 0){
        	schoolId = -1 ;
        }
        try{
        	if(schoolYear != null && !"".equals(schoolYear)){
        		/** 验证此学年是否存在 */
        		//判断有无当前学年
                SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
                Object o2 = judgeSchoolTermCurrent(current);
                if (o2 != null) {
                    return o2;
                } 
                //查询学校的学年
            	SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
            	schoolTermCondition.setSchoolId(schoolId);
            	schoolTermCondition.setSchoolYear(schoolYear);
            	List<SchoolTerm> SchoolTermList = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
            	if(SchoolTermList == null || SchoolTermList.size() == 0){
            		ResponseInfo info = new ResponseInfo();
                    info.setMsg("数据无效");
                    info.setDetail("学年值不能为null");
                    info.setParam("schoolYear");
                    return new ResponseError(CommonCode.S$INVALID_DATA, info);
            	}
            	
            	int a = 0;
            	for (SchoolTerm schoolTerm : SchoolTermList) {
    				if(schoolTerm.getSchoolYear().equals(schoolYear)){
    					a++;
    				}
    			}
            	if( a == 0){
            		return new ResponseError(CommonCode.S$INVALID_DATA, new ResponseInfo("数据无效", "学校不存在该学年", "schoolYear"));
            	}
        	}else {
        		/** 获取当前学年 */
                SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
                Object o2 = judgeSchoolTermCurrent(current);
                if (o2 != null) {
                    return o2;
                } else {
                    schoolYear = current.getSchoolYear();
                }
			}
        	
			GradeCondition gradeCondition = new GradeCondition();
        	gradeCondition.setSchoolId(schoolId);
        	gradeCondition.setSchoolYear(schoolYear);
        	List<Grade> gradeList = gradeService.findGradeByCondition(gradeCondition, null, null);
        	if(gradeList != null && gradeList.size() > 0){
        		addGradeList(gradeList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addGradeList(List<Grade> gradeList, List<Object> list) {
		for (Grade grade : gradeList) {
			Map<String, Object> map = new HashMap<>();
			map.put("gradeId", grade.getId());
	        map.put("gradeName", grade.getName());
	        map.put("stageCode", grade.getStageCode());
	        map.put("gbGradeCode", grade.getUniGradeCode());
	        if(grade.getUniGradeCode() != null && !"".equals(grade.getUniGradeCode())){
	        	map.put("gradeCode", getGradeCode(grade.getSchoolYear(), grade.getUniGradeCode()));
	        }else {
	        	map.put("gradeCode","");
			}
	        map.put("schoolYear", grade.getSchoolYear());
	        map.put("gradeNumber", grade.getGradeNumber());
	        map.put("modifiedTime", sdf.format(grade.getModifyDate()));
	        list.add(map);
		}
	}

	@Override
	public Object findTeams(String sign, String appKey, String timeStamp, Integer gradeId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_gradeId = VerificationUtil.judgeNull(gradeId, "gradeId", "年级id");
        if (o_gradeId != null) {
            return o_gradeId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	TeamCondition teamCondition = new TeamCondition();
        	teamCondition.setGradeId(gradeId);
        	List<Team> teamList = teamService.findTeamByCondition(teamCondition, null, null);
        	if(teamList != null && teamList.size() > 0){
        		addTeamList(teamList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addTeamList(List<Team> teamList, List<Object> list) {
		for (Team team : teamList) {
			Map<String, Object> map = new HashMap<>();
			map.put("teamId", team.getId());
	        map.put("teamName", team.getName());
	        map.put("teamCode", team.getCode2() != null ? team.getCode2() : "");
	        map.put("teamNumber", team.getTeamNumber());
	        map.put("schoolYear", team.getSchoolYear());
	        map.put("modifiedTime", sdf.format(team.getModifyDate()));
	        list.add(map);
		}
	}

	@Override
	public Object findStudents(String sign, String appKey, String timeStamp, Integer teamId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_teamId = VerificationUtil.judgeNull(teamId, "teamId", "班级id");
        if (o_teamId != null) {
            return o_teamId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	StudentCondition studentCondition = new StudentCondition();
        	studentCondition.setTeamId(teamId);
        	List<Student> studentList = studentService.findTeamStudentByCondition(studentCondition, null, null);
        	if(studentList != null && studentList.size() > 0){
        		addStudentList(studentList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addStudentList(List<Student> studentList, List<Object> list) {
		for (Student student : studentList) {
			Map<String, Object> map = new HashMap<>();
			map.put("userId", student.getUserId());
	        map.put("name", student.getName());
	        map.put("sex", student.getSex() != null && !"".equals(student.getSex()) ? student.getSex() : "0");
	        map.put("modifiedTime", sdf.format(student.getModifyDate()));
	        list.add(map);
		}
	}
	
	@Override
	public Object findTeachers(String sign, String appKey, String timeStamp, Integer teamId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_teamId = VerificationUtil.judgeNull(teamId, "teamId", "班级id");
        if (o_teamId != null) {
            return o_teamId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	List<TeacherVo> teacherList = teacherService.getAllSubjectTeachersByTeamId(teamId);
        	if(teacherList != null && teacherList.size() > 0){
        		addSubTeacherList(teacherList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addSubTeacherList(List<TeacherVo> teacherList, List<Object> list) {
		for (TeacherVo teacher : teacherList) {
			Map<String, Object> map = new HashMap<>();
	        //基本信息
			map.put("userId", teacher.getUserId());
	        map.put("name", teacher.getName());
	        map.put("type", getTeacherType(teacher.getTypes()));
	        map.put("sex", teacher.getSex() != null && !"".equals(teacher.getSex()) ? teacher.getSex() : "0");
	        map.put("modifiedTime", sdf.format(teacher.getModifyDate()));
	        //科目信息
	        List<Object> subjectList = new ArrayList<>();
            if (teacher.getSubjectInfo() != null && !"".equals(teacher.getSubjectInfo())) {
                String[] subjectInfo = teacher.getSubjectInfo().split(";");
                for (String s : subjectInfo) {
                    String[] subject = s.split(",");
                    Map<String, Object> subjectMap = new HashMap<>();
                    subjectMap.put("subjectCode", subject[0]);
                    subjectMap.put("subjectName", subject[1]);
                    subjectMap.put("subjectId", subject[2]);
                    subjectList.add(subjectMap);
                }
            }
	        map.put("subjectList", subjectList);
	        
	        list.add(map);
		}
		
	}
	
	@Override
	public Object findParents(String sign, String appKey, String timeStamp, Integer teamId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_teamId = VerificationUtil.judgeNull(teamId, "teamId", "班级id");
        if (o_teamId != null) {
            return o_teamId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	StudentCondition studentCondition = new StudentCondition();
        	studentCondition.setTeamId(teamId);
        	List<Student> studentList = studentService.findTeamStudentByCondition1(studentCondition, null, null);
        	if(studentList != null && studentList.size() > 0){
        		addParentList(studentList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addParentList(List<Student> studentList, List<Object> list) {
		for (Student student : studentList) {
			List<ParentVo> parentList = parentService.findParentsByStudentUserId(student.getUserId());
			if (parentList != null && parentList.size() > 0 ){
				for (ParentVo parent : parentList) {
					Map<String, Object> map = new HashMap<>();
			        map.put("userId", parent.getUserId());
			        map.put("name", parent.getName());
			        map.put("sex", parent.getSex() != null && !"".equals(parent.getSex()) ? parent.getSex() : "0");
			        map.put("modifiedTime", sdf.format(parent.getModifyDate()));
			        
			        list.add(map);
				}
			}
		}
	}
	
	@Override
	public Object findTeamsByteacher(String sign, String appKey, String timeStamp, Integer userId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_userId = VerificationUtil.judgeNull(userId, "userId", "用户id");
        if (o_userId != null) {
            return o_userId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	User user = userService.findUserById(userId);
        	if(user == null){
        		return new ResponseVo<Object>("0", list);
        	}
        	Integer schoolId = schoolUserService.findSchoolIdByUserName(user.getUserName());
        	//获取当前学年
            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }
        		addTeacherTeamList(user,list,schoolYear,schoolId);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addTeacherTeamList(User user, List<Object> list, String schoolYear, Integer schoolId) {
		TeacherCondition condition = new TeacherCondition();
		condition.setUserId(user.getId());
		List<TeacherVo> teacherLi = teacherService.findTeacherVoByCondition(condition, null, null);
		TeacherVo teacher = null;
		if(teacherLi != null && teacherLi.size() > 0){
			teacher = teacherLi.get(0);
			if(teacher == null){
				return;
			}
		}else {
			//此id没有对应的教师
			return;
		}
		
		Map<String, Object> map = new HashMap<>();
        //基本信息
        map.put("schoolYear", schoolYear);
        map.put("modifiedTime", sdf.format(teacher.getModifyDate()));
        
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
        list.add(map);
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
	public Object findDepartments(String sign, String appKey, String timeStamp, Integer schoolId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_schoolId = VerificationUtil.judgeNull(schoolId, "schoolId", "学校id");
        if (o_schoolId != null) {
            return o_schoolId;
        }
        List<Object> list = new ArrayList<>();
        try{
        	List<Department> departmentList = departmentService.findDepartmentBySchoolId(schoolId, null, null);
        	if(departmentList != null && departmentList.size() > 0){
        		addDepartmentList(departmentList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addDepartmentList(List<Department> departmentList, List<Object> list) {
		for (Department department : departmentList) {
			if(department != null){
				Map<String, Object> map = new HashMap<>();
				map.put("departmentId", department.getId());
				map.put("departmentName", department.getName());
				map.put("listOrder", department.getListOrder());
				map.put("parentId", department.getParentId());
				map.put("modifiedTime", sdf.format(department.getModifyDate()));
				list.add(map);
			}
		}
	}
	
	@Override
	public Object findMembersByDepartment(String sign, String appKey, String timeStamp, Integer departmentId) {
//		Object verify = VerificationUtil.verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Object o_departmentId = VerificationUtil.judgeNull(departmentId, "departmentId", "部门id");
        if (o_departmentId != null) {
            return o_departmentId;
        }
        List<Object> list = new ArrayList<>();
        try{
			Department department = departmentService.findDepartmentById(departmentId);
			if (department == null) {
				return new ResponseError(CommonCode.S$INVALID_DATA, new ResponseInfo("数据无效", "不存在该部门ID或该部门已被删除", "departmentId"));
			}

			List<TeacherVo> departmentTeacherList =  departmentTeacherService.findTeacherInfoByDepartmentId(departmentId);
        	if(departmentTeacherList != null && departmentTeacherList.size() > 0){
        		addDepartmentTeacherList(departmentTeacherList,list);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口异常", "接口调用发生错误", ""));
        }
        return new ResponseVo<Object>("0", list);
	}

	private void addDepartmentTeacherList(List<TeacherVo> departmentTeacherList, List<Object> list) {
		for (TeacherVo teacher : departmentTeacherList) {
			Map<String, Object> map = new HashMap<>();
	        map.put("userId", teacher.getUserId());
	        map.put("name", teacher.getName());
	        map.put("listOrder", teacher.getListOrder() != null && !"".equals(teacher.getListOrder()) ? teacher.getListOrder() : "");
	        map.put("sex", teacher.getSex() != null && !"".equals(teacher.getSex()) ? teacher.getSex() : "0");
	        map.put("modifiedTime", sdf.format(teacher.getModifyDate()));
	        list.add(map);
		}
	}

	
}

