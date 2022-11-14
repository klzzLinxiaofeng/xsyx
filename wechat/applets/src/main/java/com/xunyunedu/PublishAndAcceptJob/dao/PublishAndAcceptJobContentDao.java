package com.xunyunedu.PublishAndAcceptJob.dao;

import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 老师发布作业
 * 内容发布与接收
 *
 * @author lee
 */
public interface PublishAndAcceptJobContentDao {

    /**
     * 添加内容
     *
     * @param publishJobContent
     */
    void addPublishJobContent(PublishAndAcceptJobContentPojo publishJobContent);

    /*
    * 添加内容到学生作业关联表
    *
    * */
    void createStudent(StudentHomeWoke studentHomeWoke);
    /**
     * 修改内容
     *
     * @param publishJobContent
     * @return
     */
    void updatePublishJobContent(PublishAndAcceptJobContentPojo publishJobContent);

    /**
     * 删除内容
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(Integer id);

    void deleteByStudent(Integer id);

    /**
     * 历史
     *
     * @param teacherId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> findPublishJobContent(@Param("teacherId") Integer teacherId, @Param("id") Integer id);

    /**
     * 查询全部作业
     *
     * @param teamId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> findAcceptJobContentAll(@Param("teamId") Integer teamId, @Param("subjectId") String subjectId);

    /**
     * 根据学生id和学科查作业内容
     *
     * @param studentId
     * @param subjectId
     * @return
     */
    List<PublishAndAcceptJobContentPojo> findAcceptJobContent(@Param("teamId") Integer studentId, @Param("subjectId") Integer subjectId);

    /**
     * 作业详情
     *
     * @param id
     * @return
     */
    PublishAndAcceptJobContentPojo findContentDetailsById(Integer id);


    /**
     * 获得学科信息
     *
     * @param subjectPojo
     * @return
     */
    List<SubjectPojo> findSubject(SubjectPojo subjectPojo);

    List<PublishAndAcceptJobContentPojo> findTeamSubject(@Param("team") String[] team, @Param("teacherId") Integer teacherId, @Param("subjectId") Integer subjectId);

    Integer getTeacherStatusCount(@Param("teacherId") Integer teacherId, @Param("schoolId") Integer schoolId);
}
