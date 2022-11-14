package platform.szxyzxx.resultsStatistical.dao;

import platform.szxyzxx.resultsStatistical.pojo.AnalysisStudens;
import platform.szxyzxx.resultsStatistical.pojo.vo.AsQuery;

import java.util.List;

public interface AnalysisStudensMapper {
    List<AnalysisStudens> findByAs(AsQuery acsQuery);

    List<AnalysisStudens> findByRank(AsQuery acsQuery);

    AnalysisStudens findByDate(AsQuery acsQuery);

    List<AnalysisStudens> findByRanking(AsQuery acsQuery);

}
