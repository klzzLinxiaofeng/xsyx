package com.xunyunedu.act.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.exception.PageCondition;


import java.util.List;
public interface InSchoolActivityService {
    InSchoolActivity selectById(Integer id);
    void create(InSchoolActivity act);
    PageInfo selectList(PageCondition<InSchoolActivity> condition);
}
