package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
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

import platform.education.school.affair.model.Floor;
import platform.education.school.affair.model.HealthClinic;
import platform.education.school.affair.service.HealthClinicService;
import platform.education.school.affair.vo.FloorCondition;
import platform.education.school.affair.vo.HealthClinicCondition;
import platform.education.school.affair.vo.HealthClinicVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/schoolaffair/healthClinic")
public class HealthClinicController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/health/clinic";
	
	@Autowired
	@Qualifier("healthClinicService")
	private HealthClinicService healthClinicService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") HealthClinicCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<HealthClinic> items = this.healthClinicService.findHealthClinicByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", toVos(items));
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<HealthClinic> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") HealthClinicCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.healthClinicService.findHealthClinicByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		FloorCondition floorCondition = new FloorCondition();
		floorCondition.setSchoolId(user.getSchoolId());
		floorCondition.setIsDelete(false);
		List<Floor> floorList = this.floorService.findFloorByCondition(floorCondition);
		List<Floor> list = new ArrayList<Floor>();
		if(floorList.size() > 0) {
			for(Floor floor : floorList) {
				if("4".equals(floor.getState())) {
					
				}else{
					list.add(floor);
				}
			}
		}
		model.addAttribute("floorList", list);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(HealthClinic healthClinic, @CurrentUser UserInfo user) {
		healthClinic.setSchoolId(user.getSchoolId());
		healthClinic = this.healthClinicService.add(healthClinic);
		return healthClinic != null ? new ResponseInfomation(healthClinic.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, 
			@CurrentUser UserInfo user,
			Model model) {
		HealthClinic healthClinic = this.healthClinicService.findHealthClinicById(id);
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		model.addAttribute("healthClinic", healthClinic);
		model.addAttribute("floorList", floorList);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			Model model) {
		HealthClinic healthClinic = this.healthClinicService.findHealthClinicById(id);
		FloorCondition condition = new FloorCondition();
		condition.setSchoolId(user.getSchoolId());
		List<Floor> floorList = this.floorService.findFloorByCondition(condition);
		model.addAttribute("isCK", "disable");
		model.addAttribute("healthClinic", healthClinic);
		model.addAttribute("floorList", floorList);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, HealthClinic healthClinic) {
		if (healthClinic != null) {
			healthClinic.setId(id);
		}
		return this.healthClinicService.abandon(healthClinic);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, HealthClinic healthClinic) {
		healthClinic.setId(id);
		healthClinic = this.healthClinicService.modify(healthClinic);
		return healthClinic != null ? new ResponseInfomation(healthClinic.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "id") Integer id) {
		boolean isExist = false;
		HealthClinicCondition condition = new HealthClinicCondition();
		conditionFilter(user, condition);
		condition.setCode(code);
		if ("code".equals(dxlx)) {
			List<HealthClinic> list = healthClinicService.findHealthClinicByCondition(condition);
			if(list != null && list.size() > 0) {
				Integer currentId;
				for(HealthClinic tem : list) {
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
	
	@RequestMapping(value = "/findStorey")
	@ResponseBody
	public ResponseInfomation storey(@RequestParam(value = "id", required = true) Integer id){
		Floor floor = this.floorService.findFloorById(id);
		return floor != null ? new ResponseInfomation(floor.getLayerNumber(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
		
	}
	
	private List<HealthClinicVo> toVos(List<HealthClinic> healthClinicList) {
		List<HealthClinicVo> voList = new ArrayList<HealthClinicVo>();
		for(HealthClinic clinic : healthClinicList) {
			voList.add(toVo(clinic));
		}
		return voList;
	}
	
	private HealthClinicVo toVo(HealthClinic healthClinic) {
		HealthClinicVo vo = new HealthClinicVo();
		BeanUtils.copyProperties(healthClinic, vo);
		Floor floor = this.floorService.findFloorById(healthClinic.getFloorId());
		String floorName = "0";
		if(floor != null) {
			if(floor.getIsDelete() == true) {
				
			}else {
				floorName = floor.getName();
			}
		}
		vo.setFloorName(floorName);
		return vo;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, HealthClinicCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
