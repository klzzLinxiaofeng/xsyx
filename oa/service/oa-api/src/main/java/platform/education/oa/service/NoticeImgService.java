package platform.education.oa.service;
import platform.education.oa.model.NoticeImg;
import platform.education.oa.vo.NoticeImgCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface NoticeImgService {
    NoticeImg findNoticeImgById(Integer id);
	   
	NoticeImg add(NoticeImg noticeImg);
	   
	NoticeImg modify(NoticeImg noticeImg);
	   
	void remove(NoticeImg noticeImg);
	   
	List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Page page, Order order);
	
	List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition);
	
	List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Page page);
	
	List<NoticeImg> findNoticeImgByCondition(NoticeImgCondition noticeImgCondition, Order order);
	
	Long count();
	
	Long count(NoticeImgCondition noticeImgCondition);
	
}
