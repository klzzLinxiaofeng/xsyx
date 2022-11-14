package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.PersonTwoDao;
import com.xunyunedu.mergin.service.PersonService;
import com.xunyunedu.mergin.vo.Person;
import com.xunyunedu.mergin.vo.PersonCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PersonTwoDao personDao;

    @Override
    public Person modify(Person person) {
        return personDao.update(person);
    }

    @Override
    public Person add(Person person) {
        return personDao.create(person);
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
    public List<Person> findPersonByCondition(PersonCondition personCondition, Page page, Order order) {
        return personDao.findJwPersonByCondition(personCondition, page, order);
    }
}
