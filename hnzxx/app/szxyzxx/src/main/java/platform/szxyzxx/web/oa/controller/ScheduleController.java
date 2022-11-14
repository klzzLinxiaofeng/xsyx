package platform.szxyzxx.web.oa.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.oa.model.Schedule;
import platform.education.oa.service.ScheduleService;
import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.vo.ScheduleCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping("/oa/schedule")
public class ScheduleController { 
	
	private final static String viewBasePath = "/oa/schedule";
	
	@Autowired
	@Qualifier("scheduleService")
	private ScheduleService scheduleService;
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Schedule schedule, @CurrentUser UserInfo user) {
		Integer appId = schedule.getAppId();
		if(appId == null) {
			schedule.setAppId(SysContants.SYSTEM_APP_ID);
		}
		schedule.setOwnerId(user.getSchoolId());
		schedule.setOwnerType("1");
		schedule.setUuid(UUIDUtils.getUUID());
		schedule.setPosterName(user.getUserName());
		schedule.setPosterId(user.getId());
		schedule = this.scheduleService.add(schedule);
		return schedule != null ? new ResponseInfomation(schedule.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Schedule schedule = this.scheduleService.findScheduleById(id);
		model.addAttribute("schedule", schedule);
		return new ModelAndView(structurePath("/fullcalendar_form"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Schedule schedule = this.scheduleService.findScheduleById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("schedule", schedule);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Schedule schedule) {
		if (schedule != null) {
			schedule.setId(id);
		}
		try {
			schedule.setIsDeleted(true);
			this.scheduleService.modify(schedule);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Schedule schedule) {
		schedule.setId(id);
		schedule = this.scheduleService.modify(schedule);
		return schedule != null ? new ResponseInfomation(schedule.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	/**
	 * 去到日历界面
	 */
	@RequestMapping(value = "/toCal", method = RequestMethod.GET)
	public ModelAndView toCal(Model model) {
		return new ModelAndView(structurePath("/fullcalendar"), model.asMap());	
	}
	
	/**
	 * 新建事件
	 */
	@RequestMapping(value = "/createCal")
	public String createCal() {
		return structurePath("/fullcalendar_form");	
	}
	
	/**
	 * 数据源初始化
	 */
	@RequestMapping(value = "/dataInIt")
	@ResponseBody
	public Object dataInIt(@CurrentUser UserInfo user,
			@RequestParam(value = "start", required = false) int start,
			@RequestParam(value = "end", required = false) int end,
			@RequestParam(value = "_", required = false) long _) {
		ScheduleCondition condition  = new ScheduleCondition();
		Integer userId = user.getId();
		condition.setIsDeleted(false);
		Integer ownerId = user.getSchoolId();
		String ownerType= "1";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String starttime = df.format(new Date(Long.parseLong(start + "") * 1000));
		String endtime = df.format(new Date(Long.parseLong(end + "") * 1000));
		
		List<Schedule> list = this.scheduleService.findSheduleByDate(ownerId, ownerType, userId, starttime, endtime);
		
		List<HashMap<String, Object>> mlist = new ArrayList<HashMap<String, Object>>();
		
		for (int i = 0; i < list.size(); i++) 
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", list.get(i).getId());
			map.put("title", list.get(i).getContent());
			map.put("start", list.get(i).getPlanStartTime());
			map.put("end", list.get(i).getPlanFinishTime());
			
			mlist.add(map);
		}
		
		return mlist;
	}
	
	//编辑
	@RequestMapping(value = "/getCalDataById", method = RequestMethod.POST)
	@ResponseBody
	public Schedule getCalDataById(
			@RequestParam(value = "id", required = true) Integer id) {
		Schedule schedule = this.scheduleService.findScheduleById(id);
		return schedule;
	}
	
	//每日日程数限制
	@RequestMapping(value = "/checkCalLimit", method = RequestMethod.POST)
	@ResponseBody
	public String checkCalLimit(@CurrentUser UserInfo user,
			@RequestParam(value = "begin", required = true) String begin,
			@RequestParam(value = "end", required = true) String end) throws ParseException {
		int number = 0;
		Integer userId = user.getId();
		Integer ownerId = user.getSchoolId();
		String ownerType= "1";
		List<Schedule> list = this.scheduleService.findSheduleByDate(ownerId, ownerType, userId,null,null);
		
		if(list.size() > 0){
			for(int i = 0;i < list.size(); i++){
				if(checkLimit(begin,end,list.get(i).getPlanStartTime(),list.get(i).getPlanFinishTime())){
					number++;
				}
			}
		}
		
		if(number >= 5){
			return "beyond";
		}
		return "noBeyond";
	}
	
	public boolean checkLimit(String beginDate,String endDate,String beginDataInList,String endDataInList) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		Date begin = sdf.parse(beginDate);
		Date end = sdf.parse(endDate);
		Date beginList = sdf.parse(beginDataInList);
		Date endList = sdf.parse(endDataInList);
		boolean eqBegin = begin.equals(beginList);
		boolean eqEnd = end.equals(endList);
		boolean check1 = begin.after(beginList) && begin.before(endList);
		boolean check2 = end.after(beginList) && end.before(endList);
		boolean check3 = begin.after(beginList) && begin.before(endList);
		boolean check4 = begin.before(beginList) && end.after(endList);
		if(eqBegin || eqEnd || check1 || check2 || check3 || check4){
			return true;
		}
		return false;
	}
	
}
