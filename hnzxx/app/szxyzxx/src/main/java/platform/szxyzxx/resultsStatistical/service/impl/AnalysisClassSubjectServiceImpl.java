package platform.szxyzxx.resultsStatistical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.resultsStatistical.dao.AnalysisClassSubjectMapper;
import platform.szxyzxx.resultsStatistical.pojo.AnalysisClassSubject;
import platform.szxyzxx.resultsStatistical.pojo.vo.AcsQuery;
import platform.szxyzxx.resultsStatistical.service.AnalysisClassSubjectService;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisClassSubjectServiceImpl implements AnalysisClassSubjectService {

    @Autowired
    private AnalysisClassSubjectMapper mapper;
    @Override
    public List<AnalysisClassSubject> findByAll(AcsQuery acsQuery) {
        return mapper.findByAcs(acsQuery);
    }

    @Override
    public List<Map<String, Object>> findByGrade() {
        return mapper.findByGrade();
    }
}
