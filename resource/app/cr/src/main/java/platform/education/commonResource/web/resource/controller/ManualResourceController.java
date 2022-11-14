package platform.education.commonResource.web.resource.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.ftlUtil.GeneratorHtml;
import platform.education.commonResource.web.common.vo.ResponseInfomation;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.*;
import platform.education.resource.service.*;
import platform.education.resource.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/rrr")
public class ManualResourceController extends BaseController{
	@Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
	
	@Autowired
	@Qualifier("resourceLatestService")
	private ResourceLatestService resourceLatestService;
	
	@Autowired
    @Qualifier("resourceCountService")
	private ResourceCountService ResourceCountService;
	
	@Autowired
	@Qualifier("resourceDisplayService")
	private ResourceDisplayService resourceDisplayService;
	private final static String viewBasePath = "/resource";
	@RequestMapping(value = "/choice")
	public ModelAndView ftl(Model model){
		List<ResourceLibrary> rlList = new ArrayList<ResourceLibrary>();
		 Integer ownerId = SysContants.SYSTEM_OWNER_ID;
	        Integer appId = SysContants.SYSTEM_APP_ID;
		ResourceLibraryCondition condition=new ResourceLibraryCondition();
		
       	condition.setOwerId(ownerId);
        condition.setAppId(appId);
		rlList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
		model.addAttribute("rlList",rlList);
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/resourceOfSchool")
	public String resource_school(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page,
	        @CurrentUser UserInfo user) {
		ResourceVoCondition condition = new ResourceVoCondition();
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		Integer relateAppId=1;
		Integer relateSchoolId=user.getSchoolId();
		/**
		 * 小学语文科目
		 */
		System.out.println("资源小学语文科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosThreeChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosFourChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosFiveChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosSixChinese", vos);
		System.out.println("资源小学语文科目统计结束");
		/**
		 * 初中语文科目
		 */
		System.out.println("资源初中语文科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeChinese", vos);
		System.out.println("资源初中语文科目统计结束");
		/**
		 * 小学数学科目
		 */
		System.out.println("资源小学数学科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosThreeMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosFourMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosFiveMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosSixMath", vos);
		System.out.println("资源小学数学科目统计结束");
		/**
		 * 初中数学科目
		 */
		System.out.println("资源初中数学科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeMath", vos);
		System.out.println("资源初中数学科目统计结束");
		/**
		 * 小学英语科目
		 */
		System.out.println("资源小学英语科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosThreeEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosFourEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosFiveEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosSixEnglish", vos);
		System.out.println("资源小学英语科目统计结束");
		/**
		 * 初中英语科目
		 */
		System.out.println("资源 初中英语科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleOneEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeEnglish", vos);
		System.out.println("资源 初中英语科目统计结束");
		/**
		 * 语文精品推荐
		 */
		System.out.println("资源 初中英语科目统计开始");
		vos = getResourceBySubject(condition.SUBJECT_CHINESE,relateAppId, relateSchoolId);
		data.put("vosChineseRecommend", vos);
		System.out.println("资源 初中英语科目小学英语科目统计结束");
		/**
		 * 数学精品推荐
		 */
		System.out.println("资源数学精品推荐统计开始");
		vos = getResourceBySubject(condition.SUBJECT_MATH,relateAppId, relateSchoolId);
		data.put("vosMathRecommend", vos);
		System.out.println("资源数学精品推荐统计结束");
		/**
		 * 英语精品推荐
		 */
		System.out.println("资源英语精品推荐统计开始");
		vos = getResourceBySubject(condition.SUBJECT_ENGLISH,relateAppId, relateSchoolId);
		data.put("vosEnglishRecommend", vos);
		System.out.println("资源英语精品推荐统计结束");
		System.out.println("资源收藏统计开始");
		String context = FtlContants.HTML_BASE_PATH;
		data.put("contextPath", context);
		
		
		request.setAttribute("data", data);
		 return viewBasePath +"/resource_school";
	 }
	@RequestMapping(value = "/resource")
	@ResponseBody
	public String micro(@RequestParam(value = "id", required = true) Integer id){
		try {
		GeneratorHtml cf = new GeneratorHtml();
		String templateFilePath = FtlContants.FTL_TEMPLATEPATH;
		String htmlFilePath = FtlContants.FTL_HTMLPATH;
		ResourceLibrary rl = this.resourceLibraryService.findResourceLibraryById(id);
		Integer relateAppId = rl.getAppId();
		Integer relateSchoolId = rl.getOwerId();
		String htmlFileName = "index_"+ relateAppId +"_"+ relateSchoolId +"_"+ rl.getUuid() +".html";
		ResourceVoCondition condition = new ResourceVoCondition();
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		/**
		 * 小学语文科目
		 */
		System.out.println("资源小学语文科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosThreeChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosFourChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosFiveChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosSixChinese", vos);
		System.out.println("资源小学语文科目统计结束");
		/**
		 * 初中语文科目
		 */
		System.out.println("资源初中语文科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleOneChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoChinese", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeChinese", vos);
		System.out.println("资源初中语文科目统计结束");
		/**
		 * 小学数学科目
		 */
		System.out.println("资源小学数学科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosThreeMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosFourMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosFiveMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosSixMath", vos);
		System.out.println("资源小学数学科目统计结束");
		/**
		 * 初中数学科目
		 */
		System.out.println("资源初中数学科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleOneMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoMath", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeMath", vos);
		System.out.println("资源初中数学科目统计结束");
		/**
		 * 小学英语科目
		 */
		System.out.println("资源小学英语科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosThreeEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosFourEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosFiveEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosSixEnglish", vos);
		System.out.println("资源小学英语科目统计结束");
		/**
		 * 初中英语科目
		 */
		System.out.println("资源 初中英语科目统计开始");
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleOneEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleTwoEnglish", vos);
		vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
		data.put("vosMiddleThreeEnglish", vos);
		System.out.println("资源 初中英语科目统计结束");
		/**
		 * 语文精品推荐
		 */
		System.out.println("资源 初中英语科目统计开始");
		vos = getResourceBySubject(condition.SUBJECT_CHINESE,relateAppId, relateSchoolId);
		data.put("vosChineseRecommend", vos);
		System.out.println("资源 初中英语科目小学英语科目统计结束");
		/**
		 * 数学精品推荐
		 */
		System.out.println("资源数学精品推荐统计开始");
		vos = getResourceBySubject(condition.SUBJECT_MATH,relateAppId, relateSchoolId);
		data.put("vosMathRecommend", vos);
		System.out.println("资源数学精品推荐统计结束");
		/**
		 * 英语精品推荐
		 */
		System.out.println("资源英语精品推荐统计开始");
		vos = getResourceBySubject(condition.SUBJECT_ENGLISH,relateAppId, relateSchoolId);
		data.put("vosEnglishRecommend", vos);
		System.out.println("资源英语精品推荐统计结束");
		System.out.println("资源收藏统计开始");
		String context = FtlContants.HTML_BASE_PATH;
		data.put("contextPath", context);
	    cf.geneHtmlFile(FtlContants.FTL_TEMPLATERESOURCENAME, templateFilePath, data, htmlFilePath, htmlFileName);
		System.out.println("资源收藏统计结束");
		
		
		
	  if(relateAppId == SysContants.SYSTEM_APP_ID&&relateSchoolId == SysContants.SYSTEM_OWNER_ID){
	    System.out.println("学科资源统计资源数目开始");
	    this.addResourceCount(0,ResourceCountCondition.ALL);
	    System.out.println("学科资源统计资源数目结束");
	    
	    System.out.println("学科资源最新四条记录统计开始");
	    addLatestPublicResource(0);
	    System.out.println("学科资源最新四条记录统计结束");
	   
		 }
	  
	  System.out.println("学科资源更新操作表开始");
		System.out.println("学科资源更新操作表结束");
		 System.out.println("学科资源清空周统计表开始");
		System.out.println("学科资源清空周统计表结束");
	  System.out.println("学科资源处理成功");
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
				//ResourceCountCondition
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
	 * @param resType 资源类型 0标示所有资源，1标示微课资源
	 * @param countResType 资源类型 99标示所有资源，1标示微课资源
	 * 
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
		if(resType == 0){
			resourceVoCondition.setResType(null);
		}else{
			resourceVoCondition.setResType(resType);
		}
		
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
	order.setAscending(false);
	order.setProperty("create_date");
	page.setPageSize(7);// 页面拿数据几条数据
	List<ResourceVo> vos = new ArrayList<ResourceVo>();
	//vos = this.getResourceVo(condition, page, order);
	condition.setResType(ResourceType.LEARNING_DESIGN);
	//List<ResourceVo> kjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	List<ResourceVo> kjvos =  this.getResourceVo(condition, page, order);
	condition.setResType(ResourceType.HOMEWORK);
	//List<ResourceVo> zyvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	List<ResourceVo> zyvos =  this.getResourceVo(condition, page, order);
	condition.setResType(ResourceType.EXAM);
	//List<ResourceVo> sjvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	List<ResourceVo> sjvos =  this.getResourceVo(condition, page, order);
	condition.setResType(ResourceType.TEACHING_PLAN);
	//List<ResourceVo> javos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	List<ResourceVo> javos =  this.getResourceVo(condition, page, order);
	condition.setResType(ResourceType.MATERIAL);
	//List<ResourceVo> scvos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	List<ResourceVo> scvos =  this.getResourceVo(condition, page, order);
	if ((kjvos.size() + zyvos.size() + sjvos.size() + javos.size() + scvos.size()) <= 7) {
		vos.addAll(kjvos);
		vos.addAll(zyvos);
		vos.addAll(sjvos);
		vos.addAll(javos);
		vos.addAll(scvos);
	} else {		for (int i = 0; i < 7; i++) {

			if (kjvos.size() > i) {
				vos.add(kjvos.get(i));
				if (vos.size() == 7) {
					break;
				}

			}
			if (zyvos.size() > i) {
				vos.add(zyvos.get(i));
				if (vos.size() == 7) {
					break;
				}
			}
			if (sjvos.size() > i) {
				vos.add(sjvos.get(i));
				if (vos.size() == 7) {
					break;
				}
			}
			if (javos.size() > i) {
				vos.add(javos.get(i));
				if (vos.size() == 7) {
					break;
				}
			}
			if (scvos.size() > i) {
				vos.add(scvos.get(i));
				if (vos.size() == 7) {
					break;
				}
			}
		}
	}
	return vos;
}
private List<ResourceVo> getResourceVo(ResourceVoCondition resourceVoCondition, Page page,Order order){
	ResourceDisplayCondition condition = new ResourceDisplayCondition();
	condition.setGradeCode(resourceVoCondition.getGradeCode());
	condition.setVerify("0");
	condition.setSubjectCode(resourceVoCondition.getSubjectCode());
	condition.setResType(resourceVoCondition.getResType());
	List<ResourceVo> vos = new ArrayList<ResourceVo>();
	List<ResourceDisplay> displayList = this.resourceDisplayService.findResourceDisplayByCondition(condition, page, order);
	for (ResourceDisplay resourceDisplay : displayList) {
		ResourceVo vo = new ResourceVo();
		vo.setCatalogCode(resourceDisplay.getCatalogCode());
		vo.setCommentCount(resourceDisplay.getCommentCount());
		vo.setCreateDate(resourceDisplay.getCreateDate());
		vo.setDescription(resourceDisplay.getDescription());
		vo.setFavCount(resourceDisplay.getFavCount());
		vo.setGradeCode(resourceDisplay.getGradeCode());
		vo.setGradeName(resourceDisplay.getGradeName());
		vo.setIconType(resourceDisplay.getIconType());
		vo.setId(resourceDisplay.getResourceId());
		//vo.setImgSrc(resourceDisplay.get);
		vo.setLibraryId(resourceDisplay.getLibraryId());
		vo.setLikeCount(resourceDisplay.getLikeCount());
		vo.setModifyDate(resourceDisplay.getModifyDate());
		vo.setObjectId(resourceDisplay.getObjectId());
		vo.setResType(resourceDisplay.getResType());
		vo.setStageCode(resourceDisplay.getStageCode());
		vo.setStageName(resourceDisplay.getStageName());
		vo.setSubjectCode(resourceDisplay.getSubjectCode());
		vo.setSubjectName(resourceDisplay.getSubjectName());
		vo.setThumbnailUrl(resourceDisplay.getThumbnailUrl());
		vo.setTitle(resourceDisplay.getTitle());
		vo.setUserId(resourceDisplay.getUserId());
		vo.setUserName(resourceDisplay.getUserName());
		vo.setUuid(resourceDisplay.getResourceUuid());
		vo.setVerify(resourceDisplay.getVerify());
		vo.setVersionCode(resourceDisplay.getVersionCode());
		vo.setVersionName(resourceDisplay.getVersionName());
		vo.setVolumnCode(resourceDisplay.getVolumnCode());
		vo.setVolumnName(resourceDisplay.getVolumnName());
		vos.add(vo);
	}
	
	return vos;
}
private void addToOperationWeekCount() {
	this.resourceOperationService.createOperationWeekCountByType(OperationType.CLICK);
	this.resourceOperationService.createOperationWeekCountByType(OperationType.LIKE);
	this.resourceOperationService.createOperationWeekCountByType(OperationType.FAV);
	this.resourceOperationService.createOperationWeekCountByType(OperationType.DOWN);
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
	page.setPageSize(8);// 页面拿数据几条数据
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
	condition.setResType(ResourceType.LEARNING_DESIGN);
	condition.setVerify(condition.VERYIFY_SUCCESS);
	page.setPageSize(8);
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
private List<ResourceVo> getResourceBySubject(String subjectCode, Integer relateAppId,Integer relateSchoolId){
	Page page = new Page();
	Order order = new Order();
	ResourceVoCondition condition = new ResourceVoCondition();
	Integer ownerId = SysContants.SYSTEM_OWNER_ID;
	Integer appId = SysContants.SYSTEM_APP_ID;
	List<ResourceVo> vos = new ArrayList<ResourceVo>();
	condition.setRelateAppId(relateAppId);
	condition.setRelateSchoolId(relateSchoolId);
	condition.setOwnerId(ownerId);
	condition.setAppId(appId);
	condition.setVerify(condition.VERYIFY_SUCCESS);
	condition.setSubjectCode(subjectCode);
	page.setPageSize(6);
	order.setAscending(false);
	order.setProperty("resource_uuid");
	vos = this.getResourceVo(condition, page, order);
	//vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
	return vos;
}

}
