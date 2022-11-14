package platform.education.commonResource.web.micro.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.FtlContants;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.util.MultiDomainResolver;
import platform.education.commonResource.web.common.util.SzxyHttpClientRequest;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.commonResource.web.resource.contans.ContansOfResource;
import platform.education.commonResource.web.resource.vo.MyResourceVo;
import platform.education.exam.model.Exam;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalcode.model.Grade;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.vo.GradeCondition;
import platform.education.generalcode.vo.TextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.education.homework.model.Homework;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.material.model.Material;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonBgpicture;
import platform.education.micro.model.MicroUserRecord;
import platform.education.micro.service.MicroLessonBgpictureService;
import platform.education.micro.vo.MicroLessonBgpictureCondition;
import platform.education.micro.vo.MicroLessonBgpictureVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.contants.StudyFinishedFlag;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.ImportCatalogVo;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.teachingPlan.model.TeachingPlan;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/micro/")
public class MicroController extends BaseController {

	/*@RequestMapping(value = "/common/resource/index")
	public String index() {
		//appId
		//schoold
		//index_${appId}_${schoolId}.html ---- > index_1_1.html
		return "redirect:/html/index_1_1.html";
	}*/
	//微课星背景图
    @javax.annotation.Resource
    private MicroLessonBgpictureService microLessonBgpictureService;
	
    private final static String viewBasePath = "/micro";
    private final static String mymicroPath = "/mymicro";
    private final static String commonPath = "/common";

    @RequestMapping(value = "/play")
    public String play(HttpServletRequest request, HttpServletResponse response) {
        playMicroImpl(request);
        return viewBasePath + commonPath + "/play";
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
    
    @RequestMapping(value = "/propertyJson")
    @ResponseBody
    public String getPropertyJson(HttpServletRequest request, HttpServletResponse response) {
        String propertyPath = request.getParameter("propertyPath");
        org.json.JSONObject jsonObject = SzxyHttpClientRequest.doGetRequest(propertyPath, null);
        String jsonString = "";
        if (!StringUtils.isEmpty(jsonObject)) {
            jsonString = jsonObject.toString();            
        }
        return jsonString;
    }
    private void playMicroImpl(HttpServletRequest request) {
        String microId = request.getParameter("microId");
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
        FileResult bgpictureFile = fileService.findFileByUUID(bgpictureEntityId);
        FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
        FileResult logoFile = fileService.findFileByUUID(logoEntiyId);
        if(jsonFile.getEntityFile() != null){
        	vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
        }
        if(mediaFile.getEntityFile() != null){
        	vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
        }
        if(bgpictureFile.getEntityFile() != null){
        	vo.setBgpicturePath(bgpictureFile.getEntityFile().getRelativePath());
        }
        if(propertyFile.getEntityFile() != null){
        	vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
        }
        if(logoFile.getEntityFile() != null){
        	vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
        }
      //设置背景文件的路径
        if(ml != null){
        	List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
        	vo.setMlbs(vos);
        }
        request.setAttribute("type", MicroType.MICRO_COURSE);
        request.setAttribute("micro", vo);
    }
    private List<MicroLessonBgpictureVo> bgSettings(String uuid){
    	//设置背景文件的路径
    	MicroLessonBgpictureCondition mlbCondition = new MicroLessonBgpictureCondition();
    	mlbCondition.setLessonId(uuid);
    	List<MicroLessonBgpicture> mlbs = microLessonBgpictureService.findMicroLessonBgpictureByCondition(mlbCondition);
    	List<MicroLessonBgpictureVo> vos = new ArrayList<MicroLessonBgpictureVo>();
    	if(mlbs.size() > 0){
    		for(MicroLessonBgpicture mlb : mlbs){
    			MicroLessonBgpictureVo vo = new MicroLessonBgpictureVo();
    			BeanUtils.copyProperties(mlb, vo);
    			String entityId = mlb.getEntityId();
    			if(entityId != null){
    				FileResult fileResult = fileService.findFileByUUID(entityId);
    				vo.setBgPath(fileResult.getEntityFile().getRelativePath());
    			}
    			vos.add(vo);
    		}
    	}
    	return vos;
    }
    /**
     * 设置MicroLessonVo的属性
     * @param ml
     * @return
     */
    private MicroLessonVo BeanSettings(MicroLesson ml){
    	if(ml != null){
        	MicroLessonVo vo = new MicroLessonVo();
        	BeanUtils.copyProperties(ml, vo);
        	//设置对应文件的路径
//        	String bgpictureEntityId = ml.getBgpictureEntityId();
        	String jsonEntityId = ml.getJsonEntityId();
        	String mediaEntityId = ml.getMediaEntityId();
        	String propertyEntityId = ml.getPropertyEntityId();
        	String logoEntityId = ml.getLogoEntityId();
        	if (!StringUtils.isEmpty(logoEntityId)) {
        		FileResult logoFile = fileService.findFileByUUID(logoEntityId);
        		if (logoFile.getEntityFile() != null) {
        			vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
        		}
        		
        	}
//        	if (!StringUtils.isEmpty(bgpictureEntityId)) {
//        		FileResult bgpictureFile = fileService.findFileByUUID(bgpictureEntityId);
//        		if (bgpictureFile.getEntityFile() != null) {
//        			vo.setBgpicturePath(bgpictureFile.getEntityFile().getRelativePath());
//        		}
//        	}
        	if (!StringUtils.isEmpty(jsonEntityId)) {
        		FileResult jsonFile = fileService.findFileByUUID(jsonEntityId);
        		if (jsonFile.getEntityFile() != null) {
        			vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
        		}
        	}
        	if (!StringUtils.isEmpty(mediaEntityId)) {
        		FileResult mediaFile = fileService.findFileByUUID(mediaEntityId);
        		if (mediaFile.getEntityFile() != null) {
        			vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
        		}
        	}
        	if (!StringUtils.isEmpty(propertyEntityId)) {
        		FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
        		if (propertyFile.getEntityFile() != null) {
        			vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
        		}
        	}
        	return vo;
        }
    	return null;
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        Integer relateAppId = this.getRelateApp(request);
        String microId = request.getParameter("microId");
//        this.microLessonService.deleteMyMicroLesson(relateAppId, user.getUserId(), microId);
        Integer flag = microLessonService.deleteMyMicroLesson(relateAppId, user.getUserId(), microId);
        return flag + "";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        StringBuffer sbf = new StringBuffer();
        String prefixPath = storage.spaceName();
        String httpPrefix = storage.httpPrefix();
        sbf.append(httpPrefix);
        sbf.append("/");
        sbf.append(prefixPath);
        request.setAttribute("httpUrl", sbf.toString());
        Integer ownerId = SysContants.SYSTEM_OWNER_ID;
		Integer appId = SysContants.SYSTEM_APP_ID;
        Integer relateAppId = this.getRelateApp(request);
        Integer relateSchoolId = this.getRelateSchool(request);
        List<ResourceLibrary> list = this.resourceLibraryService.findResourceLibraryByCondition(null);
        ResourceLibraryCondition condition = new ResourceLibraryCondition();
        condition.setOwerId(relateSchoolId);
        condition.setAppId(relateAppId);
        List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
        String url = "";
        String host = FtlContants.FTL_HTMLURL;
        host = MultiDomainResolver.resolver(request.getServerName(), host);
        if(resourceLibraryList.size()<=0){
        	condition = new ResourceLibraryCondition();
        	condition.setOwerId(ownerId);
            condition.setAppId(appId);
            List<ResourceLibrary> resLibList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
            ResourceLibrary resourceLibrary = null;
            if(resLibList.size()==1){
            	resourceLibrary = resLibList.get(0);
            }
            if(resourceLibrary!=null){
//            	String host = FtlContants.FTL_HTMLURL;
            	url = "redirect:" + host + "microIndex_"+ appId +"_"+ ownerId + "_"+ resourceLibrary.getUuid()+ ".html";
            }
        }else{
        	ResourceLibrary rl = null;
        	rl = resourceLibraryList.get(0);
        	if(rl!=null){
        		String filePath = FtlContants.FTL_HTMLPATH+File.separator+"microIndex_"+ rl.getAppId() +"_"+ rl.getOwerId() + "_"+rl.getUuid()+".html";
            	File file = new File(filePath);
            	if(file.exists()){
//            		String host = FtlContants.FTL_HTMLURL;
            		url = "redirect:"+ host +"microIndex_"+ rl.getAppId() +"_"+ rl.getOwerId() + "_"+rl.getUuid()+".html";
            	}else{
            		condition = new ResourceLibraryCondition();
                	condition.setOwerId(ownerId);
                    condition.setAppId(appId);
                    List<ResourceLibrary> resLibList = this.resourceLibraryService.findResourceLibraryByCondition(condition);
                    ResourceLibrary resourceLibrary = null;
                    if(resLibList.size()==1){
                    	resourceLibrary = resLibList.get(0);
                    }
                    if(resourceLibrary!=null){
//                    	String host = FtlContants.FTL_HTMLURL;
                    	url = "redirect:" + host + "microIndex_"+ appId +"_"+ ownerId + "_"+ resourceLibrary.getUuid()+ ".html";
                    }
            	}
        	}
        	
        }
        return url;
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        String publish = request.getParameter("publish");
        if (publish != null && !"".equals(publish)) {
            request.setAttribute("publish", publish);
        }
        return viewBasePath + commonPath + "/upload";
    }

    @RequestMapping(value = "/uploadIndex")
    public String uploadIndex(HttpServletRequest request, HttpServletResponse response) {
        return viewBasePath + commonPath + "/uploadIndex";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        String microId = request.getParameter("microId");
        MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
        request.setAttribute("micro", ml);
        return viewBasePath + mymicroPath + "/myMicroEdit";
    }

    @RequestMapping(value = "/myMicro")
    public String myMicro(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String personType = request.getParameter("personType");
        if(personType==null){
        	personType=ContansOfResource.PERSONRESOURCE;
        }
        Integer relateAppId = this.getRelateApp(request);
        List<MyResourceVo> list=new ArrayList<MyResourceVo>();
        page.setPageSize(5);
        ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		resourceLibraryCondition.setOwerId(user.getSchoolId());
		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		ResourceLibrary resourceLibrary = new ResourceLibrary();
		if (resourceLibraryList != null && resourceLibraryList.size() > 0) {//如果存在取对应的uui
			resourceLibrary = resourceLibraryList.get(0);
		} else {//如果不存在添加对应的记录
			ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
			resourceLibraryAdd.setOwerId(user.getSchoolId());
			resourceLibraryAdd.setUuid(UUIDUtil.getUUID());//获取唯一值uuid
			School  school=schoolService.findSchoolById(user.getSchoolId());
			resourceLibraryAdd.setName(school.getName());
			resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);
		}
            ResourceCondition resResourceCondition = new ResourceCondition();
            resResourceCondition.setUserId(user.getUserId());
            resResourceCondition.setResType(ResourceType.MICRO);
          //校本资源筛选
          if(personType.equals(ContansOfResource.SCHOOLRESOURCE)){
          	resResourceCondition.setLibraryId(resourceLibrary.getUuid());
          	resResourceCondition.setIsPersonal(false);
          	resResourceCondition.setVerify(ResourceCondition.DEFAULT_UPLOAD_YES);
          }
          //各人资源筛选
          if(personType.equals(ContansOfResource.PERSONRESOURCE)){
          	resResourceCondition.setIsPersonal(true);
          }
          List<Resource> resourceList = this.resourceService.findResourceByCondition(resResourceCondition,page, Order.desc("create_date"));
          for(Resource r:resourceList){
        	  MyResourceVo mrvo = new MyResourceVo();
        	  EntityFile ent=new EntityFile();
        	MicroLesson  em = new MicroLesson();
        	  em=  this.microLessonService.findMicroLessonByUuid(r.getObjectId());
        	  if(em!=null){
        		  
        		  mrvo.setResEnt(em);
        		  ent = this.entityFileService.findFileByUUID(em.getEntityId());
        	  }
            if (ent != null && ent.getThumbnailUrl() != null) {
            mrvo.setThumbnailUrl(ent.getThumbnailUrl());
            mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
        } else {
            mrvo.setThumbnailUrl("");
        }
           if(em!=null&&ent!=null){
        	   list.add(mrvo);
           }
      } request.setAttribute("personType", personType);
        request.setAttribute("microLessonList", list);
        if (index != null && !"".equals(index)) {
            return viewBasePath + mymicroPath + "/myMicroIndex";
        } else {
            return viewBasePath + mymicroPath + "/myMicroList";
        }
    }

    @RequestMapping(value = "/verifyDelete")
    public String verifyDelete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String resId = request.getParameter("resId");
        Integer relateAppId = this.getRelateApp(request);
        ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
        resourceLibraryCondition.setOwerId(user.getSchoolId());
        resourceLibraryCondition.setAppId(relateAppId);
        String libraryId = "";
        List<ResourceLibrary> resourceLibraryList =  this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
        if(resourceLibraryList != null&&resourceLibraryList.size() == 1){
        	libraryId = resourceLibraryList.get(0).getUuid();
        }else{
        	libraryId = "0";
        }
        
        Resource resResource = new Resource();
        
        ResourceCondition resResourceCondition = new ResourceCondition();
        resResourceCondition.setObjectId(resId);//资源名称
//        resResourceCondition.setAppId(relateAppId);//学校appid
        resResourceCondition.setVerify(ResourceCondition.DEFAULT_VERIFY_SUCCESS);//0
        resResourceCondition.setLibraryId(libraryId);//学校资源库
        resResourceCondition.setResType(ResourceType.MICRO);
        
        List<Resource> resourceList = this.resourceService.findResourceByCondition(resResourceCondition);
        if(resourceList != null &&resourceList.size() == 1){
        	resResource = resourceList.get(0);
        }
        this.resourceService.remove(resResource);
        
        return null;
    }
    
    @RequestMapping(value = "/verifyAdd")
    public String verifyAdd(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String resId = request.getParameter("resId");
        Integer relateAppId = this.getRelateApp(request);
        String noSuffixTitle = "";
        String description = "";
        String  suffix= "";
        noSuffixTitle = getTitle(resId, ResourceType.MICRO+"");//获取标题
        suffix = getSuffix(resId, ResourceType.MICRO+"");
        this.addResource(ResourceType.MICRO, resId, noSuffixTitle, description, relateAppId, "", user,suffix);
        
        return null;
    }
    
    /**
     * 获取标题
     * @param resId
     * @param resType
     * @param noSuffixTitle
     * @return
     */
	private String getTitle(String resId, String resType) {
		String noSuffixTitle = "";
		if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
            	Exam exam = this.examService.findExamByUuid(resId);
            	noSuffixTitle = exam.getTitle();
            } else if (ResourceType.HOMEWORK == typeint) {
            	Homework homework = homeworkService.findHomeworkByUuid(resId);
            	noSuffixTitle = homework.getTitle();
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
            	LearningDesign learningDesign = learningDesignService.findLearningDesignByUuid(resId);
            	noSuffixTitle = learningDesign.getTitle();
            } else if (ResourceType.MATERIAL == typeint) {
            	Material material = materialService.findMaterialByUuid(resId);
            	noSuffixTitle = material.getTitle();
            } else if (ResourceType.TEACHING_PLAN == typeint) {
            	TeachingPlan teachingPlan  = teachingPlanService.findTeachingPlanByUuid(resId);
            	noSuffixTitle = teachingPlan.getTitle();
            }else if (ResourceType.MICRO == typeint || ResourceType.WRITE_TYPE_MICRO == typeint) {
            	MicroLesson microLesson  = this.microLessonService.findMicroLessonByUuid(resId);
            	noSuffixTitle = microLesson.getTitle();
            }
        }
		return noSuffixTitle;
	}
    

    /**
     * 获取后缀
     *
     * @param resId
     * @param resType
     * @param noSuffixTitle
     * @return
     */
    private String getSuffix(String resId, String resType) {
        String suffix = "";
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            if (ResourceType.EXAM == typeint) {
                Exam exam = this.examService.findExamByUuid(resId);
                suffix = this.entityFileService.findFileByUUID(exam.getEntityId()).getExtension();
            } else if (ResourceType.HOMEWORK == typeint) {
                Homework homework = homeworkService.findHomeworkByUuid(resId);
                suffix = this.entityFileService.findFileByUUID(homework.getEntityId()).getExtension();
            } else if (ResourceType.LEARNING_DESIGN == typeint) {
                LearningDesign learningDesign = learningDesignService.findLearningDesignByUuid(resId);
                suffix = this.entityFileService.findFileByUUID(learningDesign.getEntityId()).getExtension();
            } else if (ResourceType.MATERIAL == typeint) {
                Material material = materialService.findMaterialByUuid(resId);
                suffix = this.entityFileService.findFileByUUID(material.getEntityId()).getExtension();
            } else if (ResourceType.TEACHING_PLAN == typeint) {
                TeachingPlan teachingPlan = teachingPlanService.findTeachingPlanByUuid(resId);
                suffix = this.entityFileService.findFileByUUID(teachingPlan.getEntityId()).getExtension();
            }else if (ResourceType.MICRO == typeint) {
            	MicroLesson microLesson  = this.microLessonService.findMicroLessonByUuid(resId);
            	suffix = this.entityFileService.findFileByUUID(microLesson.getEntityId()).getExtension();
            }
        }
        return suffix;
    }
    @RequestMapping(value = "/favMicro")
    public String favMicro(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        Integer relateAppId = this.getRelateApp(request);
        page.setPageSize(5);
        String resType = request.getParameter("resType");
        String personType=request.getParameter("personType");
        page.setPageSize(5);
        String playUrl = "";
        List<Resource> resources=new ArrayList<Resource>();
        List<MyResourceVo>list=new ArrayList<MyResourceVo>();
        EntityFile ent=new EntityFile();
        if (resType != null && !"".equals(resType)) {
            Integer typeint = Integer.parseInt(resType);
            resources=this.resourceService.findMyFavResource(user.getUserId(), typeint,title,page,null);
           for(Resource r:resources){
        	   MyResourceVo vo=new MyResourceVo();
        	   vo.setResEnt(r);
        	   MicroLesson em=this.microLessonService.findMicroLessonByUuid(r.getObjectId());
        	   if(em!=null)
         	  {
           		   ent = this.entityFileService.findFileByUUID(em.getEntityId());
           		   if(ent!=null){
           			  vo.setThumbnailUrl(ent.getThumbnailUrl());
      	              vo.setIconType(IconUtil.setIcon(ent.getExtension()));
           		   }else {
       	            vo.setThumbnailUrl("");
       	        }
           	  }
        	   
        	   list.add(vo);
           }

            request.setAttribute("playUrl", playUrl);
            request.setAttribute("resList", list);
            request.setAttribute("resType", resType);
            request.setAttribute("personType",personType);
        }
        if (index != null && !"".equals(index)) {
            return viewBasePath + mymicroPath + "/myMicroIndex";
        } else {
            return viewBasePath + mymicroPath + "/myMicroList";
        }
    }

    @RequestMapping(value = "/saveOrUpdate")
    public String saveOrUpdate(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microId = request.getParameter("microId");
        String title = request.getParameter("title");
        String entityId = request.getParameter("entityId");
        String description = request.getParameter("description");
        String checked = request.getParameter("checked");
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
        String gradeName = "";
        String volumnName="";
        if(gradeCode != null&&!"".equals(gradeCode)){
        	GradeCondition gradeCondition = new GradeCondition();
        	gradeCondition.setCode(gradeCode);
        	
        	List<Grade> gradeList = this.jcGradeService.findGradeByCondition(gradeCondition, null, null);
        	if(gradeList != null&&gradeList.size()>0){
        		 gradeName = gradeList.get(0).getName();
        	}else{
        		 gradeName = "";
        	}
        }else{
        	gradeName = "";
        }
        
        if(volumn != null&&!"".equals(volumn)){
        	TextbookVolumnCondition textbookVolumnCondition= new TextbookVolumnCondition();
        	textbookVolumnCondition.setCode(volumn);
        	List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
        	if(volumnList != null&&volumnList.size()>0){
        		volumnName = volumnList.get(0).getName();
        	}else{
        		volumnName = "";
        	}
        }else{
        	volumnName = "";
        }
        Integer relateAppId = this.getRelateApp(request);
        //ImportCatalogVo vo = ResourceUtils.setDirectory(request);
        ImportCatalogVo vo = new ImportCatalogVo();
        vo.setStageCode(stageCode);
        vo.setSubjectCode(subjectCode);
        vo.setGradeCode(gradeCode);
        vo.setGradeCode(gradeCode);
        vo.setVersionCode(version);
        vo.setVolumnCode(volumn);
        
        vo.setStageName(stageName);
        vo.setSubjectName(subjectName);
        vo.setGradeName(gradeName);
        vo.setVersionName(versionName);
        vo.setVolumnName(volumnName);
        
        if(catalogEnd != null&&Integer.parseInt(catalogEnd)>0){
        	vo.setCatalogCode(catalogEnd);
        }else{
        	if(textbookId != null&&Integer.parseInt(textbookId)>0){
        		TextbookCatalogCondition jcCondition = new TextbookCatalogCondition();
            	jcCondition.setTestBookId(Integer.parseInt(textbookId));
            	jcCondition.setParentId(TextbookCatalogCondition.DEFAULT_PARENT_ID);
            	List<TextbookCatalog> catalogList = this.jcTextbookCatalogService.findTextbookCatalogByCondition(jcCondition);
            	if(catalogList != null&&catalogList.size()>0){
            		TextbookCatalog textbookCatalog = catalogList.get(0);
            		vo.setCatalogCode(textbookCatalog.getCode());
            	}
        	}
        }
        
        MicroLesson ml = this.microLessonService.saveOrUpdateMicroLesson(relateAppId, microId, entityId, MicroType.COMMON_MICRO, title, user.getUserId(), description, null, vo,null);
        if(checked != null&&"true".equals(checked)){
        	String suffix = "";
        	if(ml != null){
        		suffix = this.entityFileService.findFileByUUID(ml.getEntityId()).getExtension();
        	}
        	this.addResource(ResourceType.MICRO, ml.getUuid(), title, description, ml.getAppId(), null, user,suffix);
        }
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(ml.getUuid());
        return null;
    }
    
    /**
     * 添加资源
     * @param resType 类型
     * @param objectId 资源本身uuid
     * @param noSuffixTitle 标题
     * @param description 描述
     * @param appId appId
     * @param thumbnailUrl 缩略图
     */
    private void addResource(Integer resType,String objectId,String noSuffixTitle,String description,Integer appId,String thumbnailUrl,UserInfo user,String suffix){
    	
    	//查询相关资源单位库，判断是否存在对应的记录
    	if(user == null){
    		user = new UserInfo();
    	}
    	Integer owerId = user.getSchoolId();
    	String regionCode = user.getRegionCode();
    	String schoolName = user.getSchoolName();
    	
    	
    	ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
    	resourceLibraryCondition.setAppId(appId);
    	resourceLibraryCondition.setOwerId(owerId);
    	
    	List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
    	ResourceLibrary resourceLibrary = new ResourceLibrary(); 
    	if(resourceLibraryList != null&&resourceLibraryList.size()>0){//如果存在取对应的uui
    		resourceLibrary = resourceLibraryList.get(0);
    	}else{//如果不存在添加对应的记录
    		ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
    		resourceLibraryAdd.setAppId(appId);
    		resourceLibraryAdd.setOwerId(owerId);
    		resourceLibraryAdd.setName(schoolName);
    		resourceLibraryAdd.setUuid(UUIDUtil.getUUID());//获取唯一值uuid
//    		resourceLibraryAdd.setRegionCode(regionCode);
    		resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);
    		if(resourceLibrary != null &&resourceLibrary.getId()>0){
    			//
    		}else{
    			
    		}
    	}
    	
    	if(resourceLibrary != null &&resourceLibrary.getId()>0){
    		Resource res = new Resource();
            res.setCreateDate(new Date());
            res.setModifyDate(new Date());
            res.setVerify(ResourceCondition.DEFAULT_VERIFY_PROCESS);
            res.setLibraryId(resourceLibrary.getUuid());
            res.setUserId(user.getUserId());
            res.setUserName(user.getRealName());
            res.setResType(resType);
            res.setObjectId(objectId);
            res.setTitle(noSuffixTitle);
            //String suffix=noSuffixTitle.substring(noSuffixTitle.lastIndexOf(".")+1);
            res.setIconType(IconUtil.setIcon(suffix));
            res.setUuid(UUIDUtil.getUUID());
            res.setDescription(description);
//            res.setAppId(appId);
            res.setThumbnailUrl(thumbnailUrl);
            res = this.resourceService.add(res);
    	}
    	
    }

    @RequestMapping(value = "/getRecord")
    public String getRecord(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws IOException {
        String microId = request.getParameter("microId");
        Integer userId = user.getUserId();
        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, null);
        PrintWriter pw = setAjaxResponse(request, response);
        if (ur != null) {
            if (ur.getLastPlayTime() != null) {
                pw.print(ur.getLastPlayTime());
            } else {
                pw.print("fail");
            }
        } else {
            pw.print("fail");
        }
        return null;
    }

    @RequestMapping(value = "/saveRecord")
    public String saveRecord(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String microId = request.getParameter("microId");
        String lastPlayTime = request.getParameter("lastPlayTime");
        String finishedFlag = request.getParameter("finishedFlag");
        Integer userId = user.getUserId();
        MicroUserRecord ur = this.microUserRecordService.getUniqueRecord(userId, microId, null);
        if (ur != null) {
            ur.setModifyDate(new Date());
            ur.setPlayTime(ur.getPlayTime() + 1);
            if (finishedFlag != null && !"".equals(finishedFlag)) {
                int flag = Integer.parseInt(finishedFlag);
                if (flag == StudyFinishedFlag.FINISHED) {
                    ur.setFinishedDate(new Date());
                }
                ur.setFinishedFlag(flag);
            }
            if (lastPlayTime != null && !"".equals(lastPlayTime) && !"0".equals(lastPlayTime)) {
                ur.setLastPlayTime(Double.parseDouble(lastPlayTime));
            }
            this.microUserRecordService.modify(ur);
        } else {
            ur = new MicroUserRecord();
            ur.setCreateDate(new Date());
            ur.setModifyDate(new Date());
            ur.setMicroId(microId);
            ur.setUserId(user.getUserId());
            ur.setUserName(user.getRealName());
            ur.setPlayTime(1);
            this.microUserRecordService.add(ur);
        }

        return null;
    }
    @RequestMapping(value = "/myMicro/myShare")
    public String myShare(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String personType = request.getParameter("personType");
        String title=request.getParameter("title");
        List<Resource> resources=new ArrayList<Resource>();
        String playUrl="";
        resources=this.resourceService.findMyShareResource(user.getUserId(),ResourceType.MICRO,title,page,null);
        List<MyResourceVo> list=new ArrayList<MyResourceVo>();
        for(Resource r:resources){
      	  MyResourceVo mrvo = new MyResourceVo();
      	  EntityFile ent=new EntityFile();
      	MicroLesson  em = new MicroLesson();
      	  em=  this.microLessonService.findMicroLessonByUuid(r.getObjectId());
      	mrvo.setResEnt(r);
      	  if(em!=null){
      		  
      		  ent = this.entityFileService.findFileByUUID(em.getEntityId());
      	  }
          if (ent != null && ent.getThumbnailUrl() != null) {
          mrvo.setThumbnailUrl(ent.getThumbnailUrl());
          mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
      } else {
          mrvo.setThumbnailUrl("");
      }
         if(ent!=null){
      	   list.add(mrvo);
         }
    } 
      request.setAttribute("personType", personType);
      request.setAttribute("microLessonList", list);
      if (index != null && !"".equals(index)) {
          return viewBasePath + mymicroPath + "/myMicroIndex";
      } else {
          return viewBasePath + mymicroPath + "/myMicroList";
      }
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
