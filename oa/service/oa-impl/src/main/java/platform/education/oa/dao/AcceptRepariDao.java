package platform.education.oa.dao;

import platform.education.oa.model.AcceptRepari;
import platform.education.oa.vo.AcceptRepariCondition;
import platform.education.oa.vo.AcceptRepariVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface AcceptRepariDao extends GenericDao<AcceptRepari, java.lang.Integer> {

	List<AcceptRepari> findAcceptRepariByCondition(AcceptRepariCondition acceptRepariCondition, Page page, Order order);
	
	AcceptRepari findById(Integer id);
	
	Long count(AcceptRepariCondition acceptRepariCondition);

	List<AcceptRepariVo> findAcceptRepariByConditionAndGroup(
			AcceptRepariCondition acceptRepariCondition);
	
}
