package platform.szxyzxx.web.teach.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.contants.UserActionContans;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.PjUserActionService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.PjUserActionHonorScoreVo;
import platform.education.generalTeachingAffair.vo.PjUserActionTestVo;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.szxyzxx.web.common.controller.base.BaseController;

@Controller
@RequestMapping("/course/action")
public class PjUserActionController extends BaseController{
	
	private final static String BASE_PATH = "/teach/userAction/";
	
	@Autowired
	@Qualifier("pjUserActionService")
	private PjUserActionService pjUserActionService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;
	
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	protected SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;
	
	/**
	 * ?????????????????????????????????
	 * @param schoolUserCondition
	 * @param model
	 * @param userId
	 * @return modelandview
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(SchoolUserCondition schoolUserCondition, Model model, 
			@RequestParam(value = "userId", required = false) Integer userId) {
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		model.addAttribute("teamName", team.getName());
		Student student = studentService.findStudentByUserId(userId);
		model.addAttribute("studentName", student.getName());
		if(student.getStudentNumber() != null && student.getStudentNumber() != "") {
			model.addAttribute("studentNumber", student.getStudentNumber());
		}else {
			model.addAttribute("studentNumber", "");
		}
		model.addAttribute("userId", userId);
		return new ModelAndView(BASE_PATH + "index", model.asMap());
	}
	
	/**
	 * ?????????????????????????????????
	 * @param schoolUserCondition
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/teamCurrent", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String teamCurrent(SchoolUserCondition schoolUserCondition, 
			@RequestParam(value = "userId", required = false) Integer userId) {
		JSONArray json = new JSONArray();
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		SchoolTermCurrent s = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolUser.get(0).getSchoolId());
		json.add(s);
		return json.toString();
	}
	
	/**
	 * ??????????????????????????????????????????
	 * @param schoolUserCondition
	 * @param userId
	 * @param begin
	 * @param end
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/subject", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String subject (SchoolUserCondition schoolUserCondition,
			@RequestParam(value = "kind", required = false) String kind,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		Integer teamId = team.getId();
		try {
			if(kind.equals("1")) {
				List<String> pjUser = pjUserActionService.findPjUserActionByUserId(userId, teamId, dateFormat(begin), endDateFormat(end));
				for(String p : pjUser) {
					String subjectName = "";
					subjectName = subjectService.findUnique(schoolUser.get(0).getSchoolId(), p).getName();
					if(subjectName != null && !subjectName.equals("")) {
						obj.put(subjectName, p);
					}
				}
				json.add(obj);
			}else if(kind.equals("2")){
				List<String> time = new ArrayList<String>();
				int week = 7;
				String lastTime = "";
				while(week >= 1) {
					if(week == 7) {
						lastTime = begin;
					}else {
						time = getLastWeekTime(lastTime);
						lastTime = time.get(0);
					}
					week--;
				}
				List<String> pjUser = pjUserActionService.findPjUserActionByUserId(userId, teamId, dateFormat(lastTime), endDateFormat(end));
				for(String p : pjUser) {
					String subjectName = "";
					subjectName = subjectService.findUnique(schoolUser.get(0).getSchoolId(), p).getName();
					if(subjectName != null && !subjectName.equals("")) {
						obj.put(subjectName, p);
					}
				}
				json.add(obj);
			}else {
				String[] date = end.split("-");
				String da = getDateLastDay(date[0],date[1]);
				List<String> thisMonthTime = new ArrayList<String>();
				List<String> lastMonthTime = new ArrayList<String>();
				String lastTime = "";
				int month = 7;
				while(month >= 1) {
					if(month == 7) {
						thisMonthTime = getThisMonthTime(da);
						lastTime = thisMonthTime.get(0);
					}else {
						lastMonthTime = getLastMonthTime(lastTime);
						lastTime = lastMonthTime.get(0);
					}
					month--;
				}
				List<String> pjUser = pjUserActionService.findPjUserActionByUserId(userId, teamId, dateFormat(lastTime), endDateFormat(da));
				for(String p : pjUser) {
					String subjectName = "";
					subjectName = subjectService.findUnique(schoolUser.get(0).getSchoolId(), p).getName();
					if(subjectName != null && !subjectName.equals("")) {
						obj.put(subjectName, p);
					}
				}
				json.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * ??????????????????
	 * @param kind
	 * @param subjectCode
	 * @param type
	 * @param userId
	 * @param begin
	 * @param end
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/performance", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String performance(SchoolUserCondition schoolUserCondition,
			@RequestParam(value = "kind", required = false) String kind,
			@RequestParam(value = "subjectCode", required = false) String subjectCode, 
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		
		/**
		 * ??????teamId
		 */
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		Integer teamId = team.getId();
		
		String subcode1 = subjectCode.replace("[", "");
		String subcode2 = subcode1.replace("]", "");
		
		String[] code = subcode2.split(",");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(kind.equals("1")) {//????????????????????????????????????????????????subject_code??????
				List<Date> dateRes = getBetweenDates(begin, end);
				for(String c : code) {
					List<PjUserActionHonorScoreVo> pjvo = pjUserActionService.findHonorScore(userId, dateFormat(begin), endDateFormat(end), c, kind, teamId);
					List<PjUserActionHonorScoreVo> pj = new ArrayList<PjUserActionHonorScoreVo>();
					for(Date d : dateRes) {
						PjUserActionHonorScoreVo pv = new PjUserActionHonorScoreVo();
						int score = 0;
						for(PjUserActionHonorScoreVo p : pjvo) {
							if(simpleDateFormat.format(d.getTime()).equals(simpleDateFormat.format(p.getCreateDate().getTime()))) {//?????????????????????????????????
								score += p.getScore();
								pv.setCreateDate(p.getCreateDate());
								pv.setSubjectCode(p.getSubjectCode());
								pv.setSubjectName(p.getSubjectName());
								pv.setStudentName(p.getStudentName());
								pv.setScore(score);
							}
						}
						pj.add(pv);
					}
					
					List<Integer> score = new ArrayList<Integer>();
					for(PjUserActionHonorScoreVo p : pj) {
						if(p.getScore() != null) {
							score.add(p.getScore());
						}else {
							score.add(0);
						}
					}
					Subject subject = subjectService.findUnique(schoolUser.get(0).getSchoolId(), c);
					String subjectName = "";
					if(subject != null) {
						subjectName = subject.getName();
					}
					obj.put(subjectName, score);
				}
			}else if(kind.equals("2")) {//?????????????????????????????????????????????????????????
				for(String c : code) {
					List<String> time = new ArrayList<String>();
					int week = 7;
					String lastTime = "";
					String endTime = "";
				
					List<Integer> score = new ArrayList<Integer>();
					while(week >= 1) {
						if(week == 7) {
							lastTime = begin;
							endTime = end;
						}else {
							time = getLastWeekTime(lastTime);
							lastTime = time.get(0);
							endTime = time.get(1);
						}
						List<PjUserActionHonorScoreVo> pjvo = pjUserActionService.findHonorScore(userId, dateFormat(lastTime), endDateFormat(endTime), c, kind, teamId);
						for(PjUserActionHonorScoreVo p : pjvo) {
							if(p != null) {
								if(p.getScore() != null) {
									score.add(p.getScore());
								}else {
									score.add(0);
								}
							}else {
								score.add(0);
							}
						}
						week--;
					}
					List<Integer> s = new ArrayList<Integer>();
					for(int i=score.size();i>0;i--) {
						s.add(score.get(i-1));
					}
					Subject subject = subjectService.findUnique(schoolUser.get(0).getSchoolId(), c);
					String subjectName = "";
					if(subject != null) {
						subjectName = subject.getName();
					}
					obj.put(subjectName, s);
				}
			}else if(kind.equals("3")) {//????????????????????????????????????????????????????????????????????????????????????????????????
				String[] date = end.split("-");
				String da = getDateLastDay(date[0],date[1]);
				
				for(String c : code) {
					List<String> thisMonthTime = new ArrayList<String>();
					List<String> lastMonthTime = new ArrayList<String>();
					int month = 7;
					String lastTime = "";
					String endTime = "";
				
					List<Integer> score = new ArrayList<Integer>();
					while(month >= 1) {
						if(month == 7) {
							thisMonthTime = getThisMonthTime(da);
							lastTime = thisMonthTime.get(0);
							endTime = thisMonthTime.get(1);
						}else {
							lastMonthTime = getLastMonthTime(lastTime);
							lastTime = lastMonthTime.get(0);
							endTime = lastMonthTime.get(1);
						}
						List<PjUserActionHonorScoreVo> pjvo = pjUserActionService.findHonorScore(userId, dateFormat(lastTime), endDateFormat(endTime), c, kind, teamId);
						for(PjUserActionHonorScoreVo p : pjvo) {
							if(p != null) {
								if(p.getScore() != null) {
									score.add(p.getScore());
								}else {
									score.add(0);
								}
							}else {
								score.add(0);
							}
						}
						month--;
					}
					List<Integer> s = new ArrayList<Integer>();
					for(int i=score.size();i>0;i--) {
						s.add(score.get(i-1));
					}
					Subject subject = subjectService.findUnique(schoolUser.get(0).getSchoolId(), c);
					String subjectName = "";
					if(subject != null) {
						subjectName = subject.getName();
					}
					obj.put(subjectName, s);
				}
			}else {
				obj.put("error", "??????????????????");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", "?????????????????????");
		}
		json.add(obj);
		return json.toString();
	}
	
	/**
	 * ????????????
	 * @param kind
	 * @param subjectCode
	 * @param userId
	 * @param begin
	 * @param end
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/participation", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String participation(SchoolUserCondition schoolUserCondition,
			@RequestParam(value = "kind", required = false) String kind,
			@RequestParam(value = "subjectCode", required = false) String subjectCode, 
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		
		/**
		 * ??????teamId
		 */
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		Integer teamId = team.getId();
		
		try {
			double practice = 0.0;
			double achievement = 0.0;
			double answer = 0.0;
			if(kind.equals("1")) {//?????????????????????????????????
				practice = pjUserActionService.findStudentPractice(userId, dateFormat(end), endDateFormat(end), subjectCode ,teamId);
				achievement = pjUserActionService.findStudentAchievement(userId, dateFormat(end), endDateFormat(end), subjectCode ,teamId);
				answer = pjUserActionService.findStudentAnswer(userId, dateFormat(end), endDateFormat(end), subjectCode ,teamId);
			}else if(kind.equals("2")) {//????????????
				List<String> thisWeek = getThisWeekTime(end);
				practice += pjUserActionService.findStudentPractice(userId, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), subjectCode ,teamId);
				achievement += pjUserActionService.findStudentAchievement(userId, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), subjectCode ,teamId);
				answer += pjUserActionService.findStudentAnswer(userId, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), subjectCode ,teamId);
			}else if(kind.equals("3")) {//?????????????????????
				String[] date = end.split("-");
				String da = getDateLastDay(date[0],date[1]);
				List<String> thisMonthTime = new ArrayList<String>();
				String lastTime = "";
				String endTime = "";
				thisMonthTime = getThisMonthTime(da);
				lastTime = thisMonthTime.get(0);
				endTime = thisMonthTime.get(1);
				practice += pjUserActionService.findStudentPractice(userId, dateFormat(lastTime), endDateFormat(endTime), subjectCode ,teamId);
				achievement += pjUserActionService.findStudentAchievement(userId, dateFormat(lastTime), endDateFormat(endTime), subjectCode ,teamId);
				answer += pjUserActionService.findStudentAnswer(userId, dateFormat(lastTime), endDateFormat(endTime), subjectCode ,teamId);
			}else{
				obj.put("error", "??????????????????");
			}
			obj.put("????????????", Math.round(practice * 100));
			obj.put("????????????", Math.round(achievement * 100));
			obj.put("??????", Math.round(answer * 100));
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", "?????????????????????");
		}
		json.add(obj);
		return json.toString();
	}
	
	/**
	 * ????????????
	 * @param schoolUserCondition
	 * @param kind
	 * @param subjectCode
	 * @param userId
	 * @param begin
	 * @param end
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/courseTest", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String courseTest(SchoolUserCondition schoolUserCondition,
			@RequestParam(value = "kind", required = false) String kind,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		DecimalFormat df = new DecimalFormat("#.##");
		/**
		 * ??????teamId
		 */
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		
		List<Double> ratio = new ArrayList<Double>();
		try {
			if(kind.equals("1")) {//?????????????????????????????????
				Double questionCount = pjUserActionService.findQuestionCountSumByUserId(0, team.getId(), dateFormat(end), endDateFormat(end), subjectCode);
				if(subjectCode.equals("")) {
					List<PjUserActionTestVo> testList = new ArrayList<PjUserActionTestVo>();
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), "", dateFormat(end), endDateFormat(end));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), "", dateFormat(end), endDateFormat(end), i);
						testList.add(test);
					}
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}else {
					List<PjUserActionTestVo> testList = new ArrayList<PjUserActionTestVo>();
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), "", dateFormat(end), endDateFormat(end));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), subjectCode, dateFormat(end), endDateFormat(end), i);
						testList.add(test);
					}
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}
				obj.put("?????????????????????", ratio);
				double average = 0.0;
				for(Double d : ratio) {
					average += d;
				}
				String a = df.format((average/ratio.size())*100);
				obj.put("????????????", a);
			}else if(kind.equals("2")) {//?????????????????????????????????
				List<String> thisWeek = getThisWeekTime(end);
				Double questionCount = pjUserActionService.findQuestionCountSumByUserId(0, team.getId(), dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), subjectCode);
				
				List<PjUserActionTestVo> testList = new ArrayList<PjUserActionTestVo>();
				if(subjectCode.equals("")) {
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), "", dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), "", dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), i);
						testList.add(test);
					}
					
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}else {
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)), i);
						testList.add(test);
					}
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}
				obj.put("?????????????????????", ratio);
				double average = 0.0;
				for(Double d : ratio) {
					average += d;
				}
				String a = df.format((average/ratio.size())*100);
				obj.put("????????????", a);
			}else if(kind.equals("3")) {
				String[] date = end.split("-");
				String da = getDateLastDay(date[0],date[1]);
				
				List<String> thisMonthTime = new ArrayList<String>();
				String lastTime = "";
				String endTime = "";
				thisMonthTime = getThisMonthTime(da);
				lastTime = thisMonthTime.get(0);
				endTime = thisMonthTime.get(1);
				Double questionCount = pjUserActionService.findQuestionCountSumByUserId(0, team.getId(), dateFormat(lastTime), endDateFormat(endTime), subjectCode);
				
				List<PjUserActionTestVo> testList = new ArrayList<PjUserActionTestVo>();
				if(subjectCode.equals("")) {
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), "", dateFormat(lastTime), endDateFormat(endTime));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), "", dateFormat(lastTime), endDateFormat(endTime), i);
						testList.add(test);
					}
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}else {
					List<Integer> userIdList = pjUserActionService.findUserIdListByTeamId(team.getId(), subjectCode, dateFormat(lastTime), endDateFormat(endTime));
					for(Integer i : userIdList) {
						PjUserActionTestVo test = pjUserActionService.findStudentTestSumByTeamId(team.getId(), subjectCode, dateFormat(lastTime), endDateFormat(endTime), i);
						testList.add(test);
					}
					if(questionCount != null && questionCount != 0.0) {
						for(int i=0;i<testList.size();i++) {
							ratio.add(Double.parseDouble(df.format(testList.get(i).getValue() / questionCount)));
						}
					}
					Collections.sort(ratio, Collections.reverseOrder());
					//????????????
					for(int i=0;i<ratio.size();i++) {
						for(int j=0;j<testList.size();j++) {
							if(ratio.get(i).equals(Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)))) {
								if(testList.get(j).getUserId().equals(userId)) {
									obj.put("????????????", testList.get(j).getName());
									obj.put("????????????", Double.parseDouble(df.format(testList.get(j).getValue() / questionCount)));
									obj.put("????????????", i+1);
								}
							}
						}
					}
				}
				obj.put("?????????????????????", ratio);
				double average = 0.0;
				for(Double d : ratio) {
					average += d;
				}
				String a = df.format((average/ratio.size())*100);
				obj.put("????????????", a);
			}else {
				obj.put("error", "??????????????????");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", "?????????????????????");
		}
		json.add(obj);
		return json.toString();
	}
	
	/**
	 * ????????????
	 * @param schoolUserCondition
	 * @param kind
	 * @param subjectCode
	 * @param userId
	 * @param begin
	 * @param end
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/appraise", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String appraise(SchoolUserCondition schoolUserCondition,
			@RequestParam(value = "kind", required = false) String kind,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "begin", required = false) String begin,
			@RequestParam(value = "end", required = false) String end) {
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		
		/**
		 * ??????teamId
		 */
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType("4");
		List<SchoolUser> schoolUser = schoolUserService.findSchoolUserByCondition(schoolUserCondition, null, null);
		Team team = teamService.findCurrentTeamOfStudent(userId, schoolUser.get(0).getSchoolId());
		
		try {
			List<Integer> likeSum = new ArrayList<Integer>();
			List<Integer> honorSum = new ArrayList<Integer>();
			Integer likeHigh = 0;
			Integer likeLow = 0;
			Integer likePersonal = 0;
			Integer honorHigh = 0;
			Integer honorLow = 0;
			Integer honorPersonal = 0;
			if(kind.equals("1")) {
				List<Integer> likeUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.likeCounter, subjectCode, dateFormat(end), endDateFormat(end));
				for(Integer l : likeUserIdList) {
					likeSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.likeCounter, subjectCode, dateFormat(end), endDateFormat(end)));
				}
				if(likeSum.size() == 0) {
					likeHigh += 0;
					likeLow += 0;
					likePersonal += 0;
				}else {
					likeHigh += Collections.max(likeSum);
					likeLow += Collections.min(likeSum);
					likePersonal += pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.likeCounter, subjectCode, dateFormat(end), endDateFormat(end));
				}
				
				List<Integer> honorUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.HONORSCORE, subjectCode, dateFormat(end), endDateFormat(end));
				for(Integer l : honorUserIdList) {
					honorSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.HONORSCORE, subjectCode, dateFormat(end), endDateFormat(end)));
				}
				if(honorSum.size() == 0) {
					honorHigh += 0;
					honorLow += 0;
					honorPersonal += 0;
				}else {
					honorHigh += Collections.max(honorSum);
					honorLow += Collections.min(honorSum);
					honorPersonal += pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.HONORSCORE, subjectCode, dateFormat(end), endDateFormat(end));
				}
			}else if(kind.equals("2")) {
				List<String> thisWeek = getThisWeekTime(end);
				List<Integer> likeUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.likeCounter, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
				for(Integer l : likeUserIdList) {
					likeSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.likeCounter, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1))));
				}
				if(likeSum.size() == 0) {
					likeHigh += 0;
					likeLow += 0;
				}else {
					likeHigh += Collections.max(likeSum);
					likeLow += Collections.min(likeSum);
				}
				Integer likeCount = pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.likeCounter, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
				if(likeCount == null) {
					likePersonal += 0;
				}else {
					likePersonal += likeCount;
				}
				
				List<Integer> honorUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.HONORSCORE, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
				for(Integer l : honorUserIdList) {
					honorSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.HONORSCORE, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1))));
				}
				Integer honorScoure = pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.HONORSCORE, subjectCode, dateFormat(thisWeek.get(0)), endDateFormat(thisWeek.get(1)));
				if(honorScoure == null) {
					honorPersonal += 0;
				}else {
					honorPersonal += honorScoure;
				}
				if(honorSum.size() == 0) {
					honorHigh += 0;
					honorLow += 0;
				}else {
					honorHigh += Collections.max(honorSum);
					honorLow += Collections.min(honorSum);
				}
			}else if(kind.equals("3")){
				String[] date = end.split("-");
				String da = getDateLastDay(date[0],date[1]);
				
				List<String> thisMonthTime = new ArrayList<String>();
				String lastTime = "";
				String endTime = "";
				thisMonthTime = getThisMonthTime(da);
				lastTime = thisMonthTime.get(0);
				endTime = thisMonthTime.get(1);
				List<Integer> likeUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.likeCounter, subjectCode, dateFormat(lastTime), endDateFormat(endTime));
				for(Integer l : likeUserIdList) {
					likeSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.likeCounter, subjectCode, dateFormat(lastTime), endDateFormat(endTime)));
				}
				if(likeSum.size() == 0) {
					likeHigh += 0;
					likeLow += 0;
				}else {
					likeHigh += Collections.max(likeSum);
					likeLow += Collections.min(likeSum);
				}
				Integer likeCount = pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.likeCounter, subjectCode, dateFormat(lastTime), endDateFormat(endTime));
				if(likeCount == null) {
					likePersonal += 0;
				}else {
					likePersonal += likeCount;
				}
				
				List<Integer> honorUserIdList = pjUserActionService.findClassAssessmentStudentList(team.getId(), UserActionContans.HONORSCORE, subjectCode, dateFormat(lastTime), endDateFormat(endTime));
				for(Integer l : honorUserIdList) {
					honorSum.add(pjUserActionService.findAppraiseSum(team.getId(), l, UserActionContans.HONORSCORE, subjectCode, dateFormat(lastTime), endDateFormat(endTime)));
				}
				Integer honorScoure = pjUserActionService.findAppraiseSum(team.getId(), userId, UserActionContans.HONORSCORE, subjectCode, dateFormat(lastTime), endDateFormat(endTime));
				if(honorScoure == null) {
					honorPersonal += 0;
				}else {
					honorPersonal += honorScoure;
				}
				if(honorSum.size() == 0) {
					honorHigh += 0;
					honorLow += 0;
				}else {
					honorHigh += Collections.max(honorSum);
					honorLow += Collections.min(honorSum);
				}
			}else{
				obj.put("error", "??????????????????");
			}
			obj.put("????????????", "["+likePersonal+","+honorPersonal+"]");
			obj.put("???????????????", "["+likeHigh+","+honorHigh+"]");
			obj.put("???????????????", "["+likeLow+","+honorLow+"]");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", "?????????????????????");
		}
		json.add(obj);
		return json.toString();
	}
	
	/**
	 * ????????????,???????????????23???59???59???
	 * @param d
	 * @return
	 */
	private static Date endDateFormat(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			long da = sdf.parse(d).getTime();
			da += 86399000;
			date.setTime(da);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * ????????????,???????????????0???0???0???
	 * @param d
	 * @return
	 */
	private static Date dateFormat(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * ??????????????????????????????
	 * @param year
	 * @param month
	 * @return
	 */
	private static String getDateLastDay(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month)-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(calendar.getTime());
	}
	
	/**
	 * ?????????????????????????????????
	 * @param begin
	 * @param end
	 * @return
	 */
	private static List<Date> getBetweenDates(String begin, String end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		Date beginDate = dateFormat(begin);
		Date endDate = dateFormat(end);
		
		tempStart.setTime(beginDate);
		
		while(beginDate.getTime()<=endDate.getTime()){
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			beginDate = tempStart.getTime();
		}
		return result;
	}
	
	/**
	 * ???????????????????????????????????????????????????????????????????????????
	 * @param today
	 * @return
	 * @throws ParseException
	 */
	private static List<String> getThisWeekTime(String today) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		beginDate = sdf.parse(today);
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		int offset = 1 - dayOfWeek;
		cal.add(Calendar.DATE, offset - 7);
		cal.setTime(beginDate);
		Date d = cal.getTime();
		String day = sdf.format(d);
		
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(sdf.parse(day));
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		Date beg=cal.getTime();
		String lastBeginDate = sdf.format(beg);
		
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 4);
		Date end=cal.getTime();
		String lastEndDate = sdf.format(end);
		
		List<String> date = new ArrayList<String>();
		date.add(lastBeginDate);
		date.add(lastEndDate);
		return date;
	}
	
	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * @return
	 * @throws ParseException 
	 */
	private static List<String> getLastWeekTime(String today) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Date beginDate = new Date();
		beginDate = sdf.parse(today);
		cal1.setTime(beginDate);
		cal2.setTime(beginDate);
		
		int dayOfWeek = cal1.get(Calendar.DAY_OF_WEEK) - 1;
		
		int offset1 = 1 - dayOfWeek;
		int offset2 = 5 - dayOfWeek;
		cal1.add(Calendar.DATE, offset1 - 7);
		cal2.add(Calendar.DATE, offset2 - 7);
		
		String lastBeginDate = sdf.format(cal1.getTime());
		String lastEndDate = sdf.format(cal2.getTime());
		
		List<String> date = new ArrayList<String>();
		date.add(lastBeginDate);
		date.add(lastEndDate);
		return date;
	}
	
	/**
	 * ?????????????????????????????????????????????????????????????????????????????????
	 * @return
	 * @throws ParseException 
	 */
	private static List<String> getLastMonthTime(String today) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		
		//????????????????????????
		Calendar cale1=Calendar.getInstance();//??????????????????
		d = format.parse(today);
		cale1.setTime(d);
		cale1.add(Calendar.MONTH, -1);
		cale1.set(Calendar.DAY_OF_MONTH,1);//?????????1???,?????????????????????????????????
		String firstDay = format.format(cale1.getTime());
		
		//???????????????????????????
		Calendar cale2 = Calendar.getInstance();
		d = format.parse(today);
		cale2.setTime(d);
		cale2.set(Calendar.DAY_OF_MONTH,0);//?????????1???,?????????????????????????????????
		String lastDay = format.format(cale2.getTime());
		
		List<String> date = new ArrayList<String>();
		date.add(firstDay);
		date.add(lastDay);
		return date;
	}
	
	/**
	 * ?????????????????????????????????????????????????????????????????????????????????
	 * @return
	 * @throws ParseException 
	 */
	private static List<String> getThisMonthTime(String today) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = new Date();
		
		//???????????????????????????
		Calendar c = Calendar.getInstance();
		d = format.parse(today);
		c.setTime(d);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//?????????1???,????????????????????????????????? 
		String first = format.format(c.getTime());
		
		//???????????????????????????
		Calendar ca = Calendar.getInstance();
		d = format.parse(today);
		ca.setTime(d);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
		
		List<String> date = new ArrayList<String>();
		date.add(first);
		date.add(last);
		return date;
	}
	
}
