package platform.education.paper.dao;

import platform.education.paper.model.PaWrong;
import platform.education.paper.vo.PaWrongCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import java.util.List;

public interface PaWrongDao extends GenericDao<PaWrong, java.lang.Integer> {

	List<PaWrong> findPaWrongByCondition(PaWrongCondition paWrongCondition, Page page, Order order);
	
	PaWrong findById(Integer id);
	
	Long count(PaWrongCondition paWrongCondition);
	
	void deleteByCondition(PaWrongCondition paWrongCondition);
        
        //以下是业务方法
        PaWrong findByUuid(String uuid);
}
