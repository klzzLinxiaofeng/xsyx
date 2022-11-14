package platform.szxyzxx.resultsStatistical.dao;

import platform.szxyzxx.resultsStatistical.pojo.GradeSubjectScores;
import platform.szxyzxx.resultsStatistical.pojo.vo.GssQuery;

import java.util.List;

public interface GradeSubjectScoresMapper {

    List<GradeSubjectScores> findByGss(GssQuery gssQuery);
}
