package platform.education.generalTeachingAffair.service;
import java.util.List;

import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.vo.PersonCondition;
import platform.education.generalTeachingAffair.vo.PersonVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface PersonService {
	Person findPersonById(Integer id);

	Person add(Person person);
	   
	Person modify(Person person);
	   
	void remove(Person person);
	   
	List<Person> findPersonByCondition(PersonCondition personCondition, Page page, Order order);

	PersonVo findPersonNumberOfRaceData(Integer schoolId);
	/**
	 * 获取当前学校的
	 * @param schoolId
	 * @return
	 */
	List<Integer> findPersonAgeGroupOfStudentBySchool(Integer schoolId);
	
	List<Person> findbyIds(Integer[] ids);

	/**
	 * 根据学生id获取person
	 * @param id
	 * @return
	 */
	Person findPersonByStuId(Integer id);
}
