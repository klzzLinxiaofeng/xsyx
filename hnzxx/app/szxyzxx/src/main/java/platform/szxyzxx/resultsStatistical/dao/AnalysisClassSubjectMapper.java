package platform.szxyzxx.resultsStatistical.dao;

import platform.szxyzxx.resultsStatistical.pojo.AnalysisClassSubject;
import platform.szxyzxx.resultsStatistical.pojo.vo.AcsQuery;

import java.util.List;
import java.util.Map;

public interface AnalysisClassSubjectMapper {
    List<AnalysisClassSubject> findByAcs(AcsQuery acsQuery);
    List<Map<String,Object>> findByGrade();
}
