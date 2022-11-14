package platform.education.lads.service;

import platform.education.lads.model.LadsEditorUserStatusTool;
import platform.education.lads.vo.editortoolVo.LadsEditorUserStatusToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;

public interface LadsEditorUserStatusToolService {

    LadsEditorUserStatusTool findLadsEditorUserStatusToolById(Integer id);

    LadsEditorUserStatusTool add(LadsEditorUserStatusTool ladsEditorUserStatusTool);

    LadsEditorUserStatusTool modify(LadsEditorUserStatusTool ladsEditorUserStatusTool);

    void remove(LadsEditorUserStatusTool ladsEditorUserStatusTool);

    List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Page page, Order order);

    List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition);

    List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Page page);

    List<LadsEditorUserStatusTool> findLadsEditorUserStatusToolByCondition(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition, Order order);

    Long count();

    Long count(LadsEditorUserStatusToolCondition ladsEditorUserStatusToolCondition);

    //以下是业务方法
    LadsEditorUserStatusTool findLadsEditorUserStatusToolByUuid(String uuid);
    
    List<LadsEditorUserStatusTool> findUserStatusByToolIdAndUserId(GetToolCondition egsc);
    
    Long countFinishedStatus(GetToolStatusCondition gtsc);
    
    List<String> findScoreByToolIdAndUserId(CountFinishedStatusCondition cfsc);
}
