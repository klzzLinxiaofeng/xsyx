package platform.education.oa.service;
import platform.education.oa.model.ApplyCard;
import platform.education.oa.vo.ApplyCardCondition;
import platform.education.oa.vo.ApplyCardVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplyCardService {
    ApplyCard findApplyCardById(Integer id);
	   
	ApplyCard add(ApplyCard applyCard);
	   
	ApplyCard modify(ApplyCard applyCard);
	   
	void remove(ApplyCard applyCard);
	   
	List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Page page, Order order);
	
	List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition);
	
	List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Page page);
	
	List<ApplyCard> findApplyCardByCondition(ApplyCardCondition applyCardCondition, Order order);
	
	Long count();
	
	Long count(ApplyCardCondition applyCardCondition);

	List<ApplyCardVo> findApplyCardAllByCondition(ApplyCardCondition condition, Page page, Order order);

}
