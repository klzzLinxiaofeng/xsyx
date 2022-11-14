package platform.education.commonResource.web.resource.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.storage.Storage;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.ftlUtil.GeneratorHtml;
import platform.education.commonResource.web.common.vo.ResponseInfomation;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.OperationWeekcount;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceCount;
import platform.education.resource.model.ResourceDisplay;
import platform.education.resource.model.ResourceLatest;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceCountService;
import platform.education.resource.service.ResourceDisplayService;
import platform.education.resource.service.ResourceLatestService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.OperationWeekcountCondition;
import platform.education.resource.vo.ResourceCountCondition;
import platform.education.resource.vo.ResourceDisplayCondition;
import platform.education.resource.vo.ResourceLatestCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

@Controller
@RequestMapping(value = "/mmm")
public class ManualMicroController {
	@Autowired
    @Qualifier("storage")
    protected Storage storage;
	
	@Autowired
    @Qualifier("resourceLibraryService")
	private ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@Autowired
    @Qualifier("resourceService")
	private ResourceService resourceService;
	
	@Autowired
    @Qualifier("profileService")
    protected ProfileService profileService;
	
	@Autowired
	@Qualifier("resourceLatestService")
	private ResourceLatestService resourceLatestService;
	@Autowired
    @Qualifier("resourceCountService")
	private ResourceCountService ResourceCountService;
	
	@Autowired
	@Qualifier("resourceDisplayService")
	private ResourceDisplayService resourceDisplayService;
	
	@RequestMapping(value = "/micro")
	@ResponseBody
	public String micro(@RequestParam(value = "id", required = true) Integer id){
		try {
			GeneratorHtml cf = new GeneratorHtml();
			String templateFilePath = FtlContants.FTL_TEMPLATEPATH;
			String htmlFilePath = FtlContants.FTL_HTMLPATH;
			ResourceLibrary rl = this.resourceLibraryService.findResourceLibraryById(id);
			Integer relateAppId = rl.getAppId();
			Integer relateSchoolId = rl.getOwerId();
			String htmlFileName = "microIndex_"+ relateAppId +"_"+ relateSchoolId +"_"+ rl.getUuid() +".html";
			ResourceVoCondition condition = new ResourceVoCondition();
			List<ResourceVo> vos = new ArrayList<ResourceVo>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			/**
			 * 小学语文科目
			 */
			System.out.println("微课资源小学语文科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFourChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFiveChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosSixChinese", vos);
			System.out.println("微课资源小学语文科目生成结束");
			
			/**
			 * 初中语文科目
			 */
			System.out.println("微课资源初中语文科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleOneChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeChinese", vos);
			System.out.println("微课资源初中语文科目生成结束");
			/**
			 * 小学数学科目
			 */
			System.out.println("微课资源小学数学科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFourMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFiveMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosSixMath", vos);
			System.out.println("微课资源小学数学科目生成结束");
			/**
			 * 初中数学科目
			 */
			System.out.println("微课资源初中数学科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeMath", vos);
			System.out.println("微课资源初中数学科目生成结束");
			/**
			 * 小学英语科目
			 */
			System.out.println("微课资源小学英语科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFourEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFiveEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosSixEnglish", vos);
			System.out.println("微课资源小学英语科目生成结束");
			/**
			 * 初中英语科目
			 */
			System.out.println("微课资源初中英语科目生成中");
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeEnglish", vos);
			System.out.println("微课资源初中英语科目生成结束");
			/**
			 * 感兴趣的资源
			 */
			System.out.println("微课资源感兴趣的资源生成中");
			vos = getInterestMicro(relateAppId, relateSchoolId);
			data.put("vosInterestMicro", vos);
			String context = FtlContants.HTML_BASE_PATH;
			data.put("contextPath", context);
		    cf.geneHtmlFile(FtlContants.FTL_TEMPLATEMICRONAME, templateFilePath, data, htmlFilePath, htmlFileName);
			System.out.println("微课资源感兴趣的资源生成结束");
			
			
	    if(relateAppId == SysContants.SYSTEM_APP_ID&&relateSchoolId == SysContants.SYSTEM_OWNER_ID){
	        System.out.println("微课资源统计资源数目开始");
	 	    this.addResourceCount(ResourceType.MICRO,ResourceCountCondition.MICRO);
	 	    System.out.println("微课资源统计资源数目结束");
	 	    
	 	    System.out.println("微课资源最新四条记录统计开始");
	 	    addLatestPublicResource(ResourceType.MICRO);
	 	    System.out.println("微课资源最新四条记录统计结束");
	    }
	    System.out.println("微课资源更新操作表开始");	
		System.out.println("微课资源更新操作表结束");
	    System.out.println("微课资源清空周统计表开始");
	    System.out.println("微课资源清空周统计表结束");
	    System.out.println("微课资源处理成功");
	    
	    
	    
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
}
	
	
	/**
	 * 找出公共资源最后添加的四条，添加到资源最后更新表中
	 * @param resType 资源类型 0标示所有资源，1标示微课资源
	 */
	private void addLatestPublicResource(int resType){
		Page page = new Page();
		page.setPageSize(4);
		Order order = new Order();
		order.setProperty("id");
		order.setAscending(false);
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		
		 String libraryUUID = null;
		 
		
		 
		ResourceLibraryCondition resourceLibraryConditionTemp = new ResourceLibraryCondition();
		resourceLibraryConditionTemp.setAppId(appId);
		resourceLibraryConditionTemp.setOwerId(ownerId);
		List<ResourceLibrary> resourceLibraryTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
		if(resourceLibraryTemp != null&&!resourceLibraryTemp.isEmpty()){
			libraryUUID = resourceLibraryTemp.get(0).getUuid();
		}
		
		ResourceVoCondition resourceVoCondition = new ResourceVoCondition();
		resourceVoCondition.setLibraryId(libraryUUID);
		if(resType == 0){
			resourceVoCondition.setResType(null);
		}else{
			//resourceVoCondition.setResType(ResourceType.MICRO);
			resourceVoCondition.setResType(resType);
		}
		
		resourceVoCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
		
		List<Resource> ResourceList = new ArrayList<Resource>();
		ResourceList = this.resourceService.findLatestResourceByCondition(resourceVoCondition,page,order);
		
		for (Resource resource : ResourceList) {
			ResourceLatest resourceLatest = new ResourceLatest();
			BeanUtils.copyProperties(resource, resourceLatest);
			resourceLatest.setId(null);
			if(resType == 0){
				resourceLatest.setResType(ResourceLatestCondition.ALL);
				
			}else{
				resourceLatest.setResType(resType);
			}
			this.resourceLatestService.add(resourceLatest);
			System.out.println(resourceLatest.getId()+","+resourceLatest.getTitle()+","+resourceLatest.getUserName());
		}
		
		
	}
	
	/**
	 * 添加公共资源统计数据
	 * @param libraryUuid
	 * @param resType
	 * @param count
	 */
	private void addResourceCount(int resType,int countResType){
		
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		 String libraryUUID = null;
		 
		 ResourceCountCondition resourceCountCondition = new ResourceCountCondition();
		 resourceCountCondition.setResType(countResType);
		 resourceCountCondition.setLibraryUuid(libraryUUID);
	    List<ResourceCount> resourceCountList =  this.ResourceCountService.findResourceCountByCondition(resourceCountCondition);
	    for (ResourceCount resourceCount : resourceCountList) {
			this.ResourceCountService.remove(resourceCount);
		}
	    
	   
		ResourceLibraryCondition resourceLibraryConditionTemp = new ResourceLibraryCondition();
		resourceLibraryConditionTemp.setAppId(appId);
		resourceLibraryConditionTemp.setOwerId(ownerId);
		List<ResourceLibrary> resourceLibraryTemp = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryConditionTemp);
		if(resourceLibraryTemp != null&&!resourceLibraryTemp.isEmpty()){
			libraryUUID = resourceLibraryTemp.get(0).getUuid();
		}
		
		ResourceVoCondition resourceVoCondition = new ResourceVoCondition();
		resourceVoCondition.setLibraryId(libraryUUID);
		//resourceVoCondition.setResType(ResourceType.MICRO);
		//resourceVoCondition.setResType(null);
		resourceVoCondition.setResType(resType);
		resourceVoCondition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
		Long count = this.resourceService.countAllRes(resourceVoCondition);
	    
	    
	    ResourceCount resourceCount = new ResourceCount();
	    resourceCount.setLibraryUuid(libraryUUID);
	    resourceCount.setResType(countResType);
	    resourceCount.setCount(count.intValue());
	    resourceCount.setVerify("0");
	    this.ResourceCountService.add(resourceCount);
	}
	private List<ResourceVo> getResourceByGradeCode(String gradeCode, String subjectCode,Integer relateAppId,Integer relateSchoolId) {
		Page page = new Page();
		Order order = new Order();
		ResourceVoCondition condition = new ResourceVoCondition();
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
		condition.setGradeCode(gradeCode);
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		condition.setSubjectCode(subjectCode);
		condition.setResType(ResourceType.MICRO);
		order.setAscending(false);
		order.setProperty("create_date");
		page.setPageSize(8);// 页面拿数据几条数据
		//List<ResourceVo> vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		List<ResourceVo> vos = this.getResourceVo(condition, page, order);
		String imgSrc = "";
		String commonImg = "/res/images/ckk.png";
		StringBuffer sbf = new StringBuffer();
        String prefixPath = storage.spaceName();
        String httpPrefix = storage.httpPrefix();
        sbf.append(httpPrefix);
        sbf.append("/");
        sbf.append(prefixPath);
        String httpUrl = sbf.toString();
		for(ResourceVo vo :vos){
			String url = vo.getThumbnailUrl();
			if(url==null||url.equals("")){
				ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
				String path = context.getContextPath();
				vo.setThumbnailUrl(path+"/res/images/video.png");
			}else{
				vo.setThumbnailUrl(httpUrl+url);
			}
			if(vo.getUserId()!=null){
				imgSrc = getImgSrc(vo.getUserId());
				vo.setImgSrc(imgSrc);
			}else{
				ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
				imgSrc = context.getContextPath() + commonImg;
				vo.setUserName("参考库");
				vo.setImgSrc(imgSrc);
			}
		}
		return vos;
	}
	
	private List<ResourceVo> getResourceVo(ResourceVoCondition resourceVoCondition, Page page,Order order){
		ResourceDisplayCondition condition = new ResourceDisplayCondition();
		condition.setGradeCode(resourceVoCondition.getGradeCode());
		condition.setVerify("0");
		condition.setSubjectCode(resourceVoCondition.getSubjectCode());
		condition.setResType(ResourceType.MICRO);
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		List<ResourceDisplay> displayList = this.resourceDisplayService.findResourceDisplayByCondition(condition, page, order);
		for (ResourceDisplay resourceDisplay : displayList) {
			ResourceVo vo = new ResourceVo();
			vo.setId(resourceDisplay.getResourceId());
			vo.setThumbnailUrl(resourceDisplay.getThumbnailUrl());
			vo.setTitle(resourceDisplay.getTitle());
			vo.setImgSrc(resourceDisplay.getUserId()+"");
			vo.setUserName(resourceDisplay.getUserName());
			vo.setCreateDate(resourceDisplay.getCreateDate());
			vos.add(vo);
		}
		
		return vos;
	}
	private List<OperationWeekcount> getResourceByOperation(Integer operationType,Integer relateAppId,Integer relateSchoolId,Integer resourceType){
		Page page = new Page();
		Order order = new Order();
		ResourceLibrary resourceLibrary = null;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();;
		if(relateAppId!=null&&relateSchoolId!=null){
        	resourceLibraryCondition.setAppId(relateAppId);
        	resourceLibraryCondition.setOwerId(relateAppId);
        	List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
        	if(resourceLibraryList != null&&resourceLibraryList.size()>0){//如果存在取对应的uui
        		resourceLibrary = resourceLibraryList.get(0);
        	}
		}
		OperationWeekcountCondition operationWeekcountCondition = new OperationWeekcountCondition();
		if(resourceLibrary!=null){
			operationWeekcountCondition.setLibraryUuid(resourceLibrary.getUuid());
		}
		resourceLibraryCondition.setAppId(SysContants.SYSTEM_APP_ID);
		resourceLibraryCondition.setOwerId(SysContants.SYSTEM_OWNER_ID);
		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
    	if(resourceLibraryList != null&&resourceLibraryList.size()>0){//如果存在取对应的uui
    		resourceLibrary = resourceLibraryList.get(0);
    	}
    	if(resourceLibrary!=null){
    		operationWeekcountCondition.setLibraryPubUuid(resourceLibrary.getUuid());
    	}
		order.setAscending(false);
		order.setProperty("count");
		page.setPageSize(10);// 页面拿数据几条数据
		List<OperationWeekcount> vos = null;
		operationWeekcountCondition.setOperationType(operationType);
		operationWeekcountCondition.setResourceType(resourceType);
		if(operationWeekcountCondition.getLibraryPubUuid()!=null&&operationWeekcountCondition.getLibraryUuid()!=null){
			vos = this.operationWeekcountService.findOperationWeekcountByMoreCondition(operationWeekcountCondition, page, order);
			return vos;
		}
		return new ArrayList<OperationWeekcount>();
	}
	
	private List<OperationWeekcount> getResourceByOperationCommon(String colum){
		Page page = new Page();
		Order order = new Order();
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		ResourceVoCondition condition = new ResourceVoCondition();
		condition.setRelateAppId(appId);
		condition.setRelateSchoolId(ownerId);
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setResType(ResourceType.MICRO);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		page.setPageSize(10);
		order.setAscending(false);
		order.setProperty(colum);
		
		//List<ResourceVo> rVo = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		List<ResourceVo> rVo = this.getResourceVo(condition, page, order);
		List<OperationWeekcount> operationVos = new ArrayList<OperationWeekcount>();
		for(ResourceVo vo : rVo){
			if(vo!=null){
				OperationWeekcount operationWeekcount = new OperationWeekcount();
				operationWeekcount.setResourceId(vo.getId());
				operationWeekcount.setResourceTitle(vo.getTitle());
				operationVos.add(operationWeekcount);
			}
		}
		return operationVos;
	}
	
	private void addToOperationWeekCount() {
		this.resourceOperationService.createOperationWeekCountByType(OperationType.CLICK);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.LIKE);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.FAV);
		this.resourceOperationService.createOperationWeekCountByType(OperationType.DOWN);
	}
	
	/**
	 * 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @return
	 */
	private String getImgSrc(Integer userId){
		String outPutHtml = "";
		String def = "/res/images/no_pic.jpg";
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		}
		if ("".equals(outPutHtml)) {
			ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
			outPutHtml = context.getContextPath() + def;
		}
		return outPutHtml;
	}
	private List<ResourceVo> getInterestMicro(Integer relateAppId,Integer relateSchoolId){
		Page page = new Page();
		Order order = new Order();
		ResourceVoCondition condition = new ResourceVoCondition();
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
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(condition.VERYIFY_SUCCESS);
		condition.setResType(ResourceType.MICRO);
		order.setAscending(false);
		order.setProperty("create_date");
		page.setPageSize(7);// 页面拿数据几条数据
		List<ResourceVo> vos = this.getResourceVo(condition, page, order);
		//List<ResourceVo> vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		String imgSrc = "";
		String commonImg = "/res/images/ckk.png";
		StringBuffer sbf = new StringBuffer();
        String prefixPath = storage.spaceName();
        String httpPrefix = storage.httpPrefix();
        sbf.append(httpPrefix);
        sbf.append("/");
        sbf.append(prefixPath);
        String httpUrl = sbf.toString();
		for(ResourceVo vo :vos){
			String url = vo.getThumbnailUrl();
			if(url==null||url.equals("")){
				ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
				String path = context.getContextPath();
				vo.setThumbnailUrl(path+"/res/images/video.png");
			}else{
				vo.setThumbnailUrl(httpUrl+url);
			}
			if(vo.getUserId()!=null){
				imgSrc = getImgSrc(vo.getUserId());
				vo.setImgSrc(imgSrc);
			}else{
				ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
				imgSrc = context.getContextPath() + commonImg;
				vo.setUserName("参考库");
				vo.setImgSrc(imgSrc);
			}
		}
		return vos;
	}	
}
