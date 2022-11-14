package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.vo.SchoolTermCurrentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolTermCurrentDao extends GenericDao<SchoolTermCurrent, java.lang.Integer> {

	List<SchoolTermCurrent> findSchoolTermCurrentByCondition(SchoolTermCurrentCondition schoolTermCurrentCondition, Page page, Order order);
	
	SchoolTermCurrent findById(Integer id);
	
	List<SchoolTermCurrent> findCurrentSchoolYearById(Integer schoolId);

	String setCurrentSchoolTerm(Integer schoolTermId);
	
	/**
	 * 功能描述： 通过学校ID查找该学校当前学期
	 * @param schoolId
	 * @return
	 */
	SchoolTermCurrent findSchoolTermCurrentBySchoolId(Integer schoolId);
	
	SchoolTermCurrent findSchoolTermCurrentBySchoolIdAndSchoolYearId(Integer schoolId,Integer schoolYearId);
}
