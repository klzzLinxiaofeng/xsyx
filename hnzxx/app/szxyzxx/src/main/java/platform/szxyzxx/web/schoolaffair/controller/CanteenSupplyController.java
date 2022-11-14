package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.CanteenSupply;
import platform.education.school.affair.service.CanteenSupplyService;
import platform.education.school.affair.vo.CanteenSupplyCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/canteenSupply")
public class CanteenSupplyController { 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenSupply";
	
	@Autowired
	@Qualifier("canteenSupplyService")
	private CanteenSupplyService canteenSupplyService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") CanteenSupplyCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<CanteenSupply> canteenSupplys = this.canteenSupplyService.findCanteenSupplyByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteenSupplys", canteenSupplys );
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenSupply> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenSupplyCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenSupplyService.findCanteenSupplyByCondition(condition, page, order);
	}
	
	/**
	 * 点击创建时，跳转到input界面
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	/**
	 * 创建新的提供商
	 * @param user
	 * @param canteenSupply
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteenSupply(@CurrentUser UserInfo user,
			CanteenSupply canteenSupply) {
		canteenSupply.setSchoolId(user.getSchoolId());
		canteenSupply.setCreateDate(new Date());
		canteenSupply.setModifyDate(new Date());
		canteenSupply = this.canteenSupplyService.add(canteenSupply);
		return canteenSupply != null ? new ResponseInfomation(canteenSupply.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifySupply(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		CanteenSupply canteenSupply = this.canteenSupplyService.findCanteenSupplyById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("canteenSupply", canteenSupply);
		return new ModelAndView(structurePath("/input"), model.asMap());

	}
	
	@RequestMapping(value = "/nameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean nameChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		CanteenSupplyCondition supplyCondition = new CanteenSupplyCondition();
		supplyCondition.setSchoolId(user.getSchoolId());
		supplyCondition.setName(name);
		supplyCondition.setIsDelete(false);
		if ("name".equals(dxlx)) {
			List<CanteenSupply> list = canteenSupplyService.findCanteenSupplyNameByCondition(supplyCondition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(CanteenSupply temp : list) {
					currentId = temp.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
	
	@RequestMapping(value = "/telChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean telChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "telephone") String telephone,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		CanteenSupplyCondition supplyCondition = new CanteenSupplyCondition();
		supplyCondition.setSchoolId(user.getSchoolId());
		supplyCondition.setTelephone(telephone);
		supplyCondition.setIsDelete(false);
		if ("telephone".equals(dxlx)) {
			List<CanteenSupply> list = canteenSupplyService.findCanteenSupplyByCondition(supplyCondition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(CanteenSupply temp : list) {
					currentId = temp.getId();
					if(currentId != null && currentId.equals(id)) {
						isExist = true;
					}else {
						isExist = false;
					}
				}
			}else{
				isExist = true;
			}
		}
		return isExist;
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @param canteenSupply
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSupply(@PathVariable(value = "id") Integer id,
			CanteenSupply canteenSupply) {
		if (canteenSupply != null) {
			canteenSupply.setId(id);
		}
		return  this.canteenSupplyService.moveTo(canteenSupply);
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, CanteenSupply canteenSupply) {
		canteenSupply.setId(id);
		canteenSupply = this.canteenSupplyService.modify(canteenSupply);
		return canteenSupply != null ? new ResponseInfomation(canteenSupply.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	private void conditionFilter(UserInfo userInfo, CanteenSupplyCondition supplyCondition) {
		Integer schoolId = supplyCondition.getSchoolId();
		Boolean isDeleted = supplyCondition.getIsDelete();
		supplyCondition.setIsDelete(isDeleted != null ? isDeleted : false);
		supplyCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
