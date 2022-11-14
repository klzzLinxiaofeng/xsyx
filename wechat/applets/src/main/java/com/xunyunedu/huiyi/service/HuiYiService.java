package com.xunyunedu.huiyi.service;

import com.xunyunedu.huiyi.pojo.HuiYi;

import java.util.List;

public interface HuiYiService {
    String  create(HuiYi huiYi);
    void createshenhe(HuiYi huiYi);
    List<HuiYi> findByAll(Integer id, String name);

    Integer updateShenHe(Integer id, Integer zhuangtai, String text);

    HuiYi findById(Integer id);
}
