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
 * Description:??????????????????
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: ????????????????????????????????????
 * </p>
 *
 * @Function ???????????????
 * @Author ?????????
 * @Version 1.0
 * @Date 2015???5???18???
 */
@Controller
public class BaseController {

    /***************************************************** System services start **************************************************************************/

    /**BaseController
     * ???????????????
     */
    @Autowired
    @Qualifier("teachClientService")
    protected TeachClientService teachClientService;
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

    @Autowired
    @Qualifier("jcItemService")
    protected ItemService itemService;

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

    // ????????????
    @Autowired
    @Qualifier("jcTextbookCatalogService")
    protected TextbookCatalogService jcTextbookCatalogService;
    // ??????
    @Autowired
    @Qualifier("jcTextbookService")
    protected TextbookService jcTextbookService;
    // ??????????????????
    @Autowired
    @Qualifier("jcTextbookDirectoryService")
    protected TextbookDirectoryService jcTextbookDirectoryService;
    // ???????????????
    @Autowired
    @Qualifier("jcTextbookPublisherService")
    protected TextbookPublisherService jcTextbookPublisherService;
    // ??????????????????
    @Autowired
    @Qualifier("jcTextbookMasterService")
    protected TextbookMasterService jcTextbookMasterService;

    // ????????????????????????
    @Autowired
    @Qualifier("writerService")
    protected WriterService writerService;

    // ??????
    @Autowired
    @Qualifier("jcTextbookVersionService")
    protected TextbookVersionService jcTextbookVersionService;

    // ??????
    @Autowired
    @Qualifier("jcTextbookVolumnService")
    protected TextbookVolumnService jcTextbookVolumnService;

    // ??????????????????
    @Autowired
    @Qualifier("resTextbookCatalogService")
    protected ResTextbookCatalogService resTextbookCatalogService;
    // ??????
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

    // ??????
    @Autowired
    @Qualifier("schoolService")
    protected SchoolService schoolService;

    // ??????
    @Autowired
    @Qualifier("gradeService")
    protected GradeService gradeService;

    // ??????
    @Autowired
    @Qualifier("teamService")
    protected TeamService teamService;

    // ??????
    @Autowired
    @Qualifier("studentService")
    protected StudentService studentService;

    // ??????
    @Autowired
    @Qualifier("teacherService")
    protected TeacherService teacherService;

    // ??????
    @Autowired
    @Qualifier("subjectService")
    protected SubjectService subjectService;

    // ????????????
    @Autowired
    @Qualifier("jcSubjectService")
    protected platform.education.generalcode.service.SubjectService jcSubjectService;

    // ?????????
    @Autowired
    @Qualifier("teamTeacherService")
    protected TeamTeacherService teamTeacherService;

    // ??????
    @Autowired
    @Qualifier("jcStageService")
    protected StageService jcStageService;

    // ??????
    @Autowired
    @Qualifier("schoolYearService")
    protected SchoolYearService schoolYearService;

    // ??????
    @Autowired
    @Qualifier("schoolTermService")
    protected SchoolTermService schoolTermService;

    // ????????????
    @Autowired
    @Qualifier("schoolTermCurrentService")
    protected SchoolTermCurrentService schoolTermCurrentService;

    // ??????????????????
    @Autowired
    @Qualifier("upLoadInformationService")
    protected UpLoadInformationService upLoadInformationService;

    // ??????????????????
    @Autowired
    @Qualifier("jcGradeService")
    protected platform.education.generalcode.service.GradeService jcGradeService;

    // ??????
    @Autowired
    @Qualifier("floorService")
    protected FloorService floorService;

    // ??????
    @Autowired
    @Qualifier("classroomService")
    protected ClassroomService classroomService;

    // ??????????????????
    @Autowired
    @Qualifier("canteenService")
    protected CanteenService canteenService;

    // ??????????????????
    @Autowired
    @Qualifier("canteenGoodsService")
    protected CanteenGoodsService canteenGoodsService;

    // ?????????????????????
    @Autowired
    @Qualifier("canteenSupplyService")
    protected CanteenSupplyService canteenSupplyService;

    // ??????--??????
    @Autowired
    @Qualifier("subjectGradeService")
    protected SubjectGradeService subjectGradeService;

    // ????????????
    @Autowired
    @Qualifier("teachService")
    protected TeachService teachService;

    @Autowired
    @Qualifier("userService")
    protected UserService userService;

    // ????????????
    @Autowired
    @Qualifier("subjectTeacherService")
    protected SubjectTeacherService subjectTeacherService;

    @Autowired
    @Qualifier("teamStudentService")
    protected TeamStudentService teamStudentService;

    // ??????
    @Autowired
    @Qualifier("schoolSystemService")
    protected SchoolSystemService schoolSystemService;

    // ????????????
    @Autowired
    @Qualifier("studentAidService")
    protected StudentAidService studentAidService;

    // ????????????
    @Autowired
    @Qualifier("studentPunishService")
    protected StudentPunishService studentPunishService;

    // ????????????
    @Autowired
    @Qualifier("studentAwardService")
    protected StudentAwardService studentAwardService;

    // ????????????
    @Autowired
    @Qualifier("parentService")
    protected ParentService parentService;

    // ????????????
    @Autowired
    @Qualifier("publicClassService")
    protected PublicClassService publicClassService;

    // ??????????????????
    @Autowired
    @Qualifier("jcRegionService")
    protected RegionService jcRegionService;

    @Autowired
    @Qualifier("parentStudentService")
    protected ParentStudentService parentStudentService;

    // ?????????????????????
    @Autowired
    @Qualifier("laboratoryRoomService")
    protected LaboratoryRoomService laboratoryRoomService;

    // ????????????????????????
    @Autowired
    @Qualifier("equipmentService")
    protected EquipmentService equipmentService;

    // ?????????????????????
    @Autowired
    @Qualifier("consumableService")
    protected ConsumableService consumableService;

    /**
     * ?????????
     */
    @Autowired
    @Qualifier("personService")
    protected PersonService personService;

    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("parentProxyService")
    protected ParentProxyService parentProxyService;

    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("departmentService")
    protected DepartmentService departmentService;

    /**
     * ??????????????????????????????
     */
    @Autowired
    @Qualifier("departmentTeacherService")
    protected DepartmentTeacherService departmentTeacherService;

    /**
     * ???????????????
     */
    @Autowired
    @Qualifier("syllabusService")
    protected SyllabusService syllabusService;

    // ????????????
    @Autowired
    @Qualifier("studentCheckAttendanceService")
    protected StudentCheckAttendanceService studentCheckAttendanceService;

    // ??????????????????
    @Autowired
    @Qualifier("studentHealthArchiveService")
    protected StudentHealthArchiveService studentHealthArchiveService;

    /**
     * ????????????
     */
    @Autowired
    @Qualifier("schoolCalendarService")
    protected SchoolCalendarService schoolCalendarService;

    /**
     * ?????????????????????
     */
    @Autowired
    @Qualifier("moralEvaluationStudentService")
    protected MoralEvaluationStudentService moralEvaluationStudentService;

    /**
     * ????????????????????????
     */
    @Autowired
    @Qualifier("moralEvaluationItemService")
    protected MoralEvaluationItemService moralEvaluationItemService;

    /**
     * ??????????????????
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
     * ??????????????????
     */
    @Autowired
    @Qualifier("examTeamSubjectService")
    protected ExamTeamSubjectService examTeamSubjectService;

    // ??????????????????
    @Autowired
    @Qualifier("studentScoreService")
    protected StudentScoreService studentScoreService;

    // ????????????--????????????
    @Autowired
    @Qualifier("performanceEvaluationItemService")
    protected PerformanceEvaluationItemService performanceEvaluationItemService;

    // ????????????--????????????
    @Autowired
    @Qualifier("performanceEvaluationTaskService")
    protected PerformanceEvaluationTaskService performanceEvaluationTaskService;

    // ????????????--????????????????????????
    @Autowired
    @Qualifier("performanceEvaluationPersonnelService")
    protected PerformanceEvaluationPersonnelService performanceEvaluationPersonnelService;

    // ????????????--???????????????????????????
    @Autowired
    @Qualifier("performanceEvaluationTaskItemService")
    protected PerformanceEvaluationTaskItemService performanceEvaluationTaskItemService;

    // ????????????--????????????
    @Autowired
    @Qualifier("performanceEvaluationScoreService")
    protected PerformanceEvaluationScoreService performanceEvaluationScoreService;

    //??????
    @Autowired
    @Qualifier("pjExamService")
    protected PjExamService pjExamService;

    //??????-??????
    @Autowired
    @Qualifier("examStudentService")
    protected ExamStudentService examStudentService;

    //??????  - ??????
    @Autowired
    @Qualifier("examStatService")
    protected ExamStatService examStatService;

    //????????????--????????????
    @Autowired
    @Qualifier("teamUserService")
    protected TeamUserService teamUserService;

    //????????????
    //2016-7-27
    @Autowired
    @Qualifier("jobControlService")
    protected JobControlService jobControlService;

    //??????????????????
    //2016-12-8
    @Autowired
    @Qualifier("teacherAssistService")
    protected TeacherAssistService teacherAssistService;
    /***************************************************** ???????????? services start ************************************************************************/
    // ????????????
    @Autowired
    @Qualifier("newStudentService")
    protected NewStudentService newStudentService;

    /***************************************************** ???????????? services end ************************************************************************/

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

    // ????????????
    @Autowired
    @Qualifier("dormitoryService")
    protected DormitoryService dormitoryService;

    // ????????????
    @Autowired
    @Qualifier("dormitoryAttendanceService")
    protected DormitoryAttendanceService dormitoryAttendanceService;

    // ????????????
    @Autowired
    @Qualifier("dormitoryPersonService")
    protected DormitoryPersonService dormitoryPersonService;

    // ????????????
    @Autowired
    @Qualifier("dormitoryDaycheckService")
    protected DormitoryDaycheckService dormitoryDaycheckService;

    // ?????????????????????
    @Autowired
    @Qualifier("vehicleManagementService")
    protected VehicleManagementService vehicleManagementService;

    // ?????????????????????
    @Autowired
    @Qualifier("applyCardService")
    protected ApplyCardService applyCardService;

    @Autowired
    @Qualifier("oaApplycardDepartmentCountService")
    protected OaApplycardDepartmentCountService oaApplycardDepartmentCountService;

    // ????????????(??????
    @Autowired
    @Qualifier("applayIndiaService")
    protected ApplayIndiaService applayIndiaService;

    @Autowired
    @Qualifier("applayIndiaDepartmentCountService")
    protected ApplayIndiaDepartmentCountService applayIndiaDepartmentCountService;

    // ????????????(???)
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

    // ????????????(??????)
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

    // ???????????????
    @Autowired
    @Qualifier("applyRepairService")
    protected ApplyRepairService applyRepairService;

    // ????????????
    @Autowired
    @Qualifier("acceptRepariService")
    protected AcceptRepariService acceptRepariService;

    // ??????????????????
    @Autowired
    @Qualifier("textbookManagerService")
    protected TextbookManagerService textbookManagerService;

    // ??????????????????
    @Autowired
    @Qualifier("meetingDepartmentCountService")
    protected MeetingDepartmentCountService meetingDepartmentCountService;

    // ???????????? ??????????????????????????? ????????????????????????????????? ??????????????????
    @Autowired
    @Qualifier("noticeUserDeletedService")
    protected NoticeUserDeletedService noticeUserDeletedService;

    //??????????????????
    @Autowired
    @Qualifier("warrantyEquipmentService")
    protected WarrantyEquipmentService warrantyEquipmentService;

    //????????????
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
    //???????????????
    @Resource
    protected PjPermissionService pjPermissionService;


    //??????
    @Resource
    protected MicroLessonService microLessonService;
    //????????????
    @Resource
    protected MicroPrepareService microPrepareService;
    @Resource
    protected Storage storage;

    // ??????
    @Resource
    protected ExamService examService;
    // ????????????
    @Resource
    protected ExamPrepareService examPrepareService;

    // ??????
    @Resource
    protected HomeworkService homeworkService;
    // ????????????
    @Resource
    protected HomeworkPrepareService homeworkPrepareService;

    // ??????
    @Resource
    protected LearningDesignService learningDesignService;

/*	// ??????
	@Resource
	protected MicroLessonService microLessonService;
	// ????????????
	@Resource
	protected MicroPrepareService microPrepareService;
	@Resource
	protected Storage storage;*/

    /*********************************************************** RESOURCE END **************************************************************************/

    /*********************************************************** MESSAGE BEGIN **************************************************************************/
    // ?????????????????????
    @Autowired
    @Qualifier("messageService")
    protected MessageService messageService;

    /*********************************************************** MESSAGE END **************************************************************************/

    // ????????????
    @Autowired
    @Qualifier("teamAwardService")
    protected TeamAwardService teamAwardService;

    // ????????????
    @Autowired
    @Qualifier("teamMessageService")
    protected TeamMessageService teamMessageService;

    // ????????????????????????
    @Autowired
    @Qualifier("teamMessageFileService")
    protected TeamMessageFileService teamMessageFileService;

    // ????????????
    @Autowired
    @Qualifier("teamAccountService")
    protected TeamAccountService teamAccountService;

    // ?????????
    @Autowired
    @Qualifier("welcomeService")
    protected WelcomeService welcomeService;

    // ???????????????
    @Autowired
    @Qualifier("welcomeTemplateService")
    protected WelcomeTemplateService welcomeTemplateService;

    /*********************************************************** BBX END **************************************************************************/

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(
//                dateFormat, true));
//        // ?????????????????????????????????
//        SimpleDateFormat dateFormat2 = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss");
//        CustomDateEditor preciseDateEditor = new CustomDateEditor(dateFormat2,
//                true);
//        binder.registerCustomEditor(Date.class, "preciseEndDate",
//                preciseDateEditor);
//        binder.registerCustomEditor(Date.class, "preciseStartDate",
//                preciseDateEditor);


        /**
         * 2020???11???23???18:08:52 ??????
         * @auth yhc
         * ?????????????????????????????????
         */
        // ????????????(????????????)
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    if (StringUtils.isBlank(text)) {
                        setValue(null);
                        return;
                    }
                    String format = null; // ??????
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


    /*************************************************** ???????????? ******************************************************/

    //????????????
    @Autowired
    @Qualifier("teamApsService")
    protected TeamApsService teamApsService;

    //????????????
    @Autowired
    @Qualifier("studentApsService")
    protected StudentApsService studentApsService;


    //????????????????????????
    //2017-3-15
    @Autowired
    @Qualifier("studentAidPublishService")
    protected StudentAidPublishService studentAidPublishService;

    /*************************************************** ???????????? ******************************************************/


    /***************************************************???????????? ******************************************************/

    //????????????
    @Autowired
    @Qualifier("bpBwSignageService")
    protected BpBwSignageService bpBwSignageService;

    @Autowired
    @Qualifier("roomTeamService")
    protected RoomTeamService roomTeamService;

    /*************************************************** ????????? ******************************************************/
    @Autowired
    @Qualifier("materialService")
    protected MaterialService materialService;


    @Autowired
    @Qualifier("teachingPlanService")
    protected TeachingPlanService teachingPlanService;


    @Autowired
    @Qualifier("learningPlanService")
    protected LearningPlanService learningPlanService;//?????????

}
