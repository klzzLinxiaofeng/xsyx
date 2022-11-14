package platform.szxyzxx.web.teach.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.TextbookPublisher;
import platform.education.generalcode.vo.TextbookPublisherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/textBookMaster/textBookPublisher")
public class TextbookPublisherController  extends BaseController{ 
	private Logger log = LoggerFactory.getLogger(getClass());
	private final static String viewBasePath = "/teach/textBookMaster/textBookPublisher";
	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookPublisherCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "sort_order");
		ModelAndView mav = new ModelAndView();
		List<TextbookPublisher> items = this.jcTextbookPublisherService.findTextbookPublisherByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		mav.setViewName(viewPath);
		mav.addObject("items", items);
		return mav;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TextbookPublisher> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TextbookPublisherCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		page = usePage ? page : null;
		return this.jcTextbookPublisherService.findTextbookPublisherByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TextbookPublisher jcTextbookPublisher, @CurrentUser UserInfo user) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookPublisher jcTextbookPublisherEnd = null;
		try {
			jcTextbookPublisherEnd = this.jcTextbookPublisherService.add(jcTextbookPublisher);
			responseInfomation = jcTextbookPublisherEnd != null?responseInfomation:new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(getMessage("添加出版社错误：", e));
		}
		
		return responseInfomation;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		TextbookPublisher jcTextbookPublisher = this.jcTextbookPublisherService.findTextbookPublisherById(id);
		mav.addObject("jcTextbookPublisher", jcTextbookPublisher);
		mav.setViewName(structurePath("/input"));
		return mav;
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		TextbookPublisher jcTextbookPublisher = this.jcTextbookPublisherService.findTextbookPublisherById(id);
		mav.addObject("jcTextbookPublisher", jcTextbookPublisher);
		mav.addObject("isCK", "disable");
		mav.setViewName(structurePath("/input"));
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TextbookPublisher jcTextbookPublisher) {
		if (jcTextbookPublisher != null) {
			jcTextbookPublisher.setId(id);
		}
		try {
			this.jcTextbookPublisherService.remove(jcTextbookPublisher);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return getMessage("删除出版社出错：", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TextbookPublisher jcTextbookPublisher) {
		jcTextbookPublisher.setId(id);
		
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookPublisher jcTextbookPublisherEnd = null;
		try {
			jcTextbookPublisher = this.jcTextbookPublisherService.modify(jcTextbookPublisher);
			responseInfomation = jcTextbookPublisherEnd != null?responseInfomation:new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(getMessage("修改出版社错误：", e));
		}
		
		return responseInfomation;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	
	/**
	 * 错误信息
	 * @param e
	 * @return
	 */
	private String getMessage(String error,Exception e) {
		String message = e.getMessage().length() >=10 ?e.getMessage().substring(0, 10):e.getMessage();
		return error+":"+message;
	}
}
