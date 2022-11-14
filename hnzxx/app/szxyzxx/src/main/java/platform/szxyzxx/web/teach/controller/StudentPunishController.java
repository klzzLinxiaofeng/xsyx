package platform.szxyzxx.web.teach.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentPunish;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import platform.education.generalTeachingAffair.vo.StudentPunishCondition;
import platform.education.generalTeachingAffair.vo.StudentPunishVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;




@Controller
@RequestMapping("/teach/studentPunish")
public class StudentPunishController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/studentPunish";
	private static final Logger log = LoggerFactory.getLogger(StudentPunishController.class);

	@RequestMapping(value = "/studentPunish")
	public ModelAndView getStudentPunishList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("studentPunishCondition") StudentPunishCondition studentPunishCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		ModelAndView mov = new ModelAndView();
		order.setProperty("create_date");
		order.setAscending(false);
		studentPunishCondition.setSchoolId(user.getSchoolId());//添加学校查询条件
		List<StudentPunish> studentPunish = this.studentPunishService.findStudentPunishByCondition(studentPunishCondition, page, order);
		List<StudentPunishVo> studentPunishList = this.punishToAidPunishVo(studentPunish,user);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/studentPunishList");
		}
		mov.addObject("studentPunishList", studentPunishList);
		mov.addObject("page", page);
		mov.setViewName(viewPath);

		return mov;
	}
	
	@RequestMapping(value = "/downLoadExcel")
	@ResponseBody
	public void downLoadExcel(
			@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "studentId", required = false) Integer studentId
			,HttpServletResponse response,
			HttpServletRequest request){
			
		    StudentPunishCondition condition = new StudentPunishCondition();
		    if(schoolYear != null&&!"".equals(schoolYear.trim())){
		    	condition.setSchoolYear(schoolYear);
		    }else{
		    	condition.setSchoolYear(null);
		    }
			condition.setGradeId(gradeId);
			condition.setTeamId(teamId);
			condition.setStudentId(studentId);
			condition.setSchoolId(user.getSchoolId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(new Date());
			String filename = "学生处分信息"+time+".xls";
			condition.setSchoolId(user.getSchoolId());//添加学校查询条件
			List<StudentPunish> studentPunish = this.studentPunishService.findStudentPunishByCondition(condition, null, null);
			List<StudentPunishVo> studentPunishList = this.punishToAidPunishVo(studentPunish,user);
			
		
			List<Object> list = new ArrayList<Object>();
			for (StudentPunishVo vo : studentPunishList) {
				if(vo.getId()>0){
					list.add(vo);
				}else{
					//去除错误的
				}
				
			}
			
			ParseConfig config = SzxyExcelTookit.getConfig("studentPunishVo");
			Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
			Map<Object, Object> isRepealMaps = new HashMap<Object, Object>();
			isRepealMaps.put(false, "否");
			isRepealMaps.put(true, "是");
			
			codeWithValueMaps.put("isRepealMaps", isRepealMaps);
			config.setCodeWithValueMaps(codeWithValueMaps);
			try {
				SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(
			@CurrentUser UserInfo user,
			@RequestParam(value = "studentId", required = true) Integer studentId,
			Model model) {
		String viewPath = null;
		StudentPunishCondition condition = new StudentPunishCondition();
		condition.setStudentId(studentId);
		condition.setIsDelete(false);
		List<StudentPunish> studentPunishList = this.studentPunishService.findStudentPunishByCondition(condition);
		List<StudentPunishVo> studentPunishVoList = this.punishToAidPunishVo(studentPunishList, user);
		model.addAttribute("studentPunishVoList", studentPunishVoList);
		viewPath = structurePath("/viewList");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentPunish> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") StudentPunishCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;

		condition.setIsDelete(false);
		List<StudentPunish> studentPunishList =this.studentPunishService.findStudentPunishByCondition(condition, page, order);
		return studentPunishList;
	}
	
	@RequestMapping(value = "/addStudentPunishPage", method = RequestMethod.GET)
	public ModelAndView addStudentPunishPage() {
		return new ModelAndView(structurePath("/addStudentPunish"));
	}

	@RequestMapping(value = "/addStudentPunish", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addStudentPunish(StudentPunish studentPunish, @CurrentUser UserInfo user) {
		StudentPunish studentpunish = null;
		try {
			studentpunish = this.studentPunishService.add(studentPunish);
		} catch (Exception e) {
			log.error("学生处分信息添加错误");
			e.printStackTrace();
		}
	
		 
		return studentpunish != null ? new ResponseInfomation(studentpunish.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/modifyStudentPunishPage", method = RequestMethod.GET)
	public ModelAndView modifyStudentPunishPage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mov = new ModelAndView();
		StudentPunish studentPunish = this.studentPunishService.findStudentPunishById(id);
		
		List<StudentPunishVo> studentPunishVoList = new ArrayList<StudentPunishVo>();
		List<StudentPunish> studentPunishList = new ArrayList<StudentPunish>();
		
		studentPunishList.add(studentPunish);
		studentPunishVoList = this.punishToAidPunishVo(studentPunishList,user);
		
		mov.addObject("studentPunish", studentPunishVoList.get(0));
		mov.setViewName(structurePath("/input"));
	
		return mov;
	}
	
	@RequestMapping(value = "/detailStudentPunish", method = RequestMethod.GET)
	public ModelAndView getStudentPunish(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		
		ModelAndView mov = new ModelAndView();
		StudentPunish studentPunish = this.studentPunishService.findStudentPunishById(id);
		List<StudentPunishVo> studentPunishVoList = new ArrayList<StudentPunishVo>();
		List<StudentPunish> studentPunishList = new ArrayList<StudentPunish>();
		studentPunishList.add(studentPunish);
		studentPunishVoList = this.punishToAidPunishVo(studentPunishList,user);
		mov.addObject("studentPunish", studentPunishVoList.get(0));
		mov.setViewName(structurePath("/detailStudentPunish"));
		
		return mov;
	}

	@RequestMapping(value = "deleteStudentPunish", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteStudentPunish( StudentPunish studentPunish) {
		
		
		try {
			studentPunish.setIsDelete(true);
			this.studentPunishService.remove(studentPunish);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/updateStudentPunish", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateStudentPunish( StudentPunish studentPunish) {
	
		studentPunish = this.studentPunishService.modify(studentPunish);
		return studentPunish != null ? new ResponseInfomation(studentPunish.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	/**
	 * 把对应的实体类转换成对应的vo
	 * @param 
	 * @return
	 */
	private List<StudentPunishVo> punishToAidPunishVo(List<StudentPunish> studentPunishlist,UserInfo user) {
		List<StudentPunishVo> studentPunishVoList = new ArrayList<StudentPunishVo>();
		Student student = new Student();
		Team team = new Team();

		
			for (StudentPunish studentPunish : studentPunishlist) {
				StudentPunishVo studentPunishVo = new StudentPunishVo();
				student = this.studentService.findStudentById(studentPunish.getStudentId());//获取学生姓名
				if(student == null){
					student = new Student();
				}
				//BeanUtils.copyProperties( studentPunishVo,studentPunish);
				this.copyProperties( studentPunishVo,studentPunish);
				studentPunishVo.setStudentName(student.getName());
				
				team = this.teamService.findTeamById(studentPunish.getTeamId());//获取班级名称，年级名称，学校名称，学年
				if(team == null){
					team = new Team();
				}
				studentPunishVo.setSchoolYear(team.getSchoolYear());
				SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
				schoolYearCondition.setSchoolId(user.getSchoolId());
				schoolYearCondition.setYear(team.getSchoolYear());
				SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
				if(schoolyear == null){
					schoolyear = new SchoolYear();
				}
				studentPunishVo.setSchoolYearName(schoolyear.getName());
				studentPunishVo.setTeamName(team.getName());
				studentPunishVo.setSchoolId(team.getSchoolId());
				studentPunishVo.setGradeId(team.getGradeId());
				Grade grade = this.gradeService.findGradeById(team.getGradeId());//获取学年名称
				if(grade == null){
					grade = new Grade();
				}
				studentPunishVo.setGradeName(grade.getName());
				studentPunishVoList.add(studentPunishVo);
				studentPunishVo = null;
			}
		
		
		return studentPunishVoList;
	}

	private void copyProperties(StudentPunishVo studentPunishVo,StudentPunish studentPunish){
		studentPunishVo.setCreateDate(studentPunish.getCreateDate());
		studentPunishVo.setId(studentPunish.getId());
		studentPunishVo.setIsDelete(studentPunish.getIsDelete());
		studentPunishVo.setIsRepeal(studentPunish.getIsRepeal());
		studentPunishVo.setModifyDate(studentPunish.getModifyDate());
		studentPunishVo.setPunishCause(studentPunish.getPunishCause());
		studentPunishVo.setPunishDay(studentPunish.getPunishDay());
		studentPunishVo.setPunishEndDay(studentPunish.getPunishEndDay());
		studentPunishVo.setPunishType(studentPunish.getPunishType());
		studentPunishVo.setRemark(studentPunish.getRemark());
		studentPunishVo.setRepealDay(studentPunish.getRepealDay());
		studentPunishVo.setStudentId(studentPunish.getStudentId());
		studentPunishVo.setTeamId(studentPunish.getTeamId());
		
	}

}
