package com.xunyunedu.student.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.student.dao.PerformanceDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.PerformancePojo;
import com.xunyunedu.student.pojo.StudentDO;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.pojo.TeamDO;
import com.xunyunedu.student.service.PerformanceService;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.team.dao.TeamsDao;
import com.xunyunedu.util.ftp.FtpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: yhc
 * @Date: 2021/4/8 16:02
 * @Description: 学生表现
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceDao performanceDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    @Autowired
    private TeamsDao teamsDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    /**
     * 批量新增表现多个学生
     *
     * @param performancePojo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMatureShow(PerformancePojo performancePojo, String assesName, String assesStuName) {
        performancePojo.setCreateDate(new Date());
        // 图片保存
        if (performancePojo.getPhotos() != null && performancePojo.getPhotos().length > 0) {
            performancePojo.setUuid(StringUtils.join(performancePojo.getPhotos(), ","));
        }
        performancePojo.setAssesName(assesName);
        performanceDao.addMatureShow(performancePojo);
        Integer id = performancePojo.getId();
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.CREATE_FAIL);
        }
        // 新增学生和表现关系表
        Integer[] studentId = performancePojo.getStudentIds();
        for (Integer stuId : studentId) {
            StudentPojo pojo = new StudentPojo();
            pojo.setId(stuId);
            StudentPojo studentPojo = studentDao.findStuTeamById(pojo);
            if (studentPojo != null && studentPojo.getTeamId() != null) {
                performanceDao.addStuShow(stuId, studentPojo.getTeamId(), id, assesStuName);
            } else {
                throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
            }
        }
    }

    /**
     * 获取发布历史分页
     * 发布历史只需要展示一个学生
     *
     * @param pojo
     * @param pageNum
     * @param pageSize
     * @param assesName
     * @param assesStuName
     * @return
     */
    @Override
    public PageInfo<PerformancePojo> getTeacherReleaseShowList(PerformancePojo pojo, Integer pageNum, Integer pageSize, String assesName, String assesStuName) {
        Integer teacherId = pojo.getTeacherId();
        Integer studentId = pojo.getStudentId();
        PageHelper.startPage(pageNum, pageSize);

        // 获取发布历史
        List<PerformancePojo> list = null;

        if (teacherId != null) {
            // 查询当前老师的发布记录
            list = performanceDao.getTeacherReleaseShowList(pojo, assesName);
        } else if (studentId != null) {
            // 查询当前学生记录
            list = performanceDao.getStuReleaseShowList(studentId, null, assesName, assesStuName);
        }
        if (CollUtil.isNotEmpty(list)) {
            for (PerformancePojo performancePojo : list) {
                if (teacherId != null) {
                    // 获取一个学生的信息
                    PerformancePojo performancePojo1 = performanceDao.getOneStuInfo(performancePojo.getId(), assesStuName);
                    if (performancePojo1 != null) {
                        performancePojo.setTeamName(performancePojo1.getTeamName());
                        performancePojo.setStuName(performancePojo1.getStuName());
                    }
                }
                // 获取发布的老师信息
                if (studentId != null) {
                    TeacherPojo teacherPojo = new TeacherPojo();
                    teacherPojo.setId(performancePojo.getTeacherId());
                    TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                    if (teacher != null) {
                        performancePojo.setTeacherName(teacher.getName());
                    }
                }
            }
            return new PageInfo<>(list);
        }
        return null;
    }

    /**
     * 获取发布详情
     *
     * @param pojo
     * @return
     */
    @Override
    public PerformancePojo getReleaseDetails(PerformancePojo pojo, String assesName, String assesStuName) {
        Integer teacherId = pojo.getTeacherId();
        Integer studentId = pojo.getStudentId();
        // 获取发布历史
        List<PerformancePojo> list = null;
        if (teacherId != null) {
            // 查询当前老师的发布记录
            list = performanceDao.getTeacherReleaseShowList(pojo, assesName);
        } else if (studentId != null) {
            // 查询当前学生记录
            list = performanceDao.getStuReleaseShowList(studentId, pojo.getId(), assesName, assesStuName);
        }
        if (CollUtil.isNotEmpty(list)) {
            PerformancePojo performancePojo = list.get(0);
            // 获取图片信息
            String uuid = performancePojo.getUuid();
            if (StrUtil.isNotEmpty(uuid)) {
                String[] uuids = uuid.split(",");
                List<String> urls = new ArrayList<>(uuids.length);
                for (String s : uuids) {
                    EntityFile file = uploaderDao.findFileByUUID(s);
                    if (file != null) {
                        urls.add(ftpUtils.relativePath2HttpUrl(file));
                    }
                }
                performancePojo.setUrl(urls.toArray(new String[urls.size()]));
            }
            // 获取老师发布的对象
            if (teacherId != null) {
                List<StudentDO> studentDOList = performanceDao.getReleaseObjects(performancePojo.getId(), assesStuName);
                if (studentDOList != null) {
                    performancePojo.setStudentDOList(studentDOList);
                }
            }
            // 获取发布的老师信息
            if (studentId != null) {
                TeacherPojo teacherPojo = new TeacherPojo();
                teacherPojo.setId(performancePojo.getTeacherId());
                TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
                if (teacher != null) {
                    performancePojo.setTeacherName(teacher.getName());
                }
            }
            return performancePojo;
        }
        return null;
    }

    /**
     * 获取当前老师的班级和学生信息
     * @param teacherId
     * @return
     */
    @Override
    public List<TeamDO> getTeamStus(Integer teacherId) {
        // 获取当前老师的班级
        List<TeamDO> list = teamsDao.getTeams(teacherId);
        if (CollUtil.isNotEmpty(list)) {
            for (TeamDO teamDO : list) {
                // 获取班级下的学生信息
                Integer teamId = teamDO.getTeamId();
                List<StudentPojo> stuList = teamsDao.getTeamStus(teamId);
                teamDO.setPojoList(stuList);
            }
        }

        return list;
    }

}
