package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ParentStudentTmp;
import platform.education.generalTeachingAffair.model.StudentTmp;
import platform.education.generalTeachingAffair.model.SubjectTeacherTmp;
import platform.education.generalTeachingAffair.model.TeacherTmp;
import platform.education.generalTeachingAffair.vo.SubjectTeacherTmpCondition;
import framework.generic.dao.GenericDao;
import java.util.List;

public interface SchoolInitDao extends GenericDao<TeacherTmp, java.lang.Integer> {

	/****************************teacher********************************/
	void createTeacherTmpBatch(Object[] array);

	void deleteTeacherTmpByCode(String code);

	List<TeacherTmp> findTeacherTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteTeacherTmpByTeacherId(Integer teacherId);

	TeacherTmp findTeacherTmpById(Integer id);

	Long countTeacherTmpByCodeAndStatus(String code, Integer status);

	void updateTeacherTmp(TeacherTmp tmp, int i);

	void deleteTeacherTmpById(Integer id);
	
	/*********************************student****************************/
	void createStudentTmpBatch(Object[] array);

	void deleteStudentTmpByCode(String code);

	List<StudentTmp> findStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteStudentTmpByStudentId(Integer studentId);

	StudentTmp findStudentTmpById(Integer id);

	Long countStudentTmpByCodeAndStatus(String code, Integer status);

	void updateStudentTmp(StudentTmp tmp, int i);

	void deleteStudentTmpById(Integer id);
	
	/*********************************subject_teacher****************************/
	void createSubjectTeacherTmpBatch(Object[] array);

	void deleteSubjectTeacherTmpByCode(String code);

	List<SubjectTeacherTmp> findSubjectTeacherTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteSubjectTeacherTmpBySubjectTeacherId(Integer subjectTeacherId);

	SubjectTeacherTmp findSubjectTeacherTmpById(Integer id);

	Long countSubjectTeacherTmpByCodeAndStatus(String code, Integer status);

	void updateSubjectTeacherTmp(SubjectTeacherTmp tmp, int i);

	void deleteSubjectTeacherTmpById(Integer id);
	
	List<SubjectTeacherTmp> findSubjectTeacherTmpByCondition(SubjectTeacherTmpCondition subjectTeacherTmpCondition, Page page, Order order);

	/*********************************parent****************************/
	void createParentStudentTmpBatch(Object[] array);

	void deleteParentStudentTmpByCode(String code);

	List<ParentStudentTmp> findParentStudentTmpByCodeAndStatus(String code, Integer status, Page page, Order order);

	void deleteParentStudentTmpByStudentId(Integer studentId);

	ParentStudentTmp findParentStudentTmpById(Integer id);

	Long countParentStudentTmpByCodeAndStatus(String code, Integer status);

	void updateParentStudentTmp(ParentStudentTmp tmp, int i);

	void deleteParentStudentTmpById(Integer id);

}
