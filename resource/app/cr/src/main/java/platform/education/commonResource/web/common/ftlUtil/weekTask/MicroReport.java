package platform.education.commonResource.web.common.ftlUtil.weekTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoader;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.storage.Storage;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.ftlUtil.GeneratorHtml;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.OperationWeekcount;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.OperationWeekcountCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

public class MicroReport {
	@Resource
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
	
	public void process() {
		GeneratorHtml cf = new GeneratorHtml();
		String templateFilePath = FtlContants.FTL_TEMPLATEPATH;
		String htmlFilePath = FtlContants.FTL_HTMLPATH;
		List<ResourceLibrary> list = this.resourceLibraryService.findResourceLibraryByCondition(null);
	    Integer relateAppId = null;
	    Integer relateSchoolId = null;
	    for(ResourceLibrary rl : list){
			relateAppId = rl.getAppId();
			relateSchoolId = rl.getOwerId();
			String htmlFileName = "microIndex_"+ relateAppId +"_"+ relateSchoolId +"_"+ rl.getUuid() +".html";
			ResourceVoCondition condition = new ResourceVoCondition();
			List<ResourceVo> vos = new ArrayList<ResourceVo>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			StringBuffer sbf = new StringBuffer();
			/**
			 * 小学语文科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFourChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosFiveChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosSixChinese", vos);
			/**
			 * 初中语文科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleOneChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoChinese", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_CHINESE, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeChinese", vos);
			/**
			 * 小学数学科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFourMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosFiveMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosSixMath", vos);
			/**
			 * 初中数学科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoMath", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_MATH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeMath", vos);
			/**
			 * 小学英语科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_FOUR, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFourEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_FIVE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosFiveEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_SIX, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosSixEnglish", vos);
			/**
			 * 初中英语科目
			 */
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_ONE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleOneEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_TWO, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleTwoEnglish", vos);
			vos = getResourceByGradeCode(condition.GRADE_MIDDLE_THREE, condition.SUBJECT_ENGLISH, relateAppId, relateSchoolId);
			data.put("vosMiddleThreeEnglish", vos);
			/**
			 * 感兴趣的资源
			 */
			vos = getInterestMicro(relateAppId, relateSchoolId);
			data.put("vosInterestMicro", vos);
			String context = FtlContants.HTML_BASE_PATH;
			data.put("contextPath", context);
		    cf.geneHtmlFile(FtlContants.FTL_TEMPLATEMICRONAME, templateFilePath, data, htmlFilePath, htmlFileName);
	    }
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
		List<ResourceVo> vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
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
		List<ResourceVo> vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
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
