package platform.education.oa.service;
import platform.education.oa.model.Comments;
import platform.education.oa.vo.CommentsCondition;
import platform.education.oa.vo.CommentsVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface CommentsService {
    Comments findCommentsById(Integer id);
	   
	Comments add(Comments comments);
	   
	Comments modify(Comments comments);
	   
	void remove(Comments comments);
	   
	List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Page page, Order order);
	
	List<Comments> findCommentsByCondition(CommentsCondition commentsCondition);
	
	List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Page page);
	
	List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Order order);
	
	Long count();
	
	Long count(CommentsCondition commentsCondition);
	
	List<Comments> findCommentsByMeeting(Integer meetingId,Integer new_or_old,String baseline_date,Page page, Order order);
	
	List<CommentsVo> findCommentsVoByCondition(CommentsCondition commentsCondition, Page page, Order order);
}
