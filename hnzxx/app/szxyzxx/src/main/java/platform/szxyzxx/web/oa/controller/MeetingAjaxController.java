package platform.szxyzxx.web.oa.controller;
 

 

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
 


import platform.education.oa.model.Meeting;
import platform.education.oa.service.MeetingService; 
import platform.education.oa.utils.UtilDate;
import platform.education.oa.vo.MeetingCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 会议控制类
 * 
 * @author sky
 * @version 1.0 2015-6-19
 */

@Controller
@RequestMapping(value = "/office/ajax/meeting")
public class MeetingAjaxController {
	private final static String BASE_PATH = "oa/meeting/";
	 @Autowired MeetingService meetingService;
	
	/**
	 * 未审批请假列表
	 * 
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sponsorList")
	public String list(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MeetingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date"); 
		condition.setCreateuserId(user.getId());
           List<Meeting> items = this.meetingService.findMeetingByCondition(condition, page, order);
            
		String path = "sponsor_list";
 	if ("list".equals(sub)) { 
			path = "sponsor_list_page";
		} 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
	
	
	
	@RequestMapping(value = "myList")
	public String myList(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MeetingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date"); 
		condition.setCreateuserId(user.getId());
           List<Meeting> items = this.meetingService.findMeetingToUser(user.getSchoolId(), user.getId(), page, order);
            
		String path = "my_list";
 	if ("list".equals(sub)) { 
			path = "my_list_page";
		} 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
	
	
	@RequestMapping(value = "summaryList")
	public String summaryList(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MeetingCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date"); 
		condition.setCreateuserId(user.getId());
           List<Meeting> items = this.meetingService.findMeetingToSummary(user.getSchoolId(), UtilDate.getDateFormatter(new Date(),"yyyy-MM-dd HH:mm:ss"), page, order);
            
		String path = "summary_list";
 	if ("list".equals(sub)) { 
			path = "summary_list_page";
		} 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
}
