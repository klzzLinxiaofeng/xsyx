package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.model.ApsTeamSummary;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.RedBannerScore;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.generalTeachingAffair.vo.TeamUserCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtil;

/**
 * 红旗公示
 * 
 * @author
 */
@Controller
@RequestMapping("/teach/redBanner")
public class RedBannerController {

	private static final Logger log = LoggerFactory.getLogger(RedBannerController.class);

	private final static String BASE_PATH = "teach/redBanner/";
	private final static String TEAMEVA_PATH = "teach/teamEvaluation/";
	@Autowired
	@Qualifier("teamApsService")
	private TeamApsService teamApsService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamUserService teamUserService;
	@Autowired
	private TeacherService teacherService;

	// 红旗首页头部
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "isback", required = false, defaultValue = "false") String isback,
			Model model) throws UnsupportedEncodingException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		if(checkDate != null){
//			checkDate = new String(checkDate.getBytes("ISO-8859-1"),"utf-8");
//		}
		String path = "display";
		model.addAttribute("dm", dm);
		model.addAttribute("year", year);
		model.addAttribute("termCode", termCode);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("checkDate", checkDate);
		model.addAttribute("isback", isback);
		model.addAttribute("today", format.format(new Date()));
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	// 红旗展示
	@RequestMapping(value = "/list")
	public ModelAndView getTeamEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "dm", required = false) String dm,
			Page page,Order order,
			Model model) throws Exception {

		String path = "list";
		Integer eval=1;
		//判断是否评定的标志
		Boolean flag = true;
		//周次编号 M1,M2...
		String priodCode = weeknum(checkDate);
		//通过查询summary表拿的数据
		List<RedBannerVo> red = new ArrayList<RedBannerVo>();
		red = this.teamApsService.findWeeklyGradeRedBanner(termCode, gradeId, priodCode, null, order);
		//如果表没数据，从score表拿数据
		if (red.size() == 0) {
			eval=0;
			Date[] date = DateUtil.weekTime(checkDate);
			Date startDate = date[0];
			Date finishDate = date[1];
			red = this.teamApsService.findWeeklyGradeRedBanner(termCode, gradeId, startDate, finishDate);
			flag = false;
		}
		
		model.addAttribute("flag",flag);
		model.addAttribute("eval",eval);
		model.addAttribute("week",checkDate);
		model.addAttribute("redlist", red);
		model.addAttribute("dm", dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//周次判定
	@RequestMapping(value = "/judgeWeek")
	@ResponseBody
	public ResponseInfomation judgeWeek(@CurrentUser UserInfo user,
			@RequestParam(value = "checkDate", required = false) String checkDate) throws ParseException{
		
		Date[] date = DateUtil.weekTime(checkDate);
		Date startDate = date[0];
		Date finishDate = date[1];
		Date today = new Date();
		
		if((startDate.getTime() <= today.getTime() && today.getTime() < (finishDate.getTime()+86400000)) ||
			(startDate.getTime() <= (today.getTime()-7*86400000) && (today.getTime()-7*86400000) < finishDate.getTime()+86400000) || 
			(startDate.getTime() <= (today.getTime()-14*86400000) && (today.getTime()-14*86400000) < finishDate.getTime()+86400000) ){
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
	}
	
	// 红旗评定
	@RequestMapping(value = "/save")
	public ModelAndView setredBanner(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			Page page,Order order,
			Model model) throws Exception {
		String path = "list";
		List<RedBannerVo> red=new ArrayList<RedBannerVo>();
		String priodCode = "";
		try {
			
			priodCode = weeknum(checkDate);
			Date[] date = DateUtil.weekTime(checkDate);
			Date startDate = date[0];
			Date finishDate = date[1];
			
            //保存数据
			this.teamApsService.evaluateWeeklyGradeRedBanner(termCode, gradeId, startDate, finishDate, priodCode);
	        //找到刚保存的数据
			red = this.teamApsService.findWeeklyGradeRedBanner(termCode, gradeId, priodCode, null, order);
			Boolean flag = true;
			model.addAttribute("flag",flag);
			model.addAttribute("week",checkDate);
			model.addAttribute("redlist", red);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	

	// 修改红旗标准
	@RequestMapping(value = "/Standardr")
	@ResponseBody
	public ResponseInfomation setStandardr(@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) String gradeIds,
			@RequestParam(value = "scores", required = false) String scores,
			@RequestParam(value = "count", required = false) String count,
			@RequestParam(value = "flag", required = false) String flag
			) {
		try {
			//从过来对应的年级id和成绩
			JSONArray array1 = JSONArray.fromObject(gradeIds);
			JSONArray array2 = JSONArray.fromObject(scores);
			JSONArray array3 = JSONArray.fromObject(count);

			List<EvaluationMedalData> evaluationMedalDatas = new ArrayList<EvaluationMedalData>();
			for (int i = 0; i < array1.size(); i++) {
				EvaluationMedalData e = new EvaluationMedalData();
				e.setGradeId(array1.getInt(i));
				e.setScore(Float.parseFloat(array2.getString(i)));
				e.setCount(array3.getInt(i));
				evaluationMedalDatas.add(e);
			}

			this.teamApsService.batchSetRedBannerWeeklyStandardScore(evaluationMedalDatas);
		} catch (Exception e) {
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}

		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	//获取当前年学校流动红旗分数
	@RequestMapping(value = "/getScoreList")
	public ModelAndView getScoreList(@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			Model model){
		String flag="1";
		String path="setScore";
		List<RedBannerScore> bannerScores=this.teamApsService.findRedBannerScores(user.getSchoolId(),year);
		if(bannerScores!=null){
			for(RedBannerScore b:bannerScores){
				flag=b.getGetWay();
			}
		}
		model.addAttribute("flag",flag);
		model.addAttribute("score", bannerScores);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	

	// 周次编号
	private String weeknum(String week) {

		week = week.substring(week.indexOf("第") + 1, week.indexOf("周"));
		if (week.length() == 1) {
			week = "W0" + week;
		} else {
			week = "W" + week;
		}
		return week;
	}
	
	// 修改红旗标准方式
	@RequestMapping(value = "/way")
	@ResponseBody
	public ResponseInfomation changeWay(
			@RequestParam(value = "gradeId", required = false) String gradeIds,
			@RequestParam(value = "flag", required = false) String flag){
		try{
		if(flag.equals("1")){
			flag="2";
		}else{
			flag="1";
		}
		List<RedBannerScore>redBannerScores=new ArrayList<RedBannerScore>();
		JSONArray array1 = JSONArray.fromObject(gradeIds);
		for (int i = 0; i < array1.size(); i++) {
			RedBannerScore bannerScore=new RedBannerScore();
			bannerScore.setGradeId(array1.getInt(i));
			bannerScore.setGetWay(flag);
			redBannerScores.add(bannerScore);
			
		}    
		this.teamApsService.setRedBannerWeeklyStandardWay(redBannerScores);
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		}catch (Exception e){
			 return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
	}
	@RequestMapping(value = "/toTeamEva")
	public ModelAndView toTeamEva(@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "manager", required = false) String manager,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			Model model) throws Exception{
			
		String path="pjbb";
//		checkDate=new String(checkDate.getBytes("ISO-8859-1"),"utf-8");
		model.addAttribute("year",year);
		model.addAttribute("termCode",termCode);
		model.addAttribute("checkDate",checkDate);
		model.addAttribute("manager",manager);
		model.addAttribute("gradeId",gradeId);
		model.addAttribute("teamId",teamId);
		model.addAttribute("flag",1);
		return new ModelAndView(TEAMEVA_PATH + path, model.asMap());
		
	}
	public float addFloat(float a, float b){
		 BigDecimal b1 = new BigDecimal(String.valueOf(a));
		 BigDecimal b2 = new BigDecimal(String.valueOf(b));
		 BigDecimal b3 = b1.add(b2);
		 float f = b3.floatValue();
		 return f;
	}
	
}
