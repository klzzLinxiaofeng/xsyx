package platform.education.lads.dao;

import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.vo.editortoolVo.LadsEditorToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsEditorToolDao extends GenericDao<LadsEditorTool, java.lang.Integer> {

	List<LadsEditorTool> findLadsEditorToolByCondition(LadsEditorToolCondition ladsEditorToolCondition, Page page, Order order);
	
	LadsEditorTool findById(Integer id);
	
	Long count(LadsEditorToolCondition ladsEditorToolCondition);
	
}
