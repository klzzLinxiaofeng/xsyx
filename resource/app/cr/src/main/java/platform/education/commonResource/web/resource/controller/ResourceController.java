package platform.education.commonResource.web.resource.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.I2D;
import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.RedisKey;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.MultiDomainResolver;
import platform.education.commonResource.web.common.util.SzxyHttpClientRequest;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.commonResource.web.resource.common.service.EasyResouceService;
import platform.education.commonResource.web.resource.contans.ContansOfResource;
import platform.education.commonResource.web.resource.contans.ResResourceType;
import platform.education.commonResource.web.resource.vo.MyResourceVo;
import platform.education.exam.contants.ExamType;
import platform.education.exam.model.Exam;
import platform.education.generalTeachingAffair.model.ResPaidResource;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalcode.model.*;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.vo.*;
import platform.education.homework.contants.HomeworkType;
import platform.education.homework.model.Homework;
import platform.education.learningDesign.contants.LearningDesignType;
import platform.education.learningDesign.contants.LearningPlanType;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.material.contants.MaterialType;
import platform.education.material.model.Material;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonBgpicture;
import platform.education.micro.service.MicroLessonBgpictureService;
import platform.education.micro.vo.MicroLessonBgpictureCondition;
import platform.education.micro.vo.MicroLessonBgpictureVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.paper.model.PaperResult;
import platform.education.resource.contants.*;
import platform.education.resource.dao.ResourceDao;
import platform.education.resource.model.*;
import platform.education.resource.service.*;
import platform.education.resource.utils.DownloadUtil;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.*;
import platform.education.teachingPlan.contants.TeachingPlanType;
import platform.education.teachingPlan.model.TeachingPlan;
import platform.education.user.model.UserFav;
import platform.education.user.service.UserFavService;
import platform.education.user.vo.UserFavVo;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static platform.education.resource.vo.ResourceDisplayCondition.versionCode;

@Controller
@RequestMapping("/resource/")
public class ResourceController extends BaseController {
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	@Autowired
	@Qualifier("resourceLibraryService")
	private ResourceLibraryService resourceLibraryService;
	@Autowired
	@Qualifier("catalogResourceService")
	private CatalogResourceService catalogResourceService;
    @Autowired
    @Qualifier("microLessonBgpictureService")
	private MicroLessonBgpictureService microLessonBgpictureService;
    @Autowired
    @Qualifier("subjectService")
	private SubjectService subjectService;
	@Autowired
	@Qualifier("resourceFulltextRetrievalService")
	private ResourceFulltextRetrievalService resourceFulltextRetrievalService;
	@Autowired
	@Qualifier("resIntegralService")
	private ResIntegralService resIntegralService;
	@Autowired
	@Qualifier("resIntegralUserService")
	private ResIntegralUserService resIntegralUserService;
	@Autowired
	@Qualifier("userFavService")
	private UserFavService userFavService;
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;
	@Autowired
	@Qualifier("baseRedisCache")
	private BaseRedisCache<Object> baseRedisCache;

	@Autowired
	@Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;

	@Autowired
	private EasyResouceService easyResouceService;

	//版本
	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService jcTextbookVersionService;

	private final static String viewBasePath = "/resource";
	private final static String myresourcePath = "/myresource";
	private final static String commonPath = "/common";

	@RequestMapping(value = { "/", "home", "index" })
	public String home(HttpServletRequest request, Model model) {


		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		ResourceLibraryCondition condition = new ResourceLibraryCondition();

		condition.setOwerId(relateAppId);
		condition.setAppId(relateSchoolId);
		ResourceLibrary resourceLibrary = new ResourceLibrary();

		String url = "";
		condition = new ResourceLibraryCondition();
		condition.setOwerId(ownerId);
		condition.setAppId(appId);
		List<ResourceLibrary> resLibList = this.resourceLibraryService
				.findResourceLibraryByCondition(condition);
		resourceLibrary = resLibList.get(0);
//		String host = FtlContants.FTL_HTMLURL;
		String host = FtlContants.FTL_HTMLURL;
		host = MultiDomainResolver.resolver(request.getServerName(), host);
		url = "redirect:" + host + "index_" + appId + "_" + ownerId + "_"
				+ resourceLibrary.getUuid() + ".html";

		return url;
	}

	@RequestMapping(value = {"/searcher/index","/public/searcher/index"}, method = RequestMethod.GET)
	public ModelAndView seacher(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") ResourceVoCondition condition,
			HttpServletRequest request,
			@RequestParam(value = "personType", required = false) String personType,
			@RequestParam(value = "userType", required = false) String userType,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "resType", required = false) Integer resType,
			Model model) {

		if(user != null){
			if(isRegionUser(user.getUserId())){
				model.addAttribute("userType", userType);
			}
		}

		model.addAttribute("personType", personType);
		model.addAttribute("dm", dm);

		if (resType == ResourceType.LEARNING_DESIGN) {
			model.addAttribute("titleName", "课件资源");
		} else if (resType == ResourceType.HOMEWORK) {
			model.addAttribute("titleName", "作业资源");
		} else if (resType == ResourceType.EXAM) {
			model.addAttribute("titleName", "试卷资源");
		} else if (resType == ResourceType.TEACHING_PLAN) {
			model.addAttribute("titleName", "教案资源");
		} else if (resType == ResourceType.MATERIAL) {
			model.addAttribute("titleName", "素材资源");
		} else if (resType == null || resType.equals("null")
				|| resType.equals("")) {
			model.addAttribute("titleName", "资源列表");
		} else if(resType == ResourceType.LEARNING_PLAN) {
			model.addAttribute("titleName", "导学案资源");
		}
		return new ModelAndView(structurePath("/resource_seacher"),
				model.asMap());
	}

	@RequestMapping(value = {"/searcher/result/index","/public/searcher/result/index"})
	public ModelAndView seacherResultPage(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "personType", required = false) String personType,
			@RequestParam(value = "userType", required = false) String userType,
			Model model) throws UnsupportedEncodingException {
		model.addAttribute("personType", personType);
		model.addAttribute("userType", userType);
		model.addAttribute("title", title);
		return new ModelAndView(structurePath("/resource_search_result"),
				model.asMap());
	}

	@RequestMapping(value = {"/searcher","/public/searcher"})
	public ModelAndView seacherByCondition(
			HttpServletRequest request,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isMicro", required = false, defaultValue = "false") boolean isMicro,
			@RequestParam(value = "topType", required = true, defaultValue = "top") String topType,
			@RequestParam(value = "personType", required = false) String personType,
			@RequestParam(value = "userType", required = false) String userType,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "title2", required = false) String title2,
			@ModelAttribute("condition") ResourceVoCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {

		if(user != null){
			if(isRegionUser(user.getUserId())){
				model.addAttribute("userType", userType);
			}
		}

		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = null;
		if(user != null){
			relateSchoolId = user.getSchoolId();
		}
		Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
		String verify = "";
		boolean flag = true;

		if("".equals(title2) || title2 != null){
			condition.setTitle(title2);
		}
		if (condition.getCatalogCode() != null && condition.getCatalogCode().equals("")) {
			condition.setCatalogCode(null);
		}
		if (personType.equals(ContansOfResource.SCHOOLRESOURCE)) {
			condition.setRelateAppId(relateAppId);
			condition.setRelateSchoolId(relateSchoolId);
			verify = ContansOfResource.SCHOOLRESOURCE;
		} else if(ContansOfResource.REGIONRESOURCE.equals(personType)){
//			String title = condition.getTitle();
			String title = "";
			if (this.resourceFulltextRetrievalService.isEnable() && title != null && !"".equals(title)) {
				vos = this.resourceFulltextRetrievalService.findByFullTextRetrieval(condition.getTitle(), condition.getResType(),null,null, page, order);
				flag = false;
			} else {
				condition.setOwnerId(ownerId);
				condition.setAppId(6);
				verify = ContansOfResource.REGIONRESOURCE;
			}

		}else {
			String title = condition.getTitle();
			if (this.resourceFulltextRetrievalService.isEnable() && title != null && !"".equals(title)) {
				vos = this.resourceFulltextRetrievalService.findByFullTextRetrieval(condition.getTitle(), condition.getResType(),null,null, page, order);
				flag = false;
			} else {
				condition.setOwnerId(ownerId);
				condition.setAppId(appId);
//				verify = ResourceCondition.DEFAULT_UPLOAD_YES;
				verify = "res_region";
			}
		}

		if (flag) {
			condition.setVerify(verify);
			conditionFilter(user, condition);
			if (order == null || order.getProperty() == null) {
				order.setProperty("create_date");
				order.setAscending(false);
			}
			if (condition.getResType() != null && condition.getResType() - 0 == -1) {
				condition.setResType(null);
			}
			vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
		}

		for (ResourceVo resourceVo : vos) {
			if(ResourceType.LEARNING_PLAN.equals(resourceVo.getResType())) {
				LearningPlan learningPlan = learningPlanService.findLearningPlanByUuid(resourceVo.getObjectId());
				if(learningPlan!=null && "lp".equals(learningPlan.getEntityId())) {
					resourceVo.setLpId(learningPlan.getId());
				} else {
					String entityId = resourceService.getEntityIdByObjectIdAndType(resourceVo.getObjectId(), ResourceType.LEARNING_PLAN);
					String suffix = resourceService.getFileSuffixByEntityId(entityId);
					if(suffix==null || "".equals(suffix)) {
						continue;
					}
					resourceVo.setLpId(resourceVo.getId());
					/**因为导学案存在doc格式，在处理doc格式的导学案时，把其当作试卷处理*/
					resourceVo.setResType(ResourceType.EXAM);
				}
			}
		}

		model.addAttribute("topType", topType);
		model.addAttribute("personType", personType);
		model.addAttribute("dm", dm);
		model.addAttribute("condition", condition);
		model.addAttribute("items", user != null ?setIsFav(vos, user.getUserId(), relateAppId):vos);
		return new ModelAndView(structurePath("/resource_list"), model.asMap());
	}


	private boolean isRegionUser(Integer userId) {
		boolean flag = false;
		List<String> userRoles = userRoleService.findRoleCodesByUserId(userId, 101);
		if (userRoles != null && userRoles.size() > 0) {
			if(userRoles.contains("REGION_ADMIN")){
				flag = true;
			}
		}
		return flag;
	}

	@RequestMapping(value = "/getResource", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getResource(HttpServletRequest request,
			@ModelAttribute("condition") ResourceVoCondition condition,
			@CurrentUser UserInfo user,
			@RequestParam(value = "pageSize", required = true) String pageSize,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
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
		condition.setOwnerId(ownerId);
		condition.setAppId(appId);
		condition.setVerify(ResourceVoCondition.VERYIFY_SUCCESS);
		conditionFilter(user, condition);
		order.setAscending(false);
		order.setProperty("create_date");
		page.setPageSize(Integer.parseInt(pageSize));// 页面拿数据几条数据
		List<ResourceVo> vos = new ArrayList<ResourceVo>();
		condition.setResType(ResourceType.LEARNING_DESIGN);
		List<ResourceVo> kjvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.HOMEWORK);
		List<ResourceVo> zyvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.EXAM);
		List<ResourceVo> sjvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.TEACHING_PLAN);
		List<ResourceVo> javos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.MATERIAL);
		List<ResourceVo> scvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.MICRO);
		List<ResourceVo> mcvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		condition.setResType(ResourceType.LEARNING_PLAN);
		List<ResourceVo> lcvos = this.resourceService
				.findResourceVoByMoreCondition(condition, page, order);
		if ((kjvos.size() + zyvos.size() + sjvos.size() + javos.size()
				+ scvos.size() + mcvos.size() + lcvos.size()) <= 7) {
			vos.addAll(kjvos);
			vos.addAll(zyvos);
			vos.addAll(sjvos);
			vos.addAll(javos);
			vos.addAll(scvos);
			vos.addAll(mcvos);
			vos.addAll(lcvos);
		} else {
			for (int i = 0; i < Integer.parseInt(pageSize); i++) {

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
				if (mcvos.size() > i) {
					vos.add(scvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
				if (lcvos.size() > i) {
					vos.add(scvos.get(i));
					if (vos.size() == 7) {
						break;
					}
				}
			}
		}
		model.addAttribute("vos", vos);
		return new ModelAndView("index", model.asMap());
	}

	private List<ResourceVo> setIsFav(List<ResourceVo> vos, Integer userId,
			Integer relateAppId) {
		for (ResourceVo vo : vos) {
			Favorites fav = this.favoritesService.findUnique(relateAppId,
					userId, vo.getId());
			vo.setFav(fav != null);
		}
		return vos;
	}

	@RequestMapping(value = {"/viewer/{id}","/shiro/viewer/{id}"})
	public ModelAndView viewer(@PathVariable(value = "id") Integer id,
			@ModelAttribute(value = "condition") ResourceVoCondition condition,
			HttpServletRequest request, @CurrentUser UserInfo user, Model model) {
		Resource resource = this.resourceService.findResourceById(id);
		if (resource != null) {
			String objId = resource.getObjectId();
			Integer resType = resource.getResType();
			String entityId = null;
			if (ResourceType.EXAM == resType) {
				Exam exam = this.examService.findExamByUuid(objId);
				if (exam != null) {
					entityId = exam.getEntityId();
				}
			} else if (ResourceType.HOMEWORK == resType) {
				Homework homework = this.homeworkService
						.findHomeworkByUuid(objId);
				if (homework != null) {
					entityId = homework.getEntityId();
				}
			} else if (ResourceType.LEARNING_DESIGN == resType) {
				LearningDesign learningDesign = this.learningDesignService
						.findLearningDesignByUuid(objId);
				if (learningDesign != null) {
					entityId = learningDesign.getEntityId();
				}
			} else if (ResourceType.MATERIAL == resType) {
				Material materail = this.materialService
						.findMaterialByUuid(objId);
				if (materail != null) {
					entityId = materail.getEntityId();
				}
			} else if (ResourceType.MICRO == resType || ResourceType.WRITE_TYPE_MICRO == resType) {
				MicroLesson microLession = this.microLessonService
						.findMicroLessonByUuid(objId);
				if (microLession != null) {
					entityId = microLession.getEntityId();
				}
			} else if (ResourceType.LEARNING_PLAN == resType) {
				LearningPlan learningPlan = this.learningPlanService
						.findLearningPlanByUuid(objId);
				if (learningPlan != null) {
					entityId = learningPlan.getEntityId();
				}
			} else if (ResourceType.TEACHING_PLAN == resType) {
				TeachingPlan teachingPlan = this.teachingPlanService
						.findTeachingPlanByUuid(objId);
				if (teachingPlan != null) {
					entityId = teachingPlan.getEntityId();
				}
			} else {
				entityId = resource.getObjectId();
			}
			if (entityId != null) {
				EntityFile entity = entityFileService.findFileByUUID(entityId);
				if (entity != null) {
					model.addAttribute("entity", entity);
				}
			}
			model.addAttribute("res", resource);
			model.addAttribute("user", user);
		}
		/**
		 * 浏览操作的记录
		 */
		Integer userid = null;
		if(user != null){
			userid = user.getUserId();
		}

		if (id != null && userid!=null && resource!= null) {
			String resUuid = resource.getUuid();
			/** 用户操作资源记录 */
			resourceService.createResourceOperation(resUuid, userid, OperationType.CLICK);
			/** 用户浏览行为记录 */
			resourceService.createUserAction(resUuid, userid, ActionType.CLICK);
		}
		return new ModelAndView(structurePath("/resource_viewer"));
	}

	@RequestMapping("/fav")
	@ResponseBody
	public synchronized String fav(HttpServletRequest request, @CurrentUser UserInfo user,
			@RequestParam(value = "id", required=true) Integer resId,
			@RequestParam("isFav") boolean isFav) {
		Integer userid = user.getUserId();
		if (isFav) {

			if(easyResouceService.isFavorite(userid,resId)){
				return "faved";
			}else{
				easyResouceService.favoriteRes(userid,resId);
				return "success";
			}

		} else {
			favoritesService.removeByUserResource(user.getUserId(), resId);
			return "success";
		}
	}

	@RequestMapping("/like")
	@ResponseBody
	public synchronized String  likes(HttpServletRequest request, @CurrentUser UserInfo user,
			@RequestParam(value = "Id") Integer Id,
			@RequestParam("isLike") boolean isLike) {
		String result = "success";
		if (isLike) {
			if(easyResouceService.isLike(user.getUserId(), Id)){
				return "faved";
			}else{
				easyResouceService.likeRes(user.getUserId(), Id);
			}
		} else {
			result = likesService.removeUserLikes(user.getUserId(), Id);
		}
		return result;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, ResourceVoCondition condition) {
		/*
		 * if (appId == null) { condition.setAppId(SysContants.SYSTEM_APP_ID); }
		 */
		if (condition.getGradeCode() != null
				&& !"".equals(condition.getGradeCode().trim())) {// 对高中资源做特殊处理
			if ("0".equals(condition.getGradeCode())) {
				condition.setGradeCode("");
			}
		} else {

		}
	}


	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
    public String saveOrUpdate(HttpServletRequest request, @CurrentUser UserInfo user) throws Exception {
		try {

			Resource resource=new Resource();

			//修改参数
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String description=request.getParameter("description");
			if(id!=null && !id.isEmpty()){
				resource.setId(Integer.parseInt(id));
				resource.setTitle(title);
				resource.setDescription(description);
				resourceService.modify(resource);
				return "0";
			}

			//添加参数
			//区分是个人资源还是共享资源，true:个人资源，false:共享资源
			String isPersonalStr = request.getParameter("isPersonal");
			String resType = request.getParameter("resType");
			boolean isPersonal=org.apache.commons.lang3.StringUtils.isEmpty(isPersonalStr) ? true:new Boolean(isPersonalStr);
			String fileUuid = request.getParameter("fileUuid");
			String fileSize = request.getParameter("fileSize");
			String fileUrl=request.getParameter("fileUrl");
			String fileName=request.getParameter("fileName");


			resource.setIsPersonal(isPersonal);
			resource.setUuid(fileUuid);
			if(!isPersonal && resType!=null && !resType.isEmpty()) {
				resource.setResType(new Integer(resType));
			}
			resource.setObjectId(fileSize);
			resource.setThumbnailUrl(fileUrl);
			resource.setTitle(fileName);
			resource.setUserId(user.getUserId());
			resource.setUserName(user.getRealName());
			resource.setLikeCount(0);
			resource.setIconType(IconUtil.setIcon(fileName.substring(fileName.lastIndexOf(".")+1)));
			resource.setIsDeleted(false);
			resource.setCreateDate(new Date());
			resource.setModifyDate(new Date());
			resource.setVerify("0");
			resourceService.add(resource);
			return "0";


		} catch (Exception e) {
			e.printStackTrace();
			return "1";
		}




//		String integral = request.getParameter("integral");
//		String resId = request.getParameter("microId");
//        String id=request.getParameter("id");
//        String resType = request.getParameter("resType");
//        String entityId = request.getParameter("entityId");
//        String description = request.getParameter("description");
//        String checked = request.getParameter("checked");
//        String textbookId = request.getParameter("textbookId");
//        String catalogEnd = request.getParameter("catalogEnd");
//
//        String stageCode = request.getParameter("stageCode");
//        String subjectCode = request.getParameter("subjectCode");
//        String gradeCode = request.getParameter("gradeCode");
//        String version = request.getParameter("version");
//        String volumn = request.getParameter("volumn");
//
//        String stageName = request.getParameter("stageName");
//        String subjectName = request.getParameter("subjectName");
//        String versionName = request.getParameter("versionName");
//        String personType= request.getParameter("personType");
//
//        String shareType=request.getParameter("shareType");
//
//
//        String gradeName = "";
//        String volumnName = "";
//        String returenUuid="";
//
//        if (gradeCode != null && !"".equals(gradeCode)) {
//            GradeCondition gradeCondition = new GradeCondition();
//            gradeCondition.setCode(gradeCode);
//
//            List<Grade> gradeList = this.jcGradeService.findGradeByCondition(gradeCondition, null, null);
//            if (gradeList != null && gradeList.size() > 0) {
//                gradeName = gradeList.get(0).getName();
//            } else {
//                gradeName = "";
//            }
//        } else {
//            gradeName = "";
//        }
//
//        if (volumn != null && !"".equals(volumn)) {
//            TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
//            textbookVolumnCondition.setCode(volumn);
//            List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
//            if (volumnList != null && volumnList.size() > 0) {
//                volumnName = volumnList.get(0).getName();
//            } else {
//                volumnName = "";
//            }
//        } else {
//            volumnName = "";
//        }
//
//        Integer relateAppId = this.getRelateApp(request);
//        ImportCatalogVo vo = new ImportCatalogVo();
//        vo.setStageCode(stageCode);
//        vo.setSubjectCode(subjectCode);
//        vo.setGradeCode(gradeCode);
//        vo.setGradeCode(gradeCode);
//        vo.setVersionCode(version);
//        vo.setVolumnCode(volumn);
//        if(integral!=null && !"".equals(integral)) {
//			vo.setIntegral(Integer.parseInt(integral));
//		} else {
//			vo.setIntegral(0);
//		}
//
//        vo.setStageName(stageName);
//        vo.setSubjectName(subjectName);
//        vo.setGradeName(gradeName);
//        vo.setVersionName(versionName);
//        vo.setVolumnName(volumnName);
//
//        if (catalogEnd != null && !catalogEnd.equals("")) {
//            vo.setCatalogCode(catalogEnd);
//        } else if (textbookId != null && Integer.parseInt(textbookId) > 0) {
//            ResTextbookCatalogCondition condition= new ResTextbookCatalogCondition();
//            condition.setTestBookId(Integer.parseInt(textbookId));
//            condition.setParentId(TextbookCatalogCondition.DEFAULT_PARENT_ID);
//            List<ResTextbookCatalog> catalogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(condition);
//            if (catalogList != null && catalogList.size() > 0) {
//            	ResTextbookCatalog textbookCatalog = catalogList.get(0);
//                vo.setCatalogCode(textbookCatalog.getCode());
//            }
//        }
//
//        if(id!=null&&!id.equals("")){
//        	Resource r=this.resourceService.findResourceById(Integer.valueOf(id));
//			String title = request.getParameter("title");
//        	if(title != null){
//				r.setTitle(title);
//			}
//        	r.setDescription(description);
//        	r.setModifyDate(new Date());
//        	this.resourceService.modify(r);
//        	if(r.getResType()==ResourceType.EXAM){
//        		Exam em =this.examService.findExamByUuid(r.getObjectId());
//           	 if(subjectCode!=null){
//        		 em.setSubjectCode(subjectCode);
//        		 this.examService.modify(em);
//        	 }
//        	}
//        	if(personType.equals(ContansOfResource.SHARERESOURCE)||personType.equals(ContansOfResource.SCHOOLRESOURCE)){
//        		CatalogResource catalogResource=new CatalogResource();
//        		CatalogResourceCondition condition =new CatalogResourceCondition();
//        		condition.setResourceId(r.getId());
//        		List<CatalogResource> list=this.catalogResourceService.findCatalogResourceByCondition(condition);
//        		if(list!=null&&list.size()>0){
//
//        			catalogResource=this.catalogResourceService.findCatalogResourceByCondition(condition).get(0);
//        		}
//        		catalogResource.setCatalogCode(catalogEnd);
//        		catalogResource.setAppId(relateAppId);
//        		catalogResource.setGradeCode(gradeCode);
//        		catalogResource.setGradeName(gradeName);
//        		catalogResource.setVolumnCode(volumn);
//        		catalogResource.setVolumnName(volumnName);
//        		catalogResource.setVersionCode(version);
//        		catalogResource.setVersionName(versionName);
//        		catalogResource.setModifyDate(new Date());
//        		catalogResource.setStageCode(stageCode);
//        		catalogResource.setStageName(stageName);
//        		catalogResource.setSubjectCode(subjectCode);
//        		catalogResource.setSubjectName(subjectName);
//        		catalogResource.setResourceId(r.getId());
//        		catalogResource.setResourceType(r.getResType());
//	           if(list!=null&&list.size()>0){
//	        	   this.catalogResourceService.modify(catalogResource);
//        		}else{
//        			this.catalogResourceService.add(catalogResource);
//        		}
//
//        	}
//        }else{
//
//             if (resType != null && !"".equals(resType)) {
//                 Integer typeint = Integer.parseInt(resType);
//                 if (ResourceType.EXAM == typeint) {
//                  String type=ExamType.COMMON_EXAM;
//
//                	 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                	 PaperResult paperResult=new PaperResult();
//                	 String realPath = request.getServletContext().getRealPath("/") +"tmp.xep";
//                	 if(obj.getExtension().equals("xep")){
//                		 type=ExamType.XEP_EXAM;
//                		 File date = new File(realPath);
//                		 OutputStream os = new FileOutputStream(date);
//                		 this.fileService.download(obj.getUuid(), os);
//                		 //paperResult = this.paperHandleService.paperSplit(user.getUserId(), realPath, targerPath, obj.getUuid(), obj.getMd5(), Long.valueOf(obj.getSize()), version, stageCode, catalogEnd);
//
//                	 }
//
//                	 Exam em = this.examService.saveOrUpdateExam(relateAppId, resId, entityId, type, obj.getFileName(), user.getUserId(), description);
//                	 if(subjectCode!=null){
//                		 em.setSubjectCode(subjectCode);
//                		 this.examService.modify(em);
//                	 }
//                	 if(obj.getExtension().equals("xep")){
//                		 em.setPaperId(paperResult.getPaperId());
//                		 this.examService.modify(em);
//                	 }
//                	 if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE, vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//
//                     returenUuid = em.getUuid();
////                     new Thread(new DeleteThread(new File(targerPath))).start();
////                     forceDelete(new File(realPath));
//                 } else if (ResourceType.HOMEWORK == typeint) {
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                     Homework em = this.homeworkService.saveOrUpdateHomework(relateAppId, resId, entityId, HomeworkType.COMMON_HOMEWORK, obj.getFileName(), user.getUserId(), description);
//                     if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//
//                     returenUuid = em.getUuid();
//                 } else if (ResourceType.LEARNING_DESIGN == typeint) {
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                     LearningDesign em = this.learningDesignService.saveOrUpdateLearningDesign(relateAppId, resId, entityId, LearningDesignType.COMMON_LEARNINGDESIGN, obj.getFileName(), user.getUserId(), description);
//                     if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//
//                     returenUuid = em.getUuid();
//                 } else if (ResourceType.MATERIAL == typeint) {
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                     Material em = this.materialService.saveOrUpdateMaterial(relateAppId, resId, entityId, MaterialType.COMMON_MATERIAL, obj.getFileName(), user.getUserId(), description);
//                     if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//
//                     returenUuid = em.getUuid();
//                 } else if (ResourceType.TEACHING_PLAN == typeint) {
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                     TeachingPlan em = this.teachingPlanService.saveOrUpdateTeachingPlan(relateAppId, resId, entityId, TeachingPlanType.COMMON_TEACHINGPLAN, obj.getFileName(), user.getUserId(), description);
//                     if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//
//                     returenUuid = em.getUuid();
//                 }
//                 else if(ResourceType.MICRO ==typeint){//微课上传
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                 	MicroLesson em = this.microLessonService.saveOrUpdateMicroLesson(relateAppId, resId, entityId, MicroType.COMMON_MICRO, obj.getFileName(), user.getUserId(), description, null, vo,null);
//                 	if(em != null){
//                     	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                         		//个人资源上传的时候不共享目录
//                         		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                         	}
//                     	} else{
//                     		//校本
//                     		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                     	}
//                     }
//                 }
//                 else if(ResourceType.LEARNING_PLAN ==typeint){//导学案上传
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//                	 LearningPlan  em = this.learningPlanService.saveOrUpdateLearningPlan(relateAppId, resId, entityId, LearningPlanType.COMMON_LEARNINGPLAN,obj.getFileName(), user.getUserId(), description);
//                  	if(em != null){
//
//                      	if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							if(!"".equals(shareType) && shareType != null){
//								//个人资源上传的时候共享目录
//								vo.setShareType(shareType);
//								this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							}else{
//                          		//个人资源上传的时候不共享目录
//                          		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//                          	}
//                      	} else{
//                      		//校本
//                      		this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description, em.getAppId(), obj.getThumbnailUrl(), user,obj.getExtension(),personType,vo);
//                      	}
//                      }
//                  }else if(ResResourceType.PAID == typeint){
//
//					 EntityFile obj = this.entityFileService.findFileByUUID(entityId);
//					 ResPaidResource em = new ResPaidResource();
//					 em.setTitle(obj.getFileName());
//					 em.setDescription(description);
//					 em.setEntityId(entityId);
//					 em.setUserId(user.getUserId());
//					 em = this.resPaidResourceService.add(em);
//					 if(em != null){
//						 if(personType != null && personType.equals(ContansOfResource.PERSONRESOURCE)){
//							 if(!"".equals(shareType) && shareType != null){
//								 //个人资源上传的时候共享目录
//								 vo.setShareType(shareType);
//								 this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description,null, obj.getThumbnailUrl(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_SHARE, vo);
//							 }else{
//								 //个人资源上传的时候不共享目录
//								 this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description,null, obj.getRelativePath(), user,obj.getExtension(),ContansOfResource.PERSONRESOURCE_NOSHARE,vo);
//							 }
//						 } else{
//							 //校本
//							 this.addResource(relateAppId,typeint, em.getUuid(), obj.getFileName(), description,null, obj.getRelativePath(), user,obj.getExtension(),personType,vo);
//						 }
//					 }
//
//
//				 }
//             }
//        }
//
//        PrintWriter pw = this.setAjaxResponse(request, response);
//        //pw.print(returenUuid);
//        return viewBasePath + commonPath + "/uploadIndex";
    }




	/**
	 * 添加资源
	 *
	 * @param resType
	 *            类型
	 * @param objectId
	 *            资源本身uuid
	 * @param noSuffixTitle
	 *            标题
	 * @param description
	 *            描述
	 * @param appId
	 *            appId
	 * @param thumbnailUrl
	 *            缩略图
	 */
	private void addResource(Integer relateAppId, Integer resType,
			String objectId, String noSuffixTitle, String description,
			Integer appId, String thumbnailUrl, UserInfo user, String suffix,
			String personType, ImportCatalogVo vo) {
		String lib_uuid = "";
		String verify = ResourceCondition.DEFAULT_VERIFY_NO;
		boolean isPersonal = true;

		if (personType != null && personType.equals(ContansOfResource.PERSONRESOURCE_NOSHARE)) {
			//个人资源上传不共享
			Resource res = new Resource();
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify(verify);
			res.setLibraryId(lib_uuid);
			res.setUserId(user.getUserId());
			res.setIntegral(vo.getIntegral());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			// res.setAppId(appId);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(isPersonal);
			res = this.resourceService.add(res);

		} else if (personType != null && personType.equals(ContansOfResource.PERSONRESOURCE_SHARE)) {
			//个人资源上传并且共享
			Resource res = new Resource();
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
			res.setLibraryId("");
			res.setUserId(user.getUserId());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIntegral(0);

			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(true);
			res = this.resourceService.add(res);
			addCatalog(relateAppId, res.getId(), res.getObjectId(), res.getResType(), vo);
			// 对应共享的资源源
			String shareType = vo.getShareType();
			if(shareType != null){
				if(shareType.indexOf("xbzy") != -1){
					//共享到校本资源审核
					Integer owerId = user.getSchoolId();
					String schoolName = user.getSchoolName();

					ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
					resourceLibraryCondition.setAppId(appId);
					resourceLibraryCondition.setOwerId(owerId);

					List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
							.findResourceLibraryByCondition(resourceLibraryCondition);
					ResourceLibrary resourceLibrary = new ResourceLibrary();
					if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
						resourceLibrary = resourceLibraryList.get(0);
					} else {// 如果不存在添加对应的记录
						ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
						resourceLibraryAdd.setAppId(appId);
						resourceLibraryAdd.setOwerId(owerId);
						resourceLibraryAdd.setName(schoolName);
						resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
						resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);
					}
					lib_uuid = resourceLibrary.getUuid();
					verify = ResourceCondition.DEFAULT_SHARE_PROCESS;
					res.setId(null);
					res.setVerify(verify);
					res.setLibraryId(lib_uuid);
					res.setIsPersonal(false);
					res.setIntegral(vo.getIntegral());
					res = this.resourceService.add(res);
					addCatalog(relateAppId, res.getId(), res.getObjectId(),
							res.getResType(), vo);
				}

				if(shareType.indexOf("qbzy") != -1){
					//共享到区本资源审核
					ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
					resourceLibraryCondition.setAppId(6);
					resourceLibraryCondition.setOwerId(0);
					List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
					ResourceLibrary resourceLibrary = new ResourceLibrary();
					if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
						resourceLibrary = resourceLibraryList.get(0);
					} else {// 如果不存在添加对应的记录
						ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
						resourceLibraryAdd.setAppId(appId);
						resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
						resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);

					}
					lib_uuid = resourceLibrary.getUuid();
					verify = ResourceCondition.REGION_SHARE_PROCESS;
					isPersonal = false;
					res.setId(null);
					res.setVerify(verify);
					res.setLibraryId(lib_uuid);
					res.setIsPersonal(isPersonal);
					res = this.resourceService.add(res);
					addCatalog(relateAppId, res.getId(), res.getObjectId(), res.getResType(), vo);
				}
			}
		} else if (personType != null && personType.equals(ContansOfResource.SCHOOLRESOURCE)) {
			//上传资源到校本，不用审核
			Integer owerId = user.getSchoolId();
			String schoolName = user.getSchoolName();
			this.schoolService.findSchoolById(user.getSchoolId());
			ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
			resourceLibraryCondition.setAppId(appId);
			resourceLibraryCondition.setOwerId(owerId);
			List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
					.findResourceLibraryByCondition(resourceLibraryCondition);
			ResourceLibrary resourceLibrary = new ResourceLibrary();
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// 如果不存在添加对应的记录
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(appId);
				resourceLibraryAdd.setOwerId(owerId);
				resourceLibraryAdd.setName(schoolName);
				resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
				resourceLibrary = this.resourceLibraryService
						.add(resourceLibraryAdd);

			}
			lib_uuid = resourceLibrary.getUuid();
			verify = ResourceCondition.DEFAULT_UPLOAD_YES;
			isPersonal = false;
			Resource res = new Resource();
			res.setIntegral(vo.getIntegral());
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify(verify);
			res.setLibraryId(lib_uuid);
			res.setUserId(user.getUserId());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(isPersonal);
			res = this.resourceService.add(res);
			addCatalog(relateAppId, res.getId(), res.getObjectId(),
					res.getResType(), vo);

		} else if (personType != null && personType.equals(ContansOfResource.REGIONRESOURCE)) {
			//区本资源上传，上传到个人资源同时共享到区本资源审核
			Resource res = new Resource();
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
			res.setLibraryId("");
			res.setUserId(user.getUserId());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIntegral(0);

			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(true);
			res = this.resourceService.add(res);
			addCatalog(relateAppId, res.getId(), res.getObjectId(), res.getResType(), vo);
			//共享到区本
			Integer owerId = user.getSchoolId();
			String schoolName = user.getSchoolName();
			ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
			resourceLibraryCondition.setAppId(6);
			resourceLibraryCondition.setOwerId(0);

			List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
					.findResourceLibraryByCondition(resourceLibraryCondition);
			ResourceLibrary resourceLibrary = new ResourceLibrary();
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// 如果不存在添加对应的记录
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(appId);
				resourceLibraryAdd.setOwerId(owerId);
				resourceLibraryAdd.setName(schoolName);
				resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
				resourceLibrary = this.resourceLibraryService
						.add(resourceLibraryAdd);

			}
			lib_uuid = resourceLibrary.getUuid();
			verify = ResourceCondition.REGION_SHARE_PROCESS;
			isPersonal = false;
			res.setId(null);
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify(verify);
			res.setLibraryId(lib_uuid);
			res.setUserId(user.getUserId());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIntegral(vo.getIntegral());
			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(isPersonal);
			res = this.resourceService.add(res);
			addCatalog(relateAppId, res.getId(), res.getObjectId(),
					res.getResType(), vo);
		}else if(personType != null && personType.equals("onlypublic")){
			Integer owerId = user.getSchoolId();
			String schoolName = user.getSchoolName();

			ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
			resourceLibraryCondition.setAppId(6);
			resourceLibraryCondition.setOwerId(0);

			String shareType = vo.getShareType();
			if(shareType != null) {
				if (shareType.indexOf("xbzy") != -1) {
					resourceLibraryCondition.setAppId(appId);
					resourceLibraryCondition.setOwerId(owerId);
				}
			}

			List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
					.findResourceLibraryByCondition(resourceLibraryCondition);
			ResourceLibrary resourceLibrary = new ResourceLibrary();
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// 如果不存在添加对应的记录
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(appId);
				resourceLibraryAdd.setOwerId(owerId);
				resourceLibraryAdd.setName(schoolName);
				resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
				resourceLibrary = this.resourceLibraryService
						.add(resourceLibraryAdd);

			}
			lib_uuid = resourceLibrary.getUuid();
			Resource res = new Resource();
			res.setCreateDate(new Date());
			res.setModifyDate(new Date());
			res.setVerify("0");
			res.setLibraryId(lib_uuid);
			res.setUserId(user.getUserId());
			res.setUserName(user.getRealName());
			res.setResType(resType);
			res.setObjectId(objectId);
			res.setTitle(noSuffixTitle);
			res.setIntegral(vo.getIntegral());
			res.setIconType(IconUtil.setIcon(suffix));
			res.setUuid(UUIDUtil.getUUID());
			res.setDescription(description);
			res.setThumbnailUrl(thumbnailUrl);
			res.setIsPersonal(false);
			res = this.resourceService.add(res);

			addCatalog(relateAppId, res.getId(), res.getObjectId(),res.getResType(), vo);
		}else {
			String shareType = vo.getShareType();
			if(shareType != null){
				if(shareType.indexOf("xbzy") != -1){
					Integer owerId = user.getSchoolId();
					String schoolName = user.getSchoolName();

					ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
					resourceLibraryCondition.setAppId(appId);
					resourceLibraryCondition.setOwerId(owerId);

					List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
							.findResourceLibraryByCondition(resourceLibraryCondition);
					ResourceLibrary resourceLibrary = new ResourceLibrary();
					if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
						resourceLibrary = resourceLibraryList.get(0);
					} else {// 如果不存在添加对应的记录
						ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
						resourceLibraryAdd.setAppId(appId);
						resourceLibraryAdd.setOwerId(owerId);
						resourceLibraryAdd.setName(schoolName);
						resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
						resourceLibrary = this.resourceLibraryService
								.add(resourceLibraryAdd);

					}
					lib_uuid = resourceLibrary.getUuid();
					verify = ResourceCondition.DEFAULT_SHARE_PROCESS;
					isPersonal = false;
					Resource res = new Resource();
					res.setCreateDate(new Date());
					res.setModifyDate(new Date());
					res.setVerify(verify);
					res.setLibraryId(lib_uuid);
					res.setUserId(user.getUserId());
					res.setUserName(user.getRealName());
					res.setResType(resType);
					res.setObjectId(objectId);
					res.setTitle(noSuffixTitle);
					res.setIntegral(vo.getIntegral());
					// String suffix =
					// noSuffixTitle.substring(noSuffixTitle.lastIndexOf(".") + 1);
					res.setIconType(IconUtil.setIcon(suffix));
					res.setUuid(UUIDUtil.getUUID());
					res.setDescription(description);
					// res.setAppId(appId);
					res.setThumbnailUrl(thumbnailUrl);
					res.setIsPersonal(isPersonal);
					res = this.resourceService.add(res);
					addCatalog(relateAppId, res.getId(), res.getObjectId(),
							res.getResType(), vo);
				}
				if(shareType.indexOf("qbzy") != -1){
					Integer owerId = user.getSchoolId();
					String schoolName = user.getSchoolName();

					ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
					resourceLibraryCondition.setAppId(6);
					resourceLibraryCondition.setOwerId(0);

					List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
							.findResourceLibraryByCondition(resourceLibraryCondition);
					ResourceLibrary resourceLibrary = new ResourceLibrary();
					if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
						resourceLibrary = resourceLibraryList.get(0);
					} else {// 如果不存在添加对应的记录
						ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
						resourceLibraryAdd.setAppId(appId);
						resourceLibraryAdd.setOwerId(owerId);
						resourceLibraryAdd.setName(schoolName);
						resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
						resourceLibrary = this.resourceLibraryService
								.add(resourceLibraryAdd);

					}
					lib_uuid = resourceLibrary.getUuid();
					verify = ResourceCondition.REGION_SHARE_PROCESS;
					isPersonal = false;
					Resource res = new Resource();
					res.setCreateDate(new Date());
					res.setModifyDate(new Date());
					res.setVerify(verify);
					res.setLibraryId(lib_uuid);
					res.setUserId(user.getUserId());
					res.setUserName(user.getRealName());
					res.setResType(resType);
					res.setObjectId(objectId);
					res.setTitle(noSuffixTitle);
					res.setIntegral(vo.getIntegral());
					res.setIconType(IconUtil.setIcon(suffix));
					res.setUuid(UUIDUtil.getUUID());
					res.setDescription(description);
					res.setThumbnailUrl(thumbnailUrl);
					res.setIsPersonal(isPersonal);
					res = this.resourceService.add(res);
					addCatalog(relateAppId, res.getId(), res.getObjectId(),
							res.getResType(), vo);
				}
			}
		}
	}

	@RequestMapping(value = "/myResource")
	public String myResource(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@CurrentUser UserInfo user) {

		page.setPageSize(5);

		String index = request.getParameter("index");
		String title = request.getParameter("title");
		String resType = request.getParameter("resType");
		String personType = request.getParameter("personType");
		String isFaved = request.getParameter("isFaved");
		Integer faved=null;
		ResourceCondition condition=new ResourceCondition();

		//共享资源（校本资源）
		if (ContansOfResource.SCHOOLRESOURCE.equals(personType)) {
			condition.setIsPersonal(false);
			if(resType!=null && !resType.isEmpty()){
				condition.setResType(Integer.valueOf(resType));
			}
			if("true".equals(isFaved)){
				faved=1;
				condition.setUserId(user.getUserId());
			}
		}else{
			//个人资源
			condition.setUserId(user.getUserId());
		}

		//是否收藏的资源
		condition.setFavCount(faved);
		condition.setTitle(title);

		List<Resource> resourceList=this.easyResouceService.pagingFindBy(page,condition);

		request.setAttribute("resList", resourceList);
		request.setAttribute("resType", resType);
		request.setAttribute("personType", personType);
		request.setAttribute("isFaved", isFaved);
		if (index != null && !"".equals(index)) {
			return viewBasePath + myresourcePath + "/myResourceIndex";
		} else {
			return viewBasePath + myresourcePath + "/myResourceList";
		}
	}

	@RequestMapping(value = "/favResource")
	public String favResource(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@CurrentUser UserInfo user) {
		String index = request.getParameter("index");
		String title = request.getParameter("title");
		String resType = request.getParameter("resType");
		String personType = request.getParameter("personType");
		String dm = request.getParameter("dm");
		page.setPageSize(5);
		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
		// String playUrl = "";
		List<Resource> resources = new ArrayList<Resource>();
		if (resType != null && !resType.equals("")) {
			resources = this.resourceService.findMyFavResource(
					user.getUserId(), Integer.valueOf(resType), title, page,
					Order.desc("create_date"));
		} else {
			resources = this.resourceService.findMyFavResource(
					user.getUserId(), null, title, page,
					Order.desc("create_date"));
		}
		list = findVoByType(resources);
		String Url = "/myResource";
		if (ContansOfResource.SHARERESOURCE.equals(personType)) {
			Url = "/myResource/myShare";
		} else if (ContansOfResource.FAVRESOURCE.equals(personType)) {
			Url = "/favResource";
		}
		// request.setAttribute("playUrl", playUrl);
		request.setAttribute("resList", list);
		request.setAttribute("resType", resType);
		request.setAttribute("personType", personType);
		request.setAttribute("Url", Url);
		request.setAttribute("dm", dm);
		if (index != null && !"".equals(index)) {
			return viewBasePath + myresourcePath + "/myResourceIndex";
		} else {
			return viewBasePath + myresourcePath + "/myResourceList";
		}
	}

	@RequestMapping(value = "/uploadIndex")
	public String uploadIndex(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		String resType = request.getParameter("resType");
		String resourceType = request.getParameter("resourceType");
		String dm = request.getParameter("dm");
		if (resType != null && !"".equals(resType)) {
			request.setAttribute("resType", resType);
		}
		if (resourceType != null && !"".equals(resourceType)) {
			if(resourceType.equals(ContansOfResource.PUBLICRESOURCE)){
				resourceType = ContansOfResource.PUBLICRESOURCE;
			}else if(resourceType.equals(ContansOfResource.SCHOOLRESOURCE)){
				resourceType = ContansOfResource.SCHOOLRESOURCE;
			}
		} else {
			resourceType = ContansOfResource.PUBLICRESOURCE;
		}

//		List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
//		request.setAttribute("slist",slist);
		//request.setAttribute("dm", dm);
		request.setAttribute("resType",resType);
		request.setAttribute("resourceType", resourceType);
		return viewBasePath + commonPath + "/uploadIndex";
	}

	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		String publish = request.getParameter("publish");
		String resType = request.getParameter("resType");
		String dm = request.getParameter("dm");
		// 区分校本资源和个人资源
		String resourceType = request.getParameter("resourceType");
		if (resType != null && !"".equals(resType)) {
			request.setAttribute("resType", resType);
		}
		if (publish != null && !"".equals(publish)) {
			request.setAttribute("publish", publish);
		}

		List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
		request.setAttribute("slist",slist);
		request.setAttribute("dm", dm);
		request.setAttribute("resourceType", resourceType);
		return viewBasePath + commonPath + "/upload";
	}


	@RequestMapping(value = "/public/uploadIndex")
	public String uploadIndex1(HttpServletRequest request,
							   HttpServletResponse response, @CurrentUser UserInfo user) {
		String resType = request.getParameter("resType");
		String resourceType = request.getParameter("resourceType");
		String dm = request.getParameter("dm");
		if (resType != null && !"".equals(resType)) {
			request.setAttribute("resType", resType);
		}
		if (resourceType != null && !"".equals(resourceType)) {
			if(resourceType.equals(ContansOfResource.PUBLICRESOURCE)){
				resourceType = ContansOfResource.PUBLICRESOURCE;
			}else if(resourceType.equals(ContansOfResource.SCHOOLRESOURCE)){
				resourceType = ContansOfResource.SCHOOLRESOURCE;
			}
		} else {
			resourceType = ContansOfResource.PUBLICRESOURCE;
		}

		List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
		request.setAttribute("slist",slist);
		request.setAttribute("dm", dm);
		request.setAttribute("resourceType", resourceType);
		return viewBasePath + commonPath + "/respublic_uploadIndex";
	}

	@RequestMapping(value = "/public/upload")
	public String publicUpload(HttpServletRequest request,
							   HttpServletResponse response, @CurrentUser UserInfo user) {
		String publish = request.getParameter("publish");
		String resType = request.getParameter("resType");
		String dm = request.getParameter("dm");
		// 区分校本资源和个人资源
		String resourceType = request.getParameter("resourceType");
		if (resType != null && !"".equals(resType)) {
			request.setAttribute("resType", resType);
		}
		if (publish != null && !"".equals(publish)) {
			request.setAttribute("publish", publish);
		}

		List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
		request.setAttribute("slist",slist);
		request.setAttribute("dm", dm);
		request.setAttribute("resourceType", resourceType);
		request.setAttribute("user", user);
		return viewBasePath + commonPath + "/respublic_upload";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {

		String id = request.getParameter("Id");
		easyResouceService.deleteById(Integer.valueOf(id));
		return DeleteStatus.DELETE_SUCCESS + "";
	}

	@RequestMapping(value = "/verifyDelete")
	public String verifyDelete(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {

		String Id = request.getParameter("Id");
		String personType = request.getParameter("personType");
		String verify = "";
		String qbverify = "";
		Resource resResource = new Resource();
		if (personType.equals(ContansOfResource.PERSONRESOURCE)) {
			Resource r = this.resourceService.findResourceById(Integer
					.valueOf(Id));
			verify = r.getVerify();
			r.setVerify(ResourceCondition.DEFAULT_VERIFY_NO);
			this.resourceService.modify(r);
			// 如果标志为个人资源共享中的时候，获得校本资源的共享中
			if (verify.equals(ResourceCondition.DEFAULT_PERSON_PROCESS)) {
				verify = ResourceCondition.DEFAULT_SHARE_PROCESS;
				qbverify = ResourceCondition.REGION_SHARE_PROCESS;
			} else {
				// 校本资源的共享成功
				verify = ResourceCondition.DEFAULT_VERIFY_SUCCESS;
				qbverify = ResourceCondition.REGION_VERIFY_SUCCESS;
			}
			ResourceCondition resResourceCondition = new ResourceCondition();
			resResourceCondition.setObjectId(r.getObjectId());// 资源名称
			resResourceCondition.setVerify(verify);
			resResourceCondition.setResType(r.getResType());
			resResourceCondition.setUserId(user.getUserId());
			resResourceCondition.setIsPersonal(false);
			List<Resource> resourceList = this.resourceService.findResourceByCondition(resResourceCondition);
			if (resourceList != null && resourceList.size() > 0) {
				resResource = resourceList.get(0);
				resResource.setIsDeleted(true);
				this.resourceService.modify(resResource);
				CatalogResourceCondition crc = new CatalogResourceCondition();
				crc.setResourceId(resResource.getId());
				List<CatalogResource> crlist = this.catalogResourceService
						.findCatalogResourceByCondition(crc);
				if (crlist != null && crlist.size() != 0) {
					CatalogResource cr = new CatalogResource();
					if (crlist != null && crlist.size() > 0) {

					}
					cr = crlist.get(0);
					this.catalogResourceService.remove(cr);
				}
			}
			//删除区本共享资源
			resResourceCondition.setVerify(qbverify);
			resourceList = this.resourceService.findResourceByCondition(resResourceCondition);
			if (resourceList != null && resourceList.size() > 0) {
				resResource = resourceList.get(0);
				resResource.setIsDeleted(true);
				this.resourceService.modify(resResource);
				CatalogResourceCondition crc = new CatalogResourceCondition();
				crc.setResourceId(resResource.getId());
				List<CatalogResource> crlist = this.catalogResourceService
						.findCatalogResourceByCondition(crc);
				if (crlist != null && crlist.size() != 0) {
					CatalogResource cr = new CatalogResource();
					if (crlist != null && crlist.size() > 0) {

					}
					cr = crlist.get(0);
					this.catalogResourceService.remove(cr);
				}
			}
		} else {
			Resource r = this.resourceService.findResourceById(Integer
					.valueOf(Id));
			verify = r.getVerify();
			r.setIsDeleted(true);
			this.resourceService.modify(r);
			if (verify.equals(ResourceCondition.DEFAULT_SHARE_PROCESS)) {
				verify = ResourceCondition.DEFAULT_PERSON_PROCESS;
			} else {
				// 个人资源的共享成功
				verify = ResourceCondition.DEFAULT_VERIFY_YES;
			}
			ResourceCondition resResourceCondition = new ResourceCondition();
			resResourceCondition.setObjectId(r.getObjectId());// 资源名称
			resResourceCondition.setVerify(verify);
			resResourceCondition.setResType(r.getResType());
			resResourceCondition.setUserId(user.getUserId());
			resResourceCondition.setIsPersonal(true);
			List<Resource> resourceList = this.resourceService
					.findResourceByCondition(resResourceCondition);
			if (resourceList != null && resourceList.size() == 1) {
				resResource = resourceList.get(0);
				resResource.setVerify(verify);
				this.resourceService.modify(resResource);
			}
		}

		// // crc.setAppId(appId);
		// crc.setResourceId(resId);
		// crc.setResourceType(Integer.valueOf(resType));
		// List<CatalogResource> crList =
		// this.catalogResourceService.findCatalogResourceByCondition(crc, null,
		// null);
		// if (crList.size() > 0) {
		// CatalogResource cr = crList.get(0);
		// this.catalogResourceService.remove(cr);
		// }

		return null;
	}

	/**积分*/
	@RequestMapping(value = "/verifyAdd")
	public String verifyAdd(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {

		// resource objectID
		String resId = request.getParameter("resId");
		String integral = request.getParameter("integral");
		// resource id
		String id = request.getParameter("Id");
		String resType = request.getParameter("resType");
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		String textbookId = request.getParameter("textbookId");
		String catalogEnd = request.getParameter("catalogEnd");

		String stageCode = request.getParameter("stageCode");
		String subjectCode = request.getParameter("subjectCode");
		String gradeCode = request.getParameter("gradeCode");
		String version = request.getParameter("version");
		String volumn = request.getParameter("volumn");

		String stageName = request.getParameter("stageName");
		String subjectName = request.getParameter("subjectName");
		String versionName = request.getParameter("versionName");
		String shareType = request.getParameter("shareType");
		Integer relateAppId = this.getRelateApp(request);
		String suffix = "";
		String gradeName = "";
		String volumnName = "";
		if (gradeCode != null && !"".equals(gradeCode)) {
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setCode(gradeCode);

			List<Grade> gradeList = this.jcGradeService.findGradeByCondition(
					gradeCondition, null, null);
			if (gradeList != null && gradeList.size() > 0) {
				gradeName = gradeList.get(0).getName();
			} else {
				gradeName = "";
			}
		} else {
			gradeName = "";
		}

		if (volumn != null && !"".equals(volumn)) {
			TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
			textbookVolumnCondition.setCode(volumn);
			List<TextbookVolumn> volumnList = this.jcTextbookVolumnService
					.findTextbookVolumnByCondition(textbookVolumnCondition);
			if (volumnList != null && volumnList.size() > 0) {
				volumnName = volumnList.get(0).getName();
			} else {
				volumnName = "";
			}
		} else {
			volumnName = "";
		}
		ImportCatalogVo vo = new ImportCatalogVo();
		vo.setStageCode(stageCode);
		vo.setSubjectCode(subjectCode);
		vo.setGradeCode(gradeCode);
		vo.setGradeCode(gradeCode);
		vo.setVersionCode(version);
		vo.setVolumnCode(volumn);
		if(integral!=null && !"".equals(integral)) {
			vo.setIntegral(Integer.parseInt(integral));
		} else {
			vo.setIntegral(0);
		}

		vo.setStageName(stageName);
		vo.setSubjectName(subjectName);
		vo.setGradeName(gradeName);
		vo.setVersionName(versionName);
		vo.setVolumnName(volumnName);

		if (catalogEnd != null && !catalogEnd.equals("")) {
			vo.setCatalogCode(catalogEnd);
		} else if (textbookId != null && Integer.parseInt(textbookId) > 0) {
			TextbookCatalogCondition jcCondition = new TextbookCatalogCondition();
			jcCondition.setTestBookId(Integer.parseInt(textbookId));
			jcCondition.setParentId(TextbookCatalogCondition.DEFAULT_PARENT_ID);
			List<TextbookCatalog> catalogList = this.jcTextbookCatalogService
					.findTextbookCatalogByCondition(jcCondition);
			if (catalogList != null && catalogList.size() > 0) {
				TextbookCatalog textbookCatalog = catalogList.get(0);
				vo.setCatalogCode(textbookCatalog.getCode());
			}
		}
		// noSuffixTitle = getTitle(resId, resType);//获取标题
		suffix = getSuffix(resId, resType);// 获取后缀
		vo.setShareType(shareType);
		this.addResource(relateAppId, Integer.parseInt(resType), resId, title,
			 	description, relateAppId, "", user, suffix,
				ContansOfResource.SHARERESOURCE, vo);
		Resource resource = this.resourceService.findResourceById(Integer
				.valueOf(id));
		resource.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
		this.resourceService.modify(resource);
		return null;
	}


	/**
	 * 校管后台管理统计
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/count/schoolCount")
	public String schoolCount(@CurrentUser UserInfo user) throws ParseException {
		if(!isSchoolLeader(user)){
			return "403";
		}

		return "/resource/count/school_count";
	}

	/**
	 * 校本资源资源量统计，只限校管管可以访问
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/count/countSchoolAll")
	public String countSchoolAll(@CurrentUser UserInfo user,@RequestParam(value = "stageCode") String stageCode,Model model){
		if(!isSchoolLeader(user)){
			return "403";
		}
		Long schoolAllCountSize = (Long) baseRedisCache.getCacheObject(user.getSchoolId()+"_"+stageCode+"_schoolAllCountSize");
		StringBuffer schoolAllResJsonStr = (StringBuffer) baseRedisCache.getCacheObject(user.getSchoolId()+"_"+stageCode+"_schoolAllResJsonStr");

		if(schoolAllCountSize == null || schoolAllResJsonStr == null){
			schoolAllResJsonStr = new StringBuffer();
			Set<String> subjects = resTextbookService.findSubjectByStageAndLibId(stageCode, user.getSchoolId());

			List<platform.education.generalcode.model.Subject> subjectList = jcSubjectService.findAll();
			Map<String,String> subjectMap = new HashMap<String, String>();
			for (platform.education.generalcode.model.Subject subject : subjectList) {
				subjectMap.put(subject.getCode() + "",subject.getName());
			}

			ResourceVo resourceVo = new ResourceVo();
			ResourceLibrary resourceLibrary = resourceLibraryService.findByAppIdAndOwnerId(1, user.getSchoolId());
			String libraryId = "";
			if(resourceLibrary != null){
				libraryId = "'"+resourceLibrary.getUuid()+"'";
				resourceVo.setLibraryId(libraryId);
			}
			resourceVo.setVerify("res_school");
			resourceVo.setStageCode(stageCode);
			schoolAllCountSize = resourceService.countResByMoreCondition(resourceVo);

			Long schoolstageCodeResCounts;
			for (String subjectCode : subjects) {
				resourceVo.setSubjectCode(subjectCode);
				schoolstageCodeResCounts = resourceService.countResByMoreCondition(resourceVo);
				schoolAllResJsonStr.append("{ name:'" + subjectMap.get(subjectCode) + "', y:" + schoolstageCodeResCounts + "},");
			}

			if(schoolAllResJsonStr.length() > 0){
				schoolAllResJsonStr = schoolAllResJsonStr.deleteCharAt(schoolAllResJsonStr.length() - 1);
			}

			baseRedisCache.setCacheObject(user.getSchoolId()+"_"+stageCode+"_schoolAllCountSize",schoolAllCountSize);
			baseRedisCache.setCacheObject(user.getSchoolId()+"_"+stageCode+"_schoolAllResJsonStr",schoolAllResJsonStr);
		}

		model.addAttribute("schoolAllCountSize",schoolAllCountSize);
		model.addAttribute("schoolAllResJsonStr",schoolAllResJsonStr);
		return "/resource/count/school_allCount";
	}

	/**
	 * 后台统计更新资源数量
	 * @param countType 统计类型（week、month、year）
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/count/coutUpdate")
	public String coutUpdate(@CurrentUser UserInfo user,@RequestParam(value = "countType", required = false) String countType,
							 Model model) throws ParseException {
		if(!isSchoolLeader(user)){
			return "403";
		}
		List<Long> updateResCountList = (List<Long>) baseRedisCache.getCacheObject(user.getSchoolId()+"_"+countType+"_updateResCountList");

		if(updateResCountList == null){
			updateResCountList = new ArrayList<Long>();
			ResourceVo resourceVo = new ResourceVo();
			resourceVo.setVerify("res_school");
			ResourceLibrary resourceLibrary = resourceLibraryService.findByAppIdAndOwnerId(1,user.getSchoolId());
			String libraryId = "";
			if(resourceLibrary != null){
				libraryId = "'"+resourceLibrary.getUuid()+"'";
				resourceVo.setLibraryId(libraryId);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			if("week".equals(countType)){
				Calendar cld = Calendar.getInstance();
				int day = cld.get(Calendar.DAY_OF_WEEK);
				cld.add(Calendar.DATE, -day+1);
				for(int i = 0 ;i < 7 ; i++){
					resourceVo.setCreateDate(cld.getTime());
					cld.add(Calendar.DATE, 1);
					resourceVo.setModifyDate(cld.getTime());
					System.out.println(sdf.format(resourceVo.getCreateDate())+"----------"+sdf.format(resourceVo.getModifyDate()));
					Long updateResCount = resourceService.countResByMoreCondition(resourceVo);
					updateResCountList.add(updateResCount);
				}
			}else if("month".equals(countType)){
				Calendar cld = Calendar.getInstance();
				cld.set(Calendar.DAY_OF_MONTH, 1);// 设定当前时间为每月一号
				//dayOfWeek：1号是星期几

				// 当前日历的天数上-1变成最大值 , 此方法不会改变指定字段之外的字段
				cld.roll(Calendar.DAY_OF_MONTH, -1);
				//maxDay:一个月有几天
				int maxDay = cld.get(Calendar.DAY_OF_MONTH);
				//当前月份是几月
				int curMonth = cld.get(Calendar.MONTH);
				cld.set(Calendar.DAY_OF_MONTH, 1);// 设定当前时间为每月一号
				int dayOfWeek;
				Date beginTime,endTime;
				while (maxDay - (cld.get(Calendar.DAY_OF_MONTH)) > 0 && curMonth == cld.get(Calendar.MONTH)){
					beginTime = cld.getTime();
					if(maxDay - (cld.get(Calendar.DAY_OF_MONTH)) > 6){
						dayOfWeek = cld.get(Calendar.DAY_OF_WEEK);
						cld.add(Calendar.DATE, 7-dayOfWeek);
						endTime = cld.getTime();
						cld.add(Calendar.DATE, 1);

					}else {
						cld.add(Calendar.DATE, maxDay - (cld.get(Calendar.DAY_OF_MONTH)));
						endTime = cld.getTime();
					}

					resourceVo.setCreateDate(beginTime);
					resourceVo.setModifyDate(endTime);
					Long updateResCount = resourceService.countResByMoreCondition(resourceVo);
					updateResCountList.add(updateResCount);
				}
			}else if("year".equals(countType)){
				sdf = new SimpleDateFormat("yyyy-MM");
				Date date = new Date();
				String time = sdf.format(date);
				String[] split = time.split("-");

				for (int i = 1 ; i <=12 ; i++){
					//年-月
					String begin = split[0] + "-" + i;
					String end = split[0] + "-" + (i+1);
					resourceVo.setCreateDate(sdf.parse(begin));
					resourceVo.setModifyDate(sdf.parse(end));
					Long updateResCount = resourceService.countResByMoreCondition(resourceVo);
					updateResCountList.add(updateResCount);
				}
			}
			baseRedisCache.setCacheObject(user.getSchoolId()+"_"+countType+"_updateResCountList",updateResCountList);
		}


		model.addAttribute("countType",countType);
		model.addAttribute("updateResCountList", JSON.toJSONString(updateResCountList));
		model.addAttribute("updateResCountSize",updateResCountList.size());

		return "/resource/count/schoolUpdateCount";
	}

	/**
	 * 后台统计不同类型的资源数量
	 * @param resourceVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/count/countResTypeSize")
	public String countResTypeToCondition(@CurrentUser UserInfo user,@ModelAttribute("condition") ResourceVo resourceVo,Model model ){
		if(!isSchoolLeader(user)){
			return "403";
		}
		String stageCode = resourceVo.getStageCode();
		String subjectCode = resourceVo.getSubjectCode();
		String versionCode = resourceVo.getVersionCode();

		String key = user.getSchoolId()+"_"+stageCode + "_" + subjectCode + "_" +versionCode + "_resTypeCountList";
		List<Long> resTypeCountList = (List<Long>) baseRedisCache.getCacheObject(key);
		if(resTypeCountList == null){
			resourceVo.setVerify("res_school");
			ResourceLibrary resourceLibrary = resourceLibraryService.findByAppIdAndOwnerId(1, user.getSchoolId());
			String libraryId = "";
			if(resourceLibrary != null){
				libraryId = "'"+resourceLibrary.getUuid()+"'";
				resourceVo.setLibraryId(libraryId);
			}

			resTypeCountList = new ArrayList<Long>();
			Long resCounts;
			for(int i = 1 ; i < 7 ; i++){
				resourceVo.setResType(i);
				resCounts = resourceService.countResByMoreCondition(resourceVo);
				resTypeCountList.add(resCounts != null ? resCounts : 0);
			}
			baseRedisCache.setCacheObject(key,resTypeCountList);
		}

		model.addAttribute("resTypeCountList",JSON.toJSONString(resTypeCountList));
		return "/resource/count/resTypeCount";
	}

	@RequestMapping(value = "/count/updateSchoolCount")
	@ResponseBody
	public String updateSchoolCount(@CurrentUser UserInfo user,Model model) throws ParseException {
		baseRedisCache.evict(user.getSchoolId()+"_"+"week_updateResCountList");
		baseRedisCache.evict(user.getSchoolId()+"_"+"month_updateResCountList");
		baseRedisCache.evict(user.getSchoolId()+"_"+"year_updateResCountList");

		//将最新的更新统计保存到redis中
		this.coutUpdate(user,"week",model);
		this.coutUpdate(user,"month",model);
		this.coutUpdate(user,"year",model);

		ResTextbookCondition textbookCondition = new ResTextbookCondition();
		List<ResTextbook> textbookList = resTextbookService.findResTextbookByCondition(textbookCondition);
		Set<String> stageSet = new HashSet<String>();
		ResourceVo resourceVo = new ResourceVo();
		String stageCode = null;
		String subjectCode = null;
		String versionCode = null;
		//将最新的类型资源统计保存到redis中
		for (ResTextbook resTextbook : textbookList) {
			stageCode = resTextbook.getStageCode();
			subjectCode = resTextbook.getSubjectCode();
			versionCode = resTextbook.getVersion();
			String key = user.getSchoolId()+"_"+stageCode + "_" + subjectCode + "_" +versionCode + "_resTypeCountList";
			baseRedisCache.evict(key);
			resourceVo.setStageCode(stageCode);
			resourceVo.setSubjectCode(subjectCode);
			resourceVo.setVersionCode(versionCode);
			this.countResTypeToCondition(user,resourceVo,model);
			stageSet.add(stageCode);
		}

		//将最新的资源总量统计保存到redis中
		for (String stage : stageSet) {
			baseRedisCache.evict(user.getSchoolId()+"_"+stage+"_schoolAllCountSize");
			baseRedisCache.evict(user.getSchoolId()+"_"+stage+"_schoolAllResJsonStr");
			this.countSchoolAll(user,stage,model);
		}
		return "success";
	}



	@RequestMapping(value = "/school/getStageByTextbookCondition")
	@ResponseBody
	public Map<String,String> getStageByTextbookCondition(@CurrentUser UserInfo user){
		ResTextbookCondition textbookCondition = new ResTextbookCondition();
		textbookCondition.setResourceLibId(user.getSchoolId());
		List<ResTextbook> textbookList = resTextbookService.findResTextbookByCondition(textbookCondition);
		Set<String> stageSet = new HashSet<String>();
		for (ResTextbook textbook : textbookList) {
			stageSet.add(textbook.getStageCode());
		}
		Map<String,String> stageMap = new HashMap<String, String>();
		for (String stageCode : stageSet) {
			Stage stage = jcStageService.findStageByCode(stageCode);
			if(stage != null){
				stageMap.put(stage.getCode(),stage.getName());
			}
		}
		return stageMap;
	}

//	@RequestMapping(value = "/school/getSubjectByTextbookCondition")
//	@ResponseBody
//	public List<platform.education.generalcode.model.Subject> getSubjectByTextbookCondition(@CurrentUser UserInfo user,@ModelAttribute("condition")ResTextbookCondition textbookCondition,
//																							Model model){
//		textbookCondition.setResourceLibId(user.getSchoolId());
//		List<ResTextbook> textbookList = resTextbookService.findResTextbookByCondition(textbookCondition);
//		Set<String> subjectSet = new HashSet<String>();
//		for (ResTextbook textbook : textbookList) {
//			subjectSet.add(textbook.getSubjectCode());
//		}
//		String [] stageCodeList = subjectSet.toArray(new String[subjectSet.size()]);
//		List<platform.education.generalcode.model.Subject> subjectList = new ArrayList<platform.education.generalcode.model.Subject>();
//		if(stageCodeList.length > 0) {
//			subjectList = jcSubjectService.findSubjectByCodeBatch(stageCodeList);
//		}
//		return subjectList;
//	}
//
//	@RequestMapping(value = "/school/getVersionByTextbookCondition")
//	@ResponseBody
//	public List<TextbookVersion> getVersionByTextbookCondition(@CurrentUser UserInfo user,@ModelAttribute("condition")ResTextbookCondition textbookCondition,
//															   Model model){
//		textbookCondition.setResourceLibId(user.getSchoolId());
//		List<ResTextbook> textbookList = resTextbookService.findResTextbookByCondition(textbookCondition);
//		Set<String> versionSet = new HashSet<String>();
//		for (ResTextbook textbook : textbookList) {
//			versionSet.add(textbook.getVersion());
//		}
//		List<TextbookVersion> textbookVersionList = new ArrayList<TextbookVersion>();
//		String [] versionCodeList = versionSet.toArray(new String[versionSet.size()]);
//		if(versionCodeList.length > 0){
//			textbookVersionList = jcTextbookVersionService.findTextbookVersionBatch(versionCodeList,null,null,null);
//		}
//		return textbookVersionList;
//	}

	/**
	 * 获取后缀
	 *
	 * @param resId
	 * @param resType
	 * @return
	 */
	private String getSuffix(String resId, String resType) {
		String suffix = "";
		if (resType != null && !"".equals(resType)) {
			Integer typeint = Integer.parseInt(resType);
			if (ResourceType.EXAM == typeint) {
				Exam exam = this.examService.findExamByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						exam.getEntityId()).getExtension();
			} else if (ResourceType.HOMEWORK == typeint) {
				Homework homework = homeworkService.findHomeworkByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						homework.getEntityId()).getExtension();
			} else if (ResourceType.LEARNING_DESIGN == typeint) {
				LearningDesign learningDesign = learningDesignService
						.findLearningDesignByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						learningDesign.getEntityId()).getExtension();
			} else if (ResourceType.MATERIAL == typeint) {
				Material material = materialService.findMaterialByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						material.getEntityId()).getExtension();
			} else if (ResourceType.TEACHING_PLAN == typeint) {
				TeachingPlan teachingPlan = teachingPlanService
						.findTeachingPlanByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						teachingPlan.getEntityId()).getExtension();
			} else if (ResourceType.MICRO == typeint || ResourceType.WRITE_TYPE_MICRO == typeint) {
				MicroLesson micro = microLessonService
						.findMicroLessonByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						micro.getEntityId()).getExtension();
			} else if (ResourceType.LEARNING_PLAN == typeint) {
				LearningPlan learningPlan = learningPlanService
						.findLearningPlanByUuid(resId);
				suffix = this.entityFileService.findFileByUUID(
						learningPlan.getEntityId()).getExtension();
			}
		}
		return suffix;
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
		String personType = request.getParameter("personType");
		String resType = request.getParameter("resType");
		String id = request.getParameter("id");
		String dm = request.getParameter("dm");
		CatalogResource cr = new CatalogResource();
		Resource r = this.resourceService.findResourceById(Integer.valueOf(id));
		if (r != null && !personType.equals(ContansOfResource.PERSONRESOURCE)) {
			CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
			catalogResourceCondition.setResourceId(Integer.valueOf(id));
			List<CatalogResource> lists = this.catalogResourceService
					.findCatalogResourceByCondition(catalogResourceCondition);
			if (lists != null && lists.size() != 0) {
				cr = lists.get(0);
			}
			if (cr.getCatalogCode() != null) {
				ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
				resTextbookCatalogCondition.setCode(cr.getCatalogCode());
				List<ResTextbookCatalog> textbookCatalogList = resTextbookCatalogService
						.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
				if (textbookCatalogList != null && textbookCatalogList.size() > 0) {
					request.setAttribute("textbookCatalog",
							textbookCatalogList.get(0));
				}
			}
		}
		if(r.getResType()==ResourceType.EXAM){

		//List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
//		Exam exam=this.examService.findExamByUuid(r.getObjectId());
//		request.setAttribute("code",exam.getSubjectCode());
		//request.setAttribute("slist",slist);
		}
		request.setAttribute("personType", personType);
		request.setAttribute("cr", cr);
		request.setAttribute("item", r);
		request.setAttribute("id", id);
		request.setAttribute("resType", resType);
		request.setAttribute("dm", dm);
		return viewBasePath + myresourcePath + "/myResourceEdit";
	}

	@RequestMapping(value = "/public/edit")
	public String edit1(HttpServletRequest request, HttpServletResponse response,
						@ModelAttribute("condition") ResourceVoCondition condition,
						@CurrentUser UserInfo user) {
		String resKind = request.getParameter("resKind");
		String resType = request.getParameter("resType");
		String personType = request.getParameter("personType");
		String id = request.getParameter("id");
		String userType = request.getParameter("userType");
		Resource r = this.resourceService.findResourceById(Integer.valueOf(id));
		if(r.getResType()==ResourceType.EXAM){

			List<Subject> slist = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
			Exam exam=this.examService.findExamByUuid(r.getObjectId());
			request.setAttribute("code",exam.getSubjectCode());
			request.setAttribute("slist",slist);
		}
		request.setAttribute("item", r);
		request.setAttribute("id", id);
		request.setAttribute("resType", resType);
		request.setAttribute("resKind", resKind);
		request.setAttribute("userType", userType);
		request.setAttribute("personType", personType);
		return viewBasePath + "/resource_edit";
	}

	@RequestMapping(value = "/Play")
	public ModelAndView LearningPlanPlay(@CurrentUser UserInfo user, HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		Resource mrvo = resourceService.findResourceById(Integer.parseInt(id));
		FavoritesCondition fc=new FavoritesCondition();
		fc.setPosterId(user.getUserId());
		fc.setResourceId(mrvo.getId());
		model.addAttribute("faved",favoritesService.count(fc)>0);
		LikesCondition lc=new LikesCondition();
		lc.setPosterId(user.getUserId());
		lc.setResourceId(mrvo.getId());
		model.addAttribute("liked",likesService.count(lc)>0);
		model.addAttribute("res", mrvo);
		return new ModelAndView(viewBasePath + myresourcePath + "/play");
	}

	private void playMicroImpl(HttpServletRequest request, String microId) {
		MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
		MicroLessonVo vo = new MicroLessonVo();
		BeanUtils.copyProperties(ml, vo);
		String bgpictureEntityId = ml.getBgpictureEntityId();
		String jsonEntityId = ml.getJsonEntityId();
		String mediaEntityId = ml.getMediaEntityId();
		String propertyEntityId = ml.getPropertyEntityId();
		String logoEntiyId = ml.getLogoEntityId();
		FileResult jsonFile = fileService.findFileByUUID(jsonEntityId);
		FileResult mediaFile = fileService.findFileByUUID(mediaEntityId);
		FileResult bgpictureFile = fileService
				.findFileByUUID(bgpictureEntityId);
		FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
		FileResult logoFile = fileService.findFileByUUID(logoEntiyId);
		if (jsonFile.getEntityFile() != null) {
			vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
		}
		if (mediaFile.getEntityFile() != null) {
			vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
		}
		if (bgpictureFile.getEntityFile() != null) {
			vo.setBgpicturePath(bgpictureFile.getEntityFile().getRelativePath());
		}
		if (propertyFile.getEntityFile() != null) {
			vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
		}
		if (logoFile.getEntityFile() != null) {
			vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
		}
		// 设置背景文件的路径
		if (ml != null) {
			List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
			vo.setMlbs(vos);
		}
		request.setAttribute("type", MicroType.MICRO_COURSE);
		request.setAttribute("micro", vo);
	}

	private List<MicroLessonBgpictureVo> bgSettings(String uuid) {
		// 设置背景文件的路径
		MicroLessonBgpictureCondition mlbCondition = new MicroLessonBgpictureCondition();
		mlbCondition.setLessonId(uuid);
		List<MicroLessonBgpicture> mlbs = microLessonBgpictureService
				.findMicroLessonBgpictureByCondition(mlbCondition);
		List<MicroLessonBgpictureVo> vos = new ArrayList<MicroLessonBgpictureVo>();
		if (mlbs.size() > 0) {
			for (MicroLessonBgpicture mlb : mlbs) {
				MicroLessonBgpictureVo vo = new MicroLessonBgpictureVo();
				BeanUtils.copyProperties(mlb, vo);
				String entityId = mlb.getEntityId();
				if (entityId != null) {
					FileResult fileResult = fileService
							.findFileByUUID(entityId);
					vo.setBgPath(fileResult.getEntityFile().getRelativePath());
				}
				vos.add(vo);
			}
		}
		return vos;
	}

	@RequestMapping("/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user)
			throws IOException {
		String Id = request.getParameter("Id");
		Resource r = this.resourceService.findResourceById(Integer.valueOf(Id));
//		String entId = null;
		String downloadTitle = r.getTitle();
//		String suffix = null;
//		if (ResourceType.EXAM == r.getResType()) {
//			Exam em = this.examService.findExamByUuid(r.getObjectId());
//			entId = em.getEntityId();
//		} else if (ResourceType.HOMEWORK == r.getResType()) {
//			Homework hw = this.homeworkService.findHomeworkByUuid(r
//					.getObjectId());
//			entId = hw.getEntityId();
//		} else if (ResourceType.LEARNING_DESIGN == r.getResType()) {
//			LearningDesign ld = this.learningDesignService
//					.findLearningDesignByUuid(r.getObjectId());
//			entId = ld.getEntityId();
//		} else if (ResourceType.MATERIAL == r.getResType()) {
//			Material ml = this.materialService.findMaterialByUuid(r
//					.getObjectId());
//			entId = ml.getEntityId();
//		} else if (ResourceType.TEACHING_PLAN == r.getResType()) {
//			TeachingPlan tp = this.teachingPlanService.findTeachingPlanByUuid(r
//					.getObjectId());
//			entId = tp.getEntityId();
//		} else if (ResourceType.MICRO == r.getResType() || ResourceType.WRITE_TYPE_MICRO == r.getResType()) {
//			MicroLesson em = this.microLessonService.findMicroLessonByUuid(r
//					.getObjectId());
//			entId = em.getEntityId();
//		} else if (ResourceType.LEARNING_PLAN == r.getResType()) {
//			LearningPlan lp = this.learningPlanService.findLearningPlanByUuid(r
//					.getObjectId());
//			entId = lp.getEntityId();
//		} else if (ResourceType.OTHER == r.getResType()) {
//			entId = r.getObjectId();
//		}

//		EntityFile ent = this.entityFileService.findFileByUUID(r.getUuid());
//		if (ent != null) {
//			String suffix = ent.getExtension();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// downloadTitle = downloadTitle == null || "".equals(downloadTitle)
			// ? ent.getDiskFileName() : "default";
			String filename = DownloadUtil.encodeFilenameForDownload(request,
					URLDecoder.decode(downloadTitle, "UTF-8"));
			response.addHeader("content-type", "application/x-download");
			response.setContentType("application/x-download");
			//response.setContentLength(ent.getSize().intValue());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
			OutputStream out = response.getOutputStream();
			// Integer flag = this.fileUploadService.downloadEntity(entId, out);
			String result = this.fileService.download(r.getUuid(), out);

//			if (FileStatusCode.DOWNLOAD_SUCCESS.equals(result)) {
//				// 下载成功
//				if (r.getObjectId() != null) {
//					Integer userid = user.getUserId();
//					String resUuid = r.getUuid();
//					/** 用户操作资源记录 */
//					//resourceService.createResourceOperation(resUuid, userid, OperationType.DOWN);
//					/** 用户下载行为记录 */
//					//resourceService.createUserAction(resUuid, userid, ActionType.DONWLOAD);
//				}
//			} else if (FileStatusCode.DOWNLOAD_FAIL_FILE_NOT_EXIT
//					.equals(result)) {
//				// 远程文件不存在
//				// 还没处理好错误信息在前端显示
//			} else if (FileStatusCode.DOWNLOAD_FAIL_STREAM_ERR.equals(result)
//					|| FileStatusCode.CONNECT_SERVER_FAIL.equals(result)) {
//				// 系统错误
//				// 还没处理好错误信息在前端显示
//			}
//		} else {
//			request.setAttribute("flag", EntityIOStatus.ENTITY_NOT_EXIST);
//			return commonPath + "/download_error";
//		}
		//return null;
	}




	// lads创建端
	@RequestMapping(value = "/ladsAuthor")
	public String ladsAuthor(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user)
			throws IOException {
		String lessonId = request.getParameter("lessonId");
		String templateId = request.getParameter("templateId");
		String szxySaveUrl = "emUrl=/resource/ladsSave";
		String ladsAuthorUrl = "/lads/authoring/author?" + szxySaveUrl;
		if (lessonId != null && !"".equals(lessonId)) {
			LearningDesign ld = this.learningDesignService
					.findLearningDesignByUuid(lessonId);
			if (ld != null && ld.getEntityId() != null
					&& !"".equals(ld.getEntityId())) {
				String ldId = ld.getEntityId();
				ladsAuthorUrl = ladsAuthorUrl + "&lessonId=" + lessonId
						+ "&ldId=" + ldId;
			} else {
				// 外部课件不存在或没有绑定lads课件
			}
		} else {
			lessonId = UUIDUtil.getUUID();
			ladsAuthorUrl = ladsAuthorUrl + "&lessonId=" + lessonId;
			if (templateId != null && !"".equals(templateId)) {
				// 使用模版
				ladsAuthorUrl = ladsAuthorUrl + "&templateId=" + templateId;
			}
		}
		return "redirect:" + ladsAuthorUrl;
	}

	@RequestMapping(value = "/ladsSave")
	public String ladsSave(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user)
			throws IOException {
		// 基本信息
		String lessonId = request.getParameter("lessonId");
		String title = request.getParameter("ldTitle");
		String ldId = request.getParameter("ldId");
		Integer relateAppId = this.getRelateApp(request);
		LearningDesign ld;
		if (lessonId != null && !"".equals(lessonId)) {
			ld = this.learningDesignService.findLearningDesignByUuid(lessonId);
			if (ld != null) {
				ld.setTitle(title);
				ld.setEntityId(ldId);
				ld.setModifyDate(new Date());
				this.learningDesignService.modify(ld);
			} else {
				ld = new LearningDesign();
				ld.setUuid(lessonId);
				ld.setAppId(relateAppId);
				ld.setTitle(title);
				ld.setType(LearningDesignType.LADS_LEARNINGDESIGN);
				ld.setEntityId(ldId);
				ld.setUserId(user.getUserId());
				ld.setCreateDate(new Date());
				ld.setModifyDate(new Date());
				this.learningDesignService.add(ld);
			}
		}
		return null;
	}

	private PrintWriter setAjaxResponse(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		return response.getWriter();
	}

	private String addResourceLibrary(HttpServletRequest request) {
		Integer relateAppId = this.getRelateApp(request);
		Integer relateSchoolId = this.getRelateSchool(request);
		ResourceLibrary resourceLibrary = null;
		if (relateAppId != null && relateSchoolId != null) {
			ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
			resourceLibraryCondition.setAppId(relateAppId);
			resourceLibraryCondition.setOwerId(relateSchoolId);
			List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
					.findResourceLibraryByCondition(resourceLibraryCondition);
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// 如果不存在添加对应的记录
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(relateAppId);
				resourceLibraryAdd.setOwerId(relateSchoolId);
				School school = this.schoolService
						.findSchoolById(relateSchoolId);
				if (school != null && school.getName() != null) {
					resourceLibraryAdd.setName(school.getName());
					// resourceLibraryAdd.setRegionCode(school.getDistrict());//如果有地区属性，此为行政区划码
				}
				resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
				resourceLibrary = this.resourceLibraryService
						.add(resourceLibraryAdd);
			}
		}
		if (resourceLibrary != null) {
			return resourceLibrary.getUuid();
		}
		return null;
	}

	/**
	 * 共享页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shareIndex")
	public String share(HttpServletRequest request) {
		String Path = "/resource_share";
		String id = request.getParameter("id");
		String personType = request.getParameter("personType");
		Resource r = this.resourceService.findResourceById(Integer.valueOf(id));
		if(r.getResType()==ResourceType.EXAM){
		Exam exam=this.examService.findExamByUuid(r.getObjectId());
		request.setAttribute("code",exam.getSubjectCode());
		}
		request.setAttribute("item", r);
		request.setAttribute("personType", personType);
		return viewBasePath + Path;
	}

	/**
	 * 我的共享
	 *
	 * @param request
	 * @param response
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/myResource/myShare")
	public String myShare(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("page") Page page,
			@CurrentUser UserInfo user) {
		String index = request.getParameter("index");
		String resType = request.getParameter("resType");
		String personType = request.getParameter("personType");
		String title = request.getParameter("title");
		String dm = request.getParameter("dm");
		List<Resource> resources = new ArrayList<Resource>();
		// String playUrl="";
		if (resType != null && !resType.equals("")) {
			resources = this.resourceService.findMyShareResource(
					user.getUserId(), Integer.valueOf(resType), title, page,
					Order.desc("create_date"));
		} else {
			resources = this.resourceService.findMyShareResource(
					user.getUserId(), null, title, page,
					Order.desc("create_date"));
		}
		List<MyResourceVo> vos = new ArrayList<MyResourceVo>();
		vos = findVoByType(resources);
		String Url = "/myResource";
		if (ContansOfResource.SHARERESOURCE.equals(personType)) {
			Url = "/myResource/myShare";
		} else if (ContansOfResource.FAVRESOURCE.equals(personType)) {
			Url = "/favResource";
		}
		request.setAttribute("Url", Url);
		// request.setAttribute("playUrl", playUrl);
		request.setAttribute("resList", vos);
		request.setAttribute("resType", resType);
		request.setAttribute("personType", personType);
		request.setAttribute("dm", dm);
		if (index != null && !"".equals(index)) {
			return viewBasePath + myresourcePath + "/myResourceIndex";
		} else {
			return viewBasePath + myresourcePath + "/myResourceList";
		}
	}

	/**
	 * 通过资源集合和类型封装
	 *
	 * @param resourceList
	 * @return
	 */
	private List<MyResourceVo> findVoByType(List<Resource> resourceList) {
		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
		for (Resource r : resourceList) {
			MyResourceVo mrvo = new MyResourceVo();
			EntityFile ent = new EntityFile();
			mrvo.setResEnt(r);
			if (ResourceType.EXAM == r.getResType()) {
				Exam em = new Exam();
				em = this.examService.findExamByUuid(r.getObjectId());
				if (em != null) {

					ent = this.entityFileService.findFileByUUID(em
							.getEntityId());
				}
			} else if (ResourceType.HOMEWORK == r.getResType()) {
				Homework em = new Homework();
				em = this.homeworkService.findHomeworkByUuid(r.getObjectId());
				mrvo.setResEnt(r);
				if (em != null) {

					ent = this.entityFileService.findFileByUUID(em
							.getEntityId());
				}
			} else if (ResourceType.LEARNING_DESIGN == r.getResType()) {
				LearningDesign em = new LearningDesign();

				em = this.learningDesignService.findLearningDesignByUuid(r
						.getObjectId());
				if (em != null) {
					ent = this.entityFileService.findFileByUUID(em
							.getEntityId());
				}
			} else if (ResourceType.MATERIAL == r.getResType()) {
				Material em = new Material();
				em = this.materialService.findMaterialByUuid(r.getObjectId());
				if (em != null) {
					ent = this.entityFileService.findFileByUUID(em
							.getEntityId());
				}
			} else if (ResourceType.MICRO == r.getResType() || ResourceType.WRITE_TYPE_MICRO == r.getResType()) {
				MicroLesson em = new MicroLesson();
				em = this.microLessonService.findMicroLessonByUuid(r
						.getObjectId());
				if (em != null) {
					ent = this.entityFileService.findFileByUUID(em
							.getEntityId());
				}
			} else if (ResourceType.LEARNING_PLAN == r.getResType()) {
				LearningPlan lp = new LearningPlan();
				lp = this.learningPlanService.findLearningPlanByUuid(r
						.getObjectId());
				if (lp != null) {
					ent = this.entityFileService.findFileByUUID(lp
							.getEntityId());
				}
			} else if (ResourceType.OTHER == r.getResType()) {
				ent = this.entityFileService.findFileByUUID(r.getObjectId());
			} else if (ResourceType.TEACHING_PLAN == r.getResType()) {
				TeachingPlan teachPlan = new TeachingPlan();
				teachPlan = this.teachingPlanService.findTeachingPlanByUuid(r
						.getObjectId());
				if (teachPlan != null) {
					ent = this.entityFileService.findFileByUUID(teachPlan
							.getEntityId());
				}
			}else if(ResResourceType.PAID == r.getResType()){
				ResPaidResource resPaidResource = this.resPaidResourceService.findByUuid(r.getObjectId());
				if (resPaidResource != null) {
					ent = this.entityFileService.findFileByUUID(resPaidResource
							.getEntityId());
				}
			}
			if (ent != null && ent.getThumbnailUrl() != null) {
				mrvo.setThumbnailUrl(ent.getThumbnailUrl());
				System.out.println(ent.getExtension());

				mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
			} else {
				mrvo.setThumbnailUrl("");
			}
			list.add(mrvo);
		}

		return list;
	}

	public void addCatalog(Integer appid, Integer resourceId, String objectId,
			Integer resType, ImportCatalogVo vo) {
		// 教材目录关联
		CatalogResource cr = new CatalogResource();
		cr = this.catalogResourceService.assembCatalog(cr, vo);
		cr.setAppId(appid);
		cr.setResourceId(resourceId);
		cr.setObjectId(objectId);
		cr.setResourceType(resType);
		cr.setCreateDate(new Date());
		cr.setModifyDate(new Date());
		cr = this.catalogResourceService.add(cr);

	}

	@RequestMapping(value = "/mainJson")
    @ResponseBody
    public String getMainJson(HttpServletRequest request, HttpServletResponse response){
    	String jsonPath = request.getParameter("jsonPath");
    	org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(jsonPath, null);
    	String jsonString = "";
    	if(!StringUtils.isEmpty(jsonObject)){
    		jsonString = jsonObject.toString();
    	}
    	return jsonString;
    }

	/**
	 * 打开下载页面并显示积分详情
	 * @param user 当前用户
	 * @param integral 下载所需积分
	 * @param resId 资源id
	 * @return
	 */
	@RequestMapping(value = "/integral")
    public ModelAndView integral(
    		@CurrentUser UserInfo user,
    		@RequestParam("integral")Integer integral,
    		@RequestParam("resId")Integer resId){
		ModelAndView model = new ModelAndView();
		Resource resource = resourceService.findResourceById(resId);
		Integer userId = user.getUserId();
		if(resource.getUserId()-userId==0) {
			model.addObject("owner", 1);
		} else {
			model.addObject("owner", 0);
		}

		ResIntegral resIntegral = resIntegralService.findResIntegralByUserIdAndResId(userId, resId);
		if(resIntegral!=null) {
			model.addObject("downloaded", 1);
		} else {
			model.addObject("downloaded", 0);
		}

    	model.addObject("resId", resId);
    	model.addObject("integral", integral);
    	model.addObject("userId", userId);
    	model.setViewName(structurePath("/resource_integral"));

    	return model;
    }

	/**
	 * 获取文件大小
	 * @param user 当前登录用户
	 * @param resId	资源id
	 * @return
	 */
	@RequestMapping(value = "/getFileSize")
    @ResponseBody
    public Integer getMainJson(
    		@CurrentUser UserInfo user,
    		@RequestParam("resId")Integer resId){
		Resource resource = resourceService.findResourceById(resId);
		if(resource!=null) {
			String entityId = resourceService.getEntityIdByObjectIdAndType(resource.getObjectId(), resource.getResType());
			EntityFile entityFile = entityFileService.findFileByUUID(entityId);
	    	if(entityFile!=null) {
	    		Integer size = entityFile.getSize();
	    		return size;
	    	}
		}
    	return 0;
    }

	/**
	 * 下载资源扣除积分
	 * @param user 当前用户
	 * @param resId 资源id
	 * @param type 操作类型
	 * @return 0积分不足  1成功
	 */
	@RequestMapping(value = "/integral/operation")
	@ResponseBody
    public String integralOperation(
    		@CurrentUser UserInfo user,
    		@RequestParam("resId")Integer resId,
    		@RequestParam("type")String type){
		Resource resource = resourceService.findResourceById(resId);
		/**资源属主*/
		Integer owner = resource.getUserId();
		/**下载者*/
		Integer userId = user.getUserId();
		if(userId==null) {
			return "0";
		}
		ResIntegral resIntegral = resIntegralService.findResIntegralByUserIdAndResId(userId, resId);

		if(resIntegral!=null) {
			return "1";
		} else {
			/**属主下下载者不是同一人则添加操作记录*/
			if(resource.getUserId()-userId!=0) {
				Integer bonus = resource.getIntegral();
				if(bonus==null) {
					bonus = 0;
				}

				ResIntegralUser downloader = resIntegralUserService.findResIntegralUserByUserId(userId);
				if(downloader.getScore()-bonus<0) {
					return "0";
				}

				resIntegral = new ResIntegral();
				resIntegral.setActionId(ActionType.DONWLOAD);
				resIntegral.setBonus(-bonus);
				Date now = new Date();
				resIntegral.setCreateDate(now);
				resIntegral.setModifyDate(now);
				resIntegral.setIsDeleted(false);
				resIntegral.setOpenrationTime(now);
				resIntegral.setResourceId(resId);
				resIntegral.setUserId(userId);
				resIntegralService.add(resIntegral);

				/**下载者扣除相应的分数*/
				ResIntegralUser udownloader = new ResIntegralUser();
				udownloader.setScore(downloader.getScore()-bonus);
				udownloader.setId(downloader.getId());
				udownloader.setCredit(udownloader.getScore());
				resIntegralUserService.modify(udownloader);

				/**属主获取相应的分数*/
				ResIntegralUser resowner = resIntegralUserService.findResIntegralUserByUserId(owner);
				ResIntegralUser uresowner = new ResIntegralUser();
				if(resowner==null) {
					uresowner.setCreateDate(now);
					uresowner.setModifyDate(now);
					uresowner.setCredit(100);
					uresowner.setIsDeleted(false);
					uresowner.setScore(100+bonus);
					uresowner.setUserId(owner);
					resIntegralUserService.add(uresowner);
				} else {
					uresowner.setScore(resowner.getScore()+bonus);
					uresowner.setId(resowner.getId());
					uresowner.setCredit(uresowner.getScore());
				}
				resIntegralUserService.modify(uresowner);
			}
		}
		return "1";
    }

	/**
	 * 访问别人的个人资源首页
	 * @param page 分页参数
	 * @param ownerId 用户的id
	 * @param index 刷新部位标志
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/owner/list")
    public ModelAndView ownerList(
    		@CurrentUser UserInfo user,
    		@ModelAttribute("page") Page page,
    		@RequestParam(value="ownerId", required=true)Integer ownerId,
    		@RequestParam(value="index", defaultValue="index") String index) throws IllegalAccessException, InvocationTargetException{

		ModelAndView model = new ModelAndView();
		model.addObject("userId", ownerId);

		/**查询个人上传的资源*/
		List<Resource> list = new ArrayList<Resource>();
		if(ownerId==null || ownerId==0) {
			list = resourceService.findResourceListByAppIdAndOwnerId(
					SysContants.SYSTEM_APP_ID, SysContants.SYSTEM_OWNER_ID, page);
		} else {
			list = resourceService.findResourceListByUserId(ownerId, page);
		}


		List<ResourceVo> result = new ArrayList<ResourceVo>();
		for (Resource resource : list) {
			ResourceVo vo = new ResourceVo();
			/**属性拷贝*/
			BeanUtils.copyProperties(resource, vo);
			/**导学案的小浏览和其它资源不一样，在这里做处理*/
			if(ResourceType.LEARNING_PLAN.equals(resource.getResType())) {
				handleLearningPlan(vo);
			}
			result.add(vo);
		}
		model.addObject("result", result);

		if("index".equals(index)) {
			/**是否已经关注标志*/
			UserFav userFav = userFavService.findUserFavUnique(user.getUserId(), ownerId);
			if(userFav!=null) {
				model.addObject("fav", true);
			} else {
				model.addObject("fav", false);
			}
			model.setViewName(structurePath("/owner_index"));
		} else {
			model.setViewName(structurePath("/owner_list"));
		}

    	return model;
    }

	private ResourceVo handleLearningPlan(ResourceVo vo) throws IllegalAccessException, InvocationTargetException {
		LearningPlan learningPlan = learningPlanService.findLearningPlanByUuid(vo.getObjectId());
		if(learningPlan!=null && "lp".equals(learningPlan.getEntityId())) {
			vo.setLpId(learningPlan.getId());
		} else {
			String entityId = resourceService.getEntityIdByObjectIdAndType(vo.getObjectId(), ResourceType.LEARNING_PLAN);
			String suffix = resourceService.getFileSuffixByEntityId(entityId);
			if(suffix==null || "".equals(suffix)) {
				return vo;
			}
			vo.setLpId(vo.getId());
			/**因为导学案存在doc格式，在处理doc格式的导学案时，把其当作试卷处理*/
			vo.setResType(ResourceType.EXAM);
		}
		return vo;
	}

	/**
	 * 关注/取消关注他
	 * @param user 当前用户
	 * @param page 分页参数
	 * @param favUserId 关注谁
	 * @param fav 关注还是取消关注标志
	 * @return 1关注成功 2已经关注了 3取消关注成功
	 */
	@RequestMapping(value = "/user/fav")
	@ResponseBody
    public Object userFav(
    		@CurrentUser UserInfo user,
    		@ModelAttribute("page") Page page,
    		@RequestParam(value="favUserId", required=true)Integer favUserId,
    		@RequestParam(value="fav", required=true)Integer fav){
		Integer userId = user.getUserId();
		UserFav userFav = userFavService.findUserFavUnique(userId, favUserId);
		if(fav-0==0) {
			userFavService.remove(userFav);
			return "3";
		}
		if(userFav==null) {
			userFav = new UserFav();
			Date now = new Date();
			userFav.setCreateDate(now);
			userFav.setFavTime(now);
			userFav.setFavUserId(favUserId);
			userFav.setIsDeleted(false);
			userFav.setModifyDate(now);
			userFav.setUserId(userId);
			userFavService.add(userFav);
			return "1";
		} else {
			return "2";
		}
    }

	/**
	 * 我的关注列表
	 * @param user 当前用户
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/user/fav/list")
    public ModelAndView userFavList(
    		@CurrentUser UserInfo user) throws IllegalAccessException, InvocationTargetException{
		ModelAndView model = new ModelAndView();
		List<UserFav> userFavList = userFavService.findMyUserFavList(user.getUserId());

		List<UserFavVo> result = new ArrayList<UserFavVo>(userFavList.size());
		for (UserFav userFav : userFavList) {
			UserFavVo userFavVo = new UserFavVo();
			BeanUtils.copyProperties(userFav, userFavVo);
			School school = schoolService.findSchoolByUserId(userFav.getFavUserId());
			if(school!=null) {
				userFavVo.setSchoolId(school.getId());
				userFavVo.setSchoolName(school.getName());
			} else {
				userFavVo.setSchoolName("无");
			}
			result.add(userFavVo);

		}
		model.addObject("result", result);
		model.setViewName(structurePath(myresourcePath + "/user_fav_list"));
		return model;
    }


	/**
	 * 更新资源评分
	 * @param user 当前用户
	 * @param resId 资源id
	 * @param score 资源评分
	 * @return
	 */
	@RequestMapping(value = "/updateScore")
	@ResponseBody
    public Object updateScore(
    		@CurrentUser UserInfo user,
    		@RequestParam(value="resId", required=true)Integer resId,
    		@RequestParam(value="score", required=true)Integer score){
		Integer userId = user.getUserId();
		Resource resource = resourceService.findResourceById(resId);
		resource.setScore(resource.getScore()+score);
		resource.setScoringCount(resource.getScoringCount()+1);
		resourceService.modify(resource);

		//baseRedisCache.setCacheObject(RedisKey.CR_SCORE_ORDER, null);

		resourceService.createResourceOperation(resource.getUuid(), userId, OperationType.SCORING);

		return "success";
    }


    private Boolean isSchoolLeader(UserInfo user){
		if(user.getUserTypes() == null){
			return false;
		}else if(!user.getUserTypes().contains("2") && !user.getUserTypes().contains("1")){
			return false;
		}
		List<String> roleList = userRoleService.findRoleCodesByUserIdAndSchool(user.getUserId(), user.getSchoolId(), 1);
		Boolean isSchoolLeader = false;
		for (String roleCode : roleList) {
			if(roleCode.equals("SCHOOL_LEADER")){
				isSchoolLeader = true;
			}
		}
		if(user.getUserTypes().contains("2")){
			isSchoolLeader = true;
		}

		return isSchoolLeader;
	}


}
