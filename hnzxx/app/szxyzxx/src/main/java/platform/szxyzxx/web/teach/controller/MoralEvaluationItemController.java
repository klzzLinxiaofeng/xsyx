package platform.szxyzxx.web.teach.controller;
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

import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/moralEvaluationItem")
public class MoralEvaluationItemController extends BaseController { 
	
	private final static String viewBasePath = "/teach/moralEvaluationItem";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MoralEvaluationItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setIsDelete(false);
		List<MoralEvaluationItem> items = this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition, page, order);
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
	public List<MoralEvaluationItem> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MoralEvaluationItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(MoralEvaluationItem moralEvaluationItem, @CurrentUser UserInfo user) {
		moralEvaluationItem.setSchoolId(user.getSchoolId());
		moralEvaluationItem.setCreateDate(new Date());
		moralEvaluationItem.setModifyDate(new Date());
		moralEvaluationItem = this.moralEvaluationItemService.add(moralEvaluationItem);
		return moralEvaluationItem != null ? new ResponseInfomation(moralEvaluationItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MoralEvaluationItem moralEvaluationItem = this.moralEvaluationItemService.findMoralEvaluationItemById(id);
		model.addAttribute("moralEvaluationItem", moralEvaluationItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		MoralEvaluationItem moralEvaluationItem = this.moralEvaluationItemService.findMoralEvaluationItemById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("moralEvaluationItem", moralEvaluationItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, MoralEvaluationItem moralEvaluationItem) {
		if (moralEvaluationItem != null) {
			moralEvaluationItem.setId(id);
		}
		return this.moralEvaluationItemService.abandon(moralEvaluationItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, MoralEvaluationItem moralEvaluationItem) {
		moralEvaluationItem.setId(id);
		moralEvaluationItem = this.moralEvaluationItemService.modify(moralEvaluationItem);
		return moralEvaluationItem != null ? new ResponseInfomation(moralEvaluationItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "checker", method = RequestMethod.POST)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id", required = false) Integer id){
		boolean isExist = false;
		MoralEvaluationItemCondition condition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setName(name);
		condition.setIsDelete(false);
		if("name".equals(dxlx)) {
			List<MoralEvaluationItem> items = this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition);
			if(items != null && items.size() > 0) {
				Integer currentId;
				for(MoralEvaluationItem item : items) {
					currentId = item.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, MoralEvaluationItemCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				condition.setSchoolId(user.getSchoolId());
			}
		}
	}
}
