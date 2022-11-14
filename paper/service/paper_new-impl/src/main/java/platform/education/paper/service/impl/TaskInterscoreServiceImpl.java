package platform.education.paper.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.paper.model.TaskInterscore;
import platform.education.paper.vo.TaskInterscoreCondition;
import platform.education.paper.vo.TaskInterscoreVo;
import platform.education.paper.service.TaskInterscoreService;
import platform.education.paper.dao.TaskInterscoreDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TaskInterscoreServiceImpl implements TaskInterscoreService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TaskInterscoreDao taskInterscoreDao;

	public void setTaskInterscoreDao(TaskInterscoreDao taskInterscoreDao) {
		this.taskInterscoreDao = taskInterscoreDao;
	}
	
	@Override
	public TaskInterscore findTaskInterscoreById(Integer id) {
		try {
			return taskInterscoreDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TaskInterscore add(TaskInterscore taskInterscore) {
		if(taskInterscore == null) {
    		return null;
    	}
    	Date createDate = taskInterscore.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	taskInterscore.setCreateDate(createDate);
    	taskInterscore.setModifyDate(createDate);
		return taskInterscoreDao.create(taskInterscore);
	}

	@Override
	public TaskInterscore modify(TaskInterscore taskInterscore) {
		if(taskInterscore == null) {
    		return null;
    	}
    	Date modify = taskInterscore.getModifyDate();
    	taskInterscore.setModifyDate(modify != null ? modify : new Date());
		return taskInterscoreDao.update(taskInterscore);
	}
	
	@Override
	public void remove(TaskInterscore taskInterscore) {
		try {
			taskInterscoreDao.delete(taskInterscore);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", taskInterscore.getId(), e);
			}
		}
	}
	
	@Override
	public List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Page page, Order order) {
		return taskInterscoreDao.findTaskInterscoreByCondition(taskInterscoreCondition, page, order);
	}
	
	@Override
	public List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition) {
		return taskInterscoreDao.findTaskInterscoreByCondition(taskInterscoreCondition, null, null);
	}
	
	@Override
	public List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Page page) {
		return taskInterscoreDao.findTaskInterscoreByCondition(taskInterscoreCondition, page, null);
	}
	
	@Override
	public List<TaskInterscore> findTaskInterscoreByCondition(TaskInterscoreCondition taskInterscoreCondition, Order order) {
		return taskInterscoreDao.findTaskInterscoreByCondition(taskInterscoreCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.taskInterscoreDao.count(null);
	}

	@Override
	public Long count(TaskInterscoreCondition taskInterscoreCondition) {
		return this.taskInterscoreDao.count(taskInterscoreCondition);
	}

	@Override
	public void createBatch(TaskInterscore[] eilist) {
		taskInterscoreDao.createBatch(eilist);
	}

	@Override
	public List<TaskInterscoreVo> findExamScoringTask(Integer userId,
			String subjectCode, Page page, Order order) {
		return taskInterscoreDao.findExamScoringTask(userId, subjectCode, page, order);
	}

	@Override
	public List<TaskInterscore> findScoringTimeTaskTaskInterscore(
			Integer taskId, Integer teamId) {
		return taskInterscoreDao.findScoringTimeTaskTaskInterscore(taskId, teamId);
	}

	@Override
	public TaskInterscore findByScoredPaperId(Integer scoredPaperId) {
		TaskInterscoreCondition examInterscoreCondition = new TaskInterscoreCondition();
		examInterscoreCondition.setScoredPaperId(scoredPaperId);
		List<TaskInterscore> examInterscoreList = this.findTaskInterscoreByCondition(examInterscoreCondition);
		if(examInterscoreList.size() > 0) {
			return examInterscoreList.get(0);
		}
		return null;
	}

}
