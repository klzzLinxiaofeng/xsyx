package platform.szxyzxx.resultsStatistical.service;

import platform.szxyzxx.resultsStatistical.pojo.GradeSubjectScores;
import platform.szxyzxx.resultsStatistical.pojo.vo.GssQuery;

import java.util.List;

public interface GradeSubjectScoresService {
    List<GradeSubjectScores> findByAll(GssQuery gssQuery);
}
