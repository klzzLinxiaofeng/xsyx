package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjTeacherSalaryField;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryFieldCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjTeacherSalaryFieldDao extends GenericDao<PjTeacherSalaryField, java.lang.Integer> {

	List<PjTeacherSalaryField> findPjTeacherSalaryFieldByCondition(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition, Page page, Order order);
	
	PjTeacherSalaryField findById(Integer id);
	
	Long count(PjTeacherSalaryFieldCondition pjTeacherSalaryFieldCondition);
	
}
