package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsRuleItem;

public interface ApsRuleItemDao extends GenericDao<ApsRuleItem, java.lang.Integer> {

	ApsRuleItem findById(Integer id);
	
	//通过ruleId找到对应模板的项目
	List<ApsRuleItem> findByRuleId(Integer ruleId);
	ApsRuleItem findRuleItembyName(String name);
	//通过项目名称和ruleID找项目列表
	List<ApsRuleItem>findApsRuleItemByNameAndRuleID(Integer ruleId,String name);

	List<ApsRuleItem> findItemsBycheckType(Integer ruleId, String checkType);


}
