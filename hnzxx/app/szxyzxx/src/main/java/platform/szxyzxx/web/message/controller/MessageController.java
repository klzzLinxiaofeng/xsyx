package platform.szxyzxx.web.message.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherDetailInfo;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.education.im.service.IMService;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.message.model.MessageReceiver;
import platform.education.message.service.MessageReceiverService;
import platform.education.message.service.MessageService;
import platform.education.message.vo.Condition;
import platform.education.message.vo.MessageReceiverCondition;
import platform.education.message.vo.MessageResult;
import platform.education.message.vo.MessageVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.StatusTypeContans;

/**
 * 消息中心控制器
 *<p>
 * Title: MessageController.java 
 * </p>
 *<p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>  
 * @Fuction 方法描述 ：
 * @Author 陈业强   
 * @Version 1.0 
 * @Date 2015年8月11日
 */
@Controller
@RequestMapping("/message/center")
public class MessageController extends BaseController{ 
	
	private final String viewBasePath = "/message";
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private MessageReceiverService messageReceiverService;
	
	@Resource
	private IMService imService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "newPage", required = false) String newPage,
			@RequestParam(value = "teaMes", required = false) String teaMes,
			@ModelAttribute("condition") Condition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		condition.setReceiverId(user.getId());
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setTeacherMessage(false);
		List<MessageVo>  unreadList = messageService.findMessageByCondition(condition);
		List<MessageVo> unreadMessages = getMessage(unreadList);//获取未读的系统消息
		condition.setTeacherMessage(true);//true -- 表示为老师消息   false--表示为系统消息
		List<MessageVo> unreadTeacherList = messageService.findMessageByCondition(condition);
		List<MessageVo> teacherMessages = getMessage(unreadTeacherList);//获取未读的教师消息
		condition.setReadStatus(StatusTypeContans.ONE);//设置为已读
		condition.setTeacherMessage(false);
		List<MessageVo> readedList = messageService.findMessageByCondition(condition);
		List<MessageVo> readedMessages = getMessage(readedList);//获取已读的系统消息
		if(sub != null){
			if(sub.equals("list")){
				viewPath = structurePath("/list");
			}else{
				viewPath = structurePath("/index");
			}
		}else{
			viewPath = structurePath("/index");
		}
		
		/**自定义分页*/
		List<MessageVo> voList = new ArrayList<MessageVo>();
    	int currentPage = page.getCurrentPage();
		int pageSize = 7;
		page.setPageSize(pageSize);
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize - 1;	
		int i = 0;
		for (MessageVo vo : unreadMessages) {
			if (i >= start && i <= end) {
				voList.add(vo);
			}
			i++;
		}
		for (MessageVo vo : readedMessages) {
			if (i >= start && i <= end) {
				voList.add(vo);
			}
			i++;
		}
		int totalRows = unreadMessages.size() + readedMessages.size();
		page.setTotalRows(totalRows);
		page.setCurrentPage(currentPage);
		int totalPages = (totalRows % pageSize) == 0 ? (totalRows / pageSize) : (totalRows / pageSize) + 1 ;
		page.setTotalPages(totalPages);
		
		//添加新首页标记
		if("true".equals(newPage)){
			for (MessageVo vo : voList) {
				vo.setNewPageNum(1);
			}
		}
		
		model.addAttribute("isTeacher", user.getTeacherId());
		model.addAttribute("isStudent", user.getStudentId());
		model.addAttribute("voList", voList);
		model.addAttribute("teacherMessages", teacherMessages);
//		model.addAttribute("page", page);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@SuppressWarnings("deprecation")
	private List<MessageVo>  getMessage(List<MessageVo> volist){
		List<MessageVo> resoults = new ArrayList<MessageVo>(); 
		for(Message message : volist){
			MessageVo vo = new MessageVo();
			BeanUtils.copyProperties(message, vo);
			//设置多久之前
			Date nowDate = new Date();
			Date postTime =  vo.getPostTime();
			Long longAgo = nowDate.getTime() - postTime.getTime();
			int year = nowDate.getYear() - postTime.getYear();
			long day = longAgo / (24 * 60 * 60 * 1000);
		    long hour = (longAgo / (60 * 60 * 1000) - day * 24);
		    long min = ((longAgo / (60 * 1000)) - day * 24 * 60 - hour * 60);
		    long s = (longAgo / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		    String ago = null;
		    if(year == 0){
		    	if(day == 0){
		    		if(hour == 0){
		    			if(min == 0){
		    				ago = s + "秒前";		    			
		    			}else{
				    		ago = min + "分钟前";
				    	}
			    	}else{
			    		ago = hour + "小时前";
			    	}
		    	}else{
		    		ago = day + "天前";
		    	}
		    }else{
		    	ago = year + "年前";
		    }
		    vo.setAgo(ago);
		    resoults.add(vo);
		}
		return resoults;
	}
	
	@RequestMapping(value = "/getCount", method = RequestMethod.POST)
	@ResponseBody
	public Long getCount(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MessageReceiverCondition condition,
			Model model){
		condition.setReceiverId(user.getId());
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		Long count = messageReceiverService.count(condition);
		return count;
    }
	
	@RequestMapping(value = "/updateToReaded", method = RequestMethod.POST)
	@ResponseBody
	public String updateToReaded(@CurrentUser UserInfo user, @ModelAttribute("condition") MessageReceiverCondition condition) {
		condition.setReceiverId(user.getId());
		List<MessageReceiver> messageReceivers = messageReceiverService.findMessageReceiverByCondition(condition);
		String msg = "";
		if(messageReceivers != null && messageReceivers.size() > 0){
			for(MessageReceiver messageReceiver : messageReceivers){
				messageReceiver.setReadStatus(StatusTypeContans.ONE);
				messageReceiverService.modify(messageReceiver);
//				PushMessageUtil.pushMessage(user.getId());
				Long data = messageCount(user.getId());
				try {
					//imService.push(user.getUserName(), data + "");
					imService.push(user.getId(), "xunyun#educloud#web", String.valueOf(data));
				} catch (Exception e) {
					e.printStackTrace();
					msg = "fail";
				}
			}
			msg = "success";
		}else{
			msg = "fail";
		}
		return msg;
	}
	/**
	 * 初始化发送消息页面
	 * @param user
	 * @param model
	 * @param departmentCondition
	 * @param condition
	 * @param type 0默认消息 1留言
	 * @param specialName type为1生效 留言给谁
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/send")
	public ModelAndView send(
			@CurrentUser UserInfo user, Model model,
			@ModelAttribute(value = "departmentCondition")DepartmentCondition departmentCondition,
			@ModelAttribute("teacherCondition") TeacherCondition condition,
			@RequestParam(value="type", defaultValue="0")Integer type,
			@RequestParam(value="userId", required=false)Integer userId) throws UnsupportedEncodingException{
		String viewPath = null;
		/**查找通讯录*/
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<TeacherVo> teacherList = this.teacherService.findTeacherVoByCondition(condition, null, null);
		
		/**留言时，需要跨学校*/
		if(1-type==0) {
			condition = new TeacherCondition();
			condition.setUserId(userId);
			condition.setIsDelete(false);
			List<TeacherVo> result = this.teacherService.findTeacherVoByCondition(condition, null, null);
			if(result.size()>0) {
				TeacherVo vo = result.get(0);
				model.addAttribute("specialName", vo.getName());
				if(vo.getSchoolId()-user.getSchoolId()!=0) {
					teacherList.add(vo);
				}
			}
		}
		/**查询所有部门*/
		/**获取最近发送给消息的教师*/
		Condition messageCondition = new Condition();
		messageCondition.setReceiverId(user.getId());
		messageCondition.setRecordStatus(StatusTypeContans.ZERO);
		messageCondition.setAppId(SysContants.SYSTEM_APP_ID);
		messageCondition.setTeacherMessage(true);
		messageCondition.setPostTime(getSevenAgosDate());//获取七天前的时间
		List<MessageVo> voList = messageService.findTeacherRecord(messageCondition);
		List<TeacherDetailInfo> latestList = new ArrayList<TeacherDetailInfo>();
		List<Integer> idList = new ArrayList<Integer>();
		if(voList.size() > 0 && voList != null){
			for(MessageVo vo : voList){
				if(vo.getPosterId().intValue() != user.getId().intValue()){
					Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), vo.getPosterId());
					if(teacher != null){
						TeacherDetailInfo info = this.teacherService.findTeacherDetailInfoById(teacher.getId());
						if(!idList.contains(teacher.getId())){
							idList.add(teacher.getId());
							latestList.add(info);
						}
					}
				}else{
					Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), vo.getReceiverId());
					if(teacher != null){
						TeacherDetailInfo info = this.teacherService.findTeacherDetailInfoById(teacher.getId());
						if(!idList.contains(teacher.getId())){
							idList.add(teacher.getId());
							latestList.add(info);
						}
					}
				}
			}
		}
		viewPath = structurePath("/send");
		model.addAttribute("type", type);
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("latestList", latestList);
		model.addAttribute("currentUserId",user.getId());
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 保存发送的教师信息
	 * @param user
	 * @param teacherId
	 * @param content
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/sendMessageToTeacher", method = RequestMethod.POST)
	@ResponseBody
	public String sendMessageToTeacher(
			@CurrentUser UserInfo user, 
			@RequestParam(value = "teacherId", required = true)Integer teacherId,
			@RequestParam(value = "content", required = true)String content) {
		String msg = "";
		try {
			Teacher teacher = this.teacherService.findTeacherById(teacherId);
			Message message = new Message();
			message.setAppId(SysContants.SYSTEM_APP_ID);
			message.setContent(content);
			message.setPosterId(user.getId());
			message.setPosterName(user.getRealName());
			message.setRecordStatus(StatusDefaultContans.ZERO);
			message.setTag(MessageCenterContants.TEACHER_MESSAGE);
			MessageResult result = messageService.sendMessage(message, teacher.getUserId());
			//推送消息数到接收者页面
			long data = messageCount(teacher.getUserId());
			//imService.push(teacher.getUserName(), data + "");
			imService.push(teacher.getUserId(), "xunyun#educloud#web", data + "");
			//更新聊天页面记录
			JSONArray json = JSONArray.fromObject(result);
			//imService.push(teacher.getUserName(), json.toString());
			imService.push(teacher.getUserId(), "xunyun#educloud#web", json.toString());
			msg = "success";
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
			return msg;
		}
	}
	@RequestMapping(value = "/getMessageRecord", method = RequestMethod.GET)
//	@ResponseBody
	public void getMessageRecord(
			@CurrentUser UserInfo user, 
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "teacherId", required = true)Integer teacherId){
		Teacher teacher = this.teacherService.findTeacherById(teacherId);
		Condition condition = new Condition(); 
		condition.setRecordStatus(StatusTypeContans.ZERO);
		condition.setTeacherMessage(true);
		condition.setAppId(SysContants.SYSTEM_APP_ID);
		condition.setPosterId(teacher.getUserId());
		condition.setReceiverId(user.getId());
		condition.setPostTime(getSevenAgosDate());//获取七天前的时间
		List<MessageVo> list = messageService.findTeacherRecord(condition);
		
		for(MessageVo vo : list){
			vo.setOwn(user.getId());
		}
		try {
			JSONArray json = JSONArray.fromObject(list);
			//imService.push(user.getUserName(), json.toString());
			imService.push(user.getId(), "xunyun#educloud#web", json.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取七天前的时间
	 * @return
	 * @Author 陈业强
	 */
	private Date getSevenAgosDate(){
		Calendar   canlendar   =   Calendar.getInstance(); 
		canlendar.add(Calendar.DAY_OF_MONTH,-7);//获取7天前的时间 
		Date date = canlendar.getTime();
		return date;
	}
	/**
	 * 标记为已读
	 * @param user
	 * @param id
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/setReaded", method = RequestMethod.POST)
	@ResponseBody
	public String setReaded(
			@CurrentUser UserInfo user, 
			@RequestParam(value = "id",required = true)Integer id) {
		try {
			MessageReceiverCondition condition = new MessageReceiverCondition();
			condition.setMessageId(id);
			condition.setReceiverId(user.getId());
			condition.setReadStatus(StatusTypeContans.ZERO);
			List<MessageReceiver> list = messageReceiverService.findMessageReceiverByCondition(condition);
			if(list.size() > 0){
				for(MessageReceiver mr : list){
					mr.setReadStatus(StatusTypeContans.ONE);
					messageReceiverService.modify(mr);
				}
//				PushMessageUtil.pushMessage(user.getId());
				Long data = messageCount(user.getId());
				imService.push(user.getId(), "xunyun#educloud#web", String.valueOf(data));
				//imService.push(user.getUserName(), data + "");
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	/**
	 * 跳转到回复页面
	 * @param user
	 * @param model
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/to")
	public ModelAndView to(@CurrentUser UserInfo user,@RequestParam(value = "id",required = true)Integer id, Model model){
		String viewPath = null;
		Message message = messageService.findMessageById(id);
		Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), message.getPosterId());
		if(teacher != null){
			model.addAttribute("teacher", teacher);
			model.addAttribute("isTeacher", true);
		}else{
			/**留言时跨学校处理*/
			List<Teacher> teacherList = teacherService.findTeacherByUserId(message.getPosterId());
			if(teacherList.size()>0) {
				teacher = teacherList.get(0);
				model.addAttribute("teacher", teacher);
				model.addAttribute("isTeacher", true);
			} else {
				model.addAttribute("isTeacher", false);
			}
		}
		viewPath = structurePath("/to");
		model.addAttribute("message", message);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	private Long messageCount(Integer userId){
		MessageReceiverCondition condition = new MessageReceiverCondition();
		condition.setReceiverId(userId);
		condition.setReadStatus(StatusTypeContans.ZERO);
		condition.setRecordStatus(StatusTypeContans.ZERO);
		return messageReceiverService.count(condition);
	}
	private void conditionFilter(UserInfo user, Condition condition) {
		Integer appId = condition.getAppId();
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
}
