package com.xunyunedu.homework.dao;

import com.xunyunedu.homework.pojo.HwHomework;

public interface HwHomeworkDao {

    HwHomework selectById(Integer id);

    Integer insert(HwHomework hwHomework);

}
