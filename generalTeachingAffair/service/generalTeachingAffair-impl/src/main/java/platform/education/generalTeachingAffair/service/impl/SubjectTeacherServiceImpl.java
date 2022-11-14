package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.SubjectTeacherDao;
import platform.education.generalTeachingAffair.model.SubjectTeacher;
import platform.education.generalTeachingAffair.service.SubjectTeacherService;
import platform.education.generalTeachingAffair.vo.SubjectTeacherCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeacherVo;
import platform.education.generalTeachingAffair.vo.SubjectTeamTeacherVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SubjectTeacherServiceImpl implements SubjectTeacherService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SubjectTeacherDao subjectTeacherDao;

	public void setSubjectTeacherDao(SubjectTeacherDao subjectTeacherDao) {
		this.subjectTeacherDao = subjectTeacherDao;
	}
	
	@Override
	public SubjectTeacher findSubjectTeacherById(Integer id) {
		try {
			return subjectTeacherDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SubjectTeacher add(SubjectTeacher subjectTeacher) {
		return subjectTeacherDao.create(subjectTeacher);
	}

	@Override
	public SubjectTeacher modify(SubjectTeacher subjectTeacher) {
		return subjectTeacherDao.update(subjectTeacher);
	}
	
	@Override
	public void remove(SubjectTeacher subjectTeacher) {
		try{
			subjectTeacherDao.delete(subjectTeacher);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
		 
	}
	@Override
	public List<SubjectTeacher> findSubjectTeacherByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order)
	{
		return subjectTeacherDao.findSubjectTeacherByCondition(subjectTeacherCondition, page, order);
	}

	@Override
	public List<SubjectTeacherVo> findSubjectTeacherVoByCondition(SubjectTeacherCondition subjectTeacherCondition, Page page, Order order)
	{
		return subjectTeacherDao.findSubjectTeacherVoByCondition(subjectTeacherCondition, page, order);
	}

	@Override
	public void removeByTeacherId(Integer teacherId) {
		Date now = new Date();
		List<SubjectTeacher> result = this.findSubjectTeacherByTeacherId(teacherId);
		for (SubjectTeacher subjectTeacher : result) {
			subjectTeacher.setIsDeleted(true);
			subjectTeacher.setModifyDate(now);
			subjectTeacherDao.update(subjectTeacher);
		}
	}

	@Override
	public List<SubjectTeacher> findSubjectTeacherByTeacherId(Integer teacherId) {
		SubjectTeacherCondition condition = new SubjectTeacherCondition();
		condition.setTeacherId(teacherId);
		List<SubjectTeacher> result = subjectTeacherDao.findSubjectTeacherByCondition(condition);
		return result;
	}


	@Override
	public List<SubjectTeamTeacherVo> findSubjectsWithTeacher(Integer gradeId,
			Integer teamId) {
		return this.subjectTeacherDao.findSubjectsWithTeacher(gradeId, teamId);
	}

}
