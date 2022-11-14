package platform.education.lads.dao;

import platform.education.lads.model.LadsMediaUserStatusTool;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;
import platform.education.lads.vo.mediaToolVo.LadsMediaUserStatusToolCondition;

public interface LadsMediaUserStatusToolDao extends GenericDao<LadsMediaUserStatusTool, java.lang.Integer> {

    List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Page page, Order order);

    LadsMediaUserStatusTool findById(Integer id);

    Long count(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition);

    //以下是业务方法
    LadsMediaUserStatusTool findByUuid(String uuid);
    
    List<LadsMediaUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc);
    
    Long countFinishedStatus(GetToolStatusCondition gtsc);
    
    List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
