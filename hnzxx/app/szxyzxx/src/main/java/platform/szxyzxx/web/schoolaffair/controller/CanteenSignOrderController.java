package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.model.CanteenGoods;
import platform.education.school.affair.model.CanteenSignItem;
import platform.education.school.affair.model.CanteenSignOrder;
import platform.education.school.affair.model.CanteenStore;
import platform.education.school.affair.model.CanteenSupply;
import platform.education.school.affair.service.CanteenSignItemService;
import platform.education.school.affair.service.CanteenSignOrderService;
import platform.education.school.affair.service.CanteenStoreService;
import platform.education.school.affair.vo.CanteenGoodsCondition;
import platform.education.school.affair.vo.CanteenSignItemCondition;
import platform.education.school.affair.vo.CanteenSignOrderCondition;
import platform.education.school.affair.vo.CanteenSignOrderVo;
import platform.education.school.affair.vo.CanteenStoreCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/canteenSignOrder")
public class CanteenSignOrderController extends BaseController{ 
	
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteenSignOrder";
	
	@Autowired
	@Qualifier("canteenSignOrderService")
	private CanteenSignOrderService canteenSignOrderService;
	
	@Autowired
	@Qualifier("canteenSignItemService")
	protected CanteenSignItemService canteenSignItemService;

	@Autowired
	@Qualifier("canteenStoreService")
	protected CanteenStoreService canteenStoreService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") CanteenSignOrderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<CanteenSignOrder> canteenSignOrderList = this.canteenSignOrderService.findCanteenSignOrderByCondition(condition, page, order);
		List<CanteenSignOrderVo> canteenSignOrderVoList = new ArrayList<CanteenSignOrderVo>();
		if(canteenSignOrderList.size()>0){
			CanteenSignOrderVo vo = null;
			CanteenSignItemCondition signItemCondition = null;
			for(CanteenSignOrder signOrder:canteenSignOrderList){
				vo = new CanteenSignOrderVo();
				BeanUtils.copyProperties(signOrder, vo);
				Canteen canteen = this.canteenService.findCanteenByCode(user.getSchoolId(), signOrder.getCanteenCode());
				if(canteen!=null){
					vo.setCanteenName(canteen.getName());
				}
				//获取总价格
				signItemCondition = new CanteenSignItemCondition();
				signItemCondition.setSchoolId(user.getSchoolId());
				signItemCondition.setOrderId(signOrder.getId());
				List<CanteenSignItem> canteenSignItemList = this.canteenSignItemService.findCanteenSignItemByCondition(signItemCondition);
				Double sum = 0.0 ;
				for(int i=0;i<canteenSignItemList.size();i++){
					CanteenSignItem canteenSignItem = canteenSignItemList.get(i);
					sum += (canteenSignItem.getPrice()*canteenSignItem.getTotalCount());
					vo.setTotalPrice(sum);
				}
				
				canteenSignOrderVoList.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteenSignOrderVoList", canteenSignOrderVoList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<CanteenSignOrder> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenSignOrderCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenSignOrderService.findCanteenSignOrderByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	
	/**
	 * 创建签收单分为签收单和签收明细 
	 * @param user
	 * @param canteenSignOrder
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteenSignOrder(@CurrentUser UserInfo user,
			@ModelAttribute("canteenSignOrder") CanteenSignOrder canteenSignOrder,
//			@RequestParam("canteenSignOrder") String canteenSignOrder,
			@RequestParam("goods") String goods ) {
		try {
			if(canteenSignOrder != null){
				if(canteenSignOrder.getId() != null && !"".equals(canteenSignOrder.getId())){
					CanteenSignItemCondition canteenSignItemCondition = new CanteenSignItemCondition();
					canteenSignItemCondition.setOrderId(canteenSignOrder.getId());
					List<CanteenSignItem> itemList = canteenSignItemService.findCanteenSignItemByCondition(canteenSignItemCondition);
					for(CanteenSignItem canteenSignItem : itemList){
						canteenSignItemService.remove(canteenSignItem);
					}
					canteenSignOrderService.modify(canteenSignOrder);
				}else{
					CanteenSupply canteenSupply = this.canteenSupplyService.findCanteenSupplyById(canteenSignOrder.getSupplyId());
					canteenSignOrder.setSchoolId(user.getSchoolId());
					canteenSignOrder.setSupplyName(canteenSupply.getName());
					canteenSignOrder.setModifyDate(new Date());
					canteenSignOrder.setCreateDate(new Date());
					canteenSignOrder = canteenSignOrderService.add(canteenSignOrder);
				}
				if(goods != null && !"".equals(goods)){
					JSONObject jsonObject = JSONObject.fromObject(goods);
					JSONArray jsonArray = jsonObject.getJSONArray("items");
					CanteenSignItem item = null;
					for(int i=0; i<jsonArray.size() ;i ++){
						JSONObject json = (JSONObject) jsonArray.get(i);
						String goodsCode = json.getString("goodsCode");
						Double price = Double.parseDouble((String) json.get("price"));
						Integer totalCount = Integer.parseInt(json.getString("totalCount"));
					    item = new CanteenSignItem();
						CanteenGoods canteenGoods = this.canteenGoodsService.findCanteenGoodsByCode(user.getSchoolId(), goodsCode);
						item.setSchoolId(user.getSchoolId());
						item.setOrderId(canteenSignOrder.getId());
						item.setGoodsCode(goodsCode);
						item.setGoodsName(canteenGoods.getName());
						item.setPrice(price);
						item.setTotalCount(totalCount);
						item.setIsDelete(false);
						item.setCreateDate(new Date());
						item.setModifyDate(new Date());
						canteenSignItemService.add(item);
						CanteenStoreCondition canteenStoreCondition = new CanteenStoreCondition();
						canteenStoreCondition.setSchoolId(user.getSchoolId());
						canteenStoreCondition.setCanteenCode(canteenSignOrder.getCanteenCode());
						canteenStoreCondition.setIsDelete(false);
						canteenStoreCondition.setGoodsCode(item.getGoodsCode());
						List<CanteenStore> storeList = this.canteenStoreService.findCanteenStoreByCondition(canteenStoreCondition);
						if(storeList.size()==0){
							CanteenStore canteenStore = new CanteenStore();
							canteenStore.setCanteenCode(canteenSignOrder.getCanteenCode());
							canteenStore.setGoodsCode(item.getGoodsCode());
							canteenStore.setSchoolId(user.getSchoolId());
							canteenStore.setStoreNum(item.getTotalCount());
							canteenStore.setCreateDate(new Date());
							this.canteenStoreService.add(canteenStore);
						}else{
							for(CanteenStore store:storeList){
								store.setStoreNum(store.getStoreNum()+item.getTotalCount());
								this.canteenStoreService.modify(store);
							}
						}
					}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return canteenSignOrder!= null ? new ResponseInfomation(canteenSignOrder.getId(),
					ResponseInfomation.OPERATION_FAIL) : new ResponseInfomation();
		}
		return canteenSignOrder!= null ? new ResponseInfomation(canteenSignOrder.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Integer schoolId = user.getSchoolId();
		CanteenSignOrder canteenSignOrder = canteenSignOrderService.findCanteenSignOrderById(id);
		
		CanteenSignItemCondition canteenSignItemCondition = new CanteenSignItemCondition();
		canteenSignItemCondition.setOrderId(id);
		canteenSignItemCondition.setSchoolId(schoolId);
		List<CanteenSignItem> list = canteenSignItemService.findCanteenSignItemByCondition(canteenSignItemCondition);
		
		CanteenGoodsCondition canteenGoodsCondition = new CanteenGoodsCondition();
		canteenGoodsCondition.setIsDelete(false);
		canteenGoodsCondition.setEnable(true);
		canteenGoodsCondition.setSchoolId(schoolId);
		List<CanteenGoods> goodsList = canteenGoodsService.findCanteenGoodsByCondition(canteenGoodsCondition);
		
		model.addAttribute("canteenSignOrder", canteenSignOrder);
		model.addAttribute("list", list);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("isCK", isCK);
		return new ModelAndView(structurePath("/edit"), model.asMap());
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCanteenSignOrder(@PathVariable(value = "id") Integer id,
			CanteenSignOrder canteenSignOrder) {
		if (canteenSignOrder != null) {
			canteenSignOrder.setId(id);
		}
		return  this.canteenSignOrderService.moveTo(canteenSignOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, CanteenSignOrder canteenSignOrder) {
		canteenSignOrder.setId(id);
		canteenSignOrder = this.canteenSignOrderService.modify(canteenSignOrder);
		return canteenSignOrder != null ? new ResponseInfomation(canteenSignOrder.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, CanteenSignOrderCondition signOrderCondition) {
		Integer schoolId = signOrderCondition.getSchoolId();
		Boolean isDeleted = signOrderCondition.getIsDelete();
		signOrderCondition.setIsDelete(isDeleted != null ? isDeleted : false);
		signOrderCondition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
