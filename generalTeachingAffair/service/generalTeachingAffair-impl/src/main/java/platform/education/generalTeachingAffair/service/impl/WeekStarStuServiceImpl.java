package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.WeekStarStuDao;
import platform.education.generalTeachingAffair.service.WeekStarStuService;
import platform.education.generalTeachingAffair.vo.WeekStarParamVo;
import platform.education.generalTeachingAffair.vo.WeekStarResonseVo;
import platform.education.generalTeachingAffair.vo.WeekStuTermVo;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/5/9 14:40
 * @Description:
 */
public class WeekStarStuServiceImpl implements WeekStarStuService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private WeekStarStuDao weekStarStuDao;

    public void setWeekStarStuDao(WeekStarStuDao weekStarStuDao) {
        this.weekStarStuDao = weekStarStuDao;
    }

    @Override
    public List<Integer> findCount(String name, Integer id, Integer termId) {
        return weekStarStuDao.findCount(name, id, termId);
    }

    @Override
    public String createWeekStarStu(WeekStuTermVo weekStuTermVo, Object[] obj, Integer weekId) {
        if (weekId == null) {
            weekStarStuDao.create(weekStuTermVo);
            weekId = weekStuTermVo.getId();
        } else {
            // 修改对应的上传文件uuid
            weekStuTermVo.setId(weekId);
            weekStarStuDao.update(weekStuTermVo);
        }
        weekStarStuDao.createWeekStarStu(weekId, obj);
        return "success";
    }

    @Override
    public List<WeekStarResonseVo> findWeekStarStu(WeekStarParamVo weekStarParamVo, Page page) {
        return weekStarStuDao.findWeekStarStu(weekStarParamVo, page, null);
    }

    @Override
    public List<WeekStarResonseVo> findMonthStar(WeekStarParamVo weekStarParamVo, Page page) {
        return weekStarStuDao.findMonthStar(weekStarParamVo, page, null);
    }

    @Override
    public List<Map<String, Object>> findWeekStarStuById(Integer id) {
        return weekStarStuDao.findWeekStarStuById(id);
    }

    @Override
    public List<Map<String, Object>> findMonthStarStu(String[] id, Integer limit) {
        return weekStarStuDao.findMonthStarStu(id, limit);
    }


    @Override
    public List<WeekStarResonseVo> findTermStar(WeekStarParamVo weekStarParamVo, Page page) {
        return weekStarStuDao.findTermStar(weekStarParamVo, page, null);
    }

    @Override
    public List<Map<String, Object>> findTheTeacherScoreList(Integer type, String year, Integer schoolId, Page page,String xq,String name) {
        return weekStarStuDao.findTheTeacherScoreListMain(year, type, schoolId, page, xq,name);
    }
}
