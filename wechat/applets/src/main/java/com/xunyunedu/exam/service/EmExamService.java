package com.xunyunedu.exam.service;

import com.xunyunedu.exam.pojo.EmExam;
import com.xunyunedu.exception.ApiResult;

public interface EmExamService {
    ApiResult add(EmExam emExam);

    EmExam getById(Integer id);
}
