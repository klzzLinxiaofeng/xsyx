package platform.szxyzxx.mentalhealth.service;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.mentalhealth.vo.MentalBehavior;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface MentalBehaviorService {
    List<MentalBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<MentalBehavior> findByAllTongji(UserInfo userInfo,
                                         String schoolYear,
                                         String schoolTrem,
                                         Integer gradeId,
                                         Integer teamId,
                                         String stuName,
                                         Page page);
}
