package com.xunyunedu.student.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.student.pojo.PerformancePojo;
import com.xunyunedu.student.pojo.TeamDO;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/4/8 16:02
 *  @Description:
 */
public interface PerformanceService {

    void addMatureShow(PerformancePojo performancePojo, String assesName, String assesStuName);

    PageInfo<PerformancePojo> getTeacherReleaseShowList(PerformancePojo pojo, Integer pageNum, Integer pageSize, String assesName, String assesStuName);

    PerformancePojo getReleaseDetails(PerformancePojo pojo, String assesName, String assesStuName);

    List<TeamDO> getTeamStus(Integer teacherId);
}
