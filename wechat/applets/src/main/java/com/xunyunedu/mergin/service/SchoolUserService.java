package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.util.SchoolUserCondition;
import com.xunyunedu.mergin.vo.SchoolUser;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolUserService {
    SchoolUser findSchoolUserListByUserIdAndType(Integer userId, String type);
    SchoolUser modify(SchoolUser schoolUser);
    List<SchoolUser> findSchoolUserByCondition(SchoolUserCondition schoolUserCondition, Page page, Order order);

}
