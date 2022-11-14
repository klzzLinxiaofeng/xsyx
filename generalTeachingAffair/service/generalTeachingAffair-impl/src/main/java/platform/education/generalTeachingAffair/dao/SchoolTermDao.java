package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermVo;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface SchoolTermDao extends GenericDao<SchoolTerm, java.lang.Integer> {

	List<SchoolTerm> findSchoolTermByCondition(SchoolTermCondition schoolTermCondition, Page page, Order order);
	
	/**
	 * 功能描述：用于关联显示学年名称
	 * @param schoolTermCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<SchoolTermVo> findSchoolTermByConditionMore(SchoolTermCondition schoolTermCondition, Page page, Order order);
	
	SchoolTerm findById(Integer id);
	
	List<SchoolTerm> findSchoolTermOfSchoolId(Integer schoolId);
	
	List<SchoolTerm> findSchoolTermOfYearBySchoolId(Integer schoolId,Integer schoolYearId);

	//通过学校id和学期号找到学期
	SchoolTerm findSchoolYearBySchoolTerm(Integer schoolId,String code);
	
	SchoolTerm findSchoolYearByToday(Integer schoolId,Date date);

}
