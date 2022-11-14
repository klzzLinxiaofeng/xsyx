package platform.education.lads.dao;

import platform.education.lads.model.LadsPreviewJson;
import platform.education.lads.vo.LadsPreviewJsonCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsPreviewJsonDao extends GenericDao<LadsPreviewJson, java.lang.Integer> {

	List<LadsPreviewJson> findLadsPreviewJsonByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition, Page page, Order order);
	
	LadsPreviewJson findById(Integer id);
	
	Long count(LadsPreviewJsonCondition ladsPreviewJsonCondition);
	
	void deleteByCondition(LadsPreviewJsonCondition ladsPreviewJsonCondition);
        
        LadsPreviewJson findByUuid(String uuid);
}
