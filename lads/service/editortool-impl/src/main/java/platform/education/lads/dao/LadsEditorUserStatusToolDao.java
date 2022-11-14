package platform.education.lads.dao;

import platform.education.lads.model.LadsEditorUserStatusTool;
import platform.education.lads.vo.editortoolVo.LadsEditorUserStatusToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;

public interface LadsEditorUserStatusToolDao extends GenericDao<LadsEditorUserStatusTool, java.lang.Integer> {

	List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Page page, Order order);
	
	LadsEditorUserStatusTool findById(Integer id);
	
	Long count(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition);
        
        //以下是业务方法
        LadsEditorUserStatusTool findByUuid(String uuid);
        
        List<LadsEditorUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc);
        
	Long countFinishedStatus(GetToolStatusCondition gtsc);
        
        List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
