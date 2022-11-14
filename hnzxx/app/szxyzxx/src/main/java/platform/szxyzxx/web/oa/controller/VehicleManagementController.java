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

import platform.education.oa.model.VehicleManagement;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.VehicleManagementCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.VehicleStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/oa/vehicleManagement")
public class VehicleManagementController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/vehicleManagement";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") VehicleManagementCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		page.setPageSize(4);
		conditionFilter(user, condition);
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<VehicleManagement> items = this.vehicleManagementService.findVehicleManagementByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
//		if(items.size() > 0){
//			for(int i = 0; i < items.size(); i++){
//				if(items.get(i).getCover() != null && !"".equals(items.get(i).getCover())){
//					FileResult f = fileService.findFileByUUID(items.get(i).getCover());
//					if(f != null){
//						items.get(i).setCover(f.getHttpUrl());
//					}
//				}
//			}
//		}
		
		
		//计算总数
		VehicleManagementCondition vehicleManagementCondition = new VehicleManagementCondition();
		vehicleManagementCondition.setIsDelete(false);
		vehicleManagementCondition.setSchoolId(user.getSchoolId());
		vehicleManagementCondition.setSsword(condition.getSsword());
		model.addAttribute("tatol", vehicleManagementService.count(vehicleManagementCondition));
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<VehicleManagement> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") VehicleManagementCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.vehicleManagementService.findVehicleManagementByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(VehicleManagement vehicleManagement, @CurrentUser UserInfo user) {
		vehicleManagement.setCreateDate(new Date());
		vehicleManagement.setIsDelete(false);
		vehicleManagement.setSchoolId(user.getSchoolId());
		vehicleManagement.setServiceCondition(VehicleStatus.audit);
		vehicleManagement.setUuid(UUIDUtils.getUUID());
		vehicleManagement = this.vehicleManagementService.add(vehicleManagement);
		return vehicleManagement != null ? new ResponseInfomation(vehicleManagement.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		VehicleManagement vehicleManagement = this.vehicleManagementService.findVehicleManagementById(id);
//		if(vehicleManagement != null){
//			if(vehicleManagement.getCover() != null && !"".equals(vehicleManagement.getCover())){
//				FileResult f = fileService.findFileByUUID(vehicleManagement.getCover());
//				if(f != null){
//					vehicleManagement.setCover(f.getHttpUrl());
//				}
//			}
//		}
		model.addAttribute("vehicleManagement", vehicleManagement);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		VehicleManagement vehicleManagement = this.vehicleManagementService.findVehicleManagementById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("vehicleManagement", vehicleManagement);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, VehicleManagement vehicleManagement) {
		if (vehicleManagement != null) {
			vehicleManagement.setId(id);
		}
		try {
			vehicleManagement.setIsDelete(true);
			this.vehicleManagementService.modify(vehicleManagement);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, VehicleManagement vehicleManagement) {
		vehicleManagement.setId(id);
		vehicleManagement = this.vehicleManagementService.modify(vehicleManagement);
		return vehicleManagement != null ? new ResponseInfomation(vehicleManagement.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, VehicleManagementCondition condition) {
//		Integer groupId = condition.getGroupId();
//		Integer appId = condition.getAppId();
//		if(user != null && groupId == null) {
//			condition.setGroupId(user.getGroupId());
//		}
//		
//		if(appId == null) {
//			condition.setAppId(SysContants.SYSTEM_APP_ID);
//		}
	}
}
