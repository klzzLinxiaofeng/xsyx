package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.ExamQuestionDao;
import platform.education.generalTeachingAffair.dao.PjExamDao;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.service.ExamQuestionService;
import platform.education.generalTeachingAffair.vo.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamQuestionServiceImpl implements ExamQuestionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamQuestionDao examQuestionDao;
	
	private PjExamDao pjExamDao;

	public void setExamQuestionDao(ExamQuestionDao examQuestionDao) {
		this.examQuestionDao = examQuestionDao;
	}
	
	/**
	 * @param pjExamDao the pjExamDao to set
	 */
	public void setPjExamDao(PjExamDao pjExamDao) {
		this.pjExamDao = pjExamDao;
	}

	
	
	@Override
	public ExamQuestion findExamQuestionById(Integer id) {
		try {
			return examQuestionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamQuestion add(ExamQuestion examQuestion) {
		if(examQuestion == null) {
    		return null;
    	}
    	Date createDate = examQuestion.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examQuestion.setCreateDate(createDate);
    	examQuestion.setModifyDate(createDate);
		return examQuestionDao.create(examQuestion);
	}

	@Override
	public ExamQuestion modify(ExamQuestion examQuestion) {
		if(examQuestion == null) {
    		return null;
    	}
    	Date modify = examQuestion.getModifyDate();
    	examQuestion.setModifyDate(modify != null ? modify : new Date());
		return examQuestionDao.update(examQuestion);
	}
	
	@Override
	public void remove(ExamQuestion examQuestion) {
		try {
			examQuestionDao.delete(examQuestion);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examQuestion.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Page page, Order order) {
		return examQuestionDao.findExamQuestionByCondition(examQuestionCondition, page, order);
	}
	
	@Override
	public List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition) {
		return examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
	}
	
	@Override
	public List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Page page) {
		return examQuestionDao.findExamQuestionByCondition(examQuestionCondition, page, null);
	}
	
	@Override
	public List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Order order) {
		return examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examQuestionDao.count(null);
	}

	@Override
	public Long count(ExamQuestionCondition examQuestionCondition) {
		return this.examQuestionDao.count(examQuestionCondition);
	}

	/* (非 Javadoc) 
	* <p>Title: findExamQuestionByExamIdAndTeamId</p> 
	* <p>Description: </p> 
	* @param teamId
	* @param examId
	* @return 
	* @see platform.education.generalTeachingAffair.service.ExamQuestionService#findExamQuestionByExamIdAndTeamId(java.lang.Integer, java.lang.Integer) 
	*/
	@Override
	public List<ExamQuestionVo> findExamQuestionByExamIdAndTeamId(Integer teamId, Integer examId,String questionUuId) {
		
		List<ExamQuestionVo> list = null;
		if(teamId != null){ //统计本班
			list = this.examQuestionDao.findExamQuestionByExamIdAndTeamId(teamId,examId,null,questionUuId);
		}else{//统计jointExamCode相同的班级
			PjExam exam = pjExamDao.findById(examId);
			list = this.examQuestionDao.findExamQuestionByExamIdAndTeamId(teamId,examId,exam.getJointExamCode(),questionUuId);
			
		}
		return list;
	}

	@Override
	public void examQuestionAnswerCount(Integer teamId, Integer ownerId, Integer type, Boolean isCorrect,String answer,
			Integer examId,Integer unitId) {
		this.examQuestionDao.updateExamQuestionAnswerCount(teamId, ownerId, type, isCorrect,answer, examId,unitId);
	}

	@Override
	public void examQuestionScore(Integer teamId, Integer ownerId, Integer type, Integer examId,Integer unitId) {
		
		this.examQuestionDao.updateExamQuestionScore(teamId, ownerId, type, examId,unitId);
		
	}

	@Override
	public List<ExamQuestionVo> findExamQuestionByExamIdAndQuestionUuid(Integer examId, String questionUuId) {
		List<ExamQuestionVo> list = null;
			PjExam exam = pjExamDao.findById(examId);
			if(exam != null){
				list = this.examQuestionDao.findExamQuestionByJointExamCodeAndQuestionUuid(exam.getJointExamCode(), questionUuId);
				}
			
		return list;
	}

	@Override
	public void InitExamQuestionData(List<ExamQuestion> examQuestionList) {
		if(examQuestionList != null && examQuestionList.size()> 0){
			ExamQuestion[] eqlist1=new ExamQuestion[1000];
			int i=0;
			int j=0;
			 long startTime = System.currentTimeMillis();
			for(ExamQuestion examQuestion : examQuestionList){
//				examQuestionDao.create(examQuestion);
				eqlist1[i]=examQuestion;
				if(i==999){
					examQuestionDao.createBatch(eqlist1);
					eqlist1=new ExamQuestion[1000];
					i=0;
					j++;
				}else{
					i++;
				}
			}
			ExamQuestion[] eqlist=new ExamQuestion[examQuestionList.size()-(j*1000)];
			for(int z=0;z<eqlist.length;z++){
				eqlist[z]=eqlist1[z];
			}
			if(eqlist1[0]!=null){
				examQuestionDao.createBatch(eqlist);
			}
			 long endTime = System.currentTimeMillis();
//			 long startTime = System.currentTimeMillis();
//			examQuestionDao.createBatch(eqlist);
//			 long endTime = System.currentTimeMillis();
			 System.out.println(endTime-startTime+"ms-------------------------------------------------------------");
		}
	}

	@Override
	public List<ScoringAverageVo> findScoringAverageByExamId(Integer examId) {
		return	examQuestionDao.findScoringAverageByExamId(examId);
	}

	@Override
	public List<KnolwdgeDiffVo> findKnowledgeDiffByExamId(Integer examId){
		return examQuestionDao.findKnowledgeDiffByExamId(examId);
	}

	@Override
	public List<ScoringAverageVo> findKnowledgeCognitionByExamId(Integer examId){
		return examQuestionDao.findKnowledgeCognitionByExamId(examId);
	}

	@Override
	public List<ScoringAverageVo> findCountQuestiontTypeByExamId(Integer examId){
		return examQuestionDao.findCountQuestiontTypeByExamId(examId);
	}

	@Override
	public void examQuestionAverageScore(Integer examId) {
		
		examQuestionDao.updateExamQuestionAverageScore(examId);
		
	}

	@Override
	public List<ScoringAverageVo> findTeamKnoledgeScoreByExamId(Integer examId){
		return examQuestionDao.findTeamKnoledgeScoreByExamId(examId);
	}

	@Override
	public List<ExamErrorVo> findExamErrorByExamId(Integer examId){
		return examQuestionDao.findExamErrorByExamId(examId);
	}

	@Override
	public void examQuestiontotalTime(Integer teamId, Integer ownerId, Integer type, Integer examId,Integer unitId) {
			examQuestionDao.updateExamQuestiontotalTime(teamId, ownerId, type, examId,unitId);
	}

	@Override
	public void batchUpdateExamQuestionAnswerCount(Object [] list) {
		examQuestionDao.batchUpdateExamQuestionAnswerCount(list);
		
	}

	@Override
	public void batchUpdateExamQuestionScore(Object [] list) {
		examQuestionDao.batchUpdateExamQuestionScore(list);
		
	}

	@Override
	public void batchUpdateExamQuestiontotalTime(Object [] list) {
		examQuestionDao.batchUpdateExamQuestiontotalTime(list);
		
	}

	@Override
	public void batchUpdateExamQuestionAverageScore(Object [] list) {
		examQuestionDao.batchUpdateExamQuestionAverageScore(list);
		
	}

	@Override
	public void batchUpdateExamQuestionEmptyAnswerCount(Object [] list) {
		examQuestionDao.batchUpdateExamQuestionEmptyAnswerCount(list);
		
	}

	@Override
	public void batchUpdateExamQuestionCorrectAnswerCount(Object [] list) {
		examQuestionDao.batchUpdateExamQuestionCorrectAnswerCount(list);
		
	}

	@Override
	public void batchUpdateExamQuestion( List<Map<String, Object>> examQuestionEmptyAnswerCountList,
			 List<Map<String, Object>> examQuestionCorrectAnswerCountList,
			 List<Map<String, Object>> examQuestionAnswerCountList,  List<Map<String, Object>> examQuestionScoreList,
			List<Map<String, Object>> examQuestionAverageScoreList,
			 List<Map<String, Object>> examQuestiontotalTimeList,List<List<ExamQuestion>> teamScoringRateList) {
	
		long startTime = System.currentTimeMillis();
		if(examQuestionEmptyAnswerCountList != null && examQuestionEmptyAnswerCountList.size() > 0){
			for(int i=0,len=examQuestionEmptyAnswerCountList.size();i<len;i++){
				Map<String,Object> map = examQuestionEmptyAnswerCountList.get(i);
				if(map != null && map.size() > 0){
					
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						
						ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
						examQuestionCondition.setExamId(examId);
						examQuestionCondition.setQuestionUuid(questionUuid);
						List<ExamQuestion> examQuestionList = examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
						if(examQuestionList != null && examQuestionList.size() > 0){
							ExamQuestion examQuestion = examQuestionList.get(0);
							examQuestion.setEmptyCount(answerCount);
							examQuestionDao.update(examQuestion);
							
						}
						//examQuestionDao.updateExamQuestionEmptyCount(answerCount, questionUuid, examId);
					}
				}
				
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("examQuestionEmptyAnswerCountList程序运行时间： " + ((endTime - startTime)) + "毫秒");
		
		
		if(examQuestionCorrectAnswerCountList != null && examQuestionCorrectAnswerCountList.size() > 0){
			
			long startTime1 = System.currentTimeMillis();
			for(int i=0,len=examQuestionCorrectAnswerCountList.size();i<len;i++){
				Map<String,Object> map = examQuestionCorrectAnswerCountList.get(i);
				//examQuestionDao.updateExamQuestionAnswerCount(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, true, null, Integer.valueOf(map.get("examId")+""));
				
				if(map != null && map.size() > 0){
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						//examQuestionDao.updateExamQuestionCorrectCount(answerCount, questionUuid, examId);
						
						ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
						examQuestionCondition.setExamId(examId);
						examQuestionCondition.setQuestionUuid(questionUuid);
						List<ExamQuestion> examQuestionList = examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
						if(examQuestionList != null && examQuestionList.size() > 0){
							ExamQuestion examQuestion = examQuestionList.get(0);
							examQuestion.setRightAnswerCount(answerCount);
							examQuestionDao.update(examQuestion);
							
						}
						
					}
				}
				
			}
			long endTime1 = System.currentTimeMillis();
			System.out.println("examQuestionCorrectAnswerCountList程序运行时间： " + ((endTime1 - startTime1)) + "毫秒");
		}
		
		
		
		if(examQuestionAnswerCountList != null && examQuestionAnswerCountList.size() >0){
			
			for(int i=0,len=examQuestionAnswerCountList.size();i<len;i++){
				
				Map<String,Object> map = examQuestionAnswerCountList.get(i);
				//examQuestionDao.updateExamQuestionAnswerCount(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, null, null, Integer.valueOf(map.get("examId")+""));
			
				if(map != null && map.size() > 0){
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						
						ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
						examQuestionCondition.setExamId(examId);
						examQuestionCondition.setQuestionUuid(questionUuid);
						List<ExamQuestion> examQuestionList = examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
						if(examQuestionList != null && examQuestionList.size() > 0){
							ExamQuestion examQuestion = examQuestionList.get(0);
							examQuestion.setAnswerCount(answerCount);
							examQuestionDao.update(examQuestion);
						}
						
						//examQuestionDao.updateExamQuestionEmptyAndCorrectAnswerCount(answerCount, questionUuid, examId);
					}
				}
			}
		}
			
		
		if(examQuestionScoreList != null && examQuestionScoreList.size() >0){
			
			for(int i=0,len=examQuestionScoreList.size();i<len;i++){
				
				Map<String,Object> map = examQuestionScoreList.get(i);
				
				if(map != null && map.size() > 0){
					if(map.get("totalScore") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Double totalScore = Double.parseDouble(map.get("totalScore") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
						examQuestionCondition.setExamId(examId);
						examQuestionCondition.setQuestionUuid(questionUuid);
						List<ExamQuestion> examQuestionList = examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
						if(examQuestionList != null && examQuestionList.size() > 0){
							ExamQuestion examQuestion = examQuestionList.get(0);
							examQuestion.setScore(totalScore);
							Integer answerCount = examQuestion.getAnswerCount();
							if(answerCount != null && answerCount != 0){
								float averageScore =(float) (totalScore / answerCount);
								examQuestion.setAverageScore(averageScore);
							}
							examQuestionDao.update(examQuestion);
							
						}
						
						
						//examQuestionDao.updateExamQuestionTotalScore(totalScore, questionUuid, examId);
					}
				}
			}
		}
		
		/*if(examQuestionAverageScoreList != null && examQuestionAverageScoreList.size() > 0){
			
			for(int i=0,len=examQuestionAverageScoreList.size();i<len;i++){
				Map<String,Object> map = examQuestionAverageScoreList.get(i);
				examQuestionDao.updateExamQuestionAverageScore(Integer.valueOf(map.get("examId")+""));
			}
		}*/
		
	
		if(examQuestiontotalTimeList != null && examQuestiontotalTimeList.size() > 0){
			
			for(int i=0,len=examQuestiontotalTimeList.size();i<len;i++){
				
				Map<String,Object> map = examQuestiontotalTimeList.get(i);
				//examQuestionDao.updateExamQuestiontotalTime(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, Integer.valueOf(map.get("examId")+""));
				
				if(map != null && map.size() > 0){
					if(map.get("totalTime") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						Integer totalTime = Integer.parseInt(map.get("totalTime") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
						examQuestionCondition.setExamId(examId);
						examQuestionCondition.setQuestionUuid(questionUuid);
						List<ExamQuestion> examQuestionList = examQuestionDao.findExamQuestionByCondition(examQuestionCondition, null, null);
						if(examQuestionList != null && examQuestionList.size() > 0){
							ExamQuestion examQuestion = examQuestionList.get(0);
							examQuestion.setTotalTime(totalTime);
							examQuestionDao.update(examQuestion);
							
						}
						
					}
				}
				
			}
		}
		
		if(teamScoringRateList != null && teamScoringRateList.size() > 0){
			long startTime6 = System.currentTimeMillis();
			for(int i=0,len=teamScoringRateList.size();i<len;i++){
		
				List<ExamQuestion> examQuestionList = teamScoringRateList.get(i);
				if(examQuestionList != null && examQuestionList.size() > 0){
					
					for(ExamQuestion examQuestion :examQuestionList){ 
						Integer answerCount = examQuestion.getAnswerCount();
					if(answerCount != null && answerCount != 0){
						
						Float teamScoringRate = (float)(examQuestion.getScore() / (answerCount * examQuestion.getFullScore()));
						examQuestion.setTeamScoringRate(teamScoringRate);
					//	examQuestion.setGradeScoringRate(getGradeScoringRate(examQuestion.getExamId()));
						examQuestionDao.update(examQuestion);
					}
					
					}
				}
			}
			long endTime6 = System.currentTimeMillis();
			System.out.println("teamScoringRateList程序运行时间： " + ((endTime6 - startTime6)) + "毫秒");
			
		}
		
	}

	@Override
	public void batchUpdateExamQuestionGradeScoringRate(Integer examId) {
		
		PjExam exam = this.pjExamDao.findById(examId);
		if(exam != null){
			String jointExamCode = exam.getJointExamCode();
			examQuestionDao.updateExamQuestionGradeScoringRate(jointExamCode);
		}
		
	}

	@Override
	public void batchUpateExamQuestionTeamScoringRate(List<ExamQuestion> examQuestionList) {
		if(examQuestionList != null && examQuestionList.size() > 0) {
			for(ExamQuestion examQuestion :examQuestionList) {
				
				examQuestionDao.batchUpateExamQuestionTeamScoringRate(examQuestion);
			}
		}
	}

	@Override
	public void batchUpdateExamQuestionGradeRank(Object[] list) {
		
		examQuestionDao.batchUpdateExamQuestionGradeRank(list);
		
	}

	@Override
	public List<ExamQuestion> findExamQuestionByJointExamCode(
			String jointExamCode, String questionUuid) {
		return examQuestionDao.findExamQuestionByJointExamCode(jointExamCode, questionUuid);
	}

	@Override
	public void deleteByExamId(Integer examId) {
		 examQuestionDao.deleteByExamId(examId);
	}

	@Override
	public Map<String, ExamQuestion> findCountQuestiontByExamIdAndQuestionUuids(Integer examId,
			Object[] questionUuids) {
		Map<String, ExamQuestion> map = null;
		if(questionUuids != null && questionUuids.length > 0) {
			int len = questionUuids.length;
			map = new HashMap<String, ExamQuestion>(len);
			List<ExamQuestion> examQuestionList = this.examQuestionDao.findCountQuestiontByExamIdAndQuestionUuids(examId,questionUuids);
			
			if(examQuestionList != null && examQuestionList.size() > 0) {
				for(ExamQuestion examQuestion :examQuestionList) {
					map.put(examQuestion.getQuestionUuid(), examQuestion);	
				}
			}
		}
		return map;
	}

	@Override
	public void createBatch(ExamQuestion[] eqlist) {
		if(eqlist.length>0){
			
			examQuestionDao.createBatch(eqlist);
		}
		
	}

	@Override
	public List<ExamQuestionVo> findExamQuestionByCodeAndQuestionUuid(
			String code, String questionUuid, String examType) {
		
		   return examQuestionDao.findExamQuestionBycodeAndQuestionUuid(code, questionUuid, examType);
	}

	@Override
	public List<ExamQuestionWrongVo> findExamQuestionWrongbyExamIdAndQuestionUuids(
			Integer[] examQuestionIds) {
		
		return examQuestionDao.findExamQuestionWrongbyExamIdAndQuestionUuids(examQuestionIds);
	}

	/*@Override
	public void batchUpdateExamQuestion(List<Map<String, Object>> examQuestionEmptyAnswerCountList,
			List<Map<String, Object>> examQuestionCorrectAnswerCountList,
			List<Map<String, Object>> examQuestionAnswerCountList, List<Map<String, Object>> examQuestionScoreList,
			List<Map<String, Object>> examQuestionAverageScoreList,
			List<Map<String, Object>> examQuestiontotalTimeList) {
	//	examQuestionDao.updateExamQuestionEmptyCount1(examQuestionEmptyAnswerCountList.toArray());
		long startTime = System.currentTimeMillis();
		
		
		
		examQuestionDao.updateExamQuestionCorrectCount1(examQuestionCorrectAnswerCountList.toArray());
		long endTime = System.currentTimeMillis();
		System.out.println("updateExamQuestionCorrectCount1程序运行时间： " + ((endTime - startTime)) + "毫秒");
		long startTime1 = System.currentTimeMillis();
		examQuestionDao.updateExamQuestionEmptyAndCorrectAnswerCount1(examQuestionAnswerCountList.toArray());
		long endTime1 = System.currentTimeMillis();
		System.out.println("updateExamQuestionEmptyAndCorrectAnswerCount1程序运行时间： " + ((endTime1 - startTime1)) + "毫秒");
		long startTime2 = System.currentTimeMillis();
		examQuestionDao.updateExamQuestionTotalScore1(examQuestionScoreList.toArray());
		long endTime2 = System.currentTimeMillis();
		System.out.println("updateExamQuestionTotalScore1程序运行时间： " + ((endTime2 - startTime2)) + "毫秒");
	//	examQuestionDao.updateExamQuestionAverageScore1(examQuestionAverageScoreList);
		
		
		long startTime3 = System.currentTimeMillis();
		examQuestionDao.updateExamQuestionTotalAnswerTime1(examQuestiontotalTimeList.toArray());
		long endTime3 = System.currentTimeMillis();
		System.out.println("updateExamQuestionTotalAnswerTime1程序运行时间： " + ((endTime3 - startTime3)) + "毫秒");
		
		if(examQuestionEmptyAnswerCountList != null && examQuestionEmptyAnswerCountList.size() > 0){
			for(int i=0,len=examQuestionEmptyAnswerCountList.size();i<len;i++){
				Map<String,Object> map = examQuestionEmptyAnswerCountList.get(i);
				if(map != null && map.size() > 0){
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						examQuestionDao.updateExamQuestionEmptyCount(answerCount, questionUuid, examId);
					}
				}
				
			}
		}
		
		if(examQuestionCorrectAnswerCountList != null && examQuestionCorrectAnswerCountList.size() > 0){
			
			for(int i=0,len=examQuestionCorrectAnswerCountList.size();i<len;i++){
				long startTime = System.currentTimeMillis();
				Map<String,Object> map = examQuestionCorrectAnswerCountList.get(i);
				//examQuestionDao.updateExamQuestionAnswerCount(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, true, null, Integer.valueOf(map.get("examId")+""));
				
				if(map != null && map.size() > 0){
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						examQuestionDao.updateExamQuestionCorrectCount(answerCount, questionUuid, examId);
						long endTime = System.currentTimeMillis();
						System.out.println("examQuestionHandle程序运行时间： " + ((endTime - startTime)) + "毫秒");
					}
				}
				
			}
		}

		if(examQuestionAnswerCountList != null && examQuestionAnswerCountList.size() >0){
			
			for(int i=0,len=examQuestionAnswerCountList.size();i<len;i++){
				
				Map<String,Object> map = examQuestionAnswerCountList.get(i);
				//examQuestionDao.updateExamQuestionAnswerCount(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, null, null, Integer.valueOf(map.get("examId")+""));
			
				if(map != null && map.size() > 0){
					if(map.get("answerCount") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						examQuestionDao.updateExamQuestionEmptyAndCorrectAnswerCount(answerCount, questionUuid, examId);
					}
				}
			}
		}
		
		if(examQuestionScoreList != null && examQuestionScoreList.size() >0){
			
			for(int i=0,len=examQuestionScoreList.size();i<len;i++){
				
				Map<String,Object> map = examQuestionScoreList.get(i);
				
				if(map != null && map.size() > 0){
					if(map.get("totalScore") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Float totalScore = Float.parseFloat(map.get("totalScore") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						examQuestionDao.updateExamQuestionTotalScore(totalScore, questionUuid, examId);
					}
				}
			}
		}
		
		if(examQuestionAverageScoreList != null && examQuestionAverageScoreList.size() > 0){
			
			for(int i=0,len=examQuestionAverageScoreList.size();i<len;i++){
				
				Map<String,Object> map = examQuestionAverageScoreList.get(i);
				examQuestionDao.updateExamQuestionAverageScore(Integer.valueOf(map.get("examId")+""));
			}
		}
		
		if(examQuestiontotalTimeList != null && examQuestiontotalTimeList.size() > 0){
			
			for(int i=0,len=examQuestiontotalTimeList.size();i<len;i++){
				
				Map<String,Object> map = examQuestiontotalTimeList.get(i);
				//examQuestionDao.updateExamQuestiontotalTime(Integer.valueOf(map.get("teamId")+""), Integer.valueOf(map.get("ownerId")+""), 2, Integer.valueOf(map.get("examId")+""));
				
				if(map != null && map.size() > 0){
					if(map.get("totalTime") != null && map.get("questionUuid")!= null && map.get("examId") != null){
						
						Integer totalTime = Integer.parseInt(map.get("totalTime") + "");
						String questionUuid = map.get("questionUuid") + "";
						Integer examId = Integer.parseInt(map.get("examId") + "");
						examQuestionDao.updateExamQuestionTotalAnswerTime(totalTime, questionUuid, examId);
					}
				}
				
			}
		}
		
	}*/

}

	