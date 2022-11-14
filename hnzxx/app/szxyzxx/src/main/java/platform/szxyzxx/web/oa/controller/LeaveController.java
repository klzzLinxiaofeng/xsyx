package platform.szxyzxx.web.oa.controller;
 
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 











import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.oa.model.Leave;
import platform.education.oa.model.LeaveApprUser;
import platform.education.oa.model.Notice;
import platform.education.oa.model.NoticeUser;
import platform.education.oa.service.LeaveApprUserService;
import platform.education.oa.service.LeaveService;
import platform.education.oa.vo.LeaveApprUserCondition;
import platform.education.oa.vo.LeaveCondition;
import platform.education.oa.vo.NoticeCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.utils.StringUtils;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


/**
 * 请假控制类
 * 
 * @author sky
 * @version 1.0 2015-6-19
 */

@Controller
@RequestMapping(value = "/office/leave")
public class LeaveController extends BaseController{
	private final static String BASE_PATH = "oa/leave/";
	
	
	@Autowired LeaveService leaveService;
	
	@Autowired LeaveApprUserService lauService;
	
	
	@RequestMapping(value = "index")
	public String index() {
		return BASE_PATH + "index";
	}
	
	/**
	 * 请假申请
	 * @param leave
	 * @param userid_list
	 * @param teachName
	 * @param user
	 * @return
	 */
	
	   @RequestMapping(value = "creator")
	   @ResponseBody
		public ResponseInfomation save(Leave leave,   @RequestParam(value = "userid_list")  String userid_list, @RequestParam(value = "teachName") String teachName,@CurrentUser UserInfo user) {
	        try {
	          
	           // leave.setDepartmentId(memberService.getDepId());
	        	
	            leave.setCreatename(user.getRealName());
	            leave.setCreateuserId(user.getId());
	            leave.setUserimage(user.getIcon()); 
	            leave.setSchoolId(user.getSchoolId());
	            
	            if(StringUtils.isNotEmpty(teachName)){
	            	StringBuffer sb=new StringBuffer();
	            	sb.append(teachName);
	            	sb.deleteCharAt(sb.length()-1);
	            	leave.setSelApprName(sb.toString());
	            }
	          
	            this.leaveService.add(leave); 
	          
	            if (StringUtils.isNotEmpty(userid_list)) {
	                String[] s = userid_list.split(",");
	                for (String st : s) {
	                	Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getId()); 
	                    LeaveApprUser lau = new LeaveApprUser();
	                        teacher = this.teacherService
								.findTeacherById(Integer.valueOf(st));
	                    lau.setLeaveId(leave.getId());
	                    lau.setUserId(teacher.getUserId()); 
	                    this.lauService.add(lau); 
	                }
	            }

	       

	            return new ResponseInfomation(leave.getId(),
						ResponseInfomation.OPERATION_SUC);

	        
	        } catch (Exception e) {
                     e.printStackTrace();
	        	return new ResponseInfomation();
	        }

	        
	    }
	   
	   
	   /**
		 * 删除请假
		 * 
		 * @param id
		 * @param notice
		 * @return
		 */
		@RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
		@ResponseBody
		public String delete(@PathVariable(value = "id") Integer id, Leave leave,@CurrentUser UserInfo user) {

			try {
				leave.setId(id);
				this.leaveService.remove(leave);
				LeaveApprUserCondition condition= new LeaveApprUserCondition();
				 
				condition.setLeaveId(id); 
				condition.setApprState(0);
				 List<LeaveApprUser> nus=this.lauService.findLeaveApprUserByCondition(condition);
				 for(LeaveApprUser n:nus){
					this.lauService.remove(n);
				 }
				return ResponseInfomation.OPERATION_SUC;
			} catch (Exception e) {
				return ResponseInfomation.OPERATION_FAIL;
			}

		}
	   
}
