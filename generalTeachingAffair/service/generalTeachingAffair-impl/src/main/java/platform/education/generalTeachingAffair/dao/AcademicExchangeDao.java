package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.AcademicExchange;
import platform.education.generalTeachingAffair.vo.AcademicExchangeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface AcademicExchangeDao extends GenericDao<AcademicExchange, java.lang.Integer> {

	List<AcademicExchange> findAcademicExchangeByCondition(AcademicExchangeCondition academicExchangeCondition, Page page, Order order);
	
	AcademicExchange findById(Integer id);
	
	Long count(AcademicExchangeCondition academicExchangeCondition);

	List<AcademicExchange> findAcademicExchangeByNameAndSchool(String name,
			Integer schoolId);
	
}
