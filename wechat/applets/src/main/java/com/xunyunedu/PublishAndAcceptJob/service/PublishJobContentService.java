package com.xunyunedu.PublishAndAcceptJob.service;

import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;

import java.util.List;

/**
 * 老师发布作业
 * 学生接收作业
 * 内容信息
 * @author lee
 */
public interface PublishJobContentService {

    /**
     * 老师添加作业内容
     * @param publishJobContent
     */
    Integer  addPublishJobContent(PublishAndAcceptJobContentPojo publishJobContent);

    /**
     * 查询历史
     * @param teacherId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> getContentByTeacherIdAndSubjectId(Integer teacherId);

    /**
     * 删除内容信息
     * @param id
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 修改内容
     * @param publishJobContent
     * @return
     */
    void updatePublishJobContent(PublishAndAcceptJobContentPojo publishJobContent);

    /**
     *
     *
     * 主键查询作业详情
     * @param id
     * @return
     */
    PublishAndAcceptJobContentPojo getContentDetails(Integer id);

    Integer getTeacherStatus(Integer teacherId, Integer schoolId);
}
