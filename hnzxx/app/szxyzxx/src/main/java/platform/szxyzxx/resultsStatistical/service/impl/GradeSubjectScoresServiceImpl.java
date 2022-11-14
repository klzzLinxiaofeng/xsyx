package platform.szxyzxx.resultsStatistical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.resultsStatistical.dao.GradeSubjectScoresMapper;
import platform.szxyzxx.resultsStatistical.pojo.GradeSubjectScores;
import platform.szxyzxx.resultsStatistical.pojo.vo.GssQuery;
import platform.szxyzxx.resultsStatistical.service.GradeSubjectScoresService;

import java.util.List;

@Service
public class GradeSubjectScoresServiceImpl implements GradeSubjectScoresService {
    @Autowired
    private GradeSubjectScoresMapper mapper;
    @Override
    public List<GradeSubjectScores> findByAll(GssQuery gssQuery) {
        return mapper.findByGss(gssQuery);
    }
}
