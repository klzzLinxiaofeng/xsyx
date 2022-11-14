package platform.education.oa.dao;

import platform.education.oa.model.PaperUser;
import platform.education.oa.vo.PaperUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PaperUserDao extends GenericDao<PaperUser, java.lang.Integer> {

	List<PaperUser> findPaperUserByCondition(PaperUserCondition paperUserCondition, Page page, Order order);
	
	PaperUser findById(Integer id);
	
	Long count(PaperUserCondition paperUserCondition);
	public PaperUser findPaperUserByPaperidAndUserid(Integer paperid,
			Integer userid);
}
