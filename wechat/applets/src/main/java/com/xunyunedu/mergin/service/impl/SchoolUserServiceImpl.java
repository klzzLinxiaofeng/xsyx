package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.SchoolUserDao;
import com.xunyunedu.mergin.service.SchoolUserService;
import com.xunyunedu.mergin.util.SchoolUserCondition;
import com.xunyunedu.mergin.vo.SchoolUser;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolUserServiceImpl implements SchoolUserService {
    @Autowired
    private SchoolUserDao schoolUserDao;
    @Override
    public SchoolUser modify(SchoolUser schoolUser) {
        return schoolUserDao.update(schoolUser);
    }

    @Override
    public SchoolUser findSchoolUserListByUserIdAndType(Integer userId, String userType) {
        SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
        schoolUserCondition.setUserId(userId);
        schoolUserCondition.setUserType(userType);
        schoolUserCondition.setIsDeleted(false);
        List<SchoolUser> schoolUserList = schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
        if(schoolUserList.size()>0) {
            return schoolUserList.get(0);
        }
        return null;
    }

    @Override
    public List<SchoolUser> findSchoolUserByCondition(SchoolUserCondition schoolUserCondition, Page page, Order order) {
        return schoolUserDao.findSchoolUserByCondition(schoolUserCondition, page, order);
    }

}
