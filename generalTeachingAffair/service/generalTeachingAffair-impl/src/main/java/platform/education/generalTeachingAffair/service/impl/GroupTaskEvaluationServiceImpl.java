package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.GroupTaskEvaluation;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationCondition;
import platform.education.generalTeachingAffair.service.GroupTaskEvaluationService;
import platform.education.generalTeachingAffair.dao.GroupTaskEvaluationDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.GroupTaskEvaluationVo;

public class GroupTaskEvaluationServiceImpl implements GroupTaskEvaluationService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private GroupTaskEvaluationDao groupTaskEvaluationDao;

	public void setGroupTaskEvaluationDao(GroupTaskEvaluationDao groupTaskEvaluationDao) {
		this.groupTaskEvaluationDao = groupTaskEvaluationDao;
	}
	
	@Override
	public GroupTaskEvaluation findGroupTaskEvaluationById(Integer id) {
		try {
			return groupTaskEvaluationDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public GroupTaskEvaluation add(GroupTaskEvaluation groupTaskEvaluation) {
		if(groupTaskEvaluation == null) {
    		return null;
    	}
    	Date createDate = groupTaskEvaluation.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	groupTaskEvaluation.setCreateDate(createDate);
    	groupTaskEvaluation.setModifyDate(createDate);
		return groupTaskEvaluationDao.create(groupTaskEvaluation);
	}

	@Override
	public GroupTaskEvaluation modify(GroupTaskEvaluation groupTaskEvaluation) {
		if(groupTaskEvaluation == null) {
    		return null;
    	}
    	Date modify = groupTaskEvaluation.getModifyDate();
    	groupTaskEvaluation.setModifyDate(modify != null ? modify : new Date());
		return groupTaskEvaluationDao.update(groupTaskEvaluation);
	}
	
	@Override
	public void remove(GroupTaskEvaluation groupTaskEvaluation) {
		try {
			groupTaskEvaluationDao.delete(groupTaskEvaluation);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", groupTaskEvaluation.getId(), e);
			}
		}
	}
	
	@Override
	public List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Page page, Order order) {
		return groupTaskEvaluationDao.findGroupTaskEvaluationByCondition(groupTaskEvaluationCondition, page, order);
	}
	
	@Override
	public List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition) {
		return groupTaskEvaluationDao.findGroupTaskEvaluationByCondition(groupTaskEvaluationCondition, null, null);
	}
	
	@Override
	public List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Page page) {
		return groupTaskEvaluationDao.findGroupTaskEvaluationByCondition(groupTaskEvaluationCondition, page, null);
	}
	
	@Override
	public List<GroupTaskEvaluation> findGroupTaskEvaluationByCondition(GroupTaskEvaluationCondition groupTaskEvaluationCondition, Order order) {
		return groupTaskEvaluationDao.findGroupTaskEvaluationByCondition(groupTaskEvaluationCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.groupTaskEvaluationDao.count(null);
	}

	@Override
	public Long count(GroupTaskEvaluationCondition groupTaskEvaluationCondition) {
		return this.groupTaskEvaluationDao.count(groupTaskEvaluationCondition);
	}

	@Override
	public void batchCreate(GroupTaskEvaluation[] list) {
		this.groupTaskEvaluationDao.batchCreate(list);
	}

	@Override
	public void batchDelete(GroupTaskEvaluationCondition groupTaskEvaluationCondition) {
		this.groupTaskEvaluationDao.batchDelete(groupTaskEvaluationCondition);
	}

	@Override
	public List<GroupTaskEvaluationVo> findGroupTaskEvaluationByGroupIdAndGroupNum(Integer groupId, Integer groupNum) {
		if(groupId==null || groupNum==null){
			return null;
		}
		return this.groupTaskEvaluationDao.findGroupTaskEvaluationByGroupIdAndGroupNum(groupId, groupNum);
	}

}
