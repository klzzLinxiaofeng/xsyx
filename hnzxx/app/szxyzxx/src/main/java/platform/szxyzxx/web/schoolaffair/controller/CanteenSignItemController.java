package platform.szxyzxx.web.schoolaffair.controller;
import java.util.List;

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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.school.affair.model.CanteenSignItem;
import platform.education.school.affair.service.CanteenSignItemService;
import platform.education.school.affair.vo.CanteenSignItemCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/schoolaffair/canteenSignItem")
public class CanteenSignItemController { 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenSignItem";
	
	@Autowired
	@Qualifier("canteenSignItemService")
	private CanteenSignItemService canteenSignItemService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") CanteenSignItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<CanteenSignItem> items = this.canteenSignItemService.findCanteenSignItemByCondition(condition, page, order);
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
	public List<CanteenSignItem> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenSignItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.canteenSignItemService.findCanteenSignItemByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	/**
	 * 编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		CanteenSignItem canteenSignItem = this.canteenSignItemService.findCanteenSignItemById(id);
		model.addAttribute("canteenSignItem", canteenSignItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	/**
	 * 详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		CanteenSignItem canteenSignItem = this.canteenSignItemService.findCanteenSignItemById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("canteenSignItem", canteenSignItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 删除
	 * @param id
	 * @param canteenSignItem
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, CanteenSignItem canteenSignItem) {
		if (canteenSignItem != null) {
			canteenSignItem.setId(id);
		}
		try {
			this.canteenSignItemService.remove(canteenSignItem);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, CanteenSignItem canteenSignItem) {
		canteenSignItem.setId(id);
		canteenSignItem = this.canteenSignItemService.modify(canteenSignItem);
		return canteenSignItem != null ? new ResponseInfomation(canteenSignItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
