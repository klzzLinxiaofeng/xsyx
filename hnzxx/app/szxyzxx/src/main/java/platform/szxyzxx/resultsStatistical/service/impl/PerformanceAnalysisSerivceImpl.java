package platform.szxyzxx.resultsStatistical.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.resultsStatistical.dao.PerformanceAnalysisMapper;
import platform.szxyzxx.resultsStatistical.pojo.PerformanceAnalysis;
import platform.szxyzxx.resultsStatistical.pojo.vo.PaQuery;
import platform.szxyzxx.resultsStatistical.service.PerformanceAnalysisSerivce;

import java.util.List;
import java.util.Map;

@Service
public class PerformanceAnalysisSerivceImpl implements PerformanceAnalysisSerivce {
    @Autowired
    private PerformanceAnalysisMapper mapper;
    @Override
    public List<PerformanceAnalysis> findByAll(PaQuery paQuery, Page page) {
        return mapper.findByAll(paQuery,page);
    }

    @Override
    public List<Map<String, Object>> findByQuery(PerformanceAnalysis obj) {
        return mapper.findByQuery(obj);
    }

    @Override
    public boolean add(PerformanceAnalysis d) {
            return mapper.create(d)>0;

    }

    @Override
    public boolean delete(Integer id) {
        return mapper.deleteById(id)>0;
    }
}
