package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.VirtualTeamStudent;
import platform.education.generalTeachingAffair.vo.VirtualTeamStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface VirtualTeamStudentService {
    VirtualTeamStudent findVirtualTeamStudentById(Integer id);
	   
	VirtualTeamStudent add(VirtualTeamStudent virtualTeamStudent);
	   
	VirtualTeamStudent modify(VirtualTeamStudent virtualTeamStudent);
	   
	void remove(VirtualTeamStudent virtualTeamStudent);
	   
	List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Page page, Order order);
	
	List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition);
	
	List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Page page);
	
	List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Order order);
	
	Long count();
	
	Long count(VirtualTeamStudentCondition virtualTeamStudentCondition);

	List<Student> findStudentByVirtualTeamId(Integer virtualTeamId);

	List<VirtualTeamStudent> findByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds);

	VirtualTeamStudent findUnique(Integer virtualTeamId, Integer studentId);

	void batchCreate(VirtualTeamStudent[] list);

	void batchRemove(Integer[] ids);

	void batchRemoveByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds);

}
