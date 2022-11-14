package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ParentStudentTmp;
import platform.education.generalTeachingAffair.model.StudentTmp;
import platform.education.generalTeachingAffair.model.SubjectTeacherTmp;
import platform.education.generalTeachingAffair.model.TeacherTmp;
import platform.education.generalTeachingAffair.vo.SubjectTeacherTmpCondition;

import java.util.List;

public interface SchoolInitService {

	void addTeacherTmpBatch(Object[] array);

	void removeTeacherTmpByCode(String code);

	List<TeacherTmp> findTeacherTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteTeacherTmpByTeacherId(Integer teacherId);

	TeacherTmp findTeacherTmpById(Integer id);

	Long countTeacherTmpByCodeAndStatus(String code, Integer status);

	void modifyTeacherTmp(TeacherTmp tmp, int i);

	void deleteTeacherTmpById(Integer id);
	
	
	void addStudentTmpBatch(Object[] array);

	void removeStudentTmpByCode(String code);

	List<StudentTmp> findStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteStudentTmpByStudentId(Integer studentId);

	StudentTmp findStudentTmpById(Integer id);

	Long countStudentTmpByCodeAndStatus(String code, Integer status);

	void modifyStudentTmp(StudentTmp tmp, int i);

	void deleteStudentTmpById(Integer id);


	
	void addSubjectTeacherTmpBatch(Object[] array);

	void removeSubjectTeacherTmpByCode(String code);

	List<SubjectTeacherTmp> findSubjectTeacherByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteSubjectTeacherTmpBySubjectTeacherId(Integer subjectTeacherId);

	SubjectTeacherTmp findSubjectTeacherTmpById(Integer id);

	Long countSubjectTeacherTmpByCodeAndStatus(String code, Integer status);

	void modifySubjectTeacherTmp(SubjectTeacherTmp tmp, int i);

	void deleteSubjectTeacherTmpById(Integer id);
	
	List<SubjectTeacherTmp> findSubjectTeacherTmpByCondition(SubjectTeacherTmpCondition subjectTeacherTmpCondition, Page page, Order order);



	void addParentStudentTmpBatch(Object[] array);

	void removeParentStudentTmpByCode(String code);

	List<ParentStudentTmp> findParentStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteParentStudentTmpByStudentId(Integer studentId);

	ParentStudentTmp findParentStudentTmpById(Integer id);

	Long countParentStudentTmpByCodeAndStatus(String code, Integer status);

	void modifyParentStudentTmp(ParentStudentTmp tmp, int i);

	void deleteParentStudentTmpById(Integer id);
}
