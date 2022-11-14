package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentHealthArchive;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentHealthArchiveDao extends GenericDao<StudentHealthArchive, java.lang.Integer> {

	List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Page page, Order order);
	
	StudentHealthArchive findById(Integer id);
	
	Long count(StudentHealthArchiveCondition studentHealthArchiveCondition);
	
	void deleteByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition);
	
	/**
	 * 功能描述：通过pj_team.id、pj_student.id查询该学生的相关健康信息
	 * @param teamStudentId
	 * @return
	 */
	StudentHealthArchive findUnique(Integer teamId, Integer studentId);

	List<StudentHealthArchive> findStudentHealthArchive();
}
