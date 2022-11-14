package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

/**
 * 学生管理下的学生考勤
 * 
 * @author huangyanchun
 *
 */
@Controller
@RequestMapping("/teach/studentCheckAttendance")
public class StudentCheckAttendanceController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(StudentCheckAttendanceController.class);
	private final static String viewBasePath = "/teach/studentCheckAttendance";

	/**
	 * 考勤类型
	 */
	// 事假
	private final static String shijia = "0";
	// 病假
	private final static String bingjia = "1";
	// 缺课
	private final static String queke = "2";
	// 旷课
	private final static String kuangke = "3";
	// 迟到
	private final static String chidao = "4";
	// 早退
	private final static String zaotui = "5";

	@RequestMapping(value = "/list")
	public ModelAndView getstudentCheckAttendanceList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") StudentCheckAttendanceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "id");

		condition.setSchoolId(user.getSchoolId());
		/*
		 * // 获取该学校当前学年 SchoolTermCurrent stc = this.schoolTermCurrentService
		 * .findSchoolTermCurrentBySchoolId(user.getSchoolId());
		 * condition.setSchoolYearId(stc.getSchoolYearId());
		 */

		if (condition.getSchoolYearId() != null) {

			// 根据year得到学年的id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setYear(String.valueOf(condition.getSchoolYearId()));
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setIsDelete(false);
			SchoolYear sy = this.schoolYearService
					.findSchoolYearByYear(syCondition);
			if (sy != null) {
				condition.setSchoolYearId(sy.getId());
			}
		}

		// 学生出勤列表
		List<StudentCheckAttendanceVo> scaListVo = this.studentCheckAttendanceService
				.findGroupVoByStudentId(condition, page, order);

		// 学生事假情况
		List<StudentCheckAttendanceVo> vo0 = attendanceList(user, null, shijia, order);

		// 学生病假情况
		List<StudentCheckAttendanceVo> vo1 = attendanceList(user, null, bingjia, order);

		// 学生缺课情况
		List<StudentCheckAttendanceVo> vo2 = attendanceList(user, null, queke, order);

		// 学生旷课情况
		List<StudentCheckAttendanceVo> vo3 = attendanceList(user, null, kuangke, order);

		// 学生迟到情况
		List<StudentCheckAttendanceVo> vo4 = attendanceList(user, null, chidao, order);

		// 学会早退情况
		List<StudentCheckAttendanceVo> vo5 = attendanceList(user, null, zaotui, order);

		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}

		model.addAttribute("studentCheckAttendanceList0", vo0);
		model.addAttribute("studentCheckAttendanceList1", vo1);
		model.addAttribute("studentCheckAttendanceList2", vo2);
		model.addAttribute("studentCheckAttendanceList3", vo3);
		model.addAttribute("studentCheckAttendanceList4", vo4);
		model.addAttribute("studentCheckAttendanceList5", vo5);
		model.addAttribute("studentCheckAttendanceList", scaListVo);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(
			@CurrentUser UserInfo user,
			@RequestParam(value = "studentId", required = true) Integer studentId,
			Model model) {
		String viewPath = null;
		StudentCheckAttendanceCondition condition = new StudentCheckAttendanceCondition();

		condition.setSchoolId(user.getSchoolId());
		condition.setStudentId(studentId);

		// 某个学生出勤列表
		List<StudentCheckAttendanceVo> scaListVo = this.studentCheckAttendanceService
				.findGroupVoByStudentId(condition, null, null);

		// 事假
		List<StudentCheckAttendanceVo> vo0 = attendanceList(user, null, shijia, null);

		// 病假
		List<StudentCheckAttendanceVo> vo1 = attendanceList(user, null, bingjia, null);

		// 缺课
		List<StudentCheckAttendanceVo> vo2 = attendanceList(user, null, queke, null);

		// 旷课
		List<StudentCheckAttendanceVo> vo3 = attendanceList(user, null, kuangke, null);

		// 迟到
		List<StudentCheckAttendanceVo> vo4 = attendanceList(user, null, chidao, null);

		// 早退
		List<StudentCheckAttendanceVo> vo5 = attendanceList(user, null, zaotui, null);

		model.addAttribute("studentCheckAttendanceList0", vo0);
		model.addAttribute("studentCheckAttendanceList1", vo1);
		model.addAttribute("studentCheckAttendanceList2", vo2);
		model.addAttribute("studentCheckAttendanceList3", vo3);
		model.addAttribute("studentCheckAttendanceList4", vo4);
		model.addAttribute("studentCheckAttendanceList5", vo5);
		model.addAttribute("studentCheckAttendanceList", scaListVo);
		viewPath = structurePath("/viewList");
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentCheckAttendance> jsonList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") StudentCheckAttendanceCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;

		return this.studentCheckAttendanceService
				.findStudentCheckAttendanceByCondition(condition, page, order);
	}

	/**
	 * 添加学生出勤信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView addAttendancePage(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "isCK", required = false) String isCK,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user,
			Model model) {
		model.addAttribute("schoolId", user.getSchoolId());
		order.setAscending(true);
		order.setProperty("school_year_id");
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 保存学生出勤信息
	 * 
	 * @param studentCheckAttendance
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addAttendance(
			StudentCheckAttendance studentCheckAttendance,
			@CurrentUser UserInfo user) {
		// 根据年份查询对应的schoolYearId
		SchoolYearCondition syCondition = new SchoolYearCondition();
		syCondition.setSchoolId(user.getSchoolId());
		syCondition.setYear(String.valueOf(studentCheckAttendance
				.getSchoolYearId()));
		SchoolYear sy = this.schoolYearService
				.findSchoolYearByYear(syCondition);
		// 根据学生id查询对应的学生姓名
		TeamStudent ts = new TeamStudent();
		if (studentCheckAttendance != null) {
			ts = this.teamStudentService.findUnique(
					studentCheckAttendance.getTeamNumber(),
					studentCheckAttendance.getstudentId());
		}

		try {
			studentCheckAttendance.setSchoolId(user.getSchoolId());
			studentCheckAttendance.setSchoolYearId(sy.getId());
			studentCheckAttendance.setStudentName(ts.getName());
			studentCheckAttendance.setIsDeleted(0);
			studentCheckAttendance.setModifyDate(new Date());
			studentCheckAttendance = this.studentCheckAttendanceService
					.add(studentCheckAttendance);
		} catch (Exception e) {
			log.info("新增信息异常..");
			e.printStackTrace();
		}

		return studentCheckAttendance != null ? new ResponseInfomation(
				studentCheckAttendance.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 学生出勤详情
	 * 
	 * @param id
	 * @param dm
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "xn", required = true) Integer xn,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user, Model model) {

		StudentCheckAttendance studentCheckAttendance = this.studentCheckAttendanceService
				.findStudentCheckAttendanceById(id);

		Integer schoolId = user.getSchoolId();
		Integer studentId = studentCheckAttendance.getstudentId();
		// attendanceType=0 事假
		List<StudentCheckAttendance> scaList0 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, shijia);

		// attendanceType=1 病假
		List<StudentCheckAttendance> scaList1 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, bingjia);

		// attendanceType=2 缺课
		List<StudentCheckAttendance> scaList2 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, queke);

		// attendanceType=3 旷课
		List<StudentCheckAttendance> scaList3 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, kuangke);

		// attendanceType=4 迟到
		List<StudentCheckAttendance> scaList4 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, chidao);

		// attendanceType=5 早退
		List<StudentCheckAttendance> scaList5 = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, zaotui);

		// 根据schoolYearId得到对应的学年
		SchoolYear sy = this.schoolYearService
				.findSchoolYearById(studentCheckAttendance.getSchoolYearId());
		// 根据gradeId得到对应的年级名称
		Grade g = this.gradeService.findGradeById(studentCheckAttendance
				.getGradeId());
		// 根据teamId得到对应的班级名称
		Team t = this.teamService.findTeamById(studentCheckAttendance
				.getTeamNumber());

		model.addAttribute("isCK", "disable");
		model.addAttribute("grade", g);
		model.addAttribute("team", t);
		model.addAttribute("scas", studentCheckAttendance);
		model.addAttribute("schoolYear", sy);
		model.addAttribute("studentCheckAttendanceList0", scaList0);
		model.addAttribute("studentCheckAttendanceList1", scaList1);
		model.addAttribute("studentCheckAttendanceList2", scaList2);
		model.addAttribute("studentCheckAttendanceList3", scaList3);
		model.addAttribute("studentCheckAttendanceList4", scaList4);
		model.addAttribute("studentCheckAttendanceList5", scaList5);
		return new ModelAndView(structurePath("/view"), model.asMap());
	}

	/**
	 * 针对某个人某个出勤类型的查看
	 * 
	 * @param id
	 * @param xn
	 * @param attendanceType
	 * @param dm
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/oneView", method = RequestMethod.GET)
	public ModelAndView otherViewer(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "xn", required = true) Integer xn,
			@RequestParam(value = "attendanceType", required = true) String attendanceType,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user, Model model) {
		StudentCheckAttendance studentCheckAttendance = this.studentCheckAttendanceService
				.findStudentCheckAttendanceById(id);
		Integer schoolId = user.getSchoolId();
		Integer studentId = studentCheckAttendance.getstudentId();
		List<StudentCheckAttendance> scaList = this.studentCheckAttendanceService
				.findUnique(schoolId, studentId, attendanceType);

		// 根据schoolYearId得到对应的学年
		SchoolYear sy = this.schoolYearService
				.findSchoolYearById(studentCheckAttendance.getSchoolYearId());
		// 根据gradeId得到对应的年级名称
		Grade g = this.gradeService.findGradeById(studentCheckAttendance
				.getGradeId());
		// 根据teamId得到对应的班级名称
		Team t = this.teamService.findTeamById(studentCheckAttendance
				.getTeamNumber());

		model.addAttribute("isCK", "disable");
		model.addAttribute("grade", g);
		model.addAttribute("team", t);
		model.addAttribute("scas", studentCheckAttendance);
		model.addAttribute("schoolYear", sy);
		model.addAttribute("attendanceType", attendanceType);
		model.addAttribute("studentCheckAttendanceList", scaList);
		return new ModelAndView(structurePath("/oneView"), model.asMap());
	}

	/**
	 * 删除学生出勤信息
	 * 
	 * @param id
	 * @param studentCheckAttendance
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			@RequestParam(value = "dm", required = false) String dm,
			StudentCheckAttendance studentCheckAttendance) {
		if (studentCheckAttendance != null) {
			studentCheckAttendance.setId(id);
		}
		try {
			this.studentCheckAttendanceService.abandon(studentCheckAttendance);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * 添加某个学生的出勤情况
	 * 
	 * @param id
	 * @param dm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/creatorOther", method = RequestMethod.GET)
	public ModelAndView creatorOther(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		StudentCheckAttendance studentCheckAttendance = this.studentCheckAttendanceService
				.findStudentCheckAttendanceById(id);
		// 得到对应的学年
		SchoolYear sy = this.schoolYearService
				.findSchoolYearById(studentCheckAttendance.getSchoolYearId());

		// 得到对应的年级
		Grade g = this.gradeService.findGradeById(studentCheckAttendance
				.getGradeId());

		// 得到对应的班级
		Team t = this.teamService.findTeamById(studentCheckAttendance
				.getTeamNumber());

		// 得到对应的学生
		TeamStudent ts = this.teamStudentService.findUnique(
				studentCheckAttendance.getTeamNumber(),
				studentCheckAttendance.getstudentId());

		model.addAttribute("schoolYear", sy);
		model.addAttribute("grade", g);
		model.addAttribute("team", t);
		model.addAttribute("teamStudent", ts);
		model.addAttribute("studentCheckAttendance", studentCheckAttendance);

		return new ModelAndView(structurePath("/creatorOther"), model.asMap());
	}

	/**
	 * 保存某个学生的出勤情况
	 * 
	 * @param studentCheckAttendance
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addOther", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addOther(
			StudentCheckAttendance studentCheckAttendance,
			@CurrentUser UserInfo user) {
		StudentCheckAttendance scas = this.studentCheckAttendanceService
				.findStudentCheckAttendanceById(studentCheckAttendance.getId());
		StudentCheckAttendance sca = new StudentCheckAttendance();
		try {
			sca.setSchoolId(user.getSchoolId());
			sca.setSchoolYearId(scas.getSchoolYearId());
			sca.setGradeId(scas.getGradeId());
			sca.setTeamNumber(scas.getTeamNumber());
			sca.setStudentId(scas.getstudentId());
			sca.setStudentName(scas.getStudentName());
			sca.setDayNumber(studentCheckAttendance.getDayNumber());
			sca.setNodeNumber(studentCheckAttendance.getNodeNumber());
			sca.setOrderNumber(studentCheckAttendance.getOrderNumber());
			sca.setAttendanceType(studentCheckAttendance.getAttendanceType());
			sca.setIsDeleted(0);
			sca.setBeginDate(studentCheckAttendance.getBeginDate());
			sca.setEndDate(studentCheckAttendance.getEndDate());
			sca.setRemark(studentCheckAttendance.getRemark());
			sca.setModifyDate(new Date());
			sca = this.studentCheckAttendanceService.add(sca);
		} catch (Exception e) {
			log.info("新增信息异常..");
			e.printStackTrace();
		}

		return sca != null ? new ResponseInfomation(sca.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user,
			StudentCheckAttendanceCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (schoolId == null) {
			if (user != null) {
				condition.setSchoolId(user.getSchoolId());
			}
		}
	}

	/**
	 * 导出某个学生的考勤情况
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param user
	 * @param page
	 * @param order
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/checkOutOne")
	public void checkOutOne(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user

	) throws Exception {

		// 根据id获取对应的学生出勤信息
		StudentCheckAttendance sca = this.studentCheckAttendanceService
				.findStudentCheckAttendanceById(id);
		StudentCheckAttendanceCondition condition = new StudentCheckAttendanceCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setStudentId(sca.getstudentId());
		condition.setIsDeleted(0);
		List<StudentCheckAttendance> scaList = this.studentCheckAttendanceService
				.findStudentCheckAttendanceByCondition(condition, null, null);
		ParseConfig config = SzxyExcelTookit.getConfig("checkOutOne");
		List<Object> maps = new ArrayList<Object>();
		Map<Object, Map<Object, Object>> typeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
		Map<Object, Object> typeWithValueMap = new HashMap<Object, Object>();
		typeWithValueMap.put("0", "事假");
		typeWithValueMap.put("1", "病假");
		typeWithValueMap.put("2", "缺课");
		typeWithValueMap.put("3", "旷课");
		typeWithValueMap.put("4", "迟到");
		typeWithValueMap.put("5", "早退");
		typeWithValueMaps.put("attendanceTypeMap", typeWithValueMap);
		config.setCodeWithValueMaps(typeWithValueMaps);

		// 根据schoolYearId得到对应的学年
		SchoolYear sy = this.schoolYearService.findSchoolYearById(sca
				.getSchoolYearId());
		// 根据teamId得到对应的班级名称
		Team t = this.teamService.findTeamById(sca.getTeamNumber());

		Map<Object, Object> schoolYearWithValueMap = new HashMap<Object, Object>();
		schoolYearWithValueMap.put(sca.getSchoolYearId(), sy.getName());
		typeWithValueMaps.put("schoolYearName", schoolYearWithValueMap);
		config.setCodeWithValueMaps(typeWithValueMaps);

		Map<Object, Object> teamWithValueMap = new HashMap<Object, Object>();
		teamWithValueMap.put(sca.getTeamNumber(), t.getName());
		typeWithValueMaps.put("teamName", teamWithValueMap);
		config.setCodeWithValueMaps(typeWithValueMaps);

		String[] titles = { "学年", "班级", "姓名", "考勤类别", "开始时间", "结束时间", "天数",
				"节数", "次数", "备注" };
		config.setTitles(titles);
		config.setSheetTitle("学生考勤情况表");
		if (scaList.size() != 0) {
			for (StudentCheckAttendance sca0 : scaList) {
				StudentCheckAttendance s = new StudentCheckAttendance();
				BeanUtils.copyProperties(sca0, s);
				maps.add(s);
			}
		}

		String filename = sca.getStudentName() + "的考勤情况表.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,
				filename);

	}

	/**
	 * 根据考勤类型得到相应的学生考勤信息
	 * 
	 */
	public List<StudentCheckAttendanceVo> attendanceList(
			@CurrentUser UserInfo user, Integer studentId, String type,
			@ModelAttribute("order") Order order) {
		StudentCheckAttendanceCondition condition = new StudentCheckAttendanceCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setAttendanceType(type);
		condition.setStudentId(studentId);
		List<StudentCheckAttendanceVo> voList = this.studentCheckAttendanceService
				.findGroupVoByStudentId(condition, null, order);

		return voList;

	}

	/**
	 * 导出全部学生的考勤情况
	 */
	@RequestMapping(value = "/checkOutAll")
	public void checkOutAll(HttpServletRequest request,
			HttpServletResponse response, StudentCheckAttendance sca,
			@CurrentUser UserInfo user

	) throws Exception {
		ParseConfig config = SzxyExcelTookit.getConfig("checkOutAll");
		List<Object> maps = new ArrayList<Object>();
		Map<Object, Map<Object, Object>> typeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
		Map<Object, Object> typeWithValueMap = new HashMap<Object, Object>();
		typeWithValueMap.put("0", "事假");
		typeWithValueMap.put("1", "病假");
		typeWithValueMap.put("2", "缺课");
		typeWithValueMap.put("3", "旷课");
		typeWithValueMap.put("4", "迟到");
		typeWithValueMap.put("5", "早退");
		typeWithValueMaps.put("attendanceTypeMap", typeWithValueMap);
		config.setCodeWithValueMaps(typeWithValueMaps);
		SchoolYear sy = new SchoolYear();
		Map<Object, Object> schoolYearWithValueMap = new HashMap<Object, Object>();
		SchoolYearCondition syCondition = new SchoolYearCondition();
		if (sca.getSchoolYearId() != null) {
			// 根据year得到学年的id
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(String.valueOf(sca.getSchoolYearId()));
			sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			schoolYearWithValueMap.put(sy.getId(), sy.getName());
		} else {
			List<SchoolYear> yearList = this.schoolYearService
					.findSchoolYearOfSchool(user.getSchoolId());
			if (yearList != null && !yearList.isEmpty()) {
				for (SchoolYear schoolYear : yearList) {
					schoolYearWithValueMap.put(schoolYear.getId(),
							schoolYear.getName());
				}
			}
		}

		typeWithValueMaps.put("schoolYearName", schoolYearWithValueMap);
		config.setCodeWithValueMaps(typeWithValueMaps);

		StudentCheckAttendanceCondition scCondition = new StudentCheckAttendanceCondition();
		scCondition.setSchoolId(user.getSchoolId());
		scCondition.setSchoolYearId(sy.getId());
		scCondition.setGradeId(sca.getGradeId());
		scCondition.setTeamNumber(sca.getTeamNumber());
		scCondition.setStudentId(sca.getstudentId());
		scCondition.setStudentName(sca.getStudentName());
		scCondition.setIsDeleted(0);
		// 根据条件查询对应的学生考勤情况列表
		List<StudentCheckAttendance> scaList = this.studentCheckAttendanceService
				.findStudentCheckAttendanceByCondition(scCondition, null, null);

		// 根据teamId得到对应的班级名称
		if (sca.getTeamNumber() != null) {
			Team t = this.teamService.findTeamById(sca.getTeamNumber());
			Map<Object, Object> teamWithValueMap = new HashMap<Object, Object>();
			teamWithValueMap.put(sca.getTeamNumber(), t.getName());
			typeWithValueMaps.put("teamName", teamWithValueMap);
			config.setCodeWithValueMaps(typeWithValueMaps);
		} else {

			Map<Object, Object> teamWithValueMap = new HashMap<Object, Object>();
			for (int i = 0; i < scaList.size(); i++) {
				Team t1 = this.teamService.findTeamById(scaList.get(i)
						.getTeamNumber());
				teamWithValueMap.put(scaList.get(i).getTeamNumber(),
						t1.getName());
				typeWithValueMaps.put("teamName", teamWithValueMap);
			}
			config.setCodeWithValueMaps(typeWithValueMaps);

		}

		String[] titles = { "学年", "班级", "姓名", "考勤类别", "开始时间", "结束时间", "天数",
				"节数", "次数", "备注" };
		config.setTitles(titles);
		config.setSheetTitle("学生考勤情况表");
		if (scaList.size() != 0) {
			for (StudentCheckAttendance sca0 : scaList) {
				StudentCheckAttendance s = new StudentCheckAttendance();
				BeanUtils.copyProperties(sca0, s);
				// System.out.println(s.getStudentName());
				maps.add(s);
			}
		}
		String filename = "学生考勤情况表.xls";
		SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,
				filename);

	}

}
