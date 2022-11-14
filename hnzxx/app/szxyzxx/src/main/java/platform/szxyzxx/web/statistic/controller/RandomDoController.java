package platform.szxyzxx.web.statistic.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.service.ExamWorksService;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamSubjectService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.ScoreAnalysisHandleService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreAnalysisDataVo;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreSortVo;
import platform.education.learningDesign.model.LpTask;
import platform.education.learningDesign.model.LpTaskUnitUser;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.model.LpUnit;
import platform.education.learningDesign.model.LpUnitFile;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.service.LpTaskUnitUserService;
import platform.education.learningDesign.service.LpTaskUserService;
import platform.education.learningDesign.service.LpUnitFileService;
import platform.education.learningDesign.service.LpUnitService;
import platform.education.learningDesign.vo.LpState;
import platform.education.learningDesign.vo.LpTaskCondition;
import platform.education.learningDesign.vo.LpTaskUnitUserCondition;
import platform.education.learningDesign.vo.LpTaskUserCondition;
import platform.education.learningDesign.vo.LpUnitCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestionResult;
import platform.education.paper.model.mobile.AnswerJson;
import platform.education.paper.model.mobile.AnswersJson;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.vo.UserPaperCondition;
import platform.szxyzxx.services.statistic.service.BatchCreateAccountService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.CasTest;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * @功能描述: 随机做题控制器
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年11月14日上午10:55:01
 */

@Controller
@RequestMapping("/random")
public class RandomDoController {
	
	private final static String KULIAN = "<center><p><h1>没有使用权利，洗洗睡吧！<h1></p><img style=\"width: 500px;\"src=\"/res/images/demo/kulian.jpg\" /></center>";
	private final static String RESULT = "<h1>恭喜随机答案生成成功，老铁 放烟花庆祝下!</h1><br/><img src=\"/res/images/demo/yanhua.gif\" />";
	
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;
	
	@Autowired
	@Qualifier("userPaperService")
	private UserPaperService userPaperService;
	
	@Autowired
	@Qualifier("paperHandleService")
	private PaperHandleService paperHandleService;
	
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	@Autowired
	@Qualifier("lpTaskService")
	private LpTaskService lpTaskService;
	
	
	@Autowired
	@Qualifier("lpTaskUnitUserService")
	private LpTaskUnitUserService lpTaskUnitUserService;
	
	
	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;
	
	@Autowired
	@Qualifier("lpUnitService")
	private LpUnitService lpUnitService;
	
	
	@Autowired
	@Qualifier("lpUnitFileService")
	private LpUnitFileService lpUnitFileService;
	
	
	
	@Autowired
	@Qualifier("scoreAnalysisHandleService")
	private ScoreAnalysisHandleService scoreAnalysisHandleService;
	
	
	@Autowired
	@Qualifier("examWorksService")
	private ExamWorksService examWorksService;
	
	@Autowired
	@Qualifier("examStudentService")
	private ExamStudentService examStudentService;
	
	@Autowired
	@Qualifier("examWorksTeamSubjectService")
	private ExamWorksTeamSubjectService examWorksTeamSubjectService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("examWorksSubjectService")
	private ExamWorksSubjectService examWorksSubjectService;
	
	@Autowired
	@Qualifier("batchCreateAccountService")
	private BatchCreateAccountService batchCreateAccountService;
	
	
	@RequestMapping("index")
	public String randomUI() {
		
		return "demo/index";
	}
	
	@RequestMapping("autoLogin")
	public String autoLogin(Model model,HttpServletRequest request ,HttpServletResponse response) throws Exception {
		//final String server = "http://127.0.0.1/cas/v1/tickets";
	    final String username = "116001";
	    final String password = "123456";
	    //final String service = "http://127.0.0.1/sso";
	    Map<String,String> map = CasTest.validateFromCAS(username,password);
		//String lt = Client.getTicket(server, username, password, service);
		model.addAttribute("result", map.get("lt"));
		Cookie cookie = new Cookie("CASTGC",map.get("tgt"));
		cookie.setPath("/cas");
		response.addCookie(cookie);
		return "demo/autoLogin";
	}
	
	
	
	
	
	
	/**
	 * 随机做试卷
	 * @return
	 */
	@RequestMapping("/quan/demo/exam")
	@ResponseBody
	public String randomDoExam(@RequestParam("corporate") String corporate,@RequestParam("gradeId") Integer gradeId,@RequestParam("ownerId") Integer ownerId,@RequestParam("paperId") Integer paperId) {
		
		if(!"迈奇拓".equals(corporate)) {
			return KULIAN;
		}
		//1. 根据年级ID 查询该年级下所有班级，所有人员
		List<Team> teamList = teamService.findTeamOfGradeByAsc(gradeId);
		String paperUuid = "";
		if(teamList != null && teamList.size() > 0){
			PaPaper paper = paPaperService.findPaPaperById(paperId);
			if(paper != null) {
				paperUuid = paper.getPaperUuid();
			}
			for(Team team : teamList){
				//2.循环查询每个班级的学生238
				if(team != null){
					Integer teamId = team.getId();
					List<Student> studentList = studentService.findStudentByTeamId(teamId);
					if(studentList != null && studentList.size() > 0){
						
						for(Student student:studentList){
							
							if(student != null){
								Integer studentUserId = student.getUserId();
								
								if(!isRepeat(paperUuid,studentUserId,PaperType.EXAM,ownerId,null)) {
									
									appendData(teamId,studentUserId,paperUuid,ownerId,paperId,PaperType.EXAM,null);
								}
								
							}
						}
					}
				}
			}
		}
		
		return RESULT;
	}
	
	
	@RequestMapping("/quan/demo/learningPlan")
	@ResponseBody
	public String randomDoLearningPlan(@RequestParam("corporate") String corporate,@RequestParam("taskId") Integer taskId) {
		if(!"迈奇拓".equals(corporate)) {
			return KULIAN;
		}
		
		LpTask task = this.lpTaskService.findLpTaskById(taskId);
		LpTaskCondition lpTaskCondition = new LpTaskCondition();  
		lpTaskCondition.setUuid(task.getUuid());
		lpTaskCondition.setIsDeleted(false);
		List<LpTask> taskList = this.lpTaskService.findLpTaskByCondition(lpTaskCondition);
		
		LpTaskUser lpTaskUser = null;
		if(taskList != null && taskList.size() > 0) {
			for(LpTask lpTask :taskList) {
				LpUnitCondition lpUnitCondition  = new LpUnitCondition();
				lpUnitCondition.setIsDeleted(false);
				lpUnitCondition.setLpId(lpTask.getLpId());
				List<LpUnit> lpUnitList = this.lpUnitService.findLpUnitByCondition(lpUnitCondition);
				if(lpUnitList != null && lpUnitList.size() > 0) {
					
					for(LpUnit llpUnit :lpUnitList) {
						LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
						lpTaskUnitUserCondition.setLpId(lpTask.getLpId());
						lpTaskUnitUserCondition.setUnitId(llpUnit.getId());
						lpTaskUnitUserCondition.setTaskId(lpTask.getId());
						//更新导学案单元状态
						List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);
						if(!"2".equals(llpUnit.getUnitType())) { //非试卷就直接调用完成
							if(lpTaskUnitUserList != null && lpTaskUnitUserList.size() > 0) {
								for(LpTaskUnitUser lpTaskUnitUser:lpTaskUnitUserList) {
									lpTaskUnitUser.setHasFinished(true);
									lpTaskUnitUserService.updateFinishState(lpTaskUnitUser.getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
								}
							}
							
						}else{ //试卷单元
							if(lpTaskUnitUserList != null && lpTaskUnitUserList.size() > 0) {
								for(LpTaskUnitUser lpTaskUnitUser:lpTaskUnitUserList) {
									List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(lpTaskUnitUser.getUnitId());
									if(lpUnitFileList != null && lpUnitFileList.size() > 0) {
										for(LpUnitFile LpUnitFile :lpUnitFileList) {
											PaPaper paPaper = this.paPaperService.findPaPaperByUUid(LpUnitFile.getObjectUuid());
											Integer studentUserId = lpTaskUnitUser.getUserId();
											if(!isRepeat(LpUnitFile.getObjectUuid(),studentUserId,PaperType.LEARNING_PLAN,lpTask.getId(),lpTaskUnitUser.getUnitId())) {
												appendData(lpTask.getObjectId(),studentUserId,paPaper.getPaperUuid(),lpTask.getId(),paPaper.getId(),PaperType.LEARNING_PLAN,lpTaskUnitUser.getUnitId());
											}
										}
									}
									
									lpTaskUnitUser.setHasFinished(true);
									lpTaskUnitUserService.updateFinishState(lpTaskUnitUser.getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
									
								}
							}
						}
					}
				}
				
				//更新导学案状态
				LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
				lpTaskUserCondition.setTaskId(lpTask.getId());
				lpTaskUserCondition.setIsDeleted(false);
				List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);
				if(lpTaskUserList != null && lpTaskUserList.size()>0) {
					for(LpTaskUser lpTUser :lpTaskUserList) {
						if(!LpState.LEARNING.equals(lpTUser.getState())) {
							lpTUser.setState(LpState.FINISH_LEARN);
							lpTaskUserService.updateLpState(lpTUser.getId(), LpState.FINISH_LEARN, new Date());
						}
					}
				//	lpTaskUser = lpTaskUserList.get(0);
					/**如果学习状态不是学习中，则更改状态为学习中*/
				}
			}
		}
		
		return RESULT;
	}
	
	
	//判断是否重复提交
	private Boolean isRepeat(String paperUuId,Integer userId, Integer type,Integer ownerId,Integer unitId) {
		Boolean flag = false;
		UserPaperCondition userPaperCondition = new UserPaperCondition();
		userPaperCondition.setPaperUuid(paperUuId);
		userPaperCondition.setUserId(userId);
		userPaperCondition.setType(type);
		userPaperCondition.setOwnerId(ownerId);
		userPaperCondition.setObjectId(unitId);
		List<UserPaper> userPaperList = userPaperService.findUserPaperByCondition(userPaperCondition);
		if(userPaperList != null && userPaperList.size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	private String appendData(Integer teamId,Integer studentUserId,String paperUuid,Integer ownerId,Integer paperId,Integer type,Integer unitId){
		
		String  termCode = null;
		String  schoolYear = null;
		try {
		//通过用户ID查找对应的学校ID
		Student student = this.studentService.findStudentByUserId(studentUserId);
		if(student != null){
			Integer schoolId = student.getSchoolId();
			//获取当前学年、当前学期
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(schoolTermCurrent != null){
				termCode = schoolTermCurrent.getSchoolTermCode();
				schoolYear = schoolTermCurrent.getSchoolYear();
			}
			
		}
		
		//1. 根据试卷uuid查询该份试卷下的所有题目
		List<AnswersJson> demoEntityList = new ArrayList<AnswersJson>();
		List<UserQuestionResult> userQuestionResultList  = userQuestionService.findUserQuestionByPaperIdDemo(paperId);
		if(userQuestionResultList != null && userQuestionResultList.size() > 0){
			for(UserQuestionResult userQuestionResult :userQuestionResultList){
				AnswersJson demoEntity = new AnswersJson();
				demoEntity.setQuestionUuid(userQuestionResult.getQuestionUuid());
				demoEntity.setAnswerTime(appendAnswerTime());
				demoEntity.setIsCorrect(appendCorrect());
				AnswerJson json = new AnswerJson();
				json.setUuid(userQuestionResult.getQuestionUuid());
				String []answerStr = new String[] {appendAnswer()};
				json.setAnswer(answerStr);
				AnswerJson[] answerJson = new AnswerJson[]{json};
				demoEntity.setAnswer(answerJson);
				demoEntityList.add(demoEntity);
			}
		}
			ObjectMapper mapper = new ObjectMapper();
			String answers = mapper.writeValueAsString(demoEntityList);
			
			paperHandleService.uploadPaperAnswer(paperUuid, teamId, null, studentUserId, answers, type, ownerId, type, schoolYear, termCode, unitId);
			if(PaperType.EXAM == type) {
				taskService.modifyFinished(ownerId, studentUserId);
			}
			//paperHandleService.uploadPaperAnswer(paperUuid, teamId, null, studentUserId, answers, PaperType.EXAM, ownerId, null, schoolYear, termCode,null, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}


		//随机生成作答时间
		private static int appendAnswerTime(){
			int max =300;
			int min = 100;
			int date =(int) Math.round(Math.random()*(max-min)+min);
			return date;
		}

		//随机生成答案
		private static Integer appendCorrect(){
			
			return Math.abs((new Random().nextInt())%2);
		}
		
	//随机生成答案
	private static String appendAnswer(){
	    String[] A_z = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N  ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		Random r = new Random();
	    int sub = r.nextInt(3);
	    String answer = A_z[sub];
		return answer;
	}

	
	
	/**
	 * 成绩分析导入接口
	 * @return
	 */
	@RequestMapping("/quan/demo/score")
	@ResponseBody
	public String randomDoScore(@CurrentUser UserInfo user,@RequestParam("type") Integer type,@RequestParam("corporate") String corporate,@RequestParam("objectId") Integer objectId,@RequestParam("examWorksId") Integer examWorksId,@RequestParam("subjectCode") String subjectCode) {
	
		if(!"泉少".equals(corporate)) {
			return KULIAN;
		}
		
		switch (type) {
		case 1: //班级测试
			if(subjectCode != null && !"".equals(subjectCode)) {
				String []subjectCodes = subjectCode.split(",");
				if(subjectCodes != null && subjectCodes.length > 0) {
					List<ScoreAnalysisDataVo> scoreAnalysisData = new ArrayList<ScoreAnalysisDataVo>();
					Team team = this.teamService.findTeamById(objectId);
							for(String code:subjectCodes) {
									List<Student> studentList = studentService.findStudentByTeamId(team.getId());
									if(studentList != null && studentList.size() > 0) {
										for(Student student:studentList) {
											ScoreAnalysisDataVo scoreAnalysisDataVo = new ScoreAnalysisDataVo();
											scoreAnalysisDataVo.setUserId(student.getUserId());
											scoreAnalysisDataVo.setName(student.getName());
											scoreAnalysisDataVo.setScore(getScore());
											scoreAnalysisData.add(scoreAnalysisDataVo);
										}
										importTeamScore(examWorksId,scoreAnalysisData,team.getGradeId(),team.getId(),code,user.getSchoolId(),user.getId());
									}
								
							}
						}
				}
			
			break;
		default: //年级统考
		
			if(subjectCode != null && !"".equals(subjectCode)) {
				String []subjectCodes = subjectCode.split(",");
				if(subjectCodes != null && subjectCodes.length > 0) {
					List<ScoreAnalysisDataVo> scoreAnalysisData = new ArrayList<ScoreAnalysisDataVo>();
					//importGradeScore(Integer examWorksId,List<ScoreAnalysisDataVo> scoreAnalysisData,Integer objectId,Integer teamId,String subjectCode,Integer userId)
					List<Team> teamList = this.teamService.findTeamOfGrade(objectId);
						if(teamList != null && teamList.size() > 0) {
							for(String code:subjectCodes) {
								for(Team team:teamList) {
									List<Student> studentList = studentService.findStudentByTeamId(team.getId());
									if(studentList != null && studentList.size() > 0) {
										for(Student student:studentList) {
											ScoreAnalysisDataVo scoreAnalysisDataVo = new ScoreAnalysisDataVo();
											scoreAnalysisDataVo.setUserId(student.getUserId());
											scoreAnalysisDataVo.setName(student.getName());
											scoreAnalysisDataVo.setScore(getScore());
											scoreAnalysisData.add(scoreAnalysisDataVo);
										}
										importGradeScore(examWorksId,scoreAnalysisData,objectId,team.getId(),code,user.getId());
									}
								}
								
							}
						}
				}
			}
			
			break;
		}
		
		//1.查询当前考务信息
		
		
		return null;
	}
	
	
	private void importTeamScore(Integer examWorksId, List<ScoreAnalysisDataVo> scoreAnalysisData,Integer gradeId,Integer teamId,String subjectCode,Integer schoolId,Integer userId) {
		
		  ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
	  		//final boolean isJoinExam = examWorks.getIsJointExam();
	  		//1. 反序列模板数据
	  	//	final List<ScoreAnalysisDataVo> scoreAnalysisData = mapper.readValue(dataJson, new TypeReference<List<ScoreAnalysisDataVo>>(){});
	  		//先把学生成绩入库。。。
			 
		  
		  ExamWorksSubject examWorksSubject =  examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
			float fullScore = examWorksSubject.getFullScore();
		  	float highScore = examWorksSubject.getHighScore();
		    float lowScore = examWorksSubject.getLowScore();
		    float passScore = examWorksSubject.getPassScore();
			String examTime =  dateToStr(examWorks.getExamDateBegin(),"yyyy-MM-dd HH:mm:ss") ;
	  		ExamWorksTeamSubject examWorksTeamSubject =	scoreAnalysisHandleService.updateExamDate(examWorksId,teamId,examTime,subjectCode);
	  		if(examWorksTeamSubject != null ) {
	  			final Integer examId = examWorksTeamSubject.getExamId();
	      		final List<ScoreSortVo> list = new ArrayList<ScoreSortVo>();
					ScoreSortVo mp = null;
					//3. 更新pjExamStudent表的score字段
					List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(examId);
					if(examStudentList != null && examStudentList.size() > 0) {
						for(ExamStudent examStudent:examStudentList) {
							for(ScoreAnalysisDataVo scoreAnalysisDataVo:scoreAnalysisData) {
								//找到对应的学生更新得分
								if(examStudent.getUserId() - scoreAnalysisDataVo.getUserId() == 0) {
									mp = new ScoreSortVo();
									mp.setId(examStudent.getId());
									mp.setScore(scoreAnalysisDataVo.getScore());
									list.add(mp);
								}
							}
						}
					}
					
					//批量更新学生数据
					if(list != null && list.size() > 0) {
						examStudentService.batchUpdateExamStudentScore(list.toArray());
					}
					try {
						scoreAnalysisHandleService.importTeamExamScore(examWorksId,teamId,examTime,fullScore,highScore,lowScore,passScore,schoolId,gradeId,subjectCode,scoreAnalysisData,list,examId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				//	scoreAnalysisHandleService.importGeneralExamScore(examWorksId, objectId, teamId, subjectCode, examTime, scoreAnalysisData, list, examId);
					
	      	}
	  		
	  	//导入成功更新 pj_exam_works_team_subject表 导入人，导入时间等字段
	  	examWorksTeamSubject = this.examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
	  	if(examWorksTeamSubject != null) {
	  		List<Teacher> teacherList = this.teacherService.findTeacherByUserId(userId);
	  		if(teacherList != null && teacherList.size() > 0) {
	  			Teacher teacher = teacherList.get(0);
	  			examWorksTeamSubject.setPostTeacherId(teacher.getId());
	  		}
	  		examWorksTeamSubject.setPostTime(new Date());
	  		examWorksTeamSubjectService.modify(examWorksTeamSubject);
	  	}
	  	
	      //班级小测导入成功后更新 pj_exam_works表的起止时间
	      if (!examWorks.getIsJointExam()) {
	          ExamWorks examWorks_ = new ExamWorks(examWorksId);
	          examWorks_.setExamDateBegin(examWorks.getExamDateBegin());
	          examWorks_.setExamDateEnd(examWorks.getExamDateBegin());
	          examWorksService.modify(examWorks_);
	      }
		
		
		
		
		
		
		
	}
	
	
	private void importGradeScore(Integer examWorksId,List<ScoreAnalysisDataVo> scoreAnalysisData,Integer objectId,Integer teamId,String subjectCode,Integer userId) {
		  ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
  		//final boolean isJoinExam = examWorks.getIsJointExam();
  		//1. 反序列模板数据
  	//	final List<ScoreAnalysisDataVo> scoreAnalysisData = mapper.readValue(dataJson, new TypeReference<List<ScoreAnalysisDataVo>>(){});
  		//先把学生成绩入库。。。
		  
		String examTime =  dateToStr(examWorks.getExamDateBegin(),"yyyy-MM-dd HH:mm:ss") ;
  		ExamWorksTeamSubject examWorksTeamSubject =	scoreAnalysisHandleService.updateExamDate(examWorksId,teamId,examTime,subjectCode);
  		if(examWorksTeamSubject != null ) {
  			final Integer examId = examWorksTeamSubject.getExamId();
      		final List<ScoreSortVo> list = new ArrayList<ScoreSortVo>();
				ScoreSortVo mp = null;
				//3. 更新pjExamStudent表的score字段
				List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(examId);
				if(examStudentList != null && examStudentList.size() > 0) {
					for(ExamStudent examStudent:examStudentList) {
						for(ScoreAnalysisDataVo scoreAnalysisDataVo:scoreAnalysisData) {
							//找到对应的学生更新得分
							if(examStudent.getUserId() - scoreAnalysisDataVo.getUserId() == 0) {
								mp = new ScoreSortVo();
								mp.setId(examStudent.getId());
								mp.setScore(scoreAnalysisDataVo.getScore());
								list.add(mp);
							}
						}
					}
				}
				
				//批量更新学生数据
				if(list != null && list.size() > 0) {
					examStudentService.batchUpdateExamStudentScore(list.toArray());
				}
			
				
				scoreAnalysisHandleService.importGeneralExamScore(examWorksId, objectId, teamId, subjectCode, examTime, scoreAnalysisData, list, examId);
				
      	}
  		
  	//导入成功更新 pj_exam_works_team_subject表 导入人，导入时间等字段
  	examWorksTeamSubject = this.examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
  	if(examWorksTeamSubject != null) {
  		List<Teacher> teacherList = this.teacherService.findTeacherByUserId(userId);
  		if(teacherList != null && teacherList.size() > 0) {
  			Teacher teacher = teacherList.get(0);
  			examWorksTeamSubject.setPostTeacherId(teacher.getId());
  		}
  		examWorksTeamSubject.setPostTime(new Date());
  		examWorksTeamSubjectService.modify(examWorksTeamSubject);
  	}
  	
      //班级小测导入成功后更新 pj_exam_works表的起止时间
      if (!examWorks.getIsJointExam()) {
          ExamWorks examWorks_ = new ExamWorks(examWorksId);
          examWorks_.setExamDateBegin(examWorks.getExamDateBegin());
          examWorks_.setExamDateEnd(examWorks.getExamDateBegin());
          examWorksService.modify(examWorks_);
      }
	}
	
	private String dateToStr(Date date,String fomatStr) {
		 String str = "";
			if(date!=null){
				 SimpleDateFormat format = new SimpleDateFormat(fomatStr);
				 str = format.format(date);
			}
		  
		   return str;
	}
	
	private float getScore() {
			
			DecimalFormat dcmFmt = new DecimalFormat("0.00");
			
			float f = (float)(Math.random()*90)+50;
			return f;
		}
	
	@RequestMapping("batchCreateAccount")
	@ResponseBody
	public String batchCreateAccount(@RequestParam("num") Integer num) {
		System.out.println("开始生成账号");
		Long s = System.currentTimeMillis();
		try {
			batchCreateAccountService.batchCreateAccount(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long e = System.currentTimeMillis();
		return "成功成功:花费时间："+ (e -s) +"毫秒";
	}
	
}
