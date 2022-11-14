package com.xunyunedu.login.service.impl;

import com.xunyunedu.login.dao.UserLoginDao;
import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserLoginDao dao;


    @Override
    public UserPojo getById(Integer id){
        return dao.selectById(id);
    }




}
