package com.xunyunedu.htsfeedback.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.htsfeedback.dao.HtsFeedbackDao;
import com.xunyunedu.htsfeedback.pojo.HtsFeedbackPojo;
import com.xunyunedu.htsfeedback.service.HtsFeedbackService;
import com.xunyunedu.student.dao.ParentDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 0:49
 */
@Service
public class HtsFeedbackServiceImpl implements HtsFeedbackService {

    @Resource
    private HtsFeedbackDao htsFeedbackDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;


    @Override
    public void addFeedback(HtsFeedbackPojo htsFeedbackPojo) {
        htsFeedbackPojo.setCreateDate(new Date());
        htsFeedbackDao.addFeedback(htsFeedbackPojo);

    }

    @Override
    public List<HtsFeedbackPojo> getFeedbackMsg(HtsFeedbackPojo htsFeedbackPojo) {
        List<HtsFeedbackPojo> list = htsFeedbackDao.getFeedbackMsg(htsFeedbackPojo);
        for (HtsFeedbackPojo entity : list) {
            String uuid = entity.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    entity.setUuids(lists);
                }
            }


        }

        return list;
    }

    @Override
    public HtsFeedbackPojo findFeedbackById(Integer stuId) {

        return htsFeedbackDao.findFeednackById(stuId);
    }

    @Override
    public StudentPojo getStudent(Integer stuId) {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setId(stuId);
        List<StudentPojo> list = studentDao.read(studentPojo);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ParentPojo getParent(Integer parentUserId) {
        ParentPojo parentPojo = new ParentPojo();
        parentPojo.setUserId(parentUserId);
        List<ParentPojo> list = parentDao.read(parentPojo);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public ParentPojo getParentByStuId(Integer stuId) {
        ParentPojo parentPojo = parentDao.getParentbyStuId(stuId);
        return parentPojo;
    }

    @Override
    public List<HtsFeedbackPojo> queryByStuId(Integer stuId) {
        List<HtsFeedbackPojo> list = htsFeedbackDao.queryByStuId(stuId);
        for (HtsFeedbackPojo entity : list) {
            String uuid = entity.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    entity.setUuids(lists);
                }
            }


        }

        return list;
    }


}
