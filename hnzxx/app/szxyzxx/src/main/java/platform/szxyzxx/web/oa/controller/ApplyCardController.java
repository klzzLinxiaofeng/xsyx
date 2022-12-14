package platform.szxyzxx.web.oa.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.model.ApplyCard;
import platform.education.oa.model.OaApplycardDepartmentCount;
import platform.education.oa.model.VehicleManagement;
import platform.education.oa.vo.ApplyCardCondition;
import platform.education.oa.vo.ApplyCardVo;
import platform.education.oa.vo.OaApplycardDepartmentCountCondition;
import platform.education.oa.vo.VehicleManagementCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.contants.VehicleStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/oa/applycard")
public class ApplyCardController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/applyCard";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ApplyCardCondition condition,
			@ModelAttribute("page") Page page,
			//auditType :????????????????????????  1?????????????????????(own)   2???????????????(department)    
			//             3???????????????(all)    4????????????(wait)     5????????????(already)
			@RequestParam(value = "auditType", required = false) String auditType,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "menu", required = false) String menu,
			@ModelAttribute("order") Order order, Model model) throws ParseException {
		OaApplycardDepartmentCountCondition oaA = new OaApplycardDepartmentCountCondition();
		String viewPath = null;
		ApplyCardCondition condition1 = new ApplyCardCondition();
		condition1.setSchoolId(user.getSchoolId());
		condition1.setIsDelete(false);
		long all = this.applyCardService.count(condition1);
		
		conditionFilter(user, condition);
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		//?????????????????????????????????
		page.setPageSize(3);
		if(auditType != null && auditType.equals("own")){
			condition.setProposer(user.getId());
		}
		if(auditType != null && auditType.equals("department")){
			//?????????????????????   ???????????????????????????????????????ID???-1
			if(user.getTeacherId()!=null && !"".equals(user.getTeacherId())){
				condition.setTeacherId(user.getTeacherId());
			}else{
				condition.setTeacherId(-1);
			}
		}
		if(auditType != null && auditType.equals("all")){
			//?????????????????????
		}
		if(auditType != null && auditType.equals("wait")){
			//?????????????????????
			condition.setAuditStatus(VehicleStatus.audit);
		}
		if(auditType != null && auditType.equals("already")){
			//?????????????????????
			condition.setAuditStatusAlready("already");
		}
		if(departmentId!=null && !"".equals(departmentId)){     
			//??????????????????????????????
			condition.setDepartmentId(Integer.parseInt(departmentId));
		}
		
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<ApplyCardVo> items = this.applyCardService.findApplyCardAllByCondition(condition, page, order);
		//????????????
		if(items.size() > 0){
			for(int i = 0;i < items.size(); i ++){
			    long from = items.get(i).getBeginDate().getTime();
			    long to = items.get(i).getEndDate().getTime();
			    long day = ((to - from) / (1000 * 60 * 60 * 24));
			    items.get(i).setTolDay(Long.toString(day));
			}
			
		}
		
		//??????????????????
		DepartmentTeacherCondition depCondition = new DepartmentTeacherCondition();
		depCondition.setTeacherId(user.getTeacherId()==null?-1:user.getTeacherId());
		depCondition.setIsDeleted(false);
		depCondition.setSchoolId(user.getSchoolId());
		List<DepartmentTeacher> ownDep = departmentTeacherService.findDepartmentTeacherByCondition(depCondition, null, null);
		if(ownDep.size()<=0){
			model.addAttribute("noDepartment","noDepartment");
		}
		
		//??????????????????
		DepartmentCondition departmentCondition = new DepartmentCondition();
		departmentCondition.setSchoolId(user.getSchoolId());
		departmentCondition.setIsDelete(false);
		List<Department> dList = departmentService.findDepartmentByCondition(departmentCondition, null, null);
		
		oaA.setSchoolId(user.getSchoolId());
		//????????????
		oaA.setSsWord(condition.getSsword());
		if(auditType != null && auditType.equals("wait")){
			//?????????????????????
			oaA.setAuditStatus(VehicleStatus.audit);
		}
		if(auditType != null && auditType.equals("already")){
			oaA.setAuditStatus(VehicleStatus.using);
		}
		
		ApplyCardCondition conditionAuditOrNot = new ApplyCardCondition();
		conditionAuditOrNot.setSchoolId(user.getSchoolId());
		conditionAuditOrNot.setIsDelete(false);
		conditionAuditOrNot.setSsword(condition.getSsword());
		long tatolCount = 0;
		if(auditType != null && auditType.equals("wait")){
			//?????????????????????
			conditionAuditOrNot.setAuditStatus(VehicleStatus.audit);
			tatolCount = applyCardService.count(conditionAuditOrNot);
		}
		if(auditType != null && auditType.equals("already")){
			//???????????????????????????
			conditionAuditOrNot.setAuditStatusAlready("already");
			tatolCount = applyCardService.count(conditionAuditOrNot);
		}
		
		List<OaApplycardDepartmentCount> numList = null;
		
		//?????????????????????????????? ??? ?????? ????????????????????????
		List<OaApplycardDepartmentCount> numSqList = null;
		if(menu==null || "".equals(menu)){
			OaApplycardDepartmentCountCondition sqCondition  = new OaApplycardDepartmentCountCondition();
			sqCondition.setSchoolId(user.getSchoolId());
			sqCondition.setIsdelete(false);
			numSqList = this.oaApplycardDepartmentCountService.findOaApplycardSqnumByCondition(sqCondition);
		}else{
			//?????? ???????????????????????? ?????? ????????????????????????
			numList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(oaA);
		}
		
		if ("list".equals(sub)) {
			if(menu!=null && menu.equals("sp")){
				viewPath = structurePath("/waitlist");
			}else{
				viewPath = structurePath("/list");
			}
		} else {
			if(menu!=null && menu.equals("sp")){
				viewPath = structurePath("/waitindex");
			}else{
				viewPath = structurePath("/index");
			}
		}
		
		model.addAttribute("numSqList", numSqList);
		model.addAttribute("tatolCount", tatolCount);
		model.addAttribute("numList",numList);
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("oList", ownDep);
		model.addAttribute("dList", dList);
		model.addAttribute("curenuser", user.getId());
		model.addAttribute("items", items);
		model.addAttribute("auditType", auditType);
		model.addAttribute("menu", menu);
		model.addAttribute("all", all);  //page.getTotalRows()????????????????????????  all???????????????????????????????????????
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ApplyCard> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ApplyCardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.applyCardService.findApplyCardByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user,Model model) {
		VehicleManagementCondition condition = new VehicleManagementCondition();
		condition.setIsDelete(false);
		condition.setSchoolId(user.getSchoolId());
		List<VehicleManagement> cardList =  vehicleManagementService.findVehicleManagementByCondition(condition);
//		if(cardList.size() > 0){
//			for(int i = 0; i < cardList.size(); i++){
//				FileResult file = fileService.findFileByUUID(cardList.get(i).getCover());
//				if(file != null){
//					cardList.get(i).setCover(file.getHttpUrl());
//				}
//			}
//		}
		
		DepartmentTeacherCondition depcondition = new DepartmentTeacherCondition();
		depcondition.setTeacherId(user.getTeacherId()==null?-1:user.getTeacherId());
		depcondition.setIsDeleted(false);
		depcondition.setSchoolId(user.getSchoolId());
		List<DepartmentTeacher> departmentList = departmentTeacherService.findDepartmentTeacherByCondition(depcondition,null,null);
		model.addAttribute("depList", departmentList);
		
		model.addAttribute("cardList",cardList);
		return new ModelAndView(structurePath("/shenqing_yongche"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ApplyCard applyCard, @CurrentUser UserInfo user) {
		if(applyCard.getDepartmentId()!=null && !"".equals(applyCard.getDepartmentId())){
			applyCard.setDepartmentId(applyCard.getDepartmentId());
		}else{
			applyCard.setDepartmentId(null);
		}
		applyCard.setAuditStatus(VehicleStatus.audit);
		applyCard.setCreateDate(new Date());
		applyCard.setProposer(user.getId());
		applyCard.setProposerName(user.getRealName());
		applyCard.setIsDelete(false);
		applyCard.setSchoolId(user.getSchoolId());
		applyCard = this.applyCardService.add(applyCard);
		
		if(applyCard.getDepartmentId()!=null && !"".equals(applyCard.getDepartmentId())){
			OaApplycardDepartmentCountCondition dcondition = new OaApplycardDepartmentCountCondition();
			dcondition.setDepartmentId(applyCard.getDepartmentId());
			dcondition.setSchoolId(user.getSchoolId());
			dcondition.setIsdelete(false);
			dcondition.setAuditStatus(VehicleStatus.audit);
			List<OaApplycardDepartmentCount> dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
	
			OaApplycardDepartmentCount dCount = new OaApplycardDepartmentCount();
			if(dList.size() > 0){
				int num = dList.get(0).getNumber() + 1;
				dCount.setId(dList.get(0).getId());
				dCount.setNumber(num);
				this.oaApplycardDepartmentCountService.modify(dCount);
			}else{
				dCount.setDepartmentId(applyCard.getDepartmentId());
				dCount.setSchoolId(user.getSchoolId());
				dCount.setNumber(1);
				dCount.setAuditStatus(VehicleStatus.audit);
				this.oaApplycardDepartmentCountService.add(dCount);
			}
		}
		
		sendApplyCardMesToManager(ContansOfOa.oaMenuOfApproveCard, user.getGroupId(),user);
		return applyCard != null ? new ResponseInfomation(applyCard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		
		ApplyCard applyCard = this.applyCardService.findApplyCardById(id);
		
		VehicleManagementCondition condition = new VehicleManagementCondition();
		condition.setIsDelete(false);
		condition.setSchoolId(user.getSchoolId());
		List<VehicleManagement> cardList =  vehicleManagementService.findVehicleManagementByCondition(condition);
		
		DepartmentTeacherCondition depcondition = new DepartmentTeacherCondition();
		depcondition.setTeacherId(user.getTeacherId());
		depcondition.setIsDeleted(false);
		depcondition.setSchoolId(user.getSchoolId());
		List<DepartmentTeacher> departmentList = departmentTeacherService.findDepartmentTeacherByCondition(depcondition,null,null);
		
		model.addAttribute("depList", departmentList);
		model.addAttribute("cardList",cardList);
		model.addAttribute("applyCard", applyCard);
		return new ModelAndView(structurePath("/shenqing_yongche"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ApplyCard applyCard = this.applyCardService.findApplyCardById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("applyCard", applyCard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id, ApplyCard applyCard) {
		if (applyCard != null) {
			applyCard.setId(id);
		}
		try {
			applyCard.setIsDelete(true);
			applyCard = this.applyCardService.modify(applyCard);
			//???????????????????????????-1
			OaApplycardDepartmentCountCondition dcondition = new OaApplycardDepartmentCountCondition();
			dcondition.setDepartmentId(applyCard.getDepartmentId());
			dcondition.setSchoolId(user.getSchoolId());
			dcondition.setIsdelete(false);
			dcondition.setAuditStatus(VehicleStatus.audit);
			List<OaApplycardDepartmentCount> dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
			OaApplycardDepartmentCount dCount = new OaApplycardDepartmentCount();
			if(dList.size() > 0){
				int num = dList.get(0).getNumber() - 1;
				dCount.setId(dList.get(0).getId());
				dCount.setNumber(num);
				this.oaApplycardDepartmentCountService.modify(dCount);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,@CurrentUser UserInfo user, ApplyCard applyCard) {
		//??????????????????????????????
		//????????????????????????????????????????????????
		if(applyCard.getAuditStatus()!=null && !"".equals(applyCard.getAuditStatus())){
			boolean isUse = checkIsUse(id, user);
			if(isUse){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		applyCard.setId(id);
		if(applyCard.getAuditStatus()!=null && !"".equals(applyCard.getAuditStatus())){
			applyCard.setAuditUser(user.getId());
			applyCard.setAuditName(user.getRealName());
			applyCard.setReleaseDate(new Date());
			applyCard.setAuditStatus(applyCard.getAuditStatus());
		}
		
		//??????????????????????????????????????????????????????????????????????????????  ????????????????????????  ?????????????????????  ?????????  ??????????????????
		//????????? 2016-3-3
		ApplyCard app = applyCardService.findApplyCardById(id);
		if(!app.getAuditStatus().equals(VehicleStatus.audit)){
			//?????????????????????????????????????????????????????????
			return new ResponseInfomation("isApproval");
		}
		
		applyCard = this.applyCardService.modify(applyCard);
		
		//??????????????????????????????????????????????????????????????????
		if(applyCard.getAuditStatus()!=null && !"".equals(applyCard.getAuditStatus()) && applyCard.getAuditStatus().equals("1")){
			VehicleManagement v = new VehicleManagement();
			v.setId(applyCard.getCardId());
			v.setServiceCondition(VehicleStatus.using);
			vehicleManagementService.modify(v);
			
			//???????????????????????? ?????????1????????? ?????????+1  2????????????-1
			if(applyCard.getDepartmentId()!=null && !"".equals(applyCard.getDepartmentId())){
				OaApplycardDepartmentCountCondition dcondition = new OaApplycardDepartmentCountCondition();
				dcondition.setDepartmentId(applyCard.getDepartmentId());
				dcondition.setSchoolId(user.getSchoolId());
				dcondition.setIsdelete(false);
				dcondition.setAuditStatus(VehicleStatus.audit);
				List<OaApplycardDepartmentCount> dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
				
				//?????????-1
				OaApplycardDepartmentCount acount = new OaApplycardDepartmentCount();
				if(dList.size() > 0){
					acount.setId(dList.get(0).getId());
					acount.setNumber(dList.get(0).getNumber()-1);
					this.oaApplycardDepartmentCountService.modify(acount);
				}
				
				//?????????+1
				dcondition.setAuditStatus(VehicleStatus.using);
				dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
				if(dList.size() > 0){
					acount.setId(dList.get(0).getId());
					acount.setNumber(dList.get(0).getNumber()+1);
					this.oaApplycardDepartmentCountService.modify(acount);
				}else{
					OaApplycardDepartmentCount acounts = new OaApplycardDepartmentCount();
					acounts.setDepartmentId(applyCard.getDepartmentId());
					acounts.setNumber(1);
					acounts.setIsdelete(false);
					acounts.setAuditStatus(VehicleStatus.using);
					this.oaApplycardDepartmentCountService.add(acounts);
				}
			}
		}else{   //???????????????  ?????????1????????????????????????-1????????????????????????+1
			
			OaApplycardDepartmentCountCondition dcondition = new OaApplycardDepartmentCountCondition();
			dcondition.setDepartmentId(applyCard.getDepartmentId());
			dcondition.setSchoolId(user.getSchoolId());
			dcondition.setIsdelete(false);
			dcondition.setAuditStatus(VehicleStatus.using);
			List<OaApplycardDepartmentCount> dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
			
			//?????????+1
			OaApplycardDepartmentCount acount = new OaApplycardDepartmentCount();
			acount.setSchoolId(user.getSchoolId());
			if(dList.size() > 0){
				acount.setNumber(dList.get(0).getNumber()+1);
				acount.setId(dList.get(0).getId());
				this.oaApplycardDepartmentCountService.modify(acount);
			}else{
				acount.setAuditStatus(VehicleStatus.using);
				acount.setDepartmentId(applyCard.getDepartmentId());
				acount.setIsdelete(false);
				acount.setNumber(1);
				this.oaApplycardDepartmentCountService.add(acount);
			}
			
			//?????????-1
			dcondition.setAuditStatus(VehicleStatus.audit);
			dList = this.oaApplycardDepartmentCountService.findOaApplycardDepartmentCountByCondition(dcondition);
			if(dList.size() > 0){
				acount.setNumber(dList.get(0).getNumber()-1);
				acount.setId(dList.get(0).getId());
			}
			this.oaApplycardDepartmentCountService.modify(acount);
			
		}
		
		//??????????????????????????????
		ApplyCard ac = applyCardService.findApplyCardById(id);
		sendApplyCardMesToUser(user,ac.getProposer(),ac.getProposerName());
		
		return applyCard != null ? new ResponseInfomation(applyCard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/**
	 * ????????????????????????????????????   TRUE????????????    FALSE?????????
	 * ?????????????????????????????? ??? ??????????????????????????????
	 * 1???????????????????????? ??? ???????????? ???????????? ??? ???????????? ??????
	 * 2???????????????????????? ??? ???????????? ???????????? ??? ??????????????????
	 * 3????????????????????????????????? ?????? ???????????? ?????? ??? ????????????
	 * 4???????????????????????? ??? ????????? ?????????????????????  ?????? ????????????????????? ??? ??????????????????????????????
	 * @param id
	 * @param user
	 * @return true false
	 */
	public boolean checkIsUse(Integer id,@CurrentUser UserInfo user){
		ApplyCard applyCard = applyCardService.findApplyCardById(id);
		
		ApplyCardCondition applyCardCondition = new ApplyCardCondition();
		applyCardCondition.setSchoolId(user.getSchoolId());
		applyCardCondition.setIsDelete(false);
		applyCardCondition.setCardId(applyCard.getCardId());
		applyCardCondition.setAuditStatus(VehicleStatus.using);
		List<ApplyCard> list = applyCardService.findApplyCardByCondition(applyCardCondition);
		
		Date useBeginDate = applyCard.getBeginDate();
		Date useEndDate = applyCard.getEndDate();
		 
		if(list.size() > 0){
			for(int i = 0;i < list.size(); i++){
				Date listBeginDate = list.get(i).getBeginDate();
				Date listEndDate = list.get(i).getEndDate();
				boolean eqBegin = useBeginDate.equals(listBeginDate);
				boolean eqEnd = useEndDate.equals(listEndDate);
				boolean check1 = useBeginDate.after(listBeginDate) && useBeginDate.before(listEndDate);
				boolean check2 = useEndDate.after(listBeginDate) && useEndDate.before(listEndDate);
				boolean check3 = useBeginDate.after(listBeginDate) && useEndDate.before(listEndDate);
				boolean check4 = useBeginDate.before(listBeginDate) && useEndDate.after(listEndDate);
				if(check1 || check2 || check3 || check4 || eqBegin || eqEnd){
					return true;
				}
			}
		}
		return false;
	}
	
	
	@RequestMapping(value = "/monthChange")
	@ResponseBody
	public List<ApplyCardVo> monthChange(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ApplyCardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "curenMonth", required = false) String curenMonth,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		condition.setIsDelete(false);
		condition.setSchoolId(user.getSchoolId());
		  
		String month[] = curenMonth.split("-");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//?????????mm??????????????????  
		Date lastDate = null;
		Date firstDate = null;
		try {
			firstDate = sdf.parse(month[0]);
			lastDate = sdf.parse(month[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		condition.setBeginDate(firstDate);
		condition.setEndDate(lastDate);
		List<ApplyCardVo> list = this.applyCardService.findApplyCardAllByCondition(condition, page, order);
		
		//??????????????????  ???
		String tatol = applyCardService.count(condition).toString();
		
		condition.setAuditStatus(VehicleStatus.audit);
		String wait = applyCardService.count(condition).toString();
		
		condition.setAuditStatus(VehicleStatus.using);
		String alearty = applyCardService.count(condition).toString();
		
		condition.setAuditStatus(VehicleStatus.notAudit);
		String alearty1 = applyCardService.count(condition).toString();
		
		int alearty3 = (Integer.valueOf(alearty) + Integer.valueOf(alearty1));
		alearty = alearty3+"";
		
		if(list.size() > 0){
			Department dep = null;
			for(int i =0 ;i <list.size();i++){
				list.get(i).setTatolAudit(tatol);
				list.get(i).setAlertyAudit(alearty);
				list.get(i).setWaitAudit(wait);
				if(list.get(i).getDepartmentId()!=null){
					dep = this.departmentService.findDepartmentById(list.get(i).getDepartmentId());
					if(dep!=null){
						list.get(i).setDepetmrnt(dep.getName());
					}else{
					   list.get(i).setDepetmrnt("???");	
					}
				}else{
					list.get(i).setDepetmrnt("???");
				}
				
			}
			
		}

		return list;
	}
	
	private void conditionFilter(UserInfo user, ApplyCardCondition condition) {
	}
	
	/**
	 * 2015-10-14
	 * ??????????????????????????????????????????????????????
	 */
	public void sendApplyCardMesToManager(String persmissionCode, Integer groupId,UserInfo user){
		List<Integer> userIdList = userService.findUserIdByPermissionCode(persmissionCode,groupId);
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("????????????????????????????????????");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_CHELIANGSHENPI);
		PushMessageUtil.sendMessage(message, userIdList);
		PushMessageUtil.pushMessageList(userIdList);
	}
	
	
	/**
	 * 2015-10-14
	 * ??????????????????????????????????????????????????????
	 */
	public void sendApplyCardMesToUser(UserInfo user,Integer applyUserId,String applyUserName){
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("?????????????????????????????????????????????");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.FINISHED_PATH_CODE_CHELIANGSHENQING);
		PushMessageUtil.sendMessage(message, applyUserId);
		PushMessageUtil.pushMessage(applyUserId);
	}
	

}
