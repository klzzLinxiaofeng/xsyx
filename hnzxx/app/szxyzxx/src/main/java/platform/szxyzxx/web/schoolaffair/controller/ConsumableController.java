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

import platform.education.school.affair.model.Consumable;
import platform.education.school.affair.vo.ConsumableCondition;
import platform.education.school.affair.vo.ConsumableVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/consumable")
public class ConsumableController extends BaseController { 
	
	private final static String viewBasePath = "/schoolaffair/laboratory/consumable";
	
	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ConsumableCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<ConsumableVo> items = this.consumableService.findConsumableByCondition(condition, page, order);
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
	public List<ConsumableVo> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ConsumableCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.consumableService.findConsumableByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Consumable consumable, @CurrentUser UserInfo user) {
//		Integer groupId = consumable.getGroupId();
//		Integer appId = consumable.getAppId();
//		if(groupId == null) {
//			consumable.setGroupId(user.getGroupId());
//		}
//		if(appId == null) {
//			consumable.setAppId(SysContants.SYSTEM_APP_ID);
//		}
		consumable.setSchoolId(user.getSchoolId());
		if(consumable.getProduceDate() == null){
			consumable.setProduceDate(new Date());
		}
		consumable = this.consumableService.add(consumable);
		return consumable != null ? new ResponseInfomation(consumable.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Consumable consumable = this.consumableService.findConsumableById(id);
		model.addAttribute("consumable", consumable);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Consumable consumable = this.consumableService.findConsumableById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("consumable", consumable);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Consumable consumable) {
		if (consumable != null) {
			consumable.setId(id);
		}
		try {
			this.consumableService.remove(consumable);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Consumable consumable) {
		consumable.setId(id);
		consumable = this.consumableService.modify(consumable);
		return consumable != null ? new ResponseInfomation(consumable.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ConsumableCondition condition) {
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
