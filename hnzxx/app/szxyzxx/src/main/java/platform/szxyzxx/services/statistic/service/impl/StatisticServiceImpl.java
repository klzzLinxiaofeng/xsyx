//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import platform.education.exam.model.Exam;
//import platform.education.exam.service.ExamService;
//import platform.education.generalTeachingAffair.model.ExamQuestion;
//import platform.education.generalTeachingAffair.model.ExamStat;
//import platform.education.generalTeachingAffair.model.ExamStudent;
//import platform.education.generalTeachingAffair.model.PjExam;
//import platform.education.generalTeachingAffair.model.SchoolTerm;
//import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
//import platform.education.generalTeachingAffair.model.Team;
//import platform.education.generalTeachingAffair.service.ExamQuestionService;
//import platform.education.generalTeachingAffair.service.ExamStatService;
//import platform.education.generalTeachingAffair.service.ExamStudentService;
//import platform.education.generalTeachingAffair.service.PjExamService;
//import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
//import platform.education.generalTeachingAffair.service.SchoolTermService;
//import platform.education.generalTeachingAffair.service.TeamService;
//import platform.education.generalTeachingAffair.vo.ExamQuestionCondition;
//import platform.education.generalTeachingAffair.vo.ExamResult;
//import platform.education.generalTeachingAffair.vo.PjExamCondition;
//import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
//import platform.education.learningDesign.model.LpTaskExamUnit;
//import platform.education.learningDesign.service.LpTaskExamUnitService;
//import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
//import platform.education.paper.constants.PaperType;
//import platform.education.paper.model.Paper;
//import platform.education.paper.model.UserPaper;
//import platform.education.paper.model.UserQuestionResult;
//import platform.education.paper.service.PaperService;
//import platform.education.paper.service.UserPaperService;
//import platform.education.paper.service.UserQuestionService;
//import platform.education.paper.vo.UserPaperCondition;
//import platform.szxyzxx.services.statistic.service.StatisticService;
//
///**
// * ???????????????????????????????????????
// * 
// * @author Administrator
// *
// */
//
//public class StatisticServiceImpl implements StatisticService {
//
//	private PjExamService pjExamService;
//
//	private UserQuestionService userQuestionService;
//
//	private UserPaperService userPaperService;
//
//	private ExamStatService examStatService;
//
//	private PaperService papaperService;
//
//	private ExamStudentService examStudentService;
//
//	private ExamQuestionService examQuestionService;
//	
//	private SchoolTermCurrentService schoolTermCurrentService; 
//	
//	private TeamService teamService;
//	
//	private SchoolTermService  schoolTermService;
//	
//	private ExamService examService;
//	
//	
//	private List<Integer> examIds ;
//	
//	private List<Integer> teamIds ;
//	
//	private List<Map<String,Integer>> examIdList;
//	
//	private LpTaskExamUnitService lpTaskExamUnitService;
//	
//
//	public void setLpTaskExamUnitService(LpTaskExamUnitService lpTaskExamUnitService) {
//		this.lpTaskExamUnitService = lpTaskExamUnitService;
//	}
//
//	public void setPjExamService(PjExamService pjExamService) {
//		this.pjExamService = pjExamService;
//	}
//
//	public void setUserQuestionService(UserQuestionService userQuestionService) {
//		this.userQuestionService = userQuestionService;
//	}
//
//	public void setUserPaperService(UserPaperService userPaperService) {
//		this.userPaperService = userPaperService;
//	}
//
//	public SchoolTermCurrentService getSchoolTermCurrentService() {
//		return schoolTermCurrentService;
//	}
//
//
//	public TeamService getTeamService() {
//		return teamService;
//	}
//
//	
//	public SchoolTermService getSchoolTermService() {
//		return schoolTermService;
//	}
//
//
//	public ExamService getExamService() {
//		return examService;
//	}
//
//	
//	public PjExamService getPjExamService() {
//		return pjExamService;
//	}
//
//	public void setExamStatService(ExamStatService examStatService) {
//		this.examStatService = examStatService;
//	}
//
//	public void setPapaperService(PaperService papaperService) {
//		this.papaperService = papaperService;
//	}
//
//	public void setExamStudentService(ExamStudentService examStudentService) {
//		this.examStudentService = examStudentService;
//	}
//
//	public void setExamQuestionService(ExamQuestionService examQuestionService) {
//		this.examQuestionService = examQuestionService;
//	}
//
//	@Override
//	public Boolean statisticHandle(Integer examId, final Integer paperId, final Integer ownerId,Integer type) {
//		
//		Boolean flag = false;
//		try {
//			if(type != null && PaperType.LEARNING_PLAN == type) {
//				learningDesign(ownerId);
//			}else{
//				examStatisic(examId,ownerId,paperId);
//			}
//			flag = true;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return flag;
//	
//		}
//	
//	
//	
//	/**
//	 * ????????????
//	 */
//	private void examStatisic(Integer examId,Integer ownerId,Integer paperId) {
//		
//		long startTime = System.currentTimeMillis();
//		Boolean flag = false;
//		// ?????????????????????
//		try {
//			// 1. ??????examId???pj_exam???????????????
//			PjExam newExam = this.pjExamService.findPjExamById(examId);
//			if(newExam != null){
//				
//				// 2. ??????joint_exam_code???????????????????????????????????????????????????
//				PjExamCondition newpjExamCondition = new PjExamCondition();
//				newpjExamCondition.setJointExamCode(newExam.getJointExamCode());
//				final List<PjExam> pjExamList = this.pjExamService.findPjExamByCondition(newpjExamCondition);
//				
//				// ????????????????????????????????????
//				final List<UserQuestionResult> userQuestionResultList = this.userQuestionService
//						.findUserQuestionByOwnerId(ownerId,null,PaperType.EXAM);
//				// ???????????????????????????
//				final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerId(ownerId,null,PaperType.EXAM);
//				//?????????????????????
//				if(pjExamList != null && pjExamList.size() > 0 && userPapers != null && userPapers.size() > 0){
//					examIds = new ArrayList<Integer>();
//					examIdList = new ArrayList<Map<String,Integer>>();
//					teamIds = new ArrayList<Integer>();
//					Map<String,Integer> examIdMap = null;
//					for(PjExam pjExam :pjExamList){
//						for(UserPaper userPaper:userPapers){
//							if(pjExam.getTeamId().intValue() == userPaper.getTeamId()){
//								examIdMap = new HashMap<String,Integer>();
//								Integer eId = pjExam.getId() ;
//								Integer teamId = pjExam.getTeamId();
//								examIds.add(eId);
//								teamIds.add(teamId);
//								examIdMap.put("examId", eId);
//								examIdList.add(examIdMap);
//							}
//						}
//					}
//				}
//				
//				examStudentHandle(paperId, ownerId,userPapers,PaperType.EXAM);
//				//?????? examStat?????????
//				examStatHandle(pjExamList,userPapers,ownerId,null,paperId,PaperType.EXAM);
//				// 3. examQuestion???????????????
//				examQuestionHandle(pjExamList, userQuestionResultList, userPapers, ownerId,null,PaperType.EXAM);
//				
//				//JDBCUtil.closeConnection();
//				
//				//?????????????????????
//				if(pjExamList != null && pjExamList.size() > 0){
//					
//					ExamQuestionCondition examQuestionCondition =  new ExamQuestionCondition();
//					examQuestionCondition.setExamId(examId);
//					List<ExamQuestion> examQuestionList =  examQuestionService.findExamQuestionByCondition(examQuestionCondition);
//					if(examQuestionList != null && examQuestionList.size() > 0){
//						for(ExamQuestion examQuestion :examQuestionList){
//							Integer answerCount = examQuestion.getAnswerCount();
//							if(answerCount != null && answerCount != 0){
//								Float fullScore= examQuestion.getFullScore() == null?0f:examQuestion.getFullScore();
//								Float teamScoringRate = 0f;
//								if(fullScore != 0){
//									teamScoringRate = (float)(examQuestion.getScore() / (answerCount * examQuestion.getFullScore()));
//								}
//								examQuestion.setTeamScoringRate(teamScoringRate);
//								//	examQuestion.setGradeScoringRate(getGradeScoringRate(examQuestion.getExamId()));
//								examQuestionService.modify(examQuestion);
//							}
//						}
//					}
//					
//					}
//					
//				//?????????????????????
//				examQuestionService.batchUpdateExamQuestionGradeScoringRate(examId);
//				
//
//				flag = true;
//				long endTime = System.currentTimeMillis();
//				System.out.println("????????????????????????????????? " + ((endTime - startTime)) + "??????");
//				
//			}
//			}catch (Exception e) {
//			}
//	}
//	
//	/**
//	 * ???????????????
//	 * ownerId ??????????????????ID
//	 */
//	private void learningDesign(Integer ownerId){
//		
//		long startTime = System.currentTimeMillis();
//		//???????????????taskID ???lp_task_exam_unit???????????????????????????CODE?????????
//		LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
//		lpTaskExamUnitCondition.setDeleted(false);
//		lpTaskExamUnitCondition.setTaskId(ownerId);
//		List<LpTaskExamUnit> lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
//		ExamQuestionCondition examQuestionCondition =  new ExamQuestionCondition();
//		List<ExamQuestion> examQuestionList = null;
//		if(lpTaskExamUnitList != null && lpTaskExamUnitList.size() > 0) {
//			
//			for(LpTaskExamUnit lteu :lpTaskExamUnitList) {
//				
//				// 2. ??????joint_exam_code???????????????????????????????????????????????????
//				final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerId(ownerId,lteu.getUnitId(),PaperType.LEARNING_PLAN);
//				if(userPapers != null && userPapers.size() > 0) {
//					
//					PjExam pje = this.pjExamService.findPjExamById(lteu.getId());
//					Paper paper = papaperService.findPaperByUuid(pje.getPaperUuid());
//					
//					// ????????????????????????????????????
//					final List<UserQuestionResult> userQuestionResultList = this.userQuestionService
//							.findUserQuestionByOwnerId(lteu.getTaskId(),lteu.getUnitId(),PaperType.LEARNING_PLAN);
//					// ???????????????????????????
//					
//					PjExamCondition pjExamCondition = new PjExamCondition();
//					pjExamCondition.setJointExamCode(lteu.getJoinExamCode());
//					List<PjExam> pjExamList = pjExamService.findPjExamByCondition(pjExamCondition);
//					
//					//?????????????????????
//					if(pjExamList != null && pjExamList.size() > 0 && userPapers != null && userPapers.size() > 0){
//						examIds = new ArrayList<Integer>();
//						examIdList = new ArrayList<Map<String,Integer>>();
//						teamIds = new ArrayList<Integer>();
//						Map<String,Integer> examIdMap = null;
//						for(PjExam pjExam :pjExamList){
//							for(UserPaper userPaper:userPapers){
//								if(pjExam.getTeamId().intValue() == userPaper.getTeamId()){
//									examIdMap = new HashMap<String,Integer>();
//									Integer eId = pjExam.getId() ;
//									Integer teamId = pjExam.getTeamId();
//									examIds.add(eId);
//									teamIds.add(teamId);
//									examIdMap.put("examId", eId);
//									examIdList.add(examIdMap);
//									examQuestionCondition.setExamId(eId);
//									examQuestionList = examQuestionService.findExamQuestionByCondition(examQuestionCondition);
//									if(examQuestionList != null && examQuestionList.size() > 0){
//										for(ExamQuestion examQuestion :examQuestionList){
//											Integer answerCount = examQuestion.getAnswerCount();
//											if(answerCount != null && answerCount != 0){
//												Float fullScore= examQuestion.getFullScore() == null?0f:examQuestion.getFullScore();
//												Float teamScoringRate = 0f;
//												if(fullScore != 0){
//													teamScoringRate = (float)(examQuestion.getScore() / (answerCount * examQuestion.getFullScore()));
//												}
//												examQuestion.setTeamScoringRate(teamScoringRate);
//												
//												//	examQuestion.setGradeScoringRate(getGradeScoringRate(examQuestion.getExamId()));
//												//examQuestionService.modify(examQuestion);
//											}
//										}
//									}
//									
//								}
//							}
//						}
//					}
//					
//					//PjExam pjExam = pjExamService.findPjExamById(lteu.getExamId());
//					//Paper pa
//					//?????? examStat?????????
//					Integer unitId = lteu.getUnitId() ;
//					examStudentHandle(paper.getId(), ownerId,userPapers,PaperType.LEARNING_PLAN);
//					examStatHandle(pjExamList,userPapers,ownerId,unitId,paper.getId(),PaperType.LEARNING_PLAN);
//					// 3. examQuestion???????????????
//					examQuestionHandle(pjExamList, userQuestionResultList, userPapers, ownerId,unitId,PaperType.LEARNING_PLAN);
//					//JDBCUtil.closeConnection();
//					
//					//?????????????????????
//					
//					examQuestionService.batchUpateExamQuestionTeamScoringRate(examQuestionList);
//					
//					//?????????????????????
//					if(examIds != null && examIds.size() >0) {
//						
//						examQuestionService.batchUpdateExamQuestionGradeScoringRate(examIds.get(0));
//					}
//					
//					long endTime = System.currentTimeMillis();
//					System.out.println("????????????????????????????????? " + ((endTime - startTime)) + "??????");
//				}
//				}
//		}
//		
//	}
//	
//	
//	
//	//?????? examStudent???????????????
//		private void examStudentHandle(Integer paperId, Integer ownerId,List<UserPaper> userPapers,Integer type){
//			
//			if(userPapers != null && userPapers.size() > 0) {
//				long startTime = System.currentTimeMillis();
//				Paper paper = papaperService.findPaperById(paperId);
//				if(paper != null){
//					Object[] examIdObj = null;
//					String paperUuid = paper.getPaperUuid();
//					if(examIds != null && examIds.size() > 0) {
//						
//						examIdObj = examIds.toArray();
//					}
//					//???????????????
//					examStudentService.batchUpdateExamStudentScore(examIdObj,paperUuid, ownerId, type);
//					
//					//??????????????????
//					examStudentService.batchUpdateExamStudentTeamRank(examIdObj);
//					
//					//?????????????????????
//					examStudentService.batchUpdateExamStudentCorrectAnswerCountNew(examIdObj,paperUuid, ownerId, type, 1);
//					
//					//??????????????????
//					examStudentService.batchUpdateExamStudentAnswerCountNew(examIdObj,paperUuid, ownerId, type);
//					
//					//???????????? ????????????
//					examStudentService.batchUpdateExamStudentGradeRank(examIdObj);
//					
//					long endTime = System.currentTimeMillis();
//					System.out.println("examStudentHandle????????????????????? " + ((endTime - startTime)) + "??????");
//				}
//			}
//		}
//	
//		
//		
//		
//		// examQuestion???????????????
//		private void examQuestionHandle(List<PjExam> pjExamList, List<UserQuestionResult> userQuestionResultList,
//			List<UserPaper> userPapers, Integer ownerId,Integer unitId,Integer type) {
//			if(userPapers != null && userPapers.size() > 0) {
//				
//				long startTime = System.currentTimeMillis();
//				/*List<Map<String,Object>> examQuestionEmptyAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionCorrectAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionScoreList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionAverageScoreList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestiontotalTimeList = new ArrayList<Map<String,Object>>();
//				
//				List<List<ExamQuestion>> teamScoringRateList = new ArrayList<List<ExamQuestion>>();*/
//				
//				//???????????? ???????????????
//				if(userPapers != null && userPapers.size() >0){
//					for (PjExam pjExam : pjExamList) {
//						for(UserPaper userPaper : userPapers){
//							int teamId = pjExam.getTeamId();
//							if(teamId == userPaper.getTeamId()){
//								int examId = pjExam.getId();
//								
//								// ?????????????????????
//								/*StringBuffer stringBufferExamQuestionAnswerCount = new StringBuffer();
//						stringBufferExamQuestionAnswerCount.append("UPDATE pj_exam_question es,(SELECT count(*) answerCount,up.question_uuid");
//						stringBufferExamQuestionAnswerCount.append(" FROM pa_user_question up");
//						stringBufferExamQuestionAnswerCount.append(" WHERE 1=1");
//						stringBufferExamQuestionAnswerCount.append(" AND up.team_id ="+teamId);
//						stringBufferExamQuestionAnswerCount.append(" AND up.owner_id ="+ownerId);
//						stringBufferExamQuestionAnswerCount.append(" AND up.type =2");
//						//stringBufferExamQuestionAnswerCount.append(" AND up.is_correct =[]");
//						stringBufferExamQuestionAnswerCount.append(" AND up.answer ='[]'");
//						stringBufferExamQuestionAnswerCount.append(" GROUP BY up.question_uuid) a");
//						stringBufferExamQuestionAnswerCount.append(" SET es.answer_count = a.answerCount");
//						stringBufferExamQuestionAnswerCount.append(" WHERE 1=1");
//						stringBufferExamQuestionAnswerCount.append(" AND es.exam_id = " + examId);
//						stringBufferExamQuestionAnswerCount.append(" and es.question_uuid = a.question_uuid");
//						//JDBCUtil.update(stringBufferExamQuestionAnswerCount.toString());
//						
//						sqls.add(stringBufferExamQuestionAnswerCount.toString());*/
//								
//								/*Map<String,Object> examQuestionEmptyAnswerCount = new HashMap<String,Object>();
//						examQuestionEmptyAnswerCount.put("teamId", teamId);
//						examQuestionEmptyAnswerCount.put("ownerId", ownerId);
//						examQuestionEmptyAnswerCount.put("paperType", PaperType.EXAM);
//						examQuestionEmptyAnswerCount.put("answer", "[]");
//						examQuestionEmptyAnswerCount.put("examId", examId);
//						examQuestionEmptyAnswerCountList.add(examQuestionEmptyAnswerCount);*/
//								
//								/*List<Map<String,Object>> emptyAnswerCountList = this.userQuestionService.findUserQuestionAnswerCount(teamId, ownerId, PaperType.EXAM, null, "[]");
//						if(emptyAnswerCountList != null && emptyAnswerCountList.size() > 0){
//							
//							for(int i=0,len=emptyAnswerCountList.size();i<len;i++){
//								Map<String,Object> mp = emptyAnswerCountList.get(i);
//								if(mp != null && mp.size() > 0){
//									
//									Map<String,Object> map = new HashMap<String,Object>();
//									map.put("examId", examId);
//									map.put("answerCount", mp.get("answerCount"));
//									map.put("questionUuid", mp.get("questionUuid"));
//									examQuestionEmptyAnswerCountList.add(map);
//								}
//								
//							}
//						}*/
//								
//								examQuestionService.examQuestionAnswerCount(teamId, ownerId, type, null, "[]", examId,unitId);
//								// ???????????????
//								/*StringBuffer stringBufferExamQuestionCorrectAnswerCount = new StringBuffer();
//						stringBufferExamQuestionCorrectAnswerCount.append("UPDATE pj_exam_question es,(SELECT count(*) answerCount,up.question_uuid");
//						stringBufferExamQuestionCorrectAnswerCount.append(" FROM pa_user_question up");
//						stringBufferExamQuestionCorrectAnswerCount.append(" WHERE 1=1");
//						stringBufferExamQuestionCorrectAnswerCount.append(" AND up.team_id ="+teamId);
//						stringBufferExamQuestionCorrectAnswerCount.append(" AND up.owner_id ="+ownerId);
//						stringBufferExamQuestionCorrectAnswerCount.append(" AND up.type =2");
//						stringBufferExamQuestionCorrectAnswerCount.append(" AND up.is_correct =1");
//						//stringBufferExamQuestionCorrectAnswerCount.append(" AND up.answer =[]");
//						stringBufferExamQuestionCorrectAnswerCount.append(" GROUP BY up.question_uuid) a");
//						stringBufferExamQuestionCorrectAnswerCount.append(" SET es.answer_count = a.answerCount");
//						stringBufferExamQuestionCorrectAnswerCount.append(" WHERE 1=1");
//						stringBufferExamQuestionCorrectAnswerCount.append(" AND es.exam_id = " + examId);
//						stringBufferExamQuestionCorrectAnswerCount.append(" and es.question_uuid = a.question_uuid");
//						//JDBCUtil.update(stringBufferExamQuestionCorrectAnswerCount.toString());
//						
//						sqls.add(stringBufferExamQuestionCorrectAnswerCount.toString());*/
//								/*Map<String,Object> examQuestionCorrectAnswerCount = new HashMap<String,Object>();
//						examQuestionCorrectAnswerCount.put("teamId", teamId);
//						examQuestionCorrectAnswerCount.put("ownerId", ownerId);
//						examQuestionCorrectAnswerCount.put("paperType", PaperType.EXAM);
//						examQuestionCorrectAnswerCount.put("isCorrect", 1);
//						//examQuestionCorrectAnswerCount.put("answer", "[]");
//						examQuestionCorrectAnswerCount.put("examId", examId);*/
//								
//								
//								/*List<Map<String,Object>> correctAnswerCountList = this.userQuestionService.findUserQuestionAnswerCount(teamId, ownerId, PaperType.EXAM, true, null);
//						if(correctAnswerCountList != null && correctAnswerCountList.size() > 0){
//							
//							for(int i=0,len=correctAnswerCountList.size();i<len;i++){
//								Map<String,Object> mp = correctAnswerCountList.get(i);
//								if(mp != null && mp.size() > 0){
//									
//									Map<String,Object> map = new HashMap<String,Object>();
//									map.put("examId", examId);
//									map.put("answerCount", mp.get("answerCount"));
//									map.put("questionUuid", mp.get("questionUuid"));
//									examQuestionCorrectAnswerCountList.add(map);
//								}
//								
//							}
//						}*/
//								
//								
//								
//								
//								examQuestionService.examQuestionAnswerCount(teamId, ownerId, type, true, null, examId,unitId);
//								// ???????????????
//								/*StringBuffer stringBufferExamQuestionAnswerCount1 = new StringBuffer();
//						stringBufferExamQuestionAnswerCount1.append("UPDATE pj_exam_question es,(SELECT count(*) answerCount,up.question_uuid");
//						stringBufferExamQuestionAnswerCount1.append(" FROM pa_user_question up");
//						stringBufferExamQuestionAnswerCount1.append(" WHERE 1=1");
//						stringBufferExamQuestionAnswerCount1.append(" AND up.team_id ="+teamId);
//						stringBufferExamQuestionAnswerCount1.append(" AND up.owner_id ="+ownerId);
//						stringBufferExamQuestionAnswerCount1.append(" AND up.type =2");
//						//stringBufferExamQuestionAnswerCount1.append(" AND up.is_correct =1");
//						//stringBufferExamQuestionCorrectAnswerCount.append(" AND up.answer =[]");
//						stringBufferExamQuestionAnswerCount1.append(" GROUP BY up.question_uuid) a");
//						stringBufferExamQuestionAnswerCount1.append(" SET es.answer_count = a.answerCount");
//						stringBufferExamQuestionAnswerCount1.append(" WHERE 1=1");
//						stringBufferExamQuestionAnswerCount1.append(" AND es.exam_id = " + examId);
//						stringBufferExamQuestionAnswerCount1.append(" and es.question_uuid = a.question_uuid");
//						//JDBCUtil.update(stringBufferExamQuestionAnswerCount1.toString());
//						sqls.add(stringBufferExamQuestionAnswerCount1.toString());*/
//								
//								/*Map<String,Object> examQuestionAnswerCount = new HashMap<String,Object>();
//						examQuestionAnswerCount.put("teamId", teamId);
//						examQuestionAnswerCount.put("ownerId", ownerId);
//						examQuestionAnswerCount.put("paperType", PaperType.EXAM);
//						//examQuestionCorrectAnswerCount.put("answer", "[]");
//						examQuestionAnswerCount.put("examId", examId);
//						examQuestionAnswerCountList.add(examQuestionAnswerCount);*/
//								
//								/*List<Map<String,Object>> answerCountList = this.userQuestionService.findUserQuestionAnswerCount(teamId, ownerId, PaperType.EXAM, null, null);
//						if(answerCountList != null && answerCountList.size() > 0){
//							
//							for(int i=0,len=answerCountList.size();i<len;i++){
//								Map<String,Object> mp = answerCountList.get(i);
//								if(mp != null && mp.size() > 0){
//									
//									Map<String,Object> map = new HashMap<String,Object>();
//									map.put("examId", examId);
//									map.put("answerCount", mp.get("answerCount"));
//									map.put("questionUuid", mp.get("questionUuid"));
//									examQuestionAnswerCountList.add(map);
//								}
//								
//							}
//						}*/
//								
//								
//								
//								examQuestionService.examQuestionAnswerCount(teamId, ownerId, type, null, null, examId,unitId);
//								//??????????????????
//								
//								/*StringBuffer stringBufferExamQuestionScore = new StringBuffer();
//						stringBufferExamQuestionScore.append("UPDATE pj_exam_question es,(SELECT SUM(up.score) totalScore,up.question_uuid");
//						stringBufferExamQuestionScore.append(" FROM pa_user_question up");
//						stringBufferExamQuestionScore.append(" WHERE 1=1");
//						stringBufferExamQuestionScore.append(" AND up.team_id ="+teamId);
//						stringBufferExamQuestionScore.append(" AND up.owner_id ="+ownerId);
//						stringBufferExamQuestionScore.append(" AND up.type =2");
//						//stringBufferExamQuestionAnswerCount1.append(" AND up.is_correct =1");
//						//stringBufferExamQuestionCorrectAnswerCount.append(" AND up.answer =[]");
//						stringBufferExamQuestionScore.append(" GROUP BY up.question_uuid) a");
//						stringBufferExamQuestionScore.append(" SET es.score = a.totalScore");
//						stringBufferExamQuestionScore.append(" WHERE 1=1");
//						stringBufferExamQuestionScore.append(" AND es.exam_id = " + examId);
//						stringBufferExamQuestionScore.append(" and es.question_uuid = a.question_uuid");
//						//JDBCUtil.update(stringBufferExamQuestionScore.toString());
//						
//						sqls.add(stringBufferExamQuestionScore.toString());*/
//								
//								
//								/*Map<String,Object> examQuestionScore = new HashMap<String,Object>();
//						examQuestionScore.put("teamId", teamId);
//						examQuestionScore.put("ownerId", ownerId);
//						examQuestionScore.put("paperType", PaperType.EXAM);
//						examQuestionScore.put("examId", examId);
//						examQuestionScoreList.add(examQuestionScore);*/
//								
//								/*List<Map<String,Object>> scoreCount = this.userQuestionService.findUserQuestionScoreCount(teamId, ownerId, PaperType.EXAM);
//						
//						if(scoreCount != null && scoreCount.size() > 0){
//							
//							for(int i=0,len=scoreCount.size();i<len;i++){
//								Map<String,Object> mp = scoreCount.get(i);
//								if(mp != null && mp.size() > 0){
//									
//									Map<String,Object> map = new HashMap<String,Object>();
//									map.put("examId", examId);
//									map.put("totalScore", mp.get("totalScore"));
//									map.put("questionUuid", mp.get("questionUuid"));
//									examQuestionScoreList.add(map);
//								}
//								
//							}
//						}*/
//								examQuestionService.examQuestionScore(teamId, ownerId, type, examId,unitId);
//								//???????????????
//								/*StringBuffer stringBufferExamQuestionAverageScore = new StringBuffer();
//						stringBufferExamQuestionAverageScore.append("UPDATE pj_exam_question es");
//						stringBufferExamQuestionAverageScore.append(" SET es.average_score = (es.score / es.answer_count)");
//						stringBufferExamQuestionAverageScore.append(" WHERE 1=1");
//						stringBufferExamQuestionAverageScore.append(" AND es.exam_id = " + examId);
//						//JDBCUtil.update(stringBufferExamQuestionAverageScore.toString());
//						sqls.add(stringBufferExamQuestionAverageScore.toString());*/
//								
//								/*Map<String,Object> examQuestionAverageScore = new HashMap<String,Object>();
//						examQuestionAverageScore.put("examId", examId);
//						examQuestionAverageScoreList.add(examQuestionAverageScore);*/
//								
//								examQuestionService.examQuestionAverageScore(examId);
//								//?????????????????????????????????
//								/*StringBuffer stringBufferExamQuestiontotalTime = new StringBuffer();
//						stringBufferExamQuestiontotalTime.append("UPDATE pj_exam_question es,(SELECT SUM(up.answer_time) totalTime,up.question_uuid");
//						stringBufferExamQuestiontotalTime.append(" FROM pa_user_question up");
//						stringBufferExamQuestiontotalTime.append(" WHERE 1=1");
//						stringBufferExamQuestiontotalTime.append(" AND up.team_id ="+teamId);
//						stringBufferExamQuestiontotalTime.append(" AND up.owner_id ="+ownerId);
//						stringBufferExamQuestiontotalTime.append(" AND up.type =2");
//						//stringBufferExamQuestionAnswerCount1.append(" AND up.is_correct =1");
//						//stringBufferExamQuestionCorrectAnswerCount.append(" AND up.answer =[]");
//						stringBufferExamQuestiontotalTime.append(" GROUP BY up.question_uuid) a");
//						stringBufferExamQuestiontotalTime.append(" SET es.total_time = a.totalTime");
//						stringBufferExamQuestiontotalTime.append(" WHERE 1=1");
//						stringBufferExamQuestiontotalTime.append(" AND es.exam_id = " + examId);
//						stringBufferExamQuestiontotalTime.append(" and es.question_uuid = a.question_uuid");
//					//	JDBCUtil.update(stringBufferExamQuestiontotalTime.toString());
//						
//						sqls.add(stringBufferExamQuestiontotalTime.toString());*/
//								
//								/*Map<String,Object> examQuestiontotalTime = new HashMap<String,Object>();
//						examQuestiontotalTime.put("teamId", teamId);
//						examQuestiontotalTime.put("ownerId", ownerId);
//						examQuestiontotalTime.put("paperType", PaperType.EXAM);
//						examQuestiontotalTime.put("examId", examId);
//						examQuestiontotalTimeList.add(examQuestiontotalTime);*/
//								
//								/*List<Map<String,Object>> answerTime = this.userQuestionService.findUserQuestionAnswerTime(teamId, ownerId, PaperType.EXAM);
//						if(answerTime != null && answerTime.size() > 0){
//							
//							for(int i=0,len=answerTime.size();i<len;i++){
//								Map<String,Object> mp = answerTime.get(i);
//								if(mp != null && mp.size() > 0){
//									Map<String,Object> map = new HashMap<String,Object>();
//									map.put("totalTime", mp.get("totalTime"));
//									map.put("examId", examId);
//									map.put("questionUuid", mp.get("questionUuid"));
//									examQuestiontotalTimeList.add(map);
//								}
//								
//							}
//						}*/
//								
//								//?????????????????????
//								/*ExamQuestionCondition examQuestionCondition =  new ExamQuestionCondition();
//						examQuestionCondition.setExamId(examId);
//						List<ExamQuestion> examQuestionList =  examQuestionService.findExamQuestionByCondition(examQuestionCondition);
//						teamScoringRateList.add(examQuestionList);*/
//								
//								examQuestionService.examQuestiontotalTime(teamId, ownerId, type, examId,unitId);
//							}	
//						}
//					}
//					
//					
//					/*List<Map<String,Object>> examQuestionEmptyAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionCorrectAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionAnswerCountList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionScoreList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestionAverageScoreList = new ArrayList<Map<String,Object>>();
//				List<Map<String,Object>> examQuestiontotalTimeList = new ArrayList<Map<String,Object>>();*/
//					
//					/*examQuestionService.ubatchUpdateExamQuestionScore(examQuestionScoreList.toArray());
//				examQuestionService.batchUpdateExamQuestionEmptyAnswerCount(examQuestionEmptyAnswerCountList.toArray());
//				examQuestionService.batchUpdateExamQuestionCorrectAnswerCount(examQuestionCorrectAnswerCountList.toArray());
//				examQuestionService.batchUpdateExamQuestionAnswerCount(examQuestionAnswerCountList.toArray());
//				examQuestionService.batchUpdateExamQuestionAverageScore(examQuestionScoreList.toArray());
//				examQuestionService.batchUpdateExamQuestiontotalTime(examQuestiontotalTimeList.toArray());*/
//					
//					//examQuestionService.batchUpdateExamQuestion(examQuestionEmptyAnswerCountList,examQuestionCorrectAnswerCountList,examQuestionAnswerCountList,examQuestionScoreList,examQuestionAverageScoreList,examQuestiontotalTimeList,teamScoringRateList);
//				}
//				long endTime = System.currentTimeMillis();
//				System.out.println("examQuestionHandle????????????????????? " + ((endTime - startTime)) + "??????");
//			}
//		}
//		
//		
//	private void examStatHandle(List<PjExam> pjExamList,List<UserPaper>userPapers,Integer ownerId ,Integer unitId,Integer paperId,Integer type){
//		long startTime = System.currentTimeMillis();
//		List<ExamStat> examStatList = new ArrayList<ExamStat>();
//		Paper paper = papaperService.findPaperById(paperId);
//		Object[] examIdObj =null;
//		Object[] teamIdObj =null;
//		 if(examIds != null && examIds.size() > 0 ) {
//			 
//			 examIdObj =  examIds.toArray();
//			 teamIdObj = teamIds.toArray();
//		 }
//		Map<Integer,Long> sumStudentMap= this.examStudentService.countTotalStudentByExamIds(examIdObj);
//		Map<Integer,ExamStat> examStatMap = examStatService.findExamStatRankByExamIdObj(examIdObj);
//		Map<Integer,Long> sumScoreMap = userPaperService.countUserPaperTeamsTotalScoreObj(teamIdObj,ownerId,unitId,type);
//		Map<Integer,Integer> answerCountMap = userPaperService.countUserPaperAnswerCount(teamIdObj,ownerId,unitId,type);
//		Map<Integer,List<ExamStudent>> examStudentMap = examStudentService.findExamStudentByExamIdObj(examIdObj);
//		Map<Integer,Float> highestScoreMap = examStudentService.findExamStudentHighestScoreByExamIdObj(examIdObj);
//		Map<Integer,Float> lowestScoreMap = examStudentService.findExamStudentLowestScoreByExamIdObj(examIdObj);
//		//???????????? ???????????????
//				if(userPapers != null && userPapers.size() > 0){
//					for (PjExam pjExam : pjExamList) {
//						for(UserPaper userPaper : userPapers){
//						int teamId = pjExam.getTeamId();
//						if(teamId == userPaper.getTeamId()){
//						Integer examId = pjExam.getId();
//						Long sumStudent = sumStudentMap.get(examId);
//						ExamStat examStat = examStatMap.get(examId);
//						
//						if(sumStudent != null){
//						if (examStat != null && examStat.getDataChanged() == true) {
//							// ??????????????????
//							Long sumScore = sumScoreMap.get(teamId);
//								//	userPaperService.countUserPaperTeamTotalScore(ownerId, pjExam.getTeamId());
//							
//							if(sumScore != null && sumScore.intValue()!=0 && sumStudent.intValue()!=0){
//								if (sumScore != null) {
//									examStat.setTotalScore((float) sumScore);// ????????????
//								}
//								// ???????????????????????????
//									
//									if(paper != null){
//										
//										Integer highCount = 0;
//										Integer lowCount = 0;
//										Integer passCount =0;
//										Float fullScore = paper.getScore();
//										examStat.setFullScore(fullScore); //????????????
//										Float highScore =  (float) (fullScore* 0.9);
//										examStat.setHighScore(highScore); //?????? / ?????? ?????? 
//										
//										Float lowScore = (float) (fullScore* 0.75);
//										examStat.setLowScore(lowScore);  //?????? / ?????? ??????
//										
//										Float passScore = (float) (fullScore* 0.6);
//										examStat.setPassScore(passScore); //????????????
//										List<ExamStudent> examStudentList = examStudentMap.get(examId);
//												//this.examStudentService.findExamStudentsByExamId(examId);
//										List<Float> list = new ArrayList<Float>();
//										//?????????????????? ???????????????????????????
//										for(ExamStudent es : examStudentList){
//											if(es.getScore().floatValue()!=-1){
//												list.add(es.getScore());
//												if(es.getScore() >= highScore){
//													highCount = highCount +1;
//												}
//												if(es.getScore() >= lowScore){
//													lowCount = lowCount +1;
//												}
//												if(es.getScore() >= passScore){
//													passCount = passCount +1;
//												}
//											}
//										}
//										
//										
//										Float[] floatArray = new Float[list.size()];
//										for (int i = 0; i < list.size(); i++) {
//											floatArray[i] = list.get(i);
//										}
//										//??????
//										Integer answerCount = answerCountMap.get(teamId);
//										examStat.setStudentCount(answerCount);// ??????????????????
//										if(answerCount != 0){
//											examStat.setAverageScore((float) sumScore / answerCount);// ?????????
//										}
//										if(floatArray != null && floatArray.length > 0){
//											examStat.setSdScore(getAdScore(floatArray,(float)(sumScore/sumStudent)));//?????????
//											examStat.setMadValue(getMadValue(floatArray,(float)(sumScore/sumStudent)));//??????
//											examStat.setMovValue(floatArray[floatArray.length-1]-floatArray[0]);//??????
//										}
//										
//										//Integer param = 1;
//										//Integer highCount = this.examStatService.countHighAndLowAndPass(param, examId);
//										examStat.setHighCount(highCount); //????????????
//										
//										//param = 2;
//										//Integer lowCount = this.examStatService.countHighAndLowAndPass(param, examId);
//										examStat.setLowCount(lowCount); //????????????
//										
//										//param = 3;
//										//Integer passCount = this.examStatService.countHighAndLowAndPass(param, examId);;
//										examStat.setPassCount(passCount); //????????????
//										
//										Float highestScore = highestScoreMap.get(examId);
//												//examStudentService.findExamStudentHighestScoreByExamId(examId);
//										examStat.setHighestScore(highestScore); //???????????????
//										Float lowestScore = lowestScoreMap.get(examId);
//												//examStudentService.findExamStudentLowestScoreByExamId(examId);
//										examStat.setLowestScore(lowestScore); //???????????????
//										
//									}
//							}
//							
//							}
//							
//							examStatList.add(examStat);
//							}
//						
//							}	
//							
//						}
//					}
//					
//					examStatService.batchUpdateExamStat(examStatList);
//					
//					long endTime = System.currentTimeMillis();
//					System.out.println("examStatHandle????????????????????? " + ((endTime - startTime)) + "??????");
//				}
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/**
//	 * @function ???????????????
//	 * @param array
//	 * @return
//	 * @date 2016???1???26???
//	 */
//	private Float getAdScore(Float[] array,Float avg){
//		double sum = 0f;
//        for(int i = 0;i < array.length;i++){
//            sum += Math.sqrt(((double)array[i] -avg) * (array[i] -avg));
//        }
//        //return (float)(sum / (array.length - 1));
//        return (float)(sum / (array.length));
//	}
//	
//	/**
//	 * @function ????????????
//	 * @param array
//	 * @param avg
//	 * @return
//	 * @date 2016???1???26???
//	 */
//	private Float getMadValue(Float[] array,Float avg){
//		Float date = 0f;
//		for(int i = 0;i < array.length;i++){
//			date += array[i]-avg;
//		}
//		return date;
//	}
//
//	@Override
//	public ExamResult InitExamStatistics(Integer teamId, Integer schoolId,
//			Integer teacherId, String type, String examUUid, String code) {
//		String subjectCode="";
//		String termValue="";
//		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
//				.findSchoolTermCurrentBySchoolId(schoolId);
//		List<UserQuestionResult> userQuestionResultList = null;
//			Exam em = examService.findExamByUuid(examUUid);
//			if (em != null) {
//				userQuestionResultList = userQuestionService
//						.findUserQuestionByPaperId(em.getPaperId());
//				subjectCode = em.getSubjectCode();
//
//			}
//		if (subjectCode == null) {
//			subjectCode = "";
//		}
//		String scholYear = schoolTermCurrent.getSchoolYear();
//		String termCode = schoolTermCurrent.getSchoolTermCode();
//		SchoolTerm schoolTerm = getSchoolTermByCode(termCode);
//		if (schoolTerm != null) {
//			termValue = schoolTerm.getGbCode();
//		}
//		Team team = teamService.findTeamById(teamId);
//		PjExam pj = new PjExam();
//		pj.setCreateDate(new Date());
//		pj.setModifyDate(new Date());
//		pj.setExamDate(new Date());
//		pj.setSchoolYear(scholYear);
//		pj.setTermCode(termCode);
//		pj.setTermValue(termValue);
//		pj.setTeacherId(teacherId);
//		pj.setIsDelete(false);
//		pj.setSchoolId(schoolId);
//		if (team != null && team.getGradeId() != null) {
//			pj.setGradeId(team.getGradeId());
//		}
//		pj.setTeamId(teamId);
//		pj.setSubjectCode(subjectCode);
//		pj.setJointExamCode(code);
//		pj.setExamRound(1);
//		pj.setExamType(type);
//
//		Paper pp = papaperService.findPaperById(em.getPaperId());
//		if (pp != null) {
//
//			pj.setFullScorce(pp.getScore());
//			pj.setPaperUuid(pp.getPaperUuid());
//		}
//
//		ExamResult examResult = pjExamService.InitExamData(pj);
//		List<ExamQuestion> examQuestionList = new ArrayList<ExamQuestion>();
//
//		if (userQuestionResultList != null
//				&& userQuestionResultList.size() > 0) {
//			for (UserQuestionResult userQuestionResult : userQuestionResultList) {
//				// ?????????ExamQuestion???
//				ExamQuestion examQuestion = new ExamQuestion();
//				examQuestion.setQuestionUuid(userQuestionResult
//						.getQuestionUuid());
//				examQuestion.setExamId(examResult.getPjExam().getId());
//				examQuestion.setQuestionType(userQuestionResult
//						.getQuestionType());
//				examQuestion.setAnswerCount(0);
//				examQuestion.setRightAnswerCount(0);
//				examQuestion.setEmptyCount(0);
//				examQuestion.setScore(0d);
//				examQuestion.setAverageScore(0f);
//				// ?????????????????????
//				examQuestion.setSubjectCode(userQuestionResult
//						.getSubjectCode());
//				examQuestion.setKnowledgeId(userQuestionResult
//						.getKnowledgeId());
//				if (userQuestionResult.getDifficulity() != null) {
//					examQuestion.setDifficulity(Float
//							.valueOf(userQuestionResult
//									.getDifficulity() + ""));
//				}
//				examQuestion.setCognition(userQuestionResult
//						.getCognition());
//				examQuestion.setFullScore(userQuestionResult
//						.getScore());
//				examQuestion.setTeamRank(0);
//				examQuestion.setGradeRank(0);
//				examQuestion.setTotalTime(0);
//				examQuestion.setAverageTime(0);
//				examQuestion.setIsDeleted(false);
//				examQuestion.setCreateDate(new Date());
//				examQuestionList.add(examQuestion);
//				// examQuestionService.add(examQuestion);
//
//			}
//		}
//
//		examQuestionService.InitExamQuestionData(examQuestionList);
//		
//		return examResult;
//	}
//	
//	private SchoolTerm getSchoolTermByCode(String code) {
//		SchoolTerm schoolTerm = null;
//		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
//		schoolTermCondition.setCode(code);
//		schoolTermCondition.setIsDelete(false);
//		List<SchoolTerm> schoolTermList = schoolTermService
//				.findSchoolTermByCondition(schoolTermCondition, null,
//						null);
//		if (schoolTermList.size() > 0) {
//			schoolTerm = schoolTermList.get(0);
//		}
//		return schoolTerm;
//	}
//
//	public void setSchoolTermService(SchoolTermService schoolTermService) {
//		this.schoolTermService = schoolTermService;
//	}
//
//	public void setSchoolTermCurrentService(SchoolTermCurrentService schoolTermCurrentService) {
//		this.schoolTermCurrentService = schoolTermCurrentService;
//	}
//
//	public void setTeamService(TeamService teamService) {
//		this.teamService = teamService;
//	}
//
//	public void setExamService(ExamService examService) {
//		this.examService = examService;
//	}
//}
//
