package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpCircleRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.DateUtil;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.CircleMessageClientVo;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.sns.model.Circle;
import platform.education.sns.model.CircleMessage;
import platform.education.sns.service.CircleMessageService;
import platform.education.sns.service.CircleService;
import platform.education.sns.vo.CircleCondition;
import platform.education.sns.vo.CircleMessageCondition;
import platform.education.sns.vo.CircleMessageVo;
import platform.education.user.service.AppEditionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BpCircleRestServiceImpl implements BpCircleRestService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("snsCircleMessageService")
	private CircleMessageService snsCircleMessageService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("snsCircleService")
	private CircleService snsCircleService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	@Resource(name = "bbx_circleMessage_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object findCircle(Integer teamId, Integer searchId,Integer searchType, String pageSize,
			String appKey,String signage) {
		try {
			if(teamId == null || "".equals(teamId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			// 处理分页参数
			Page page = new Page();
			// 处理排序参数
			Order order = new Order();
			CircleMessageCondition circleMessageCondition = new CircleMessageCondition();
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
			}else if(searchType.equals(1)){
				circleMessageCondition.setSearchId(searchId);
				circleMessageCondition.setSearchType(searchType);
			}else if(searchType.equals(2)){
				circleMessageCondition.setSearchId(searchId);
				circleMessageCondition.setSearchType(searchType);
				page.setPageSize(Integer.parseInt(pageSize));// pageSize
			}
			// 如果指定班级则按班级查询.
			if (teamId != null) {
				circleMessageCondition.setTeamIds(teamId + "");
			}

			List<CircleMessageClientVo> circleMessageClientVos = new ArrayList<CircleMessageClientVo>();;
			List<CircleMessageVo> circleMessageVos = snsCircleMessageService
					.findCircleMessageVoByCondition(circleMessageCondition,
							page, order);
			if (circleMessageVos != null && circleMessageVos.size() > 0) {
				CircleMessageClientVo circleMessageClientVo = null;
				// 遍历结果集组拼客户端需要的数据
				for (CircleMessageVo circleMessageVo : circleMessageVos) {
					circleMessageClientVo = new CircleMessageClientVo();
					PropertyUtils.copyProperties(circleMessageClientVo,
							circleMessageVo);
					circleMessageClientVo.setPostTime(circleMessageVo.getCreateDate());
					circleMessageClientVo.setModifyTime(DateUtil
							.dateToString(circleMessageVo.getModifyDate()));
					// 处理图片.
					String entityId = circleMessageVo.getEntityId();
					if (entityId != null && !entityId.trim().equals("")) {
						List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
						String[] uuids = entityId.split(",");
						for (int j = 0; j < uuids.length; j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("fileUuid", uuids[j]);
							map.put("fileUrl", circleMessageVo.getImgUrls()
									.get(j));
							files.add(map);
						}
						circleMessageClientVo.setFiles(files);
					}
					circleMessageClientVos.add(circleMessageClientVo);
				}
			}
			return new ResponseOrder<List<CircleMessageClientVo>>("0",
					circleMessageClientVos, page.getTotalRows());
		} catch (Exception e) {
			log.info("查询动态异常...");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("查询动态异常...");
			info.setMsg(e.getMessage());
			info.setParam("");
			return new ResponseError("060001", info);
		}

	}

	public Object createCircleMessage(Integer teamId, Integer posterId, String content, String files, String appKey,
			String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if ( posterId == null ) {
				return ResponseUtil.paramerIsNull("posterId:"+posterId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			CircleCondition circleCondition = new CircleCondition();
			circleCondition.setObjectId(teamId);
			List<Circle> circles = snsCircleService.findCircleByCondition(circleCondition);
			if ( circles == null || circles.size() == 0 ) {
				return ResponseUtil.dataNotExist("teamId:" + teamId);
			}
			Integer circleId = circles.get(0).getId();
			CircleMessage circleMessage = new CircleMessage();
			circleMessage.setCircleId(circleId);
			String imgUUIDs = "";
			if (files != null && !files.trim().equals("")) {
				String[] fileUuids = (String[]) JSONArray.toArray(
						JSONArray.fromObject(files), String.class);
				if (fileUuids != null && fileUuids.length > 0) {
					for (String file : fileUuids) {
						imgUUIDs += file + ",";
					}
					imgUUIDs = imgUUIDs.substring(0, imgUUIDs.length() - 1);
				}
			}
			circleMessage.setEntityId(imgUUIDs);
			circleMessage.setContent(content);
			circleMessage.setPosterId(posterId);
			circleMessage = snsCircleMessageService.add(circleMessage);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(teamId);
//			IMPushUtil.push(teamIds, DataAction.D$add, circleMessage.getId(), BpCommonConstants.PUSH_CIRCLE, "", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, circleMessage.getId(), BpCommonConstants.PUSH_CIRCLE, "", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,circleMessage.getId(), BpCommonConstants.PUSH_CIRCLE,"", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Integer>("0", circleMessage.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object deleteCircleMessage(Integer id, String appKey, String signage) {
		try {
			if ( id == null ) {
				return ResponseUtil.paramerIsNull("id:"+id);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			CircleMessage circleMessage = snsCircleMessageService.findCircleMessageById(id);
			if ( circleMessage == null ) {
				return ResponseUtil.dataNotExist("id:"+id);
			}
			snsCircleMessageService.remove(circleMessage);
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
