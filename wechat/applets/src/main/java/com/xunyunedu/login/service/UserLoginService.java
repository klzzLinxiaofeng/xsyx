package com.xunyunedu.login.service;

import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.teacher.pojo.TeacherPojo;

import java.util.Map;

/**
 *  @author: yhc
 *  @Date: 2020/9/19 14:07
 *  @Description: 用户授权登录
 */
public interface UserLoginService {

    /**
     * 获取用户状态
     *
     * @param s
     * @param name
     * @param pwd
     * @param dataMap
     * @return
     */
    Integer getAccuontStatus(String name, String pwd, Integer type, Map<String, String> dataMap, UserPojo pojo);
    Integer getAccuontStatusYiHao(String name, String pwd, Map<String, Object> dataMap, UserPojo pojo);
    TeacherPojo getTeacher(String userName);
    /**
     * 生成token保存用户信息
     * @param name
     * @param pwd
     * @param openid
     * @return
     */
    String createToken(String name, String pwd, String openid, UserPojo pojo);

    String createTokenYiHao(String name, String pwd, UserPojo pojo);


    void modifyUserPassword(String userName, String newPwd, String oldPwd, String oldToken);

    void editMobile(String name, String mobile);

    String getPublicContentList(Integer offset, Integer count);

    String getMaterial(String mediaId);
}
