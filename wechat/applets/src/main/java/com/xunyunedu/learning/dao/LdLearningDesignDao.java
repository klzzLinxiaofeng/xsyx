package com.xunyunedu.learning.dao;

import com.xunyunedu.learning.pojo.LdLearningDesign;

public interface LdLearningDesignDao {

    LdLearningDesign selectById(Integer id);

    Integer insert(LdLearningDesign ldLearningDesign);
}
