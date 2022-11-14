package platform.education.oa.service;
import platform.education.oa.model.ApplayIndia;
import platform.education.oa.vo.ApplayIndiaCondition;
import platform.education.oa.vo.ApplayIndiaVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ApplayIndiaService {
    ApplayIndia findApplayIndiaById(Integer id);
    
    ApplayIndiaVo findApplayIndiaById1(Integer id);
	   
	ApplayIndia add(ApplayIndia applayIndia);
	   
	ApplayIndia modify(ApplayIndia applayIndia);
	   
	void remove(ApplayIndia applayIndia);
	   
	List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Page page, Order order);
	
	List<ApplayIndiaVo> findApplayIndiaByCondition1(ApplayIndiaCondition applayIndiaCondition, Page page, Order order);
	
	List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition);
	
	List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Page page);
	
	List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Order order);
	
	Long count();
	
	Long count(ApplayIndiaCondition applayIndiaCondition);
	
	/**
	 * 功能描述：逻辑上删除文印信息即数据库仍然保存记录，只是修改删除标识
	 * @param Dormitory
	 * @return
	 */
	void abandon(ApplayIndia applayIndia);

	
	
}
