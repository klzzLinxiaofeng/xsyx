package platform.education.rest.bp.service.impl;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.*;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.*;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.rest.basic.service.vo.ExtTeamStudentVo;
import platform.education.rest.bp.service.BpAttendanceRestService;
import platform.education.rest.bp.service.contants.BpAttendancesContants;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.*;
import platform.education.rest.bp.service.vo.*;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.AppEditionService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BpAttendanceRestServiceImpl implements BpAttendanceRestService {
	
	private static final SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("teamStudentService")
	protected TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("bpBwAttendancesService")
	private BpBwAttendancesService bpBwAttendancesService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("bpBwSchoolCardService")
	private BpBwSchoolCardService bpBwSchoolCardService;
	
	@Autowired
	@Qualifier("schoolCourseService")
	private SchoolCourseService schoolCourseService;
	
	@Autowired
	@Qualifier("bwSyllabusLessonService")
	private BwSyllabusLessonService bwSyllabusLessonService;
	
	@Autowired
	@Qualifier("syllabusStudentService")
	private SyllabusStudentService syllabusStudentService;
	
	@Autowired
	@Qualifier("attendancesSyllabusService")
	private AttendancesSyllabusService attendancesSyllabusService;
	
	@Autowired
	@Qualifier("syllabusEvaluateService")
	private SyllabusEvaluateService syllabusEvaluateService;
	
	@Resource(name = "bp_attendance_taskExecutor")
	private TaskExecutor taskExecutor;
	

	@Override
	public Object getAttendanceList(Integer teamId, Long attendanceDay, String pageSize, String appKey, String signage) {		
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			/*if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+ attendanceDay);
			}
			*/
			List<BpAttendanceVo> items = new ArrayList<BpAttendanceVo>();			
			Date attendanceD = new Date(attendanceDay);
			for(int i=0; i<Integer.valueOf(pageSize); i++){
				Date d = DateUtil.getDay(attendanceD, i, false);
				d = shortSdf.parse(shortSdf.format(d));
				items.add(getAttendanceVo(d, teamId));
			}		
			return new ResponseVo<List<BpAttendanceVo>>("0", items);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
		
	}

	private BpAttendanceVo getAttendanceVo(Date attendanceDay, Integer teamId){
		BpAttendanceVo bpAttendanceVo = new BpAttendanceVo();
		List<PersonVo> lateList = new ArrayList<PersonVo>();
		List<PersonVo> outEarlyList = new ArrayList<PersonVo>();
		List<PersonVo> absentList = new ArrayList<PersonVo>();
		List<PersonVo> leaveList = new ArrayList<PersonVo>();
		
		BpBwAttendancesCondition condition = new BpBwAttendancesCondition();
		condition.setAttendanceDay(attendanceDay);
		condition.setTeamId(teamId);
		List<BpBwAttendances> list = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
		if(list != null && list.size() > 0){
			for(BpBwAttendances b: list){
				if(b.getIsAbsent()){
					absentList.add(this.getPersonVo(b.getStudentId()));
				}
				if(b.getIsLate()){
					lateList.add(this.getPersonVo(b.getStudentId()));
				}
				if(b.getIsOutEarly()){
					outEarlyList.add(this.getPersonVo(b.getStudentId()));
				}
				if(b.getIsLeave()){
					leaveList.add(this.getPersonVo(b.getStudentId()));
				}
			}
		}
		bpAttendanceVo.setAttendanceDay(attendanceDay.getTime());
		bpAttendanceVo.setLateList(lateList);
		bpAttendanceVo.setOutEarlyList(outEarlyList);
		bpAttendanceVo.setAbsentList(absentList);
		bpAttendanceVo.setLeaveList(leaveList);
		return bpAttendanceVo;
	}
	
	private PersonVo getPersonVo(Integer studentId){		
		Student student = this.studentService.findStudentByUserId(studentId);
		if(student == null){
			return null;
		}
		PersonVo personVo = new PersonVo();
		personVo.setUserId(student.getUserId());
		personVo.setName(student.getName());
		personVo.setIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
		return personVo;
	}

	@Override
	public Object createAttendance(Integer teamId, Integer type, String userIds, Long attendanceDay, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			/*if (StringUtils.isEmpty(type)) {
				return ResponseUtil.paramerIsNull("type:"+type);
			}
			if (StringUtils.isEmpty(userIds)) {
				return ResponseUtil.paramerIsNull("userIds:"+userIds);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+ attendanceDay);
			}*/
			
			String[] userIdArr = userIds.split(",");
			BpBwAttendances bwAttendances = null;
			Date attendanceD = new Date(attendanceDay);
			Team team = this.teamService.findTeamById(teamId);
			if(team == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			for(String userIdStr: userIdArr){
				Integer userId = Integer.valueOf(userIdStr);
				bwAttendances =  this.bpBwAttendancesService
						.findBwAttendancesByStudentIdAndDay(userId, attendanceD);				
				if(bwAttendances == null){
					bwAttendances = new BpBwAttendances();
					bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
					bwAttendances.setStudentId(userId);				
					bwAttendances.setSchoolYear(stc.getSchoolYear());
					bwAttendances.setSchoolId(team.getSchoolId());
					bwAttendances.setGradeId(team.getGradeId());
					bwAttendances.setTeamId(teamId);
					bwAttendances.setTermCode(stc.getSchoolTermCode());
					bwAttendances.setAttendanceDay(attendanceD);
					if(BpAttendancesContants.ATTENDANCE_TYPE_LATE == type){
						bwAttendances.setIsLate(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_OUT_EARLY == type){
						bwAttendances.setIsOutEarly(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_ABSENT == type){
						bwAttendances.setIsAbsent(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_LEAVE == type){
						bwAttendances.setIsAbsent(true);
					}
					bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
				}else{
					if(BpAttendancesContants.ATTENDANCE_TYPE_LATE == type){
						bwAttendances.setIsLate(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_OUT_EARLY == type){
						bwAttendances.setIsOutEarly(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_ABSENT == type){
						bwAttendances.setIsAbsent(true);
					}else if(BpAttendancesContants.ATTENDANCE_TYPE_LEAVE == type){
						bwAttendances.setIsAbsent(true);
					}
					bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
				}	
			}		
			return new ResponseVo<Integer>("0", bwAttendances.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object createAttendanceTime(String cardId, Long attendanceDay, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
		/*	if (StringUtils.isEmpty(cardId)) {
				return ResponseUtil.paramerIsNull("cardId:"+cardId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			
			BpBwSchoolCard bpBwSchoolCard = this.bpBwSchoolCardService.findBwSchoolCardByAccountId(cardId);		
			Integer userId = bpBwSchoolCard.getUserId();
			Student student = studentService.findStudentByUserId(userId);
			Team team = this.teamService.findTeamById(student.getTeamId());	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
					
			BpBwAttendances bwAttendances = null;
			Date attendanceDate = new Date(attendanceDay);		
			Date shortDate = shortSdf.parse(shortSdf.format(attendanceDate));
			
			bwAttendances =  this.bpBwAttendancesService.findBwAttendancesByStudentIdAndDay(userId, shortDate);				
			if(bwAttendances == null){
				bwAttendances = new BpBwAttendances();
				bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
				bwAttendances.setStudentId(userId);				
				bwAttendances.setSchoolYear(stc.getSchoolYear());
				bwAttendances.setSchoolId(team.getSchoolId());
				bwAttendances.setGradeId(team.getGradeId());
				bwAttendances.setTeamId(team.getId());
				bwAttendances.setTermCode(stc.getSchoolTermCode());
				bwAttendances.setAttendanceDay(attendanceDate);
				bwAttendances.setInTime(attendanceDate);
				bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
			}else{
				if(bwAttendances.getInTime() == null){
					bwAttendances.setInTime(attendanceDate);
				}else{
					bwAttendances.setOutTime(attendanceDate);
				}
				bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
			}
			
			//要推送的班级
			List<Integer> userIds = new ArrayList<Integer>();
			userIds.add(userId);
			//推送data
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", student.getUserId());
			map.put("name", student.getName());
			map.put("attendanceTime", attendanceDay);
			IMPushUtil.pushToUser(userIds, DataAction.D$add, userId, BpCommonConstants.PUSH_ATTENDANCE,
					JSONObject.fromObject(map).toString(), taskExecutor);
			//推送结束 ====	
			
			PersonVo personVo = new PersonVo();
			personVo.setUserId(student.getUserId());
			personVo.setName(student.getName());
			personVo.setIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
			return new ResponseVo<PersonVo>("0", personVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object statisticsSchoolAttendance(Integer schoolId, Long startDay, Long endDay, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
		/*	if (StringUtils.isEmpty(startDay)) {
				return ResponseUtil.paramerIsNull("startDay:"+startDay);
			}
			if (StringUtils.isEmpty(endDay)) {
				return ResponseUtil.paramerIsNull("endDay:"+endDay);
			}*/
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			List<StaGradeAttendanceVo> list = this.bpBwAttendancesService
					.findStatisticsAttendance(schoolId, shortSdf.format(new Date(startDay)), 
							shortSdf.format(new Date(endDay)), schoolTermCurrent.getSchoolTermCode());
			return new ResponseVo<List<StaGradeAttendanceVo>>("0", list);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object createAttendanceTimeBySchool(String cardId,
			Long attendanceDay, Integer schoolId, String uuid,String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(cardId)) {
				return ResponseUtil.paramerIsNull("cardId:"+cardId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			
			BpBwSchoolCard bpBwSchoolCard = this.bpBwSchoolCardService.findBwSchoolCardByAccountId(cardId);		
			Integer userId = bpBwSchoolCard.getUserId();
			Student student = studentService.findStudentByUserId(userId);
			Team team = this.teamService.findTeamById(student.getTeamId());	
			if(!schoolId.equals(team.getSchoolId())){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("非本校IC卡");
				info.setMsg("非本校IC卡");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
					
			BpBwAttendances bwAttendances = null;
			Date attendanceDate = new Date(attendanceDay);		
			Date shortDate = shortSdf.parse(shortSdf.format(attendanceDate));
			
			bwAttendances =  this.bpBwAttendancesService.findBwAttendancesByStudentIdAndDay(userId, shortDate);				
			if(bwAttendances == null){
				bwAttendances = new BpBwAttendances();
				bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
				bwAttendances.setStudentId(userId);				
				bwAttendances.setSchoolYear(stc.getSchoolYear());
				bwAttendances.setSchoolId(team.getSchoolId());
				bwAttendances.setGradeId(team.getGradeId());
				bwAttendances.setTeamId(team.getId());
				bwAttendances.setTermCode(stc.getSchoolTermCode());
				bwAttendances.setAttendanceDay(attendanceDate);
				bwAttendances.setInTime(attendanceDate);
				bwAttendances.setInTimeUuid(uuid);
				bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
			}else{
				if(bwAttendances.getInTime() == null){
					bwAttendances.setInTime(attendanceDate);
					bwAttendances.setInTimeUuid(uuid);
				}else{
					bwAttendances.setOutTime(attendanceDate);
					bwAttendances.setOutTimeUuid(uuid);
				}
				bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
			}
			
			//要推送的班级
			/*List<Integer> userIds = new ArrayList<Integer>();
			userIds.add(userId);
			//推送data
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", student.getUserId());
			map.put("name", student.getName());
			map.put("attendanceTime", attendanceDay);
			IMPushUtil.pushToUser(userIds, DataAction.D$add, userId, BpCommonConstants.PUSH_ATTENDANCE, 
					JSONObject.fromObject(map).toString(), taskExecutor);*/
			//推送结束 ====	
			
			PersonVo personVo = new PersonVo();
			personVo.setUserId(student.getUserId());
			personVo.setName(student.getName());
			personVo.setIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
			return new ResponseVo<PersonVo>("0", personVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object listAttendanceTime(Integer teamId, Long attendanceDay,String appKey, String signage) {
		try {
		/*	if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			Date attendanceDate = new Date(attendanceDay);		
			Date shortDate = shortSdf.parse(shortSdf.format(attendanceDate));
			List<Map<String, Object>> list = this.bpBwAttendancesService.findAttendanceTimeList(teamId, shortDate);
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			if(list != null && list.size() > 0){
				for(Map<String, Object> map : list){
					Map<String, Object> result = new HashMap<String, Object>();
					Integer userId = (Integer) map.get("userId");
					Student student = this.studentService.findStudentByUserId(userId);
					result.put("userId", userId);
					result.put("attnedanceTime", ((Date)map.get("attendanceTime")).getTime());
					result.put("name", student.getName());
					result.put("icon", ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
					resultList.add(result);
				}
			}
			return new ResponseVo<List<Map<String, Object>>>("0", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object studentAttendanceList(Integer teamId, Integer studentId,Integer week, String appKey, String signage) {
		try{
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			Team team = this.teamService.findTeamById(teamId);	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			WeekVo weekVo = WeekUtil.getDate(stc.getBeginDate(), stc.getFinishDate(), week);
			BpStudentAttendanceVo vo = new BpStudentAttendanceVo();
			BpBwAttendancesCondition condition = new BpBwAttendancesCondition();
			condition.setStartDay(shortSdf.format(weekVo.getBegin()));
			condition.setEndDay(shortSdf.format(weekVo.getEnd()));
			condition.setStudentId(studentId);
			condition.setIsDeleted(false);
			condition.setTeamId(teamId);
			condition.setIsLate(true);
			//迟到
			List<BpBwAttendances> late = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
			vo.setLateList(late);
			condition.setIsLate(null);
			//缺勤
			condition.setIsAbsent(true);
			List<BpBwAttendances> absent = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
			vo.setAbsentList(absent);
			condition.setIsAbsent(null);
			//请假
			condition.setIsLeave(true);
			List<BpBwAttendances> leave = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
			vo.setLeaveList(leave);
			condition.setIsLeave(null);
			//早退
			condition.setIsOutEarly(true);
			List<BpBwAttendances> outEarly = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
			vo.setOutEarlyList(outEarly);
			return new ResponseVo<BpStudentAttendanceVo>("0", vo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	
	@Override
	public Object getAttendanceStudentList(String teamId, String type,Long attendanceDay) {
		// TODO Auto-generated method stub
		List<TeamStudentVo> teamStudentVoList = new ArrayList<TeamStudentVo>();
		List<ExtTeamStudentVo> teamStudents = new ArrayList<ExtTeamStudentVo>();
		List<ExtTeamStudentVo> removeStudents = new ArrayList<ExtTeamStudentVo>();
		AttendanceStudentVo attendanceStudentVo = new AttendanceStudentVo();
		try{
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			Team team = this.teamService.findTeamById(Integer.parseInt(teamId));	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			Student student = null;
			teamStudentVoList = this.teamStudentService.getTeamStudentsByTeamId(Integer.parseInt(teamId));
			//默认头像
			String imgUrl = ImgUtil.getImgUrl(null);
			for (TeamStudentVo vo : teamStudentVoList) {
				Integer personId = this.studentService.findStudentByUserId(vo.getUserId()).getPersonId();
				imgUrl = ImgUtil.getStudentIconUrl(personId, personService);
				Student s = this.studentService.findStudentById(vo.getStudentId());
				ExtTeamStudentVo teamStudentVo = new ExtTeamStudentVo();
				if(s.getPosition()!=null){
					teamStudentVo.setPosition(s.getPosition());
				}
				BpBwAttendancesCondition condition = new BpBwAttendancesCondition();
//				condition.setIsLate(false);
//				condition.setIsOutEarly(false);
//				condition.setIsAbsent(false);
//				condition.setIsLeave(false);
				condition.setStudentId(vo.getUserId());
				condition.setIsDeleted(false);
				condition.setTeamId(Integer.parseInt(teamId));
				condition.setAttendanceDay(new Date(attendanceDay));
				condition.setTermCode(stc.getSchoolTermCode());
				if(!"".equals(type) && type.equals("isLate")){
					condition.setIsLate(true);
				}else if(!"".equals(type) && type.equals("isOutEarly")){
					condition.setIsOutEarly(true);
				}else if(!"".equals(type) && type.equals("isAbsent")){
					condition.setIsAbsent(true);
				}else if(!"".equals(type) && type.equals("isLeave")){
					condition.setIsLeave(true);
				}else if(!"".equals(type) && type.equals("isRemove")){
					condition.setIsLate(null);
					condition.setIsOutEarly(null);
					condition.setIsAbsent(null);
					condition.setIsLeave(null);
				}
				List<BpBwAttendances> list = this.bpBwAttendancesService.findBwAttendancesByCondition(condition);
				teamStudentVo.setUserIcon(imgUrl);
				teamStudentVo.setAlias(student == null ? null : student.getAlias());
				teamStudentVo.setId(vo.getUserId());
				teamStudentVo.setStudentId(vo.getStudentId());
				teamStudentVo.setName(vo.getName());
				teamStudentVo.setSex(vo.getSex());
				teamStudentVo.setNumber(vo.getNumber());
				teamStudentVo.setStudentNumber(vo.getStudentNumber());
				teamStudentVo.setUserName(vo.getUserName());
				if(list.size()>0){
					removeStudents.add(teamStudentVo);
				}else{
					teamStudents.add(teamStudentVo);
				}
			}
			attendanceStudentVo.setRemoveStudents(removeStudents);
			attendanceStudentVo.setTeamStudents(teamStudents);
			return new ResponseVo<AttendanceStudentVo>("0", attendanceStudentVo);
			
		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过班级ID获取班级学生名单异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	
	@Override
	public Object createStudentAttendance(Integer teamId, String studentIds,
			Long attendanceDay, String remarks,String type,String isRemove,String appKey, String signage) {
		BpBwAttendances bwAttendances = null;
		try {
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			Team team = this.teamService.findTeamById(teamId);	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			String[] ids = studentIds.split(",");
			for (String studentId : ids) {
				bwAttendances =  this.bpBwAttendancesService
						.findBwAttendancesByStudentIdAndDay(Integer.parseInt(studentId), new Date(attendanceDay));				
				if(bwAttendances == null){
					bwAttendances = new BpBwAttendances();
					bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
					bwAttendances.setStudentId(Integer.parseInt(studentId));
					bwAttendances.setSchoolYear(stc.getSchoolYear());
					bwAttendances.setSchoolId(stc.getSchoolId());
					bwAttendances.setGradeId(team.getGradeId());
					bwAttendances.setTeamId(teamId);
					bwAttendances.setTermCode(stc.getSchoolTermCode());
					bwAttendances.setAttendanceDay(new Date(attendanceDay));
					if(!"".equals(type) && type.equals("isLate")){
						bwAttendances.setIsLate(true);
					}else if(!"".equals(type) && type.equals("isOutEarly")){
						bwAttendances.setIsOutEarly(true);
					}else if(!"".equals(type) && type.equals("isAbsent")){
						bwAttendances.setIsAbsent(true);
					}else if(!"".equals(type) && type.equals("isLeave")){
						bwAttendances.setIsLeave(true);
						bwAttendances.setRemarks(remarks);
					}
					this.bpBwAttendancesService.add(bwAttendances);
				}else{
					if(isRemove.equals("0")){
						if(!"".equals(type) && type.equals("isLate")){
							bwAttendances.setIsLate(true);
						}else if(!"".equals(type) && type.equals("isOutEarly")){
							bwAttendances.setIsOutEarly(true);
						}else if(!"".equals(type) && type.equals("isAbsent")){
							bwAttendances.setIsAbsent(true);
						}else if(!"".equals(type) && type.equals("isLeave")){
							bwAttendances.setIsLeave(true);	
							bwAttendances.setRemarks(remarks);
						}
					}else{
						if(!"".equals(type) && type.equals("isLate")){
							bwAttendances.setIsLate(false);
						}else if(!"".equals(type) && type.equals("isOutEarly")){
							bwAttendances.setIsOutEarly(false);
						}else if(!"".equals(type) && type.equals("isAbsent")){
							bwAttendances.setIsAbsent(false);
						}else if(!"".equals(type) && type.equals("isLeave")){
							bwAttendances.setIsLeave(false);
							bwAttendances.setRemarks("");
						}
					}
					this.bpBwAttendancesService.modify(bwAttendances);
				}
			}
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
	public Object allAttendanceTimeList(Integer teamId, Long attendanceDay,String pageSize,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+ attendanceDay);
			}*/
			
			List<BpAttendanceVo> items = new ArrayList<BpAttendanceVo>();			
			Date attendanceD = new Date(attendanceDay);
			for(int i=0; i<Integer.valueOf(pageSize); i++){
				Date d = DateUtil.getDay(attendanceD, i, false);
				d = shortSdf.parse(shortSdf.format(d));
				items.add(getAllAttendanceVo(d, teamId));
				
			}		
			return new ResponseVo<List<BpAttendanceVo>>("0", items);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	private BpAttendanceVo getAllAttendanceVo(Date attendanceDay, Integer teamId){
		
		BpAttendanceVo bpAttendanceVo = this.getAttendanceVo(attendanceDay, teamId);
		Date shortDate = null;
		try {
			shortDate = shortSdf.parse(shortSdf.format(attendanceDay));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> mapList = this.bpBwAttendancesService.findAttendanceTimeList(teamId, shortDate);
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		if(mapList != null && mapList.size() > 0){
			for(Map<String, Object> map : mapList){
				Map<String, Object> result = new HashMap<String, Object>();
				Integer userId = (Integer) map.get("userId");
				Student student = this.studentService.findStudentByUserId(userId);
				result.put("userId", userId);
				result.put("attnedanceTime", ((Date)map.get("attendanceTime")).getTime());
				result.put("name", student.getName());
				result.put("icon", ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
				resultList.add(result);
			}
		}
		bpAttendanceVo.setAttendanceTimeList(resultList);
		return bpAttendanceVo;
	}

	
	@Override
	public Object recordCardBySchool(String cardId, Integer type, Long attendanceDay, Integer schoolId, Integer syllabusLessonId, String uuid,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
		/*	if (StringUtils.isEmpty(cardId)) {
				return ResponseUtil.paramerIsNull("cardId:"+cardId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			/*if (StringUtils.isEmpty(syllabusLessonId)) {
				return ResponseUtil.paramerIsNull("syllabusLessonId:"+syllabusLessonId);
			}*/
			
			BpBwSchoolCard bpBwSchoolCard = this.bpBwSchoolCardService.findBwSchoolCardByAccountId(cardId);
			if(bpBwSchoolCard == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("非法卡号");
				info.setMsg("非法卡号");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			Integer userId = bpBwSchoolCard.getUserId();
			Student student = studentService.findStudentByUserId(userId);
			Team team = this.teamService.findTeamById(student.getTeamId());	
			if(!schoolId.equals(team.getSchoolId())){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("非本校IC卡");
				info.setMsg("非本校IC卡");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}	
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			
			
			if(type == 1){
				//行政考勤			
				BpBwAttendances bwAttendances = null;
				Date attendanceDate = new Date(attendanceDay);		
				Date shortDate = shortSdf.parse(shortSdf.format(attendanceDate));				
				bwAttendances =  this.bpBwAttendancesService.findBwAttendancesByStudentIdAndDay(userId, shortDate);				
				if(bwAttendances == null){
					bwAttendances = new BpBwAttendances();
					bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
					bwAttendances.setStudentId(userId);				
					bwAttendances.setSchoolYear(stc.getSchoolYear());
					bwAttendances.setSchoolId(team.getSchoolId());
					bwAttendances.setGradeId(team.getGradeId());
					bwAttendances.setTeamId(team.getId());
					bwAttendances.setTermCode(stc.getSchoolTermCode());
					bwAttendances.setAttendanceDay(attendanceDate);
					bwAttendances.setInTime(attendanceDate);
					bwAttendances.setInTimeUuid(uuid);
					bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
				}else{
					if(bwAttendances.getInTime() == null){
						bwAttendances.setInTime(attendanceDate);
						bwAttendances.setInTimeUuid(uuid);
					}else{
						bwAttendances.setOutTime(attendanceDate);
						bwAttendances.setOutTimeUuid(uuid);
					}
					bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
				}
			}else if(type == 2){
				//走班考勤
				
				//1、更新行政打卡						
				BpBwAttendances bwAttendances = null;
				Date attendanceDate = new Date(attendanceDay);		
				Date shortDate = shortSdf.parse(shortSdf.format(attendanceDate));				
				bwAttendances =  this.bpBwAttendancesService.findBwAttendancesByStudentIdAndDay(userId, shortDate);				
				if(bwAttendances == null){
					bwAttendances = new BpBwAttendances();
					bwAttendances.setCheckType(BpAttendancesContants.ADMINISTRATION);
					bwAttendances.setStudentId(userId);				
					bwAttendances.setSchoolYear(stc.getSchoolYear());
					bwAttendances.setSchoolId(team.getSchoolId());
					bwAttendances.setGradeId(team.getGradeId());
					bwAttendances.setTeamId(team.getId());
					bwAttendances.setTermCode(stc.getSchoolTermCode());
					bwAttendances.setAttendanceDay(attendanceDate);
					bwAttendances.setInTime(attendanceDate);
					bwAttendances = this.bpBwAttendancesService.add(bwAttendances);
				}else{
					if(bwAttendances.getInTime() == null){
						bwAttendances.setInTime(attendanceDate);
						bwAttendances.setInTimeUuid(uuid);
					}else{
						bwAttendances.setOutTime(attendanceDate);
						bwAttendances.setOutTimeUuid(uuid);
					}
					bwAttendances = this.bpBwAttendancesService.modify(bwAttendances);
				}
				
				//2、走班打卡
				//获取打卡时间在本学期第几周
				WeekVo weekVo = WeekUtil.Week(stc.getBeginDate(), stc.getFinishDate(), new Date(attendanceDay));
				if(weekVo == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("本周没有该课程");
					info.setMsg("本周没有该课程");
					return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
				}
				
				//上课考勤,开通走班才有上课考勤
				SchoolCourse schoolCourse = schoolCourseService.findSchoolCourseBySchoolId(schoolId);
				if(schoolCourse != null && schoolCourse.getIsOpen()){
					//通过syllabusLessonId查找该节课类型，只有走班课才需要考勤
					if(syllabusLessonId != null){
						BwSyllabusLesson bwSyllabusLesson = bwSyllabusLessonService.findBwSyllabusLessonById(syllabusLessonId);
						if(bwSyllabusLesson != null && bwSyllabusLesson.getType() == BpCommonConstants.COURSE_TYPE_2){
							//判断该学生是否有该课程
							SyllabusStudentCondition syllabusStudentCondition = new SyllabusStudentCondition();
							syllabusStudentCondition.setStudentUserId(userId);
							syllabusStudentCondition.setLessonId(bwSyllabusLesson.getId());
							Long count = syllabusStudentService.count(syllabusStudentCondition);
							if(count > 0){
								//该学生有该课程
								//判断该学生是否已签到
								AttendancesSyllabusCondition attendancesSyllabusCondition = new AttendancesSyllabusCondition();
								attendancesSyllabusCondition.setLessonId(bwSyllabusLesson.getId());
								attendancesSyllabusCondition.setStudentUserId(userId);
								attendancesSyllabusCondition.setWeek(weekVo.getWeek());
								Long c = attendancesSyllabusService.count(attendancesSyllabusCondition);
								if(c == 0){
									AttendancesSyllabus attendancesSyllabus = new AttendancesSyllabus();
									attendancesSyllabus.setLessonId(bwSyllabusLesson.getId());
									attendancesSyllabus.setStudentUserId(userId);
									attendancesSyllabus.setWeek(weekVo.getWeek());
									attendancesSyllabusService.add(attendancesSyllabus);
								}
							}else{
								ResponseInfo info = new ResponseInfo();
								info.setDetail("学生没有该课程");
								info.setMsg("学生没有该课程");
								return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
							}
						}
					}
				}				
			}
			
			PersonVo personVo = new PersonVo();
			personVo.setUserId(student.getUserId());
			personVo.setName(student.getName());
			personVo.setIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
			return new ResponseVo<PersonVo>("0", personVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object attendanceSyllabus(Integer lessonId, Integer week,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(lessonId)) {
				return ResponseUtil.paramerIsNull("lessonId:"+lessonId);
			}
			if (StringUtils.isEmpty(week)) {
				return ResponseUtil.paramerIsNull("week:"+week);
			}*/
			BpAttendanceSyllabusVo attendanceSyllabusVo = new BpAttendanceSyllabusVo();
			List<AttendancesSyllabusVo> turnOutList = new ArrayList<AttendancesSyllabusVo>();//出勤
			List<AttendancesSyllabusVo> absenceList = new ArrayList<AttendancesSyllabusVo>();//缺勤
			AttendancesSyllabusVo vo = null;
			AttendancesSyllabusCondition condition = new AttendancesSyllabusCondition();
			condition.setWeek(week);
			condition.setLessonId(lessonId);
			List<AttendancesSyllabus> list = this.attendancesSyllabusService.findAttendancesSyllabusByCondition(condition);
			SyllabusStudentCondition syllabusStudentCondition = new SyllabusStudentCondition();
			syllabusStudentCondition.setLessonId(lessonId);

			List<SyllabusStudent> syllabusStudentList= this.syllabusStudentService.findSyllabusStudentByCondition(syllabusStudentCondition);
			for(AttendancesSyllabus attendancesSyllabus:list){
				vo = new AttendancesSyllabusVo();
				syllabusStudentCondition = new SyllabusStudentCondition();
				syllabusStudentCondition.setLessonId(lessonId);
				syllabusStudentCondition.setStudentUserId(attendancesSyllabus.getStudentUserId());
				SyllabusStudent syllabusStudent = this.syllabusStudentService.findSyllabusStudentByCondition(syllabusStudentCondition).get(0);
				Student student = this.studentService.findStudentByUserId(attendancesSyllabus.getStudentUserId());
				//默认头像
				String imgUrl = ImgUtil.getImgUrl(null);
				imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
				vo.setImgUrl(imgUrl);
				vo.setId(syllabusStudent.getId());
				vo.setStudentUserId(student.getUserId());
				vo.setStudentName(student.getName());
				vo.setSex(student.getSex());
				turnOutList.add(vo);
			}
			for(SyllabusStudent syllabusStudent:syllabusStudentList){
				Boolean flag = true;
				for(AttendancesSyllabus attendancesSyllabus:list){
					if(syllabusStudent.getStudentUserId().equals(attendancesSyllabus.getStudentUserId())){
						flag = false;
					}
				}
				if(flag){
					vo = new AttendancesSyllabusVo();
					Student student = this.studentService.findStudentByUserId(syllabusStudent.getStudentUserId());
					//默认头像
					String imgUrl = ImgUtil.getImgUrl(null);
					imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
					vo.setImgUrl(imgUrl);
					vo.setId(syllabusStudent.getId());
					vo.setStudentUserId(student.getUserId());
					vo.setStudentName(student.getName());
					vo.setSex(student.getSex());
					absenceList.add(vo);
				}
			}
			attendanceSyllabusVo.setAbsenceList(absenceList);
			attendanceSyllabusVo.setTurnOutList(turnOutList);
			return new ResponseVo<BpAttendanceSyllabusVo>("0", attendanceSyllabusVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object studentAttendance(Integer lessonId, Integer week,Integer userId,Integer schoolId,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(lessonId)) {
				return ResponseUtil.paramerIsNull("lessonId:"+lessonId);
			}
			if (StringUtils.isEmpty(week)) {
				return ResponseUtil.paramerIsNull("week:"+week);
			}*/
			List<AttendancesSyllabus> list = null;
			List<AttendancesSyllabusVo> newList = new ArrayList<AttendancesSyllabusVo>();
			AttendancesSyllabusVo vo = null;
			AttendancesSyllabusCondition condition = new AttendancesSyllabusCondition(); 
//			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
//			Date begin = schoolTermCurrent.getBeginDate();
//			Date finish = schoolTermCurrent.getFinishDate();
//			List<WeekVo> weekList = WeekUtil.getWeeks(begin, finish);
			condition.setLessonId(lessonId);
			condition.setStudentUserId(userId);
			for(int i=1;i<=week;i++){
				condition.setWeek(i);
				list = this.attendancesSyllabusService.findAttendancesSyllabusByCondition(condition);
				vo = new AttendancesSyllabusVo();
				vo.setWeekName("第"+i+"周");
				if(list.size()>0){
					if(!list.get(0).getIsAttendance()){
						vo.setAttendances(BpAttendancesContants.LESSON_TURN_OUT);
					}else{
						vo.setAttendances(BpAttendancesContants.LESSON_LEAVE);
					}	
				}else{
					vo.setAttendances(BpAttendancesContants.LESSON_ABSENCE);
				}
				newList.add(vo);
			}
			return new ResponseVo<List<AttendancesSyllabusVo>>("0", newList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object attendanceEvaluate(Integer lessonId, Integer week,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(lessonId)) {
				return ResponseUtil.paramerIsNull("lessonId:"+lessonId);
			}
			if (StringUtils.isEmpty(week)) {
				return ResponseUtil.paramerIsNull("week:"+week);
			}*/
			SyllabusEvaluateCondition condition = new SyllabusEvaluateCondition();
			condition.setWeek(week);
			condition.setLessionId(lessonId);
			List<SyllabusEvaluateVo> list = this.syllabusEvaluateService.findSyllabusEvaluateVoByCondition(condition,null,null);
			List<SyllabusEvaluateVo> newList = new ArrayList<SyllabusEvaluateVo>();
			for(SyllabusEvaluateVo vo : list){
				SyllabusEvaluateVo newVo = new SyllabusEvaluateVo();
				Student student = this.studentService.findStudentByUserId(vo.getPostUserId());
				//默认头像
				String imgUrl = ImgUtil.getImgUrl(null);
				newVo.setImgUrl(imgUrl);
				imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
				PropertyUtils.copyProperties(newVo,vo);
				newList.add(newVo);
			}
			return new ResponseVo<List<SyllabusEvaluateVo>>("0", newList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object createEvaluate(Integer lessonId, Integer week,
			String content, Integer userId, Double item1,Double item2,Double item3,Double item4,String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(content)) {
				return ResponseUtil.paramerIsNull("content:"+content);
			}
			if (StringUtils.isEmpty(week)) {
				return ResponseUtil.paramerIsNull("week:"+week);
			}
			if (StringUtils.isEmpty(lessonId)) {
				return ResponseUtil.paramerIsNull("lessonId:"+lessonId);
			}
			if (StringUtils.isEmpty(userId)) {
				return ResponseUtil.paramerIsNull("userId:"+userId);
			}*/
			SyllabusEvaluateCondition condition = new SyllabusEvaluateCondition();
			condition.setLessionId(lessonId);
			condition.setPostUserId(userId);
			condition.setWeek(week);
			List<SyllabusEvaluate> list = this.syllabusEvaluateService.findSyllabusEvaluateByCondition(condition);
			if(list != null && list.size()>0){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("请勿重复提交评价");
				info.setMsg("请勿重复提交评价");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}else{
				Double score = (item1+item2+item3+item4)/4;
				SyllabusEvaluate syllabusEvaluate = new SyllabusEvaluate();
				syllabusEvaluate.setLessionId(lessonId);
				syllabusEvaluate.setContent(content);
				syllabusEvaluate.setPostUserId(userId);
				syllabusEvaluate.setWeek(week);
				syllabusEvaluate.setItem1(item1);
				syllabusEvaluate.setItem2(item2);
				syllabusEvaluate.setItem3(item3);
				syllabusEvaluate.setItem4(item4);
				syllabusEvaluate.setScore(score);
				this.syllabusEvaluateService.add(syllabusEvaluate);
				return new ResponseVo<Boolean>("0", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	@Override
	public Object retroactive(String studentIds, Integer week, Integer syllabusType, Integer isAttendance,
			String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(studentIds)) {
				return ResponseUtil.paramerIsNull("studentIds:"+studentIds);
			}
			if (StringUtils.isEmpty(week)) {
				return ResponseUtil.paramerIsNull("week:"+week);
			}*/
			String[] ids = studentIds.split(",");
			List<SyllabusStudent> list = new ArrayList<>();
			for (String id : ids) {
				if(id!= null && !"".equals(id)){
					SyllabusStudent sbs = new SyllabusStudent(Integer.valueOf(id));
					list.add(sbs);
				}
			}
			Boolean isAttendanceFlag = false;
			if(1 == isAttendance){
				isAttendanceFlag = true;
			}
			if(list.size() > 0){
				this.attendancesSyllabusService.addAttendances(list, week, syllabusType, isAttendanceFlag);
			}
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
	public Object syllabusLessonAttendance(Integer syllabusLessonId, Integer schoolId, Integer week, String appKey, String signage) {
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
			
			List<AttendancesSyllabusVo> turnOutList = new ArrayList<AttendancesSyllabusVo>();//出勤
			List<AttendancesSyllabusVo> absenceList = new ArrayList<AttendancesSyllabusVo>();//缺勤
			List<AttendancesSyllabusVo> attendanceList = new ArrayList<AttendancesSyllabusVo>();//请假
			AttendancesSyllabusVo vo = null;
			AttendancesSyllabusCondition condition = new AttendancesSyllabusCondition();
			condition.setWeek(week);
			condition.setSyllabusType(BpCommonConstants.COURSE_TYPE_2);
			condition.setLessonId(syllabusLessonId);
			//出勤及请假名单
			List<AttendancesSyllabus> attendancesSyllabusList = 
					this.attendancesSyllabusService.findAttendancesSyllabusByCondition(condition);
			for(AttendancesSyllabus attendancesSyllabus: attendancesSyllabusList){
				//请假
				if(attendancesSyllabus.getIsAttendance()){
					vo = new AttendancesSyllabusVo();
					Student student = this.studentService.findStudentByUserId(attendancesSyllabus.getStudentUserId());
					vo.setStudentUserId(student.getUserId());
					vo.setStudentName(student.getName());
					String imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
					vo.setImgUrl(imgUrl);
					attendanceList.add(vo);
				}else{
					//出勤
					vo = new AttendancesSyllabusVo();
					Student student = this.studentService.findStudentByUserId(attendancesSyllabus.getStudentUserId());
					vo.setStudentUserId(student.getUserId());
					vo.setStudentName(student.getName());
					String imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
					vo.setImgUrl(imgUrl);
					turnOutList.add(vo);
				}
			}
			
			SyllabusStudentCondition syllabusStudentCondition = new SyllabusStudentCondition();
			syllabusStudentCondition.setLessonId(syllabusLessonId);
			List<SyllabusStudent> syllabusStudentList= this.syllabusStudentService.findSyllabusStudentByCondition(syllabusStudentCondition);
			
			for(SyllabusStudent syllabusStudent: syllabusStudentList){
				Boolean flag = true;
				for(AttendancesSyllabus attendancesSyllabus: attendancesSyllabusList){	
					if(attendancesSyllabus.getStudentUserId().equals(syllabusStudent.getStudentUserId())){
						flag = false;
						continue;
					}
				}
				if(flag){
					vo = new AttendancesSyllabusVo();
					Student student = this.studentService.findStudentByUserId(syllabusStudent.getStudentUserId());
					vo.setId(syllabusStudent.getId());
					vo.setStudentUserId(student.getUserId());
					vo.setStudentName(student.getName());
					String imgUrl = ImgUtil.getStudentIconUrl(student.getPersonId(), personService);
					vo.setImgUrl(imgUrl);
					absenceList.add(vo);
				}
			}
			BpAttendancesSyllabusIndexVo bpAttendancesSyllabusIndexVo = new BpAttendancesSyllabusIndexVo();
			bpAttendancesSyllabusIndexVo.setTurnOutList(turnOutList);
			bpAttendancesSyllabusIndexVo.setAttendanceList(attendanceList);
			bpAttendancesSyllabusIndexVo.setAbsenceList(absenceList);		
			return new ResponseVo<BpAttendancesSyllabusIndexVo>("0",bpAttendancesSyllabusIndexVo);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object getAttendancePageIndex(Integer schoolId, Integer teamId, Long attendanceDay, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			/*if (StringUtils.isEmpty(schoolId)) {
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			if (StringUtils.isEmpty(teamId)) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			Team team = this.teamService.findTeamById(teamId);
			if(team == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(team.getSchoolId());
			if(stc == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("接口异常");
				info.setMsg("接口异常");
				return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
			}
			
			//SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
			Date day = new Date(attendanceDay);
			BpAttendancePageIndexVo bpAttendancePageIndexVo = new BpAttendancePageIndexVo();
			//今日上学考勤
			//List<BpBwAttendancesVo> todayAttendances = new ArrayList<BpBwAttendancesVo>();
			List<BpBwAttendancesVo> todayAttendancesShangxue = this.bpBwAttendancesService
					.findTeamAttendanceDetailShangxue(schoolId, team.getGradeId(), teamId, shortSdf.format(day));			
			if(todayAttendancesShangxue != null && todayAttendancesShangxue.size() > 0){
				for(BpBwAttendancesVo b: todayAttendancesShangxue){
					Student student = this.studentService.findStudentByUserId(b.getUserId());
					if(student != null){
						b.setHeadUrl(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
					}	
				}
			}
			bpAttendancePageIndexVo.setTodayAttendancesShangxue(todayAttendancesShangxue);
			//今日放学考勤
			//List<BpBwAttendancesVo> todayAttendances = new ArrayList<BpBwAttendancesVo>();
			List<BpBwAttendancesVo> todayAttendancesFangxue = this.bpBwAttendancesService
					.findTeamAttendanceDetailShangxue(schoolId, team.getGradeId(), teamId, shortSdf.format(day));			
			if(todayAttendancesFangxue != null && todayAttendancesFangxue.size() > 0){
				for(BpBwAttendancesVo b:todayAttendancesFangxue){
					Student student = this.studentService.findStudentByUserId(b.getUserId());
					if(student != null){
						b.setHeadUrl(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
					}	
				}
			}
			bpAttendancePageIndexVo.setTodayAttendancesFangxue(todayAttendancesFangxue);		
			
			//昨日考勤
			Date yesterday = DateUtil.getDay(day, 1, false);
			BpAttendanceVo yesterdayAttendance = this.getAttendanceVo(shortSdf.parse(shortSdf.format(yesterday)), teamId);
			bpAttendancePageIndexVo.setYesterdayAttendance(yesterdayAttendance);
			//本周考勤统计
			List<StaTeamAttendanceVo> staTeamWeekAttendance = this.bpBwAttendancesService
					.findWeekStatisticsAttendanceByTeamId(teamId, day, stc.getSchoolTermCode());
			bpAttendancePageIndexVo.setStaTeamWeekAttendance(staTeamWeekAttendance);
			return new ResponseVo<BpAttendancePageIndexVo>("0",bpAttendancePageIndexVo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object getStudentPersonalAttendancePageIndex(Integer userId, Long attendanceDay, String appKey, String signage) {
		try {
			/*Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}*/
			/*if (StringUtils.isEmpty(userId)) {
				return ResponseUtil.paramerIsNull("userId:"+userId);
			}
			if (StringUtils.isEmpty(attendanceDay)) {
				return ResponseUtil.paramerIsNull("attendanceDay:"+attendanceDay);
			}*/
			BpStudentPersonalAttendancePageIndexVo vo = new BpStudentPersonalAttendancePageIndexVo();
			Student student = this.studentService.findStudentByUserId(userId);
			if(student != null){
				vo.setStudentName(student.getName());
				vo.setStudentUserId(student.getUserId());
				vo.setStudentIcon(ImgUtil.getStudentIconUrl(student.getPersonId(), personService));
			}
			
			Date date = new Date(attendanceDay);	
			Date weekStartDate = DateUtil.getWeekDayStartTime(date);
			Date weekEndDate = DateUtil.getWeekDayEndTime(date);
			String weekStartDateStr = shortSdf.format(weekStartDate);
			String weekEndDateStr = shortSdf.format(weekEndDate);
			//周考勤统计数目
			StaStudentAttendanceVo weekStaStudentAttendance = this.bpBwAttendancesService
					.findStaStudentAttendanceByUserId(userId, weekStartDateStr, weekEndDateStr);
			vo.setWeekStaStudentAttendance(weekStaStudentAttendance);
			//周7天考勤详情
			List<StaStudentAttendanceVo> weekStaStudentAttendanceDetailLilst = new ArrayList<StaStudentAttendanceVo>();	
			for(int i = 0; i < 7; i++){
				Date day = DateUtil.getDay(weekStartDate, i, true);
				String dayStr = shortSdf.format(day);
				StaStudentAttendanceVo dayStaStudentAttendance = this.bpBwAttendancesService
						.findStaStudentAttendanceByUserId(userId, dayStr, dayStr);
				weekStaStudentAttendanceDetailLilst.add(dayStaStudentAttendance);
			}
			vo.setWeekStaStudentAttendanceDetailLilst(weekStaStudentAttendanceDetailLilst);
				
			//月考勤统计数目
			Date monthStartDate = DateUtil.getFirstDayOfMonth(date);
			Date monthEndDate = DateUtil.getLastDayOfMonth(date);
			String monthStartDateStr = shortSdf.format(monthStartDate);
			String monthEndDateStr = shortSdf.format(monthEndDate);
			StaStudentAttendanceVo monthStaStudentAttendance = this.bpBwAttendancesService
					.findStaStudentAttendanceByUserId(userId, monthStartDateStr, monthEndDateStr);
			vo.setMonthStaStudentAttendance(monthStaStudentAttendance);
			//月考勤详情
			List<StaStudentAttendanceVo> monthStaStudentAttendanceDetailLilst = new ArrayList<StaStudentAttendanceVo>();	
			Integer monthDay = DateUtil.getDaysOfMonth(date);
			for(int i = 0; i < monthDay; i++){
				Date day = DateUtil.getDay(monthStartDate, i, true);
				String dayStr = shortSdf.format(day);
				StaStudentAttendanceVo dayStaStudentAttendance = this.bpBwAttendancesService
						.findStaStudentAttendanceByUserId(userId, dayStr, dayStr);
				monthStaStudentAttendanceDetailLilst.add(dayStaStudentAttendance);
			}
			vo.setMonthStaStudentAttendanceDetailLilst(monthStaStudentAttendanceDetailLilst);
			return new ResponseVo<BpStudentPersonalAttendancePageIndexVo>("0", vo);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}	
		
	}




}
