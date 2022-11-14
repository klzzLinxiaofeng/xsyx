package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.GroupTaskStudent;
import platform.education.generalTeachingAffair.vo.GroupTaskStudentCondition;
import platform.education.generalTeachingAffair.service.GroupTaskStudentService;
import platform.education.generalTeachingAffair.dao.GroupTaskStudentDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class GroupTaskStudentServiceImpl implements GroupTaskStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private GroupTaskStudentDao groupTaskStudentDao;

	public void setGroupTaskStudentDao(GroupTaskStudentDao groupTaskStudentDao) {
		this.groupTaskStudentDao = groupTaskStudentDao;
	}
	
	@Override
	public GroupTaskStudent findGroupTaskStudentById(Integer id) {
		try {
			return groupTaskStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public GroupTaskStudent add(GroupTaskStudent groupTaskStudent) {
		if(groupTaskStudent == null) {
    		return null;
    	}
    	Date createDate = groupTaskStudent.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	groupTaskStudent.setCreateDate(createDate);
    	groupTaskStudent.setModifyDate(createDate);
		return groupTaskStudentDao.create(groupTaskStudent);
	}

	@Override
	public GroupTaskStudent modify(GroupTaskStudent groupTaskStudent) {
		if(groupTaskStudent == null) {
    		return null;
    	}
    	Date modify = groupTaskStudent.getModifyDate();
    	groupTaskStudent.setModifyDate(modify != null ? modify : new Date());
		return groupTaskStudentDao.update(groupTaskStudent);
	}
	
	@Override
	public void remove(GroupTaskStudent groupTaskStudent) {
		try {
			groupTaskStudentDao.delete(groupTaskStudent);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", groupTaskStudent.getId(), e);
			}
		}
	}
	
	@Override
	public List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Page page, Order order) {
		return groupTaskStudentDao.findGroupTaskStudentByCondition(groupTaskStudentCondition, page, order);
	}
	
	@Override
	public List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition) {
		return groupTaskStudentDao.findGroupTaskStudentByCondition(groupTaskStudentCondition, null, null);
	}
	
	@Override
	public List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Page page) {
		return groupTaskStudentDao.findGroupTaskStudentByCondition(groupTaskStudentCondition, page, null);
	}
	
	@Override
	public List<GroupTaskStudent> findGroupTaskStudentByCondition(GroupTaskStudentCondition groupTaskStudentCondition, Order order) {
		return groupTaskStudentDao.findGroupTaskStudentByCondition(groupTaskStudentCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.groupTaskStudentDao.count(null);
	}

	@Override
	public Long count(GroupTaskStudentCondition groupTaskStudentCondition) {
		return this.groupTaskStudentDao.count(groupTaskStudentCondition);
	}

	@Override
	public void batchCreate(GroupTaskStudent[] list) {
		this.groupTaskStudentDao.batchCreate(list);
	}

}
