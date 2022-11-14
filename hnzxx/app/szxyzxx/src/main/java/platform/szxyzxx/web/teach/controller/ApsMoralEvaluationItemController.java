package platform.szxyzxx.web.teach.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import platform.education.generalTeachingAffair.model.ApsMoralEvaluationItem;
import platform.education.generalTeachingAffair.service.ApsMoralEvaluationItemService;
import platform.education.generalTeachingAffair.vo.ApsMoralEvaluationItemCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/apsmoralevaluationitem")
public class ApsMoralEvaluationItemController { 
	
	private final static String viewBasePath = "/teach/apsmoralevaluationitem/";
	
	@Autowired
	@Qualifier("apsMoralEvaluationItemService")
	private ApsMoralEvaluationItemService apsMoralEvaluationItemService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ApsMoralEvaluationItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		//conditionFilter(user, condition);
		if(user != null) {
			condition.setIsDelete(false);
			condition.setSchoolId(user.getSchoolId());
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<ApsMoralEvaluationItem> items = this.apsMoralEvaluationItemService.findApsMoralEvaluationItemByCondition(condition, page, order);
			Integer type = condition.getType();
			String path = stuctureSubPaht(type);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list",path);
			} else {
				viewPath = structurePath("/index",path);
			}
			model.addAttribute("items", items);
			
			model.addAttribute("type", type);
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ApsMoralEvaluationItem> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ApsMoralEvaluationItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.apsMoralEvaluationItemService.findApsMoralEvaluationItemByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@RequestParam(value = "type", required = true) Integer type) {
		
		String path = stuctureSubPaht(type);
		return new ModelAndView(structurePath("/input",path));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ApsMoralEvaluationItem apsMoralEvaluationItem, @CurrentUser UserInfo user,@RequestParam(value = "publishTime", required = false) String publishTime ) {
		/*Integer groupId = apsMoralEvaluationItem.getGroupId();
		Integer appId = apsMoralEvaluationItem.getAppId();
		if(groupId == null) {
			apsMoralEvaluationItem.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			apsMoralEvaluationItem.setAppId(SysContants.SYSTEM_APP_ID);
		}*/
		if(user != null) {
			
			Date publishDate = null;
			try {
				if(publishTime != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					publishDate = format.parse(publishTime);
					apsMoralEvaluationItem.setPubishDate(publishDate);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			apsMoralEvaluationItem.setSchoolId(user.getSchoolId());
			
			apsMoralEvaluationItem = this.apsMoralEvaluationItemService.add(apsMoralEvaluationItem);
		}
		
		return apsMoralEvaluationItem != null ? new ResponseInfomation(apsMoralEvaluationItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ApsMoralEvaluationItem apsMoralEvaluationItem = this.apsMoralEvaluationItemService.findApsMoralEvaluationItemById(id);
		model.addAttribute("apsMoralEvaluationItem", apsMoralEvaluationItem);
		String path = stuctureSubPaht(apsMoralEvaluationItem.getType());
		
		return new ModelAndView(structurePath("/input",path), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ApsMoralEvaluationItem apsMoralEvaluationItem = this.apsMoralEvaluationItemService.findApsMoralEvaluationItemById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("apsMoralEvaluationItem", apsMoralEvaluationItem);
		
		String path = stuctureSubPaht(apsMoralEvaluationItem.getType());
		return new ModelAndView(structurePath("/viewer",path), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ApsMoralEvaluationItem apsMoralEvaluationItem) {
		if (apsMoralEvaluationItem != null) {
			apsMoralEvaluationItem.setId(id);
		}
		
		try {
			apsMoralEvaluationItem = this.apsMoralEvaluationItemService.findApsMoralEvaluationItemById(id);
			if(apsMoralEvaluationItem != null) {
				apsMoralEvaluationItem.setIsDelete(true);
				this.apsMoralEvaluationItemService.modify(apsMoralEvaluationItem);
			}
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ApsMoralEvaluationItem apsMoralEvaluationItem,@RequestParam(value = "publishTime", required = false) String publishTime ) {
		apsMoralEvaluationItem.setId(id);
		
		Date publishDate = null;
		try {
			if(publishTime != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				publishDate = format.parse(publishTime);
				apsMoralEvaluationItem.setPubishDate(publishDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		apsMoralEvaluationItem = this.apsMoralEvaluationItemService.modify(apsMoralEvaluationItem);
		
		return apsMoralEvaluationItem != null ? new ResponseInfomation(apsMoralEvaluationItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath,String path) {
		return viewBasePath + path + subPath;
	}

	private String stuctureSubPaht(Integer type) {
		String path = "";
		if(type == 1) {
			path = "course";
		}else if(type == 2){
			path = "activity";
		}else if(type == 3){
			path = "association";
		}else if(type == 4){
			path = "psychology";
		}
		
		return path;
	}
	
}
