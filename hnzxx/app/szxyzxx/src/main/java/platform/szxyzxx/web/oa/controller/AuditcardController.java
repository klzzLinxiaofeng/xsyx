package platform.szxyzxx.web.oa.controller;
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

import platform.education.oa.model.Auditcard;
import platform.education.oa.model.Returncard;
import platform.education.oa.model.Usecard;
import platform.education.oa.model.Vehicle;
import platform.education.oa.vo.AuditcardCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.VehicleStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/oa/auditcard")
public class AuditcardController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/auditcard";

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AuditcardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Auditcard> items = this.auditcardService.findAuditcardByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Auditcard> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") AuditcardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.auditcardService.findAuditcardByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Auditcard auditcard, @CurrentUser UserInfo user) {
		String cardId = "";//获取车牌号的主键
		Usecard usecard = new Usecard();
		if(auditcard.getAuditType().equals(VehicleStatus.returning)){
			Integer Id = returncardService.findReturncardById(auditcard.getReturnOrUseId()).getUsecardId();
			cardId = usecardService.findUsecardById(Id).getPlateNumber();
			usecard.setId(Id);
		}else{
			cardId = usecardService.findUsecardById(auditcard.getReturnOrUseId()).getPlateNumber();//获取车牌号的主键
			usecard.setId(auditcard.getReturnOrUseId());
		}
		auditcard.setAuditUser(user.getTeacherId());
		auditcard.setAuditDate(new Date());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(Integer.parseInt(cardId));
		
		
		//判断 归还 和 申请 状态
		if(auditcard.getAuditType().equals(VehicleStatus.returning)){
			//归还状态
			if(auditcard.getAuditResult().equals(VehicleStatus.audit)){
				usecard.setStatus(VehicleStatus.returnOver);
				vehicle.setServiceCondition(VehicleStatus.audit);
				
					Returncard returncard = new Returncard();
					returncard.setReturnStatus(VehicleStatus.returnOver);
					returncard.setId(auditcard.getReturnOrUseId());
					returncardService.modify(returncard);
			}else{
				usecard.setStatus(VehicleStatus.using);
				vehicle.setServiceCondition(VehicleStatus.using);
			}
		}else{
			//申请状态  判断 是否同意
			if(auditcard.getAuditResult().equals(VehicleStatus.audit)){
				
				//申请同意的时候，去车辆库存查询  审批的该车  是否已经被使用
				//如果被使用 则审批不成功
				Vehicle vcard = vehicleService.findVehicleById(Integer.parseInt(cardId));
				if(vcard.getServiceCondition().equals(VehicleStatus.using)){
					return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
				}
				
				
				usecard.setStatus(VehicleStatus.using);
				usecard.setApproval(user.getTeacherId().toString());
				vehicle.setServiceCondition(VehicleStatus.using);
			}else{
				usecard.setStatus(VehicleStatus.notAudit);
				usecard.setApproval(user.getTeacherId().toString());
				vehicle.setServiceCondition(VehicleStatus.audit);
			}
		}
		try{
			vehicle = this.vehicleService.modify(vehicle);
			auditcard = this.auditcardService.add(auditcard);
			usecardService.modify(usecard);
		}catch(Exception e){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return auditcard != null ? new ResponseInfomation(auditcard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Auditcard auditcard = this.auditcardService.findAuditcardById(id);
		model.addAttribute("auditcard", auditcard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Auditcard auditcard = this.auditcardService.findAuditcardById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("auditcard", auditcard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Auditcard auditcard) {
		if (auditcard != null) {
			auditcard.setId(id);
		}
		try {
			this.auditcardService.remove(auditcard);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Auditcard auditcard) {
		auditcard.setId(id);
		auditcard = this.auditcardService.modify(auditcard);
		return auditcard != null ? new ResponseInfomation(auditcard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/shenpi")
	public ModelAndView shenpi(Model model,@RequestParam(value = "returnOrUseId", required = false) String returnOrUseId,@RequestParam(value = "auditType", required = false) String auditType) {
		model.addAttribute("returnOrUseId", returnOrUseId);
		model.addAttribute("auditType", auditType);
		return new ModelAndView(structurePath("/auditInput"),model.asMap());
	}
		
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AuditcardCondition condition) {
	}
}
