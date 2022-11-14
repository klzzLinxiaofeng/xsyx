package platform.education.lads.dao;

import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;

public interface LadsDiscussUserStatusToolDao extends GenericDao<LadsDiscussUserStatusTool, java.lang.Integer> {

	List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Page page, Order order);
	
	LadsDiscussUserStatusTool findById(Integer id);
	
	Long count(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition);
	
        //以下是业务方法
        LadsDiscussUserStatusTool findByUuid(String uuid);
        
        LadsDiscussUserStatusTool findUserStatusByToolIdAndUserId(GetToolCondition egsc);
        
        List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
