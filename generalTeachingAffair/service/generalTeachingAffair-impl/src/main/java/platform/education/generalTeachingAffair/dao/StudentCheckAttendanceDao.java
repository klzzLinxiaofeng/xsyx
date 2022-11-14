package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentCheckAttendanceDao extends GenericDao<StudentCheckAttendance, java.lang.Integer> {

	 
	List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page, Order order);
	
	List<StudentCheckAttendanceVo> findGroupVoByStudentId(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page, Order order);
	
	StudentCheckAttendance findById(Integer id);
	
	List<StudentCheckAttendance> findUnique(Integer schoolId,Integer studentId,String attendanceType);
	
	Long count(StudentCheckAttendanceCondition studentCheckAttendanceCondition);

	void deleteByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition);

}
