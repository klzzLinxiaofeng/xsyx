package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.LaboratoryRoom;
import platform.education.school.affair.vo.LaboratoryRoomCondition;
import platform.education.school.affair.vo.LaboratoryRoomVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/laboratoryroom")
public class LaboratoryRoomController extends BaseController { 
	
	private final static String viewBasePath = "/schoolaffair/laboratory/laboratoryroom";
	
	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") LaboratoryRoomCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<LaboratoryRoomVo> items = this.laboratoryRoomService.findLaboratoryRoomByCondition(condition, page, order);
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
	public List<LaboratoryRoomVo> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") LaboratoryRoomCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.laboratoryRoomService.findLaboratoryRoomByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(LaboratoryRoomVo laboratoryRoomVo, @CurrentUser UserInfo user) {
		laboratoryRoomVo.setSchoolId(user.getSchoolId());
		LaboratoryRoom laboratoryRoom = null;
		laboratoryRoom = this.laboratoryRoomService.add(laboratoryRoomVo);
		return laboratoryRoom != null ? new ResponseInfomation(laboratoryRoom.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/validateName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean validateName(@RequestParam(value = "name") String name, @CurrentUser UserInfo user) {
		return laboratoryRoomService.validateName(user.getSchoolId(), name);
	}
	
	@RequestMapping(value = "/getLaboratoryRoomInfoList", method = RequestMethod.POST)
	@ResponseBody
	public List<LaboratoryRoomVo> getLaboratoryRoomInfoList(@CurrentUser UserInfo user){
		LaboratoryRoomCondition laboratoryRoomCondition = new LaboratoryRoomCondition();
		conditionFilter(user, laboratoryRoomCondition);
		List<LaboratoryRoomVo> result = laboratoryRoomService.findLaboratoryRoomByCondition(laboratoryRoomCondition);
		return result;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		LaboratoryRoom laboratoryRoom = this.laboratoryRoomService.findLaboratoryRoomById(id);
		model.addAttribute("laboratoryroom", laboratoryRoom);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		LaboratoryRoom laboratoryRoom = this.laboratoryRoomService.findLaboratoryRoomById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("laboratoryRoom", laboratoryRoom);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, LaboratoryRoom laboratoryRoom) {
		if (laboratoryRoom != null) {
			laboratoryRoom.setId(id);
		}
		try {
			this.laboratoryRoomService.remove(laboratoryRoom);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, LaboratoryRoom laboratoryRoom) {
		laboratoryRoom.setId(id);
		laboratoryRoom = this.laboratoryRoomService.modify(laboratoryRoom);
		return laboratoryRoom != null ? new ResponseInfomation(laboratoryRoom.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, LaboratoryRoomCondition laboratoryRoomCondition) {
		laboratoryRoomCondition.setSchoolId(user.getSchoolId());
		laboratoryRoomCondition.setIsDelete(false);
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
