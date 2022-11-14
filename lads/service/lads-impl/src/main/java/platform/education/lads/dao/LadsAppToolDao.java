package platform.education.lads.dao;

import platform.education.lads.model.LadsAppTool;
import platform.education.lads.vo.LadsAppToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsAppToolDao extends GenericDao<LadsAppTool, java.lang.Integer> {

	List<LadsAppTool> findLadsAppToolByCondition(LadsAppToolCondition ladsAppToolCondition, Page page, Order order);
	
	LadsAppTool findById(Integer id);
	
	Long count(LadsAppToolCondition ladsAppToolCondition);
	
	void deleteByCondition(LadsAppToolCondition ladsAppToolCondition);
        
        LadsAppTool findByUuid(String uuid);
}
