package platform.education.generalTeachingAffair.dao;

import java.util.List;

import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.vo.SubjectGradeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface SubjectGradeDao {

	List<SubjectGrade> findSubjectGradeByCondition(SubjectGradeCondition subjectGradeCondition, Page page, Order order);
	
	SubjectGrade findById(Integer id);
	
	SubjectGrade create(SubjectGrade subjectGrade);
	
	SubjectGrade update(SubjectGrade subjectGrade);
	
	void delete(SubjectGrade subjectGrade);
	
	/**
	 * 功能描述：根据学校ID,年级Code可以查询到本学校该年级下的科目
	 * @param gradeCode
	 * @return
	 */
	public List<SubjectGrade> findSubjectGradeByGradeCode(Integer schoolId, String gradeCode);
	
	/**
	 * 功能描述：根据学校ID,年级Code,科目Code可以查到年级科目中唯一记录
	 * @param gradeCode
	 * @param subjectCode
	 * @param schoolId
	 * @return
	 */
	public SubjectGrade findSubjectGradeByGradeCodeAndSubjectCode(String gradeCode, String subjectCode, Integer schoolId);
	
	
	
}
