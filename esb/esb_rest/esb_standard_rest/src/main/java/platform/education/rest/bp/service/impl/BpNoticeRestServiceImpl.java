package platform.education.rest.bp.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BBXNotice;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.TeamNoticeService;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpNoticeRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.BBXNoticeClient;
import platform.education.rest.bp.service.vo.ResponseAdd;
import platform.education.rest.bp.service.vo.ResponseDataVo;
import platform.education.rest.bp.service.vo.ResponseInfoVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

public class BpNoticeRestServiceImpl implements BpNoticeRestService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("profileService")
	protected ProfileService profileService;
	
	// 班级
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("teamNoticeService")
	TeamNoticeService teamNoticeService;
	
		
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bbx_teamMessage_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Resource(name="teamUserService")
	TeamUserService teamUserService;
	
	@Override
	public Object findTeamNotice(Integer teamId,Integer searchId,Integer searchType,String pageSize,
			String appKey,String signage) {
		Team t = null;
		Order order = new Order();
		Page page = new Page();
		BBXNoticeClient data[] = null;
		BBXNoticeClient bc = null;
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			//设置传过来的分页和排序
			if(searchType == null || "".equals(searchType)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("searchType参数必填");
				info.setMsg("searchType参数不能为空");
				info.setParam("searchType");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			if(searchType.equals(0)){
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}else if(searchType.equals(2)){
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}
			//根据条件查询对应的班级通知列表
			List<BBXNotice>list = this.teamNoticeService.findBBXNoticeByCondition(teamId, searchId, searchType, page, order);
			t = teamService.findTeamById(teamId);
			if(list.size()>0){
				//封装查询到的班级通知列表并返回给客户端，以json格式返回
				data = new BBXNoticeClient[list.size()];
				for(int i = 0; i < list.size(); i++){
					bc = new BBXNoticeClient();
					bc.setId(list.get(i).getId());
					bc.setUuid(list.get(i).getUuid());
					bc.setAppKey(list.get(i).getAppKey());
					bc.setTitle(list.get(i).getTitle());
					bc.setPosterId(list.get(i).getPosterId());
					bc.setPosterName(list.get(i).getPosterName());
					bc.setPostTime(list.get(i).getPostTime());
					bc.setReceiverType(list.get(i).getReceiverType());
					bc.setPosterIcon(ImgUtil.getImgSrc(list.get(i).getPosterId(),profileService));
					bc.setReceiverName(t.getName());
					bc.setContent(list.get(i).getContent());
					bc.setUserCount(list.get(i).getUserCount());
					bc.setReadCount(list.get(i).getReadCount());
					bc.setCreateDate(list.get(i).getCreateDate());
					if(list.get(i).getStartTime() != null){
						bc.setStartTime(list.get(i).getStartTime().getTime());
					}
					if(list.get(i).getFinishTime() != null){
						bc.setFinishTime(list.get(i).getFinishTime().getTime());
					}			
					bc.setFiles(list.get(i).getBbxNoticeFileVo());
					data[i]= bc;
					
				}
			}
			
			
		} catch (Exception e) {
			log.info("查询通知公告异常...");
			return new ResponseInfoVo<BBXNoticeClient>("0",
					null);
		}
		if(data==null){
			List<BBXNoticeClient> list = new ArrayList<BBXNoticeClient>();
			return new ResponseVo<List<BBXNoticeClient>>("0", list);
		}else{
			return new ResponseDataVo<BBXNoticeClient>("0",data);
		}
	}

	@Override
	public Object createTeamNotice(Integer teamId, Integer posterId,
			String posterName, String uuids, String title, Long postTime,
			String content,  Long startTime, Long finishTime, String appKey, String signage) {
		BBXNotice bbxNotice = new BBXNotice();
		ResponseAdd add = new ResponseAdd();
		List<String>  noticeFiles = new ArrayList<String>();
		List<Integer>  teamIds = new ArrayList<Integer>();
		Long userCount = 0L;
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			teamIds.add(teamId);
			String receiverName = "";
			//拼装发送的对象
			try {
				Team t = this.teamService.findTeamById(teamId);
				receiverName+=t.getName()+",";
				List<TeamUser>teamUserAll = this.teamUserService.findTeamUserOfAll(teamId);
				userCount+=teamUserAll.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String> imageUuidlList = null;
			if ( uuids != null && uuids.length() > 0  ) {
				imageUuidlList = JSONObject.parseArray(uuids, String.class);
			} 
			//拼装上传的文件的uuid
			if(imageUuidlList!=null){
				if(imageUuidlList.size()>0){
					for(int j=0;j<imageUuidlList.size();j++){
						try {
							noticeFiles.add(imageUuidlList.get(j));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			//添加通知记录
			if(startTime != null){
				bbxNotice.setStartTime(new Date(startTime));
			}
			if(finishTime != null){
				bbxNotice.setFinishTime(new Date(finishTime));
			}
		 	bbxNotice.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
	        bbxNotice.setAppKey(appKey);
	        bbxNotice.setTitle(("".equals(title) || title==null)?"班级通知":title);
	        bbxNotice.setPosterId(posterId);
	        bbxNotice.setPostTime(("".equals(postTime) || postTime==null)?new Date():new Date(postTime));
	        bbxNotice.setPosterName(posterName);
	        bbxNotice.setReceiverName("".equals(receiverName)?"":receiverName.substring(0, receiverName.length()-1));
	        bbxNotice.setContent(content);
	        bbxNotice.setUserCount(Integer.parseInt(String.valueOf(userCount)));
	        bbxNotice = this.teamNoticeService.senTeamNotice(bbxNotice, noticeFiles, teamIds);
	        add.setAddedId(bbxNotice.getId());
			add.setCreateTime(sdf.format(bbxNotice.getCreateDate()));
			//推送开始=====
			//推送
			Map<String, Object> map = new HashMap<String, Object>();	
			if(bbxNotice.getStartTime() != null){
				map.put("startTime", bbxNotice.getStartTime().getTime());
			}else{
				map.put("startTime", null);
			}
			if(bbxNotice.getFinishTime() != null){
				map.put("endTime", bbxNotice.getFinishTime().getTime());
			}else{
				map.put("endTime", null);
			}	
			map.put("modeType", BpCommonConstants.PUSH_TEAM_NOTICE);
			map.put("priority", BpCommonConstants.PRIORITY_1);
			map.put("immediately", 0);
//			IMPushUtil.push(teamIds, DataAction.D$add, bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, 
//					map.toString(), bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, bbxNotice.getId(),BpCommonConstants.PUSH_TEAM_NOTICE,map.toString(), bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			/*String IMcontent = StringContentUtil.getString(bbxNotice.getContent());
			IMPushUtil.push(teamIds, DataAction.D$add, bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, 
					("".equals(IMcontent) || IMcontent==null)? title:IMcontent, bpBwSignageService, schoolTermCurrentService, 
					roomTeamService, teamService, taskExecutor);*/
			//推送结束 ====
		}catch(IllegalArgumentException e){
			return new ResponseVo<ResponseAdd>("参数格式错误", null);
		} catch (Exception e) {
			log.info("参数为空...");
			return new ResponseVo<ResponseAdd>("0", null);
		}
		return new ResponseVo<ResponseAdd>("0", add);
	}

	@Override
	public Object deleteTeamNotice(Integer teamNoticeId, String appKey,
			String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamNoticeId == null || "".equals(teamNoticeId)){
				return ResponseUtil.paramerIsNull("teamNoticeId:"+teamNoticeId);
			}		
			//推送开始=====
			//需要推送的班级id
			List<Integer> teamIds = this.teamNoticeService.findReceiverTeamByNoticeId(teamNoticeId);
//			IMPushUtil.push(teamIds, DataAction.D$delete, teamNoticeId, BpCommonConstants.PUSH_TEAM_NOTICE, 
//					null, bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$delete, teamNoticeId,BpCommonConstants.PUSH_TEAM_NOTICE, null, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$delete,teamNoticeId, BpCommonConstants.PUSH_TEAM_NOTICE,null, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====		
			this.teamNoticeService.remove(teamNoticeId);			
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}

	@Override
	public Object createTeamNotices(String teamIds, Integer posterId,
			String posterName, String uuids, String title, Long postTime,
			String content, Long startTime, Long finishTime, String appKey, String signage) {
		BBXNotice bbxNotice = new BBXNotice();
		ResponseAdd add = new ResponseAdd();
		List<String>  noticeFiles = new ArrayList<String>();
		List<Integer>  teams = new ArrayList<Integer>();
		Long userCount = 0L;
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamIds == null || "".equals(teamIds)){
				return ResponseUtil.paramerIsNull("teamIds:"+teamIds);
			}
			String[] teamList = teamIds.split(",");
			String receiverName = "";
			for(String teamId:teamList){
				teams.add(Integer.parseInt(teamId));
				//拼装发送的对象
				try {
					Team t = this.teamService.findTeamById(Integer.parseInt(teamId));
					receiverName+=t.getName()+",";
					List<TeamUser>teamUserAll = this.teamUserService.findTeamUserOfAll(Integer.parseInt(teamId));
					userCount+=teamUserAll.size();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<String> imageUuidlList = null;
			if ( uuids != null && uuids.length() > 0  ) {
				imageUuidlList = JSONObject.parseArray(uuids, String.class);
			} 
			//拼装上传的文件的uuid
			if(imageUuidlList!=null){
				if(imageUuidlList.size()>0){
					for(int j=0;j<imageUuidlList.size();j++){
						try {
							noticeFiles.add(imageUuidlList.get(j));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			//添加通知记录
			if(startTime != null){
				bbxNotice.setStartTime(new Date(startTime));
			}
			if(finishTime != null){
				bbxNotice.setFinishTime(new Date(finishTime));
			}
		 	bbxNotice.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
	        bbxNotice.setAppKey(appKey);
	        bbxNotice.setTitle(("".equals(title) || title==null)?"班级通知":title);
	        bbxNotice.setPosterId(posterId);
	        bbxNotice.setPostTime(("".equals(postTime) || postTime==null)?new Date():new Date(postTime));
	        bbxNotice.setPosterName(posterName);
	        bbxNotice.setReceiverName("".equals(receiverName)?"":receiverName.substring(0, receiverName.length()-1));
	        bbxNotice.setContent(content);
	        bbxNotice.setUserCount(Integer.parseInt(String.valueOf(userCount)));
	        bbxNotice = this.teamNoticeService.senTeamNotice(bbxNotice, noticeFiles, teams);
	        add.setAddedId(bbxNotice.getId());
			add.setCreateTime(sdf.format(bbxNotice.getCreateDate()));
			//推送开始=====
			//推送
			Map<String, Object> map = new HashMap<String, Object>();	
			if(bbxNotice.getStartTime() != null){
				map.put("startTime", bbxNotice.getStartTime().getTime());
			}else{
				map.put("startTime", null);
			}
			if(bbxNotice.getFinishTime() != null){
				map.put("endTime", bbxNotice.getFinishTime().getTime());
			}else{
				map.put("endTime", null);
			}
			map.put("modeType", BpCommonConstants.PUSH_TEAM_NOTICE);
			map.put("priority", BpCommonConstants.PRIORITY_1);
			map.put("immediately", 0);
//			IMPushUtil.push(teams, DataAction.D$add, bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, 
//					map.toString(), bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);
			
//			IMPushUtil.signagePush(teams, 1, DataAction.D$delete, bbxNotice.getId(),BpCommonConstants.PUSH_TEAM_NOTICE, map.toString(), bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teams, 1, DataAction.D$delete,bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			/*String IMcontent = StringContentUtil.getString(bbxNotice.getContent());
			IMPushUtil.push(teams, DataAction.D$add, bbxNotice.getId(), BpCommonConstants.PUSH_TEAM_NOTICE, 
					("".equals(IMcontent) || IMcontent==null)? title:IMcontent, bpBwSignageService, schoolTermCurrentService, 
					roomTeamService, teamService, taskExecutor);*/
			//推送结束 ====
		}catch(IllegalArgumentException e){
			return new ResponseVo<ResponseAdd>("参数格式错误", null);
		} catch (Exception e) {
			log.info("参数为空...");
			return new ResponseVo<ResponseAdd>("0", null);
		}
		return new ResponseVo<ResponseAdd>("0", add);
	}

}
