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
import platform.education.school.affair.model.CanteenTakeOrder;
import platform.education.school.affair.service.CanteenStoreService;
import platform.education.school.affair.service.CanteenTakeOrderService;
import platform.education.school.affair.vo.CanteenStoreCondition;
import platform.education.school.affair.vo.CanteenTakeOrderCondition;
import platform.education.school.affair.vo.CanteenTakeOrderVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/schoolaffair/canteenTakeOrder")
public class CanteenTakeOrderController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenTakeOrder";
	
	@Autowired
	@Qualifier("canteenTakeOrderService")
	private CanteenTakeOrderService canteenTakeOrderService;
	
	@Autowired
	@Qualifier("canteenStoreService")
	private CanteenStoreService canteenStoreService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") CanteenTakeOrderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<CanteenTakeOrder> canteenTakeOrderList = this.canteenTakeOrderService.findCanteenTakeOrderByCondition(condition, page, order);
		List<CanteenTakeOrderVo> canteenTakeOrderVoList = new ArrayList<CanteenTakeOrderVo>();
		CanteenTakeOrderVo vo = null;
		for(CanteenTakeOrder takeOrder:canteenTakeOrderList){
			vo = new CanteenTakeOrderVo();
			BeanUtils.copyProperties(takeOrder, vo);
			Canteen canteen = this.canteenService.findCanteenByCode(user.getSchoolId(), takeOrder.getCanteenCode());
			if(canteen!=null){
				//获得食堂的名称
				vo.setCanteenName(canteen.getName());
			}
			CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsByCode(user.getSchoolId(), takeOrder.getGoodsCode());
			if(canteenGoods!=null){
				//获得商品的名称 
				vo.setGoodsName(canteenGoods.getName());
			}
			//获得剩余数量 
			vo.setRemainNum(takeOrder.getStoreNum()-takeOrder.getTakeCount());
			canteenTakeOrderVoList.add(vo);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteenTakeOrderVoList", canteenTakeOrderVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenTakeOrder> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenTakeOrderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenTakeOrderService.findCanteenTakeOrderByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 添加签收 
	 * @param user
	 * @param canteenTakeOrder
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteenTakeOrder(@CurrentUser UserInfo user,
			@ModelAttribute("canteenTakeOrder") CanteenTakeOrder canteenTakeOrder){
		try {
			if(canteenTakeOrder!=null){
				canteenTakeOrder.setSchoolId(user.getSchoolId());
				canteenTakeOrder.setCreateDate(new Date());
				canteenTakeOrder.setModifyDate(new Date());
				canteenTakeOrder = this.canteenTakeOrderService.add(canteenTakeOrder);
				CanteenStoreCondition canteenStoreCondition = new CanteenStoreCondition();
				canteenStoreCondition.setCanteenCode(canteenTakeOrder.getCanteenCode());
				canteenStoreCondition.setGoodsCode(canteenTakeOrder.getGoodsCode());
				canteenStoreCondition.setIsDelete(false);
				canteenStoreCondition.setSchoolId(user.getSchoolId());
				List<CanteenStore> canteenStoreList = this.canteenStoreService.findCanteenStoreByCondition(canteenStoreCondition);
				if(canteenStoreList.size()!=1){
					return canteenTakeOrder!= null ? new ResponseInfomation(canteenTakeOrder.getId(),
							ResponseInfomation.OPERATION_FAIL) : new ResponseInfomation();
				}else{
					CanteenStore canteenStore = canteenStoreList.get(0);
					canteenStore.setStoreNum(canteenTakeOrder.getStoreNum()-canteenTakeOrder.getTakeCount());
					this.canteenStoreService.modify(canteenStore);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return canteenTakeOrder!= null ? new ResponseInfomation(canteenTakeOrder.getId(),
					ResponseInfomation.OPERATION_FAIL) : new ResponseInfomation();
		}
		return canteenTakeOrder!= null ? new ResponseInfomation(canteenTakeOrder.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, CanteenTakeOrder canteenTakeOrder) {
		if (canteenTakeOrder != null) {
			canteenTakeOrder.setId(id);
		}
		return  this.canteenTakeOrderService.moveTo(canteenTakeOrder);
	}

    /**
     * 利用ajax获得数据 
     * @param canteenCode
     * @param goodsCode
     * @return
     */
	@RequestMapping(value = "/getStoreNum")
	@ResponseBody
	public ResponseInfomation storey(@CurrentUser UserInfo user,@RequestParam("canteenCode") String canteenCode,@RequestParam("goodsCode") String goodsCode){
		CanteenStoreCondition canteenStoreCondition = new CanteenStoreCondition();
		canteenStoreCondition.setCanteenCode(canteenCode);
		canteenStoreCondition.setGoodsCode(goodsCode);
		canteenStoreCondition.setSchoolId(user.getSchoolId());
		canteenStoreCondition.setIsDelete(false);
		List<CanteenStore> canteenStoreList = this.canteenStoreService.findCanteenStoreByCondition(canteenStoreCondition);
		int sum = 0;
		for(int i=0;i<canteenStoreList.size();i++){
			sum += canteenStoreList.get(i).getStoreNum();
		}
		return sum>=0 ? new ResponseInfomation(sum,
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	/**
	 * 页面的remote校验
	 * @param user
	 * @param storeNum
	 * @param takeCount
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam("storeNum") Integer storeNum,
			@RequestParam("takeCount") Integer takeCount) {
		if(storeNum!=0 && takeCount>0){
			if(takeCount<=storeNum){
				return true;
			}
		}
		return false;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, CanteenTakeOrderCondition signTakeCondition) {
		Integer schoolId = signTakeCondition.getSchoolId();
		Boolean isDeleted = signTakeCondition.getIsDelete();
		signTakeCondition.setIsDelete(isDeleted != null ? isDeleted : false);
		signTakeCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
