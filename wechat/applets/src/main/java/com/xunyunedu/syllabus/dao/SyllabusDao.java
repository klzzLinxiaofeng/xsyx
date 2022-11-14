package com.xunyunedu.syllabus.dao;

import com.xunyunedu.syllabus.dto.SyllabusDTO;
import com.xunyunedu.syllabus.pojo.SyllabusPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SyllabusDao {

    List<SyllabusPojo> getSyllabus(@Param("syllabusDTO") SyllabusDTO syllabusDTO,@Param("schoolTermCode") String schoolTermCode);

    int insert(SyllabusPojo syllabusPojo);

    SyllabusPojo getSyllabusId(@Param("syllabusId") Integer syllabusId);

    SyllabusPojo getSyllabusByTeamIdCode(@Param("teamId") Integer teamId,@Param("code") String code);
}
