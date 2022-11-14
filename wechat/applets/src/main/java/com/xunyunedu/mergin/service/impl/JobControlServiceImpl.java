package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.JobControlDao;
import com.xunyunedu.mergin.service.JobControlService;
import com.xunyunedu.mergin.vo.JobControl;
import com.xunyunedu.mergin.vo.JobControlCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobControlServiceImpl implements JobControlService {

    @Autowired
    private JobControlDao jobControlDao;
    @Override
    public JobControl add(JobControl jobControl) {
        if(jobControl == null) {
            return null;
        }
        Date createDate = jobControl.getCreateDate();
        if(createDate == null) {
            createDate = new Date();
        }
        jobControl.setCreateDate(createDate);
        jobControl.setModifyDate(createDate);
        return jobControlDao.create(jobControl);
    }

    @Override
    public JobControl modify(JobControl jobControl) {
        if(jobControl == null) {
            return null;
        }
        Date modify = jobControl.getModifyDate();
        jobControl.setModifyDate(modify != null ? modify : new Date());
        return jobControlDao.update(jobControl);
    }

    @Override
    public List<JobControl> findJobControlByCondition(JobControlCondition jobControlCondition) {
        return jobControlDao.findJobControlByCondition(jobControlCondition, null, null);
    }

}
