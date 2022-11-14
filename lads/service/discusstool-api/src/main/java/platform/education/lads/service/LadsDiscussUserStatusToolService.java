package platform.education.lads.service;

import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;

public interface LadsDiscussUserStatusToolService {

    LadsDiscussUserStatusTool findLadsDiscussUserStatusToolById(Integer id);

    LadsDiscussUserStatusTool add(LadsDiscussUserStatusTool ladsDiscussUserStatusTool);

    LadsDiscussUserStatusTool modify(LadsDiscussUserStatusTool ladsDiscussUserStatusTool);

    void remove(LadsDiscussUserStatusTool ladsDiscussUserStatusTool);

    List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Page page, Order order);

    List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition);

    List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Page page);

    List<LadsDiscussUserStatusTool> findLadsDiscussUserStatusToolByCondition(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition, Order order);

    Long count();

    Long count(LadsDiscussUserStatusToolCondition ladsDiscussUserStatusToolCondition);
    
    //以下是业务方法
    LadsDiscussUserStatusTool findLadsDiscussUserStatusToolByUuid(String uuid);
    
    LadsDiscussUserStatusTool findUserStatusByToolIdAndUserId(GetToolCondition egsc);
    
    List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
