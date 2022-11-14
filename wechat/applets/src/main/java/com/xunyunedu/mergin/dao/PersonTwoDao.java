package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.Person;
import com.xunyunedu.mergin.vo.PersonCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PersonTwoDao extends GenericDao<Person, Integer> {
   Person findById(@Param("id") Integer id);
   List<Person> findJwPersonByCondition(@Param("jw") PersonCondition jwPersonCondition,
                                        @Param("page") Page page,
                                        @Param("order") Order order);

}
