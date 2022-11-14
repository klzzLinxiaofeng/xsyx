package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.Equipment;
import platform.education.school.affair.vo.EquipmentCondition;
import platform.education.school.affair.vo.EquipmentVo;
import platform.education.school.affair.vo.EquipmentsCountVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/equipment")
public class EquipmentController extends BaseController { 
	
	private final static String viewBasePath = "/schoolaffair/laboratory/equipment";
	
	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") EquipmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<EquipmentVo> items = this.equipmentService.findEquipmentByCondition(condition, page, order);
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
	public List<EquipmentVo> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") EquipmentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.equipmentService.findEquipmentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Equipment equipment, @CurrentUser UserInfo user) {
//		Integer groupId = equipment.getGroupId();
//		Integer appId = equipment.getAppId();
//		if(groupId == null) {
//			equipment.setGroupId(user.getGroupId());
//		}
//		if(appId == null) {
//			equipment.setAppId(SysContants.SYSTEM_APP_ID);
//		}
		equipment.setSchoolId(user.getSchoolId());
		if(equipment.getBoughtTime() == null){
			equipment.setBoughtTime(new Date());
		}
		equipment = this.equipmentService.add(equipment);
		return equipment != null ? new ResponseInfomation(equipment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/getEquipmentsCountVo", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView getEquipmentsCountVo(@RequestParam(value = "laboratoryRoomId", required = true) Integer laboratoryRoomId){
		List<EquipmentsCountVo> voList = this.equipmentService.getEquipmentsCountVo(laboratoryRoomId);
		ModelAndView result = new ModelAndView();
		result.addObject("items", voList);
		result.setViewName("/schoolaffair/laboratoryroom/equipmentList");
		return result;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Equipment equipment = this.equipmentService.findEquipmentById(id);
		model.addAttribute("equipment", equipment);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Equipment equipment = this.equipmentService.findEquipmentById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("equipment", equipment);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Equipment equipment) {
		if (equipment != null) {
			equipment.setId(id);
		}
		try {
			this.equipmentService.remove(equipment);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Equipment equipment) {
		equipment.setId(id);
		equipment = this.equipmentService.modify(equipment);
		return equipment != null ? new ResponseInfomation(equipment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, EquipmentCondition condition) {
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
