package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentCheckAttendanceService {
    StudentCheckAttendance findStudentCheckAttendanceById(Integer id);

    List<StudentCheckAttendance> findUnique(Integer schoolId,Integer studentId,String attendanceType);
    
	StudentCheckAttendance add(StudentCheckAttendance studentCheckAttendance);
	   
	StudentCheckAttendance modify(StudentCheckAttendance studentCheckAttendance);
	   
	void remove(StudentCheckAttendance studentCheckAttendance);
	   
	List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page, Order order);
	
	List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition);
	
	List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page);
	
	List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Order order);
	
	Long count();
	
	Long count(StudentCheckAttendanceCondition studentCheckAttendanceCondition);

	List<StudentCheckAttendanceVo> findGroupVoByStudentId(StudentCheckAttendanceCondition studentCheckAttendanceCondition,Page page, Order order);
	
	
	/**
	 * 功能描述：逻辑上删除学生考勤信息即数据库仍然保存记录，只是修改删除标识
	 * @param StudentCheckAttendance
	 * @return
	 */
	void abandon(StudentCheckAttendance studentCheckAttendance);
}
