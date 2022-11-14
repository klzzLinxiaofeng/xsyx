package com.xunyunedu.PublishAndAcceptJob.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xunyunedu.PublishAndAcceptJob.dao.PublishAndAcceptJobContentDao;
import com.xunyunedu.PublishAndAcceptJob.pojo.PublishAndAcceptJobContentPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.PublishAndAcceptJob.service.PublishJobContentService;
import com.xunyunedu.core.service.BasicSQLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 老师发布作业
 *
 * @author: lee
 */

@Service
public class PublishJobContentServiceImpl implements PublishJobContentService {

    Logger log = LoggerFactory.getLogger(PublishJobContentServiceImpl.class);

    @Autowired
    private PublishAndAcceptJobContentDao publishJobContentDao;
    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 老师添加作业内容
     *
     * @param publishJobContent
     */
    @Override
    @Transactional
    public Integer addPublishJobContent(PublishAndAcceptJobContentPojo publishJobContent) {
        publishJobContent.setCreateDate(new Date());
        publishJobContent.setModifyDate(new Date());
        publishJobContentDao.addPublishJobContent(publishJobContent);
        List<Map<String ,Object>> studentList=basicSQLService.find("select * from pj_student where is_delete=0 and school_id="+publishJobContent.getSchoolId()+" and team_id="+publishJobContent.getTeamId());
        if(studentList.size()>0){
            for(Map<String,Object> aa:studentList){
                StudentHomeWoke studentHomeWoke=new StudentHomeWoke();
                studentHomeWoke.setCreateTime(publishJobContent.getCreateDate());
                studentHomeWoke.setModieTime(publishJobContent.getModifyDate());
                studentHomeWoke.setJobId(publishJobContent.getId());
                studentHomeWoke.setStudentId(Integer.parseInt(aa.get("id").toString()));
                publishJobContentDao.createStudent(studentHomeWoke);
            }
        }
        return null;
    }

    /**
     * 查询内容历史
     *
     * @param teacherId
     * @return
     */
    @Override
    public List<PublishAndAcceptJobContentPojo> getContentByTeacherIdAndSubjectId(Integer teacherId) {
        List<PublishAndAcceptJobContentPojo> list = publishJobContentDao.findPublishJobContent(teacherId, null);
        return list;
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        publishJobContentDao.deleteByPrimaryKey(id);
        publishJobContentDao.deleteByStudent(id);

    }

    @Override
    public void updatePublishJobContent(PublishAndAcceptJobContentPojo publishJobContent) {
        publishJobContent.setModifyDate(new Date());
        publishJobContentDao.updatePublishJobContent(publishJobContent);
    }

    @Override
    public PublishAndAcceptJobContentPojo getContentDetails(Integer id) {
        List<PublishAndAcceptJobContentPojo> list = publishJobContentDao.findPublishJobContent(null, id);
        if (CollUtil.isNotEmpty(list)) {
            PublishAndAcceptJobContentPojo pojo = list.get(0);
            List<String> teamNames = new ArrayList<>();
            String teamId = pojo.getTeamId();
            if (StrUtil.isNotEmpty(teamId)) {
                String[] team = teamId.split(",");
                List<PublishAndAcceptJobContentPojo> teamList = publishJobContentDao.findTeamSubject(team, pojo.getTeacherId(), pojo.getSubjectId());
                for (PublishAndAcceptJobContentPojo contentPojo : teamList) {
                    teamNames.add(contentPojo.getTeamName());
                    pojo.setTeacherName(contentPojo.getTeacherName());
                    pojo.setSubjectName(contentPojo.getSubjectName());
                }
                pojo.setTeamNames(teamNames);
            }
            return pojo;
        }
        return null;
    }

    /**
     * 判断当前教师是否有教授的课程
     *
     * @param teacherId
     * @param schoolId
     */
    @Override
    public Integer getTeacherStatus(Integer teacherId, Integer schoolId) {
        return publishJobContentDao.getTeacherStatusCount(teacherId, schoolId);
    }


}