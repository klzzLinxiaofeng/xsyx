package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentHealthArchive;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentHealthArchiveService {
    StudentHealthArchive findStudentHealthArchiveById(Integer id);

	List<StudentHealthArchive> findStudentHealthArchive();
	   
	StudentHealthArchive add(StudentHealthArchive studentHealthArchive);
	   
	StudentHealthArchive modify(StudentHealthArchive studentHealthArchive);
	   
	void remove(StudentHealthArchive studentHealthArchive);
	   
	List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Page page, Order order);
	
	List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition);
	
	List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Page page);
	
	List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Order order);
	
	Long count();
	
	Long count(StudentHealthArchiveCondition studentHealthArchiveCondition);
	
	void remove(StudentHealthArchiveCondition studentHealthArchiveCondition);
	
	/**
	 * 功能描述：通过pj_team.id、pj_student.id查询该学生的相关健康信息
	 * @param teamStudentId
	 * @return
	 */
	StudentHealthArchive findUnique(Integer teamId, Integer studentId);
	
}
