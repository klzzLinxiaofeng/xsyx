package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface PjAptsTaskUserService {
    PjAptsTaskUser findPjAptsTaskUserById(Integer id);
	   
	PjAptsTaskUser add(PjAptsTaskUser pjAptsTaskUser);
	   
	PjAptsTaskUser modify(PjAptsTaskUser pjAptsTaskUser);
	   
	void remove(PjAptsTaskUser pjAptsTaskUser);
	   
	List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Page page, Order order);
	
	List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition);
	
	List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Page page);
	
	List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskUserCondition pjAptsTaskUserCondition);
	
	List<PjAptsTaskUserVo>findPjAptsTaskUserByJudgeId(Integer judgeId,Page page,Order order);

	List<PjAptsTaskUserVo> findPjAptsTaskUserVoByCondition(PjAptsTaskUserCondition condition, Page page, Order order);
	
	PjAptsTaskUserVo  findAptsTaskUserVoById(Integer id);
	
	List<PjAptsTaskUserVo> findAptsTaskUserVoByUserId(String termCode,Integer type,Integer userId, Page page, Order order);
}
