package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Complain;
import platform.education.generalTeachingAffair.vo.ComplainCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.ComplainVo;

import java.util.Date;
import java.util.List;

public interface ComplainDao extends GenericDao<Complain, Integer> {

	List<Complain> findComplainByCondition(ComplainCondition complainCondition, Page page, Order order);
	
	Complain findById(Integer id);
	
	Long count(ComplainCondition complainCondition);

	//获取某人的所有投诉
	List<ComplainVo> findByComplainant(Integer schoolId, Integer complainantId, String description, Page page);

	//获取全部投诉（按部门、按是否已处理）
	List<ComplainVo> findAllComplain(Integer schoolId, Integer departId, String description, Boolean isDispose, Page page);

	List<ComplainVo> findByCreateDate(Integer schoolId, Integer departId, String description, Date beginDate, Date endDate);

	ComplainVo findComplainVoById(Integer id);
}
