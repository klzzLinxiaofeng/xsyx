package platform.szxyzxx.web.oa.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

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
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.model.Paper;
import platform.education.oa.model.PaperDepartmentCount;
import platform.education.oa.model.PaperUser;
import platform.education.oa.model.PaperUserRead;
import platform.education.oa.service.PaperUserReadService;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.PaperCondition;
import platform.education.oa.vo.PaperDepartmentCountCondition;
import platform.education.oa.vo.PaperUserCondition;
import platform.education.oa.vo.PaperUserReadCondition;
import platform.education.oa.vo.PaperUserReadVo;
import platform.education.oa.vo.PaperVo;
import platform.education.user.model.UserRole;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.utils.CommonUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.notice.pojo.SystemWechatNotice;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;

/**
 * ???????????????
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/office/paper")
public class PaperController extends BaseController {

	// ?????????????????????
	private final static Integer quanyuan = 0;
	// ?????????????????????
	private final static Integer bumen = 1;
	// ?????????????????????
	private final static Integer geren = 2;
	@Resource
	PaperUserReadService paperUserReadService;
	@Resource
	private BasicSQLService basicSQLService;
	@Autowired
	@Qualifier("asyncWechatNoticeService")
	private SystemWechatNotifyService notifyService;

	String[] departments = null;
	String[] teachers = null;
	String[] names = null;

	private final static String BASE_PATH = "oa/paper/";


	/**
	 * ?????????????????? ????????? 2015-08-20
	 */
	// ??????????????????
	@RequestMapping(value = "/index1")
	public ModelAndView index1(
			@CurrentUser UserInfo user,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@ModelAttribute("condition") PaperCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		page.setPageSize(3);
		if ("list1".equals(sub)) {
			viewPath = structurePath("/list1");
		} else {
			viewPath = structurePath("/index1");
		}
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		if (departmentId == null || "".equals(departmentId)) {
			departmentId = null;
		}
		condition.setOwnerId(user.getSchoolId());
		condition.setOwnerType(user.getGroupType());
		condition.setReceiverId(user.getId());
		condition.setTeacherId(user.getTeacherId() == null ? -1 : user
				.getTeacherId());
		condition.setPosterId(user.getId());
		if (departmentId != null && !"".equals(departmentId)) {
			condition.setDepartmentId(Integer.parseInt(departmentId));
		}
		condition.setIsDeleted(false);

		if (type != null && type.equals("related")) {
			condition.setReceiverType(0);
			condition.setIsRelatedWithMe(true);
		}

		if (type != null && type.equals("all")) {
			condition.setIsAll(true);

		}
		if (type != null && type.equals("department")) {
			condition.setIsDepartmentRecord(true);
		}

		if (type != null && type.equals("own")) {
			condition.setIsMyPublish(true);
		}

		List<PaperVo> items = this.paperService.findPaperByRelated(condition,
				page, order);
		//????????????for??????count????????????????????????bug???select?????????????????????????????????????????????r.id is not null or paper.poster_id =#{args[0].posterId} as is_publish_or_receiver,(select count(*) from oa_paper_user_read re where re.paper_id=paper.id and re.`read_status`=1) as read_number
		for (PaperVo item : items) {
			item.setReadNumber((int)basicSQLService.findUniqueLong("select count(*) from oa_paper_user_read where paper_id="+item.getId()+" and read_status=1"));
			item.setIsPublishOrReceiver(item.getPosterId().equals(user.getId()) || item.getReadStatus()!=null);
		}

		List<Department> dList = CommonUtil.findDepartment(departmentService,user);
		
		PaperDepartmentCountCondition pdcCondition = new PaperDepartmentCountCondition();
		pdcCondition.setIsDeleted(false);
		pdcCondition.setOwnerId(user.getSchoolId());
		pdcCondition.setOwnerType(user.getGroupType());
		List<PaperDepartmentCount> pdcList = this.paperDepartmentCountService
				.findPaperDepartmentCountByCondition(pdcCondition);

		// ???????????????????????? ???????????????
//		List<DepartmentTeacher> tdep = CommonUtil.findTeacherOfTeacherId(departmentTeacherService,user);
//		if (tdep.size() <= 0) {
//			model.addAttribute("noDepartment", "noDepartment");
//		}
		model.addAttribute("currentUser", user.getId());
		model.addAttribute("sub", sub);
		model.addAttribute("dList", dList);
		model.addAttribute("pdcList", pdcList);
		model.addAttribute("currentLogin", user.getTeacherId());
		model.addAttribute("items", items);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("type", type);
		model.addAttribute("totalCount", page.getTotalRows());
		return new ModelAndView(viewPath, model.asMap());
	}

	// ??????????????????
	@RequestMapping(value = "/creator1", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		// ??????teacherId???????????????departmentId
		List<DepartmentTeacher> departmentList = CommonUtil.findTeacherOfTeacherIdMore(departmentTeacherService,user);
		
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("isSchoolOperator", isSchoolOperator(user));
		model.addAttribute("teacherId", user.getTeacherId());
		return new ModelAndView(structurePath("/input1"));
	}

	/**
	 * ??????????????????????????????????????????
	 * @param user
	 * @return
	 */
	public boolean isSchoolOperator(UserInfo user){
		List<UserRole> userRoles = userRoleService.findByUserId(user.getId());

		for (UserRole userRole : userRoles) {
			if(userRole.getRole().getCode().equals("SCHOOL_OPERATOR")){
				return true;
			}
		}
		return false;
	}

	// ??????????????????
	@RequestMapping(value = "/viewer1", method = RequestMethod.GET)
	public ModelAndView viewer1(Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user) {
		AlterPaperUserRead(id,user);
		// ??????????????????????????????????????????????????? 1????????????????????????????????????????????????????????????????????????
		PaperVo papervo = this.paperService.findPaperById1(id);
		if (papervo.getAttachmentUuid()==null || papervo.getAttachmentUuid().equals("")) {
			papervo.setRealFileName("");
		} else {
			EntityFile entity = this.entityFileService.findFileByUUID(papervo
					.getAttachmentUuid());
			papervo.setRealFileName(entity.getFileName());
		}

		// ???????????????
		PaperUserReadCondition puCondition = new PaperUserReadCondition();
		puCondition.setOwnerId(user.getSchoolId());
		puCondition.setOwnerType(user.getGroupType());
		puCondition.setPaperId(papervo.getId());
		puCondition.setReadStatus(true);
		//???????????????????????????
		List<PaperUserReadVo> puList = this.paperUserReadService
				.findPaperUserReadByConditionVo(puCondition, null, null);

		//???????????????
		PaperUserReadCondition puCondition1 = new PaperUserReadCondition();
		puCondition1.setOwnerId(user.getSchoolId());
		puCondition1.setOwnerType(user.getGroupType());
		puCondition1.setPaperId(papervo.getId());
		puCondition1.setReadStatus(false);
		//???????????????????????????
		List<PaperUserReadVo> puLists = this.paperUserReadService
				.findPaperUserReadByConditionVo(puCondition1, null, null);
		//??????????????????????????????
		page.setPageSize(30);
		List<PaperUserReadVo> puLists1 = this.paperUserReadService
				.findPaperUserReadByConditionVo(puCondition1, page, order);
		if(puLists1!=null){
			for(int i=0;i<puLists1.size();i++){
				Teacher t = this.teacherService.findOfUser(user.getSchoolId(), puLists1.get(i).getUserId());
				 if(t!=null){
					 puLists1.get(i).setRealName(t.getName());
					 puLists1.get(i).setTelphone(t.getMobile());
				 }
			}
		}
		
		
		model.addAttribute("paperId", papervo.getId());
		model.addAttribute("type", "weiyuedu");
		model.addAttribute("puLists1", puLists1);
		model.addAttribute("puLists", puLists);
		model.addAttribute("puList", puList);
		model.addAttribute("papervo", papervo);
		return new ModelAndView(structurePath("/viewer1"));
	}

	// ?????????????????????
	@RequestMapping(value = "/creator1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			Paper paper,
			@RequestParam(value = "departmentIds", required = false) String departmentIds,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@RequestParam(value = "teacherNames", required = false) String teacherNames,
			@CurrentUser UserInfo user) {
		try {
			departments = departmentIds.split(",");
			teachers = teacherIds.split(",");
			names = teacherNames.split(",");

			paper.setAppId(SysContants.SYSTEM_APP_ID);
			paper.setUuid(UUIDUtils.getUUID());
			paper.setOwnerId(user.getSchoolId());
			paper.setOwnerType(user.getGroupType());
			paper.setPosterId(user.getId());
			paper.setPosterName(user.getRealName());
			paper.setReadCount(0);
			if (paper.getPublishDate() == null
					|| paper.getPublishDate().equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				paper.setPublishDate(sdf.format(new Date()));
			}
			//??????????????????????????????????????????????????????
			AddByType(paper,user);
			// publishStatus ????????????????????? 0??????????????? 1??????????????? 2????????????????????????
			Integer receiverType = paper.getReceiverType();
			paper = this.paperService.add(paper);
			Integer paperId = paper.getId();
			// ?????????????????????????????????
			savePaperUser(paperId, departments, teachers, names, receiverType,user);
			//???????????????????????????
			sendSystemAndWechatNotice(paper);
			return paper != null ? new ResponseInfomation(paper.getId(),ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation();
		}
	}


	private void sendSystemAndWechatNotice(Paper paper){
		List notifyUserList=basicSQLService.find("select u.id user_id,u.open_id from oa_applay_leave_approve_user a left join yh_user u on u.id=a.approve_id where a.leave_id="+paper.getId());
		if(notifyUserList!=null && notifyUserList.size()>0) {
			SystemWechatNotice swNotice = new SystemWechatNotice();
			swNotice.setTitle(paper.getTitle());
			swNotice.setContent(paper.getContent());
			swNotice.setDataId(paper.getId().toString());
			swNotice.setSystemNoticeType(SystemNoticeType.APPROVAL);
			swNotice.setDataIdType(SystemNoticeDataIdType.GW);
			swNotice.setWechatMessageTemplate(new ApprovalWechatMessageTemplate("????????????", paper.getTitle()));
			notifyService.sendSystemAndWechatNotice(swNotice, notifyUserList, "user_id", "open_id");
		}
	}


	// ??????????????????
	@RequestMapping(value = "/editor1", method = RequestMethod.GET)
	public ModelAndView editor1(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, @CurrentUser UserInfo user) {
		Paper paper = this.paperService.findPaperById(id);
		if (paper.getReceiverType().equals(bumen)) {
			PaperUserCondition paperUserCondition = new PaperUserCondition();
			paperUserCondition.setIsDeleted(false);
			paperUserCondition.setPaperId(paper.getId());
			List<PaperUser> paperUser = this.paperUserService
					.findPaperUserByCondition(paperUserCondition);
			String departmentId = "";
			String departmentName = "";
			for (int i = 0; i < paperUser.size(); i++) {
				Department department = this.departmentService
						.findDepartmentById(paperUser.get(i).getDepartmentId());
				departmentId += department.getId() + ",";
				departmentName += department.getName() + ",";
			}
			model.addAttribute("depId",
					departmentId.substring(0, departmentId.length() - 1));
			model.addAttribute("depName",
					departmentName.substring(0, departmentName.length() - 1));
		} else if (paper.getReceiverType().equals(geren)) {

			List<Map<String,Object>> teacherInfoList=basicSQLService.find("select t.id,t.`name` from oa_paper_user_read r inner join pj_teacher t on t.user_id=r.user_id where r.paper_id="+id);

			StringBuilder tNames=new StringBuilder();
			StringBuilder tIds = new StringBuilder();
			if (teacherInfoList.size() > 0) {
				for(int i=0;i<teacherInfoList.size();i++){
					Map<String,Object> teacher=teacherInfoList.get(i);
					tNames.append(teacher.get("name"));
					tIds.append(teacher.get("id"));
					if(i!=teacherInfoList.size()-1){
						tNames.append(",");
						tIds.append(",");
					}
				}
				model.addAttribute("tNames",tNames);
				model.addAttribute("tIds", tIds);
			}


		}

		if (paper != null) {
			EntityFile entity = this.entityFileService.findFileByUUID(paper
					.getAttachmentUuid());
			model.addAttribute("entity", entity);
		}
		model.addAttribute("teacherId", user.getTeacherId());
		model.addAttribute("paper", paper);
		return new ModelAndView(structurePath("/input1"), model.asMap());
	}

	// ??????????????????
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation editor1(
			@PathVariable(value = "id") Integer id,
			Paper paper,
			@RequestParam(value = "departmentIds", required = false) String departmentIds,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@RequestParam(value = "teacherNames", required = false) String teacherNames,
			@CurrentUser UserInfo user) {
		try {
			departments = departmentIds.split(",");
			teachers = teacherIds.split(",");
			names = teacherNames.split(",");
			paper.setId(id);
			//??????????????????????????????????????????????????????
			AddByType(paper,user);
			// receiverType ????????????????????? 0:???????????? 1????????? 2?????????????????????
			Integer receiverType = paper.getReceiverType();
			Integer paperId = paper.getId();
			// ?????????????????????????????????????????????????????????
			removePaperUser(paperId, receiverType, user);
			
			// ?????????????????????????????????????????????????????????
			if (departments != null) {
				modifyPaperDepartmentCount(paperId, user, departments);
			}
			paper = this.paperService.modify(paper);
			// ????????????????????????????????????????????????
			savePaperUser(paperId, departments, teachers, names,
					paper.getReceiverType(), user);
			

			return new ResponseInfomation(paper.getId(),
					ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation();
		}
	}

	// ???????????????????????????
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete1(@PathVariable(value = "id") Integer id, Paper paper,
			@CurrentUser UserInfo user) {
		try {
			if (paper != null) {
				paper.setId(id);
			}


			basicSQLService.updateBatch("delete from oa_paper where id="+id,"delete from oa_paper_user_read where paper_id="+id);

			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}

	}

	/*
	 * ????????????/?????????????????????????????????????????????
	 * ?????????oa_paper?????????setReceiverCount??????
	 */
	public void AddByType(Paper paper,@CurrentUser UserInfo user ){
		if (paper.getReceiverType().equals(bumen)) {
			// ???????????????????????????????????????
			List<DepartmentTeacher>dtLists = new ArrayList<DepartmentTeacher>();
			for (int k = 0; k < departments.length; k++) {
				DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
				dtCondition.setSchoolId(user.getSchoolId());
				dtCondition.setIsDeleted(false);
				dtCondition.setDepartmentId(Integer
						.parseInt(departments[k]));
				List<DepartmentTeacher> dtList = this.departmentTeacherService
						.findDepartmentTeacherByCondition1(dtCondition,
								null, null);
				dtLists.addAll(dtList);
			}
			
			//??????teacherId????????????????????????????????????
			dtLists = CommonUtil.distinctDepartmentTeacherOfTeacherId(dtLists);
			paper.setReceiverCount(dtLists.size());
		} else if (paper.getReceiverType().equals(geren)) {
			// ???????????????????????????????????????
			paper.setReceiverCount(teachers.length);
		} else if (paper.getReceiverType().equals(quanyuan)) {
			// ???????????????????????????????????????
			TeacherCondition teacherCondition = new TeacherCondition();
			teacherCondition.setSchoolId(user.getSchoolId());
			teacherCondition.setIsDelete(false);
			List<Teacher> teacherList = this.teacherService
					.findTeacherByCondition(teacherCondition, null, null);
			Integer readNumber = teacherList.size();
			paper.setReceiverCount(readNumber);
		}
	}
	
	
	
	// ?????????????????????????????????
	public void savePaperUser(Integer paperId, String[] departments,
			String[] teachers, String[] names, Integer receiverType,
			@CurrentUser UserInfo user) {
		PaperUser paperUser = null;
		List<Teacher> tList = new ArrayList<Teacher>();
		if (receiverType.equals(bumen)) {
			for (int i = 0; i < departments.length; i++) {
//				paperUser = new PaperUser();
//				paperUser.setOwnerId(user.getSchoolId());
//				paperUser.setOwnerType(user.getGroupType());
//				paperUser.setIsDeleted(false);
//				paperUser.setPaperId(paperId);
//				paperUser.setDepartmentId(Integer.parseInt(departments[i]));
//				paperUser.setReadStatus(false);
//				this.paperUserService.add(paperUser);
				// ????????????????????????????????????
				addPaperDepartmentCount(paperId, user,
						Integer.parseInt(departments[i]));
				// ????????????????????????????????????????????????????????????????????????????????????
				// ???oa_paper_user_read???????????????????????????read_status????????????false?????????0 ???????????????
				// ???????????????id???department_teacher????????????????????????????????????????????????
				List<DepartmentTeacher> dtList = CommonUtil.findTeacherOfdepartmentIdMore(departmentTeacherService,user, Integer.parseInt(departments[i]));
				if (dtList.size() > 0) {
					for (int k = 0; k < dtList.size(); k++) {
						Teacher t = this.teacherService.findTeacherById(dtList
								.get(k).getTeacherId());
						tList.add(t);
					}
					
					//??????teacherId???????????????????????????
					tList = CommonUtil.distinctTeacherOfTeacherId(tList);
				}
			}
		} else if (receiverType.equals(geren)) {
			Teacher teacher1 = null;
			for (int i = 0; i < teachers.length; i++) {
				teacher1 = this.teacherService.findTeacherById(Integer
						.valueOf(teachers[i]));
//				paperUser = new PaperUser();
//				paperUser.setOwnerId(user.getSchoolId());
//				paperUser.setOwnerType(user.getGroupType());
//				paperUser.setIsDeleted(false);
//				paperUser.setPaperId(paperId);
//				paperUser.setReceiverId(teacher1.getUserId());
//				paperUser.setReceiverName(names[i]);
//				paperUser.setReadStatus(false);
//				this.paperUserService.add(paperUser);
				// ????????????????????????????????????????????????????????????
				tList.add(teacher1);

			}
			
		} else if (receiverType.equals(quanyuan)) {
			// ????????????????????????????????????????????????????????????????????????????????????
			// ???oa_paper_user_read???????????????????????????read_status????????????false?????????0 ???????????????
			// ??????????????????department_teacher????????????????????????????????????????????????
			TeacherCondition tCondition = new TeacherCondition();
			tCondition.setSchoolId(user.getSchoolId());
			tCondition.setIsDelete(false);
			tList = this.teacherService.findTeacherByCondition(tCondition,
					null, null);
		}
		
		//???????????????????????????????????????????????????????????????
		if (tList.size() > 0) {
			AddPaperUserRead(tList, user, paperId);
		}

		//??????????????????????????????????????????
		List<Integer> pushTteacher = new ArrayList<Integer>();
		for (Teacher teacherId : tList) {
			pushTteacher.add(teacherId.getUserId());
		}
		
		sendApplyPaperMesToManager(user, pushTteacher);
	}

	// ?????????????????????????????????
	public void removePaperUser(Integer paperId, Integer receiverType,
			@CurrentUser UserInfo user) {
		PaperUserCondition paperUserCondition = new PaperUserCondition();
		paperUserCondition.setPaperId(paperId);
		paperUserCondition.setOwnerId(user.getSchoolId());
		paperUserCondition.setOwnerType(user.getGroupType());
		List<PaperUser> pUser = this.paperUserService
				.findPaperUserByCondition(paperUserCondition);
		for (PaperUser p : pUser) {
			// ?????????????????????????????????????????????
			this.paperUserService.remove(p);

		}
		if (receiverType.equals(bumen)) {
			removePaperDepartmentCount(pUser, paperId, user);
		}

		deletePaperUserRead(paperId,user);

	}

	// ?????????????????????????????????????????????
	public void addPaperDepartmentCount(Integer paperId,
			@CurrentUser UserInfo user, Integer departmentId) {
		PaperDepartmentCountCondition pdcCondition = new PaperDepartmentCountCondition();
		pdcCondition.setOwnerId(user.getSchoolId());
		pdcCondition.setOwnerType(user.getGroupType());
		pdcCondition.setIsDeleted(false);
		pdcCondition.setDepartmentId(departmentId);
		List<PaperDepartmentCount> pdcList = this.paperDepartmentCountService
				.findPaperDepartmentCountByCondition(pdcCondition);
		PaperDepartmentCount pdc = new PaperDepartmentCount();
		if (pdcList.size() > 0) {
			int num = pdcList.get(0).getCount() + 1;
			pdc.setId(pdcList.get(0).getId());
			pdc.setDepartmentId(pdcList.get(0).getDepartmentId());
			pdc.setCount(num);
			this.paperDepartmentCountService.modify(pdc);
		} else {
			pdc.setOwnerId(user.getSchoolId());
			pdc.setOwnerType(user.getGroupType());
			pdc.setCount(1);
			pdc.setDepartmentId(departmentId);
			this.paperDepartmentCountService.add(pdc);
		}
	}

	// ?????????????????????????????? ???????????????
	public void modifyPaperDepartmentCount(Integer paperId,
			@CurrentUser UserInfo user, String[] departments) {
		PaperDepartmentCountCondition pdcCondition = null;
		PaperDepartmentCount pdc = null;
		for (int i = 0; i < departments.length; i++) {
			pdc = new PaperDepartmentCount();
			pdcCondition = new PaperDepartmentCountCondition();
			pdcCondition.setOwnerId(user.getSchoolId());
			pdcCondition.setOwnerType(user.getGroupType());
			pdcCondition.setIsDeleted(false);
			pdcCondition.setDepartmentId(Integer.parseInt(departments[i]
					.equals("") ? "-1" : departments[i]));
			List<PaperDepartmentCount> pdcList = this.paperDepartmentCountService
					.findPaperDepartmentCountByCondition(pdcCondition);
			if (pdcList.size() > 0) {
				if (pdcList.get(0).getCount() > 0) {
					int num = pdcList.get(0).getCount() - 1 >= 0 ? pdcList.get(
							0).getCount() - 1 : 0;
					pdc.setId(pdcList.get(0).getId());
					pdc.setDepartmentId(pdcList.get(0).getDepartmentId());
					pdc.setCount(num);
					this.paperDepartmentCountService.modify(pdc);
				}
			}
		}
	}

	// ?????????????????????????????????????????????
	public void removePaperDepartmentCount(List<PaperUser> pUser,
			Integer paperId, @CurrentUser UserInfo user) {
		if (pUser.size() > 0) {
			PaperDepartmentCountCondition pdCondition = null;
			List<PaperDepartmentCount> pdList = null;
			PaperDepartmentCount paperdc = null;
			for (int i = 0; i < pUser.size(); i++) {
				pdCondition = new PaperDepartmentCountCondition();
				pdCondition.setDepartmentId(pUser.get(i).getDepartmentId());
				pdCondition.setOwnerId(user.getSchoolId());
				pdCondition.setOwnerType(user.getGroupType());
				pdList = this.paperDepartmentCountService
						.findPaperDepartmentCountByCondition(pdCondition);
				paperdc = new PaperDepartmentCount();
				if (pdList.size() > 0) {
					for (PaperDepartmentCount p : pdList) {
						if (p.getCount() > 0) {
							int num = p.getCount() - 1 >= 0 ? p.getCount() - 1
									: 0;
							paperdc.setId(p.getId());
							paperdc.setDepartmentId(p.getDepartmentId());
							paperdc.setCount(num);
							this.paperDepartmentCountService.modify(paperdc);
						}
					}

				}

			}
		}

	}
	
	// ??????paper_id?????????????????????????????????
	public void deletePaperUserRead(Integer paperId, @CurrentUser UserInfo user) {
		PaperUserRead pur = new PaperUserRead();
		pur.setOwnerId(user.getSchoolId());
		pur.setOwnerType(user.getGroupType());
		pur.setPaperId(paperId);
		this.paperUserReadService.deleteByPaperId(pur);
	}

	// ???????????????
	public List<PaperUserReadVo> readList(Integer paperId,
			@CurrentUser UserInfo user) {
		PaperUserReadCondition purCondition = new PaperUserReadCondition();
		purCondition.setOwnerId(user.getSchoolId());
		purCondition.setOwnerType(user.getGroupType());
		purCondition.setPaperId(paperId);
		List<PaperUserReadVo> purList = this.paperUserReadService
				.findPaperUserReadByConditionVo(purCondition, null, null);

		return purList;
	}
	
	
	/*???????????????????????????????????????
	 * @param paperId
	 * @param user
	 */
	public void AlterPaperUserRead(Integer paperId, UserInfo user){
		PaperUserRead pRead = this.paperUserReadService.findByPaperIdAndUserId(
				user.getSchoolId(), user.getGroupType(), paperId, user.getId());
		// ???????????????????????????????????????????????????????????????????????????????????????????????????
		String[] userType = user.getUserTypes().split(", ");
		if (!Arrays.asList(userType).contains("1")) {
			return;
		}

		if (pRead != null) {
			pRead.setReadStatus(true);
			this.paperUserReadService.modify(pRead);
		}
	}
	
	private String structurePath(String subPath) {
		return BASE_PATH + subPath;
	}


	public void AddPaperUserRead(final List<Teacher> list,final @CurrentUser UserInfo user, final Integer paperId) {
			try {
				for (Teacher t : list) {
					PaperUserRead puRead = new PaperUserRead();
					puRead.setOwnerId(user.getSchoolId());
					puRead.setOwnerType(user.getGroupType());
					puRead.setPaperId(paperId);
					puRead.setReadStatus(false);
					puRead.setUserId(t.getUserId());
					paperUserReadService.add(puRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * 2015-10-16 ??????????????????????????????????????????????????????
	 */
	public void sendApplyPaperMesToManager(UserInfo user,
			List<Integer> teacherList) {

		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("????????????????????????????????????");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_GONGWENGUANLI);
//		PushMessageUtil.sendMessage(message, teacherList);
//		PushMessageUtil.pushMessageList(teacherList);
		messageService.sendMessage(message, teacherList);
	}
}
