package platform.szxyzxx.web.schoolaffair.controller;
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

import platform.education.school.affair.model.Room;
import platform.education.school.affair.service.RoomService;
import platform.education.school.affair.vo.RoomCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller("xwRoomController")
@RequestMapping("/schoolaffair/room")
public class RoomController { 
	
	private final static String viewBasePath = "/schoolaffair/room";
	
	@Autowired
	@Qualifier("xwRoomService")
	private RoomService xwRoomService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") RoomCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		if(user != null) {
			condition.setIsDeleted(false);
			condition.setSchoolId(user.getSchoolId());
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			List<Room> items = this.xwRoomService.findRoomByCondition(condition, page, order);
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("items", items);
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Room> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") RoomCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.xwRoomService.findRoomByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Room room, @CurrentUser UserInfo user) {
		if(user != null) {
			room.setSchoolId(user.getSchoolId());
			room = this.xwRoomService.add(room);
		}
		return room != null ? new ResponseInfomation(room.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Room room = this.xwRoomService.findRoomById(id);
		model.addAttribute("room", room);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Room room = this.xwRoomService.findRoomById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("room", room);
		return new ModelAndView(structurePath("/viwer"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Room room) {
		if (room != null) {
			room.setId(id);
		}
		try {
			room = this.xwRoomService.findRoomById(id);
			if(room != null) {
				room.setIsDeleted(true);
				this.xwRoomService.modify(room);
			}
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Room room) {
		room.setId(id);
		room = this.xwRoomService.modify(room);
		return room != null ? new ResponseInfomation(room.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
