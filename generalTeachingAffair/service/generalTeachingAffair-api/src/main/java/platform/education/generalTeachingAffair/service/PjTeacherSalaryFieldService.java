package platform.education.generalTeachingAffair.service;
import java.util.List;

import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface PjTeacherSalaryFieldService {
    PjTeacherSalaryField findPjTeacherSalaryFieldById(Integer id);
	   
	PjTeacherSalaryField add(PjTeacherSalaryField pjTeacherSalaryField);
	   
	PjTeacherSalaryField modify(PjTeacherSalaryField pjTeacherSalaryField);
	   
	void remove(PjTeacherSalaryField pjTeacherSalaryField);
	   
	List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Page page, Order order);
	
	List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition);
	
	List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Page page);
	
	List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Order order);
	
	Long count();
	
	Long count(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition);
	
}
