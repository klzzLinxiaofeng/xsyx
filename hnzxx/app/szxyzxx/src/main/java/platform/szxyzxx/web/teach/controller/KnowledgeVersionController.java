package platform.szxyzxx.web.teach.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.KnowledgeVersion;
import platform.education.generalcode.service.KnowledgeVersionService;
import platform.education.generalcode.vo.KnowledgeVersionCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/knowledgeVersion")
public class KnowledgeVersionController { 
	
	private final static String viewBasePath = "/teach/knowledgeVersion";
	
	@Resource
	private KnowledgeVersionService knowledgeVersionService;
	
//	@Resource
//	private KnowledgeCatalogService knowledgeCatalogService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") KnowledgeVersionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<KnowledgeVersion> items = this.knowledgeVersionService.findKnowledgeVersionByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			model.addAttribute("items", items);
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeVersion> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") KnowledgeVersionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.knowledgeVersionService.findKnowledgeVersionByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(KnowledgeVersion KnowledgeVersion, @CurrentUser UserInfo user) {
		KnowledgeVersion = this.knowledgeVersionService.add(KnowledgeVersion);
		return KnowledgeVersion != null ? new ResponseInfomation(KnowledgeVersion.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		KnowledgeVersion knowledgeVersion = this.knowledgeVersionService.findKnowledgeVersionById(id);
		model.addAttribute("knowledgeVersion", knowledgeVersion);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	/**
	 * 跳转到管理知识点页面
	 * @param id
	 * @param model
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		KnowledgeVersion knowledgeVersion = this.knowledgeVersionService.findKnowledgeVersionById(id);
		model.addAttribute("knowledgeVersion", knowledgeVersion);
		model.addAttribute("id", id);
		return new ModelAndView(structurePath("/viewer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, KnowledgeVersion KnowledgeVersion) {
		if (KnowledgeVersion != null) {
			KnowledgeVersion.setId(id);
		}
		try {
			if(id != null && id != 0){
				KnowledgeVersion = knowledgeVersionService.findKnowledgeVersionById(id);
			}
			this.knowledgeVersionService.removeByGroup(KnowledgeVersion);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, KnowledgeVersion KnowledgeVersion) {
		KnowledgeVersion.setId(id);
		KnowledgeVersion = this.knowledgeVersionService.modify(KnowledgeVersion);
		return KnowledgeVersion != null ? new ResponseInfomation(KnowledgeVersion.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	/**
	 * 检查名称是否可用
	 * @param name
	 * @param opera
	 * @param id
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "opera", required = true) String opera,
			@RequestParam(value = "id", required = false) Integer id) {
		boolean isExist = true;
		try {
			name = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		KnowledgeVersionCondition condition = new KnowledgeVersionCondition();
		condition.setName(name);
		List<KnowledgeVersion> list = knowledgeVersionService.findKnowledgeVersionByCondition(condition);
		if("save".equals(opera)){
			if(list.size() > 0){
				isExist = false;
			}
		}else{
			KnowledgeVersion version = knowledgeVersionService.findKnowledgeVersionById(id);
			
			if(version.getName().equals(name)){
				isExist = true;
			}else{
				if(list.size() > 0){
					isExist = false;
				}
			}
		}
		return isExist;
	}
}
