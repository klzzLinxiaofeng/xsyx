package platform.szxyzxx.services.statistic.service;

import java.util.List;

import platform.education.generalTeachingAffair.vo.ExamResult;

/**
 * 统计service
 * @author Administrator
 *
 */

public interface StatisticService {
	
	/**
	 * 统计业务逻辑处理
	 * @param examId 
	 * @param paperId
	 * @param ownerId
	 * @return
	 */
	public Boolean statisticHandle(Integer examId,Integer paperId,Integer ownerId,Integer type);
	
	
	public ExamResult  initExamStatistics(Integer teamId,Integer schoolId,Integer teacherId,String type,String examUUid,String code);
	
	public void delPaperAnswer(List<Integer> taskIds,Integer type);
	
	public void initBatchExamStatistics(List<Integer> teamIds,Integer schoolId,Integer teacherId,String type,String examUUid,String code);


	void initBatchExamStatistics(List<Integer> teamIds, Integer schoolId,
			Integer teacherId, String type, Integer paperId, String code);
	
	
	
	

	
}
