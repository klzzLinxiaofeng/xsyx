package com.xunyunedu.task.Job;

import com.xunyunedu.personinfor.service.impl.MyinforServiceImpl;
import com.xunyunedu.task.service.PublicClassJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 选课功能定时处理
 *
 * @author: yhc
 * @Date: 2020/11/20 15:20
 * @Description:
 */
public class PublicClassJob {

    Logger log = LoggerFactory.getLogger(PublicClassJob.class);

    @Autowired
    private PublicClassJobService publicClassJobService;

    /**
     * 保存已经选课后的并且选课时间已经截止的用户记录
     */
    public void historyJob() {

        // 获取已经报名截止的课程和学生id
        publicClassJobService.classPojoList();

    }
}
