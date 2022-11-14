package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.StudentAidPublish;
import platform.education.generalTeachingAffair.vo.StudentAidPublishCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface StudentAidPublishDao extends GenericDao<StudentAidPublish, java.lang.Integer> {

	List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Page page, Order order);
	
	StudentAidPublish findById(Integer id);
	
	Long count(StudentAidPublishCondition studentAidPublishCondition);
	
	StudentAidPublish findBySchoolIdAndSchoolYear(Integer schoolId, String schoolYear);
	
}
