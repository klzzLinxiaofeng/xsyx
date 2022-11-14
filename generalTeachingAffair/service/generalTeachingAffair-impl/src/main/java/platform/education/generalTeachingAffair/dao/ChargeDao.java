package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Charge;
import platform.education.generalTeachingAffair.vo.ChargeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.ChargeVo;

import java.util.List;

public interface ChargeDao extends GenericDao<Charge, Integer> {

	List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Page page, Order order);
	
	Charge findById(Integer id);

	Long count(ChargeCondition chargeCondition);

	Charge findUniqueStudent(Integer itemId, String termCode, Integer teamId, Integer studentId);

	ChargeVo findChargeVoById(Integer id);

	//List<ChargeVo> findChargeVo(Integer schoolId, String termCode, Integer gradeId, Integer teamId, Integer itemId, Boolean isPay, String studentName, Page page);
	List<ChargeVo> findByChargeVo(ChargeVo chargeVo, Page page);

	//获取单个学生所有收费项目的记录，未缴费的金额数为0
	List<ChargeVo> findAllItemByStudent(Integer schoolId, String termCode, Integer teamId, Integer studentId);

	List<ChargeVo> findStatDate(Integer schoolId, String schoolYear, String termCode, Integer gradeId, Integer teamId, Integer itemId);

	
}
