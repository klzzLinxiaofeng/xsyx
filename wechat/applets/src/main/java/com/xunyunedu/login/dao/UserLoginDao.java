package com.xunyunedu.login.dao;

import com.xunyunedu.login.pojo.RolePojo;
import com.xunyunedu.login.pojo.UserBindingPojo;
import com.xunyunedu.login.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/9/19 14:19
 * @Description: 获取用户状态
 */
public interface UserLoginDao {


    /**
     * 根据名称查询用户信息
     *
     * @param name
     * @return
     */
    List<UserPojo> getUserInfoByName(@Param("name") String name, @Param("bindType") Integer bindType);

    List<RolePojo> getRoleType(@Param("id") Integer id, @Param("type") Integer type);

    UserPojo findByUserName(@Param("userName") String userName);

    void update(UserPojo user);

    void updateBindMobile(UserBindingPojo userBinding);

    List<UserBindingPojo> findByBindName(UserBindingPojo userBindingPojo);

    UserPojo selectById(Integer id);

}
