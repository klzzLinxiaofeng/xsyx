package platform.szxyzxx.web.im.controller;
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

import platform.education.im.model.AppAuthorization;
import platform.education.im.model.PushObject;
import platform.education.im.service.PushObjectService;
import platform.education.im.vo.AppAuthorizationCondition;
import platform.education.im.vo.PushObjectCondition;
import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.vo.CanteenCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/im/pushObject")
public class PushObjectController { 
	
	private final static String viewBasePath = "/im/pushObject";
	
	@Autowired
	@Qualifier("pushObjectService")
	private PushObjectService pushObjectService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PushObjectCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<PushObject> items = this.pushObjectService.findPushObjectByCondition(condition, page, order);
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
	public List<PushObject> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PushObjectCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.pushObjectService.findPushObjectByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			PushObject pushObject, @CurrentUser UserInfo user) {
		pushObject = this.pushObjectService.add(pushObject);
		return pushObject != null ? new ResponseInfomation(pushObject.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PushObject pushObject = this.pushObjectService.findPushObjectById(id);
		model.addAttribute("pushObject", pushObject);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		PushObject pushObject = this.pushObjectService.findPushObjectById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("pushObject", pushObject);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PushObject pushObject) {
		if (pushObject != null) {
			pushObject.setId(id);
		}
		try {
			this.pushObjectService.remove(pushObject);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PushObject pushObject) {
		pushObject.setId(id);
		pushObject = this.pushObjectService.modify(pushObject);
		return pushObject != null ? new ResponseInfomation(pushObject.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, PushObjectCondition condition) {
		condition.setIsDeleted(false);
	}
	/**
	 * 回显验证
	 * @param code
	 * @return
	 */
	@RequestMapping("/check")
	@ResponseBody
	public boolean editChecker(@RequestParam(value="code")String code){
		PushObjectCondition condition = new PushObjectCondition();
		condition.setCode(code);
		List<PushObject> aa = pushObjectService.findPushObjectByCondition(condition);
		if(aa != null && aa.size() > 0) {
			return false;
		}else{
			return true;
		}
	}
}
