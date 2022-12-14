package platform.szxyzxx.web.scoreAnalysis.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.model.ExamWorksGrade;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.ExamStatMajorStudentService;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.service.ExamWorksGradeService;
import platform.education.generalTeachingAffair.service.ExamWorksService;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamSubjectService;
import platform.education.generalTeachingAffair.service.ScoreAnalysisHandleService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ExamStatMajorStudentCondition;
import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.MqtHttpRequest;
import platform.szxyzxx.web.scoreAnalysis.vo.ResponseEntity;
/**
 * 
 * @????????????:????????????????????? 
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @????????????:2018???2???10?????????6:59:10
 */
@Controller
@RequestMapping("/scoreAnalysis/WeChat/")
public class ScoreAnalysisController {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final String baseUrl = SysContants.SCORE_ANALYSIS_PATH;

	/**????????????service???**/
	@Autowired
	@Qualifier("examWorksTeamSubjectService")
	protected ExamWorksTeamSubjectService examWorksTeamSubjectService;
	
	@Autowired
	@Qualifier("examStudentService")
	protected ExamStudentService examStudentService;
	
	@Autowired
	@Qualifier("examStatService")
	protected ExamStatService examStatService;
	
	@Autowired
	@Qualifier("examWorksService")
	protected ExamWorksService examWorksService;
	
	@Autowired
	@Qualifier("examStatMajorStudentService")
	protected ExamStatMajorStudentService examStatMajorStudentService;
	
	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;
	
	@Autowired
	@Qualifier("examWorksSubjectService")
	protected ExamWorksSubjectService examWorksSubjectService;
	
	@Autowired
	@Qualifier("examWorksGradeService")
	protected ExamWorksGradeService examWorksGradeService;
	
	@Autowired
	@Qualifier("teamService")
	protected TeamService teamService;
	
	@Autowired
	@Qualifier("teamStudentService")
	protected TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("examWorksTeamService")
	protected ExamWorksTeamService examWorksTeamService;
	
	
	@Autowired
	@Qualifier("scoreAnalysisHandleService")
	protected ScoreAnalysisHandleService scoreAnalysisHandleService;
	
	
	//????????????
	private static String TEAMDIR="scoreAnalysis/team/";
	private static String GRADEDIR="scoreAnalysis/grade/";
	
	/**
	 * ????????????????????????????????????????????????????????????
	 * @param uiMode
	 * @param schoolId ??????ID
	 * @param userId ??????ID
	 * @param parentId ??????ID
	 * @return
	 */
	@RequestMapping("list/{schoolId}/{userId}/{parentId}")
	public String index(Model uiMode,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {		
		
		//??????????????????????????????
		List<Map<String,Object>> schoolCurrets = scoreAnalysisHandleService.findSchoolTermByUserId(schoolId,userId);
		if(schoolCurrets != null && schoolCurrets.size() > 0) {
			for(int i=0;i<schoolCurrets.size() ;i++) {
				Map<String,Object> mp = schoolCurrets.get(i);
				Object isCurrent = mp.get("is_current");
				if(isCurrent != null) {
					Integer isCurrentInt = Integer.parseInt(isCurrent.toString());
					if(isCurrentInt == 1) {
						Integer teamId = Integer.parseInt(mp.get("team_id")+"");
						String termCode = mp.get("termCode")+"";
						String schoolYear = mp.get("schoolYear")+"";
						uiMode.addAttribute("teamId", teamId);
						uiMode.addAttribute("userId", userId);
						uiMode.addAttribute("termCode", termCode);
						uiMode.addAttribute("schoolYear", schoolYear);
						uiMode.addAttribute("slideIndex", i);
						uiMode.addAttribute("schoolId", schoolId);
					}
				}
			}
		}
		uiMode.addAttribute("schoolCurrets", schoolCurrets);
		uiMode.addAttribute("parentId", parentId);
		return TEAMDIR + "bj_free_list";
	}
	/**
	 * ????????????????????????????????????
	 * @param uiMode
	 * @param teamId ??????ID
	 * @param termCode ??????CODE
	 * @param schoolYear ??????
	 * @param index 
	 * @param userId ??????ID
	 * @return
	 */
	@RequestMapping("loadData/{teamId}/{termCode}/{schoolYear}/{index}/{userId}/{schoolId}/{parentId}")
	public String loadData(Model uiMode,@PathVariable("teamId") Integer teamId,@PathVariable("termCode") String termCode,@PathVariable("schoolYear") String schoolYear,@PathVariable("index") Integer index,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {
		Integer isJointExam = null;
		if(index == 1) {
			isJointExam = 1;
		}else if(index == 2) {
			isJointExam = 0; 
		}
		//??????????????????????????????
		List<Map<String,Object>> examWorks = scoreAnalysisHandleService.findAllExamWorksByTeam(teamId, schoolYear ,termCode, isJointExam,schoolId);
		if(examWorks != null && examWorks.size() > 0) {
			
			System.out.println("?????????-------------"+examWorks.get(0).get("publishTime"));
		}
		uiMode.addAttribute("examWorks", examWorks);
		uiMode.addAttribute("userId", userId);
		uiMode.addAttribute("schoolId", schoolId);
		uiMode.addAttribute("parentId", parentId);
		
		return "scoreAnalysis/list";
	}
	
	/**
	 * ????????????????????????
	 * @param uiMode
	 * @param examWorksId ??????ID
	 * @param userId ??????ID
	 * @param schoolId ??????ID
	 * @return
	 */
	@RequestMapping("teamFreeIndex/{examWorksId}/{schoolId}/{userId}/{parentId}")
	public String teamFreeIndex(Model uiMode,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("userId") Integer userId,@PathVariable("schoolId") Integer schoolId,@PathVariable("parentId") Integer parentId) {
		String path = TEAMDIR + "bj_free";
		
		if(examWorksId != null) {
			try {
				
				//???????????????????????????
				ResponseEntity responseEntity =	this.getServicesStatus(parentId, userId, schoolId);
				//1??????????????????VIP??????
				if(responseEntity != null) {
					String status = responseEntity.getStatus();
					String callbackurl = baseUrl + "/scoreAnalysis/WeChat/teamVipIndex/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
					if("0".equals(status)) {  //????????????????????????VIP??????
						return "redirect:"+callbackurl; 
					}else if("2".equals(status)) { //????????????
						uiMode.addAttribute("message", responseEntity.getMessage());
						return GRADEDIR + "error";
					}else if("1".equals(status)) { //?????????
						callbackurl = baseUrl + "/scoreAnalysis/WeChat/subscribe/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
						return "redirect:"+callbackurl;
					}
					
				}else {
					uiMode.addAttribute("message", "??????????????????");
					return GRADEDIR + "error";
				}
				
				//??????????????????????????????
				Map<String,Object> userTeamExam = scoreAnalysisHandleService.findTeamExamWorksByUserId(examWorksId, schoolId, userId);
				uiMode.addAttribute("userTeamExam", userTeamExam);
				uiMode.addAttribute("schoolId", schoolId);
				uiMode.addAttribute("userId", userId);
				if(userTeamExam.get("score") != null) {
					float score = Float.parseFloat(userTeamExam.get("score")+"");
					if(score == -1) { //??????
						return  TEAMDIR + "quekao";
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return path;
	}
	
	/**
	 * ????????????
	 * @return
	 */
	@RequestMapping("subscribe/{examWorksId}/{schoolId}/{userId}/{parentId}")
	public String subscribe(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {
		try {
			//???????????????????????????
			ResponseEntity responseEntity =	this.getServicesStatus(parentId, userId, schoolId);
			if(responseEntity != null) {
				String status = responseEntity.getStatus();
				if("1".equals(status)){ //?????????
					String callbackurl = "";
					ExamWorks examWorks = this.examWorksService.findExamWorksById(examWorksId);
					if(examWorks != null) {
						if(examWorks.getIsJointExam()) { //????????????gradeVipIndex
							callbackurl = baseUrl + "/scoreAnalysis/WeChat/gradeVipIndex/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
						}else { //????????????
							callbackurl = baseUrl + "/scoreAnalysis/WeChat/teamVipIndex/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
						}
					}
					String newCallbackurl = URLEncoder.encode(callbackurl,"UTF-8");
					//??????URL
					String payUrl = responseEntity.getJump_url() + "?callbackurl=" + newCallbackurl;
					uiModel.addAttribute("payUrl", payUrl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "scoreAnalysis/subscribe";
	}
	
	/**
	 * ??????VIP??????
	 * @param uiMode
	 * @param examWorksId ??????ID
	 * @param schoolId ??????ID
	 * @param userId ??????ID
	 * @return
	 */
	@RequestMapping("teamVipIndex/{examWorksId}/{schoolId}/{userId}/{parentId}*")
	public String teamVipIndex(Model uiMode,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {
		
		String path = TEAMDIR + "bj_vip";
		try {
			//???????????????????????????
			ResponseEntity responseEntity =	this.getServicesStatus(parentId, userId, schoolId);
			//1??????????????????VIP??????
			if(responseEntity != null) {
				String status = responseEntity.getStatus();
				String callbackurl = baseUrl + "/scoreAnalysis/WeChat/subscribe/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
				if("1".equals(status)) {  //?????????
					return "redirect:"+callbackurl;
				}else if("2".equals(status)) { //????????????
					uiMode.addAttribute("message", responseEntity.getMessage());
					return GRADEDIR + "error";
				}else if("0".equals(status)) { //????????????????????????VIP??????
					if(examWorksId != null) {
						ExamWorks examWorks = this.examWorksService.findExamWorksById(examWorksId);
						if(examWorks != null) {
							Map<String,Object> userTeamExam = scoreAnalysisHandleService.findTeamExamWorksByUserId(examWorksId, schoolId, userId);
							ExamWorksTeamSubject examWorksTeamSubject =  this.examWorksTeamSubjectService.findUnique(examWorksId, examWorks.getTeamId(), examWorks.getSubjectCode());
							if(examWorksTeamSubject != null) {
								Integer examId = examWorksTeamSubject.getExamId();
								Integer studentCount = scoreAnalysisHandleService.findExamStudentCount(examId,null);
								//?????????????????????
								ExamStat examStat = this.examStatService.findExamStatByExamId(examId);
								String subsection = "";
								Float userScore = 0f;
								if(userTeamExam != null && userTeamExam.size() > 0) {
									//????????????
									userScore = Float.parseFloat(userTeamExam.get("score")+"");
								}
								if(examStat != null) {
									if(userScore >= examStat.getHighScore()) { //?????????????????????
										subsection = "?????????";
									}else if((userScore >= examStat.getPassScore())){ //???????????????????????????
										subsection = "?????????";
									}else if(userScore <= examStat.getPassScore()){ //??????????????????
										subsection = "?????????";
									}
									//????????????
									uiMode.addAttribute("fullScore", examStat.getFullScore());	
								}
								//?????????
								uiMode.addAttribute("subsection", subsection);
								//???????????????????????????
								uiMode.addAttribute("studentCount", studentCount);
								
								//?????????????????????
								List<Map<Integer,Object>> examStudentList = this.scoreAnalysisHandleService.findTeamRanksByExamId(examId);
								if(examStudentList != null && examStudentList.size() > 0) {
									if(!examWorks.getShowRanking()) { //????????????
										if(userTeamExam.get("team_rank") != null) {
											float ratio = Integer.parseInt(userTeamExam.get("team_rank")+"") / examStudentList.size();
											userTeamExam.put("team_rank", getNameByRatio(ratio));
										}
									}
									List<Object> teamRankList = new ArrayList<Object>();
									List<Object> nteamRankList = new ArrayList<Object>();
									List<Float> teamScoreList = new ArrayList<Float>();
									float teamMaxScore = 0f;
									for(int i=0; i<examStudentList.size();i++) {
										Map<Integer,Object> map = examStudentList.get(i);
										
										//??????????????????????????????
										if(map.get("user_id")+"" != null) {
											if(Integer.parseInt(map.get("user_id")+"") == userId) {
												String s = "{marker: {radius: 10},y:"+map.get("teamRank")+",color:'#f18441'}";
												teamRankList.add(s);
											}else {
												teamRankList.add(map.get("teamRank"));
											}
											
										}
										if(map.get("score") != null) {
											float score = Float.parseFloat(map.get("score")+"");
											teamMaxScore = Float.parseFloat(examStudentList.get(0).get("score")+"");
											if(score > teamMaxScore) {
												teamMaxScore = score;
											}
											teamScoreList.add(score);
										}
										
									}
									uiMode.addAttribute("teamRankList", teamRankList);
									uiMode.addAttribute("teamScoreList", teamScoreList);
									uiMode.addAttribute("teamMaxScore", teamMaxScore);
									float result = teamAvgRank(teamScoreList,examStat.getAverageScore());
									ExamStudentCondition examStudentCondition = new ExamStudentCondition();
									examStudentCondition.setScore(result);
									examStudentCondition.setExamId(examId);
									List<ExamStudent> eList = this.examStudentService.findExamStudentByCondition(examStudentCondition);
									if(eList != null && eList.size() > 0) {
										ExamStudent e = eList.get(0);
										uiMode.addAttribute("teamAvgRank", e.getTeamRank());
									}
									
									//????????????
									List<Map<String,Object>> userFractionalTrendList = this.scoreAnalysisHandleService.findFractionalTrendByUserId(userId, examWorks.getTeamId());
									if(userFractionalTrendList != null &&userFractionalTrendList.size() > 0) {
										List<Object> userScoreList = new ArrayList<Object>();
										List<Object> titleList = new ArrayList<Object>();
										List<Object> avgScoreList = new ArrayList<Object>();
										for(int i=userFractionalTrendList.size()-1;i>=0 ;i--) {
											Map<String,Object> userFractionalTrend = userFractionalTrendList.get(i);
											if(userFractionalTrend.get("score") != null) {
												userScoreList.add(userFractionalTrend.get("score"));
												titleList.add(userFractionalTrend.get("name"));
												avgScoreList.add(userFractionalTrend.get("average_score"));
												nteamRankList.add(userFractionalTrend.get("team_rank"));
											}
										}
										uiMode.addAttribute("userScoreList", userScoreList);
										uiMode.addAttribute("titleList",  mapper.writeValueAsString(titleList));
										uiMode.addAttribute("avgScoreList", avgScoreList);
										uiMode.addAttribute("nteamRankList", mapper.writeValueAsString(nteamRankList));
										
									}
								}
								
							}
							
							uiMode.addAttribute("userTeamExam", userTeamExam);
							//??????????????????
							uiMode.addAttribute("showRanking", examWorks.getShowRanking());
						}
					}
				}else {
					uiMode.addAttribute("message", "??????????????????");
					return GRADEDIR + "error";
				}
			}else {
				uiMode.addAttribute("message", "??????????????????");
				return GRADEDIR + "error";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	
	@RequestMapping("gradeFreeIndex/{examWorksId}/{schoolId}/{userId}/{parentId}")
	public String gradeFreeIndex(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {
		String path = GRADEDIR + "nj_free";
		//???????????????????????????
		ResponseEntity responseEntity =	this.getServicesStatus(parentId, userId, schoolId);
		//1??????????????????VIP??????
		if(responseEntity != null) {
			String status = responseEntity.getStatus();
			//String callbackurl = "/gradeVipIndex/"+examWorksId+"/"+schoolId+"/"+userId+"";
			if("1".equals(status)) {  //???????????????????????????????????????
				
				path = GRADEDIR + "nj_free2";
			}else if("0".equals(status)){ //?????????
				
				path = GRADEDIR + "nj_free";
			}else{
				uiModel.addAttribute("message", responseEntity.getMessage());
				path = GRADEDIR + "error";
			}
			
			Team team = this.teamService.findCurrentTeamOfStudent(userId, schoolId);
			if(team != null) {
				ExamWorksGrade examWorksGrade = this.examWorksGradeService.findUnique(examWorksId, team.getGradeId());
				//?????????????????????
			//	Integer examStudentCount = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, null, team.getId()).size();
				uiModel.addAttribute("examStudentCount", examWorksGrade.getStatStudentCount());
				
				//???????????????
				if(examWorksGrade != null && examWorksGrade.getExamStudentCount() != null && examWorksGrade.getStatStudentCount() != null) {
					uiModel.addAttribute("gradeExamStudentCount", examWorksGrade.getStatStudentCount());
					uiModel.addAttribute("noImportStudentCount", examWorksGrade.getExamStudentCount() - examWorksGrade.getStatStudentCount());
				}
				//????????????????????????
				if(examWorksGrade != null && examWorksGrade.getExamTeamCount() != null && examWorksGrade.getFinishedTeamCount() != null) {
					DecimalFormat df = new DecimalFormat("0.00");//???????????????    
					String num = df.format((float)examWorksGrade.getFinishedTeamCount()/examWorksGrade.getExamTeamCount()*100);//????????????String??????    
					num = num+"%";
					uiModel.addAttribute("progress",num);
				}else {
					uiModel.addAttribute("progress", "0%");
				}
				
			}
			//????????????
			List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId,null,1);
			Map<String,Object> userExamWork = new HashMap<String,Object>();
			uiModel.addAttribute("userExamWork", userExamWork);
			float totalFullScore = 0;
			float totalScore =0f;
			List<Map<String,Object>> newUserExamWorks = new ArrayList<Map<String,Object>>();
			if(userExamWorks != null && userExamWorks.size() > 0) {
				userExamWork = userExamWorks.get(0);
				for(int i=0;i<userExamWorks.size();i++) {
					Map<String,Object> map = userExamWorks.get(i);
					if(map.get("full_score") != null) {
						
						//?????????????????????????????????
						totalFullScore += Float.parseFloat(map.get("full_score")+"");
					}
					//?????????????????????????????????
					if(Float.parseFloat(map.get("score")+"") != -1) {
						totalScore += Float.parseFloat(map.get("score")+"");
					}
					Map<String,Object> mp =  userExamWorks.get(i);
					newUserExamWorks.add(mp);
				}
				
				uiModel.addAttribute("totalFullScore", totalFullScore);
				uiModel.addAttribute("totalScore", totalScore);
				uiModel.addAttribute("userExamWork", userExamWork);
				uiModel.addAttribute("userExamWorks", newUserExamWorks);
			}
			
		}else {
			uiModel.addAttribute("message", "??????????????????");
			return GRADEDIR + "error";
		}
		uiModel.addAttribute("parentId",parentId);
		
		return path;
		
	}
	
	
	
	/**
	 * ??????VIP??????
	 * @param uiModel
	 * @param examWorksId ??????ID
	 * @param userId ??????ID
	 * @return
	 */
	@RequestMapping("gradeVipIndex/{examWorksId}/{schoolId}/{userId}/{parentId}*")
	public String gradeVipIndex(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("parentId") Integer parentId) {
		String path = GRADEDIR + "nj_vip";
		//???????????????????????????
		ResponseEntity responseEntity =	this.getServicesStatus(parentId, userId, schoolId);
		if(responseEntity != null) {
			String status = responseEntity.getStatus();
			String callbackurl = baseUrl + "/scoreAnalysis/WeChat/subscribe/"+examWorksId+"/"+schoolId+"/"+userId+"/"+parentId;
			if("1".equals(status)) { //?????????
				return "redirect:"+callbackurl;
			}else if("0".equals(status)){ //???????????????VIP??????
				Team team = this.teamService.findCurrentTeamOfStudent(userId, schoolId);
				Student student = this.studentService.findStudentByUserId(userId);
				if(team != null && student != null) {
					ExamWorksGrade examWorksGrade = this.examWorksGradeService.findUnique(examWorksId, team.getGradeId());
					//?????????????????????
					//Integer examStudentCount = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, null, team.getId()).size();
					//uiModel.addAttribute("examStudentCount", examStudentCount);
					//???????????????
					if(examWorksGrade.getExamStudentCount() != null && examWorksGrade.getStatStudentCount() != null) {
						uiModel.addAttribute("gradeExamStudentCount", examWorksGrade.getStatStudentCount());
						uiModel.addAttribute("noImportStudentCount", examWorksGrade.getExamStudentCount() - examWorksGrade.getStatStudentCount());
					}
					uiModel.addAttribute("examWorksId", examWorksId);
					uiModel.addAttribute("teamId", team.getId());
					uiModel.addAttribute("studentId", student.getId());
				}
				//????????????
				List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId,null,1);
				Map<String,Object> userExamWork = new HashMap<String,Object>();
				uiModel.addAttribute("userExamWork", userExamWork);
				if(userExamWorks != null && userExamWorks.size() > 0) {
					userExamWork = userExamWorks.get(0);
					uiModel.addAttribute("userExamWork", userExamWork);
				}
				
			}
		}else {
			uiModel.addAttribute("message", "??????????????????");
			path = GRADEDIR + "error";
		}
		
		return path;
	}
	

	/**
	 * ????????????????????????
	 * @return
	 */
	@RequestMapping("loadCompositeData/{examWorksId}/{studentId}/{teamId}")
	public String loadCompositeData(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("studentId") Integer studentId,@PathVariable("teamId") Integer teamId) {
		
		try {
			DecimalFormat decimalFormat=new DecimalFormat(".00");
			ExamWorksGrade examWorksGrade = null;
			Map<String,Object> totalComparisonMap = new HashMap<String,Object>();
			Integer gradeStudentCount = 0;
			Integer teamStudentCount = 0;
			ExamWorks examWorks = this.examWorksService.findExamWorksById(examWorksId);
			List<ExamStatMajorStudent> gradeExamStatMajorStudentList = null;
			List<ExamStatMajorStudent> teamExamStatMajorStudentList = null;
			//???????????????
			float gradeAvgScore = 0;
			//??????????????????
			float teamAvgScore = 0;
			float ratio = 0;
			if(examWorks != null) {
				Team team = this.teamService.findTeamById(teamId);
				if(team != null) {
					examWorksGrade = this.examWorksGradeService.findUnique(examWorksId, team.getGradeId());
					//????????????????????????
					//gradeExamStatMajorStudentList =this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, examWorksGrade.getJointExamCode(), null);
					//if(gradeExamStatMajorStudentList != null && gradeExamStatMajorStudentList.size() > 0) {
						uiModel.addAttribute("gradeExamStudentCount", examWorksGrade.getStatStudentCount());
						gradeStudentCount = examWorksGrade.getStatStudentCount();
						uiModel.addAttribute("gradeStudentCount", gradeStudentCount);
						
				//	}
					//??????????????????
					teamExamStatMajorStudentList =this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, null, team.getId(),1);
					if(teamExamStatMajorStudentList != null && teamExamStatMajorStudentList.size() > 0) {
						teamStudentCount = teamExamStatMajorStudentList.size();
						uiModel.addAttribute("teamStudentCount", teamStudentCount);
					}
				}
				
				Map<String,Object> compositeMap = null;
			//	Map<String,Object> allSubjectScoreMap = null;
				ExamStatMajorStudent examStatMajorStudent = this.examStatMajorStudentService.findExamStatMajorStudentByExamWorksIdAndStudentId(examWorksId, studentId);
				String jointExamCode = examStatMajorStudent.getJointExamCode();
				if(examStatMajorStudent != null) {
					Student student = this.studentService.findStudentById(studentId);
					compositeMap = new HashMap<String,Object>();
					if(student != null) {
						compositeMap.put("studentName", student.getName());
						
					}
					Float userScore = examStatMajorStudent.getTotalScore();
					Integer teamRank = examStatMajorStudent.getTeamRank();
					Integer gradeRank = examStatMajorStudent.getGradeRank();
					int hight = (int) (gradeStudentCount*0.2);
					int low = (int) (gradeStudentCount*0.6);
					
					compositeMap.put("totalScore", userScore);
					DecimalFormat df = new DecimalFormat("0.00");//???????????????    
				    String num = df.format((float)teamRank/teamStudentCount);//????????????String??????
				    ratio = Float.parseFloat(num);
					String subsection = "";
						if((gradeRank>0) &&  gradeRank <=hight) { //?????????????????????
							subsection = "?????????";
						}else if((gradeRank > hight ) && (gradeRank <= low)){ //???????????????????????????
							subsection = "?????????";
						}else{ //??????????????????
							subsection = "?????????";
						}
					uiModel.addAttribute("subsection", subsection);	
					uiModel.addAttribute("showRanking", examWorks.getShowRanking());
					compositeMap.put("teamRankChange",examStatMajorStudent.getTeamRankChange());
					compositeMap.put("gradeRankChange",examStatMajorStudent.getGradeRankChange());
					List<Map<String,Object>> userSubjectList = this.scoreAnalysisHandleService.findUserExamTeamSubjectScore(examWorksId,student.getUserId());
					if(examWorks.getShowRanking()) {
						compositeMap.put("teamRank", examStatMajorStudent.getTeamRank());
						compositeMap.put("gradeRank", examStatMajorStudent.getGradeRank());
						
					}else {
						//???????????????
						//???????????????20%???
						//???????????????21-40%???
						//???????????????41%-60%???
						//???????????????61%-80%???
						//???????????????81%-100%???
						//????????????????????????
						compositeMap.put("teamRank", getNameByRatio(ratio));
						if(examStatMajorStudent.getGradeRank() != null && gradeStudentCount != null && gradeStudentCount != 0) {
							ratio = examStatMajorStudent.getGradeRank() / gradeStudentCount;
							compositeMap.put("gradeRank", getNameByRatio(ratio));
						}
						
						//??????????????????
						if(userSubjectList != null && userSubjectList.size() > 0) {
							for(int i=0;i<userSubjectList.size();i++) {
								
								Map<String,Object> userSubject = userSubjectList.get(i);
								//????????????
								if(userSubject.get("team_rank") != null && teamStudentCount != null && teamStudentCount != 0) {
									df = new DecimalFormat("0.00");//???????????????    
								    num = df.format((float)Integer.parseInt(userSubject.get("team_rank")+"")/teamStudentCount);//????????????String??????
								    ratio = Float.parseFloat(num);
								    userSubject.put("team_rank", getNameByRatio(ratio)); 
								}
								
								//????????????
								if(userSubject.get("grade_rank") != null && gradeStudentCount != null && gradeStudentCount != 0) {
									 df = new DecimalFormat("0.00");//???????????????    
								    num = df.format((float)Integer.parseInt(userSubject.get("grade_rank")+"")/gradeStudentCount);//????????????String??????
								    ratio = Float.parseFloat(num);
								    userSubject.put("grade_rank", getNameByRatio(ratio)); 
								}
								
								
							}
							
							
						}
						
						
					}
					float allSubjectTotalScore = 0;
					List<Object> userScoreList = new ArrayList<Object>();
					List<Object> teamAvgScoreList = new ArrayList<Object>();
					List<Object> gradeAvgScoreList = new ArrayList<Object>();
					List<Object> subjectNameList = new ArrayList<Object>();
					List<Object> gradeMaxScoreList = new ArrayList<Object>();
					if(userSubjectList != null && userSubjectList.size() > 0) {
						for(int i=0;i<userSubjectList.size();i++) {
							
							Map<String,Object> userSubject = userSubjectList.get(i);
							
							//????????????
							if(userSubject.get("score") != null) {
								userScoreList.add(userSubject.get("score"));
							}
							
							//???????????????
							if(userSubject.get("average_score") != null) {
								teamAvgScoreList.add(userSubject.get("average_score"));
							}
							//????????????
							if(userSubject.get("subjectName") != null) {
								subjectNameList.add(userSubject.get("subjectName"));
							}
							if(userSubject.get("subjectCode") != null) {
								Float gradeTotalScore = scoreAnalysisHandleService.findGradeTotalScore(jointExamCode, userSubject.get("subjectCode")+"");
								if(gradeTotalScore != null && gradeStudentCount != null && gradeStudentCount != 0) {
									
									df = new DecimalFormat("0.00");//???????????????    
								    num = df.format((float)gradeTotalScore/gradeStudentCount);//????????????String??????
									gradeAvgScoreList.add(num);
								}
								
								Float maxScore = this.examWorksTeamSubjectService.findGradeMaxScore(examWorksGrade.getJointExamCode(),userSubjectList.get(i).get("subjectCode")+"");
								gradeMaxScoreList.add(maxScore);
							}
							if(userSubject.get("full_score") != null) {
								allSubjectTotalScore += Float.parseFloat(userSubject.get("full_score")+"");
								
							}
							
						}
						
					/*	if((gradeRank>0) &&  gradeRank <=hight) { //?????????????????????
							subsection = "?????????";
						}else if((gradeRank > hight ) && (gradeRank <= low)){ //???????????????????????????
							subsection = "?????????";
						}else{ //??????????????????
							subsection = "?????????";
						}*/	
					compositeMap.put("allSubjectTotalScore", allSubjectTotalScore);
					//????????????????????????	
					uiModel.addAttribute("userScoreList", userScoreList);
					uiModel.addAttribute("gradeAvgScoreList", gradeAvgScoreList);
					uiModel.addAttribute("gradeMaxScoreList", gradeMaxScoreList);
					uiModel.addAttribute("teamAvgScoreList", teamAvgScoreList);
					uiModel.addAttribute("subjectNameList", mapper.writeValueAsString(subjectNameList));
					uiModel.addAttribute("subjectSize", subjectNameList.size());
					
					uiModel.addAttribute("userSubjectList", userSubjectList);
					uiModel.addAttribute("compositeMap", compositeMap);
					//?????????????????????
					float gradeTotalScore = 0f;
					List<Float> totalScoreList = new ArrayList<Float>();
					//??????
					gradeExamStatMajorStudentList = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, null,1);
					if(gradeExamStatMajorStudentList != null && gradeExamStatMajorStudentList.size() > 0) {
						List<Object> gradeRankList = new ArrayList<Object>();
						//???????????????
						float maxGradeScore = gradeExamStatMajorStudentList.get(0).getTotalScore();
						for(ExamStatMajorStudent esStudent : gradeExamStatMajorStudentList) {
							
							//??????????????????????????????
							if(esStudent.getStudentId() != null) {
								if(esStudent.getStudentId().intValue() == studentId) {
									String s = "{marker: {radius: 10},y:"+esStudent.getGradeRank()+",color:'#f18441'}";
									gradeRankList.add(s);
								}else {
									gradeRankList.add(esStudent.getGradeRank());
								}
							}
							if(esStudent.getTotalScore() != null) {
								totalScoreList.add(esStudent.getTotalScore());
								gradeTotalScore += esStudent.getTotalScore();
								if(esStudent.getTotalScore() > maxGradeScore) {
									maxGradeScore = esStudent.getTotalScore();
								}
								
							}
						}
						if(gradeStudentCount != null && gradeStudentCount != 0) {
							
							gradeAvgScore = gradeTotalScore / gradeStudentCount;
							float result = this.teamAvgRank(totalScoreList, gradeAvgScore);
							ExamStatMajorStudentCondition ees = new ExamStatMajorStudentCondition();
							ees.setExamWorksId(examWorksId);
							ees.setJointExamCode(examWorksGrade.getJointExamCode());
							ees.setTotalScore(result);
							ees.setIsDeleted(false);
							List<ExamStatMajorStudent> list = this.examStatMajorStudentService.findExamStatMajorStudentByCondition(ees);
							if(list != null && list.size() > 0) {
								ExamStatMajorStudent e = list.get(0);
								uiModel.addAttribute("gradeAvgRank", e.getGradeRank());
							}
						}
						
						totalComparisonMap.put("gradeMaxScore", decimalFormat.format(maxGradeScore));
						uiModel.addAttribute("gradeRankList", gradeRankList);
					}
					
					//???????????????
					
					List<Object> teamTotalScoreList = new ArrayList<Object>();
					float teamTotalScore = 0f;
					if(teamExamStatMajorStudentList != null && teamExamStatMajorStudentList.size() > 0) {
						for(ExamStatMajorStudent teamesList : teamExamStatMajorStudentList) {
							if(teamesList.getTotalScore() != null) {
								teamTotalScore += teamesList.getTotalScore();
								teamTotalScoreList.add(teamesList.getTotalScore());
							}
						}
					}
					
					//??????????????????
					//List<ExamStatMajorStudent> essList = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, null, teamId);
					//???????????????
					if(teamStudentCount != null && teamStudentCount != 0) {
						teamAvgScore = teamTotalScore / teamStudentCount;
						totalComparisonMap.put("teamAvgScore", decimalFormat.format(teamAvgScore));
					}
					//???????????????
					totalComparisonMap.put("gradeAvgScore", decimalFormat.format(gradeAvgScore));
					uiModel.addAttribute("totalComparisonMap", totalComparisonMap);
					uiModel.addAttribute("teamTotalScoreList", teamTotalScoreList);
				}
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return GRADEDIR + "nj_composite_list";
		}
	
	@RequestMapping("loadSingleData/{examWorksId}/{schoolId}/{userId}/{index}")
	public String loadSingleData(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("schoolId") Integer schoolId,@PathVariable("userId") Integer userId,@PathVariable("index") Integer index) {
		List<Map<String,Object>> userSubjectList = this.scoreAnalysisHandleService.findUserExamTeamSubjectScore(examWorksId,userId);
		Student student = this.studentService.findStudentByUserId(userId);
		uiModel.addAttribute("userSubjectList", userSubjectList);
		uiModel.addAttribute("index", index);
		uiModel.addAttribute("studentId", student.getId());
		uiModel.addAttribute("schoolId", schoolId);
		return GRADEDIR + "nj_single_list";
	}
	
	@RequestMapping("loadSingleSubjectData/{examWorksId}/{schoolId}/{userId}/{subjectCode}")
	public String loadSingleSubjectData(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("userId") Integer userId,@PathVariable("schoolId") Integer schoolId,@PathVariable("subjectCode") String subjectCode) {
		Team team = this.teamService.findCurrentTeamOfStudent(userId, schoolId);
		List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId,subjectCode,1);
		uiModel.addAttribute("userExamWork", userExamWorks.get(0));
		ExamWorksGrade examWorksGrade = null;
		Float gradeAvg = null;
		if(team != null) {
			examWorksGrade = this.examWorksGradeService.findUnique(examWorksId, team.getGradeId());
			Float gradeTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(examWorksGrade.getJointExamCode(),subjectCode);
			Integer gradeStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(examWorksGrade.getJointExamCode(),subjectCode);
			if(gradeTotalScore != null && gradeStudentCount != null) {
				
				gradeAvg =gradeTotalScore / gradeStudentCount;
				uiModel.addAttribute("gradeAvg", gradeAvg);
			}
		}
		List<Map<String,Object>> gradeRankList = examWorksTeamSubjectService.findGradeRankBySubjectCode(team.getGradeId(), subjectCode, examWorksId);
		try {
			List<Float> gradeScoreList = new ArrayList<Float>();
			List<Object> gradeRanks = new ArrayList<Object>();
			uiModel.addAttribute("gradeRankList", mapper.writeValueAsString(gradeRankList));
			if(gradeRankList != null && gradeRankList.size() > 0) {
				for(int i=0;i<gradeRankList.size() ;i++) {
					Map<String,Object> map = gradeRankList.get(i);
					if(map.get("user_id") != null) {
						if(Integer.parseInt(map.get("user_id")+"") == userId) {
							String s = "{marker: {radius: 10},y:"+map.get("grade_rank")+",color:'#f18441'}";
							gradeRanks.add(s);
						}else {
							if(map.get("grade_rank") != null) {
								
								gradeRanks.add(Float.parseFloat(map.get("grade_rank")+""));
							}
						}
					}
					if(map.get("score") != null) {
						gradeScoreList.add(Float.parseFloat(map.get("score")+""));
					}
					
				}
			}
			uiModel.addAttribute("gradeRanks", gradeRanks);
			
			
			float result = teamAvgRank(gradeScoreList, gradeAvg);
			List<ExamStudent> list = this.examStudentService.findGradeRankByScoreJointCode(result,examWorksGrade.getJointExamCode(), subjectCode);
			
			if(list != null && list.size() > 0) {
				ExamStudent e = list.get(0);
				uiModel.addAttribute("gradeAvgRank", e.getGradeRank());
			}
			
			Map<String,Object> subjectScoreMap = new HashMap<String,Object>();
			//?????????
			ExamStat examStat = this.examStatService.findExamStatByExamId(Integer.parseInt(userExamWorks.get(0).get("exam_id")+""));
			if(examStat != null) {
				subjectScoreMap.put("fullScore", examStat.getFullScore());
			}
			subjectScoreMap.put("gradeScoreList", mapper.writeValueAsString(gradeScoreList));
			uiModel.addAttribute("subjectScoreMap", subjectScoreMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return GRADEDIR + "nj_single_subject_list";
	}
	
	@RequestMapping("loadAnalysisData/{examWorksId}/{schoolId}/{userId}/{studentId}/{subjectCode}")
	public String loadAnalysisData(Model uiModel,@PathVariable("examWorksId") Integer examWorksId,@PathVariable("studentId") Integer studentId,@PathVariable("userId") Integer userId,@PathVariable("schoolId") Integer schoolId,@PathVariable("subjectCode") String subjectCode) {
		
		Team team = this.teamService.findCurrentTeamOfStudent(userId, schoolId);
		List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId,subjectCode,1);
		
		if(userExamWorks != null && userExamWorks.size() > 0) {
			Map<String,Object> userExamWork = userExamWorks.get(0);
			if(userExamWork.get("exam_id") != null) {
				//?????????????????????
				Map<String,Object> teamBalanceMap = null;
				Map<String,Object> teamScoreMap = null;
				Integer examId = Integer.parseInt(userExamWork.get("exam_id")+"");
				List<ExamStudent> examStudents = examStudentService.findExamStudentsByExamId(examId);
				teamScoreMap = new LinkedHashMap<String,Object>();
				
				if(examStudents != null && examStudents.size() > 0) {
					for(ExamStudent examStudent:examStudents) {
						if(examStudent.getScore() != -1 && examStudent.getTeamRank() != null) {
							if(userId - examStudent.getUserId() == 0) {
								teamBalanceMap = new HashMap<String,Object>();
								teamBalanceMap.put("score", examStudent.getScore());
								teamScoreMap.put("userScore", examStudent.getScore());
								teamScoreMap.put("userTeamRank", examStudent.getTeamRank());
								teamScoreMap.put("name", examStudent.getName());
							}
						}
					}
					
					//????????????
					List<Object> titleList = new ArrayList<Object>();
					List<Float> teamAvgScoreList = new ArrayList<Float>();
					List<Object> gradeAvgScoreList = new ArrayList<Object>();
					List<Object> userScoreList = new ArrayList<Object>();
					List<Integer> userTeamRankList = new ArrayList<Integer>();
					List<Integer> userGradeRankList = new ArrayList<Integer>();
					Integer teamId = Integer.parseInt(userExamWorks.get(0).get("team_id")+"");
					
					List<Map<String,Object>> scoreTrendList = scoreAnalysisHandleService.findUserExamWorksBySubjectCodeAndUserId(1,subjectCode,teamId,userId, 1);
					
					if(scoreTrendList != null && scoreTrendList.size() > 0 ) {
						for(int i=scoreTrendList.size()-1; i>=0 ;i--) {
							Map<String,Object> scoreTrend = scoreTrendList.get(i);
							
							titleList.add(scoreTrend.get("name"));
							if(scoreTrend.get("average_score") != null) {
								teamAvgScoreList.add(Float.parseFloat(scoreTrend.get("average_score")+""));
							}
							if(scoreTrend.get("score") != null) {
								userScoreList.add(Float.parseFloat(scoreTrend.get("score")+""));
							}
							
							if(scoreTrend.get("team_rank") != null) {
								userTeamRankList.add(Integer.parseInt(scoreTrend.get("team_rank")+""));
							}
							if(scoreTrend.get("grade_rank") != null) {
								userGradeRankList.add(Integer.parseInt(scoreTrend.get("grade_rank")+""));
							}
							ExamWorksGrade examWorksGrade = this.examWorksGradeService.findUnique(Integer.parseInt(scoreTrend.get("exam_works_id")+""),team.getGradeId() );
							
							Float gradeTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(examWorksGrade.getJointExamCode(),subjectCode);
							
							Integer gradeStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(examWorksGrade.getJointExamCode(),subjectCode);
							if(gradeTotalScore != null && gradeStudentCount != null && gradeStudentCount != 0) {
							
								DecimalFormat df = new DecimalFormat("0.00");//???????????????    
							    String num = df.format((float)gradeTotalScore/gradeStudentCount);//????????????String??????
								gradeAvgScoreList.add(num);
							}
							
						}
					}
					//String hightStr = "{dataLabels: {style: {fontWeight: 'bold',color:'#ff5252',fontSize:'18'}},y:150.00,color:'#ff5252'}";
					try {
						uiModel.addAttribute("teamAvgScoreList", teamAvgScoreList);
						uiModel.addAttribute("gradeAvgScoreList", gradeAvgScoreList);
						uiModel.addAttribute("titleList", mapper.writeValueAsString(titleList));
						uiModel.addAttribute("userScoreList", mapper.writeValueAsString(userScoreList));
						uiModel.addAttribute("userTeamRankList", mapper.writeValueAsString(userTeamRankList));
						uiModel.addAttribute("userGradeRankList", mapper.writeValueAsString(userGradeRankList));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			uiModel.addAttribute("userExamWork", userExamWork);
		}
		
		return GRADEDIR + "nj_analysis_list";
	}
	
	
	/**
	 * ????????????????????????????????????????????????????????????????????????????????????????????????BUG???
	 * @param scoreList ????????????????????????
	 * @param nearNum ????????????????????????
	 * @return ??????????????????????????????????????????
	 * @author pantq
	 * @mail:pantq@gmail.com
	 * ???????????????2018-02-23 10:59:58 
	 */
	private float teamAvgRank(List<Float> scoreList,Float nearNum) {
		float result = 0;
		if(scoreList != null && scoreList.size() > 0) {
			// ???????????????
			// ???????????????
			float diffNum = Math.abs(scoreList.get(0) - nearNum);
			// ????????????
			result = scoreList.get(0);
			for (Float integer : scoreList)
			{
				float diffNumTemp = Math.abs(integer - nearNum);
				if (diffNumTemp < diffNum)
				{
					diffNum = diffNumTemp;
					result = integer;
				}
			}
		}
		return result;
	}
	
	
	
	
	/**
	 * ??????????????????????????????
	 * @param ratio
	 * @return
	 */
	private String getNameByRatio(Float ratio) {
		String result = "";
		//???????????????
		//???????????????20%???
		//???????????????21-40%???
		//???????????????41%-60%???
		//???????????????61%-80%???
		//???????????????81%-100%???
		if(ratio == 0.20) {
			result = "????????????";
		}else if((ratio<=0.21) && (ratio >0.40)) {
			result = "????????????";
		}else if((ratio<=0.41) && (ratio >0.60)){
			result = "????????????";
		}else if((ratio<=0.61) && (ratio >0.80)){
			result = "????????????";
		}else {
			result = "????????????";
		}
		
		return result;
		
	}
	
	private ResponseEntity getServicesStatus(Integer parentId,Integer studentId,Integer schoolId) {
		
		//??????????????????URL
		String url = SysContants.SCORE_ANALYSIS_10NIU_PAY_URL_PATH;
		ResponseEntity responseEntity = null;
		String result = MqtHttpRequest.sendGet(url, "service_id=2&parent_id="+parentId +"&open_student_id="+studentId+"&open_school_id="+schoolId);
		try {
			if(result != null &&!"".equals(result)) {
				
				responseEntity = mapper.readValue(result, ResponseEntity.class);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
}
