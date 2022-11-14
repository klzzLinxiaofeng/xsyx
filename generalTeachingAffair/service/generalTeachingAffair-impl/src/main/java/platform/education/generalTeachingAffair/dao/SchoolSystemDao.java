package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolSystemDao extends GenericDao<SchoolSystem, java.lang.Integer> {

	List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Page page, Order order);
	
	SchoolSystem findById(Integer id);
	
	Long count(SchoolSystemCondition schoolSystemCondition);
	
	SchoolSystem findUnique(Integer schoolId, String stageCode, String gradeCode);
	
	/**
	 * 功能描述：获得学制表本学校的年级
	 * @param schoolId
	 * @return
	 */
	List<SchoolSystem> findDefaultGradesOfSchool(Integer schoolId);
	
}
