package platform.szxyzxx.web.teach.controller;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import platform.education.generalTeachingAffair.model.StudentAid;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentAidCondition;
import platform.education.generalTeachingAffair.vo.StudentAidVo;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;




@Controller
@RequestMapping("/teach/studentaid")
public class StudentAidController extends BaseController{
	private static final Logger log = LoggerFactory.getLogger(StudentAidController.class);
	private final static String viewBasePath = "/teach/studentaid";

	@RequestMapping(value = "/studentAidList")
	public ModelAndView getStudentAidList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("studentAidCondition") StudentAidCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		condition.setSchoolId(user.getSchoolId());//添加学校查询条件
		order.setProperty("create_date");
		order.setAscending(false);
		List<StudentAid> studentaidlist = this.studentAidService.findStudentAidByCondition(condition, page, order);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentaidlist,user);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/studentAidList");
		}
		mav.addObject("studentAidList",studentAidVoList);
		mav.setViewName(viewPath);
		return mav;
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

		StudentAidCondition condition = new StudentAidCondition();

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
		String filename = "学生资助信息"+time+".xls";
		condition.setSchoolId(user.getSchoolId());//添加学校查询条件
		List<StudentAid> studentaidlist = this.studentAidService.findStudentAidByCondition(condition, null, null);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentaidlist,user);


		List<Object> list = new ArrayList<Object>();
		for (StudentAidVo vo : studentAidVoList) {
			if(vo.getId()>0){
				list.add(vo);
			}else{
				//去除错误的
			}

		}

		ParseConfig config = SzxyExcelTookit.getConfig("studentAidVo");
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
		StudentAidCondition condition = new StudentAidCondition();
		condition.setStudentId(studentId);
		condition.setIsDelete(false);
		List<StudentAid> studentAidList = this.studentAidService.findStudentAidByCondition(condition);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentAidList, user);
		model.addAttribute("studentAidVoList", studentAidVoList);
		viewPath = structurePath("/viewList");
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * 把对应的实体类转换成对应的vo
	 * @param studentaidlist
	 * @return
	 */
	private List<StudentAidVo> aidToAidVo(List<StudentAid> studentaidlist,UserInfo user) {
		List<StudentAidVo> studentAidVoList = new ArrayList<StudentAidVo>();
		Student student = new Student();
		Team team = new Team();

		try {
			for (StudentAid studentAid : studentaidlist) {
				StudentAidVo studentAidvo = new StudentAidVo();
				student = this.studentService.findStudentById(studentAid.getStudentId());//获取学生姓名
				BeanUtils.copyProperties( studentAidvo,studentAid);
				studentAidvo.setStudentName(student.getName());

				team = this.teamService.findTeamById(studentAid.getTeamId());//获取班级名称，年级名称，学校名称，学年
				SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
				schoolYearCondition.setSchoolId(user.getSchoolId());
				if(team != null){
					studentAidvo.setSchoolYear(team.getSchoolYear());
					schoolYearCondition.setYear(team.getSchoolYear());
				}
				SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
				if(schoolyear != null){
					studentAidvo.setSchoolYearName(schoolyear.getName());
				}
				if(team != null){
					studentAidvo.setTeamName(team.getName());
					studentAidvo.setSchoolId(team.getSchoolId());
					studentAidvo.setGradeId(team.getGradeId());
					Grade grade = this.gradeService.findGradeById(team.getGradeId());//获取学年名称
					if(grade != null){
						studentAidvo.setGradeName(grade.getName());
					}
				}
				studentAidVoList.add(studentAidvo);
				studentAidvo = null;
			}
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}

		return studentAidVoList;
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentAidVo> jsonList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") StudentAidCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		List<StudentAid> studentaidlist = this.studentAidService.findStudentAidByCondition(condition, page, order);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentaidlist,user);
		return studentAidVoList;
	}
	/**
	 * 新增学生资助记录页面
	 * @return
	 */
	@RequestMapping(value = "/addStudentAidPage", method = RequestMethod.GET)
	public ModelAndView addStudentAidPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/teach/studentaid/addStudentAidPage");
		return mav;
	}

	@RequestMapping(value = "/addStudentAid", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addStudentAid(
			StudentAid studentAid,
			@CurrentUser UserInfo user) {
		StudentAid sa = null;
		try{
			sa = this.studentAidService.add(studentAid);
		}catch(Exception e){
			e.printStackTrace();
			log.info("学生资助信息添加错误");
		}
		return sa != null ? new ResponseInfomation(sa.getId(),ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/modifyStudentAid")
	public ModelAndView modifyStudentAid(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		StudentAid studentAid = this.studentAidService.findStudentAidById(id);
		List<StudentAid> studentaidlist =  new ArrayList<StudentAid>();
		studentaidlist.add(studentAid);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentaidlist,user);

		mav.addObject("studentAidVo", studentAidVoList.get(0));
		mav.setViewName("/teach/studentaid/modifyStudentAid");
		return mav;
	}

	@RequestMapping(value = "/detailStudentAid")
	public ModelAndView getStudentAidById(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		StudentAid studentAid = this.studentAidService.findStudentAidById(id);
		List<StudentAid> studentaidlist =  new ArrayList<StudentAid>();
		studentaidlist.add(studentAid);
		List<StudentAidVo> studentAidVoList = aidToAidVo(studentaidlist,user);
		mav.addObject("studentAidVo", studentAidVoList.get(0));
		mav.setViewName("/teach/studentaid/detailStudentAid");
		return mav;
	}


	/**
	 * 更新学生资助
	 * @param grade
	 * @return
	 */
	@RequestMapping("/updateStudentAid")
	@ResponseBody
	public ResponseInfomation updateStudentAid(StudentAid studentAid,
											   @CurrentUser UserInfo user){
		StudentAid studentaid = null;
		studentaid = this.studentAidService.modify(studentAid);

		return studentaid != null ? new ResponseInfomation(studentaid.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}


	/**
	 * 删除学生资助
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteStudentAid(@RequestParam(value="id",required=true) Integer id,StudentAid studentaid) {

		try {
			studentaid.setId(id);
			this.studentAidService.remove(studentaid);

		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}


	@RequestMapping(value = "/updateStudentAid", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateStudentAid(StudentAid studentAid) {
		StudentAid studentaid = this.studentAidService.modify(studentAid);
		ResponseInfomation responseInfomation = studentaid != null ? new ResponseInfomation(studentaid.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		return responseInfomation;
	}

	@RequestMapping(value = "checkAmount", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkAmount(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "inAmount", required = false) String inAmount,
			@RequestParam(value = "oneIncome", required = false) String oneIncome,
			@RequestParam(value = "aidAmount", required = false) String aidAmount,
			@RequestParam(value = "id", required = false) Integer id){

		boolean isAmount = true;
		String moneyPattern = "[0-9]{1,}[.]{0,1}[0-9]{0,2}";
		String money = null;
		if("inAmount".equals(dxlx)){
			money = inAmount;
		}else if("oneIncome".equals(dxlx)){
			money = oneIncome;
		}else if("aidAmount".equals(dxlx)){
			money = aidAmount;
		}

		if("inAmount".equals(dxlx)||"oneIncome".equals(dxlx)||"aidAmount".equals(dxlx)){

			Pattern pattern = Pattern.compile(moneyPattern);
			Matcher matcher = pattern.matcher(money);
			isAmount =  matcher.matches();

		}else{
			isAmount = false;
		}

		return isAmount;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

//	public static void main(String[] args) {
//		boolean isAmount = true;
//		String oneIncome = "122.02";
//		BigDecimal amount = new  BigDecimal("0");;
//		try {
//			amount = new BigDecimal(oneIncome);
//		} catch (Exception e) {
//			isAmount = false;
//		}
//	}

}
