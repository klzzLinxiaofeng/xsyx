package platform.szxyzxx.web.oa.contans;
/**
 * OA常量
 * @author Administrator
 */
public class ContansOfOa {
	//=======OA 会议 、通知 发布对象========//
	/**
	 * 指的是发布对象是所有人 0
	 */
	public static final Integer all = 0;
	/**
	 * 指的是发布对象是部门 1
	 */
	public static final Integer department = 1;
	/**
	 * 指的是发布对象是指定的人员 2
	 */
	public static final Integer personer = 2;
	//=======OA 会议 、通知 发布对象========//
	
	/**
	 * 管理员角色
	 */
	public static final String Administrator = "2";
	
	/**
	 * 教师角色
	 */
	public static final String teacher = "1";
	
	/**
	 * OA车辆审批的菜单
	 */
	public static final String oaMenuOfApproveCard = "YONG_CHE_SHEN_PI";
	
	/**
	 * OA维修受理的菜单 
	 */
	public static final String oaMenuOfApproveRepair = "WEI_XIU_SHOU_LI";
	
	/**
	 * OA请假审批的菜单
	 */
	public static final String oaMenuOfApproveLeave = "QING_JIA_SHEN_Pi";
	
	/**
	 * OA文印处理的菜单
	 */
	public static final String oaMenuOfApproveIndia = "WEN_YIN_CHU_LI";
	
	/**
	 * 发布的通知类型是学校  
	 * pj.school
	 */
	public static final String NoticeType_school = "pj.school";
	/**
	 * 发布的通知类型是部门  
	 * pj.dept
	 */
	public static final String NoticeType_dept = "pj.dept";
	
	/**
	 * 发布的通知类型是个人this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);this.pubNoticeService.findNoticesByReceiver(schoolID, OaPushConstant.NoticeType_school, beginDate, endDate);
	 * pj.person
	 */
	public static final String NoticeType_person = "pj.person";


	public static final String NoticeType_team = "pj.team";


	public static final String NoticeType_allTeacher = "pj.allTeacher";

	public static final String NoticeType_allStudent = "pj.allStudent";


	/**
	 * 调课审批状态 审批中
	 */
	public static final Integer COURSE_TRANSFER_APPROVAL = 0;

	/**
	 * 调课审批状态 同意
	 */
	public static final Integer COURSE_TRANSFER_AGREE = 1;

	/**
	 * 调课审批状态 驳回
	 */
	public static final Integer COURSE_TRANSFER_REJECT = 2;

	/**
	 * 调课审批状态 已审批（包括同意和驳回）
	 */
	public static final Integer COURSE_TRANSFER_APPROVED = 3;
	
}
