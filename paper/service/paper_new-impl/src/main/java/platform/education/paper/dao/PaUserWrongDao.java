package platform.education.paper.dao;

import platform.education.paper.model.PaUserWrong;
import platform.education.paper.vo.PaUserWrongCondition;
import platform.education.paper.vo.WrongPaperVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaUserWrongDao extends GenericDao<PaUserWrong, java.lang.Integer> {

	List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Page page, Order order);
	
	PaUserWrong findById(Integer id);
	
	Long count(PaUserWrongCondition paUserWrongCondition);
	
	List<WrongPaperVo> findUserWrongList(Integer userId,
				String subjectCode, Page page, Order order);
	
}
