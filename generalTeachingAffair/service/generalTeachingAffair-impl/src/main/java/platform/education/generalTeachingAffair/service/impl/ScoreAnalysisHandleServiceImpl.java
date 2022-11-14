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
 * @功能描述: 成绩分析导入相关接口实现类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年2月11日下午12:27:21
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
				//3. 更新pjExamStudent表的score字段
				ScoreAnalysisUtil scoreAnalysisUtil = ScoreAnalysisUtil.getInstance();
				//获取到排名
				list = scoreAnalysisUtil.getRankByScore(list);
				//更新班级平均分/总分
				//批量更新每个学生的得分以及排名
				examStudentDao.batchUpdateExamStudentScore(list.toArray());
				//更新三率
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
		//查询年级三率
		ExamWorksSubject examWorksSubject =  examWorksSubjectDao.findUnique(examWorksId, gradeId, subjectCode);
		if(examWorksSubject != null) {
			float highScore = examWorksSubject.getHighScore();
    		float lowScore = examWorksSubject.getLowScore();
    		float passScore = examWorksSubject.getPassScore();
    		if(list == null) {
            		list = new ArrayList<ScoreSortVo>();
    				ScoreSortVo mp = null;
    				//3. 更新pjExamStudent表的score字段
    				List<ExamStudent> examStudentList = examStudentDao.findExamStudentsByExamId(examId);
    				if(examStudentList != null && examStudentList.size() > 0) {
    					for(ExamStudent examStudent:examStudentList) {
    						//for(ScoreAnalysisDataVo scoreAnalysisDataVo:scoreAnalysisData) {
    							//找到对应的学生更新得分
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
    		
    		//更新三率
    		updateExamStat(scoreAnalysisUtil,examId,list,highScore,lowScore,passScore);
    		//查找当前考务信息
    		ExamWorks examWorks = this.examWorksDao.findById(examWorksId);
    		//获取班级排名
    		list = scoreAnalysisUtil.getRankByScore(list);
    	
    		examStudentDao.batchUpdateExamStudentScore(list.toArray());
    		ExamWorksGrade examWorksGrade = this.examWorksGradeDao.findUnique(examWorksId, gradeId, null);
    		//更新年级排名
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
    			//批量更新单科年级排名
    			examStudentDao.batchUpdateExamStudentGradeRankByScore(listTeamRank.toArray());
    		}
    		
    		//更新pj_exam_works_team 的科目导入完成数
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
    		//更新全科的得分，班级，年级排名
    		List<Map<String,Object>> allSubjectStudentTotalScore = this.scoreAnalysisDao.findAllSubjectTotalScoreByExamWorksId(examWorksId, teamId);
    		if(examStatMajorStudentList != null && examStatMajorStudentList.size()>0 && allSubjectStudentTotalScore != null && allSubjectStudentTotalScore.size() > 0) {
    			for(int i=0;i<allSubjectStudentTotalScore.size();i++) {
    				for(ExamStatMajorStudent examStatMajorStudent :examStatMajorStudentList) {
    					if(allSubjectStudentTotalScore.get(i).get("student_id") != null) {
    						examStatMajorMap = new HashMap<String,Object>();
    						studentId =Integer.parseInt(allSubjectStudentTotalScore.get(i).get("student_id")+"");
    						if(studentId - examStatMajorStudent.getStudentId() == 0) {
    							totalScore = Float.parseFloat(allSubjectStudentTotalScore.get(i).get("totalScore")+"");
    							//更新总分
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
    			
    			
    			//批量更新某个学生全科总分
        		if(examStatMajorList != null && examStatMajorList.size() > 0) {
        			this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTotalScore(examStatMajorList.toArray());
        		}
        		
        		
        		listTeamRank = scoreAnalysisUtil.getRankByScore(listTeamRank);
        		//批量更新某个学生全科总分班级排名    			listTeamRank = scoreAnalysisUtil.getRankByScore(listTeamRank);
	    		this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTeamRank(listTeamRank.toArray());
    			
	    		//批量更新某个学生全科总分年级排名
        		
	    		ExamWorksGrade examWorksGrade_ = this.examWorksGradeDao.findUnique(examWorksId, gradeId, null);
        		if(examWorksGrade != null ) {
        			//更新年级排名
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
        				//批量更新某个学生全科年级排名
        				this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentGradeRank(listGradeRankList.toArray());
        			}
        		}
    		}
    		
    		
    		//上一次考务信息
    		ExamWorks frontExamWorks = null;
    		if(examWorks != null) {
    			//查找当前考务的开始考试时间为了查找上一次考务做名次对比
    			String examDateBegin = DateUtil.dateToStr(examWorks.getExamDateBegin());
    			Integer schoolId = examWorks.getSchoolId();
    			//上一次考务信息
    			frontExamWorks = this.examWorksDao.findFrontExamWorksByExamDate(examDateBegin,schoolId);
    			//上一次考务对应的EXAMID
    			if(frontExamWorks != null) {
    				
    				//当前
    				ExamWorksGrade examWorksGrade_ = this.examWorksGradeDao.findUnique(examWorks.getId(), gradeId, null);
    				//上次
    				ExamWorksGrade frontExamWorksGrade = this.examWorksGradeDao.findUnique(frontExamWorks.getId(), gradeId, null);
    				//单科班级进退步
    				//teamRankChange(ExamWorks examWorks,ExamWorks frontExamWorks,Integer teamId,String subjectCode,ExamWorksGrade examWorksGrade,ExamWorksGrade frontExamWorksGrade)
    				teamRankChange(examWorks,frontExamWorks,teamId,subjectCode,examWorksGrade_,frontExamWorksGrade);
    				examStatMajorRankChange(examWorks,frontExamWorks, teamId, examWorksGrade_, frontExamWorksGrade);
    				
    		}
    		
    		}
		}
		return false;
	}
	
	/**
	 * 单科班级，年级进退步情况
	 * @param examWorks 当前考试
	 * @param frontExamWorks 上一次考试
	 * @param teamId 班级ID
	 * @param subjectCode 科目
	 * @param gradeId 年级ID
	 */
	private void teamRankChange(ExamWorks examWorks,ExamWorks frontExamWorks,Integer teamId,String subjectCode,ExamWorksGrade examWorksGrade,ExamWorksGrade frontExamWorksGrade) {
		
		//更新单科的排名进退步情况
		//当前考务
		ExamWorksTeamSubject examWorksTeamSubject =  this.examWorksTeamSubjectDao.findUnique(examWorks.getId(), teamId, subjectCode);
		ExamWorksTeamSubject frontExamWorksTeamSubject =  this.examWorksTeamSubjectDao.findUnique(frontExamWorks.getId(), teamId, subjectCode);
		if(frontExamWorksTeamSubject != null && examWorksTeamSubject != null) {
			Integer frontExamId = frontExamWorksTeamSubject.getExamId();
			Integer examId = examWorksTeamSubject.getExamId();
			
			//当前考务学生信息
			List<ExamStudent> examStudentList = this.examStudentDao.findExamStudentsByExamId(examId);
			//查找上一次考务的学生信息
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
			
			//更新单科年级进退步
			
			
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
			
			
		}	//查看当前学生信息
			
	}
	
	
	/**
	 * 全科进退步情况
	 * @param examWorks 当前考试
	 * @param frontExamWorks 上一次考试
	 * @param teamId 班级ID
	 * @param examWorksGrade 当前考试年级信息
	 * @param frontExamWorksGrade 上一次考试年级信息
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
		
		//更新全科的进退步情况
			
		//更新年级排名
		List<ExamStatMajorStudent> gradeExamStatMajorStudentList = this.examStatMajorStudentDao.findExamStatMajorCount(examWorksId, examWorksGrade.getJointExamCode(),null,1);
		List<ExamStatMajorStudent> frontGradeExamStatMajorStudentList  = this.examStatMajorStudentDao.findExamStatMajorCount(frontExamWorks.getId(), frontExamWorksGrade.getJointExamCode(), null,1);
		if(gradeExamStatMajorStudentList != null && gradeExamStatMajorStudentList.size() > 0 && frontGradeExamStatMajorStudentList != null && frontGradeExamStatMajorStudentList.size() > 0) {
			//当前考务全科总分信息
			for(ExamStatMajorStudent examStatMajorStudent :gradeExamStatMajorStudentList) {
				//上一次考务全科总分信息
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
	 * 更新 pj_exam_stat 三率
	 * @param scoreAnalysisUtil
	 * @param examId 
	 * @param list 要更新的内容
	 * @param highScore 高分
	 * @param lowScore 优秀分
	 * @param passScore 及格分
	 */
	private void updateExamStat(ScoreAnalysisUtil scoreAnalysisUtil,Integer examId,List<ScoreSortVo> list ,Float highScore,Float lowScore,Float passScore) {
		
		//更新班级平均分/总分
		Map<String,Object> examStatTatalScore = scoreAnalysisUtil.getAvergeScoreByScore(list, highScore,  lowScore,  passScore);
		//批量更新每个学生的得分以及排名
		ExamStat examStat = this.examStatDao.findExamStatByExamId(examId);
		if(examStat != null && examStatTatalScore != null && examStatTatalScore.size() > 0) {
			//班级平均分
			Object avergeScore = examStatTatalScore.get("avergeScore");
			if(avergeScore != null) {
				examStat.setAverageScore(Float.parseFloat(avergeScore+""));
			}
			// 班级总分
			Object totalScore = examStatTatalScore.get("totalScore");
			if(totalScore != null) {
				examStat.setTotalScore(Float.parseFloat(totalScore+""));
			}
			//最高最低分
			Object highestScore = examStatTatalScore.get("highestScore");
			if(highestScore != null) {
				
				examStat.setHighestScore(Float.parseFloat(highestScore+""));
			}
			//最低分
			Object lowestScore = examStatTatalScore.get("lowestScore");
			if(lowestScore != null) {
				
				examStat.setLowestScore(Float.parseFloat(lowestScore+""));
			}
			//高分人数
			Object highCount = examStatTatalScore.get("highCount");
			if(highCount != null) {
				
				examStat.setHighCount(Integer.parseInt(highCount+""));
			}
			//良好人数
			Object lowCount = examStatTatalScore.get("lowCount");
			if(lowCount != null) {
				
				examStat.setLowCount(Integer.parseInt(lowCount+""));
			}
			//及格人数
			Object passCount = examStatTatalScore.get("passCount");
			if(passCount != null) {
				
				examStat.setPassCount(Integer.parseInt(passCount+""));
			}
			this.examStatDao.update(examStat);
		}
	}
	
	
	
	/**
	 * 更新考试时间
	 * @param examWorksId 考务ID
	 * @param teamId  班级ID
	 * @param examTime 考试时间
	 * @param subjectCode 考试科目
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
    		//2. 更新pjExam 表的考试时间
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
