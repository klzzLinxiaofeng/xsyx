package platform.education.rest.bp.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwInfo;
import platform.education.clazz.service.BpBwInfoService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.vo.BpBwInfoVo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpBwInfoRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;

import javax.annotation.Resource;
import java.util.*;

public class BpBwInfoRestServiceImpl implements BpBwInfoRestService {
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("bpBwInfoService")
	private BpBwInfoService bpBwInfoService;

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bp_welcome_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object getWelcomeText(Integer teamId,Long currentTime,String appKey,String signage) {		
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}	
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
			String welcomeText = "";
			if(bpBwInfo != null && bpBwInfo.getWelcomeText() != ""){
				Date welcomeTextTime = bpBwInfo.getWelcomeTextTime();
				Date nowTime = new Date(currentTime);
				if(welcomeTextTime.before(nowTime)){
					welcomeText = bpBwInfo.getWelcomeText();
				}
			}
			return new ResponseVo<String>("0", welcomeText);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	
	@Override
	public Object getWelcomeText(Integer teamId,String appKey,String signage) {		
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}	
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
			String welcomeText = "";
			if(bpBwInfo != null && bpBwInfo.getWelcomeText() != "" && bpBwInfo.getWelcomeText() != null){
				Date welcomeTextTime = bpBwInfo.getWelcomeTextTime();
				Date nowTime = new Date();
				if(welcomeTextTime != null && welcomeTextTime.before(nowTime)){
					welcomeText = bpBwInfo.getWelcomeText();
				}
			}
			return new ResponseVo<String>("0", welcomeText);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object createWelcomeText(Integer schoolId, Integer gradeId, Integer teamId, String welcomeText, Long welcomeTextTime, Long welcomeTextEndTime, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}						
			/*if(StringUtils.isEmpty(welcomeTextEndTime)){
				return ResponseUtil.paramerIsNull("welcomeTextEndTime:"+welcomeTextEndTime);
			}
			
			Date welcomeTextStartTime = null;
			if(StringUtils.isEmpty(welcomeTextTime)){
				welcomeTextStartTime = new Date();
			}else{
				 welcomeTextStartTime = new Date(welcomeTextTime);
			}
			*/
			//要推送的班级	
			List<Integer> teamIds = new ArrayList<Integer>();		
			/*if(!StringUtils.isEmpty(teamId)){
				//发某班
				this.addWelcomeText(teamId, welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
				teamIds.add(teamId);
			}else if(!StringUtils.isEmpty(gradeId)){
				//发某年级
				List<Team> teamList = teamService.findTeamOfGrade(gradeId);
				if(teamList != null && teamList.size() > 0){
					for(Team team: teamList){
						this.addWelcomeText(teamId, welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
						teamIds.add(team.getId());
					}
				}
			}else if(StringUtils.isEmpty(gradeId) && StringUtils.isEmpty(teamId)){
				//发全校
				List<Team> teamList = teamService.findCurrentTeamOfSchool(schoolId);
				if(teamList != null && teamList.size() > 0){
					for(Team team: teamList){
						this.addWelcomeText(teamId, welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
						teamIds.add(team.getId());
					}
				}*/
			//}
						
			//推送		
			//推送内容
			Map<String, Object> map = new HashMap<String, Object>();	
			map.put("endTime", welcomeTextEndTime);
			map.put("modeType", BpCommonConstants.PUSH_WELCOME_TEXT);
			map.put("priority", BpCommonConstants.PRIORITY_1);
			/*if(StringUtils.isEmpty(welcomeTextTime)){
				map.put("startTime", (new Date()).getTime());
				map.put("immediately", 1);
			}else{
				map.put("startTime", welcomeTextTime);
				map.put("immediately", 0);
			}*/
//			IMPushUtil.push(teamIds, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT, map.toString(), 
//				bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT, map.toString(), bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT,map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	@Override
	public Object deleteWelcomeText(Integer infoId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
			 	return checkAppKeyResult;
			}			
			BpBwInfo bwInfo = this.bpBwInfoService.findBpBwInfoById(infoId);
			if(bwInfo == null){
				return ResponseUtil.paramerIsNull("infoId:"+infoId);
			}
			Date date = new Date();
			bwInfo.setWelcomeText(null);
			bwInfo.setWelcomeTextTime(null);
			bwInfo.setModifyDate(date);
			//推送开始 ====
			//要推送的班级	
			List<Integer> teamIds = new ArrayList<Integer>();	
			teamIds.add(bwInfo.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$delete, 0, BpCommonConstants.PUSH_WELCOME_TEXT, null, 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$delete, 0, BpCommonConstants.PUSH_WELCOME_TEXT, null, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$delete, 0, BpCommonConstants.PUSH_WELCOME_TEXT,null, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			this.bpBwInfoService.modify(bwInfo);		
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
		 	e.printStackTrace();
		 	ResponseInfo info = new ResponseInfo();
		 	info.setDetail("接口异常");
		 	info.setMsg("接口异常");
		 	return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		 }
	}

	
	private BpBwInfo addWelcomeText(Integer teamId, String welcomeText, Date welcomeTextTime, Date welcomeTextEndTime){
//		if(StringUtils.isEmpty(teamId)){
//			return null;
//		}
		BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
		if(bpBwInfo != null){
			bpBwInfo.setWelcomeText(welcomeText);
			bpBwInfo.setWelcomeTextTime(welcomeTextTime);
			bpBwInfo.setWelcomeTextEndTime(welcomeTextEndTime);
			bpBwInfo = this.bpBwInfoService.modify(bpBwInfo);
		}else{
			bpBwInfo = new BpBwInfo();
			bpBwInfo.setTeamId(teamId);
			bpBwInfo.setWelcomeText(welcomeText);
			bpBwInfo.setWelcomeTextTime(welcomeTextTime);
			bpBwInfo.setWelcomeTextEndTime(welcomeTextEndTime);
			bpBwInfo = this.bpBwInfoService.add(bpBwInfo);
		}	
		return bpBwInfo;
	}

	@Override
	public Object getWelcomeTextInfo(Integer teamId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
			 	return checkAppKeyResult;
			}
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);	
			}*/
			BpBwInfo bpBwInfo = bpBwInfoService.findBpBwInfoByTeamId(teamId);
			BpBwInfoVo vo = new BpBwInfoVo();
			if(bpBwInfo!=null){
				PropertyUtils.copyProperties(vo,bpBwInfo);
				Team team = this.teamService.findTeamById(teamId);
				vo.setTeamName(team.getName());
			}
			return new ResponseVo<BpBwInfoVo>("0", vo);
		}catch (Exception e) {
		 	e.printStackTrace();
		 	ResponseInfo info = new ResponseInfo();
		 	info.setDetail("接口异常");
		 	info.setMsg("接口异常");
		 	return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		 }
	}


	@Override
	public Object createWelcomeTexts(Integer schoolId, Integer gradeId,
			String teamIds, String welcomeText, Long welcomeTextTime,
			Long welcomeTextEndTime, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}						
			/*if(StringUtils.isEmpty(welcomeTextEndTime)){
				return ResponseUtil.paramerIsNull("welcomeTextEndTime:"+welcomeTextEndTime);
			}
			
			Date welcomeTextStartTime = null;
			if(StringUtils.isEmpty(welcomeTextTime)){
				welcomeTextStartTime = new Date();
			}else{
				 welcomeTextStartTime = new Date(welcomeTextTime);
			}
			
			//要推送的班级	
			List<Integer> team = new ArrayList<Integer>();
			if(!StringUtils.isEmpty(teamIds)){
				String[] teamIdList = teamIds.split(",");
				//发某班
				for(String teamId:teamIdList){
					team.add(Integer.parseInt(teamId));
					this.addWelcomeText(Integer.parseInt(teamId), welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
				}
			}else if(!StringUtils.isEmpty(gradeId)){
				//发某年级
				List<Team> teamList = teamService.findTeamOfGrade(gradeId);
				if(teamList != null && teamList.size() > 0){
					for(Team teamVo: teamList){
						this.addWelcomeText(teamVo.getId(), welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
						team.add(teamVo.getId());
					}
					
				}
			}else if(StringUtils.isEmpty(gradeId) && StringUtils.isEmpty(teamIds)){
				//发全校
				List<Team> teamList = teamService.findCurrentTeamOfSchool(schoolId);
				if(teamList != null && teamList.size() > 0){
					for(Team teamVo: teamList){
						this.addWelcomeText(teamVo.getId(), welcomeText, welcomeTextStartTime, new Date(welcomeTextEndTime));
						team.add(teamVo.getId());
					}
				}
			}*/
						
			//推送		
			//推送内容
			Map<String, Object> map = new HashMap<String, Object>();	
			map.put("endTime", welcomeTextEndTime);
			map.put("modeType", BpCommonConstants.PUSH_WELCOME_TEXT);
			map.put("priority", BpCommonConstants.PRIORITY_1);
			/*if(StringUtils.isEmpty(welcomeTextTime)){
				map.put("startTime", (new Date()).getTime());
				map.put("immediately", 1);
			}else{
				map.put("startTime", welcomeTextTime);
				map.put("immediately", 0);
			}*/
//			IMPushUtil.push(team, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT, map.toString(), 
//				bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(team, 1, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT, map.toString(), bpBwSignageService, taskExecutor);
			//IMPushUtil.PushByXJXP(team, 1, DataAction.D$add, 0, BpCommonConstants.PUSH_WELCOME_TEXT,map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	
}
