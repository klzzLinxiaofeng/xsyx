package platform.education.oa.dao;

import platform.education.oa.model.ApplayIndia;
import platform.education.oa.vo.ApplayIndiaCondition;
import platform.education.oa.vo.ApplayIndiaVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ApplayIndiaDao extends GenericDao<ApplayIndia, java.lang.Integer> {

	List<ApplayIndia> findApplayIndiaByCondition(ApplayIndiaCondition applayIndiaCondition, Page page, Order order);
	
	List<ApplayIndiaVo> findApplayIndiaByCondition1(ApplayIndiaCondition applayIndiaCondition, Page page, Order order);
	
	ApplayIndia findById(Integer id);
	
	ApplayIndiaVo findById1(Integer id);
	
	Long count(ApplayIndiaCondition applayIndiaCondition);
	
}
