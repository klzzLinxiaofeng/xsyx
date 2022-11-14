package com.xunyunedu.syllabus.service.impl;

import com.xunyunedu.syllabus.dao.SyllabusDao;
import com.xunyunedu.syllabus.dto.SyllabusDTO;
import com.xunyunedu.syllabus.pojo.SyllabusPojo;
import com.xunyunedu.syllabus.service.SyllabusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Resource
    SyllabusDao syllabusDao;

    @Override
    public List<SyllabusPojo> getSyllabus(SyllabusDTO syllabusDTO,String schoolTermCode) {
        return syllabusDao.getSyllabus(syllabusDTO,schoolTermCode);
    }


    @Override
    public int insert(SyllabusPojo syllabusPojo) {
        return this.syllabusDao.insert(syllabusPojo);
    }


    @Override
    public SyllabusPojo getSyllabusId(Integer syllabusId) {
        return syllabusDao.getSyllabusId(syllabusId);
    }

    @Override
    public SyllabusPojo getSyllabusByTeamIdCode(Integer teamId,String code) {
        return syllabusDao.getSyllabusByTeamIdCode(teamId,code);
    }
}
