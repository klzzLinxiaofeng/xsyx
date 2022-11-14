package com.xunyunedu.learning.service;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.learning.pojo.LdLearningDesign;

public interface LdLearningDesignService {
    LdLearningDesign getById(Integer id);

    ApiResult add(LdLearningDesign ldLearningDesign);
}
