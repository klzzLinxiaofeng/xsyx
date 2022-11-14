package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsRuleMedal;

public interface ApsRuleMedalDao extends GenericDao<ApsRuleMedal, java.lang.Integer> {

	
	ApsRuleMedal findById(Integer id);
	
	List<ApsRuleMedal> findByObjectType(String objectType);
}
