package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.AmountTrendPojo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 大屏展示
 *
 * @author: yhc
 * @Date: 2020/12/6 10:40
 * @Description:
 */

public interface GeneralService {

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
