package platform.education.commonResource.serivce;

import java.util.List;

public interface StatisticService {
   
	/**
	 * 统计业务逻辑处理
	 * @param examId 
	 * @param paperId
	 * @param ownerId
	 * @return
	 */
	public Boolean statisticHandle(Integer objectId,Integer type,Integer stId);
	
	public void initBatchExamStatistics(List<Integer> teamIds,Integer schoolId,Integer teacherId,String type,Integer paperId,String code);
}
