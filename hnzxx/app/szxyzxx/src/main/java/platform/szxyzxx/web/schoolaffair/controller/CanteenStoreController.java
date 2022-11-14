package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.Date;
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
import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.model.CanteenGoods;
import platform.education.school.affair.model.CanteenStore;
import platform.education.school.affair.service.CanteenSignItemService;
import platform.education.school.affair.service.CanteenSignOrderService;
import platform.education.school.affair.service.CanteenStoreService;
import platform.education.school.affair.vo.CanteenStoreCondition;
import platform.education.school.affair.vo.CanteenStoreVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/schoolaffair/canteenStore")
public class CanteenStoreController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenStore";
	
	@Autowired
	@Qualifier("canteenStoreService")
	private CanteenStoreService canteenStoreService;
	
	@Autowired
	@Qualifier("canteenSignOrderService")
	private CanteenSignOrderService canteenSignOrderService;
	
	@Autowired
	@Qualifier("canteenSignItemService")
	private CanteenSignItemService canteenSignItemService;
	/**
	 * 显示列表
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
			@ModelAttribute("condition") CanteenStoreCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		//根据code获得名称，在页面上显示 
		List<CanteenStore> canteenStoreList = this.canteenStoreService.findCanteenStoreByCondition(condition, page, order);
		List<CanteenStoreVo> canteenStoreVoList = new ArrayList<CanteenStoreVo>();
		if(canteenStoreList.size()>0){
			CanteenStoreVo vo = null;
			for(CanteenStore store : canteenStoreList){
				vo = new CanteenStoreVo();
				BeanUtils.copyProperties(store, vo);
				Canteen canteen = this.canteenService.findCanteenByCode(user.getSchoolId(), store.getCanteenCode());
				CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsByCode(user.getSchoolId(), store.getGoodsCode());
				if(canteen!=null){
					vo.setCanteenName(canteen.getName());
				}
				if(canteenGoods!=null){
					vo.setGoodsName(canteenGoods.getName());
				}
				canteenStoreVoList.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteenStoreVoList", canteenStoreVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenStore> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenStoreCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenStoreService.findCanteenStoreByCondition(condition, page, order);
	}
	/**
	 * 控制页面跳转的
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteenStore(CanteenStore canteenStore, @CurrentUser UserInfo user) {
		CanteenStoreCondition canteenStoreCondition = new CanteenStoreCondition();
		canteenStoreCondition.setCanteenCode(canteenStore.getCanteenCode());
		canteenStoreCondition.setGoodsCode(canteenStore.getGoodsCode());
		canteenStoreCondition.setIsDelete(false);
		List<CanteenStore> storeList = this.canteenStoreService.findCanteenStoreByCondition(canteenStoreCondition);
		//添加库存如果有记录，就数量相加
		if(storeList.size()>=1){
			for(CanteenStore store : storeList){
				if(store.getCanteenCode().equals(canteenStore.getCanteenCode()) && store.getGoodsCode().equals(canteenStore.getGoodsCode())){
					int num = store.getStoreNum();
					store.setStoreNum(num+canteenStore.getStoreNum());
					this.canteenStoreService.modify(store);
				}
			}
		}else{
			//没有对应的记录，就添加一条记录 
			canteenStore.setSchoolId(user.getSchoolId());
			canteenStore.setCreateDate(new Date());
			canteenStore.setModifyDate(new Date());
			canteenStore.setIsDelete(false);
			canteenStore = this.canteenStoreService.add(canteenStore);
		}
		return canteenStore != null ? new ResponseInfomation(canteenStore.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 编辑页面 
	 * @param user
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		CanteenStore canteenStore = this.canteenStoreService.findCanteenStoreById(id);
		Canteen canteen = this.canteenService.findCanteenByCode(user.getSchoolId(), canteenStore.getCanteenCode());
		CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsByCode(user.getSchoolId(), canteenStore.getGoodsCode());
		model.addAttribute("canteenStore", canteenStore);
		model.addAttribute("canteen", canteen);
		model.addAttribute("canteenGoods", canteenGoods);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		CanteenStore canteenStore = this.canteenStoreService.findCanteenStoreById(id);
		Canteen canteen = this.canteenService.findCanteenByCode(user.getSchoolId(), canteenStore.getCanteenCode());
		CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsByCode(user.getSchoolId(), canteenStore.getGoodsCode());
		model.addAttribute("isCK", "disable");
		model.addAttribute("canteen", canteen);
		model.addAttribute("canteenGoods", canteenGoods);
		model.addAttribute("canteenStore", canteenStore);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCanteenStore(@PathVariable(value = "id") Integer id,
			CanteenStore canteenStore) {
		if (canteenStore != null) {
			canteenStore.setId(id);
		}
		return  this.canteenStoreService.moveTo(canteenStore);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, CanteenStore canteenStore) {
		canteenStore.setId(id);
		canteenStore.setModifyDate(new Date());
		canteenStore = this.canteenStoreService.modify(canteenStore);
		return canteenStore != null ? new ResponseInfomation(canteenStore.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, CanteenStoreCondition storeCondition) {
		Integer schoolId = storeCondition.getSchoolId();
		Boolean isDeleted = storeCondition.getIsDelete();
		storeCondition.setIsDelete(isDeleted != null ? isDeleted : false);
		storeCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
