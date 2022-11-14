package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.vo.AptsTeamStuentCount;
import platform.education.generalTeachingAffair.vo.AssessmentItemVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherScoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface PjAptsTaskService {
    PjAptsTask findPjAptsTaskById(Integer id);
	   
	PjAptsTask add(PjAptsTask pjAptsTask);
	   
	PjAptsTask modify(PjAptsTask pjAptsTask);
	   
	void remove(PjAptsTask pjAptsTask);
	   
	List<PjAptsTask> findPjAptsTaskByCondition(PjAptsTaskCondition pjAptsTaskCondition, Page page, Order order);
	
	List<PjAptsTask> findPjAptsTaskByCondition(PjAptsTaskCondition pjAptsTaskCondition);
	
	List<PjAptsTask> findPjAptsTaskByCondition(PjAptsTaskCondition pjAptsTaskCondition, Page page);
	
	List<PjAptsTask> findPjAptsTaskByCondition(PjAptsTaskCondition pjAptsTaskCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskCondition pjAptsTaskCondition);
	
	PjAptsTask addPjAptsTaskRelete(PjAptsTask pjAptsTask,List<String>items);
	
	
	List<Map<String,Object>> findTodayAssessment(Integer userId);
	
	Map<String,Object> findAssessmentBoard(Integer userId,
			Integer AssessmentId);
	
	String addAssessment(Integer AssessmentId,Integer userId,String description,List<AssessmentItemVo> scoreList);
	
	void modifyTaskItem(List<String> items,List<Integer>listOrder,Integer taskId);
	
	
	LinkedHashMap<String,Object> findStatisticsByCondition(PjAptsTaskUserCondition condition,Date StartDate,Date FinishDate) throws Exception ;
	
	List<AptsTeamStuentCount> findTeamStuentCount(Integer schoolId,String schoolYear);
	/**
	 * 生成属于今天的所有任务
	 */
	void initAptsTaskUserOfToday(Integer schoolId,Integer type)throws Exception;
	
	PjAptsTask initDefaultTask(Integer schoolId,Integer type); 
	
	Map<String,Object>  findAptsTaskUserMapByAssessmentId(Integer assessmentId);
	
	List<Map<String,Object>> findAptsTaskUserMapListByUserId(String termCode,Integer type,Integer userId,Page page,Order order);
	
}
