package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.vo.Person;
import com.xunyunedu.mergin.vo.PersonCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PersonService {
    Person modify(Person person);
    Person add(Person person);
  Person findPersonById(Integer id);
    List<Person> findPersonByCondition(PersonCondition personCondition, Page page, Order order);


}
