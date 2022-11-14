package com.xunyunedu.teacher.dao;

import com.xunyunedu.teacher.pojo.PersonPojo;

import java.util.List;

/**
 * 档案信息
 *
 * @author: yhc
 * @Date: 2020/12/8 9:15
 * @Description:
 */
public interface PersonDao {

    List<PersonPojo> findPersonByparam(PersonPojo personPojo);
    void update(PersonPojo personPojo);

    void insert(PersonPojo personPojo);
}
