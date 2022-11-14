package platform.szxyzxx.resultsStatistical.service;

import platform.szxyzxx.resultsStatistical.pojo.AnalysisStudens;
import platform.szxyzxx.resultsStatistical.pojo.vo.AsQuery;

import java.text.ParseException;
import java.util.List;

public interface AnalysisStudensService {
    List<AnalysisStudens> findByAs(AsQuery acsQuery) throws ParseException;
}
