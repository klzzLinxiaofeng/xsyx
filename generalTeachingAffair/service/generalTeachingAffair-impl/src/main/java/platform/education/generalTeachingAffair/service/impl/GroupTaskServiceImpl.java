package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.GroupTask;
import platform.education.generalTeachingAffair.vo.GroupTaskCondition;
import platform.education.generalTeachingAffair.service.GroupTaskService;
import platform.education.generalTeachingAffair.dao.GroupTaskDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.GroupTaskVo;

public class GroupTaskServiceImpl implements GroupTaskService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private GroupTaskDao groupTaskDao;

	public void setGroupTaskDao(GroupTaskDao groupTaskDao) {
		this.groupTaskDao = groupTaskDao;
	}
	
	@Override
	public GroupTask findGroupTaskById(Integer id) {
		try {
			return groupTaskDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public GroupTask add(GroupTask groupTask) {
		if(groupTask == null) {
    		return null;
    	}
    	Date createDate = groupTask.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	groupTask.setCreateDate(createDate);
    	groupTask.setModifyDate(createDate);
		return groupTaskDao.create(groupTask);
	}

	@Override
	public GroupTask modify(GroupTask groupTask) {
		if(groupTask == null) {
    		return null;
    	}
    	Date modify = groupTask.getModifyDate();
    	groupTask.setModifyDate(modify != null ? modify : new Date());
		return groupTaskDao.update(groupTask);
	}
	
	@Override
	public void remove(GroupTask groupTask) {
		try {
			groupTaskDao.delete(groupTask);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", groupTask.getId(), e);
			}
		}
	}
	
	@Override
	public List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Page page, Order order) {
		return groupTaskDao.findGroupTaskByCondition(groupTaskCondition, page, order);
	}
	
	@Override
	public List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition) {
		return groupTaskDao.findGroupTaskByCondition(groupTaskCondition, null, null);
	}
	
	@Override
	public List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Page page) {
		return groupTaskDao.findGroupTaskByCondition(groupTaskCondition, page, null);
	}
	
	@Override
	public List<GroupTask> findGroupTaskByCondition(GroupTaskCondition groupTaskCondition, Order order) {
		return groupTaskDao.findGroupTaskByCondition(groupTaskCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.groupTaskDao.count(null);
	}

	@Override
	public Long count(GroupTaskCondition groupTaskCondition) {
		return this.groupTaskDao.count(groupTaskCondition);
	}

	@Override
	public void batchCreate(GroupTask[] list) {
		this.groupTaskDao.batchCreate(list);
	}

	@Override
	public List<Integer> findGroupIdByHourAndPeriod(GroupTaskCondition groupTaskCondition) {
		return groupTaskDao.findGroupIdByHourAndPeriod(groupTaskCondition);
	}

	@Override
	public List<Integer> findQuestionNum(GroupTaskCondition groupTaskCondition) {
		return groupTaskDao.findQuestionNum(groupTaskCondition);
	}

	@Override
	public List<GroupTaskVo> findGroupTaskByGroupIdAndGroupNum(Integer groupId, Integer groupNum, Integer studentId) {
		if(groupId==null || groupNum==null){
			return null;
		}
		return groupTaskDao.findGroupTaskByGroupIdAndGroupNum(groupId, groupNum, studentId);
	}

}
