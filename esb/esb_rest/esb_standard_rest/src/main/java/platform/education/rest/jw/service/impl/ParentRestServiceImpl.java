package platform.education.rest.jw.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.rest.bbx.service.vo.StudentInfo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.ParentRestService;
import platform.education.rest.jw.service.vo.ParentMsg;
import platform.education.rest.jw.service.vo.ParentStuMsg;
import platform.education.rest.jw.service.vo.StudentParentMsg;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

public class ParentRestServiceImpl implements ParentRestService{
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Override
	public Object getMyChild(String userId, String schoolId) {
		try {
			ParentStudentCondition condition = new ParentStudentCondition();
			condition.setParentUserId(Integer.parseInt(userId));
			List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(condition);
			List<StudentInfo> studentList = new ArrayList<StudentInfo>();
			StudentInfo studentInfo = null;
			for(ParentStudent ps : psList){
				Student student = this.studentService.findStudentByUserId(ps.getStudentUserId());
				if(student != null){
					studentInfo = new StudentInfo(student.getUserId(), student.getName(), ImgUtil.getImgSrc(student.getUserId(), profileService));
					studentList.add(studentInfo);
				}
			}
			return new ResponseVo<List<StudentInfo>>("0",studentList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("用户Id转换异常...");
			info.setMsg("参数出错");
			info.setParam("user");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getStudentAtSchoolMsg(String userId,String appKey) {
		try {
			ParentStudentCondition parent = new ParentStudentCondition();
 			parent.setParentUserId(Integer.parseInt(userId));
			List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByCondition(parent);
			Student student = null;
			SchoolInfo school = null;
			Grade grade = null;
			ParentMsg msg = null;
			Person person = null;//person档案
			List<ParentMsg> msgList = new ArrayList<ParentMsg>();
			for(ParentStudent parentStudent : parentStudentList){
				student = studentService.findStudentByUserId(parentStudent.getStudentUserId());
				if(student != null){
					person = personService.findPersonById(student.getPersonId());

					msg = new ParentMsg();
					msg.setUserId(student.getUserId());
					msg.setName(student.getName());
					msg.setSchoolId(student.getSchoolId());
//					msg.setTeamId(student.getTeamId());
//					msg.setTeamName(student.getTeamName());	
					msg.setSex(student.getSex());
					
					school = schoolService.findSchoolInfoById(student.getSchoolId());
					if(school != null){
						msg.setSchoolName(school.getName());					
					}
					
//					TeamStudent ts = teamStudentService.findUnique(student.getTeamId(), student.getId());
//					if(ts != null){
//						msg.setGradeId(ts.getGradeId());
//					}
					//学生班级不直接获取，根据学校的当前学年获取
					Team team = teamService.findCurrentTeamOfStudent(student.getUserId(), student.getSchoolId());
					msg.setTeamId(team.getId());
					msg.setTeamName(team.getName());
					msg.setGradeId(team.getGradeId());
					
					grade = gradeService.findGradeById(team.getGradeId());
					if(grade != null){
						msg.setGradeName(grade.getName());
					}
					
					if(person != null && person.getPhotoUuid() != null && !"".equals(person.getPhotoUuid())){//根据person的Uuid获得档案照片url
						msg.setArchivesIcon(FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(person.getPhotoUuid()));
					}else{
						msg.setArchivesIcon("");
					}
					
					msg.setIcon(ImgUtil.getImgSrc(student.getUserId(), profileService));
					msgList.add(msg);
				}
			}
			return new ResponseVo<List<ParentMsg>>("0",msgList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询错误");
			info.setMsg("参数出错");
			info.setParam("userId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getStudentParentMsg(String userId, String appKey) {
		try {
			List<ParentStudent> studentParentList = parentStudentService.findParentStudentByStudentUserId(Integer.parseInt(userId));
			Student student = null;
			StudentParentMsg msg = null;
			Parent parent = null;
			List<StudentParentMsg> studentParentMsg = new ArrayList<StudentParentMsg>();
			for(ParentStudent parentStudent : studentParentList){
				msg = new StudentParentMsg();
				
				student = studentService.findStudentByUserId(Integer.parseInt(userId));
				if(student != null){
					msg.setUserId(student.getUserId());//学生ID
				}
				if(parentStudent != null){
					msg.setRelation(parentStudent.getParentRelation());//与子女的关系
					msg.setRank(parentStudent.getRank());//0=普通 1=主监护人
				}
				
				parent = this.parentService.findUniqueByUserId(parentStudent.getParentUserId());
				if(parent != null){
					msg.setParentId(parent.getUserId());//家长ID					
					msg.setName(parent.getName());//家长姓名
					msg.setMobile(parent.getMobile());//家长手机号码
				}
				msg.setIcon(ImgUtil.getImgSrc(parent.getUserId(), profileService));
			}
			studentParentMsg.add(msg);
			
			return new ResponseVo<List<StudentParentMsg>>("0",studentParentMsg);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询错误");
			info.setMsg("参数出错");
			info.setParam("userId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object getStudentAtSchoolByJSONP(Integer userId, String jsonpCallback) {

		try{
			if(jsonpCallback == null || "".equals(jsonpCallback)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ParentStudentCondition parent = new ParentStudentCondition();
 			parent.setParentUserId(userId);
			List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByCondition(parent);
			Student student = null;
			SchoolInfo school = null;
			Grade grade = null;
			
			List<ParentStuMsg> msgList = new ArrayList<ParentStuMsg>();
			ParentStuMsg msg = null;
			for(ParentStudent parentStudent : parentStudentList){
				student = studentService.findStudentByUserId(parentStudent.getStudentUserId());
				if(student != null){
					msg = new ParentStuMsg();
					msg.setUserId(student.getUserId());
					msg.setName(student.getName());
					msg.setSchoolId(student.getSchoolId());
//					msg.setTeamId(student.getTeamId());
//					msg.setTeamName(student.getTeamName());	
					msg.setStudentId(student.getId());
					
					school = schoolService.findSchoolInfoById(student.getSchoolId());
					if(school != null){
						msg.setSchoolName(school.getName());					
					}
					
//					TeamStudent ts = teamStudentService.findUnique(student.getTeamId(), student.getId());
//					if(ts != null){
//						msg.setGradeId(ts.getGradeId());
//					}
					Team team = teamService.findCurrentTeamOfStudent(student.getUserId(), student.getSchoolId());
					msg.setTeamId(team.getId());
					msg.setTeamName(team.getName());
					msg.setGradeId(team.getGradeId());
					
					grade = gradeService.findGradeById(team.getGradeId());
					if(grade != null){
						msg.setGradeName(grade.getName());
					}
					
					msg.setIcon(ImgUtil.getImgSrc(student.getUserId(), profileService));
					
					SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(student.getSchoolId());
					msg.setTermCode(schoolTermCurrent.getSchoolTermCode());
					Integer number = Integer.valueOf(schoolTermCurrent.getSchoolYear().substring(2));
					String n1 = "";
					String n2 = "";
					if(number < 9){
						n1 = 0+String.valueOf(number);
						n2 = 0+String.valueOf(number+1);
					}else if(number == 9){
						n1 = 0+String.valueOf(number);
						n2 = String.valueOf(number+1);
					}else if(number == 99){
						n1 = String.valueOf(number);
						n2 ="00";
					}else{
						n1 = String.valueOf(number);
						n2 = String.valueOf(number+1);
					}
					String[] code = schoolTermCurrent.getSchoolTermCode().split("-");
					Integer tn = Integer.valueOf(code[2]);
					String termName = "";
					if(tn == 1){
						termName = "上学期";
					}else if(tn == 2){
						termName = "下学期";
					}else if(tn == 3){
						termName = "夏季学期";
					}else if(tn == 5){
						termName = "寒假";
					}else if(tn == 6){
						termName = "暑假";
					}
					msg.setTermName(n1+"-"+n2+"学年"+termName);
					msg.setBeginDate(sdf.format(schoolTermCurrent.getBeginDate()));
					msg.setFinishDate(sdf.format(schoolTermCurrent.getFinishDate()));
					
					msgList.add(msg);
				}
			}
			
			String json = JSON.toJSONString(msgList);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询错误");
			info.setMsg("参数出错");
			info.setParam("userId");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR,info) + ")";
		}
	}

}
