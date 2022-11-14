package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamWorksSubjectDao;
import platform.education.generalTeachingAffair.dao.ExamWorksSubjectTemplateDao;
import platform.education.generalTeachingAffair.model.ExamWorksSubject;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.vo.ExamWorksSubjectCondition;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExamWorksSubjectServiceImpl implements ExamWorksSubjectService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamWorksSubjectDao examWorksSubjectDao;

	private ExamWorksSubjectTemplateDao examWorksSubjectTemplateDao;

	public void setExamWorksSubjectDao(ExamWorksSubjectDao examWorksSubjectDao) {
		this.examWorksSubjectDao = examWorksSubjectDao;
	}

	public void setExamWorksSubjectTemplateDao(ExamWorksSubjectTemplateDao examWorksSubjectTemplateDao) {
		this.examWorksSubjectTemplateDao = examWorksSubjectTemplateDao;
	}

	private GradeService gradeService;

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}



	@Override
	public ExamWorksSubject findExamWorksSubjectById(Integer id) {
		try {
			return examWorksSubjectDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamWorksSubject add(ExamWorksSubject examWorksSubject) {
		if(examWorksSubject == null) {
    		return null;
    	}
    	Date createDate = examWorksSubject.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examWorksSubject.setCreateDate(createDate);
    	examWorksSubject.setModifyDate(createDate);
		return examWorksSubjectDao.create(examWorksSubject);
	}

	@Override
	public ExamWorksSubject modify(ExamWorksSubject examWorksSubject) {
		if(examWorksSubject == null) {
    		return null;
    	}
    	Date modify = examWorksSubject.getModifyDate();
    	examWorksSubject.setModifyDate(modify != null ? modify : new Date());
		return examWorksSubjectDao.update(examWorksSubject);
	}
	
	@Override
	public void remove(ExamWorksSubject examWorksSubject) {
		try {
			examWorksSubjectDao.delete(examWorksSubject);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorksSubject.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Page page, Order order) {
		return examWorksSubjectDao.findExamWorksSubjectByCondition(examWorksSubjectCondition, page, order);
	}
	
	@Override
	public List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition) {
		return examWorksSubjectDao.findExamWorksSubjectByCondition(examWorksSubjectCondition, null, null);
	}
	
	@Override
	public List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Page page) {
		return examWorksSubjectDao.findExamWorksSubjectByCondition(examWorksSubjectCondition, page, null);
	}
	
	@Override
	public List<ExamWorksSubject> findExamWorksSubjectByCondition(ExamWorksSubjectCondition examWorksSubjectCondition, Order order) {
		return examWorksSubjectDao.findExamWorksSubjectByCondition(examWorksSubjectCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examWorksSubjectDao.count(null);
	}

	@Override
	public Long count(ExamWorksSubjectCondition examWorksSubjectCondition) {
		return this.examWorksSubjectDao.count(examWorksSubjectCondition);
	}

	@Override
	public ExamWorksSubject findUnique(Integer examWorksId, Integer gradeId, String subjectCode) {
		return this.examWorksSubjectDao.findUnique(examWorksId, gradeId, subjectCode);
	}

	@Override
	public ExamWorksSubject findUniqueWithTemplate(Integer examWorksId, Integer gradeId, String subjectCode) {
		return null;
	}

	@Override
	public List<ExamWorksSubject> findByEamWorksId(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded) {
		return this.examWorksSubjectDao.findByExamWorksId(examWorksId,gradeId,subjectCode, statNeeded);
	}

	@Override
	public List<ExamWorksSubject> findSubjectsOfExamWorks(Integer examWorksId, Integer gradeId) {
		return this.examWorksSubjectDao.findByExamWorksId(examWorksId, gradeId, null, null);
	}

	@Override
	public List<Map<String, Object>> findStatSubjects(Integer examWorksId, Integer gradeId, String subjectCode, Boolean statNeeded) {
		return this.examWorksSubjectDao.findStatSubjects(examWorksId, gradeId, subjectCode, statNeeded);
	}
}
