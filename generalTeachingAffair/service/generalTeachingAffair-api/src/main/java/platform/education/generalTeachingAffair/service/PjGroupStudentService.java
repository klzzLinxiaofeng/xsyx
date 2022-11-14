package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjGroupStudent;
import platform.education.generalTeachingAffair.vo.PjGroupStudentCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentVo;

import java.util.List;

public interface PjGroupStudentService {
    PjGroupStudent findPjGroupStudentById(Integer id);
	   
	PjGroupStudent add(PjGroupStudent pjGroupStudent);
	   
	PjGroupStudent modify(PjGroupStudent pjGroupStudent);
	   
	void remove(PjGroupStudent pjGroupStudent);
	   
	List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order);
	
	List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition);
	
	List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page);

	List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Order order);

	List<PjGroupStudentVo> findPjGroupStudentVoByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order);
	
	Long count();
	
	Long count(PjGroupStudentCondition pjGroupStudentCondition);

	List<PjGroupStudentVo> findPjGroupStudentVoByTeamId(Integer teamId);

	List<PjGroupStudentVo> findPjGroupStudentVoByTeamId(Integer teamId, Integer groupId);

	List<PjGroupStudentVo> findPjGroupStudentVoByStudentId(Integer studentId);

	void deleteByPjGroupStudentCondition(Integer groupId, Integer groupNumber);
}
