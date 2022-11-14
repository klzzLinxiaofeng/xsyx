package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.TeacherSort;
import platform.education.generalTeachingAffair.vo.TeacherSortCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface TeacherSortDao extends GenericDao<TeacherSort, java.lang.Integer> {

	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition);
	
	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Page page, Order order);
	
	TeacherSort findById(Integer id);
	
	Long count(TeacherSortCondition teacherSortCondition);
	
}
