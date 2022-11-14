package platform.szxyzxx.web.resource.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.exam.contants.ExamType;
import platform.education.exam.model.Exam;
import platform.education.exam.service.ExamService;
import platform.education.exam.vo.ExamCondition;
import platform.education.exam.vo.ExamVo;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.StudentAward;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import platform.education.generalTeachingAffair.vo.StudentAwardVo;
import platform.education.homework.contants.HomeworkType;
import platform.education.homework.model.Homework;
import platform.education.homework.service.HomeworkService;
import platform.education.homework.vo.HomeworkCondition;
import platform.education.homework.vo.HomeworkVo;
import platform.education.learningDesign.contants.LearningDesignType;
import platform.education.learningDesign.contants.LearningPlanType;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.vo.LearningDesignCondition;
import platform.education.learningDesign.vo.LearningDesignVo;
import platform.education.material.contants.MaterialType;
import platform.education.material.model.Material;
import platform.education.material.service.MaterialService;
import platform.education.material.vo.MaterialCondition;
import platform.education.material.vo.MaterialVo;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MicroLessonService;
import platform.education.resource.contants.OperationType;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.model.ResourceOperation;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.FavoritesService;
import platform.education.resource.service.LikesService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.utils.DownloadUtil;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.CatalogResourceCondition;
import platform.education.resource.vo.ImportCatalogVo;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.education.teachingPlan.contants.TeachingPlanType;
import platform.education.teachingPlan.model.TeachingPlan;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.education.teachingPlan.vo.TeachingPlanCondition;
import platform.education.teachingPlan.vo.TeachingPlanVo;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.resource.contants.MyResourceContans;
import platform.szxyzxx.web.resource.vo.MyResourceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
//3.16.0 ---
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private final static String viewBasePath = "/resource";
    private final static String housePath = "/resourcehouse";
    @Autowired
    @Qualifier("microLessonService")
    private MicroLessonService microLessonService;
    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;
    @Autowired
    @Qualifier("learningDesignService")
    private LearningDesignService learningDesignService;
    @Autowired
    @Qualifier("materialService")
    private MaterialService materialService;
    @Autowired
    @Qualifier("examService")
    private ExamService examService;
    @Autowired
    @Qualifier("teachingPlanService")
    private TeachingPlanService teachingPlanService;
    @Autowired
    @Qualifier("favoritesService")
    private FavoritesService favoritesService;
    @Autowired
    @Qualifier("likesService")
    private LikesService likesService;
    
    @Autowired
    @Qualifier("catalogResourceService")
    private CatalogResourceService catalogResourceService;
    @Autowired
    @Qualifier("resourceOperationService")
    private ResourceOperationService resourceOperationService;

    @RequestMapping("/searcher/index")
    public String seacher(@ModelAttribute("condition") ResourceVoCondition condition) {
    	/**转到获取公共资源页面*/
    	if(condition.getIsPublish()==null || condition.getIsPublish()==1) {
    		 return structurePath("/common/resource_seacher");
    	} else {
    		/**转到获取校本资源页面*/
    		return structurePath("/school/resource_seacher");
    	}
    	
    }

    @RequestMapping(value = "/searcher")
    public ModelAndView seacherByCondition(
            @CurrentUser UserInfo user,
            @RequestParam(value = "topType", required = true, defaultValue = "top") String topType,
            @ModelAttribute("condition") ResourceVoCondition condition,
            @ModelAttribute("page") Page page,
            @RequestParam(value = "isPublish") Integer isPublish,
            @ModelAttribute("order") Order order, Model model) {
        page.setPageSize(5);
        if(condition!= null&&condition.getGradeCode()!=null&&"0".equals(condition.getGradeCode())){
        	condition.setGradeCode("");
        }
        Integer appIdCommon = SysContants.SYSTEM_COMMON_RESOURCE_APP_ID;
        Integer appId = SysContants.SYSTEM_APP_ID;
        Integer ownerid= SysContants.SYSTEM_COMMON_RESOURCE_OWNER_ID;
        if(order==null||order.getProperty()==null){
        	order.setProperty("create_date");
        	order.setAscending(false);
        }
        String verify="";
        if(isPublish==0){
        	condition.setRelateAppId(appId);
        	condition.setRelateSchoolId(user.getSchoolId());
        	verify=MyResourceContans.SCHOOLRESOURCE;
        }else{
            condition.setOwnerId(ownerid);
            condition.setAppId(appIdCommon);
        	verify=ResourceCondition.DEFAULT_UPLOAD_YES;
        }
        condition.setVerify(verify);
        conditionFilter(user, condition);
        List<ResourceVo> vos = this.resourceService.findResourceVoByMoreCondition(condition, page, order);
        if(condition.getResType().intValue()==ResourceType.EXAM){
        	vos= this.resourceService.findExpResourceVoByCondition(condition, page, order);
        }
        model.addAttribute("items", vos);
        model.addAttribute("topType", topType);
        // 3.16.0
        return new ModelAndView(structurePath("/common/resource_list"), model.asMap());
    }
    
    @RequestMapping(value = "/indexVerify")
    public ModelAndView indexVerify( @CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ResourceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model
    		) {
      
    	
		String viewPath = null;
		ModelAndView mov = new ModelAndView();
		order.setProperty("id");
		order.setAscending(false);
		List<ResourceVo> resourceList = null;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		resourceLibraryCondition.setOwerId(user.getSchoolId());
		resourceLibraryCondition.setAppId(SysContants.SYSTEM_APP_ID);
		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		
		
		if(resourceLibraryList != null&&resourceLibraryList.size()==1){
			ResourceLibrary resourceLibrary = resourceLibraryList.get(0);
			condition.setLibraryId(resourceLibrary.getUuid());
	
			if(condition.getGradeCode()!=null || condition.getSubjectCode()!=null){
				resourceList =  this.resourceService.findResourceVerifyByCondition(condition, page, order);
			} else {
				resourceList =  this.resourceService.findResourceVerifyPartByCondition(condition, page, order);	
				if(resourceList!=null&&resourceList.size()>0){
					Integer[] resourceIds = new Integer[resourceList.size()];
					for (int i = 0; i < resourceList.size(); i++) {
						resourceIds[i] = resourceList.get(i).getId();
					}
					
					Map<Integer, CatalogResource> map = catalogResourceService.findByResourceIds(resourceIds);
					
					for (ResourceVo vo : resourceList) {
						CatalogResource catalogResource = map.get(vo.getId());
						if(catalogResource!=null) {
							vo.setGradeName(catalogResource.getGradeName());
							vo.setGradeCode(catalogResource.getGradeCode());
							vo.setSubjectCode(catalogResource.getSubjectCode());
							vo.setSubjectName(catalogResource.getSubjectName());
						}
					}
				}
			}
		}else{
			resourceList = new ArrayList<ResourceVo>();
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/common/list");
		} else {
			viewPath = structurePath("/common/resource_verify");
		}
		mov.addObject("resourceList", resourceList);
		mov.setViewName(viewPath);
		return mov;
    	
        
    }

    
	@RequestMapping(value = "/addVerify/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String addVerify(@PathVariable(value = "id") Integer id) {
		
		try {
			Resource resResource = new Resource();
			resResource=this.resourceService.findResourceById(id);
			resResource.setVerify(ResourceCondition.DEFAULT_VERIFY_SUCCESS);
			this.resourceService.modify(resResource);
			ResourceCondition condition=new ResourceCondition();
			condition.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
			condition.setIsPersonal(true);
			condition.setObjectId(resResource.getObjectId());
			condition.setResType(resResource.getResType());
			condition.setUserId(resResource.getUserId());
			condition.setIsDeleted(false);
			Resource resource=this.resourceService.findResourceByCondition(condition).get(0);
			resource.setVerify(ResourceCondition.DEFAULT_VERIFY_YES);
			this.resourceService.modify(resource);
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	@RequestMapping(value = "/deleteVerify/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String deleteVerify(@PathVariable(value = "id") Integer id) {
		
		try {
			Resource resResource = new Resource();
			resResource=this.resourceService.findResourceById(id);
			resResource.setVerify(ResourceCondition.DEFAULT_SHARE_FAIL);
			this.resourceService.modify(resResource);
			ResourceCondition condition=new ResourceCondition();
			condition.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
			condition.setIsPersonal(true);
			condition.setObjectId(resResource.getObjectId());
			condition.setResType(resResource.getResType());
			condition.setIsDeleted(false);
			Resource resource=this.resourceService.findResourceByCondition(condition).get(0);
			resource.setVerify(ResourceCondition.DEFAULT_VERIFY_NO);
			this.resourceService.modify(resource);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
    
    @RequestMapping(value = "/viewer/{id}")
    public ModelAndView viewer(@PathVariable(value = "id") Integer id, Model model) {
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
                Homework homework = this.homeworkService.findHomeworkByUuid(objId);
                if (homework != null) {
                    entityId = homework.getEntityId();
                }
            } else if (ResourceType.LEARNING_DESIGN == resType) {
                LearningDesign learningDesign = this.learningDesignService.findLearningDesignByUuid(objId);
                if (learningDesign != null) {
                    entityId = learningDesign.getEntityId();
                }
            } else if (ResourceType.MATERIAL == resType) {
                Material materail = this.materialService.findMaterialByUuid(objId);
                if (materail != null) {
                    entityId = materail.getEntityId();
                }
            } else if (ResourceType.MICRO == resType) {
                MicroLesson microLession = this.microLessonService.findMicroLessonByUuid(objId);
                if (microLession != null) {
                    entityId = microLession.getEntityId();
                }
            } else if (ResourceType.TEACHING_PLAN == resType) {
                TeachingPlan teachingPlan = this.teachingPlanService.findTeachingPlanByUuid(objId);
                if (teachingPlan != null) {
                    entityId = teachingPlan.getEntityId();
                }
            }
            if (entityId != null) {
                EntityFile entity = entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    model.addAttribute("entity", entity);
                }
            }
            model.addAttribute("res", resource);
        }
        return new ModelAndView(structurePath("/common/resource_viewer"));
    }

    @RequestMapping("/fav")
    @ResponseBody
    public String fav(
            HttpServletRequest request,
            @CurrentUser UserInfo user,
            @RequestParam(value = "Id")Integer resId,
            @RequestParam("isFav") boolean isFav) {
        String result = "";
        Integer relateAppId = SysContants.SYSTEM_COMMON_RESOURCE_APP_ID;
        if (isFav) {
            
                if (resId != 0) {
                    Resource resource = this.resourceService.findResourceById(resId);
                    String libraryUuid = null;
                    result = favoritesService.saveByUserResource(relateAppId, user.getId(),resource.getResType(),resource.getObjectId(), resource.getTitle(),resource.getId());
                    if (resource != null&&result.equals("success")) {
                        Date date = new Date();
                        ResourceLibraryCondition condition=new ResourceLibraryCondition();
                        condition.setOwerId(user.getSchoolId());
                        condition.setAppId(relateAppId);
                        List<ResourceLibrary> rlist= resourceLibraryService.findResourceLibraryByCondition(condition);
                        if(rlist!=null&&rlist.size()>0){
                        	libraryUuid=rlist.get(0).getUuid();
                        }
                        ResourceOperation resourceOperation = new ResourceOperation();
                        resourceOperation.setResourceId(resource.getId());
                        resourceOperation.setResourceUuid(resource.getObjectId());
                        resourceOperation.setResourceTitle(resource.getTitle());
                        resourceOperation.setResourceType(resource.getResType());
                        resourceOperation.setLibraryUuid(libraryUuid);
                        resourceOperation.setOperationType(OperationType.FAV);
                        resourceOperation.setUserId(user.getId());
                        resourceOperation.setThisWeek(0);//本周
                        resourceOperation.setOperationDate(date);
                        resourceOperation.setCreateDate(date);
                        resourceOperation.setModifyDate(date);
                        if (resourceOperation.getResourceTitle() != null && resourceOperation.getLibraryUuid() != null) {
                            this.resourceOperationService.add(resourceOperation);
                        }
                    }
                }
        } else {
            result = favoritesService.removeByUserResource(user.getId(),resId);
        }
        return result;
    }

    @RequestMapping("/like")
    @ResponseBody
    public String likes(
            HttpServletRequest request,
            @CurrentUser UserInfo user,

            @RequestParam(value = "Id") Integer Id,

            @RequestParam("isLike") boolean isLike) {
        String result = "";
        Integer relateAppId = SysContants.SYSTEM_COMMON_RESOURCE_APP_ID;
        if (isLike) {
                    String libraryUuid = null;
                    Resource resource = this.resourceService.findResourceById(Id);
                    result = likesService.saveUserLikes(relateAppId, user.getId(), resource.getResType(), resource.getObjectId(),resource.getTitle(),Id);
                    if (resource != null&&result.equals("success")) {
                        Date date = new Date();
                        Integer schoolId= user.getSchoolId();
                        ResourceLibraryCondition condition=new ResourceLibraryCondition();
                        condition.setOwerId(schoolId);
                        condition.setAppId(relateAppId);
                        List<ResourceLibrary> rlist= resourceLibraryService.findResourceLibraryByCondition(condition);
                        if(rlist!=null&&rlist.size()>0){
                        	libraryUuid=rlist.get(0).getUuid();
                        }
                        ResourceOperation resourceOperation = new ResourceOperation();
                        resourceOperation.setResourceId(resource.getId());
                        resourceOperation.setResourceUuid(resource.getObjectId());
                        resourceOperation.setResourceType(resource.getResType());
                        resourceOperation.setResourceTitle(resource.getTitle());
                        resourceOperation.setLibraryUuid(libraryUuid);
                        resourceOperation.setOperationType(OperationType.LIKE);
                        resourceOperation.setUserId(user.getId());
                        resourceOperation.setThisWeek(0);//本周
                        resourceOperation.setOperationDate(date);
                        resourceOperation.setCreateDate(date);
                        resourceOperation.setModifyDate(date);
                        if (resourceOperation.getResourceTitle() != null && resourceOperation.getLibraryUuid() != null) {
                            this.resourceOperationService.add(resourceOperation);
                        }
              
            }
        } else {
            result = likesService.removeUserLikes(user.getId(), Id);
        }
        return result;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user, ResourceVoCondition condition) {
        Integer appId = condition.getAppId();
        if (appId == null) {
            condition.setAppId(SysContants.SYSTEM_COMMON_RESOURCE_APP_ID);
        }
    }

    @RequestMapping(value = "/saveOrUpdate")
    public String saveOrUpdate(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String resId = request.getParameter("microId");
        String resType = request.getParameter("resType");
        String title = request.getParameter("title");
        String entityId = request.getParameter("entityId");
        String description = request.getParameter("description");
        //ImportCatalogVo vo = ResourceUtils.setDirectory(request);
        ImportCatalogVo vo = new ImportCatalogVo();
        String returenUuid = null;
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                Exam em = this.examService.saveOrUpdateExam(SysContants.SYSTEM_APP_ID, resId, entityId, ExamType.COMMON_EXAM, title, user.getId(), description);
                returenUuid = em.getUuid();
            } else if (ResourceType.HOMEWORK == typeint) {
                Homework em = this.homeworkService.saveOrUpdateHomework(SysContants.SYSTEM_APP_ID, resId, entityId, HomeworkType.COMMON_HOMEWORK, title, user.getId(), description);
                returenUuid = em.getUuid();
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                LearningDesign em = this.learningDesignService.saveOrUpdateLearningDesign(SysContants.SYSTEM_APP_ID, resId, entityId, LearningDesignType.COMMON_LEARNINGDESIGN, title, user.getId(), description);
                returenUuid = em.getUuid();
            } else if (ResourceType.MATERIAL == typeint) {
                Material em = this.materialService.saveOrUpdateMaterial(SysContants.SYSTEM_APP_ID, resId, entityId, MaterialType.COMMON_MATERIAL, title, user.getId(), description);
                returenUuid = em.getUuid();
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                TeachingPlan em = this.teachingPlanService.saveOrUpdateTeachingPlan(SysContants.SYSTEM_APP_ID, resId, entityId, TeachingPlanType.COMMON_TEACHINGPLAN, title, user.getId(), description);
                returenUuid = em.getUuid();
            }
        }
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(returenUuid);
        return viewBasePath + "/common/uploadIndex";
    }

    @RequestMapping(value = "/myResource")
    public String myResource(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String resType = request.getParameter("resType");
        page.setPageSize(5);
        List list = new ArrayList();
        String playUrl = "";
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                playUrl = "/exampublish/play";
                ExamCondition ec = new ExamCondition();
                ec.setAppId(SysContants.SYSTEM_APP_ID);
                ec.setType(ExamType.COMMON_EXAM);
                ec.setUserId(user.getId());
                ec.setTitle(title);
                List<Exam> emList = this.examService.findExamByCondition(ec, page, Order.desc("create_date"));
                for (Exam em : emList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.HOMEWORK == typeint) {
                playUrl = "/homeworkpublish/play";
                HomeworkCondition hc = new HomeworkCondition();
                hc.setAppId(SysContants.SYSTEM_APP_ID);
                hc.setType(HomeworkType.COMMON_HOMEWORK);
                hc.setUserId(user.getId());
                hc.setTitle(title);
                List<Homework> hwList = this.homeworkService.findHomeworkByCondition(hc, page, Order.desc("create_date"));
                for (Homework em : hwList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                playUrl = "/resource/learningDesignPlay";
                LearningDesignCondition lc = new LearningDesignCondition();
                lc.setAppId(SysContants.SYSTEM_APP_ID);
                lc.setType(LearningDesignType.COMMON_LEARNINGDESIGN);
                lc.setUserId(user.getId());
                lc.setTitle(title);
                List<LearningDesign> lcList = this.learningDesignService.findLearningDesignByCondition(lc, page, Order.desc("create_date"));
                for (LearningDesign em : lcList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.MATERIAL == typeint) {
                playUrl = "/resource/materialPlay";
                MaterialCondition mc = new MaterialCondition();
                mc.setAppId(SysContants.SYSTEM_APP_ID);
                mc.setType(MaterialType.COMMON_MATERIAL);
                mc.setUserId(user.getId());
                mc.setTitle(title);
                List<Material> mcList = this.materialService.findMaterialByCondition(mc, page, Order.desc("create_date"));
                for (Material em : mcList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                playUrl = "/resource/teachingPlanPlay";
                TeachingPlanCondition tp = new TeachingPlanCondition();
                tp.setAppId(SysContants.SYSTEM_APP_ID);
                tp.setType(TeachingPlanType.COMMON_TEACHINGPLAN);
                tp.setUserId(user.getId());
                tp.setTitle(title);
                List<TeachingPlan> tpList = this.teachingPlanService.findTeachingPlanByCondition(tp, page, Order.desc("create_date"));
                for (TeachingPlan em : tpList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            }
            request.setAttribute("playUrl", playUrl);
            request.setAttribute("resList", list);
            request.setAttribute("resType", resType);
        }
        //3.16.0
        if (index != null && !"".equals(index)) {
            return viewBasePath + housePath + "/myResourceIndex";
        } else {
            return viewBasePath + housePath + "/myResourceList";
        }
    }

    @RequestMapping(value = "/favResource")
    public String favResource(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String resType = request.getParameter("resType");
        page.setPageSize(5);
        List list = new ArrayList();
        String playUrl = "";
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                playUrl = "/exampublish/play";
                List<ExamVo> emList = this.examService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page, user.getId(), title, ExamType.COMMON_EXAM);
                for (ExamVo em : emList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.HOMEWORK == typeint) {
                playUrl = "/homeworkpublish/play";
                List<HomeworkVo> hwList = this.homeworkService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page, user.getId(), title, HomeworkType.COMMON_HOMEWORK);
                for (HomeworkVo em : hwList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                playUrl = "/resource/learningDesignPlay";
                List<LearningDesignVo> lcList = this.learningDesignService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page, user.getId(), title, LearningDesignType.COMMON_LEARNINGDESIGN);
                for (LearningDesignVo em : lcList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.MATERIAL == typeint) {
                playUrl = "/resource/materialPlay";
                List<MaterialVo> mcList = this.materialService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page, user.getId(), title, MaterialType.COMMON_MATERIAL);
                for (MaterialVo em : mcList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                playUrl = "/resource/teachingPlanPlay";
                List<TeachingPlanVo> tpList = this.teachingPlanService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page, user.getId(), title, TeachingPlanType.COMMON_TEACHINGPLAN);
                for (TeachingPlanVo em : tpList) {
                    MyResourceVo mrvo = new MyResourceVo();
                    EntityFile ent = this.entityFileService.findFileByUUID(em.getEntityId());
                    mrvo.setResEnt(em);
                    mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
                    list.add(mrvo);
                }
            }
            request.setAttribute("playUrl", playUrl);
            request.setAttribute("resList", list);
            request.setAttribute("resType", resType);
        }
        if (index != null && !"".equals(index)) {
            return viewBasePath + housePath + "/favResourceIndex";
        } else {
            return viewBasePath + housePath + "/favResourceList";
        }
    }

    @RequestMapping(value = "/uploadIndex")
    public String uploadIndex(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String resType = request.getParameter("resType");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        return viewBasePath + "/common/uploadIndex";
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        String publish = request.getParameter("publish");
        String resType = request.getParameter("resType");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        if (publish != null && !"".equals(publish)) {
            request.setAttribute("publish", publish);
        }
        return viewBasePath + "/common/upload";
    }

    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String resId = request.getParameter("resId");
        String resType = request.getParameter("resType");
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                Integer flag = this.examService.deleteMyExam(SysContants.SYSTEM_APP_ID, user.getId(), resId);
            } else if (ResourceType.HOMEWORK == typeint) {
                Integer flag = this.homeworkService.deleteMyHomework(SysContants.SYSTEM_APP_ID, user.getId(), resId);
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                Integer flag = this.learningDesignService.deleteMyLearningDesign(SysContants.SYSTEM_APP_ID, user.getId(), resId);
            } else if (ResourceType.MATERIAL == typeint) {
                Integer flag = this.materialService.deleteMyMaterial(SysContants.SYSTEM_APP_ID, user.getId(), resId);
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                Integer flag = this.teachingPlanService.deleteMyTeachingPlan(SysContants.SYSTEM_APP_ID, user.getId(), resId);
            }
        }
        return null;
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        String resId = request.getParameter("resId");
        String resType = request.getParameter("resType");
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                Exam em = this.examService.findExamByUuid(resId);
                request.setAttribute("micro", em);
            } else if (ResourceType.HOMEWORK == typeint) {
                Homework em = this.homeworkService.findHomeworkByUuid(resId);
                request.setAttribute("micro", em);
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                LearningDesign em = this.learningDesignService.findLearningDesignByUuid(resId);
                request.setAttribute("micro", em);
            } else if (ResourceType.MATERIAL == typeint) {
                Material em = this.materialService.findMaterialByUuid(resId);
                request.setAttribute("micro", em);
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                TeachingPlan em = this.teachingPlanService.findTeachingPlanByUuid(resId);
                request.setAttribute("micro", em);
            }
        }
        request.setAttribute("resType", resType);
        return viewBasePath + housePath + "/myResourceEdit";
    }

    @RequestMapping(value = "/materialPlay")
    public ModelAndView materialPlay(HttpServletRequest request, HttpServletResponse response, Model model) {
        String objId = request.getParameter("objId");
        Material exam = this.materialService.findMaterialByUuid(objId);
        if (exam != null) {
            MyResourceVo mrvo = new MyResourceVo();
            mrvo.setResEnt(exam);
            String entityId = exam.getEntityId();
            if (entityId != null) {
                EntityFile entity = entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    mrvo.setThumbnailUrl(entity.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
                    model.addAttribute("entity", entity);
                }
            }
            model.addAttribute("res", mrvo);
        }
        return new ModelAndView(structurePath("/common/play"));
    }

    @RequestMapping(value = "/teachingPlanPlay")
    public ModelAndView teachingPlanPlay(HttpServletRequest request, HttpServletResponse response, Model model) {
        String objId = request.getParameter("objId");
        TeachingPlan exam = this.teachingPlanService.findTeachingPlanByUuid(objId);
        if (exam != null) {
            MyResourceVo mrvo = new MyResourceVo();
            mrvo.setResEnt(exam);
            String entityId = exam.getEntityId();
            if (entityId != null) {
                EntityFile entity = entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    mrvo.setThumbnailUrl(entity.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
                    model.addAttribute("entity", entity);
                }
            }
            model.addAttribute("res", mrvo);
        }
        return new ModelAndView(structurePath("/common/play"));
    }

    @RequestMapping(value = "/learningDesignPlay")
    public ModelAndView learningDesignPlay(HttpServletRequest request, HttpServletResponse response, Model model) {
        String objId = request.getParameter("objId");
        LearningDesign exam = this.learningDesignService.findLearningDesignByUuid(objId);
        if (exam != null) {
            MyResourceVo mrvo = new MyResourceVo();
            mrvo.setResEnt(exam);
            String entityId = exam.getEntityId();
            if (entityId != null) {
                EntityFile entity = entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    mrvo.setThumbnailUrl(entity.getThumbnailUrl());
                    mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
                    model.addAttribute("entity", entity);
                }
            }
            model.addAttribute("res", mrvo);
        }
        return new ModelAndView(structurePath("/common/play"));
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String Id=request.getParameter("Id");
     Resource r=this.resourceService.findResourceById(Integer.valueOf(Id));
        String entId = null;
        String downloadTitle = r.getTitle();
        String suffix = null;
        if (ResourceType.EXAM == r.getResType()) {
            Exam em = this.examService.findExamByUuid(r.getObjectId());
            entId = em.getEntityId();
        } else if (ResourceType.HOMEWORK ==  r.getResType()) {
            Homework hw = this.homeworkService.findHomeworkByUuid(r.getObjectId());
            entId = hw.getEntityId();
        } else if (ResourceType.LEARNING_DESIGN ==  r.getResType()) {
            LearningDesign ld = this.learningDesignService.findLearningDesignByUuid(r.getObjectId());
            entId = ld.getEntityId();
        } else if (ResourceType.MATERIAL ==  r.getResType()) {
            Material ml = this.materialService.findMaterialByUuid(r.getObjectId());
            entId = ml.getEntityId();
        } else if (ResourceType.TEACHING_PLAN ==  r.getResType()) {
            TeachingPlan tp = this.teachingPlanService.findTeachingPlanByUuid(r.getObjectId());
            entId = tp.getEntityId();
        } else if (ResourceType.OTHER ==  r.getResType()) {
            entId = r.getObjectId();
        }else if(ResourceType.MICRO==r.getResType()){
        	MicroLesson em=this.microLessonService.findMicroLessonByUuid(r.getObjectId());
        	entId=em.getEntityId();
        }else if(ResourceType.LEARNING_PLAN==r.getResType()){
          	LearningPlan em=this.learningPlanService.findLearningPlanByUuid(r.getObjectId());
        	entId=em.getEntityId();
        }
        EntityFile ent = this.entityFileService.findFileByUUID(entId);
        if (ent != null) {
            suffix = ent.getExtension();
            String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(downloadTitle, "UTF-8"));
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");
            response.setContentLength(ent.getSize().intValue());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + "." + suffix);
            String flag = this.fileService.download(entId, response.getOutputStream());
        }else{
            PrintWriter pw = this.setAjaxResponse(request, response);
            pw.print("<script type=\"text/javascript\">alert(\"源文件不存在\")</script>");
        }
        return null;
    }
    @RequestMapping("/downloadByObject")
    public String downloadByObject(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String mid=request.getParameter("objectId");
     String type= request.getParameter("resType");
     Integer resType=Integer.valueOf(type);
        String entId = null;
        String downloadTitle = null;
        String suffix = null;
        if (ResourceType.EXAM == resType) {
            Exam em = this.examService.findExamByUuid(mid);
            entId = em.getEntityId();
            downloadTitle = em.getTitle();
        } else if (ResourceType.HOMEWORK ==  resType) {
            Homework hw = this.homeworkService.findHomeworkByUuid(mid);
            entId = hw.getEntityId();
            downloadTitle = hw.getTitle();
        } else if (ResourceType.LEARNING_DESIGN ==  resType) {
            LearningDesign ld = this.learningDesignService.findLearningDesignByUuid(mid);
            entId = ld.getEntityId();
            downloadTitle = ld.getTitle();
        } else if (ResourceType.MATERIAL ==  resType) {
            Material ml = this.materialService.findMaterialByUuid(mid);
            entId = ml.getEntityId();
            downloadTitle = ml.getTitle();
        } else if (ResourceType.TEACHING_PLAN ==  resType) {
            TeachingPlan tp = this.teachingPlanService.findTeachingPlanByUuid(mid);
            entId = tp.getEntityId();
            downloadTitle = tp.getTitle();
        } else if (ResourceType.OTHER ==  resType) {
            entId = mid;
        }else if(ResourceType.MICRO==resType){
        	MicroLesson em=this.microLessonService.findMicroLessonByUuid(mid);
        	entId=em.getEntityId();
        	downloadTitle = em.getTitle();
        }else if(ResourceType.LEARNING_PLAN==resType){
        	LearningPlan em=this.learningPlanService.findLearningPlanByUuid(mid);
        	entId=em.getEntityId();
        	downloadTitle = em.getTitle();
        }
        EntityFile ent = this.entityFileService.findFileByUUID(entId);
        if (ent != null) {
            suffix = ent.getExtension();
            String filename = DownloadUtil.encodeFilenameForDownload(request, URLDecoder.decode(downloadTitle, "UTF-8"));
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");
            response.setContentLength(ent.getSize().intValue());
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + "." + suffix);
            String flag = this.fileService.download(entId, response.getOutputStream());
        }else{
            PrintWriter pw = this.setAjaxResponse(request, response);
            pw.print("<script type=\"text/javascript\">alert(\"源文件不存在\")</script>");
        }
        return null;
    }
    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
    
}
