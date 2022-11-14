package platform.szxyzxx.web.oa.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.model.ApplayLeave;
import platform.education.oa.model.ApplayLeaveApproveUser;
import platform.education.oa.model.ApplayLeaveDepartmentCount;
import platform.education.oa.model.ApplayLeaveUser;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.utils.UtilDate;
import platform.education.oa.vo.*;
import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.notice.pojo.OaApplyNotice;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.notice.service.OaApplyNoticeService;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.utils.CommonUtil;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请假申请的主页面
 * 
 * @author huangyanchun
 *
 */
@Controller
@RequestMapping("/oa/applayleave")
public class ApplayLeaveController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(ApplayLeaveController.class);


	@Resource
	private BasicSQLService basicSQLService;
	@Autowired
	@Qualifier("asyncWechatNoticeService")
	private SystemWechatNotifyService notifyService;

	@Autowired
	private OaApplyNoticeService oaApplyNoticeService;

	private final static String viewBasePath = "/oa/applayLeave";
	
	/*
	 * 请假审批状态
	 */
	/**
	 * 待审批
	 */
	public static final String daishenpi = "0";
	
	/**
	 * 已审批
	 */
	public static final String yishenpi="1" ;
	
	/**
	 * 审批通过
	 */
	public static final String tongguo="0";
	
	/**
	 * 审批未通过
	 */
	public static final String weitongguo="1";
	
	

	// 有代课教师
	private static Integer you = 0;
	
	//无代课教师
	private static Integer wu = 1;



	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ApplayLeaveCondition condition,
			@ModelAttribute("page") Page page,
			// auditType :用于区别几个展示 1、我的申请请假(own) 2、部门请假(department)
			// 3、全部请假(all) 4、待审批(wait) 5、已审批(deal)
			@RequestParam(value = "auditType", required = false) String auditType,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "menu", required = false) String menu,
			@ModelAttribute("order") Order order, Model model)
			throws ParseException {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		condition.setIsDeleted(false);

		ApplayLeaveCondition condition1 = new ApplayLeaveCondition();
		condition1.setOwnerId(user.getSchoolId());
		condition1.setOwnerType(user.getGroupType());
		condition1.setIsDeleted(false);
		long all = this.applayLeaveService.count(condition1);

		// 设置每页显示两条记录
		page.setPageSize(3);

		// 我的申请请假
		if (auditType != null && auditType.equals("own")) {
			condition.setPropserId(user.getId());
		}

		// 部门请假
		if (auditType != null && auditType.equals("department")) {
			// 将部门条件设置，当前用户没有部门的设置教师ID为-1
			if (user.getTeacherId() != null && !"".equals(user.getTeacherId())) {
				condition.setTeacherId(user.getTeacherId() == null ? -1 : user
						.getTeacherId());
			}
		}

		// 全部请假
		if (auditType != null && auditType.equals("all")) {
			// 将所有条件设置
		}

		// 待审批请假
		if (auditType != null && auditType.equals("wait")) {
			// 将待审批条件设置
			condition.setAuditStatus(daishenpi);
		}

		if (auditType != null && auditType.equals("deal")) {
			// 将已审批条件设置
			condition.setAuditStatus(yishenpi);
		}

		if (departmentId != null && !"".equals(departmentId)) {
			// 将详细的部门条件设置
			condition.setPropserDepartmentId(Integer.parseInt(departmentId));
		}

		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<ApplayLeaveVo> items = this.applayLeaveService
				.findApplayLeaveVoByCondition(condition, page, order);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 小写的mm表示的是分钟
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				ApplayLeaveVo applayLeaveVo = this.applayLeaveService
						.findApplayLeaveVoById(items.get(i).getId());
				// 如果发布请假者所属部门不为空，则把部门名称设置进去
				if (applayLeaveVo.getPropserDepartmentId() != null) {
					// 根据部门id查找对应的部门名称
					Department d = this.departmentService
							.findDepartmentById(applayLeaveVo
									.getPropserDepartmentId());
					if(d!=null){
						items.get(i).setPropserDepartmentName(d.getName());
					}else{
						items.get(i).setPropserDepartmentName("无");
					}
				} else {
					items.get(i).setPropserDepartmentName("无");
				}

				// 计算时间，该值得到的是分钟
				int sum = UtilDate.getLeftDayTime(
						sdf.parse(items.get(i).getStartDate()),
						sdf.parse(items.get(i).getEndDate()));
				int day = (int) Math.floor(sum / (60 * 24));
				items.get(i).setDaySum(day);

				// 代课教师
				ApplayLeaveUserCondition aluCondition = new ApplayLeaveUserCondition();
				aluCondition.setLeaveId(items.get(i).getId());
				List<ApplayLeaveUser> aluList = this.applayLeaveUserService
						.findApplayLeaveUserByCondition(aluCondition);
				String daiName = "";
				if (aluList.size() > 0) {
					for (ApplayLeaveUser alu1 : aluList) {
						daiName += alu1.getDaikeName() + ",";
					}
					items.get(i).setDaikeName(
							daiName.substring(0, daiName.length() - 1));
				} else {
					items.get(i).setDaikeName("无");
				}
				
				
				
				//当前登录的人员是否有权限对该请假进行审批
				ApplayLeaveApproveUserCondition alauCondition = new ApplayLeaveApproveUserCondition();
				alauCondition.setLeaveId(items.get(i).getId());
				List<ApplayLeaveApproveUser>alauList = this.applayLeaveApproveUserService.findApplayLeaveApproveUserByCondition(alauCondition);
				if(alauList.size()>0){
					for(ApplayLeaveApproveUser alau1:alauList){
						if(alau1.getApproveId().equals(user.getId())){
							items.get(i).setIsApprove(true);
						}
					}
				
				}

			}
		}
		// 个人部门信息
		List<DepartmentTeacher> ownDep = CommonUtil.findTeacherOfTeacherId(departmentTeacherService,user);

		// 所有部門信息

		List<Department> dList = CommonUtil.findDepartment(departmentService,user); 
		
		ApplayLeaveDepartmentCountCondition lcon = new ApplayLeaveDepartmentCountCondition();

		// 文字搜索
		lcon.setSearchWord(condition.getSearchWord());

		if (auditType != null && auditType.equals("wait")) {
			// 将待审批条件设置
			lcon.setAuditStatus(daishenpi);
		}

		if (auditType != null && auditType.equals("deal")) {
			// 将已审批条件设置
			lcon.setAuditStatus(yishenpi);
		}

		ApplayLeaveCondition alCondition = new ApplayLeaveCondition();
		alCondition.setOwnerId(user.getSchoolId());
		alCondition.setOwnerType(user.getGroupType());
		alCondition.setIsDeleted(false);
		alCondition.setSearchWord(condition.getSearchWord());

		long totalCount = 0;
		if (auditType != null && auditType.equals("wait")) {
			// 将待审批条件设置
			alCondition.setAuditStatus(daishenpi);
			totalCount = this.applayLeaveService.count(alCondition);
		}

		// 已审批
		if (auditType != null && auditType.equals("deal")) {
			alCondition.setAuditStatus(yishenpi);
			totalCount = this.applayLeaveService.count(alCondition);
		}

		List<ApplayLeaveDepartmentCount> numList = null;
		// 计算我的申请中的部门和全部的每个部门的数量
		List<ApplayLeaveDepartmentCount> numSqList = null;

		try {
			if (menu == null || "".equals(menu)) {
				ApplayLeaveDepartmentCountCondition sqCondition = new ApplayLeaveDepartmentCountCondition();
				sqCondition.setOwnerId(user.getSchoolId());
				sqCondition.setOwnerType(user.getGroupType());
				numSqList = this.applayLeaveDepartmentCountService
						.findApplayLeaveSqnumByCondition(sqCondition);
			} else {
				// 计算请假全部的每个部门的人数
				numList = this.applayLeaveDepartmentCountService
						.findApplayLeaveDepartmentCountByCondition(lcon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("list".equals(sub)) {
			if (menu != null && menu.equals("sp")) {
				viewPath = structurePath("/tongjilist");
			} else {
				viewPath = structurePath("/list");
			}
		} else {
			if (menu != null && menu.equals("sp")) {
				viewPath = structurePath("/tongjiindex");
			} else {
				viewPath = structurePath("/index");
			}
		}

		// 将没有部门的用户 不显示部门
		List<DepartmentTeacher> tdep = CommonUtil.findTeacherOfTeacherId(departmentTeacherService,user);
		if (tdep.size() <= 0) {
			model.addAttribute("noDepartment", "noDepartment");
		}
		model.addAttribute("sub", sub);
		model.addAttribute("nowDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("numSqList", numSqList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("numList", numList);
		model.addAttribute("oList", ownDep);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("auditType", auditType);
		model.addAttribute("menu", menu);
		model.addAttribute("items", items);
		model.addAttribute("curenuser", user.getId());
		model.addAttribute("dList", dList);
		model.addAttribute("all", all);
		model.addAttribute("currentUser", user.getTeacherId());
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ApplayLeave> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") ApplayLeaveCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.applayLeaveService.findApplayLeaveByCondition(condition,
				page, order);
	}

	/**
	 * 我请假的请假页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		// 根据teacherId查找对应的departmentId
		List<DepartmentTeacher> departmentList = CommonUtil.findTeacherOfTeacherIdMore(departmentTeacherService,user);
		model.addAttribute("departmentList", departmentList);

		model.addAttribute("teacherId", user.getTeacherId());

		return new ModelAndView(structurePath("/input"));
	}

	// 保存发布的请假
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			ApplayLeave applayLeave,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@RequestParam(value = "teacherNames", required = false) String teacherNames,
			@RequestParam(value = "personIds", required = false) String personIds,
			@CurrentUser UserInfo user) {

		try {
			String [] persons = personIds.split(",");
			applayLeave.setTitle(user.getRealName()+"的请假申请");
			applayLeave.setAppId(SysContants.SYSTEM_APP_ID);
			applayLeave.setUuid(UUIDUtils.getUUID());
			applayLeave.setOwnerId(user.getSchoolId());
			applayLeave.setOwnerType(user.getGroupType());
			applayLeave.setPropserId(user.getId());
			applayLeave.setPropserName(user.getRealName());
			applayLeave.setAuditStatus(daishenpi);
			applayLeave.setApproveId(Integer.valueOf(persons[0]));
			if (applayLeave.getPropserDepartmentId() == null) {
				applayLeave.setPropserDepartmentId(null);
			} else {
				applayLeave.setPropserDepartmentId(applayLeave
						.getPropserDepartmentId());
			}

			applayLeave = this.applayLeaveService.add(applayLeave);
			// 当前发布请假条的人员有部门，则在oa_applay_leave_department_count表中要添加相应的数据
//			Integer propserDepartmentId = applayLeave.getPropserDepartmentId();
//			String auditStatus = applayLeave.getAuditStatus();
//			if (propserDepartmentId != null) {
//				saveLeaveDepartmentCount(auditStatus, propserDepartmentId, user);
//			}

			// 当前发布请假条的人员有代课教师，则在oa_applay_leave_user表添加相应的数据
			Integer leaveId = applayLeave.getId();
//			if (applayLeave.getIsDaike().equals(you)) {
//				saveLeaveUser(leaveId, teachers, names);
//			}
			
			//当前发布请假条的人员指定审批人员，则在oa_applay_leave_approve_user表添加相应的数据
			
			if(persons!=null){
				saveApproveLeaveUser(leaveId,persons,user);
			}
			if(applayLeave.getId()!=null) {
				//发送待审批通知
				sendNotApprovedSystemAndWechatNotice(applayLeave,user.getRealName(),applayLeave.getDetail());
			}
			return applayLeave != null ? new ResponseInfomation(
					applayLeave.getId(), ResponseInfomation.OPERATION_SUC)
					: new ResponseInfomation();
		} catch (Exception e) {
			return new ResponseInfomation();
		}

	}

	//发送待审批通知
	private void sendNotApprovedSystemAndWechatNotice(ApplayLeave applayLeave,String userName,String explain){
		try {
			List<Map<String,Object>> notifyUserList=basicSQLService.find("select u.id user_id,u.open_id from oa_applay_leave_approve_user a left join yh_user u on u.id=a.approve_id where a.leave_id="+applayLeave.getId());
			if(notifyUserList!=null && notifyUserList.size()>0) {
				SystemWechatNotice swNotice = new SystemWechatNotice();
				swNotice.setTitle("老师请假审批");
				swNotice.setContent("你有一条"+userName+"的请假待审批");
				swNotice.setDataId(applayLeave.getId().toString());
				swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
				swNotice.setDataIdType(SystemNoticeDataIdType.JSQJ);
				swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("老师请假审批", "你有一条"+userName+"的请假待审批"));
				notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

				//发送代办事项
				OaApplyNotice oaApplyNotice=new OaApplyNotice();
				oaApplyNotice.setTitle(userName+"的请假申请");
				oaApplyNotice.setReceiverId(applayLeave.getApproveId());
				oaApplyNotice.setApplicantName(userName);
				oaApplyNotice.setApplyExplain(explain);
				oaApplyNotice.setApplyResult(0);
				oaApplyNotice.setDataIdType(SystemNoticeDataIdType.JSQJ);
				oaApplyNotice.setDataId(applayLeave.getId());
				oaApplyNoticeService.add(oaApplyNotice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//发送审批结果通知
	private void sendApprovedResultSystemAndWechatNotice(Integer id,String result,Integer applyUserId,Integer handlerUserId){
		try {
			List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+applyUserId);
			if(notifyUserList!=null && notifyUserList.size()>0) {
				SystemWechatNotice swNotice = new SystemWechatNotice();
				swNotice.setTitle("请假审批结果");
				swNotice.setContent("审批结果：审批"+result);
				swNotice.setDataId(id.toString());
				swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
				swNotice.setDataIdType(SystemNoticeDataIdType.JSQJ);
				swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("请假审批结果通知", "请假审批"+result));
				notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

				//更新代办
				oaApplyNoticeService.updateApplyResult(handlerUserId,id,result.equals("【同意】")? 1 : 2);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 我申请的请假编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, @CurrentUser UserInfo user) {
		ApplayLeave applayLeave = this.applayLeaveService
				.findApplayLeaveById(id);

		// 根据teacherId查找对应的departmentId
		List<DepartmentTeacher> departmentList = CommonUtil.findTeacherOfTeacherIdMore(departmentTeacherService,user);
		model.addAttribute("departmentList", departmentList);

		// 如果申请请假的人有所属部门的情况
		if (applayLeave.getPropserDepartmentId() != null) {
			// 根据propserDepartmentId查找该用户所属部门
			Department d = this.departmentService
					.findDepartmentById(applayLeave.getPropserDepartmentId());
			model.addAttribute("d", d);
		}

		// 如果申请请假的人有找到代课教师
		if (applayLeave.getIsDaike().equals(you)) {
			ApplayLeaveUserCondition alUserCondition = new ApplayLeaveUserCondition();
			alUserCondition.setLeaveId(applayLeave.getId());
			List<ApplayLeaveUser> alUserList = this.applayLeaveUserService
					.findApplayLeaveUserByCondition(alUserCondition);
			String tNames = "";
			String tIds = "";
			if (alUserList.size() > 0) {
				for (int i = 0; i < alUserList.size(); i++) {
					tNames = tNames + alUserList.get(i).getDaikeName() + ",";
					Teacher tc = this.teacherService.findOfUser(
							user.getSchoolId(), alUserList.get(i).getDaikeId());
					tIds = tIds + tc.getId() + ",";
				}
			}
			model.addAttribute("tNames",
					tNames.substring(0, tNames.length() - 1));
			model.addAttribute("tIds", tIds);
			model.addAttribute("alUserList", alUserList);
		}

		//申请请假的人指定的审批人员
		ApplayLeaveApproveUserCondition alaUserCondition = new ApplayLeaveApproveUserCondition();
		alaUserCondition.setLeaveId(applayLeave.getId());
		List<ApplayLeaveApproveUser>alaUserList = this.applayLeaveApproveUserService.findApplayLeaveApproveUserByCondition(alaUserCondition);
		String pNames = "";
		String pIds = "";
		if(alaUserList.size()>0){
			for(int i=0;i<alaUserList.size();i++){
				Teacher tc = this.teacherService.findOfUser(
						user.getSchoolId(), alaUserList.get(i).getApproveId());
				pNames = pNames +tc.getName()+",";
				pIds = pIds+tc.getId()+",";
			}
			model.addAttribute("pNames",
					pNames.substring(0, pNames.length() - 1));
			model.addAttribute("pIds", pIds);
			model.addAttribute("alaUserList", alaUserList);
		}
		
		
		model.addAttribute("propserDepartmentId",
				applayLeave.getPropserDepartmentId());
		model.addAttribute("teacherId", user.getTeacherId());
		model.addAttribute("applayLeave", applayLeave);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ApplayLeave applayLeave = this.applayLeaveService
				.findApplayLeaveById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("applayLeave", applayLeave);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 删除发布的请假信息
	 * 
	 * @param id
	 * @param applayLeave
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			@CurrentUser UserInfo user, ApplayLeave applayLeave) {
		if (applayLeave != null) {
			applayLeave.setId(id);
		}
		try {

			ApplayLeave al = this.applayLeaveService.findApplayLeaveById(id);
			// 如果发布的请假信息包含有部门，则部门数量表的相应数据要进行改变(即要对对应的部门的数量进行-1操作)
			if (al.getPropserDepartmentId() != null) {
				AlertLeaveDepartmentCount(al.getPropserDepartmentId(), al.getAuditStatus(), user);
			}

			// 如果发布的请假对象有找到代课教师，则代课教师信息表的相关信息要进行修改(即把该请假对应的id的代课教师进行删除)
			if (al.getIsDaike().equals(you)) {
				deleteLeaveUser(id);
			}
			
			//删除请假条的同时要对审批人员信息表的相关信息(即把该请假对应的id的审批人员进行删除)
			deleteApproveLeaveUser(id);

			applayLeave.setIsDeleted(true);
			this.applayLeaveService.modify(applayLeave);

			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
	}

	/**
	 * 保存我申请的请假编辑
	 * 
	 * @param id
	 * @param applayLeave
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			@PathVariable(value = "id") Integer id,
			@RequestParam(value = "fabuId", required = false) String fabuId,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@RequestParam(value = "teacherNames", required = false) String teacherNames,
			@RequestParam(value = "personIds", required = false) String personIds,
			@CurrentUser UserInfo user, ApplayLeave applayLeave) {

		String[] teachers = null;
		String[] names = null;
		String [] persons = null;

		try {
			// applayLeave = this.applayLeaveService.findApplayLeaveById(id);
			if (teacherIds != null || teacherNames != null || personIds!=null) {
				teachers = teacherIds.split(",");
				names = teacherNames.split(",");
				persons = personIds.split(",");
			}
			applayLeave.setId(id);

			if(applayLeave.getApproveState()!=null && !"".equals(applayLeave.getApproveState())){
				applayLeave.setApproveId(user.getId());
				applayLeave.setApproveName(user.getRealName());
			}
			
			ApplayLeave le = applayLeaveService.findApplayLeaveVoById(id);
			if(le.getAuditStatus().equals(yishenpi)){
				return new ResponseInfomation("isApplay");
			}



			applayLeave = this.applayLeaveService.modify(applayLeave);




			if(applayLeave.getApproveState()!=null && !"".equals(applayLeave.getApproveState())){
				
				sendApplyLeaveMesToUser(user,applayLeave.getPropserId());
				// 请假人员有所属部门的情况
				if (applayLeave.getPropserDepartmentId() != null) {                                                                       
					// 未审批的请假部门数量-1，已审批的请假部门数量+1
					Integer propserDepartmentId = applayLeave
							.getPropserDepartmentId();
					AlterLeaveDepartmentCountShenPi(propserDepartmentId, user);
				}
				
				//如果对该请假条进行了审批则对审批人员用户表的审批状态进行修改
				//首先根据请假id查找到对应的审批人员列表，
				ApplayLeaveApproveUserCondition alauCondition =new ApplayLeaveApproveUserCondition();
				alauCondition.setLeaveId(id);
				List<ApplayLeaveApproveUser>alList = this.applayLeaveApproveUserService.findApplayLeaveApproveUserByCondition(alauCondition);
				if(alList.size()>0){
					for(int i=0;i<alList.size();i++){
						if(alList.get(i).getApproveId().equals(user.getId())){
							//再根据得到的审批人员列表和当前登录的人员进行对比，如果user_id相等则进行修改，否则不进行操作
							AlterApproveLeaveUser(id,user.getId());
						}
					}
				}

			}

			if (applayLeave.getApproveState() == null
					|| applayLeave.getApproveState().equals("")) {

				// 请假人员有所属部门的情况
				if (applayLeave.getPropserDepartmentId() != null) {
					/*
					 * 如果保存的请假信息中的部门没有修改，则请假用户部门数量表中的相关数量信息不用修改，
					 * 否则要修改请假用户部门数量表中的相关数量信息
					 */
					if (!"".equals(fabuId)) {

						if (!applayLeave.getPropserDepartmentId().equals(
								Integer.parseInt(fabuId))) {
							/*
							 * 首先把原来的发布部门的数量-1，在根据当前发布的部门进行查找，如果有相应的部门则+1，
							 * 否则在请假用户部门数量表中新建
							 */
							// 原来的-1
							AlertLeaveDepartmentCount(Integer.parseInt(fabuId),
									daishenpi, user);
							// 当前的+1
							saveLeaveDepartmentCount(
									daishenpi,
									applayLeave.getPropserDepartmentId(), user);

						}
					}

				}

				// 请假的时候有找到代课教师的情况
				if (applayLeave.getIsDaike().equals(you)) {
					// 首先删除请假代课教师用户表中与该请假相关的所有信息
					// 根据请假id查找对应的代课教师从而进行删除
					deleteLeaveUser(applayLeave.getId());
					// 再重新在子表中添加相应的信息
					saveLeaveUser(applayLeave.getId(), teachers, names);
				}
				
				
				if(applayLeave.getIsDaike().equals(wu)){
					//删除掉请假代课教师用户表中与该请假相关的所有信息
					// 根据请假id查找对应的代课教师从而进行删除
					deleteLeaveUser(applayLeave.getId());
				}
				
				//编辑请假信息的时候对审批人员的情况进行相应的修改
				//首先删除审批人员表中与该请假相关的所有信息
				//根据请假id查找对应的审批人员从而进行删除
				deleteApproveLeaveUser(applayLeave.getId());
				//再重新在子表中添加相应的信息
				saveApproveLeaveUser(applayLeave.getId(),persons,user);
				
			}


			//发送审批结果给申请人
			ApplayLeave applayLeave1=applayLeaveService.findApplayLeaveById(id);
			String result="【驳回】";
			if(applayLeave1.getAuditStatus().equals("0")){
				result="【同意】";
			}
			sendApprovedResultSystemAndWechatNotice(applayLeave1.getId(),result,applayLeave1.getPropserId(),applayLeave1.getApproveId());

			return applayLeave != null ? new ResponseInfomation(
					applayLeave.getId(), ResponseInfomation.OPERATION_SUC)
					: new ResponseInfomation();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation();
		}

	}

	/**
	 * 按月份进行统计
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/monthChange")
	@ResponseBody
	public List<ApplayLeaveVo> monthChange(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") ApplayLeaveCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			String startTime,String endTime,
			@RequestParam(value = "usePage", required = false) boolean usePage)
			throws ParseException {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		condition.setIsDeleted(false);
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
		Date lastDate = null;
		Date firstDate = null;
		try {
			firstDate = sdf.parse(startTime);
			lastDate = sdf.parse(endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		condition.setBeginDate(firstDate);
		condition.setLastDate(lastDate);
		List<ApplayLeaveVo> list = this.applayLeaveService
				.findApplayLeaveTJByCondition(condition, null, order);

		// 计算申请人数
		Integer renshu = list.size();

		// 所有的请假天数
		Integer allDay = 0;

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++  ) {
				list.get(i).setRenshu(renshu);

				if (list.get(i).getPropserDepartmentId() != null) {
					Department dep = this.departmentService
							.findDepartmentById(list.get(i)
									.getPropserDepartmentId());
					if(dep!=null){
						list.get(i).setPropserDepartmentName(dep.getName());
					}else{
						list.get(i).setPropserDepartmentName("无");
					}
					
				} else {
					list.get(i).setPropserDepartmentName("无");
				}

				Long ciShu = 0L;
				ApplayLeaveCondition alCondition = new ApplayLeaveCondition();
				alCondition.setOwnerId(list.get(i).getOwnerId());
				alCondition.setOwnerType(list.get(i).getOwnerType());
				alCondition.setPropserId(list.get(i).getPropserId());
				alCondition.setIsDeleted(false);
				ciShu = this.applayLeaveService.count(alCondition);
				list.get(i).setCiSum(Integer.parseInt(ciShu.toString()));

				// 计算某个人的请假天数
				List<ApplayLeaveVo> oneList = this.applayLeaveService
						.findApplayLeaveVoByCondition(alCondition, null, null);
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 小写的mm表示的是分钟
				if (oneList.size() > 0) {
					Integer day1 = 0;
					for (ApplayLeaveVo one : oneList) {
						// 计算时间，该值得到的是分钟
						int sum1 = UtilDate.getLeftDayTime(
								sdf1.parse(one.getStartDate()),
								sdf1.parse(one.getEndDate()));
						day1 += (int) Math.floor(sum1 / (60 * 24));
					}

					list.get(i).setOneSum(day1);

					allDay += list.get(i).getOneSum();
				}

				list.get(i).setAllDaySum(allDay);

			}
		}

		return list;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, ApplayLeaveCondition condition) {
		Integer ownerId = condition.getOwnerId();
		Integer appId = condition.getAppId();
		if (user != null && ownerId == null) {
			condition.setOwnerId(user.getSchoolId());
		}

		if (appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}

	/*
	 * 如果当前的请假对象所属部门在oa_applayLeave_department_count表中已经存在数据，
	 * 则只要对该数据的count字段进行+1操作
	 * 否则在该表中新增一条数据
	 */
	// 保存发布请假对象有部门的相关部门的相关数据
	public void saveLeaveDepartmentCount(String auditStatus,
			Integer propserDepartmentId, @CurrentUser UserInfo user) {
		ApplayLeaveDepartmentCountCondition dcondition = new ApplayLeaveDepartmentCountCondition();
		dcondition.setDepartmentId(propserDepartmentId);
		dcondition.setOwnerId(user.getSchoolId());
		dcondition.setOwnerType(user.getGroupType());
		dcondition.setAuditStatus(auditStatus);
		List<ApplayLeaveDepartmentCount> dList = this.applayLeaveDepartmentCountService
				.findApplayLeaveDepartmentCountByCondition(dcondition);
		ApplayLeaveDepartmentCount dcount = new ApplayLeaveDepartmentCount();
		if (dList.size() > 0) {
			int num = dList.get(0).getCount() + 1;
			dcount.setId(dList.get(0).getId());
			dcount.setCount(num);
			this.applayLeaveDepartmentCountService.modify(dcount);
		} else {
			dcount.setDepartmentId(propserDepartmentId);
			dcount.setOwnerId(user.getSchoolId());
			dcount.setOwnerType(user.getGroupType());
			dcount.setCount(1);
			dcount.setAuditStatus(auditStatus);
			this.applayLeaveDepartmentCountService.add(dcount);
		}
	}

	/*
	 * 如果当前的请假对象所属部门在oa_applayLeave_department_count表中已经存在数据，
	 * 则只要对该数据的count字段进行-1操作
	 */
	// 修改请假对象有部门且请假对象所属部门修改前和修改后部门不相同的修改前的部门数量的相关信息
	public void AlertLeaveDepartmentCount(Integer fabuId, String auditStatus,
			@CurrentUser UserInfo user) {
		ApplayLeaveDepartmentCountCondition ldcCondition = null;
		ApplayLeaveDepartmentCount ldc = null;
		ldcCondition = new ApplayLeaveDepartmentCountCondition();
		ldcCondition.setAuditStatus(daishenpi);
		ldcCondition.setDepartmentId(fabuId);
		ldcCondition.setOwnerId(user.getSchoolId());
		ldcCondition.setOwnerType(user.getGroupType());
		List<ApplayLeaveDepartmentCount> ldcList = this.applayLeaveDepartmentCountService
				.findApplayLeaveDepartmentCountByCondition(ldcCondition);
		if (ldcList.size() > 0) {
			ldc = new ApplayLeaveDepartmentCount();
			int num = ldcList.get(0).getCount() - 1 >= 0 ? ldcList.get(0)
					.getCount() - 1 : 0;
			ldc.setId(ldcList.get(0).getId());
			ldc.setCount(num);
			this.applayLeaveDepartmentCountService.modify(ldc);
		}

	}

	// 保存请假对象有选择代课教师的代课教师的相关信息
	public void saveLeaveUser(Integer leaveId, String[] teachers, String[] names) {
		ApplayLeaveUser alUser = null;
		Teacher teacher = null;
		if (teachers.length > 0) {
			for (int i = 0; i < teachers.length; i++) {
				teacher = this.teacherService.findTeacherById(Integer
						.valueOf(teachers[i]));
				alUser = new ApplayLeaveUser();
				alUser.setLeaveId(leaveId);
				alUser.setDaikeId(teacher.getUserId());
				alUser.setDaikeName(names[i]);
				this.applayLeaveUserService.add(alUser);
			}
		}
	}

	// 删除请假对象有选择代课教师的代课教师的相关信息
	public void deleteLeaveUser(Integer leaveId) {
		List<ApplayLeaveUser> aluList = null;
		try {
			ApplayLeaveUserCondition luCondition = new ApplayLeaveUserCondition();
			luCondition.setLeaveId(leaveId);
			aluList = this.applayLeaveUserService
					.findApplayLeaveUserByCondition(luCondition);
			if (aluList.size() > 0) {
				for (ApplayLeaveUser lu : aluList) {
					this.applayLeaveUserService.remove(lu);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//保存请假对象所指定的审批人员的相关信息
	public  void  saveApproveLeaveUser(Integer leaveId,String persons[],@CurrentUser UserInfo user){
		ApplayLeaveApproveUser alaUser = null;
		Teacher person = null;
		if (persons.length > 0) {
			for (int i = 0; i < persons.length; i++) {
				person = this.teacherService.findTeacherById(Integer
						.valueOf(persons[i]));
				alaUser = new ApplayLeaveApproveUser();
				alaUser.setLeaveId(leaveId);
				alaUser.setApproveId(person.getUserId());
				alaUser.setApproveState(0);
				this.applayLeaveApproveUserService.add(alaUser);
			}
		}
		
//		List<Integer>personList = new ArrayList<Integer>();
//		Teacher t = null;
//		for(int i=0;i<persons.length;i++){
//			t = this.teacherService.findTeacherById(Integer.parseInt(persons[i]));
//			personList.add(t.getUserId());
//		}
		//将申请请假的信息发送给给审批人，让审批人进行审批
		//sendApplyPaperMesToManager(user, personList);
	}
	
	
	//对请假对象所指定的审批人员的相关信息进行修改
	public void AlterApproveLeaveUser(Integer leaveId,Integer approveId){
		ApplayLeaveApproveUserCondition alauCondition = new ApplayLeaveApproveUserCondition();
		alauCondition.setLeaveId(leaveId);
		alauCondition.setApproveId(approveId);
		List<ApplayLeaveApproveUser>alauList1 = this.applayLeaveApproveUserService.findApplayLeaveApproveUserByCondition(alauCondition);
		ApplayLeaveApproveUser alau1 = null;
		if(alauList1.size()>0){
			alau1 = new ApplayLeaveApproveUser();
			alau1.setId(alauList1.get(0).getId());
			alau1.setApproveState(1);
			this.applayLeaveApproveUserService.modify(alau1);
			
		}
	}
	
	
	//删除请假对象所指定的审批人员的相关信息
	public void deleteApproveLeaveUser(Integer leaveId){
		List<ApplayLeaveApproveUser> alauList = null;
		try {
			ApplayLeaveApproveUserCondition lauCondition = new ApplayLeaveApproveUserCondition();
			lauCondition.setLeaveId(leaveId);
			alauList = this.applayLeaveApproveUserService.findApplayLeaveApproveUserByCondition(lauCondition);
			if (alauList.size() > 0) {
				for (ApplayLeaveApproveUser lau : alauList) {
					this.applayLeaveApproveUserService.remove(lau);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改请假对象有部门的审批前和待审批后的部门数量的相关信息
	public void AlterLeaveDepartmentCountShenPi(Integer propserDepartmentId,
			@CurrentUser UserInfo user) {
		// 未审核的请假部门数量-1
		ApplayLeaveDepartmentCountCondition aldCondition = null;
		ApplayLeaveDepartmentCount ald = null;
		aldCondition = new ApplayLeaveDepartmentCountCondition();
		aldCondition.setAuditStatus(daishenpi);
		aldCondition.setOwnerId(user.getSchoolId());
		aldCondition.setOwnerType(user.getGroupType());
		aldCondition.setDepartmentId(propserDepartmentId);
		List<ApplayLeaveDepartmentCount> aldList = this.applayLeaveDepartmentCountService
				.findApplayLeaveDepartmentCountByCondition(aldCondition);
		if (aldList.size() > 0) {
			ald = new ApplayLeaveDepartmentCount();
			int num = aldList.get(0).getCount() - 1 >= 0 ? aldList.get(0)
					.getCount() - 1 : 0;
			ald.setId(aldList.get(0).getId());
			ald.setCount(num);
			this.applayLeaveDepartmentCountService.modify(ald);
		}

		// 已审核的请假部门数量+1
		ApplayLeaveDepartmentCountCondition aldCondition1 = null;
		ApplayLeaveDepartmentCount ald1 = null;
		aldCondition1 = new ApplayLeaveDepartmentCountCondition();
		aldCondition1.setAuditStatus(yishenpi);
		aldCondition1.setOwnerId(user.getSchoolId());
		aldCondition1.setOwnerType(user.getGroupType());
		aldCondition1.setDepartmentId(propserDepartmentId);
		List<ApplayLeaveDepartmentCount> aldList1 = this.applayLeaveDepartmentCountService
				.findApplayLeaveDepartmentCountByCondition(aldCondition1);
		if (aldList1.size() > 0) {
			ald1 = new ApplayLeaveDepartmentCount();
			int num1 = aldList1.get(0).getCount() + 1;
			ald1.setId(aldList1.get(0).getId());
			ald1.setCount(num1);
			this.applayLeaveDepartmentCountService.modify(ald1);
		} else {
			ald1 = new ApplayLeaveDepartmentCount();
			ald1.setDepartmentId(propserDepartmentId);
			ald1.setOwnerId(user.getSchoolId());
			ald1.setOwnerType(user.getGroupType());
			ald1.setCount(1);
			ald1.setAuditStatus(yishenpi);
			this.applayLeaveDepartmentCountService.add(ald1);
		}
	}
	
	
	/**
	 * 将申请请假的信息发送给相对应的审批人员
	 * @param user
	 * @param personList
	 */
	public void sendApplyPaperMesToManager(UserInfo user,
			List<Integer> personList) {

		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的请假申请待审批！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_QINGJIASHENPI);
		PushMessageUtil.sendMessage(message, personList);
		PushMessageUtil.pushMessageList(personList);
	}
	
	
	
	/**
	 * 2015-10-16
	 * 将申请请假的信息发送给相对应的管理员
	 *//*
	public void sendApplyLeaveMesToManager(String persmissionCode, Integer groupId,UserInfo user){
		List<Integer> userIdList = userService.findUserIdByPermissionCode(persmissionCode,groupId);
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的请假申请待审批！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_QINGJIASHENPI);
		PushMessageUtil.sendMessage(message, userIdList);
		PushMessageUtil.pushMessageList(userIdList);
	}*/
	
	
	/**
	 * 2015-10-16
	 * 将请假审批的信息发送给相对应的申请人
	 */
	public void sendApplyLeaveMesToUser(UserInfo user,Integer applyUserId){
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的请假申请审批结果通知！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_QINGJIASHENQING);
		PushMessageUtil.sendMessage(message, applyUserId);
		PushMessageUtil.pushMessage(applyUserId);
	}

}
