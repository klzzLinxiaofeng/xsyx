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
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 *
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月18日
 */
@Controller
public class BaseController {

    /**
     * *************************************************** System services start *************************************************************************
     */
    /**
     * 用户绑定业务器
     */
    @Autowired
    @Qualifier("userBindingService")
    protected UserBindingService userBindingService;
    /**
     * 校级管理业务器
     */
    @Autowired
    @Qualifier("groupAdminService")
    protected GroupAdminService groupAdminService;
    /**
     * 分组业务器
     */
    @Autowired
    @Qualifier("groupService")
    protected GroupService groupService;
    /**
     * 访问控制列表
     */
    @Autowired
    @Qualifier("aclService")
    protected AclService aclService;
    /**
     * 角色权限资源业务器
     */
    @Autowired
    @Qualifier("userPermissionService")
    protected UserPermissionService userPermissionService;
    /**
     * 用户权限资源业务器
     */
    @Autowired
    @Qualifier("rolePermissionService")
    protected RolePermissionService rolePermissionService;
    /**
     * 应用业务器
     */
    @Autowired
    @Qualifier("appService")
    protected AppService appService;
    /**
     * 分组权限业务器
     */
    @Autowired
    @Qualifier("groupPermissionService")
    protected GroupPermissionService groupPermissionService;
    /**
     * 权限资源业务器
     */
    @Autowired
    @Qualifier("permissionService")
    protected PermissionService permissionService;
    /**
     * 角色业务器
     */
    @Autowired
    @Qualifier("roleService")
    protected RoleService roleService;
    /**
     * 复杂型基础代码缓存业务器
     */
    @Autowired
    @Qualifier("jcCacheService")
    protected JcCacheService jcCacheService;
    /**
     * 简单型KV基础代码缓存业务器
     */
    @Autowired
    @Qualifier("jcGcCacheService")
    protected JcGcCacheService jcGcCacheService;
    /**
     * 分配用户角色业务器
     */
    @Autowired
    @Qualifier("userRoleService")
    protected UserRoleService userRoleService;
    /**
     * 个人信息
     */
    @Autowired
    @Qualifier("profileService")
    protected ProfileService profileService;
    /**
     * 用户管理区域
     */
    @Autowired
    @Qualifier("userRegionService")
    protected UserRegionService userRegionService;
    //教材目录
    @Autowired
    @Qualifier("jcTextbookCatalogService")
    protected TextbookCatalogService jcTextbookCatalogService;
    //教材
    @Autowired
    @Qualifier("jcTextbookService")
    protected TextbookService jcTextbookService;
    //教材分类目录
    @Autowired
    @Qualifier("jcTextbookDirectoryService")
    protected TextbookDirectoryService jcTextbookDirectoryService;
    //教材出版社
    @Autowired
    @Qualifier("jcTextbookPublisherService")
    protected TextbookPublisherService jcTextbookPublisherService;
    //教材综合服务
    @Autowired
    @Qualifier("jcTextbookMasterService")
    protected TextbookMasterService jcTextbookMasterService;
    //教材的编写者信息
    @Autowired
    @Qualifier("writerService")
    protected WriterService writerService;
    @Qualifier("jcGradeService")
    protected GradeService gradeService;
    //基础科目
    @Autowired
    @Qualifier("jcSubjectService")
    protected platform.education.generalcode.service.SubjectService jcSubjectService;
    // 学段
    @Autowired
    @Qualifier("jcStageService")
    protected StageService jcStageService;
    // 基础年级代码
    @Autowired
    @Qualifier("jcGradeService")
    protected platform.education.generalcode.service.GradeService jcGradeService;
    
    
	//册次
	@Autowired
	@Qualifier("jcTextbookVolumnService")
	protected TextbookVolumnService jcTextbookVolumnService;
	
	// 校本教材目录
		@Autowired
		@Qualifier("resTextbookCatalogService")
		protected ResTextbookCatalogService resTextbookCatalogService;
	
    
    @Autowired
    @Qualifier("userService")
    protected UserService userService;
    //基础数据区域
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
	protected LearningPlanService learningPlanService;//导学案 
	
    @Autowired
	@Qualifier("schoolService")
	protected SchoolService schoolService;

    @Autowired
    @Qualifier("resPaidResourceService")
    protected ResPaidResourceService resPaidResourceService;

    //微课学习记录
    @Resource
    protected MicroUserRecordService microUserRecordService;
    
    @Resource
    protected Storage storage;
    
    /*********************************************************** MESSAGE BEGIN **************************************************************************/
    //通知中心业务器
    @Resource
	protected MessageService messageService;
	
	/*********************************************************** MESSAGE END **************************************************************************/
	

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
        //添加日期转换的重载方法
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
