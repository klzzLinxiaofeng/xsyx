package platform.szxyzxx.web.oa.controller;

 
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import platform.education.oa.model.Leave;
import platform.education.oa.model.LeaveAppr;
import platform.education.oa.service.LeaveApprService;
import platform.education.oa.service.LeaveApprUserService;
import platform.education.oa.service.LeaveService;
import platform.education.oa.vo.LeaveCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
  
/**
 * 
 * 请假ajax请求
 * @author sky
 * @version 1.0 2014-4-17
 */
@Controller
@RequestMapping(value = "/office/ajax/appr")
public class ApprAjaxController  {
	private final static String BASE_PATH = "oa/appr/";
	
	@Autowired LeaveService leaveService;
	
	@Autowired LeaveApprUserService lauService;
	@Autowired LeaveApprService leaveApprService;
	/**
	 * 未审批请假列表
	 * 
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "leaveList")
	public String list(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") LeaveCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date"); 
           List<Leave> items = this.leaveService.findLeaveByLeaveAndApprToUser(0,user.getSchoolId(), user.getId(), page, order);
 
		String path = "leave_list";
 	if ("list".equals(sub)) { 
			path = "list";
		} 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
	
	
	/**
	 * 已审批请假列表
	 * 
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "apprLeaveList")
	public String appr_list(@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") LeaveCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
 
		List<Leave> items = this.leaveService.findLeaveByLeaveAndApprToUser(1,user.getSchoolId(), user.getId(), page, order);
		 
		if(items !=null&&!items.isEmpty()){
			for(Leave leave:items){
			    LeaveAppr appr=this.leaveApprService.findLeaveApprByLeaveId(leave.getId());
			 
	            leave.setApprs(appr);
			}
		}
	
		String path = "leave_appr";
	 	if ("list".equals(sub)) {
			 
				path = "appr_list";
			} 
			model.addAttribute("items", items);
	 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
	
	 
	 
 
	
	
}
