package com.xunyunedu.student.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.school.dao.PjSchoolDao;
import com.xunyunedu.school.pojo.SchoolTermCurrentVo;
import com.xunyunedu.student.dao.MicroManagerDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.*;
import com.xunyunedu.student.service.MicroManagerService;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:23
 * @Description:
 */
@Service
public class MicroManagerServiceImpl implements MicroManagerService {

    @Autowired
    private MicroManagerDao microManagerDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private PjSchoolDao pjSchoolDao;


    @Override
    public PageInfo<MicroManagerPojo> getMicroByCondition(CommonPojo common, Integer pageNum, Integer pageSize) {
        Integer typeId = common.getTypeId();
        if (typeId != null && typeId == 1) {
            // 校园风采对应所有年级
            common.setGradeId(null);
        }
        Integer microId = common.getMicroId();
        boolean flg = false;
        if (microId != null) {
            flg = true;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MicroManagerPojo> list = microManagerDao.getMicroByCondition(common);
        getUrl(list, flg);
        System.out.println(""+list);
        System.out.println("size"+list);
        PageInfo<MicroManagerPojo> objectPageInfo = new PageInfo<>(list);
        return objectPageInfo;
    }

    /**
     * 获取当前微课的评论信息
     *
     * @param userCommentsPojo
     * @return
     */
    @Override
    public PageInfo<UserCommentsPojo> getMicroCommentsMsg(UserCommentsPojo userCommentsPojo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserCommentsPojo> list = microManagerDao.getMicroCommentsMsg(userCommentsPojo);
        for (UserCommentsPojo commentsPojo : list) {
            Integer userId = commentsPojo.getUserId();
            Integer userType = commentsPojo.getUserType();
            if (userId != null && userType != null) {
                // 0学生 1教师
                if (userType == 0) {
                    StudentPojo studentPojo = new StudentPojo();
                    studentPojo.setId(userId);
                    studentPojo.setSchoolId(commentsPojo.getSchoolId());
                    List<StudentPojo> read = studentDao.read(studentPojo);
                    if (CollUtil.isNotEmpty(read)) {
                        commentsPojo.setName(read.get(0).getName());
                    }
                } else if (userType == 1) {
                    TeacherPojo teacherPojo = new TeacherPojo();
                    teacherPojo.setId(userId);
                    teacherPojo.setSchoolId(commentsPojo.getSchoolId());
                    TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                    if (teacher != null) {
                        commentsPojo.setName(teacher.getName());
                    }
                }
            }
        }

        PageInfo<UserCommentsPojo> objectPageInfo = new PageInfo<>(list);
        return objectPageInfo;
    }

    /**
     * 新增评论
     *
     * @param userCommentsPojo
     */
    @Override
    public void addComments(UserCommentsPojo userCommentsPojo) {
        userCommentsPojo.setCommentsDate(new Date());
        userCommentsPojo.setCreateDate(new Date());
        microManagerDao.addComments(userCommentsPojo);
    }


    /**
     * 获取微课类型
     *
     * @param schooldId
     * @return
     */
    @Override
    public List<MicroTypePojo> getMicroTypeBySchoolId(Integer schooldId) {
        return microManagerDao.getMicroTypeBySchoolId(schooldId);
    }

    /**
     * 获取微课年级
     *
     * @param schooldId
     * @return
     */
    @Override
    public List<GradePojo> getMicroGradeBySchoolId(Integer schooldId) {
        SchoolTermCurrentVo schoolTermCurrentVo = pjSchoolDao.findSchoolTermCurrentBySchoolId(schooldId);
        String schoolYear = null;
        if (schoolTermCurrentVo != null) {
            // 当前学校所属的当前学期
            schoolYear = schoolTermCurrentVo.getSchoolYear();
        }
        return microManagerDao.getMicroGradeBySchoolId(schooldId, schoolYear);
    }

    /**
     * 获取当前用户的点赞和收藏信息
     *
     * @param collectionCommentsPojo
     * @return
     */
    @Override
    public CollectionCommentsPojo getCollectType(CollectionCommentsPojo collectionCommentsPojo) {
        return microManagerDao.getCollectType(collectionCommentsPojo);
    }

    /**
     * 新增点赞和收藏信息
     *
     * @param collectionCommentsPojo
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addKeepCollect(CollectionCommentsPojo collectionCommentsPojo) {
        // 判断当前用户是否收藏和点赞过
        CollectionCommentsPojo collectType = microManagerDao.getCollectType(collectionCommentsPojo);
        if (collectType != null) {
            // 修改点赞和收藏状态
            collectionCommentsPojo.setId(collectType.getId());
            collectionCommentsPojo.setModifyDate(new Date());
            microManagerDao.updateCollectionByCondition(collectionCommentsPojo);

            // 获取原来的收藏和点赞状态
            Integer collectionStatus = collectType.getCollectionStatus();
            Integer likeStatus = collectType.getLikeStatus();

            // 获取新的收藏和点赞状态
            Integer newCollectionStatus = collectionCommentsPojo.getCollectionStatus();
            Integer newLikeStatus = collectionCommentsPojo.getLikeStatus();
            MicroManagerPojo microManagerPojo = new MicroManagerPojo();
            if (newCollectionStatus != null) {
                // 收藏/取消收藏  0:（已收藏/收藏） 1:（未收藏/取消收藏）
                if (collectionStatus == 1 && newCollectionStatus == 0) {
                    microManagerPojo.setCollect(1);
                } else if (collectionStatus == 0 && newCollectionStatus == 1) {
                    microManagerPojo.setCollect(-1);
                }
            }
            if (newLikeStatus != null) {
                // 点赞: 0:(已点赞/点赞) 1:(未点赞/取消点赞)
                if (likeStatus == 1 && newLikeStatus == 0) {
                    microManagerPojo.setThumbs(1);
                } else if (likeStatus == 0 && newLikeStatus == 1) {
                    microManagerPojo.setThumbs(-1);
                }
            }
            // 修改点赞和收藏数量
            microManagerPojo.setModifyDate(new Date());
            microManagerPojo.setId(collectType.getMicroId());
            // 取消点赞/点赞 取消收藏/收藏
            microManagerDao.updateAddMicro(microManagerPojo);
        } else {
            collectionCommentsPojo.setCreateDate(new Date());
            // 新增收藏和点赞
            microManagerDao.addCollection(collectionCommentsPojo);
            MicroManagerPojo microManagerPojo = new MicroManagerPojo();
            microManagerPojo.setId(collectionCommentsPojo.getMicroId());
            Integer collectionStatus = collectionCommentsPojo.getCollectionStatus();
            Integer likeStatus = collectionCommentsPojo.getLikeStatus();
            if (collectionStatus != null) {
                microManagerPojo.setCollect(1);
            }
            if (likeStatus != null) {
                microManagerPojo.setThumbs(1);
            }
            // 修改新增的点赞 收藏数量
            microManagerDao.updateAddMicro(microManagerPojo);
        }
    }


    @Override
    public PageInfo<MicroManagerPojo> getMyCollection(CommonPojo common, Integer pageNum, Integer pageSize) {
        Integer typeId = common.getTypeId();
        if (typeId != null && typeId == 1) {
            // 校园风采对应所有年级
            common.setGradeId(null);
        }
        Integer microId = common.getMicroId();
        boolean flg = false;
        if (microId != null) {
            flg = true;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<MicroManagerPojo> list = microManagerDao.getMyCollectionMicroByCondition(common);
        getUrl(list, flg);
        PageInfo<MicroManagerPojo> objectPageInfo = new PageInfo<>(list);
        return objectPageInfo;
    }

    public void getUrl(List<MicroManagerPojo> list, boolean flg){
        // 获取封面路径
        for (MicroManagerPojo microManagerPojo : list) {
            String coverUuid = microManagerPojo.getCoverUuid();
            if (coverUuid != null && !("").equals(coverUuid)) {
                // 根据uuid查询封面的url
                EntityFile file = uploaderDao.findFileByUUID(coverUuid);
                if (file != null) {
                    microManagerPojo.setCoverUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            if (flg) {
                // 获取微课对应的课件和视频
                String videoUuid = microManagerPojo.getVideoUuid();
                if (videoUuid != null && !("").equals(videoUuid)) {
                    // 根据uuid查询封面的url
                    EntityFile file = uploaderDao.findFileByUUID(videoUuid);
                    if (file != null) {
                        microManagerPojo.setVideoUrl(ftpUtils.relativePath2HttpUrl(file));
                    }
                }
                String classUuid = microManagerPojo.getClassUuid();
                if (classUuid != null && !("").equals(classUuid)) {
                    // 根据uuid查询封面的url
                    EntityFile file = uploaderDao.findFileByUUID(classUuid);
                    if (file != null) {
                        microManagerPojo.setClassUrl(ftpUtils.relativePath2HttpUrl(file));
                        microManagerPojo.setCoursewareName(file.getFileName());
                    }
                }

            }
        }
    }
}
