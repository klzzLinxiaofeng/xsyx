package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.*;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.*;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.SubjectTeamTeacherVo;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpSyllabusRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.*;
import platform.education.rest.bp.service.vo.*;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseNormal;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.Profile;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BpSyllabusRestServiceImpl implements BpSyllabusRestService {

	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	// 班级
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("personService")
	protected PersonService personService;
	
	@Autowired
	@Qualifier("bwUserInfoService")
	private BwUserInfoService bwUserInfoService;
	
	// 当前学期
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	/**
	 * 课表业务器
	 */
	@Autowired
	@Qualifier("syllabusService")
	protected SyllabusService syllabusService;
	
	
	// 班级
	@Autowired
	@Qualifier("syllabusStudentService")
	private SyllabusStudentService syllabusStudentService;
	
	
	// 教师
	@Autowired
	@Qualifier("teacherService")
	protected TeacherService teacherService;
	
	// 科目
	@Autowired
	@Qualifier("subjectService")
	protected SubjectService subjectService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("messageLessonChangeService")
	private MessageLessonChangeService messageLessonChangeService;
	
	// 科任教师
	@Autowired
	@Qualifier("subjectTeacherService")
	protected SubjectTeacherService subjectTeacherService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	
	@Resource(name = "bp_syllabus_taskExecutor")
	private TaskExecutor taskExecutor;
		
	@Autowired
	@Qualifier("schoolCourseService")
	private SchoolCourseService schoolCourseService;
	
	@Autowired
	@Qualifier("bwSyllabusService")
	private BwSyllabusService bwSyllabusService;
	
	@Autowired
	@Qualifier("bwSyllabusLessonService")
	private BwSyllabusLessonService bwSyllabusLessonService;
	
	@Autowired
	@Qualifier("bwGradeSyllabusService")
	private BwGradeSyllabusService bwGradeSyllabusService;
	
	@Autowired
	@Qualifier("courseTeacherService")
	private CourseTeacherService courseTeacherService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	@Autowired
	@Qualifier("attendancesSyllabusService")
	private AttendancesSyllabusService attendancesSyllabusService;
	
	@Autowired
	@Qualifier("roomService")
	private RoomService roomService;
	
	
	@Override
	public Object findSyllabus(Integer schoolId, Integer teamId, Integer roomId, String appKey,String signage) {
		try {
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			if(roomId == null){
				RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, stc.getSchoolYear());
				roomId = roomTeam.getRoomId();
			}		
			return this.findSyllabusByRoomId(schoolId, roomId, appKey, signage);
			/*Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(schoolId == null || "".equals(schoolId)){
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			
			BpSyllabusVo bpSyllabusVo = new BpSyllabusVo();
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(schoolId);
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}			
			
			//开通走班制
			if(isOpen){
				//获取房室绑定的班级
				RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer roomId = roomTeam.getRoomId();
				
				//获取走班课程表
				BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
				if(syllabus != null){	
					//上课时间表
					String lessonTimesJsonStr = syllabus.getLessonTimes();
					JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
					List<BpSyllabusLessonTimeVo> lessonTimeList =  
							(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
					bpSyllabusVo.setSyllabusLessonTimeList(lessonTimeList);
					
					//每日本教室课程（行政，走班）
					String dayOfWeekArrStr = syllabus.getDaysPlan();
					if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("接口异常");
						info.setMsg("接口异常");
						return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
					}
					String[] dayOfWeekArr = dayOfWeekArrStr.split(",");
					
					List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
					for(String dayOfWeek: dayOfWeekArr){
						BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
						BwSyllabusLessonCondition syllabusLessonCondition = new BwSyllabusLessonCondition();
						syllabusLessonCondition.setSyllabusId(syllabus.getId());
						syllabusLessonCondition.setDayOfWeek(dayOfWeek);
						Order order = new Order();
						order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
						order.setAscending(true);
						List<BwSyllabusLesson> lessonlList = 
								this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(syllabusLessonCondition, null, order);	
						List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
						int lessonNum = lessonTimeList.size();
						BwUserInfoCondition condition = null;
						for(int i=1; i<=lessonNum; i++){
							BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
							lessonDetailVo.setLesson(i);
							if(lessonlList != null && lessonlList.size() > 0){
								for(BwSyllabusLesson syllabusLesson: lessonlList){
									if(i == syllabusLesson.getLesson() ){
										//lessonDetailVo.setLesson(syllabusLesson.getLesson());
										int type = syllabusLesson.getType();
										lessonDetailVo.setType(syllabusLesson.getType());
										lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
										if(type != 0){						
											lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
											lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
											if(syllabusLesson.getTeacherId()!=null){
												Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
												if(teacher != null){
													Profile profile = this.profileService.findByUserId(teacher.getUserId());
													lessonDetailVo.setTeacherUserId(teacher.getUserId());
													lessonDetailVo.setTeacherName(teacher.getName());
													lessonDetailVo.setTeacherPostion(teacher.getPosition());
													
													condition = new BwUserInfoCondition();
													condition.setUserId(teacher.getUserId());
													List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
													if(list.size()>0){
														lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
													}
													if(profile != null){
														lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
													}
												}
																																	
											}
										}
										break;
									}										
								}
							}
							lessonDetailList.add(lessonDetailVo);
						}				
						lessonDayVo.setDayOfWeek(dayOfWeek);
						lessonDayVo.setLessonDetailList(lessonDetailList);
						lessonDayList.add(lessonDayVo);						
					}
					bpSyllabusVo.setLessonDayList(lessonDayList);
				}
			}else{
				//行政班课程表
				Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
				if(syllabus != null){	
					
					//上课时间表
					String lessonTimesJsonStr = syllabus.getLessonTimes();
					JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
					List<BpSyllabusLessonTimeVo> lessonTimeList =  
							(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
					bpSyllabusVo.setSyllabusLessonTimeList(lessonTimeList);
					
					//每日课程表列表
					String dayOfWeekArrStr = syllabus.getDaysPlan();
					if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("接口异常");
						info.setMsg("接口异常");
						return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
					}
					String[] dayOfWeekArr = dayOfWeekArrStr.split(",");
					
					List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
					for(String dayOfWeek: dayOfWeekArr){
						BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
						SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
						syllabusLessonCondition.setSyllabusId(syllabus.getId());
						syllabusLessonCondition.setDayOfWeek(dayOfWeek);
						Order order = new Order();
						order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
						order.setAscending(true);
						List<SyllabusLesson> lessonlList = this.syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, order);
						List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
						int lessonNum = lessonTimeList.size();
						BwUserInfoCondition condition = null;
						for(int i=1; i<=lessonNum; i++){
							BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
							lessonDetailVo.setLesson(i);
							if(lessonlList != null && lessonlList.size() > 0){
								for(SyllabusLesson syllabusLesson: lessonlList){
									if(i == syllabusLesson.getLesson() ){
										lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
										lessonDetailVo.setType(1);//默认全部行政课
										//lessonDetailVo.setLesson(syllabusLesson.getLesson());
										lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
										lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
										Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
										if(teacher != null){
											Profile profile = this.profileService.findByUserId(teacher.getUserId());
											lessonDetailVo.setTeacherUserId(teacher.getUserId());
											lessonDetailVo.setTeacherName(teacher.getName());
											lessonDetailVo.setTeacherPostion(teacher.getPosition());
											
											condition = new BwUserInfoCondition();
											condition.setUserId(teacher.getUserId());
											List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
											if(list.size()>0){
												lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
											}
											if(profile != null){
												lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
											}
										}																					
										break;
									}										
								}
							}
							lessonDetailList.add(lessonDetailVo);
						}				
						lessonDayVo.setDayOfWeek(dayOfWeek);
						lessonDayVo.setLessonDetailList(lessonDetailList);
						lessonDayList.add(lessonDayVo);
					}
					bpSyllabusVo.setLessonDayList(lessonDayList);	
				}	
			}
			
			return new ResponseVo<BpSyllabusVo>("0",bpSyllabusVo);	*/	
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object findLessonChangeContentList(Integer teamId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			List<MessageLessonChange> list = this.messageLessonChangeService.findActivityLessonChangeMessagesOfTeam(teamId, new Date());		
			List<String> lessonChangeList = new ArrayList<String>();
			if(list != null && list.size() > 0){
				for(MessageLessonChange m: list){
					lessonChangeList.add(m.getContent());
				}
			}
			return new ResponseVo<List<String>>("0",lessonChangeList);
			
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	@Override
	public Object findLessonChangeList(Integer teamId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			List<MessageLessonChange> list = this.messageLessonChangeService.findActivityLessonChangeMessagesOfTeam(teamId, new Date());		
			return new ResponseVo<List<MessageLessonChange>>("0",list);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	

	@Override
	public Object createSyllabus(Integer schoolId, Integer teamId, Integer days, String daysPlan,
			Integer lessonOfMorning, Integer lessonOfAfternoon, Integer lessonOfEvening, String lessonTime,
			String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if ( schoolId == null ) {
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			String schoolTermCode = schoolTermCurrent.getSchoolTermCode();
			String schoolYear = schoolTermCurrent.getSchoolYear();
			Syllabus syllabus = new Syllabus();
			syllabus.setDays(days);
			syllabus.setDaysPlan(daysPlan);
			syllabus.setTermCode(schoolTermCode);
			syllabus.setSchoolYear(schoolYear);
			syllabus.setLessonOfAfternoon(lessonOfAfternoon);
			syllabus.setLessonOfEvening(lessonOfEvening);
			syllabus.setLessonOfMorning(lessonOfMorning);
			syllabus.setLessonTimes(lessonTime);
			syllabus.setTeamId(teamId);
			syllabus.setSchoolId(schoolId);
			syllabus = syllabusService.add(syllabus);
			return new ResponseVo<Integer>("0", syllabus.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object modifySyllabus(Integer id, Integer schoolId, Integer teamId, Integer days, String daysPlan,
			Integer lessonOfMorning, Integer lessonOfAfternoon, Integer lessonOfEvening, String lessonTime,
			String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if ( schoolId == null ) {
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			Syllabus syllabus = syllabusService.findSyllabusById(id);
			if ( syllabus == null ) {
				return ResponseUtil.dataNotExist("id:"+id);
			}
			if ( !syllabus.getSchoolId().equals(schoolId) || !syllabus.getTeamId().equals(teamId)  ) {
				return ResponseUtil.paramterError("id:"+id+",schoolId:"+schoolId+",teamId:"+teamId+"不匹配");
			}
			syllabus.setDays(days);
			syllabus.setDaysPlan(daysPlan);
			syllabus.setLessonOfAfternoon(lessonOfAfternoon);
			syllabus.setLessonOfEvening(lessonOfEvening);
			syllabus.setLessonOfMorning(lessonOfMorning);
			syllabus.setLessonTimes(lessonTime);
			syllabusService.modify(syllabus);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getSubjectTeamTeacherList(Integer teamId, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			Team team = teamService.findTeamById(teamId);
			if ( checkResult != null ) {
				return checkResult;
			}
			if ( team == null ) {
				return ResponseUtil.dataNotExist("teamId:"+teamId);
			}
			List<SubjectTeamTeacherVo> subjcetTeamTeacherList = this.subjectTeacherService.findSubjectsWithTeacher(team.getGradeId(), teamId);
			return new ResponseOrder("0", subjcetTeamTeacherList, subjcetTeamTeacherList.size());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object createSyllabusLesson(Integer schoolId, Integer teamId, Integer lesson, String dayOfWeek,
			Integer teacherId, String subjectCode, String subjectName, String appKey, String signage) {
		try {
			if ( schoolId == null ) {
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if ( stc == null ) {
				return ResponseUtil.dataNotExist("schoolId:"+schoolId+"找不到当前学年信息");
			}
			Syllabus syllabus = syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
			if ( syllabus == null ) {
				return ResponseUtil.dataNotExist("schoolId:"+schoolId+",teamId:"+teamId+"找不到对应的课程表基础信息");
			}
			Integer syllabusId = syllabus.getId();
			Subject subject = subjectService.findUnique(schoolId, subjectCode);
			SyllabusLesson lessonEntity = new SyllabusLesson();
			lessonEntity.setDayOfWeek(dayOfWeek);
			lessonEntity.setLesson(lesson);
			lessonEntity.setSubjectCode(subjectCode);
			lessonEntity.setSubjectName(subjectName);
			lessonEntity.setSyllabusId(syllabusId);
			lessonEntity.setTeacherId(teacherId);
			if(subject != null){
				lessonEntity.setSubjectName(subject.getName());
			}
			lessonEntity = this.syllabusService.addSyllabusLesson(lessonEntity);
			return new ResponseVo<Integer>("0", lessonEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object modifySyllabusLesson(Integer id, Integer teacherId, String subjectCode, String subjectName,
			String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SyllabusLesson syllabusLesson = syllabusService.findSyllabusLessonById(id);
			if ( syllabusLesson == null ) {
				return ResponseUtil.dataNotExist("id:"+id);
			}
			if ( teacherId != null ) {
				syllabusLesson.setTeacherId(teacherId);
			}
			if ( subjectCode != null ) {
				syllabusLesson.setSubjectCode(subjectCode);
			}
			if ( subjectName != null ) {
				syllabusLesson.setSubjectName(subjectName);
			}
			syllabusService.modifySyllabusLesson(syllabusLesson);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object deleteSyllabusLesson(Integer id, String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			SyllabusLesson syllabusLesson = syllabusService.findSyllabusLessonById(id);
			if ( syllabusLesson == null ) {
				return ResponseUtil.dataNotExist("id:"+id);
			}
			syllabusService.removeSyllabusLesson(syllabusLesson);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
		
	}

	@Override
	public Object getLessonTime(Integer teamId, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			
			Team t = this.teamService.findTeamById(teamId);				
			if(t==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(t.getSchoolId());
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(t.getSchoolId());
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}	
				
			List<CommonLessonTime> list = new ArrayList<CommonLessonTime>();
			//开通走班制
			if(isOpen){			
				List<CommonLessonTime> list1 = new ArrayList<CommonLessonTime>();				
				if(t!=null){
					//获取房室绑定的班级
					RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, stc.getSchoolYear());
					if(roomTeam == null){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("房室未绑定班级");
						info.setMsg("房室未绑定班级");
						return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
					}
					Integer roomId = roomTeam.getRoomId();
					
					BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
					if(syllabus != null){
						BwGradeSyllabus gradeSyllabus = this.bwGradeSyllabusService.findBwGradeSyllabusById(syllabus.getGradeSyllabusId());
						if(gradeSyllabus != null){
							String time = gradeSyllabus.getLessonTimes();
							if(time != null & !"".equals(time)){
								JSONArray array = JSONArray.fromObject(time);
								list1 = JSONArray.toList(array, new CommonLessonTime(), new JsonConfig());
								int i = 1;
								for(CommonLessonTime lesson1:list1){
									CommonLessonTime lesson = new CommonLessonTime();
									lesson.setStartTime(lesson1.getStartTime());
									lesson.setEndTime(lesson1.getEndTime());
									lesson.setLesson(i);
									list.add(lesson);
									i++;
								}
							}
						}else{
							Integer allCount = gradeSyllabus.getLessonOfMorning()+gradeSyllabus.getLessonOfAfternoon()+gradeSyllabus.getLessonOfEvening();
							for(int i=1;i<=allCount;i++){
								CommonLessonTime lessonTime = new CommonLessonTime();
								lessonTime.setLesson(i);
								lessonTime.setStartTime("");
								lessonTime.setEndTime("");
								list.add(lessonTime);
							}
						}
						
					}
				}
			}else{
				List<CommonLessonTime> list1 = new ArrayList<CommonLessonTime>();				
				Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
				String time = syllabus.getLessonTimes();
				if(time != null & !"".equals(time)){
					JSONArray array = JSONArray.fromObject(time);
					list1 = JSONArray.toList(array, new CommonLessonTime(), new JsonConfig());
					int i = 1;
					for(CommonLessonTime lesson1:list1){
						CommonLessonTime lesson = new CommonLessonTime();
						lesson.setStartTime(lesson1.getStartTime());
						lesson.setEndTime(lesson1.getEndTime());
						lesson.setLesson(i);
						list.add(lesson);
						i++;
					}
				}else{
					Integer allCount = syllabus.getLessonOfMorning()+syllabus.getLessonOfAfternoon()+syllabus.getLessonOfEvening();
					for(int i=1;i<=allCount;i++){
						CommonLessonTime lessonTime = new CommonLessonTime();
						lessonTime.setLesson(i);
						lessonTime.setStartTime("");
						lessonTime.setEndTime("");
						list.add(lessonTime);
					}
				}
			}		
			return new ResponseVo<List<CommonLessonTime>>("0",list);
		}catch(Exception e ){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}
	}

	@Override
	public Object getTable(Integer teamId, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}	
			Team t = this.teamService.findTeamById(teamId);				
			if(t==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(t.getSchoolId());
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}			
			//获取房室绑定的班级
			RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId,  stc.getSchoolYear());
			if(roomTeam == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("房室未绑定班级");
				info.setMsg("房室未绑定班级");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}	
			return this.getTableByRoom(roomTeam.getRoomId(), appKey, signage);
		} catch (NumberFormatException nfe) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId...");
			return new ResponseError("060112",info);
		} catch (Exception e ){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}
	}

	@Override
	public Object createLessonChange(Integer teamId, String appKey,
			Long changeDate, Integer posterId, String content, String signage) {
		ResponseAdd add =null;
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if ( changeDate == null ) {
				return ResponseUtil.paramerIsNull("changeDate:"+changeDate);
			}
			if ( content == null ) {
				return ResponseUtil.paramerIsNull("content:"+content);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			MessageLessonChange messageLessonChange = new MessageLessonChange();
			messageLessonChange.setCreateDate(new Date());
			messageLessonChange.setIsDelete(false);
			messageLessonChange.setModifyDate(new Date());
			messageLessonChange.setPosterId(posterId);
			messageLessonChange.setChangeDate(new Date(changeDate));
			messageLessonChange.setContent(content);
			messageLessonChange.setTeamId(teamId);
			if(messageLessonChange.getPosterId()==null){
				messageLessonChange.setPosterId(0);
			}
			messageLessonChange = this.messageLessonChangeService.add(messageLessonChange);
			add = new ResponseAdd(messageLessonChange.getId(),DateUtil.dateToString(messageLessonChange.getCreateDate()));
			//推送开始=====
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(messageLessonChange.getTeamId());
			//推送
//			IMPushUtil.push(teamIds, DataAction.D$add, messageLessonChange.getId(), BpCommonConstants.PUSH_LESSON_CHANGE, null, 
//					 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, messageLessonChange.getId(),BpCommonConstants.PUSH_LESSON_CHANGE, null, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,messageLessonChange.getId(), BpCommonConstants.PUSH_LESSON_CHANGE,null, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId...");
			return new ResponseError("060112",info);
		}
		
		return new ResponseVo<ResponseAdd>("0",add);
	}

	@Override
	public Object deleteLessonChange(Integer id, String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			MessageLessonChange messageLessonChange = this.messageLessonChangeService.findMessageLessonChangeById(id);
			this.messageLessonChangeService.remove(messageLessonChange);
			
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("id...");
			return new ResponseError("060112",info);
		}
		return new ResponseNormal("0");
	}

	@Override
	public Object syllabusLessonStudent(Integer syllabusLessonId,Integer schoolId, Long date,String appKey,
			String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(syllabusLessonId == null || "".equals(syllabusLessonId)){
				return ResponseUtil.paramerIsNull("lessonId:"+syllabusLessonId);
			}
			if(schoolId == null || "".equals(schoolId)){
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			/*if(date == null || "".equals(date)){
				return ResponseUtil.paramerIsNull("date:"+date);
			}*/
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			
//			SyllabusStudentCondition syllabusStudentCondition = new SyllabusStudentCondition();
//			syllabusStudentCondition.setLessonId(syllabusLessonId);
//			WeekVo weekVo = WeekUtil.Week(stc.getBeginDate(), stc.getFinishDate(), new Date(date));
//			List<SyllabusStudent> slist = this.syllabusStudentService.findSyllabusStudentByCondition(syllabusStudentCondition);
//			List<SyllabusStudentVo> list = new ArrayList<SyllabusStudentVo>();
//			if(slist != null && slist.size() > 0){
//				for(SyllabusStudent vo : slist){
//					Student student = this.studentService.findStudentByUserId(vo.getStudentUserId());
//					String imgUrl = "";
//					SyllabusStudentVo newVo = new SyllabusStudentVo();
//					newVo.setStudentName(student.getName());
//					imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
//					BeanUtils.copyProperties(vo,newVo);
//					newVo.setImgUrl(imgUrl);
//					list.add(newVo);
//				}
//			}
			WeekVo weekVo = WeekUtil.Week(stc.getBeginDate(), stc.getFinishDate(), new Date(date));
			List<SyllabusStudentVo> voList = this.syllabusStudentService.findSyllabusStudent(syllabusLessonId, weekVo.getWeek());
			List<SyllabusStudentVo> list = new ArrayList<SyllabusStudentVo>();
			for(SyllabusStudentVo vo : voList){
				String imgUrl = "";
				SyllabusStudentVo newVo = new SyllabusStudentVo();
				imgUrl = ImgUtil.getStudentIconUrl(vo.getPersonId(), personService);
				BeanUtils.copyProperties(vo,newVo);
				newVo.setImgUrl(imgUrl);
				list.add(newVo);
			}
			return new ResponseVo<List<SyllabusStudentVo>>("0",list);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object findSyllabusByRoomId(Integer schoolId, Integer roomId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(roomId == null || "".equals(roomId)){
				return ResponseUtil.paramerIsNull("roomId:"+roomId);
			}
			if(schoolId == null || "".equals(schoolId)){
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			
			BpSyllabusVo bpSyllabusVo = new BpSyllabusVo();
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}		
			//获取房室绑定的班级
			RoomTeam roomTeam = this.roomTeamService.findRoomTeamByRoomId(roomId, stc.getSchoolYear());
			if(roomTeam == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("房室未绑定班级");
				info.setMsg("房室未绑定班级");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			Integer teamId = roomTeam.getTeamId();
			
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(schoolId);
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}				
			//开通走班制
			if(isOpen){
				//获取走班课程表
				BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
				if(syllabus != null){	
					//上课时间表
					String lessonTimesJsonStr = syllabus.getLessonTimes();
					JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
					List<BpSyllabusLessonTimeVo> lessonTimeList =  
							(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
					bpSyllabusVo.setSyllabusLessonTimeList(lessonTimeList);
					
					//每日本教室课程（行政，走班）
					String dayOfWeekArrStr = syllabus.getDaysPlan();
					if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("接口异常");
						info.setMsg("接口异常");
						return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
					}
					String[] dayOfWeekArr = dayOfWeekArrStr.split(",");
					
					List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
					for(String dayOfWeek: dayOfWeekArr){
						BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
						BwSyllabusLessonCondition syllabusLessonCondition = new BwSyllabusLessonCondition();
						syllabusLessonCondition.setSyllabusId(syllabus.getId());
						syllabusLessonCondition.setDayOfWeek(dayOfWeek);
						Order order = new Order();
						order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
						order.setAscending(true);
						List<BwSyllabusLesson> lessonlList = 
								this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(syllabusLessonCondition, null, order);	
						List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
						int lessonNum = lessonTimeList.size();
						BwUserInfoCondition condition = null;
						for(int i=1; i<=lessonNum; i++){
							BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
							lessonDetailVo.setLesson(i);
							if(lessonlList != null && lessonlList.size() > 0){
								for(BwSyllabusLesson syllabusLesson: lessonlList){
									if(i == syllabusLesson.getLesson() ){
										//lessonDetailVo.setLesson(syllabusLesson.getLesson());
										int type = syllabusLesson.getType();
										lessonDetailVo.setType(syllabusLesson.getType());
										lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
										if(type != 0){						
											lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
											lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
											if(syllabusLesson.getTeacherId()!=null){
												Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
												if(teacher != null){
													Profile profile = this.profileService.findByUserId(teacher.getUserId());
													lessonDetailVo.setTeacherUserId(teacher.getUserId());
													lessonDetailVo.setTeacherName(teacher.getName());
													lessonDetailVo.setTeacherPostion(teacher.getPosition());
													condition = new BwUserInfoCondition();
													condition.setUserId(teacher.getUserId());
													List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
													if(list.size()>0){
														lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
													}
													if(profile != null){
														lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
													}
												}
																																	
											}
										}
										break;
									}										
								}
							}
							lessonDetailList.add(lessonDetailVo);
						}				
						lessonDayVo.setDayOfWeek(dayOfWeek);
						lessonDayVo.setLessonDetailList(lessonDetailList);
						lessonDayList.add(lessonDayVo);						
					}
					bpSyllabusVo.setLessonDayList(lessonDayList);
				}
			}else{
				//行政班课程表
				Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
				if(syllabus != null){	
					
					//上课时间表
					String lessonTimesJsonStr = syllabus.getLessonTimes();
					JSONArray jsonArr = JSONArray.fromObject(lessonTimesJsonStr);
					List<BpSyllabusLessonTimeVo> lessonTimeList =  
							(List<BpSyllabusLessonTimeVo>)JSONArray.toCollection(jsonArr, BpSyllabusLessonTimeVo.class);
					bpSyllabusVo.setSyllabusLessonTimeList(lessonTimeList);
					
					//每日课程表列表
					String dayOfWeekArrStr = syllabus.getDaysPlan();
					if(dayOfWeekArrStr == null && "".equals(dayOfWeekArrStr)){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("接口异常");
						info.setMsg("接口异常");
						return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
					}
					String[] dayOfWeekArr = dayOfWeekArrStr.split(",");
					
					List<BpSyllabusLessonDayVo> lessonDayList = new ArrayList<BpSyllabusLessonDayVo>();
					for(String dayOfWeek: dayOfWeekArr){
						BpSyllabusLessonDayVo lessonDayVo = new BpSyllabusLessonDayVo();
						SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
						syllabusLessonCondition.setSyllabusId(syllabus.getId());
						syllabusLessonCondition.setDayOfWeek(dayOfWeek);
						Order order = new Order();
						order.setProperty(order.getProperty() != null ? order.getProperty() : "lesson");
						order.setAscending(true);
						List<SyllabusLesson> lessonlList = this.syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, order);
						List<BpSyllabusLessonDetailVo> lessonDetailList = new ArrayList<BpSyllabusLessonDetailVo>();
						int lessonNum = lessonTimeList.size();
						BwUserInfoCondition condition = null;
						for(int i=1; i<=lessonNum; i++){
							BpSyllabusLessonDetailVo lessonDetailVo = new BpSyllabusLessonDetailVo();
							lessonDetailVo.setLesson(i);
							if(lessonlList != null && lessonlList.size() > 0){
								for(SyllabusLesson syllabusLesson: lessonlList){
									if(i == syllabusLesson.getLesson() ){
										lessonDetailVo.setSyllabusLessonId(syllabusLesson.getId());
										lessonDetailVo.setType(1);//默认全部行政课
										//lessonDetailVo.setLesson(syllabusLesson.getLesson());
										lessonDetailVo.setSubjectCode(syllabusLesson.getSubjectCode());
										lessonDetailVo.setSubjectName(syllabusLesson.getSubjectName());
										Teacher teacher = this.teacherService.findTeacherById(syllabusLesson.getTeacherId());
										if(teacher != null){
											Profile profile = this.profileService.findByUserId(teacher.getUserId());
											lessonDetailVo.setTeacherUserId(teacher.getUserId());
											lessonDetailVo.setTeacherName(teacher.getName());
											lessonDetailVo.setTeacherPostion(teacher.getPosition());
											condition = new BwUserInfoCondition();
											condition.setUserId(teacher.getUserId());
											List<BwUserInfo> list = this.bwUserInfoService.findBwUserInfoByCondition(condition);
											if(list.size()>0){
												lessonDetailVo.setTeacherFeature(list.get(0).getCharacteristic());
											}
											if(profile != null){
												lessonDetailVo.setTeacherHeadUrl(this.fileService.relativePath2HttpUrlByUUID(profile.getIcon()));
											}
										}																				
										break;
									}										
								}
							}
							lessonDetailList.add(lessonDetailVo);
						}				
						lessonDayVo.setDayOfWeek(dayOfWeek);
						lessonDayVo.setLessonDetailList(lessonDetailList);
						lessonDayList.add(lessonDayVo);
					}
					bpSyllabusVo.setLessonDayList(lessonDayList);	
				}	
			}	
			return new ResponseVo<BpSyllabusVo>("0",bpSyllabusVo);
						
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}	
	}

	@Override
	public Object getStudentSyllabus(Integer schoolId, Integer teamId, Integer userId, String appKey, String signage) {
		try {
			
			/*if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*Team t = this.teamService.findTeamById(teamId);				
			if(t==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}*/
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			Student student = this.studentService.findStudentByUserId(userId);
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("无学生数据");
				info.setMsg("无学生数据");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}	
					
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(schoolId);
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}	
				
			List[] list = null;
			//开通走班制
			if(isOpen){
				
				//获取走班课程表
				Integer allCount = Integer.valueOf(SysContants.MAX_LESSON_COUNT_MORING) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_AFTERNOON) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_EVENING);
				list = new ArrayList[allCount];
				for(int i=0;i<allCount;i++){
					list[i] = new ArrayList<SyllabusLesson>();
				}
				/*list = new ArrayList[allCount];
				for(int i=0;i<allCount;i++){
					list[i] = new ArrayList<SyllabusLesson>();
				}*/
				
				
				List<Map<String, Object>> slList = this.syllabusStudentService
						.findStudentSyllabus(userId, student.getTeamId(), stc.getSchoolTermCode());
				List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
				CommonSyllabus vo = null;
				for(Map<String, Object> syl : slList){
					Integer syllabusLessonId = ((Long) syl.get("syllabusLessonId")).intValue();
					Integer lesson = (Integer) syl.get("lesson");
					Integer teacherId = (Integer) syl.get("teacherId");
					String dayOfWeek = (String) syl.get("dayOfWeek");
					String subjectName = (String) syl.get("subjectName");
					String subjectCode = (String) syl.get("subjectCode");
					Integer type = (Integer) syl.get("type");
					String roomName = (String) syl.get("roomName");
						
					vo = new CommonSyllabus();
					Teacher teac = this.teacherService.findTeacherById(teacherId);
					if(teac!=null){
						vo.setTeacherName(teac.getName());
						String imgUrl = ImgUtil.getImgUrl(null);
						imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
						vo.setTeacherHeadUrl(imgUrl);
					}
					vo.setSyllabusId(syllabusLessonId);
					vo.setLesson(lesson);
					vo.setTeacherId(teacherId);
					vo.setDayOfWeek(dayOfWeek);
					vo.setSubjectCode(subjectCode);
					vo.setSubjectName(subjectName);
					vo.setType(type);
					vo.setRoomName(roomName);
					
					if(subjectCode != null){
						Subject subject = this.subjectService.findUnique(schoolId, subjectCode);
						if(subject!=null){
							vo.setSubjectName(subject.getName());
						}
					}
					vo.setLesson(lesson);
					sylVo.add(vo);
				}
				
				for(int i=0;i<allCount;i++){
					for(int j=0;j<7;j++){
						//一周7天
						list[i].add(j,null);
					}
				}
				
				for(CommonSyllabus sl : sylVo){
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
							Integer lesson = sl.getLesson();
							if(dayOfWeek.intValue()==j&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}
						}
					}
				}	
				
				//获取房室绑定的班级
				/*RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer roomId = roomTeam.getRoomId();
				
				//获取走班课程表
				BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
				if(syllabus != null){	
					BwGradeSyllabus gradeSyllabus = this.bwGradeSyllabusService.findBwGradeSyllabusById(syllabus.getGradeSyllabusId());
					if(gradeSyllabus != null){
						Integer allCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon() 
								+ gradeSyllabus.getLessonOfEvening();
						list = new ArrayList[allCount];
						for(int i=0;i<allCount;i++){
							list[i] = new ArrayList<SyllabusLesson>();
						}
						BwSyllabusLessonCondition bwSyllabusLessonCondition = new BwSyllabusLessonCondition();
						bwSyllabusLessonCondition.setSyllabusId(syllabus.getId());
						//List<BwSyllabusLesson> slList = this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(bwSyllabusLessonCondition);
						List<Map<String, Object>> slList = 
								this.syllabusStudentService.findStudentSyllabus(userId, teamId, stc.getSchoolTermCode());					
						List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
						CommonSyllabus vo = null;
						for(Map<String, Object> syl : slList){
							Integer syllabusLessonId = ((Long) syl.get("syllabusLessonId")).intValue();
							Integer lesson = (Integer) syl.get("lesson");
							Integer teacherId = (Integer) syl.get("teacherId");
							String dayOfWeek = (String) syl.get("dayOfWeek");
							String subjectName = (String) syl.get("subjectName");
							String subjectCode = (String) syl.get("subjectCode");
							Integer type = (Integer) syl.get("type");
							String roomName = (String) syl.get("roomName");
								
							vo = new CommonSyllabus();
							Teacher teac = this.teacherService.findTeacherById(teacherId);
							if(teac!=null){
								vo.setTeacherName(teac.getName());
								String imgUrl = ImgUtil.getImgUrl(null);
								imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
								vo.setTeacherHeadUrl(imgUrl);
							}
							vo.setSyllabusId(syllabusLessonId);
							vo.setLesson(lesson);
							vo.setTeacherId(teacherId);
							vo.setDayOfWeek(dayOfWeek);
							vo.setSubjectCode(subjectCode);
							vo.setSubjectName(subjectName);
							vo.setType(type);
							vo.setRoomName(roomName);
							
							if(subjectCode != null){
								Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), subjectCode);
								if(subject!=null){
									vo.setSubjectName(subject.getName());
								}
							}
							
							//解决早上2节下午2节类似情况
							//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-gradeSyllabus.getLessonOfMorning())-1:syl.getLesson()-(8-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);
							int morningCount = gradeSyllabus.getLessonOfMorning();
							int afternoonCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon();
							
							vo.setLesson(lesson<morningCount?lesson-1:lesson<afternoonCount?
									lesson-(morningCount-gradeSyllabus.getLessonOfMorning())-1:
										lesson-(afternoonCount-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);
							sylVo.add(vo);
						}
						
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								//一周7天
								list[i].add(j,null);
							}
						}
						
						for(CommonSyllabus sl : sylVo){
							for(int i=0;i<allCount;i++){
								for(int j=0;j<7;j++){
									Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
									Integer lesson = sl.getLesson();
									if(dayOfWeek.intValue()==j&&lesson.intValue()==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&lesson.intValue()==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}
								}
							}
						}
					}
				}*/
			}else{
				Syllabus syllabus = this.syllabusService.getTeamSyllabus(student.getTeamId(), stc.getSchoolTermCode());
				if(syllabus!=null){
					Team team = this.teamService.findTeamById(student.getTeamId());
					Integer allCount = syllabus.getLessonOfMorning()+syllabus.getLessonOfAfternoon()+syllabus.getLessonOfEvening();
					list = new ArrayList[allCount];
					for(int i=0;i<allCount;i++){
						list[i] = new ArrayList<SyllabusLesson>();
					}
					List<SyllabusLesson> slList = this.syllabusService.getSyllabusLessonBySyllabusId(syllabus.getId());
					List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
					CommonSyllabus vo = null;
					for(SyllabusLesson syl : slList){
						Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
						vo = new CommonSyllabus();
						vo.setType(1);
						BeanUtils.copyProperties(syl, vo);
						if(teac!=null){
							vo.setTeacherName(teac.getName());
							String imgUrl = ImgUtil.getImgUrl(null);
							imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
							vo.setTeacherHeadUrl(imgUrl);
						}
						Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), syl.getSubjectCode());
						if(subject!=null){
							vo.setSubjectName(subject.getName());
						}
						//解决早上2节下午2节类似情况
						//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-syllabus.getLessonOfMorning())-1:syl.getLesson()-(8-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);
						int morningCount = syllabus.getLessonOfMorning();
						int afternoonCount = syllabus.getLessonOfMorning() + syllabus.getLessonOfAfternoon();
						
						/*vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
								syl.getLesson()-(morningCount-syllabus.getLessonOfMorning())-1:
									syl.getLesson()-(afternoonCount-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);*/
						vo.setLesson(syl.getLesson());
						sylVo.add(vo);
					}
//					syllabus.getDays();
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							//一周7天
							list[i].add(j,null);
						}
					}
					for(CommonSyllabus sl : sylVo){
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
								Integer lesson = sl.getLesson();
								if(dayOfWeek.intValue()==j&&(lesson.intValue()-1)==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&(lesson.intValue()-1)==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}
							}
						}
					}
				}			
			}
			
			return new ResponseVo<List[]>("0",list);
		} catch (NumberFormatException nfe) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId...");
			return new ResponseError("060112",info);
		} catch (Exception e ){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}		
	}

	@Override
	public Object getTeacherSyllabus(Integer schoolId, Integer teamId, Integer userId, String appKey, String signage) {
		try {	
			/*if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*Team t = this.teamService.findTeamById(teamId);				
			if(t==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}*/
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
	
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(schoolId);
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}	
			
			TeacherCondition teacherCondition = new TeacherCondition();
			teacherCondition.setUserId(userId);
			teacherCondition.setIsDelete(false);
			Teacher teacher = (this.teacherService.findTeacherByCondition(teacherCondition, null, null)).get(0);
					
			List[] list = null;
			//开通走班制
			if(isOpen){
				//获取房室绑定的班级
				/*RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer roomId = roomTeam.getRoomId();*/
								
				//获取走班课程表
				Integer allCount = Integer.valueOf(SysContants.MAX_LESSON_COUNT_MORING) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_AFTERNOON) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_EVENING);
				list = new ArrayList[allCount];
				for(int i=0;i<allCount;i++){
					list[i] = new ArrayList<SyllabusLesson>();
				}
				List<BwSyllabusLessonVo> slList = 
						bwSyllabusLessonService.findBwSyllabusLessonVoByTeacherId(teacher.getId(), stc.getSchoolTermCode());					
				List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
				CommonSyllabus vo = null;
				for(BwSyllabusLessonVo syl : slList){
					vo = new CommonSyllabus();					
					Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
					if(teac!=null){
						vo.setTeacherName(teac.getName());
						String imgUrl = ImgUtil.getImgUrl(null);
						imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
						vo.setTeacherHeadUrl(imgUrl);
					}
					vo.setSyllabusId(syl.getId());
					vo.setLesson(syl.getLesson());
					vo.setTeacherId(syl.getTeacherId());
					vo.setDayOfWeek(syl.getDayOfWeek());
					vo.setSubjectCode(syl.getSubjectCode());
					vo.setSubjectName(syl.getSubjectName());
					vo.setType(syl.getType());
					vo.setRoomName(syl.getRoomName());
					
					if(syl.getSubjectCode() != null){
						Subject subject = this.subjectService.findUnique(schoolId, syl.getSubjectCode());
						if(subject!=null){
							vo.setSubjectName(subject.getName());
						}
					}
					vo.setLesson(syl.getLesson());
					sylVo.add(vo);
				}
				
				for(int i=0;i<allCount;i++){
					for(int j=0;j<7;j++){
						//一周7天
						list[i].add(j,null);
					}
				}
				
				for(CommonSyllabus sl : sylVo){
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
							Integer lesson = sl.getLesson();
							if(dayOfWeek.intValue()==j&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}
						}
					}
				}
			
				
				
				
				/*BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
				if(syllabus != null){	
					BwGradeSyllabus gradeSyllabus = this.bwGradeSyllabusService.findBwGradeSyllabusById(syllabus.getGradeSyllabusId());
					if(gradeSyllabus != null){
						Integer allCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon() 
								+ gradeSyllabus.getLessonOfEvening();
						list = new ArrayList[allCount];
						for(int i=0;i<allCount;i++){
							list[i] = new ArrayList<SyllabusLesson>();
						}
						BwSyllabusLessonCondition bwSyllabusLessonCondition = new BwSyllabusLessonCondition();
						bwSyllabusLessonCondition.setSyllabusId(syllabus.getId());
						
						TeacherCondition teacherCondition = new TeacherCondition();
						teacherCondition.setUserId(userId);
						teacherCondition.setIsDelete(false);
						Teacher teacher = (this.teacherService.findTeacherByCondition(teacherCondition, null, null)).get(0);
						List<BwSyllabusLessonVo> slList = 
								bwSyllabusLessonService.findBwSyllabusLessonVoByTeacherId(teacher.getId(), stc.getSchoolTermCode());					
						List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
						CommonSyllabus vo = null;
						for(BwSyllabusLessonVo syl : slList){
							vo = new CommonSyllabus();					
							Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
							if(teac!=null){
								vo.setTeacherName(teac.getName());
								String imgUrl = ImgUtil.getImgUrl(null);
								imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
								vo.setTeacherHeadUrl(imgUrl);
							}
							vo.setSyllabusId(syl.getSyllabusId());
							vo.setLesson(syl.getLesson());
							vo.setTeacherId(syl.getTeacherId());
							vo.setDayOfWeek(syl.getDayOfWeek());
							vo.setSubjectCode(syl.getSubjectCode());
							vo.setSubjectName(syl.getSubjectName());
							vo.setType(syl.getType());
							vo.setRoomName(syl.getRoomName());
							
							if(syl.getSubjectCode() != null){
								Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), syl.getSubjectCode());
								if(subject!=null){
									vo.setSubjectName(subject.getName());
								}
							}
							
							//解决早上2节下午2节类似情况
							//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-gradeSyllabus.getLessonOfMorning())-1:syl.getLesson()-(8-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);
							int morningCount = gradeSyllabus.getLessonOfMorning();
							int afternoonCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon();
							
							vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
									syl.getLesson()-(morningCount-gradeSyllabus.getLessonOfMorning())-1:
										syl.getLesson()-(afternoonCount-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);
							sylVo.add(vo);
						}
						
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								//一周7天
								list[i].add(j,null);
							}
						}
						
						for(CommonSyllabus sl : sylVo){
							for(int i=0;i<allCount;i++){
								for(int j=0;j<7;j++){
									Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
									Integer lesson = sl.getLesson();
									if(dayOfWeek.intValue()==j&&lesson.intValue()==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&lesson.intValue()==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}
								}
							}
						}
					}
				}*/
			}else{
				Integer allCount = Integer.valueOf(SysContants.MAX_LESSON_COUNT_MORING) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_AFTERNOON) +
						Integer.valueOf(SysContants.MAX_LESSON_COUNT_EVENING);
				list = new ArrayList[allCount];
				for(int i=0;i<allCount;i++){
					list[i] = new ArrayList<SyllabusLesson>();
				}
				List<SyllabusLesson> slList = this.syllabusService.getTeacherSyllabus(teacher.getId(), stc.getSchoolTermCode());
				List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
				CommonSyllabus vo = null;
				for(SyllabusLesson syl : slList){
					Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
					vo = new CommonSyllabus();
					vo.setType(1);
					BeanUtils.copyProperties(syl, vo);
					if(teac!=null){
						vo.setTeacherName(teac.getName());
						String imgUrl = ImgUtil.getImgUrl(null);
						imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
						vo.setTeacherHeadUrl(imgUrl);
					}
					Subject subject = this.subjectService.findUnique(schoolId, syl.getSubjectCode());
					if(subject!=null){
						vo.setSubjectName(subject.getName());
					}
					//解决早上2节下午2节类似情况
					//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-syllabus.getLessonOfMorning())-1:syl.getLesson()-(8-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);
					/*int morningCount = syllabus.getLessonOfMorning();
					int afternoonCount = syllabus.getLessonOfMorning() + syllabus.getLessonOfAfternoon();
					
					vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
							syl.getLesson()-(morningCount-syllabus.getLessonOfMorning())-1:
								syl.getLesson()-(afternoonCount-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);	*/		
					vo.setLesson(syl.getLesson());
					sylVo.add(vo);
				}
//				syllabus.getDays();
				for(int i=0;i<allCount;i++){
					for(int j=0;j<7;j++){
						//一周7天
						list[i].add(j,null);
					}
				}
				for(CommonSyllabus sl : sylVo){
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
							Integer lesson = sl.getLesson();
							if(dayOfWeek.intValue()==j&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&(lesson.intValue()-1)==i){
								list[i].remove(j);
								list[i].add(j, sl);
							}
						}
					}
				}
				
				/*Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
				if(syllabus!=null){
					Integer allCount = syllabus.getLessonOfMorning()+syllabus.getLessonOfAfternoon()+syllabus.getLessonOfEvening();
					list = new ArrayList[allCount];
					for(int i=0;i<allCount;i++){
						list[i] = new ArrayList<SyllabusLesson>();
					}
					List<SyllabusLesson> slList = this.syllabusService.getSyllabusLessonBySyllabusId(syllabus.getId());
					List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
					CommonSyllabus vo = null;
					for(SyllabusLesson syl : slList){
						Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
						vo = new CommonSyllabus();
						vo.setType(1);
						BeanUtils.copyProperties(syl, vo);
						if(teac!=null){
							vo.setTeacherName(teac.getName());
							String imgUrl = ImgUtil.getImgUrl(null);
							imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
							vo.setTeacherHeadUrl(imgUrl);
						}
						Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), syl.getSubjectCode());
						if(subject!=null){
							vo.setSubjectName(subject.getName());
						}
						//解决早上2节下午2节类似情况
						//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-syllabus.getLessonOfMorning())-1:syl.getLesson()-(8-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);
						int morningCount = syllabus.getLessonOfMorning();
						int afternoonCount = syllabus.getLessonOfMorning() + syllabus.getLessonOfAfternoon();
						
						vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
								syl.getLesson()-(morningCount-syllabus.getLessonOfMorning())-1:
									syl.getLesson()-(afternoonCount-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);						
						sylVo.add(vo);
					}
//					syllabus.getDays();
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							//一周7天
							list[i].add(j,null);
						}
					}
					for(CommonSyllabus sl : sylVo){
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
								Integer lesson = sl.getLesson();
								if(dayOfWeek.intValue()==j&&lesson.intValue()==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&&lesson.intValue()==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}
							}
						}
					}
				}	*/		
			}
			
			return new ResponseVo<List[]>("0",list);
		} catch (NumberFormatException nfe) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId...");
			return new ResponseError("060112",info);
		} catch (Exception e ){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}
	}

	@Override
	public Object getTableByRoom(Integer roomId, String appKey, String signage) {
		try {
			if ( roomId == null ) {
				return ResponseUtil.paramerIsNull("roomId:"+roomId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			
			/*Team t = this.teamService.findTeamById(teamId);				
			if(t==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}*/	
			
			Room room = this.roomService.findRoomById(roomId);
			if(room == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("教室ID不存在");
				info.setMsg("教室ID不存在");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			Integer schoolId = room.getSchoolId();
			
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}	
			
			SchoolCourse schoolCourse = this.schoolCourseService.findSchoolCourseBySchoolId(schoolId);
			Boolean isOpen = false;
			if(schoolCourse != null){
				isOpen = schoolCourse.getIsOpen();
			}	
					
			List[] list = null;
			//开通走班制
			if(isOpen){		
				//获取走班课程表
				BwSyllabusVo syllabus = this.bwSyllabusService.findBwSyllabusByRoomIdAndTermCode(roomId, stc.getSchoolTermCode());
				if(syllabus != null){	
					BwGradeSyllabus gradeSyllabus = this.bwGradeSyllabusService.findBwGradeSyllabusById(syllabus.getGradeSyllabusId());
					if(gradeSyllabus != null){
						Integer allCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon() 
								+ gradeSyllabus.getLessonOfEvening();
						list = new ArrayList[allCount];
						for(int i=0;i<allCount;i++){
							list[i] = new ArrayList<SyllabusLesson>();
						}
						BwSyllabusLessonCondition bwSyllabusLessonCondition = new BwSyllabusLessonCondition();
						bwSyllabusLessonCondition.setSyllabusId(syllabus.getId());
						List<BwSyllabusLesson> slList = this.bwSyllabusLessonService.findBwSyllabusLessonByCondition(bwSyllabusLessonCondition);
						List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
						CommonSyllabus vo = null;
						for(BwSyllabusLesson syl : slList){
							Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
							vo = new CommonSyllabus();
							BeanUtils.copyProperties(syl, vo);
							vo.setSyllabusId(syl.getId());
							if(teac!=null){
								vo.setTeacherName(teac.getName());
								String imgUrl = ImgUtil.getImgUrl(null);
								imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
								vo.setTeacherHeadUrl(imgUrl);
							}
							Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), syl.getSubjectCode());
							if(subject!=null){
								vo.setSubjectName(subject.getName());
							}
							//解决早上2节下午2节类似情况
							//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-gradeSyllabus.getLessonOfMorning())-1:syl.getLesson()-(8-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);
							int morningCount = gradeSyllabus.getLessonOfMorning();
							int afternoonCount = gradeSyllabus.getLessonOfMorning() + gradeSyllabus.getLessonOfAfternoon();
							
							/*vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
									syl.getLesson()-(morningCount-gradeSyllabus.getLessonOfMorning())-1:
										syl.getLesson()-(afternoonCount-gradeSyllabus.getLessonOfMorning()-gradeSyllabus.getLessonOfAfternoon())-1);*/
							vo.setLesson(syl.getLesson());
							sylVo.add(vo);
						}
						
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								//一周7天
								list[i].add(j,null);
							}
							/*String dayPlan = syllabus.getDaysPlan();
							String[] dayPlanArr = dayPlan.split(",");
							for(String dayPlanStr: dayPlanArr){
								list[i].add(Integer.parseInt(dayPlanStr), null);
							}*/
						}
						
						for(CommonSyllabus sl : sylVo){
							for(int i=0;i<allCount;i++){
								for(int j=0;j<7;j++){
									Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
									Integer lesson = sl.getLesson();
									if(dayOfWeek.intValue()==j&& (lesson.intValue()-1)==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&& (lesson.intValue()-1)==i){
										list[i].remove(j);
										list[i].add(j, sl);
									}
								}
							}
						}
					}
				}
			}else{
				
				//获取房室绑定的班级
				RoomTeam roomTeam = this.roomTeamService.findRoomTeamByRoomId(roomId, stc.getSchoolYear());
				if(roomTeam == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("房室未绑定班级");
					info.setMsg("房室未绑定班级");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				Integer teamId = roomTeam.getTeamId();
				
				Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, stc.getSchoolTermCode());
				if(syllabus!=null){
					Integer allCount = syllabus.getLessonOfMorning()+syllabus.getLessonOfAfternoon()+syllabus.getLessonOfEvening();
					list = new ArrayList[allCount];
					for(int i=0;i<allCount;i++){
						list[i] = new ArrayList<SyllabusLesson>();
					}
					List<SyllabusLesson> slList = this.syllabusService.getSyllabusLessonBySyllabusId(syllabus.getId());
					List<CommonSyllabus> sylVo = new ArrayList<CommonSyllabus>();
					CommonSyllabus vo = null;
					for(SyllabusLesson syl : slList){
						Teacher teac = this.teacherService.findTeacherById(syl.getTeacherId());
						vo = new CommonSyllabus();
						vo.setType(1);
						BeanUtils.copyProperties(syl, vo);
						if(teac!=null){
							vo.setTeacherName(teac.getName());
							String imgUrl = ImgUtil.getImgUrl(null);
							imgUrl = ImgUtil.getStudentIconUrl(teac.getPersionId(), personService);
							vo.setTeacherHeadUrl(imgUrl);
						}
						Subject subject = this.subjectService.findUnique(syllabus.getSchoolId(), syl.getSubjectCode());
						if(subject!=null){
							vo.setSubjectName(subject.getName());
						}
						//解决早上2节下午2节类似情况
						//vo.setLesson(syl.getLesson()<4?syl.getLesson()-1:syl.getLesson()<8?syl.getLesson()-(4-syllabus.getLessonOfMorning())-1:syl.getLesson()-(8-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);
						int morningCount = syllabus.getLessonOfMorning();
						int afternoonCount = syllabus.getLessonOfMorning() + syllabus.getLessonOfAfternoon();
						
						/*vo.setLesson(syl.getLesson()<morningCount?syl.getLesson()-1:syl.getLesson()<afternoonCount?
								syl.getLesson()-(morningCount-syllabus.getLessonOfMorning())-1:
									syl.getLesson()-(afternoonCount-syllabus.getLessonOfMorning()-syllabus.getLessonOfAfternoon())-1);	*/
						vo.setLesson(syl.getLesson());
						sylVo.add(vo);
					}
//					syllabus.getDays();
					for(int i=0;i<allCount;i++){
						for(int j=0;j<7;j++){
							//一周7天
							list[i].add(j,null);
						}
						/*String dayPlan = syllabus.getDaysPlan();
						String[] dayPlanArr = dayPlan.split(",");
						for(String dayPlanStr: dayPlanArr){
							list[i].add(Integer.parseInt(dayPlanStr), null);
						}*/
					}
					for(CommonSyllabus sl : sylVo){
						for(int i=0;i<allCount;i++){
							for(int j=0;j<7;j++){
								Integer dayOfWeek = Integer.parseInt(sl.getDayOfWeek())-1;
								Integer lesson = sl.getLesson();
								if(dayOfWeek.intValue()==j&&(lesson.intValue()-1)==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}else if(Integer.parseInt(sl.getDayOfWeek())==0&&j==6&& (lesson.intValue()-1)==i){
									list[i].remove(j);
									list[i].add(j, sl);
								}
							}
						}
					}
				}			
			}
			return new ResponseVo<List[]>("0",list);
		} catch (NumberFormatException nfe) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId...");
			return new ResponseError("060112",info);
		} catch (Exception e ){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}
	}

	
}
