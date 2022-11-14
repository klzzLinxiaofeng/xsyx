package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamWorksGradeDao;
import platform.education.generalTeachingAffair.model.ExamWorksGrade;
import platform.education.generalTeachingAffair.service.ExamWorksGradeService;
import platform.education.generalTeachingAffair.vo.ExamWorksGradeCondition;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExamWorksGradeServiceImpl implements ExamWorksGradeService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamWorksGradeDao examWorksGradeDao;

	public void setExamWorksGradeDao(ExamWorksGradeDao examWorksGradeDao) {
		this.examWorksGradeDao = examWorksGradeDao;
	}
	
	@Override
	public ExamWorksGrade findExamWorksGradeById(Integer id) {
		try {
			return examWorksGradeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamWorksGrade add(ExamWorksGrade examWorksGrade) {
		if(examWorksGrade == null) {
    		return null;
    	}
    	Date createDate = examWorksGrade.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examWorksGrade.setCreateDate(createDate);
    	examWorksGrade.setModifyDate(createDate);
		return examWorksGradeDao.create(examWorksGrade);
	}

	@Override
	public ExamWorksGrade modify(ExamWorksGrade examWorksGrade) {
		if(examWorksGrade == null) {
    		return null;
    	}
    	Date modify = examWorksGrade.getModifyDate();
    	examWorksGrade.setModifyDate(modify != null ? modify : new Date());
		return examWorksGradeDao.update(examWorksGrade);
	}
	
	@Override
	public void remove(ExamWorksGrade examWorksGrade) {
		try {
			examWorksGradeDao.delete(examWorksGrade);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorksGrade.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Page page, Order order) {
		return examWorksGradeDao.findExamWorksGradeByCondition(examWorksGradeCondition, page, order);
	}
	
	@Override
	public List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition) {
		return examWorksGradeDao.findExamWorksGradeByCondition(examWorksGradeCondition, null, null);
	}
	
	@Override
	public List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Page page) {
		return examWorksGradeDao.findExamWorksGradeByCondition(examWorksGradeCondition, page, null);
	}
	
	@Override
	public List<ExamWorksGrade> findExamWorksGradeByCondition(ExamWorksGradeCondition examWorksGradeCondition, Order order) {
		return examWorksGradeDao.findExamWorksGradeByCondition(examWorksGradeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examWorksGradeDao.count(null);
	}

	@Override
	public Long count(ExamWorksGradeCondition examWorksGradeCondition) {
		return this.examWorksGradeDao.count(examWorksGradeCondition);
	}

	@Override
	public List<ExamWorksGrade> findOfExamWorks(Integer examWorksId) {
		return this.examWorksGradeDao.findOfExamWorks(examWorksId);
	}

	@Override
	public ExamWorksGrade findUnique(Integer examWorksId, Integer gradeId) {
		return this.examWorksGradeDao.findUnique(examWorksId, gradeId, null);
	}

	@Override
	public ExamWorksGrade findUnique(Integer examWorksId, Integer gradeId, String jointExamCode) {
		return this.examWorksGradeDao.findUnique(examWorksId, gradeId, jointExamCode);
	}

	@Override
	public List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId) {
		return this.examWorksGradeDao.findOfExamWorksWithScore(examWorksId, null);
	}

	@Override
	public Map<String, Object> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId) {
		List<Map<String, Object>> list = this.examWorksGradeDao.findOfExamWorksWithScore(examWorksId, gradeId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
