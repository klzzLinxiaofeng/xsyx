package platform.szxyzxx.web.teach.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseSummaryData;
import platform.education.generalTeachingAffair.vo.NormalEvaScoreData;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseStudentData;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtil;

/**
 * 激励评价
 */
@Controller				
@RequestMapping("/teach/incentiveEvaluation")
public class IncentiveEvaluationController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(IncentiveEvaluationController.class);
	
	private final static String BASE_PATH = "/teach/incentiveEvaluation/";
	
	
	//进入查看页面
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(@RequestParam(value = "dm",required = false) String dm,Model model){
		String path="index";
		model.addAttribute("dm",dm);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	//进入评价报表的页面
	@RequestMapping(value = "/indexReport")
	public ModelAndView pjbb(@CurrentUser UserInfo user,
			@RequestParam(value = "manager",required = false) String manager,
			@RequestParam(value = "schoolId",required = false) Integer schoolId,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "termCode",required = false)String termCode,
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) throws ParseException {
		
		String path="indexpjbb";//普通管理员
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
			@RequestParam(value = "month",required = false)String month,
			@RequestParam(value = "week",required = false) String week, Model model) {
		String path="indexlist";
		List<IncreaseSummaryData> increaseSummaryData = new ArrayList<IncreaseSummaryData>();
		try {
			Date beginDate = null;
			Date endDate = null;
			if(week!=null && !week.equals("")){
				Date[] weekDate = DateUtil.weekTime(week);
				beginDate = weekDate[0];
				endDate = weekDate[1];
			}else if(month != null && !month.equals("")){
				Date[] monthDate = DateUtil.monthTime(month);
				beginDate = monthDate[0];
				endDate = monthDate[1];
			}else if( termCode != null && !termCode.equals("")){
				SchoolTermCondition condition = new SchoolTermCondition();
				condition.setSchoolId(user.getSchoolId());
				condition.setCode(termCode);
				List<SchoolTerm> list = schoolTermService.findSchoolTermByCondition(condition, null, null);
				beginDate = list.get(0).getBeginDate();
				endDate = list.get(0).getFinishDate();
			}
			String object = null;
			if(teamId != null){
				object = "pjk";
				increaseSummaryData = this.studentApsService.findIncreaseCountForTeam(teamId, null, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else if(gradeId != null && teamId == null){
				object = "bj";
				increaseSummaryData = this.studentApsService.findIncreaseCountForGrade(gradeId, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else{
				object = "nj";
				increaseSummaryData = this.studentApsService.findIncreaseCountForSchool(user.getSchoolId(), termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("increaseSummaryData",increaseSummaryData);
			model.addAttribute("manager",manager);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	

	//激励评价列表
	@RequestMapping(value = "/list")
	public ModelAndView list(@CurrentUser UserInfo user,
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "month",required = false) String month,
			Model model){
		String path = "list";
		Date[] monthDate;
		List<IncreaseEvaScoreData> evaScore= new ArrayList<IncreaseEvaScoreData>();
		
		studentApsService.addStudentEvaluationTask(user.getSchoolId(), termCode);
		try {
			Team team = teamService.findTeamById(teamId);
			monthDate = DateUtil.monthTime(month);
			String monthTime = month.substring(5);
			evaScore= this.studentApsService.findIncreaseScoresForTeam(year, termCode, teamId, monthDate[0], monthDate[1]);
			model.addAttribute("team", team);
			model.addAttribute("monthTime", monthTime);
			model.addAttribute("evaScore", evaScore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	//跳转到添加页面
	@RequestMapping(value = "/addEva")
	public ModelAndView toTeamEvaluation(@CurrentUser UserInfo user,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "checkDate",required = false) String checkDate,
			@RequestParam(value = "dm",required = false) String dm,
	        Model model) {
		String path="addEva";
 		model.addAttribute("dm", dm);
 		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCodeCurrent", schoolTermCurrent.getSchoolTermCode());
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	
	//获取激励评价数据(回显)
	@RequestMapping(value = "/getEvaList")
	public ModelAndView getEvaList(	@CurrentUser UserInfo user,
			@RequestParam(value = "year",required = false) String year,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "gradeId",required = false) Integer gradeId,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "checkDate",required = false) String checkDate,
			Model model){
		String path="addEvaList";
		try {
			
			studentApsService.addStudentEvaluationTask(user.getSchoolId(), termCode);
			
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(checkDate);
			
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(year);
			List<TeamStudent> studentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
				
			//获取所有评价数据
			List<IncreaseStudentData> pinde = getCategoryDate(termCode, ApsTaskContants.CATEGORY_PINDE, teamId, date);
			List<IncreaseStudentData> xueye = getCategoryDate(termCode, ApsTaskContants.CATEGORY_XUEYE, teamId, date);
			List<IncreaseStudentData> shenxin = getCategoryDate(termCode, ApsTaskContants.CATEGORY_SHENXIN, teamId, date);
			List<IncreaseStudentData> xingqu = getCategoryDate(termCode, ApsTaskContants.CATEGORY_SHIJIAN, teamId, date);
			List<IncreaseStudentData> shijian = getCategoryDate(termCode, ApsTaskContants.CATEGORY_XUEYE, teamId, date);
				
			model.addAttribute("studentList",studentList);
			model.addAttribute("pinde",pinde);
			model.addAttribute("xueye",xueye);
			model.addAttribute("shenxin",shenxin);
			model.addAttribute("xingqu",xingqu);
			model.addAttribute("shijian",shijian);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
	
	public List<IncreaseStudentData> getCategoryDate(String termCode, String Category, Integer teamId, Date date){
		//每个分组的所有项目
		List<ApsTaskItem> itemList = studentApsService.findAwardedItemsOfCategory(termCode, Category);
		//每个项目的评价数据
		List<IncreaseStudentData> list = new ArrayList<IncreaseStudentData>();
		for(ApsTaskItem item : itemList){
			List<StudentItemData> itemDates = studentApsService.findStudentsByItemIdForIncrease(teamId, item.getId(), date);
			IncreaseStudentData data = new IncreaseStudentData();
			data.setItemId(item.getId());
			data.setItemName(item.getName());
			data.setStudentDates(itemDates);
			list.add(data);
		}
		return list;
	}
	
	
	/**
	 * 激励评价录入
	 */
	@RequestMapping(value = "/setScores")
	@ResponseBody
	public ResponseInfomation saveTeamEvaluation(@CurrentUser UserInfo user,
			@RequestParam(value = "itemDatas",required = false) String itemDatas,
			@RequestParam(value = "termCode",required = false) String termCode,
			@RequestParam(value = "teamId",required = false) Integer teamId,
			@RequestParam(value = "checkDate",required = false) String checkDate){
		
		List<StudentItemData> studentItemDatas = new ArrayList<StudentItemData>();
		try {
			if(user.getTeacherId() == null){
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
			
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(checkDate);
			JSONArray jsonArray = JSONArray.fromObject(itemDatas);
			for(int i=0;i<jsonArray.size();i++){
				JSONArray array = JSONArray.fromObject(jsonArray.get(i));
				Integer itemId = array.getInt(0);
				Integer studentId = array.getInt(1);
				
				StudentItemData data = new StudentItemData();
				data.setItemId(itemId);
				data.setStudentId(studentId);
				studentItemDatas.add(data);
			}
			
			Boolean delete = studentApsService.deleteItemScoresOfIncrease(termCode, teamId, date);
			if(delete){
				Boolean flag = studentApsService.batchSetIncreaseScores(teamId, user.getTeacherId(), date, studentItemDatas);
				if(flag){
					return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
				}else{
					return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
				}				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
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
		List<IncreaseSummaryData> increaseSummaryData = new ArrayList<IncreaseSummaryData>();
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];

			String object = null;
			if(teamId != null){
				object = "pjk";
				increaseSummaryData = this.studentApsService.findIncreaseCountForTeam(teamId, null, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else if(gradeId != null && teamId == null){
				object = "bj";
				increaseSummaryData = this.studentApsService.findIncreaseCountForGrade(gradeId, termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}else{
				object = "nj";
				increaseSummaryData = this.studentApsService.findIncreaseCountForSchool(user.getSchoolId(), termCode, beginDate, endDate);
				model.addAttribute("object",object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("increaseSummaryData",increaseSummaryData);
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
		try {
			Date[] dataArr = DateUtil.startAndEndTime(user.getSchoolId(), termCode, month, week);
			Date beginDate = dataArr[0];
			Date endDate = dataArr[1];
			
			List<IncreaseSummaryData> teamData = this.studentApsService.findIncreaseCountForTeam(teamId, null, termCode, beginDate, endDate);
			
			List<IncreaseSummaryData> studentData = null;
			if(teamId != null && studentId != null){
				studentData = this.studentApsService.findIncreaseCountForTeam(teamId, studentId, termCode, beginDate, endDate);
				for(IncreaseSummaryData sdata : studentData){
					String name = sdata.getObjectName();
					for(IncreaseSummaryData tdata : teamData){
						if(name.equals(tdata.getObjectName())){
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
			model.addAttribute("studentData",studentData);
		} catch (Exception e) {
			e.printStackTrace();
		}
			model.addAttribute("manager",manager);
			model.addAttribute("studentName",studentName);
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}
}	
