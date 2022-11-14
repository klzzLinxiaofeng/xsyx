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
import platform.education.oa.model.Returncard;
import platform.education.oa.model.Usecard;
import platform.education.oa.vo.ReturncardCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.VehicleStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/oa/returncard")
public class ReturncardController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/returncard";
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ReturncardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setSchoolId(user.getSchoolId());
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Returncard> items = this.returncardService.findReturncardByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = "list";
		} else {
			viewPath = "index";
		}
		model.addAttribute("items", items);
		return new ModelAndView("/oa/usecard/return"+viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Returncard> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ReturncardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.returncardService.findReturncardByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	//创建一个还车信息
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Returncard returncard, @CurrentUser UserInfo user) {
		
		//在创建还车信息的时候先判断是否有该条还车信息存在，若存在测修改，不存在就添加
		ReturncardCondition returncardCondition = new ReturncardCondition();
		returncardCondition.setIsDelete(false);
		returncardCondition.setReturnStatus(VehicleStatus.returning);
		returncardCondition.setUsecardId(returncard.getUsecardId());
		returncardCondition.setSchoolId(user.getSchoolId());
		List<Returncard> list = this.returncardService.findReturncardByCondition(returncardCondition);
		
		returncard.setReturnDate(new Date());
		returncard.setReturnStatus(VehicleStatus.returning);
		returncard.setIsDelete(false);
		returncard.setSchoolId(user.getSchoolId());
		
		if(list.size() > 0){
			Integer id = list.get(0).getId();
			returncard.setId(id);
			returncard = this.returncardService.modify(returncard);
		}else{
			returncard = this.returncardService.add(returncard);
		}
		
		//审批前的操作
		Usecard usecard = new Usecard();
		usecard.setId(returncard.getUsecardId());
		usecard.setStatus(VehicleStatus.returning);
		this.usecardService.modify(usecard);
		
		return returncard != null ? new ResponseInfomation(returncard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Returncard returncard = this.returncardService.findReturncardById(id);
		model.addAttribute("returncard", returncard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Returncard returncard = this.returncardService.findReturncardById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("returncard", returncard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Returncard returncard) {
		if (returncard != null) {
			returncard.setId(id);
		}
		try {
			this.returncardService.remove(returncard);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Returncard returncard) {
		returncard.setId(id);
		returncard = this.returncardService.modify(returncard);
		return returncard != null ? new ResponseInfomation(returncard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ReturncardCondition condition) {
	}
}
