package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.PerformancePojo;
import com.xunyunedu.student.pojo.StudentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/8 16:02
 * @Description: 学生表现
 */
public interface PerformanceDao {

    void addMatureShow(PerformancePojo performancePojo);

    List<PerformancePojo> getTeacherReleaseShowList(@Param("pojo") PerformancePojo pojo, @Param("tableName") String assesName);

    void addStuShow(@Param("stuId") Integer stuId, @Param("teamId") Integer teamId, @Param("id") Integer id, @Param("assesStuName") String assesStuName);

    PerformancePojo getOneStuInfo(@Param("id") Integer id, @Param("assesStuName") String assesStuName);

    List<PerformancePojo> getStuReleaseShowList(@Param("studentId") Integer studentId, @Param("id") Integer id, @Param("assesName") String assesName, @Param("assesStuName") String assesStuName);

    List<StudentDO> getReleaseObjects(@Param("id") Integer id, @Param("assesStuName") String assesStuName);
}