package platform.education.lads.service;

import platform.education.lads.model.LadsMediaUserStatusTool;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;
import platform.education.lads.vo.mediaToolVo.LadsMediaUserStatusToolCondition;

public interface LadsMediaUserStatusToolService {

    LadsMediaUserStatusTool findLadsMediaUserStatusToolById(Integer id);

    LadsMediaUserStatusTool add(LadsMediaUserStatusTool ladsMediaUserStatusTool);

    LadsMediaUserStatusTool modify(LadsMediaUserStatusTool ladsMediaUserStatusTool);

    void remove(LadsMediaUserStatusTool ladsMediaUserStatusTool);

    List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Page page, Order order);

    List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition);

    List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Page page);

    List<LadsMediaUserStatusTool> findLadsMediaUserStatusToolByCondition(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition, Order order);

    Long count();

    Long count(LadsMediaUserStatusToolCondition ladsMediaUserStatusToolCondition);

    //以下是业务方法
    LadsMediaUserStatusTool findLadsMediaUserStatusToolByUuid(String uuid);
    
    List<LadsMediaUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc);
    
    Long countFinishedStatus(GetToolStatusCondition gtsc);
    
    List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
