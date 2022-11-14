package platform.szxyzxx.exam.service;

import framework.generic.dao.Page;
import platform.szxyzxx.exam.vo.ExamQuery;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

public interface ExamScoreService {
    Map<String,Object> findByGrrenScore(UserInfo userInfo, ExamQuery examQuery, Page page);
    Map<String,Object> findByGrrenScoreDaochu(UserInfo userInfo, ExamQuery examQuery, Page page);
    List<Map<String, Object>> findByscoreStudentScore(UserInfo userInfo, ExamQuery examQuery);
    List<Map<String, Object>> findByTeamScore(UserInfo userInfo, ExamQuery examQuery);
    List<Map<String, Object>> findByTeamScoreDaoChu(UserInfo userInfo, ExamQuery examQuery);
    Map<String,Object> findByGradeScore(UserInfo userInfo, ExamQuery examQuery);
    Map<String,Object> findByGradeScoreDaochu(UserInfo userInfo, ExamQuery examQuery);
    List<Map<String,Object>> findByFenShuDuanFenXi(UserInfo userInfo, ExamQuery examQuery);
    int updateExamSubject(Integer examId,Integer zhuangtai);
   }
