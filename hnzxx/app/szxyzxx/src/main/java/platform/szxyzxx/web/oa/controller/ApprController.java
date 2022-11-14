package platform.szxyzxx.web.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.oa.model.Leave;
import platform.education.oa.model.LeaveAppr;
import platform.education.oa.model.LeaveApprUser;
import platform.education.oa.service.LeaveApprService;
import platform.education.oa.service.LeaveApprUserService;
import platform.education.oa.service.LeaveService;
import platform.education.oa.vo.LeaveApprUserCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping(value = "/office/appr")
public class ApprController {
	@Autowired LeaveService leaveService;
	
	@Autowired LeaveApprUserService lauService;
	
	@Autowired LeaveApprService apprService;
	private final static String BASE_PATH = "oa/appr/";
	@RequestMapping(value = "index")
	public String index() {
		return BASE_PATH + "index";
	}

    @RequestMapping("shenpi/{id}")
    public String shenpi(@PathVariable("id") String id, HttpServletRequest request) {
        try {
        
            Leave leave = this.leaveService.findByUUID(id);
           
            request.setAttribute("leave", leave);
        } catch (Exception e) {

        }
        return BASE_PATH + "shenpi";
    }
    
    
    @RequestMapping(value = "shenpi" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation appr(LeaveAppr leaveappr, HttpServletRequest request,@CurrentUser UserInfo user
             ) {
        try {
            leaveappr.setApprovationName(user.getNickName());
            leaveappr.setApprovationId(user.getId());
            this.apprService.add(leaveappr);
            Leave leave = this.leaveService.findLeaveById(leaveappr.getLeaveId());
            if (leaveappr.getLeavestate() == 1) {
                leave.setLeavestate(1);
            } else {
                leave.setLeavestate(2);
            }
           
            this.leaveService.modify(leave);
           LeaveApprUserCondition leaveApprUserCondition=new LeaveApprUserCondition();
            leaveApprUserCondition.setLeaveId(leave.getId());
           List<LeaveApprUser> lau=this.lauService.findLeaveApprUserByCondition(leaveApprUserCondition);
           
           for(LeaveApprUser u:lau){
        	   u.setApprState(1);
        	   this.lauService.modify(u);
           }
           return new ResponseInfomation(leave.getId(),
					ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
               
			return new ResponseInfomation();
		}
    }
}
