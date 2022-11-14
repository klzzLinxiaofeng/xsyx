package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.WeekStarParamVo;
import platform.education.generalTeachingAffair.vo.WeekStarResonseVo;
import platform.education.generalTeachingAffair.vo.WeekStuTermVo;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/5/9 14:39
 * @Description: 周明星学生
 */
public interface WeekStarStuService {

    /**
     * 判断相同名称下上传的次数
     *
     * @param name
     * @param id
     * @param termId
     * @return
     */
    List<Integer> findCount(String name, Integer id, Integer termId);

    String createWeekStarStu(WeekStuTermVo weekStuTermVo, Object[] obj, Integer weekId);

    List<WeekStarResonseVo> findWeekStarStu(WeekStarParamVo weekStarParamVo, Page page);

    List<WeekStarResonseVo> findMonthStar(WeekStarParamVo weekStarParamVo, Page page);

    List<Map<String, Object>> findWeekStarStuById(Integer id);

    /**
     * xx星对应的学生排名分数信息
     *
     * @param id
     * @param limit
     * @return
     */
    List<Map<String, Object>> findMonthStarStu(String[] id, Integer limit);


    /**
     * 学期星
     *
     * @param weekStarParamVo
     * @param page
     * @return
     */
    List<WeekStarResonseVo> findTermStar(WeekStarParamVo weekStarParamVo, Page page);

    List<Map<String, Object>> findTheTeacherScoreList(Integer type, String year, Integer schoolId, Page page,String xq,String name);
}
