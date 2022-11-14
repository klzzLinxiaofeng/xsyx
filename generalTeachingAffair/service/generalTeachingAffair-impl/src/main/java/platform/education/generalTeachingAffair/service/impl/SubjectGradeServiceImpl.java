package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.SubjectGradeDao;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.vo.SubjectGradeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SubjectGradeServiceImpl implements SubjectGradeService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SubjectGradeDao subjectGradeDao;
	
	@Override
	public SubjectGrade findSubjectGardeById(Integer id) {
		return subjectGradeDao.findById(id);
	}

	@Override
	public SubjectGrade add(SubjectGrade subjectGrade) {
		return subjectGradeDao.create(subjectGrade);
	}

	@Override
	public SubjectGrade modify(SubjectGrade subjectGrade) {
		return subjectGradeDao.update(subjectGrade);
	}
	
	/**
	 * 功能描述：根据学校ID,年级Code,科目Code可以查到年级科目中唯一记录
	 */
	@Override
	public SubjectGrade findSubjectGradeByGradeCodeAndSubjectCode(String gradeCode, String subjectCode, Integer schoolId){
		return subjectGradeDao.findSubjectGradeByGradeCodeAndSubjectCode(gradeCode, subjectCode, schoolId);
	}

	@Override
	public void remove(SubjectGrade subjectGrade) {
		subjectGradeDao.delete(subjectGrade);

	}

	@Override 
	public List<SubjectGrade> findSubjectGradeByCondition(
			SubjectGradeCondition subjectGradeCondition, Page page, Order order) {
		return subjectGradeDao.findSubjectGradeByCondition(subjectGradeCondition, page, order);
	}

	/**
	 * 功能描述：根据学校ID,年级Code可以查询到本学校该年级下的科目
	 */
	@Override
	public List<SubjectGrade> findSubjectGradeByGradeCode(Integer schoolId, String gradeCode) {
		return this.subjectGradeDao.findSubjectGradeByGradeCode(schoolId, gradeCode);
	}

	public SubjectGradeDao getSubjectGradeDao() {
		return subjectGradeDao;
	}

	public void setSubjectGradeDao(SubjectGradeDao subjectGradeDao) {
		this.subjectGradeDao = subjectGradeDao;
	}

	@Override
	public void addByBatch(String gradeCode,List<Subject> sList, String stageCode,Integer schoolId) {
		for(Subject sub : sList){
			SubjectGrade subjectGrade = new SubjectGrade();
			subjectGrade.setGradeCode(gradeCode);
			subjectGrade.setSchoolId(schoolId);
			subjectGrade.setStageCode(stageCode);
			subjectGrade.setSubjectCode(sub.getCode());
			subjectGrade.setSubjectName(sub.getName());
			subjectGrade.setDelete(false);
			subjectGrade.setCreateDate(new Date());
			subjectGrade.setModifyDate(new Date());
			this.subjectGradeDao.create(subjectGrade);
		}
	}

	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 */
	@Override
	public String abandon(SubjectGrade subjectGrade) {
		if(subjectGrade != null) {
			subjectGrade.setDelete(true);
			try {
				subjectGrade = this.subjectGradeDao.update(subjectGrade);
				if(subjectGrade != null) {
					return SubjectGradeService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", subjectGrade.getId(), e);
				}
				return SubjectGradeService.OPERATE_ERROR;
			}
		}
		return SubjectGradeService.OPERATE_FAIL;
	}

	
}
