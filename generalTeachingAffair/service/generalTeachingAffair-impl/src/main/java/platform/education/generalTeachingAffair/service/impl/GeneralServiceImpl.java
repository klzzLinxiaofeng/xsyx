package platform.education.generalTeachingAffair.service.impl;

import platform.education.generalTeachingAffair.dao.GeneralDao;
import platform.education.generalTeachingAffair.model.AmountTrendPojo;
import platform.education.generalTeachingAffair.service.GeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 大屏展示
 *
 * @author: yhc
 * @Date: 2020/12/6 10:41
 * @Description:
 */
public class GeneralServiceImpl implements GeneralService {

    private GeneralDao generalDao;

    public void setGeneralDao(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }


    /**
     * 充值总金额
     *
     * @param schoolId
     * @return
     */
    @Override
    public BigDecimal findAmount(Integer schoolId) {
        return generalDao.findAmount(schoolId);
    }


    /**
     * 充值趋势图
     *
     * @param schoolId
     * @param beginDate
     * @param endDate
     * @return
     */
    @Override
    public List<AmountTrendPojo> findSumAmount(Integer schoolId, String beginDate, String endDate) {
        return generalDao.findSumAmount(schoolId, beginDate, endDate);
    }

    /**
     * @param schoolId
     * @param flgMonth  是否查询当前月
     * @param userType  0学生 1教师
     * @param isSendFlg 是否查询已经发卡的条件
     * @return
     */
    @Override
    public Integer findReplaceCardTotal(Integer schoolId, boolean flgMonth, Integer userType, boolean isSendFlg) {
        return generalDao.findReplaceCardTotal(schoolId, flgMonth, userType, isSendFlg);
    }

    @Override
    public Integer findnotCardTeacherTotal(Integer schoolId) {
        return generalDao.findnotCardTeacherTotal(schoolId);
    }

    @Override
    public Integer findnotCardStuTotal(Integer schoolId) {
        return generalDao.findnotCardStuTotal(schoolId);
    }

    @Override
    public Integer findfeedbackTotal(Integer schoolId) {
        return generalDao.findfeedbackTotal(schoolId);
    }

    @Override
    public List<AmountTrendPojo> findFeedbackMonthTotal(Integer schoolId, String beginDate, String endDate) {
        return generalDao.findFeedbackMonthTotal(schoolId, beginDate, endDate);
    }

    @Override
    public List<AmountTrendPojo> findRepairMonthTotal(Integer schoolId, String beginDate, String endDate) {
        return generalDao.findRepairMonthTotal(schoolId, beginDate, endDate);
    }

    /**
     * 报修未处理
     *
     * @param schoolId
     * @return
     */
    @Override
    public Map<String, Integer> findRepairUntreated(Integer schoolId) {
        return generalDao.findRepairUntreated(schoolId);
    }
}
