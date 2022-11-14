package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwInfoMore;
import platform.education.clazz.service.BpBwInfoMoreService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.vo.BpBwInfoMoreCondition;
import platform.education.clazz.vo.BpBwInfoMoreVo;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpInfoMoreRestService;
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
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BpInfoMoreRestServiceImpl implements BpInfoMoreRestService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("bpBwInfoMoreService")
	private BpBwInfoMoreService bpBwInfoMoreService;
	
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
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;

	@Resource(name = "bp_notice_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findLost(Integer teamId, Integer searchId,Integer searchType, String pageSize,
			String appKey,String signage) {
		Order order = new Order();
		Page page = new Page();
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
			BpBwInfoMoreCondition condition = new BpBwInfoMoreCondition();
			condition.setIsDeleted(false);
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
			condition.setTeamId(teamId);
			condition.setType(2);
			condition.setIsDeleted(false);
			List<BpBwInfoMore> infoList = this.bpBwInfoMoreService.findBpBwInfoMoreByCondition(condition, page, order);
			return new ResponseVo<List<BpBwInfoMore>>("0", infoList);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}

	}
	
	@Override
	public Object create(String appKey,String signage, Integer teamId, String content) {
		try{
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			BpBwInfoMore notice = new BpBwInfoMore();
			notice.setTeamId(teamId);
			notice.setType(2);//1：电子黑板报 2：寻物启事
			notice.setContent(content);
			notice = this.bpBwInfoMoreService.add(notice);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(teamId);
//			IMPushUtil.push(teamIds, DataAction.D$add, notice.getId(), BpCommonConstants.PUSH_NOTICE, "", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, notice.getId(),BpCommonConstants.PUSH_NOTICE,"", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,notice.getId(), BpCommonConstants.PUSH_NOTICE, "", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Integer>("0", notice.getId());
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	
	@Override
	public Object creates(String appKey, String signage, String teamIds,
			String content) {
		try{
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			List<Integer> teams = new ArrayList<Integer>();
			String[] teamList = teamIds.split(",");
			for(String teamId:teamList){
				BpBwInfoMore notice = new BpBwInfoMore();
				notice.setType(2);//1：电子黑板报 2：寻物启事
				notice.setContent(content);
				notice.setTeamId(Integer.parseInt(teamId));
				notice = this.bpBwInfoMoreService.add(notice);
				//推送
				//要推送的班级
				teams.add(Integer.parseInt(teamId));
			}
//			IMPushUtil.push(teams, DataAction.D$add, null, BpCommonConstants.PUSH_NOTICE, "", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teams, 1, DataAction.D$add, null,BpCommonConstants.PUSH_NOTICE,"", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teams, 1, DataAction.D$add,null, BpCommonConstants.PUSH_NOTICE, "", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Boolean>("0", true);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object delete(Integer noticeId, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			BpBwInfoMore notice = this.bpBwInfoMoreService.findBpBwInfoMoreById(noticeId);
			if ( notice == null ) {
				return ResponseUtil.dataNotExist("notice:"+notice);
			}
			this.bpBwInfoMoreService.remove(notice);
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
	public Object findBlackBoardNews(Integer teamId,Integer searchId,Integer searchType, String pageSize,String appKey,String signage) {
		Order order = new Order();
		Page page = new Page();
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
			BpBwInfoMoreCondition condition = new BpBwInfoMoreCondition();
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
			condition.setTeamId(teamId);
			condition.setType(1);
			condition.setIsDeleted(false);
			List<BpBwInfoMore> infoList = this.bpBwInfoMoreService.findBpBwInfoMoreByCondition(condition, page, order);
			List<BpBwInfoMoreVo> list = new ArrayList<BpBwInfoMoreVo>();
//			BpBwInfoMore info = null;
			// if(infoList.size()>0){
			for (BpBwInfoMore bpBwInfoMore : infoList) {
//				info = infoList.get(0);
				BpBwInfoMoreVo vo = new BpBwInfoMoreVo();
				vo.setImgUrl(this.fileService.relativePath2HttpUrlByUUID(bpBwInfoMore.getFile()));
				PropertyUtils.copyProperties(vo,bpBwInfoMore);
				list.add(vo);
			}
			
			// }
			return new ResponseVo<List<BpBwInfoMoreVo>>("0", list);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object createBlackBoardNews(Integer teamId, String content, String file, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			BpBwInfoMore bpBwInfoMore = new BpBwInfoMore();
			bpBwInfoMore.setTeamId(teamId);
			bpBwInfoMore.setFile(file);
			bpBwInfoMore.setType(1);//1：电子黑板报 2：寻物启事
			bpBwInfoMore.setContent(content);
			bpBwInfoMore.setFile(file);
			bpBwInfoMore = bpBwInfoMoreService.add(bpBwInfoMore);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(bpBwInfoMore.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$add, bpBwInfoMore.getId(), BpCommonConstants.PUSH_BLACKBOARD, "", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, bpBwInfoMore.getId(),BpCommonConstants.PUSH_BLACKBOARD,"", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,bpBwInfoMore.getId(), BpCommonConstants.PUSH_BLACKBOARD, "", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Integer>("0", bpBwInfoMore.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object deleteBlackBoardNews(Integer id, String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			BpBwInfoMore bpBwInfoMore = bpBwInfoMoreService.findBpBwInfoMoreById(id);
			if ( bpBwInfoMore == null ) {
				return ResponseUtil.dataNotExist("id:" + id);
			}
			bpBwInfoMoreService.remove(bpBwInfoMore);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	

	
	

	


	
}
