package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.WeekStarParamVo;
import platform.education.generalTeachingAffair.vo.WeekStarResonseVo;
import platform.education.generalTeachingAffair.vo.WeekStuTermVo;

import java.util.List;
import java.util.Map;

public interface WeekStarStuDao extends GenericDao<WeekStuTermVo, Integer> {

    void createWeekStarStu(Integer id, Object[] obj);

    List<WeekStarResonseVo> findWeekStarStu(WeekStarParamVo weekStarParamVo, Page page, Order order);

    List<Integer> findCount(String name, Integer teacherId, Integer termId);

    List<Map<String, Object>> findWeekStarStuById(Integer id);

    List<WeekStarResonseVo> findMonthStar(WeekStarParamVo weekStarParamVo, Page page, Order order);

    List<Map<String, Object>> findMonthStarStu(String[] id, Integer limit);

    List<WeekStarResonseVo> findTermStar(WeekStarParamVo weekStarParamVo, Page page, Object o);

    List<Map<String, Object>> findTheTeacherScoreListMain(String year, Integer type, Integer shcoolId, Page page, String xq,String name);

    List<Map<String, Object>> findTheTeacherScoreListOther(String year, String[] clas, Integer schoolId, Page page, Order order);
}
