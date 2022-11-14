package com.xunyunedu.act.dao;

import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.act.pojo.OutSchoolActivity;

import java.util.List;

public interface OutSchoolActivityDao {
    OutSchoolActivity selectById(Integer id);
    void create(OutSchoolActivity act);
    List<OutSchoolActivity> selectList(OutSchoolActivity act);
}
