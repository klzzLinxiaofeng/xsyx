package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsTask;

public interface ApsTaskDao extends GenericDao<ApsTask, java.lang.Integer> {

	ApsTask findById(Integer id);
	
	List<ApsTask> findBySchoolId(Integer schoolId, String ObjectType);
	
	//根据学期code和评价对象类型找到对应的评价任务
	ApsTask findUniqueTask(String termCode, String ObjectType);
	
}
