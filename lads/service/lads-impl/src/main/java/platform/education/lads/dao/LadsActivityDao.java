package platform.education.lads.dao;

import platform.education.lads.model.LadsActivity;
import platform.education.lads.vo.LadsActivityCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;
import platform.education.lads.vo.LadsActivityVo;

public interface LadsActivityDao extends GenericDao<LadsActivity, java.lang.Integer> {

	List<LadsActivity> findLadsActivityByCondition(LadsActivityCondition ladsActivityCondition, Page page, Order order);
	
	LadsActivity findById(Integer id);
	
	Long count(LadsActivityCondition ladsActivityCondition);
	
	void deleteByCondition(LadsActivityCondition ladsActivityCondition);
        
        //以下是业务方法
        
        LadsActivity findByUuid(String uuid);
        
        void deleteChildrenActivity(String parentActivityId);
        
        List<String> findToolIdByToolNameAndLdid(LadsActivityVo avo);
}
