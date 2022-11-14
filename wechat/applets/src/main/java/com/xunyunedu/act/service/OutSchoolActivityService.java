package com.xunyunedu.act.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.act.pojo.OutSchoolActivity;
import com.xunyunedu.exception.PageCondition;


public interface OutSchoolActivityService {

    OutSchoolActivity selectById(Integer id);
    void create(OutSchoolActivity act);
    PageInfo selectList(PageCondition<OutSchoolActivity> condition);
}
