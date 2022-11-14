package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.PersonDao;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.vo.PersonCondition;
import platform.education.generalTeachingAffair.vo.PersonVo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private PersonDao personDao;


    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findPersonById(Integer id) {
        try {
            return personDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public Person add(Person person) {
        return personDao.create(person);
    }

    @Override
    public Person modify(Person person) {
        return personDao.update(person);
    }

    @Override
    public void remove(Person person) {
        try {
            personDao.delete(person);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("删除数据库无存在ID为 {} ");
        }

    }

    @Override
    public List<Person> findPersonByCondition(PersonCondition personCondition, Page page, Order order) {
        return personDao.findJwPersonByCondition(personCondition, page, order);
    }

    @Override
    public PersonVo findPersonNumberOfRaceData(Integer schoolId) {
        return personDao.findPersonNumberOfRaceData(schoolId);
    }

    @Override
    public List<Integer> findPersonAgeGroupOfStudentBySchool(Integer schoolId) {
        List<Integer> list = null;
        Integer maxAge = null;
        Integer minAge = null;
        Person maxAgePerson = personDao.findPersonOfMaxAgeBySchool(schoolId);
        if (maxAgePerson != null) {
            if (maxAgePerson.getId() != null) {
                maxAge = getAge(maxAgePerson.getBirthday());
            }
            Person minAgePerson = personDao.findPersonOfMinAgeBySchool(schoolId);
            if (minAgePerson.getId() != null) {
                minAge = getAge(minAgePerson.getBirthday());
            }
            if (maxAge != null && minAge != null) {
                list = getAgeGroup(maxAge, minAge);
            }
        }
        return list;
    }

    /**
     * 根据出生日期获得年龄
     *
     * @param birthDate
     * @return
     */
    private static Integer getAge(Date birthDate) {
        Integer age = null;
        if (birthDate == null) {
            throw new RuntimeException("出生日期不能为null");
        } else {
            Date now = new Date();
            SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
            SimpleDateFormat format_M = new SimpleDateFormat("MM");
            String birth_year = format_y.format(birthDate);
            String this_year = format_y.format(now);
            String birth_month = format_M.format(birthDate);
            String this_month = format_M.format(now);
            // 初步，估算
            age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
            // 如果未到出生月份，则age - 1
            if (this_month.compareTo(birth_month) < 0)
                age -= 1;
            if (age < 0)
                age = 0;
        }
        return age;
    }

    private List<Integer> getAgeGroup(int maxAge, int minAge) {
        List<Integer> ages = new ArrayList<Integer>();
        if (maxAge - minAge < 4) {
            for (int i = minAge; i <= maxAge; i++) {
                ages.add(i);
            }
        } else {
            //取中间年龄
            int average = (maxAge + minAge) % 2 == 0 ? (maxAge + minAge) / 2 : (maxAge + minAge + 1) / 2;
            ages.add(average - 1);
            ages.add(average - 2);
            ages.add(average);
            ages.add(average + 1);
        }
        return ages;
    }

    @Override
    public List<Person> findbyIds(Integer[] ids) {
        List<Person> personList = null;
        if (ids.length > 0) {
            personList = personDao.findbyIds(ids);
        }
        return personList;
    }

    @Override
    public Person findPersonByStuId(Integer id) {
        try {
            return personDao.findPersonByStuId(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

}
