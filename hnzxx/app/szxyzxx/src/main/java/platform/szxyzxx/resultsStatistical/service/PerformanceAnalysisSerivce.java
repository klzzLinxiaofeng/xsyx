package platform.szxyzxx.resultsStatistical.service;


import framework.generic.dao.Page;
import platform.szxyzxx.resultsStatistical.pojo.PerformanceAnalysis;
import platform.szxyzxx.resultsStatistical.pojo.vo.PaQuery;

import java.util.List;
import java.util.Map;

public interface PerformanceAnalysisSerivce {
    List<PerformanceAnalysis> findByAll(PaQuery paQuery, Page page);
    List<Map<String,Object>> findByQuery(PerformanceAnalysis obj);
    boolean add(PerformanceAnalysis d);
    boolean delete(Integer id);
}
