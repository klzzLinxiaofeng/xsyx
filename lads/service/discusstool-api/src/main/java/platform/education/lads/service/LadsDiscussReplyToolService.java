package platform.education.lads.service;

import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussAttachmentResult;

public interface LadsDiscussReplyToolService {

    LadsDiscussReplyTool findLadsDiscussReplyToolById(Integer id);

    LadsDiscussReplyTool add(LadsDiscussReplyTool ladsDiscussReplyTool);

    LadsDiscussReplyTool modify(LadsDiscussReplyTool ladsDiscussReplyTool);

    void remove(LadsDiscussReplyTool ladsDiscussReplyTool);

    List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order);

    List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition);

    List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page);

    List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Order order);

    Long count();

    Long count(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition);

    //以下是业务方法
    LadsDiscussReplyTool findLadsDiscussReplyToolByUuid(String uuid);
    
    List<LadsDiscussAttachmentResult> findAttachMentByDiscussIdAndUserId(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order);
    
    List<LadsDiscussReplyTool> findReplyListByToolId(String toolId, Page page, Order order);
    
    List<LadsDiscussReplyTool> findReplyListByToolIdAndUserId(GetToolCondition gtc, Page page, Order order);
    
    Long countFinishedStatus(CountFinishedStatusCondition cfsc);
}
