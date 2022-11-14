package platform.szxyzxx.web.teach.controller;
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

import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/moralEvaluationResult")
public class MoralEvaluationResultController extends BaseController { 
	
	private final static String viewBasePath = "/teach/moralEvaluationResult";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MoralEvaluationResultCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<MoralEvaluationResult> items = this.moralEvaluationResultService.findMoralEvaluationResultByCondition(condition, page, order);
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
	public List<MoralEvaluationResult> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MoralEvaluationResultCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.moralEvaluationResultService.findMoralEvaluationResultByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(MoralEvaluationResult moralEvaluationResult, @CurrentUser UserInfo user) {
		/*Integer groupId = moralEvaluationResult.getGroupId();
		Integer appId = moralEvaluationResult.getAppId();
		if(groupId == null) {
			moralEvaluationResult.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			moralEvaluationResult.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		moralEvaluationResult = this.moralEvaluationResultService.add(moralEvaluationResult);
		return moralEvaluationResult != null ? new ResponseInfomation(moralEvaluationResult.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MoralEvaluationResult moralEvaluationResult = this.moralEvaluationResultService.findMoralEvaluationResultById(id);
		model.addAttribute("moralEvaluationResult", moralEvaluationResult);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		MoralEvaluationResult moralEvaluationResult = this.moralEvaluationResultService.findMoralEvaluationResultById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("moralEvaluationResult", moralEvaluationResult);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, MoralEvaluationResult moralEvaluationResult) {
		if (moralEvaluationResult != null) {
			moralEvaluationResult.setId(id);
		}
		try {
			this.moralEvaluationResultService.remove(moralEvaluationResult);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, MoralEvaluationResult moralEvaluationResult) {
		moralEvaluationResult.setId(id);
		moralEvaluationResult = this.moralEvaluationResultService.modify(moralEvaluationResult);
		return moralEvaluationResult != null ? new ResponseInfomation(moralEvaluationResult.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, MoralEvaluationResultCondition condition) {
		/*Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
	}
}
