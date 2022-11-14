package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.vo.GradeCondition;

import java.util.Date;
import java.util.List;

public interface GradeDao extends GenericDao<Grade, java.lang.Integer> {

	public List<Grade> findGradeByCondition(GradeCondition gradeCondition);
	
	public List<Grade> findGradeByCondition(GradeCondition gradeCondition, Page page, Order order);
	
	public List<Grade> findGradeByConditionTemp(GradeCondition gradeCondition, Page page, Order order);
	
	public Grade findById(Integer id);
	
	public List<Grade> findGradeBySchoolYear(String schoolYear);
	
	public List<Grade> findGrageListBySchoolId(Integer schoolId);
	
	public List<Grade> findGradeBySchoolYearAndSchoolId(Integer schoolId,String schoolYear);
	
	public Grade update(Grade grade);
	
	public List<Grade> findGradeByCode(String code);

	List<Grade> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer gradeId, Boolean isGetNew);
    List<Grade> findGradeOfSchoolYearAndTeacherId(Integer schoolId,String schoolYear,Integer teacherId);
}
