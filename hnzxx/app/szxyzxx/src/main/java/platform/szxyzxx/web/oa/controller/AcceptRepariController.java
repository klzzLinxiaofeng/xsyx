package platform.szxyzxx.web.oa.controller;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.model.AcceptRepari;
import platform.education.oa.model.ApplyRepair;
import platform.education.oa.model.Approval;
import platform.education.oa.model.WarrantyEquipment;
import platform.education.oa.vo.*;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.RepairStatus;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/oa/acceptrepari")
public class AcceptRepariController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/acceptRepari";
	
	@Autowired
	private BasicSQLService basicSQLService;
	@RequestMapping(value = "/ownRepair")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "applyId", required = true) Integer id,
			@RequestParam(value = "approval", required = false) String approval,
			@RequestParam(value = "isCK", required = false) String isCK,Model model) {
		String viewPath = structurePath("/acceptInput");
		Teacher tc = teacherService.findOfUser(user.getSchoolId(), user.getId());
		model.addAttribute("teacher",tc);
		if(approval != null && approval.equals("approval")){
			viewPath = structurePath("/approvalInput");
			model.addAttribute("currentTeacherId", tc == null ? -1 :tc.getId());
		}
		AcceptRepariCondition acc = new AcceptRepariCondition();
		acc.setRepariId(id);
		acc.setIsDelete(false);
		List<AcceptRepari> list = this.acceptRepariService.findAcceptRepariByCondition(acc);
		AcceptRepari accRepair = null;
		String status = "";
		if(list.size() > 0){
			accRepair = list.get(0);
			ApplyRepair apply = applyRepairService.findApplyRepairById(accRepair.getRepariId());
			status = apply.getStatus();
		}
		if(accRepair != null && accRepair.getPicture() != null){
			if(accRepair.getPicture() != null && !"".equals(accRepair.getPicture())){
				FileResult file = fileService.findFileByUUID(accRepair.getPicture());
				if(file != null){
					String imgUrl = file.getHttpUrl();
					model.addAttribute("imgUrl", imgUrl);
				}
			}
		}
		//====2016-3-8
		WarrantyEquipmentCondition wec = new WarrantyEquipmentCondition();
		wec.setRepairId(id);
		wec.setIsDelete(false);
		List<WarrantyEquipment> wecList = warrantyEquipmentService.findWarrantyEquipmentByCondition(wec);
		if(wecList != null && wecList.size() > 0){
			model.addAttribute("wecList", wecList);
		}
		
		//查询指派的教师并发送数据
		ApprovalCondition appCon = new ApprovalCondition();
		appCon.setIsDelete(false);
		appCon.setOwnerId(user.getSchoolId());
		appCon.setOwnerType(user.getGroupType());
		appCon.setProjectId(id);
		appCon.setProjectType("weixiu");
		List<ApprovalVo> appConList = approvalService.findApprovalByCondition(appCon);
		if(appConList != null && appConList.size() > 0){
			for(ApprovalVo vo : appConList){
				if(vo != null && vo.getTeacherId() != null){
					Teacher teacher = teacherService.findTeacherById(vo.getTeacherId());
					vo.setTeacherName(teacher.getName());
				}
			}
		}
		model.addAttribute("appConList", appConList);
		
		model.addAttribute("status", status);
		model.addAttribute("isCK", isCK);
		model.addAttribute("AcceptRepari", accRepair);
		model.addAttribute("applyId", id);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	//点击维修时展示的页面
	@RequestMapping(value = "/applyIndex")
	public ModelAndView applyIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ApplyRepairCondition condition,
			@ModelAttribute("page") Page page,
			@RequestParam(value = "approval", required = false) String approval,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		page.setPageSize(4);
		condition.setIsDelete(false);
		condition.setShcoolId(user.getSchoolId());
		List<ApplyRepairVo> items = this.applyRepairService.findApplyAndAcceptRepairByCondition(condition, page, order);
		
		if(approval != null && approval.equals("approval")){
			Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
			items = this.applyRepairService.findRepairHasApprovalByTeacherId(teacher == null ? -1 :teacher.getId(),page,order);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		for(ApplyRepairVo aa:items){
			if(aa.getPictureId()!=null){
				FileResult file = fileService.findFileByUUID(aa.getPictureId());
				if (file != null) {
					aa.setPictureUrl(file.getHttpUrl());
				}
			}
			System.out.println(aa.getWeixiugong()+"---"+user.getId());
			if(aa.getWeixiugong()!=null){
				if(aa.getWeixiugong().equals(user.getId())){
					aa.setIsc(1);
				}else{
					aa.setIsc(0);
				}
			}else {
				aa.setIsc(0);
			}
		}
		long count = this.applyRepairService.count(condition);
		
		model.addAttribute("approval", approval);
		model.addAttribute("items", items);
		model.addAttribute("count", count);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<AcceptRepari> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AcceptRepariCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.acceptRepariService.findAcceptRepariByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	//2016-4-12
	//维修受理创建
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(AcceptRepari acceptRepari,
			@RequestParam(value = "tableData", required = false) String tableData, 
			@CurrentUser UserInfo user,@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "teacherId", required = false) Integer teacherId) {
		if(acceptRepari.getAcceptDate()==null){
			acceptRepari.setAcceptDate(new Date());
		}
		acceptRepari.setSchoolId(user.getSchoolId());
		
		acceptRepari = this.acceptRepariService.add(acceptRepari);
				
		ApplyRepair applyRepair = new ApplyRepair();
		applyRepair.setId(acceptRepari.getRepariId());
		if(!"".equals(state) && state.equals("02")){
			applyRepair.setStatus(RepairStatus.receive);
		}else if(!"".equals(state) && state.equals("01")){
			applyRepair.setStatus(RepairStatus.apply);
		}else if(!"".equals(state) && state.equals("03")){
			applyRepair.setStatus(RepairStatus.finish);
		}else if(!"".equals(state) && state.equals("04")){
			applyRepair.setStatus(RepairStatus.notReceive);
		}
		this.applyRepairService.modify(applyRepair);
		
//		//添加过保配件信息
//		JSONArray data = JSONArray.fromObject(tableData);
//		if(data != null && data.size() > 0){
//			WarrantyEquipment we = null;
//			for(int i = 0; i < data.size(); i++){
//				JSONObject jsonJ = data.getJSONObject(i);
//				String name = jsonJ.getString("name");
//				String unit =  jsonJ.getString("unit");
//				Integer number =  jsonJ.getInt("number");
//
//				we = new WarrantyEquipment();
//				we.setCreateDate(new Date());
//				we.setIsDelete(false);
//				we.setModifyDate(new Date());
//				we.setName(name);
//				we.setNumber(number);
//				we.setRepairId(acceptRepari.getRepariId());
//				we.setUnit(unit);
//				warrantyEquipmentService.add(we);
//			}
//		}
//
//		//如果指派了审核人，将审核人添加到审核表中，为第一次审核
//		if(teacherId != null){
//			Approval approval = new Approval();
//			approval.setApprovalOrder("1");
//			//1表示通过   2表示不通过    3表示还未审批
//			approval.setApprovalResult("3");
//			approval.setCreateDate(new Date());
//			approval.setIsDelete(false);
//			approval.setModifyDate(new Date());
//			approval.setOwnerId(user.getSchoolId());
//			approval.setOwnerType(user.getGroupType());
//			approval.setProjectId(acceptRepari.getRepariId());
//			approval.setProjectType("weixiu");
//			approval.setTeacherId(teacherId);
//			approvalService.add(approval);
//		}
		
		//新增加的发送维修结果给对应的申请人
//		if(state.equals("03") || state.equals("04")){
//			ApplyRepair ar = applyRepairService.findApplyRepairById(acceptRepari.getRepariId());
//			sendApplyCardMesToUser(user,ar.getProposerId());
//		}
		
		return acceptRepari != null ? new ResponseInfomation(acceptRepari.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AcceptRepari acceptRepari = this.acceptRepariService.findAcceptRepariById(id);
		model.addAttribute("acceptRepari", acceptRepari);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AcceptRepari acceptRepari = this.acceptRepariService.findAcceptRepariById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("acceptRepari", acceptRepari);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AcceptRepari acceptRepari) {
		if (acceptRepari != null) {
			acceptRepari.setId(id);
		}
		try {
			this.acceptRepariService.remove(acceptRepari);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	//2016-4-12
	//维修受理修改
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AcceptRepari acceptRepari,
			@RequestParam(value = "tableData", required = false)String tableData,
			@RequestParam(value = "jsonStr", required = false)String jsonData,
			@RequestParam(value = "state", required = false) String state,@CurrentUser UserInfo user,
			@RequestParam(value = "teacherId", required = false) Integer teacherId) {
		acceptRepari.setId(id);
		AcceptRepari ac = acceptRepariService.findAcceptRepariById(id);
		if(acceptRepari.getPicture() != "undefined" && acceptRepari.getPicture() != null && !"".equals(acceptRepari.getPicture())){
			acceptRepari.setPicture(acceptRepari.getPicture());
		}else{
			if(ac != null){
				acceptRepari.setPicture(ac.getPicture());
			}
		}
		acceptRepari = this.acceptRepariService.modify(acceptRepari);
		
		//添加过保配件信息  添加之前先查询原有数据  将其删除
		WarrantyEquipmentCondition warrantyEquipmentCondition = new WarrantyEquipmentCondition();
		warrantyEquipmentCondition.setIsDelete(false);
		warrantyEquipmentCondition.setRepairId(acceptRepari.getRepariId());
		List<WarrantyEquipment> warrantyEquipmentList = warrantyEquipmentService.findWarrantyEquipmentByCondition(warrantyEquipmentCondition);
		if(warrantyEquipmentList != null && warrantyEquipmentList.size() > 0){
			for(WarrantyEquipment we : warrantyEquipmentList){
				if(we != null && we.getId() != null){
					warrantyEquipmentService.remove(we);
				}
			}
		}
		
		JSONArray data = JSONArray.fromObject(tableData);
		if(data != null && data.size() > 0){
			WarrantyEquipment we = null;
			for(int i = 0; i < data.size(); i++){
				JSONObject jsonJ = data.getJSONObject(i);
				String name = jsonJ.getString("name");
				String unit =  jsonJ.getString("unit");
				Integer number =  jsonJ.getInt("number");
				
				we = new WarrantyEquipment();
				we.setCreateDate(new Date());
				we.setIsDelete(false);
				we.setModifyDate(new Date());
				we.setName(name);
				we.setNumber(number);
				we.setRepairId(acceptRepari.getRepariId());
				we.setUnit(unit);
				warrantyEquipmentService.add(we);
			}
		}
		
		//如果指派了审核人，将审核人添加到审核表中，为第一次审核
		ApprovalCondition appCon = new ApprovalCondition();
		appCon.setIsDelete(false);
		appCon.setApprovalOrder("1");
		appCon.setOwnerId(user.getSchoolId());
		appCon.setOwnerType(user.getGroupType());
		appCon.setProjectId(acceptRepari.getRepariId());
		appCon.setProjectType("weixiu");
		List<ApprovalVo> list = approvalService.findApprovalByCondition(appCon);
		//如果不存在则添加   存在不需要改动  不是在这里改动
		if(list == null || list.size() <= 0){
			Approval approval = new Approval();
			approval.setApprovalOrder("1");
			//1表示通过   2表示不通过    3表示还未审批
			approval.setApprovalResult("3");
			approval.setCreateDate(new Date());
			approval.setIsDelete(false);
			approval.setModifyDate(new Date());
			approval.setOwnerId(user.getSchoolId());
			approval.setOwnerType(user.getGroupType());
			approval.setProjectId(acceptRepari.getRepariId());
			approval.setProjectType("weixiu");
			approval.setTeacherId(teacherId);
			approval.setDepartment(null);
			approval.setApprovalExplain(null);
			approvalService.add(approval);
		}
		
		ApplyRepair applyRepair = new ApplyRepair();
		applyRepair.setId(acceptRepari.getRepariId());
		if(!"".equals(state) && state.equals("02")){
			applyRepair.setStatus(RepairStatus.receive);
		}else if(!"".equals(state) && state.equals("01")){
			applyRepair.setStatus(RepairStatus.apply);
		}else if(!"".equals(state) && state.equals("03")){
			applyRepair.setStatus(RepairStatus.finish);
		}else if(!"".equals(state) && state.equals("04")){
			applyRepair.setStatus(RepairStatus.notReceive);
		}
		this.applyRepairService.modify(applyRepair);
		
		//新增加的发送维修结果给对应的申请人
//		if(state.equals("03") || state.equals("04")){
//			ApplyRepair ar = applyRepairService.findApplyRepairById(acceptRepari.getRepariId());
//			sendApplyCardMesToUser(user,ar.getProposerId());
//		}
		return acceptRepari != null ? new ResponseInfomation(acceptRepari.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/addApproval", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation addApproval(@CurrentUser UserInfo user,@RequestParam(value = "acceptId", required = false)Integer acceptId,
			@RequestParam(value = "jsonStr", required = false)String jsonData){
		
		JSONArray data = JSONArray.fromObject(jsonData);
		Approval approval = null;
		Approval app = null;
		if(data != null && data.size() > 0){
			for(int i = 0; i < data.size(); i++){
				approval = new Approval();
				JSONObject jsonJ = data.getJSONObject(i);
				String id = jsonJ.getString("id");
				String teacherId =  jsonJ.getString("teacherId");
				String department =  jsonJ.getString("department");
				String approvalResult =  jsonJ.getString("approvalResult");
				String approvalExplain =  jsonJ.getString("approvalExplain");
				String approvalOrder =  jsonJ.getString("approvalOrder");
				String pictureId=jsonJ.getString("pictureId");
				String haosun=jsonJ.getString("haosun");
				if(id == null || "".equals(id) || id.equals("undefined")){
					id = "0";
				}
				if(teacherId == null || "".equals(teacherId) || teacherId.equals("undefined")){
					teacherId = "";
				}
				if(approvalOrder == null || "".equals(approvalOrder) || approvalOrder.equals("undefined")){
					approvalOrder = "";
				}
				if(department == null || "".equals(department) || department.equals("undefined")){
					department = "";
				}
				if(approvalResult == null || "".equals(approvalResult) || approvalResult.equals("undefined")){
					approvalResult = "3";
				}
				if(approvalExplain == null || "".equals(approvalExplain) || approvalExplain.equals("undefined")){
					approvalExplain = "";
				}
				
				Approval appro = approvalService.findApprovalById(Integer.valueOf(id));
				AcceptRepari acceptRepari = acceptRepariService.findAcceptRepariById(acceptId);
				if(acceptRepari != null){
					if(appro != null){
						approval.setId(Integer.valueOf(id));
						approval.setTeacherId(teacherId == "" ? null : Integer.valueOf(teacherId));
						approval.setApprovalExplain(approvalExplain);
						approval.setApprovalOrder(approvalOrder);
						approval.setApprovalResult(approvalResult);
						approval.setDepartment(department);
						approval.setIsDelete(false);
						approval.setModifyDate(new Date());
						approval.setOwnerId(user.getSchoolId());
						approval.setOwnerType(user.getGroupType());
						approval.setProjectId(acceptRepari.getRepariId());
						approval.setProjectType("weixiu");
						approval.setTeacherId(teacherId == "" ? null : Integer.valueOf(teacherId));
						app = approvalService.modify(approval);
					}else{
						if(teacherId != null && !"".equals(teacherId)){
							approval.setTeacherId(teacherId == "" ? null : Integer.valueOf(teacherId));
							approval.setApprovalExplain(approvalExplain);
							approval.setApprovalOrder(approvalOrder);
							approval.setApprovalResult(approvalResult);
							approval.setDepartment(department);
							approval.setIsDelete(false);
							approval.setModifyDate(new Date());
							approval.setCreateDate(new Date());
							approval.setOwnerId(user.getSchoolId());
							approval.setOwnerType(user.getGroupType());
							approval.setProjectId(acceptRepari.getRepariId());
							approval.setProjectType("weixiu");
							approval.setTeacherId(teacherId == "" ? null : Integer.valueOf(teacherId));
							approval.setPictureuuId(pictureId);
							approval.setIsHaoCai(Integer.parseInt(haosun));
							app = approvalService.add(approval);
						}
					}
				}
				
			}
		}
		
		return app != null ? new ResponseInfomation(app.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AcceptRepariCondition condition) {
	}
	
	/**
	 * 2015-10-14
	 * 将维修受理的信息发送给相对应的申请人
	 */
	public void sendApplyCardMesToUser(UserInfo user,Integer applyUserId){
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的维修申请审批结果通知！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_WEIXIUSHENQING);
		PushMessageUtil.sendMessage(message, applyUserId);
		PushMessageUtil.pushMessage(applyUserId);
	}
}
