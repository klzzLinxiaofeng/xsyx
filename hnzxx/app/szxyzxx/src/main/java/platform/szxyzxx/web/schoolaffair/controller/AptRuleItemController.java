package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.AptRule;
import platform.education.school.affair.model.AptRuleItem;
import platform.education.school.affair.service.AptRuleItemService;
import platform.education.school.affair.service.AptRuleService;
import platform.education.school.affair.vo.AptRuleCondition;
import platform.education.school.affair.vo.AptRuleItemCondition;
import platform.education.school.affair.vo.AptRuleItemVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolaffair.contants.AptContans;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



/**
 * 考核项目
 * @author 陈业强
 *
 */
@Controller
@RequestMapping("/schoolAffair/aptRuleItem")
public class AptRuleItemController { 
	
	private final static String viewBasePath = "/schoolaffair/aptRuleItem";
	
	@Resource
	private AptRuleItemService aptRuleItemService;
	
	@Resource
	private AptRuleService aptRuleService;
	/**
	 * 考核项目列表
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptRuleItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
		if(user.getSchoolId() != null){
			condition.setSchoolId(user.getSchoolId());
		}
		List<AptRuleItemVo> items = this.aptRuleItemService.findAptRuleItemByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		//考核标准项目下拉初始化
		AptRuleCondition aptRuleCondition = new AptRuleCondition();
		conditionFilter(user, aptRuleCondition);
		List<AptRule> arList = aptRuleService.findAptRuleByCondition(aptRuleCondition);
		model.addAttribute("items", items);
		model.addAttribute("arList", arList);
		return new ModelAndView(viewPath, model.asMap());
	}
	/**
	 * 加载创建考核项目对话框
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		//考核标准项目下拉初始化
		AptRuleCondition aptRuleCondition = new AptRuleCondition();
		conditionFilter(user, aptRuleCondition);
		List<AptRule> arList = aptRuleService.findAptRuleByCondition(aptRuleCondition);
		model.addAttribute("arList", arList);
		return new ModelAndView(structurePath("/input"));
	}
	/**
	 * 加载标准管理对话框
	 * @return
	 */
	@RequestMapping(value = "/ruleCreator", method = RequestMethod.GET)
	public ModelAndView ruleCreator() {
		return new ModelAndView(structurePath("/add_item"));
	}
	/**
	 * 创建考核项目评分细则
	 * @param aptRuleItem
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(@ModelAttribute("aptRuleItem")AptRuleItem aptRuleItem, @CurrentUser UserInfo user) {
		aptRuleItem.setCheckType(AptContans.DAILY);
		aptRuleItem = this.aptRuleItemService.add(aptRuleItem);
		return aptRuleItem != null ? new ResponseInfomation(aptRuleItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 创建标准管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ruleCreator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation ruleCreator(@ModelAttribute("aptRule")AptRule aptRule, @CurrentUser UserInfo user) {
		aptRule.setSchoolId(user.getSchoolId());
		aptRule.setCreateUserId(user.getId());
		aptRule = this.aptRuleService.add(aptRule);
		return aptRule != null ? new ResponseInfomation(aptRule.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 考评标准名称检测唯一性
	 * @param user
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "name") String name){
		boolean isExist = false;
		try {
			name = URLDecoder.decode(name,"utf8");
			AptRuleCondition condition = new AptRuleCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setName(name);
			List<AptRule> list = aptRuleService.findAptRuleByCondition(condition);
			if(list != null && list.size() > 0) {
				isExist = false;
			}else{
				isExist = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	/**
	 * 考评项目评分细则名称检测唯一性
	 * @param user
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/itemChecker", method = RequestMethod.GET)
	@ResponseBody
	public boolean itemChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "name") String name){
		boolean isExist = false;
		try {
			name = URLDecoder.decode(name,"utf8");
			AptRuleItemCondition condition = new AptRuleItemCondition();
			condition.setName(name);
			List<AptRuleItemVo> list = aptRuleItemService.findAptRuleItemByCondition(condition);
			if(list != null && list.size() > 0) {
				isExist = false;
			}else{
				isExist = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	/**
	 * 编辑考核项目评分细则
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AptRuleItem aptRuleItem = this.aptRuleItemService.findAptRuleItemById(id);
		model.addAttribute("aptRuleItem", aptRuleItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	/**
	 * 查看考核项目评分细则
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		AptRuleItem aptRuleItem = this.aptRuleItemService.findAptRuleItemById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("aptRuleItem", aptRuleItem);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	/**
	 * 删除考核项目评分细则
	 * @param id
	 * @param aptRuleItem
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AptRuleItem aptRuleItem) {
		if (aptRuleItem != null) {
			aptRuleItem.setId(id);
		}
		try {
			this.aptRuleItemService.remove(aptRuleItem);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	/**
	 * 保存考核项目评分细则修改
	 * @param id
	 * @param aptRuleItem
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AptRuleItem aptRuleItem) {
		aptRuleItem.setId(id);
		aptRuleItem = this.aptRuleItemService.modify(aptRuleItem);
		return aptRuleItem != null ? new ResponseInfomation(aptRuleItem.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AptRuleCondition condition) {
		Integer schoolId = user.getSchoolId();
		if(schoolId != null){
			condition.setSchoolId(schoolId);
		}
	}
}
