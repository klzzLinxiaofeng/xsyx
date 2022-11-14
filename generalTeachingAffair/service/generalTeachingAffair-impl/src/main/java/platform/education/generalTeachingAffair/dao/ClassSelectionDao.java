package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ClassSelection;
import platform.education.generalTeachingAffair.vo.ClassSelectionCondition;

import java.util.List;

public interface ClassSelectionDao extends GenericDao<ClassSelection, java.lang.Integer> {

	List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Page page, Order order);
	
	ClassSelection findById(Integer id);
	
	Long count(ClassSelectionCondition classSelectionCondition);
	
	/**
	 * 功能描述：通过部分条件查询出某个学生是否已经存在报名记录 (schoolId publicClassId studentId teamId isDelete)
	 * @param classSelectionCondition
	 * @return
	 */
	ClassSelection findClassSelectionByPartCondition(ClassSelectionCondition classSelectionCondition);


}
