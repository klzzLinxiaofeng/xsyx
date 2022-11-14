package platform.education.oa.service;
import platform.education.oa.model.Notice;
import platform.education.oa.model.Paper;
import platform.education.oa.vo.NoticeCondition;
import platform.education.oa.vo.PaperCondition;
import platform.education.oa.vo.PaperVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PaperService {
    Paper findPaperById(Integer id);
    
    PaperVo findPaperById1(Integer id);
	   
	Paper add(Paper paper);
	   
	Paper modify(Paper paper);
	   
	void remove(Paper paper);
	   
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page, Order order);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Page page);
	
	List<Paper> findPaperByCondition(PaperCondition paperCondition, Order order);
	
	Long count();
	
	Long count(PaperCondition paperCondition);
	public List<Paper> findPaperToUser(Integer ownerId,String ownerType, Integer userId,
			Page page, Order order) ;
	
	/**
	 * 该方法接收的参数有：
	 * 1、ownerId、ownerType、searchWord（关键字搜索）、
	 * 2、receiverType、receiverId、teacherId、posterId、isRelatedWithMe、isDepartmentRecord、
	 *   isMyPublish 这十个个，多设置没有用
	 * 3、其中 ownerId、ownerType、searchWord 可不设置
	 * 4、设置了 isRelatedWithMe（是否与我相关）则必须设置 receiverType、receiverId、teacherId、posterId 这四个参数
	 * 5、设置了 isDepartmentRecord（是否是部门记录）则必须设置 teacherId
	 * 6、设置了 isMyPublish（是否是我发布的）则必须设置 posterId
	 * 7、只设置  2 不设置 4、5、6 ，方法没有作用
	 * @param condition
	 * @param page
	 * @param order
	 * @return
	 */
	List<PaperVo> findPaperByRelated(PaperCondition condition,
			Page page, Order order);
	
}
