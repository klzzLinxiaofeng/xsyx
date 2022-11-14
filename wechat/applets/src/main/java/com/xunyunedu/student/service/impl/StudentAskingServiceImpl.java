package com.xunyunedu.student.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.pay.dao.PayOrderDao;
import com.xunyunedu.pay.pojo.StudentPojo;
import com.xunyunedu.student.dao.StudentAskingDao;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.student.pojo.TeacherPojo;
import com.xunyunedu.student.service.StudentAskingService;
import com.xunyunedu.util.UUIDUtil;
import com.xunyunedu.util.ftp.FtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/15 9:01
 * @Description:
 */

@Service
public class StudentAskingServiceImpl implements StudentAskingService {
    Logger log = LoggerFactory.getLogger(StudentAskingServiceImpl.class);

    @Autowired
    private StudentAskingDao studentAskingDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private PayOrderDao payOrderDao;


    /**
     * 根据学生获取对应的班主任教师信息
     *
     * @param schooldId
     * @param stuId
     * @return
     */
    @Override
    public TeacherPojo getTeacherById(Integer schooldId, Integer stuId) {
        // type 1:班主任 2：任课教师
        List<TeacherPojo> list = studentAskingDao.getTeacher(schooldId, stuId, 1);
        if (list != null && list.size() > 0) {
            // 查询多个也只展示一个 ,班主任一个
            TeacherPojo pojo = list.get(0);
            if (!StrUtil.hasEmpty(pojo.getPhotoUuid())){
                EntityFile entityFile = uploaderDao.findFileByUUID(pojo.getPhotoUuid());
                if (entityFile != null) {
                    pojo.setHttpUrl(ftpUtils.relativePath2HttpUrl(entityFile));
                }
            }
            return pojo;
        }
        return null;
    }


    /**
     * 获取对应教师信息
     *
     * @param schooldId
     * @param id
     * @return
     */
    @Override
    public TeacherPojo getTeacher(Integer schooldId, Integer id) {
        TeacherPojo list = studentAskingDao.getTeacherById(schooldId, id);
        // 查询多个也只展示一个 ,班主任一个
        return list;
    }

    /**
     * 添加学生请假信息
     *
     * @param studentAskingPojo
     */
    @Override
    public Integer addAskingInfo(StudentAskingPojo studentAskingPojo) {
        studentAskingPojo.setCreateDate(new Date());
        studentAskingPojo.setIndiaStatus(1);
        String number = UUIDUtil.genUniqueKey();
        studentAskingPojo.setNumber(number);
        return studentAskingDao.addAskingInfo(studentAskingPojo);
    }

    /**
     * 获取学生请假信息
     *
     * @param schooldId
     * @param id
     * @return
     */
    @Override
    public StudentAskingPojo getStuAskingById(Integer schooldId, Integer id) {
        StudentAskingPojo studentAskingPojo = studentAskingDao.getStuAskingById(schooldId, id);

        // 获取文件的UUID,根据uuid查询文件的详细地址
        if (studentAskingPojo != null) {
            if (!StrUtil.hasEmpty(studentAskingPojo.getUploadId())){
                EntityFile entityFile = uploaderDao.findFileByUUID(studentAskingPojo.getUploadId());
                if (entityFile != null) {
                    studentAskingPojo.setHttpUrl(ftpUtils.relativePath2HttpUrl(entityFile));
                    studentAskingPojo.setFileName(entityFile.getFileName());
                }
            }

            if (studentAskingPojo.getStudentId() != null) {
                List<TeacherPojo> list = studentAskingDao.getTeacher(schooldId, studentAskingPojo.getStudentId(), 1);
                if (list != null && list.size() > 0) {
                    // 查询多个也只展示一个 ,班主任一个
                    studentAskingPojo.setTeacherName(list.get(0).getName());
                }
            }
        }
        return studentAskingPojo;
    }

    /**
     * 获取所有审批信息（已审批、未审批）
     *
     * @param studentAskingPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<StudentAskingPojo> getStuAskingList(StudentAskingPojo studentAskingPojo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentAskingPojo> stuAskingList = studentAskingDao.getStuAskingList(studentAskingPojo);
        PageInfo<StudentAskingPojo> objectPageInfo = new PageInfo<>(stuAskingList);
        return objectPageInfo;
    }
}