package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.VirtualTeamStudent;
import platform.education.generalTeachingAffair.vo.VirtualTeamStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface VirtualTeamStudentDao extends GenericDao<VirtualTeamStudent, Integer> {

	List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Page page, Order order);
	
	VirtualTeamStudent findById(Integer id);
	
	Long count(VirtualTeamStudentCondition virtualTeamStudentCondition);

	List<Student> findStudentByVirtualTeamId(Integer virtualTeamId);

	List<VirtualTeamStudent> findByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds);

	VirtualTeamStudent findUnique(Integer virtualTeamId, Integer studentId);

	void batchCreate(VirtualTeamStudent[] list);

	void batchRemove(Integer[] ids);

	void batchRemoveByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds);
}
