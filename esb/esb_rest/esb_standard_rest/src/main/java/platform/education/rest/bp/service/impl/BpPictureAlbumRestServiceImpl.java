package platform.education.rest.bp.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import platform.education.clazz.model.BpBwPicture;
import platform.education.clazz.model.BpBwPictureAlbum;
import platform.education.clazz.service.BpBwPictureAlbumService;
import platform.education.clazz.service.BpBwPictureService;
import platform.education.clazz.service.BpBwSignageService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.vo.BpBwPictureAlbumCondition;
import platform.education.clazz.vo.BpBwPictureAlbumVo;
import platform.education.clazz.vo.BpBwPictureCondition;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.rest.bp.service.BpPictureAlbumRestService;
import platform.education.rest.bp.service.contants.BpCommonConstants;
import platform.education.rest.bp.service.contants.DataAction;
import platform.education.rest.bp.service.util.IMPushUtil;
import platform.education.rest.bp.service.util.ResponseUtil;
import platform.education.rest.bp.service.util.ValidateUtil;
import platform.education.rest.bp.service.vo.ResponseOrder;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BpPictureAlbumRestServiceImpl implements BpPictureAlbumRestService {

	
	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("bpBwPictureAlbumService")
	private BpBwPictureAlbumService bpBwPictureAlbumService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("bpBwPictureService")
	private BpBwPictureService bpBwPictureService;
	
	@Resource(name = "bp_picture_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Autowired
	@Qualifier("bpBwSignageService")
	private BpBwSignageService bpBwSignageService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getPictureAlbumList(Integer teamId, Integer searchId, Integer searchType, Integer pageNumber, Integer pageSize,
			String sortItem, boolean ascending, String appKey, String signage) {
		try {
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			if(searchType == null || "".equals(searchType)){
				return ResponseUtil.paramerIsNull("searchType");
			}
			if ( teamId < 1 ) {
				return ResponseUtil.paramterError("fileId:"+teamId);
			}
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			page.setPageSize(pageSize);
			Order order = new Order(sortItem, ascending);
			BpBwPictureAlbumCondition bwPictureAlbumCondition = new BpBwPictureAlbumCondition();
			bwPictureAlbumCondition.setTeamId(teamId);
			if(searchType.equals(0)){ // 第一次查询
				page.setPageSize(pageSize);// pageSize
			}else if(searchType.equals(1)){ // 下拉查询
				bwPictureAlbumCondition.setSearchId(searchId);
				bwPictureAlbumCondition.setSearchType(searchType);
			}else if(searchType.equals(2)){ // 上拉查询
				bwPictureAlbumCondition.setSearchId(searchId);
				bwPictureAlbumCondition.setSearchType(searchType);
				page.setPageSize(pageSize);// pageSize
			}
			List<BpBwPictureAlbumVo> albumVoList = bpBwPictureAlbumService.findBwPictureAlbumByCondition(bwPictureAlbumCondition, page, order);
			long recordCount = bpBwPictureAlbumService.count(bwPictureAlbumCondition);
			return new ResponseOrder("0", albumVoList, (int)recordCount);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	

	@Override
	public Object createPictureAlbum(Integer postUserId, Integer teamId, String name, String uuids, String appKey, String signage) {
		try {
			if ( postUserId == null ) {
				return ResponseUtil.paramerIsNull("postUserId:"+postUserId);
			}
			if ( teamId == null ) {
				return ResponseUtil.paramerIsNull("teamId:"+teamId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			List<String> uuidList = null;
			try {
				uuidList = JSONObject.parseArray(uuids, String.class);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseUtil.paramterError("uuids:"+uuids);
			}
			BpBwPictureAlbumVo bwPictureAlbumVo = new BpBwPictureAlbumVo();
			bwPictureAlbumVo.setPostUserId(postUserId);
			bwPictureAlbumVo.setTeamId(teamId);
			bwPictureAlbumVo.setName(name);
			bwPictureAlbumVo.setImageUuidlList(uuidList);
			BpBwPictureAlbum result = bpBwPictureAlbumService.add(bwPictureAlbumVo);
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(result.getTeamId());
//			IMPushUtil.push(teamIds, DataAction.D$add, result.getId(), BpCommonConstants.PUSH_CLASS_PHOTO, "", 
//					bpBwSignageService, schoolTermCurrentService, roomTeamService, teamService, taskExecutor);	
			
//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$add, result.getId(),BpCommonConstants.PUSH_CLASS_PHOTO, "", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1, DataAction.D$add,result.getId(), BpCommonConstants.PUSH_CLASS_PHOTO, "", bpBwSignageService,imAccountService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====	
			
			return new ResponseVo<Integer>("0", result.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object delete(Integer pictureAlbumId, String appKey, String signage) {
		try {
			if ( pictureAlbumId == null ) {
				return ResponseUtil.paramerIsNull("pictureAlbumId:"+pictureAlbumId);
			}
			Object checkResult = ValidateUtil.checkAppKeyAndSignae(appKey, signage, appEditionService, bpBwSignageService);
			if ( checkResult != null ) {
				return checkResult;
			}
			BpBwPictureAlbumVo album = bpBwPictureAlbumService.findBwPictureAlbumById(pictureAlbumId);
			if ( album == null ) {
				return ResponseUtil.dataNotExist("pictureAlbumId:" + pictureAlbumId);
			}
			BpBwPictureAlbum bwPictureAlbum = new BpBwPictureAlbum();
			bwPictureAlbum.setId(pictureAlbumId);
			bpBwPictureAlbumService.remove(bwPictureAlbum);;
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}


	@Override
	public Object add(Integer pictureAlbumId, String pictureUuid, String appKey) {
		List<String> urlList = new ArrayList<String>();
		try {
			List<String> uuidList = null;
			uuidList = JSONObject.parseArray(pictureUuid, String.class);
			BpBwPictureCondition condition = new BpBwPictureCondition();
			condition.setAlbumId(pictureAlbumId);
			BpBwPictureAlbumVo album = bpBwPictureAlbumService.findBwPictureAlbumById(pictureAlbumId);
			BpBwPicture picture = null;
			List<Map<String, Object>> imageDetailList = new ArrayList<Map<String, Object>>();
			for(String uuid : uuidList){
				picture = new BpBwPicture();
				picture.setAlbumId(pictureAlbumId);
				picture.setTeamId(album.getTeamId());
				picture.setPostUserId(album.getPostUserId());
				picture.setFileUuid(uuid);
				urlList.add(fileService.relativePath2HttpUrlByUUID(uuid));
				BpBwPicture entity = this.bpBwPictureService.add(picture);
				String fileUuid = entity.getFileUuid();
				String fileUrl = fileService.relativePath2HttpUrlByUUID(fileUuid);
				Map<String, Object> map = new TreeMap<String, Object>();
				map.put("id", entity.getId());
				map.put("url", fileUrl);
				map.put("uuid", fileUuid);
				imageDetailList.add(map);
			}
			return new ResponseVo<List<Map<String, Object>>>("0", imageDetailList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}


	@Override
	public Object deleteFromAlbum(
			String pictureIds,
			String appKey) {
		try {
			List<Integer> idList = null;
			idList = JSONObject.parseArray(pictureIds, Integer.class);
			for (Integer id : idList) {
				BpBwPicture entity = bpBwPictureService.findBwPictureById(id);
				if ( entity != null && entity.getId() != null ) {
					this.bpBwPictureService.remove(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
		return new ResponseVo<Boolean>("0", true);
	}

}
