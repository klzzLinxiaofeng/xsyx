package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.vo.PersonCondition;
import platform.education.generalTeachingAffair.vo.PersonVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PersonDao extends GenericDao<Person, java.lang.Integer> {

	List<Person> findJwPersonByCondition(PersonCondition jwPersonCondition, Page page, Order order);
	
	Person findById(Integer id);

	PersonVo findPersonNumberOfRaceData(Integer schoolId);
	/**
	 * 查找年龄最大的一条记录
	 * @param schoolId
	 * @return
	 * @author 陈业强
	 */
	Person findPersonOfMaxAgeBySchool(Integer schoolId);
	/**
	 * 查找年龄最小的一条记录
	 * @param schoolId
	 * @return
	 * @author 陈业强
	 */
	Person findPersonOfMinAgeBySchool(Integer schoolId);
	
	List<Person> findbyIds(Integer[] ids);

    Person findPersonByStuId(Integer id);
}
