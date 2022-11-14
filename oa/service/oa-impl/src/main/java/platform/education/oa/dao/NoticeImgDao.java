package platform.education.oa.dao;

import platform.education.oa.model.NoticeImg;
import platform.education.oa.vo.NoticeImgCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface NoticeImgDao extends GenericDao<NoticeImg, java.lang.Integer> {

	List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Page page, Order order);
	
	NoticeImg findById(Integer id);
	
	Long count(NoticeImgCondition noticeImgCondition);
	
}
