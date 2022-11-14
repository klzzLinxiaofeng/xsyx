package platform.education.paper.service;
import platform.education.paper.model.TaskInterscore;
import platform.education.paper.vo.TaskInterscoreCondition;
import platform.education.paper.vo.TaskInterscoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TaskInterscoreService {
    TaskInterscore findTaskInterscoreById(Integer id);
	   
	TaskInterscore add(TaskInterscore taskInterscore);
	   
	TaskInterscore modify(TaskInterscore taskInterscore);
	   
	void remove(TaskInterscore taskInterscore);
	
	List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Page page, Order order);
	
	List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition);
	
	List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Page page);
	
	List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Order order);
	
	Long count();
	
	Long count(TaskInterscoreCondition taskInterscoreCondition);
	
	void createBatch(TaskInterscore[] eilist);
	
	List<TaskInterscoreVo> findExamScoringTask(Integer userId,String subjectCode,Page page,Order order);
	
	List<TaskInterscore> findScoringTimeTaskTaskInterscore(Integer taskId,Integer teamId);

	TaskInterscore findByScoredPaperId(Integer scoredPaperId);
	
}
