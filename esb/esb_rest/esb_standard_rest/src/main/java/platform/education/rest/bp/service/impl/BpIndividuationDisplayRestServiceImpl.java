package platform.education.rest.bp.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.IndividuationDisplay;
import platform.education.clazz.service.*;
import platform.education.clazz.vo.IndividuationDisplayCondition;
import platform.education.clazz.vo.IndividuationDisplayVo;
import platform.education.generalTeachingAffair.service.*;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpIndividuationDisplayRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.DateUtil;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

public class BpIndividuationDisplayRestServiceImpl implements BpIndividuationDisplayRestService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
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

	@Resource(name = "bp_individuationDisplay_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Autowired
	@Qualifier("teamHomeworkService")
	private TeamHomeworkService teamHomeworkService;
	
	@Autowired
	@Qualifier("subjectService")
	protected SubjectService subjectService;
	
	@Autowired
	@Qualifier("teamHomeworkFileService")
	private TeamHomeworkFileService teamHomeworkFileService;

	@Autowired
	@Qualifier("entityFileService")
	protected EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("individuationDisplayService")
	protected IndividuationDisplayService individuationDisplayService;
	
	@Autowired
	@Qualifier("teacherService")
	protected TeacherService teacherService;
	
	@Autowired
	@Qualifier("teamUserService")
	protected TeamUserService teamUserService;
	
	@Autowired
	@Qualifier("teamAccountService")
	protected TeamAccountService teamAccountService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	protected TeamTeacherService teamTeacherService;
	
	@Autowired
	@Qualifier("roomTeamService")
	protected RoomTeamService roomTeamService;
	
	@Override
	public Object getIndividuationDisplay( Integer teamId,Integer searchId,Integer searchType,Integer pageSize,
			String appKey, String signage) {
		Order order = new Order();
		Page page = new Page();
		List<IndividuationDisplayVo> items = new ArrayList<IndividuationDisplayVo>();
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			IndividuationDisplayCondition condition = new IndividuationDisplayCondition();
			if(searchType != null && !"".equals(searchType)){
				if(searchType.equals(0)){
					page.setPageSize(pageSize);// pageSize
				}else if(searchType.equals(1)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
				}else if(searchType.equals(2)){
					if(searchId == null || "".equals(searchId)){
						return ResponseUtil.paramerIsNull("searchId:"+searchId);
					}
					condition.setSearchId(searchId);
					condition.setSearchType(searchType);
					page.setPageSize(pageSize);// pageSize
				}
			}
			condition.setTeamId(teamId);
			condition.setIsDeleted(false);
			List<IndividuationDisplay> list = this.individuationDisplayService.findIndividuationDisplayByCondition(condition,page,order);
			if(list != null && list.size() > 0){
				for(IndividuationDisplay i: list){
					IndividuationDisplayVo iVo = new IndividuationDisplayVo();
					BeanUtils.copyProperties(i, iVo);
					String imgUuids = i.getPictureUuid();
					/*if(!StringUtils.isEmpty(imgUuids)){
						String[] uuidArr = imgUuids.split(",");
						List<String> imgUrls = new ArrayList<String>();
						for(String uuid: uuidArr){
							imgUrls.add(fileService.relativePath2HttpUrlByUUID(uuid));
						}
						iVo.setImgUrls(imgUrls);
					}*/
					items.add(iVo);
				}
		
			}
			return new ResponseVo<List<IndividuationDisplayVo>>("0", items);
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
	public Object createIndividuationDisplay(String teamIds, Integer schoolId,
			String title, Integer contentType, String content, String uuids,String isCirculate,Long examDate,
			Long startTime, Long finishTime, String appKey, String signage) {
		Date nowTime = new Date();
		List<Integer> teamIdList = new ArrayList<Integer>();
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(teamIds == null || "".equals(teamIds)){
				return ResponseUtil.paramerIsNull("teamIds:"+teamIds);
			}
			if(schoolId == null || "".equals(schoolId)){
				return ResponseUtil.paramerIsNull("schoolId:"+schoolId);
			}
			if(contentType == null || "".equals(contentType)){
				return ResponseUtil.paramerIsNull("contentType:"+contentType);
			}
			if(title == null || "".equals(title)){
				return ResponseUtil.paramerIsNull("title:"+title);
			}
			if(finishTime == null || "".equals(finishTime)){
				return ResponseUtil.paramerIsNull("finishTime:"+finishTime);
			}
			String[] teamList = teamIds.split(",");
			for(String teamId:teamList){
				IndividuationDisplay individuationDisplay = new IndividuationDisplay();
				individuationDisplay.setSchoolId(schoolId);
				individuationDisplay.setTeamId(Integer.parseInt(teamId));
				individuationDisplay.setTitle(title);
				individuationDisplay.setContentType(contentType);
				if("1".equals(isCirculate)){
					individuationDisplay.setIsCirculate(true);
				}else{
					individuationDisplay.setIsCirculate(false);
				}
				if(examDate!=null && !examDate.equals(0l)){
					individuationDisplay.setExamDate(new Date(examDate));
				}
				if(1 == contentType){
					individuationDisplay.setContent(content);
				}else if(2 == contentType){
					List<String> uuidList = JSONObject.parseArray(uuids, String.class);
					String pictureUuids = "";
					if(uuidList.size()>0 ){
						for(int i=0;i<uuidList.size();i++){
							if(i == 0){
								pictureUuids = uuidList.get(i);
							}else{
								pictureUuids = pictureUuids +","+uuidList.get(i);
							}
						}
						individuationDisplay.setPictureUuid(pictureUuids);
					}
				}
				if(startTime == null){
					individuationDisplay.setStartTime(nowTime);
				}else{
					individuationDisplay.setStartTime(new Date(startTime));
				}
				individuationDisplay.setFinishTime(new Date(finishTime));
				individuationDisplay.setIsDeleted(false);
				this.individuationDisplayService.add(individuationDisplay);
				teamIdList.add(Integer.parseInt(teamId));
			}
			//推送内容
			Map<String, Object> map = new HashMap<String, Object>();	
			if(startTime == null){
				map.put("startTime", nowTime);	
			}else{
				map.put("startTime", startTime);	
			}
			
			map.put("endTime", finishTime);
			map.put("modeType", BpCommonConstants.INDIVIDUATION_DISPLAY);
			map.put("priority", BpCommonConstants.PRIORITY_3);
			map.put("immediately", 0);
			if("1".equals(isCirculate)){
				map.put("everyDay",1);
			}else{
				map.put("everyDay",0);
			}
//			IMPushUtil.push(teamIdList, DataAction.D$add, null, BpCommonConstants.INDIVIDUATION_DISPLAY, map.toString(),
//					 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIdList, 1, DataAction.D$add, null,BpCommonConstants.INDIVIDUATION_DISPLAY,map.toString(), bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIdList, 1, DataAction.D$add, 0, BpCommonConstants.INDIVIDUATION_DISPLAY,map.toString(), bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			return new ResponseVo<Boolean>("0", true);
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
	public Object deleteIndividuationDisplay(Integer id, String appKey,
			String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			if(id == null || "".equals(id)){
				return ResponseUtil.paramerIsNull("teamId:"+id);
			}
			IndividuationDisplay individuationDisplay = this.individuationDisplayService.findIndividuationDisplayById(id);
			this.individuationDisplayService.remove(individuationDisplay);
			//推送开始 ====
			List<Integer> teamIds = new ArrayList<Integer>();	
			teamIds.add(individuationDisplay.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$delete, null, BpCommonConstants.INDIVIDUATION_DISPLAY, null,
//					 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$delete, null,BpCommonConstants.INDIVIDUATION_DISPLAY,null, bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$delete, null, BpCommonConstants.INDIVIDUATION_DISPLAY,null, bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
			return new ResponseVo<Boolean>("0", true);
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
	public Object getIndividuationDisplay(Integer teamId, Long currentTime, String appKey, String signage) {
		try {
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}	
			/*if(StringUtils.isEmpty(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}*/
			
			Date nowTime = new Date(currentTime);
			
			
			Order order = new Order();
			order.setProperty("create_date");
			order.setAscending(false);
					IndividuationDisplayCondition condition = new IndividuationDisplayCondition();
			condition.setTeamId(teamId);
			condition.setIsDeleted(false);
			List<IndividuationDisplay> list = this.individuationDisplayService.findIndividuationDisplayByCondition(condition,null,order);
			List<IndividuationDisplayVo> items = new ArrayList<IndividuationDisplayVo>();
			if(list != null && list.size() > 0){
				for(IndividuationDisplay i: list){
					if(i.getStartTime().before(nowTime) && i.getFinishTime().after(nowTime)){
						IndividuationDisplayVo iVo = new IndividuationDisplayVo();
						BeanUtils.copyProperties(i, iVo);
						String imgUuids = i.getPictureUuid();
						/*if(!StringUtils.isEmpty(imgUuids)){
							String[] uuidArr = imgUuids.split(",");
							List<String> imgUrls = new ArrayList<String>();
							for(String uuid: uuidArr){
								imgUrls.add(fileService.relativePath2HttpUrlByUUID(uuid));
							}
							iVo.setImgUrls(imgUrls);
						}*/
						if(iVo.getExamDate() != null){
							iVo.setExamDateTime(DateUtil.getBetweenTimeDay(new Date(), iVo.getExamDate()));
						}				
						items.add(iVo);
					}			
				}
				
				if(items.size() == 0){
					IndividuationDisplayVo iVo = new IndividuationDisplayVo();
					IndividuationDisplay i = list.get(0);
					BeanUtils.copyProperties(i, iVo);
					String imgUuids = i.getPictureUuid();
					/*if(!StringUtils.isEmpty(imgUuids)){
						String[] uuidArr = imgUuids.split(",");
						List<String> imgUrls = new ArrayList<String>();
						for(String uuid: uuidArr){
							imgUrls.add(fileService.relativePath2HttpUrlByUUID(uuid));
						}
						iVo.setImgUrls(imgUrls);			
					}*/
					if(iVo.getExamDate() != null){
						iVo.setExamDateTime(DateUtil.getBetweenTimeDay(new Date(), iVo.getExamDate()));
					}
					items.add(iVo);
				}			
			}
			return new ResponseVo<List<IndividuationDisplayVo>>("0", items);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
	
	
}
