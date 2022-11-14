package platform.education.lads.dao;

import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.vo.LadsLearningdesignCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsLearningdesignDao extends GenericDao<LadsLearningdesign, java.lang.Integer> {

	List<LadsLearningdesign> findLadsLearningdesignByCondition(LadsLearningdesignCondition ladsLearningdesignCondition, Page page, Order order);
	
	LadsLearningdesign findById(Integer id);
	
	Long count(LadsLearningdesignCondition ladsLearningdesignCondition);
	
	void deleteByCondition(LadsLearningdesignCondition ladsLearningdesignCondition);
        
        //以下是业务方法
        
        LadsLearningdesign findByUuid(String uuid);
        
        List<String> findUserIdByLdId(String ldId);
}
