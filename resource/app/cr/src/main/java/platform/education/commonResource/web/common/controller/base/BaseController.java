package platform.education.commonResource.web.common.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import platform.education.commonResource.web.common.util.SessionManager;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.ResPaidResource;
import platform.education.generalTeachingAffair.service.ResPaidResourceService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalcode.service.GradeService;
import platform.education.generalcode.service.JcCacheService;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.generalcode.service.RegionService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.TextbookCatalogService;
import platform.education.generalcode.service.TextbookDirectoryService;
import platform.education.generalcode.service.TextbookMasterService;
import platform.education.generalcode.service.TextbookPublisherService;
import platform.education.generalcode.service.TextbookService;
import platform.education.generalcode.service.TextbookVolumnService;
import platform.education.generalcode.service.WriterService;
import platform.education.homework.service.HomeworkService;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.material.service.MaterialService;
import platform.education.message.service.MessageService;
import platform.education.micro.service.MicroLessonService;
import platform.education.micro.service.MicroUserRecordService;
import platform.education.resource.service.FavoritesService;
import platform.education.resource.service.LikesService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceService;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.education.user.service.AclService;
import platform.education.user.service.AppService;
import platform.education.user.service.GroupAdminService;
import platform.education.user.service.GroupPermissionService;
import platform.education.user.service.GroupService;
import platform.education.user.service.PermissionService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.RolePermissionService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserPermissionService;
import platform.education.user.service.UserRegionService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import framework.generic.storage.Storage;

/**
 * <p>Title:BaseController.java</p>
 * <p>Description:??????????????????</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: ????????????????????????????????????</p>
 *
 * @Function ???????????????
 * @Author ?????????
 * @Version 1.0
 * @Date 2015???5???18???
 */
@Controller
public class BaseController {

    /**
     * *************************************************** System services start *************************************************************************
     */
    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("userBindingService")
    protected UserBindingService userBindingService;
    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("groupAdminService")
    protected GroupAdminService groupAdminService;
    /**
     * ???????????????
     */
    @Autowired
    @Qualifier("groupService")
    protected GroupService groupService;
    /**
     * ??????????????????
     */
    @Autowired
    @Qualifier("aclService")
    protected AclService aclService;
    /**
     * ???????????????????????????
     */
    @Autowired
    @Qualifier("userPermissionService")
    protected UserPermissionService userPermissionService;
    /**
     * ???????????????????????????
     */
    @Autowired
    @Qualifier("rolePermissionService")
    protected RolePermissionService rolePermissionService;
    /**
     * ???????????????
     */
    @Autowired
    @Qualifier("appService")
    protected AppService appService;
    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("groupPermissionService")
    protected GroupPermissionService groupPermissionService;
    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("permissionService")
    protected PermissionService permissionService;
    /**
     * ???????????????
     */
    @Autowired
    @Qualifier("roleService")
    protected RoleService roleService;
    /**
     * ????????????????????????????????????
     */
    @Autowired
    @Qualifier("jcCacheService")
    protected JcCacheService jcCacheService;
    /**
     * ?????????KV???????????????????????????
     */
    @Autowired
    @Qualifier("jcGcCacheService")
    protected JcGcCacheService jcGcCacheService;
    /**
     * ???????????????????????????
     */
    @Autowired
    @Qualifier("userRoleService")
    protected UserRoleService userRoleService;
    /**
     * ????????????
     */
    @Autowired
    @Qualifier("profileService")
    protected ProfileService profileService;
    /**
     * ??????????????????
     */
    @Autowired
    @Qualifier("userRegionService")
    protected UserRegionService userRegionService;
    //????????????
    @Autowired
    @Qualifier("jcTextbookCatalogService")
    protected TextbookCatalogService jcTextbookCatalogService;
    //??????
    @Autowired
    @Qualifier("jcTextbookService")
    protected TextbookService jcTextbookService;
    //??????????????????
    @Autowired
    @Qualifier("jcTextbookDirectoryService")
    protected TextbookDirectoryService jcTextbookDirectoryService;
    //???????????????
    @Autowired
    @Qualifier("jcTextbookPublisherService")
    protected TextbookPublisherService jcTextbookPublisherService;
    //??????????????????
    @Autowired
    @Qualifier("jcTextbookMasterService")
    protected TextbookMasterService jcTextbookMasterService;
    //????????????????????????
    @Autowired
    @Qualifier("writerService")
    protected WriterService writerService;
    @Qualifier("jcGradeService")
    protected GradeService gradeService;
    //????????????
    @Autowired
    @Qualifier("jcSubjectService")
    protected platform.education.generalcode.service.SubjectService jcSubjectService;
    // ??????
    @Autowired
    @Qualifier("jcStageService")
    protected StageService jcStageService;
    // ??????????????????
    @Autowired
    @Qualifier("jcGradeService")
    protected platform.education.generalcode.service.GradeService jcGradeService;
    
    
	//??????
	@Autowired
	@Qualifier("jcTextbookVolumnService")
	protected TextbookVolumnService jcTextbookVolumnService;
	
	// ??????????????????
		@Autowired
		@Qualifier("resTextbookCatalogService")
		protected ResTextbookCatalogService resTextbookCatalogService;
	
    
    @Autowired
    @Qualifier("userService")
    protected UserService userService;
    //??????????????????
    @Autowired
    @Qualifier("jcRegionService")
    protected RegionService jcRegionService;
    @Autowired
    @Qualifier("entityFileService")
    protected EntityFileService entityFileService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    @Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
    @Autowired
    @Qualifier("favoritesService")
    protected FavoritesService favoritesService;
    @Autowired
    @Qualifier("likesService")
    protected LikesService likesService;
    @Autowired
    @Qualifier("microLessonService")
    protected MicroLessonService microLessonService;
    @Autowired
    @Qualifier("homeworkService")
    protected HomeworkService homeworkService;
    @Autowired
    @Qualifier("learningDesignService")
    protected LearningDesignService learningDesignService;
    @Autowired
    @Qualifier("materialService")
    protected MaterialService materialService;
    @Autowired
    @Qualifier("examService")
    protected ExamService examService;
    @Autowired
    @Qualifier("teachingPlanService")
    protected TeachingPlanService teachingPlanService;
    
	@Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("learningPlanService")
	protected LearningPlanService learningPlanService;//????????? 
	
    @Autowired
	@Qualifier("schoolService")
	protected SchoolService schoolService;

    @Autowired
    @Qualifier("resPaidResourceService")
    protected ResPaidResourceService resPaidResourceService;

    //??????????????????
    @Resource
    protected MicroUserRecordService microUserRecordService;
    
    @Resource
    protected Storage storage;
    
    /*********************************************************** MESSAGE BEGIN **************************************************************************/
    //?????????????????????
    @Resource
	protected MessageService messageService;
	
	/*********************************************************** MESSAGE END **************************************************************************/
	

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
        //?????????????????????????????????
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor preciseDateEditor = new CustomDateEditor(dateFormat2, true);
        binder.registerCustomEditor(Date.class, "preciseEndDate", preciseDateEditor);
        binder.registerCustomEditor(Date.class, "preciseStartDate", preciseDateEditor);
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
}
