package com.xunyunedu.mergin.service;


import platform.education.user.model.Profile;

public interface ProfileService {
    Profile findByUserId(Integer var1);

    Profile add(Profile var1);
    Profile modify(Profile var1);
}
