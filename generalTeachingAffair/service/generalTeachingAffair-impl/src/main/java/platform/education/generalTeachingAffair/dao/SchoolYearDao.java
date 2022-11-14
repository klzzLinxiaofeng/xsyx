package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolYearDao extends GenericDao<SchoolYear, java.lang.Integer> {

	List<SchoolYear> findSchoolYearByCondition(SchoolYearCondition schoolYearCondition, Page page, Order order);
	
	SchoolYear findById(Integer id);
	
	List<SchoolYear> findSchoolYearOfSchool(Integer schoolId);
	
	/**
	 * 功能描述：通过当前学年返回学年记录
	 * @param year
	 * @return
	 */
	SchoolYear findSchoolYearByYear(SchoolYearCondition schoolYearCondition);
	
	/**
	 * 根据学年返回学校记录
	 * @param schoolId
	 * @param year
	 * @return
	 */
	SchoolYear findByYearAndSchoolId(Integer schoolId, String year);
}
