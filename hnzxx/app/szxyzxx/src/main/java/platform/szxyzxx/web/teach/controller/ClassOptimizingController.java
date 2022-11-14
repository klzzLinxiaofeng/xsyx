package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.ClassEvaScoreData;
import platform.education.generalTeachingAffair.vo.ClassOptimizingSummaryData;
import platform.education.generalTeachingAffair.vo.IncreaseSummaryData;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseStudentData;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtil;

/**
 * 课堂优化
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/teach/classOptimizing")
public class ClassOptimizingController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(ClassOptimizingController.class);

	private final static String BASE_PATH = "/teach/classOptimizing/";

	// 进入查看页面
	@RequestMapping(value = "/index")
	public ModelAndView getClassEvaScoreList(
			@RequestParam(value = "dm", required = false) String dm, Model model) {
		String path = "index";
		model.addAttribute("dm", dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "week", required = false) String week,
			@RequestParam(value = "name", required = false) String name,
			Model model) {
		String path = "list";
		Date[] weekDate;
		String msg = "";
		List<ClassEvaScoreData> scoreData = new ArrayList<ClassEvaScoreData>();

		studentApsService.addStudentEvaluationTask(user.getSchoolId(), termCode);
		try {
			weekDate = DateUtil.weekTime(week);
			String time = week.substring(week.indexOf("第"), week.indexOf("("));

			scoreData = studentApsService.findClassScoresForTeam(year,termCode, teamId, weekDate[0], weekDate[1], name);
			SchoolTermCondition termCondition = new SchoolTermCondition();
			SchoolYearCondition yearCondition = new SchoolYearCondition();
			yearCondition.setYear(year);
			yearCondition.setSchoolId(user.getSchoolId());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
			termCondition.setCode(termCode);
			List<SchoolTerm> terms = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			Team team1 = this.teamService.findTeamById(teamId);
			for (SchoolTerm term : terms) {
				msg = schoolYear.getName() + term.getName() + "  " + team1.getName() + "  " + time;
			}
			model.addAttribute("msg", msg);
			model.addAttribute("scoreData", scoreData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 跳转到课堂优化页面
	@RequestMapping(value = "/addEva")
	public ModelAndView classOptimizingPage(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "termCode", required = false) String termCode,
			Model model) {
		String path = "addEva";
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCodeCurrent", schoolTermCurrent.getSchoolTermCode());
		model.addAttribute("dm", dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 跳转到课堂优化页面(重新编辑)
	@RequestMapping(value = "/modifyEva")
	public ModelAndView classOptimizingModifyPage(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "checkRange", required = false) String checkRange,
			@RequestParam(value = "badBehaviour", required = false) String badBehaviour,
//			@RequestParam(value = "isEdit", required = false, defaultValue="true") boolean isEdit,
			Model model) {
		String path = "addEva";
		Boolean isEdit = true;
		try {
			badBehaviour = java.net.URLDecoder.decode(badBehaviour, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCodeCurrent", schoolTermCurrent.getSchoolTermCode());
		model.addAttribute("dm", dm);
		model.addAttribute("badBehaviour", badBehaviour);
		model.addAttribute("isEdit", isEdit);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 获取课堂优化（回显）
	@RequestMapping(value = "/getEvaList")
	public ModelAndView getclassOptimizingList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "checkRange", required = false) String checkRange,
			Model model) {

		String path = "addEvaList";
		studentApsService
				.addStudentEvaluationTask(user.getSchoolId(), termCode);
		try {
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(year);
			List<TeamStudent> studentList = teamStudentService
					.findCurrentTeamStudentByCondition(teamStudentCondition,
							null, null);

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(checkDate);
			checkRange = "第" + checkRange + "节";

			// 获取各项目数据
			List<IncreaseStudentData> dataList = new ArrayList<IncreaseStudentData>();
			List<ApsTaskItem> itemList = studentApsService
					.findDeductMarksItems(termCode);
			for (ApsTaskItem item : itemList) {
				List<StudentItemData> itemDatas = studentApsService.findStudentsByItemIdForClassOptimizing(
						teamId,item.getId(), date, checkRange);

				IncreaseStudentData data = new IncreaseStudentData();
				data.setItemId(item.getId());
				data.setItemName(item.getName());
				data.setStudentDates(itemDatas);
				dataList.add(data);
			}

			model.addAttribute("itemList", itemList);
			model.addAttribute("studentList", studentList);
			model.addAttribute("dataList", dataList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 设置课堂优化
	@RequestMapping(value = "/setScores")
	@ResponseBody
	public ResponseInfomation setclassOptimizingList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "checkRange", required = false) String checkRange,
			@RequestParam(value = "itemDatas", required = false) String itemDatas,
			Model model) {
		List<StudentItemData> studentItemDatas = new ArrayList<StudentItemData>();
		try {
			if (user.getTeacherId() == null) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}

			JSONArray jsonArray = JSONArray.fromObject(itemDatas);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONArray array = JSONArray.fromObject(jsonArray.get(i));
				Integer itemId = array.getInt(0);
				Integer studentId = array.getInt(1);

				StudentItemData data = new StudentItemData();
				data.setItemId(itemId);
				data.setStudentId(studentId);
				studentItemDatas.add(data);
			}

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(checkDate);
			checkRange = "第" + checkRange + "节";

			Boolean delete = studentApsService.deleteItemScoresOfOptimizing(termCode, teamId, date, checkRange);
			if (delete) {
				Boolean flag = studentApsService.batchSetClassScores(
						user.getTeacherId(), teamId, date, checkRange, studentItemDatas);
				if (flag) {
					return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
				} else {
					return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);

	}

	// 进入评价报表的页面
	@RequestMapping(value = "/indexReport")
	public ModelAndView pjbb(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "manager", required = false) String manager,
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "week", required = false) String week,
			Model model) throws ParseException {
		String path = "report";// 普通管理员
		model.addAttribute("manager", manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 查找评价报表中的报表信息
	@RequestMapping(value = "/bbxx")
	public ModelAndView bbxx(
			@CurrentUser UserInfo user,
			@RequestParam(value = "manager", required = false) String manager,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "week", required = false) String week,
			Model model) {
		String path = "reportList";
		List<ClassOptimizingSummaryData> classOptimizingSummaryData = new ArrayList<ClassOptimizingSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];

			String object = null;
			String name = null;
			if (teamId != null) {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForTeam(
						teamId, null, termCode,beginDate, endDate);
				Team team = this.teamService.findTeamById(teamId);
				object = "pjk";
				name = team.getName();
			} else if (gradeId != null && teamId == null) {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForGrade(
						gradeId, termCode,beginDate, endDate);
				Grade grade = gradeService.findGradeById(gradeId);
				object = "bj";
				name = grade.getName();
			} else {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForSchool(
						user.getSchoolId(), termCode, beginDate, endDate);
				object = "nj";
				name = "全校";
			}
			model.addAttribute("name", name);
			model.addAttribute("object", object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("classOptimizingSummaryData", classOptimizingSummaryData);
		model.addAttribute("manager", manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	
	//获取到全部班级学生的数据
	@RequestMapping(value = "/myself")
	public ModelAndView myself (@CurrentUser UserInfo user,
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			Model model){
		String path = "/indexlistmyself";
		try {
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(year);
			List<TeamStudent> studentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
			
			model.addAttribute("studentList",studentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//默认一个全部班级的列表
	@RequestMapping(value = "/inmyself")
	public ModelAndView inmyself(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) {
		String path="indexListMyselfDefaultData";
		List<ClassOptimizingSummaryData> classOptimizingSummaryData = new ArrayList<ClassOptimizingSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			String object = null;
			String name = null;
			if (teamId != null) {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForTeam(teamId, null, termCode,beginDate, endDate);
				Team team = this.teamService.findTeamById(teamId);
				object = "pjk";
				name = team.getName();
			} else if (gradeId != null && teamId == null) {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForGrade(gradeId, termCode,beginDate, endDate);
				Grade grade = gradeService.findGradeById(gradeId);
				object = "bj";
				name = grade.getName();
			} else {
				classOptimizingSummaryData = this.studentApsService.findClassOptimizingCountForSchool(user.getSchoolId(),termCode, beginDate, endDate);
				object = "nj";
				name = "全校";
			}
			model.addAttribute("name", name);
			model.addAttribute("object", object);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("classOptimizingSummaryData",classOptimizingSummaryData);
		model.addAttribute("manager", manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//单个学生查询
	@RequestMapping(value = "/myselfList")
	public ModelAndView myselfList(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "studentId",required = false)Integer studentId,
			@RequestParam(value = "studentName",required = false)String studentName,
			@RequestParam(value = "week",required = false) String week, Model model){
		String path = "indexListMyselfData";
		List<ClassOptimizingSummaryData> classOptimizingSummaryData = new ArrayList<ClassOptimizingSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];

			List<ClassOptimizingSummaryData> teamData = this.studentApsService.findClassOptimizingCountForTeam(teamId, null, termCode, beginDate, endDate);
			
			List<ClassOptimizingSummaryData> studentData = null;
			if(teamId != null && studentId != null){
				studentData = this.studentApsService.findClassOptimizingCountForTeam(teamId, studentId, termCode, beginDate, endDate);
				if(studentData != null && studentData.size() > 0){
					for(ClassOptimizingSummaryData sdata : studentData){
						Integer id = sdata.getObjectId();
						for(ClassOptimizingSummaryData tdata : teamData){
							if(id.equals(tdata.getObjectId())){
								if(tdata.getCount() != 0){
									sdata.setRatio((float)sdata.getCount()/tdata.getCount());
								}else{
									sdata.setRatio((float)0);
								}
								break;
							}
						}
					}
				}
			}
			model.addAttribute("studentData",studentData);
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("classOptimizingSummaryData",classOptimizingSummaryData);
			model.addAttribute("manager",manager);
			model.addAttribute("studentName",studentName);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
}
