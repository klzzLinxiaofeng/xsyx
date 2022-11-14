package platform.education.oa.dao;

import platform.education.oa.model.ApplyCard;
import platform.education.oa.vo.ApplyCardCondition;
import platform.education.oa.vo.ApplyCardVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplyCardDao extends GenericDao<ApplyCard, java.lang.Integer> {

	List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Page page, Order order);
	
	ApplyCard findById(Integer id);
	
	Long count(ApplyCardCondition applyCardCondition);

	List<ApplyCardVo> findApplyCardAllByCondition(ApplyCardCondition condition,
			Page page, Order order);
	
}
