package platform.szxyzxx.indicators.service;

import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.indicators.pojo.IndicatorsPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface IndicatorsService {
    List<Grade> findBygrade(UserInfo user, String sch);
    List<IndicatorsPojo> findByAll(UserInfo user,Integer gradeId,String schoolYear);
    Boolean create(IndicatorsPojo indicatorsPojo,UserInfo user);
}
