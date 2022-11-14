package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Charge;
import platform.education.generalTeachingAffair.vo.ChargeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.ChargeVo;

import java.util.List;

public interface ChargeService {
    Charge findChargeById(Integer id);
	   
	Charge add(Charge charge);
	   
	Charge modify(Charge charge);
	   
	void remove(Charge charge);
	   
	List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Page page, Order order);
	
	List<Charge> findChargeByCondition(ChargeCondition chargeCondition);
	
	List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Page page);
	
	List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Order order);
	
	Long count();
	
	Long count(ChargeCondition chargeCondition);


	void addAllRecord(Integer schoolId, String schoolYear, String termCode, Integer itemId, String objectType, String objectIds);

	ChargeVo findChargeVoById(Integer id);

	Charge modifyPayment(Charge charge);

	List<ChargeVo> findChargeList(ChargeVo chargeVo, Page page);

	float[][] findBatchPayment(Integer schoolId, String termCode, Integer teamId);

	void batchSetPayment(Integer schoolId, String termCode, Integer gradeId, Integer teamId, String[][] amounts);

	List<ChargeVo> findStatDate(Integer schoolId, String schoolYear, String termCode, Integer gradeId, Integer teamId, Integer itemId);

}
