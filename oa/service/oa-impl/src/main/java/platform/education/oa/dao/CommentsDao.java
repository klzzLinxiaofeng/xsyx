package platform.education.oa.dao;

import platform.education.oa.model.Comments;
import platform.education.oa.vo.CommentsCondition;
import platform.education.oa.vo.CommentsVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface CommentsDao extends GenericDao<Comments, java.lang.Integer> {

	List<Comments> findCommentsByCondition(CommentsCondition commentsCondition, Page page, Order order);
	
	Comments findById(Integer id);
	
	Long count(CommentsCondition commentsCondition);
	List<Comments> findCommentsByMeeting(Integer meetingId,Integer new_or_old,String baseline_date,Page page, Order order);

	List<CommentsVo> findCommentsVoByCondition(
			CommentsCondition commentsCondition, Page page, Order order);
	
}
