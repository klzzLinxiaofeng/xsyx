package platform.education.lads.service;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.vo.mediaToolVo.LadsMediaToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsMediaToolService {
    LadsMediaTool findLadsMediaToolById(Integer id);
	   
	LadsMediaTool add(LadsMediaTool ladsMediaTool);
	   
	LadsMediaTool modify(LadsMediaTool ladsMediaTool);
	   
	void remove(LadsMediaTool ladsMediaTool);
	   
	List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Page page, Order order);
	
	List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition);
	
	List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Page page);
	
	List<LadsMediaTool> findLadsMediaToolByCondition(LadsMediaToolCondition ladsMediaToolCondition, Order order);
	
	Long count();
	
	Long count(LadsMediaToolCondition ladsMediaToolCondition);
	
}
