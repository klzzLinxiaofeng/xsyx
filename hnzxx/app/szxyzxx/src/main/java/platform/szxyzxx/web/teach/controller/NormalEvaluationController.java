package platform.szxyzxx.web.teach.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseSummaryData;
import platform.education.generalTeachingAffair.vo.NormalEvaScoreData;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.NormalEvaScoreData;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.education.generalTeachingAffair.vo.StudentScoreData;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtil;


@Controller
@RequestMapping("/teach/normalEvaluation")
public class NormalEvaluationController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(NormalEvaluationController.class);
	
	private final static String BASE_PATH = "/teach/normalEvaluation/";
	
	@Autowired
	@Qualifier("studentApsService")
	private StudentApsService studentApsService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm",required = false) String dm, Model model) throws ParseException{
		String path="index";
		model.addAttribute("dm",dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	@RequestMapping(value = "/list")
	public ModelAndView list(
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			Model model){
		String path = "list";
		Date[] monthDate;
		List<NormalEvaScoreData> evaScore= new ArrayList<NormalEvaScoreData>();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd ");
		String date="";
		try {
			Team team = teamService.findTeamById(teamId);
			monthDate = DateUtil.monthTime(month);
			String monthTime = month.substring(5);
			evaScore= this.studentApsService.findNormalScoresForTeam(year, termCode, teamId, monthDate[0], monthDate[1]);
			for(NormalEvaScoreData data : evaScore){
				if(data.getCheckDate()!=null){
					date = "录入时间："+sdf.format(data.getCheckDate());
					break;
				}else{
					date = "暂无录入数据";
				}
			}
			model.addAttribute("team", team);
			model.addAttribute("date", date);
			model.addAttribute("monthTime", monthTime);
			model.addAttribute("evaScore", evaScore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//发展评价卡录入页面
	@RequestMapping(value = "/enterIndex")
	public ModelAndView enterIndex(@CurrentUser UserInfo user, Model model){
		String path="enter_index";
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCodeCurrent", schoolTermCurrent.getSchoolTermCode());
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	@RequestMapping(value = "/enterList")
	public ModelAndView enterList(
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			Model model){
		String path = "enter_list";
		List<NormalEvaScoreData> evaScore= new ArrayList<NormalEvaScoreData>();
		try {
			Date[] monthDate = DateUtil.monthTime(month);
			evaScore= this.studentApsService.findNormalScoresForTeam(year, termCode, teamId, monthDate[0], monthDate[1]);
			model.addAttribute("evaScore", evaScore);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseInfomation save(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			@RequestParam(value = "json",required = false) String json){
		
		List<StudentScoreData> scoreList = new ArrayList<StudentScoreData>();
		try {
			Date[] monthDate = DateUtil.monthTime(month);
			Date checkDate = new Date();
			JSONArray array = JSONArray.fromObject(json);
			List<StudentScoreData> list = JSONArray.toList(array, new StudentScoreData(), new JsonConfig());
			for(StudentScoreData data:list){
				float score = 0;
				StudentScoreData newData = new StudentScoreData();
				if(data.getScore() != null){
					score = data.getScore();
				}
				newData.setScore(score);
				newData.setStudentId(data.getStudentId());
				scoreList.add(newData);
			}
			if (user.getTeacherId() != null) {
				this.studentApsService.batchSetNormalScores(termCode, teamId, user.getTeacherId(), scoreList, monthDate[1]);
			} else {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
	}
	
	//进入评价报表的页面
	@RequestMapping(value = "/indexReport")
	public ModelAndView pjbb(@CurrentUser UserInfo user,
			@RequestParam(value = "dm",required = false) String dm,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "schoolId",required = false) Integer schoolId,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) throws ParseException {
		String path="indexpjbb";
		model.addAttribute("manager",manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//查找评价报表中的报表信息
	@RequestMapping(value = "/bbxx")
	public ModelAndView bbxx(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,Model model) {
		String path="indexlist";
		List<NormalSummaryData> normalSummaryData = new ArrayList<NormalSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, null);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			String object = null;
			if(teamId != null){
				object = "pjk";
				normalSummaryData = this.studentApsService.findNormalCountForTeam(teamId, null, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else if(gradeId != null && teamId == null){
				object = "bj";
				normalSummaryData = this.studentApsService.findNormalCountForGrade(gradeId, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else{
				object = "nj";
				normalSummaryData = this.studentApsService.findNormalCountForSchool(user.getSchoolId(), termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("normalSummaryData",normalSummaryData);
			model.addAttribute("manager",manager);
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
		List<NormalSummaryData> normalSummaryData = new ArrayList<NormalSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			String object = null;
			if(teamId != null){
				object = "pjk";
				normalSummaryData = this.studentApsService.findNormalCountForTeam(teamId, null, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else if(gradeId != null && teamId == null){
				object = "bj";
				normalSummaryData = this.studentApsService.findNormalCountForGrade(gradeId, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else{
				object = "nj";
				normalSummaryData = this.studentApsService.findNormalCountForSchool(user.getSchoolId(), termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("normalSummaryData",normalSummaryData);
			model.addAttribute("manager",manager);
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
		List<NormalSummaryData> normalSummaryData = new ArrayList<NormalSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			normalSummaryData = this.studentApsService.findNormalCountForTeam(teamId, null, termCode, beginDate, endDate);
			float allData = 0;
			float oneData = 0;
			for(NormalSummaryData data : normalSummaryData){
				allData += data.getCount();
			}
			if(teamId != null && studentId != null){
				normalSummaryData = this.studentApsService.findNormalCountForTeam(teamId, studentId, termCode, beginDate, endDate);
				for(NormalSummaryData data : normalSummaryData){
					oneData = data.getCount();
				}
			}
			float percent =0;
			if(allData == 0.0 & allData == 0){
				percent = 0;
			}else{
				percent = oneData / allData;
			}
			model.addAttribute("percent",percent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("normalSummaryData",normalSummaryData);
		model.addAttribute("manager",manager);
		model.addAttribute("studentName",studentName);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
}




















