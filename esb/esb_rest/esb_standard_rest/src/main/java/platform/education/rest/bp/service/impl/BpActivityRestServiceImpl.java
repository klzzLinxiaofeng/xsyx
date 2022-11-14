package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.UncategorizedSQLException;
import platform.education.clazz.model.TeamActivity;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.TeamActivityService;
import platform.education.clazz.vo.TeamActivityCondition;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpActivityRestService;
import platform.education.rest.bp.service.contants.BpActivityConstants;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.*;
import platform.education.rest.bp.service.vo.CommonActivity;
import platform.education.rest.bp.service.vo.ResponseAdd;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BpActivityRestServiceImpl implements BpActivityRestService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 班级
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	// 当前学期
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("teamActivityService")
	private TeamActivityService teamActivityService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bbx_teamActivity_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findActivity(Integer teamId, String termCode, Integer searchId,Integer searchType,
			String pageSize,String appKey,String signage) {
		Order order = new Order();
		Page page = new Page();
		Team team = null;
		SchoolTermCurrent stc = null;
		TeamActivityCondition condition = new TeamActivityCondition();
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(searchType == null || "".equals(searchType)){
				return ResponseUtil.paramerIsNull("searchType:"+searchType);
			}
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			if(searchType.equals(0)){
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}else if(searchType.equals(1)){
				condition.setSearchId(searchId);
				condition.setSearchType(searchType);
			}else if(searchType.equals(2)){
				condition.setSearchId(searchId);
				condition.setSearchType(searchType);
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}
			team = this.teamService.findTeamById(teamId);
			condition.setTeamId(teamId);
			if (team != null) {
				stc = this.schoolTermCurrentService
						.findSchoolTermCurrentBySchoolId(team.getSchoolId());
				if (stc != null) {
					condition.setTermCode(stc.getSchoolTermCode());
				}
			}
			List<TeamActivity> activityList = this.teamActivityService
					.findTeamActivityByCondition(condition, page, order);
			List<CommonActivity> activityListVo = new ArrayList<CommonActivity>();
			CommonActivity activityVo = null;
			Date date = new Date();
			for (TeamActivity ta : activityList) {
				activityVo = new CommonActivity();
				BeanUtils.copyProperties(ta, activityVo);
				if (team != null) {
					activityVo.setTeamName(team.getName());
				}
				if(ta.getFileId()!=null){
					activityVo.setImgUrl(fileService.relativePath2HttpUrlByUUID(ta.getFileId()));
				}
				activityVo.setCreateDate(ta.getCreateDate());
				activityVo.setFinishTime(ta.getFinishTime());
				activityVo.setModifyDate(DateUtil.dateToString(ta.getModifyDate()));
				activityVo.setStartTime(ta.getStartTime());
				if(date.before(ta.getStartTime())){
					activityVo.setStatus(BpActivityConstants.ACTIVITY_READY);
				}else if(date.after(ta.getStartTime())&&date.before(ta.getFinishTime())){
					activityVo.setStatus(BpActivityConstants.ACTIVITY_START);
				}else{
					activityVo.setStatus(BpActivityConstants.ACTIVITY_END);
				}
				activityListVo.add(activityVo);
			}
			return new ResponseOrder<List<CommonActivity>>("0", activityListVo,
					page.getTotalRows());
		} catch (NumberFormatException nfe) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("teamId,pageNumber,pageSize...");
			return new ResponseError("060112", info);
		} catch (UncategorizedSQLException usql) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不存在...");
			info.setMsg("数据库异常");
			info.setParam("sortItem");
			return new ResponseError("020001", info);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析失败...");
			info.setMsg("参数出错");
			info.setParam("teamId");
			return new ResponseError("060110", info);
		}
	}

	@Override
	public Object createTeamActivity(Integer teamId, Integer posterId,
			String name, String place, String image,String uuid, Long startTime,
			Long finishTime, String comment, String appKey, String signage) {
		Team t = null;
		SchoolTermCurrent stc = null;
		TeamActivity teamActivity = new TeamActivity();
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(startTime == null || "".equals(startTime)){
				return ResponseUtil.paramerIsNull("startTime:"+startTime);
			}
			if(finishTime == null || "".equals(finishTime)){
				return ResponseUtil.paramerIsNull("finishTime:"+finishTime);
			}
			Date start = new Date(startTime);
			Date end = new Date(finishTime);
			t = this.teamService.findTeamById(teamId);
			if (t != null) {
				teamActivity.setSchoolId(t.getSchoolId());
				teamActivity.setTeamId(t.getId());
				stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(t.getSchoolId());
				teamActivity.setActivityImage(image);
				teamActivity.setFileId(uuid);
				teamActivity.setName(name);
				teamActivity.setPlace(place);
				teamActivity.setComment(comment);
				teamActivity.setSchoolYear(stc.getSchoolYear());
				teamActivity.setTermCode(stc.getSchoolTermCode());
				teamActivity.setStartTime(start);
				teamActivity.setFinishTime(end);
				teamActivity.setCreateDate(new Date());
				teamActivity.setModifyDate(new Date());
			} else {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("班级不存在...");
				info.setMsg("不存在该班级...");
				info.setParam("teamId");
				return new ResponseError("060113", info);
			}
			teamActivity = this.teamActivityService.add(teamActivity);
			ResponseAdd add = new ResponseAdd(teamActivity.getId(),DateUtil.dateToString(teamActivity.getCreateDate()));
			//推送开始=====
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(teamActivity.getTeamId());
			//推送
//			IMPushUtil.push(teamIds, DataAction.D$add, teamActivity.getId(), BpCommonConstants.PUSH_TEAM_ACTIVITY, 
//					("".equals(teamActivity.getName())||teamActivity.getName()==null)?StringContentUtil.getString(teamActivity.getComment()):teamActivity.getName(), 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, teamActivity.getId(), BpCommonConstants.PUSH_TEAM_ACTIVITY, 
//					("".equals(teamActivity.getName())||teamActivity.getName()==null)?StringContentUtil.getString(teamActivity.getComment()):teamActivity.getName()
//					, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,teamActivity.getId(), BpCommonConstants.PUSH_TEAM_ACTIVITY,
					("".equals(teamActivity.getName())||teamActivity.getName()==null)?StringContentUtil.getString(teamActivity.getComment()):teamActivity.getName()
							, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			return new ResponseVo<ResponseAdd>("0", add);
		} catch (Exception e) {
			log.info("创建班级活动异常...");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数解析异常...");
			info.setMsg("参数出错");
			info.setParam("comment,place...");
			return new ResponseError("060113", info);
		}
	}

	@Override
	public Object deleteTeamActivity(Integer activityId, String appKey,
			String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(activityId == null || "".equals(activityId)){
				return ResponseUtil.paramerIsNull("activityId:"+activityId);
			}
			TeamActivity teamActivity = this.teamActivityService.findTeamActivityById(activityId);
			this.teamActivityService.remove(teamActivity);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("活动id转换异常...");
			info.setMsg("参数出错");
			info.setParam("id");
			return new ResponseError("060612", info);
		}
	}

}
