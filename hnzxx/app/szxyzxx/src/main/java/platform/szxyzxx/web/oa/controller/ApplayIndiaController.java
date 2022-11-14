package platform.szxyzxx.web.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.model.ApplayIndia;
import platform.education.oa.model.ApplayIndiaDepartmentCount;
import platform.education.oa.model.ApplayLeave;
import platform.education.oa.utils.WebUtil;
import platform.education.oa.vo.ApplayIndiaCondition;
import platform.education.oa.vo.ApplayIndiaDepartmentCountCondition;
import platform.education.oa.vo.ApplayIndiaVo;
import platform.service.storage.model.EntityFile;
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
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.web.oa.utils.CommonUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

import javax.annotation.Resource;

/**
 * 文印申请的主页面
 * 
 * @author huangyanchun
 *
 */

@Controller
@RequestMapping("/office/applayIndia")
public class ApplayIndiaController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(ApplayIndiaController.class);
	private final static String viewBasePath = "/oa/applayIndia";

	@Resource
	private BasicSQLService basicSQLService;
	@Autowired
	@Qualifier("asyncWechatNoticeService")
	private SystemWechatNotifyService notifyService;

	@Autowired
	private OaApplyNoticeService oaApplyNoticeService;

	
	/*
	 * 文印处理状态
	 */
	/**
	 * 待处理
	 */
	public static final String daichuli = "0";
	/**
	 * 未处理
	 */
	public static final String weichuli = "1";
	/**
	 * 处理中
	 */
	public static final String chulizhong = "2";
	/**
	 * 已处理
	 */
	public static final String yichuli = "3";
	
	
	

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ApplayIndiaCondition condition,
			@ModelAttribute("page") Page page,
			// auditType :用于区别几个展示 1、我的申请文印(own) 2、部门文印(department)
			// 3、全部文印(all) 4、待处理(wait) 5、处理中(pending)
			// 6、未处理(undisposed) 7、已处理(deal)
			@RequestParam(value = "auditType", required = false) String auditType,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "menu", required = false) String menu,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		condition.setIsDeleted(false);


		ApplayIndiaCondition condition1 = new ApplayIndiaCondition();
		condition1.setOwnerId(user.getSchoolId());
		condition1.setOwnerType(user.getGroupType());
		condition1.setIsDeleted(false);
		// 全部文印
		if (auditType != null && auditType.equals("all")) {
			// 将所有条件设置
			condition1.setApproverId(user.getId());
		}

		long all = this.applayIndiaService.count(condition1);

		// 设置每页显示两条记录
		page.setPageSize(3);
		// 我的申请文印
		if (auditType != null && auditType.equals("own")) {
			condition.setProposerId(user.getId());
		}

		// 部门文印
		if (auditType != null && auditType.equals("department")) {
			// 将部门条件设置，当前用户没有部门的设置教师ID为-1
			if (user.getTeacherId() != null && !"".equals(user.getTeacherId())) {
				condition.setTeacherId(user.getTeacherId()==null?-1:user.getTeacherId());
			}
		}

		// 全部文印
		if (auditType != null && auditType.equals("all")) {
			// 将所有条件设置
			condition.setApproverId(user.getId());
		}

		// 待处理文印
		if (auditType != null && auditType.equals("wait")) {
			// 将待处理条件设置
			condition.setIndiaStatus(daichuli);
			condition.setApproverId(user.getId());
		}

		// 未处理文印
		if (auditType != null && auditType.equals("undisposed")) {
			// 将未处理条件设置
			condition.setIndiaStatus(weichuli);
			condition.setApproverId(user.getId());
		}

		// 处理中文印
		if (auditType != null && auditType.equals("pending")) {
			// 将处理中条件设置
			condition.setIndiaStatus(chulizhong);
			condition.setApproverId(user.getId());
		}

		// 已处理文印
		if (auditType != null && auditType.equals("deal")) {
			// 将已处理条件设置
			condition.setIndiaStatus(yichuli);
			condition.setApproverId(user.getId());
		}

		if (departmentId != null && !"".equals(departmentId)) {
			// 将详细的部门条件设置
			condition.setDepartmentId(Integer.parseInt(departmentId));
		}

		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		//获取文印申请单
		List<ApplayIndiaVo> items = this.applayIndiaService
				.findApplayIndiaByCondition1(condition, page, order);

		// 个人部门信息
		DepartmentTeacherCondition depCondition = new DepartmentTeacherCondition();
		depCondition.setTeacherId(user.getTeacherId() == null ? -1 : user
				.getTeacherId());
		depCondition.setIsDeleted(false);
		depCondition.setSchoolId(user.getSchoolId());
		List<DepartmentTeacher> ownDep = departmentTeacherService
				.findDepartmentTeacherByCondition(depCondition, null, null);

		// 所有部門信息
		List<Department> dList = CommonUtil.findDepartment(departmentService,user);

		ApplayIndiaDepartmentCountCondition icon = new ApplayIndiaDepartmentCountCondition();
		// 文字搜索
		icon.setSearchWord(condition.getSearchWord());
		if (auditType != null && auditType.equals("wait")) {
			// 将待处理条件设置
			icon.setIndiaStatus(daichuli);

		}

		// 未处理文印
		if (auditType != null && auditType.equals("undisposed")) {
			// 将未处理条件设置
			icon.setIndiaStatus(weichuli);
		}

		// 处理中文印
		if (auditType != null && auditType.equals("pending")) {
			// 将处理中条件设置
			icon.setIndiaStatus(chulizhong);
		}

		// 已处理文印
		if (auditType != null && auditType.equals("deal")) {
			// 将已处理条件设置
			icon.setIndiaStatus(yichuli);
		}

		ApplayIndiaCondition aiCondition = new ApplayIndiaCondition();
		aiCondition.setOwnerId(user.getSchoolId());
		aiCondition.setOwnerType(user.getGroupType());
		aiCondition.setIsDeleted(false);
		aiCondition.setSearchWord(condition.getSearchWord());
		long totalCount = 0;
		if (auditType != null && auditType.equals("wait")) {
			// 将待处理条件设置
			aiCondition.setIndiaStatus(daichuli);
			aiCondition.setApproverId(user.getId());
			totalCount = this.applayIndiaService.count(aiCondition);
		}

		// 未处理文印
		if (auditType != null && auditType.equals("undisposed")) {
			// 将未处理条件设置
			aiCondition.setIndiaStatus(weichuli);
			aiCondition.setApproverId(user.getId());
			totalCount = this.applayIndiaService.count(aiCondition);
		}

		// 处理中文印
		if (auditType != null && auditType.equals("pending")) {
			// 将处理中条件设置
			aiCondition.setIndiaStatus(chulizhong);
			aiCondition.setApproverId(user.getId());
			totalCount = this.applayIndiaService.count(aiCondition);
		}

		// 已处理文印
		if (auditType != null && auditType.equals("deal")) {
			// 将已处理条件设置
			aiCondition.setIndiaStatus(yichuli);
			aiCondition.setApproverId(user.getId());
			totalCount = this.applayIndiaService.count(aiCondition);
		}

		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				ApplayIndiaVo applayIndiavo = this.applayIndiaService
						.findApplayIndiaById1(items.get(i).getId());
				/*如果发布文印者所属部门不为空，则把部门名称设置进去，如果为空，则设置为空字符串*/
				if (applayIndiavo.getDepartmentId() == null || "".equals(applayIndiavo.getDepartmentId())) {
					items.get(i).setDepartmentId(null);
					items.get(i).setDepartmentName("");

				} else {
					//根据部门id查找对应的部门名称
					Department d = this.departmentService
							.findDepartmentById(items.get(i).getDepartmentId());
					if(d!=null){
						items.get(i).setDepartmentName(d.getName());
					}else{
						items.get(i).setDepartmentName("");
					}
				}

				if (applayIndiavo.getUploadId()==null || applayIndiavo.getUploadId().equals("")) {
					items.get(i).setRealFileName("");
				} else {
					EntityFile entity = this.entityFileService
							.findFileByUUID(items.get(i).getUploadId());
					items.get(i).setRealFileName(entity.getFileName());
				}
				
				
				//文字样式显示
				if(applayIndiavo.getRemark()!=null || !"".equals(applayIndiavo.getRemark())){
					items.get(i).setRemark(WebUtil.HtmltoText(applayIndiavo.getRemark()));
				}

			}

		}

		List<ApplayIndiaDepartmentCount> numList = null;
		// 计算我的申请中的部门和全部的每个部门的
		List<ApplayIndiaDepartmentCount> numSqList = null;

		try {

			if (menu == null || "".equals(menu)) {
				ApplayIndiaDepartmentCountCondition sqCondition = new ApplayIndiaDepartmentCountCondition();
				sqCondition.setOwnerId(user.getSchoolId());
				sqCondition.setOwnerType(user.getGroupType());
				sqCondition.setIsDeleted(false);
				numSqList = this.applayIndiaDepartmentCountService
						.findApplayIndiaSqnumByCondition(sqCondition);
			} else {
				// 计算文印全部的每个部门的人数
				numList = this.applayIndiaDepartmentCountService
						.findApplayIndiaDepartmentCountByCondition(icon);
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

	/**
	 * 我申请的文印申请页面
	 * 
	 * @param applayIndia
	 * @param user
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

	/**
	 * 发布 我申请的文印
	 * 
	 * @param applayIndia
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ApplayIndia applayIndia,
			@CurrentUser UserInfo user,String approverTeacherId

	) {

		if(approverTeacherId != null){
			Teacher teacher = teacherService.findTeacherById(Integer.parseInt(approverTeacherId.split(",")[0]));
			applayIndia.setApproverId(teacher.getUserId());
		}


		applayIndia.setTitle(user.getRealName()+"的用章");
		applayIndia.setOwnerId(user.getSchoolId());
		applayIndia.setOwnerType(user.getGroupType());
		applayIndia.setIndiaStatus(daichuli);
		applayIndia.setProposerId(user.getId());
		applayIndia.setProposerName(user.getRealName());
		applayIndia.setIsDeleted(false);
		applayIndia.setPublishDate(new Date());
		applayIndia.setMobile(user.getTelephone());
		if (applayIndia.getDepartmentId() == null) {
			applayIndia.setDepartmentId(null);
		} else {
			applayIndia.setDepartmentId(applayIndia.getDepartmentId());
		}

		/*如果申请人有所属部门，则在oa_applayIndia_department_count查找，
		 * 如果根据该部门id和处理状态在该表中找到相应的数据，则在该条数据的基础上在count字段中+1
		 * 否则重新根据条件进行新增一条数据
		 */ 
//		if (applayIndia.getDepartmentId() != null
//				&& !"".equals(applayIndia.getDepartmentId())) {
//			ApplayIndiaDepartmentCountCondition dcondition = new ApplayIndiaDepartmentCountCondition();
//			dcondition.setDepartmentId(applayIndia.getDepartmentId());
//			dcondition.setOwnerId(user.getSchoolId());
//			dcondition.setOwnerType(user.getGroupType());
//			dcondition.setIsDeleted(false);
//			dcondition.setIndiaStatus(daichuli);
//			List<ApplayIndiaDepartmentCount> dList = this.applayIndiaDepartmentCountService
//					.findApplayIndiaDepartmentCountByCondition(dcondition);
//			/*判断该条数据是否存在,如果存在，则在该数据的基础上对count字段进行+1操作，
//			 * 否则对oa_applayindia_department_count表进行新增操作
//			 */
//			if (dList.size() > 0) {
//				//对oa_applayindia_department_count表进行+1操作
//				IncreatIndiaDepartmentCount(dList);
//			} else {
//				//对oa_applayindia_department_count表进行新增操作
//				AddIndiaDepartmentCount(applayIndia,null,user);
//			}
//		}

		//sendApplyIndiaMesToManager(ContansOfOa.oaMenuOfApproveIndia, user.getGroupId(), user);

		applayIndia = this.applayIndiaService.add(applayIndia);

		//发送待审批通知
		sendNotApprovedSystemAndWechatNotice(applayIndia.getId(),applayIndia.getApproverId(),user.getRealName(),applayIndia.getRemark());

		return applayIndia != null ? new ResponseInfomation(
				applayIndia.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}


	//发送待审批通知
	private void sendNotApprovedSystemAndWechatNotice(Integer id, Integer userId,String userName,String explain){
		try {
			List notifyUserList=basicSQLService.find("select id user_id,open_id from yh_user where id="+userId);
			if(notifyUserList!=null && notifyUserList.size()>0) {
				SystemWechatNotice swNotice = new SystemWechatNotice();
				swNotice.setTitle("用章审批");
				swNotice.setContent("你有一条"+userName+"的用章待审批");
				swNotice.setDataId(id.toString());
				swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
				swNotice.setDataIdType(SystemNoticeDataIdType.YZ);
				swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("用章审批", "你有一条"+userName+"的用章待审批"));
				notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

				//发送代办事项
				OaApplyNotice oaApplyNotice=new OaApplyNotice();
				oaApplyNotice.setTitle(userName+"的用章申请");
				oaApplyNotice.setReceiverId(userId);
				oaApplyNotice.setApplicantName(userName);
				oaApplyNotice.setApplyExplain(explain);
				oaApplyNotice.setApplyResult(0);
				oaApplyNotice.setDataIdType(SystemNoticeDataIdType.YZ);
				oaApplyNotice.setDataId(id);
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
				swNotice.setTitle("用章审批结果");
				swNotice.setContent("审批结果："+result);
				swNotice.setDataId(id.toString());
				swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
				swNotice.setDataIdType(SystemNoticeDataIdType.YZ);
				swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("用章审批结果通知", "用章申请"+result));
				notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");

				//更新代办
				oaApplyNoticeService.updateApplyResult(handlerUserId,id,result.equals("【同意】")? 1 : 2);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 我申请的文印编辑页面
	 * 
	 * @param id
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, @CurrentUser UserInfo user) {
		ApplayIndia applayIndia = this.applayIndiaService
				.findApplayIndiaById(id);
		// 根据teacherId查找对应的departmentId
		List<DepartmentTeacher> departmentList = CommonUtil.findTeacherOfTeacherIdMore(departmentTeacherService,user);
		model.addAttribute("departmentList", departmentList);

		/*
		 * 如果申请人有所属部门，则把申请人的所属部门信息发送到页面
		 */
		if (applayIndia.getDepartmentId() != null) {
			// 根据departmentId查找该用户所属部门
			Department d = this.departmentService
					.findDepartmentById(applayIndia.getDepartmentId());
			model.addAttribute("d", d);

		}
		if (applayIndia.getUploadId() != null) {
			EntityFile entity = this.entityFileService
					.findFileByUUID(applayIndia.getUploadId());
			model.addAttribute("entity", entity);
		}
		model.addAttribute("teacherId", user.getTeacherId());
		model.addAttribute("applayIndia", applayIndia);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 保存我申请的文印的编辑
	 * 
	 * @param id
	 * @param applayIndia
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			ApplayIndia applayIndia) {
		applayIndia.setId(id);
		applayIndia.setPublishDate(new Date());
		applayIndia = this.applayIndiaService.modify(applayIndia);
		return applayIndia != null ? new ResponseInfomation(
				applayIndia.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ApplayIndia applayIndia = this.applayIndiaService
				.findApplayIndiaById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("applayIndia", applayIndia);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 删除文印信息
	 * 
	 * @param id
	 * @param applayIndia
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			ApplayIndia applayIndia) {
		if (applayIndia != null) {
			applayIndia.setId(id);
		}
		try {
			ApplayIndia aindia = this.applayIndiaService
					.findApplayIndiaById(id);
			/*
			 *判断当前要删除的文印的申请人是否有所属部门，如果有，则在oa_applayIndia_department_count表中进行查找
			 *如果根据条件查找到的数据的count字段不为0，则进行-1操作，否则不操作
			 */
			if (aindia.getDepartmentId() != null
					&& !"".equals(aindia.getDepartmentId())) {
				
				ApplayIndiaDepartmentCountCondition dcondition = new ApplayIndiaDepartmentCountCondition();
				dcondition.setDepartmentId(aindia.getDepartmentId());
				dcondition.setOwnerId(aindia.getOwnerId());
				dcondition.setOwnerType(aindia.getOwnerType());
				dcondition.setIsDeleted(false);
				dcondition.setIndiaStatus(aindia.getIndiaStatus());
				List<ApplayIndiaDepartmentCount> dList = this.applayIndiaDepartmentCountService
						.findApplayIndiaDepartmentCountByCondition(dcondition);
				
				//对oa_applayIndia_department_count表进行-1修改操作
				AlterIndiaDepartmentCount(dList);
				
			}

			this.applayIndiaService.abandon(applayIndia);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/monthChange")
	@ResponseBody
	public List<ApplayIndiaVo> monthChange(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") ApplayIndiaCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "curenMonth", required = false) String curenMonth,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		condition.setIsDeleted(false);
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		condition.setIsDeleted(false);

		String month[] = curenMonth.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 小写的mm表示的是分钟
		Date lastDate = null;
		Date firstDate = null;
		try {
			firstDate = sdf.parse(month[0]);
			lastDate = sdf.parse(month[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		condition.setBeginDate(firstDate);
		condition.setLastDate(lastDate);
		List<ApplayIndiaVo> list = this.applayIndiaService
				.findApplayIndiaByCondition1(condition, null, null);

		// 计算文印申请总数
		String total = this.applayIndiaService.count(condition).toString();

		// 计算文印待处理数
		condition.setIndiaStatus(daichuli);
		String daichuli = this.applayIndiaService.count(condition).toString();

		// 计算文印处理中数
		condition.setIndiaStatus(chulizhong);
		String chulizhong = this.applayIndiaService.count(condition).toString();

		// 计算文印已处理数
		condition.setIndiaStatus(yichuli);
		;
		String yichuli = this.applayIndiaService.count(condition).toString();

		// 计算文印未处理数
		condition.setIndiaStatus(weichuli);
		String weichuli = this.applayIndiaService.count(condition).toString();

		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				list.get(i).setTotalCount(total);
				list.get(i).setDaichuliCount(daichuli);
				list.get(i).setChulizhongCount(chulizhong);
				list.get(i).setYichuliCount(yichuli);
				list.get(i).setWeichuliCount(weichuli);
				if (list.get(i).getDepartmentId() != null) {
					Department dep = this.departmentService
							.findDepartmentById(list.get(i).getDepartmentId());
					if(dep!=null){
						
						list.get(i).setDepartmentName(dep.getName());
					}else{
						list.get(i).setDepartmentName("无");
					}
				} else {
					list.get(i).setDepartmentName("无");
				}

			}

		}

		return list;
	}

	// 点击按钮，跳出相应的页面
	@RequestMapping(value = "/chuli")
	public ModelAndView chuli(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") ApplayIndiaCondition condition,
			// type :用于区别几个展示 1、未处理(undisposed)
			// 2、处理中(pending) 3、已处理(deal)
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "id", required = false) String id, Model model) {
		ApplayIndia applayIndia = this.applayIndiaService
				.findApplayIndiaById(Integer.parseInt(id));
		model.addAttribute("publishDate", applayIndia.getPublishDate());
		model.addAttribute("id", id);
		model.addAttribute("type", type);
		return new ModelAndView(structurePath("/chuli"));
	}

	//点击提交按钮，提交处理结果
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation submit(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "nontreat", required = false) String nontreat,
			@RequestParam(value = "yuji", required = false) String yuji,
			@CurrentUser UserInfo user) {
		ApplayIndia applayIndia = this.applayIndiaService
				.findApplayIndiaById(Integer.parseInt(id));

		//ApplayIndiaDepartmentCountCondition condition = new ApplayIndiaDepartmentCountCondition();
		//condition.setOwnerId(applayIndia.getOwnerId());
		applayIndia.setOwnerType(applayIndia.getOwnerType());
//		condition.setIndiaStatus(applayIndia.getIndiaStatus());
//		condition.setIsDeleted(false);
//		if (applayIndia.getDepartmentId() != null
//				&& !"".equals(applayIndia.getDepartmentId())) {
//			//condition.setDepartmentId(applayIndia.getDepartmentId());
//		}

//		List<ApplayIndiaDepartmentCount> adList = this.applayIndiaDepartmentCountService
//				.findApplayIndiaDepartmentCountByCondition(condition);

		/*
		 *提交未处理的结果，把未处理事由插入数据表，对applay_india表的处理状态进行修改 
		 */

		String result="【同意】";

		if (type.equals("undisposed")) {
			applayIndia.setNonTreatmentCause(nontreat);
			applayIndia.setIndiaStatus(weichuli);
			//condition.setIndiaStatus(weichuli);
			result="【驳回】";
			/*
			 * 提交处理中的结果，把预计完成时间插入数据表，对applay_india表的处理转态进行修改
			 */
			
		} else if (type.equals("pending")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date exceptionTime = null;
			try {
				exceptionTime = sdf.parse(yuji);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			applayIndia.setExpectedCompletionTime(exceptionTime);
			applayIndia.setIndiaStatus(chulizhong);
			//condition.setIndiaStatus(chulizhong);
			result="【处理中】";
			/*
			 * 提交已处理的结果，对applay_india表中的处理状态进行修改
			 */
		} else if (type.equals("deal")) {
			applayIndia.setTreatDate(new Date());
			applayIndia.setIndiaStatus(yichuli);
			//condition.setIndiaStatus(yichuli);
		}
		applayIndia = this.applayIndiaService.modify(applayIndia);
		
		
//		List<ApplayIndiaDepartmentCount> dList = this.applayIndiaDepartmentCountService
//				.findApplayIndiaDepartmentCountByCondition(condition);
//		/*
//		 * 如果发布文印者有所属部门，则对oa_applayindia_department_count表进行相关的操作
//		 * 否则不对该表进行操作
//		 */
//		if (condition.getDepartmentId() != null
//				&& !"".equals(condition.getDepartmentId())) {
//			if (adList.size() > 0) {
//				//对oa_applayIndia_department_count表进行-1修改操作
//				AlterIndiaDepartmentCount(adList);
//			}
//			if (dList.size() > 0) {
//				//对oa_applayIndia_department_count表进行+1修改操作
//				IncreatIndiaDepartmentCount(dList);
//			} else {
//				//对oa_applayIndia_department_count表进行新增操作
//				AddIndiaDepartmentCount(applayIndia,type,user);
//			}
//		}
//
//		sendApplyIndiaMesToUser(user,applayIndia.getProposerId());

		sendApprovedResultSystemAndWechatNotice(applayIndia.getId(),result,applayIndia.getProposerId(),applayIndia.getApproverId());

		return applayIndia != null ? new ResponseInfomation(
				applayIndia.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ApplayIndia> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") ApplayIndiaCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.applayIndiaService.findApplayIndiaByCondition(condition,
				page, order);
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, ApplayIndiaCondition condition) {
		Integer ownerId = condition.getOwnerId();
		String ownerType = condition.getOwnerType();
		if (user != null && ownerId == null) {
			condition.setOwnerId(ownerId);
		}
		if (user != null && ownerType == null) {
			condition.setOwnerType(ownerType);
		}

	}
	
	
	/*
	 * 对oa_applayIndia_department_count表count字段进行-1修改
	 */
	public void AlterIndiaDepartmentCount(List<ApplayIndiaDepartmentCount>aidList){
		ApplayIndiaDepartmentCount dcount = new ApplayIndiaDepartmentCount();
		if (aidList.size() > 0) {
			int num = aidList.get(0).getNumber() - 1 >= 0 ? aidList.get(0)
					.getNumber() - 1 : 0;
			dcount.setId(aidList.get(0).getId());
			dcount.setNumber(num);
			this.applayIndiaDepartmentCountService.modify(dcount);
		}
	}
	
	
	/*
	 *对oa_applayIndia_department_count表count字段进行+1修改 
	 */
	public void IncreatIndiaDepartmentCount(List<ApplayIndiaDepartmentCount>dList){
		ApplayIndiaDepartmentCount dcount = new ApplayIndiaDepartmentCount();
		int num1 = dList.get(0).getNumber() + 1;
		dcount.setId(dList.get(0).getId());
		dcount.setNumber(num1);
		this.applayIndiaDepartmentCountService.modify(dcount);
	}
	
	
	/*
	 *对oa_applayIndia_department_count表count字段进行新增操作  
	 */
	public void AddIndiaDepartmentCount(ApplayIndia applayIndia,String type,@CurrentUser UserInfo user){
		ApplayIndiaDepartmentCount adcount = new ApplayIndiaDepartmentCount();
		
		/*
		 * 如果type类型 为null 或空，则为对该oa_applayIndia进行新增操作，从而从表进行相应的新增操作
		 * 否则是对该文印进行处理操作，从而对从表进行相应的新增操作
		 */
		if(type==null || type.equals("")){
			//对oa_applayindia_department_count进行待处理状态的新增操作
			adcount.setDepartmentId(applayIndia.getDepartmentId());
			adcount.setOwnerId(user.getSchoolId());
			adcount.setOwnerType(user.getGroupType());
			adcount.setNumber(1);
			adcount.setIndiaStatus(daichuli);
		}else{
			//对oa_applayindia_department_count进行处理状态的新增操作
			adcount.setDepartmentId(applayIndia.getDepartmentId());
			adcount.setOwnerId(applayIndia.getOwnerId());
			adcount.setOwnerType(applayIndia.getOwnerType());
			adcount.setNumber(1);
			adcount.setIsDeleted(false);
				//未处理状态
			if (type.equals("undisposed")) {
				adcount.setIndiaStatus(weichuli);
				//处理中状态
			} else if (type.equals("pending")) {
				adcount.setIndiaStatus(chulizhong);
				//已处理状态
			} else if (type.equals("deal")) {
				adcount.setIndiaStatus(yichuli);
			} 
		}

		this.applayIndiaDepartmentCountService.add(adcount);
	}
	
	/**
	 * 2015-10-16
	 * 将申请文印的信息发送给相对应的管理员
	 */
	public void sendApplyIndiaMesToManager(String persmissionCode, Integer groupId,UserInfo user){
		List<Integer> userIdList = userService.findUserIdByPermissionCode(persmissionCode,groupId);
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的文印申请待处理！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_WENYINCHULI);
		PushMessageUtil.sendMessage(message, userIdList);
		PushMessageUtil.pushMessageList(userIdList);
	}
	
	
	/**
	 * 2015-10-16
	 * 将文印处理的信息发送给相对应的申请人
	 */
	public void sendApplyIndiaMesToUser(UserInfo user,Integer applyUserId){
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的文印申请处理结果通知！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_WENYINSHENQING);
		PushMessageUtil.sendMessage(message, applyUserId);
		PushMessageUtil.pushMessage(applyUserId);
	}
	
	
}
