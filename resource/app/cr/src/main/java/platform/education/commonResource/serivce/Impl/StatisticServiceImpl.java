package platform.education.commonResource.serivce.Impl;

import platform.education.commonResource.serivce.StatisticService;
import platform.education.commonResource.serivce.unit.JDBCHandle;
import platform.education.commonResource.serivce.unit.ValueComparator;
import platform.education.commonResource.serivce.vo.TeamQuestionVo;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.*;
import platform.education.paper.vo.*;
import platform.education.resource.model.StatisticsTask;
import platform.education.resource.service.StatisticsTaskService;

import java.math.BigDecimal;
import java.util.*;

public class StatisticServiceImpl implements StatisticService{
	private PjExamService pjExamService;

	private ExamStatService examStatService;

	private PaPaperService paPaperService;

	private UserQuestionService userQuestionService;

	private UserPaperService userPaperService;

	private ExamStudentService examStudentService;

	private ExamQuestionService examQuestionService;

	private SchoolTermCurrentService schoolTermCurrentService;

	private TeamService teamService;

	private SchoolTermService schoolTermService;

    private TeamStudentService teamStudentService;
    
	private LpTaskExamUnitService lpTaskExamUnitService;
	
	private PaPaperCatalogService paPaperCatalogService;
	
	private PaQuestionService paQuestionService;
	
	private TaskService taskService;
	
	public StatisticsTaskService getStatisticsTaskService() {
		return statisticsTaskService;
	}
	public void setStatisticsTaskService(StatisticsTaskService statisticsTaskService) {
		this.statisticsTaskService = statisticsTaskService;
	}
	private StatisticsTaskService statisticsTaskService;
	
	public PjExamService getPjExamService() {
		return pjExamService;
	}
	public void setPjExamService(PjExamService pjExamService) {
		this.pjExamService = pjExamService;
	}
	public ExamStatService getExamStatService() {
		return examStatService;
	}
	public void setExamStatService(ExamStatService examStatService) {
		this.examStatService = examStatService;
	}
	public PaPaperService getPaPaperService() {
		return paPaperService;
	}
	public void setPaPaperService(PaPaperService paPaperService) {
		this.paPaperService = paPaperService;
	}
	public ExamStudentService getExamStudentService() {
		return examStudentService;
	}
	public void setExamStudentService(ExamStudentService examStudentService) {
		this.examStudentService = examStudentService;
	}
	public ExamQuestionService getExamQuestionService() {
		return examQuestionService;
	}
	public void setExamQuestionService(ExamQuestionService examQuestionService) {
		this.examQuestionService = examQuestionService;
	}
	public SchoolTermCurrentService getSchoolTermCurrentService() {
		return schoolTermCurrentService;
	}
	public void setSchoolTermCurrentService(
			SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}
	public TeamService getTeamService() {
		return teamService;
	}
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	public SchoolTermService getSchoolTermService() {
		return schoolTermService;
	}
	public void setSchoolTermService(SchoolTermService schoolTermService) {
		this.schoolTermService = schoolTermService;
	}
	public TeamStudentService getTeamStudentService() {
		return teamStudentService;
	}
	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}
	@Override
	public void initBatchExamStatistics(List<Integer> teamIds,
			Integer schoolId, Integer teacherId, String type, Integer paperId,
			String code) {
				String subjectCode = "";
				String termValue = "";
				Float fullScorce=0.0f;
				String ppUuid="";
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
				List<PaQuestionVo> volist= paQuestionService.findPaQuestionVoByPaperId(paperId,0,null);
				PaPaper pp = paPaperService.findPaPaperById(paperId);
				PaPaperCatalogCondition ppcondition=new PaPaperCatalogCondition();
				ppcondition.setPaperId(paperId);
				List<PaPaperCatalog> list=paPaperCatalogService.findPaPaperCatalogByCondition(ppcondition);
				if(list!=null&&list.size()>0){
					
					subjectCode = list.get(0).getSubjectCode();
				}

				if (subjectCode == null) {
					subjectCode = "";
				}
				if (pp != null) {
					fullScorce= pp.getScore();
					ppUuid=pp.getPaperUuid();
				}
				String scholYear = schoolTermCurrent.getSchoolYear();
				String termCode = schoolTermCurrent.getSchoolTermCode();
				SchoolTerm schoolTerm = getSchoolTermByCode(termCode);
				if (schoolTerm != null) {
					termValue = schoolTerm.getGbCode();
				}
				PjExam[] pjlist=new PjExam[teamIds.size()];
				ExamStat[] pslist=new ExamStat[teamIds.size()];
				Integer[] teamIDs=new Integer[teamIds.size()];
				List<ExamQuestion>eqlist=new ArrayList<ExamQuestion>();
				int i=0;
				for(Integer teamId:teamIds){
					
					Team team = teamService.findTeamById(teamId);
					PjExam pj = new PjExam();
					pj.setCreateDate(new Date());
					pj.setModifyDate(new Date());
					pj.setExamDate(new Date());
					pj.setSchoolYear(scholYear);
					pj.setTermCode(termCode);
					pj.setTermValue(termValue);
					pj.setTeacherId(teacherId);
					pj.setIsDelete(false);
					pj.setSchoolId(schoolId);
					if (team != null && team.getGradeId() != null) {
						pj.setGradeId(team.getGradeId());
					}
					pj.setTeamId(teamId);
					pj.setSubjectCode(subjectCode);
					pj.setJointExamCode(code);
					pj.setExamRound(1);
					pj.setExamType(type);
					pj.setPaperUuid(ppUuid);
					pj.setFullScorce(fullScorce);
					pjlist[i]=pj;
					teamIDs[i]=teamId;
					i++;
				}
			   pjExamService.createBatch(pjlist);
		       PjExamCondition pjc=new PjExamCondition();
		       pjc.setIsDelete(false);
		       pjc.setJointExamCode(code);
		       pjc.setExamType(type);
		       List<PjExam> pelist=pjExamService.findPjExamByCondition(pjc);
		       Map<Integer,Integer> map=new HashMap<Integer, Integer>();
		       if(pelist!=null){
		    	   i=0;
		    	   for(PjExam pj:pelist){
		    		List<ExamQuestion>teamEqlist=new ArrayList<ExamQuestion>();
		    		map.put(pj.getTeamId(), pj.getId());
		    		//examStat
		    		ExamStat examStat = new ExamStat();
		    		examStat.setExamId(pj.getId());
		    		examStat.setFullScore(fullScorce);
		    		examStat.setCreateDate(new Date());
		    		examStat.setModifyDate(new Date());
		    		examStat.setDataChanged(true);
		    		pslist[i]=examStat;
		    		i++;
		    		//ExamQuestion
		    		teamEqlist=toExamQuestionList(volist, pj.getId());
		    		eqlist.addAll(teamEqlist);
		    	   }
		    	   //ExamStudent
		    	   List<TeamStudentVo> tslist=teamStudentService.findTeamStudentVoByTeamIds(teamIDs);
		    	   if(tslist!=null&&tslist.size()>0){
		    		   ExamStudent[] eslist=new ExamStudent[tslist.size()];
		    		   i=0;
		    		   for(TeamStudentVo teamStudent:tslist){
		    			    ExamStudent examStudent=new ExamStudent();
		    				examStudent.setExamId(map.get(teamStudent.getTeamId()));
		    				examStudent.setStudentId(teamStudent.getStudentId());
		    				examStudent.setUserId(teamStudent.getUserId());
		    				examStudent.setNumber(teamStudent.getNumber()); // ????????????????????????????????????
		    				examStudent.setSchoolNumber(teamStudent.getStudentNumber()); // ?????????????????????????????????
		    				examStudent.setName(teamStudent.getName()); // ??????????????????????????????????????????
		    				examStudent.setTestType("01"); // 01--????????????
		    				examStudent.setScore(-1F);
		    				examStudent.setSumTime(-1);
		    				examStudent.setCreateDate(new Date());
		    				examStudent.setModifyDate(new Date());
		    				eslist[i]=examStudent;
		    				i++;
		    		   }
		    		   examStudentService.createBatch(eslist);
		    	   }
		    	   examStatService.createBatch(pslist);
		    	   examQuestionService.InitExamQuestionData(eqlist);
		       }
			}
			public PaPaperCatalogService getPaPaperCatalogService() {
		return paPaperCatalogService;
	}
	public void setPaPaperCatalogService(PaPaperCatalogService paPaperCatalogService) {
		this.paPaperCatalogService = paPaperCatalogService;
	}
	public PaQuestionService getPaQuestionService() {
		return paQuestionService;
	}
	public void setPaQuestionService(PaQuestionService paQuestionService) {
		this.paQuestionService = paQuestionService;
	}
			private List<ExamQuestion> toExamQuestionList(List<PaQuestionVo> userQuestionResultList,Integer examId){
				List<ExamQuestion> examQuestionList=new ArrayList<ExamQuestion>();
				if (userQuestionResultList != null && userQuestionResultList.size() > 0) {
					for (PaQuestionVo userQuestionResult : userQuestionResultList) {
						// ?????????ExamQuestion???
						ExamQuestion examQuestion = new ExamQuestion();
						examQuestion.setQuestionUuid(userQuestionResult.getUuid());
						examQuestion.setExamId(examId);
						examQuestion.setQuestionType(userQuestionResult.getQuestionTypeString());
						// ?????????????????????
						examQuestion.setSubjectCode(userQuestionResult.getSubjectCode());
						examQuestion.setPos(userQuestionResult.getPos());
						if (userQuestionResult.getDifficulity() != null) {
							examQuestion.setDifficulity(Float.valueOf(userQuestionResult.getDifficulity() + ""));
						}
						examQuestion.setCognition(userQuestionResult.getCognition());
						examQuestion.setFullScore(userQuestionResult.getScore());
						examQuestion.setCreateDate(new Date());
						examQuestion.setModifyDate(new Date());
						examQuestionList.add(examQuestion);
					}
				}
				return examQuestionList;
			}
		
			private SchoolTerm getSchoolTermByCode(String code) {
				SchoolTerm schoolTerm = null;
				SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
				schoolTermCondition.setCode(code);
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> schoolTermList = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if (schoolTermList.size() > 0) {
					schoolTerm = schoolTermList.get(0);
				}
				return schoolTerm;
			}
			@Override
			public Boolean statisticHandle(Integer objectId,Integer type,Integer stId) {
				Boolean flag = false;
				try {
					if (type != null && PaperType.LEARNING_PLAN == type.intValue()) {
						learningDesign(objectId);
					} else {
						examStatisic(objectId);
					}
					flag = true;
					StatisticsTask stc=statisticsTaskService.findStatisticsTaskById(stId);
					stc.setState(1);
					stc.setModifyDate(new Date());
					statisticsTaskService.modify(stc);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return flag;
			}
			/**
			 * ????????????
			 */
			private Boolean examStatisic(Integer examId) {
                 TaskVo vo=taskService.findTaskVoByExamId(examId);
				// long startTime = System.currentTimeMillis();
				Boolean flag = false;
				
				Integer ownerId=vo.getId();
				final Integer  paperId=vo.getPaperId();
				// ?????????????????????
				try {

					// 1. ??????????????????????????????????????????????????????????????????????????????
					final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerId(ownerId, null, PaperType.EXAM);

					if (userPapers != null && userPapers.size() > 0) { // ????????????
						// 2 . ??????examId???pj_exam???????????????
						final PjExam newExam = this.pjExamService.findPjExamById(examId);
						if (newExam != null) {
							UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
							userQuestionCondition.setOwnerId(ownerId);
//							userQuestionCondition.setTeamId(newExam.getTeamId());
							userQuestionCondition.setType(PaperType.EXAM);
							List<UserQuestion> uqlist = userQuestionService.findUserQuestionByCondition(userQuestionCondition);
							// 2. ??????joint_exam_code???????????????????????????????????????????????????
							PjExamCondition newpjExamCondition = new PjExamCondition();
							newpjExamCondition.setJointExamCode(newExam.getJointExamCode());
							newpjExamCondition.setExamType(PaperType.PAPER_CODE);
							newpjExamCondition.setIsDelete(false);
							final List<PjExam> pjExamList = this.pjExamService.findPjExamByCondition(newpjExamCondition);
							final Map<Integer, List<UserQuestion>> questionMap = new HashMap<Integer, List<UserQuestion>>();
							questionMap.put(ownerId, uqlist);
							// ??????pj_exam_student?????????
//							if(examStudentHandle(paperId, pjExamList, userPapers,questionMap,PaperType.EXAM)) {
//								// ?????? pj_exam_stat?????????
//								examStatHandle(pjExamList, userPapers, null, paperId, PaperType.EXAM);
//								// 3. pj_exam_question???????????????
//								examQuestionHandle(pjExamList, userPapers, null, questionMap, PaperType.EXAM, newExam.getJointExamCode());
//							};
					                examStudentHandle(paperId, pjExamList, userPapers,questionMap,PaperType.EXAM);
				                	examStatHandle(pjExamList, userPapers, null, paperId, PaperType.EXAM);
				                	examQuestionHandle(pjExamList, userPapers, null, questionMap, PaperType.EXAM, newExam.getJointExamCode());
						}
                        
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return flag;
			}

			public TaskService getTaskService() {
				return taskService;
			}
			public void setTaskService(TaskService taskService) {
				this.taskService = taskService;
			}
			public UserQuestionService getUserQuestionService() {
				return userQuestionService;
			}
			public void setUserQuestionService(UserQuestionService userQuestionService) {
				this.userQuestionService = userQuestionService;
			}
			public UserPaperService getUserPaperService() {
				return userPaperService;
			}
			public void setUserPaperService(UserPaperService userPaperService) {
				this.userPaperService = userPaperService;
			}
			public LpTaskExamUnitService getLpTaskExamUnitService() {
				return lpTaskExamUnitService;
			}
			public void setLpTaskExamUnitService(LpTaskExamUnitService lpTaskExamUnitService) {
				this.lpTaskExamUnitService = lpTaskExamUnitService;
			}
			/**
			 * ??????????????? ownerId ??????????????????ID ?????????????????????????????????????????????
			 */
			private void learningDesign(Integer ownerId) {

				// long startTime = System.currentTimeMillis();
				// ???????????????taskID ???lp_task_exam_unit???????????????????????????CODE?????????
				LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
				lpTaskExamUnitCondition.setDeleted(false);
				lpTaskExamUnitCondition.setTaskId(ownerId);
				List<LpTaskExamUnit> lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
				if (lpTaskExamUnitList != null && lpTaskExamUnitList.size() > 0) {
					// ?????????????????????????????????
					for (LpTaskExamUnit lteu : lpTaskExamUnitList) {
						lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
						lpTaskExamUnitCondition.setJoinExamCode(lteu.getJoinExamCode());
						List<LpTaskExamUnit> lpTaskExamUnitList1 = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
						Integer paperId = null;
						Integer untilId = null;
						// ?????????????????????ownerId??????????????????
						if (lpTaskExamUnitList1 != null && lpTaskExamUnitList1.size() > 0) {
							Integer[] ownerIds = new Integer[lpTaskExamUnitList1.size()];
							for (int i = 0; i < lpTaskExamUnitList1.size(); i++) {
								ownerIds[i] = lpTaskExamUnitList1.get(i).getTaskId();
								if (i == 0) {
									PjExam pje = this.pjExamService.findPjExamById(lpTaskExamUnitList1.get(i).getExamId());
									PaPaperCondition condition=new PaPaperCondition();
									condition.setPaperUuid(pje.getPaperUuid());
									List<PaPaper> pa =paPaperService.findPaPaperByCondition(condition);
									paperId = pa.get(0).getId();
									untilId = lpTaskExamUnitList1.get(i).getUnitId();
								}
							}

							// ?????????????????????ownerId,??????????????????????????????????????????ownerIds?????????userQuestion
							final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerIds(ownerIds, untilId, PaperType.LEARNING_PLAN);
							if (userPapers != null && userPapers.size() > 0) {
								ownerIds = new Integer[userPapers.size()];
								int i = 0;
								for (UserPaper up : userPapers) {
									ownerIds[i] = up.getOwnerId();
									i++;
								}
							}
							// ????????????????????????????????????????????????????????????
							final List<UserQuestion> uqlist = this.userQuestionService.findUserQuestionByOwnerIds(ownerIds, untilId, PaperType.LEARNING_PLAN);
							Map<Integer, List<UserQuestion>> questionMap = new HashMap<Integer, List<UserQuestion>>();
							if (uqlist != null && uqlist.size() > 0) {
								List<UserQuestion> ownList = new ArrayList<UserQuestion>();
								for (UserQuestion uq : uqlist) {
									ownList = new ArrayList<UserQuestion>();
									if (questionMap.get(uq.getOwnerId()) != null) {
										ownList = questionMap.get(uq.getOwnerId());
									}
									ownList.add(uq);
									questionMap.put(uq.getOwnerId(), ownList);
								}
							}
							if (userPapers != null && userPapers.size() > 0) {
								// ??????code???????????????????????????????????????????????????;
								PjExamCondition pjExamCondition = new PjExamCondition();
								pjExamCondition.setJointExamCode(lteu.getJoinExamCode());
								pjExamCondition.setExamType(PaperType.LEARNING_CODE);
								pjExamCondition.setIsDelete(false);
								List<PjExam> pjExamList = pjExamService.findPjExamByCondition(pjExamCondition);
								if(examStudentHandle(paperId, pjExamList, userPapers,questionMap,PaperType.LEARNING_PLAN)) {
									// ?????? pj_exam_stat?????????
									examStatHandle(pjExamList, userPapers, untilId, paperId, PaperType.LEARNING_PLAN);
									// 3. pj_exam_question???????????????
									examQuestionHandle(pjExamList, userPapers, untilId, questionMap, PaperType.LEARNING_PLAN, lteu.getJoinExamCode());
									
								}
							}
						}
					}
				}

			}
			// ?????? examStudent???????????????
			private boolean examStudentHandle(Integer paperId, List<PjExam> pjExamList, List<UserPaper> userPapers, Map<Integer, List<UserQuestion>> questionMap,Integer type) {
				boolean flag = false;
				// long startTime = System.currentTimeMillis();
				PaPaper paper = paPaperService.findPaPaperById(paperId);
				List<ExamStudent> examStudentList = null;
				List<ExamStudent> sumExamStudentList = new ArrayList<ExamStudent>();
				Map<Integer,Object> gradeMap=new HashMap<Integer, Object>();
				Map<Integer,Integer> rightMap=new HashMap<Integer, Integer>();
				Map<Integer,Integer> anwerMap=new HashMap<Integer, Integer>();
				Map<Integer,Integer> timeCountMap=new HashMap<Integer, Integer>();
				for (List<UserQuestion> value : questionMap.values()) {  
					  
				    for(UserQuestion uq:value){
				    	Integer anwer=0;
				    	if(anwerMap.get(uq.getUserId())!=null){
				    		anwer=anwerMap.get(uq.getUserId());
			    		}
				    	anwerMap.put(uq.getUserId(), anwer+1);
				    	if(uq.getIsCorrect()){
				    		Integer right=0;
				    		if(rightMap.get(uq.getUserId())!=null){
				    			right=rightMap.get(uq.getUserId());
				    		}
				    		rightMap.put(uq.getUserId(), right+1);
				    	}
				    Integer time=0;
				    if(timeCountMap.get(uq.getUserId())!=null){
				    	
				    }else{
				    	timeCountMap.put(uq.getUserId(), time);
				    }
				    Integer ast=uq.getAnswerTime();
			    	if(ast==null){
			    		ast=0;
			    	}
			    	time=timeCountMap.get(uq.getUserId())+ast;
				    timeCountMap.put(uq.getUserId(), time);
				    }
				   
				}  
				if (paper != null) {
					String paperUuid = paper.getPaperUuid();
					if (pjExamList != null && pjExamList.size() > 0) {
						for (PjExam pjExam : pjExamList) {
							Map<Integer,Object>teamMap=new HashMap<Integer, Object>();
							if (userPapers != null && userPapers.size() > 0) {
								for (UserPaper userPaper : userPapers) {
									// ????????????????????????
									if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
										Integer ag=userPaper.getObjectId();
                                         if(type==2){
                                        	 ag=null;
                                         }
										List<UserPaper> list = this.userPaperService.findUserPaperUserTotalScore(ag, userPaper.getOwnerId(), type, userPaper.getTeamId());
										// ??????EXAMID???????????????????????????????????????
										examStudentList = this.examStudentService.findExamStudentsByExamId(pjExam.getId(), null);

										if (list != null && list.size() > 0) {
											Map<Integer, Object> map = new HashMap<Integer, Object>();
											for (UserPaper up : list) {
												map.put(up.getUserId(), up.getScore() + "");
												gradeMap.put(up.getUserId(), up.getScore());
												if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) 
												{
													teamMap.put(up.getUserId(), up.getScore());
												}
											}
											ValueComparator bvc = new ValueComparator(teamMap);
											TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
											rank_map = sort(teamMap);
											if (examStudentList != null && examStudentList.size() > 0) {

												for (ExamStudent es : examStudentList) {
													String score = "-1.0";

													if (map.get(es.getUserId()) != null) {
														score = (String) map.get(es.getUserId());
													}
													if(rank_map.get(es.getUserId())!=null){
														es.setTeamRank(rank_map.get(es.getUserId()));
													}else{
														es.setTeamRank(0);
													}
													if(anwerMap.get(es.getUserId())!=null){
														es.setAnswerCount(anwerMap.get(es.getUserId()));
													}else{
														es.setAnswerCount(0);
													}
													
													if(rightMap.get(es.getUserId())!=null){
														es.setRightAnswerCount(rightMap.get(es.getUserId()));
													}else{
														es.setRightAnswerCount(0);
													}
													if(timeCountMap.get(es.getUserId())!=null){
														es.setSumTime(timeCountMap.get(es.getUserId()));
													}else{
														es.setSumTime(-1);
													}
													es.setScore(Float.valueOf(score));
													sumExamStudentList.add(es);
												}
											}
		                                  
										}

										// ????????????????????????????????????
//										String SQL = "UPDATE pj_exam_student es set es.score = ? where id = ?";
//										JDBCHandle.jdbcUpdateExamStudent(SQL, examStudentList);
									}
								}
							}
						}
						ValueComparator bvc1 = new ValueComparator(gradeMap);
						TreeMap<Integer, Integer> grade_rank_map = new TreeMap<Integer, Integer>(bvc1);
						grade_rank_map = sort(gradeMap);
						if(sumExamStudentList!=null){
							for(ExamStudent es:sumExamStudentList){
								if(grade_rank_map.get(es.getUserId())!=null){
									es.setGradeRank(grade_rank_map.get(es.getUserId()));
								}else{
									es.setGradeRank(0);
								}
							}
							String SQL = "UPDATE pj_exam_student es set es.score = ? ,es.team_rank=?,es.grade_rank=?,es.answer_count=?,es.right_answer_count=?,es.sum_time=?  where id = ?";
							JDBCHandle.jdbcUpdateExamStudent(SQL, sumExamStudentList);
							flag = true;
						}
						// long endTime = System.currentTimeMillis();
						// System.out.println("examStudentHandle????????????????????? "
						// + ((endTime - startTime)) + "??????");
					}

				}
				return flag;
			}

			// examQuestion???????????????
			private void examQuestionHandle(List<PjExam> pjExamList, List<UserPaper> userPapers, Integer unitId, Map<Integer, List<UserQuestion>> quetsionMap, Integer type, String code) {
				// ??????examId???????????????
				List<ExamQuestion> examQuestionList = null;
				// ???????????????????????????????????????
				List<ExamQuestion> notSumExamQuestionList = new ArrayList<ExamQuestion>();
				// ??????examId???????????????
				List<ExamQuestion> notExamQuestionList = new ArrayList<ExamQuestion>();
				// ???????????????????????????????????????
				List<ExamQuestion> sumExamQuestionList = new ArrayList<ExamQuestion>();
				// ????????????????????????????????????????????????key:questionUuid value RightCount*fullScore,????????????????????????
				Map<String, Object> teamCoutQuestionMap = new HashMap<String, Object>();
				// ????????????????????????????????????????????????key:questionUuid value score,????????????????????????
				Map<String, Object> teamScoreQuestionMap = new HashMap<String, Object>();
				// ????????????????????????????????????????????????key:questionUuid value AnwerCout,???????????????????????????
				Map<String, Integer> gradeAnwerCoutQuestionMap = new HashMap<String, Integer>();
				// ????????????????????????????????????????????????key:questionUuid value RightCout,???????????????????????????
				Map<String, Integer> gradeRightCoutQuestionMap = new HashMap<String, Integer>();
				// ?????????????????????????????????????????????key:questionUuid value Map<Integer, Object>,????????????
				Map<String, Object> teamRateQuestionMap = new HashMap<String, Object>();
				// ????????????????????????????????????
				List<ExamQuestionVo> evlist = new ArrayList<ExamQuestionVo>();
				List<TeamQuestionVo> volist = new ArrayList<TeamQuestionVo>();
				// ???????????????????????????
				if (pjExamList != null && pjExamList.size() > 0) {

					for (PjExam pjExam : pjExamList) {
						if (userPapers != null && userPapers.size() > 0) {
							// ?????????????????????
							if (isExist(pjExam.getTeamId().intValue(), userPapers)) {
								ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
								examQuestionCondition.setExamId(pjExam.getId());
								notExamQuestionList = this.examQuestionService.findExamQuestionByCondition(examQuestionCondition);
								if (notExamQuestionList != null && notExamQuestionList.size() > 0) {
									notSumExamQuestionList.addAll(notExamQuestionList);
								}
							}
							for (UserPaper userPaper : userPapers) {
								// ????????????????????????
								if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
									Integer examId = pjExam.getId();
									ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
									examQuestionCondition.setExamId(examId);
									examQuestionList = this.examQuestionService.findExamQuestionByCondition(examQuestionCondition);
									if (examQuestionList != null && examQuestionList.size() > 0) {
										List<UserQuestion> list = new ArrayList<UserQuestion>();
										List<UserQuestion> list1 = new ArrayList<UserQuestion>();
										// ?????????????????????????????????????????????????????????
										list1 = quetsionMap.get(userPaper.getOwnerId());
										// ????????????list,????????????????????????????????????
										Map<String, Integer> questionCount = new HashMap<String, Integer>(examQuestionList.size());
										// ????????????list,????????????????????????????????????
										Map<String, Integer> rightQuestionCount = new HashMap<String, Integer>(examQuestionList.size());
										// ????????????list,????????????????????????????????????
										Map<String, Integer> empytQuestionCount = new HashMap<String, Integer>(examQuestionList.size());
										// ????????????list,?????????????????????????????????
										Map<String, Double> sumQuestion = new HashMap<String, Double>(examQuestionList.size());
										//  ????????????list,?????????????????????????????????
										Map<String, Integer> sumTimeQuestion = new HashMap<String, Integer>(examQuestionList.size());
										if(unitId!=null){
											if(list1!=null){
												
												list.addAll(list1);
											}
										}else{
											for (UserQuestion uq : list1){
												if(uq.getTeamId().intValue()==pjExam.getTeamId().intValue())
												{
													list.add(uq);
												}
											}
										}
										// ????????????
										if (list != null && list.size() > 0) {
											for (UserQuestion uq : list) {
												Integer count = questionCount.get(uq.getQuestionUuid());
												Integer rightCount = rightQuestionCount.get(uq.getQuestionUuid());
												Integer emptyCont = empytQuestionCount.get(uq.getQuestionUuid());
												Double sum = sumQuestion.get(uq.getQuestionUuid());
												Integer sumCount=sumTimeQuestion.get(uq.getQuestionUuid());
												if(sumCount==null){
													if(uq.getAnswerTime()!=null){
														sumTimeQuestion.put(uq.getQuestionUuid(), uq.getAnswerTime());
													}else {
														sumTimeQuestion.put(uq.getQuestionUuid(), 0);
													}
												}else{
													int Tcount =0;
													if(uq.getAnswerTime()!=null){
														Tcount = uq.getAnswerTime();
													}
													sumTimeQuestion.put(uq.getQuestionUuid(), sumCount+Tcount);
												}
												if (count == null) {
													questionCount.put(uq.getQuestionUuid(), 1);
												} else {
													questionCount.put(uq.getQuestionUuid(), count + 1);
												}

												if (rightCount == null && !uq.getIsCorrect()) {

													rightQuestionCount.put(uq.getQuestionUuid(), 0);
												} else if (rightCount == null && uq.getIsCorrect()) {
													rightQuestionCount.put(uq.getQuestionUuid(), 1);
												} else if (rightCount != null && uq.getIsCorrect()) {
													rightQuestionCount.put(uq.getQuestionUuid(), rightCount + 1);
												}

												if (emptyCont == null) {
													empytQuestionCount.put(uq.getQuestionUuid(), 0);
												} else if ("[]".equals(uq.getAnswer())) {
													empytQuestionCount.put(uq.getQuestionUuid(), emptyCont + 1);
												}
												if (sum == null && uq.getScore() == null) {
													sumQuestion.put(uq.getQuestionUuid(), 0.0);
												} else if (sum == null && uq.getScore() != null) {
													sumQuestion.put(uq.getQuestionUuid(), uq.getScore());
												} else if (sum != null && uq.getScore() != null) {
													sumQuestion.put(uq.getQuestionUuid(), sum + uq.getScore());
												}

											}
											
											

										}
										// ????????????????????????examQuestionList
										for (ExamQuestion examQuestion : examQuestionList) {
											Integer answerCount = 0;
											Integer sumRightCount=gradeRightCoutQuestionMap.get(examQuestion.getQuestionUuid());
											Integer sumAnwerCount=gradeAnwerCoutQuestionMap.get(examQuestion.getQuestionUuid());
											answerCount = questionCount.get(examQuestion.getQuestionUuid());
											Integer sumTCount=sumTimeQuestion.get(examQuestion.getQuestionUuid());
											if (answerCount == null) {
												answerCount = 0;
											}

											if (answerCount != null) {

												examQuestion.setAnswerCount(answerCount);
											}
											if(sumTCount!=null){
												examQuestion.setTotalTime(sumTCount);
											}

											Integer emptyCount = 0;

											emptyCount = empytQuestionCount.get(examQuestion.getQuestionUuid());

											if (emptyCount != null) {
												examQuestion.setEmptyCount(emptyCount);
											}

											Integer rightAnswerCount = 0;
											rightAnswerCount = rightQuestionCount.get(examQuestion.getQuestionUuid());

											if (rightAnswerCount == null) {
												rightAnswerCount = 0;
											}
											examQuestion.setRightAnswerCount(rightAnswerCount);
		                                    if(sumRightCount==null){
		                                    	gradeRightCoutQuestionMap.put(examQuestion.getQuestionUuid(), rightAnswerCount);
											}else{
												gradeRightCoutQuestionMap.put(examQuestion.getQuestionUuid(), sumRightCount+rightAnswerCount);
											}
		                                    if(sumAnwerCount==null){
		                                    	gradeAnwerCoutQuestionMap.put(examQuestion.getQuestionUuid(), answerCount);
											}else{
												gradeAnwerCoutQuestionMap.put(examQuestion.getQuestionUuid(), sumAnwerCount+answerCount);
											}
											Double totalScore = 0.0;
											if (sumQuestion.get(examQuestion.getQuestionUuid()) != null) {
												totalScore = sumQuestion.get(examQuestion.getQuestionUuid());
											}
											examQuestion.setScore(totalScore);

											if (answerCount != null && answerCount != 0) {

												examQuestion.setAverageScore((float) (totalScore / answerCount));
											}

											Float fullScore = examQuestion.getFullScore() == null ? 0f : examQuestion.getFullScore();
											Float teamScoringRate = 0.0f;
											if (fullScore != 0 && answerCount != 0) {
												BigDecimal b11=new BigDecimal(totalScore+"");
												BigDecimal b21=new BigDecimal(answerCount * fullScore+"");
//												rRate = b1.divide(b2, 2,0).floatValue();
												teamScoringRate = b11.divide(b21, 4,BigDecimal.ROUND_HALF_DOWN).floatValue();
//												teamScoringRate = (float) (Float.parseFloat(totalScore.toString()) / (answerCount * fullScore));
											}
											if (teamScoringRate.isNaN()) {
												teamScoringRate = 0.0f;
											}
											// ---------------------------------------------
											if (teamCoutQuestionMap.get(examQuestion.getQuestionUuid()) != null) {
												Double dScore = (Double) teamCoutQuestionMap.get(examQuestion.getQuestionUuid()) + totalScore;
												teamCoutQuestionMap.put(examQuestion.getQuestionUuid(), dScore);
											} else {
												teamCoutQuestionMap.put(examQuestion.getQuestionUuid(), totalScore);
											}
											if (teamScoreQuestionMap.get(examQuestion.getQuestionUuid()) != null) {

												teamScoreQuestionMap.put(examQuestion.getQuestionUuid(), (Float) teamScoreQuestionMap.get(examQuestion.getQuestionUuid()) + answerCount * fullScore);
											} else {
												teamScoreQuestionMap.put(examQuestion.getQuestionUuid(), answerCount * fullScore);
											}
											TeamQuestionVo vo = new TeamQuestionVo();
											vo.setExamId(examQuestion.getExamId());
											vo.setQuestionUuid(examQuestion.getQuestionUuid());
//											vo.setTeamRight(teamScoringRate);
											float rRate = 0.0f;
											String questionType = examQuestion.getQuestionType();
											if (!questionType.contains("???") && !questionType.contains("???")) {
//												rRate = teamScoringRate;
												if (examQuestion.getAnswerCount() == null || examQuestion.getAnswerCount() == 0) {
												} else {
													BigDecimal b1=new BigDecimal(examQuestion.getRightAnswerCount()+"");
													BigDecimal b2=new BigDecimal(examQuestion.getAnswerCount()+"");
													rRate = b1.divide(b2, 4,BigDecimal.ROUND_HALF_DOWN).floatValue();
												}
											} else {
												rRate = teamScoringRate;
											}
											vo.setTeamRight(rRate);
											volist.add(vo);
											examQuestion.setTeamScoringRate(rRate);
											sumExamQuestionList.add(examQuestion);
										}
									}

								}
							}
						}
					}
					// ???????????????????????????????????????map
					if (volist != null && volist.size() > 0) {
						for (ExamQuestion eq : examQuestionList) {
							Map<Integer, Object> teamScoringRateMap = new HashMap<Integer, Object>();
							for (TeamQuestionVo vo : volist) {
								if (eq.getQuestionUuid().equals(vo.getQuestionUuid())) {
									teamScoringRateMap.put(vo.getExamId(), vo.getTeamRight());
								}
							}
							teamRateQuestionMap.put(eq.getQuestionUuid(), teamScoringRateMap);
						}
					}
					Map<String, Object> gradeRateMap = new HashMap<String, Object>();
					Map<String, Object> sortRateQuestionMap = new HashMap<String, Object>();
					// ????????????
					for (ExamQuestion eq : examQuestionList) {
						Double svm = 0.0;
						Double count = 0.0;
						float sum = 0.0f;
						count = (Double) teamCoutQuestionMap.get(eq.getQuestionUuid());
						sum = (Float) teamScoreQuestionMap.get(eq.getQuestionUuid());
						Integer anwerCount=gradeAnwerCoutQuestionMap.get(eq.getQuestionUuid());
						Integer rightCount=gradeRightCoutQuestionMap.get(eq.getQuestionUuid());
						if (!eq.getQuestionType().contains("???") &&! eq.getQuestionType().contains("???")) {
							if(anwerCount!=null&&anwerCount!=0&&rightCount!=null&&rightCount!=0){
								gradeRateMap.put(eq.getQuestionUuid(), (double)(float)rightCount / anwerCount);
							}else{
								gradeRateMap.put(eq.getQuestionUuid(), svm);
							}
						}else{
							Double rate=0.0;
							if (sum != 0) {
								rate=count / sum;
								java.text.DecimalFormat df = new   java.text.DecimalFormat("#.0000");
								rate=Double.valueOf(df.format(rate));
							}else{
								rate=0.0;
							}
							gradeRateMap.put(eq.getQuestionUuid(), rate);
						}
						Map<Integer, Object> tr = (Map<Integer, Object>) teamRateQuestionMap.get(eq.getQuestionUuid());
						ValueComparator bvc = new ValueComparator(tr);
						TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
						rank_map = sort(tr);
						sortRateQuestionMap.put(eq.getQuestionUuid(), rank_map);
					}
					// ?????????????????????
					for (ExamQuestion eq : sumExamQuestionList) {
						Double rate = 0.0;
						Integer rank = 0;
						if (gradeRateMap.get(eq.getQuestionUuid()) != null) {
							rate = (Double) gradeRateMap.get(eq.getQuestionUuid());
						}
						eq.setGradeScoringRate(Float.parseFloat(rate.toString()));
//						int scale = 4;// ????????????
//						BigDecimal bd = new BigDecimal(rate);
//						BigDecimal bd1 = new BigDecimal(eq.getTeamScoringRate());
//						int roundingMode = BigDecimal.ROUND_HALF_DOWN;
//						bd = bd.setScale(scale, roundingMode);
//						bd1 = bd1.setScale(scale, roundingMode);
//						eq.setGradeScoringRate(bd.floatValue());
//						eq.setTeamScoringRate(bd1.floatValue());
						Map<Integer, Integer> rank_map = new HashMap<Integer, Integer>();
						rank_map = (Map<Integer, Integer>) sortRateQuestionMap.get(eq.getQuestionUuid());
						if (rank_map != null) {
							if (rank_map.get(eq.getExamId()) != null) {
								rank = rank_map.get(eq.getExamId());
							}
						}
						if (eq.getAverageScore() == null) {
							eq.setAverageScore(0.0f);
						}
						if (eq.getTeamScoringRate().isNaN()) {
							eq.setTeamScoringRate(0.0f);
						}
						if (eq.getGradeScoringRate() == null) {
							eq.setGradeScoringRate(0.0f);
						}

						eq.setGradeRank(rank);
					}
					if (notSumExamQuestionList.size() > 0) {
						for (ExamQuestion eq : notSumExamQuestionList) {
							Double rate = 0.0;
							if (gradeRateMap.get(eq.getQuestionUuid()) != null) {
								rate = (Double) gradeRateMap.get(eq.getQuestionUuid());
							}
							eq.setGradeScoringRate(Float.parseFloat(rate.toString()));
						}
					}
				}
				String SQL = "UPDATE pj_exam_question eq SET eq.answer_count = ?,eq.empty_count = ?, eq.right_answer_count = ? , eq.score = ?, eq.average_score = ?, eq.team_scoring_rate = ?,eq.grade_rank=?,eq.grade_scoring_rate=?,eq.total_time=? where id = ?";
				JDBCHandle.jdbcUpdateExamQuestion(SQL, sumExamQuestionList);
				String SQL1 = "UPDATE pj_exam_question eq SET eq.grade_scoring_rate=? where id = ?";
				JDBCHandle.jdbcUpdateExamQuestionGradeScoreRate(SQL1, notSumExamQuestionList);

			}

			private void examStatHandle(List<PjExam> pjExamList, List<UserPaper> userPapers, Integer unitId, Integer paperId, Integer type) {
			//	long startTime = System.currentTimeMillis();
				PaPaper paper = paPaperService.findPaPaperById(paperId);
		        Map<Integer,Object> scoreMap=new HashMap<Integer, Object>();
				List<ExamStat> examStatList = new ArrayList<ExamStat>();
				if (paper != null)
					if (pjExamList != null && pjExamList.size() > 0) {
						for (PjExam pjExam : pjExamList) {
							if (userPapers != null && userPapers.size() > 0) {
								for (UserPaper userPaper : userPapers) {
									// ????????????????????????
									if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
										Integer teamId = userPaper.getTeamId();
										Integer examId = pjExam.getId();

										ExamStat examStat = this.examStatService.findExamStatByExamId(examId);

										UserPaperCondition userPaperCondition = new UserPaperCondition();

										userPaperCondition.setOwnerId(userPaper.getOwnerId());
										userPaperCondition.setTeamId(teamId);
										userPaperCondition.setType(type);
										userPaperCondition.setObjectId(unitId);
										// ??????????????????
										Long studentCount = userPaperService.count(userPaperCondition);

										if (studentCount != null) {
											examStat.setStudentCount(Integer.parseInt(studentCount + ""));
										}

										// ????????????
										Float totalScore = userPaperService.countUserPaperTeamTotalScore(userPaper.getOwnerId(), teamId, unitId, type);
										if (totalScore == null) {
											totalScore = 0F;
										}
										if (totalScore != null) {
											examStat.setTotalScore((float) totalScore);
										}

										// ???????????????
										if (studentCount != 0) {
											examStat.setAverageScore((float) totalScore / studentCount);// ?????????
										}

										List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examId);

										// ????????????
										Integer gradeRank = null;

										// ????????????
										Integer highCount = 0;

										// ????????????
										Integer lowCount = 0;

										// ????????????
										Integer passCount = 0;

										Float fullScore = paper.getScore();
										examStat.setFullScore(fullScore); // ????????????
										Float highScore = (float) (fullScore * 0.9);
										examStat.setHighScore(highScore); // ?????? / ?????? ??????

										Float lowScore = (float) (fullScore * 0.75);
										examStat.setLowScore(lowScore); // ?????? / ?????? ??????

										Float passScore = (float) (fullScore * 0.6);
										examStat.setPassScore(passScore); // ????????????

										List<Float> list = new ArrayList<Float>();
										// ?????????????????? ???????????????????????????
										for (ExamStudent es : examStudentList) {
											if (es.getScore().floatValue() != -1) {
											
												list.add(es.getScore());
												if (es.getScore() >= highScore) {
													highCount = highCount + 1;
												}
												if (es.getScore() >= lowScore) {
													lowCount = lowCount + 1;
												}
												if (es.getScore() >= passScore) {
													passCount = passCount + 1;
												}
												
											}else{
												list.add(0.0f);
											}
										}
										
										
										Float[] floatArray = new Float[list.size()];
										for (int i = 0; i < list.size(); i++) {
											floatArray[i] = list.get(i);
										}

										float highestScore = Collections.max(list);
										float lowestScore = Collections.min(list);
										
										if (floatArray != null && floatArray.length > 0 && studentCount != 0) {
											examStat.setSdScore(getAdScore(floatArray, (float) (totalScore / studentCount)));// ?????????
											examStat.setMadValue(getMadValue(floatArray, (float) (totalScore / studentCount)));// ??????
											examStat.setMovValue(floatArray[floatArray.length - 1] - floatArray[0]);// ??????
										}
										examStat.setHighCount(highCount); // ????????????
										examStat.setLowCount(lowCount); // ????????????
										examStat.setPassCount(passCount); // ????????????

										//Float highestScore = examStudentService.findExamStudentHighestScoreByExamId(examId);
										examStat.setHighestScore(highestScore); // ???????????????
										//Float lowestScore = examStudentService.findExamStudentLowestScoreByExamId(examId);
										if(lowestScore==-1){
											lowestScore=0f;
										}
										examStat.setLowestScore(lowestScore); // ???????????????
										if (examStat.getAverageScore() == null) {
											examStat.setAverageScore(0.0f);
										}
										if (examStat.getSdScore() == null) {
											examStat.setSdScore(0.0f);
										}
										if (examStat.getMadValue() == null) {
											examStat.setMadValue(0.0f);
										}
										if (examStat.getMovValue() == null) {
											examStat.setMovValue(0.0f);
										}
										scoreMap.put(examStat.getExamId(), examStat.getTotalScore());
										examStatList.add(examStat);
										// this.examStatService.modify(examStat);
									}
								}
							}
						}
						ValueComparator bvc = new ValueComparator(scoreMap);
						TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
						rank_map = sort(scoreMap);
						for(ExamStat es:examStatList){
							if(rank_map.get(es.getExamId())!=null){
								es.setGradeRank(rank_map.get(es.getExamId()));
							}else{
								es.setGradeRank(0);
							}
						}
						String SQL = "UPDATE pj_exam_stat es" + " SET es.student_count = ?, es.total_score = ?, es.average_score = ?, es.full_score = ?, es.high_score = ?, es.low_score = ?, es.pass_score = ?, es.sd_score = ?, es.mad_value = ?, es.mov_value = ?, es.high_count = ?, es.low_count = ?, es.pass_count = ?, es.highest_score = ?, es.lowest_score =?, es.grade_rank=?" + " WHERE" + "	id = ?";

						JDBCHandle.jdbcUpdateExamStat(SQL, examStatList);

						// long endTime = System.currentTimeMillis();
						// System.out.println("examStatHandle????????????????????? "
						// + ((endTime - startTime)) + "??????");
					}
			}


			/**
			 * @function ???????????????
			 * @param array
			 * @return
			 * @date 2016???1???26???
			 */
			private Float getAdScore(Float[] array, Float avg) {
				double sum = 0f;
				for (int i = 0; i < array.length; i++) {
					sum += Math.sqrt(((double) array[i] - avg) * (array[i] - avg));
				}
				// return (float)(sum / (array.length - 1));
				return (float) (sum / (array.length));
			}

			/**
			 * @function ????????????
			 * @param array
			 * @param avg
			 * @return
			 * @date 2016???1???26???
			 */
			private Float getMadValue(Float[] array, Float avg) {
				Float date = 0f;
				for (int i = 0; i < array.length; i++) {
					date += Math.abs(array[i] - avg);
				}
				return date / array.length;
			}
			private boolean isExist(Integer id, List<UserPaper> uplist) {
				boolean flag = true;
				for (UserPaper up : uplist) {
					if (up.getTeamId().intValue() == id) {
						flag = false;
						break;
					}
				}
				return flag;
			}
			private TreeMap<Integer, Integer> sort(Map<Integer, Object> tr) {
				ValueComparator bvc = new ValueComparator(tr);
				TreeMap<Integer, Object> sorted_map = new TreeMap<Integer, Object>(bvc);
				sorted_map.putAll(tr);
				tr.clear();
				tr.putAll(sorted_map);
				TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>();
				for (Integer key : sorted_map.keySet()) {
					if (rank_map.size() == 0) {
						rank_map.put(key, 1);
					} else {
						if(tr.get(key) instanceof Float){
							Float a= (Float) tr.get(key);
							Float b= (Float) tr.get((Integer) rank_map.ceilingKey(rank_map.size() - 1));
							if (a.toString().equals(b.toString())) {
								rank_map.put(key, rank_map.ceilingEntry(rank_map.size() - 1).getValue());
							} else {
								rank_map.put(key, rank_map.size()+1);
							}
						}else{
							Double a=(Double) tr.get(key);
							Double b= (Double) tr.get((Integer) rank_map.ceilingKey(rank_map.size() - 1));
//							System.out.println(a+"---------------------------------"+b);
							if (a.toString().equals(b.toString())) {
								rank_map.put(key, rank_map.ceilingEntry(rank_map.size() - 1).getValue());
							} else {
								rank_map.put(key, rank_map.size()+1);
							}
						}
					}
				}
				return rank_map;
			}
}
