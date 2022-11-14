package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.JobControl;
import platform.education.generalTeachingAffair.vo.JobControlCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface JobControlDao extends GenericDao<JobControl, java.lang.Integer> {

	List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition, Page page, Order order);
	
	JobControl findById(Integer id);
	
	Long count(JobControlCondition jobControlCondition);

	public JobControl findByObjectId(Integer objectId,String name);


    JobControl findByName(String name);
}
