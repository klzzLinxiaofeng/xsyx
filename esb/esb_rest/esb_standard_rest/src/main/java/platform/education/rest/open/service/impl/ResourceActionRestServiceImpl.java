package platform.education.rest.open.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.message.model.Comment;
import platform.education.message.model.LessComment;
import platform.education.message.service.CommentService;
import platform.education.resource.contants.ActionType;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.Favorites;
import platform.education.resource.model.LessFavorites;
import platform.education.resource.model.Resource;
import platform.education.resource.service.FavoritesService;
import platform.education.resource.service.LikesService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.FavoritesCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.rest.common.PageResponseVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.ObjectTypeContans;
import platform.education.rest.open.service.ResourceActionRestService;
import platform.education.rest.util.DateUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AclService;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

/**
 * @功能描述: 资源操作行为接口实现类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年1月19日下午3:47:10
 */

public class ResourceActionRestServiceImpl implements ResourceActionRestService {

	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;

	@Autowired
	@Qualifier("likesService")
	private LikesService likesService;

	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;

	@Autowired
	@Qualifier("favoritesService")
	private FavoritesService favoritesService;

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;

	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;

	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("aclService")
	private AclService aclService;

	@Override
	public Object commentListByResUuid(String sign, String appKey, String timeStamp, String resUuid) {
		ResourceVo resourceVo = this.resourceService.findSubFiledByResUuid(resUuid);
		if (resourceVo != null) {
			List<LessComment> lessCommentList = commentService.findCommentByObjectId(resourceVo.getObjectId());
			if (lessCommentList.size() == 0) {
				ResponseInfo info = new ResponseInfo();
				info.setMsg("找不到数据");
				info.setDetail("找不到数据");
				info.setParam("resUuid");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			return new ResponseVo<Object>("0", lessCommentList);
		}

		ResponseInfo info = new ResponseInfo();
		info.setMsg("没有此资源");
		info.setDetail("没有此资源");
		info.setParam("resUuid,userId");
		return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);

	}

	@Override
	public Object addComment(String sign, String appKey, String timeStamp, String resUuid, Integer userId,
			String content) {
		ResourceVo resourceVo = this.resourceService.findSubFiledByResUuid(resUuid);
		if (resourceVo == null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("没有此资源");
			info.setDetail("没有此资源");
			info.setParam("resUuid,userId");
			return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
		}

		Date nowdate = new Date();
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setUserId(userId);
		List<SchoolUser> schoolUserList = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);

		Comment comment = new Comment();
		comment.setAppId(0);
		comment.setObjectId(resourceVo.getObjectId());
		comment.setObjectType(compareType(resourceVo.getResType()));
		comment.setPosterId(userId);
		comment.setContent(content);
		if (schoolUserList != null && schoolUserList.size() > 0) {
			SchoolUser schoolUser = schoolUserList.get(0);
			comment.setPostName(schoolUser.getName());
		}
		comment.setCreateDate(nowdate);
		comment.setModifyDate(nowdate);
		comment.setResourceId(resourceVo.getId());

		comment = commentService.add(comment);
		if (comment.getId() != null) {
			LessComment lessComment = new LessComment();
			lessComment.setCommentId(comment.getId());
			lessComment.setPosterId(comment.getPosterId());
			lessComment.setPosterName(comment.getPostName());
			lessComment.setContent(comment.getContent());
			lessComment.setPostTime(DateUtil.dateToStrSS(comment.getPostTime()));

			Resource resource = resourceService.findSubFiledByResUuid(resUuid);

			// 更新resouce表评论总数
			if (resource != null) {
				Integer commentCount = resource.getCommentCount();
				if (commentCount != null) {
					commentCount = commentCount + 1;
				} else {
					commentCount = 1;
				}
				Resource temp = new Resource();
				temp.setId(resource.getId());
				temp.setCommentCount(commentCount);
				resourceService.modify(temp);
			}

			/** 用户操作资源记录 */
			resourceService.createResourceOperation(resUuid, userId, OperationType.COMMENT);
			/** 用户收藏行为记录 */
			resourceService.createUserAction(resUuid, userId, ActionType.COMMENT);

			return new ResponseVo<Object>("0", lessComment);
		}

		ResponseInfo info = new ResponseInfo();
		info.setMsg("发表评论失败");
		info.setDetail("发表评论失败");
		info.setParam("resUuid,userId,content");
		return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
	}

	@Override
	public Object deleteComment(String sign, String appKey, String timeStamp, Integer commentId, Integer userId) {
		Comment comment = commentService.findCommentById(commentId);
		
		if (comment == null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("commentId");
			return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
		}
		
		Integer posterId = comment.getPosterId();
		if(posterId==null || posterId-userId!=0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("参数内容错误");
			info.setDetail("不能删除非自身发表的评论");
			info.setParam("commentId， userId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		
		commentService.remove(comment);
		// 查找资源
		Resource resource = this.resourceService.findResourceById(comment.getResourceId());
		// 更新resouce表评论总数
		Integer commentCount = resource.getCommentCount();
		if (commentCount != null) {
			commentCount = commentCount - 1;
		} else {
			commentCount = 0;
		}
		Resource temp = new Resource();
		temp.setId(resource.getId());
		temp.setCommentCount(commentCount);
		return new ResponseVo<Object>("0", new HashMap<Object, Object>());

	}

	@Override
	public Object addLike(String sign, String appKey, String timeStamp, String resUuid, Integer userId) {
		ResourceVo resourceVo = this.resourceService.findSubFiledByResUuid(resUuid);
		if(resourceVo==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		AppEdition appEdition = appEditionService.findByAppKey(appKey);
		Integer appId = null;
		if (appEdition != null) {
			appId = appEdition.getAppId();
		}
		String status = likesService.saveUserLikes(appId, userId, resourceVo.getResType(), resourceVo.getObjectId(),
				resourceVo.getTitle(), resourceVo.getId());
		
		ResponseInfo info = new ResponseInfo();
		if ("success".equals(status)) {
			/** 用户操作资源记录 */
			resourceService.createResourceOperation(resUuid, userId, OperationType.LIKE);
			/** 用户收藏行为记录 */
			resourceService.createUserAction(resUuid, userId, ActionType.LIKES);
			return new ResponseVo<Object>("0", new HashMap<Object, Object>());
		} else if("faved".equals(status)) {
			info.setMsg("资源已经点赞过, 无法重复点赞");
			info.setDetail("资源已经点赞过, 无法重复点赞");
			info.setParam("resUuid,userId");
		} else {
			info.setMsg("添加点赞失败");
			info.setDetail("添加点赞失败");
			info.setParam("resUuid,userId");
		}
		return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);
	}

	@Override
	public Object removeLike(String sign, String appKey, String timeStamp, String resUuid, Integer userId) {
		ResourceVo resourceVo = this.resourceService.findSubFiledByResUuid(resUuid);
		if(resourceVo==null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		String likes = this.likesService.removeUserLikes(userId, resourceVo.getId());
		ResponseInfo info = new ResponseInfo();
		if ("success".equals(likes)) {
			resourceService.createUserAction(resUuid, userId, ActionType.UNLIKE);
			return new ResponseVo<Object>("0", new HashMap<Object, Object>());
		} else if("faved".equals(likes)) {
			info.setMsg("资源未点赞，不允许进行取消点赞操作");
			info.setDetail("资源未点赞，不允许进行取消点赞操作");
			info.setParam("resUuid,userId");
		} else {
			info.setMsg("取消点赞失败");
			info.setDetail("取消点赞失败");
			info.setParam("resUuid,userId");
		}

		return new ResponseError(CommonCode.$FAILED_TO_INTERPRET_PARAMETERS, info);

	}

	@Override
	public Object favoriteListByUserIdAndResType(String sign, String appKey, String timeStamp, Integer userId,
			String resType, String content, Integer pageNumber, Integer pageSize) {
		Page page = getPage(pageSize, pageNumber);
		List<LessFavorites> lessFavoritesList = this.favoritesService.findFavoriteListByUserIdAndResType(userId, resType, content, page);
		if (lessFavoritesList == null || lessFavoritesList.size() == 0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("userId, resType, pageNumber, pageSize");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (LessFavorites lessFavorites : lessFavoritesList) {
			Map<String, Object> result = getFavoriteResult(lessFavorites);
			resultList.add(result);
		}

		return new PageResponseVo<Object>("0", resultList, page.getTotalRows());
	}
	
	@Override
	public Object permissionGet(String sign, String appKey, String timeStamp, Integer schoolId, Integer userId) {
		boolean permission = aclService.hasPermission(userId, "XIAO_BEN_ZI_YUAN", 0, 1);
		
		Map<String, Boolean> result = new HashMap<String, Boolean>(1);
		result.put("isPermitted", permission);
		
		return new ResponseVo<Object>("0", result);
	}

	private Map<String, Object> getFavoriteResult(LessFavorites lessFavorites) {
		if(lessFavorites==null) {
			return null;
		}
		String objectId = lessFavorites.getObjectId();
		Integer resType = lessFavorites.getResType();
		String entityId = resourceService.getEntityIdByObjectIdAndType(objectId, resType);
		
		String fileSuffix = resourceService.getFileSuffixByEntityId(entityId);
		String thumbnailUrl = lessFavorites.getThumbnailUrl();
		if(thumbnailUrl!=null && !"".equals(thumbnailUrl)) {
			thumbnailUrl = fileService.thumb2HttpUrl(thumbnailUrl);
		} else {
			thumbnailUrl = "";
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resUuid", lessFavorites.getResUuid());
		result.put("title", lessFavorites.getTitle());
		result.put("resType", resType);
		result.put("thumbnailUrl", thumbnailUrl);
		result.put("fileSuffix", fileSuffix);
		result.put("description", lessFavorites.getDescription());
		result.put("createdTime", lessFavorites.getCreatedTime());
		result.put("modifiedTime", lessFavorites.getModifiedTime());
		
		return result;
	}

	/**
	 * 资源下载
	 * 
	 * @param resUuid
	 * @param userId
	 * @return
	 */
	@Override
	public Object addDownload(String sign, String appKey, String timeStamp, String resUuid, Integer userId) {
		/** 创建资源操作记录 */
		ResourceVo resourceVo = resourceService.createResourceOperation(resUuid, userId, OperationType.DOWN);
		if (resourceVo == null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		/** 用户下载行为记录 */
		resourceService.createUserAction(resUuid, userId, ActionType.DONWLOAD);

		/** 更改资源下载记录 */
		Resource temp = new Resource();
		temp.setId(resourceVo.getId());
		temp.setDownloadCount(resourceVo.getDownloadCount() + 1);
		
		resourceService.modify(temp);

		return new ResponseVo<Object>("0", new HashMap<Object, Object>());
	}

	/**
	 * 收藏
	 * @param resUuid
	 * @param userId
	 * @return
	 */
	@Override
	public Object addFavorite(String sign, String appKey, String timeStamp, String resUuid, Integer userId) {
		AppEdition appEdition = appEditionService.findByAppKey(appKey);
		Integer appId = null;
		if (appEdition != null) {
			appId = appEdition.getAppId();
		}
		
		ResourceVo resourceVo = resourceService.findSubFiledByResUuid(resUuid);
		if (resourceVo == null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		FavoritesCondition favoritesCondition = new FavoritesCondition();
		favoritesCondition.setPosterId(userId);
		favoritesCondition.setResourceId(resourceVo.getId());
		List<Favorites> favList = favoritesService.findFavoritesByCondition(favoritesCondition);
		
		if(favList.size()!=0) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("资源已经被收藏，无法重复执行收藏操作");
			info.setDetail("资源已经被收藏，无法重复执行收藏操作");
			info.setParam("resUuid, userId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		
		resourceService.addFavorites(resUuid, userId, appId);

		/** 用户操作资源记录 */
		resourceService.createResourceOperation(resUuid, userId, OperationType.FAV);
		/** 用户收藏行为记录 */
		resourceService.createUserAction(resUuid, userId, ActionType.FAV);

		return new ResponseVo<Object>("0", new HashMap<Object, Object>());
	}

	/**
	 * 取消收藏
	 * @param resUuid
	 * @param userId
	 * @return
	 */
	@Override
	public Object removeFavorite(String sign, String appKey, String timeStamp, String resUuid, Integer userId) {
		ResourceVo resourceVo = resourceService.findSubFiledByResUuid(resUuid);
		if (resourceVo == null) {
			ResponseInfo info = new ResponseInfo();
			info.setMsg("找不到数据");
			info.setDetail("找不到数据");
			info.setParam("resUuid");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}
		Favorites favorites = favoritesService.findUnique(null, userId, resourceVo.getId());
		if (favorites != null) {
			favoritesService.remove(favorites);
			
			Resource temp = new Resource();
			temp.setId(resourceVo.getId());
			temp.setFavCount(resourceVo.getFavCount() - 1);
			
			resourceService.modify(temp);
			resourceService.createUserAction(resUuid, userId, ActionType.CACELFAV);
		}

		return new ResponseVo<Object>("0", new HashMap<Object, Object>());
	}

	private Page getPage(Integer pageSize, Integer pageNumber) {
		Page page = new Page();
		if (pageSize != null) {
			page = new Page();
			page.setPageSize(pageSize);
		}

		if (pageNumber != null) {
			page.setCurrentPage(pageNumber);
		}
		return page;
	}

	private String compareType(Integer resType) {
		if (resType == ResourceType.MICRO) {
			return ObjectTypeContans.MICRO_TYPE;
		} else if (resType == ResourceType.LEARNING_DESIGN) {
			return ObjectTypeContans.DESINE_TYPE;
		} else if (resType == ResourceType.EXAM) {
			return ObjectTypeContans.EXAM_TYPE;
		} else if (resType == ResourceType.HOMEWORK) {
			return ObjectTypeContans.HOMEWORD_TYPE;
		} else if (resType == ResourceType.MATERIAL) {
			return ObjectTypeContans.METERIAL_TYPE;
		} else if (resType == ResourceType.TEACHING_PLAN) {
			return ObjectTypeContans.TEACHRING_PLAN_TYPE;
		} else {
			return ObjectTypeContans.OTHERS;
		}
	}
}
