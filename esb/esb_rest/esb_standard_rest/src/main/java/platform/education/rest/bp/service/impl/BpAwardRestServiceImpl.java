package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.TeamAward;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.TeamAccountService;
import platform.education.clazz.service.TeamAwardService;
import platform.education.clazz.vo.TeamAwardCondition;
import platform.education.clazz.vo.TeamAwardVo;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpAwardRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.ClientTeamAwardVo;
import platform.education.rest.bp.service.vo.ResponseDataVo;
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

public class BpAwardRestServiceImpl implements BpAwardRestService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	// 班级
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	// 班级账号
	@Autowired
	@Qualifier("teamAccountService")
	protected TeamAccountService teamAccountService;

	// 班级荣誉
	@Autowired
	@Qualifier("teamAwardService")
	protected TeamAwardService teamAwardService;
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	// 当前学期
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bbx_teamAward_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findTeamAward(Integer teamId, Integer searchId,Integer searchType, String pageSize,
			String appKey,String signage) {
		
		ClientTeamAwardVo data[] = null;
		ClientTeamAwardVo ctav = null;
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(searchType == null || "".equals(searchType)){
				return ResponseUtil.paramerIsNull("searchType:"+searchType);
			}
			Order order = new Order();
			Page page = new Page();
			TeamAwardCondition condition = new TeamAwardCondition();
			condition.setTeamId(teamId);
			if (teamId != null && !"".equals(teamId)) {
				Team t = this.teamService.findTeamById(teamId);
				if (t != null) {
					condition.setSchoolId(t.getSchoolId());
				}
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
			List<TeamAwardVo> list = this.teamAwardService.findTeamAwardVoByCondition(condition, page, order);
			if (list.size() > 0) {
				data = new ClientTeamAwardVo[list.size()];
				for (int i = 0; i < list.size(); i++) {
					data[i] = new ClientTeamAwardVo();
					ctav = new ClientTeamAwardVo();
					Team t = this.teamService.findTeamById(list.get(i)
							.getTeamId());
					if (t != null) {
						ctav.setId(list.get(i).getId());
						ctav.setSchoolId(list.get(i).getSchoolId());
						ctav.setTeamId(list.get(i).getTeamId());
						ctav.setTeanName(t.getName());
						ctav.setName(list.get(i).getName());
						ctav.setAwardTime(sdf.format(list.get(i).getAwardTime()));
						ctav.setAwardImage(list.get(i).getAwardImage());
						if (list.get(i).getAwardImage() != null
								|| !"".equals(list.get(i).getAwardImage())) {
							ctav.setAwardImageURL(this.fileService
									.relativePath2HttpUrlByUUID(ctav
											.getAwardImage()));
						} else {
							ctav.setAwardImageURL("");
						}
						ctav.setComment(list.get(i).getComment());
						ctav.setSchoolYear(list.get(i).getSchoolYear());
						ctav.setTermCode(list.get(i).getTermCode());
						ctav.setCreateDate(list.get(i).getCreateDate());
						ctav.setModifyDate(sdf.format(list.get(i).getModifyDate()));
						data[i] = ctav;
					}
				}
			}
			if(data == null){
				List<ClientTeamAwardVo> list1 = new ArrayList<ClientTeamAwardVo>();
				return new ResponseVo<List<ClientTeamAwardVo>>("0", list1);
			}else{
				return new ResponseDataVo<ClientTeamAwardVo>("0", data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}

	
	@Override
	public Object createTeamAward(Integer teamId, String name, Long awardTime, String awardImage, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Team t = this.teamService.findTeamById(teamId);
			if (t == null) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			
			TeamAward teamAward = new TeamAward();
			teamAward.setName(name);
		/*	if(!StringUtils.isEmpty(awardTime)){
				teamAward.setAwardTime(new Date(awardTime));
			}	*/
			teamAward.setAwardImage(awardImage);
			teamAward.setSchoolId(t.getSchoolId());
			teamAward.setTeamId(t.getId());
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(t.getSchoolId());
			teamAward.setSchoolYear(stc.getSchoolYear());
			teamAward.setTermCode(stc.getSchoolTermCode());
			teamAward = this.teamAwardService.add(teamAward);

			//推送开始=====
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(teamAward.getTeamId());
			//推送
//			IMPushUtil.push(teamIds, DataAction.D$add, teamAward.getId(), BpCommonConstants.PUSH_TEAM_AWARD, t.getName()+"又获得"+teamAward.getName()+"!", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, teamAward.getId(), BpCommonConstants.PUSH_TEAM_AWARD, t.getName()+"又获得"+teamAward.getName()+"!", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add, teamAward.getId(), BpCommonConstants.PUSH_TEAM_AWARD,t.getName()+"又获得"+teamAward.getName()+"!", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			return new ResponseVo<Integer>("0", teamAward.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}


	@Override
	public Object deleteTeamAward(Integer teamAwardId, String appKey, String signage) {
		try {
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			
			TeamAward teamAward = this.teamAwardService.findTeamAwardById(teamAwardId);
			if(teamAward == null){
				return ResponseUtil.dataNotExist("teamAwardId:"+teamAwardId);
			}
			this.teamAwardService.remove(teamAward);
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
