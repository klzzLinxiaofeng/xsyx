package platform.education.paper.dao;

import platform.education.paper.model.TaskInterscore;
import platform.education.paper.vo.TaskInterscoreCondition;
import platform.education.paper.vo.TaskInterscoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface TaskInterscoreDao extends GenericDao<TaskInterscore, java.lang.Integer> {

	List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Page page, Order order);
	
	TaskInterscore findById(Integer id);
	
	Long count(TaskInterscoreCondition taskInterscoreCondition);
	
	void createBatch(TaskInterscore[] eilist);
	
	List<TaskInterscoreVo> findExamScoringTask(Integer userId,String subjectCode,Page page,Order order);
	
	List<TaskInterscore> findScoringTimeTaskTaskInterscore(Integer taskId,Integer teamId);
	
}
