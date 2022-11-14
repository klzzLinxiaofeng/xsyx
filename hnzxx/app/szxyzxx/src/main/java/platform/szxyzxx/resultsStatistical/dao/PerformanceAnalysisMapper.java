package platform.szxyzxx.resultsStatistical.dao;


import framework.generic.dao.Page;
import platform.szxyzxx.resultsStatistical.pojo.PerformanceAnalysis;
import platform.szxyzxx.resultsStatistical.pojo.vo.PaQuery;

import java.util.List;
import java.util.Map;

public interface PerformanceAnalysisMapper {
    List<PerformanceAnalysis> findByAll(PaQuery p,Page page);

    int create(PerformanceAnalysis record);

    List<Map<String,Object>> findByQuery(PerformanceAnalysis obj);
    int deleteById(Integer id);
}
