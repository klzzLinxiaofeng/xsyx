package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.TeacherAssist;
import platform.education.generalTeachingAffair.vo.TeacherAssistCondition;
import platform.education.generalTeachingAffair.service.TeacherAssistService;
import platform.education.generalTeachingAffair.dao.TeacherAssistDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TeacherAssistServiceImpl implements TeacherAssistService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TeacherAssistDao teacherAssistDao;

	public void setTeacherAssistDao(TeacherAssistDao teacherAssistDao) {
		this.teacherAssistDao = teacherAssistDao;
	}
	
	@Override
	public TeacherAssist findTeacherAssistById(Integer id) {
		try {
			return teacherAssistDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TeacherAssist add(TeacherAssist teacherAssist) {
		if(teacherAssist == null) {
    		return null;
    	}
    	Date createDate = teacherAssist.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	teacherAssist.setCreateDate(createDate);
    	teacherAssist.setModifyDate(createDate);
		return teacherAssistDao.create(teacherAssist);
	}

	@Override
	public TeacherAssist modify(TeacherAssist teacherAssist) {
		if(teacherAssist == null) {
    		return null;
    	}
    	Date modify = teacherAssist.getModifyDate();
    	teacherAssist.setModifyDate(modify != null ? modify : new Date());
		return teacherAssistDao.update(teacherAssist);
	}
	
	@Override
	public void remove(TeacherAssist teacherAssist) {
		try {
			teacherAssistDao.delete(teacherAssist);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", teacherAssist.getId(), e);
			}
		}
	}
	
	@Override
	public List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Page page, Order order) {
		return teacherAssistDao.findTeacherAssistByCondition(teacherAssistCondition, page, order);
	}
	
	@Override
	public List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition) {
		return teacherAssistDao.findTeacherAssistByCondition(teacherAssistCondition, null, null);
	}
	
	@Override
	public List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Page page) {
		return teacherAssistDao.findTeacherAssistByCondition(teacherAssistCondition, page, null);
	}
	
	@Override
	public List<TeacherAssist> findTeacherAssistByCondition(TeacherAssistCondition teacherAssistCondition, Order order) {
		return teacherAssistDao.findTeacherAssistByCondition(teacherAssistCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.teacherAssistDao.count(null);
	}

	@Override
	public Long count(TeacherAssistCondition teacherAssistCondition) {
		return this.teacherAssistDao.count(teacherAssistCondition);
	}
	
	@Override
	public TeacherAssist findOfUser(Integer schoolId, Integer userId) {
		return this.teacherAssistDao.findOfUser(schoolId, userId);
	}

	@Override
	public TeacherAssist updateTeacherAssist(Integer schoolId, Integer userId,
			TeacherAssist teacherAssist) {
		TeacherAssist ta = this.teacherAssistDao.findOfUser(schoolId, userId);
		if(ta != null){
			teacherAssist.setId(ta.getId());
			teacherAssist.setUserId(userId);
			teacherAssist.setIsDelete(false);
			teacherAssist.setModifyDate(new Date());
			teacherAssist = teacherAssistDao.update(teacherAssist);
		}else{
			teacherAssist.setSchoolId(schoolId);
			teacherAssist.setUserId(userId);
			teacherAssist.setIsDelete(false);
			teacherAssist.setCreateDate(new Date());
			teacherAssist.setModifyDate(new Date());
			teacherAssist = teacherAssistDao.create(teacherAssist);
		}
		return teacherAssist;
	}

	@Override
	public void removeByUserId(Integer userId) {
		TeacherAssistCondition condition = new TeacherAssistCondition();
		condition.setUserId(userId);
		List<TeacherAssist> result = teacherAssistDao.findTeacherAssistByCondition(condition);
		
		for (TeacherAssist teacherAssist : result) {
			teacherAssistDao.delete(teacherAssist);
		}
		
	}
}
