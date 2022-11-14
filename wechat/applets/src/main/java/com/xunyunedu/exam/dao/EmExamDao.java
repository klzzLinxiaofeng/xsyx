package com.xunyunedu.exam.dao;

import com.xunyunedu.exam.pojo.EmExam;

public interface EmExamDao {

    EmExam selectById(Integer id);


    Integer insert(EmExam emExam);

}
