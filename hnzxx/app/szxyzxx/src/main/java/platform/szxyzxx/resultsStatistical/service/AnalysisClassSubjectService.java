package platform.szxyzxx.resultsStatistical.service;

import platform.szxyzxx.resultsStatistical.pojo.AnalysisClassSubject;
import platform.szxyzxx.resultsStatistical.pojo.vo.AcsQuery;

import java.util.List;
import java.util.Map;

public interface AnalysisClassSubjectService {
    List<AnalysisClassSubject> findByAll(AcsQuery acsQuery);
    List<Map<String,Object>> findByGrade();
}
