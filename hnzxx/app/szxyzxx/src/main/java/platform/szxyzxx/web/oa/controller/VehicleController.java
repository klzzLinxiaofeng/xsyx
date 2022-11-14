package platform.szxyzxx.web.oa.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.oa.model.Vehicle;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.VehicleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/oa/vehicle")
public class VehicleController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/vehicle";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") VehicleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<Vehicle> items = this.vehicleService.findVehicleByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Vehicle> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") VehicleCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.vehicleService.findVehicleByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Vehicle vehicle, @CurrentUser UserInfo user) {
		Vehicle veh = this.vehicleService.findVehicleByplateNumber(vehicle.getPlateNumber());
		if(veh != null){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		vehicle.setSchoolId(user.getSchoolId());
		vehicle.setUuid(UUIDUtils.getUUID());
		vehicle = this.vehicleService.add(vehicle);
		return vehicle != null ? new ResponseInfomation(vehicle.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Vehicle vehicle = this.vehicleService.findVehicleById(id);
		model.addAttribute("vehicle", vehicle);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Vehicle vehicle = this.vehicleService.findVehicleById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("vehicle", vehicle);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Vehicle vehicle) {
		if (vehicle != null) {
			vehicle.setId(id);
			vehicle.setIsDelete(true);
		}
		try {
			this.vehicleService.modify(vehicle);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Vehicle vehicle) {
		vehicle.setId(id);
		String num = vehicleService.findVehicleById(id).getPlateNumber();
		VehicleCondition vehicleCondition = new VehicleCondition();
		vehicleCondition.setPlateNumber(vehicle.getPlateNumber());
		vehicleCondition.setIsDelete(false);
		List<Vehicle> list = this.vehicleService.findVehicleByCondition(vehicleCondition);
		if(list.size() > 0 && !num.equals(vehicle.getPlateNumber())){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		vehicle = this.vehicleService.modify(vehicle);
		return vehicle != null ? new ResponseInfomation(vehicle.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, VehicleCondition condition) {
	}
}
