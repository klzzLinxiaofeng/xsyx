package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjStudentGroupInfo;
import platform.education.generalTeachingAffair.vo.PjStudentGroupInfoCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjStudentGroupInfoService {
    PjStudentGroupInfo findPjStudentGroupInfoById(Integer id);
	   
	PjStudentGroupInfo add(PjStudentGroupInfo pjStudentGroupInfo);
	   
	PjStudentGroupInfo modify(PjStudentGroupInfo pjStudentGroupInfo);
	   
	void remove(PjStudentGroupInfo pjStudentGroupInfo);
	   
	List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Page page, Order order);
	
	List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition);
	
	List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Page page);
	
	List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Order order);
	
	Long count();
	
	Long count(PjStudentGroupInfoCondition pjStudentGroupInfoCondition);
	
	void deleteByTeamId(Integer teamId);
	
}
