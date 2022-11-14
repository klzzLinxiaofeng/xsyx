package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.AmountTrendPojo;
import platform.education.generalTeachingAffair.model.PublicClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GeneralDao extends GenericDao<PublicClass, Integer> {

    /**
     * 获取当前学校的当日充值总额
     *
     * @param schoolId
     * @return
     */
    BigDecimal findAmount(Integer schoolId);

    List<AmountTrendPojo> findSumAmount(Integer schoolId, String beginDate, String endDate);

    Integer findReplaceCardTotal(Integer schoolId, boolean flgMonth, Integer userType, boolean isSendFlg);

    Integer findnotCardTeacherTotal(Integer schoolId);

    Integer findnotCardStuTotal(Integer schoolId);

    Integer findfeedbackTotal(Integer schoolId);

    List<AmountTrendPojo> findFeedbackMonthTotal(Integer schoolId, String beginDate, String endDate);

    List<AmountTrendPojo> findRepairMonthTotal(Integer schoolId, String beginDate, String endDate);

    Map<String, Integer> findRepairUntreated(Integer schoolId);
}
