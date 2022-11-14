package platform.szxyzxx.web.micro.controller;
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

import platform.education.micro.model.MicroCatalog;
import platform.education.micro.service.MicroCatalogService;
import platform.education.micro.vo.MicroCatalogCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/wkx/microcatalog")
public class MicroCatalogController { 
	
	private final static String viewBasePath = "wkx/catalog";
	
	@Autowired
	@Qualifier("microCatalogService")
	private MicroCatalogService microCatalogService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MicroCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<MicroCatalog> items = this.microCatalogService.findMicroCatalogByCondition(condition, page, order);
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
	public List<MicroCatalog> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MicroCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.microCatalogService.findMicroCatalogByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/catalog_input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(MicroCatalog microCatalog, @CurrentUser UserInfo user) {
		microCatalog = this.microCatalogService.add(microCatalog);
		return microCatalog != null ? new ResponseInfomation(microCatalog.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MicroCatalog microCatalog = this.microCatalogService.findMicroCatalogById(id);
		model.addAttribute("microCatalog", microCatalog);
		return new ModelAndView(structurePath("/catalog_input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer")
	public String viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		MicroCatalog microCatalog = this.microCatalogService.findMicroCatalogById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("microCatalog", microCatalog);
		return structurePath("/input");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, MicroCatalog microCatalog) {
		if (microCatalog != null) {
			microCatalog.setId(id);
		}
		try {
			this.microCatalogService.remove(microCatalog);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, MicroCatalog microCatalog) {
		microCatalog.setId(id);
		microCatalog = this.microCatalogService.modify(microCatalog);
		return microCatalog != null ? new ResponseInfomation(microCatalog.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
