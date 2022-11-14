package platform.education.generalTeachingAffair.dao;


import java.util.List;

import platform.education.generalTeachingAffair.model.StudentAid;
import platform.education.generalTeachingAffair.vo.StudentAidCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface StudentAidDao extends GenericDao<StudentAid, java.lang.Integer> {

	List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Page page, Order order);

	StudentAid findById(Integer id);

	Long count(StudentAidCondition StudentAidCondition);
}
