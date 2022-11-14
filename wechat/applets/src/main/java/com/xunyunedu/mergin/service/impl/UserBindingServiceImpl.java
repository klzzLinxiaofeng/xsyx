package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.UserBindingDao;
import com.xunyunedu.mergin.service.UserBindingService;
import com.xunyunedu.mergin.vo.UserBinding;
import com.xunyunedu.mergin.vo.UserBindingCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserBindingServiceImpl implements UserBindingService {
    @Autowired
    private UserBindingDao userBindingDao;
    @Override
    public void remove(UserBinding UserBinding) { userBindingDao.delete(UserBinding);
    }

    @Override
    public UserBinding add(UserBinding userBinding) {
        if (userBinding == null) {
            return null;
        } else {
            Integer userId = userBinding.getUserId();
            String bindingName = userBinding.getBindingName();
            Integer bindingType = userBinding.getBindingType();
            if (userId != null && bindingName != null && bindingType != null) {
               UserBinding perUserBinding = userBindingDao.findByBindingName(bindingName);
                if (perUserBinding != null && !perUserBinding.getBindingType().equals(1)) {
                    return perUserBinding;
                } else {
                    Date date = new Date();
                    userBinding.setCreateDate(date);
                    userBinding.setModifyDate(date);
                    return (UserBinding)userBindingDao.create(userBinding);
                }
            } else {
                return null;
            }
        }
    }


    @Override
    public List<UserBinding> findUserBindingByCondition(UserBindingCondition userBindingCondition, Page page, Order order) {
        return userBindingDao.findUserBindingByCondition(userBindingCondition, page, order);
    }
    @Override
    public UserBinding findByBindingName(String bindingName) {
        return this.userBindingDao.findByBindingName(bindingName);
    }
}
