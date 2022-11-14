package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ApsMedal;
import platform.education.generalTeachingAffair.model.ApsStuSummary;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StarEvaluateData;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.contants.StarPersonContants;
import platform.szxyzxx.web.teach.util.DateUtil;

/**
 * 星级个人
 * 
 * @author
 */
@Controller
@RequestMapping("/teach/starPerson")
public class StarPersonController {

	private static final Logger log = LoggerFactory
			.getLogger(RedBannerController.class);

	private String BASE_PATH = "teach/starPerson/";
	@Autowired
	@Qualifier("studentApsService")
	private StudentApsService studentApsService;

	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;

	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;

	// 首页
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(
			@RequestParam(value = "usertype", required = false, defaultValue = StarPersonContants.TEAM) String usertype,
			@RequestParam(value = "datetype", required = false, defaultValue = StarPersonContants.MONTH) String datetype,
			@RequestParam(value = "dm", required = false) String dm, Model model) {

		String path = "index";

		model.addAttribute("datetype", datetype);
		model.addAttribute("usertype", usertype);
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	// 展示页面
	@RequestMapping(value = "/list")
	public ModelAndView list(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usertype", required = false, defaultValue = StarPersonContants.TEAM) String usertype,
			@RequestParam(value = "datetype", required = false, defaultValue = StarPersonContants.MONTH) String datetype,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "date", required = false) String date,
//			@ModelAttribute("page") Page page,
			Model model) throws Exception {
		
		Integer flag=1;
		String path = "list";
		Date[] datetime;
		Date startDate;
		Date endDate;
		String periodCode = "";
		int usertypeId = 0;
		String msg = "";
		String users = "";
		String times = "";
		List<ApsStuSummary> starEvaluateDatas = new ArrayList<ApsStuSummary>();
		try {
			if(date!=null&&date.indexOf("年") == -1){
				date=new String(date.getBytes("ISO-8859-1"),"UTF-8");
			}
			if (datetype.equals(StarPersonContants.MONTH)) {
				datetime = DateUtil.monthTime(date);
				startDate = datetime[0];
				endDate = datetime[1];
				periodCode = monthnum(date, termCode);
				times = "月份: "+ date.substring(date.indexOf("年") + 1,date.indexOf("月") + 1) + "份";

			} else {
				SchoolTermCondition condition = new SchoolTermCondition();
				condition.setCode(termCode);
				List<SchoolTerm> st = schoolTermService.findSchoolTermByCondition(condition, null, null);
				startDate = st.get(0).getBeginDate();
				endDate = st.get(0).getFinishDate();
				periodCode = termnum(termCode);
				SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
				schoolYearCondition.setSchoolId(user.getSchoolId());
				schoolYearCondition.setYear(st.get(0).getSchoolYear());
				SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
				times = "学期: " + schoolYear.getName() + " "+ st.get(0).getName();

			}
			if (usertype.equals(StarPersonContants.TEAM)) {
				usertypeId = teamId;
				Team team = this.teamService.findTeamById(teamId);
				users = team.getName();
			} else if (usertype.equals(StarPersonContants.GRADE)) {
				usertypeId = gradeId;
				Grade grade = this.gradeService.findGradeById(gradeId);
				users = grade.getName();
			} else {
				usertypeId = user.getSchoolId();
			}

			starEvaluateDatas = this.studentApsService.getEvaluateStar(
					termCode, usertypeId, usertype, startDate, endDate,periodCode);
			if (starEvaluateDatas==null||starEvaluateDatas.size()==0) {
                flag=0;
				starEvaluateDatas=	this.studentApsService.getPersonalStar(year,termCode, usertypeId, usertype, startDate, endDate);
			}
			  if(starEvaluateDatas==null||starEvaluateDatas.size()==0){
		        	flag=2;
		        }

		} catch (ParseException e) {

			e.printStackTrace();
		}


		msg = users + " " + times;
		model.addAttribute("msg", msg);
		model.addAttribute("flag", flag);
		model.addAttribute("usertype", usertype);
		model.addAttribute("datetype", datetype);
		model.addAttribute("items", starEvaluateDatas);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 获取星级个人评定的人数
	// 展示页面
	@RequestMapping(value = "/getNum")
	public ModelAndView getNum(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode", required = false) String termCode,
			Model model) {

		String path = "setNum";
		List<EvaluationMedalData> dates = new ArrayList<EvaluationMedalData>();
		ApsMedal apsMedal = this.studentApsService
				.getSchooltStarReachCount(user.getSchoolId());
		int schoolnum = apsMedal.getReachCount();
		dates=this.studentApsService.batchgetStarReachCount(user.getSchoolId(), termCode);
		model.addAttribute("items", dates);
		model.addAttribute("schoolnum", schoolnum);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	@RequestMapping(value = "/setNum")
	@ResponseBody
	public ResponseInfomation setNum(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) String gradeIds,
			@RequestParam(value = "gradenum", required = false) String gradenum,
			@RequestParam(value = "teamnum", required = false) String teamnum,
			@RequestParam(value = "schoolnum", required = false) int schoolnum) {
		JSONArray array1 = JSONArray.fromObject(gradeIds);
		JSONArray array2 = JSONArray.fromObject(gradenum);
		JSONArray array3 = JSONArray.fromObject(teamnum);
		List<EvaluationMedalData> datas = new ArrayList<EvaluationMedalData>();
		try{
			for (int i = 0; i < array1.size(); i++) {
				EvaluationMedalData e = new EvaluationMedalData();
				e.setGradeId(array1.getInt(i));
				e.setGradeCount(array2.getInt(i));
				e.setTeamCount(array3.getInt(i));
				datas.add(e);
				
			}
		  this.studentApsService.setSchoolStarReachCount(user.getSchoolId(), schoolnum);
		  this.studentApsService.batchsetStarReachCount(datas);
		}catch (Exception e){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	// 星级评定
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseInfomation setStartPerson(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usertype", required = false, defaultValue = StarPersonContants.TEAM) String usertype,
			@RequestParam(value = "datetype", required = false, defaultValue = StarPersonContants.MONTH) String datetype,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "date", required = false) String date,
			Model model) {
		Date[] datetime;
		Date startDate;
		Date endDate;
		String periodCode = "";
		int usertypeId = 0;
		List<ApsStuSummary> starEvaluateDatas = new ArrayList<ApsStuSummary>();
		try {
			
			if(user.getTeacherId() == null){
				return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
			}

			if (datetype.equals(StarPersonContants.MONTH)) {
				datetime = DateUtil.monthTime(date);
				startDate = datetime[0];
				endDate = datetime[1];
				periodCode = monthnum(date, termCode);

			} else {
				SchoolTermCondition condition = new SchoolTermCondition();
				condition.setCode(termCode);
				List<SchoolTerm> st = schoolTermService.findSchoolTermByCondition(condition, null, null);
				startDate = st.get(0).getBeginDate();
				endDate = st.get(0).getFinishDate();
				periodCode = termnum(termCode);
			}
			if (usertype.equals(StarPersonContants.TEAM)) {
				usertypeId = teamId;
			} else if (usertype.equals(StarPersonContants.GRADE)) {
				usertypeId = gradeId;
			} else {
				usertypeId = user.getSchoolId();
			}
			starEvaluateDatas = this.studentApsService.evaluatePersonalStar(
					year,termCode, usertypeId, usertype, startDate, endDate,periodCode);
			if(starEvaluateDatas==null||starEvaluateDatas.size()==0){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
			
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		
	
	}

	// 月份判定
	@RequestMapping(value = "/judgeMonth")
	@ResponseBody
	public ResponseInfomation judgeWeek(@CurrentUser UserInfo user,
			@RequestParam(value = "date", required = false) String date)
			throws ParseException {

		Date today = new Date();
		int year = today.getYear()+1900;
		int month = today.getMonth()+1;
		int year1 = Integer.valueOf(date.substring(0, date.indexOf("年")));
		int month1 = Integer.valueOf(date.substring(date.indexOf("年")+1,
				date.indexOf("月")));

		if (year1 > year) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		} else if(year1==year){
			if (month1 > month) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}


	// 学期编号
	public String termnum(String termCode) {
		String term = "";
		term = "T0" + termCode.substring(termCode.length() - 1);
		return term;
	}

	// 月份编号
	public String monthnum(String date, String termCode) {
		String term = "";
		int realmonth = 0;
		int month = Integer.valueOf(date.substring(date.indexOf("年") + 1, date.indexOf("月")));
		int year = Integer.valueOf(date.substring(0, date.indexOf("年")));
		SchoolTermCondition condition = new SchoolTermCondition();
		condition.setCode(termCode);
		List<SchoolTerm> st = schoolTermService.findSchoolTermByCondition(condition, null, null);
		int month1 = st.get(0).getBeginDate().getMonth() + 1;
		int year1 = st.get(0).getBeginDate().getYear() + 1900;
		if (year == year1) {
			realmonth = month - month1 + 1;
		} else {
			realmonth = 12 - month1 + month + 1;
		}
		term = "M0" + realmonth;

		return term;
	}
	
}
