package platform.szxyzxx.web.oa.termial.controller;
 

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody;
 










 
import platform.education.oa.service.AppStatisticsService;
import platform.education.oa.service.AppSumStatisticsService; 
import platform.education.oa.utils.AppStatisticsUtils;  
import platform.szxyzxx.web.common.util.ResponseInfomation;  
import platform.szxyzxx.web.oa.utils.JsonWriteUtils; 

/**
 * 操作统计
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/termial/oa/statistics")
public class PhoneAppStatisticsController  {
	 
 

	@Autowired
	private AppSumStatisticsService appSumStatisticsService;

	 @Autowired AppStatisticsService appStatisticsService;

	/**
	 * 发布公告
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/creator")
	@ResponseBody
	public void creator(HttpServletRequest request, HttpServletResponse response) {
		 
		JSONObject json_return = new JSONObject();
	       try {
	    	   String timestamp = request.getParameter("timestamp"); 
	    	   String signature = request.getParameter("signature"); 
	           String posterId = request.getParameter("posterId");
	         
	           String ownerId = request.getParameter("ownerId");
	           String ownerType = request.getParameter("ownerType");
	           String type = request.getParameter("type");  
	          //保存总的统计
	           AppStatisticsUtils.sumStatisticsCreator(appSumStatisticsService, type);
	           //保存每个用户的每一次统计
	           AppStatisticsUtils.statisticsCreator(appStatisticsService, ownerId, ownerType, posterId, type);
          
	            
	        
	       } catch (Exception e) {
	           
	          

	       } finally {

	           if (json_return != null) {
	               json_return.clear();
	           }

	       }
	}
	
	 
}
