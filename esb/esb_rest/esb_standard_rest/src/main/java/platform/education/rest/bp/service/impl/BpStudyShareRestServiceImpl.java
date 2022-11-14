package platform.education.rest.bp.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.StudyShare;
import platform.education.clazz.model.StudyShareFile;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.StudyShareFileService;
import platform.education.clazz.service.StudyShareService;
import platform.education.clazz.vo.StudyShareCondition;
import platform.education.clazz.vo.StudyShareFileCondition;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpStudyShareRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.ByStudyShareVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BpStudyShareRestServiceImpl implements BpStudyShareRestService {
	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("studyShareService")
	private StudyShareService studyShareService;
	
	@Autowired
	@Qualifier("studyShareFileService")
	private StudyShareFileService studyShareFileService;
	
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
	
	@Resource(name = "bp_studyShare_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Override
	public Object list(Integer teamId, Integer searchId,Integer searchType, String pageSize, String appKey, String signage) {
		Order order = new Order();
		Page page = new Page();
		try {
			if(teamId == null || "".equals(teamId)){
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if(searchType == null || "".equals(searchType)){
				return ResponseUtil.paramerIsNull("searchType:"+searchType);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			order.setProperty("create_date");
			order.setAscending(false);
			page.setCurrentPage(1);// 当前页
			StudyShareCondition condition = new StudyShareCondition();
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
			condition.setIsDeleted(false);
			List<ByStudyShareVo> items = new ArrayList<ByStudyShareVo>();
			List<StudyShare> list = studyShareService.findStudyShareByCondition(condition, page, order);
			if(list != null && list.size() > 0){
				for(StudyShare s: list){
					ByStudyShareVo vo = new ByStudyShareVo();
					vo.setId(s.getId());
					vo.setTeamId(s.getTeamId());
					vo.setTitle(s.getTitle());
					vo.setContent(s.getContent());
					vo.setCreateDate(s.getCreateDate().getTime());
					List<String> imageUrlList = new ArrayList<String>();
					StudyShareFileCondition studyShareFileCondition = new StudyShareFileCondition();
					studyShareFileCondition.setStudyShareId(s.getId());
					studyShareFileCondition.setIsDeleted(false);
					List<StudyShareFile> studyShareFileList = this.studyShareFileService.findStudyShareFileByCondition(studyShareFileCondition);
					if(studyShareFileList != null && studyShareFileList.size() > 0){
						for(StudyShareFile file: studyShareFileList){
							imageUrlList.add(fileService.relativePath2HttpUrlByUUID(file.getFileId()));
						}
					}
					vo.setImageUrlList(imageUrlList);	
					items.add(vo);
				}				
			}
			return new ResponseVo<List<ByStudyShareVo>>("0", items);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	

	@Override
	public Object createStudyShare(Integer teamId, String title, String content, String uuids, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			if ( uuids == null ) {
				return ResponseUtil.paramerIsNull("uuids:"+uuids);
			}
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			StudyShare studyShare = new StudyShare();
			studyShare.setTeamId(teamId);
			studyShare.setContent(content);
			studyShare.setTitle(title);
			List<String> uuidList = null;
			try {
				uuidList = JSONObject.parseArray(uuids, String.class);
				studyShare = this.studyShareService.add(studyShare);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtil.paramterError("uuids:"+uuids);
			}
			if(uuidList.size() > 0){
				for(String imageUuid: uuidList){
					StudyShareFile studyShareFile = new StudyShareFile();
					studyShareFile.setStudyShareId(studyShare.getId());
					studyShareFile.setFileId(imageUuid);
					this.studyShareFileService.add(studyShareFile);
				}
			}
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(studyShare.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$add, studyShare.getId(), BpCommonConstants.PUSH_STUDY_SHARE, "", 
//				 bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, studyShare.getId(),BpCommonConstants.PUSH_STUDY_SHARE, "", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,studyShare.getId(), BpCommonConstants.PUSH_STUDY_SHARE,"", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			return new ResponseVo<Integer>("0", studyShare.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
	}

	
	@Override
	public Object deleteStudyShare(Integer studyShareId, String appKey, String signage) {
		try {
			if ( studyShareId == null ) {
				return ResponseUtil.paramerIsNull("studyShareId:"+studyShareId);
			}
			Object checkAppKeyResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkAppKeyResult != null ) {
				return checkAppKeyResult;
			}
			StudyShare studyShare = studyShareService.findStudyShareById(studyShareId);
			if ( studyShare == null ) {
				return ResponseUtil.dataNotExist("studyShareId:"+studyShareId);
			}
			studyShareService.remove(studyShare);
			return new ResponseVo<Boolean>("0", true);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}	
		 
	}

}
