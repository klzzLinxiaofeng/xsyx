package platform.szxyzxx.culturalideal.service;

import framework.generic.dao.Page;
import platform.szxyzxx.culturalideal.vo.IdealBehavior;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */

public interface IdealBehaviorService {
    List<IdealBehavior> findByAll(QueryPojo queryPojo, Page page);
    List<IdealBehavior> findByAllTongji(UserInfo userInfo,
                                       String schoolYear,
                                       String schoolTrem,
                                       Integer gradeId,
                                       Integer teamId,
                                       String stuName,
                                       Page page);
}
