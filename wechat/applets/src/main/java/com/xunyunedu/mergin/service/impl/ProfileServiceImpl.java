package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.ProfileDao;
import com.xunyunedu.mergin.dao.UserTwoDao;
import com.xunyunedu.mergin.service.ProfileService;
import com.xunyunedu.mergin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.user.model.Profile;

import java.util.Date;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserTwoDao userDao;

    @Override
    public Profile findByUserId(Integer userId) {
        return this.profileDao.findByUserId(userId);
    }
    @Override
    public Profile add(Profile profile) {
        Date date = new Date();
        if (profile == null) {
            return null;
        } else {
            Integer userId = profile.getUserId();
            String userName = profile.getUserName();
            if (userId == null && userName == null) {
                return null;
            } else {
                Profile perProfile = this.profileDao.findByUserId(userId);
                if (perProfile != null) {
                    return perProfile;
                } else {
                    perProfile = this.profileDao.findByUserName(userName);
                    if (perProfile != null) {
                        return perProfile;
                    } else {
                        profile.setCreateDate(date);
                        profile.setModifyDate(date);
                        return (Profile)this.profileDao.create(profile);
                    }
                }
            }
        }
    }
    @Override
    public Profile modify(Profile profile) {
        Date date = new Date();
        if (profile == null) {
            return null;
        } else if (profile.getId() != null) {
            profile.setModifyDate(date);
            return (Profile)this.profileDao.update(profile);
        } else {
            if (profile.getUserId() != null) {
                Integer userId = profile.getUserId();
                String username = profile.getUserName();
                Profile perProfile = this.profileDao.findByUserId(userId);
                User user = new User();
                user.setId(userId);
                user.setModifyDate(new Date());
                userDao.update(user);
                if (perProfile != null && username != null) {
                    if (username.equals(perProfile.getUserName())) {
                        Integer id = perProfile.getId();
                        profile.setId(id);
                        profile.setModifyDate(date);
                        return (Profile)this.profileDao.update(profile);
                    }

                    return null;
                }
            }

            profile.setCreateDate(date);
            profile.setModifyDate(date);
            return this.add(profile);
        }
    }


}
