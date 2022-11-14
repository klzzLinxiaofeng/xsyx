package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskUserDao extends GenericDao<PjAptsTaskUser, java.lang.Integer> {

	List<PjAptsTaskUser> findPjAptsTaskUserByCondition(PjAptsTaskUserCondition pjAptsTaskUserCondition, Page page, Order order);
	
	PjAptsTaskUser findById(Integer id);
	
	Long count(PjAptsTaskUserCondition pjAptsTaskUserCondition);
	
	List<PjAptsTaskUserVo>findPjAptsTaskUserByJudgeId(Integer judgeId,Page page,Order order);

    List<PjAptsTaskUserVo> findPjAptsTaskUserVoByCondition(PjAptsTaskUserCondition condition, Page page, Order order);
    
     PjAptsTaskUserVo  findAptsTaskUserVoById(Integer id);
     
     List<PjAptsTaskUserVo> findAptsTaskUserVoByUserId(String termCode,Integer type,Integer userId, Page page, Order order);
}
