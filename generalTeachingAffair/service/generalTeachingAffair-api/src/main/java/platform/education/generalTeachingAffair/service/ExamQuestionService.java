package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.vo.*;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface ExamQuestionService {
    ExamQuestion findExamQuestionById(Integer id);
	   
	ExamQuestion add(ExamQuestion examQuestion);
	   
	ExamQuestion modify(ExamQuestion examQuestion);
	   
	void remove(ExamQuestion examQuestion);
	   
	List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Page page, Order order);
	
	List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition);
	
	List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Page page);
	
	List<ExamQuestion> findExamQuestionByCondition(ExamQuestionCondition examQuestionCondition, Order order);
	
	Long count();
	
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
	List<ExamQuestionVo> findExamQuestionByExamIdAndTeamId(Integer teamId,Integer examId,String questionUuId);

	
	void examQuestionAnswerCount(Integer teamId,Integer ownerId,Integer type,Boolean isCorrect,String answer,Integer examId,Integer unitId);
    void examQuestionScore(Integer teamId,Integer ownerId,Integer type,Integer examId,Integer unitId);
    void examQuestiontotalTime(Integer teamId,Integer ownerId,Integer type,Integer examId,Integer unitI);

	List<ExamQuestionVo> findExamQuestionByExamIdAndQuestionUuid(Integer examId,String questionUuId);
	
	void InitExamQuestionData(List<ExamQuestion> examQuestionList);

	List<ScoringAverageVo> findScoringAverageByExamId(Integer examId);

	List<KnolwdgeDiffVo> findKnowledgeDiffByExamId(Integer examId);

	List<ScoringAverageVo> findKnowledgeCognitionByExamId(Integer examId);

	List<ScoringAverageVo> findCountQuestiontTypeByExamId(Integer examId);
	
	void examQuestionAverageScore(Integer examId);

	List<ScoringAverageVo> findTeamKnoledgeScoreByExamId(Integer examId);

	/**
	 * @function 根据exam ID获取典型错题分析 丢分率
	 * @param examId
	 * @return
     */
	List<ExamErrorVo> findExamErrorByExamId(Integer examId);
	
	void batchUpdateExamQuestionAnswerCount(Object [] list);
	
	void batchUpdateExamQuestionScore(Object [] list);
	
	void batchUpdateExamQuestiontotalTime(Object [] list);
	
	void batchUpdateExamQuestionAverageScore(Object [] list);
	
	void batchUpdateExamQuestionEmptyAnswerCount(Object [] list);
	void batchUpdateExamQuestionCorrectAnswerCount(Object [] list);
	
	void batchUpdateExamQuestion(List<Map<String,Object>> examQuestionEmptyAnswerCountList,List<Map<String,Object>> examQuestionCorrectAnswerCountList,
	List<Map<String,Object>> examQuestionAnswerCountList,List<Map<String,Object>> examQuestionScoreList,List<Map<String,Object>> examQuestionAverageScoreList,List<Map<String,Object>> examQuestiontotalTimeList,List<List<ExamQuestion>> teamScoringRateList );
	
	void batchUpdateExamQuestionGradeScoringRate(Integer examId);
	
	void batchUpateExamQuestionTeamScoringRate(List<ExamQuestion> examQuestionList);
	
	void batchUpdateExamQuestionGradeRank(Object [] list);
	
	List<ExamQuestion> findExamQuestionByJointExamCode(String jointExamCode,String questionUuid);
	
	void deleteByExamId(Integer examId);
	
	Map<String,ExamQuestion> findCountQuestiontByExamIdAndQuestionUuids(Integer examId,Object []questionUuids);
	
	void createBatch(ExamQuestion[] eqlist);
	
    /**
     * 根据code和uuid找到一道题的年级答题记录。
     * @param code
     * @param questionUuid
     * @param examType
     * @return
     */
    List<ExamQuestionVo> findExamQuestionByCodeAndQuestionUuid(String code,String questionUuid,String examType);
    
    List<ExamQuestionWrongVo> findExamQuestionWrongbyExamIdAndQuestionUuids(
			Integer[] examQuestionIds);
	
}
