package platform.szxyzxx.web.common.controller.base;

import framework.generic.storage.Storage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import platform.education.clazz.service.*;
import platform.education.enrollStudent.service.NewStudentService;
import platform.education.exam.service.ExamPrepareService;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalcode.service.*;
import platform.education.hnzxx.ui.service.PjPermissionService;
import platform.education.homework.service.HomeworkPrepareService;
import platform.education.homework.service.HomeworkService;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.material.service.MaterialService;
import platform.education.message.service.MessageService;
import platform.education.micro.service.MicroLessonService;
import platform.education.micro.service.MicroPrepareService;
import platform.education.oa.service.*;
import platform.education.personnel.service.*;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceService;
import platform.education.school.affair.service.*;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.education.user.service.*;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.szxyzxx.services.teach.service.TeachService;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Title:BaseController.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 *
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月18日
 */
@Controller
public class BaseController {

    /***************************************************** System services start **************************************************************************/

    /**BaseController
     * 客户端接口
     */
    @Autowired
    @Qualifier("teachClientService")
    protected TeachClientService teachClientService;
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

    @Autowired
    @Qualifier("jcItemService")
    protected ItemService itemService;

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

    // 教材目录
    @Autowired
    @Qualifier("jcTextbookCatalogService")
    protected TextbookCatalogService jcTextbookCatalogService;
    // 教材
    @Autowired
    @Qualifier("jcTextbookService")
    protected TextbookService jcTextbookService;
    // 教材分类目录
    @Autowired
    @Qualifier("jcTextbookDirectoryService")
    protected TextbookDirectoryService jcTextbookDirectoryService;
    // 教材出版社
    @Autowired
    @Qualifier("jcTextbookPublisherService")
    protected TextbookPublisherService jcTextbookPublisherService;
    // 教材综合服务
    @Autowired
    @Qualifier("jcTextbookMasterService")
    protected TextbookMasterService jcTextbookMasterService;

    // 教材的编写者信息
    @Autowired
    @Qualifier("writerService")
    protected WriterService writerService;

    // 版本
    @Autowired
    @Qualifier("jcTextbookVersionService")
    protected TextbookVersionService jcTextbookVersionService;

    // 册次
    @Autowired
    @Qualifier("jcTextbookVolumnService")
    protected TextbookVolumnService jcTextbookVolumnService;

    // 校本教材目录
    @Autowired
    @Qualifier("resTextbookCatalogService")
    protected ResTextbookCatalogService resTextbookCatalogService;
    // 教材
    @Autowired
    @Qualifier("resTextbookService")
    protected ResTextbookService resTextbookService;

    /***************************************************** System services end **************************************************************************/

    /***************************************************** personnel services end ***********************************************************************/

    @Autowired
    @Qualifier("teacherTitleService")
    protected TeacherTitleService teacherTitleService;

    /***************************************************** personnel services end ***********************************************************************/

    /***************************************************** Affair services start ************************************************************************/

    // 学校
    @Autowired
    @Qualifier("schoolService")
    protected SchoolService schoolService;

    // 年级
    @Autowired
    @Qualifier("gradeService")
    protected GradeService gradeService;

    // 班级
    @Autowired
    @Qualifier("teamService")
    protected TeamService teamService;

    // 学生
    @Autowired
    @Qualifier("studentService")
    protected StudentService studentService;

    // 教师
    @Autowired
    @Qualifier("teacherService")
    protected TeacherService teacherService;

    // 科目
    @Autowired
    @Qualifier("subjectService")
    protected SubjectService subjectService;

    // 基础科目
    @Autowired
    @Qualifier("jcSubjectService")
    protected platform.education.generalcode.service.SubjectService jcSubjectService;

    // 班主任
    @Autowired
    @Qualifier("teamTeacherService")
    protected TeamTeacherService teamTeacherService;

    // 学段
    @Autowired
    @Qualifier("jcStageService")
    protected StageService jcStageService;

    // 学年
    @Autowired
    @Qualifier("schoolYearService")
    protected SchoolYearService schoolYearService;

    // 学期
    @Autowired
    @Qualifier("schoolTermService")
    protected SchoolTermService schoolTermService;

    // 当前学期
    @Autowired
    @Qualifier("schoolTermCurrentService")
    protected SchoolTermCurrentService schoolTermCurrentService;

    // 导入学生信息
    @Autowired
    @Qualifier("upLoadInformationService")
    protected UpLoadInformationService upLoadInformationService;

    // 基础年级代码
    @Autowired
    @Qualifier("jcGradeService")
    protected platform.education.generalcode.service.GradeService jcGradeService;

    // 楼层
    @Autowired
    @Qualifier("floorService")
    protected FloorService floorService;

    // 教室
    @Autowired
    @Qualifier("classroomService")
    protected ClassroomService classroomService;

    // 后勤食堂管理
    @Autowired
    @Qualifier("canteenService")
    protected CanteenService canteenService;

    // 后勤商品管理
    @Autowired
    @Qualifier("canteenGoodsService")
    protected CanteenGoodsService canteenGoodsService;

    // 后勤供货商管理
    @Autowired
    @Qualifier("canteenSupplyService")
    protected CanteenSupplyService canteenSupplyService;

    // 年级--学科
    @Autowired
    @Qualifier("subjectGradeService")
    protected SubjectGradeService subjectGradeService;

    // 教学教务
    @Autowired
    @Qualifier("teachService")
    protected TeachService teachService;

    @Autowired
    @Qualifier("userService")
    protected UserService userService;

    // 科任教师
    @Autowired
    @Qualifier("subjectTeacherService")
    protected SubjectTeacherService subjectTeacherService;

    @Autowired
    @Qualifier("teamStudentService")
    protected TeamStudentService teamStudentService;

    // 学制
    @Autowired
    @Qualifier("schoolSystemService")
    protected SchoolSystemService schoolSystemService;

    // 学生资助
    @Autowired
    @Qualifier("studentAidService")
    protected StudentAidService studentAidService;

    // 学生处分
    @Autowired
    @Qualifier("studentPunishService")
    protected StudentPunishService studentPunishService;

    // 学生奖励
    @Autowired
    @Qualifier("studentAwardService")
    protected StudentAwardService studentAwardService;

    // 家长信息
    @Autowired
    @Qualifier("parentService")
    protected ParentService parentService;

    // 开课管理
    @Autowired
    @Qualifier("publicClassService")
    protected PublicClassService publicClassService;

    // 基础数据区域
    @Autowired
    @Qualifier("jcRegionService")
    protected RegionService jcRegionService;

    @Autowired
    @Qualifier("parentStudentService")
    protected ParentStudentService parentStudentService;

    // 校务实验室管理
    @Autowired
    @Qualifier("laboratoryRoomService")
    protected LaboratoryRoomService laboratoryRoomService;

    // 校务仪器设备管理
    @Autowired
    @Qualifier("equipmentService")
    protected EquipmentService equipmentService;

    // 校务易耗品管理
    @Autowired
    @Qualifier("consumableService")
    protected ConsumableService consumableService;

    /**
     * 档案表
     */
    @Autowired
    @Qualifier("personService")
    protected PersonService personService;

    /**
     * 家长信息服务类
     */
    @Autowired
    @Qualifier("parentProxyService")
    protected ParentProxyService parentProxyService;

    /**
     * 部门管理业务器
     */
    @Autowired
    @Qualifier("departmentService")
    protected DepartmentService departmentService;

    /**
     * 部门教职工管理业务器
     */
    @Autowired
    @Qualifier("departmentTeacherService")
    protected DepartmentTeacherService departmentTeacherService;

    /**
     * 课表业务器
     */
    @Autowired
    @Qualifier("syllabusService")
    protected SyllabusService syllabusService;

    // 学生考勤
    @Autowired
    @Qualifier("studentCheckAttendanceService")
    protected StudentCheckAttendanceService studentCheckAttendanceService;

    // 学生健康档案
    @Autowired
    @Qualifier("studentHealthArchiveService")
    protected StudentHealthArchiveService studentHealthArchiveService;

    /**
     * 学校校历
     */
    @Autowired
    @Qualifier("schoolCalendarService")
    protected SchoolCalendarService schoolCalendarService;

    /**
     * 学生德育业务器
     */
    @Autowired
    @Qualifier("moralEvaluationStudentService")
    protected MoralEvaluationStudentService moralEvaluationStudentService;

    /**
     * 学生德育评价结果
     */
    @Autowired
    @Qualifier("moralEvaluationItemService")
    protected MoralEvaluationItemService moralEvaluationItemService;

    /**
     * 德育项业务器
     */
    @Autowired
    @Qualifier("moralEvaluationResultService")
    protected MoralEvaluationResultService moralEvaluationResultService;

    @Autowired
    @Qualifier("entityFileService")
    protected EntityFileService entityFileService;

    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    /**
     * 考试日程安排
     */
    @Autowired
    @Qualifier("examTeamSubjectService")
    protected ExamTeamSubjectService examTeamSubjectService;

    // 考试成绩录入
    @Autowired
    @Qualifier("studentScoreService")
    protected StudentScoreService studentScoreService;

    // 绩效考核--考核项目
    @Autowired
    @Qualifier("performanceEvaluationItemService")
    protected PerformanceEvaluationItemService performanceEvaluationItemService;

    // 绩效考核--考核任务
    @Autowired
    @Qualifier("performanceEvaluationTaskService")
    protected PerformanceEvaluationTaskService performanceEvaluationTaskService;

    // 绩效考核--考核人与被考核人
    @Autowired
    @Qualifier("performanceEvaluationPersonnelService")
    protected PerformanceEvaluationPersonnelService performanceEvaluationPersonnelService;

    // 绩效考核--考核任务与考核项目
    @Autowired
    @Qualifier("performanceEvaluationTaskItemService")
    protected PerformanceEvaluationTaskItemService performanceEvaluationTaskItemService;

    // 绩效考核--考核评分
    @Autowired
    @Qualifier("performanceEvaluationScoreService")
    protected PerformanceEvaluationScoreService performanceEvaluationScoreService;

    //考试
    @Autowired
    @Qualifier("pjExamService")
    protected PjExamService pjExamService;

    //考试-学生
    @Autowired
    @Qualifier("examStudentService")
    protected ExamStudentService examStudentService;

    //考试  - 统计
    @Autowired
    @Qualifier("examStatService")
    protected ExamStatService examStatService;

    //绩效考核--考核评分
    @Autowired
    @Qualifier("teamUserService")
    protected TeamUserService teamUserService;

    //业务控制
    //2016-7-27
    @Autowired
    @Qualifier("jobControlService")
    protected JobControlService jobControlService;

    //教师辅助业务
    //2016-12-8
    @Autowired
    @Qualifier("teacherAssistService")
    protected TeacherAssistService teacherAssistService;
    /***************************************************** 招生管理 services start ************************************************************************/
    // 新生注册
    @Autowired
    @Qualifier("newStudentService")
    protected NewStudentService newStudentService;

    /***************************************************** 招生管理 services end ************************************************************************/

    /***************************************************** OA BEGIN ************************************************************************/
    @Autowired
    @Qualifier("deviceService")
    protected DeviceService deviceService;

    @Autowired
    @Qualifier("auditcardService")
    protected AuditcardService auditcardService;

    @Autowired
    @Qualifier("vehicleService")
    protected VehicleService vehicleService;

    @Autowired
    @Qualifier("usecardService")
    protected UsecardService usecardService;

    @Autowired
    @Qualifier("returncardService")
    protected ReturncardService returncardService;

    @Autowired
    @Qualifier("repairService")
    protected RepairService repairService;

    @Autowired
    @Qualifier("repairFileService")
    protected RepairFileService repairFileService;

    // 宿舍信息
    @Autowired
    @Qualifier("dormitoryService")
    protected DormitoryService dormitoryService;

    // 住宿考勤
    @Autowired
    @Qualifier("dormitoryAttendanceService")
    protected DormitoryAttendanceService dormitoryAttendanceService;

    // 宿舍人员
    @Autowired
    @Qualifier("dormitoryPersonService")
    protected DormitoryPersonService dormitoryPersonService;

    // 日常检查
    @Autowired
    @Qualifier("dormitoryDaycheckService")
    protected DormitoryDaycheckService dormitoryDaycheckService;

    // 车辆管理（新）
    @Autowired
    @Qualifier("vehicleManagementService")
    protected VehicleManagementService vehicleManagementService;

    // 用车申请（新）
    @Autowired
    @Qualifier("applyCardService")
    protected ApplyCardService applyCardService;

    @Autowired
    @Qualifier("oaApplycardDepartmentCountService")
    protected OaApplycardDepartmentCountService oaApplycardDepartmentCountService;

    // 文印申请(新）
    @Autowired
    @Qualifier("applayIndiaService")
    protected ApplayIndiaService applayIndiaService;

    @Autowired
    @Qualifier("applayIndiaDepartmentCountService")
    protected ApplayIndiaDepartmentCountService applayIndiaDepartmentCountService;

    // 公文管理(新)
    @Autowired
    @Qualifier("paperService")
    protected PaperService paperService;

    @Autowired
    @Qualifier("paperUserService")
    protected PaperUserService paperUserService;

    @Autowired
    @Qualifier("paperDepartmentCountService")
    protected PaperDepartmentCountService paperDepartmentCountService;

    @Autowired
    @Qualifier("paperUserReadService")
    protected PaperUserReadService paperUserReadService;

    @Autowired
    @Qualifier("indiaService")
    private IndiaService indiaService;

    // 请假管理(修改)
    @Autowired
    @Qualifier("applayLeaveService")
    protected ApplayLeaveService applayLeaveService;

    @Autowired
    @Qualifier("applayLeaveDepartmentCountService")
    protected ApplayLeaveDepartmentCountService applayLeaveDepartmentCountService;

    @Autowired
    @Qualifier("applayLeaveUserService")
    protected ApplayLeaveUserService applayLeaveUserService;

    @Autowired
    @Qualifier("applayLeaveApproveUserService")
    protected ApplayLeaveApproveUserService applayLeaveApproveUserService;

    // 报修（新）
    @Autowired
    @Qualifier("applyRepairService")
    protected ApplyRepairService applyRepairService;

    // 报修受理
    @Autowired
    @Qualifier("acceptRepariService")
    protected AcceptRepariService acceptRepariService;

    // 学校教材管理
    @Autowired
    @Qualifier("textbookManagerService")
    protected TextbookManagerService textbookManagerService;

    // 部门会议数量
    @Autowired
    @Qualifier("meetingDepartmentCountService")
    protected MeetingDepartmentCountService meetingDepartmentCountService;

    // 通知公告 手机端用户删除数据 当前用户看不见本条数据 其他用户可见
    @Autowired
    @Qualifier("noticeUserDeletedService")
    protected NoticeUserDeletedService noticeUserDeletedService;

    //维修过保设备
    @Autowired
    @Qualifier("warrantyEquipmentService")
    protected WarrantyEquipmentService warrantyEquipmentService;

    //审核业务
    @Autowired
    @Qualifier("approvalService")
    protected ApprovalService approvalService;
    /***************************************************** OA END ************************************************************************/

    /*********************************************************** RESOURCE BEGIN ************************************************************************/

    @Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;

    @Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
    //菜单自定义
    @Resource
    protected PjPermissionService pjPermissionService;


    //微课
    @Resource
    protected MicroLessonService microLessonService;
    //微课发布
    @Resource
    protected MicroPrepareService microPrepareService;
    @Resource
    protected Storage storage;

    // 试卷
    @Resource
    protected ExamService examService;
    // 试卷发布
    @Resource
    protected ExamPrepareService examPrepareService;

    // 作业
    @Resource
    protected HomeworkService homeworkService;
    // 作业发布
    @Resource
    protected HomeworkPrepareService homeworkPrepareService;

    // 作业
    @Resource
    protected LearningDesignService learningDesignService;

/*	// 微课
	@Resource
	protected MicroLessonService microLessonService;
	// 微课发布
	@Resource
	protected MicroPrepareService microPrepareService;
	@Resource
	protected Storage storage;*/

    /*********************************************************** RESOURCE END **************************************************************************/

    /*********************************************************** MESSAGE BEGIN **************************************************************************/
    // 通知中心业务器
    @Autowired
    @Qualifier("messageService")
    protected MessageService messageService;

    /*********************************************************** MESSAGE END **************************************************************************/

    // 班级荣誉
    @Autowired
    @Qualifier("teamAwardService")
    protected TeamAwardService teamAwardService;

    // 班级通知
    @Autowired
    @Qualifier("teamMessageService")
    protected TeamMessageService teamMessageService;

    // 班级通知消息附件
    @Autowired
    @Qualifier("teamMessageFileService")
    protected TeamMessageFileService teamMessageFileService;

    // 班级账号
    @Autowired
    @Qualifier("teamAccountService")
    protected TeamAccountService teamAccountService;

    // 欢迎词
    @Autowired
    @Qualifier("welcomeService")
    protected WelcomeService welcomeService;

    // 欢迎词模板
    @Autowired
    @Qualifier("welcomeTemplateService")
    protected WelcomeTemplateService welcomeTemplateService;

    /*********************************************************** BBX END **************************************************************************/

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(
//                dateFormat, true));
//        // 添加日期转换的重载方法
//        SimpleDateFormat dateFormat2 = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss");
//        CustomDateEditor preciseDateEditor = new CustomDateEditor(dateFormat2,
//                true);
//        binder.registerCustomEditor(Date.class, "preciseEndDate",
//                preciseDateEditor);
//        binder.registerCustomEditor(Date.class, "preciseStartDate",
//                preciseDateEditor);


        /**
         * 2020年11月23日18:08:52 修改
         * @auth yhc
         * 原有不符合其他时间格式
         */
        // 重新处理(动态格式)
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    if (StringUtils.isBlank(text)) {
                        setValue(null);
                        return;
                    }
                    String format = null; // 格式
                    if (text.indexOf(":") == -1){
						format = "yyyy-MM-dd";
					} else {
                        if (text.split(":").length == 2){
							format = "yyyy-MM-dd HH:mm";
						} else if (text.split(":").length == 3){
							format = "yyyy-MM-dd HH:mm:ss";
						} else if (text.split(":").length == 4){
                            format = "yyyy-MM-dd HH:mm:ss:SSS";
                        }
					}
                    setValue(new SimpleDateFormat(format).parse(text));
                } catch (Exception e) {
                    setValue(null);
                }
            }
        });
    }


    /*************************************************** 德育管理 ******************************************************/

    //课堂评价
    @Autowired
    @Qualifier("teamApsService")
    protected TeamApsService teamApsService;

    //学生评价
    @Autowired
    @Qualifier("studentApsService")
    protected StudentApsService studentApsService;


    //学生资助公布标识
    //2017-3-15
    @Autowired
    @Qualifier("studentAidPublishService")
    protected StudentAidPublishService studentAidPublishService;

    /*************************************************** 德育管理 ******************************************************/


    /***************************************************智慧班牌 ******************************************************/

    //班牌设备
    @Autowired
    @Qualifier("bpBwSignageService")
    protected BpBwSignageService bpBwSignageService;

    @Autowired
    @Qualifier("roomTeamService")
    protected RoomTeamService roomTeamService;

    /*************************************************** 云资源 ******************************************************/
    @Autowired
    @Qualifier("materialService")
    protected MaterialService materialService;


    @Autowired
    @Qualifier("teachingPlanService")
    protected TeachingPlanService teachingPlanService;


    @Autowired
    @Qualifier("learningPlanService")
    protected LearningPlanService learningPlanService;//导学案

}
