package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.service.CanteenService;
import platform.education.school.affair.vo.CanteenCondition;
import platform.education.school.affair.vo.FloorCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 后勤管理
 * 食堂管理
 * 食堂管理
 * @author Administrator
 *
 */


@Controller
@RequestMapping("/schoolaffair/canteen")
public class CanteenController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/canteenManager/canteen";
	@Autowired
	@Qualifier("canteenService")
	private CanteenService canteenService;
	
	/**
	 * 食堂信息列表
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
			@ModelAttribute("condition") CanteenCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, condition);
		List<Canteen> canteens = this.canteenService.findCanteenByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("canteens",canteens);//把食堂信息绑定，在list.jsp中取出来
		return new ModelAndView(viewPath, model.asMap());
	}
	
	//查询数据
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Canteen> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CanteenCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.canteenService.findCanteenByCondition(condition, page, order);
	}
	
	
	/**
	 * 创建食堂
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addCanteenPage(@CurrentUser UserInfo user, Model model) {
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		List<Floor> list = new ArrayList<Floor>();
		if(floorList != null) {
			for(Floor floor : floorList) {
				//把未删除的大楼查询出来
				if("4".equals(floor.getState())) {
					
					}else {
						list.add(floor);
					}
				}
		}
		model.addAttribute("floorList", list);
		return new ModelAndView(structurePath("/input"));//抓到input界面
	}
	
	/**
	 * 创建食堂用户
	 * @param user
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addCanteen(@CurrentUser UserInfo user,
			Canteen canteen) {
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), canteen.getFloorCode());
		canteen.setFloorName(floor.getName());
		canteen.setSchoolId(user.getSchoolId());
		canteen.setCreateDate(new Date());
		canteen.setModifyDate(new Date());
		canteen = this.canteenService.add(canteen);
		return canteen != null ? new ResponseInfomation(canteen.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	/**
	 * 修改页面，编辑页面和详情页面整合在一起
	 * 
	 * @param id
	 * @param isCK
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifyCanteen(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Canteen canteen = this.canteenService.findCanteenById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		model.addAttribute("floorList", floorList);
		model.addAttribute("canteen", canteen);
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
		CanteenCondition condition = new CanteenCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setCode(code);
		condition.setIsDelete(false);
		if ("code".equals(dxlx)) {
			List<Canteen> list = canteenService.findCanteenByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Canteen temp : list) {
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
		CanteenCondition condition = new CanteenCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setName(name);
		condition.setIsDelete(false);
		if ("name".equals(dxlx)) {
			List<Canteen> list = canteenService.findCanteenNameByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(Canteen temp : list) {
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
	 * @param canteen
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCanteen(@PathVariable(value = "id") Integer id,
			Canteen canteen) {
		if (canteen != null) {
			canteen.setId(id);
		}
		return  this.canteenService.moveTo(canteen);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateCanteen(@CurrentUser UserInfo user,
			@PathVariable(value = "id") Integer id, Canteen canteen) {
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), canteen.getFloorCode());
		canteen.setFloorName(floor.getName());
		canteen.setModifyDate(new Date());
		canteen = this.canteenService.modify(canteen);
		return canteen != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	private void conditionFilter(UserInfo userInfo, CanteenCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}


}
