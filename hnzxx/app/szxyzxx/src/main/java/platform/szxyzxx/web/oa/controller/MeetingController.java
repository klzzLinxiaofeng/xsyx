package platform.szxyzxx.web.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.im.service.IMService;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.oa.constants.OaPushConstant;
import platform.education.oa.model.Comments;
import platform.education.oa.model.Meeting;
import platform.education.oa.model.MeetingDepartmentCount;
import platform.education.oa.model.MeetingSummary;
import platform.education.oa.model.MeetingUser;
import platform.education.oa.model.SummaryReadUser;
import platform.education.oa.service.CommentsService;
import platform.education.oa.service.MeetingService;
import platform.education.oa.service.MeetingSummaryService;
import platform.education.oa.service.MeetingUserService;
import platform.education.oa.service.SummaryReadUserService;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.utils.UtilDate;
import platform.education.oa.vo.CommentsCondition;
import platform.education.oa.vo.CommentsVo;
import platform.education.oa.vo.MeetingCondition;
import platform.education.oa.vo.MeetingDepartmentCountCondition;
import platform.education.oa.vo.MeetingSummaryCondition;
import platform.education.oa.vo.MeetingUserCondition;
import platform.education.oa.vo.MeetingVo;
import platform.education.oa.vo.SummaryReadUserCondition;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.web.oa.termial.vo.PhoneMeetingVo;
import platform.szxyzxx.web.oa.utils.PushUtils;
import platform.szxyzxx.web.oa.utils.StringUtils;

/**
 * ???????????????
 * @author sky
 * @version 1.0 2015-6-19
 */

@Controller
@RequestMapping(value = "/office/meeting")
public class MeetingController extends BaseController{
//	private List<Object> list;
	private final static String BASE_PATH = "oa/meeting/";
	
	@Autowired MeetingService meetingService;
	@Autowired SummaryReadUserService sruService;
	@Autowired MeetingUserService muService;
	@Autowired MeetingSummaryService summaryService;
	@Autowired IMService imService;
	@Autowired CommentsService commentService;
	@Resource(name = "oa_meeting_taskExecutor")
    private TaskExecutor taskExecutor;
	@RequestMapping(value = "index")
	public String index() {
		return BASE_PATH + "index";
	}
	
	 /**
	  * ???????????????????????????
	  * @param request
	  * @return
	  */
	@RequestMapping(value="creator" , method = RequestMethod.GET)
	public String creatorUI(HttpServletRequest request){
              
		 return BASE_PATH+"input";
	}


	/**
	 * ????????????
	 * @param meeting
	 * @param ids
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Meeting meeting,
			@RequestParam(value = "ids") String ids, @CurrentUser UserInfo user) {
		try {
			
			
			meeting.setCreateuserId(user.getId());
			meeting.setSchoolId(user.getSchoolId());
			 
				meeting.setCreatename(user.getRealName());
		 
			
			if (!StringUtils.isNotEmpty(ids)) {
				meeting.setQuanbu(1);	
			 
			}
			meeting.setFanwei(0);
			this.meetingService.add(meeting);
	
			
         
		
			if (StringUtils.isNotEmpty(ids)) {
				StringBuffer sb = new StringBuffer();
				sb.append(ids);
				sb.deleteCharAt(sb.length() - 1);
				String[] us = ids.split(",");
				List<String> userlists = new ArrayList<String>();
				for (String s : us) {// ????????????ID
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
				PhoneMeetingVo vo=new PhoneMeetingVo(meeting);
   				JSONObject jsonObjects = JSONObject.fromObject(vo);
				for (String s : userlists) {
					Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getId());       
					  teacher = this.teacherService
							.findTeacherById(Integer.valueOf(s));
					if(teacher!=null){
						MeetingUser mu=new MeetingUser();
						mu.setMeetingId(meeting.getId());
						mu.setUserId(teacher.getUserId());
						mu.setUserName(teacher.getName());
						mu.setIsCanhui(0);
						this.muService.add(mu);
						JSONObject messageMap = new JSONObject();
   						messageMap.put("objectid", teacher.getUserId());
   						messageMap.put("receiveid", teacher.getUserId()); // ??????ID
   						messageMap.put("message_type", 16);
   						messageMap.put("receiverType", 0);
   					    messageMap.put("return_info", jsonObjects);//????????????
   						//imService.push(teacher.getUserName(), messageMap.toString());//??????
						imService.push(teacher.getUserId(), "xunyun#educloud#web", messageMap.toString());

					}
					
				}
			}
			return new ResponseInfomation(meeting.getId(),
					ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
                
			return new ResponseInfomation();
		}

	}
	
	
	/**
	 * ?????????????????????????????????
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String update_ui(@PathVariable("id") Integer id,
			HttpServletRequest request) {

		Meeting meeting =  this.meetingService.findMeetingById(id);
		MeetingUserCondition condition=new MeetingUserCondition();
		condition.setMeetingId(id);
		 List<MeetingUser> nus=this.muService.findMeetingUserByCondition(condition);
		request.setAttribute("meeting", meeting);
		request.setAttribute("meetingUser", nus);
		return BASE_PATH + "edit";
	}


	/**
	 * ????????????
	 * @param meeting
	 * @param id
	 * @param ids
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(Meeting meeting,
			@PathVariable(value = "id") Integer id,@RequestParam(value = "ids") String ids,@CurrentUser UserInfo user, HttpServletRequest request) {

		try {

			meeting.setId(id);
			if (StringUtils.isNotEmpty(ids)){
				meeting.setQuanbu(0);
			}
			meeting =this.meetingService.modify(meeting);
			
			
			if (StringUtils.isNotEmpty(ids)) {
				MeetingUserCondition condition=new MeetingUserCondition();
				condition.setMeetingId(id);
				 List<MeetingUser> nus=this.muService.findMeetingUserByCondition(condition);
				 for(MeetingUser n:nus){
					 this.muService.remove(n);
				 }
				 
				  
				StringBuffer sb = new StringBuffer();
				sb.append(ids);
				sb.deleteCharAt(sb.length() - 1);
				String[] us = ids.split(",");
				List<String> userlists = new ArrayList<String>();
				for (String s : us) {// ????????????ID
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
				for (String s : userlists) {

					Teacher  teacher = this.teacherService
							.findTeacherById(Integer.valueOf(s));
					if(teacher!=null){
						MeetingUser mu=new MeetingUser();
						mu.setMeetingId(meeting.getId());
						mu.setUserId(teacher.getUserId());
						mu.setUserName(teacher.getName());
						mu.setIsCanhui(0);
						this.muService.add(mu);
					}
					
				}
			} 
			return new ResponseInfomation(meeting.getId(),
					ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			return new ResponseInfomation();
		}

	}

	
	/**
	 * ????????????
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewer/{id}", method = RequestMethod.GET)
	public String viewer(@PathVariable("id") Integer id,@CurrentUser UserInfo user,
			HttpServletRequest request) {
		Meeting meeting = this.meetingService.findMeetingById(id);
         MeetingUser mu=this.muService.findByUserId(id, user.getId());
        request.setAttribute("meetingUser", mu);
		request.setAttribute("meeting", meeting);
		return BASE_PATH + "viewer";
	}
	
	
    
    @RequestMapping(value = "canhui" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation canhui(MeetingUser mUser,  HttpServletRequest request,@CurrentUser UserInfo user
             ) {
        try {
        	
          MeetingUser mu=this.muService.findByUserId(mUser.getMeetingId(), user.getId());
          mUser.setId(mu.getId());
          this.muService.modify(mUser);
           return new ResponseInfomation(null,
					ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
               e.printStackTrace();
			return new ResponseInfomation();
		}
    }


	/**
	 * ????????????
	 * @param id
	 * @param meeting
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Meeting meeting,@CurrentUser UserInfo user) {

		try {
			meeting.setId(id);
			 this.meetingService.remove(meeting);
			
			 MeetingUserCondition condition=new MeetingUserCondition();
				condition.setMeetingId(id);
				 List<MeetingUser> nus=this.muService.findMeetingUserByCondition(condition);
			 for(MeetingUser n:nus){
				 this.muService.remove(n);
			 }
			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}

	}
	
	
	/**
	 * ?????????????????????????????????
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "summary/{id}", method = RequestMethod.GET)
	public String summary_ui(@PathVariable("id") Integer id,
			HttpServletRequest request) {

		Meeting meeting =  this.meetingService.findMeetingById(id);
		MeetingUserCondition condition=new MeetingUserCondition();
		condition.setMeetingId(id);
		 List<MeetingUser> nus=this.muService.findMeetingUserByCondition(condition);
		request.setAttribute("meeting", meeting);
		request.setAttribute("meetingUser", nus);
	 
		return BASE_PATH + "summary";
	}
	
	
	
	
	
	  @RequestMapping(value = "summary" , method = RequestMethod.POST)
		@ResponseBody
		public ResponseInfomation summary(MeetingSummary summary,@RequestParam(value = "muserid", required = false) String muserid,@RequestParam(value = "meetingid", required = false) String meetingid,HttpServletRequest request,@CurrentUser UserInfo user
	             ) {
	        try {
	        
	        	  summary.setUserName(user.getRealName());
	        	  String[] str=null;
	        	 if(StringUtils.isNotEmpty(muserid)){
	        		 str=muserid.split(",");
	        	 }
	        	  
	               summary.setUserId(user.getId());
	               
	               StringBuffer sb=new StringBuffer();
	               StringBuffer sb2=new StringBuffer();
	               MeetingUserCondition condition=new MeetingUserCondition();
	       		condition.setMeetingId(summary.getMeetingId());
	       		 List<MeetingUser> nus=this.muService.findMeetingUserByCondition(condition);  
	               for(MeetingUser u:nus){
	            	   boolean flag=true;
	            	    if(muserid!=null){
	            	    	 
	            	    	 for(String s: str){
	   	            		 
	   	            		   if(u.getId()==Integer.valueOf(s)){
	   	            			   sb.append(u.getUserName()).append(",");
	   	            			   flag=false;
	   	            		   }
	   	            	   } 
	            	    }
	            	  
	            	   if(flag){
	            		   sb2.append(u.getUserName()).append(",");
	            	   }
	               }
	               if(sb.length()>1){
	            	   sb.deleteCharAt(sb.length()-1);
	            	   summary.setMeetingAttend(sb.toString());
	               }
	               if(sb2.length()>1){
	            	   sb2.deleteCharAt(sb2.length()-1);
	            	   summary.setMeetingAbsent(sb2.toString());
	               }
	              
	             
	        	 this.summaryService.add(summary);
	        	 
	        	 Meeting meeting=this.meetingService.findMeetingById(summary.getMeetingId());
	        	 meeting.setSummaryId(summary.getId());
	        	 this.meetingService.modify(meeting);
	          
	           return new ResponseInfomation(null,
						ResponseInfomation.OPERATION_SUC);
			} catch (Exception e) {
	             e.printStackTrace();
				return new ResponseInfomation();
			}
	    }
	  
	  
		/**
		 * ??????????????????
		 * 
		 * @param id
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "summary/info/{id}", method = RequestMethod.GET)
		public String summary_info(@PathVariable("id") Integer id,
				HttpServletRequest request) {

		 
			 
				request.setAttribute("summary", this.summaryService.findMeetingSummaryById(id));
		 
			
			return BASE_PATH + "summary_info";
		}


	/**
	 * ????????????
	 * @param id
	 * @param summary
	 * @return
	 */
	@RequestMapping(value = "summary/del/{id}", method = RequestMethod.DELETE)
		@ResponseBody
		public String delete(@PathVariable(value = "id") Integer id, MeetingSummary summary) {

			try {
				summary= this.summaryService.findMeetingSummaryById(id);
				 Meeting meeting=this.meetingService.findMeetingById(summary.getMeetingId());
	        	 meeting.setSummaryId(null);
	        	 this.meetingService.modify(meeting);
				 this.summaryService.remove(summary);
				 
				return ResponseInfomation.OPERATION_SUC;
			} catch (Exception e) {
				return ResponseInfomation.OPERATION_FAIL;
			}

		}
		
		//===========????????????    2015-8-26 ===========//
		
		/**
		 * ??????????????????????????????ID
		 * @return departmentId
		 */
		public Object getUserDepartment(@CurrentUser UserInfo user){
			Object departmentId = null;
			DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
			if(user.getTeacherId() != null){
				condition.setIsDeleted(false);
				condition.setSchoolId(user.getSchoolId());
				condition.setTeacherId(user.getTeacherId());
				List<DepartmentTeacher> list = this.departmentTeacherService.findDepartmentTeacherByCondition(condition, null, null);
				if(list.size() > 0){
					for(int i=0;i<list.size();i++){
						departmentId = list.get(i).getDepartmentId();
					}
				}
			}
			return departmentId;
		}
		
		/**
		 * ???????????? ??????
		 */
		@RequestMapping(value="creatorMetting" , method = RequestMethod.GET)
		public String creatorMetting(){
			 return BASE_PATH+"newInput";
		}
		
		/**
		 * ????????????
		 * @throws ParseException 
		 */
		@RequestMapping(value = "newIndex")
		public ModelAndView newList(@RequestParam(value = "dm", required = false) String dm,
				@RequestParam(value = "sub", required = false) String sub,
				@RequestParam(value = "type", required = false) String type,
				@ModelAttribute("condition") MeetingCondition condition,
				@ModelAttribute("page") Page page,
				@ModelAttribute("order") Order order, Model model,
				@CurrentUser UserInfo user) throws ParseException {
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			
			condition.setSchoolId(user.getSchoolId());
			condition.setCreateuserId(user.getId());
			condition.setQuanbu(1);
			condition.setTeacherId(user.getTeacherId()==null?-1:user.getTeacherId());
			condition.setFanwei(ContansOfOa.all);
			
			//????????????
			if(type!=null && type.equals("relatedWithMe")){
				condition.setIsRelatedWithMe(true);
				//2016-7-5 ???????????????????????????????????????????????????????????????
				condition.setUserId(user.getId());
			}
			//????????????
			if(type!=null && type.equals("department")){
				condition.setIsDepartmentRecord(true);
			}
			//????????????
			if(type!=null && type.equals("myPublish")){
				condition.setIsMePublish(true);
			}
			//????????????????????????????????????????????????????????????????????????
			if(type!=null && type.equals("allRecord")){
				condition.setIsAllRecord(true);
				List<Department> depList = this.departmentService.findDepartmentBySchoolId(user.getSchoolId(),null, null);
				MeetingDepartmentCountCondition depCou = new MeetingDepartmentCountCondition();
				depCou.setIsDelete(false);
				depCou.setOwnerId(user.getSchoolId());
				depCou.setOwnerType(user.getGroupId());
				List<MeetingDepartmentCount> numList = this.meetingDepartmentCountService.findMeetingDepartmentCountByCondition(depCou);
				model.addAttribute("depList", depList);
				model.addAttribute("depNumList", numList);
			}
			page.setPageSize(3);
			List<MeetingVo> items = this.meetingService.findMeetingByRelatedWithMe(condition, page, order);
			
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			Date date_today = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//?????????mm??????????????????
			EntityFile entity = null;
			List<MeetingUser> list = null;
			if(items.size() > 0){
				for(int i = 0;i<items.size();i++){
					meetingUserCondition.setMeetingId(items.get(i).getId());
					list = this.muService.findMeetingUserByCondition(meetingUserCondition);
					
					//????????????  ????????????????????????
					int sum = UtilDate.getLeftDayTime(date_today,sdf.parse(items.get(i).getStarttime()));
					int day = (int) Math.floor(sum/(60*24));
					int hour = (int) Math.floor((sum%(60*24))/60);
					
					//??????????????????
					if(list.size() > 0){
						for(int j=0;j<list.size();j++){
							if(list.get(j).getDepartmentId() != null){
								Department dep = this.departmentService.findDepartmentById(list.get(j).getDepartmentId());
								if(dep!=null){
									list.get(j).setDepartmentName(dep.getName());
								}
							}
						}
					}
					
					//????????????
					if(items.get(i).getUploadFile()!=null && !"".equals(items.get(i).getUploadFile())){
						entity = this.entityFileService.findFileByUUID(items.get(i).getUploadFile());
						items.get(i).setFileName(entity.getFileName());
					}
					
					//???????????????????????????????????????????????????
					String status = "notStarted";
					//??????????????????????????????
					boolean today_start = date_today.before(sdf.parse(items.get(i).getStarttime()));
					//??????????????????????????????
					boolean today_end = date_today.before(sdf.parse(items.get(i).getEndtime()));
					if(!today_start && today_end){
						status = "underway";
					}
					if(!today_end){
						status = "complete";
					}
					items.get(i).setMeetingStatus(status);
					
					//?????????????????? ??????????????????????????????????????????
					Date stDate = sdf.parse(items.get(i).getStarttime());
					long t = stDate.getTime() - 60*60*60;
					Date canModifyDate = new Date(t);
					boolean ck = date_today.after(canModifyDate); 
					if(ck){
						items.get(i).setIsCanModify("no");
					}else{
						items.get(i).setIsCanModify("yes");
					}
					
					items.get(i).setDay(day<0?0:day);
					items.get(i).setHour(hour<0?0:hour);
					items.get(i).setMeetingUser(list);
				}
			}
			
			//???????????????????????????????????????????????????
			for(MeetingVo mVo : items){
				boolean isCheck = isRead(mVo,user);
				mVo.setIsCheck(isCheck);
			}
			
			//???????????????????????? ???????????????
			DepartmentTeacherCondition con = new DepartmentTeacherCondition();
			con.setIsDeleted(false);
			con.setSchoolId(user.getSchoolId());
			con.setTeacherId(user.getTeacherId()==null?-1:user.getTeacherId());
			List<DepartmentTeacher> tdep = this.departmentTeacherService.findDepartmentTeacherByCondition(con, null, null);
			String hasDepartment = "noDepartment";
			if(tdep.size()>0){
				hasDepartment = "hasDepartment";
			}
			
			model.addAttribute("hasDepartment", hasDepartment);
			model.addAttribute("cuurenLogin", user.getId());
			model.addAttribute("items", items);
			model.addAttribute("type", type);
			model.addAttribute("totalSize", page.getTotalRows());
			model.addAttribute("entity", entity);
			String path = "newIndex";
			if ("list".equals(sub)) {
				path = "newList";
			}
			return new ModelAndView(BASE_PATH+path,model.asMap());
		}
		
		/**
		 * ????????????
		 */
		@RequestMapping(value = "/addMeeting", method = RequestMethod.POST)
		@ResponseBody
		public ResponseInfomation addMeeting(Meeting oaMeeting,
				@RequestParam(value = "departmentIds", required = false) String departmentIds,
				@RequestParam(value = "teacherIds", required = false) String teacherIds,
				@RequestParam(value = "teacherNames", required = false) String teacherNames,
				@CurrentUser final UserInfo user) {
				Integer departmentId = (Integer)getUserDepartment(user);
			try {
				
				//fanwei ?????????????????????  0???????????? 1:????????????   2???????????????  
				String[] departments = departmentIds.split(",");
				String[] teachers = teacherIds.split(",");
				String[] names = teacherNames.split(",");
				final Integer receiverType = oaMeeting.getFanwei();
				
				//List<Teacer> teacher1 ???????????????????????????  ????????????????????????
				List<Teacher> teacher1 = new ArrayList<Teacher>();
				//num ?????????????????????
				int num = 0;
				
				if(receiverType.equals(ContansOfOa.personer)){
					Teacher t1 = null;
					for(String t : teachers){
						t1 = this.teacherService.findTeacherById(Integer.parseInt(t));
						teacher1.add(t1);
					}
					
					num = teachers.length;
				}else if(receiverType.equals(ContansOfOa.department)){
					List<DepartmentTeacher> dts = null;
					Teacher t1 = null;
					DepartmentTeacherCondition con = new DepartmentTeacherCondition();
					if(departmentIds.length() > 0){
						for(int i=0;i<departments.length;i++){
							con.setDepartmentId(Integer.parseInt(departments[i]));
							dts = this.departmentTeacherService.findDepartmentTeacherByCondition(con, null, null);
							num += dts.size();
							
							if(dts.size()>0){
								for(int dt=0;dt<dts.size();dt++){
									t1 = this.teacherService.findTeacherById(dts.get(dt).getTeacherId());
									if(t1!=null){
										teacher1.add(t1);
									}
								}
							}
							
						}
					}
					
					//?????????????????????
					teacher1 = removeRep(teacher1);
					
				}else{
					teacher1 = this.teacherService.findTeacherListBySchoolId(user.getSchoolId());
					num = teacher1.size();
					
				}
				
				oaMeeting.setMeetingNum(num);
				oaMeeting.setUuid(UUIDUtils.getUUID());
				oaMeeting.setCreatename(user.getRealName());
				oaMeeting.setCreateuserId(user.getId());
				oaMeeting.setSchoolId(user.getSchoolId());
				oaMeeting.setDepartmentId(departmentId);
				Meeting meeting = this.meetingService.add(oaMeeting);
				
				if(receiverType.equals(ContansOfOa.department)){
					for(int i = 0; i < departments.length; i++){
						MeetingUser meetingUser = new MeetingUser();
						meetingUser.setIsCanhui(1);
						meetingUser.setMeetingId(meeting.getId());
						meetingUser.setDepartmentId(Integer.parseInt(departments[i]));
						this.muService.add(meetingUser);
						
						addDepartment(Integer.parseInt(departments[i]), user);
					}
				}else if(receiverType.equals(ContansOfOa.personer)){
					Teacher teacher = null;
					for(int i = 0; i < teachers.length; i++){
						teacher = this.teacherService.findTeacherById(Integer.valueOf(teachers[i]));
						MeetingUser meetingUser = new MeetingUser();
						meetingUser.setIsCanhui(1);
						meetingUser.setMeetingId(meeting.getId());
						meetingUser.setUserId(teacher.getUserId());
						meetingUser.setUserName(names[i]);
						this.muService.add(meetingUser);
					}
				}
				
				List<Integer> pushTteacher = new ArrayList<Integer>();
				for(Teacher teacherId : teacher1){
					pushTteacher.add(teacherId.getUserId());
				}
				Message message = new Message();
				message.setAppId(SysContants.SYSTEM_APP_ID);
				message.setContent("???????????????????????????");
				message.setPosterId(user.getId());
				message.setPosterName(user.getUserName());
				message.setRecordStatus(StatusDefaultContans.ZERO);
				message.setTag(MessageCenterContants.FINISHED_PATH_CODE_OAMEETING);
//				PushMessageUtil.sendMessage(message, pushTteacher);
//				PushMessageUtil.pushMessageList(pushTteacher);
				messageService.sendMessage(message, pushTteacher);
				
				//?????????????????????
				PhoneMeetingVo vo=new PhoneMeetingVo(meeting);
				final JSONObject jsonObjects = JSONObject.fromObject(vo);
				PushUtils.pushOfTaskExecutor(teacher1,OaPushConstant.oaMeeting,"0",jsonObjects,imService,taskExecutor);
				return new ResponseInfomation(meeting.getId(),ResponseInfomation.OPERATION_SUC);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}

		}
		
		/**
		 * ????????????
		 */
		@RequestMapping(value="editMetting" , method = RequestMethod.GET)
		public ModelAndView editMettingPage(Model model,
				@RequestParam(value = "id", required = true) Integer id,
				@CurrentUser UserInfo user){
			String path = "newInput";
			Meeting meeting = this.meetingService.findMeetingById(id);
			
			EntityFile entity = this.entityFileService.findFileByUUID(meeting.getUploadFile());
			
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			meetingUserCondition.setMeetingId(meeting.getId());
			
			//??????????????? ??????
			if(meeting.getFanwei().equals(ContansOfOa.department)){
				meetingUserCondition.setUserId(null);
				meetingUserCondition.setUserName(null);
				List<MeetingUser> mu = this.muService.findMeetingUserByCondition(meetingUserCondition);
				String depId = "";
				String depName = "";
				if(mu.size() > 0){
					Department department = null;
					for(int i = 0;i<mu.size();i++){
						depId += mu.get(i).getDepartmentId() + ",";
						department = this.departmentService.findDepartmentById(mu.get(i).getDepartmentId());
						if(department!=null){
							depName += department.getName() + ",";
						}
					}
				}
				model.addAttribute("depId", depId.substring(0, depId.length()-1));
				model.addAttribute("depName", depName.substring(0, depName.length()-1));
			}
			
			//??????????????? ??????
			if(meeting.getFanwei().equals(ContansOfOa.personer)){
				meetingUserCondition.setUserId(null);
				meetingUserCondition.setUserName(null);
				List<MeetingUser> mu = this.muService.findMeetingUserByCondition(meetingUserCondition);
				String tNames = "";
				String tIds = "";
				if(mu.size() > 0){
					Teacher teacher = null;
					for(int i=0;i<mu.size();i++){
						tNames = tNames + mu.get(i).getUserName() + ",";
						teacher = this.teacherService.findOfUser(user.getSchoolId(),mu.get(i).getUserId());
						tIds = tIds + teacher.getId()+",";
					}
				}
				model.addAttribute("tNames", tNames.substring(0, tNames.length()-1));
				model.addAttribute("tIds", tIds);
			}
			
			model.addAttribute("entity", entity);
			model.addAttribute("meeting", meeting);
			return new ModelAndView(BASE_PATH+path,model.asMap());
		}
		
		/**
		 * ?????????????????? :
		 * 1????????????????????????  
		 * 2????????????????????????????????????????????????
		 * 3???????????????????????????????????????????????????????????????????????????????????????????????????????????????
		 * 4??????????????????????????????????????????
		 * 5?????????????????????????????????????????????????????????
		 */
		@RequestMapping(value="editMetting/{id}" , method = RequestMethod.PUT)
		@ResponseBody
		public ResponseInfomation editToSaveMetting(@CurrentUser UserInfo user,Meeting meeting,
				@RequestParam(value = "departmentIds", required = false) String departmentIds,
				@RequestParam(value = "teacherIds", required = false) String teacherIds,
				@RequestParam(value = "teacherNames", required = false) String teacherNames,
				@PathVariable(value = "id") Integer id){
				
			try{
				Meeting m1 = this.meetingService.findMeetingById(id);
				if(m1.getFanwei().equals(ContansOfOa.department)){
					MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
					meetingUserCondition.setMeetingId(id);
					List<MeetingUser> list = this.muService.findMeetingUserByCondition(meetingUserCondition);
					if(list.size()>0){
						for(MeetingUser mu : list){
							delDepartment(mu.getDepartmentId(), user);
						}
					}
				}
			//??????????????????
			String[] departments = departmentIds.split(",");
			String[] teachers = teacherIds.split(",");
			String[] names = teacherNames.split(",");
			Integer receiverType = meeting.getFanwei();
			int num = 0;
			if(receiverType.equals(ContansOfOa.personer)){
				num = teachers.length;
			}else if(receiverType.equals(ContansOfOa.department)){
				DepartmentTeacherCondition con = new DepartmentTeacherCondition();
				if(departmentIds.length() > 0){
					for(int i=0;i<departments.length;i++){
						con.setDepartmentId(Integer.parseInt(departments[i]));
						num += this.departmentTeacherService.findDepartmentTeacherByCondition(con, null, null).size();
					}
				}
			}else{
				num = this.teacherService.findTeacherListBySchoolId(user.getSchoolId()).size();
			}
				
			//?????????????????????  ???????????????????????????  ???????????????????????????  ????????????
			//?????????????????? ??????????????????????????????????????????
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//?????????mm??????????????????
			Date stDate = sdf.parse(m1.getStarttime());
			long t = stDate.getTime() - 60*60*60;
			Date canModifyDate = new Date(t);
			Date date_today = new Date();
			boolean ck = date_today.after(canModifyDate); 
			if(ck){
				return new ResponseInfomation("notUpdate");
			}
			
				
			meeting.setMeetingNum(num);	
			meeting.setId(id);
			meetingService.modify(meeting);
			
			//????????????????????????????????????
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			meetingUserCondition.setMeetingId(id);
			List<MeetingUser> muList = muService.findMeetingUserByCondition(meetingUserCondition);
			if(muList.size() > 0){
				for(MeetingUser muData : muList){
					muService.remove(muData);
				}
			}
			
			//????????????????????????
			//??????????????? ??????
			MeetingUser mud = null;
			if(meeting.getFanwei().equals(ContansOfOa.department)){
				for(int i=0;i<departments.length;i++){
					mud = new MeetingUser();
					mud.setDepartmentId(Integer.parseInt(departments[i]));
					mud.setIsCanhui(0);
					mud.setMeetingId(id);
					muService.add(mud);
					
					addDepartment(Integer.parseInt(departments[i]), user);
				}
			}
			
			//??????????????? ??????
			if(meeting.getFanwei().equals(ContansOfOa.personer)){
				Teacher teacher = null;
				for(int i = 0; i < teachers.length; i++){
					teacher = this.teacherService.findTeacherById(Integer.valueOf(teachers[i]));
					MeetingUser meetingUser = new MeetingUser();
					meetingUser.setIsCanhui(1);
					meetingUser.setMeetingId(meeting.getId());
					meetingUser.setUserId(teacher.getUserId());
					meetingUser.setUserName(names[i]);
					this.muService.add(meetingUser);
				}
			}
			
			return new ResponseInfomation(meeting.getId(),ResponseInfomation.OPERATION_SUC);
			} catch (Exception e) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}
		
		/**
		 * ?????????????????????
		 */
		@RequestMapping(value = "addMeetingComments")
		@ResponseBody
		public ResponseInfomation addComments(@RequestParam(value = "meetingId") Integer meetingId,
				Comments comments,@CurrentUser UserInfo user){
			try {
				comments.setMeetingId(meetingId);
				comments.setCreateuserId(user.getId());
				comments.setCreatename(user.getRealName());
				this.commentService.add(comments);
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			} catch (Exception e) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
			}
		}
		
		/**
		 * ??????????????????
		 */
		@RequestMapping(value = "addMeetingSummary")
		@ResponseBody
		public ResponseInfomation addSummary(@RequestParam(value = "meetingId") Integer meetingId,
				MeetingSummary summary,@CurrentUser UserInfo user){
			try {
				summary.setMeetingId(meetingId);
				summary.setUserId(user.getId());
				summary.setUserName(user.getRealName());
				this.summaryService.add(summary);
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			} catch (Exception e) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
			}
		}
		
		/**
		 * ??????????????????
		 * ??????????????????????????????
		 * 1?????????????????????
		 * @throws ParseException 
		 */
		@RequestMapping(value="meetingInDetail")
		public ModelAndView meetingInDetail(
				@RequestParam(value = "meetingId") Integer meetingId,Page page,Order order,
				@CurrentUser UserInfo user,Model model) throws ParseException{
			String path = "detail";
			
			//?????????????????????
			addCkNumAndData(meetingId,user);
			
			Meeting meeting = this.meetingService.findMeetingById(meetingId);
			
			EntityFile entity = this.entityFileService.findFileByUUID(meeting.getUploadFile());
			
			//????????????  ????????????????????????
			Date date_today = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//?????????mm??????????????????
			int sum = UtilDate.getLeftDayTime(date_today,sdf.parse(meeting.getStarttime()));
			int day = (int) Math.floor(sum/(60*24));
			int hour = (int) Math.floor((sum%(60*24))/60);
			
			String status = "notStarted";
			//??????????????????????????????
			boolean today_start = date_today.before(sdf.parse(meeting.getStarttime()));
			//??????????????????????????????
			boolean today_end = date_today.before(sdf.parse(meeting.getEndtime()));
			if(!today_start && today_end){
				status = "underway";
			}
			if(!today_end){
				status = "complete";
			}
			
			model.addAttribute("status", status);
			model.addAttribute("day", day);
			model.addAttribute("hour", hour);
			
			//????????????
			CommentsCondition commentsCondition = new CommentsCondition();
			order.setAscending(true);
			order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
			commentsCondition.setMeetingId(meetingId);
			List<CommentsVo> cList = this.commentService.findCommentsVoByCondition(commentsCondition,null,order);
			if(cList.size() > 0){
				String distance = "";
				for(int i=0;i<cList.size();i++){
					//??????????????????
					distance = UtilDate.howAgoDistanceNow(cList.get(i).getCreateDate());
					cList.get(i).setDistanceNowTime(distance);
				}
			}
			
			//????????????
			MeetingSummaryCondition meetingSummaryCondition = new MeetingSummaryCondition();
			meetingSummaryCondition.setMeetingId(meetingId);
			List<MeetingSummary> sList = this.summaryService.findMeetingSummaryByCondition(meetingSummaryCondition);
			
			//????????????
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			meetingUserCondition.setMeetingId(meetingId);
			
			//??????????????? ??????
			if(meeting.getFanwei().equals(ContansOfOa.department)){
				meetingUserCondition.setUserId(null);
				meetingUserCondition.setUserName(null);
				List<MeetingUser> mu = this.muService.findMeetingUserByCondition(meetingUserCondition);
				String depId = "";
				String depName = "";
				if(mu.size() > 0){
					Department department = null;
					for(int i = 0;i<mu.size();i++){
						depId += mu.get(i).getDepartmentId() + ",";
						department = this.departmentService.findDepartmentById(mu.get(i).getDepartmentId());
						if(department!=null){
							depName += department.getName() + ",";
						}
					}
				}
				model.addAttribute("depId", depId.substring(0, depId.length()-1));
				model.addAttribute("depName", depName.substring(0, depName.length()-1));
			}
			
			//??????????????? ??????
			if(meeting.getFanwei().equals(ContansOfOa.personer)){
				meetingUserCondition.setDepartmentId(null);
				List<MeetingUser> mu = this.muService.findMeetingUserByCondition(meetingUserCondition);
				String tNames = "";
				String tIds = "";
				if(mu.size() > 0){
					Teacher teacher = null;
					for(int i=0;i<mu.size();i++){
						tNames = tNames + mu.get(i).getUserName() + ",";
						teacher = this.teacherService.findOfUser(user.getSchoolId(),mu.get(i).getUserId());
						if(teacher!=null){
							tIds = tIds + teacher.getId()+",";
						}
					}
				}
				model.addAttribute("tNames", tNames.substring(0, tNames.length()-1));
				model.addAttribute("tIds", tIds);
			}
			
			SummaryReadUserCondition summaryReadUserCondition = new SummaryReadUserCondition();
			summaryReadUserCondition.setSummaryId(meeting.getSummaryId()==null? -1:meeting.getSummaryId());
			int num = this.sruService.findSummaryReadUserByCondition(summaryReadUserCondition).size();
			model.addAttribute("num", num);
			
			model.addAttribute("clist", cList);
			model.addAttribute("slist", sList);
			model.addAttribute("cListSize",page.getTotalRows());
			model.addAttribute("meeting", meeting);
			model.addAttribute("currrntUser", user.getId());
			model.addAttribute("entity", entity);
			return new ModelAndView(BASE_PATH+path,model.asMap());
		}
		
		//?????????????????????1 ??????????????????????????????
		public void addCkNumAndData(Integer meetingId,UserInfo user){
			//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? ??????????????????
			MeetingSummaryCondition meetingSummaryCondition = new MeetingSummaryCondition();
			meetingSummaryCondition.setMeetingId(meetingId);
			List<MeetingSummary> list = this.summaryService.findMeetingSummaryByCondition(meetingSummaryCondition);
			if(list.size()<=0){
				return;
			}
			
			//???????????????????????????????????????????????????
			//???????????????  ???????????????
			MeetingSummary ms = list.get(0);
			SummaryReadUser msummary = sruService.findBySummaryAndUserId(ms.getId(), user.getId());
			if(msummary!=null){
				return;
			}
			
			//????????????????????????????????????
			if(user.getUserTypes() != null && user.getUserTypes() != ""){
				String[] userType = user.getUserTypes().split(", ");
				if(!Arrays.asList(userType).contains(ContansOfOa.teacher)){
					return;
				}
			}
			
			//?????????????????????
			Meeting meeting = this.meetingService.findMeetingById(meetingId);
			
			//??????????????????????????????????????????????????? ???????????????????????????????????? ????????????
			List<MeetingUser> muList = null;
			List<Integer> userList = new ArrayList<Integer>();
			MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
			meetingUserCondition.setMeetingId(meeting.getId());
			if(meeting.getFanwei().equals(ContansOfOa.department) || meeting.getFanwei().equals(ContansOfOa.personer)){
				muList = muService.findMeetingUserByCondition(meetingUserCondition);
			}
			
			//??????????????????????????????????????????????????????????????????????????????
			if(meeting.getFanwei().equals(ContansOfOa.department)){
				if(muList.size()>0){
					List<DepartmentTeacher> depList = null;
					for(MeetingUser ml : muList){
						Integer depId = ml.getDepartmentId();
						DepartmentTeacherCondition con = new DepartmentTeacherCondition();
						con.setDepartmentId(depId);
						depList = departmentTeacherService.findDepartmentTeacherByCondition(con, null, null);
						if(depList.size() > 0){
							for(DepartmentTeacher dt : depList){
								userList.add(dt.getTeacherId());
							}
							if(!userList.contains(user.getTeacherId())){
								return;
							}
						}
					}
				}
			}
			
			if(meeting.getFanwei().equals(ContansOfOa.personer)){
				if(muList.size() > 0){
					for(MeetingUser ml : muList){
						userList.add(ml.getUserId());
					}
					if(!userList.contains(user.getId())){
						return;
					}
				}
			}
			
			//????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????0 ??????1
			meeting.setSummaryId(ms.getId());
			this.meetingService.modify(meeting);
			
			SummaryReadUser summaryReadUser = new SummaryReadUser();
			summaryReadUser.setSummaryId(ms.getId());
			summaryReadUser.setUserId(user.getId());
			summaryReadUser.setUserName(user.getRealName());
			sruService.add(summaryReadUser);
		}
		
		/**
		 * ????????????
		 */
		@RequestMapping(value = "delMeeting/{id}", method = RequestMethod.DELETE)
		@ResponseBody
		public String deleteNotice(@PathVariable(value = "id") Integer id, Meeting meeting,
				@CurrentUser UserInfo user) {
			try {
				Meeting m = this.meetingService.findMeetingById(id);
				if(m.getFanwei().equals(ContansOfOa.department)){
					delDepartment(m.getDepartmentId(),user);
				}
				
				//??????????????????
				meeting.setId(id);
				this.meetingService.remove(meeting);
				
				//?????????????????????
				MeetingUserCondition meetingUserCondition = new MeetingUserCondition();
				meetingUserCondition.setMeetingId(id);
				 List<MeetingUser> nus = this.muService.findMeetingUserByCondition(meetingUserCondition);
				 if(nus.size() > 0){
					 for(MeetingUser n:nus){
						 this.muService.remove(n);
					 }
				 }
				 
				 //?????????????????????
				 MeetingSummaryCondition msc = new MeetingSummaryCondition();
				 msc.setMeetingId(id);
				 List<MeetingSummary> mscList = summaryService.findMeetingSummaryByCondition(msc);
				 if(mscList.size() > 0){
					 for(MeetingSummary mscData : mscList){
						 this.summaryService.remove(mscData);
					 }
				 }
				 
				 //????????????????????????
				 CommentsCondition ctc = new CommentsCondition();
				 ctc.setMeetingId(id);
				 List<Comments> ctcList = this.commentService.findCommentsByCondition(ctc);
				 if(ctcList.size() > 0){
					 for(Comments ctcData : ctcList){
						 this.commentService.remove(ctcData);
					 }
				 }
				 
				return ResponseInfomation.OPERATION_SUC;
			} catch (Exception e) {
				return ResponseInfomation.OPERATION_FAIL;
			}
		}
		
	    /**
	     * ???????????????
	     * ?????????????????????
	     */
		public void addDepartment(Integer departmentId,UserInfo user){
			MeetingDepartmentCountCondition mCondition = new MeetingDepartmentCountCondition();
			mCondition.setDepartmentId(departmentId);
			mCondition.setOwnerId(user.getSchoolId());
			mCondition.setOwnerType(user.getGroupId());
			List<MeetingDepartmentCount> list = this.meetingDepartmentCountService.findMeetingDepartmentCountByCondition(mCondition);
			MeetingDepartmentCount mdc = new MeetingDepartmentCount();
			if(list.size() > 0){
				Integer num = list.get(0).getMeetingCount();
				mdc.setMeetingCount(num+1);
				mdc.setId(list.get(0).getId());
				this.meetingDepartmentCountService.modify(mdc);
			}else{
				mdc.setMeetingCount(1);
				mdc.setDepartmentId(departmentId);
				mdc.setOwnerId(user.getSchoolId());
				mdc.setOwnerType(user.getGroupId());
				this.meetingDepartmentCountService.add(mdc);
			}
		}
		
		public void delDepartment(Integer departmentId,UserInfo user){
			MeetingDepartmentCountCondition mCondition = new MeetingDepartmentCountCondition();
			mCondition.setDepartmentId(departmentId);
			mCondition.setOwnerId(user.getSchoolId());
			mCondition.setOwnerType(user.getGroupId());
			List<MeetingDepartmentCount> list = this.meetingDepartmentCountService.findMeetingDepartmentCountByCondition(mCondition);
			if(list.size() > 0){
				mCondition.setId(list.get(0).getId());
				Integer num = list.get(0).getMeetingCount();
				mCondition.setMeetingCount(num-1 >= 0 ? num-1 : 0);
				this.meetingDepartmentCountService.modify(mCondition);
				return;
			}
		}
		
		/**
		 * ????????????????????????????????????????????????????????????
		 * 	        ????????????????????????????????????????????????????????????????????????????????????
		 *     ????????????????????????
		 * meetingVo:??????????????????????????? user:??????????????????????????????
		 * @return true:??????????????????  false????????????????????????
		 */
		public boolean isRead(MeetingVo mVo,UserInfo user){
			boolean read = false;
			Integer type = mVo.getFanwei();
			
			//??????????????????????????????????????????
			if(user.getUserTypes() != null && user.getUserTypes() != ""){
				String[] userType = user.getUserTypes().split(", ");
				if(Arrays.asList(userType).contains(ContansOfOa.Administrator)){
					read = true;
					return read;
				}
			}
			
			//???????????????????????????
			if(user.getId().equals(mVo.getCreateuserId())){
				read = true;
				return read;
			}
			
			//????????????????????????
			if(type.equals(ContansOfOa.all)){
				read = true;
				return read;
			}else{
				List<MeetingUser> mu = mVo.getMeetingUser();
				if(mu != null && mu.size()>0){
				//?????????????????????
				if(type.equals(ContansOfOa.department)){
						DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
						dtCondition.setIsDeleted(false);
						List<DepartmentTeacher> dtList = null;
						for(MeetingUser mUser : mu){
							dtCondition.setDepartmentId(mUser.getDepartmentId());
							dtList = this.departmentTeacherService.findDepartmentTeacherByCondition(dtCondition, null, null);
							if(dtList.size() > 0){
							for(DepartmentTeacher dt : dtList){
								if(dt.getTeacherId().equals(user.getTeacherId())){
									read = true;
									break;
								}
							}
							if(read == true){
								break;
							}
							}
						}
						return read;
				}
				//???????????????????????????
				if(type.equals(ContansOfOa.personer)){
						for(MeetingUser mUser : mu){
							if(mUser.getUserId().equals(user.getId()) || user.getId().equals(mVo.getCreateuserId())){
								read = true;
								break;
							}
						}
					}
				return read;
				}
			}
			return read;
		}
		
		/**
		 * ????????????
		 * @param teacher1
		 * @return
		 */
		public List<Teacher> removeRep(List<Teacher> teacher1){
			List<Teacher> teacher = new ArrayList<Teacher>();
			for(Teacher t1 : teacher1){
				boolean flag = true;
				if(teacher!=null){
				for(Teacher t2 : teacher){
					if(t1.getId().equals(t2.getId())){
						flag = false;
						break;
					}
				}
				}
				if(flag){
					teacher.add(t1);
				}
			}
			return teacher;
		}
		
}
