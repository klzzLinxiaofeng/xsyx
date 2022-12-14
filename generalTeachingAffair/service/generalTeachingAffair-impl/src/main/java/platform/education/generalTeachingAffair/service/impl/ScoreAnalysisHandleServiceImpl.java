package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.education.generalTeachingAffair.dao.ExamStatDao;
import platform.education.generalTeachingAffair.dao.ExamStatMajorStudentDao;
import platform.education.generalTeachingAffair.dao.ExamStudentDao;
import platform.education.generalTeachingAffair.dao.ExamWorksDao;
import platform.education.generalTeachingAffair.dao.ExamWorksGradeDao;
import platform.education.generalTeachingAffair.dao.ExamWorksSubjectDao;
import platform.education.generalTeachingAffair.dao.ExamWorksTeamDao;
import platform.education.generalTeachingAffair.dao.ExamWorksTeamSubjectDao;
import platform.education.generalTeachingAffair.dao.PjExamDao;
import platform.education.generalTeachingAffair.dao.ScoreAnalysisDao;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.model.ExamWorksGrade;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.model.ExamWorksTeam;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.service.ScoreAnalysisHandleService;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.utils.ScoreAnalysisUtil;
import platform.education.generalTeachingAffair.vo.ExamStatMajorStudentCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreAnalysisDataVo;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreSortVo;

/**
 * 
 * @????????????: ???????????????????????????????????????
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @????????????:2018???2???11?????????12:27:21
 */
public class ScoreAnalysisHandleServiceImpl implements ScoreAnalysisHandleService {

	private ExamWorksTeamSubjectDao examWorksTeamSubjectDao;
	private PjExamDao pjExamDao;
	private ExamStudentDao examStudentDao;
	private ExamStatDao examStatDao;
	private ExamWorksSubjectDao examWorksSubjectDao;
	private ExamStatMajorStudentDao examStatMajorStudentDao;
	private ExamWorksGradeDao examWorksGradeDao;
	private ExamWorksTeamDao examWorksTeamDao ;
	private ExamWorksDao examWorksDao ;
	private ScoreAnalysisDao scoreAnalysisDao;
	
	public void setScoreAnalysisDao(ScoreAnalysisDao scoreAnalysisDao) {
		this.scoreAnalysisDao = scoreAnalysisDao;
	}
	
	public void setExamWorksDao(ExamWorksDao examWorksDao) {
		this.examWorksDao = examWorksDao;
	}
	
	public void setExamWorksGradeDao(ExamWorksGradeDao examWorksGradeDao) {
		this.examWorksGradeDao = examWorksGradeDao;
	}
	
	public void setExamWorksTeamDao(ExamWorksTeamDao examWorksTeamDao) {
		this.examWorksTeamDao = examWorksTeamDao;
	}
	
	public void setExamStatMajorStudentDao(ExamStatMajorStudentDao examStatMajorStudentDao) {
		this.examStatMajorStudentDao = examStatMajorStudentDao;
	}
	
	public void setExamWorksTeamSubjectDao(ExamWorksTeamSubjectDao examWorksTeamSubjectDao) {
		this.examWorksTeamSubjectDao = examWorksTeamSubjectDao;
	}

	public void setPjExamDao(PjExamDao pjExamDao) {
		this.pjExamDao = pjExamDao;
	}

	public void setExamStudentDao(ExamStudentDao examStudentDao) {
		this.examStudentDao = examStudentDao;
	}

	public void setExamStatDao(ExamStatDao examStatDao) {
		this.examStatDao = examStatDao;
	}

	public void setExamWorksSubjectDao(ExamWorksSubjectDao examWorksSubjectDao) {
		this.examWorksSubjectDao = examWorksSubjectDao;
	}

	@Override
	public Boolean importTeamExamScore(Integer examWorksId, Integer teamId, String examTime, Float fullScore,
			Float highScore, Float lowScore, Float passScore, Integer schoolId, Integer gradeId, String subjectCode,
			List<ScoreAnalysisDataVo> scoreAnalysisData,List<ScoreSortVo> list,Integer examId) {
		boolean flag = false;
		try {
				//3. ??????pjExamStudent??????score??????
				ScoreAnalysisUtil scoreAnalysisUtil = ScoreAnalysisUtil.getInstance();
				//???????????????
				list = scoreAnalysisUtil.getRankByScore(list);
				//?????????????????????/??????
				//?????????????????????????????????????????????
				examStudentDao.batchUpdateExamStudentScore(list.toArray());
				//????????????
        		updateExamStat(scoreAnalysisUtil,examId,list,highScore,lowScore,passScore);
				Date nowDate = new Date();
				ExamWorksSubject examWorksSubject = examWorksSubjectDao.findUnique(examWorksId, gradeId, subjectCode);
					if (examWorksSubject != null) {
						Integer examWorksSubjectId = examWorksSubject.getId();
						examWorksSubject = new ExamWorksSubject();
						examWorksSubject.setId(examWorksSubjectId);
						examWorksSubject.setFullScore(fullScore);
						examWorksSubject.setHighScore(highScore);
						examWorksSubject.setLowScore(lowScore);
						examWorksSubject.setPassScore(passScore);
						examWorksSubjectDao.update(examWorksSubject);
					} else {
						examWorksSubject = new ExamWorksSubject();
						examWorksSubject.setExamWorksId(examWorksId);
						examWorksSubject.setSchoolId(schoolId);
						examWorksSubject.setGradeId(gradeId);
						examWorksSubject.setSubjectCode(subjectCode);
						examWorksSubject.setStatNeeded(false);
						examWorksSubject.setFullScore(fullScore);
						examWorksSubject.setHighScore(highScore);
						examWorksSubject.setLowScore(lowScore);
						examWorksSubject.setPassScore(passScore);
						examWorksSubject.setIsDelteted(false);
						examWorksSubject.setCreateDate(nowDate);
						examWorksSubject.setModifyDate(nowDate);
						examWorksSubjectDao.create(examWorksSubject);
					}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return flag;
	}

	@Override
	public Boolean importGeneralExamScore(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode,
			String examTime, List<ScoreAnalysisDataVo> scoreAnalysisData,List<ScoreSortVo> list,Integer examId) {
		
		ScoreAnalysisUtil scoreAnalysisUtil = ScoreAnalysisUtil.getInstance();
		//??????????????????
		ExamWorksSubject examWorksSubject =  examWorksSubjectDao.findUnique(examWorksId, gradeId, subjectCode);
		if(examWorksSubject != null) {
			float highScore = examWorksSubject.getHighScore();
    		float lowScore = examWorksSubject.getLowScore();
    		float passScore = examWorksSubject.getPassScore();
    		if(list == null) {
            		list = new ArrayList<ScoreSortVo>();
    				ScoreSortVo mp = null;
    				//3. ??????pjExamStudent??????score??????
    				List<ExamStudent> examStudentList = examStudentDao.findExamStudentsByExamId(examId);
    				if(examStudentList != null && examStudentList.size() > 0) {
    					for(ExamStudent examStudent:examStudentList) {
    						//for(ScoreAnalysisDataVo scoreAnalysisDataVo:scoreAnalysisData) {
    							//?????????????????????????????????
    								mp = new ScoreSortVo();
    								mp.setId(examStudent.getId());
    								mp.setScore(examStudent.getScore());
    								list.add(mp);
    							//}
    						//}
    					}
    				}else {
    					return false;
    				}
    		}
    		
    		//????????????
    		updateExamStat(scoreAnalysisUtil,examId,list,highScore,lowScore,passScore);
    		//????????????????????????
    		ExamWorks examWorks = this.examWorksDao.findById(examWorksId);
    		//??????????????????
    		list = scoreAnalysisUtil.getRankByScore(list);
    	
    		examStudentDao.batchUpdateExamStudentScore(list.toArray());
    		ExamWorksGrade examWorksGrade = this.examWorksGradeDao.findUnique(examWorksId, gradeId, null);
    		//??????????????????
    		List<ExamStudent> examStudentGradeRankList = this.examStudentDao.findExamStudentByJointExamCode(examWorksGrade.getJointExamCode(),subjectCode);
    		if(examStudentGradeRankList != null && examStudentGradeRankList.size() > 0) {
    			List<ScoreSortVo> listTeamRank = new ArrayList<ScoreSortVo>();
    			ScoreSortVo teamRank = null;                    
    			for(ExamStudent examStudent :examStudentGradeRankList) {
					teamRank = new ScoreSortVo();
					teamRank.setId(examStudent.getId());
					teamRank.setScore(examStudent.getScore());
					listTeamRank.add(teamRank);
    			}
    			
    			listTeamRank = scoreAnalysisUtil.getRankByScore(listTeamRank);
    			//??????????????????????????????
    			examStudentDao.batchUpdateExamStudentGradeRankByScore(listTeamRank.toArray());
    		}
    		
    		//??????pj_exam_works_team ????????????????????????
    		ExamWorksTeam examWorksTeam = this.examWorksTeamDao.findUnique(examWorksId, teamId);
    		if(examWorksTeam != null) {
    			Integer finishedSubjectCount = examWorksTeam.getFinishedSubjectCount();
    			if(finishedSubjectCount != null) {
    				finishedSubjectCount = finishedSubjectCount+1;
    			}else {
    				finishedSubjectCount = 1;
    			}
    			examWorksTeam.setFinishedSubjectCount(finishedSubjectCount);
    			this.examWorksTeamDao.update(examWorksTeam);
    		}
    		
    		
    		List<Map<String,Object>> examStatMajorList = new ArrayList<Map<String,Object>>();
    		Map<String,Object> examStatMajorMap = null;
    		List<ExamStatMajorStudent> examStatMajorStudentList = this.examStatMajorStudentDao.findExamStatMajorCount(examWorksId, null, teamId,null);
    		Integer studentId = null;
    		float totalScore = 0;
    		List<ScoreSortVo> listTeamRank = new ArrayList<ScoreSortVo>();
    		ScoreSortVo teamRank = null;
    		//?????????????????????????????????????????????
    		List<Map<String,Object>> allSubjectStudentTotalScore = this.scoreAnalysisDao.findAllSubjectTotalScoreByExamWorksId(examWorksId, teamId);
    		if(examStatMajorStudentList != null && examStatMajorStudentList.size()>0 && allSubjectStudentTotalScore != null && allSubjectStudentTotalScore.size() > 0) {
    			for(int i=0;i<allSubjectStudentTotalScore.size();i++) {
    				for(ExamStatMajorStudent examStatMajorStudent :examStatMajorStudentList) {
    					if(allSubjectStudentTotalScore.get(i).get("student_id") != null) {
    						examStatMajorMap = new HashMap<String,Object>();
    						studentId =Integer.parseInt(allSubjectStudentTotalScore.get(i).get("student_id")+"");
    						if(studentId - examStatMajorStudent.getStudentId() == 0) {
    							totalScore = Float.parseFloat(allSubjectStudentTotalScore.get(i).get("totalScore")+"");
    							//????????????
    							examStatMajorStudent.setTotalScore(totalScore);
    							examStatMajorMap.put("id", examStatMajorStudent.getId());
    							examStatMajorMap.put("totalScore", totalScore);
    							examStatMajorList.add(examStatMajorMap);
    	        				teamRank = new ScoreSortVo();
    	        				teamRank.setId(examStatMajorStudent.getId());
    	        				teamRank.setScore(totalScore);
    	        				listTeamRank.add(teamRank);
    	        				
    						}
    						
    					}
    				}
    				
    			}
    			
    			
    			//????????????????????????????????????
        		if(examStatMajorList != null && examStatMajorList.size() > 0) {
        			this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTotalScore(examStatMajorList.toArray());
        		}
        		
        		
        		listTeamRank = scoreAnalysisUtil.getRankByScore(listTeamRank);
        		//????????????????????????????????????????????????    			listTeamRank = scoreAnalysisUtil.getRankByScore(listTeamRank);
	    		this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTeamRank(listTeamRank.toArray());
    			
	    		//????????????????????????????????????????????????
        		
	    		ExamWorksGrade examWorksGrade_ = this.examWorksGradeDao.findUnique(examWorksId, gradeId, null);
        		if(examWorksGrade != null ) {
        			//??????????????????
        			ExamStatMajorStudentCondition ess = new ExamStatMajorStudentCondition();
        			ess.setExamWorksId(examWorksId);
        			ess.setJointExamCode(examWorksGrade_.getJointExamCode());
        			List<ExamStatMajorStudent> essList = this.examStatMajorStudentDao.findExamStatMajorStudentByCondition(ess,null,null);
        			if(essList != null && essList.size() > 0) {
        				List<ScoreSortVo> listGradeRankList = new ArrayList<ScoreSortVo>();
                		ScoreSortVo listGradeRank = null;
            			for(ExamStatMajorStudent examStatMajorStudent :essList) {
            				if(examStatMajorStudent.getTotalScore() != null) {
            					listGradeRank = new ScoreSortVo();
            					listGradeRank.setId(examStatMajorStudent.getId());
            					listGradeRank.setScore(examStatMajorStudent.getTotalScore());
            					listGradeRankList.add(listGradeRank);
            				}
            			}
            			listGradeRankList = scoreAnalysisUtil.getRankByScore(listGradeRankList);
        				//??????????????????????????????????????????
        				this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentGradeRank(listGradeRankList.toArray());
        			}
        		}
    		}
    		
    		
    		//?????????????????????
    		ExamWorks frontExamWorks = null;
    		if(examWorks != null) {
    			//?????????????????????????????????????????????????????????????????????????????????
    			String examDateBegin = DateUtil.dateToStr(examWorks.getExamDateBegin());
    			Integer schoolId = examWorks.getSchoolId();
    			//?????????????????????
    			frontExamWorks = this.examWorksDao.findFrontExamWorksByExamDate(examDateBegin,schoolId);
    			//????????????????????????EXAMID
    			if(frontExamWorks != null) {
    				
    				//??????
    				ExamWorksGrade examWorksGrade_ = this.examWorksGradeDao.findUnique(examWorks.getId(), gradeId, null);
    				//??????
    				ExamWorksGrade frontExamWorksGrade = this.examWorksGradeDao.findUnique(frontExamWorks.getId(), gradeId, null);
    				//?????????????????????
    				//teamRankChange(ExamWorks examWorks,ExamWorks frontExamWorks,Integer teamId,String subjectCode,ExamWorksGrade examWorksGrade,ExamWorksGrade frontExamWorksGrade)
    				teamRankChange(examWorks,frontExamWorks,teamId,subjectCode,examWorksGrade_,frontExamWorksGrade);
    				examStatMajorRankChange(examWorks,frontExamWorks, teamId, examWorksGrade_, frontExamWorksGrade);
    				
    		}
    		
    		}
		}
		return false;
	}
	
	/**
	 * ????????????????????????????????????
	 * @param examWorks ????????????
	 * @param frontExamWorks ???????????????
	 * @param teamId ??????ID
	 * @param subjectCode ??????
	 * @param gradeId ??????ID
	 */
	private void teamRankChange(ExamWorks examWorks,ExamWorks frontExamWorks,Integer teamId,String subjectCode,ExamWorksGrade examWorksGrade,ExamWorksGrade frontExamWorksGrade) {
		
		//????????????????????????????????????
		//????????????
		ExamWorksTeamSubject examWorksTeamSubject =  this.examWorksTeamSubjectDao.findUnique(examWorks.getId(), teamId, subjectCode);
		ExamWorksTeamSubject frontExamWorksTeamSubject =  this.examWorksTeamSubjectDao.findUnique(frontExamWorks.getId(), teamId, subjectCode);
		if(frontExamWorksTeamSubject != null && examWorksTeamSubject != null) {
			Integer frontExamId = frontExamWorksTeamSubject.getExamId();
			Integer examId = examWorksTeamSubject.getExamId();
			
			//????????????????????????
			List<ExamStudent> examStudentList = this.examStudentDao.findExamStudentsByExamId(examId);
			//????????????????????????????????????
			List<ExamStudent> frontExamStudentList = this.examStudentDao.findExamStudentsByExamId(frontExamId);
			if(examStudentList != null && examStudentList.size() >0 && frontExamStudentList != null && frontExamStudentList.size() > 0) {
				for(ExamStudent examStudent:examStudentList) {
					for(ExamStudent frontExamStudent:frontExamStudentList) {
						if(examStudent.getUserId().intValue() == frontExamStudent.getUserId().intValue()) {
							if(examStudent.getTeamRank() != null && frontExamStudent.getTeamRank() != null) {
								examStudent.setTeamRankChange(frontExamStudent.getTeamRank() - examStudent.getTeamRank());
							}else {
								examStudent.setTeamRankChange(0);
							}
							
							this.examStudentDao.update(examStudent);
							break;
							
						}
						
					}
					
				}
				
			}
			
			//???????????????????????????
			
			
			List<ExamStudent> examGradeStudentList = this.examStudentDao.findExamStudentByJointExamCode(examWorksGrade.getJointExamCode(), subjectCode);
			List<ExamStudent> frontExamGradeStudentList = this.examStudentDao.findExamStudentByJointExamCode(frontExamWorksGrade.getJointExamCode(), subjectCode);
			if(examGradeStudentList != null && examGradeStudentList.size() >0 && frontExamGradeStudentList != null && frontExamGradeStudentList.size() > 0) {
				for(ExamStudent examStudent:examGradeStudentList) {
					for(ExamStudent frontExamStudent:frontExamGradeStudentList) {
						if(examStudent.getUserId().intValue() == frontExamStudent.getUserId().intValue()) {
							if(examStudent.getGradeRank() != null && frontExamStudent.getGradeRank() != null) {
								examStudent.setGradeRankChange(frontExamStudent.getGradeRank() - examStudent.getGradeRank());
							}else {
								examStudent.setGradeRankChange(0);
							}
							
							this.examStudentDao.update(examStudent);
							break;
							
						}
						
					}
					
				}
				
				
			}
			
			
		}	//????????????????????????
			
	}
	
	
	/**
	 * ?????????????????????
	 * @param examWorks ????????????
	 * @param frontExamWorks ???????????????
	 * @param teamId ??????ID
	 * @param examWorksGrade ????????????????????????
	 * @param frontExamWorksGrade ???????????????????????????
	 */
	private void examStatMajorRankChange(ExamWorks examWorks,ExamWorks frontExamWorks,Integer teamId,ExamWorksGrade examWorksGrade,ExamWorksGrade frontExamWorksGrade) {
		Integer examWorksId = examWorks.getId();
		List<ExamStatMajorStudent> examStatMajorStudentList = this.examStatMajorStudentDao.findExamStatMajorCount(examWorksId, null, teamId,1);
		List<ExamStatMajorStudent> frontExamStatMajorStudentList  = this.examStatMajorStudentDao.findExamStatMajorCount(frontExamWorks.getId(), null, teamId,1);
		if(examStatMajorStudentList != null && examStatMajorStudentList.size() > 0 && frontExamStatMajorStudentList != null && frontExamStatMajorStudentList.size() > 0) {
			for(ExamStatMajorStudent examStatMajorStudent :examStatMajorStudentList) {
				for(ExamStatMajorStudent frontExamStatMajorStudent :frontExamStatMajorStudentList) {
					if(examStatMajorStudent.getStudentId() - frontExamStatMajorStudent.getStudentId() == 0) {
						if(frontExamStatMajorStudent.getTeamRank() != null && examStatMajorStudent.getTeamRank() != null) {
							examStatMajorStudent.setTeamRankChange(frontExamStatMajorStudent.getTeamRank() - examStatMajorStudent.getTeamRank());
						}else {
							examStatMajorStudent.setTeamRankChange(0);
						}
						
						this.examStatMajorStudentDao.update(examStatMajorStudent);
						break;
					}
				}
				
			}
		
		//??????????????????????????????
			
		//??????????????????
		List<ExamStatMajorStudent> gradeExamStatMajorStudentList = this.examStatMajorStudentDao.findExamStatMajorCount(examWorksId, examWorksGrade.getJointExamCode(),null,1);
		List<ExamStatMajorStudent> frontGradeExamStatMajorStudentList  = this.examStatMajorStudentDao.findExamStatMajorCount(frontExamWorks.getId(), frontExamWorksGrade.getJointExamCode(), null,1);
		if(gradeExamStatMajorStudentList != null && gradeExamStatMajorStudentList.size() > 0 && frontGradeExamStatMajorStudentList != null && frontGradeExamStatMajorStudentList.size() > 0) {
			//??????????????????????????????
			for(ExamStatMajorStudent examStatMajorStudent :gradeExamStatMajorStudentList) {
				//?????????????????????????????????
				for(ExamStatMajorStudent frontExamStatMajorStudent :frontGradeExamStatMajorStudentList) {
					if(examStatMajorStudent.getStudentId() - frontExamStatMajorStudent.getStudentId() == 0) {
						if(frontExamStatMajorStudent.getGradeRank() != null && examStatMajorStudent.getGradeRank() != null) {
							examStatMajorStudent.setGradeRankChange(frontExamStatMajorStudent.getGradeRank() - examStatMajorStudent.getGradeRank());
						}else {
							examStatMajorStudent.setGradeRankChange(0);
						}
						
						this.examStatMajorStudentDao.update(examStatMajorStudent);
						break;
					}
				}
				
			}
			
		}
		
	}
		
	}	
	
	
	/**
	 * ?????? pj_exam_stat ??????
	 * @param scoreAnalysisUtil
	 * @param examId 
	 * @param list ??????????????????
	 * @param highScore ??????
	 * @param lowScore ?????????
	 * @param passScore ?????????
	 */
	private void updateExamStat(ScoreAnalysisUtil scoreAnalysisUtil,Integer examId,List<ScoreSortVo> list ,Float highScore,Float lowScore,Float passScore) {
		
		//?????????????????????/??????
		Map<String,Object> examStatTatalScore = scoreAnalysisUtil.getAvergeScoreByScore(list, highScore,  lowScore,  passScore);
		//?????????????????????????????????????????????
		ExamStat examStat = this.examStatDao.findExamStatByExamId(examId);
		if(examStat != null && examStatTatalScore != null && examStatTatalScore.size() > 0) {
			//???????????????
			Object avergeScore = examStatTatalScore.get("avergeScore");
			if(avergeScore != null) {
				examStat.setAverageScore(Float.parseFloat(avergeScore+""));
			}
			// ????????????
			Object totalScore = examStatTatalScore.get("totalScore");
			if(totalScore != null) {
				examStat.setTotalScore(Float.parseFloat(totalScore+""));
			}
			//???????????????
			Object highestScore = examStatTatalScore.get("highestScore");
			if(highestScore != null) {
				
				examStat.setHighestScore(Float.parseFloat(highestScore+""));
			}
			//?????????
			Object lowestScore = examStatTatalScore.get("lowestScore");
			if(lowestScore != null) {
				
				examStat.setLowestScore(Float.parseFloat(lowestScore+""));
			}
			//????????????
			Object highCount = examStatTatalScore.get("highCount");
			if(highCount != null) {
				
				examStat.setHighCount(Integer.parseInt(highCount+""));
			}
			//????????????
			Object lowCount = examStatTatalScore.get("lowCount");
			if(lowCount != null) {
				
				examStat.setLowCount(Integer.parseInt(lowCount+""));
			}
			//????????????
			Object passCount = examStatTatalScore.get("passCount");
			if(passCount != null) {
				
				examStat.setPassCount(Integer.parseInt(passCount+""));
			}
			this.examStatDao.update(examStat);
		}
	}
	
	
	
	/**
	 * ??????????????????
	 * @param examWorksId ??????ID
	 * @param teamId  ??????ID
	 * @param examTime ????????????
	 * @param subjectCode ????????????
	 * @return
	 */
	@Override
	public ExamWorksTeamSubject updateExamDate(Integer examWorksId,Integer teamId,String examTime,String subjectCode) {
		ExamWorksTeamSubject examWorksTeamSubject = null;
		ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition = new ExamWorksTeamSubjectCondition();
    	examWorksTeamSubjectCondition.setExamWorksId(examWorksId);
    	examWorksTeamSubjectCondition.setTeamId(teamId);
    	examWorksTeamSubjectCondition.setIsDeleted(false);
    	examWorksTeamSubjectCondition.setSubjectCode(subjectCode);
    	List<ExamWorksTeamSubject> ExamWorksTeamSubjectList = examWorksTeamSubjectDao.findExamWorksTeamSubjectByCondition(examWorksTeamSubjectCondition,null,null);
    	if(ExamWorksTeamSubjectList != null && ExamWorksTeamSubjectList.size() >0) {
    		examWorksTeamSubject = ExamWorksTeamSubjectList.get(0);
    		Integer examId = examWorksTeamSubject.getExamId();
    		//2. ??????pjExam ??????????????????
    		PjExam pjExam = pjExamDao.findById(examId);
    		if(pjExam != null) {
    			
    			pjExam.setExamDate(DateUtil.strToDate(examTime,"yyyy-MM-dd HH:mm:ss"));
    			pjExamDao.update(pjExam);
    		}
    	
    	}
		return examWorksTeamSubject;
	}

	@Override
	public List<Map<String, Object>> findSchoolTermByUserId(Integer schoolId, Integer userId) {
		
		return scoreAnalysisDao.findSchoolTermByUserId(schoolId, userId);
	}

	@Override
	public List<Map<String, Object>> findAllExamWorksByTeam(Integer teamId, String schoolYear, String termCode,
			Integer isJointExam, Integer schoolId) {
		return scoreAnalysisDao.findAllExamWorksByTeam(teamId, schoolYear, termCode, isJointExam, schoolId);
	}

	@Override
	public Map<String, Object> findTeamExamWorksByUserId(Integer examWorksId, Integer schoolId, Integer userId) {
		return scoreAnalysisDao.findTeamExamWorksByUserId(examWorksId, schoolId, userId);
	}

	@Override
	public Integer findExamStudentCount(Integer examId, String jointExamCode) {
		return scoreAnalysisDao.findExamStudentCount(examId, jointExamCode);
	}

	@Override
	public List<Map<String, Object>> findFractionalTrendByUserId(Integer userId, Integer teamId) {
		return scoreAnalysisDao.findFractionalTrendByUserId(userId, teamId);
	}

	@Override
	public List<Map<Integer,Object>> findTeamRanksByExamId(Integer examId) {
		return scoreAnalysisDao.findTeamRanksByExamId(examId);
	}
	
	@Override
	public List<Map<String, Object>> findExamWorksByUserId(Integer examWorksId, Integer userId,String subjectCode, Integer isPublished) {
		return scoreAnalysisDao.findExamWorksByUserId(examWorksId, userId,subjectCode, isPublished);
	}

	@Override
	public List<Map<String, Object>> findUserExamTeamSubjectScore(Integer examWorksId, Integer userId) {
		return scoreAnalysisDao.findUserExamTeamSubjectScore(examWorksId, userId);
	}

	@Override
	public Float findGradeTotalScore(String jointExamCode, String subjectCode) {
		return scoreAnalysisDao.findGradeTotalScore(jointExamCode, subjectCode);
	}

	@Override
	public List<Map<String, Object>> findUserExamWorksBySubjectCodeAndUserId(Integer isJointExam, String subjectCode,
			Integer teamId, Integer userId, Integer isPublished) {
		return scoreAnalysisDao.findUserExamWorksBySubjectCodeAndUserId(isJointExam, subjectCode, teamId, userId, isPublished);
	}

	
}
