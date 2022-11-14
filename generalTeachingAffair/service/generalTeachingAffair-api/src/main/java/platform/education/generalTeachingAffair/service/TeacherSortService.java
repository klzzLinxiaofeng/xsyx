package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TeacherSort;
import platform.education.generalTeachingAffair.vo.TeacherSortCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TeacherSortService {
    TeacherSort findTeacherSortById(Integer id);
	   
	TeacherSort add(TeacherSort teacherSort);
	   
	TeacherSort modify(TeacherSort teacherSort);
	   
	void remove(TeacherSort teacherSort);
	   
	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Page page, Order order);
	
	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition);
	
	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Page page);
	
	List<TeacherSort> findTeacherSortByCondition(TeacherSortCondition teacherSortCondition, Order order);
	
	Long count();
	
	Long count(TeacherSortCondition teacherSortCondition);

	List<TeacherSort> batchAdd(List<TeacherSort> teacherSorts);

	void removeByTeacherId(Integer id);
	
}
