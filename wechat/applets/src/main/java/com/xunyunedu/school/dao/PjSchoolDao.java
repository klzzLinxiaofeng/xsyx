package com.xunyunedu.school.dao;

import com.xunyunedu.school.pojo.PjSchool;
import com.xunyunedu.school.pojo.SchoolTermCurrentVo;
import com.xunyunedu.team.pojo.SchoolTermPojo;
import com.xunyunedu.team.vo.TermTeamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author edison
 */
public interface PjSchoolDao {


    PjSchool selectById(Integer id);

    /**
     * 通过学校ID查找该学校当前学期
     *
     * @param schoolId
     * @return
     */
    SchoolTermCurrentVo findSchoolTermCurrentBySchoolId(@Param("schoolId") Integer schoolId);


    /**
     * 获取当前学年的教师（班主任）班级
     * @param teamVo
     * @return
     */
    TermTeamVo findTermCurrent(TermTeamVo teamVo);

    SchoolTermPojo findTermBycontidion(TermTeamVo termTeamVo);

    List<SchoolTermPojo> findTerm(SchoolTermPojo schoolTermPojo);

    SchoolTermPojo getTermBySchoolId(@Param("schoolId") Integer schoolId,@Param("startDate") String startDate);
}

