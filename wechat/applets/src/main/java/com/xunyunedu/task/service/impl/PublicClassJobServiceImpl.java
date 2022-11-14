package com.xunyunedu.task.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xunyunedu.personinfor.dao.MyInforDao;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import com.xunyunedu.personinfor.pojo.PublicElectiveHistoryPojo;
import com.xunyunedu.task.service.PublicClassJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/20 17:22
 * @Description:
 */

@Service
public class PublicClassJobServiceImpl implements PublicClassJobService {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MyInforDao myInforDao;

    /**
     * 获取超过截止日期并且没有保存的课程
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void classPojoList() {
        long millis = System.currentTimeMillis();
        log.info("开始获取已经报名截止的课程和学生,{}", millis);
        PublicClassPojo pojo = new PublicClassPojo();
        pojo.setIsDelete(0);
        pojo.setExpiryDate(new Date());
        pojo.setIsSaveHistory(0);
        // 获取已经报名截止的课程和学生id
        List<PublicClassPojo> classPojoList = myInforDao.findExpireDateClass(pojo);
        for (PublicClassPojo publicClassPojo : classPojoList) {
            Integer cid = publicClassPojo.getCid();
            Integer schoolId = publicClassPojo.getSchoolId();
            if (cid != null && schoolId != null) {
                // 根据课程获取已经报名的学生
                List<Integer> stuIds = myInforDao.findPublicUserByIdAndSchoolId(cid, schoolId);
                if (CollUtil.isNotEmpty(stuIds)) {
                    // 保存学生用户
                    PublicElectiveHistoryPojo historyPojo = new PublicElectiveHistoryPojo();
                    historyPojo.setCreatedAt(new Date());
                    historyPojo.setPublicClassId(cid);
                    historyPojo.setStudentIds(stuIds);
                    historyPojo.setSchoolId(schoolId);
                    myInforDao.addChoseHistory(historyPojo);
                }
                // 修改当前课程的状态
                PublicClassPojo publicPojo = new PublicClassPojo();
                publicPojo.setCid(cid);
                publicPojo.setIsSaveHistory(1);
                myInforDao.updatePublicClass(publicPojo);
            }

        }
        log.info("获取已经报名截止的课程和学生，保存课程记录，耗时：{}ms", System.currentTimeMillis() - millis);
    }
}
