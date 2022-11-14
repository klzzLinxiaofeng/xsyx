package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjStudentGroupInfo;
import platform.education.generalTeachingAffair.vo.PjStudentGroupInfoCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjStudentGroupInfoDao extends GenericDao<PjStudentGroupInfo, java.lang.Integer> {

	List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Page page, Order order);
	
	PjStudentGroupInfo findById(Integer id);
	
	Long count(PjStudentGroupInfoCondition pjStudentGroupInfoCondition);
	
	void deleteByTeamId(Integer teamId);
	
}
