package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.SchoolYearVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.TeamComparator;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.util.DateUtils;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



@Controller
@RequestMapping("/teach/team")
public class TeamController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(TeamController.class);

	/**
	 * 班级列表
	 * 
	 * @return
	 * 
	 */
	@RequestMapping("/teamList")
	public ModelAndView getTeamList(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("teamCondition") TeamCondition teamCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		page.setPageSize(1);
		String viewPath = "";
		// List<Team> teamList = teamService.findTeamByCondition(teamCondition,
		// page, order);
		// List<SchoolYear> schoolYearList =
		// schoolYearService.findSchoolYearByCondition(null, null, null);
		// 获取学年和当前学年
		// List<SchoolYearVo> schoolYearVoList =
		// getSchoolYearList(user.getSchoolId());
		// Integer schoolId = user.getSchoolId() == null ? 0 :
		// user.getSchoolId();
		// SchoolTermCurrent schoolTermCurrent =
		// schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
		// String schoolYear = schoolTermCurrent.getSchoolYear();
		// List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId,
		// schoolYear);
		if ("list".equals(sub)) {
			viewPath = "/teach/team/list";
		} else {
			viewPath = "/teach/team/teamList";
		}

		// mav.addObject("schoolYearVoList", schoolYearVoList);
		// mav.addObject("gradeList", gradeList);
		// mav.addObject("teamList", teamList);
		mav.setViewName(viewPath);
		return mav;
	}

	public List<SchoolYearVo> getSchoolYearList(Integer schoolId) {
		List<SchoolYearVo> schoolYearVoList = new ArrayList<SchoolYearVo>();
		List<SchoolYear> schoolYearList = schoolYearService
				.findSchoolYearOfSchool(schoolId);
		for (int i = 0; i < schoolYearList.size(); i++) {
			SchoolYearVo schoolYearVo = new SchoolYearVo();
			SchoolYear schoolYear = schoolYearList.get(i);
			SchoolTermCurrent stc = this.schoolTermCurrentService
					.findSchoolTermCurrentBySchoolIdAndSchoolYearId(schoolId,
							schoolYear.getId());
			if (stc != null) {
				schoolYearVo.setFlag("1");
			} else {
				schoolYearVo.setFlag("0");
			}
			schoolYearVo.setYear(schoolYear.getYear());
			schoolYearVo.setName(schoolYear.getName());
			schoolYearVoList.add(schoolYearVo);
		}
		return schoolYearVoList;
	}

	/**
	 * 异步加载年级
	 */
	@RequestMapping(value = "/getAjaxGradeList", method = RequestMethod.POST)
	@ResponseBody
	public List<Grade> getAjaxGradeList(
			@RequestParam(value = "schoolYear", required = true) String schoolYear,
			@CurrentUser UserInfo user) {

		List<Grade> gradeList = gradeService.findGradeBySchoolYear(
				user.getSchoolId(), schoolYear);
		return gradeList;
	}

	/**
	 * 异步加载班级列表
	 */
	@RequestMapping(value = "/getAjaxTeamList", method = RequestMethod.POST)
	@ResponseBody
	public List<Team> getAjaxTeamList(
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@CurrentUser UserInfo user) {
		List<Team> teamList = this.teamService.findTeamOfGradeAndSchool(
				Integer.parseInt(gradeId), user.getSchoolId());
		
		for (Team team : teamList) {
			Long diff = DateUtils.getDateBetween(new Date(), team.getCreateDate());
			if (diff - 15 >= 0) {
				team.setIsDelete(false);
			} else {
				team.setIsDelete(true);
			}
		}
		
		return teamList;
	}

	/**
	 * 修改班级
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifyTeam")
	public ModelAndView modifyTeam(
			@RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		try {
			Team team = teamService.findTeamById(Integer.parseInt(id));
			mav.addObject("team", team);
			mav.setViewName("/teach/team/modifyTeam");
		} catch (Exception e) {
			log.info("...修改班级异常..");
			// e.printStackTrace();
		}

		return mav;
	}
	
	/**
	 * 查询某个教师教哪些班级
	 */
	@RequestMapping("/getclass")
	@ResponseBody
	public List<TeamTeacherVo> findClass(@CurrentUser UserInfo user, @RequestParam(value = "schoolYear", required = false) String schoolYear, 
			@RequestParam(value = "type", required=true, defaultValue="1") Integer type){
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(user.getSchoolId());
		teamTeacherCondition.setTeacherId(user.getTeacherId());
		teamTeacherCondition.setSchoolYear(schoolYear);
		teamTeacherCondition.setType(type);
		List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherVoByGroupBy(teamTeacherCondition);
		return teamList;
	}
	
	/**
	 * 查询某个教师教那些年级
	 */
	@RequestMapping("/getGrade")
	@ResponseBody
	public List<TeamTeacherVo> findGrade(@CurrentUser UserInfo user, @RequestParam(value = "schoolYear", required = false) String schoolYear, 
			@RequestParam(value = "type", required=true, defaultValue="1") Integer type){
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(user.getSchoolId());
		teamTeacherCondition.setTeacherId(user.getTeacherId());
		teamTeacherCondition.setSchoolYear(schoolYear);
		teamTeacherCondition.setType(type);
		List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherGradeByCondition(teamTeacherCondition);
		return teamList;
	}

	/**
	 * 检查班级代码是否重复
	 */

	@RequestMapping("/checkerTeamCode")
	@ResponseBody
	public boolean checkerTeamCode(
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@CurrentUser UserInfo user) {
		TeamCondition condition = new TeamCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		condition.setGradeId(gradeId);
		condition.setCode(code);
		List<Team> teamList = teamService.findTeamByCondition(condition, null, null);
		// List<Team> teamList = teamService.findGradeByCode(code);
		boolean isExist = false;
		if (teamList.isEmpty()) {
			isExist = true;
		} else {
			isExist = false;
		}
		return isExist;
	}

	// @RequestMapping("/checkerTeamCode")
	// @ResponseBody
	// public boolean checkerTeamCode(
	// @RequestParam(value = "code", required = true) String code) {
	// List<Team> teamList = teamService.findGradeByCode(code);
	// boolean isExist = false;
	// if (teamList.isEmpty()) {
	// isExist = true;
	// } else {
	// isExist = false;
	// }
	// return isExist;
	// }

	/**
	 * 更新班级
	 *
	 * @return
	 */
	@RequestMapping("/updateTeam")
	@ResponseBody
	public ResponseInfomation updateTeam(Team team) {
		Team t = null;
		try {
			team.setModifyDate(new Date());
			t = teamService.modify(team);
		} catch (Exception e) {
			log.info("...更新班级异常...");
			// e.printStackTrace();
			return new ResponseInfomation();
		}
		return t != null ? new ResponseInfomation(t.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * 删除班级
	 * 
	 * @param id
	 */
	@RequestMapping("/deleteTeam")
	@ResponseBody
	public String deleteTeam(
			@RequestParam(value = "id", required = true) String id,
			@CurrentUser UserInfo user) {
		String returnMsg = "";
		try {
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(Integer.parseInt(id));
			teamStudentCondition.setStudentId(user.getSchoolId());
			List<TeamStudent> teamStudentList = this.teamStudentService
					.findTeamStudentByCondition(teamStudentCondition, null,
							null);
			if (!teamStudentList.isEmpty()) {
				returnMsg = ResponseInfomation.NO_DELETE;
			} else {
				Team team = new Team();
				team.setId(Integer.parseInt(id));
				team.setIsDelete(true);
				teamService.modify(team);
				returnMsg = ResponseInfomation.OPERATION_SUC;
			}

		} catch (Exception e) {
			log.info("删除班级异常....");
			// e.printStackTrace();
			returnMsg = ResponseInfomation.OPERATION_FAIL;
			return returnMsg;
		}
		return returnMsg;
	}

	/**
	 * 新增班级页面
	 * 
	 * @return
	 */
	@RequestMapping("/addTeamPage")
	public ModelAndView addTeamPage(
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		String schoolYear = schoolTermCurrent.getSchoolYear();
		Grade grade = gradeService.findGradeById(Integer.parseInt(gradeId));
		mav.addObject("schoolYear", schoolYear);
		mav.addObject("grade", grade);
		mav.setViewName("/teach/team/addTeamPage");
		return mav;
	}

	/**
	 * 
	 * @param gradeId
	 * @param user
	 * @return
	 */
	@RequestMapping("/addTeamBatchPage")
	public ModelAndView addTeamBatchPage(
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		String schoolYear = schoolTermCurrent.getSchoolYear();
		Grade grade = gradeService.findGradeById(Integer.parseInt(gradeId));
		mav.addObject("schoolYear", schoolYear);
		mav.addObject("grade", grade);
		mav.setViewName("/teach/team/createClass");
		return mav;
	}

	/**
	 * 保存班级
	 */
	@RequestMapping("/addTeam")
	@ResponseBody
	public ResponseInfomation addTeam(Team team, @CurrentUser UserInfo user) {
		
		Team t = null;
		String stage = "";
		String sy = "";
		String tn = "";
		try {
			
			TeamCondition condition = new TeamCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDelete(false);
			condition.setGradeId(team.getGradeId());
			condition.setCode(team.getCode());
			List<Team> teamList = teamService.findTeamByCondition(condition, null, null);
			if(!teamList.isEmpty()) {
				return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
			}
			
			team.setCreateDate(new Date());
			team.setModifyDate(new Date());
			team.setBeginDate(new Date());
			team.setFinishDate(new Date());
			team.setIsDelete(false);
//			Grade grade = gradeService.findGradeById(team.getGradeId());

			// if(grade.getStageCode()=="2"|| "2".equals(grade.getStageCode())){
			// stage="X";
			// }else if(grade.getStageCode()=="3"||
			// "3".equals(grade.getStageCode())){
			// stage="C";
			// }else if(grade.getStageCode()=="4"||
			// "4".equals(grade.getStageCode())){
			// stage="G";
			// }else{
			// stage="O";
			// }
			//
			// if(team.getSchoolYear()!=null ||
			// !"".equals(team.getSchoolYear())){
			// sy = team.getSchoolYear().substring(2, 4);
			// }
			//
			// if(team.getTeamNumber().toString().length()==1){
			// tn="0"+team.getTeamNumber().toString();
			// }else{
			// tn=team.getTeamNumber().toString();
			// }
			//
			// team.setCode2(stage+sy+tn);
			
			t = teamService.add(team);
			
		} catch (Exception e) {
			log.info("-----新增班级异常----");
			// e.printStackTrace();
			return new ResponseInfomation();
		}
		return t != null ? new ResponseInfomation(t.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	
	/**
	 * 批量保存班级
	 * @param gradeId
	 * @param str
	 * @param user
	 * @return
	 */
	@RequestMapping("/addTeamBatch")
	@ResponseBody
	public ResponseInfomation addTeamBatch(
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@RequestParam(value = "str", required = true) String str,
			@CurrentUser UserInfo user) {
		Grade grade = gradeService.findGradeById(gradeId);
		List<Team> teams = new ArrayList<Team>();
		if (grade != null) {
			String[] teamNames = str.split(";");
			Integer teamNumber = 0;
			String teamName = "";
			String code = "";
			String fullName = "";
			String name = "";
			String code2 = "";
			String schoolYear = grade.getSchoolYear(); // 获得年级的学年记录
			Integer gradeNumber = grade.getGradeNumber(); // 获得年级在学段中的顺序
			String stageCode = grade.getStageCode(); // 获得通用学段代码
			String schoolYearSub = "";
			String teamNumberTemp = "";
			Integer schoolYearTemp = 0;
			String stage = "";
			if (stageCode == "2" || "2".equals(stageCode)) {
				stage = "X";
			} else if (stageCode == "3" || "3".equals(stageCode)) {
				stage = "C";
			} else if (stageCode == "4" || "4".equals(stageCode)) {
				stage = "G";
			} else {
				stage = "O";
			}
			schoolYearTemp = Integer.parseInt(schoolYear) - gradeNumber + 1;
			schoolYearSub = schoolYearTemp.toString().substring(2);  //获取学年的后两位
			
			TeamCondition condition = new TeamCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDelete(false);
			List<Team> teamList = new ArrayList<Team>();
			for (int i = 0; i < teamNames.length; i++) {
				teamName = teamNames[i];
				teamName = teamName.substring(0, teamName.length()-1);
				teamNumber = Integer.parseInt(teamName);
				code = grade.getCode() + "-" + teamNumber;
				condition.setGradeId(gradeId);
				condition.setCode(code);
				teamList = teamService.findTeamByCondition(condition, null, null);
				if (teamList.isEmpty()) {
					fullName = grade.getFullName() + "(" + teamNumber + ")" + "班";
					name = grade.getName() + "(" + teamNumber + ")" + "班";
					//班级编号不足两位的补"0",如1补0变成01
					if(teamNumber.toString().length() == 1){
						teamNumberTemp = "0" + teamNumber;
					}else{
						teamNumberTemp = teamNumber.toString();
					}
					code2 = stage + schoolYearSub + teamNumberTemp;
					Team team = new Team();
					team.setSchoolId(user.getSchoolId());
					team.setGradeId(gradeId);
					team.setFullName(fullName);
					team.setName(name);
					team.setTeamNumber(teamNumber);
					team.setCode(code);
					team.setCode2(code2);
					team.setSchoolYear(schoolYear);
					team.setTeamType("0");	//默认为行政班
					team.setBeginDate(new Date());
					team.setFinishDate(new Date());
					team.setIsDelete(false);
					team = this.teamService.add(team);
					if(team != null) {
						teams.add(team);
					}
				}
			}
		}
		return !teams.isEmpty() ? new ResponseInfomation(teams.get(0).getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	/**
	 * @Method jsonList
	 * @Function 功能描述：获取班级json数据
	 * @param user
	 * @param condition
	 * @param page
	 * @param order
	 * @param usePage
	 * @return
	 * @Date 2015年5月18日
	 */
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Team> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") TeamCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage,
			@RequestParam(value = "enableRole", required = false) boolean enableRole) {
		order.setProperty("team_number");
		order.setAscending(true);
		conditionFilter(user, condition);
		page = usePage ? page : null;
		
		Teacher t = this.teacherService.findOfUser(user.getSchoolId(), user.getId());
		List<Team>list = new ArrayList<Team>();
		if(enableRole){
			if(t!=null){
				TeamTeacherCondition ttCondition = new TeamTeacherCondition();
				ttCondition.setSchoolId(user.getSchoolId());
				ttCondition.setSchoolYear(condition.getSchoolYear());
				ttCondition.setTeacherId(t.getId());
				ttCondition.setGradeId(condition.getGradeId());
				List<TeamTeacherVo>tList = this.teamTeacherService.findTeamTeacherVoByCondition(ttCondition);
				Map<Integer,Team>tMaps = new HashMap();
				for(TeamTeacherVo ttvo : tList){
					Team team = this.teamService.findTeamById(ttvo.getTeamId());
					if(team!=null){
						tMaps.put(team.getId(), team);
					}
				}
				
				//去重
				List<Team>teamList = new ArrayList(tMaps.values());
				Team[] teams = (Team[])teamList.toArray(new Team[teamList.size()]);
				java.util.Arrays.sort(teams, TeamComparator.getComparator());
				for (int i = 0; i < teams.length; i++) {  
					list.add(teams[i]);
	           }  
				
				
				
			}
		}else{
			list = this.teamService.findTeamByCondition(condition, page, order);
		}
		
		
		
		
		return list;
	}

	private void conditionFilter(UserInfo user, TeamCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if (user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
		}
		
		if(schoolId!=null && !"".equals(schoolId)){
			//当前学校所属的当前学期
			SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			condition.setSchoolYear(stc.getSchoolYear());
		}
		
		
		
	}
	
	/**
	 * 功能描述：用于更新pj_team.code2字段
	 */
	@RequestMapping(value = "/updateCode2")
	public void updateCode2() {
		List<Team> teamList = this.teamService.findTeamByCondition(null, null, null);
		if(teamList.size() > 0) {
			for(Team team : teamList) {
				Integer gradeId = team.getGradeId();    //年级ID：pj_grade.id
				Integer teamNumber = team.getTeamNumber();   //班号：在同一年级中的顺序编号
				Grade grade = this.gradeService.findGradeById(gradeId);   
				if(grade != null) {
					String schoolYear = grade.getSchoolYear();   //学年值：pj_grade.school_year
					String stageCode = grade.getStageCode();     //学段代码：pj_grade.stage_code
					Integer gradeNumber = grade.getGradeNumber();  //年级在学段中的顺序：pj_grade.grade_number
					
					//学段对应的标记（小学--X,初中--C,高中--G,其他--O）
					String stage = "";
					if (stageCode == "2" || "2".equals(stageCode)) {
						stage = "X";
					} else if (stageCode == "3" || "3".equals(stageCode)) {
						stage = "C";
					} else if (stageCode == "4" || "4".equals(stageCode)) {
						stage = "G";
					} else {
						stage = "O";
					}
					
					//与学年值--年级顺序--班级序号相关
					schoolYear = schoolYear.substring(2);
					Integer schoolYearTemp = Integer.parseInt(schoolYear) - gradeNumber + 1;
					//班级编号不足两位的补"0",如1补0变成01
					String teamNumberTemp = "";
					String code2 = "";
					if(teamNumber.toString().length() == 1){
						teamNumberTemp = "0" + teamNumber;
					}else{
						teamNumberTemp = teamNumber.toString();
					}
					code2 = stage + schoolYearTemp + teamNumberTemp;
					team.setCode2(code2);
					team = this.teamService.modify(team);
				}
			}
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam(value="id", required=true)Integer teamId) {
		String result = "success";
		String studentType = SysContants.USER_TYPE_STUDENT;
		String parentType = SysContants.USER_TYPE_PARENT;
		Team team = teamService.findTeamById(teamId);
		if(team!=null) {
			Long diff = DateUtils.getDateBetween(new Date(), team.getCreateDate());
			if (diff - 15 >= 0) {
				result = "data_out_of_delete_time";
			} else {
				teamService.deleteAllInfoById(teamId, studentType, parentType);
			}
		} else {
			result = "data_no_find";
		}
		
		return result;
	}


	/**
	 * 獲取班級
	 * @param ids
	 * @return
	 *
	 */
	@RequestMapping(value = "/getByIds",method = RequestMethod.GET)
	@ResponseBody
	public List<Team> getByIds(@RequestParam(value = "ids")String ids){

		String[] strings = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (String string : strings) {
			list.add(Integer.parseInt(string.trim()));
		}
		return teamService.findByIds(list);
	}

}
