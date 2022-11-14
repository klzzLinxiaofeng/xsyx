package platform.education.lads.dao;

import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsDiscussToolDao extends GenericDao<LadsDiscussTool, java.lang.Integer> {

	List<LadsDiscussTool> findLadsDiscussToolByCondition(LadsDiscussToolCondition ladsDiscussToolCondition, Page page, Order order);
	
	LadsDiscussTool findById(Integer id);
	
	Long count(LadsDiscussToolCondition ladsDiscussToolCondition);
        
        //以下是业务方法
        LadsDiscussTool findByUuid(String uuid);
	
}
