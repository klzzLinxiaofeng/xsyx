package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjGroupStudent;
import platform.education.generalTeachingAffair.vo.PjGroupStudentCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentVo;

import java.util.List;

public interface PjGroupStudentDao extends GenericDao<PjGroupStudent, Integer> {

	List<PjGroupStudent> findPjGroupStudentByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order);

	List<PjGroupStudentVo> findPjGroupStudentVoByCondition(PjGroupStudentCondition pjGroupStudentCondition, Page page, Order order);
	
	PjGroupStudent findById(Integer id);
	
	Long count(PjGroupStudentCondition pjGroupStudentCondition);

	List<PjGroupStudentVo> findPjGroupStudentVoByTeamId(Integer teamId, Integer groupId);

	List<PjGroupStudentVo> findPjGroupStudentVoByStudentId(Integer studentId);

	void deleteByPjGroupStudentCondition(Integer groupId, Integer groupNumber);
}
