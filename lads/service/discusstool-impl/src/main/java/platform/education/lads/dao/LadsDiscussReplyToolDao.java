package platform.education.lads.dao;

import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyToolCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussAttachmentResult;

public interface LadsDiscussReplyToolDao extends GenericDao<LadsDiscussReplyTool, java.lang.Integer> {

	List<LadsDiscussReplyTool> findLadsDiscussReplyToolByCondition(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order);
	
	LadsDiscussReplyTool findById(Integer id);
	
	Long count(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition);
	
        //以下是业务方法
        LadsDiscussReplyTool findByUuid(String uuid);
        
        List<LadsDiscussAttachmentResult> findAttachMentByDiscussIdAndUserId(LadsDiscussReplyToolCondition ladsDiscussReplyToolCondition, Page page, Order order);
        
        List<LadsDiscussReplyTool> findReplyListByToolId(String toolId, Page page, Order order);
        
        List<LadsDiscussReplyTool> findReplyListByToolIdAndUserId(GetToolCondition gtc, Page page, Order order);
        
        Long countFinishedStatus(CountFinishedStatusCondition cfsc);
}
