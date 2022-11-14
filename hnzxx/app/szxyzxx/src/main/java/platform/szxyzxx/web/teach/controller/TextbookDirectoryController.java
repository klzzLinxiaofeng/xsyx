package platform.szxyzxx.web.teach.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.TextbookDirectory;
import platform.education.generalcode.vo.TextbookDirectoryCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/textBookMaster/textBookDirectory")
public class TextbookDirectoryController  extends BaseController{ 
	private Logger log = LoggerFactory.getLogger(getClass());
	private final static String viewBasePath = "/teach/textBookMaster/textBookDirectory";

	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookDirectoryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<TextbookDirectory> items = this.jcTextbookDirectoryService.findTextbookDirectoryByCondition(condition, page, order);
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
	public List<TextbookDirectory> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TextbookDirectoryCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.jcTextbookDirectoryService.findTextbookDirectoryByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TextbookDirectory jcTextbookDirectory, @CurrentUser UserInfo user) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookDirectory jcTextbookDirectoryEnd = null;
		try {
			jcTextbookDirectoryEnd = this.jcTextbookDirectoryService.add(jcTextbookDirectory);
			responseInfomation = jcTextbookDirectoryEnd != null ? responseInfomation: new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(this.getMessage("添加教材分类目录出错：", e));
		}
		return responseInfomation;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		TextbookDirectory jcTextbookDirectory = this.jcTextbookDirectoryService.findTextbookDirectoryById(id);
		model.addAttribute("jcTextbookDirectory", jcTextbookDirectory);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		TextbookDirectory jcTextbookDirectory = this.jcTextbookDirectoryService.findTextbookDirectoryById(id);
		mav.addObject("isCK", "disable");
		mav.addObject("jcTextbookDirectory", jcTextbookDirectory);
		mav.setViewName(structurePath("/input"));
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TextbookDirectory jcTextbookDirectory) {
		if (jcTextbookDirectory != null) {
			jcTextbookDirectory.setId(id);
		}
		try {
			this.jcTextbookDirectoryService.remove(jcTextbookDirectory);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return this.getMessage("删除教材分类目录出错：", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TextbookDirectory jcTextbookDirectory) {
		
		jcTextbookDirectory.setId(id);
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		TextbookDirectory jcTextbookDirectoryEnd = null;
		try {
			jcTextbookDirectoryEnd = this.jcTextbookDirectoryService.modify(jcTextbookDirectory);
			responseInfomation = jcTextbookDirectoryEnd != null ? responseInfomation: new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			responseInfomation = new ResponseInfomation(this.getMessage("修改教材分类目录出错：", e));
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
