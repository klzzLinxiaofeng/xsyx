package platform.szxyzxx.web.homework.controller;

import framework.generic.dao.Page;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import platform.education.resource.model.CatalogResource;
import platform.education.resource.model.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import platform.education.generalcode.model.Grade;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.vo.GradeCondition;
import platform.education.generalcode.vo.TextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.education.homework.contants.HomeworkType;
import platform.education.homework.model.Homework;
import platform.education.homework.vo.HomeworkCondition;
import platform.education.homework.vo.HomeworkVo;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.ImportCatalogVo;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.utils.StringUtils;
import platform.szxyzxx.web.resource.contants.MyResourceContans;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping(value = "/homeworkhouse")
public class HomeworkHouseController extends BaseController{


    private static final String COMMON_DIR = "homework/common";
    private static final String DIR = "homework/examhouse";
    @Autowired
    @Qualifier("catalogResourceService")
    private CatalogResourceService catalogResourceService;
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,@CurrentUser UserInfo user) {
        String microId = request.getParameter("microId");
        Integer flag = homeworkService.deleteMyHomework(SysContants.SYSTEM_APP_ID, user.getId(), microId);
        return null;
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        String publish = request.getParameter("publish");
        if(publish!=null&&!"".equals(publish)){
            request.setAttribute("publish", publish);
        }
        return COMMON_DIR + "/upload";
    }
    
    @RequestMapping(value = "/uploadIndex")
    public String uploadIndex(HttpServletRequest request, HttpServletResponse response) {
        return DIR + "/uploadIndex";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        String microId = request.getParameter("microId");
        Homework ml  = this.homeworkService.findHomeworkByUuid(microId);
        request.setAttribute("micro", ml);
        return DIR + "/myHomeworkEdit";
    }

    @RequestMapping(value = "/myHomework")
    public String myHomework(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        page.setPageSize(5);
        List<HomeworkVo> lessonList = this.homeworkService.searchHomework(SysContants.SYSTEM_APP_ID, page, user.getId(), title, null, null, null, null);
        request.setAttribute("microLessonList", lessonList);
        if (index != null && !"".equals(index)) {
            return DIR + "/myHomeworkIndex";
        } else {
            return DIR + "/myHomeworkList";
        }
    }
    
    @RequestMapping(value = "/favHomework")
    public String favHomework(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        String title = request.getParameter("title");
        page.setPageSize(5);
        List<HomeworkVo> lessonList = this.homeworkService.searchUserFavorites(SysContants.SYSTEM_APP_ID, page,user.getId(), title, null);
        request.setAttribute("microLessonList", lessonList);
        if (index != null && !"".equals(index)) {
            return DIR + "/favHomeworkIndex";
        } else {
            return DIR + "/favHomeworkList";
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
        if(catalogEnd != null&&!catalogEnd.equals("")){
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
        //ImportCatalogVo vo = ResourceUtils.setDirectory(request);
       
        Homework ml = this.homeworkService.saveOrUpdateHomework(SysContants.SYSTEM_APP_ID, microId, entityId, HomeworkType.COMMON_HOMEWORK, title, user.getId(), description);
        String suffix="";
        if(ml != null){
        suffix = this.entityFileService.findFileByUUID(ml.getEntityId()).getExtension();
        addPersonResource(HomeworkCondition.DEFAULT_RESTYPE, ml.getUuid(), title, description, ml.getAppId(), null, user,suffix);
        }
        if(checked != null&&"true".equals(checked)){
        	if(ml != null){
        		this.addResource(HomeworkCondition.DEFAULT_RESTYPE, ml.getUuid(), title, description, ml.getAppId(), null, user,suffix,vo);
        	}
        	
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
    private void addResource(Integer resType,String objectId,String noSuffixTitle,String description,Integer appId,String thumbnailUrl,UserInfo user,String suffix,ImportCatalogVo vo){
    	
    	Integer owerId = user.getSchoolId();
		String schoolName = user.getSchoolName();
     	String lib_uuid="";
        boolean isPersonal=true;
		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
		resourceLibraryCondition.setAppId(appId);
		resourceLibraryCondition.setOwerId(owerId);
		
		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		ResourceLibrary resourceLibrary = new ResourceLibrary();
		if (resourceLibraryList != null && resourceLibraryList.size() > 0) {//如果存在取对应的uui
			resourceLibrary = resourceLibraryList.get(0);
		} else {//如果不存在添加对应的记录
			ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
			resourceLibraryAdd.setAppId(appId);
			resourceLibraryAdd.setOwerId(owerId);
			resourceLibraryAdd.setName(schoolName);
			resourceLibraryAdd.setUuid(UUIDUtil.getUUID());//获取唯一值uuid
			resourceLibrary = this.resourceLibraryService.add(resourceLibraryAdd);
			
		}
		lib_uuid=resourceLibrary.getUuid();
	     String	verify=ResourceCondition.DEFAULT_SHARE_PROCESS;
		isPersonal=false;
		 Resource res = new Resource();
         res.setCreateDate(new Date());
         res.setModifyDate(new Date());
         res.setVerify(verify);
         res.setLibraryId(lib_uuid);
         res.setUserId(user.getId());
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
         addCatalog(res.getId(),res.getObjectId(),res.getResType(),vo);
    	
    }
    private void addPersonResource(Integer resType,String objectId,String noSuffixTitle,String description,Integer appId,String thumbnailUrl,UserInfo user,String suffix){
    	Resource res = new Resource();
        res.setCreateDate(new Date());
        res.setModifyDate(new Date());
        res.setVerify(ResourceCondition.DEFAULT_PERSON_PROCESS);
        res.setLibraryId("");
        res.setUserId(user.getId());
        res.setUserName(user.getRealName());
        res.setResType(resType);
        res.setObjectId(objectId);
        res.setTitle(noSuffixTitle);
        res.setIconType(IconUtil.setIcon(suffix));
        res.setUuid(UUIDUtil.getUUID());
        res.setDescription(description);
        res.setThumbnailUrl(thumbnailUrl);
        res.setIsPersonal(true);
        res = this.resourceService.add(res);
    }
    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
    public void  addCatalog(Integer resourceId,String objectId,Integer resType,ImportCatalogVo vo){
 	   //教材目录关联
 		CatalogResource cr = new CatalogResource();
 		cr = this.catalogResourceService.assembCatalog(cr, vo);
 		cr.setResourceId(resourceId);
 		cr.setObjectId(objectId);
 		cr.setResourceType(resType);
 		cr.setCreateDate(new Date());
 		cr.setModifyDate(new Date());
 		cr = this.catalogResourceService.add(cr);
 	   
    }
}
