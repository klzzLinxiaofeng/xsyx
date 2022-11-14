package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.vo.JobControl;
import com.xunyunedu.mergin.vo.JobControlCondition;

import java.util.List;

public interface JobControlService {

    JobControl add(JobControl jobControl);

    JobControl modify(JobControl jobControl);

    List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition);
}
