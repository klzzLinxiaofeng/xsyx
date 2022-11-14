package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.vo.*;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import java.util.Map;

public interface ExamQuestionDao extends GenericDao<ExamQuestion, java.lang.Integer> {

	List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Page page, Order order);
	
	ExamQuestion findById(Integer id);
	
	Long count(ExamQuestionCondition examQuestionCondition);
	/**
	 *
	* @Title: findExamQuestionByExamIdAndTeamId
	* @author pantq
	* @Description: 根据班级ID和examId查询 本班的做题情况
	* @param teamId
	* @param examId
	* @return    设定文件
	* @return List<ExamQuestionVo>    返回类型
	* @throws
	 */
	List<ExamQuestionVo> findExamQuestionByExamIdAndTeamId(Integer teamId,Integer examId,String jointExamCode,String questionUuId);

	void updateExamQuestionAnswerCount(Integer teamId,Integer ownerId,Integer type,Boolean isCorrect,String answer,Integer examId,Integer unitId);
	
	void updateExamQuestionScore(Integer teamId,Integer ownerId,Integer type,Integer examId,Integer unitId);
	
	List<ExamQuestionVo> findExamQuestionByJointExamCodeAndQuestionUuid(String jointExamCode,String questionUuId);

	List<ScoringAverageVo> findScoringAverageByExamId(Integer examId);

	List<KnolwdgeDiffVo> findKnowledgeDiffByExamId(Integer examId);

	List<ScoringAverageVo> findKnowledgeCognitionByExamId(Integer examId);

	List<ScoringAverageVo> findCountQuestiontTypeByExamId(Integer examId);
	void updateExamQuestionAverageScore(Integer examId);

	List<ScoringAverageVo> findTeamKnoledgeScoreByExamId(Integer examId);

	List<ExamErrorVo> findExamErrorByExamId(Integer examId);
	
	void updateExamQuestiontotalTime(Integer teamId,Integer ownerId,Integer type,Integer examId,Integer unitId);
	
	
void batchUpdateExamQuestionAnswerCount(Object[] list);
	
	void batchUpdateExamQuestionScore(Object[] list);
	
	void batchUpdateExamQuestiontotalTime(Object[] list);
	
	void batchUpdateExamQuestionAverageScore(Object[] list);
	
	void batchUpdateExamQuestionEmptyAnswerCount(Object[] list);
	void batchUpdateExamQuestionCorrectAnswerCount(Object[] list);
	
	void updateExamQuestionEmptyCount(Integer answerCount,String questionUuid,Integer examId);
	
	void updateExamQuestionCorrectCount(Integer answerCount,String questionUuid,Integer examId);
	
	void updateExamQuestionEmptyAndCorrectAnswerCount(Integer answerCount,String questionUuid,Integer examId);
	
	void updateExamQuestionTotalScore(Float answerCount,String questionUuid,Integer examId);
	
	void updateExamQuestionTotalAnswerTime(Integer answerCount,String questionUuid,Integer examId);

	
	void updateExamQuestionEmptyCount1(Object[] examQuestionEmptyAnswerCountList);
	void updateExamQuestionCorrectCount1(Object[] examQuestionCorrectAnswerCountList);
	void updateExamQuestionEmptyAndCorrectAnswerCount1(Object[] examQuestionAnswerCountList);
	void updateExamQuestionTotalScore1(Object[] examQuestionScoreList);
	void updateExamQuestionAverageScore1(Object[] examQuestionAverageScoreList);
	void updateExamQuestionTotalAnswerTime1(Object[] examQuestiontotalTimeList);
	
	void updateExamQuestionGradeScoringRate(String examId);
	public List<ExamQuestionWrongVo> findExamQuestionWrongbyExamId(
			Integer[] examIds, Integer type,float rate);

	public List<ExamQuestionWrongVo> findExamQuestionWrongForStudent(
			Integer gradeId, Integer teamId, String subjectCode, Integer type,float rate, Order order);
	
	void batchUpateExamQuestionTeamScoringRate(ExamQuestion examQuestion);
	
	void batchUpdateExamQuestionGradeRank(Object[] list);
	
	List<ExamQuestion> findExamQuestionByJointExamCode(String jointExamCode,String questionUuid);
	
	
    void deleteByExamId(Integer examId);
    
    List<ExamQuestion> findCountQuestiontByExamIdAndQuestionUuids(Integer examId,Object []questionUuids);
	
    
    void createBatch(ExamQuestion[] eqlist);

    List<ExamQuestionVo> findExamQuestionBycodeAndQuestionUuid(String code,String questionUuid,String examType);
    
	 List<ExamQuestionWrongVo> findExamQuestionWrongbyExamIdAndQuestionUuids(
			 Integer[] examQuestionIds);
}
