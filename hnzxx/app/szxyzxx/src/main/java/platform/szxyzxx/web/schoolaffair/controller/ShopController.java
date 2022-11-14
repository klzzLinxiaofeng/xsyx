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

import platform.education.school.affair.model.Floor;
import platform.education.school.affair.model.Shop;
import platform.education.school.affair.service.ShopService;
import platform.education.school.affair.vo.FloorCondition;
import platform.education.school.affair.vo.ShopCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 后勤超市管理
 * @author Administrator
 *
 */


@Controller
@RequestMapping("/schoolaffair/shop")
public class ShopController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/shopManager/shop";
	
	@Autowired
	@Qualifier("shopService")
	private ShopService shopService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ShopCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setNameLike(true);
		List<Shop> shops = this.shopService.findShopByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("shops", shops);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Shop> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ShopCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.shopService.findShopByCondition(condition, page, order);
	}
	

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addShopPage(@CurrentUser UserInfo user, Model model) {
		List<Floor> floorList = this.floorService.findFloorByCondition(getFloorCondition(user));
		model.addAttribute("floorList", floorList);
		return new ModelAndView(structurePath("/input"));//抓到input界面
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addShop(@CurrentUser UserInfo user,
			@ModelAttribute("shop") Shop shop) {
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), shop.getFloorCode());
		if ( floor != null && floor.getName() != null ) {
			shop.setFloorName(floor.getName());
			shop.setSchoolId(user.getSchoolId());
			shop = this.shopService.add(shop);
		}
		return shop != null ? new ResponseInfomation(shop.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifyShop(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Shop shop = this.shopService.findShopById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		List<Floor> floorList = this.floorService.findFloorByCondition(getFloorCondition(user));
		model.addAttribute("floorList", floorList);
		model.addAttribute("shop", shop);
		return new ModelAndView(structurePath("/input"), model.asMap());

	}

	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		ShopCondition condition = new ShopCondition();
		conditionFilter(user, condition);
		condition.setCode(code);
		if ("code".equals(dxlx)) {
			List<Shop> list = shopService.findShopByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Shop temp : list) {
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
	
	@RequestMapping(value = "/nameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean nameChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		ShopCondition condition = new ShopCondition();
		conditionFilter(user, condition);
		condition.setName(name);
		if ("name".equals(dxlx)) {
			List<Shop> list = shopService.findShopByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Shop temp : list) {
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
	public String deleteShop(@PathVariable(value = "id") Integer id,
			Shop shop) {
		if (shop != null) {
			shop.setId(id);
		}
		return  this.shopService.moveTo(shop);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateShop(@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id, Shop shop) {
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), shop.getFloorCode());
		shop.setFloorName(floor.getName());
		shop.setModifyDate(new Date());
		shop = this.shopService.modify(shop);
		return shop != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, ShopCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
	
	private FloorCondition getFloorCondition(UserInfo userInfo) {
		FloorCondition condition = new FloorCondition(); 
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
		return condition;
	}
}

