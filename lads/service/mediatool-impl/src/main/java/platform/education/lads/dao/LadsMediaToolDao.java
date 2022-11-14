package platform.education.lads.dao;

import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.vo.mediaToolVo.LadsMediaToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsMediaToolDao extends GenericDao<LadsMediaTool, java.lang.Integer> {

	List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Page page, Order order);
	
	LadsMediaTool findById(Integer id);
	
	Long count(LadsMediaToolCondition ladsMediaToolCondition);
	
}
