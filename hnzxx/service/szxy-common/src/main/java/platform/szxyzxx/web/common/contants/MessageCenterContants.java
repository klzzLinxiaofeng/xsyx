package platform.szxyzxx.web.common.contants;

import platform.szxyzxx.web.common.util.MessageCenterContantsAccessor;


/**
 * 
 *<p>
 * Title: MessageCenterContants.java 
 * </p>
 *<p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>  
 * @Fuction 方法描述 ：消息中心常量
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年8月12日
 */
public class MessageCenterContants {
	
	//====================================================================================================
	//										系统消息 常量		
	//====================================================================================================
	
	/**
	 * 作业学习code
	 */
	private final static String PATH_CODE_HOMEWORK_KEY = "message.resource.code.homework";
	public final static String PATH_CODE_HOMEWORK = MessageCenterContantsAccessor.getStringProp(PATH_CODE_HOMEWORK_KEY);
	
	/**
	 * 试卷学习code
	 */
	private final static String PATH_CODE_EXAM_KEY = "message.resource.code.exam";
	public final static String PATH_CODE_EXAM = MessageCenterContantsAccessor.getStringProp(PATH_CODE_EXAM_KEY);
	/**
	 * 微课学习code
	 */
	private final static String PATH_CODE_MICRO_KEY = "message.resource.code.micro";
	public final static String PATH_CODE_MICRO = MessageCenterContantsAccessor.getStringProp(PATH_CODE_MICRO_KEY);
	/**
	 * 课件学习code
	 */
	private final static String PATH_CODE_LEARNING_DESIGN_KEY = "message.resource.code.learningDesign";
	public final static String PATH_CODE_LEARNING_DESIGN = MessageCenterContantsAccessor.getStringProp(PATH_CODE_LEARNING_DESIGN_KEY);
	/**
	 * 完成作业学习code
	 */
	private final static String FINISHED_PATH_CODE_HOMEWORK_KEY = "message.finish.code.homework";
	public final static String FINISHED_PATH_CODE_HOMEWORK = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_HOMEWORK_KEY);
	
	/**
	 * 试卷学习code
	 */
	private final static String FINISHED_PATH_CODE_EXAM_KEY = "message.finish.code.exam";
	public final static String FINISHED_PATH_CODE_EXAM = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_EXAM_KEY);
	/**
	 * 微课学习code
	 */
	private final static String FINISHED_PATH_CODE_MICRO_KEY = "message.finish.code.micro";
	public final static String FINISHED_PATH_CODE_MICRO = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_MICRO_KEY);
	/**
	 * 课件学习code
	 */
	private final static String FINISHED_PATH_CODE_LEARNING_DESIGN_KEY = "message.finish.code.learningDesign";
	public final static String FINISHED_PATH_CODE_LEARNING_DESIGN = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_LEARNING_DESIGN_KEY);
	
	/**
	 * 办公会议code
	 */
	private final static String FINISHED_PATH_CODE_OAMEETING_KEY = "message.finish.code.oa_meeting";
	public final static String FINISHED_PATH_CODE_OAMEETING = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_OAMEETING_KEY);
	
	/**
	 * 办公通知公告code
	 */
	private final static String FINISHED_PATH_CODE_TONGZHIGONGGAO_KEY = "message.finish.code.oa_notice";
	public final static String FINISHED_PATH_CODE_TONGZHIGONGGAO = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_TONGZHIGONGGAO_KEY);
	
	/**
	 * 车辆申请code
	 */
	private final static String FINISHED_PATH_CODE_CHELIANGSHENQING_KEY = "message.finish.code.oa_applycard";
	public final static String FINISHED_PATH_CODE_CHELIANGSHENQING = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_CHELIANGSHENQING_KEY);
	
	/**
	 * 车辆申请审批code
	 */
	private final static String FINISHED_PATH_CODE_CHELIANGSHENPI_KEY = "message.finish.code.oa_approvecard";
	public final static String FINISHED_PATH_CODE_CHELIANGSHENPI = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_CHELIANGSHENPI_KEY);
	
	/**
	 * 维修申请code
	 */
	private final static String FINISHED_PATH_CODE_WEIXIUSHENQING_KEY = "message.finish.code.oa_applyRepair";
	public final static String FINISHED_PATH_CODE_WEIXIUSHENQING = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_WEIXIUSHENQING_KEY);
	
	/**
	 * 维修受理code
	 */
	private final static String FINISHED_PATH_CODE_WEIXIUSHOULI_KEY = "message.finish.code.oa_approveRepair";
	public final static String FINISHED_PATH_CODE_WEIXIUSHOULI = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_WEIXIUSHOULI_KEY);
	
	/**
	 * 请假申请code
	 */
	private final static String FINISHED_PATH_CODE_QINGJIASHENQING_KEY = "message.finish.code.oa_applyLeave";
	public final static String FINISHED_PATH_CODE_QINGJIASHENQING = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_QINGJIASHENQING_KEY);
	
	/**
	 * 请假受理code
	 */
	private final static String FINISHED_PATH_CODE_QINGJIASHENPI_KEY = "message.finish.code.oa_approveLeave";
	public final static String FINISHED_PATH_CODE_QINGJIASHENPI = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_QINGJIASHENPI_KEY);
	
	
	/**
	 * 文印申请code
	 */
	private final static String FINISHED_PATH_CODE_WENYINSHENQING_KEY = "message.finish.code.oa_applyIndia";
	public final static String FINISHED_PATH_CODE_WENYINSHENQING = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_WENYINSHENQING_KEY);
	
	/**
	 * 文印受理code
	 */
	private final static String FINISHED_PATH_CODE_WENYINCHULI_KEY = "message.finish.code.oa_approveIndia";
	public final static String FINISHED_PATH_CODE_WENYINCHULI = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_WENYINCHULI_KEY);
	
	/**
	 * 公文申请code
	 */
	private final static String FINISHED_PATH_CODE_GONGWENGUANLI_KEY = "message.finish.code.oa_applyPaper";
	public final static String FINISHED_PATH_CODE_GONGWENGUANLI = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_GONGWENGUANLI_KEY);
	
	/**
	 * 视频会议code
	 */
	private final static String UIN_NEW_MEETING_KEY = "message.uin.new_meeting";
	public final static String UIN_NEW_MEETING = MessageCenterContantsAccessor.getStringProp(UIN_NEW_MEETING_KEY);
	
	
	/**
	 * 班班星  班级通知  code
	 */
	private final static String FINISHED_PATH_CODE_TEAMMESSAGE_KEY = "message.finish.code.bbx_teamMessage";
	public final static String  FINISHED_PATH_CODE_TEAMMESSAGE = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_TEAMMESSAGE_KEY);
	
	
	/**
	 * 班班星  全校通知 code
	 */
	private final static String FINISHED_PATH_CODE_SCHOOLMESSAGE_KEY = "message.finish.code.bbx_schoolMessage";
	public final static String  FINISHED_PATH_CODE_SCHOOLMESSAGE = MessageCenterContantsAccessor.getStringProp(FINISHED_PATH_CODE_SCHOOLMESSAGE_KEY);
	
	//====================================================================================================
	//											教师消息 常量		
	//===================================================================================================
	
	public final static String TEACHER_MESSAGE = "0";
	
}

