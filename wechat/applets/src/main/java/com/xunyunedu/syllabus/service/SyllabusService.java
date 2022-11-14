package com.xunyunedu.syllabus.service;

import com.xunyunedu.syllabus.dto.SyllabusDTO;
import com.xunyunedu.syllabus.pojo.SyllabusPojo;

import java.util.List;

public interface SyllabusService {

    List<SyllabusPojo> getSyllabus(SyllabusDTO syllabusDTO,String schoolTermCode);

    int insert(SyllabusPojo syllabusPojo);

    SyllabusPojo getSyllabusId(Integer syllabusId);

    SyllabusPojo getSyllabusByTeamIdCode(Integer teamId,String code);
}
