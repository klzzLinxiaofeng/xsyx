package platform.education.oa.service;
import platform.education.oa.model.AcceptRepari;
import platform.education.oa.vo.AcceptRepariCondition;
import platform.education.oa.vo.AcceptRepariVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface AcceptRepariService {
    AcceptRepari findAcceptRepariById(Integer id);
	   
	AcceptRepari add(AcceptRepari acceptRepari);
	   
	AcceptRepari modify(AcceptRepari acceptRepari);
	   
	void remove(AcceptRepari acceptRepari);
	   
	List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Page page, Order order);
	
	List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition);
	
	List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Page page);
	
	List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Order order);
	
	Long count();
	
	Long count(AcceptRepariCondition acceptRepariCondition);

	List<AcceptRepariVo> findAcceptRepariByConditionAndGroup(AcceptRepariCondition acceptRepariCondition);
}
