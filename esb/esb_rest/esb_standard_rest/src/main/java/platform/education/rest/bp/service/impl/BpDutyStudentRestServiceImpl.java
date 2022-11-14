package platform.education.rest.bp.service.impl;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.TeamDutyUser;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.TeamDutyUserService;
import platform.education.clazz.vo.TeamDutyUserCondition;
import platform.education.clazz.vo.TeamDutyUserVo;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpDutyStudentRestService;
import platform.education.rest.bp.service.util.DateUtil;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.BbxTeamDutyUser;
import platform.education.rest.bp.service.vo.CommonDutyUser;
import platform.education.rest.bp.service.vo.DutyUser;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseNormal;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BpDutyStudentRestServiceImpl implements BpDutyStudentRestService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("roomTeamService")
	protected RoomTeamService roomTeamService;
	
	@Autowired
	@Qualifier("teacherService")
	protected TeacherService teacherService;
	
	@Autowired
	@Qualifier("parentStudentService")
	protected ParentStudentService parentStudentService;
	
	@Autowired
	@Qualifier("teamDutyUserService")
	private TeamDutyUserService teamDutyUserService;
	
	@Autowired
	@Qualifier("profileService")
	protected ProfileService profileService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("personService")
	protected PersonService personService;
	
	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	protected TeamTeacherService teamTeacherService;
	
	@Resource(name = "bbx_teamDuty_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findDutyStudent(Integer teamId, Long startDate, Long endDate,
			String appKey,String signage) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate;
		DutyUser vo = null;
		Date date = null;
		try {
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(startDate == null || "".equals(startDate)){
				return ResponseUtil.paramerIsNull("startDate:"+startDate);
			}
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			sdate = new Date(startDate);
			CommonDutyUser[] dutyUser = new CommonDutyUser[7];
			for(int i=0;i<7;i++){
				//date = new Date();
				date = DateUtil.getDay(sdate, i, true);
				date = DateUtil.getDayStartTime(date);
				List<TeamDutyUser> tdulist = this.teamDutyUserService.findTeamDutyStudent(teamId,date);
				List<DutyUser> userList = new ArrayList<DutyUser>();
				String imgSrc = "";
				//默认头像
				String imgUrl = ImgUtil.getImgUrl(null);
				String modifyDate = null;
				if(tdulist.size()>0){
					for(TeamDutyUser tdu : tdulist){
						vo = new DutyUser();
						vo.setUserId(tdu.getUserId());
						Integer personId = this.studentService.findStudentByUserId(tdu.getUserId()).getPersonId();
						imgSrc = ImgUtil.getStudentIconUrl(personId, personService);
						vo.setStudentName(this.studentService.findStudentByUserId(tdu.getUserId()).getName());
						vo.setUserIcon(imgSrc);
						if("".equals(imgSrc)){
							vo.setUserIcon(imgUrl);
						}
						userList.add(vo);
						modifyDate = DateUtil.dateToString(tdu.getModifyDate());
					}
				}
				CommonDutyUser cdu = new CommonDutyUser(date,modifyDate,userList);
				dutyUser[i] = cdu;
			}
			List<CommonDutyUser> cduList = Arrays.asList(dutyUser);
			return new ResponseVo<List<CommonDutyUser>>("0",cduList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询异常...");
			info.setMsg("参数出错");
			info.setParam("数据错误");
			return new ResponseError("020001",info);
		}
	}

	@Override
	public Object createDutyStudent(Integer teamId, Long dutyDate,
			String students, String appKey, String signage) {
		Date dutyDateF = new Date();
		try {
			Team team = this.teamService.findTeamById(teamId);
			dutyDateF = new Date(dutyDate);
			List<TeamDutyUser> itemList = this.teamDutyUserService.findTeamDutyStudent(teamId, dutyDateF);
			for(TeamDutyUser tdu : itemList){
				this.teamDutyUserService.remove(tdu);
			}
			List<TeamDutyUser> newList = new ArrayList<TeamDutyUser>();
			if(team!=null){
				if(students != null && !"".equals(students)){
					JSONArray jsonArray = JSONArray.fromObject(students);
					TeamDutyUser item = null;
					Date date = new Date();
					for(int i=0; i<jsonArray.size() ;i ++){
						Integer userId = (Integer)jsonArray.get(i);
					    item = new TeamDutyUser();
					    item.setCreateDate(date);
					    item.setModifyDate(date);
					    item.setSchoolId(team.getSchoolId());
					    item.setDutyDate(dutyDateF);
					    item.setLeader(false);
					    item.setTeamId(teamId);
					    item.setUserId(userId);
					    newList.add(item);
					    this.teamDutyUserService.add(item);
					}
				}
			}
			return new ResponseNormal("0");
			} catch (Exception e) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数解析异常...");
				info.setMsg("参数出错");
				info.setParam("students");
				return new ResponseError("060113",info);
			}
	}

	@Override
	public Object getDutyStudent(Integer teamId, String appKey, String signage) {
		try {
			BbxTeamDutyUser[] bbxTeamDutyUsers = new BbxTeamDutyUser[7];
			for (int i=1; i<= 6; i++) {
				BbxTeamDutyUser bbxTeamDutyUser = new BbxTeamDutyUser();
				bbxTeamDutyUser.setDayOfWeek(i + "");
				
				TeamDutyUserCondition teamDutyUserCondition = new TeamDutyUserCondition();
				teamDutyUserCondition.setTeamId(teamId);
				teamDutyUserCondition.setWeek(i);
				List<TeamDutyUser> tdulist = this.teamDutyUserService.findTeamDutyUserByCondition(teamDutyUserCondition);
				List<TeamDutyUserVo> voList = new ArrayList<TeamDutyUserVo>();
				TeamDutyUserVo vo = null;
				for (TeamDutyUser tdu : tdulist) {
					vo = new TeamDutyUserVo();
					BeanUtils.copyProperties(tdu, vo);
					Student student = this.studentService.findStudentByUserId(tdu.getUserId());
					Person p = this.personService.findPersonById(student.getPersonId());
					vo.setStudentName(student.getName());
					if (p != null) {
						vo.setImgUrl(fileService.relativePath2HttpUrlByUUID(p.getPhotoUuid()));
					}
					voList.add(vo);
				}
				bbxTeamDutyUser.setTduList(voList);
				bbxTeamDutyUsers[i-1] = bbxTeamDutyUser;
			}
			//星期日
			BbxTeamDutyUser bbxTeamDutyUser = new BbxTeamDutyUser();
			bbxTeamDutyUser.setDayOfWeek("0");
			
			TeamDutyUserCondition teamDutyUserCondition = new TeamDutyUserCondition();
			teamDutyUserCondition.setTeamId(teamId);
			teamDutyUserCondition.setWeek(0);
			List<TeamDutyUser> tdulist = this.teamDutyUserService.findTeamDutyUserByCondition(teamDutyUserCondition);
			List<TeamDutyUserVo> voList = new ArrayList<TeamDutyUserVo>();
			TeamDutyUserVo vo = null;
			for (TeamDutyUser tdu : tdulist) {
				vo = new TeamDutyUserVo();
				BeanUtils.copyProperties(tdu, vo);
				Student student = this.studentService.findStudentByUserId(tdu.getUserId());
				Person p = this.personService.findPersonById(student.getPersonId());
				vo.setStudentName(student.getName());
				if (p != null) {
					vo.setImgUrl(fileService.relativePath2HttpUrlByUUID(p.getPhotoUuid()));
				}
				voList.add(vo);
			}
			bbxTeamDutyUser.setTduList(voList);
			bbxTeamDutyUsers[6] = bbxTeamDutyUser;
			return new ResponseVo<BbxTeamDutyUser[]>("0",bbxTeamDutyUsers);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析异常...");
			info.setMsg("参数出错");
			info.setParam("students");
			return new ResponseError("060113",info);
		}
	}

	@Override
	public Object createWeekDutyStudent(Integer teamId, Integer schoolId,String students,
			Integer week, String appKey, String signage) {
		try {
			TeamDutyUserCondition teamDutyUserCondition = new TeamDutyUserCondition();
			teamDutyUserCondition.setTeamId(teamId);
			teamDutyUserCondition.setWeek(week);
			List<TeamDutyUser> itemList = this.teamDutyUserService.findTeamDutyUserByCondition(teamDutyUserCondition);
			for (TeamDutyUser tdu : itemList) {
				this.teamDutyUserService.remove(tdu);
			}
			List<TeamDutyUser> newList = new ArrayList<TeamDutyUser>();
			if (students != null && !"".equals(students)) {
				JSONArray jsonArray = JSONArray.fromObject(students);
				TeamDutyUser item = null;
				Date date = new Date();
				for (int i = 0; i < jsonArray.size(); i++) {
					Integer userId = (Integer)jsonArray.get(i);
					item = new TeamDutyUser();
					item.setCreateDate(date);
					item.setModifyDate(date);
					item.setSchoolId(schoolId);
					item.setWeek(week);
					item.setLeader(false);
					item.setTeamId(teamId);
					item.setUserId(userId);
					newList.add(item);
				}
				this.teamDutyUserService.save(newList); 
			}

			// 推送教师端
			if (teamId != null) {
				//推送开始=====
				
				//值日生给班级账号、班主任做推送
//				String roleGroups = "";
//				roleGroups+=RoleGroup.T$BBXACCOUNT+","+RoleGroup.T$TEACHER+"," + RoleGroup.T$PARENT +"," ;
				//要推送的班级
				List<Integer>teamIds = new ArrayList<Integer>();
				teamIds.add(teamId);
				//拼装要推送的学生的家长的userId
				List<Integer> parentIds = new ArrayList<Integer>();
				for(TeamDutyUser student:newList){
					ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
					parentStudentCondition.setStudentUserId(student.getUserId());
					List<ParentStudent> parentStudentList= parentStudentService.findParentIdByCondition(parentStudentCondition);
					for(ParentStudent parentStudent:parentStudentList){
						parentIds.add(parentStudent.getParentUserId());
					}
				}
				//推送
//				IMPushUtil.push(teamIds, null, null, "125", "值日情况已公布！", bpBwSignageService, schoolTermCurrentService, 
//						roomTeamService, teamService, taskExecutor);
				
//				IMPushUtil.signagePush(teamIds, 1, null, null,"125", "值日情况已公布！", bpBwSignageService, taskExecutor);
				IMPushUtil.PushByXJXP(teamIds, 1,null,null,"125","值日情况已公布！", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
				//推送给指定家长
				//IMPushUtil.pushToUser(roleGroups, parentIds, null, null, "125", dutyDate+"的值日情况已公布！", taskExecutor);
				//推送结束 ====
				
			}

			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析异常...");
			info.setMsg("参数出错");
			info.setParam("students");
			return new ResponseError("060113",info);
		}
	}

}
