package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.UserTwoDao;
import com.xunyunedu.mergin.service.UserServiceTwo;
import com.xunyunedu.mergin.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.user.utils.holder.PwdEncoderHolder;

import java.util.Date;

@Service
public class UserServiceTwoImpl implements UserServiceTwo {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTwoImpl.class);

    @Autowired
    private UserTwoDao userDao;

    @Override
    public User add(User user) {
        if (user != null) {
            String username = user.getUserName();
            User persistentUser = userDao.findByUsername(username);
            if (persistentUser != null) {
                user = persistentUser;
            } else {
                user.setPassword(PwdEncoderHolder.getInstance().getPwdEncoder().encodePassword(user.getPassword()));
                Date date = new Date();
                user.setModifyDate(date);
                user.setCreateDate(date);

                try {
                    user = (User)userDao.create(user);
                } catch (Exception var6) {
                    if (log.isInfoEnabled()) {
                        log.info("添加账号失败 ：" + user + ", 异常为 : ", var6);
                    }

                    user = null;
                }
            }
        }

        return user;
    }

    @Override
    public User modify(User user) {
        return (User)userDao.update(user);
    }


}
