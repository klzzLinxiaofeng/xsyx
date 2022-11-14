package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.StudentAidPublish;
import platform.education.generalTeachingAffair.vo.StudentAidPublishCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentAidPublishService {
    StudentAidPublish findStudentAidPublishById(Integer id);
	   
	StudentAidPublish add(StudentAidPublish studentAidPublish);
	   
	StudentAidPublish modify(StudentAidPublish studentAidPublish);
	   
	void remove(StudentAidPublish studentAidPublish);
	   
	List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Page page, Order order);
	
	List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition);
	
	List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Page page);
	
	List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Order order);
	
	Long count();
	
	Long count(StudentAidPublishCondition studentAidPublishCondition);
	
	StudentAidPublish findBySchoolIdAndSchoolYear(Integer schoolId, String schoolYear);
	
	/**
	 * 确认发布
	 */
	StudentAidPublish makeSurePublish(Integer schoolId, String schoolYear, Boolean isPublish);
	
	boolean getHasPublish(Integer schoolId, String schoolYear, Integer count);
	
}
