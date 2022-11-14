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

import platform.education.oa.model.Usecard;
import platform.education.oa.model.Vehicle;
import platform.education.oa.vo.UsecardCondition;
import platform.education.oa.vo.VehicleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.VehicleStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/oa/usecard")
public class UsecardController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/usecard";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") UsecardCondition condition,
			@ModelAttribute("page") Page page,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setSchoolId(user.getSchoolId());
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		if(type==null || "".equals(type)){
			if(user.getTeacherId() != null){
				condition.setProposer(user.getTeacherId().toString());//当前登陆的人可以看到自己的
			}
		}
		List<Usecard> items = this.usecardService.findUsecardByCondition(condition, page, order);
		if(items.size() > 0){
			for(int i = 0; i< items.size();i++){
				Integer cardId = Integer.parseInt(items.get(i).getPlateNumber());
				if(cardId != null && !"".equals(cardId)){
					items.get(i).setPlateNumber( vehicleService.findVehicleById(cardId).getPlateNumber());
				}
				String useIDs = items.get(i).getCardUser();
				String teacherName = "";
				if(useIDs.length() > 0){
					String[] uid = useIDs.split(",");
					for(int j = 0;j < uid.length;j++){
						Integer id = Integer.parseInt(uid[j]);
						String tName = this.teacherService.findTeacherById(id).getName();
						teacherName += tName + ",";
					}
				}
				items.get(i).setCardUser(teacherName.substring(0, teacherName.length()-1));
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("type", type);
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Usecard> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") UsecardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.usecardService.findUsecardByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model, @CurrentUser UserInfo user) {
		VehicleCondition vehicle = new VehicleCondition();
		vehicle.setServiceCondition(VehicleStatus.audit);
		vehicle.setSchoolId(user.getSchoolId());
		vehicle.setIsDelete(false);
		List<Vehicle> list =  vehicleService.findVehicleByCondition(vehicle);
		model.addAttribute("vehicle",list);
		return new ModelAndView(structurePath("/input"),model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Usecard usecard, @CurrentUser UserInfo user,@RequestParam(value = "ids", required = false) String ids) {
		VehicleCondition vehicleCondition = new VehicleCondition();
		vehicleCondition.setPlateNumber(usecard.getPlateNumber());
		Vehicle cardId = vehicleService.findVehicleById(Integer.parseInt(usecard.getPlateNumber()));
		if(ids!=null && !"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
		}
		usecard.setSchoolId(user.getSchoolId());
		usecard.setCardUser(ids);
		usecard.setProposer(user.getTeacherId().toString());
		usecard.setStatus("0");
		usecard.setPlateNumber(cardId.getId().toString());//将车牌号设置成  车牌号  的唯一主键
		usecard = this.usecardService.add(usecard);
		return usecard != null ? new ResponseInfomation(usecard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,@CurrentUser UserInfo user, Model model) {
		Usecard usecard = this.usecardService.findUsecardById(id);
		if(usecard.getCardUser() != null && !"".equals(usecard.getCardUser())){
			String useIDs = usecard.getCardUser();
			String teacherName = "";
			if(useIDs.length() > 0){
				String[] uid = useIDs.split(",");
				for(int j = 0;j < uid.length;j++){
					Integer useId = Integer.parseInt(uid[j]);
					String tName = this.teacherService.findTeacherById(useId).getName();
					teacherName += tName + ",";
				}
			}
			usecard.setCardUser(teacherName.substring(0, teacherName.length()-1));
		}
		VehicleCondition vehicle = new VehicleCondition();
		vehicle.setSchoolId(user.getSchoolId());
		vehicle.setIsDelete(false);
		List<Vehicle> list =  vehicleService.findVehicleByCondition(vehicle);
		model.addAttribute("vehicle",list);
		model.addAttribute("usecard", usecard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model,@CurrentUser UserInfo user) {
		Usecard usecard = this.usecardService.findUsecardById(id);
		if(usecard.getCardUser() != null && !"".equals(usecard.getCardUser())){
			String useIDs = usecard.getCardUser();
			String teacherName = "";
			if(useIDs.length() > 0){
				String[] uid = useIDs.split(",");
				for(int j = 0;j < uid.length;j++){
					Integer useId = Integer.parseInt(uid[j]);
					String tName = this.teacherService.findTeacherById(useId).getName();
					teacherName += tName + ",";
				}
			}
			usecard.setCardUser(teacherName.substring(0, teacherName.length()-1));
		}
		VehicleCondition vehicle = new VehicleCondition();
		vehicle.setSchoolId(user.getSchoolId());
		vehicle.setIsDelete(false);
		List<Vehicle> list =  vehicleService.findVehicleByCondition(vehicle);
		model.addAttribute("vehicle",list);
		model.addAttribute("isCK", "disable");
		model.addAttribute("usecard", usecard);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Usecard usecard) {
		if (usecard != null) {
			usecard.setId(id);
		}
		try {
			this.usecardService.remove(usecard);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,@CurrentUser UserInfo user, 
			Usecard usecard,@RequestParam(value = "ids", required = false) String ids) {
		ids = ids.substring(0, ids.length()-1);
		usecard.setId(id);
		usecard.setCardUser(ids);
		
		VehicleCondition vehicle = new VehicleCondition();
		vehicle.setSchoolId(user.getSchoolId());
		vehicle.setIsDelete(false);
		List<Vehicle> list =  vehicleService.findVehicleByCondition(vehicle);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getServiceCondition().equals(VehicleStatus.using)){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		usecard = this.usecardService.modify(usecard);
		return usecard != null ? new ResponseInfomation(usecard.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/returnuse")
	public ModelAndView reandus() {
		return new ModelAndView("/oa/auditcard/returnanduse");
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, UsecardCondition condition) {
	}
}
