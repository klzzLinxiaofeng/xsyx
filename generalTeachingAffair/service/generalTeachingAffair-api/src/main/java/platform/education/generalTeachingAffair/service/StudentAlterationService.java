package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentAlteration;
import platform.education.generalTeachingAffair.vo.StudentAlterationCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentAlterationService {
    StudentAlteration findStudentAlterationById(Integer id);
	   
	StudentAlteration add(StudentAlteration studentAlteration);
	   
	StudentAlteration modify(StudentAlteration studentAlteration);
	   
	void remove(StudentAlteration studentAlteration);
	   
	List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Page page, Order order);
	
	List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition);
	
	List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Page page);
	
	List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Order order);
	
	Long count();
	
	Long count(StudentAlterationCondition studentAlterationCondition);
	
	/**
	 * 获取某班某学生的变更记录
	 * @param teamId	
	 * @param studentId
	 * @param alterType	 等于11（招生）时可以没有班级
	 * @return
	 */
	StudentAlteration findStudentRecord(Integer teamId, Integer studentId, String alterType);
	
}
