package com.xunyunedu.PublishAndAcceptJob.service;

import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 学生接收作业
 * 内容信息
 * @author lee
 */
public interface AcceptJobContentService {


    /**
     * 查询全部
     * @param studentId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> getAcceptJobContentAll(Integer studentId);

    /**
     * 学生接收作业
     * @param teamId
     * @param subjectId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> getContentByStudentIdAndSubjectId(Integer teamId, String subjectId);

    /**
     * 主键查询作业详情
     * @param id
     * @return
     */
    PublishAndAcceptJobContentPojo getContentDetails(Integer id);

    /**
     * 获得学科信息
     * @param subjectPojo
     * @return
     */
    Set<SubjectPojo> getSubject(SubjectPojo subjectPojo);
}
