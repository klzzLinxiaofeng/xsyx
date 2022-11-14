<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
<#assign classNameUpper = className?upper_case>
<#assign classNameAllLower = className?lower_case> 
<#assign sqlName = table.sqlName>   
<#assign sqlNameLower = sqlName?uncap_first>   
<#assign sqlNameUpper = sqlName?upper_case>
package ${basepackage}.web.${subpackage}.controller;
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

import platform.education.${subpackage}.model.${className};
import platform.education.${subpackage}.service.${className}Service;
import platform.education.${subpackage}.vo.${className}Condition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/${subpackage}/${classNameAllLower}")
public class ${className}Controller { 
	
	private final static String viewBasePath = "/${subpackage}/${classNameAllLower}";
	
	@Autowired
	@Qualifier("${classNameLower}Service")
	private ${className}Service ${classNameLower}Service;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ${className}Condition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<${className}> items = this.${classNameLower}Service.find${className}ByCondition(condition, page, order);
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
	public List<${className}> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ${className}Condition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.${classNameLower}Service.find${className}ByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(${className} ${classNameLower}, @CurrentUser UserInfo user) {
		Integer groupId = ${classNameLower}.getGroupId();
		Integer appId = ${classNameLower}.getAppId();
		if(groupId == null) {
			${classNameLower}.setGroupId(user.getGroupId());
		}
		if(appId == null) {
			${classNameLower}.setAppId(SysContants.SYSTEM_APP_ID);
		}
		${classNameLower} = this.${classNameLower}Service.add(${classNameLower});
		return ${classNameLower} != null ? new ResponseInfomation(${classNameLower}.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		${className} ${classNameLower} = this.${classNameLower}Service.find${className}ById(id);
		model.addAttribute("${classNameLower}", ${classNameLower});
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		${className} ${classNameLower} = this.${classNameLower}Service.find${className}ById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("${classNameLower}", ${classNameLower});
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ${className} ${classNameLower}) {
		if (${classNameLower} != null) {
			${classNameLower}.setId(id);
		}
		try {
			this.${classNameLower}Service.remove(${classNameLower});
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ${className} ${classNameLower}) {
		${classNameLower}.setId(id);
		${classNameLower} = this.${classNameLower}Service.modify(${classNameLower});
		return ${classNameLower} != null ? new ResponseInfomation(${classNameLower}.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ${className}Condition condition) {
		Integer groupId = condition.getGroupId();
		Integer appId = condition.getAppId();
		if(user != null && groupId == null) {
			condition.setGroupId(user.getGroupId());
		}
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
}
