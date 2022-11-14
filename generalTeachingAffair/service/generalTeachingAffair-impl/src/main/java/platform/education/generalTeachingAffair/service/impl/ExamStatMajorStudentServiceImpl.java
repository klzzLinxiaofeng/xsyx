package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.vo.ExamStatMajorStudentCondition;
import platform.education.generalTeachingAffair.service.ExamStatMajorStudentService;
import platform.education.generalTeachingAffair.dao.ExamStatMajorStudentDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ExamStatMajorStudentServiceImpl implements ExamStatMajorStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamStatMajorStudentDao examStatMajorStudentDao;

	public void setExamStatMajorStudentDao(ExamStatMajorStudentDao examStatMajorStudentDao) {
		this.examStatMajorStudentDao = examStatMajorStudentDao;
	}
	
	@Override
	public ExamStatMajorStudent findExamStatMajorStudentById(Integer id) {
		try {
			return examStatMajorStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamStatMajorStudent add(ExamStatMajorStudent examStatMajorStudent) {
		if(examStatMajorStudent == null) {
    		return null;
    	}
		return examStatMajorStudentDao.create(examStatMajorStudent);
	}

	@Override
	public ExamStatMajorStudent modify(ExamStatMajorStudent examStatMajorStudent) {
		if(examStatMajorStudent == null) {
    		return null;
    	}
		return examStatMajorStudentDao.update(examStatMajorStudent);
	}
	
	@Override
	public void remove(ExamStatMajorStudent examStatMajorStudent) {
		try {
			examStatMajorStudentDao.delete(examStatMajorStudent);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examStatMajorStudent.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Page page, Order order) {
		return examStatMajorStudentDao.findExamStatMajorStudentByCondition(examStatMajorStudentCondition, page, order);
	}
	
	@Override
	public List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition) {
		return examStatMajorStudentDao.findExamStatMajorStudentByCondition(examStatMajorStudentCondition, null, null);
	}
	
	@Override
	public List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Page page) {
		return examStatMajorStudentDao.findExamStatMajorStudentByCondition(examStatMajorStudentCondition, page, null);
	}
	
	@Override
	public List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Order order) {
		return examStatMajorStudentDao.findExamStatMajorStudentByCondition(examStatMajorStudentCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examStatMajorStudentDao.count(null);
	}

	@Override
	public Long count(ExamStatMajorStudentCondition examStatMajorStudentCondition) {
		return this.examStatMajorStudentDao.count(examStatMajorStudentCondition);
	}

	@Override
	public ExamStatMajorStudent findExamStatMajorStudentByExamWorksIdAndStudentId(Integer examWorksId,
			Integer studentId) {
		return examStatMajorStudentDao.findExamStatMajorStudentByExamWorksIdAndStudentId(examWorksId, studentId);
	}

	@Override
	public void createBatch(ExamStatMajorStudent[] examStatMajorStudents) {
		if (examStatMajorStudents.length > 0) {
			this.examStatMajorStudentDao.createBatch(examStatMajorStudents);
		}
	}

	@Override
	public void batchUpdateExamStatMajorStudentTotalScore(Object[] list) {
		if (list != null && list.length > 0) {
			this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTotalScore(list);
		}
	}

	@Override
	public void batchUpdateExamStatMajorStudentTeamRank(Object[] list) {
		if (list != null && list.length > 0) {
			this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentTeamRank(list);
		}
		
	}

	@Override
	public void batchUpdateExamStatMajorStudentGradeRank(Object[] list) {
		if (list != null && list.length > 0) {
			this.examStatMajorStudentDao.batchUpdateExamStatMajorStudentGradeRank(list);
		}
	}

	@Override
	public List<ExamStatMajorStudent> findExamStatMajorCount(Integer examWorksId, String jointExamCode, Integer teamId,Integer flag) {
		return examStatMajorStudentDao.findExamStatMajorCount(examWorksId, jointExamCode, teamId,flag);
	}
}
