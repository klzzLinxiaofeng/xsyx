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

import platform.education.oa.model.Device;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.DeviceCondition;
import platform.education.oa.vo.DeviceVo;
import platform.education.school.affair.model.Canteen;
import platform.education.school.affair.model.Classroom;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.CanteenCondition;
import platform.education.school.affair.vo.ClassroomCondition;
import platform.education.school.affair.vo.FloorCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/oa/device")
public class DeviceController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/device";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") DeviceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<DeviceVo> items = this.deviceService.findDeviceAllByCondition(condition, page, order);
		if(items.size() > 0){
			for(int i=0;i<items.size();i++){
				if(items.get(i).getBlidingId()!=null && !"".equals(items.get(i).getBlidingId())){
					if(this.floorService.findFloorById(items.get(i).getBlidingId())!=null){
						items.get(i).setBlidingName(this.floorService.findFloorById(items.get(i).getBlidingId()).getName());
					}
				}
				if(items.get(i).getRoomId()!=null && !"".equals(items.get(i).getRoomId())){
					if(this.classroomService.findClassroomById(items.get(i).getRoomId())!=null){
						items.get(i).setRoomName(this.classroomService.findClassroomById(items.get(i).getRoomId()).getName());
					}
				}
			}
		}
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
	public List<Device> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") DeviceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.deviceService.findDeviceByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model, @CurrentUser UserInfo user) {
		FloorCondition floorCondition = new FloorCondition();
		floorCondition.setIsDelete(false);
		floorCondition.setSchoolId(user.getSchoolId());
		List<Floor> list = this.floorService.findFloorByCondition(floorCondition);
		model.addAttribute("floorList", list);
		return new ModelAndView(structurePath("/input"),model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Device device, @CurrentUser UserInfo user) {
		
		//验证编号重复的问题
		DeviceCondition deviceCondition = new DeviceCondition();
		deviceCondition.setCode(device.getCode());
		deviceCondition.setSchoolId(user.getSchoolId());
		deviceCondition.setIsDelete(false);
		List<Device> list = deviceService.findDeviceByCondition(deviceCondition);
		if(list.size() > 0){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		device.setSchoolId(user.getSchoolId());
		device.setUuid(UUIDUtils.getUUID());
		device = this.deviceService.add(device);
		return device != null ? new ResponseInfomation(device.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model,@CurrentUser UserInfo user) {
		Device device = this.deviceService.findDeviceById(id);
		FloorCondition floorCondition = new FloorCondition();
		floorCondition.setIsDelete(false);
		floorCondition.setSchoolId(user.getSchoolId());
		List<Floor> list = this.floorService.findFloorByCondition(floorCondition);
		ClassroomCondition classroomCondition = new ClassroomCondition();
		classroomCondition.setSchoolId(user.getSchoolId());
		classroomCondition.setIsDelete(false);
		List<Classroom> roomList = this.classroomService.findClassroomByCondition(classroomCondition);
		model.addAttribute("roomList", roomList);
		model.addAttribute("floorList", list);
		model.addAttribute("device", device);
		model.addAttribute("vie","vie");
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,@CurrentUser UserInfo user,
			Model model) {
		Device device = this.deviceService.findDeviceById(id);
		FloorCondition floorCondition = new FloorCondition();
		floorCondition.setIsDelete(false);
		floorCondition.setSchoolId(user.getSchoolId());
		List<Floor> list = this.floorService.findFloorByCondition(floorCondition);
		ClassroomCondition classroomCondition = new ClassroomCondition();
		classroomCondition.setSchoolId(user.getSchoolId());
		classroomCondition.setIsDelete(false);
		List<Classroom> roomList = this.classroomService.findClassroomByCondition(classroomCondition);
		model.addAttribute("roomList", roomList);
		model.addAttribute("floorList", list);
		model.addAttribute("isCK", "disable");
		model.addAttribute("device", device);
		model.addAttribute("vie","vie");
		return new ModelAndView(structurePath("/readInput"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Device device) {
		if (device != null) {
			device.setId(id);
			device.setIsDelete(true);
		}
		try {
			this.deviceService.modify(device);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Device device, @CurrentUser UserInfo user) {
		//验证编号重复的问题
		String code = deviceService.findDeviceById(id).getCode();
		DeviceCondition deviceCondition = new DeviceCondition();
		deviceCondition.setCode(device.getCode());
		deviceCondition.setSchoolId(user.getSchoolId());
		deviceCondition.setIsDelete(false);
		List<Device> list = deviceService.findDeviceByCondition(deviceCondition);
		if(list.size() > 0 && !code.equals(device.getCode())){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		device.setId(id);
		device = this.deviceService.modify(device);
		return device != null ? new ResponseInfomation(device.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, DeviceCondition condition) {
	}
	
	@RequestMapping(value = "/selectRoom")
	@ResponseBody
	public List<Classroom> selectRoom(@RequestParam(value = "blidingId", required = false)String blidingId,@CurrentUser UserInfo user) {
		ClassroomCondition classroomCondition = new ClassroomCondition();
		classroomCondition.setSchoolId(user.getSchoolId());
		if(blidingId != "" && blidingId != null){
			classroomCondition.setFloorId(Integer.parseInt(blidingId));
		}else{
			classroomCondition.setFloorId(0);
		}
		classroomCondition.setIsDelete(false);
		List<Classroom> list = this.classroomService.findClassroomByCondition(classroomCondition);
		return list;
	}
	
	@RequestMapping(value = "/checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "code",required=false) String code,
			@RequestParam(value = "id",required=false) Integer id) {
		boolean isExist = false;
		String codeNum = "";
		if(id != null && !"".equals(id)){
			codeNum = this.deviceService.findDeviceById(id).getCode();
		}
		DeviceCondition deviceCondition = new DeviceCondition();
		deviceCondition.setCode(code);
		deviceCondition.setIsDelete(false);
		deviceCondition.setSchoolId(user.getSchoolId());
		List<Device> list = this.deviceService.findDeviceByCondition(deviceCondition);
		if(list != null && list.size() > 0){
//			for(int i = 0; i < list.size(); i++){
//				if(list.get(i).getCode().equals(code) && !"".equals(code) && !code.equals(codeNum)){
//					isExist = true;
//				}
//			}
			Integer currentId;
			for(Device device : list) {
				currentId = device.getId();
				if(currentId != null && currentId.equals(id)) {
					isExist = true;
				}else {
					isExist = false;
				}
			}
		}else{
			isExist = true;
		}
		return isExist;
	}
	
	@RequestMapping(value = "/nameChecker", method = RequestMethod.POST)
	@ResponseBody
	public boolean nameNhecker(
			@CurrentUser UserInfo user,
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "id",required=false) Integer id) {
		boolean isExist = false;
//		String codeNum = "";
//		if(id != null && !"".equals(id)){
//			codeNum = this.deviceService.findDeviceById(id).getCode();
//		}
		DeviceCondition deviceCondition = new DeviceCondition();
		deviceCondition.setName(name);
		deviceCondition.setIsDelete(false);
		deviceCondition.setSchoolId(user.getSchoolId());
		List<Device> list = this.deviceService.findDeviceNameByCondition(deviceCondition);
		if(list != null && list.size() > 0){
			Integer currentId;
			for(Device device : list) {
				currentId = device.getId();
				if(currentId != null && currentId.equals(id)) {
					isExist = true;
				}else {
					isExist = false;
				}
			}
		}else{
			isExist = true;
		}
		return isExist;
	}
	
}
