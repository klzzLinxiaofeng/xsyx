package platform.szxyzxx.web.teach.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 学生学号
 * @author tangzh
 *
 */
@Controller
@RequestMapping("/teach/student")
public class StudentNumberController extends BaseController{
private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	private final static String BASE_PATH = "/teach/studentNumber/";
	
	@RequestMapping("/studentNumber")
	public ModelAndView getStudentNum(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setIsDelete(false);
			List<StudentVo> studentVoList = null;
			
			Long manNum = null;
			Long womanNum = null;
			Long totalNum = null;
			Boolean interrupteur = true;
			if(studentCondition != null){
				if(studentCondition.getTeamId() != null){
					interrupteur = jobControlService.studentArchiveCanEdit(studentCondition.getTeamId());
					
					studentCondition.setSex("1");
					manNum = studentService.count(studentCondition);
					studentCondition.setSex("2");
					womanNum = studentService.count(studentCondition);
					studentCondition.setSex(null);
					totalNum = studentService.count(studentCondition);
				}
			}
			
			if("list".equals(sub)){
				viewPath= BASE_PATH + "index";
				studentVoList = studentService.findStudentVoByCondition(studentCondition,page, Order.asc("number"));
			}else{
				viewPath= BASE_PATH + "index";
			}
			mav.addObject("manNum", manNum);
			mav.addObject("womanNum", womanNum);
			mav.addObject("totalNum", totalNum);
			mav.addObject("studentList", studentVoList);
			mav.addObject("interrupteur",interrupteur);
			//mav.addObject("schoolYearList",schoolYearList);
			mav.setViewName(viewPath);
		}catch (Exception e) {
			log.info("查询学生学号列表异常...");
			e.printStackTrace();
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/schoolTerm/json", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") SchoolTermCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		List<SchoolTerm> st = schoolTermService.findSchoolTermByCondition(
				condition, page, order);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();
		map.put("begin", df.format(st.get(0).getBeginDate()));
		map.put("end", df.format(st.get(0).getFinishDate()));

		JSONObject json = JSONObject.fromObject(map);
		return json;
	}
	
	private void conditionFilter(UserInfo userInfo,
			SchoolTermCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		if (schoolId == null) {
			if (userInfo != null) {
				condition.setSchoolId(userInfo.getSchoolId());
			}
		}
	}
	
	@RequestMapping(value = "/studentNumber/list")
	public ModelAndView list(
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			Model mav){
		String path = "list";
		try {
			Team team = teamService.findTeamById(teamId);
			
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setIsDelete(false);
			List<StudentVo> studentVoList = null;
			studentVoList = studentService.findStudentVoByCondition(studentCondition,null, Order.asc("number"));
			
			mav.addAttribute("team", team);
			mav.addAttribute("studentVoList", studentVoList);
		} catch (Exception e) {
			log.info("查询学生学号列表异常...");
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, mav.asMap());
	}
	
	/**
	 * 查询某个教师所担任的班主任或者任课 的班级
	 */
	@RequestMapping("/getTeam")
	@ResponseBody
	public List<TeamTeacherVo> findGrade(@CurrentUser UserInfo user, @RequestParam(value = "schoolYear", required = false) String schoolYear){
		List<TeamTeacherVo> teamList1=new ArrayList<TeamTeacherVo>();
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(user.getSchoolId());
		teamTeacherCondition.setTeacherId(user.getTeacherId());
		teamTeacherCondition.setSchoolYear(schoolYear);
		List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherVoByGroupBy(teamTeacherCondition);
		teamList1=removeRepTeam(teamList);
		return teamList1;
	}
	
	/**
	 * 去掉重复的班级
	 */
	public List<TeamTeacherVo> removeRepTeam(List<TeamTeacherVo> nList) {
		List<TeamTeacherVo> teachers = new ArrayList<TeamTeacherVo>();

		for (TeamTeacherVo n1 : nList) {
			boolean flag = true;
			if (teachers != null) {
				for (TeamTeacherVo n2 : teachers) {
					if (n1.getTeamId().equals(n2.getTeamId())) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				teachers.add(n1);
			}
		}
		return teachers;
	}
	
	/**
	 * 学号录入页面
	 */
	@RequestMapping(value = "/studentNumber/enterIndex")
	public ModelAndView enterIndex(@CurrentUser UserInfo user,
							@RequestParam(value = "type",required = false) String type,
							@RequestParam(value = "dm",required = false) String dm,
							Model model){
		String path="enter_index";
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCodeCurrent", schoolTermCurrent.getSchoolTermCode());
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	@RequestMapping(value = "/studentNumber/enterList")
	public ModelAndView enterList(
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			Model mav){
		String path = "enter_list";
		ArrayList<Integer> nlist = new ArrayList<>();
		Integer num = 0;
		try {
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setIsDelete(false);
			List<StudentVo> studentVoList = null;
			studentVoList = studentService.findStudentVoByCondition(studentCondition,null, Order.asc("number"));
			
			//自动补齐未填学号，从1开始补，重复的+1继续判断去补
			for (StudentVo stu1 : studentVoList) {
				if(stu1.getNumber() != null && !"".equals(stu1.getNumber())){
					nlist.add(stu1.getNumber());
				}
			}
			for (StudentVo stu : studentVoList) {
				if(stu.getNumber() == null || "".equals(stu.getNumber())){
					for (int j = 0; j < studentVoList.size() + 1; j++) {
						num++;
						if(!nlist.contains(num)){
							stu.setNumber(num);
							nlist.add(num);
							break;
						}
					}
				}
			}
			
			mav.addAttribute("studentVoList", studentVoList);
		} catch (Exception e) {
			log.info("查询学生学号列表异常...");
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, mav.asMap());
	}
	
	@RequestMapping(value = "/studentNumber/sortList")
	public ModelAndView sortList(
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			Model mav){
		String path = "enter_list";
		ArrayList<Integer> nlist = new ArrayList<>();
		Integer num = 0;
		try {
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setIsDelete(false);
			List<StudentVo> studentVoList = null;
			studentVoList = studentService.findStudentVoByCondition(studentCondition,null, Order.asc("number"));
			//打乱学生顺序去排号
			Collections.shuffle(studentVoList);
			//自动重新排序学号
			for (StudentVo stu : studentVoList) {
				num++;
				stu.setNumber(num);
			}
			
			mav.addAttribute("studentVoList", studentVoList);
		} catch (Exception e) {
			log.info("查询学生学号列表异常...");
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, mav.asMap());
	}
	
	@RequestMapping(value = "/enterSave")
	@ResponseBody
	public ResponseInfomation enterSave(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			@RequestParam(value = "json",required = false) String json){
		List<StudentVo> list = new ArrayList<>();
		try {
			JSONArray array = JSONArray.fromObject(json);
			list = JSONArray.toList(array, new StudentVo(), new JsonConfig());
			//ts用来modify  TeamStudent中的number
			TeamStudent ts = null;
			//teamStudentCondition 查询条件用来查出对应TeamStudent的Id
			TeamStudentCondition teamStudentCondition = null;
			for (StudentVo stu : list) {
				
				teamStudentCondition = new TeamStudentCondition();
				teamStudentCondition.setStudentId(stu.getId());
				teamStudentCondition.setTeamId(teamId);
				List<TeamStudent> tsList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
				
				if(tsList != null && tsList.size() >0){
					ts = new TeamStudent();
					ts.setId(tsList.get(0).getId());
					ts.setNumber(stu.getNumber());
					TeamStudent modify = teamStudentService.modify(ts);
				}
				
			}
			
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
	}
	
}


