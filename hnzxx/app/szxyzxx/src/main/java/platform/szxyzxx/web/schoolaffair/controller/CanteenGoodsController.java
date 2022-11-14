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

import platform.education.school.affair.model.CanteenGoods;
import platform.education.school.affair.service.CanteenGoodsService;
import platform.education.school.affair.vo.CanteenGoodsCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/canteenGoods")
public class CanteenGoodsController { 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenGoods";
	
	@Autowired
	@Qualifier("canteenGoodsService")
	private CanteenGoodsService canteenGoodsService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") CanteenGoodsCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<CanteenGoods> canteenGoods = this.canteenGoodsService.findCanteenGoodsByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteenGoods", canteenGoods);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenGoods> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenGoodsCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		condition.setEnable(true);
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenGoodsService.findCanteenGoodsByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/getCanteenGoodsByCanteen", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenGoods> getCanteenGoodsByCanteen(@CurrentUser UserInfo user, @ModelAttribute("condition") CanteenGoodsCondition condition) {
		condition.setEnable(true);
		conditionFilter(user, condition);
		return canteenGoodsService.getCanteenGoodsByCanteen(condition);
	}
	
	/**
	 * 跳转到创建的页面
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	/**
	 * 添加商品
	 * @param user
	 * @param canteenGoods
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteenGoods(@CurrentUser UserInfo user,
			CanteenGoods canteenGoods) {
		canteenGoods.setSchoolId(user.getSchoolId());
		canteenGoods.setCreateDate(new Date());
		canteenGoods.setModifyDate(new Date());
		canteenGoods = this.canteenGoodsService.add(canteenGoods);
		return canteenGoods != null ? new ResponseInfomation(canteenGoods.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 编辑商品
	 * @param user
	 * @param id
	 * @param isCK
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifyCanteenGoods(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("canteenGoods", canteenGoods);
		return new ModelAndView(structurePath("/input"), model.asMap());

	}

	/**
	 * 检查编号是否重复
	 * @param user
	 * @param dxlx
	 * @param code
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		CanteenGoodsCondition goodsCondition = new CanteenGoodsCondition();
		goodsCondition.setSchoolId(user.getSchoolId());
		goodsCondition.setCode(code);
		goodsCondition.setIsDelete(false);
		if ("code".equals(dxlx)) {
			List<CanteenGoods> list = canteenGoodsService.findCanteenGoodsByCondition(goodsCondition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(CanteenGoods temp : list) {
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
	 * 检查名称是否重复
	 * @param user
	 * @param dxlx
	 * @param name
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/nameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean nameChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		CanteenGoodsCondition goodsCondition = new CanteenGoodsCondition();
		goodsCondition.setSchoolId(user.getSchoolId());
		goodsCondition.setName(name);
		goodsCondition.setIsDelete(false);
		if ("name".equals(dxlx)) {
			List<CanteenGoods> list = canteenGoodsService.findCanteenGoodsNameByCondition(goodsCondition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(CanteenGoods temp : list) {
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteClassroom(@PathVariable(value = "id") Integer id,
			CanteenGoods canteenGoods) {
		if (canteenGoods != null) {
			canteenGoods.setId(id);
		}
		return  this.canteenGoodsService.moveTo(canteenGoods);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, CanteenGoods canteenGoods) {
		canteenGoods.setId(id);
		canteenGoods = this.canteenGoodsService.modify(canteenGoods);
		return canteenGoods != null ? new ResponseInfomation(canteenGoods.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, CanteenGoodsCondition goodsCondition) {
		Integer schoolId = goodsCondition.getSchoolId();
		Boolean isDeleted = goodsCondition.getIsDelete();
		goodsCondition.setIsDelete(isDeleted != null ? isDeleted : false);
		goodsCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
	

}
