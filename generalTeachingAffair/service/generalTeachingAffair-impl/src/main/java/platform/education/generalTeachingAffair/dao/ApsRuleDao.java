package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsRule;

public interface ApsRuleDao extends GenericDao<ApsRule, java.lang.Integer> {

	ApsRule findById(Integer id);
	
	ApsRule findBySchoolId(Integer schoolId, String ObjectType);
	
	
}
