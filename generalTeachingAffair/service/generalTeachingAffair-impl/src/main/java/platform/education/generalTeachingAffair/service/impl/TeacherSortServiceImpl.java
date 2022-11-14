package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.TeacherSortDao;
import platform.education.generalTeachingAffair.model.TeacherSort;
import platform.education.generalTeachingAffair.service.TeacherSortService;
import platform.education.generalTeachingAffair.vo.TeacherSortCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TeacherSortServiceImpl implements TeacherSortService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TeacherSortDao teacherSortDao;

	public void setTeacherSortDao(TeacherSortDao teacherSortDao) {
		this.teacherSortDao = teacherSortDao;
	}
	
	@Override
	public TeacherSort findTeacherSortById(Integer id) {
		try {
			return teacherSortDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TeacherSort add(TeacherSort teacherSort) {
		if(teacherSort == null) {
    		return null;
    	}
    	Date createDate = teacherSort.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	teacherSort.setCreateDate(createDate);
    	teacherSort.setModifyDate(createDate);
		return teacherSortDao.create(teacherSort);
	}

	@Override
	public TeacherSort modify(TeacherSort teacherSort) {
		if(teacherSort == null) {
    		return null;
    	}
    	Date modify = teacherSort.getModifyDate();
    	teacherSort.setModifyDate(modify != null ? modify : new Date());
		return teacherSortDao.update(teacherSort);
	}
	
	@Override
	public void remove(TeacherSort teacherSort) {
		try {
			teacherSortDao.delete(teacherSort);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", teacherSort.getId(), e);
			}
		}
	}
	
	@Override
	public List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Page page, Order order) {
		return teacherSortDao.findTeacherSortByCondition(teacherSortCondition, page, order);
	}
	
	@Override
	public List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition) {
		return teacherSortDao.findTeacherSortByCondition(teacherSortCondition, null, null);
	}
	
	@Override
	public List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Page page) {
		return teacherSortDao.findTeacherSortByCondition(teacherSortCondition, page, null);
	}
	
	@Override
	public List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Order order) {
		return teacherSortDao.findTeacherSortByCondition(teacherSortCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.teacherSortDao.count(null);
	}

	@Override
	public Long count(TeacherSortCondition teacherSortCondition) {
		return this.teacherSortDao.count(teacherSortCondition);
	}

	@Override
	public List<TeacherSort> batchAdd(List<TeacherSort> teachers) {
		List<TeacherSort> list = new ArrayList<TeacherSort>();
		if(teachers.size() > 0 && teachers != null){
			for(TeacherSort teacherSort : teachers){
				if(teacherSort == null) {
		    		return null;
		    	}
				Date createDate = teacherSort.getCreateDate();
				Date modifyDate = teacherSort.getModifyDate();
				if(createDate == null){
					createDate = new Date();
					teacherSort.setCreateDate(createDate);
				}
				if(modifyDate == null){
					modifyDate = new Date();
					teacherSort.setModifyDate(modifyDate);
				}
				teacherSort = teacherSortDao.create(teacherSort);
				list.add(teacherSort);
			}
		}
		return list;
	}

	@Override
	public void removeByTeacherId(Integer teacherId) {
		TeacherSortCondition condition = new TeacherSortCondition();
		condition.setTeacherId(teacherId);
		List<TeacherSort> result = teacherSortDao.findTeacherSortByCondition(condition);
		for (TeacherSort teacherSort : result) {
			teacherSortDao.delete(teacherSort);
		}
	}

}
