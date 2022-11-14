package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Complain;
import platform.education.generalTeachingAffair.vo.ComplainCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.ComplainVo;

import java.util.Date;
import java.util.List;

public interface ComplainService {
    Complain findComplainById(Integer id);
	   
	Complain add(Complain complain);
	   
	Complain modify(Complain complain);
	   
	void remove(Complain complain);
	   
	List<Complain> findComplainByCondition(ComplainCondition complainCondition, Page page, Order order);
	
	List<Complain> findComplainByCondition(ComplainCondition complainCondition);
	
	List<Complain> findComplainByCondition(ComplainCondition complainCondition, Page page);
	
	List<Complain> findComplainByCondition(ComplainCondition complainCondition, Order order);
	
	Long count();
	
	Long count(ComplainCondition complainCondition);

	/**
	 * 获取某个教师的投诉
	 * @param complainantId  投诉人userId
	 * @param description	问题描述/问题类别
	 */
	List<ComplainVo> findByComplainant(Integer schoolId, Integer complainantId, String description, Page page);

	/**
	 * 获取全部投诉
	 * @param description	问题描述/问题类别
	 * @param isDispose	是否已处理
	 */
	List<ComplainVo> findAllComplain(Integer schoolId, Integer departId, String description, Boolean isDispose, Page page);

	/**
	 * 已处理数据统计
	 */
	List<ComplainVo> findByCreateDate(Integer schoolId, Integer departId, String description, Date beginDate, Date endDate);

	Complain addComplain(Complain complain, String fileUUIDs);

	Complain modifyComplain(Complain complain, String fileUUIDs, boolean isChange);

	ComplainVo findComplainVoById(Integer id);
	
}
