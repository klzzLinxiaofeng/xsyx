package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.vo.ParentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.ParentVo;

import java.util.Date;
import java.util.List;

public interface ParentDao extends GenericDao<Parent, java.lang.Integer> {

	List<Parent> findParentByCondition(ParentCondition parentCondition);
	
	List<Parent> findParentByCondition(ParentCondition parentCondition, Page page, Order order);
	
	List<Parent> findParentByCondition2(ParentCondition parentCondition, Page page, Order order);
	
	Parent findById(Integer id);
	
	Long count(ParentCondition parentCondition);
	
	/**
	 * 功能描述：通过userId查询出对应的家长信息
	 * @param userId
	 * @return
	 */
	Parent findUniqueByUserId(Integer userId);

	List<ParentVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer parentId, Boolean isGetNew);
	List<ParentStudent> findByUser(String stuName, String name, Integer state, String userName, String mobile, Page page);
	ParentVo findParentVoByUserId(Integer userId);

	List<ParentVo> findParentsByStudentUserId(Integer studentUserId);

	void updateNoRead(Parent parent, int i);
}
