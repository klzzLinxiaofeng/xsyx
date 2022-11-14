package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.user.model.Group;
import platform.education.user.model.User;
import platform.education.user.vo.GroupCondition;

public interface SchoolDao extends GenericDao<School, java.lang.Integer> {

	List<School> findSchoolByCondition(SchoolCondition schoolCondition, Page page, Order order);

	School findById(Integer id);

	public List<School> findSchoolByName(String name);

	public List<School> findSchoolByCode(String code);

	public List<School> findSchoolOfRegionByCondition(Integer appId, String regionCode, Page page, Order order);

	public SchoolInfo findSchoolInfoById(Integer id);

	List<SchoolInfo> findSchoolInfoByCondition(SchoolCondition condition, Page page, Order order);

	List<User> findUserBySchoolAndGroupAndRoleId(SchoolCondition schoolCondition, Group group, Integer roleId, Page page, Order order);

	List<Group> findGroupBySchoolCondition(GroupCondition groupCondition, SchoolCondition schoolCondition, Page page, Order order);

	List<School> findIncrementData(Boolean isDelete, Date modifyDate, Integer id, Boolean isGetNew);
}
