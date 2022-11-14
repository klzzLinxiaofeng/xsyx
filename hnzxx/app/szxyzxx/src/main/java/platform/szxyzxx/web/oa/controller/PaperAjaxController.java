package platform.szxyzxx.web.oa.controller;
 

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import platform.education.oa.model.Paper;
import platform.education.oa.service.PaperService;
import platform.education.oa.vo.PaperCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
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
@RequestMapping(value = "/office/ajax/paper")
public class PaperAjaxController extends BaseController{
	private final static String BASE_PATH = "oa/paper/";
	 @Autowired PaperService paperService;
	
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
			@ModelAttribute("condition") PaperCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date"); 
		condition.setPosterId(user.getId());
		condition.setIsDeleted(false);
           List<Paper> items = this.paperService.findPaperByCondition(condition, page, order);
           
           for(Paper p:items){
        	   EntityFile entity = this.entityFileService.findFileByUUID(p.getAttachmentUuid()) ;
//        	   if(entity!=null){
//        		   p.setRealFileName(entity.getFileName());  
//        	   }
        	   
           }
          
   		 
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
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model,
			@CurrentUser UserInfo user) {
		
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		
           List<Paper> items = this.paperService.findPaperToUser(user.getSchoolId(),user.getUserTypes(), user.getId(), page, order);
           
           for(Paper p:items){
        	   EntityFile entity = this.entityFileService.findFileByUUID(p.getAttachmentUuid()) ;
        	    
//        	   if(entity!=null){
//        		   p.setRealFileName(entity.getFileName());  
//        	   }
        	   
           }
          
		String path = "my_list";
 	if ("list".equals(sub)) { 
			path = "my_list_page";
		} 
		model.addAttribute("items", items);
		return BASE_PATH + path;
	}
	
	
 
}
