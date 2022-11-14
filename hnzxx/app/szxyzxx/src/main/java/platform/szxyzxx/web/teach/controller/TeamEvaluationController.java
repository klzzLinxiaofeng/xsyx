package platform.szxyzxx.web.teach.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskJudge;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.DayInfo;
import platform.education.generalTeachingAffair.vo.DutyTeacherStatData;
import platform.education.generalTeachingAffair.vo.JudgeTeacher;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentScoreData;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.notice.model.Notice;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtil;
import platform.szxyzxx.web.teach.vo.TeamScoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 班集体评价
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/teach/teamEvaluation")
public class TeamEvaluationController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(TeamEvaluationController.class);
	private final static String BASE_PATH = "teach/teamEvaluation/";

	@Autowired
	@Qualifier("teamApsService")
	private TeamApsService teamApsService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;

	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;

	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;

	public void setTeamApsService(TeamApsService teamApsService) {
		this.teamApsService = teamApsService;
	}
	
	/**
	 * 新增学期时添加评价任务
	 */
	@RequestMapping(value = "/addTask")
	@ResponseBody
	public ResponseInfomation addTask(@CurrentUser UserInfo user,
			@RequestParam(value = "termId", required = false) Integer termId){
		SchoolTerm term = schoolTermService.findSchoolTermById(termId);
		if(term != null){
			teamApsService.AddTeamTask(user.getSchoolId(), term.getCode());
			studentApsService.addStudentEvaluationTask(user.getSchoolId(), term.getCode());
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	
	@RequestMapping(value = "/list")
	public ModelAndView getTeamEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) Date checkDate,
			Model model) {

		teamApsService.AddTeamTask(user.getSchoolId(), termCode);

		List<TeamScoreData> teamAddScoreDatas = new ArrayList<TeamScoreData>();
		List<TeamScoreData> teamMinusScoreDatas = new ArrayList<TeamScoreData>();
		Map<String, List<TeamScoreData>> map = new HashMap<String, List<TeamScoreData>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String path = "list";
		String name = "";
		int addNum = 0;
		int minusNum = 0;
		float addSum = 0;
		float minusSum = 0;
		Date time = checkDate;
		String msg = "";
		try {
			String date = sdf.format(checkDate);
			SchoolTermCondition termCondition = new SchoolTermCondition();
			SchoolYearCondition yearCondition = new SchoolYearCondition();
			yearCondition.setYear(year);
			yearCondition.setSchoolId(user.getSchoolId());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
			termCondition.setCode(termCode);
			List<SchoolTerm> terms = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			Team team1 = this.teamService.findTeamById(teamId);
			for (SchoolTerm term : terms) {
				msg = schoolYear.getName() + term.getName() + "  " + team1.getName() + "  " + date;
			}
			teamAddScoreDatas = this.teamApsService.getTeamAddScore(termCode, teamId, time);
			teamMinusScoreDatas = this.teamApsService.getTeamMinusScore(termCode, teamId, time);
			Team team = teamService.findTeamById(teamId);
			name = team.getName();
			if (teamAddScoreDatas != null) {
				addNum = teamAddScoreDatas.size();
			}
			if (teamMinusScoreDatas != null) {
				minusNum = teamMinusScoreDatas.size();
			}

			for (TeamScoreData teamScoreData : teamAddScoreDatas) {
				addSum = addFloat(addSum, teamScoreData.getScore());
			}
			for (TeamScoreData teamScoreData : teamMinusScoreDatas) {
				minusSum = addFloat(minusSum, teamScoreData.getScore());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("addsum", addSum);
		model.addAttribute("minussum", minusSum);
		model.addAttribute("addnum", addNum);
		model.addAttribute("week", time);
		model.addAttribute("minusnum", minusNum);
		model.addAttribute("additem", teamAddScoreDatas);
		model.addAttribute("minusitem", teamMinusScoreDatas);
		model.addAttribute("teamName", name);
		model.addAttribute("msg", msg);
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	
	/**
	 * 班集体查看主页面
	 */
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,Model model) {

		String path = "bjtpj";
		
		//值日教师权限判断
		Boolean isOnDuty = false;		//是否是值日教师（当天是否有权限）
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		if(user.getTeacherId() != null){
			List<ApsTaskJudge> list = teamApsService.findJudgeTeacher(
					schoolTermCurrent.getSchoolTermCode(), user.getTeacherId(), new Date());
			if(list != null && list.size() > 0){
				isOnDuty = true;
			}
		}
		model.addAttribute("isOnDuty", isOnDuty);
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
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

	// 跳转到添加班级评价页面
	@RequestMapping(value = "/addEvaluationIndex")
	public ModelAndView addTeamEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			Model model) {
		String path = "addEvaluation";

		model.addAttribute("dm", dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}

	// 加载分数列表
	@RequestMapping(value = "/evaList")
	public ModelAndView getTeamEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) Date checkDate,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "isOnDuty", required = false, defaultValue = "false") Boolean isOnDuty,
			Model model) {
		String path = "evaList";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String msg = null;
		teamApsService.AddTeamTask(user.getSchoolId(), termCode);
		try {
			
			String date = sdf.format(checkDate);
			SchoolTermCondition termCondition = new SchoolTermCondition();
			SchoolYearCondition yearCondition = new SchoolYearCondition();
			yearCondition.setYear(year);
			yearCondition.setSchoolId(user.getSchoolId());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
			termCondition.setCode(termCode);
			List<SchoolTerm> terms = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			Grade grade = this.gradeService.findGradeById(gradeId);
			for (SchoolTerm term : terms) {
				msg = schoolYear.getName() + term.getName() + "  " + grade.getFullName() + "  " + date;
			}
			
			TeamCondition condition = new TeamCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setGradeId(gradeId);
			List<Team> teams = this.teamService.findTeamByCondition(condition,null, null);
			if(teams.size()==0){
				path = "remind";
				return new ModelAndView(BASE_PATH + path, model.asMap());
			}
			List<ApsTaskItem> reduceItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, checkDate);
			List<ApsTaskItem> addItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, checkDate);
			float[][] addList = this.teamApsService.findAllItemScoreForTeam(termCode, ApsTaskContants.CHECK_TYPE_ADD, checkDate, teams);
			float[][] reduceList = this.teamApsService.findAllItemScoreForTeam(termCode, ApsTaskContants.CHECK_TYPE_MINUS, checkDate, teams);
//			float[][] addList = new float[teams.size()][addItems.size()];
//			float[][] reduceList = new float[teams.size()][reduceItems.size()];
//			List<TeamEvaScoreData> reduceDatas = null;
//			List<TeamEvaScoreData> addDatas = null;
//			int i = 0;
//			for (Team team : teams) {
//				int j = 0;
//
//				// 从数据库查找数据在页面呈现，无数据为0
//				reduceDatas = teamApsService.getScoreOfMinus(termCode, team.getId(), checkDate);
//				for(ApsTaskItem item : reduceItems){
//					reduceList[i][j] = 0;
//					for(TeamEvaScoreData data : reduceDatas){
//						if(item.getId().equals(data.getItemId())){
//							reduceList[i][j] = data.getScore();
//							break;
//						}
//					}
//					j++;
//				}
//
//				int k = 0;
//				addDatas= teamApsService.getScoreOfAdd(termCode, team.getId(), checkDate);
//				for(ApsTaskItem item : addItems){
//					addList[i][k] = 0;
//					for(TeamEvaScoreData data : addDatas){
//						if(item.getId().equals(data.getItemId())){
//							addList[i][k] = data.getScore();
//							break;
//						}
//					}
//					k++;
//				}
//
//				i++;
//			}
			model.addAttribute("msg", msg);
			model.addAttribute("reduceItems", reduceItems);
			model.addAttribute("addItems", addItems);
			model.addAttribute("teams", teams);
			model.addAttribute("addList", addList);
			model.addAttribute("reduceList", reduceList);
			model.addAttribute("isOnDuty", isOnDuty);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	//单项录入页面
	@RequestMapping(value = "/dx_evaList")
	public ModelAndView getSingleTeamEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) Date checkDate,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "isOnDuty", required = false, defaultValue = "false") Boolean isOnDuty,
			Model model) {
		String path = "dx_evaList";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String msg = null;
		teamApsService.AddTeamTask(user.getSchoolId(), termCode);
		try {
			
			String date = sdf.format(checkDate);
			SchoolTermCondition termCondition = new SchoolTermCondition();
			SchoolYearCondition yearCondition = new SchoolYearCondition();
			yearCondition.setYear(year);
			yearCondition.setSchoolId(user.getSchoolId());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
			termCondition.setCode(termCode);
			List<SchoolTerm> terms = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			Grade grade = this.gradeService.findGradeById(gradeId);
			for (SchoolTerm term : terms) {
				msg = schoolYear.getName() + term.getName() + "  " + grade.getFullName() + "  " + date;
			}
			
			List<ApsTaskItem> reduceItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, checkDate);
			List<ApsTaskItem> addItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, checkDate);
			model.addAttribute("msg", msg);
			model.addAttribute("reduceItems", reduceItems);
			model.addAttribute("addItems", addItems);
			model.addAttribute("isOnDuty", isOnDuty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}
	
	@RequestMapping(value = "/dx_list")
	public ModelAndView getSingleTeamEvaluationList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) Date checkDate,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name", required = false) String name,
			Model model) {
		String path = "dx_list";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String msg = null;
		teamApsService.AddTeamTask(user.getSchoolId(), termCode);
		try {
			String date = sdf.format(checkDate);
			SchoolTermCondition termCondition = new SchoolTermCondition();
			SchoolYearCondition yearCondition = new SchoolYearCondition();
			yearCondition.setYear(year);
			yearCondition.setSchoolId(user.getSchoolId());
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
			termCondition.setCode(termCode);
			List<SchoolTerm> terms = this.schoolTermService.findSchoolTermByCondition(termCondition, null, null);
			Grade grade = this.gradeService.findGradeById(gradeId);
			for (SchoolTerm term : terms) {
				msg = schoolYear.getName() + term.getName() + "  " + grade.getFullName() + "  " + date;
			}
			
			TeamCondition condition = new TeamCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setGradeId(gradeId);
			List<Team> teams = this.teamService.findTeamByCondition(condition, null, null);
			if(teams.size()==0){
				path = "remind";
				return new ModelAndView(BASE_PATH + path, model.asMap());
			}
			ApsTaskItem item = new ApsTaskItem();
			item.setId(id);
			item.setName(name);
			List<ApsTaskItem> reduceItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, checkDate);
			List<ApsTaskItem> addItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, checkDate);
			List<Float> addList = new ArrayList<Float>();
			List<Float> reduceList = new ArrayList<Float>();
			List<TeamScoreVo> teamScore = new ArrayList<TeamScoreVo>();
			int i = 0;
			for (Team team : teams) {
				TeamScoreVo vo = new TeamScoreVo();
				// 从数据库查找数据在页面呈现，无数据为0
				float score = 0;
					ApsTaskScore taskScore = this.teamApsService.
							getTeamEvaluationTaskScore(item.getId(),team.getId(), checkDate);
					for(ApsTaskItem task:addItems){
						if(task.getId().equals(item.getId())){
							if (taskScore != null && !"".equals(taskScore)) {
								addList.add(taskScore.getScore());
								score = taskScore.getScore();
							}else{
								addList.add((float) 0);
							}
						}
					}
					for(ApsTaskItem task:reduceItems){
						if(task.getId().equals(item.getId())){
							if (taskScore != null && !"".equals(taskScore)) {
								reduceList.add(taskScore.getScore());
								score = taskScore.getScore();
							}else{
								reduceList.add((float) 0);
							}
						}
					}
					vo.setScore(score);
					vo.setTeam(team.getName());
					teamScore.add(vo);
			}
			model.addAttribute("msg", msg);
			model.addAttribute("item", item);
			model.addAttribute("teams", teams);
			model.addAttribute("teamScore", teamScore);
			model.addAttribute("addList", addList);
			model.addAttribute("reduceList", reduceList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	
	
	// 保存评分
	@RequestMapping(value = "/setScore")
	@ResponseBody
	public ResponseInfomation addEvaluation(
			@CurrentUser UserInfo user,
			@RequestParam(value = "reduceScore", required = false) String reduceScore,
			@RequestParam(value = "addScore", required = false) String addScore,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "json",required = false) String json,
			@RequestParam(value = "id",required = false) Integer id,
			Model model) {
		List<TeamScoreData> addScoreDates = new ArrayList<TeamScoreData>();
		List<TeamScoreData> reduceScoreDates = new ArrayList<TeamScoreData>();
		TeamScoreData scoreDate = null;
		List<TeamScoreData> scoreList = new ArrayList<TeamScoreData>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(checkDate);
			List<ApsTaskItem> reduceItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, date);
			List<ApsTaskItem> addItems = this.teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, date);
			TeamCondition condition = new TeamCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setGradeId(gradeId);
			List<Team> teams = this.teamService.findTeamByCondition(condition, null, null);
			if(reduceScore!=null){
				JSONArray jsonArray = JSONArray.fromObject(reduceScore);
				JSONArray addArray = JSONArray.fromObject(addScore);
				int i = 0;
				for (Team team : teams) {
					JSONArray jsonArray1 = JSONArray.fromObject(jsonArray.get(i));
					JSONArray jsonArray2 = JSONArray.fromObject(addArray.get(i));
					int j = 0;
					for (ApsTaskItem task : reduceItems) {
						float rdScore = 0;
						scoreDate = new TeamScoreData();
						if (!"".equals(jsonArray1.get(j))) {
							rdScore = (float) jsonArray1.getDouble(j);
						}
						scoreDate.setItemId(task.getId());
						scoreDate.setItemName(task.getName());
						scoreDate.setTeamId(team.getId());
						scoreDate.setScore(rdScore);
						reduceScoreDates.add(scoreDate);
						j++;
					}

					int k = 0;
					for (ApsTaskItem task : addItems) {
						scoreDate = new TeamScoreData();
						float adScore = 0;
						if (!"".equals(jsonArray2.get(k))) {
							adScore = (float) jsonArray2.getDouble(k);
						}
						scoreDate.setItemId(task.getId());
						scoreDate.setItemName(task.getName());
						scoreDate.setTeamId(team.getId());
						scoreDate.setScore(adScore);
						addScoreDates.add(scoreDate);
						k++;
					}
					i++;
				}
				if (user.getTeacherId() != null) {
					this.teamApsService.batchSetTeamEvaluationTaskScore(
							user.getTeacherId(), gradeId, date, addScoreDates);
					this.teamApsService.batchSetTeamEvaluationTaskScore(
							user.getTeacherId(), gradeId, date, reduceScoreDates);
				} else {
					return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
				}
			}else{
				JSONArray array = JSONArray.fromObject(json);
				int i = 0;
				for (Team team : teams) {
					float score = 0;
					scoreDate = new TeamScoreData();
					if(!array.getString(i).equals("")){
						score = (float) array.getDouble(i);
					}
					scoreDate.setItemId(id);
					scoreDate.setScore(score);
					scoreDate.setTeamId(team.getId());
					scoreList.add(scoreDate);
					i++;
				}
				if (user.getTeacherId() != null) {
					this.teamApsService.batchSetTeamEvaluationTaskScore(user.getTeacherId(), gradeId, date, scoreList);
				} else {
					return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
				}
			}
			if (user.getTeacherId() != null) {
				this.teamApsService.finishedJudge(termCode, gradeId, user.getTeacherId(), date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	// 加载添加评价项目窗口
	@RequestMapping(value = "/input")
	public ModelAndView checkList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "checkDate", required = false) String checkDate,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			Model model) {
		String path = "input";
		try {
			
			List<ApsTaskItem> reduceItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_MINUS);
			List<ApsTaskItem> addItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_ADD);

			model.addAttribute("reduceItems", reduceItems);
			model.addAttribute("addItems", addItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	// 加载添加评价项目窗口
		@RequestMapping(value = "/inputList")
		public ModelAndView inputList(@CurrentUser UserInfo user,
				@RequestParam(value = "termCode", required = false) String termCode,
				Model model){
			String path = "input_list";
			teamApsService.AddTeamTask(user.getSchoolId(), termCode);
			try {
				List<ApsTaskItem> reduceItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_MINUS);
				List<ApsTaskItem> addItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_ADD);
				
				model.addAttribute("reduceItems", reduceItems);
				model.addAttribute("addItems", addItems);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(BASE_PATH + path, model.asMap());
		}
				

	// 删除项目
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ResponseInfomation deleteItem(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = false) Integer itemId) {
		try {

			teamApsService.deleteTeamEvaItem(itemId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	// 评价项目是否使用
	@RequestMapping(value = "/change")
	public void change(
			@CurrentUser UserInfo user,
			@RequestParam(value = "itemId", required = false) Integer itemId,
			@RequestParam(value = "isChange", required = false) String isChange,
			Model model) {
		try {
			this.teamApsService.changeEnableOfItem(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 增加评价项目
	@RequestMapping(value = "/addCheck")
	@ResponseBody
	public ResponseInfomation addCheck(
			@CurrentUser UserInfo user,
			@RequestParam(value = "taskId", required = false) Integer taskId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "jianData", required = false) String jianData,
			@RequestParam(value = "addData", required = false) String addData,
			Model model) {
		List<String> addNames = new ArrayList<String>();
		List<String> jianNames = new ArrayList<String>();
		try {
			List<ApsTaskItem> reduceItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_MINUS);
			List<ApsTaskItem> addItems = this.teamApsService.findAllTaskItemsOfType(termCode,ApsTaskContants.CHECK_TYPE_ADD);
			float score = 1;
			String[] addDatas = addData.split(",");
			String[] jianDatas = jianData.split(",");
			for (int i = 0; i < addDatas.length; i++) {
				addNames.add(addDatas[i]);
			}
			for (int i = 0; i < jianDatas.length; i++) {
				jianNames.add(jianDatas[i]);
			}
			if (jianNames.size() > 0) {
				for (String name : jianNames) {
					if (!"".equals(name)) {
						for (ApsTaskItem item : reduceItems) {
							if (item.getName().equals(name)) {
								return new ResponseInfomation(name, ResponseInfomation.OPERATION_FAIL);
							}
						}
					}
				}
				for (String name : jianNames) {
					if (name != null && !"".equals(name.trim())) {
						this.teamApsService.addTeamEvaluationTask(taskId, name,ApsTaskContants.CHECK_TYPE_MINUS, score);
					}
				}
			}

			if (addNames.size() > 0) {
				for (String name : addNames) {
					if (!"".equals(name)) {
						for (ApsTaskItem item : addItems) {
							if (item.getName().equals(name)) {
								return new ResponseInfomation(name, ResponseInfomation.OPERATION_FAIL);
							}
						}
					}
				}
				for (String name : addNames) {
					if (name != null && !"".equals(name.trim())) {
						this.teamApsService.addTeamEvaluationTask(taskId, name,ApsTaskContants.CHECK_TYPE_ADD, score);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
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

	//查找评价报表中的报表信息
	@RequestMapping(value = "/bbxx")
	public ModelAndView bbxx(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) {
		String path="list1";
		List<TeamSummaryData> teamSummaryData = new ArrayList<TeamSummaryData>();
		float totalScore = 0;
		float addScore = 0 ;
		float deductScore = 0;
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			String object = null;
			if(teamId != null){
				object = "jcx";
				teamSummaryData = teamApsService.summaryTeamEvaluationTaskForTeam(termCode, teamId, beginDate, endDate);
				model.addAttribute("object",object);
			}else if(gradeId != null && teamId == null){
				object = "bj";
				teamSummaryData = this.teamApsService.summaryTeamEvaluationTaskForGrade(gradeId, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else{
				object = "nj";
				teamSummaryData = this.teamApsService.summaryTeamEvaluationTaskForSchool(user.getSchoolId(), termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}
			
			if(teamSummaryData != null && teamSummaryData.size()>0){		
				for(TeamSummaryData data : teamSummaryData){
					totalScore = addFloat(totalScore, data.getTotalScore());
					addScore = addFloat(addScore, data.getAddScore());
					deductScore = addFloat(deductScore, data.getDeductScore());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("totalScore",totalScore);
			model.addAttribute("addScore",addScore);
			model.addAttribute("deductScore",deductScore);
			model.addAttribute("teamSummaryData",teamSummaryData);
			model.addAttribute("manager",manager);
			model.addAttribute("gradeId",gradeId);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	//进入评价报表的页面
	@RequestMapping(value = "/pjbb")
	public ModelAndView pjbb(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "schoolId",required = false) Integer schoolId,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) throws ParseException {
		String path = "pjbb";//普通管理员
		model.addAttribute("manager",manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
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
	 * 查询某个教师所担任的班主任或者任课 的班级
	 */
	@RequestMapping("/getGrade")
	@ResponseBody
	public List<TeamTeacherVo> findTeam(@CurrentUser UserInfo user, @RequestParam(value = "schoolYear", required = false) String schoolYear){
		List<TeamTeacherVo> teamList1=new ArrayList<TeamTeacherVo>();
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(user.getSchoolId());
		teamTeacherCondition.setTeacherId(user.getTeacherId());
		teamTeacherCondition.setSchoolYear(schoolYear);
		List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherGradeByCondition(teamTeacherCondition);
		teamList1=removeRepGrade(teamList);
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
	 * 去掉重复的年级
	 */
	public List<TeamTeacherVo> removeRepGrade(List<TeamTeacherVo> nList) {
		List<TeamTeacherVo> teachers = new ArrayList<TeamTeacherVo>();

		for (TeamTeacherVo n1 : nList) {
			boolean flag = true;
			if (teachers != null) {
				for (TeamTeacherVo n2 : teachers) {
					if (n1.getGradeId().equals(n2.getGradeId())) {
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
		
	public float addFloat(float a, float b){
		 BigDecimal b1 = new BigDecimal(String.valueOf(a));
		 BigDecimal b2 = new BigDecimal(String.valueOf(b));
		 BigDecimal b3 = b1.add(b2);
		 float f = b3.floatValue();
		 return f;
	}
	
	
	
	
	
	/**
	 * 值日管理页面
	 */
	@RequestMapping(value = "/duty/index")
	public ModelAndView dutyIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "isOnDuty", required = false) String isOnDuty,
			Model model){
		String path = "duty_index";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(user.getSchoolId(), schoolTermCurrent.getSchoolYear());
		String termCode = schoolTermCurrent.getSchoolTermCode();
		String beginDate = sdf.format(schoolTermCurrent.getBeginDate());
		String endDate = sdf.format(schoolTermCurrent.getFinishDate());
		String today = sdf.format(new Date());
		
		model.addAttribute("isOnDuty", isOnDuty);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("termCode", termCode);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("today", today);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	/**
	 * 值日教师的录入页面
	 */
	@RequestMapping(value = "/duty/add")
	public ModelAndView addEvaForDuty(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			Model model) {
		String path = "duty_add";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		List<ApsTaskJudge> list = teamApsService.findJudgeTeacher(
				schoolTermCurrent.getSchoolTermCode(), user.getTeacherId(), new Date());
		List<Grade> gradeList = new ArrayList<Grade>();
		Grade grade = null;
		if(list != null && list.size() > 0){
			for(ApsTaskJudge judge : list){
				grade = gradeService.findGradeById(judge.getGradeId());
				gradeList.add(grade);
			}
		}
		Collections.sort(gradeList, new Comparator<Grade>(){  
			public int compare(Grade d1, Grade d2) {  
				Integer n1 = Integer.parseInt(d1.getUniGradeCode());
				Integer n2 = Integer.parseInt(d2.getUniGradeCode());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
		String year = schoolTermCurrent.getSchoolYear();
		String termCode = schoolTermCurrent.getSchoolTermCode();
		String checkDte = sdf.format(new Date());
		model.addAttribute("year", year);
		model.addAttribute("termCode", termCode);
		model.addAttribute("checkDate", checkDte);
		model.addAttribute("gradeList", gradeList);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	/**
	 * 获取每周的工作日
	 */
	public List<DayInfo> getDaysOfweek(String week) throws ParseException{
		List<DayInfo> list = new ArrayList<DayInfo>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		week = week.substring(week.indexOf("(")+1,week.indexOf(")"));
		String[] weekStr = week.split("~\\s*");
		Date beginDate = sdf.parse(weekStr[0]);
		Date endDate = sdf.parse(weekStr[1]);
		
		String[] dayArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		if(beginDate.getDay() < 1){		//以星期天开始，初始时间变为星期一
			beginDate.setTime(beginDate.getTime()+86400000);
		}
		if(endDate.getDay() > 5){			//以星期六结束，结束时间变为星期五
			endDate.setTime(endDate.getTime()-86400000);
		}
		
		Date currentDate = new Date();
		Date today = new Date();
		int n = (int) ((endDate.getTime()-beginDate.getTime())/86400000)+1;
		for(int i=0; i<n; i++){
			DayInfo dutyTeacher = new DayInfo();
			currentDate.setTime(beginDate.getTime()+86400000*i);
			dutyTeacher.setDate(sdf.format(currentDate));
			dutyTeacher.setDayOfWeek(dayArr[currentDate.getDay()]);
			if(currentDate.getTime() <= today.getTime() && today.getTime() < (currentDate.getTime()+86400000)){
				dutyTeacher.setIsCurrent(1);
			}else{
				dutyTeacher.setIsCurrent(0);
			}
			list.add(dutyTeacher);
		}
		return list;
	}
	
	
	/**
	 * 值日教师列表(全部)
	 */
	@RequestMapping(value = "/duty/list")
	public ModelAndView getDutyTeacherList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "week",required = false) String week,
			Model model) {
		String path = "duty_list";
		List<DayInfo> dayList = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] weekStr = week.substring(week.indexOf("(")+1,week.indexOf(")")).split("~\\s*");
			//当前学期，拼成标题
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			Grade grade = gradeService.findGradeById(gradeId);
			String title = schoolTermCurrent.getSchoolYearName()+"  "+schoolTermCurrent.getSchoolTermName()
					+"  "+grade.getFullName()+"  "+week;		
			
			//获取当周的工作日
			dayList = getDaysOfweek(week);
			//年级的所有教师
			//List<TeamTeacher> teamTeacherList = teamTeacherService.getTeachersOfGrade(gradeId);	
			List<Teacher> teacherList = teacherService.findGradeOfTeacher(gradeId, schoolTermCurrent.getSchoolYear(),true);
			List<ApsTaskJudge> judgeList = null;	//值日教师数据
			List<JudgeTeacher> judgeTeacherList = null;
			String date = "";
			int count = teacherList.size();
			for(DayInfo dayInfo : dayList){
				date = dayInfo.getDate();
				judgeTeacherList = new ArrayList<JudgeTeacher>();  //年级所有教师转存数据
				judgeList = teamApsService.findTaskJudge(schoolTermCurrent.getSchoolTermCode(), gradeId, sdf.parse(date));
				
				//全部教师列表
				//将年级所有教师的数据转入judgeTeacherList中
				for(Teacher teacher : teacherList){
					JudgeTeacher judgeTeacher = new JudgeTeacher();
					judgeTeacher.setTeacherId(teacher.getId());
					judgeTeacher.setTeacherName(teacher.getName());
					judgeTeacher.setUserId(teacher.getUserId());
					judgeTeacher.setOnDutyDate(date);
					
					Boolean flag = false;
					//遍历judge表中教师，判断是否与教师相同
					if(judgeList != null && judgeList.size() > 0){
						for(int i=0; i<judgeList.size(); i++){
							if(judgeTeacher.getTeacherId().equals(judgeList.get(i).getTeacherId())){
								flag = true;
								judgeList.remove(i);
								break;
							}
						}
					}
					if(flag){
						judgeTeacher.setIsChoose(1);
					}else{
						judgeTeacher.setIsChoose(0);
					}
					judgeTeacherList.add(judgeTeacher);
				}
				dayInfo.setTeacherList(judgeTeacherList);
				
			}
			
			model.addAttribute("title", title);
			model.addAttribute("dayList", dayList);
			model.addAttribute("teamTeacherList", teacherList);
			model.addAttribute("count", count);
			model.addAttribute("beginDate", weekStr[0]);
			model.addAttribute("endDate", weekStr[1]);
			model.addAttribute("today", sdf.format(new Date()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	
	/**
	 * 值日教师列表(个人)
	 */
	@RequestMapping(value = "duty/view")
	public ModelAndView getDutyTeacher(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "week",required = false) String week,
			Model model) {
		String path = "duty_view";
		List<DayInfo> dayList = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			Grade grade = gradeService.findGradeById(gradeId);
			String title = schoolTermCurrent.getSchoolYearName()+"  "+schoolTermCurrent.getSchoolTermName()
							+"  "+grade.getFullName()+"  "+week;
			
			//年级的所有教师
			//List<TeamTeacher> teamTeacherList = teamTeacherService.getTeachersOfGrade(gradeId);	
			
			dayList = getDaysOfweek(week);
			List<ApsTaskJudge> judgeList = null;	//值日教师数据
			List<JudgeTeacher> judgeTeacherList = null;
			String date = "";
			int count = 0;
			for(DayInfo dayInfo : dayList){
				date = dayInfo.getDate();
				judgeTeacherList = new ArrayList<JudgeTeacher>();  
				judgeList = teamApsService.findTaskJudge(schoolTermCurrent.getSchoolTermCode(), gradeId, sdf.parse(date));
				if(judgeList != null && judgeList.size() > 0){
					for(ApsTaskJudge taskJudge : judgeList){
						JudgeTeacher teacher = new JudgeTeacher();
						teacher.setTeacherId(taskJudge.getTeacherId());
						teacher.setTeacherName(teacherService.findTeacherById(taskJudge.getTeacherId()).getName());
						teacher.setUserId(taskJudge.getUserId());
						teacher.setOnDutyDate(date);
						if(teacher.getTeacherId().equals(user.getTeacherId())){
							teacher.setIsChoose(1);
						}else{
							teacher.setIsChoose(0);
						}
						judgeTeacherList.add(teacher);
					}
				}
				dayInfo.setTeacherList(judgeTeacherList);
				if(judgeTeacherList.size() >  count){
					count = judgeTeacherList.size();
				}
			}
			
			model.addAttribute("title", title);
			model.addAttribute("dayList", dayList);
			//model.addAttribute("teamTeacherList", teamTeacherList);
			model.addAttribute("count", count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	/**
	 * 保存值日教师
	 */
	@RequestMapping(value = "/duty/save")
	@ResponseBody
	public ResponseInfomation saveJudgeTeacher(@CurrentUser UserInfo user,
			@RequestParam(value = "beginDate", required = false) String beginDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "week", required = false) String week,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teacherData",required = false) String teacherData){
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
			List<JudgeTeacher> list = new ArrayList<JudgeTeacher>();
			JSONArray jsonArr = JSONArray.fromObject(teacherData);
			if(jsonArr != null && jsonArr.size() > 0){
				for(int i=0; i<jsonArr.size(); i++){
					JSONObject json = jsonArr.getJSONObject(i);
					JudgeTeacher teacher = new JudgeTeacher();
					teacher.setTeacherId(json.getInt("teacherId"));
					teacher.setUserId(json.getInt("userId"));
					teacher.setOnDutyDate(json.getString("onDutyDate"));
					list.add(teacher);
				}
			}
			week = week.substring(0, week.indexOf("("));
			teamApsService.batchSetJudgeTeacher(schoolTermCurrent.getSchoolTermCode(), gradeId, sdf.parse(beginDate), sdf.parse(endDate), list, week);
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
	}
	
	
	
	//评价明细报表页面
	@RequestMapping(value = "/report/detail/index")
	public ModelAndView toReportDetail(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "manager",required = false) String manager,
			Model model) {
		String path = "report_detail";
		model.addAttribute("dm", dm);
		model.addAttribute("manager", manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	//获取星期
	@RequestMapping(value = "/report/daylist")
	@ResponseBody
	public ResponseInfomation getDay(@CurrentUser UserInfo user,
			@RequestParam(value = "week",required = false) String week){
		 try {
			//List<DayInfo> dayList = getDaysOfweek(week);
			List<DayInfo> list = new ArrayList<DayInfo>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			week = week.substring(week.indexOf("(")+1,week.indexOf(")"));
			String[] weekStr = week.split("~\\s*");
			Date beginDate = sdf.parse(weekStr[0]);
			Date endDate = sdf.parse(weekStr[1]);
			
			String[] dayArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
			
			Date currentDate = new Date();
			Date today = new Date();
			int n = (int) ((endDate.getTime()-beginDate.getTime())/86400000)+1;
			for(int i=0; i<n; i++){
				DayInfo dutyTeacher = new DayInfo();
				currentDate.setTime(beginDate.getTime()+86400000*i);
				dutyTeacher.setDate(sdf.format(currentDate));
				dutyTeacher.setDayOfWeek(dayArr[currentDate.getDay()]);
				if(currentDate.getTime() <= today.getTime() && today.getTime() < (currentDate.getTime()+86400000)){
					dutyTeacher.setIsCurrent(1);
				}else{
					dutyTeacher.setIsCurrent(0);
				}
				list.add(dutyTeacher);
			}
			return new ResponseInfomation(list, ResponseInfomation.OPERATION_SUC);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
	}
	
	//明细报表表格
	@RequestMapping(value = "/report/detail/list")
	public ModelAndView toReportDetailList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "week",required = false) String week,
			@RequestParam(value = "date",required = false) String date,
			@RequestParam(value = "day",required = false) String day,
			@RequestParam(value = "isAll",required = false) Boolean isAll,
			Model model) {
		String path = "report_detail_list";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setSchoolId(user.getSchoolId());
			schoolYearCondition.setYear(year);
			List<SchoolYear> yearList = schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
			SchoolTermCondition schoolTermCondition  = new SchoolTermCondition();
			schoolTermCondition.setSchoolId(user.getSchoolId());
			schoolTermCondition.setCode(termCode);
			List<SchoolTerm> termList = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
			String title = yearList.get(0).getName() + termList.get(0).getName() + 
					week.substring(0, week.indexOf("(")) + "-" + day + "   " + "日常评比报表" ;
			
			List<ApsTaskItem> itemList = new ArrayList<ApsTaskItem>();
			List<ApsTaskItem> minusItems = teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, sdf.parse(date));
			List<ApsTaskItem> addItems = teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, sdf.parse(date));
			itemList.addAll(minusItems);
			itemList.addAll(addItems);
			
			List<Team> teamList = new ArrayList<Team>();
			if(isAll){
				teamList = teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), year);
			}else{
				TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
				teamTeacherCondition.setSchoolId(user.getSchoolId());
				teamTeacherCondition.setTeacherId(user.getTeacherId());
				teamTeacherCondition.setSchoolYear(year);
				List<TeamTeacherVo> teamTeacherList = teamTeacherService.findTeamTeacherGradeByCondition(teamTeacherCondition);
				for(TeamTeacherVo teamTeacher : teamTeacherList){
					List<Team> teams = teamService.findTeamOfGrade(teamTeacher.getGradeId());
					teamList.addAll(teams);
				}
			}
			
			if(teamList != null && teamList.size() > 0){
				float[][] scoreList = new float[teamList.size()][itemList.size()];
				float[][] minusList = teamApsService.findAllItemScoreForTeam(termCode, ApsTaskContants.CHECK_TYPE_MINUS, sdf.parse(date), teamList);
				float[][] addList = teamApsService.findAllItemScoreForTeam(termCode, ApsTaskContants.CHECK_TYPE_ADD, sdf.parse(date), teamList);
				for(int i=0; i<teamList.size(); i++){
					
					for(int j=0; j<minusItems.size(); j++){
						scoreList[i][j] = minusList[i][j];
					}
					for(int k=0; k<addItems.size(); k++){
						scoreList[i][minusItems.size()+k] = addList[i][k];
					}
				}
				
//				List<TeamEvaScoreData> minusDatas = null;
//				List<TeamEvaScoreData> addDatas = null;
//				for(int i=0; i<teamList.size(); i++){
//					Team team = teamList.get(i);
//					int j = 0;
//					minusDatas = teamApsService.getScoreOfMinus(termCode, team.getId(), sdf.parse(date));
//					addDatas = teamApsService.getScoreOfAdd(termCode, team.getId(), sdf.parse(date));
//					for(ApsTaskItem item : itemList){
//						scoreList[i][j] = 0;
//						if("03".equals(item.getCheckType())){
//							for(TeamEvaScoreData data : minusDatas){
//								if(item.getId().equals(data.getItemId())){
//									scoreList[i][j] = data.getScore();
//									break;
//								}
//							}
//						}
//						if("02".equals(item.getCheckType())){
//							for(TeamEvaScoreData data : addDatas){
//								if(item.getId().equals(data.getItemId())){
//									scoreList[i][j] = data.getScore();
//									break;
//								}
//							}
//						}
//						j++;
//					}
//				}
				model.addAttribute("scoreList", scoreList);
			}
			
			model.addAttribute("title", title);
			model.addAttribute("itemList", itemList);
			model.addAttribute("teamList", teamList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	/**
	 * 值日统计页面
	 */
	@RequestMapping(value = "/duty/statistics/index")
	public ModelAndView dutyStatisticsIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "isOnDuty", required = false) String isOnDuty,
			Model model){
		String path = "dutyStatistics_index";
		
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	/**
	 * 值日统计列表
	 */
	@RequestMapping(value = "/duty/statistics/list")
	public ModelAndView dutyStatisticsList(@CurrentUser UserInfo user,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "week", required = false) String week,
			@RequestParam(value = "sortord", required = false) String sortord,
			Model model){
		
		String path = "dutyStatistics_list";
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			List<DutyTeacherStatData> teacherList = new ArrayList<DutyTeacherStatData>();
			if(sortord == null || ("desc").equals(sortord)){
				teacherList = teamApsService.dutyTeacherStatistics(user.getSchoolId(), year, termCode, gradeId, beginDate, endDate, page, Order.desc("finishedDayCount"));
			}else{
				teacherList = teamApsService.dutyTeacherStatistics(user.getSchoolId(), year, termCode, gradeId, beginDate, endDate, page, Order.asc("finishedDayCount"));
			}
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("week", week);
			model.addAttribute("month", month);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	/**
	 * 值日统计明细页面
	 */
	@RequestMapping(value = "/duty/statistics/view")
	public ModelAndView dutyStatisticsView(@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teacherId", required = true) Integer teacherId,
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "week", required = false) String week,
			Model model){
		String path = "dutyStatistics_view";
		int type = 3;
		if(week != null && week != ""){
			week = "第" + week.substring(0, week.indexOf("(")) + "周" + week.substring(week.indexOf("("));
			type = 1;
		}
		if(month != null && month != ""){
			String[] mstr = month.split("-");
			month = mstr[0] + "年" + mstr[1] + "月";
			type = 2;
		}
		model.addAttribute("teacherId", teacherId);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("year", year);
		model.addAttribute("termCode", termCode);
		model.addAttribute("month", month);
		model.addAttribute("week", week);
		model.addAttribute("type", type);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	/**
	 * 值日统计明细列表
	 */
	@RequestMapping(value = "/duty/statistics/viewlist")
	public ModelAndView dutyStatisticsViewList(@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "week", required = false) String week,
			@RequestParam(value = "teacherId", required = true) Integer teacherId,
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			Model model){
		String path = "dutyStatistics_viewlist";
		
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			Teacher teacher = teacherService.findTeacherById(teacherId);
			List<ApsTaskJudge> list = teamApsService.findUniqueJudge(termCode, gradeId, teacherId, beginDate, endDate);
			
			model.addAttribute("name", teacher.getName());
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
}
