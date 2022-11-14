package com.xunyunedu.mergin.service;

import com.xunyunedu.mergin.vo.UserBinding;
import com.xunyunedu.mergin.vo.UserBindingCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface UserBindingService {

    void remove(UserBinding var1);
    UserBinding add(UserBinding var1);

    List<UserBinding> findUserBindingByCondition(UserBindingCondition var1, Page var2, Order var3);

    UserBinding findByBindingName(String var1);
}
