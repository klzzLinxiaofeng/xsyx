package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolSystemService {
	SchoolSystem findSchoolSystemById(Integer id);

	SchoolSystem add(SchoolSystem schoolSystem);

	SchoolSystem modify(SchoolSystem schoolSystem);

	void remove(SchoolSystem schoolSystem);

	List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Page page, Order order);

	List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition);

	List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Page page);

	List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Order order);

	Long count();

	Long count(SchoolSystemCondition schoolSystemCondition);

	SchoolSystem findUnique(Integer schoolId, String stageCode, String gradeCode);
	
	/**
	 * 获得学制表本学校的年级
	 * @param schoolId
	 * @return
	 */
	List<SchoolSystem> findDefaultGradesOfSchool(Integer schoolId);

}
