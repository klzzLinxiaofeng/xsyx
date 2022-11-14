package com.xunyunedu.student.service.impl;

import com.xunyunedu.personinfor.service.impl.LockClassService;
import com.xunyunedu.student.pojo.CollectionCommentsPojo;
import com.xunyunedu.student.service.MicroManagerService;
import com.xunyunedu.util.LockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 点赞收藏功能加锁
 *
 * @author: yhc
 * @Date: 2020/12/12 18:59
 * @Description:
 */
@Service
public class LockMicroClass {

    @Autowired
    private MicroManagerService microManagerService;

    public void addKeepCollect(CollectionCommentsPojo collectionCommentsPojo) {
        // 根据微课id加锁 减小锁粒度
        synchronized (LockUtil.getLock("micro" + collectionCommentsPojo.getMicroId())) {
            microManagerService.addKeepCollect(collectionCommentsPojo);
        }
    }

}
