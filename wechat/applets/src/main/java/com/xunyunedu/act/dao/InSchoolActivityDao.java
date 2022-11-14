package com.xunyunedu.act.dao;

import com.xunyunedu.act.pojo.InSchoolActivity;

import java.util.List;

public interface InSchoolActivityDao {
    InSchoolActivity selectById(Integer id);
    void create(InSchoolActivity act);
    List<InSchoolActivity> selectList(InSchoolActivity act);
}
