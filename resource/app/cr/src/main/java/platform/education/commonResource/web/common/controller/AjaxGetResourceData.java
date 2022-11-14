package platform.education.commonResource.web.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.RedisKey;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.SessionManager;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.HeadPic;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceCount;
import platform.education.resource.model.ResourceLatest;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceCountService;
import platform.education.resource.service.ResourceLatestService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.CommonResource;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceCountCondition;
import platform.education.resource.vo.ResourceLatestCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

/**
 * @function 该类中，主要定义一些ajax方法，主要为了获得html静态页中获得动态数据的方法
 * @author hmzhang
 * @date 2015年10月12日
 *
 */
@Controller
@RequestMapping("/ajax/resource")
public class AjaxGetResourceData {
	
	
    @Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
    @Autowired
    @Qualifier("teacherService")
    protected TeacherService teacherService;
    @Autowired
    @Qualifier("profileService")
    protected ProfileService profileService;
    
    @Autowired
	@Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("resourceCountService")
	protected ResourceCountService resourceCountService;
	
	@Autowired
	@Qualifier("resourceLatestService")
	private ResourceLatestService resourceLatestService;
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("baseRedisCache")
	private BaseRedisCache<Object> baseRedisCache;
	
    /**
	 * 获取最近上传的资源
	 * @param user
	 * @param request
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/getAjaxNearResource", method = RequestMethod.POST)
	@ResponseBody
	public List<ResourceVo> getAjaxNearResource(
			@CurrentUser UserInfo user,
			HttpServletRequest request, 
			@RequestParam(value = "resType", required = true) int resType,
			Page page, Order order) {
		
		
		page.setPageSize(4);
		order.setProperty("id");
		order.setAscending(false);
		
		
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		String libraryPublicUUID = null;
		
		List<ResourceLatest> resourceLatestList = new ArrayList<ResourceLatest>();
		ResourceLibraryCondition resourceLibraryConditionTemp = new ResourceLibraryCondition();
		resourceLibraryConditionTemp.setAppId(appId);
		resourceLibraryConditionTemp.setOwerId(ownerId);
		List<ResourceLibrary> resourceLibraryPublicTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
		if(resourceLibraryPublicTemp != null&&!resourceLibraryPublicTemp.isEmpty()){
			libraryPublicUUID = resourceLibraryPublicTemp.get(0).getUuid();
		}
		
		if(relateAppId != null&&relateSchoolId != null){
			String librarySelfUUID = null;
			resourceLibraryConditionTemp.setAppId(relateAppId);
			resourceLibraryConditionTemp.setOwerId(relateSchoolId);
			List<ResourceLibrary> resourceLibrarySelfTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
			if(resourceLibrarySelfTemp != null&&!resourceLibrarySelfTemp.isEmpty()){
				librarySelfUUID = resourceLibrarySelfTemp.get(0).getUuid();
			}
			
			ResourceVoCondition resourceVoCondition = new ResourceVoCondition();
			resourceVoCondition.setLibraryId(librarySelfUUID);
			
			if(resType == 0){
				resourceVoCondition.setResType(null);
			}else{
				resourceVoCondition.setResType(resType);
			}
			
			resourceVoCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
			List<Resource> vosNearResourceList = new ArrayList<Resource>();
			
			vosNearResourceList = this.resourceService.findLatestResourceByCondition(resourceVoCondition,page,order);
			
			if(vosNearResourceList != null){
				if(vosNearResourceList.isEmpty()){
					ResourceLatestCondition resourceLatestCondition = new ResourceLatestCondition();
					resourceLatestCondition.setLibraryId(libraryPublicUUID);
					if(resType == 0){
						resourceLatestCondition.setResType(ResourceLatestCondition.ALL);
					}else{
						resourceLatestCondition.setResType(resType);
					}
					resourceLatestCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
					resourceLatestList = this.resourceLatestService.findResourceLatestByCondition(resourceLatestCondition,page,order);
				}else if(vosNearResourceList.size()<4){
					ResourceLatestCondition resourceLatestCondition = new ResourceLatestCondition();
					resourceLatestCondition.setLibraryId(libraryPublicUUID);
					if(resType == 0){
						resourceLatestCondition.setResType(ResourceLatestCondition.ALL);
					}else{
						resourceLatestCondition.setResType(resType);
					}
					resourceLatestCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
					page.setPageSize(4-vosNearResourceList.size());
					resourceLatestList = this.resourceLatestService.findResourceLatestByCondition(resourceLatestCondition,page,order);
					for (Resource resource : vosNearResourceList) {
						ResourceLatest resourceLatest = new ResourceLatest();
						BeanUtils.copyProperties(resource, resourceLatest);
						resourceLatestList.add(resourceLatest);
					}
				}else{
					for (Resource resource : vosNearResourceList) {
						ResourceLatest resourceLatest = new ResourceLatest();
						BeanUtils.copyProperties(resource, resourceLatest);
						resourceLatestList.add(resourceLatest);
					}
				}
			}else{
				ResourceLatestCondition resourceLatestCondition = new ResourceLatestCondition();
				resourceLatestCondition.setLibraryId(libraryPublicUUID);
				if(resType == 0){
					resourceLatestCondition.setResType(ResourceLatestCondition.ALL);
				}else{
					resourceLatestCondition.setResType(resType);
				}
				resourceLatestCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
				resourceLatestList = this.resourceLatestService.findResourceLatestByCondition(resourceLatestCondition,page,order);
			}
			
		}else{
			
			
			ResourceLatestCondition resourceLatestCondition = new ResourceLatestCondition();
			resourceLatestCondition.setLibraryId(libraryPublicUUID);
			if(resType == 0){
				resourceLatestCondition.setResType(ResourceLatestCondition.ALL);
			}else{
				resourceLatestCondition.setResType(resType);
			}
			
			resourceLatestCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
			resourceLatestList = this.resourceLatestService.findResourceLatestByCondition(resourceLatestCondition,page,order);
			
		}
		
		
		List<ResourceVo> vosNearResourceResult = new ArrayList<ResourceVo>();
		for (ResourceLatest resourceLatest : resourceLatestList) {
			ResourceVo resourceVo = new ResourceVo();
			BeanUtils.copyProperties(resourceLatest, resourceVo);
			vosNearResourceResult.add(resourceVo);
		}
		
		
		String imgSrc = "";
		String commonImg = "/res/images/ckk.png";
		for(ResourceVo vo : vosNearResourceResult){
			if(vo.getUserId()!=null){
				imgSrc = getImgSrc(vo.getUserId(),request);
				TeacherCondition condition=new TeacherCondition();
				condition.setUserId(vo.getUserId());
				
				List<Teacher> tlist=this.teacherService.findTeacherByCondition(condition, null, null);
				if(tlist!=null&&tlist.size()>0){
					vo.setUserName(tlist.get(0).getName());
				}else{
					vo.setUserName("");
				}
				
				vo.setImgSrc(imgSrc);
			}else{
				imgSrc =  request.getContextPath()+commonImg;
				vo.setUserName("参考库");
				vo.setImgSrc(imgSrc);
			}
		}
		return vosNearResourceResult;
	}

	
	/**
	 * 获取登陆用户的名字和图片 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAjaxHeadPic", method = RequestMethod.POST)
	@ResponseBody
	public HeadPic getAjaxHeadPic(@CurrentUser UserInfo user,HttpServletRequest request){
		HeadPic pic = new HeadPic();
		Integer userId = user.getUserId();
		String imgSrc = "";
		imgSrc = getImgSrc(userId,request);
		String userName = user.getRealName();
		pic.setImgSrc(imgSrc);
		pic.setUserName(userName);
		return pic;
	}
	
	@RequestMapping(value = "/getAjaxResCount", method = RequestMethod.POST)
	@ResponseBody
	public Long getAjaxResCount(HttpServletRequest request){
		
		Long countSelf = 0L;
		int countPublic = 0;
		Long count = 0L;
		
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		
		//查询自己学校资源的数目
				if(relateAppId == null||relateSchoolId==null){
					
				}else{

					ResourceLibraryCondition resourceLibraryConditionTemp = new ResourceLibraryCondition();
					resourceLibraryConditionTemp.setAppId(relateAppId);
					resourceLibraryConditionTemp.setOwerId(relateSchoolId);
					List<ResourceLibrary> resourceLibraryTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
					if(resourceLibraryTemp != null&&!resourceLibraryTemp.isEmpty()){
						condition.setLibraryId(resourceLibraryTemp.get(0).getUuid());
						condition.setVerify(condition.VERYIFY_SUCCESS);
						countSelf = this.resourceService.countAllRes(condition);
						condition = null;
						System.out.println(countSelf);
					}
				
				}
				
				//查询公共资源的数目
				ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
				resourceLibraryCondition.setAppId(appId);
				resourceLibraryCondition.setOwerId(ownerId);
				List<ResourceLibrary> resourceLibrary = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
				if(resourceLibrary != null&&resourceLibrary.size()>0){
					String libraryUuid = resourceLibrary.get(0).getUuid();
					ResourceCountCondition resourceCountCondition = new ResourceCountCondition();
					resourceCountCondition.setLibraryUuid(libraryUuid);
					resourceCountCondition.setResType(ResourceCountCondition.ALL);
					List<ResourceCount> resourceCount = this.resourceCountService.findResourceCountByCondition(resourceCountCondition);
					if(resourceCount != null&&resourceCount.size()>0){
						countPublic = resourceCount.get(0).getCount();
						count = countSelf+countPublic;
						System.out.println(countPublic);
					}
				}
		
		return count;
	}
	
	@RequestMapping(value = "/getAjaxStageCode", method = RequestMethod.POST)
	@ResponseBody
	public String[] getAjaxStageCode(HttpServletRequest request,@CurrentUser UserInfo user){
		String stageCode = user.getStageCode();
		if(stageCode==null) {
			stageCode="2,3,4";
		}
		String[] code = stageCode.split(";");
		return code;
	}
	
	@RequestMapping(value = "/getAjaxMicCount", method = RequestMethod.POST)
	@ResponseBody
	public Long getAjaxMicCount(HttpServletRequest request){
		
		Long countSelf = 0L;
		int countPublic = 0;
		Long count = 0L;
		
		//设置查询条件
		ResourceVoCondition condition = new ResourceVoCondition();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		if (relateAppId != null) {
			condition.setRelateAppId(relateAppId);
		} else {
			condition.setRelateAppId(appId);
		}
		if (relateSchoolId != null) {
			condition.setRelateSchoolId(relateSchoolId);
		} else {
			condition.setRelateSchoolId(ownerId);
		}
		
		
		//查询自己学校资源的数目
				if(relateAppId == null||relateSchoolId == null){
					
				}else{
					ResourceLibraryCondition resourceLibraryConditionTemp = new ResourceLibraryCondition();
					resourceLibraryConditionTemp.setAppId(relateAppId);
					resourceLibraryConditionTemp.setOwerId(relateSchoolId);
					List<ResourceLibrary> resourceLibraryTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
					if(resourceLibraryTemp != null&&!resourceLibraryTemp.isEmpty()){
						
						condition.setLibraryId(resourceLibraryTemp.get(0).getUuid());
						condition.setVerify(condition.VERYIFY_SUCCESS);
						condition.setResType(ResourceCountCondition.MICRO);
						countSelf = this.resourceService.countAllRes(condition);
						condition = null;
						System.out.println(countSelf);
					}
				}
				
				//查询公共资源的数目
				ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
				resourceLibraryCondition.setAppId(appId);
				resourceLibraryCondition.setOwerId(ownerId);
				List<ResourceLibrary> resourceLibrary = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
				if(resourceLibrary != null&&resourceLibrary.size()>0){
					String libraryUuid = resourceLibrary.get(0).getUuid();
					ResourceCountCondition resourceCountCondition = new ResourceCountCondition();
					resourceCountCondition.setLibraryUuid(libraryUuid);
					resourceCountCondition.setResType(ResourceCountCondition.MICRO);
					List<ResourceCount> resourceCount = this.resourceCountService.findResourceCountByCondition(resourceCountCondition);
					if(resourceCount != null&&resourceCount.size()>0){
						//countPublic = resourceCount.get(0).getCount();
						countPublic = resourceCount.get(0).getCount();
						count = countSelf+countPublic;
						
						count = countSelf+countPublic;
						System.out.println(countPublic);
					
					}
				}
		
		return count;
	}
	
	/**
	 * @function 获取资源浏览排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxResClick", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxResClick(HttpServletRequest request){
		return getRes(request,OperationType.CLICK);
	}
	
	/**
	 * @function 获取资源点赞排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxResLike", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxResLike(HttpServletRequest request){
		return getRes(request,OperationType.LIKE);
	}
	
	/**
	 * @function 获取资源收藏排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxResFav", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxResFav(HttpServletRequest request){
		return getRes(request,OperationType.FAV);
	}
	
	/**
	 * @function 获取资源下载排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxResDown", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxResDown(HttpServletRequest request){
		return getRes(request,OperationType.DOWN);
	}
	
	/**
	 * @function 获取微课浏览排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxMicroClick", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxMicroClick(HttpServletRequest request){
		return getMicro(request,OperationType.CLICK);
	}
	
	/**
	 * @function 获取微课点赞排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxMicroLike", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxMicroLike(HttpServletRequest request){
		return getMicro(request,OperationType.LIKE);
	}
	
	/**
	 * @function 获取微课收藏排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxMicroFav", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxMicroFav(HttpServletRequest request){
		return getMicro(request,OperationType.FAV);
	}
	
	/**
	 * @function 获取微课下载排行榜
	 * @param request
	 * @return 
	 * @author hmzhang
	 * @date 2016年1月21日
	 */
	@RequestMapping(value = "/getAjaxMicroDown", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonResource> getAjaxMicroDown(HttpServletRequest request){
		return getMicro(request,OperationType.DOWN);
	}
	
	/**
	 * 获取资源评分排行榜
	 * @return
	 */
	@RequestMapping(value = "/getAjaxScoreOrder", method = RequestMethod.POST)
	@ResponseBody
    public Object getScoreOrderList(){
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		
		ResourceLibrary library = resourceLibraryService.findByAppIdAndOwnerId(appId, ownerId);
		if(library==null) {
			return new ArrayList<Resource>();
		}
		
		List<Resource> result = (List<Resource>) baseRedisCache.getCacheObject(RedisKey.CR_SCORE_ORDER);
		if(result==null || result.size()==0) {
			result = resourceService.findScoreOrderList(library.getUuid(), ResourceVoCondition.VERYIFY_SUCCESS);
			baseRedisCache.setCacheObject(RedisKey.CR_SCORE_ORDER, result);
		}
		
		return result;
    }
	
	/**
	 * @function 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @author hmzhang
	 * @return
	 * @date 2015年10月13日
	 */
	private String getImgSrc(Integer userId,HttpServletRequest request){
		String outPutHtml = "";
		String def = "/res/images/no_pic.jpg";
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		}
		if ("".equals(outPutHtml)) {
			outPutHtml = request.getContextPath() + def;
		}
		return outPutHtml;
	}
	
	
    protected Integer getRelateApp(HttpServletRequest request) {
        String relateAppId = request.getParameter("relateAppId");
        Integer returnValue;
        if (relateAppId != null && !"".equals(relateAppId)) {
            Integer intValue = Integer.parseInt(relateAppId);
            SessionManager.setRelateAppId(intValue);
            returnValue = intValue;
        } else {
            returnValue =  SessionManager.getRelateAppId();
        }
        return returnValue;
    }
    
    protected Integer getRelateSchool(HttpServletRequest request) {
        String relateSchoolId = request.getParameter("relateSchoolId");
        Integer returnValue;
        if (relateSchoolId != null && !"".equals(relateSchoolId)) {
            Integer intValue = Integer.parseInt(relateSchoolId);
            SessionManager.setRelateSchoolId(intValue);
            returnValue = intValue;
        } else {
            return SessionManager.getRelateSchoolId();
        }
        return returnValue;
    }
    
    /**
     * @function 公共方法获取排行榜
     * @param request
     * @param OperationType
     * @return
     * @author hmzhang
     * @date 2016年1月21日
     */
    private List<CommonResource> getRes(HttpServletRequest request,Integer OperationType){
    	Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		String libraryUuid = null;
		List<CommonResource> resList = null;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		if(relateAppId != null&&relateSchoolId != null){
			resourceLibraryCondition.setAppId(relateAppId);
			resourceLibraryCondition.setOwerId(relateSchoolId);
			List<ResourceLibrary> resourceLibrary = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
			if(resourceLibrary != null&&!resourceLibrary.isEmpty()){
				libraryUuid = resourceLibrary.get(0).getUuid();
			}
			/**
			 * 资源
			 */
			resList = this.operationWeekcountService.findRes(libraryUuid, OperationType);
		}
		return resList;
    }
    
    /**
     * @function 公共方法，获取微课的排行榜
     * @param request
     * @param OperationType
     * @author hmzhang
     * @return
     * @date 2016年1月21日
     * 
     */
    private List<CommonResource> getMicro(HttpServletRequest request,Integer OperationType){
    	Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		String libraryUuid = null;
		List<CommonResource> resList = null;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		if(relateAppId != null&&relateSchoolId != null){
			resourceLibraryCondition.setAppId(relateAppId);
			resourceLibraryCondition.setOwerId(relateSchoolId);
			List<ResourceLibrary> resourceLibrary = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
			if(resourceLibrary != null&&!resourceLibrary.isEmpty()){
				libraryUuid = resourceLibrary.get(0).getUuid();
			}
			/**
			 * 微课
			 */
			resList = this.operationWeekcountService.findMicro(libraryUuid, OperationType);
		}
		return resList;
    }
    
}
