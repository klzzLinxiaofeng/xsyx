package platform.education.rest.bp.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.Course;
import platform.education.clazz.model.CourseConfig;
import platform.education.clazz.model.CourseConfigDetail;
import platform.education.clazz.model.CourseStudent;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.*;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.rest.bp.service.BpCourseRestService;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.BpCourseStudentVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.service.storage.service.FileService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BpCourseRestServiceImpl implements BpCourseRestService{

	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;

	@Autowired
	@Qualifier("profileService")
	protected ProfileService profileService;

	@Autowired
	@Qualifier("userService")
	protected UserService userService;

	@Autowired
	@Qualifier("personService")
	protected PersonService personService;

	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;

	@Autowired
	@Qualifier("teamStudentService")
	protected TeamStudentService teamStudentService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("subjectGradeService")
	protected SubjectGradeService subjectGradeService;

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
	@Qualifier("courseStudentService")
	private CourseStudentService courseStudentService;

	@Autowired
	@Qualifier("courseConfigService")
	private CourseConfigService courseConfigService;

	@Autowired
	@Qualifier("courseConfigDetailService")
	private CourseConfigDetailService courseConfigDetailService;

	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;

	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;

	@Override
	public Object getCourseStudent(Integer schoolId, Integer userId, String appKey,String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(userId)) {
				return ResponseUtil.paramerIsNull("userId:"+userId);
			}
			if (StringUtils.isEmpty(schoolId)) {
				return ResponseUtil.paramerIsNull("schoolId:"+ schoolId);
			}*/
			SchoolTermCurrent stc = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Student student = this.studentService.findStudentByUserId(userId);
			TeamStudentCondition studentCondition = new TeamStudentCondition();
			studentCondition.setUserId(userId);
			studentCondition.setStudentId(student.getId());
			studentCondition.setInState(true);
			List<TeamStudent> list = this.teamStudentService.findTeamStudentByCondition(studentCondition,null,null);
			CourseConfigCondition configCondition = new CourseConfigCondition();
			configCondition.setGradeId(list.get(0).getGradeId());
			configCondition.setSchoolId(student.getSchoolId());
			configCondition.setTermCode(stc.getSchoolTermCode());
			configCondition.setIsDeleted(false);
			List<CourseConfig> configList = this.courseConfigService.findCourseConfigByCondition(configCondition);
			CourseConfig config = null;
			String prompt = "";
			CourseStudentCondition condition = new CourseStudentCondition();
			List<CourseConfigDetailVo> voList = new ArrayList<CourseConfigDetailVo>();
			BpCourseStudentVo courseStudentVo = new BpCourseStudentVo();
			Date date = new Date();
			CourseCondition courseCondition = new CourseCondition();
			courseCondition.setSchoolId(student.getSchoolId());
			courseCondition.setStartTerm(stc.getSchoolTermCode());
			courseCondition.setGradeId(list.get(0).getGradeId());
			List<Course> courses = this.courseService.findCourseByCondition(courseCondition);
			if(configList.size()>0){
				config = configList.get(0);
				if(config.getStatus().equals(0)){
					prompt = "暂无选课活动";
				}else if(config.getStatus().equals(1)){
					//获取选课科目组
					CourseConfigDetailCondition courseConfigDetailCondition = new CourseConfigDetailCondition();
					courseConfigDetailCondition.setCourseConfigId(config.getId());
					List<CourseConfigDetail> courseConfigDetailList = this.courseConfigDetailService
							.findCourseConfigDetailByCondition(courseConfigDetailCondition);
					if(courseConfigDetailList != null && courseConfigDetailList.size() > 0){
						for(CourseConfigDetail c: courseConfigDetailList){
							CourseConfigDetailVo vo = new CourseConfigDetailVo();
							PropertyUtils.copyProperties(vo,c);
							if(date.after(config.getSignupFinishDate())){
								prompt = "已超过选课时间！";
								vo.setDisalbed(true);
							}else if(date.before(config.getSignupStartDate())){
								prompt = "选课未开始！";
								vo.setDisalbed(true);
							}
							//判断是否已选过课
							condition.setCourseConfigDetailId(c.getId());
							condition.setUserId(userId);
							List<CourseStudent> courseStudentList = this.courseStudentService.findCourseStudentByCondition(condition);
							if(courseStudentList.size()>0){
								vo.setSelected(true);
							}
							//已选人数
							condition = new CourseStudentCondition();
							condition.setCourseConfigDetailId(c.getId());
							List<CourseStudent> enrollList = this.courseStudentService.findCourseStudentByCondition(condition);
							vo.setEnrollCount(enrollList.size());
							voList.add(vo);
						}
					}
				}else if(config.getStatus().equals(2)){
					//获取选课科目组
					CourseConfigDetailCondition courseConfigDetailCondition = new CourseConfigDetailCondition();
					courseConfigDetailCondition.setCourseConfigId(config.getId());
					List<CourseConfigDetail> courseConfigDetailList = this.courseConfigDetailService
							.findCourseConfigDetailByCondition(courseConfigDetailCondition);
					if(courseConfigDetailList != null && courseConfigDetailList.size() > 0){
						for(CourseConfigDetail c: courseConfigDetailList){
							CourseConfigDetailVo vo = new CourseConfigDetailVo();
							PropertyUtils.copyProperties(vo,c);
							if(date.after(config.getSignupFinishDate())){
								prompt = "已超过选课时间！";
								vo.setDisalbed(true);
							}else if(date.before(config.getSignupStartDate())){
								prompt = "选课未开始！";
								vo.setDisalbed(true);
							}
							//判断是否已选过课
							condition.setCourseConfigDetailId(c.getId());
							condition.setUserId(userId);
							List<CourseStudent> courseStudentList = this.courseStudentService.findCourseStudentByCondition(condition);
							if(courseStudentList.size()>0){
								vo.setSelected(true);
							}
							//已选人数
							condition = new CourseStudentCondition();
							condition.setCourseConfigDetailId(c.getId());
							List<CourseStudent> enrollList = this.courseStudentService.findCourseStudentByCondition(condition);
							vo.setEnrollCount(enrollList.size());
							voList.add(vo);
						}
					}

					//公布结果
					prompt = "";
					//prompt = "选课结果公布：";
					condition = new CourseStudentCondition();
					condition.setTermCode(stc.getSchoolTermCode());
					condition.setUserId(userId);
					List<CourseStudent> courseStudentList = this.courseStudentService.findCourseStudentByCondition(condition);
					if(courseStudentList.size()>0){
						CourseStudent courseStudent = courseStudentList.get(0);
						if(courseStudent.getPassCourseConfigDetailId() != null){
							CourseConfigDetail courseConfigDetail = this.courseConfigDetailService
									.findCourseConfigDetailById(courseStudent.getPassCourseConfigDetailId());
							prompt += courseConfigDetail.getCourseNames();
						}else{
							prompt += "暂无结果";
						}
					}else{
						prompt += "暂无结果";
					}
				}
				PropertyUtils.copyProperties(courseStudentVo,config);
			}else{
				prompt = "暂无选课活动";
			}
			courseStudentVo.setCourseConfigDetailVoList(voList);
			courseStudentVo.setPrompt(prompt);
			courseStudentVo.setCourseList(courses);
			return new ResponseVo<BpCourseStudentVo>("0", courseStudentVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object creatorCourseStudent(Integer courseConfigDetailId,Integer schoolId, Integer userId,
									   String appKey, String signage) {
		try{
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(userId)) {
				return ResponseUtil.paramerIsNull("userId:"+userId);
			}
			if (StringUtils.isEmpty(schoolId)) {
				return ResponseUtil.paramerIsNull("schoolId:"+ schoolId);
			}*/

			CourseStudent courseStudent = new CourseStudent();
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Student student = this.studentService.findStudentByUserId(userId);
			if(courseConfigDetailId != null){
				//获取该科目报名上限
				CourseConfigDetail courseConfigDetail = this.courseConfigDetailService.findCourseConfigDetailById(courseConfigDetailId);
				CourseConfig courseConfig = this.courseConfigService.findCourseConfigById(courseConfigDetail.getCourseConfigId());
				//判断是否超过报名时间
				Date date = new Date();
				if(date.before(courseConfig.getSignupStartDate()) || date.after(courseConfig.getSignupFinishDate())){
					return new ResponseVo<Integer>("0", 2);
				}
				Long num = 0l;
				if(courseConfig.getIsLimited()){
					CourseStudentCondition condition = new CourseStudentCondition();
					condition.setCourseConfigDetailId(courseConfigDetailId);
					condition.setTermCode(schoolTermCurrent.getSchoolTermCode());
					condition.setSchoolId(schoolId);
					num = this.courseStudentService.count(condition);
					if(num >= courseConfigDetail.getMaxNum()){
						return new ResponseVo<Integer>("0", 1);
					}
				}

				CourseStudentCondition condition = new CourseStudentCondition();
				condition.setTermCode(schoolTermCurrent.getSchoolTermCode());
				condition.setUserId(userId);
				condition.setSchoolId(schoolId);
				condition.setStudentId(student.getId());
				List<CourseStudent> courseStudentlist = this.courseStudentService.findCourseStudentByCondition(condition);
				if(courseStudentlist != null && courseStudentlist.size() > 0){
					courseStudent = courseStudentlist.get(0);
					courseStudent.setCourseConfigDetailId(courseConfigDetailId);
					this.courseStudentService.modify(courseStudent);
					return new ResponseVo<Integer>("0", 0);
				}else{
					TeamStudentCondition studentCondition = new TeamStudentCondition();
					studentCondition.setUserId(userId);
					studentCondition.setStudentId(student.getId());
					studentCondition.setInState(true);
					List<TeamStudent> list = this.teamStudentService.findTeamStudentByCondition(studentCondition,null,null);
					courseStudent = new CourseStudent();
					courseStudent.setGradeId(list.get(0).getGradeId());
					courseStudent.setSchoolYear(Integer.parseInt(schoolTermCurrent.getSchoolYear()));
					courseStudent.setTermCode(schoolTermCurrent.getSchoolTermCode());
					courseStudent.setCourseConfigDetailId(courseConfigDetailId);
					courseStudent.setSchoolId(schoolId);
					courseStudent.setAdminTeamId(list.get(0).getTeamId());
					courseStudent.setUserId(userId);
					courseStudent.setStudentId(student.getId());
					courseStudent.setSignupDate(new Date());
					this.courseStudentService.add(courseStudent);
					return new ResponseVo<Integer>("0", 0);
				}
			}


			/*CourseStudent courseStudent = new CourseStudent();
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Student student = this.studentService.findStudentByUserId(userId);
			if(courseConfigDetailId != null){
				CourseStudentCondition condition = new CourseStudentCondition();
				condition.setTermCode(schoolTermCurrent.getSchoolTermCode());
				condition.setUserId(userId);
				condition.setSchoolId(schoolId);
				condition.setStudentId(student.getId());
				List<CourseStudent> courseStudentlist = this.courseStudentService.findCourseStudentByCondition(condition);
				if(courseStudentlist != null && courseStudentlist.size() > 0){
					courseStudent = courseStudentlist.get(0);
					courseStudent.setCourseConfigDetailId(courseConfigDetailId);
					this.courseStudentService.modify(courseStudent);
				}else{
					TeamStudentCondition studentCondition = new TeamStudentCondition();
					studentCondition.setUserId(userId);
					studentCondition.setStudentId(student.getId());
					studentCondition.setInState(true);
					List<TeamStudent> list = this.teamStudentService.findTeamStudentByCondition(studentCondition,null,null);
					courseStudent = new CourseStudent();
					courseStudent.setGradeId(list.get(0).getGradeId());
					courseStudent.setSchoolYear(Integer.parseInt(schoolTermCurrent.getSchoolYear()));
					courseStudent.setTermCode(schoolTermCurrent.getSchoolTermCode());
					courseStudent.setCourseConfigDetailId(courseConfigDetailId);
					courseStudent.setSchoolId(schoolId);
					courseStudent.setAdminTeamId(list.get(0).getTeamId());
					courseStudent.setUserId(userId);
					courseStudent.setStudentId(student.getId());
					courseStudent.setSignupDate(new Date());
					this.courseStudentService.add(courseStudent);
				}
			}*/
			return new ResponseVo<Boolean>("0", true);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}


}
