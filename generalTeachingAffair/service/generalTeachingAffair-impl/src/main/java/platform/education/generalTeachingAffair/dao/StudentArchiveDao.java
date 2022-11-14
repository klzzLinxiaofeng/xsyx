package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentArchive;
import platform.education.generalTeachingAffair.vo.StudentArchiveCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentArchiveDao extends GenericDao<StudentArchive, java.lang.Integer> {

	List<StudentArchive> findStudentArchiveByCondition(StudentArchiveCondition studentArchiveCondition, Page page, Order order);
	
	StudentArchive findById(Integer id);
	
	Long count(StudentArchiveCondition studentArchiveCondition);

	StudentArchive findByStudentId(Integer studentId);
}
