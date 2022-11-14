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

import platform.education.im.model.ImProvider;
import platform.education.im.model.PushObject;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.ImProviderCondition;
import platform.education.im.vo.PushObjectCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/im/imProvider")
public class ImProviderController { 
	
	private final static String viewBasePath = "/im/imProvider";
	
	@Autowired
	@Qualifier("imProviderService")
	private ImProviderService imProviderService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ImProviderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<ImProvider> items = this.imProviderService.findImProviderByCondition(condition, page, order);
		System.out.println(items.size());
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
	public List<ImProvider> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ImProviderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.imProviderService.findImProviderByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ImProvider imProvider, @CurrentUser UserInfo user) {
		imProvider = this.imProviderService.add(imProvider);
		return imProvider != null ? new ResponseInfomation(imProvider.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ImProvider imProvider = this.imProviderService.findImProviderById(id);
		model.addAttribute("imProvider", imProvider);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ImProvider imProvider = this.imProviderService.findImProviderById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("imProvider", imProvider);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ImProvider imProvider) {
		if (imProvider != null) {
			imProvider.setId(id);
		}
		try {
			this.imProviderService.remove(imProvider);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ImProvider imProvider) {
		imProvider.setId(id);
		imProvider = this.imProviderService.modify(imProvider);
		return imProvider != null ? new ResponseInfomation(imProvider.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ImProviderCondition condition) {
		condition.setIsDefault(true);
	}
	/**
	 * 默认按钮
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/defaultButton", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView defaultButton (
			@RequestParam(value = "id", required = true) Integer id, Model model){
		ImProviderCondition condition = null;
		List<ImProvider> items = this.imProviderService.findImProviderByCondition(condition);
		for(ImProvider ip : items){
			if(ip.getId()==id){
				ip.setIsDefault(true);
			}else{
				ip.setIsDefault(false);
			}
			imProviderService.update(ip);
		}
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/index"),model.asMap());
	}
	
	@RequestMapping("/check")
	@ResponseBody
	public boolean editChecker(@RequestParam(value="isDefault") Integer isDefault){
		if(isDefault.intValue() == 1){
			ImProvider ip = this.imProviderService.findDefaultProvider();
			if(ip != null){
				return false;
			}else{
				return true;
			}
		}
		if(isDefault.intValue() == 0){
			return true;
		}
		return false;
	}
	
}
