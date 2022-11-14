package platform.education.paper.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.Task;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.TaskCondition;
import platform.education.paper.vo.TaskVo;

import java.util.List;

public interface TaskDao extends GenericDao<Task, java.lang.Integer> {

	List<Task> findTaskByCondition(TaskCondition taskCondition, Page page, Order order);
	
	Task findById(Integer id);
	
	Long count(TaskCondition taskCondition);
	
	List<Integer> findPaperStatus(Integer paperId);
	
	List<TaskVo> findTaskVoByPaperVo(PaPaperVo vo,Integer userId,Page page,Order order);
	
	List<TaskVo> findTaskVoByTeamId(PaPaperVo vo,Integer teamId,Page page,Order order);
	
	List<TaskVo> findTaskVoOfReceiveByPaperVo(PaPaperVo vo,Integer userId,Page page,Order order);
	
	void updateFinishedCount(Integer id);
	
	TaskVo  findTaskVoByExamId(Integer examId);
	
	void updateBatchTaskPjExamId(Integer taskTeamId,Integer pjExamId);
	
	Task findTaskByUuid(String uuid);

	List<TaskVo> findTaskVo(Integer teamId, Integer subjectCode, String title, Integer schoolId, Integer gradeId, Page page, Order order);
}
