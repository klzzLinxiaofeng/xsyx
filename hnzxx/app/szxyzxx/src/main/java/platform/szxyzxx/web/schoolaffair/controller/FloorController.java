package platform.szxyzxx.web.schoolaffair.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import platform.education.school.affair.vo.FloorCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

@Controller
@RequestMapping(value = "/schoolaffair/floor")
public class FloorController extends BaseController {

	private final static String viewBasePath = "/schoolaffair/logistics/floor";

	/**
	 * 楼层列表
	 * 
	 * @param dm
	 * @param sub
	 * @param xwHqFloorCondition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView getFloorList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("xwHqFloorCondition") FloorCondition floorCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		conditionFilter(user, floorCondition);
		List<Floor> floorList = floorService.findFloorByCondition(
				floorCondition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/floorList");
		}
		model.addAttribute("floorList", floorList);
		return new ModelAndView(viewPath, model.asMap());
	}

	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Floor> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") FloorCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		condition.setState("1");
		return this.floorService.findFloorByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/list/json2", method = RequestMethod.GET)
	@ResponseBody
	public List<Floor> jsonList2(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") FloorCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.floorService.findFloorByCondition(condition, page, order);
	}
		
	/**
	 * 修改楼层
	 * 
	 * @param id
	 * @param isCK
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView modifyFloor(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		Floor floor = floorService.findFloorById(id);
		if (isCK.equals("disable")) {
			model.addAttribute("isCK", isCK);
		}
		model.addAttribute("floor", floor);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * 更新楼层
	 * 
	 * @param floor
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateFloor(
			@PathVariable(value = "id") Integer id, Floor floor) {
		floor.setId(id);
		floor.setModifyDate(new Date());
		floor = this.floorService.modify(floor);
		return floor != null ? new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 删除楼层
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteFloor(@PathVariable(value = "id") Integer id,
			Floor floor) {
		if (floor != null) {
			floor.setId(id);
		}
		return this.floorService.abandon(floor);
	}

	/**
	 * 新增楼层页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addFloorPage() {
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 保存楼层
	 * 
	 * @param floor
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addFloor(@CurrentUser UserInfo user,
			Floor floor) {
		floor.setSchoolId(user.getSchoolId());
		floor.setCreateDate(new Date());
		floor.setModifyDate(new Date());
		floor = this.floorService.add(floor);
		return floor != null ? new ResponseInfomation(floor.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setCode(code);
		condition.setIsDelete(false);
		if ("code".equals(dxlx)) {
			List<Floor> list = floorService.findFloorByCondition(condition, null, null);
			if(list != null && list.size()>0) {
				Integer currentId;
				for(Floor tem : list) {
					currentId = tem.getId();
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
	
	@RequestMapping(value = "nameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean nameChecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setName(name);
		condition.setIsDelete(false);
		if ("name".equals(dxlx)) {
			List<Floor> list = floorService.findFloorNameByCondition(condition);
			if(list != null && list.size()>0) {
				Integer currentId;
				for(Floor tem : list) {
					currentId = tem.getId();
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
	 * 查询楼的层数
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findlayernumber", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation storey(@CurrentUser UserInfo user, @RequestParam("code") String code){
		Floor floor = this.floorService.findFloorByCode(user.getSchoolId(), code);
		return floor != null ? new ResponseInfomation(floor.getLayerNumber(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/downLoadFloor")
	public void downLoadFloor(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "name", required = false) String name,
			@ModelAttribute("floor") Floor floor,
			@CurrentUser UserInfo user) {
		ParseConfig config = SzxyExcelTookit.getConfig("downLoadFloor");
		FloorCondition condition = new FloorCondition();
		conditionFilter(user, condition);
//		try {
//			request.setCharacterEncoding("utf-8");
//			name = new String((request.getParameter("name")).getBytes("iso-8859-1"), "utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		condition.setName(name);
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		List<Object> list = new ArrayList<Object>();
		String[] titles = {"楼层名称", "编号", "用途", "楼层数", "状态", "创建时间"};
		config.setTitles(titles);
		config.setSheetTitle("楼层信息表");
		String fileName = "楼层信息表.xls";
		try {
			for(Floor temp : floorList) {
				list.add(temp);
			}
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo userInfo, FloorCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : userInfo.getSchoolId());
	}
}
