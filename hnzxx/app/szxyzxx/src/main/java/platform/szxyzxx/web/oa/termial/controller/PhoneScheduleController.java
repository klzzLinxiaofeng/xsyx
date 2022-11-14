package platform.szxyzxx.web.oa.termial.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.im.service.IMService;
import platform.education.oa.constants.OaPushConstant;
import platform.education.oa.model.Schedule;
import platform.education.oa.model.ScheduleUser;
import platform.education.oa.service.ScheduleRemindService;
import platform.education.oa.service.ScheduleService;
import platform.education.oa.service.ScheduleUserService;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.ScheduleCondition;
import platform.education.oa.vo.ScheduleUserCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.oa.termial.vo.PhoneScheduleUserVo;
import platform.szxyzxx.web.oa.termial.vo.PhoneScheduleVo;
import platform.szxyzxx.web.oa.utils.JsonWriteUtils;
import platform.szxyzxx.web.oa.utils.PushUtils;
import platform.szxyzxx.web.oa.utils.StringUtils;

@Controller
@RequestMapping(value = "/termial/oa/schedule")
public class PhoneScheduleController extends BaseController {

	@Autowired private ScheduleService scheduleService;
	@Autowired private ScheduleUserService scheduleUserService;
	
	@Autowired private ScheduleRemindService scheduleRemindService;
	@Autowired IMService imService;
	/**
	 * 发布日程
	 * 
	 * @param oaNotice
	 * @param user
	 * @return
	 */

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public void creator(HttpServletRequest request, HttpServletResponse response) {
		 
		JSONObject json_return = new JSONObject();
	       try {
	    	  
	    	   String timestamp = request.getParameter("timestamp"); 
	    	   String signature = request.getParameter("signature"); 
	           String posterId = request.getParameter("posterId");
	           String posterName =request.getParameter("posterName") ; 
	           String ownerId = request.getParameter("ownerId");
	           String ownerType = request.getParameter("ownerType");
	           String appId = request.getParameter("appId");
	           String plan_start_time = request.getParameter("planStartTime");
	           String plan_finish_time = request.getParameter("planFinishTime");
	           String rank =request.getParameter("rank") ;  
	           String content = request.getParameter("content") ;  
	           String receiverType = request.getParameter("receiverType");
	           String receiverList = request.getParameter("receiverList"); 
	         
	           String [] str = {timestamp,posterId,posterName,ownerId,ownerType,appId,plan_start_time,plan_finish_time,rank,content,receiverType,receiverList};
	           Schedule schedule=new Schedule();
	           schedule.setPosterId(Integer.valueOf(posterId));
	           schedule.setPosterName(posterName);
	           schedule.setOwnerId(Integer.valueOf(ownerId));
	           schedule.setOwnerType(ownerType);
	           schedule.setAppId(SysContants.SYSTEM_APP_ID);
	           schedule.setUuid(UUIDUtils.getUUID());
	           schedule.setPlanStartTime(plan_start_time);
	           schedule.setPlanFinishTime(plan_finish_time);
   			   if(StringUtils.isNotEmpty(rank)){
   				   schedule.setRank(Integer.valueOf(rank));
   			   }
   			   
   		       schedule.setContent(content);
   		       if(StringUtils.isNotEmpty(receiverType)){
   		    	schedule.setShareTo(receiverType);
   		       }
   		       
   		       this.scheduleService.add(schedule);
   			List<Teacher> teachers=new ArrayList<Teacher>();
   		    if (StringUtils.isNotEmpty(receiverList)) {
   			 
   				String[] us = receiverList.split(",");
   			 
   				List<String> userlists = new ArrayList<String>();
   				for (String s : us) {// 过滤重复ID
   					boolean flag = true;
   					for (String si : userlists) {
   						if (s.equals(si)) {
   							flag = false;
   						}
   					}
   					if (flag) {
   						if (StringUtils.isNotEmpty(s)) {
   							userlists.add(s);
   						}

   					}

   				}
            
   				Teacher   teacher = null;
   				ScheduleUser su = null;
   				for (String s : userlists) {
                           
   					teacher = this.teacherService.findOfUser(Integer.valueOf(ownerId), Integer.valueOf(s));
   					if(teacher!=null){
   						 su = new ScheduleUser();
   						 su.setReceiverId(teacher.getUserId());
   						 su.setReceiverName(teacher.getName());
   						 su.setScheduleId(schedule.getId());
   						 this.scheduleUserService.add(su);
   						teachers.add(teacher);
   					}
   					
   				}
   			}
   		
             json_return.put("common_return",ResponseInfomation.OPERATION_SUC);
             json_return.put("scheduleId",schedule.getId()+"");
	   	/*	}else{
	   		 json_return.put("common_return","非法调用！");
	   		}
	           */

	           JsonWriteUtils.setJson(json_return, response);
	       	if(!teachers.isEmpty()){
	       		PhoneScheduleVo vo=new PhoneScheduleVo(schedule);
	               JSONObject jsonObjects = JSONObject.fromObject(vo);
	               PushUtils.push(teachers, OaPushConstant.oaCardr, receiverType, jsonObjects, imService);	
	       	}
	           
	       } catch (Exception e) {
	           
	           json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
	           JsonWriteUtils.setJson(json_return, response);

	       } finally {

	           if (json_return != null) {
	               json_return.clear();
	           }

	       }
	}
	
	
	 /**
	    * 查询发送给学校全体教工的通知
	    * 
	    * @param request
	    * @return
	    */
	   @RequestMapping(value = "dateList")
	   @ResponseBody
	   public void getdateList(HttpServletRequest request, HttpServletResponse response ) {
			 
		   JSONObject json_return = new JSONObject();
	       JSONArray array = new JSONArray();
	       try {
	           String userId = request.getParameter("userId");
	           String ownerId = request.getParameter("ownerId");  
	           String ownerType = request.getParameter("ownerType");  
	           String date = request.getParameter("date");
	             
	           List<Schedule> items= this.scheduleService.findSheduleByDate(Integer.valueOf(ownerId), ownerType, Integer.valueOf(userId), date+" 00:00:00", date+" 59:59:59");
	           PhoneScheduleVo vo = null;
	           JSONObject jsonObjects = null;
	           for (Schedule info : items) {
	        	    
	        	   vo = new PhoneScheduleVo(info);
	        	   jsonObjects = JSONObject.fromObject(vo);
	               array.add(jsonObjects); 
	           }
	           
	           json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
	           json_return.put("return_info", array);
	           JsonWriteUtils.setJson(json_return, response);
	       } catch (Exception e) {
	      
	           json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
	           JsonWriteUtils.setJson(json_return, response);
	       } finally {
	           if (!array.isEmpty()) {
	               array.clear();
	           }
	           if (json_return != null) {
	               json_return.clear();
	           }

	       }

	   }
	
	
	   
		 /**
	    * 查询发送给学校全体教工的通知
	    * 
	    * @param request
	    * @return
	    */
	   @RequestMapping(value = "myList")
	   @ResponseBody
	   public void myList(HttpServletRequest request, HttpServletResponse response ,@ModelAttribute("page") Page page,
				@ModelAttribute("order") Order order) {
			 
		   JSONObject json_return = new JSONObject();
	       JSONArray array = new JSONArray();
	       try {
	           String userId = request.getParameter("userId");
	           String ownerId = request.getParameter("ownerId");  
	           String ownerType = request.getParameter("ownerType");  
	           String new_or_old = request.getParameter("new_or_old");
	           String baseline_date = request.getParameter("baseline_date");
	           String page_num = request.getParameter("page_num");
	           int num=8;
	           if(StringUtils.isNotEmpty(page_num)){
	               try{
	                   num=Integer.parseInt(page_num);
	               }catch (Exception ex) {
	                // TODO: handle exception
	            }
	           }
	 
	           order.setProperty(order.getProperty() != null ? order.getProperty()
		: "create_date");
	           page.setPageSize(num);
	            
	           ScheduleCondition scheduleCondition = new ScheduleCondition();
	           scheduleCondition.setPosterId(Integer.valueOf(userId));
	           
	           List<Schedule> items= this.scheduleService.findSheduleByUser(Integer.valueOf(ownerId),ownerType, Integer.valueOf(userId),Integer.valueOf(new_or_old),baseline_date, page, order);
	           
	           PhoneScheduleVo vo = null;
	           JSONObject jsonObjects = null;
	           for (Schedule info : items) {
	        	    
	        	   vo = new PhoneScheduleVo(info);
	        	   jsonObjects = JSONObject.fromObject(vo);
	               array.add(jsonObjects); 
	           }
	           
	           json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
	           json_return.put("return_info", array);
	           JsonWriteUtils.setJson(json_return, response);
	       } catch (Exception e) {
	          
	           json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
	           JsonWriteUtils.setJson(json_return, response);
	       } finally {
	           if (!array.isEmpty()) {
	               array.clear();
	           }
	           if (json_return != null) {
	               json_return.clear();
	           }

	       }
	      


	   }
	   
	   
	   /**
		 *删除日程
		 * 
		 * @param oaNotice
		 * @param user
		 * @return
		 */

		@RequestMapping(value = "/del", method = RequestMethod.POST)
		@ResponseBody
		public void del(HttpServletRequest request, Schedule schedule, HttpServletResponse response) {
			 
			JSONObject json_return = new JSONObject();
		       try {
		    	   String timestamp = request.getParameter("timestamp"); 
		    	   String signature = request.getParameter("signature"); 
		           String schedule_id = request.getParameter("schedule_id");
		         
		           schedule.setId(Integer.valueOf(schedule_id));
		           schedule.setIsDeleted(true); 
		           this.scheduleService.modify(schedule);
		   			json_return.put("common_return", ResponseInfomation.OPERATION_SUC);  
		   			
		           JsonWriteUtils.setJson(json_return, response);
		        
		       } catch (Exception e) {
		              
		           json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
		           JsonWriteUtils.setJson(json_return, response);

		       } finally {

		           if (json_return != null) {
		               json_return.clear();
		           }

		       }
		}
		
		
		 /**
		    * 查询当前日程的所有分享记录
		    * 
		    * @param request
		    * @return
		    */
		   @RequestMapping(value = "shareToRepeat")
		   @ResponseBody
		   public void shareToRepeat(HttpServletRequest request, HttpServletResponse response ,@ModelAttribute("page") Page page,
					@ModelAttribute("order") Order order) {
				  
			   JSONObject json_return = new JSONObject();
		       JSONArray array = new JSONArray();
		       try {
		           String scheduleId = request.getParameter("scheduleId");
		           
		           String page_num = request.getParameter("page_num");
		           int num=8;
		           if(StringUtils.isNotEmpty(page_num)){
		               try{
		                   num=Integer.parseInt(page_num);
		               }catch (Exception ex) {
		                // TODO: handle exception
		            }
		           }
		 
		           order.setProperty(order.getProperty() != null ? order.getProperty()
			: "create_date");
		           page.setPageSize(num);
		             
		           ScheduleUserCondition scheduleUserCondition=new ScheduleUserCondition();
	   				scheduleUserCondition.setScheduleId(Integer.valueOf(scheduleId));
                   List<ScheduleUser> sus = this.scheduleUserService.findScheduleUserByCondition(scheduleUserCondition);
		           
                   PhoneScheduleUserVo vo = null;
                   JSONObject jsonObjects = null;
		           for (ScheduleUser info : sus) {
		        	    
		        	   vo = new PhoneScheduleUserVo(info);
		        	   jsonObjects = JSONObject.fromObject(vo);
		               array.add(jsonObjects); 
		           }
		           
		           json_return.put("common_return", ResponseInfomation.OPERATION_SUC);
		           json_return.put("return_info", array);
		           JsonWriteUtils.setJson(json_return, response);
		       } catch (Exception e) {
		          
		           json_return.put("common_return",  ResponseInfomation.OPERATION_ERROR);
		           JsonWriteUtils.setJson(json_return, response);
		       } finally {
		           if (!array.isEmpty()) {
		               array.clear();
		           }
		           if (json_return != null) {
		               json_return.clear();
		           }

		       }
		      
		   }
		
		
		/**
		 * 编辑日程
		 * 
		 * @param oaNotice
		 * @param user
		 * @return
		 */

		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		@ResponseBody
		public void edit(HttpServletRequest request, HttpServletResponse response) {
			  
			JSONObject json_return = new JSONObject();
		       try {
		    	   String timestamp = request.getParameter("timestamp"); 
		    	   String signature = request.getParameter("signature"); 
		    	   String scheduleId = request.getParameter("scheduleId");
		           String posterId = request.getParameter("posterId");
		           String posterName = request.getParameter("posterName") ; 
		           String ownerId = request.getParameter("ownerId");
		           String ownerType = request.getParameter("ownerType");
		           String appId = request.getParameter("appId");
		           String plan_start_time = request.getParameter("planStartTime");
		           String plan_finish_time = request.getParameter("planFinishTime");
		           String rank =request.getParameter("rank") ;   
		           String content = request.getParameter("content");
		           String receiverType = request.getParameter("receiverType");
		           String receiverList = request.getParameter("receiverList"); 
		         
		           String [] str = {timestamp,posterId,posterName,ownerId,ownerType,appId,plan_start_time,plan_finish_time,rank,content,receiverType,receiverList};
		           Schedule schedule=this.scheduleService.findScheduleById(Integer.valueOf(scheduleId));
		           schedule.setPosterId(Integer.valueOf(posterId));
		            
		           schedule.setPosterName(posterName);
	               
		           schedule.setOwnerId(Integer.valueOf(ownerId));
	        	   
		           schedule.setOwnerType(ownerType);
		           schedule.setAppId(SysContants.SYSTEM_APP_ID);
		           schedule.setUuid(UUIDUtils.getUUID());
		           schedule.setPlanStartTime(plan_start_time);
		           schedule.setPlanFinishTime(plan_finish_time);
	   			   if(StringUtils.isNotEmpty(rank)){
	   				   schedule.setRank(Integer.valueOf(rank));
	   			   }
	   			   
	   		       schedule.setContent(content);
	   		       schedule.setShareTo(receiverType);
	   		       this.scheduleService.modify(schedule);
	   		    if (StringUtils.isNotEmpty(receiverList)) {
	   			 
	   				 
	   				String[] us = receiverList.split(",");
	   			 
	   				List<String> userlists = new ArrayList<String>();
	   				boolean flag = true;
	   				for (String s : us) {// 过滤重复ID
	   					for (String si : userlists) {
	   						if (s.equals(si)) {
	   							flag = false;
	   							break;
	   						}
	   					}
	   					if (flag) {
	   						if (StringUtils.isNotEmpty(s)) {
	   							userlists.add(s);
	   						}

	   					}

	   				}
	   				
	   				ScheduleUserCondition scheduleUserCondition=new ScheduleUserCondition();
	   				scheduleUserCondition.setScheduleId(Integer.valueOf(scheduleId));
                    List<ScheduleUser> sus = this.scheduleUserService.findScheduleUserByCondition(scheduleUserCondition);
                    for(ScheduleUser su:sus){
                    	this.scheduleUserService.remove(su);
                    }
                    Teacher teacher = null;
                    ScheduleUser su = null;
	   				for (String s : userlists) {
	                           
	   					teacher = this.teacherService.findOfUser(Integer.valueOf(ownerId), Integer.valueOf(s));
	   					if(teacher!=null){
	   						 su = new ScheduleUser();
	   						 su.setReceiverId(teacher.getUserId());
	   						 su.setReceiverName(teacher.getName());
	   						 su.setScheduleId(schedule.getId());
	   						 this.scheduleUserService.add(su);
	   					}
	   					
	   				}
	   			}
	          
	             json_return.put("common_return",ResponseInfomation.OPERATION_SUC);
		   	/*	}else{
		   		 json_return.put("common_return","非法调用！");
		   		}
		           */
		         
		            

		           JsonWriteUtils.setJson(json_return, response);
		        
		       } catch (Exception e) {
		           
		           json_return.put("common_return", ResponseInfomation.OPERATION_ERROR);
		           JsonWriteUtils.setJson(json_return, response);

		       } finally {

		           if (json_return != null) {
		               json_return.clear();
		           }

		       }
		}
		
	   private String parameter(HttpServletRequest request,String p){
		   try {
				return new String(request.getParameter(p).getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null; 
			} 
	   }
		
}
