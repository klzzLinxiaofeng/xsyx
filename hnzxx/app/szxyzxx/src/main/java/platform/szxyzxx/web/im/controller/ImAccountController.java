package platform.szxyzxx.web.im.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.im.exception.DbException;
import platform.education.im.model.ImAccount;
import platform.education.im.model.ImProvider;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.education.im.vo.ImAccountCondition;
import platform.education.im.vo.ImAccountVo;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/im/imAccount")
public class ImAccountController { 
	
	private final static String viewBasePath = "/im/imAccount";
	
	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("imProviderService")
	private ImProviderService imProviderService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ImAccountCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<ImAccount> iaList = this.imAccountService.findImAccountByCondition(condition, page, order);
		List<ImAccountVo> items = new ArrayList<ImAccountVo>();
		ImAccountVo vo = null;
		for(ImAccount ia : iaList){
			vo = new ImAccountVo();
			BeanUtils.copyProperties(ia, vo);
			AppEdition imApp = this.appEditionService.findByAppKey(ia.getAppKey());
			if(imApp != null){
				vo.setAppName(imApp.getName());
			}
			ImProvider ip = this.imProviderService.findByImType(ia.getImType());
			if(ip != null){
				vo.setImProviderName(ip.getName());
			}
			items.add(vo);
		}
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
	public List<ImAccount> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ImAccountCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.imAccountService.findImAccountByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ImAccount imAccount, @CurrentUser UserInfo user) {
		try {
			imAccount = this.imAccountService.add(imAccount);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return imAccount != null ? new ResponseInfomation(imAccount.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ImAccount imAccount = this.imAccountService.findImAccountById(id);
		model.addAttribute("imAccount", imAccount);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ImAccount imAccount = this.imAccountService.findImAccountById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("imAccount", imAccount);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ImAccount imAccount) {
		if (imAccount != null) {
			imAccount.setId(id);
		}
		try {
			this.imAccountService.remove(imAccount);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ImAccount imAccount) {
		imAccount.setId(id);
		imAccount = this.imAccountService.modify(imAccount);
		return imAccount != null ? new ResponseInfomation(imAccount.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ImAccountCondition condition) {
		condition.setIsDeleted(false);
	}
}
