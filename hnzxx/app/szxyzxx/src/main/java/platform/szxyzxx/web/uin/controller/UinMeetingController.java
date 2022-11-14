package platform.szxyzxx.web.uin.controller;

import java.util.ArrayList;
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

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.message.contans.StatusDefaultContans;
import platform.education.message.model.Message;
import platform.education.user.model.UserWeb;
import platform.education.user.service.UserWebService;
import platform.education.user.vo.UserWebCondition;
import platform.service.uin.service.UinMeetService;
import platform.service.uin.vo.ConfsRecord;
import platform.service.uin.vo.InviteeDetail;
import platform.service.uin.vo.InviteeDetailVo;
import platform.service.uin.vo.MeetInfo;
import platform.service.uin.vo.MeetResult;
import platform.service.uin.vo.Meeting;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.MessageCenterContants;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.PushMessageUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.contants.ServiceContants;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/uin/meeting")
public class UinMeetingController extends BaseController{
	private final static String viewBasePath = "/uin/meeting";
	
	@Resource
	private UinMeetService uinMeetService;
	
	@Resource(name = "userWebService")
	private UserWebService userWebService;
	
	private static List<ConfsRecord> recordList = new ArrayList<ConfsRecord>();
	
	/**
	 * 查看今日会议列表
	 * @param user
	 * @param dm
	 * @param sub
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		String username = "13430219430";
//		String password = "033969965";
		String username = user.getUserName();
		String password = user.getInputPassword();
		String name = user.getRealName();
		Integer schoolId = user.getSchoolId();
		UserWeb uw = getUserWeb(schoolId);
		try {
			List<MeetInfo> list = uinMeetService.findAllConfs(username, password,name,uw.getAccount(),ServiceContants.DEFAULT_PWD,uw.getExt());
			if ("list".equals(sub)) {
				viewPath = structurePath("/list");
			} else {
				viewPath = structurePath("/index");
			}
			model.addAttribute("currentUser", user);
			model.addAttribute("items", list);
		} catch (Exception e) {
			model.addAttribute("title", "今日会议");
			return new ModelAndView(structurePath("/no_access"), model.asMap());
		}
//		List<MeetInfo> list = uinMeetService.findAllConfs(user.getUserName(), user.getPassword());
		return new ModelAndView(viewPath, model.asMap());
	}
	/**
	 * 跳转到创建页面
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	/**
	 * 创建会议
	 * @param meeting
	 * @param user
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			Meeting meeting,
			@RequestParam(value = "startTime", required = false)Date startTime, 
			@CurrentUser UserInfo user) {
		if(startTime != null){
			meeting.setStartTime(startTime.getTime() + "");
		}
//		meeting.setUserName("13430219430");
//		meeting.setUserPswd("033969965");
		meeting.setUserName(user.getUserName());
		meeting.setUserPswd(user.getInputPassword());
		Integer schoolId = user.getSchoolId();
		UserWeb uw = getUserWeb(schoolId);
		MeetResult result = uinMeetService.CreateMeeting(meeting,uw.getAccount(),ServiceContants.DEFAULT_PWD,uw.getExt());
		
		//发送消息通知
		List<Teacher> list = teacherService.findTeacherListBySchoolId(user.getSchoolId());
		List<Integer> pushTeacherIds = new ArrayList<Integer>();
		for(Teacher t : list){
			pushTeacherIds.add(t.getUserId());
		}
		Message message = new Message();
		message.setAppId(SysContants.SYSTEM_APP_ID);
		message.setContent("您有新的视频会议可参加！");
		message.setPosterId(user.getId());
		message.setPosterName(user.getUserName());
		message.setRecordStatus(StatusDefaultContans.ZERO);
		message.setTag(MessageCenterContants.UIN_NEW_MEETING);
		PushMessageUtil.sendMessage(message, pushTeacherIds, true);
		return result != null ? new ResponseInfomation(result.getMeetID(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 结束会议
	 * @param meetId
	 * @param user
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/closeConf", method = RequestMethod.POST)
	@ResponseBody
	public String closeConf(@RequestParam(value = "meetId", required = false)Integer meetId, @CurrentUser UserInfo user) {
//		String username = "13430219430";
//		String password = "033969965";
		String username = user.getUserName();
		String password = user.getInputPassword();
		String name = user.getNickName();
		Integer schoolId = user.getSchoolId();
		UserWeb uw = getUserWeb(schoolId);
		String msg = "";
		try {
			uinMeetService.closeConf(username, password,name, meetId,uw.getAccount(),ServiceContants.DEFAULT_PWD,uw.getExt());
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "fail";
		}
		return msg;
	}
	/**
	 * 会议记录
	 * @param user
	 * @param dm
	 * @param sub
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/findConfRecord")
	public ModelAndView record(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "meetId", required = false) Integer meetId,
			@RequestParam(value = "startTime", required = false) Date startTime,
			@RequestParam(value = "endTime", required = false) Date endTime,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		String username = "13430219430";
//		String password = "033969965";
		String username = user.getUserName();
		String password = user.getInputPassword();
		String name = user.getNickName();
		Integer schoolId = user.getSchoolId();
		UserWeb uw = getUserWeb(schoolId);
		try {
			List<ConfsRecord> list =uinMeetService.findConfRecord(username, password, name, meetId, 
					startTime == null ? null : startTime.getTime() + "", endTime == null ? null : endTime.getTime()
							+ "",uw.getAccount(),ServiceContants.DEFAULT_PWD,uw.getExt());
			recordList = list;
			if ("list".equals(sub)) {
				viewPath = structurePath("/record_list");
			} else {
				viewPath = structurePath("/record_index");
			}
			/**自定义分页*/
			List<ConfsRecord> voList = new ArrayList<ConfsRecord>();
			int currentPage = page.getCurrentPage();
			int pageSize = page.getPageSize();
			int start = (currentPage - 1) * pageSize;
			int end = currentPage * pageSize - 1;	
			int i = 0;
			for (ConfsRecord vo : list) {
				if (i >= start && i <= end) {
					voList.add(vo);
				}
				i++;
			}
			int totalRows = list.size();
			page.setTotalRows(totalRows);
			page.setCurrentPage(currentPage);
			int totalPages = (totalRows % pageSize) == 0 ? (totalRows / pageSize) : (totalRows / pageSize) + 1 ;
			page.setTotalPages(totalPages);
			model.addAttribute("items", voList);
			model.addAttribute("page", page);
			return new ModelAndView(viewPath, model.asMap());
		} catch (Exception e) {
			model.addAttribute("title", "会议记录");
			return new ModelAndView(structurePath("/no_access"), model.asMap());
		}
	}
	/**
	 * 查看与会者详情
	 * @param invitees json数据
	 * @param model
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam(value = "index") Integer index, Model model,HttpServletRequest request,HttpServletResponse response) {
		List<InviteeDetail> list = recordList.get(index).getInvitees();
		List<InviteeDetailVo> voList = new  ArrayList<InviteeDetailVo>();
		for(InviteeDetail idt : list){
			InviteeDetailVo vo = new InviteeDetailVo();
			BeanUtils.copyProperties(idt, vo);
			Long enrollmenttime = Long.parseLong(idt.getEnrollmenttime());
			Long leavetime = Long.parseLong(idt.getLeavetime());
			Date enrollmentDate = new Date();
			enrollmentDate.setTime(enrollmenttime);
			Date leaveDate = new Date();
			leaveDate.setTime(leavetime);
			vo.setEnrollmentDate(enrollmentDate);
			vo.setLeaveDate(leaveDate);
			voList.add(vo);
		}
		model.addAttribute("list", voList);
		return new ModelAndView(structurePath("/detail"));
	}
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	private UserWeb getUserWeb(Integer schoolId){
		UserWeb uw = new UserWeb();
		if(schoolId != null){
			UserWebCondition condition = new UserWebCondition();
			condition.setSchoolId(schoolId);
			condition.setServiceType(ServiceContants.SERVICE_TYPE_HUI_YI);
			condition.setAccountType(ServiceContants.AC_TYPE_XUE_XIAO);
			List<UserWeb> uwList = userWebService.findUserWebByCondition(condition);
			if(uwList.size() > 0){
				uw = uwList.get(0);
			}
		}
		return uw;
	}
}
